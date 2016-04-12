package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.Generator;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.task.*;
import com.dc.esb.servicegov.service.impl.GeneratorServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class ConfigBathGenerator {
    private static final Log logger = LogFactory.getLog(ConfigBathGenerator.class);
    private static String errorMsg = null;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private GeneratorServiceImpl generatorService;
    /**
     * 批量生成xml文件
     * @param list
     * @return 配置文件所在
     */
    public String generate(HttpServletRequest request, ConfigListVO list){
        if(validate(list)){
            String path = ConfigExportGenerator.class.getResource("/").getPath() + "/generator" + new Date().getTime();
//            for(ConfigVO configVO : list.getList()){
//                generate(request, configVO, path);
//            }
            generates(request,list,path);
            return path;
        }else{
            return null;
        }
    }

    /**
     * 多线程处理初始化
     * @param request
     * @param list
     * @param path
     */
    public void generates(HttpServletRequest request,ConfigListVO list,String path){
        List<String> consumerServiceInvokeIds = new ArrayList<String>();//所有消费者
        List<String> providerServiceInvokeIds = new ArrayList<String>();//所有提供者
        List<String> conGeneratorIds = new ArrayList<String>();
        List<String> proGeneratorIds = new ArrayList<String>();
        for(ConfigVO configVO : list.getList()){
            consumerServiceInvokeIds.add(configVO.getConsumerServiceInvokeId());
            providerServiceInvokeIds.add(configVO.getProviderServiceInvokeId());
            conGeneratorIds.add(configVO.getConGeneratorId());
            proGeneratorIds.add(configVO.getProGeneratorId());
        }
        List<ServiceInvoke> consumerServiceInvokes = new ArrayList<ServiceInvoke>();
        List<ServiceInvoke> providerServiceInvokes = new ArrayList<ServiceInvoke>();
        if(null != consumerServiceInvokeIds){
            for(String consumerServiceInvokeId:consumerServiceInvokeIds){
                consumerServiceInvokes.add(serviceInvokeService.findUniqueBy("invokeId", consumerServiceInvokeId));
            }
        }
        if(null != providerServiceInvokeIds){
            for(String providerServiceInvokeId:providerServiceInvokeIds){
                providerServiceInvokes.add(serviceInvokeService.findUniqueBy("invokeId", providerServiceInvokeId));
            }
        }
        List<Generator> conGenerators = new ArrayList<Generator>();
        List<Generator> proGenerators = new ArrayList<Generator>();
        if(null != conGeneratorIds){
            for(String conGeneratorId:conGeneratorIds){
                conGenerators.add(generatorService.findUniqueBy("id", conGeneratorId));
            }
        }
        if(null != proGeneratorIds){
            for(String proGeneratorId:proGeneratorIds){
                proGenerators.add(generatorService.findUniqueBy("id", proGeneratorId));
            }
        }
        //获得consumerServiceInvokes，providerServiceInvokes，conGenerators，proGenerators
        List<ConfigExportGenerator> conConfigExportGenerators = new ArrayList<ConfigExportGenerator>();
        if(null != consumerServiceInvokes){
            WebApplicationContext cont = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            if(null != conGenerators){
                for(Generator conGenerator:conGenerators){
                    conConfigExportGenerators.add((ConfigExportGenerator)cont.getBean(getSpringBeanName(conGenerator.getImplementsClazz())));
                }
            }
        }
        List<ConfigExportGenerator> proConfigExportGenerators = new ArrayList<ConfigExportGenerator>();
        if(null != providerServiceInvokes){
            WebApplicationContext cont = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            if(null != proGenerators){
                for(Generator proGenerator:proGenerators){
                    proConfigExportGenerators.add((ConfigExportGenerator)cont.getBean(getSpringBeanName(proGenerator.getImplementsClazz())));
                }
            }
        }
        //初始化的一些工作完毕，开始线程
        if(null != conConfigExportGenerators && null != consumerServiceInvokes && null != providerServiceInvokes){
            List<ServiceInvoke> serviceInvokes = new ArrayList<ServiceInvoke>();
            serviceInvokes.addAll(consumerServiceInvokes);
            serviceInvokes.addAll(providerServiceInvokes);
            startThreads(conConfigExportGenerators,proConfigExportGenerators,serviceInvokes,path);
        }
    }

    /**
     * 线程开始
     * @param
     * @param serviceInvokes
     * @param path
     */
    public void startThreads(List<ConfigExportGenerator> conConfigExportGenerators,List<ConfigExportGenerator> proConfigExportGenerators,
                             List<ServiceInvoke> serviceInvokes,String path){
        //去掉for循环加入新线程池
        //对serviceInvokes进行分类
//        ConfigExportGenerator configExportGenerator = configExportGenerators.get(0);
        List<ServiceInvoke> serviceInvokesIns = new ArrayList<ServiceInvoke>();//In端
        List<ServiceInvoke> serviceInvokesOuts = new ArrayList<ServiceInvoke>();//Out端
        String pathIn = null;
        String pathOut = null;
        if(null != serviceInvokes){
            for(ServiceInvoke serviceInvoke:serviceInvokes){
                if(null != serviceInvoke){
                    if(Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType())){
                        serviceInvokesIns.add(serviceInvoke);
                        pathIn = path + File.separator + "in_config";
                    }
                    if(Constants.INVOKE_TYPE_PROVIDER.equals(serviceInvoke.getType())){
                        serviceInvokesOuts.add(serviceInvoke);
                        pathOut = path + File.separator + "out_config";
                    }
                }else{
                    logger.error("导出错误！无法识别是消费者还是提供者！");
                }
            }
            /*********Out端线程池2个**********/
            int taskOutCount = serviceInvokesOuts.size();
            int outPoolSize = taskOutCount > 10 ? 10 : taskOutCount;
            List<ExportConfigOutReqTask> taskOutReq = new ArrayList<ExportConfigOutReqTask>();
            List<ExportConfigOutResTask> taskOutRes = new ArrayList<ExportConfigOutResTask>();
            CountDownLatch countDownOut1 = new CountDownLatch(taskOutCount);
            CountDownLatch countDownOut2 = new CountDownLatch(taskOutCount);
            ExecutorService executorReq = null;
            ExecutorService executorRes = null;
            if(null != serviceInvokesOuts && null != proConfigExportGenerators && null != pathOut){
                executorReq = Executors.newFixedThreadPool(outPoolSize);
                executorRes = Executors.newFixedThreadPool(outPoolSize);
                int i = 0;
                for(ServiceInvoke serviceInvokeOut:serviceInvokesOuts){
                    //out端请求线程
                    ExportConfigOutReqTask taskReq = new ExportConfigOutReqTask();
                    taskReq.init(proConfigExportGenerators.get(i),serviceInvokeOut,pathOut,countDownOut1);
                    taskOutReq.add(taskReq);
                    //out端响应线程
                    ExportConfigOutResTask taskRes = new ExportConfigOutResTask();
                    taskRes.init(proConfigExportGenerators.get(i),serviceInvokeOut,pathOut,countDownOut2);
                    i++;
                    taskOutRes.add(taskRes);
                }
                for(ExportConfigOutReqTask t:taskOutReq){
                    executorReq.execute(t);
                }
                for(ExportConfigOutResTask t:taskOutRes){
                    executorRes.execute(t);
                }
            }else{
                logger.error("out端导入错误！");
            }
            //serviceInvokesIns，path，configExportGenerators

            startIn(conConfigExportGenerators, proConfigExportGenerators, serviceInvokes, pathIn, pathOut, serviceInvokesIns);

            if(null != serviceInvokesOuts && null != proConfigExportGenerators && null != pathOut){
                try {
                    countDownOut1.await();
                    countDownOut2.await();
                } catch (InterruptedException e) {
                    logger.error("ConfigOut export : " + countDownOut1.getCount());
                    e.printStackTrace();
                }finally {
                    executorReq.shutdown();
                    executorRes.shutdown();
                }
            }

            }
    }
    public void startIn(List<ConfigExportGenerator> conConfigExportGenerators,List<ConfigExportGenerator> proConfigExportGenerators,
                         List<ServiceInvoke> serviceInvokes,String pathIn, String pathOut,List<ServiceInvoke> serviceInvokesIns){
        logger.info("开始out端导入......");
        /**********out端线程2个********/
        //serviceInvokesOuts，path
        int taskInCount = serviceInvokesIns.size();//In端总任务数
        int inPoolSize = taskInCount > 10 ? 10 : taskInCount;//In端开启的线程数
        List<ExportConfigInReqTask> taskInReq = new ArrayList<ExportConfigInReqTask>();//In端请求任务全部放入
        List<ExportConfigInResTask> taskInRes = new ArrayList<ExportConfigInResTask>();//In端响应任务全部放入
        CountDownLatch countDownIn1 = new CountDownLatch(taskInCount);
        CountDownLatch countDownIn2 = new CountDownLatch(taskInCount);
        ExecutorService executorReq = null;
        ExecutorService executorRes = null;
        if(null != serviceInvokesIns && null != conConfigExportGenerators && null != pathIn){//In端的请求与响应均放在if中
            executorReq = Executors.newFixedThreadPool(inPoolSize);
            executorRes = Executors.newFixedThreadPool(inPoolSize);
            int i =0;
            for(ServiceInvoke serviceInvokesIn:serviceInvokesIns){
                //In端请求线程
                ExportConfigInReqTask taskReq = new ExportConfigInReqTask();
                taskReq.init(conConfigExportGenerators.get(i),serviceInvokesIn,pathIn,countDownIn1);
                taskInReq.add(taskReq);
                //In端响应线程
                ExportConfigInResTask taskRes = new ExportConfigInResTask();
                taskRes.init(conConfigExportGenerators.get(i),serviceInvokesIn,pathIn,countDownIn2);
                taskInRes.add(taskRes);
                i++;
            }
            for(ExportConfigInReqTask t:taskInReq){
                executorReq.execute(t);
            }
            for(ExportConfigInResTask t:taskInRes){
                executorRes.execute(t);
            }
        }else{
            logger.error("导出错误！ServiceInvoke为空！");
        }
        startSerDefOut(conConfigExportGenerators, proConfigExportGenerators, serviceInvokes, pathIn, pathOut);

        if(null != serviceInvokesIns && null != conConfigExportGenerators && null != pathIn){
            try {
                countDownIn1.await();
                countDownIn2.await();
            } catch (InterruptedException e) {
                logger.error("ConfigOut export : " + countDownIn1.getCount());
                e.printStackTrace();
            }finally {
                executorReq.shutdown();
                executorRes.shutdown();
            }
        }
    }


    public void startSerDefOut(List<ConfigExportGenerator> conConfigExportGenerators,List<ConfigExportGenerator> proConfigExportGenerators,
                               List<ServiceInvoke> serviceInvokes, String pathIn,String pathOut){
        logger.info("Out端服务定义开始导入......");
        /**********服务定义1个***********/
        //serviceInvokes
        int taskSerDefCount = serviceInvokes.size();
        int serDefPoolSize = taskSerDefCount > 10 ? 10 : taskSerDefCount;
        List<ExportConfigServiceDefTask> taskSerDefs = new ArrayList<ExportConfigServiceDefTask>();
        CountDownLatch countDownSerDef = new CountDownLatch(taskSerDefCount);
        ExecutorService executorSerDef = null;
        if(null != serviceInvokes && null != proConfigExportGenerators && null != pathOut){
            executorSerDef = Executors.newFixedThreadPool(serDefPoolSize);
            int i = 0;
            for(ServiceInvoke serviceInvoke:serviceInvokes){
                ExportConfigServiceDefTask taskSerDef = new ExportConfigServiceDefTask();
                taskSerDef.init(proConfigExportGenerators.get(0),serviceInvoke,pathOut,countDownSerDef);
                i++;
                taskSerDefs.add(taskSerDef);
            }
            for(ExportConfigServiceDefTask t:taskSerDefs){
                executorSerDef.execute(t);
            }
        }

        startSerDefIn(conConfigExportGenerators, proConfigExportGenerators,serviceInvokes, pathIn, pathOut);

        if(null != serviceInvokes && null != proConfigExportGenerators && null != pathOut){
            try {
                countDownSerDef.await();
            } catch (InterruptedException e) {
                logger.error("ConfigServiceDefine export : " + countDownSerDef.getCount());
                e.printStackTrace();
            }finally {
                executorSerDef.shutdown();
            }
        }

    }


    public void startSerDefIn(List<ConfigExportGenerator> conConfigExportGenerators,List<ConfigExportGenerator> proConfigExportGenerators,
                              List<ServiceInvoke> serviceInvokes, String pathIn,String pathOut){
        logger.info("In端服务定义开始导入......");
        /**********服务定义1个***********/
        //serviceInvokes
        int taskSerDefCount = serviceInvokes.size();
        int serDefPoolSize = taskSerDefCount > 10 ? 10 : taskSerDefCount;
        List<ExportConfigServiceDefTask> taskSerDefs = new ArrayList<ExportConfigServiceDefTask>();
        CountDownLatch countDownSerDef = new CountDownLatch(taskSerDefCount);
        if(null != serviceInvokes && null != conConfigExportGenerators && null != pathIn){
            ExecutorService executorSerDef = Executors.newFixedThreadPool(serDefPoolSize);
            int i = 0;
                for(ServiceInvoke serviceInvoke:serviceInvokes){
                    ExportConfigServiceDefTask taskSerDef = new ExportConfigServiceDefTask();
                    taskSerDef.init(conConfigExportGenerators.get(0),serviceInvoke,pathIn,countDownSerDef);
                    i++;
                    taskSerDefs.add(taskSerDef);
                }

                for(ExportConfigServiceDefTask t:taskSerDefs){
                    executorSerDef.execute(t);
                }
            try {
                countDownSerDef.await();
            } catch (InterruptedException e) {
                logger.error("ConfigServiceDefine export : " + countDownSerDef.getCount());
                e.printStackTrace();
            }finally {
                executorSerDef.shutdown();
            }
        }
    }
    /**
     * 生成一条交易的配置文件
     * @param configVO
     * @return
     */
    public File generate(HttpServletRequest request, ConfigVO configVO, String path){
        String consumerServiceInvokeId = configVO.getConsumerServiceInvokeId();
        String providerServiceInvokeId = configVO.getProviderServiceInvokeId();
        ServiceInvoke consumerServiceInvoke = serviceInvokeService.findUniqueBy("invokeId", consumerServiceInvokeId);
        ServiceInvoke providerServiceInvoke = serviceInvokeService.findUniqueBy("invokeId", providerServiceInvokeId);

        String conGeneratorId = configVO.getConGeneratorId();
        String proGeneratorId = configVO.getProGeneratorId();
        Generator conGenerator = generatorService.findUniqueBy("id", conGeneratorId);
        Generator proGenerator = generatorService.findUniqueBy("id", proGeneratorId);

        if(null != consumerServiceInvoke){//消费者
            WebApplicationContext cont = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            ConfigExportGenerator conConfigExportGenerator = (ConfigExportGenerator)cont.getBean(getSpringBeanName(conGenerator.getImplementsClazz()));
            conConfigExportGenerator.generate(consumerServiceInvoke, path);

//                Class conGeneratorClass = Class.forName(conGenerator.getImplementsClazz());
//                ConfigExportGenerator conConfigExportGenerator = (ConfigExportGenerator)conGeneratorClass.newInstance();
//                conConfigExportGenerator.generate(consumerServiceInvoke, path);
        }
        if(null != providerServiceInvoke){//提供者
            WebApplicationContext cont = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            ConfigExportGenerator proConfigExportGenerator = (ConfigExportGenerator)cont.getBean(getSpringBeanName(proGenerator.getImplementsClazz()));
            proConfigExportGenerator.generate(providerServiceInvoke, path);
        }

        return null;
    }

    /**
     * 数据正确性验证
     * @param configListVO
     * @return
     */
    public boolean validate(ConfigListVO configListVO){
        if(null == configListVO || 0 == configListVO.getList().size()){
            errorMsg = "传入数据为空！";
            return  false;
        }
        List<ConfigVO> list = configListVO.getList();
        for(int i = 0; i < list.size(); i++){
            ConfigVO configVO = list.get(i);
            if(null == configVO){
                errorMsg = "第"+ (i+1) + "条数据数据为空!";
            }else{
            }
        }
        return true;
    }
    public void generateErrorMsg(String errorMsg){
        if(StringUtils.isNotEmpty(errorMsg)){//如果导出过程中出现错误，将错误信息写入errorMsg.txt

        }
    }

    public String getSpringBeanName(String className){
        String[] classNameStrs = className.split("\\.");
        className = classNameStrs[classNameStrs.length-1];
        String firstChar = className.substring(0,1);
        String beanName = className.replaceFirst(firstChar, firstChar.toLowerCase());
        return  beanName;
    }
}
