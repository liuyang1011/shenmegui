package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.excel.MappingSheetTask;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.Counter;
import com.dc.esb.servicegov.vo.InterfaceHeadVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dc.esb.servicegov.excel.support.Constants.INTERFACE_FILE_TYPE;
import static com.dc.esb.servicegov.excel.support.Constants.MAPPING_FILE_TYPE;
import static com.dc.esb.servicegov.excel.support.Constants.SERVICE_FILE_TYPE;

/**
 * Created by Administrator on 2015/7/21.
 */
@org.springframework.stereotype.Service
@Transactional
public class ExcelExportServiceImpl  extends AbstractBaseService {
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ServiceInvokeDAOImpl siDao;
    @Autowired
    private SDADAOImpl sdaDao;
    @Autowired
    private IdaDAOImpl idaDao;
    @Autowired
    private MetadataDAOImpl metadataDao;
    @Autowired
    private ServiceDAOImpl serviceDao;
    @Autowired
    private OperationDAOImpl operationDAO;
    @Autowired
    private ServiceCategoryDAOImpl serviceCategoryDao;
    @Autowired
    private SystemDAOImpl systemDAOImpl;
    /**
     * TODO根据参数id和类型，返回excel文件
     */
    public HSSFWorkbook genderExcel(String id, String type){
        if(MAPPING_FILE_TYPE.equals(type)){
//			return new MappingGeneraterTask();
        }else if(SERVICE_FILE_TYPE.equals(type)){
            return genderServiceExcel(id);
        }else if(INTERFACE_FILE_TYPE.equals(type)){
//			return new InterfaceGeneaterTask();
        }else{
            String errorMsg = "暂时不支持类型为["+type+"]的文档导出！";
            logger.error(errorMsg);
        }
        return null;
    }

    /**
     * TODO 读取模板
     * @return
     */
    public  HSSFWorkbook getTempalteWb(String templatePath){
        File file = new File(templatePath);
        HSSFWorkbook wb = null;
        BufferedInputStream in = null;
        InputStream is;
        try {
            is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
//            in = new BufferedInputStream(new FileInputStream(file));
//            POIFSFileSystem fs = new POIFSFileSystem(in);
//
//            wb = new HSSFWorkbook(fs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    /**
     * TODO根据服务id生成excel
     * @param serviceId
     * @return
     */
    public HSSFWorkbook genderServiceExcel(String serviceId){
        HSSFWorkbook workbook =  getTempalteWb(Constants.EXCEL_TEMPLATE_SERVICE);
        List<ServiceInvoke> siList = siDao.findBy("serviceId", serviceId);

        fillIndex(workbook, siList);
        fillMapings(workbook, siList);
        //List<InterfaceHeadVO> ihvList = getByInterfaceHeadVOServiceId(serviceId);
        //fillHeads(workbook, ihvList);

        return workbook;
    }
    /**
     * TODO 填充index页
     * @param siList
     */
    public boolean fillIndex(HSSFWorkbook workbook, List<ServiceInvoke> siList){
        HSSFSheet sheet = workbook.getSheet("INDEX");
        try {
            for (int i = 0; i < siList.size(); i++) {
                ServiceInvoke si = siList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                if (si.getInterfaceId() != null) {
                    row.createCell(0).setCellValue(si.getInter().getEcode());//交易码
                }
                if (si.getInter() != null) {
                    row.createCell(1).setCellValue(si.getInter().getInterfaceName());//交易名称
                }
                if (si.getServiceId() != null) {
                    Service service = serviceDao.findUniqueBy("serviceId", si.getServiceId());
                    row.createCell(2).setCellValue(service.getServiceName());//服务名称
                    if (si.getOperationId() != null) {
                        row.createCell(3).setCellValue(si.getOperationId());//场景id
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("serviceId", si.getServiceId());
                        params.put("operationId", si.getOperationId());
                        Operation operation = operationDAO.findUniqureBy(params);
                        row.createCell(4).setCellValue(operation.getOperationName());//场景名称
                    }
                }
                if (si.getType() != null && si.getType().equals(Constants.INVOKE_TYPE_CONSUMER)) {
                    row.createCell(5).setCellValue(si.getSystem().getSystemAb());//系统ab
                    row.createCell(7).setCellValue("Consumer");//消费者
                    row.createCell(14).setCellValue(si.getSystem().getSystemChineseName());
                    //根据消费者去查找对应提供者
                    ServiceInvoke serviceInvoke = siDao.getByOtherType(si.getInvokeId());
                    if(serviceInvoke != null){
                        row.createCell(6).setCellValue(serviceInvoke.getSystem().getSystemAb());//系统ab
                        row.createCell(8).setCellValue(serviceInvoke.getSystemId());//接口提供系统ID
                    }
                }
                if (si.getType() != null && si.getType().equals(Constants.INVOKE_TYPE_PROVIDER)) {
                    row.createCell(6).setCellValue(si.getSystem().getSystemAb());//系统ab
                    row.createCell(7).setCellValue("Provider");//提供者
                    row.createCell(8).setCellValue(si.getSystemId());//接口提供系统ID
                    //根据提供者查询消费者
                    ServiceInvoke serviceInvoke = siDao.getByOtherType(si.getInvokeId());
                    if(serviceInvoke != null){
                        row.createCell(5).setCellValue(serviceInvoke.getSystem().getSystemAb());//调用方
                        row.createCell(14).setCellValue(serviceInvoke.getSystem().getSystemChineseName());
                    }

                }
                if (si.getInter() != null) {
                    row.createCell(9).setCellValue(si.getInter().getProtocolName());//报文名称
                    row.createCell(18).setCellValue(si.getInter().getHeadName());//业务报文头
                }
                row.createCell(10).setCellValue("");//处理人
                row.createCell(11).setCellValue("");//更新时间
                row.createCell(12).setCellValue("");//报文转换方向
                row.createCell(13).setCellValue("");//是否已有调用
                row.createCell(15).setCellValue("");//参考文档
                row.createCell(16).setCellValue("");//模块划分
                row.createCell(17).setCellValue("");//是否穿透
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========填充[" + sheet.getSheetName() + "]页失败===========");
            return false;
        }
        return true;
    }

    /**
     * 循环填充mapping
     * @param workbook
     * @param siList
     * @return
     */
    public void fillMapings(HSSFWorkbook workbook, List<ServiceInvoke> siList) {
        HSSFSheet mappingSheet = workbook.getSheet("MAPPING");
        int poolSize = siList.size() > 10?10:siList.size();
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        for(int i= 0; i < siList.size(); i++){
            ServiceInvoke si = siList.get(i);
            //TODO taizhou 标准没有interfaceId
            if(null == si.getInterfaceId()) continue;
            HSSFSheet sheet = workbook.cloneSheet(workbook.getSheetIndex(mappingSheet));//复制模板中mapping页
            workbook.setSheetName(workbook.getSheetIndex(sheet), si.getInterfaceId());//修改sheet名称
//            MappingSheetTask msTask = new MappingSheetTask(sheet, si, this);
//            pool.execute(msTask);
            fillMapping(sheet, si);
        }
        pool.shutdown();
        while(true){ //判断多线程是否结束
            try {
                if(pool.isTerminated()){
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("===========创建mapping页进程错误！");
            }
        }
        workbook.removeSheetAt(workbook.getSheetIndex(mappingSheet));//删除mapping页
    }
    /**
     * TODO 填充mapping页
     */
    public boolean fillMapping(HSSFSheet sheet, ServiceInvoke si){
        try {
            HSSFRow row0 = sheet.getRow(0);
            HSSFRow row1 = sheet.getRow(1);
            HSSFRow row2 = sheet.getRow(2);
            HSSFRow row3 = sheet.getRow(3);
            if (si.getInter() != null) {
                row0.createCell(1).setCellValue(si.getInter().getEcode());//交易码
                row1.createCell(1).setCellValue(si.getInter().getInterfaceName());//交易名称
            }
            if (si.getServiceId() != null) {
                Service service = serviceDao.findUniqueBy("serviceId", si.getServiceId());
                row0.createCell(8).setCellValue(service.getServiceName());//服务名称
                row2.createCell(8).setCellValue(service.getDesc());//服务名称
                if (si.getOperationId() != null) {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("serviceId", si.getServiceId());
                    params.put("operationId", si.getOperationId());
                    Operation operation = operationDAO.findUniqureBy(params);
                    row1.createCell(8).setCellValue(operation.getOperationName());//服务操作名称
                    row3.createCell(8).setCellValue(operation.getOperationDesc());//服务操作描述
                }
            }

            //获取request元数据
            List<Ida> reqListIda = getIdaByParentName(si.getInterfaceId(), "request");
            List<Ida> resListIda = getIdaByParentName(si.getInterfaceId(), "response");
            List<SDA> reqListSDA = getSDAByParentName(si.getServiceId(), si.getOperationId(), "request");
            List<SDA> resListSDA = getSDAByParentName(si.getServiceId(), si.getOperationId(), "response");
            Counter counter = new Counter(6);
            for (int i = 0; i < reqListSDA.size(); i++) {
                fillMappRow(sheet, counter, reqListSDA.get(i), reqListIda );
            }
//        if(reqListIda.size() > 0){//处理没有对应的ida，可能没有
//            for(int i = 0; i < reqListIda.size(); i++){
//                fillIda(sheet, 7+reqListSDA.size()+i, reqListIda.get(i));
//            }
//        }
           // sheet.createRow(8+reqListSDA.size());
            counter.increment();
            for (int i = 0; i < resListSDA.size(); i++) {
                fillMappRow(sheet, counter, resListSDA.get(i), resListIda );
            }
//            if(resListIda.size() > 0){//处理没有对应的ida，可能没有
//                for(int i = 0; i < resListIda.size(); i++){
//                    fillIda(sheet, 8+reqListSDA.size()+reqListIda.size()+resListSDA.size()+i, reqListIda.get(i));
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========填充[" + sheet.getSheetName() + "]页失败===========");
            return false;
        }
        return true;
    }

    public void fillMappRow(HSSFSheet sheet, Counter counter, SDA sda,  List<Ida> idaList){
        sheet.createRow(sheet.getLastRowNum() + 1);
        counter.increment();
        sheet.shiftRows(counter.getCount(), sheet.getLastRowNum(), 1, true, false); //插入一行
        fillSDA(sheet, counter.getCount(), sda);
        Ida ida = judgeMetadataId(idaList, sda.getMetadataId());
        if (ida != null) {
            fillIda(sheet, counter.getCount(), ida);
            idaList.remove(ida);
        }
        else{
            sheet.getRow(counter.getCount()).createCell(5).setCellValue("不映射");;
        }
        if((!StringUtils.isEmpty(sda.getType()) && (sda.getType().equalsIgnoreCase("array") || sda.getType().equalsIgnoreCase("struct"))) ||
                (!StringUtils.isEmpty(sda.getLength()) && (sda.getLength().equalsIgnoreCase("array") || sda.getLength().equalsIgnoreCase("struct")))){
            List<SDA> childList = getSDAChildren(sda.getSdaId());
            for(int i = 0; i < childList.size(); i++){
                fillMappRow(sheet, counter, childList.get(i), idaList);
            }
            sheet.createRow(sheet.getLastRowNum() + 1);
            counter.increment();
            sheet.shiftRows(counter.getCount(), sheet.getLastRowNum(), 1, true, false); //插入一行
            sda.setRemark("end");
            fillSDA(sheet, counter.getCount(), sda);
            if (ida != null) {
                fillIda(sheet, counter.getCount(), ida);
            }
            else{
                sheet.getRow(counter.getCount()).createCell(5).setCellValue("不映射");;
            }
        }
    }
    public void fillSDA(HSSFSheet sheet, int index, SDA sda){
        HSSFRow row = sheet.getRow(index);
        row.createCell(7).setCellValue(sda.getStructName()); //英文名称
        row.createCell(8).setCellValue(sda.getStructAlias()); //中文名称
        row.createCell(9).setCellValue(sda.getType()); //数据类型
        row.createCell(10).setCellValue(sda.getLength()); //长度
        row.createCell(11).setCellValue("");//约束条件
        row.createCell(12).setCellValue(sda.getRequired());//是否必输
        row.createCell(13).setCellValue(sda.getRemark());//备注
    }
    public void fillIda(HSSFSheet sheet, int index, Ida ida){
        HSSFRow row = sheet.getRow(index);
//        if( null == row ){
//            row = sheet.createRow(sheet.getLastRowNum() + 1);
//            sheet.shiftRows(index, sheet.getLastRowNum(), 1, true, false); //插入一行
//        }
        fillIda(sheet, row, ida);
        if(!StringUtils.isEmpty(ida.getType()) && (ida.getType().equalsIgnoreCase("array") || ida.getType().equalsIgnoreCase("struct"))){
            List<Ida> childList = getIdaChildren(ida.getId());
            for(int i = 0; i < childList.size(); i++){
                fillIda(sheet, index + i + 1, childList.get(i));
            }
            ida.setRemark("end");
            HSSFRow last = sheet.getRow(index + childList.size() + 1);
            fillIda(sheet, last, ida);
        }
    }
    public void fillIda(HSSFSheet sheet, HSSFRow row, Ida ida){
        row.createCell(0).setCellValue(ida.getStructName());//英文名称
        row.createCell(1).setCellValue(ida.getStructAlias());//中文名称
        row.createCell(2).setCellValue(ida.getType());//类型
        row.createCell(3).setCellValue(ida.getLength());//长度
        row.createCell(4).setCellValue(ida.getRequired());//是否必输
        row.createCell(5).setCellValue(ida.getRemark());//备注
    }
    public Ida judgeMetadataId(List<Ida> idaList, String metadataId){
        for(int i = 0; i < idaList.size(); i++){
            Ida ida = idaList.get(i);
            if(!StringUtils.isEmpty(ida.getMetadataId()) && !StringUtils.isEmpty(metadataId) && ida.getMetadataId().equals(metadataId)){
                return ida;
            }
        }
        return null;
    }

    public List<SDA> getSDAChildren(String sdaId){
        String hql = " from " + SDA.class.getName() + " where parentId=?";
        List<SDA> list = sdaDao.find(hql, sdaId);
        return list;
    }
    public List<Ida> getIdaChildren(String id){
        List<Ida> list = idaDao.findBy("_parentId", id);
        return list;
    }
    public List<Ida> getIdaByParentName(String interfaceId, String parentName){
        String hql = " from " + Ida.class.getName() + " as i where i._parentId in("+
                " select i2.id from "+ Ida.class.getName() + " as i2 where i2.interfaceId = ? and structName = ?"+
                ")";
        List<Ida> list = idaDao.find(hql, interfaceId, parentName);
        return list;
    }
    /**
     * 循环填充head
     */
    public void fillHeads(HSSFWorkbook workbook, List<InterfaceHeadVO> ihvList){
        HSSFSheet headSheet = workbook.getSheet("HEAD");
        int poolSize = ihvList.size() > 10?10:ihvList.size();
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        for(int i= 0; i < ihvList.size(); i++){
            InterfaceHeadVO ihv = ihvList.get(i);
            HSSFSheet sheet = workbook.cloneSheet(workbook.getSheetIndex(headSheet));//复制模板中mapping页
            workbook.setSheetName(workbook.getSheetIndex(sheet), ihv.getInterfaceHead().getHeadName());//修改sheet名称
//            MappingSheetTask msTask = new MappingSheetTask(sheet, si, this);
//            pool.execute(msTask);
            fillHead(sheet, ihv);
        }
        pool.shutdown();
        while(true){ //判断多线程是否结束
            try {
                if(pool.isTerminated()){
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

    /**
     * 填充head页
     */
    public void fillHead(HSSFSheet sheet, InterfaceHeadVO ihv){
        HSSFRow row1 = sheet.getRow(1);
        row1.getCell(0).setCellValue(ihv.getInterfaceHeadRelate().getRelateInters().getInterfaceId());//交易码
        row1.getCell(1).setCellValue(ihv.getInterfaceHeadRelate().getRelateInters().getInterfaceName());//交易名称
        Operation operation = operationDAO.getBySO(ihv.getServiceInvoke().getServiceId(), ihv.getServiceInvoke().getOperationId());
        if(operation != null){
            row1.getCell(6).setCellValue(operation.getService().getServiceName());//服务名称
            row1.getCell(8).setCellValue(operation.getOperationName());//场景名称
        }
        Counter counter = new Counter(5);
        List<Ida> reqListIda = getIdaByParentName(ihv.getServiceInvoke().getInterfaceId(), "request");
        List<Ida> resListIda = getIdaByParentName(ihv.getServiceInvoke().getInterfaceId(), "response");
        //输入
        for(int i = 0 ; i < reqListIda.size(); i++){
            counter.increment();
        }



    }
    /**
     * @param serviceId
     * @param operationId
     * @param parentName
     * @return
     */
    public List<SDA> getSDAByParentName(String serviceId, String operationId, String parentName){
        String hql = " from " + SDA.class.getName() + " as s where s.parentId in( " +
                "select s2.sdaId from " + SDA.class.getName() + " as s2 where s2.serviceId=? and s2.operationId=? and s2.structName=? " +
                ")";
        List<SDA> list = sdaDao.find(hql, serviceId, operationId, parentName);
        return list;
    }

    /**
     * @param categoryId
     * @return
     */
    public HSSFWorkbook genderServiceView(String categoryId){
        try{
            HSSFWorkbook wb = getTempalteWb(Constants.EXCEL_TEMPLATE_SERVICE_VIEW);
            HSSFCellStyle cellStyle = commonStyle(wb);
            //填充view
            fillView(wb, categoryId, cellStyle);
            //填充system页
            fillSystem(wb.getSheet("APP-ID"), cellStyle);
            //删除view页
            wb.removeSheetAt(wb.getSheetIndex(wb.getSheet("VIEW")));
            return wb;
        }catch (Exception e){
            logger.error(e, e);
        }
        return null;
    }

    public void fillView(HSSFWorkbook wb, String categoryId, HSSFCellStyle cellStyle){
        //根据categoryId查询
        ServiceCategory sc = serviceCategoryDao.findUniqueBy("categoryId", categoryId);

        HSSFSheet  view = wb.getSheet("VIEW");
        HSSFSheet sheet = wb.cloneSheet(wb.getSheetIndex(view)); //复制index页
        wb.setSheetName(wb.getSheetIndex(sheet), sc.getCategoryName());
        int counter = 1;
        //查询子分类
        List<ServiceCategory> scList = serviceCategoryDao.findBy("parentId", categoryId);
        String[] values0 = {sc.getCategoryName(), " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        if(scList.size() == 0){
            HSSFRow row = sheet.createRow(counter);
            setRowValue(row, cellStyle, values0);
            return;
        }
        for(ServiceCategory child : scList){
            int start = counter;
            String[] values1 = {sc.getCategoryName(), child.getCategoryName(), " ", " ", " ", " ", " ", " ", " ", " ", " "};
            List<Service> services = serviceDao.findBy("categoryId", child.getCategoryId());
            if(services.size() == 0){
                HSSFRow row = sheet.createRow(counter);
                counter++;
                setRowValue(row , cellStyle, values1);
                continue;
            }
            for(Service service : services){
                String[] values2 = {sc.getCategoryName(), child.getCategoryName(), service.getServiceId(), service.getServiceName(), " ", " ", " ", " ", " "};

                List<Operation> operations = operationDAO.findBy("serviceId", service.getServiceId());
                if(operations.size() == 0){
                    HSSFRow row = sheet.createRow(counter);
                    counter++;
                    setRowValue(row , cellStyle, values2);
                    continue;
                }
                for(Operation operation : operations){
                    HSSFRow row = sheet.createRow(counter);
                    counter++;
                    String[] values3 = {sc.getCategoryName(), child.getCategoryName(), service.getServiceId(), service.getServiceName(),
                            operation.getOperationId(), operation.getOperationName(), " ", " ", " ", " ", operation.getOperationRemark()};
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("serviceId", operation.getServiceId());
                    params.put("operationId", operation.getOperationId());
                    params.put("type", Constants.INVOKE_TYPE_CONSUMER);
                    List<ServiceInvoke> consumerInvokes = siDao.findBy(params);
                    if(consumerInvokes.size() > 0){
                        values3[6] = consumerInvokes.get(0).getSystem().getSystemChineseName();//服务消费者
                        if(consumerInvokes.get(0).getInter() != null){
                            values3[7] = consumerInvokes.get(0).getInter().getInterfaceName();
                            values3[8] = consumerInvokes.get(0).getInter().getInterfaceId();
                        }

                    }
                    params.put("type", Constants.INVOKE_TYPE_PROVIDER);
                    List<ServiceInvoke> providerInvokes = siDao.findBy(params);
                    if(providerInvokes.size() > 0){
                        values3[9] = providerInvokes.get(0).getSystem().getSystemChineseName();//服务提供者
                    }
                    setRowValue(row, cellStyle, values3);
                }
                CellRangeAddress region2 = new CellRangeAddress(counter-operations.size(), counter-1, (short) 2, (short) 2);
                CellRangeAddress region3 = new CellRangeAddress(counter-operations.size(), counter-1, (short) 3, (short) 3);
                sheet.addMergedRegion(region2);//合并单元格：服务号
                sheet.addMergedRegion(region3);//合并单元格：服务名称
                HSSFRow R = sheet.getRow(counter - operations.size());//居中
                HSSFCell C = R.getCell(2);
                C.setCellStyle(cellStyle);
                sheet.getRow(counter-operations.size()).getCell(3).setCellStyle(cellStyle);//居中
            }
            CellRangeAddress region1 = new CellRangeAddress(start, counter-1, (short) 1, (short) 1);
            sheet.addMergedRegion(region1);//合并单元格：子类
            sheet.getRow(start).getCell(1).setCellStyle(cellStyle);//居中

        }
        CellRangeAddress region0 = new CellRangeAddress(1, counter-1, (short) 0, (short) 0);
        sheet.addMergedRegion(region0);
        sheet.getRow(1).getCell(0).setCellStyle(cellStyle);//居中

    }

    /**
     * 填充系统页
     * @param sheet
     * @param cellStyle
     */
    public void fillSystem(HSSFSheet sheet, HSSFCellStyle cellStyle){
        List<System> systemList = systemDAOImpl.getAll();
        for(int i = 0; i < systemList.size(); i++){
            System system = systemList.get(i);
            HSSFRow row = sheet.createRow(2+i);

            setCellValue(row.createCell(1), cellStyle, system.getSystemId());//系统id
            setCellValue(row.createCell(2), cellStyle, system.getSystemAb());//英文简称
            setCellValue(row.createCell(3), cellStyle, system.getSystemChineseName());;//中文名称
        }
    }
    public void setRowValue(HSSFRow row, HSSFCellStyle cellStyle, String[] values){
        for(int i = 0; i < values.length; i++){
            setCellValue(row.createCell(i), cellStyle, values[i]);
        }
    }
    public void setCellValue(HSSFCell cell, HSSFCellStyle cellStyle, String value){
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

    /**
     * 样式：边框+居中
     * @param wb
     * @return
     */
    public HSSFCellStyle commonStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return cellStyle;
    }

    public List<InterfaceHeadVO> getByInterfaceHeadVOServiceId(String serviceId){
        try{
            String hql = " select new " + InterfaceHeadVO.class.getName() + "(ih, ihr, si) from " + InterfaceHead.class.getName()
                    + " as ih, " + InterfaceHeadRelate.class.getName() + " as ihr, " + ServiceInvoke.class.getName()
                    + " as si where ih.headId = ihr.headId and ihr.interfaceId = si.interfaceId and si.serviceId = ?";
            List<InterfaceHeadVO> list = this.find(hql, serviceId);
            return list;
        }catch (Exception e){
            e.printStackTrace();;
        }

        return null;
    }
    @Override
    public HibernateDAO getDAO() {
        return null;
    }
}
