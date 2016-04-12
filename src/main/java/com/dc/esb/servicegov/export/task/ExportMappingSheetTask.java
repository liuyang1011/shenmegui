package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.excel.support.CellStyleSupport;
import com.dc.esb.servicegov.export.impl.MappingFileDataFromDB;
import com.dc.esb.servicegov.service.impl.IdaServiceImpl;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.Counter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExportMappingSheetTask implements Runnable{
    protected Log logger = LogFactory.getLog(getClass());
    private Sheet sheet;
    private ServiceInvoke si;
    private CountDownLatch countDown;
    private MappingFileDataFromDB db;

    CellStyle commonStyle;
    CellStyle arrayStyle;
    CellStyle splitStyle;

    public ExportMappingSheetTask(MappingFileDataFromDB db, CountDownLatch countDown, ServiceInvoke si, Sheet sheet) {
        this.db = db;
        this.countDown = countDown;
        this.si = si;
        this.sheet = sheet;
    }

    public void run() {
        try {
            commonStyle = sheet.getRow(0).getCell(1).getCellStyle();
            arrayStyle = sheet.getRow(4).getCell(0).getCellStyle();
            splitStyle = sheet.getRow(0).getCell(6).getCellStyle();
            fillMapping(sheet, si);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countDown.countDown();
        }
    }

    public void fillMapping(Sheet sheet, ServiceInvoke si) {
        try {
            Row row0 = sheet.getRow(0);
            Row row1 = sheet.getRow(1);
            Row row2 = sheet.getRow(2);
            Row row3 = sheet.getRow(3);
            if (si.getInter() != null) {
                setCellValue(row0.createCell(1), commonStyle, si.getInter().getEcode());//交易码
                setCellValue(row1.createCell(1), commonStyle, si.getInter().getInterfaceName());//交易名称
                setCellValue(row2.createCell(1), commonStyle, si.getInter().getDesc());//交易描述
            }
            if (si.getServiceId() != null) {
                Service service = db.getService(si.getServiceId());
                setCellValue(row0.createCell(8), commonStyle, service.getServiceName() + "(" + service.getServiceId() +")");//服务名称
                setCellValue(row2.createCell(8), commonStyle, service.getDesc());//服务描述
                if (si.getOperationId() != null) {
                        Operation operation = db.getOperation(si.getServiceId(), si.getOperationId());
                    synchronized (ExportMappingSheetTask.class){
                        setCellValue(row1.createCell(8), commonStyle, operation.getOperationName()+"("+ operation.getOperationId() +")");//服务操作名称
                        setCellValue(row3.createCell(8), commonStyle, operation.getOperationDesc());//服务操作描述
                    }
                }
            }

            Counter counter = new Counter(6);
            List<SDA> reqListSDA = db.getSDAByParentName(si.getServiceId(), si.getOperationId(), "request");
            List<SDA> resListSDA = db.getSDAByParentName(si.getServiceId(), si.getOperationId(), "response");
            if(Constants.INVOKE_TYPE_STANDARD_Y.equals(si.getIsStandard())){//如果是标准接口，不输出ida
                for(SDA sda : reqListSDA){
                    fillStandarNode(sheet, counter, sda);
                }
                counter.increment();//分隔行
                sheet.createRow(sheet.getLastRowNum() + 1);
                CellRangeAddress region = new CellRangeAddress(counter.getCount(), counter.getCount(), (short) 0, (short) 12);
                sheet.addMergedRegion(region);
                CellStyle cellStyle = sheet.getRow(6).getCell(0).getCellStyle();
                Cell outPutCell = sheet.getRow(counter.getCount()).createCell(0);
                outPutCell.setCellStyle(cellStyle);
                outPutCell.setCellValue("输出");
                for(SDA sda : resListSDA){
                    fillStandarNode(sheet, counter, sda);
                }
            }else{
                List<Ida> reqListIda = db.getIdaByParentName(si.getInterfaceId(), "request");
                List<Ida> resListIda = db.getIdaByParentName(si.getInterfaceId(), "response");
                for(Ida ida : reqListIda){
                    fillUnstandardNode(sheet, counter, si.getServiceId(), si.getOperationId(), ida);
                    SDA sda = db.findByXpath(si.getServiceId(), si.getOperationId(), ida.getXpath());
                    reqListSDA.remove(sda);//从对应的sda数组中移除对应元素
                }
                if(reqListSDA.size() > 0){
                    for(SDA sda : reqListSDA){
                        fillStandarNode(sheet, counter, sda);//只插入sda
                    }
                }
                counter.increment();//分隔行
                sheet.createRow(sheet.getLastRowNum() + 1);
                CellRangeAddress region = new CellRangeAddress(counter.getCount(), counter.getCount(), (short) 0, (short) 12);
                sheet.addMergedRegion(region);
                CellStyle cellStyle = sheet.getRow(6).getCell(0).getCellStyle();
                Cell outPutCell = sheet.getRow(counter.getCount()).createCell(0);
                outPutCell.setCellStyle(cellStyle);
                outPutCell.setCellValue("输出");

                for(Ida ida : resListIda){
                    fillUnstandardNode(sheet, counter, si.getServiceId(), si.getOperationId(), ida);
                    SDA sda = db.findByXpath(si.getServiceId(), si.getOperationId(), ida.getXpath());
                    resListSDA.remove(sda);//从对应的sda数组中移除对应元素
                }
                if(resListSDA.size() > 0){
                    for(SDA sda : resListSDA){
                        fillStandarNode(sheet, counter, sda);//只插入sda
                    }
                }
            }

        } catch (Exception e) {
            sheet = null;
            e.printStackTrace();
            logger.error("===========填充[" + sheet.getSheetName() + "]页失败===========");
        }
    }

    /**
     * 标准接口插入sda
     * @param sheet
     * @param counter
     * @param sda
     */
    public void fillStandarNode(Sheet sheet, Counter counter, SDA sda){
        addRow(sheet, counter);
        fillIda(sheet, counter.getCount(), null);
        fillSDA(sheet, counter.getCount(), sda);
        if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())) ) {
            List<SDA> children = db.getChildren(sda);
            if(null != children && 0 < children.size()){
                for(SDA child :children){
                    fillStandarNode(sheet, counter, child);
                }
                fillArrayEndRow(sheet, counter, null, sda);
            }
        }

    }

    /**
     * 非标时插入ida，sda行
     * @param sheet
     * @param counter
     * @param serviceId
     * @param operationId
     * @param ida
     */
    public void fillUnstandardNode(Sheet sheet, Counter counter, String serviceId, String operationId, Ida ida){
        addRow(sheet, counter);
        fillIda(sheet, counter.getCount(), ida);
        SDA sda = db.findByXpath(serviceId, operationId, ida.getXpath());
        fillSDA(sheet, counter.getCount(), sda);
        List<Ida> children = db.findByParentIdOrder(ida.getId());
        if(StringUtils.isNotEmpty(ida.getType()) && !"string".equalsIgnoreCase(ida.getType())){
            if(null != children && 0 < children.size()){
                for(Ida child :children){
                    fillUnstandardNode(sheet, counter, serviceId, operationId, child);
                }
                fillArrayEndRow(sheet, counter, ida, sda);
            }
        }else{
            if(null != children && 0 < children.size()){
                for(Ida child :children){
                    fillUnstandardNode(sheet, counter, serviceId, operationId, child);
                }
                fillArrayEndRow(sheet, counter, ida, sda);
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
    public void fillArrayEndRow(Sheet sheet, Counter counter, Ida ida, SDA sda){
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
        fillIda(sheet, counter.getCount(), endIda);
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
        fillSDA(sheet, counter.getCount(), endSda);
    }

    public void fillSDA(Sheet sheet, int index, SDA sda) {
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

    public void fillIda(Sheet sheet,int index, Ida ida) {
        if(null == ida){
            ida = new Ida();
        }
        Row row = sheet.getRow(index);
        String[]  values = {ida.getStructName(), ida.getStructAlias(), ida.getType(), ida.getLength(), ida.getRequired(), ida.getRemark()};
        if(StringUtils.isNotEmpty(ida.getRemark()) && (ida.getRemark().toLowerCase().startsWith("start") || (ida.getRemark().toLowerCase().startsWith("end")))){
            setRowValue(row, arrayStyle, values);
        }else{
            setRowValue(row, commonStyle, values);
        }

    }


    public void setCellValue(Cell cell, CellStyle cellStyle, String value) {
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }
    public void setRowValue(Row row, CellStyle cellStyle, String[] values) {
        for (int i = 0; i < values.length; i++) {
            setCellValue(row.createCell(i), cellStyle, values[i]);
        }
    }


    public void addRow(Sheet sheet, Counter counter){
        sheet.createRow(sheet.getLastRowNum() + 1);
        counter.increment();
        Row row = sheet.getRow(counter.getCount());
        row.setHeight(Constants.EXCEL_ROW_DEFAULT_HEIGHT);
        Cell splitCell = row.createCell(6);

        splitCell.setCellStyle(splitStyle);
    }
}
