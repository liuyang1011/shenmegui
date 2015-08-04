package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.util.ExcelTool;
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
}
