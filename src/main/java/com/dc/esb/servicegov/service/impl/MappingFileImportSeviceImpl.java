package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.service.MappingFileImportService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.ExcelTool;
import com.dc.esb.servicegov.vo.MappingIndexHeadIndexVO;
import com.dc.esb.servicegov.vo.MappingImportIndexRowVO;
import com.dc.esb.servicegov.vo.MappingSheetIndexVO;
import com.dc.esb.servicegov.vo.MappingSheetRowVO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional
public class MappingFileImportSeviceImpl extends AbstractBaseService implements MappingFileImportService {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    LogInfoServiceImpl logInfoService;

    @Autowired
    private ServiceCategoryDAOImpl serviceCategoryDAO;
    @Autowired
    private ServiceDAOImpl serviceDAO;
    @Autowired
    private OperationDAOImpl operationDAO;
    @Autowired
    private InterfaceDAOImpl interfaceDAO;
    @Autowired
    private SystemProtocolDAOImpl systemProtocolDAO;
    @Autowired
    private InterfaceHeadDAOImpl interfaceHeadDAO;
    @Autowired
    private InterfaceHeadRelateDAOImpl interfaceHeadRelateDAO;
    @Autowired
    private ServiceInvokeDAOImpl serviceInvokeDAO;
    @Autowired
    private InterfaceInvokeDAOImpl interfaceInvokeDAO;
    @Autowired
    private MetadataDAOImpl metadataDAO;
    @Autowired
    private VersionServiceImpl versionService;
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private IdaServiceImpl idaService;
    @Autowired
    private SystemServiceImpl systemService;
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private MetadataServiceImpl metadataService;
    @Autowired
    private MetaTypeServiceImpl metaTypeService;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    InterfaceHeadRelateServiceImpl interfaceHeadRelateService;

    public final static String XLS = "xls";// Excel 2003
    public final static String XLSX = "xlsx";//Excel 2007
    public final static String INDEX_SHEET_NAME = "INDEX";//index页签名称
    public final static String INDEX_EX_SHEET_NAME = "INDEX_EXD";//index_EX页签名称
    private final static Integer SERVICE_DESC_ROW = 2;//服务描述所在行
    private final static Integer SERVICE_DESC_COL = 8;//服务描述所在列
    private final static Integer OPERATION_DESC_ROW = 3;//场景描述所在行
    private final static Integer OPERATION_DESC_COL = 8;//场景描述所在列

    private final static Integer INTERFACE_CODE_ROW = 0;//交易码所在列
    private final static Integer INTERFACE_CODE_COL = 1;//交易码所在列
    private final static Integer INTERFACE_NAME_ROW = 1;//交易名称所在列
    private final static Integer INTERFACE_NAME_COL = 1;//交易名称所在列
    private final static Integer INTERFACE_DES_ROW = 2;//交易名称所在列
    private final static Integer INTERFACE_DES_COL = 1;//交易名称所在列

    public static final String operateFlagStr = "Y";//覆盖标志
    private  boolean operateFlag = true;//覆盖标志,默认覆盖
    private Map<String, InterfaceHead> interfaceHeads = new HashMap<String, InterfaceHead>();//报文头名称缓存
    private String msg;

    private Set<String> allReqHeadMetadatas = null;
    private Set<String> allResHeadMetadatas = null;

    @Override
    public HibernateDAO<Interface, String> getDAO() {
        return interfaceDAO;
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
        this.operateFlag =  operateFlagStr.equals(operateFlag) ? true : false;

        String extensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        Workbook workbook = null;
        InputStream is = null;
        try {
            is = file.getInputStream();
            if (extensionName.toLowerCase().equals(XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (extensionName.toLowerCase().equals(XLSX)) {
                workbook = new XSSFWorkbook(is);
            } else {
                logMsg("导入文档格式不正确!");
                return  msg;
            }
            Sheet indexSheet = workbook.getSheet(INDEX_SHEET_NAME);//读取index页
            if(null != indexSheet){
                Row row = indexSheet.getRow(0);
                MappingIndexHeadIndexVO headRowVO = new MappingIndexHeadIndexVO(row);//根据index页头列名称获取顺序
                for(int i =1; i <= indexSheet.getLastRowNum(); i++){//提取index每条记录
                    MappingImportIndexRowVO indexVO = new MappingImportIndexRowVO(i, headRowVO, indexSheet.getRow(i));//index页一条记录
                    logger.info("===========开始导入INDEX页第" + indexVO.getIndexNum() + "条记录,接口代码[" + indexVO.getInterfaceId() + "]=============");
                    long time = java.lang.System.currentTimeMillis();
                    if(imoportIndexRecord(workbook, indexVO)){
                        long useTime = java.lang.System.currentTimeMillis() - time;
                        logger.info("===========INDEX页第" + indexVO.getIndexNum() + "条记录导入完成，,接口代码[" + indexVO.getInterfaceId() + "]，耗时" + useTime + "ms=============");
                    }
                }
            }else{
                logMsg("缺少INDEX页!");
                return  msg;
            }
            Sheet indexExSheet = workbook.getSheet(INDEX_EX_SHEET_NAME);//读取index_ex页
            if(null != indexExSheet){
                Row row = indexSheet.getRow(0);
                MappingIndexHeadIndexVO headRowVO = new MappingIndexHeadIndexVO(row);//根据index页头列名称获取顺序
                for(int i =1; i <= indexExSheet.getLastRowNum(); i++){//提取index每条记录
                    MappingImportIndexRowVO indexVO = new MappingImportIndexRowVO(i, headRowVO, indexExSheet.getRow(i));//一条记录
                    logger.info("===========开始导入INDEX_EX页第" + indexVO.getIndexNum() + "条记录=============");
                    long time = java.lang.System.currentTimeMillis();
                    if(importIndexExRecord(workbook, indexVO)){
                        long useTime = java.lang.System.currentTimeMillis() - time;
                        logger.info("===========INDEX_EX页第" + indexVO.getIndexNum() + "条记录导入完成，耗时" + useTime + "ms=============");
                    }
                }
            }
            if(StringUtils.isNotEmpty(msg)){
                msg = "导入完成，但导入过程中有异常，详情见日志！";
            }else{
                msg = "导入成功!";
            }
        }catch (IOException e){
            logMsg("读取文档内容时发生IO错误，请检查文档格式！");
            logger.error(e, e);
        }
        finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
            interfaceHeads.clear();
            return msg;
        }

    }

    /**
     * 跟index页一条记录，导入对应服务，场景，接口,报文头信息
     * @param workbook excel映射文件
     * @param indexVO  index页一条记录
     */
    public boolean imoportIndexRecord(Workbook workbook, MappingImportIndexRowVO indexVO){
        if(indexRecordDataValidate(workbook, indexVO, false)) {
            if (importService(workbook, indexVO)) { //导入服务
                if (importOperation(workbook, indexVO)) {//导入场景
                    if (importInterface(workbook, indexVO)) {//导入接口
                        if (!importServiceInvoke(indexVO)) {//建立关系
                            return false;
                        }
                        if (StringUtils.isNotEmpty(indexVO.getInterfaceHeadName())) {
                            List<InterfaceHead> interfaceHeadList = importInterfaceHead(workbook, indexVO);//导入报文头
                            if (null != interfaceHeadList && 0 < interfaceHeadList.size()) {
                                for(InterfaceHead interfaceHead : interfaceHeadList){
                                    if (!importInterfaceHeadRelate(indexVO, interfaceHead)) {//关联接口和报文头
                                        return false;
                                    }
                                }
                            } else {
                                return false;//错误信息已经在importInterfaceHead方法中注入
                            }
                        }
                        if (!importIdaSDAContent(workbook, indexVO)) {//导入接口ida,场景sda信息
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * 跟index页一条记录，导入对应服务，场景，接口,报文头信息
     * @param workbook excel映射文件
     * @param indexVO  index页一条记录
     */
    public boolean imoportIndexRecordWithMetadatas(Workbook workbook, MappingImportIndexRowVO indexVO, Set<String> allReqMetadatas,
                                                   Set<String> allResMetadatas,Set<String> allLocalHeadMetadatas){
        this.allReqHeadMetadatas = allReqMetadatas;
        this.allResHeadMetadatas = allResMetadatas;
        Set<String> reqLocalHeadMetadatas = new HashSet<String>();
        Set<String> resLocalHeadMetadatas = new HashSet<String>();
        List<String> headIdList = new ArrayList<String>();
        if(indexRecordDataValidate(workbook, indexVO, false)) {//校验数据是否正确
            if (importService(workbook, indexVO)) { //导入服务
                if (importOperation(workbook, indexVO)) {//导入场景
                    if (importInterface(workbook, indexVO)) {//导入接口
                        if (!importServiceInvoke(indexVO)) {//建立关系
                            return false;
                        }
                        if (StringUtils.isNotEmpty(indexVO.getInterfaceHeadName())) {
                            List<InterfaceHead> interfaceHeadList = importInterfaceHead(workbook, indexVO);//导入报文头
                            if (null != interfaceHeadList && 0 < interfaceHeadList.size()) {
                                for(InterfaceHead interfaceHead : interfaceHeadList){
                                    headIdList.add(interfaceHead.getHeadId());//获取所有的系统的headId
                                    if (!importInterfaceHeadRelate(indexVO, interfaceHead)) {//关联接口和报文头
                                        return false;
                                    }
                                }
                            } else {
                                return false;//错误信息已经在importInterfaceHead方法中注入
                            }
                            if(null == allLocalHeadMetadatas){//若是第一次导入localHead
                                List<SDA> sdas = null;
                                for(String headId : headIdList){
                                    sdas.addAll(sdaService.getLocalHeadByHeadId(headId));
                                }
                                for(SDA sda : sdas){
                                    reqLocalHeadMetadatas.add(divideLocalHead(sda,Constants.ElementAttributes.REQUEST_NAME));
                                    resLocalHeadMetadatas.add(divideLocalHead(sda,Constants.ElementAttributes.RESPONSE_NAME));
                                }
                                allReqHeadMetadatas.addAll(reqLocalHeadMetadatas);
                                allResHeadMetadatas.addAll(resLocalHeadMetadatas);
                            }
                        }
                        if (!importIdaSDAContent(workbook, indexVO)) {//导入接口ida,场景sda信息
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean importIndexExRecord(Workbook workbook, MappingImportIndexRowVO indexVO){
        if(indexRecordDataValidate(workbook, indexVO, true)){
            importService(workbook, indexVO);//建立服务、场景
            if(!importExdServiceInvoke(indexVO)){//建立关系
                return false;
            }
        }
        return true;
    }
    //校验记录数据是否正确
    public boolean indexRecordDataValidate(Workbook workbook, MappingImportIndexRowVO indexVO, boolean isIndexEx){
        String m = isIndexEx ? "index_exd页第" : "index页第";
        String interfaceId = indexVO.getInterfaceId();
        boolean flage = true;
        if(StringUtils.isEmpty(interfaceId) && !isIndexEx){//检查接口id是否为空, index_ex页不需要校验此数据
            logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：接口代码为空！");
            return false;
        }
        if(null == workbook.getSheet(interfaceId)&& !isIndexEx){
            logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：未找到对应" + indexVO.getInterfaceId() + "页！");
            return false;
        }else if(!isIndexEx){//校验Mapping页描述是否正确
            Sheet mappingSheet = workbook.getSheet(interfaceId);
            Row row1 = mappingSheet.getRow(0);
            String code = getCell(row1, 1).trim();
            if(null == code || "".equals(code)){
                logMsg("["+interfaceId+"]页面导入失败！交易码不存在！");
                flage = false;
            }
            String serviceName = getCell(row1, 8).trim();
            if(null == serviceName || "".equals(serviceName)){
                logMsg("["+interfaceId+"]页面导入失败！服务名称不存在！");
                flage = false;
            }
            Row row2 = mappingSheet.getRow(1);
            String codeName = getCell(row2,1).trim();
            if(null == codeName || "".equals(codeName)){
                logMsg("["+interfaceId+"]页面导入失败！交易名称不存在！");
                flage = false;
            }
            String serviceName2 = getCell(row2,8).trim();
            if(null == serviceName2 || "".equals(serviceName2)){
                logMsg("["+interfaceId+"]页面导入失败！原子服务名称不存在！");
                flage = false;
            }
            Row row3 = mappingSheet.getRow(2);
            String serviceDesc = getCell(row3,8).trim();
            if(null == serviceDesc || "".equals(serviceDesc)){
                logMsg("["+interfaceId+"]页面导入失败！服务描述不存在！");
                flage = false;
            }
            Row row4 = mappingSheet.getRow(3);
            String serviceDesc2 = getCell(row4,8).trim();
            if(null ==serviceDesc2 || "".equals(serviceDesc2)){
                logMsg("["+interfaceId+"]页面导入失败！原子服务描述不存在！");
                flage = false;
            }
        }
        String serviceId = indexVO.getServiceId();
        if(StringUtils.isNotEmpty(serviceId)){//检查服务id是否为空
            String categoryId = serviceId.substring(0, Constants.ServiceCategory.CATEGORY_CHILD_LENGTH);//服务类别id
            ServiceCategory serviceCategory = serviceCategoryDAO.getEntity(categoryId);
            if (null != serviceCategory){//检查服务类别是否存在
                String parentId = serviceId.substring(0, Constants.ServiceCategory.CATEGORY_PARENT_LENGTH);//大类id
                if (!parentId.equals(serviceCategory.getParentId())) {//检查服务大类是否存在
                    logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：服务大类[编码：" + parentId +"]不存在！");
                    return false;
                }
            }else{
                logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：服务类别[编码：" + categoryId + "]不存在！");
                return false;
            }
        }else{
            logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：服务名称解析失败！");
            return false;
        }
        if(StringUtils.isEmpty(indexVO.getOperationId())){//检查服务操作id是否为空
            logMsg(m + indexVO.getIndexNum() + "条记录导入失败，原因：服务操作ID为空！");
            return false;
        }
        String systemId = indexVO.getInterfaceProId();
        if(StringUtils.isEmpty(systemId)){
            logMsg( m + indexVO.getIndexNum() + "条记录导入失败，原因：接口提供系统ID为空！");
            return false;
        }
        if(null == systemService.findUniqueByName(systemId)){//接口系统id是否存在
            logMsg( m + indexVO.getIndexNum() + "条记录导入失败，原因：接口提供系统ID[" + systemId + "]对应系统不存在！");
            return false;
        }
        String consumerStr= indexVO.getConsumerAbs();
        if(StringUtils.isNotEmpty(consumerStr)){
            consumerStr = consumerStr.replace("，", ",");
            String[] consumerAbs = consumerStr.split("\\,");
            for(String consumerAb : consumerAbs){
                if(null == systemService.findUniqueByName(consumerAb)){//消费方系统是否存在
                    logMsg( m + indexVO.getIndexNum() + "条记录导入失败，原因：调用方[" + consumerAb + "]对应系统不存在！");
                    return false;
                }
            }
        }
        String providerStr= indexVO.getProviderAbs();
        if(StringUtils.isNotEmpty(providerStr)){
            providerStr = providerStr.replace("，", ",");
            String[] providerAbs = providerStr.split("\\,");
            for(String providerAb : providerAbs){
                if(null == systemService.findUniqueByName(providerAb)){//提供方系统是否存在
                    logMsg( m + indexVO.getIndexNum() + "条记录导入失败，原因：提供方[" + providerAb + "]对应系统不存在！");
                    return false;
                }
            }
        }
        String headNameStr = indexVO.getInterfaceHeadName();
        if(StringUtils.isNotEmpty(headNameStr)){
            String[] headNames = headNameStr.split("\\,");
            for(String headName : headNames){
                if(StringUtils.isNotEmpty(headName) && !isIndexEx){
                    if(null == workbook.getSheet(headName)){
                        logMsg( m + indexVO.getIndexNum() + "条记录导入失败，原因：未找到报文头页[" + headName + "]！");
                        return false;
                    }
                }
            }
        }
        return flage;
    }
    /**
     * 导入服务信息
     */
    public boolean importService(Workbook workbook, MappingImportIndexRowVO indexVO){
        com.dc.esb.servicegov.entity.Service service = null;
        String serviceId = indexVO.getServiceId();
        String interfaceId = indexVO.getInterfaceId();
        Sheet mappingSheet = workbook.getSheet(interfaceId);//找到对应的页面
        String serviceDesc = null;
        if(null != mappingSheet) {
            Row row = mappingSheet.getRow(SERVICE_DESC_ROW);//获取服务描述
            if (null != row) {
                serviceDesc = getCell(row, SERVICE_DESC_COL);
            }
        }
        //检查服务是否存在
        com.dc.esb.servicegov.entity.Service serviceDB = serviceDAO.getEntity(serviceId);
        if(null != serviceDB) {//服务已存在
            if (operateFlag) {//覆盖
                serviceDB.setServiceName(indexVO.getServiceName());
                if(StringUtils.isNotEmpty(serviceDesc)){
                    serviceDB.setDesc(serviceDesc);
                }
                service = serviceDB;
            }else{
                service = serviceDB;
            }
        }else{//服务不存在,创建服务
            service = new com.dc.esb.servicegov.entity.Service();
            service.setServiceId(serviceId);
            service.setServiceName(indexVO.getServiceName());
            service.setDesc(serviceDesc);
            String categoryId = serviceId.substring(0, Constants.ServiceCategory.CATEGORY_CHILD_LENGTH);
            service.setCategoryId(categoryId);
        }
        serviceDAO.save(service);
        return true;
    }
    /**
     * 导入场景信息
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean importOperation(Workbook workbook, MappingImportIndexRowVO indexVO){
        Operation operation = null;
        String operationId = indexVO.getOperationId();
        String serviceId = indexVO.getServiceId();
        String interfaceId = indexVO.getInterfaceId();
        Sheet mappingSheet = workbook.getSheet(interfaceId);
        String operationDesc = null;
        if(null != mappingSheet){
            Row row = mappingSheet.getRow(OPERATION_DESC_ROW);//获取服务操作描述所在行
            if(null != row){
                operationDesc = getCell(row, OPERATION_DESC_COL);//获取服务操作描述
            }
        }else{
            logMsg("获取"+interfaceId+"页面错误！");
            return false;
        }
        Operation operationDB = operationDAO.getBySO(serviceId, operationId);
        if(null != operationDB){//已存在该服务
            if(operateFlag){//覆盖
                operationDB.setOperationName(indexVO.getOperationName());
                operationDB.setOperationDesc(operationDesc);
                operationDB.setState(indexVO.getOperationState());
                operationDAO.save(operationDB);
                versionService.editVersion(operationDB.getVersionId());//修改版本号
                //删除已有sda
                sdaService.deleteByOS(serviceId, operationId);
            }
        }else{
            operation = new Operation();
            operation.setOperationName(indexVO.getOperationName());
            operation.setServiceId(serviceId);
            operation.setOperationId(operationId);
            operation.setOperationDesc(operationDesc);
            operation.setState(indexVO.getOperationState());
            operation.setHeadId(Constants.ServiceHead.DEFAULT_HEAD_ID);
            String versionId = versionService.addVersion(Constants.Version.TARGET_TYPE_OPERATION, operationId, Constants.Version.TYPE_ELSE);//初始化版本信息
            operation.setVersionId(versionId);
            operationDAO.save(operation);
        }
        return true;
    }
    /**
     * 导入接口信息
     */
    public boolean importInterface(Workbook workbook, MappingImportIndexRowVO indexVO){
        String interfaceId = indexVO.getInterfaceId();
        String interfaceState = indexVO.getInterfaceState();
        Sheet mappingSheet = workbook.getSheet(interfaceId);
        Row codeRow = mappingSheet.getRow(INTERFACE_CODE_ROW);
        String interfaceCode = getCell(codeRow, INTERFACE_CODE_COL);//交易码
        Row nameRow = mappingSheet.getRow(INTERFACE_NAME_ROW);
        String interfaceName =  getCell(nameRow, INTERFACE_NAME_COL);;//交易名称
        Row desRow = mappingSheet.getRow(INTERFACE_DES_ROW);
        String interfaceDes =  getCell(desRow, INTERFACE_DES_COL);;//交易描述

        if(null == interfaceId){
            logMsg("导入接口信息时，获取"+interfaceId+"页面错误！");
            return false;
        }else if(null == interfaceCode){
            logMsg("导入"+interfaceId+"页面接口信息时，获取交易码失败！");
        }else if(null == interfaceName){
            logMsg("导入"+interfaceId+"页面接口信息时，获取交易名称失败！");
        }else if(null == interfaceDes){
//            logMsg("导入"+interfaceId+"页面接口信息时，获取交易描述失败！");
        }


        Interface inter = interfaceDAO.findUniqueBy("interfaceId", interfaceId);
        if(null != inter){//接口已存在
            if(operateFlag){
                inter.setEcode(interfaceCode);
                inter.setInterfaceName(interfaceName);
                inter.setStatus(interfaceState);
                if(StringUtils.isNotEmpty(interfaceDes)){
                    inter.setDesc(interfaceDes);
                }
                interfaceDAO.save(inter);
                versionService.editVersion(inter.getVersionId());
                //删除接口相关ida
                idaService.deleteByInterfaceId(interfaceId);
                //更新版本
                versionService.editVersion(inter.getInterfaceId());
            }
        }else{
            inter = new Interface();
            inter.setInterfaceId(interfaceId);
            inter.setEcode(interfaceCode);
            inter.setInterfaceName(interfaceName);
            inter.setDesc(interfaceDes);
            String versionId = versionService.addVersion(Constants.Version.TARGET_TYPE_INTERFACE, inter.getInterfaceId(), Constants.Version.TYPE_ELSE);
            inter.setVersionId(versionId);
            interfaceDAO.save(inter);
        }
        return true;
    }
    /**
     * 导入报文头信息
     */
    public synchronized List<InterfaceHead> importInterfaceHead(Workbook workbook, MappingImportIndexRowVO indexVO){
        List<InterfaceHead> list = new ArrayList<InterfaceHead>();
        String[] headNames = indexVO.getInterfaceHeadName().split("\\,");//处理报文头页
        for(String headName : headNames){
            InterfaceHead interfaceHead = null;
            interfaceHead = interfaceHeads.get(headName);
            if(null == interfaceHead){//该报文头未导入过
                String systemId = systemService.findUniqueByName(indexVO.getInterfaceProId()).getSystemId();
                Map<String, String> param = new HashMap<String, String>();
                param.put("systemId", systemId);
                param.put("headName", headName);
                interfaceHead = interfaceHeadDAO.findUniqureBy(param);
                if(null != interfaceHead){//如果系统中已有该报文头

                    interfaceHeads.put(headName, interfaceHead);
                    if(operateFlag){
                        //删除报文头ida，sda
                        idaService.deleteByHeadId(interfaceHead.getHeadId());
                        sdaService.deleteByHeadId(interfaceHead.getHeadId());
                        insertHeadContent(workbook, indexVO, headName);
                    }
                }else{
                    Sheet headSheet = workbook.getSheet(headName);
                    if(null != headSheet){
                        //导入一条报文头
                        interfaceHead = new InterfaceHead();
                        interfaceHead.setHeadName(headName);
                        interfaceHead.setSystemId(systemId);
                        interfaceHeadDAO.save(interfaceHead);
                        interfaceHeads.put(headName, interfaceHead);
                        insertHeadContent(workbook, indexVO, headName);
                    }else{
                        logMsg( "index页第" + indexVO.getIndexNum() + "条记录导入失败，原因：未找到对应报文头[" + headName +"]页！");
                        logInfoService.saveLog(msg, "导入");
                        return null;
                    }
                }
            }
            list.add(interfaceHead);
        }
        return  list;
    }
    /**
     * 关联接口和报文头
     */
    public boolean importInterfaceHeadRelate(MappingImportIndexRowVO indexVO, InterfaceHead interfaceHead){
        Map<String, String> param = new HashMap<String, String>();
        param.put("interfaceId", indexVO.getInterfaceId());
        param.put("headId", interfaceHead.getHeadId());
        InterfaceHeadRelate interfaceHeadRelate = interfaceHeadRelateDAO.findUniqureBy(param);
        if(null == interfaceHeadRelate){//如果关联关系不存在
            interfaceHeadRelate = new InterfaceHeadRelate();
            interfaceHeadRelate.setInterfaceId(indexVO.getInterfaceId());
            interfaceHeadRelate.setHeadId(interfaceHead.getHeadId());
            interfaceHeadRelateDAO.save(interfaceHeadRelate);
        }
        return true;

    }
    public boolean insertHeadContent(Workbook workbook, MappingImportIndexRowVO indexVO, String headName){
        InterfaceHead interfaceHead = interfaceHeads.get(headName);//报文头已经存入缓存
        Sheet headSheet = workbook.getSheet(headName);
        Map<String, Ida> idas = idaService.genderHeadIdaAuto(interfaceHead.getHeadId());//自动生成根节点
        Map<String, SDA> sdas = sdaService.genderSDAAuto(interfaceHead.getHeadId());
        MappingSheetIndexVO sheetIndexVO = new MappingSheetIndexVO(headSheet);
        List<Ida> idaParents = new ArrayList<Ida>();
        idaParents.add(idas.get(Constants.ElementAttributes.REQUEST_NAME));//ida父节点
        List<SDA> sdaParents = new ArrayList<SDA>();
        sdaParents.add(sdas.get(Constants.ElementAttributes.REQUEST_NAME));//sda父节点
        //导入输入数据
        for(int i = sheetIndexVO.inputIndex; i < sheetIndexVO.outputIndex-1; i++ ){
            MappingSheetRowVO sheetRowVO = new MappingSheetRowVO(sheetIndexVO, headSheet, i, idaParents, sdaParents);
            SDA sda = sheetRowVO.getSda();
            if(null != sda){
                insertMetaType(sda.getStructName(),sheetRowVO.getMetaType(),indexVO,headName,"输入");
                sda.setSeq(i);
                sda.setHeadId(interfaceHead.getHeadId());
                boolean result1 = insertLocalHeadSDA(sda, sdaParents);
                if(result1){
                    Ida ida = sheetRowVO.getIda();
                    ida.setSeq(i);
                    ida.setHeadId(interfaceHead.getHeadId());
                    ida.setState(Constants.IDA_STATE_COMMON);
                    insertIda(ida, idaParents);
                }else{
                    logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败，原因：报文头[" + headName +"]页元数据[" + sda.getMetadataId() + "]未定义！");
                    return false;
                }
            }
        }
        //导入输出数据
        idaParents.add(idas.get(Constants.ElementAttributes.RESPONSE_NAME));//ida父节点
        sdaParents.add(sdas.get(Constants.ElementAttributes.RESPONSE_NAME));//sda父节点ID
        for(int i = sheetIndexVO.outputIndex; i <= headSheet.getLastRowNum(); i++ ){
            Row row = headSheet.getRow(i);
            MappingSheetRowVO sheetRowVO = new MappingSheetRowVO(sheetIndexVO, headSheet, i, idaParents, sdaParents);
            SDA sda = sheetRowVO.getSda();
            if(null != sda){
                insertMetaType(sda.getStructName(),sheetRowVO.getMetaType(),indexVO,headName,"输出");
                sda.setSeq(i);
                sda.setHeadId(interfaceHead.getHeadId());
                boolean result1 = insertLocalHeadSDA(sda, sdaParents);
                if(result1){
                    Ida ida = sheetRowVO.getIda();
                    ida.setSeq(i);
                    ida.setHeadId(interfaceHead.getHeadId());
                    ida.setState(Constants.IDA_STATE_COMMON);
                    insertIda(ida, idaParents);
                }else{
                    logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败，原因：报文头[" + headName +"]页元数据[" + sda.getMetadataId() + "]未定义！");
                    return false;
                }
            }
        }
        return true;
    }
    public boolean importIdaSDAContent(Workbook workbook, MappingImportIndexRowVO indexVO){
        String interfaceId = indexVO.getInterfaceId();
        String serviceId = indexVO.getServiceId();
        String operationId = indexVO.getOperationId();

        Map<String, Ida> idas = idaService.genderInterIdaAuto(interfaceId);
        List<SDA> sdas = sdaService.genderSDAAuto(serviceId, operationId);

        List<Ida> idaParents = new ArrayList<Ida>();
        idaParents.add(idas.get(Constants.ElementAttributes.REQUEST_NAME));
        List<SDA> sdaParents = new ArrayList<SDA>();
        sdaParents.add(sdas.get(1));

        //获取Head中的所有元素
        Set<String> inAllReqHeadMetadatas = new HashSet<String>();
        inAllReqHeadMetadatas.addAll(allReqHeadMetadatas);//syshead和apphead
//        getLocalHeadMetadata(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, inAllReqHeadMetadatas);//localhead

        Sheet mappingSheet = workbook.getSheet(interfaceId);
        MappingSheetIndexVO sheetIndexVO = new MappingSheetIndexVO(mappingSheet);
        //导入输入数据
        for(int i = sheetIndexVO.inputIndex; i < sheetIndexVO.outputIndex-1; i++ ){
            MappingSheetRowVO sheetRowVO = new MappingSheetRowVO(sheetIndexVO, mappingSheet, i, idaParents, sdaParents);
            SDA sda = sheetRowVO.getSda();
            if(null != sda){
                insertMetaType(sda.getStructName(),sheetRowVO.getMetaType(),indexVO,null,"输入");//将元数据导入数据字典，并检查元数据表是否存在这条记录
                sda.setSeq(i);
                sda.setServiceId(serviceId);
                sda.setOperationId(operationId);
                boolean result1 = insertSDA(sda, sdaParents, inAllReqHeadMetadatas);
                if(result1){
                    Ida ida = sheetRowVO.getIda();
                    ida.setSeq(i);
                    ida.setInterfaceId(interfaceId);
                    ida.setState(Constants.IDA_STATE_COMMON);
                    insertIda(ida, idaParents);
                }else{
                    logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败，原因：[" + interfaceId +"]页元数据[" + sda.getMetadataId() + "]未定义！");
                    return false;
                }
            }

        }
        idaParents.add(idas.get(Constants.ElementAttributes.RESPONSE_NAME));
        sdaParents.add(sdas.get(2));
        // 填充localhead的Metadatas
        Set<String> inAllResHeadMetadatas = new HashSet<String>();
        inAllResHeadMetadatas.addAll(allResHeadMetadatas);
//        getLocalHeadMetadata(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, inAllResHeadMetadatas);
        //输出
        for(int i = sheetIndexVO.outputIndex; i <= mappingSheet.getLastRowNum(); i++ ){
            MappingSheetRowVO sheetRowVO = new MappingSheetRowVO(sheetIndexVO, mappingSheet, i, idaParents, sdaParents);
            SDA sda = sheetRowVO.getSda();
            if(null != sda){
                insertMetaType(sda.getStructName(),sheetRowVO.getMetaType(),indexVO,null,"输出");
                sda.setSeq(i);
                sda.setServiceId(serviceId);
                sda.setOperationId(operationId);
                boolean result1 = insertSDA(sda, sdaParents, inAllResHeadMetadatas);
                if(result1){
                    Ida ida = sheetRowVO.getIda();
                    ida.setSeq(i);
                    ida.setInterfaceId(interfaceId);
                    ida.setState(Constants.IDA_STATE_COMMON);
                    insertIda(ida, idaParents);
                }else{
                    logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败，原因：[" + interfaceId +"]页元数据[" + sda.getMetadataId() + "]未定义！");
                    return false;
                }
            }
        }

        // 释放内存
        inAllReqHeadMetadatas = null;
        inAllResHeadMetadatas = null;
        return true;
    }

    /**
     * 将数据插入数据字典，并对元数据表中没有但字段映射有的进行处理
     * @param metadataId  数据字典
     * @param metaType  元数据规范
     */
    public void insertMetaType(String metadataId,String metaType, MappingImportIndexRowVO indexVO,String headName,String seat){
        String serviceId = indexVO.getServiceId();
        String operationId = indexVO.getOperationId();
        if(null != metadataId && null != metaType && !"".equals(metaType)){
            Metadata metadata = metadataService.getEntityByMetaId(metadataId);
            if(null != metadata){
                metadata.setMetaType(metaType);
                metadataService.save(metadata);
                //判断元数据规范中是否有
                if(metaTypeService.uniqueValName(metaType)){
                    if(null == headName){
                        logMsg("元数据规范中不存在该字段！服务码："+serviceId+"，场景码："+operationId+"，位置："+seat+"，数据字典："+metadataId+"，元数据："+metaType);
                    }else{
                        logMsg("元数据规范中不存在该字段！头部名称："+headName+"，位置："+seat +",数据字典："+metadataId+"，元数据："+metaType);
                    }
                }
            }
        }
    }
    /**
     * @param sda
     * @param sdaParents 父节点缓存
     * @return
     */
    public boolean insertSDA(SDA sda, List<SDA> sdaParents, Set<String> allHeadMetadatas){
        if(null != sda && StringUtils.isNotEmpty(sda.getMetadataId())) {//判断元数据是否存在
            sdaService.save(sda);
            if(allHeadMetadatas.contains(sda.getMetadataId().trim())){
                sda.setMetadataId(sda.getMetadataId().trim()+"_C");
                allHeadMetadatas.add(sda.getMetadataId().trim()+"_C");
            }
            Metadata metadata = metadataDAO.findUniqueBy("metadataId", sda.getMetadataId());
            Pattern pattern = Pattern.compile("^*_array$");
            if (null == metadata) {
                createNewMetaData(sda, pattern);
            }else{
                // 对数组元数据增加“_array”后缀
                if("array".equalsIgnoreCase(sda.getType().toLowerCase())){
                    String metadataId = sda.getMetadataId();
                    if(!pattern.matcher(metadataId).find()){
                        metadataId+="_array";
                        Metadata arrayMetadata = metadataDAO.findUniqueBy("metadataId", metadataId);
                        if(null != arrayMetadata){
                            metadata = arrayMetadata;
                        }else{
                            metadata = new Metadata();
                            metadata.setMetadataId(metadataId);
                            metadata.setChineseName(sda.getStructAlias().trim());
                            metadata.setType(sda.getType().trim());
                        }
                    }
                }
                metadataDAO.save(metadata);
            }
        }else{
            return true;
        }
        return true;
    }
    /**
     * @param sda
     * @param sdaParents 父节点缓存
     * @return
     */
    public boolean insertLocalHeadSDA(SDA sda, List<SDA> sdaParents){
        if(null != sda && StringUtils.isNotEmpty(sda.getMetadataId())) {//判断元数据是否存在
            Metadata metadata = metadataDAO.findUniqueBy("metadataId", sda.getMetadataId());
            Pattern pattern = Pattern.compile("^*_array$");
            if (null == metadata) {
                metadata = new Metadata();
                metadata.setMetadataId(sda.getMetadataId().trim());
                metadata.setChineseName(sda.getStructAlias().trim());
                String type = sda.getType();
                type.replaceAll("（","(").replaceAll("）",")");
                if(type.indexOf("(")>=0){
                    metadata.setType(type.split("[()]+")[0]);
                    String lengthAndScale = type.split("[()]+")[1];
                    if(StringUtils.isNotEmpty(lengthAndScale)){
                        String strs[]  = lengthAndScale.split("\\,");
                        metadata.setLength(strs[0]);
                        if(strs.length > 1){
                            metadata.setScale(strs[1]);
                        }
                    }

                }else{
                    metadata.setType(type);
                    // 对数组元数据增加“_array”后缀
                    if("array".equalsIgnoreCase(metadata.getType().toLowerCase())){
                        String metadataId = metadata.getMetadataId();
                        if(!pattern.matcher(metadataId).find()){
                            metadata.setMetadataId(metadataId+"_array");
                            Metadata arrayMetadata = metadataDAO.findUniqueBy("metadataId", metadata.getMetadataId());
                            if(null != arrayMetadata){
                                metadata = arrayMetadata;
                            }
                        }
                    }
                }
                if(!"".equals(metadata.getType())){
                    metadataDAO.save(metadata);
                }
            }else{
                // 对数组元数据增加“_array”后缀
                if("array".equalsIgnoreCase(sda.getType().toLowerCase())){
                    String metadataId = sda.getMetadataId().trim();
                    if(!pattern.matcher(metadataId).find()){
                        metadataId+="_array";
                        Metadata arrayMetadata = metadataDAO.findUniqueBy("metadataId", metadataId);
                        if(null != arrayMetadata){
                            metadata = arrayMetadata;
                        }else{
                            metadata = new Metadata();
                            metadata.setMetadataId(metadataId);
                            metadata.setChineseName(sda.getStructAlias().trim());
                            metadata.setType(sda.getType().trim());
                        }
                    }
                }
                metadataDAO.save(metadata);
            }
            sdaService.save(sda);
        }else{
            return true;
        }

//        if(null != sda && StringUtils.isNotEmpty(sda.getType()) && sda.getType().toLowerCase().contains("array")){
//            if(StringUtils.isNotEmpty(sda.getRemark()) && sda.getRemark().toLowerCase().startsWith("start")){//一个新数组加入父节点缓存
//                sdaService.save(sda);
//                sdaParents.add(sda);
//            }
//            if(StringUtils.isNotEmpty(sda.getRemark()) && sda.getRemark().toLowerCase().startsWith("end")){//最后加入的数组最先结束
//                sdaParents.remove(sdaParents.size() -1);//删除最后一个元素
//            }
//        }else{
//            sdaService.save(sda);
//        }
        return true;
    }

    private void createNewMetaData(SDA sda, Pattern pattern) {
        Metadata metadata;
        metadata = new Metadata();
        metadata.setMetadataId(sda.getMetadataId().trim());
        metadata.setChineseName(sda.getStructAlias().trim());
        String type = sda.getType();
        if(type.indexOf("(")>=0 || type.indexOf("（")>=0){
            type.replaceAll("（","(").replaceAll("）",")");
            String data[] = type.split("[()]+");
            if(data.length==2){
                metadata.setType(type.split("[()]+")[0]);
                String lengthAndScale = type.split("[()]+")[1];
                if(StringUtils.isNotEmpty(lengthAndScale)){
                    String strs[]  = lengthAndScale.split("\\,");
                    metadata.setLength(strs[0]);
                    if(strs.length > 1){
                        metadata.setScale(strs[1]);
                    }
                }
            }else{
                metadata.setType("");
                logMsg("导入元数据：" + sda.getMetadataId().trim() + "错误！请检查该元数据的类型是否正确，然后重新导入。");
            }
        }else{
            metadata.setType(type);
            // 对数组元数据增加“_array”后缀
            if("array".equalsIgnoreCase(metadata.getType().toLowerCase())){
                String metadataId = metadata.getMetadataId();
                if(!pattern.matcher(metadataId).find()){
                    metadata.setMetadataId(metadataId+"_array");
                    Metadata arrayMetadata = metadataDAO.findUniqueBy("metadataId", metadata.getMetadataId());
                    if(null != arrayMetadata){
                        metadata = arrayMetadata;
                    }
                }
            }
        }
        if(!"".equals(metadata.getType())){
            metadataDAO.save(metadata);
        }

    }

    public boolean insertIda(Ida ida, List<Ida> idaParents){
        idaService.save(ida);
//        if(null != ida && "array".equalsIgnoreCase(ida.getType())){
//            if(StringUtils.isNotEmpty(ida.getRemark()) && ida.getRemark().toLowerCase().startsWith("start")){
//                idaService.save(ida);
//                idaParents.add(ida);
//            }
//            if(StringUtils.isNotEmpty(ida.getRemark()) && ida.getRemark().toLowerCase().startsWith("end")){
//                idaParents.remove(idaParents.size() -1);//删除最后一个元素
//            }
//        }else{
//            idaService.save(ida);
//        }
        return true;
    }
    //建立映射关系
    public boolean importServiceInvoke(MappingImportIndexRowVO indexVO){
        String type = indexVO.getType();
        String systemId = null;
        String protocolId = null;
        if(StringUtils.isNotEmpty(indexVO.getInterfaceProId())){
            System system = systemService.findUniqueByName(indexVO.getInterfaceProId());
            if(null != system){
                systemId = system.getSystemId();
            }else{
                logMsg("index页第"+indexVO.getIndexNum()+"条记录建立映射关系时，无法从接口"+indexVO.getInterfaceProId()+"获取对应系统！请检查该系统是否存在！");
            }
        }
        if(StringUtils.isNotEmpty(indexVO.getProtocolType())){
            String hql = " from SystemProtocol where systemId = ? and protocol.protocolName = ?";
            SystemProtocol systemProtocol = systemProtocolDAO.findUnique(hql, systemId, indexVO.getProtocolType());//根据协议名称和系统id查询协议
            if(null != systemProtocol){
                protocolId = systemProtocol.getProtocolId();
            }else{
                logMsg("index页第" + indexVO.getIndexNum() + "条记录，协议[" + indexVO.getProtocolType() + "]在系统["+ indexVO.getInterfaceProId() +"]中未定义！");
            }
        }
        Map<String, String> params = new HashMap<String, String>();
        if(StringUtils.isNotEmpty(indexVO.getInterfaceId())){
            params.put("interfaceId", indexVO.getInterfaceId());
        }
        params.put("operationId", indexVO.getOperationId());
        params.put("serviceId", indexVO.getServiceId());
        params.put("systemId", systemId);
        params.put("type", indexVO.getType());
        params.put("isStandard", indexVO.getIsStandard());
        ServiceInvoke serviceInvoke = serviceInvokeDAO.findUniqureBy(params);

        if(null != serviceInvoke){//判断关系是否已存在
            if(operateFlag){
                serviceInvoke.setRemark(indexVO.getRemark());
                serviceInvoke.setProtocolId(protocolId);
                serviceInvokeDAO.save(serviceInvoke);
            }
        }else{
            String hql2 = " from ServiceInvoke where operationId=? and serviceId=? and systemId=? and type=? and isStandard = ? and interfaceId is null and ecode=? and protocolId is null";
            serviceInvoke = serviceInvokeDAO.findUnique(hql2, indexVO.getOperationId(), indexVO.getServiceId(), systemId, indexVO.getType(), Constants.INVOKE_TYPE_STANDARD_U, indexVO.getInterfaceName()); //从自动生成的映射中查询
            if(null != serviceInvoke){
                serviceInvoke.setInterfaceId(indexVO.getInterfaceId());
                serviceInvoke.setIsStandard(indexVO.getIsStandard());
                serviceInvoke.setRemark(indexVO.getRemark());
                serviceInvoke.setProtocolId(protocolId);
            }else{
                serviceInvoke = new ServiceInvoke(systemId, indexVO.getIsStandard(), indexVO.getServiceId(), indexVO.getOperationId(), indexVO.getInterfaceId(), indexVO.getInterfaceName(), indexVO.getType(), null, indexVO.getRemark(), protocolId);
            }
            serviceInvokeDAO.save(serviceInvoke);
        }
        //处理调用关系
        String systemAbs = null;
        if(Constants.INVOKE_TYPE_PROVIDER.equals(type)){
            systemAbs = indexVO.getConsumerAbs();
        }
        if(Constants.INVOKE_TYPE_CONSUMER.equals(type)){
            systemAbs = indexVO.getProviderAbs();
        }
        if(!insertInterfaceInvoke(indexVO, serviceInvoke, systemAbs)){
            return false;
        };
        return true;
    }
    //建立调用关系
    public boolean insertInterfaceInvoke(MappingImportIndexRowVO indexVO, ServiceInvoke serviceInvoke, String systemAbsStr){
        String providerInvokeId = null;
        String consumerInvokeId = null;
        if(StringUtils.isNotEmpty(systemAbsStr)){
            String otherType = Constants.INVOKE_TYPE_PROVIDER.equalsIgnoreCase(serviceInvoke.getType()) ?  Constants.INVOKE_TYPE_CONSUMER : Constants.INVOKE_TYPE_PROVIDER;
            systemAbsStr = systemAbsStr.replaceAll("，", ",");
            String[] systemAbs = systemAbsStr.split("\\,");
            if(null != systemAbs && systemAbs.length > 0){
                String hql2 = " from ServiceInvoke where operationId=? and serviceId=? and systemId=? and type=? and ecode=?";
                int i = 0;
                do{
                    String systemId = systemService.findUniqueByName(systemAbs[i]).getSystemId();
                    List<ServiceInvoke> list = serviceInvokeDAO.find(hql2, serviceInvoke.getOperationId(), serviceInvoke.getServiceId(), systemId, otherType, serviceInvoke.getEcode());
                    if(list.size() == 0){
                        //如果不存在可以匹配的映射关系
                        //生成一条调用的映射关系，接口id为空，是否标准属性为未知
                        ServiceInvoke serviceInvoke2 = new ServiceInvoke(systemId, Constants.INVOKE_TYPE_STANDARD_U, serviceInvoke.getServiceId(), serviceInvoke.getOperationId(), null, serviceInvoke.getEcode(), otherType, null, null, null);
                        serviceInvokeDAO.save(serviceInvoke2);
                        list.add(serviceInvoke2);
                    }
                    for(ServiceInvoke serviceInvoke2 : list){
                        if(Constants.INVOKE_TYPE_PROVIDER.equalsIgnoreCase(serviceInvoke.getType())){//provider方向
                            providerInvokeId = serviceInvoke.getInvokeId();
                            consumerInvokeId = serviceInvoke2.getInvokeId();
                        }else{//consumer方向
                            providerInvokeId = serviceInvoke2.getInvokeId();
                            consumerInvokeId = serviceInvoke.getInvokeId();
                        }
                        InterfaceInvoke interfaceInvoke = interfaceInvokeDAO.getByProIdConId(providerInvokeId, consumerInvokeId);//查询是否已经建立了调用关系
                        if(null == interfaceInvoke){
                            interfaceInvoke = new InterfaceInvoke(providerInvokeId, consumerInvokeId);
                            interfaceInvokeDAO.save(interfaceInvoke);
                        }
                    }
                    i++;
                }while ( i < systemAbs.length);
            }
        }else{
            if(Constants.INVOKE_TYPE_PROVIDER.equalsIgnoreCase(serviceInvoke.getType())){//provider方向
                providerInvokeId = serviceInvoke.getInvokeId();
            }else{
                consumerInvokeId = serviceInvoke.getInvokeId();
            }
            String hql = " from InterfaceInvoke where 1=1";
            Map<String, String> params = new HashMap<String, String>();
            if(StringUtils.isNotEmpty(providerInvokeId)){
                hql += " and providerInvokeId = :providerInvokeId ";
                params.put("providerInvokeId", providerInvokeId);
            }else{
                hql += " and providerInvokeId is null ";
            }
            if(StringUtils.isNotEmpty(consumerInvokeId)){
                hql += " and consumerInvokeId = :consumerInvokeId";
                params.put("consumerInvokeId", consumerInvokeId);
            }else{
                hql += " and consumerInvokeId is null ";
            }
            InterfaceInvoke interfaceInvoke = interfaceInvokeDAO.findUnique(hql, params);//查询是否已经建立了调用关系
            if(null == interfaceInvoke){
                interfaceInvoke = new InterfaceInvoke(providerInvokeId, consumerInvokeId);
                interfaceInvokeDAO.save(interfaceInvoke);
            }
        }
        return true;
    }
    //建立映射关系
    public boolean importExdServiceInvoke(MappingImportIndexRowVO indexVO){//索引补充中调用关系为一对一（系统-系统）,为标准调用
        String type = indexVO.getType();
        String systemId = null;
        String protocolId = null;
        System system = null;
        if(Constants.INVOKE_TYPE_PROVIDER.equals(type)){
            system = systemService.findUniqueByName(indexVO.getProviderAbs());
            if(null != system){
                systemId = system.getSystemId();
            }
        }else{
            system = systemService.findUniqueByName(indexVO.getConsumerAbs());
            if(null != system){
                systemId = system.getSystemId();
            }
        }
        if(null == system){
            logMsg("index_exd页第" + indexVO.getIndexNum() + "条记录，接口方向对应系统不存在！");
            return false;
        }
        if(StringUtils.isNotEmpty(indexVO.getProtocolType())){
            String hql = " from SystemProtocol where systemId = ? and protocol.protocolName = ?";
            SystemProtocol systemProtocol = systemProtocolDAO.findUnique(hql, systemId, indexVO.getProtocolType());//根据协议名称和系统id查询协议
            if(null != systemProtocol){
                protocolId = systemProtocol.getProtocolId();
            }else{
                logMsg("index_exd页第" + indexVO.getIndexNum() + "条记录，报文类型[" + indexVO.getProtocolType() + "]在系统["+ system.getSystemChineseName()+"]中未定义！");
            }
        }
        String hql = " from ServiceInvoke where operationId=? and serviceId=? and systemId=? and type=? and interfaceId is null";//判断存在条件：同一场景下同一接口方向统一系统接口id为空
        ServiceInvoke serviceInvoke = serviceInvokeDAO.findUnique(hql, indexVO.getOperationId(), indexVO.getServiceId(), systemId, indexVO.getType());
        if(null != serviceInvoke){
            serviceInvoke.setIsStandard(Constants.INVOKE_TYPE_STANDARD_Y);
            serviceInvoke.setRemark(indexVO.getRemark());
            serviceInvoke.setProtocolId(protocolId);
        }
        else{
            serviceInvoke = new ServiceInvoke(systemId, Constants.INVOKE_TYPE_STANDARD_Y, indexVO.getServiceId(), indexVO.getOperationId(), null, indexVO.getInterfaceName(), type, null, indexVO.getRemark(), protocolId);
        }
        serviceInvokeDAO.save(serviceInvoke);
        updateInterfaceInvoke(indexVO, serviceInvoke);
        return true;
    }
    public void updateInterfaceInvoke(MappingImportIndexRowVO indexVO, ServiceInvoke serviceInvoke){
        String otherType = Constants.INVOKE_TYPE_CONSUMER.equals(indexVO.getType()) ? Constants.INVOKE_TYPE_PROVIDER :  Constants.INVOKE_TYPE_CONSUMER;
        String otherSystem = Constants.INVOKE_TYPE_CONSUMER.equals(otherType) ? indexVO.getConsumerAbs() : indexVO.getProviderAbs();
        System system = systemService.findUniqueByName(otherSystem);

        String hql = " from ServiceInvoke where operationId=? and serviceId=? and type=? and systemId=?";//判断存在条件：同一场景下类型不同的
        List<ServiceInvoke> otherInvokes = serviceInvokeDAO.find(hql, serviceInvoke.getOperationId(), serviceInvoke.getServiceId(), otherType, system.getSystemId());

        String consumerInvokeId = null;
        String providerInvokeId = null;

        if(otherInvokes.size() > 0 ){
            ServiceInvoke otherInvoke = otherInvokes.get(0);//多个的取第一个，其他的不管

            if( Constants.INVOKE_TYPE_CONSUMER.equals(otherType)){
                consumerInvokeId = otherInvoke.getInvokeId();
                providerInvokeId = serviceInvoke.getInvokeId();
            }else{
                consumerInvokeId = serviceInvoke.getInvokeId();
                providerInvokeId = otherInvoke.getInvokeId();
            }
            String hql2 = " from InterfaceInvoke where consumerInvokeId=? and providerInvokeId = ?";
            List<InterfaceInvoke> list = interfaceInvokeDAO.find(hql2, consumerInvokeId, providerInvokeId);
            if(list.size() == 0){
                InterfaceInvoke interfaceInvoke = new InterfaceInvoke();
                interfaceInvoke.setConsumerInvokeId(UUID.randomUUID().toString());
                interfaceInvoke.setConsumerInvokeId(consumerInvokeId);
                interfaceInvoke.setProviderInvokeId(providerInvokeId);
                interfaceInvokeDAO.save(interfaceInvoke);
            }
        }else{
            String hql2 = " from InterfaceInvoke";
            if( Constants.INVOKE_TYPE_CONSUMER.equals(otherType)){
                providerInvokeId = serviceInvoke.getInvokeId();
                hql2 += " where consumerInvokeId is null and providerInvokeId = ?";
            }else{
                consumerInvokeId = serviceInvoke.getInvokeId();
                hql2 += " where consumerInvokeId = ? and providerInvokeId is null";
            }
            List<InterfaceInvoke> list = interfaceInvokeDAO.find(hql2, consumerInvokeId, providerInvokeId);
            if(list.size() == 0){
                InterfaceInvoke interfaceInvoke = new InterfaceInvoke();
                interfaceInvoke.setConsumerInvokeId(UUID.randomUUID().toString());
                interfaceInvoke.setConsumerInvokeId(consumerInvokeId);
                interfaceInvoke.setProviderInvokeId(providerInvokeId);
                interfaceInvokeDAO.save(interfaceInvoke);
            }
        }
    }

    public String getCell(Row row, int col) {
        return ExcelTool.getInstance().getCellContent(
                row.getCell(col));
    }
    public void logMsg(String msg){
        this.msg = msg;
        logInfoService.saveLog(msg, "字段映射导入");
        logger.error(msg);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }

    public void setOperateFlag(boolean operateFlag) {
        this.operateFlag = operateFlag;
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
     * 获取系统自己报文头元数据
     * @author yehu
     * @param serviceId
     * @param operationId
     * @param structName
     * @param metadataList
     */
    public void getLocalHeadMetadata(String serviceId, String operationId, String structName, Set<String> metadataList){
        //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
            for(Object inter : interfaces){
                String interfaceId = inter.toString();
                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                if(null != relates && 0 < relates.size()){
                    for(InterfaceHeadRelate interfaceHeadRelate : relates){
                        String headId = interfaceHeadRelate.getHeadId();
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("headId", headId);
                        params.put("structName", structName);
                        Ida ida = idaService.findUniqueBy(params);
                        if(null != ida){
                            String hql3 = " from Ida where _parentId = ? and structName is not null";
                            List<Ida> children = idaService.find(hql3, ida.getId());
                            for(Ida child : children){
                                params.clear();
                                params.put("headId", headId);
                                params.put("xpath", child.getXpath());
                                SDA sda = sdaService.findUniqueBy(params);
                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
                                    inputMetadata(metadataList, sda);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public Map<String, InterfaceHead> getInterfaceHeads() {
        return interfaceHeads;
    }

    public void setInterfaceHeads(Map<String, InterfaceHead> interfaceHeads) {
        this.interfaceHeads = interfaceHeads;
    }

    /**
     * 通过系统Id获得localHead
     * @param localHeadMetadatas localHead所有元素
     * @param systemId 系统Id
     * @param flage 请求或者响应标志
     */
    public void getLocalHeadMetadatas(Set<String> localHeadMetadatas,String systemId,String flage){
        if(null != systemId){
            InterfaceHead interfaceHead = interfaceHeadDAO.findUniqueBy("systemId", systemId);
            if(null != interfaceHead){
                String headId = interfaceHead.getHeadId();
                List<SDA> sdas = sdaService.getLocalHeadByHeadId(headId);
                if(null != sdas && 0 < sdas.size()){
                    for(SDA sda : sdas){
                        localHeadMetadatas.add(divideLocalHead(sda,flage));
                    }
                }
            }
        }
    }

    /**
     * 将SDA根据path划分成请求或者响应
     * @param sda SDA
     * @param flage 请求或者响应标志
     * @return 字段名metadataId
     */
    public String divideLocalHead(SDA sda,String flage){
        String path = sda.getXpath();
        if("request".equals(flage) && path.startsWith("/request")){
            return sda.getMetadataId();
        }
        if("response".equals(flage) && path.startsWith("/response")){
            return sda.getMetadataId();
        }
        return null;
    }
}























