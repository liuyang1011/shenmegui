package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.SystemDAOImpl;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.export.IExportableNode;
import com.dc.esb.servicegov.export.IMetadataConfigGenerator;
import com.dc.esb.servicegov.export.IPackerParserConfigGenerator;
import com.dc.esb.servicegov.export.bean.ExportBean;
import com.dc.esb.servicegov.export.exception.ExportException;
import com.dc.esb.servicegov.export.impl.ConfigBathGenerator;
import com.dc.esb.servicegov.export.impl.ConfigExportGenerator;
import com.dc.esb.servicegov.export.impl.StandardSOAPConfigGenerator;
import com.dc.esb.servicegov.export.impl.StandardXMLConfigGenerator;
import com.dc.esb.servicegov.export.task.ExportConfigTask;
import com.dc.esb.servicegov.export.task.ExportWSDLTask;
import com.dc.esb.servicegov.export.util.ExportUtil;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/7/14.
 */
@Controller
@RequestMapping("/export")
public class ConfigExportController {

    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    SystemService systemService;
    @Autowired
    SystemDAOImpl systemDAO;
    @Autowired
    ServiceInvokeService serviceInvokeService;
    @Autowired
    SDAService sdaService;
    @Autowired
    OperationServiceImpl operationService;
    @Autowired
    IdaService idaService;
    @Autowired
    StandardXMLConfigGenerator standardXMLConfigGenerator;
    @Autowired
    StandardSOAPConfigGenerator standardSOAPConfigGenerator;
    @Autowired
    InterfaceService interfaceService;
    @Autowired
    ProtocolService protocolService;
    @Autowired
    LogInfoServiceImpl logInfoService;
    @Autowired
    private InterfaceHeadServiceImpl interfaceHeadService;

    @Autowired
    private InterfaceInvokeServiceImpl interfaceInvokeService;

    private static String errorMsg = null;

    @Autowired
    ConfigBathGenerator configBathGenerator;
    /**
     * 导出配置文件
     *
     * @param serviceId            服务ID
     * @param operationId          操作ID
     * @param providerSystemId     提供系统ID
     * @param providerInterfaceId  提供接口ID
     * @param providerIsStandard   提供方是否为标准
     * @param consumerInterfaceId  消费方接口
     * @param consumerSystemId     消费方系统ID
     * @param consumerIsStandard   消费方是否为标准
     * @param providerStandardType
     * @param consumerStandardType
     * @param response
     * @return
     */
    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/exportHandle/{serviceId}/{operationId}/{providerSystemId}/{providerInterfaceId}/{providerIsStandard}/{consumerSystemId}/{consumerInterfaceId}/{consumerIsStandard}/{providerStandardType}/{consumerStandardType}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> export(@PathVariable String serviceId, @PathVariable String operationId,
                        @PathVariable String providerSystemId, @PathVariable String providerInterfaceId,
                        @PathVariable boolean providerIsStandard, @PathVariable String consumerInterfaceId,
                        @PathVariable String consumerSystemId, @PathVariable boolean consumerIsStandard,
                        @PathVariable String providerStandardType, @PathVariable String consumerStandardType,
                        HttpServletResponse response) {
        logger.info("开始导出配置,服务ID:[" + serviceId + "],操作ID:[" + operationId + "],提供者ID:[" + providerSystemId + "" +
                "],消费者ID:[" + consumerSystemId + "],提供接口ID:[" + providerInterfaceId + "],消费接口ID:[" + consumerInterfaceId + "]");
        OperationLog operationLog = systemLogService.record("服务", "配置导出", "服务ID:[" + serviceId + "],操作ID:[" + operationId + "],提供者ID:[" + providerSystemId + "" +
                "],消费者ID:[" + consumerSystemId + "],提供接口ID:[" + providerInterfaceId + "],消费接口ID:[" + consumerInterfaceId + "]");
        File in_file = null;
        //构造导出的条件Bean
        ExportBean export = new ExportBean(serviceId, operationId, providerSystemId, providerInterfaceId,
                providerIsStandard, consumerSystemId, consumerInterfaceId, consumerIsStandard);
        //sdaMap
        Map<String, String> sdaMap = new HashMap<String, String>();
        Map<String, String> idaMap = new HashMap<String, String>();
        sdaMap.put("serviceId", export.getServiceId());
        sdaMap.put("operationId", export.getOperationId());
//        sdaMap.put("interfaceId", export.getConsumerInterfaceId());
        List<SDA> sdas = sdaService.findBy(sdaMap);
        //这个又是什么鬼东西
        List<Ida> idas = idaService.findBy(idaMap);
        SDA SDARequest = null;
        SDA SDAResponse = null;
        for (SDA sda : sdas) {
            if (sda.getStructName().equalsIgnoreCase("request")) {
                SDARequest = sda;
                continue;
            }
            if (sda.getStructName().equalsIgnoreCase("response")) {
                SDAResponse = sda;
            }
            if (SDARequest != null && SDAResponse != null) {
                break;
            }
        }
        String requestText = "";
        String responseText = "";
        String requestSOAPText = "";
        String responseSOAPText = "";

        //消费方是否标准接口
        if (export.isConsumerIsStandard()) {
            requestText = ExportUtil.generatorServiceDefineXML(sdas, SDARequest);
            responseText = ExportUtil.generatorServiceDefineXML(sdas, SDAResponse);
            if (consumerStandardType.equalsIgnoreCase("xml")) {
                standardXMLConfigGenerator.init(requestText, responseText);
                in_file = standardXMLConfigGenerator.generatorIn(idas, sdas, export);
            } else if (consumerStandardType.equalsIgnoreCase("soap")) {
                requestSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDARequest);
                responseSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDAResponse);
                standardSOAPConfigGenerator.init(requestText, responseText, requestSOAPText, responseSOAPText);
                in_file = standardSOAPConfigGenerator.generatorIn(idas, sdas, export);
            }
        } else {
            //非标准接口导出,我不得不改了
            try {
                IPackerParserConfigGenerator generator = getGenerator(response, export.getServiceId(), export.getOperationId(), export.getConsumerInterfaceId(), export.getConsumerSystemId());
                Map<String, String> param = new HashMap<String, String>();
                param.put("interfaceId", export.getConsumerInterfaceId());
                List<Ida> bodyNodes = idaService.findBy(param, "seq");
                List<List<? extends IExportableNode>> headers = new ArrayList<List<? extends IExportableNode>>();
                List<List<Ida>> headIdas = interfaceHeadService.getHeadersByInterfaceId(export.getConsumerInterfaceId());
                for(List<Ida> headIda : headIdas){
                    headers.add(headIda);
                }
                List<File> resultFiles = generator.generate(headers, bodyNodes, export.getServiceId(), export.getOperationId(),
                        export.getConsumerSystemId(), "consumer");
                in_file = resultFiles.get(0);
            } catch (Exception e) {
                logger.error(e,e);
                printMsg(response, e.getMessage());
            }
        }
        //提供方是否标准接口
        if (export.isProviderIsStandard()) {
            if (providerStandardType.equalsIgnoreCase("xml")) {
                standardXMLConfigGenerator.generatorOut(null, sdas, export);
            } else if (providerStandardType.equalsIgnoreCase("soap")) {
                standardSOAPConfigGenerator.generatorOut(idas, sdas, export);
            }
        } else {
            //非标准接口导出,我不得不改了
            try {
                IPackerParserConfigGenerator generator = getGenerator(response, export.getServiceId(),export.getOperationId(),export.getProviderInterfaceId(), export.getProviderSystemId());
                Map<String, String> param = new HashMap<String, String>();
                param.put("interfaceId", export.getProviderInterfaceId());
                List<Ida> bodyNodes = idaService.findBy(param, "seq");
                List<List<? extends IExportableNode>> headers = new ArrayList<List<? extends IExportableNode>>();
                List<List<Ida>> headIdas = interfaceHeadService.getHeadersByInterfaceId(export.getProviderInterfaceId());
                for(List<Ida> headIda : headIdas){
                    headers.add(headIda);
                }
                List<File> resultFiles = generator.generate(headers, bodyNodes, export.getServiceId(), export.getOperationId(),
                        export.getConsumerSystemId(), "provider");
                in_file = resultFiles.get(0);
            } catch (Exception e) {
                logger.error(e,e);
                printMsg(response, e.getMessage());
            }
        }
        String path = in_file.getPath();
        ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");
        InputStream in = null;
        OutputStream out = null;
        try {
            File metadata = new File(path + "/metadata.zip");

            response.setContentType("application/zip");
            response.addHeader("Content-Disposition",
                    "attachment;filename=metadata.zip");
            in = new BufferedInputStream(new FileInputStream(metadata));
            out = new BufferedOutputStream(response.getOutputStream());
            long fileLength = metadata.length();
            byte[] cache = null;
            if (fileLength > Integer.MAX_VALUE) {
                cache = new byte[Integer.MAX_VALUE];
            } else {
                cache = new byte[(int) fileLength];
            }
            int i = 0;
            while ((i = in.read(cache)) > 0) {
                out.write(cache, 0, i);
            }
            out.flush();

            systemLogService.updateResult(operationLog);

        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            try {
                in.close();
                out.close();
                FileUtil.deleteDirectory(new File(path).getParent());
            } catch (Exception e) {
                logger.error("导出文件，关闭流异常," + e.getMessage(),e);
            }
        }

        return null;
    }


    private IPackerParserConfigGenerator getGenerator(HttpServletResponse response, String serviceId, String operationId, String interfaceId, String systemId) throws Exception {
        IPackerParserConfigGenerator generator = null;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", serviceId);
        paramMap.put("operationId", operationId);
        paramMap.put("interfaceId", interfaceId);
        paramMap.put("systemId", systemId);
        ServiceInvoke invoke = serviceInvokeService.findUniqueBy(paramMap);
        if (invoke != null) {
            String protocolId = invoke.getProtocolId();
            if (protocolId == null || "".equals(protocolId)) {
                String msg = "接口未关联协议，导出失败";
                if(StringUtils.isNotEmpty(invoke.getType())){
                    if(Constants.INVOKE_TYPE_PROVIDER.equalsIgnoreCase(invoke.getType())){
                        msg = "提供方接口未关联协议，导出失败";
                    }
                    if(Constants.INVOKE_TYPE_CONSUMER.equalsIgnoreCase(invoke.getType())){
                        msg = "消费方接口未关联协议，导出失败";
                    }
                }
                logger.error(msg);
//                logInfoService.saveLog("消费方接口未关联协议，导出失败", "导出");
                printMsg(response, msg);
                return null;
            } else {
                Protocol protocol = protocolService.getById(protocolId);
                Generator generatorClass = protocol.getGenerator();
                if(null != generatorClass){
                    try {
                        Class c = Class.forName(generatorClass.getImplementsClazz());
                        generator = (IPackerParserConfigGenerator) c.newInstance();
                    } catch (Exception e) {
                        logger.error("接口协议报文生成类反射失败，导出失败");
//                    logInfoService.saveLog("消费方接口协议报文生成类反射失败，导出失败", "导出");
                        printMsg(response, "接口协议报文生成类反射失败，导出失败");
                        throw new ExportException("接口协议报文生成类反射失败，导出失败");
                    }
                }else{
                    printMsg(response, "接口协议报文没有关联生成类，导出失败");
                }
            }
        }
        return generator;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getSystem/{serviceId}/{operationId}/{type}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Map<String, String>> getSystemAll(@PathVariable String serviceId, @PathVariable String operationId, @PathVariable String type) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", serviceId);
        paramMap.put("operationId", operationId);
        paramMap.put("type", type);
        List<ServiceInvoke> invokes = serviceInvokeService.findBy(paramMap);

        Map<String, String> map = new HashMap<String, String>();


        for (ServiceInvoke system : invokes) {
            if (!contains(resList, system.getSystemId())) {
                map = new HashMap<String, String>();
                map.put("id", system.getSystemId());
                map.put("text", system.getSystem().getSystemAb());
                resList.add(map);
            }
        }
        return resList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getInterface/{serviceId}/{operationId}/{type}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Map<String, String>> getInterfaceAll(@PathVariable String serviceId, @PathVariable String operationId, @PathVariable String type) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", serviceId);
        paramMap.put("operationId", operationId);
        paramMap.put("type", type);
        List<ServiceInvoke> invokes = serviceInvokeService.findBy(paramMap);

        Map<String, String> map = new HashMap<String, String>();


        for (ServiceInvoke system : invokes) {
            map = new HashMap<String, String>();
            map.put("id", system.getInterfaceId());
            Interface inter = new Interface();
            inter = system.getInter();
            if (null == inter) continue;
            map.put("text", inter.getInterfaceName());
            resList.add(map);
        }
        return resList;
    }

    private boolean contains(List<Map<String, String>> mapList, String systemId) {
        for (Map<String, String> map : mapList) {
            Iterator iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String value = map.get(iter.next());
                if (systemId.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printMsg(HttpServletResponse response, String message) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/html; charset=utf-8");
            pw = response.getWriter();
            pw.print("<script language='javascript'>alert('" + message + "');closeDialog();</script>");
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }

    /*根据场景列表导出字段映射*/
    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/getConfigVo/{consumer}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<ConfigVO> getConfigVo(@RequestBody List list,@PathVariable String consumer) {
        List<ConfigVO> result = operationService.getConfigVo(list);
        if(!"null".equals(consumer)){//前端送的是null用于标识
            System system = systemDAO.getEntity(consumer);
            String systemName = system.getSystemChineseName();
            for(Iterator<ConfigVO> it = result.iterator();it.hasNext();){
                ConfigVO configVO = it.next();
                if(!systemName.equals(configVO.getConsumerName())){
                    it.remove();//去除不想要的集合
                }
            }
        }
        return result;
    }

    /**
     * 批量导出
     * @param request
     * @param response
     * @param list 一条交易配置导出信息
     * @return
     */
    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportBatch", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> exportBatch(HttpServletRequest request, HttpServletResponse response, ConfigListVO list) {
        logger.info("**************开始导出配置文件......**************");
        long time = java.lang.System.currentTimeMillis();
        OperationLog operationLog = systemLogService.record("导出", "配置文件导出","");

        String path  = configBathGenerator.generate(request, list);


        //加入多线程
//        int poolSize =  list.getList().size()>20 ? 20 : list.getList().size();
//        String path = ConfigExportGenerator.class.getResource("/").getPath() + "/generator" + new Date().getTime();
//        if(validate(list)){
//            List<ExportConfigTask> taskLst = new ArrayList<ExportConfigTask>();
//            CountDownLatch countDown = new CountDownLatch(list.getList().size());
//            for(ConfigVO configVO : list.getList()){
//                ExportConfigTask task = new ExportConfigTask();
//                task.init(configBathGenerator,request,configVO,path,countDown);
//                taskLst.add(task);
//            }
//            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
//            for (ExportConfigTask t : taskLst) {
//                executor.execute(t);
//            }
//            try {
//                countDown.await();
//            } catch (InterruptedException e) {
//                logger.error("Config export : " + countDown.getCount());
//                e.printStackTrace();
//            }
//        }

        if(StringUtils.isNotEmpty(path)){
            ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");
            InputStream in = null;
            OutputStream out = null;
            try {
                File metadata = new File(path + "/metadata.zip");

                response.setContentType("application/zip");
                response.addHeader("Content-Disposition",
                        "attachment;filename=metadata.zip");
                in = new BufferedInputStream(new FileInputStream(metadata));
                out = new BufferedOutputStream(response.getOutputStream());
                long fileLength = metadata.length();
                byte[] cache = null;
                if (fileLength > Integer.MAX_VALUE) {
                    cache = new byte[Integer.MAX_VALUE];
                } else {
                    cache = new byte[(int) fileLength];
                }
                int i = 0;
                while ((i = in.read(cache)) > 0) {
                    out.write(cache, 0, i);
                }
                out.flush();
            } catch (Exception e) {
                logger.error(e,e);
                printMsg(response, "导出配置文件出现错误,请检查数据！");
            } finally {
                try {
                    in.close();
                    out.close();
                    FileUtil.deleteDirectory(new File(path));
                } catch (Exception e) {
                    logger.error("导出配置文件，关闭流异常," + e.getMessage(),e);
                }

            }
        }
        systemLogService.updateResult(operationLog);
        long useTime = java.lang.System.currentTimeMillis() - time;
        logger.info("**************导出配置文件共耗时:"+useTime/1000+"秒**************");
        return null;
    }
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

    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.GET  , value = "/exportBatchAll", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> exportBatchAll(HttpServletRequest request, HttpServletResponse response) {
        List<InterfaceInvoke> interfaceInvokes = interfaceInvokeService.getAll();
        List<ConfigVO> configVOs = new ArrayList<ConfigVO>();

        for(InterfaceInvoke interfaceInvoke : interfaceInvokes){
            ConfigVO configVO = new ConfigVO();
            configVO.setConsumerServiceInvokeId(interfaceInvoke.getConsumerInvokeId());
            configVO.setProviderServiceInvokeId(interfaceInvoke.getProviderInvokeId());
            configVO.setConGeneratorId("1");
            configVO.setProGeneratorId("789d7027-efa0-4363-a067-f7b809b4fb8d");
            configVOs.add(configVO);
        }

        ConfigListVO list = new ConfigListVO();
        list.setList(configVOs);
        logger.info("**************开始导出配置文件......**************");
        long time = java.lang.System.currentTimeMillis();
        OperationLog operationLog = systemLogService.record("导出", "配置文件导出","");

        String path  = configBathGenerator.generate(request, list);

        if(StringUtils.isNotEmpty(path)){
            ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");
            InputStream in = null;
            OutputStream out = null;
            try {
                File metadata = new File(path + "/metadata.zip");

                response.setContentType("application/zip");
                response.addHeader("Content-Disposition",
                        "attachment;filename=metadata.zip");
                in = new BufferedInputStream(new FileInputStream(metadata));
                out = new BufferedOutputStream(response.getOutputStream());
                long fileLength = metadata.length();
                byte[] cache = null;
                if (fileLength > Integer.MAX_VALUE) {
                    cache = new byte[Integer.MAX_VALUE];
                } else {
                    cache = new byte[(int) fileLength];
                }
                int i = 0;
                while ((i = in.read(cache)) > 0) {
                    out.write(cache, 0, i);
                }
                out.flush();
            } catch (Exception e) {
                logger.error(e,e);
                printMsg(response, "导出配置文件出现错误,请检查数据！");
            } finally {
                try {
                    in.close();
                    out.close();
                    FileUtil.deleteDirectory(new File(path));
                } catch (Exception e) {
                    logger.error("导出配置文件，关闭流异常," + e.getMessage(),e);
                }

            }
        }
        systemLogService.updateResult(operationLog);
        long useTime = java.lang.System.currentTimeMillis() - time;
        logger.info("**************导出配置文件共耗时:"+useTime/1000+"秒**************");
        return null;
    }
}
