package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.excel.support.CellStyleSupport;
import com.dc.esb.servicegov.export.task.ExportMappingSheetTask;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.Counter;
import com.dc.esb.servicegov.vo.OperationPKVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dc.esb.servicegov.excel.support.Constants.MAPPING_FILE_TYPE;

/**
 * Created by xhx109 on 2016/3/26.
 */
@Component
public class XssfMappingFileExport {
    protected Log logger = LogFactory.getLog(getClass());

    private static final String serviceType = "service";
    private static final String serviceCategoryType0 = "root";
    private static final String serviceCategoryType1 = "serviceCategory1";
    private static final String serviceCategoryType2 = "serviceCategory2";

    @Autowired
    private ServiceInvokeDAOImpl siDao;
    @Autowired
    private SDADAOImpl sdaDao;
    @Autowired
    private IdaDAOImpl idaDao;

    @Autowired
    private OperationDAOImpl operationDAO;

    @Autowired
    private InterfaceInvokeDAOImpl interfaceInvokeDAO;
    @Autowired
    private InterfaceDAOImpl interfaceDAO;

    @Autowired
    private InterfaceHeadServiceImpl interfaceHeadService;
    @Autowired
    private ProtocolDAOImpl protocolDAO;

    @Autowired
    private SDAServiceImpl sdaService;

    @Autowired
    private IdaServiceImpl idaServiceImpls;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired
    private OperationServiceImpl operationService;

    @Autowired
    private MappingFileDataFromDB db;

    /**
     * TODO根据参数id和类型，返回excel文件
     */
    public Workbook genderExcel(String id, String type) {
        if (MAPPING_FILE_TYPE.equals(type)) {
//			return new MappingGeneraterTask();
        } else if (serviceType.equals(type)) {
            return genderServiceExcel(id);
        } else if (serviceCategoryType0.equals(type)) {
            return genderServiceCagegoryRootExcel();
        } else if (serviceCategoryType1.equals(type)) {
            return genderServiceCagegory1Excel(id);
        } else if (serviceCategoryType2.equals(type)) {
            return genderServiceCagegory2Excel(id);
        } else {
            String errorMsg = "暂时不支持类型为[" + type + "]的文档导出！";
            logger.error(errorMsg);
        }
        return null;
    }

    /**
     * TODO 读取模板
     *
     * @return
     */
    public Workbook getTempalteWb(String templatePath) {
        File file = new File(templatePath);
        Workbook wb = null;
        BufferedInputStream in = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            wb = new SXSSFWorkbook(xssfWorkbook);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                logger.error(e, e);
            }

        }
        return wb;
    }

    /**
     * 根据“服务类”生成excel
     */
    public Workbook genderServiceCagegoryRootExcel() {
        List<ServiceInvoke> siList = siDao.getByServiceCagegoryId0();
        Workbook workbook = fillExcel(siList);
        return workbook;
    }

    /**
     * 根据服务类别生成excel
     */
    public Workbook genderServiceCagegory1Excel(String serviceCategoryId) {
        List<ServiceInvoke> siList = siDao.getByServiceCagegoryId1(serviceCategoryId);
        Workbook workbook = fillExcel(siList);
        return workbook;
    }

    public Workbook genderServiceCagegory2Excel(String serviceCategoryId) {
        List<ServiceInvoke> siList = siDao.getByServiceCagegoryId2(serviceCategoryId);
        Workbook workbook = fillExcel(siList);
        return workbook;
    }

    /**
     * TODO根据服务id生成excel
     *
     * @param serviceId
     * @return
     */
    public Workbook genderServiceExcel(String serviceId) {
        String hql = " from " + ServiceInvoke.class.getName() + " where serviceId=? order by operationId asc";
        List<ServiceInvoke> siList = siDao.find(hql, serviceId);
        Workbook workbook = fillExcel(siList);
        return workbook;
    }

    public Workbook genderExcelByOperation(OperationPKVO pkvo) {
        List<ServiceInvoke> siList = new ArrayList<ServiceInvoke>();
        List<OperationPK> pks = pkvo.getPks();
        for (int i = 0; i < pks.size(); i++) {
            List<ServiceInvoke> opSiList = siDao.getByOperationPK(pks.get(i));
            for (ServiceInvoke si : opSiList) {
                siList.add(si);
            }
        }
        Workbook workbook = fillExcel(siList);
        return workbook;
    }

    //根据系统id生成excel
    public Workbook genderExcelBySystemId(String systemId) {
        String hql = " from ServiceInvoke where systemId = ? and operationId is not null and serviceId is not null";
        List<ServiceInvoke> siList = siDao.find(hql, systemId);
        Workbook workbook = fillExcel(siList);
        return workbook;
    }


    public Workbook fillExcel(List<ServiceInvoke> siList) {
        if (siList.size() > 0) {
            Workbook workbook = getTempalteWb(Constants.EXCEL_TEMPLATE_SERVICE_XLSX);
            CellStyle commonStyle = CellStyleSupport.leftStyle(workbook);
            CellStyle arrayStyle = CellStyleSupport.arrayStyle(workbook);
            Date s1 = new Date();
            fillIndex(workbook, siList,commonStyle, arrayStyle);
            java.lang.System.out.println(">>>>>>>>>>>>>>>>>>>>>生成index页耗时：" + (new Date().getTime() - s1.getTime()) + "ms<<<<<<<<<<<<<<<<<<<<<<<<<<");
            Date s2 = new Date();
            fillHeads(workbook, siList,commonStyle, arrayStyle);
            java.lang.System.out.println(">>>>>>>>>>>>>>>>>>>>>生成报文头耗时：" + (new Date().getTime() - s2.getTime()) + "ms<<<<<<<<<<<<<<<<<<<<<<<<<<");
            fillProtocol(workbook, siList,commonStyle, arrayStyle);
            Date s3 = new Date();
            fillMapings(workbook, siList);
            java.lang.System.out.println(">>>>>>>>>>>>>>>>>>>>>生成mapping耗时：" + (new Date().getTime() - s3.getTime()) + "ms<<<<<<<<<<<<<<<<<<<<<<<<<<");
            return workbook;
        }
        return null;
    }

    /**
     * 填充index和index_exd页
     *
     * @param siList
     */
    public boolean fillIndex(Workbook workbook, List<ServiceInvoke> siList, CellStyle commonStyle, CellStyle arrayStyle) {
        Sheet sheet = workbook.getSheet("INDEX");
        int sheetRowNum = 1;
        Sheet exdSheet = workbook.getSheet("INDEX_EXD");
        int exdSheetRow = 1;
        for (int i = 0; i < siList.size(); i++) {
            ServiceInvoke serviceInvoke = siList.get(i);
            if (!Constants.INVOKE_TYPE_STANDARD_U.equals(serviceInvoke.getIsStandard())) {//不是未知
                String hql = " from InterfaceInvoke where providerInvokeId = ? or consumerInvokeId = ?";
                List<InterfaceInvoke> interfaceInvokes = interfaceInvokeDAO.find(hql, serviceInvoke.getInvokeId(), serviceInvoke.getInvokeId());
                List<ServiceInvoke> consumers = new ArrayList<ServiceInvoke>();
                List<ServiceInvoke> providers = new ArrayList<ServiceInvoke>();
                for (InterfaceInvoke interfaceInvoke : interfaceInvokes) {
                    ServiceInvoke consumer = interfaceInvoke.getConsumer();
                    ServiceInvoke provider = interfaceInvoke.getProvider();
                    if (null != consumer) {
                        if (!consumers.contains(consumer)) {
                            consumers.add(consumer);
                        }
                    }
                    if (null != provider) {
                        if (!providers.contains(provider)) {
                            providers.add(provider);
                        }
                    }
                }
                if (StringUtils.isNotEmpty(serviceInvoke.getInterfaceId())) {
                    fillIndexRow(sheet, sheetRowNum, serviceInvoke, consumers, providers, commonStyle);
                    sheetRowNum++;
                } else {
                    fillIndexRow(exdSheet, exdSheetRow, serviceInvoke, consumers, providers, commonStyle);
                    exdSheetRow++;
                }
            } else {
                continue;
            }
        }
        return true;
    }

    public void fillIndexRow(Sheet sheet, int rowNum, ServiceInvoke serviceInvoke, List<ServiceInvoke> consumers, List<ServiceInvoke> providers, CellStyle commonStyle) {
        Row row = sheet.createRow(rowNum);
        Operation operation = operationDAO.getBySO(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
        setCellValue(row.createCell(0), commonStyle, serviceInvoke.getInterfaceId());//接口代码

        CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
        Hyperlink hyperlink = creationHelper.createHyperlink(Hyperlink.LINK_URL);
        // "#"表示本文档    "明细页面"表示sheet页名称  "A10"表示第几列第几行
        hyperlink.setAddress("#" + serviceInvoke.getInterfaceId() + "!A1");
        row.getCell(0).setHyperlink(hyperlink);
        row.getCell(0).setCellStyle(CellStyleSupport.hyperLinkStyle(sheet.getWorkbook()));
        Interface inter = interfaceDAO.findUniqueBy("interfaceId", serviceInvoke.getInterfaceId());
        if (null != inter) {
            setCellValue(row.createCell(1), commonStyle, inter.getEcode());//交易名称
            setCellValue(row.createCell(2), commonStyle, inter.getInterfaceName());//交易名称

            String interStatus = "";
            if (Constants.INTERFACE_STATUS_TC.equals(inter.getStatus())) {
                interStatus = "投产";
            }
            if (Constants.INTERFACE_STATUS_FQ.equals(inter.getStatus())) {
                interStatus = "废弃";
            }
            setCellValue(row.createCell(21), commonStyle, interStatus);//接口状态
        } else {
            setCellValue(row.createCell(1), commonStyle, null);//交易名称
            setCellValue(row.createCell(2), commonStyle, null);//交易名称
            setCellValue(row.createCell(21), commonStyle, null);//接口状态
        }
        if (null != operation) {
            setCellValue(row.createCell(3), commonStyle, serviceInvoke.getServiceId() + serviceInvoke.getOperationId());//服务场景码
            setCellValue(row.createCell(4), commonStyle, operation.getService().getServiceName() + "(" + operation.getServiceId() + ")");//服务名称
            setCellValue(row.createCell(5), commonStyle, operation.getOperationId());//场景id
            setCellValue(row.createCell(6), commonStyle, operation.getOperationName());//场景名称

            String operaStatus = Constants.Operation.getStateName(operation.getState());
            setCellValue(row.createCell(22), commonStyle, operaStatus);//场景状态
        }

        //用systemAb
//                setCellValue(row.createCell(5), commonStyle, vo.getConsumers());//调用方
        setCellValue(row.createCell(7), commonStyle, joinServiceInvokeSystemName(consumers, "systemAb"));//调用方
//                setCellValue(row.createCell(6), commonStyle, vo.getProviders());//提供者
        setCellValue(row.createCell(8), commonStyle, joinServiceInvokeSystemName(providers, "systemAb"));//提供者
        String systemAb = Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType()) ? "consumer" : "provider";
        setCellValue(row.createCell(9), commonStyle, systemAb);//接口方向
        setCellValue(row.createCell(10), commonStyle, joinServiceInvokeSystemName(providers, "systemId"));//接口提供系统ID

        setCellValue(row.createCell(11), commonStyle, "");//报文名称
        setCellValue(row.createCell(12), commonStyle, "");//处理人
        setCellValue(row.createCell(13), commonStyle, "");//更新时间
        setCellValue(row.createCell(14), commonStyle, "");//报文转换方向
        setCellValue(row.createCell(15), commonStyle, "");//是否已有调用
        setCellValue(row.createCell(16), commonStyle, joinServiceInvokeSystemName(consumers, "systemChineseName"));//调用方系统名称
        setCellValue(row.createCell(17), commonStyle, "");//参考文档
        setCellValue(row.createCell(18), commonStyle, "");//模块划分
        setCellValue(row.createCell(19), commonStyle, "");//是否穿透
        setCellValue(row.createCell(20), commonStyle, interfaceHeadService.getHeadNames(serviceInvoke.getInterfaceId()));//业务报文头

        String isStandard = "";
        if (Constants.INVOKE_TYPE_STANDARD_Y.equals(serviceInvoke.getIsStandard())) {
            isStandard = "是";
        }
        if (Constants.INVOKE_TYPE_STANDARD_N.equals(serviceInvoke.getIsStandard())) {
            isStandard = "否";
        }
        setCellValue(row.createCell(23), commonStyle, isStandard);//是否标准
        String protocolName = "";
        if (StringUtils.isNotEmpty(serviceInvoke.getProtocolId())) {
            Protocol protocol = protocolDAO.findUniqueBy("protocolId", serviceInvoke.getProtocolId());
            if (null != protocol) {
                protocolName = protocol.getProtocolName();

            }
        }
        setCellValue(row.createCell(24), commonStyle, protocolName);//关联协议
    }

    /**
     * 循环填充mapping
     * @param workbook
     * @param siList
     * @return
     */
    public void fillMapings(Workbook workbook, List<ServiceInvoke> siList) {
        Sheet mappingSheet = workbook.getSheet("MAPPING");
        ExecutorService pool = Executors.newFixedThreadPool(20);
        CountDownLatch countDown = new CountDownLatch(siList.size());

        List<ExportMappingSheetTask> list = new ArrayList<ExportMappingSheetTask>();

        for (int i = 0; i < siList.size(); i++) {
            ServiceInvoke si = siList.get(i);
            if (null == si.getInterfaceId() || null != workbook.getSheet(si.getInterfaceId())) {
                countDown.countDown();
                continue;//如果已经有同名sheet
            }
            Sheet sheet = workbook.cloneSheet(workbook.getSheetIndex(mappingSheet));//复制模板中mapping页
            workbook.setSheetName(workbook.getSheetIndex(sheet), si.getInterfaceId());//修改sheet名称
            ExportMappingSheetTask task = new ExportMappingSheetTask(db, countDown,si, sheet);
            list.add(task);
//            fillMapping(sheet, si);
        }
        for(ExportMappingSheetTask task : list){
            pool.execute(task);
        }
        try {
            countDown.await();

        } catch (InterruptedException e) {
            logger.error("WSDL export : " + countDown.getCount());
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        workbook.removeSheetAt(workbook.getSheetIndex(mappingSheet));//删除mapping页
    }


    /**
     * 循环填充head
     */
    public void fillHeads(Workbook workbook, List<ServiceInvoke> siList, CellStyle commonStyle, CellStyle arrayStyle) {
        Sheet headSheet = workbook.getSheet("HEAD");
        List<InterfaceHead> heads = getInterfaceHeads(siList);

        int poolSize = heads.size() > 10 ? 10 : heads.size();
        if(poolSize == 0 ) return;
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < heads.size(); i++) {
            InterfaceHead interfaceHead = heads.get(i);
            Sheet sheet = workbook.cloneSheet(workbook.getSheetIndex(headSheet));//复制模板中head页
            workbook.setSheetName(workbook.getSheetIndex(sheet), interfaceHead.getHeadName());//修改sheet名称
//            MappingSheetTask msTask = new MappingSheetTask(sheet, si, this);
//            pool.execute(msTask);
            fillHead(sheet, interfaceHead, commonStyle, arrayStyle);
        }
        pool.shutdown();
        while (true) { //判断多线程是否结束
            try {
                if (pool.isTerminated()) {
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("===========创建head页进程错误！");
            }
        }
        workbook.removeSheetAt(workbook.getSheetIndex(headSheet));//删除mapping页

    }
    public List<InterfaceHead> getInterfaceHeads(List<ServiceInvoke> siList){
        List<String> systemIds = new ArrayList<String>();
        if(siList != null){
            for(int i = 0; i < siList.size(); i++){
                if(siList.get(i) != null){
                    ServiceInvoke si = siList.get(i);
                    if(StringUtils.isNotEmpty(si.getSystemId())){
                        if(!systemIds.contains(si.getSystemId())) {
                            systemIds.add(si.getSystemId());
                        }
                    }
                }
            }
        }
        if(systemIds.size() == 0){
            return new ArrayList<InterfaceHead>();
        }
        List<InterfaceHead> heads = interfaceHeadService.getBySystemIds(systemIds);
        return heads;
    }
    /**
     * 填充head页
     */
    public void fillHead(Sheet sheet, InterfaceHead head, CellStyle commonStyle,CellStyle arrayStyle) {
        Row row0 = sheet.getRow(0);
        Cell splitCell = row0.getCell(6);
        Counter counter = new Counter(4);

        List<Ida> reqListIda = idaDao.findHeadOrder(head.getHeadId(), Constants.ElementAttributes.REQUEST_NAME);
        List<Ida> resListIda = idaDao.findHeadOrder(head.getHeadId(), Constants.ElementAttributes.RESPONSE_NAME);
        List<SDA> reqListSDA = sdaDao.findByHead(head.getHeadId(), Constants.ElementAttributes.REQUEST_NAME);
        List<SDA> resListSDA = sdaDao.findByHead(head.getHeadId(), Constants.ElementAttributes.RESPONSE_NAME);
        for(Ida ida : reqListIda){
            fillHeadNode(sheet, counter, head.getHeadId(), ida, commonStyle, arrayStyle);
            reqListSDA.remove(sdaDao.findByXpath(head.getHeadId(), ida.getXpath()));//从对应的sda数组中移除对应元素
        }
        if(reqListSDA.size() > 0){
            for(SDA sda : reqListSDA){
                fillStandarNode(sheet, counter, sda, commonStyle, arrayStyle);//只插入sda
            }
        }
        counter.increment();//分隔行
        sheet.createRow(sheet.getLastRowNum() + 1);
        CellRangeAddress region = new CellRangeAddress(counter.getCount(), counter.getCount(), (short) 0, (short) 12);
        sheet.addMergedRegion(region);
        CellStyle cellStyle = sheet.getRow(4).getCell(0).getCellStyle();
        Cell outPutCell = sheet.getRow(counter.getCount()).createCell(0);
        outPutCell.setCellStyle(cellStyle);
        outPutCell.setCellValue("输出");
        for(Ida ida : resListIda){
            fillHeadNode(sheet, counter,head.getHeadId(), ida, commonStyle, arrayStyle);
            resListSDA.remove(sdaDao.findByXpath(head.getHeadId(), ida.getXpath()));//从对应的sda数组中移除对应元素
        }
        if(resListSDA.size() > 0){
            for(SDA sda : resListSDA){
                fillStandarNode(sheet, counter, sda, commonStyle, arrayStyle);//只插入sda
            }
        }
    }
    public void fillHeadNode(Sheet sheet, Counter counter, String headId, Ida ida, CellStyle commonStyle, CellStyle arrayStyle){
        addRow(sheet, counter);
        fillIda(sheet, counter.getCount(), ida, commonStyle, arrayStyle);
        SDA sda = sdaDao.findByXpath(headId, ida.getXpath());
        fillSDA(sheet, counter.getCount(), sda, commonStyle, arrayStyle);
        List<Ida> children = idaDao.findByParentIdOrder(ida.getId());
        if(null != children && 0 < children.size()){
            for(Ida child :children){
                fillHeadNode(sheet, counter, headId, child, commonStyle, arrayStyle);
            }
            fillArrayEndRow(sheet, counter, ida, sda, commonStyle, arrayStyle);
        }
    }

    /**
     * @param serviceId
     * @param operationId
     * @param parentName
     * @return
     */
    public List<SDA> getSDAByParentName(String serviceId, String operationId, String parentName) {
        String hql = " from " + SDA.class.getName() + " as s where s.parentId in( " +
                "select s2.id from " + SDA.class.getName() + " as s2 where s2.serviceId=? and s2.operationId=? and s2.structName=? " +
                ") order by s.seq asc";
        List<SDA> list = sdaDao.find(hql, serviceId, operationId, parentName);
        return list;
    }

    /**
     * 填充协议页
     * @param workbook
     * @param siList
     */
    public void fillProtocol(Workbook workbook, List<ServiceInvoke> siList, CellStyle commonStyle, CellStyle arrayStyle){
        Sheet sheet = workbook.getSheet("PROTOCOL");
        if(null != sheet){
            List<Protocol> protocols = new ArrayList<Protocol>();
            Counter counter = new Counter(0);
            for(int i = 0; i < siList.size(); i++){
                ServiceInvoke serviceInvoke = siList.get(i);
                if(StringUtils.isNotEmpty(serviceInvoke.getProtocolId())){
                    Protocol protocol = protocolDAO.findUniqueBy("protocolId", serviceInvoke.getProtocolId());
                    if(null != protocol){
                        if(!protocols.contains(protocol)){
                            System system = serviceInvoke.getSystem();
                            fillProtocolRow(sheet, counter, protocol, system, commonStyle, arrayStyle);
                            protocols.add(protocol);
                        }
                    }
                }
            }
        }
    }
    public void fillProtocolRow(Sheet sheet , Counter counter, Protocol protocol, System system, CellStyle commonStyle, CellStyle arrayStyle){
        sheet.createRow(sheet.getLastRowNum() + 1);
        counter.increment();
        String generatorName = "";
        if(null != protocol.getGenerator()){
            generatorName = protocol.getGenerator().getName();
        }
        String[] values ={system.getSystemChineseName(), protocol.getProtocolName(), protocol.getCommuProtocol(), protocol.getIsEncrypt(), protocol.getIsSync(), protocol.getIsLongCon(),
                protocol.getEncoding(), protocol.getMsgType(), protocol.getTimeout(), protocol.getSuccCode(), protocol.getErrorCode(), generatorName};
        Row row = sheet.getRow(counter.getCount());
        setRowValue(row, commonStyle, values);
    }

    /**
     * 标准接口插入sda
     * @param sheet
     * @param counter
     * @param sda
     */
    public void fillStandarNode(Sheet sheet, Counter counter, SDA sda, CellStyle commonStyle, CellStyle arrayStyle){
        addRow(sheet, counter);
        fillSDA(sheet, counter.getCount(), sda, commonStyle, arrayStyle);
        if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())) ) {
            List<SDA> children = sdaDao.findByParentId(sda.getId());
            if(null != children && 0 < children.size()){
                for(SDA child :children){
                    fillStandarNode(sheet, counter, child, commonStyle, arrayStyle);
                }
                fillArrayEndRow(sheet, counter, null, sda, commonStyle, arrayStyle);
            }
        }

    }
    /**
     * 数组结束，插入一条end记录
     * @param sheet
     * @param counter
     * @param ida
     * @param sda
     */
    public void fillArrayEndRow(Sheet sheet, Counter counter, Ida ida, SDA sda, CellStyle commonStyle, CellStyle arrayStyle){
        addRow(sheet, counter);
        Ida endIda = new Ida();
        if(null != ida){
            endIda.setStructName(ida.getStructName());
            endIda.setStructAlias(ida.getStructAlias());
            endIda.setType(ida.getType());
            endIda.setLength(ida.getLength());
            endIda.setRequired(ida.getRequired());
            if(StringUtils.isNotEmpty(ida.getRemark()) && ida.getRemark().toLowerCase().contains("start")){
                endIda.setRemark("end");
            }
        }
        fillIda(sheet, counter.getCount(), endIda, commonStyle, arrayStyle);
        SDA endSda = new SDA();
        if(null != sda){
            endSda.setStructAlias(sda.getStructAlias());
            endSda.setStructName(sda.getStructName());
            endSda.setMetadataId(sda.getMetadataId());
            endSda.setType(sda.getType());
            endSda.setRequired(sda.getRequired());
            endSda.setConstraint(sda.getConstraint());
            if(StringUtils.isNotEmpty(sda.getRemark()) && sda.getRemark().toLowerCase().contains("start")){
                endSda.setRemark("end");
            }
        }
        fillSDA(sheet, counter.getCount(), endSda, commonStyle ,arrayStyle);
    }

    public void fillSDA(Sheet sheet, int index, SDA sda, CellStyle commonStyle, CellStyle arrayStyle) {
        if(sda == null){
            sda = new SDA();
        }
        Row row = sheet.getRow(index);
        CellStyle style = "array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()) ? arrayStyle : commonStyle;
        setCellValue(row.createCell(7), style, sda.getStructName()); //英文名称
        setCellValue(row.createCell(8), style,sda.getStructAlias());//中文名称

        setCellValue(row.createCell(9), style, sda.getType());//数据类型/长度
//        setCellValue(row.createCell(10), commonStyle, sda.getLength()); //长度
        setCellValue(row.createCell(10), style, sda.getConstraint());//约束条件
        setCellValue(row.createCell(11), style, sda.getRequired());//是否必输
        setCellValue(row.createCell(12), style, sda.getRemark());//备注
    }

    public void fillIda(Sheet sheet,int index, Ida ida, CellStyle commonStyle, CellStyle arrayStyle) {
        if(null == ida){
            ida = new Ida();
        }
        Row row = sheet.getRow(index);
        String[]  values = {ida.getStructName(), ida.getStructAlias(), ida.getType(), ida.getLength(), ida.getRequired(), ida.getRemark()};
        if("array".equalsIgnoreCase(ida.getType()) || "struct".equalsIgnoreCase(ida.getType())){
            setRowValue(row, arrayStyle, values);
        }else{
            setRowValue(row, commonStyle, values);
        }

    }
    public void addRow(Sheet sheet, Counter counter){
        sheet.createRow(sheet.getLastRowNum() + 1);
        counter.increment();
//        sheet.shiftRows(counter.getCount(), sheet.getLastRowNum(), 1, true, false); //插入一行
        Row row = sheet.getRow(counter.getCount());
        row.setHeight(Constants.EXCEL_ROW_DEFAULT_HEIGHT);

        Cell splitCell = row.createCell(6);

        splitCell.setCellStyle(sheet.getRow(0).getCell(6).getCellStyle());
    }
    public void setRowValue(Row row, CellStyle cellStyle, String[] values) {
        for (int i = 0; i < values.length; i++) {
            setCellValue(row.createCell(i), cellStyle, values[i]);
        }
    }

    public void setCellValue(Cell cell, CellStyle cellStyle, String value) {
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

    /**
     * 将一组serviceInvoke中的系统名称或者系统id连接起来,用逗号隔开
     * @param list
     * @return
     */
    public String joinServiceInvokeSystemName(List<ServiceInvoke> list, String field){
        String result = "";
        if(list != null && list.size() > 0){
            for(ServiceInvoke si : list){
                if(si.getSystem() != null){
                    if("systemChineseName".equals(field)){
                        result += si.getSystem().getSystemChineseName() + ",";
                    }
                    if("systemId".equals(field)){
                        result += si.getSystem().getSystemId() + ",";
                    }
                    if("systemAb".equals(field)){
                        result += si.getSystem().getSystemAb() + ",";
                    }
                }
            }
            if(result.lastIndexOf(",") > 0){
                result = result.substring(0, result.lastIndexOf(","));
            }
        }
        return result;
    }
}
