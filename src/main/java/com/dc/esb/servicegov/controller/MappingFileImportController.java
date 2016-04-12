package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.InterfaceHead;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.export.task.ExportWSDLTask;
import com.dc.esb.servicegov.inport.task.MappingFileImportTask;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.ExcelTool;
import com.dc.esb.servicegov.vo.MappingImportIndexRowVO;
import com.dc.esb.servicegov.vo.MappingIndexHeadIndexVO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * 映射文档导入处理
 */
@Controller
@RequestMapping("/mappingImport")
public class MappingFileImportController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private LogInfoServiceImpl logInfoService;
    @Autowired
    private MappingFileImportSeviceImpl mappingFileImportSevice;
    @Autowired
    private OperationServiceImpl operationService;

    @Autowired
    private SDAServiceImpl sdaService;


    @RequiresPermissions({"importExcel-update"})
    @RequestMapping(method = RequestMethod.POST, value = "/fieldImport")
    public ModelAndView importFieldMapping(@RequestParam("file")
                                   MultipartFile file, Model model, HttpServletResponse response, @RequestParam("select")
                                   String operateFlag) {
        OperationLog operationLog = systemLogService.record("字段映射文档", "导入", "文件名称：" + file.getOriginalFilename());

        ModelAndView mv = new ModelAndView("sysadmin/fieldmapping_import");//导入完成后还是跳转到导入页面，页面显示结果信息，刷新导入日志
        String msg = importMappingFile(file, operateFlag);
        mappingFileImportSevice.setMsg(null);
        mv.addObject("message", msg);

        systemLogService.updateResult(operationLog);
        return mv;

    }
    /**
     * @param file 导入文件
     * @param operateFlag 覆盖标志
     * @return 处理结果
     */
    public String importMappingFile(MultipartFile file, String operateFlag){
        if(null == file){
            return "导入文件不能为空!";
        }
        boolean flag =  "Y".equalsIgnoreCase(operateFlag) ? true : false;
        mappingFileImportSevice.setOperateFlag(flag);
        String extensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        Workbook workbook = null;
        InputStream is = null;
        try {
            is = file.getInputStream();
            if (extensionName.toLowerCase().equals(mappingFileImportSevice.XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (extensionName.toLowerCase().equals(mappingFileImportSevice.XLSX)) {
                workbook = new XSSFWorkbook(is);
            } else {
                mappingFileImportSevice.logMsg("导入文档格式不正确!");
                return  mappingFileImportSevice.getMsg();
            }
            Sheet indexSheet = workbook.getSheet(mappingFileImportSevice.INDEX_SHEET_NAME);//读取index页
            if(null != indexSheet){
                Row row = indexSheet.getRow(0);
                MappingIndexHeadIndexVO headRowVO = new MappingIndexHeadIndexVO(row);//根据index页头列名称获取顺序

                // 初始化allReqHeadMetadatas,allResHeadMetadatas
                Set<String> allReqHeadMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.REQUEST_NAME);
                Set<String> allResHeadMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.RESPONSE_NAME);

//                //获取LocalHead
                Set<String> reqLocalHeadMetadatas = new HashSet<String>();
                Set<String> resLocalHeadMetadatas = new HashSet<String>();
                Set<String> allLocalHeadMetadatas = new HashSet<String>();
                String systemId = null;
                if(indexSheet.getRow(1) != null && indexSheet.getRow(1).getCell(11) != null){
                    MappingImportIndexRowVO indexRowVO = new MappingImportIndexRowVO(1, headRowVO, indexSheet.getRow(1));
                    systemId = indexRowVO.getInterfaceProId2().trim();
                    mappingFileImportSevice.getLocalHeadMetadatas(reqLocalHeadMetadatas,systemId,Constants.ElementAttributes.REQUEST_NAME);
                    mappingFileImportSevice.getLocalHeadMetadatas(resLocalHeadMetadatas,systemId,Constants.ElementAttributes.RESPONSE_NAME);
                }else{
                    mappingFileImportSevice.logMsg("获取Index页[接口提供系统ID]失败！");
                }
                allReqHeadMetadatas.addAll(reqLocalHeadMetadatas);
                allResHeadMetadatas.addAll(resLocalHeadMetadatas);
                allLocalHeadMetadatas.addAll(reqLocalHeadMetadatas);
                allLocalHeadMetadatas.addAll(resLocalHeadMetadatas);
//                Map<String, MappingImportIndexRowVO> distinctIndexVo = new HashMap<String, MappingImportIndexRowVO>();
//                List<MappingImportIndexRowVO> multiesIndexVo = new ArrayList<MappingImportIndexRowVO>();
//
//                for (int i = 1; i <= indexSheet.getLastRowNum(); i++) {
//                    if (indexSheet.getRow(i) != null && indexSheet.getRow(i).getCell(0) != null) {
//                        MappingImportIndexRowVO indexVO = new MappingImportIndexRowVO(i, headRowVO, indexSheet.getRow(i));//index页一条记录
//                        String key = indexVO.getServiceId() + indexVO.getOperationId();
//                        if(!distinctIndexVo.containsKey(key)){
//                            distinctIndexVo.put(key, indexVO);
//                        }else{
//                            multiesIndexVo.add(indexVO);
//                        }
//                    }
//                }
//                CountDownLatch countDown1 = new CountDownLatch(distinctIndexVo.size());
//                CountDownLatch countDown2 = new CountDownLatch(multiesIndexVo.size());
//
//                ExecutorService executor = Executors.newFixedThreadPool(20);
//
//                for(String key : distinctIndexVo.keySet()){
//                    MappingImportIndexRowVO indexVO = distinctIndexVo.get(key);
//                    MappingFileImportTask task = new MappingFileImportTask();
//                    task.init(workbook, indexVO, allReqHeadMetadatas, allResHeadMetadatas, countDown1, mappingFileImportSevice);
//                    executor.execute(task);
//                }
//                try {
//                    countDown1.await(60 * 5, TimeUnit.SECONDS);
//                } catch (InterruptedException e) {
//                    logger.error("mapping file import unique error : " + countDown1.getCount());
//                    e.printStackTrace();
//                }
//
//                for(MappingImportIndexRowVO indexVO : multiesIndexVo){
//                    MappingFileImportTask task = new MappingFileImportTask();
//                    task.init(workbook, indexVO, allReqHeadMetadatas, allResHeadMetadatas, countDown2, mappingFileImportSevice);
//                    executor.execute(task);
//                }
//
//                try {
//                    countDown1.await(60 * 5, TimeUnit.SECONDS);
//                } catch (InterruptedException e) {
//                    logger.error("mapping file import unique error : " + countDown2.getCount());
//                    e.printStackTrace();
//                }finally {
//                    executor.shutdown();
//                }
                long time2 = System.currentTimeMillis();
                //多线程属性初始化
                int poolSize = indexSheet.getLastRowNum();
                int taskNum = poolSize;//设置总任务数
                boolean isThreadPool = true;
                if(0 == taskNum){
                    taskNum = 1;
                    poolSize = 1;
                    isThreadPool = false;
                }else{
                    poolSize = poolSize > 20 ? 20 : poolSize;//允许最多20个线程
                }
                CountDownLatch countDown = new CountDownLatch(taskNum);
                ExecutorService executor = Executors.newFixedThreadPool(poolSize);//创建线程池

                for(int i =1; i <= indexSheet.getLastRowNum(); i++){//提取index每条记录
                    if(indexSheet.getRow(i) != null && indexSheet.getRow(i).getCell(0) != null){
                    MappingImportIndexRowVO indexVO = new MappingImportIndexRowVO(i, headRowVO, indexSheet.getRow(i));//index页一条记录
                    logger.info("===========开始导入INDEX页第" + indexVO.getIndexNum() + "条记录,接口代码[" + indexVO.getInterfaceId() + "]=============");
                    long time = System.currentTimeMillis();
                    try {
                        if(startThreads(workbook, indexVO, allReqHeadMetadatas, allResHeadMetadatas,allLocalHeadMetadatas,executor,countDown,isThreadPool)){//多线程处理
//                        if (mappingFileImportSevice.imoportIndexRecordWithMetadatas(workbook, indexVO, allReqHeadMetadatas, allResHeadMetadatas,allLocalHeadMetadatas)) {//单线程处理
                                //根据版本信息发布版本
                                if (Constants.Operation.LIFE_CYCLE_STATE_PUBLISHED.equalsIgnoreCase(indexVO.getOperationState())) {
                                    operationService.release(indexVO.getOperationId(), indexVO.getServiceId(), "导入发布");
                                }
                                long useTime = System.currentTimeMillis() - time;
                                logger.info("===========INDEX页第" + indexVO.getIndexNum() + "条记录导入完成，,接口代码[" + indexVO.getInterfaceId() + "]，耗时" + useTime + "ms=============");
                            }
                        }catch (Exception e){
                            mappingFileImportSevice.logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败,导入时发生异常！异常信息："+e.getMessage());
                            logger.error(e, e);
                            continue;
                        }
                    }
                }
                if(0 != indexSheet.getLastRowNum()){
                    try {
                        countDown.await();
                    } catch (InterruptedException e) {
                        logger.error("MappingFile export : " + countDown.getCount());
                        e.printStackTrace();
                    }finally {
                        executor.shutdown();
                    }
                }
                long useTime2 = System.currentTimeMillis() - time2;
                logger.info("=========导入完成，共耗时："+useTime2/1000+"秒！===========");
            }else{
               mappingFileImportSevice.logMsg("缺少INDEX页!");
                return  mappingFileImportSevice.getMsg();
            }
            Sheet indexExSheet = workbook.getSheet(mappingFileImportSevice.INDEX_EX_SHEET_NAME);//读取index_ex页
            if(null != indexExSheet){
                Row row = indexExSheet.getRow(0);
                MappingIndexHeadIndexVO headRowVO = new MappingIndexHeadIndexVO(row);//根据index页头列名称获取顺序
                for(int i =1; i <= indexExSheet.getLastRowNum(); i++){//提取index每条记录
                    if(indexExSheet.getRow(i) != null && indexExSheet.getRow(i).getCell(0) != null){
                        MappingImportIndexRowVO indexExVO = new MappingImportIndexRowVO(i, headRowVO, indexExSheet.getRow(i));//一条记录
                        logger.info("===========开始导入INDEX_EX页第" + indexExVO.getIndexNum() + "条记录=============");
                        long time = System.currentTimeMillis();
                        try {
                            if (mappingFileImportSevice.importIndexExRecord(workbook, indexExVO)) {
                                long useTime = System.currentTimeMillis() - time;
                                logger.info("===========INDEX_EX页第" + indexExVO.getIndexNum() + "条记录导入完成，耗时" + useTime + "ms=============");
                            }
                        }catch (Exception e){
                            mappingFileImportSevice.logMsg("index_ex页第" + indexExVO.getIndexNum() + "条记录导入失败,导入时发生异常！");
                            logger.error(e, e);
                            continue;
                        }
                    }
                }
            }
            if(StringUtils.isNotEmpty( mappingFileImportSevice.getMsg())){
                mappingFileImportSevice.setMsg("导入完成，但导入过程中有异常，详情见日志！");
            }else{
                mappingFileImportSevice.setMsg("导入成功!");
            }
        }catch (IOException e){
           mappingFileImportSevice.logMsg("读取文档内容时发生IO错误，请检查文档格式！异常信息：" + e.getMessage());
            logger.error(e, e);
        }catch (Exception e){
           mappingFileImportSevice.logMsg("导入出现异常，导入失败！异常信息：" + e.getMessage()+"======"+e.getCause()+"====="+e.getStackTrace());
            logger.error(e, e);
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
            mappingFileImportSevice.getInterfaceHeads().clear();
            return mappingFileImportSevice.getMsg();
        }

    }

    /**
     * 获取报文头所有元数据集合
     * @author yehu
     * @param structName
     * @return
     */
    public Set<String> getAllHeadMetadatas(String structName){
        Set<String> allHeadMetadatas =  new HashSet<String>();


        //填充reqsyshead
        SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, structName);
        List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
        if(null != reqSysHeadChildren){
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                inputMetadata(allHeadMetadatas, reqSysHeadChild);
            }
        }


        //填充reqapphead
        SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, structName);
        List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
        if(null != reqAppHeadChildren){
            for(SDA reqAppHeadChild : reqAppHeadChildren){
                inputMetadata(allHeadMetadatas, reqAppHeadChild);
            }
        }
        //若有localHead，则获取


        return allHeadMetadatas;
    }

    /**
     * 插入对应的元数据
     * @author yehu
     * @param metadataList
     * @param sda
     */
    public void inputMetadata(Set<String> metadataList, SDA sda){
        metadataList.add(sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                inputMetadata(metadataList, child);
            }
        }
    }

    /**
     * 导入映射文档多线程处理
     * @param workbook  映射文档
     * @param indexVO  头页
     * @param allReqMetadatas  所有请求部分头部元素
     * @param allResMetadatas  所有响应部分头部元素
     * @param allLocalHeadMetadatas  所有LocalHead部分元素
     * @param executor  线程池
     * @return  读取是否正常标志
     */
    public boolean startThreads(Workbook workbook, MappingImportIndexRowVO indexVO, Set<String> allReqMetadatas,Set<String> allResMetadatas,
                                Set<String> allLocalHeadMetadatas,ExecutorService executor,CountDownLatch countDown,boolean isThreadPool){
        if(isThreadPool){
            MappingFileImportTask threadTask = new MappingFileImportTask();
            threadTask.init(workbook,indexVO,allReqMetadatas,allResMetadatas,countDown,mappingFileImportSevice,allLocalHeadMetadatas);
            Future<Boolean> bool = executor.submit(threadTask);
            boolean bool2 = false;
            try {
                bool2 = bool.get();
            }catch (ExecutionException e){
                mappingFileImportSevice.logMsg("多线程导入错误！");
                logger.error(e, e);
                return false;
            }catch (InterruptedException e){
                mappingFileImportSevice.logMsg("获取线程返回值错误！！");
                logger.error(e, e);
                return false;
            }
            return bool2;
        }
        return mappingFileImportSevice.imoportIndexRecordWithMetadatas(workbook, indexVO, allReqMetadatas, allResMetadatas,allLocalHeadMetadatas);
    }
}

















