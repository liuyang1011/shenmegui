package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.util.ExcelTool;
import com.dc.esb.servicegov.util.GlobalImport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jboss.seam.annotations.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincentfxz on 15/7/23.
 */
@Service("TaizhouExcelImportService")
@Transactional
public class TaizhouExcelImportServiceImpl extends ExcelImportServiceImpl {
    /**
     * 获取服务,场景信息
     * @param tranSheet
     * @return
     */
    @Override
    public Map<String, Object> getServiceInfo(Sheet tranSheet) {
        boolean flag = true;
        // 读取每个sheet页服务信息
        int start = tranSheet.getFirstRowNum();
        int end = tranSheet.getLastRowNum();
        com.dc.esb.servicegov.entity.Service service = new com.dc.esb.servicegov.entity.Service();
        Operation oper = new Operation();
        for (int j = start; j <= end; j++) {
            Row sheetRow = tranSheet.getRow(j);
            String serviceName = "";
            String serviceId = "";
            String operId = "";
            String operName = "";
            String serviceDesc = "";
            String operDesc = "";
            int cellStart = sheetRow.getFirstCellNum();
            int cellEnd = sheetRow.getLastCellNum();

            for (int k = cellStart; k < cellEnd; k++) {

                Cell cellObj = sheetRow.getCell(k);
                if (cellObj != null) {

                    String cell = ExcelTool.getInstance().getCellContent(
                            cellObj);
                    if ("服务名称".equals(cell)) {
                        serviceName = sheetRow.getCell(k + 1)
                                .getStringCellValue();
                        if (serviceName == null || "".equals(serviceName)) {
                            logger.error(tranSheet.getSheetName()
                                    + "sheet页，服务名称为空");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务名称为空", "导入");
                            flag = false;
                        }

                        try {
                            String[] req = getContext(serviceName);
                            serviceName = req[0];
                            serviceId = req[1];
                            service.setServiceName(serviceName);
                            service.setServiceId(serviceId);
                        } catch (Exception e) {
                            logger.error("服务名称格式不正确，格式应为为：服务名称(服务ID)");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务名称格式不正确，格式应为为：服务名称(服务ID)", "导入");
                            flag = false;
                        }

                        break;
                    } else if ("服务操作名称".equals(cell)) {
                        operName = sheetRow.getCell(k + 1).getStringCellValue();
                        if (operName == null || "".equals(operName)) {
                            logger.error(tranSheet.getSheetName()
                                    + "sheet页，服务操作名称为空");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务操作名称为空", "导入");
                            flag = false;
                        }

                        try {
                            String[] req = getContext(operName);
                            operName = req[0];
                            operId = req[1];
                            oper.setOperationId(operId);
                            oper.setOperationName(operName);
                        } catch (Exception e) {
                            logger.error("服务操作名称格式不正确，格式应为为：服务操作名称(操作ID)");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务操作名称格式不正确，格式应为为：服务操作名称(操作ID)", "导入");
                            flag = false;
                        }
                        break;
                    } else if ("服务描述".equals(cell)) {
                        serviceDesc = sheetRow.getCell(k + 1)
                                .getStringCellValue();
                        if (serviceDesc == null || "".equals(serviceDesc)) {
                            logger.error(tranSheet.getSheetName()
                                    + "sheet页，服务描述为空");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务描述为空", "导入");
                            flag = false;
                        }
                        service.setDesc(serviceDesc);
                        break;
                    } else if ("服务操作描述".equals(cell)) {
                        operDesc = sheetRow.getCell(k + 1).getStringCellValue();
                        if (operDesc == null || "".equals(operDesc)) {
                            logger.error(tranSheet.getSheetName()
                                    + "sheet页，服务操作描述为空");
                            logInfoService.saveLog(tranSheet.getSheetName()
                                    + "sheet页，服务操作描述为空", "导入");
                            flag = false;
                        }
                        oper.setOperationDesc(operDesc);
                        break;
                    } else if ("原始接口".equals(cell)) {
                        // 将表头跳过,获取接口字段信息
                        readline = j += 3;
                        break;
                    }
                }
            }
        }

        //信息不正确返回空
        if (!flag) {
            return null;
        }
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("service", service);
        resMap.put("operation", oper);

        return resMap;
    }

    @Override
    public  Map<String, Object> getStandardInputArg(Sheet sheet) {
        boolean flag = true;
        StringBuffer msg = new StringBuffer();
        List<SDA> sdas = new ArrayList<SDA>();
        ExcelTool tools = ExcelTool.getInstance();
        int start = readline;
        int end = sheet.getLastRowNum();

        int order = 0;
        for (int j = start; j <= end; j++) {
            SDA sda = new SDA();
            Row sheetRow = sheet.getRow(j);

            Cell cellObj = sheetRow.getCell(0);

            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if ("输出".equals(cell)) {
                    readline = j++;
                    break;
                }

            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setMetadataId(isNull(cell));
                sda.setStructName(isNull(cell));

            }

            cellObj = sheetRow.getCell(8);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setStructAlias(isNull(cell));
            }

            //TODO 本地化修改(第九个类型和长度合并)
            cellObj = sheetRow.getCell(9);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                cell = isNull(cell).replaceAll("，",",");
                String[] str = cell.split("[()]+");
                if(str.length>1){
                    //DOUBLE(16,2) STRING(6)
                    String len = str[1];
                    sda.setType(str[0]);
                    String[] lenArr = len.split(",");
                    sda.setLength(lenArr[0]);

                }else{
                    //STRUCT
                    sda.setType(isNull(cell));
                    sda.setLength(isNull(cell));
                }
            }

            cellObj = sheetRow.getCell(11);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setRequired(isNull(cell));
            }
            cellObj = sheetRow.getCell(12);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                if("start".equalsIgnoreCase(remark)) {
                    sda.setMetadataId("");
                }
                sda.setRemark(remark);
            }

            if(null != sda.getMetadataId() && !"".equals(sda.getMetadataId()) && !"start".equalsIgnoreCase(sda.getRemark()) && !"end".equalsIgnoreCase(sda.getRemark())){
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(metadata==null){
                    logger.error(sheet.getSheetName()+"页,元数据["+sda.getMetadataId()+"]未配置，导入失败...");
                    msg.append(sda.getMetadataId()).append(",");
                    flag = false;
                }
            }
            sda.setSeq(order);

            sdas.add(sda);
            order++;

        }

        if(!flag){
            logInfoService.saveLog(sheet.getSheetName()+"页,元数据["+msg.toString()+"]未配置，导入失败...","导入(输入)");
            return null;
        }
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("sdas", sdas);

        return resMap;
    }

    @Override
    public Map<String, Object> getStandardOutputArg(Sheet sheet){
        boolean flag = true;
        StringBuffer msg = new StringBuffer();
        List<SDA> sdas = new ArrayList<SDA>();
        ExcelTool tools = ExcelTool.getInstance();
        int start = readline;
        int end = sheet.getLastRowNum();
        int order = 0;
        for (int j = start; j <= end; j++) {
            SDA sda = new SDA();
            Row sheetRow = sheet.getRow(j);

            Cell cellObj = sheetRow.getCell(0);

            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if ("输出".equals(cell)) {
                    continue;
                }
            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setStructName(isNull(cell));
                sda.setMetadataId(isNull(cell));
            }

            cellObj = sheetRow.getCell(8);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setStructAlias(isNull(cell));
            }

            //TODO 本地化修改(第九个类型和长度合并)
            cellObj = sheetRow.getCell(9);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                cell = isNull(cell).replaceAll("，",",");
                String[] str = cell.split("[()]+");
                if(str.length>1){
                    //DOUBLE(16,2) STRING(6)
                    String len = str[1];
                    sda.setType(str[0]);
                    String[] lenArr = len.split(",");
                    sda.setLength(lenArr[0]);

                }else{
                    //STRUCT
                    sda.setType(isNull(cell));
                    sda.setLength(isNull(cell));
                }
            }
            cellObj = sheetRow.getCell(11);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setRequired(isNull(cell));
            }
            cellObj = sheetRow.getCell(12);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                if ("start".equalsIgnoreCase(cell)) {
                    sda.setMetadataId("");
                }
                sda.setRemark(remark);
            }

            if (sda.getMetadataId() != null && !"".equals(sda.getMetadataId()) && !"start".equalsIgnoreCase(sda.getRemark()) && !"end".equalsIgnoreCase(sda.getRemark())) {
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if (metadata == null) {
                    logger.error(sheet.getSheetName() + "页,元数据[" + sda.getMetadataId() + "]未配置，导入失败...");
                    msg.append(sda.getMetadataId()).append(",");
                    flag = false;
                }
            }
//e

            sda.setSeq(order);

            sdas.add(sda);
            order++;

        }

        if (!flag) {
            logInfoService.saveLog(sheet.getSheetName() + "页,元数据[" + msg.toString() + "]未配置，导入失败...", "导入(输出)");
            return null;
        }

        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("sdas", sdas);

        return resMap;
    }

    @Override
    public Map<String, Object> getInputArg(Sheet sheet) {
        boolean flag = true;
        StringBuffer msg = new StringBuffer();
        List<Ida> idas = new ArrayList<Ida>();
        List<SDA> sdas = new ArrayList<SDA>();
        ExcelTool tools = ExcelTool.getInstance();
        int start = readline;
        int end = sheet.getLastRowNum();

        int order = 0;
        for (int j = start; j <= end; j++) {
            Ida ida = new Ida();
            SDA sda = new SDA();
            Row sheetRow = sheet.getRow(j);

            Cell cellObj = sheetRow.getCell(0);

            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if ("输出".equals(cell)) {
                    readline = j++;
                    break;
                }

                ida.setStructName(isNull(cell));
            }
            cellObj = sheetRow.getCell(1);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructAlias(isNull(cell));
            }
            cellObj = sheetRow.getCell(2);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setType(isNull(cell));
            }

            cellObj = sheetRow.getCell(3);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setLength(isNull(cell));
            }

            cellObj = sheetRow.getCell(4);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRequired(isNull(cell));
            }

            cellObj = sheetRow.getCell(5);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                ida.setRemark(remark);
            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);

                if(!"start".equalsIgnoreCase(ida.getRemark())) {
                    ida.setMetadataId(isNull(cell));

                }
                sda.setMetadataId(isNull(cell));
                sda.setStructName(isNull(cell));

            }
            ida.setSeq(order);

            cellObj = sheetRow.getCell(8);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setStructAlias(isNull(cell));
            }

            //TODO 本地化修改(第九个类型和长度合并)
            cellObj = sheetRow.getCell(9);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                cell = isNull(cell).replaceAll("，",",");
                String[] str = cell.split("[()]+");
                if(str.length>1){
                    //DOUBLE(16,2) STRING(6)
                    String len = str[1];
                    sda.setType(str[0]);
                    String[] lenArr = len.split(",");
                    sda.setLength(lenArr[0]);

                }else{
                    //STRUCT
                    sda.setType(isNull(cell));
                    sda.setLength(isNull(cell));
                }
            }

            cellObj = sheetRow.getCell(11);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setRequired(isNull(cell));
            }
            cellObj = sheetRow.getCell(12);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                if("start".equalsIgnoreCase(remark)) {
                    sda.setMetadataId("");
                }
                sda.setRemark(remark);
            }

            if(ida.getMetadataId()!=null&&!"".equals(ida.getMetadataId()) && !"start".equalsIgnoreCase(sda.getRemark()) && !"end".equalsIgnoreCase(sda.getRemark())){
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(metadata==null){
                    logger.error(sheet.getSheetName()+"页,元数据["+ida.getMetadataId()+"]未配置，导入失败...");
                    msg.append(ida.getMetadataId()).append(",");
                    flag = false;
                }
            }
            sda.setSeq(order);

            idas.add(ida);
            sdas.add(sda);
            order++;

        }

        if(!flag){
            logInfoService.saveLog(sheet.getSheetName()+"页,元数据["+msg.toString()+"]未配置，导入失败...","导入(输入)");
            return null;
        }
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("idas", idas);
        resMap.put("sdas", sdas);

        return resMap;
    }

    public Map<String, Object> getOutputArg(Sheet sheet) {
        boolean flag = true;
        StringBuffer msg = new StringBuffer();
        List<Ida> idas = new ArrayList<Ida>();
        List<SDA> sdas = new ArrayList<SDA>();
        ExcelTool tools = ExcelTool.getInstance();
        int start = readline;
        int end = sheet.getLastRowNum();
        int order = 0;
        for (int j = start; j <= end; j++) {
            Ida ida = new Ida();
            SDA sda = new SDA();
            Row sheetRow = sheet.getRow(j);

            Cell cellObj = sheetRow.getCell(0);

            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if ("输出".equals(cell)) {
                    continue;
                }
                ida.setStructName(isNull(cell));
            }
            cellObj = sheetRow.getCell(1);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructAlias(isNull(cell));
            }
            cellObj = sheetRow.getCell(2);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setType(isNull(cell));
            }

            cellObj = sheetRow.getCell(3);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setLength(isNull(cell));
            }

            cellObj = sheetRow.getCell(4);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRequired(isNull(cell));
            }
            cellObj = sheetRow.getCell(5);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                ida.setRemark(remark);
            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if (!"start".equalsIgnoreCase(ida.getRemark())) {
                    ida.setMetadataId(isNull(cell));

                }
                sda.setStructName(isNull(cell));
                sda.setMetadataId(isNull(cell));
            }
            ida.setSeq(order);

            cellObj = sheetRow.getCell(8);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setStructAlias(isNull(cell));
            }

            //TODO 本地化修改(第九个类型和长度合并)
            cellObj = sheetRow.getCell(9);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                cell = isNull(cell).replaceAll("，",",");
                String[] str = cell.split("[()]+");
                if(str.length>1){
                    //DOUBLE(16,2) STRING(6)
                    String len = str[1];
                    sda.setType(str[0]);
                    String[] lenArr = len.split(",");
                    sda.setLength(lenArr[0]);

                }else{
                    //STRUCT
                    sda.setType(isNull(cell));
                    sda.setLength(isNull(cell));
                }
            }
            cellObj = sheetRow.getCell(11);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                sda.setRequired(isNull(cell));
            }
            cellObj = sheetRow.getCell(12);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                String remark = isNull(cell);
                if ("start".equalsIgnoreCase(cell)) {
                    sda.setMetadataId("");
                }
                sda.setRemark(remark);
            }

            if (ida.getMetadataId() != null && !"".equals(ida.getMetadataId()) && !"start".equalsIgnoreCase(sda.getRemark()) && !"end".equalsIgnoreCase(sda.getRemark())) {
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if (metadata == null) {
                    logger.error(sheet.getSheetName() + "页,元数据[" + ida.getMetadataId() + "]未配置，导入失败...");
                    msg.append(ida.getMetadataId()).append(",");
                    flag = false;
                }
            }
//e

            sda.setSeq(order);

            idas.add(ida);
            sdas.add(sda);
            order++;

        }

        if (!flag) {
            logInfoService.saveLog(sheet.getSheetName() + "页,元数据[" + msg.toString() + "]未配置，导入失败...", "导入(输出)");
            return null;
        }

        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("idas", idas);
        resMap.put("sdas", sdas);

        return resMap;
    }

    protected Map<String, Object> getInterfaceHead(Sheet sheet) {
        boolean flag = true;
        StringBuffer msg = new StringBuffer();
        Map<String, Object> resMap = new HashMap<String, Object>();
        ExcelTool tools = ExcelTool.getInstance();
        int start = sheet.getFirstRowNum();
        int end = sheet.getLastRowNum();
        int inputIndex = 0;
        int outIndex = 0;
        for (int i = 0; i <= end; i++) {
            Row sheetRow = sheet.getRow(i);
            Cell cellObj = sheetRow.getCell(0);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                if ("输入".equals(cell)) {
                    inputIndex = i + 1;
                } else if ("输出".equals(cell)) {
                    outIndex = i + 1;
                    break;
                }
            }
        }
        int order = 0;
        List<Ida> input = new ArrayList<Ida>();
        for (int i = inputIndex; i < outIndex - 1; i++) {
            Ida ida = new Ida();
            Row sheetRow = sheet.getRow(i);
            Cell cellObj = sheetRow.getCell(0);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructName(isNull(cell));
                if ("".equals(isNull(cell))) {
                    continue;
                }
            }

            cellObj = sheetRow.getCell(1);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructAlias(isNull(cell));
            }

            cellObj = sheetRow.getCell(2);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setType(isNull(cell));
            }

            cellObj = sheetRow.getCell(3);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setLength(isNull(cell));
            }
            cellObj = sheetRow.getCell(4);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRequired(isNull(cell));
            }

            cellObj = sheetRow.getCell(5);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRemark(isNull(cell));
            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setMetadataId(isNull(cell));
            }
            input.add(ida);
            ida.setSeq(order);
            order++;
        }

        order = 0;
        List<Ida> output = new ArrayList<Ida>();
        for (int j = outIndex; j <= end; j++) {
            Ida ida = new Ida();
            Row sheetRow = sheet.getRow(j);
            Cell cellObj = sheetRow.getCell(0);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructName(isNull(cell));
                if ("".equals(isNull(cell))) {
                    continue;
                }
            }

            cellObj = sheetRow.getCell(1);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setStructAlias(isNull(cell));
            }

            cellObj = sheetRow.getCell(2);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setType(isNull(cell));
            }

            cellObj = sheetRow.getCell(3);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setLength(isNull(cell));
            }
            cellObj = sheetRow.getCell(4);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRequired(isNull(cell));
            }

            cellObj = sheetRow.getCell(5);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setRemark(isNull(cell));
            }

            cellObj = sheetRow.getCell(7);
            if (cellObj != null) {
                String cell = tools.getCellContent(cellObj);
                ida.setMetadataId(isNull(cell));
                if (cell != null && !"".equals(cell)) {
                    Metadata metadata = metadataService.findUniqueBy("metadataId", cell);
                    if (metadata == null) {

                        logger.error(sheet.getSheetName() + "页,元数据[" + cell + "]未配置，导入失败...");
                        //logInfoService.saveLog(sheet.getSheetName()+"页,元数据["+cell+"]未配置，导入失败...","导入");
                        msg.append(cell).append(",");
                        flag = false;
                        //return null;
                    }
                }
            }
            output.add(ida);
            ida.setSeq(order);
            order++;
        }

        if (!flag) {
            logInfoService.saveLog(sheet.getSheetName() + "页,元数据[" + msg.toString() + "]未配置，导入失败...", "导入报文头");
            return null;
        }
        resMap.put("input", input);
        resMap.put("output", output);

        return resMap;
    }
    @Override
    public boolean executeStandardImport(Map<String, Object> infoMap, Map<String, Object> inputMap, Map<String, Object> outMap, Map<String, String> publicMap, Map<String, Object> headMap) {
        com.dc.esb.servicegov.entity.Service service = (com.dc.esb.servicegov.entity.Service) infoMap.get("service");
        Operation operation = (Operation) infoMap.get("operation");

        List<SDA> sdainput = (List<SDA>) inputMap.get("sdas");
        List<SDA> sdaoutput = (List<SDA>) outMap.get("sdas");

        //导入服务定义相关信息
        logger.info("导入服务定义信息...");
        if (insertService(service)) {
            //导入服务场景相关信息
            logger.info("导入服务场景信息...");
            boolean existsOper = insertOperation(service, operation);
            //维护调用关系
            //接口提供方 service_invoke 中 type=0
            String providerSystem = publicMap.get("providerSystem");
            //接口消费方 service_invoke 中 type=1
            String cusumerSystem = publicMap.get("cusumerSystem");
            //接口方向
            String interfacepoint = publicMap.get("interfacepoint");
            //TODO excel传入的是简称，已经转化为id
            HashMap<String,String> param = new HashMap<String, String>();
            param.put("systemAb",cusumerSystem);
            System system = systemDao.findUniqureBy(param);
            String systemId = system.getSystemId();
            String type = "1";
            if ("Provider".equalsIgnoreCase(interfacepoint)) {
                param = new HashMap<String, String>();
                param.put("systemAb",providerSystem);
                system = systemDao.findUniqureBy(param);
                systemId = system.getSystemId();
                type = "0";
            }
            //获取调用关系
            //TODO 接口id为null
            ServiceInvoke provider_invoke = serviceInvokeProviderQuery(service, operation, systemId, interfacepoint,null);
            //获取消费关系
            //ServiceInvoke cusumer_invoke = serviceInvokeCusumerQuery(service, operation, cusumerSystem);
            //导入接口相关信息
            logger.info("导入接口定义信息...");
            boolean exists = insertStrandardInvoke(service, operation, provider_invoke, systemId, type);
            insertSDA(existsOper, operation, service, sdainput, sdaoutput);
            //处理业务报文头
            //TODO 标准不管报文头
//            if (headMap != null && headMap.size()>0) {
//                insertInterfaceHead(exists, inter, headMap);
//            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    protected boolean insertStrandardInvoke(com.dc.esb.servicegov.entity.Service service, Operation operation, ServiceInvoke provider_invoke, String providerSystem, String type) {
        Map<String, String> paramMap = new HashMap<String, String>();
        boolean exists = false;
        //已存在提供系统关系
        if (provider_invoke != null) {
            exists = true;
            provider_invoke.setIsStandard("0");
            serviceInvokeDAO.save(provider_invoke);
        } else {
            //建立调用关系
            provider_invoke = new ServiceInvoke();
            //TODO 已经改为id格式
//			paramMap.put("systemAb", providerSystem);
//			System system = systemDao.findUniqureBy(paramMap);
            provider_invoke.setSystemId(providerSystem);
            provider_invoke.setServiceId(service.getServiceId());
            provider_invoke.setOperationId(operation.getOperationId());
            provider_invoke.setType(type);
            provider_invoke.setIsStandard("0");
            //添加协议==================
            // provider_invoke.setProtocolId("");
            serviceInvokeDAO.save(provider_invoke);
        }

        return exists;
    }

    /**
     * 解析Index页,获取IndexDO对象
     * @param indexSheet
     * @return
     */
    @Override
    public List parseIndexSheet(Sheet indexSheet) {
        List<IndexDO> indexDOs = new ArrayList<IndexDO>();
        int endRow = indexSheet.getLastRowNum();
        StringBuffer msg = new StringBuffer();
        for (int i = 1; i <= endRow; i++) {
            Row row = indexSheet.getRow(i);
            // 读取每一行第一列，获取每个交易sheet名称
            String sheetName = getCell(row, INDEX_SHEET_NAME_COL);
            //接口消费方
//            String consumerSystem = getCell(row, INDEX_CONSUMER_COL);
            //形式：name,name
            String consumerSystem[] = getCell(row, INDEX_CONSUMER_COL).replaceAll("，",",").split(",");
            for (int j = 0; j < consumerSystem.length; j++) {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("systemAb",consumerSystem[j]);
                System system = systemDao.findUniqureBy(param);

                if (null == system) {
                    logger.error("" + consumerSystem[j] + "系统不存在");
                    logInfoService.saveLog("" + consumerSystem[j] + "系统不存在", "导入");
                    msg.append("" + consumerSystem[j] + "系统不存在");
                    continue;
                }
                String consumerSystemId = system.getSystemId();
                //接口提供方
                String providerSystem = getCell(row, INDEX_PROVIDER_COL);
                param = new HashMap<String, String>();
                param.put("systemAb",providerSystem);
                system = systemDao.findUniqureBy(param);
                if (null == system) {
                    logger.error("" + providerSystem + "系统不存在");
                    logInfoService.saveLog("" + providerSystem + "系统不存在", "导入");
                    msg.append("" + providerSystem + "系统不存在");
                    continue;
                }
                String providerSystemId = system.getSystemId();
                //接口方向
                String interfacePoint = getCell(row, INDEX_INTERFACE_POINT_COL);
                String interfaceHead = getCell(row, INDEX_INTERFACE_HEAD_COL);
                String operationId = getCell(row, INDEX_OPERATION_ID_COL);
                String temp = getCell(row,INDEX_SERVICE_ID_COL).replaceAll("（","(").replaceAll("）",")");
                String serviceId = temp.split("[()]+")[1];
                String systemId = consumerSystemId;
                String systemAb = consumerSystem[j];
                if ("Provider".equalsIgnoreCase(interfacePoint)) {
                    systemId = providerSystemId;
                    systemAb = providerSystem;
                }
                IndexDO indexDO = new IndexDO();
                indexDO.setConsumerSystem(consumerSystem[j]);
                indexDO.setConsumerSystemId(consumerSystemId);
                indexDO.setSheetName(sheetName);
                indexDO.setInterfaceHead(interfaceHead);
                indexDO.setProviderSystem(providerSystem);
                indexDO.setProviderSystemId(providerSystemId);
                indexDO.setSystemId(systemId);
                indexDO.setInterfacePoint(interfacePoint);
                indexDO.setOperationId(operationId);
                indexDO.setServiceId(serviceId);
                indexDO.setSystemAb(systemAb);
                indexDOs.add(indexDO);
            }
        }
        List list = new ArrayList();
        list.add(indexDOs);
        list.add(msg);
        return list;
    }
}
