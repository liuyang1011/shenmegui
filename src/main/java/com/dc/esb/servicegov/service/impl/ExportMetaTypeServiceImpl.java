package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetaTypeDAOImpl;
import com.dc.esb.servicegov.entity.MetaType;
import com.dc.esb.servicegov.entity.MetaTypeCodeEnum;
import com.dc.esb.servicegov.excel.support.CellStyleSupport;
import com.dc.esb.servicegov.service.support.Constants;
import com.lowagie.text.Cell;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.util.Region;

import java.awt.*;
import java.awt.Color;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * Created by xhx113 on 2016/4/6.
 */
@Component
public class ExportMetaTypeServiceImpl {

    private Log log = LogFactory.getLog(ExportMetaTypeServiceImpl.class);

    private static final String TYPES = "数量,比率,金额,数值,时间,产品编号,单证编号,机构编号,联系信息编号,流水编号,人员编号,"+
            "证件编号,账号,业务编号,其他编号,标志,方式,方向,级别,类型,状态,代码,地址,敏感信息,名称,特殊字段";

    private static final String TYPES_ROW = "数字-数量,数字-数值-比率,数字-数值-金额,数字-数值-数值（普通）,文本-规则文本-时间,"+
            "文本-规则文本-编号-产品编号,文本-规则文本-编号-单证编号,文本-规则文本-编号-机构编号,文本-规则文本-编号-联系信息编号,"+
            "文本-规则文本-编号-流水编号,文本-规则文本-编号-人员编号,文本-规则文本-编号-证件编号,文本-规则文本-编号-账号,"+
            "文本-规则文本-编号-业务编号,文本-规则文本-编号-其他编号,文本-规则文本-代码-标志,文本-规则文本-代码-方式,"+
            "文本-规则文本-代码-方向,文本-规则文本-代码-级别,文本-规则文本-代码-类型,文本-规则文本-代码-状态,文本-规则文本-代码-代码,"+
            "文本-不规则文本-地址,文本-不规则文本-敏感信息,文本-不规则文本-名称,文本-不规则文本-特殊文本";

    private String[] typeArray = TYPES.split(",");
    private String[] typeRowArray = TYPES_ROW.split(",");
    private Map<String,String> codeEnumMap = new HashMap<String,String>();

    @Autowired
    private MetaTypeDAOImpl metaTypeDAO;
    @Autowired
    MetaTypeCodeEnumServiceImpl metaTypeCodeEnumService;

    /**
     * 校验数据是否正确，准备生成Excel
     * @return 生成好的元数据规范文档
     */
    public XSSFWorkbook genderExcel(){
        //校验数据库中是否正确
        List<MetaType> allMetaType = metaTypeDAO.getAll();
        List<MetaTypeCodeEnum> allMetaTypeCodeEnum = metaTypeCodeEnumService.getAll();
        if(null != allMetaType && null != allMetaTypeCodeEnum){
            XSSFWorkbook workbook = fillExcel(allMetaType,allMetaTypeCodeEnum);
            return workbook;
        }else{
            log.error("获取所有元数据规范失败！");
        }
        return null;
    }

    /**
     * 分步生成元数据规范页和代码型枚举值页
     * @return 生成好的文档
     */
    public XSSFWorkbook fillExcel(List<MetaType> allMetaType,List<MetaTypeCodeEnum> allMetaTypeCodeEnum){
        //读取模版
        XSSFWorkbook workbook = getTempalteWb(Constants.EXCEL_TEMPLATE_METATYPE_XLSX);
        //获取样式
        XSSFCellStyle metaTypeRowStyle = getMetaTypeRowStyle(workbook);//元数据行属性
        XSSFCellStyle metaTypeCellStyle = getMetaTypeCellStyle(workbook);//元数据单元格属性
        XSSFCellStyle metaTypeCodeEnumStyle = getMetaTypeCodeEnumCellStyle(workbook);//枚举值单元格属性
        XSSFCellStyle metaTypeHyperLinkStyle = getMetaTypeHyperLinkCellStyle(workbook);//元数据链接属性
        XSSFCellStyle codeEnumHyperLinkStyle = getCodeEnumHyperLinkCellStyle(workbook);//枚举值链接属性

        //填充代码枚举值
        fillCodeEnum(allMetaTypeCodeEnum,workbook,metaTypeCodeEnumStyle,codeEnumHyperLinkStyle);
        //填充元数据页面
        fillMetaType(allMetaType,workbook,metaTypeRowStyle,metaTypeCellStyle,metaTypeHyperLinkStyle);

        return workbook;
    }

    /**
     * 填充元数据页面
     * @param workbook 模版
     * @param metaTypeRowStyle 样式
     */
    public void fillMetaType(List<MetaType> allMetaType,XSSFWorkbook workbook,XSSFCellStyle metaTypeRowStyle,
                             XSSFCellStyle metaTypeCellStyle,XSSFCellStyle hyperLinkStyle){
        XSSFSheet sheet = workbook.getSheet("表2ESB元数据规范");
        XSSFSheet sheet2 = workbook.getSheet("附1标准代码枚举值");
        int rowNum = 1;
        int colNum = 0;
        if(null != sheet){
            for(int i=0;i<typeArray.length;i++){//按照类型
                sheet.addMergedRegion(new CellRangeAddress(++rowNum,rowNum,0,29));//合并单元格
                XSSFRow row = sheet.createRow(rowNum);
                setCellValue(row.createCell(colNum),metaTypeRowStyle,typeRowArray[i]);
                for(MetaType metaType:allMetaType){
                    if(typeArray[i].equals(metaType.getClassify())){
                        XSSFRow metaTypeRow = sheet.createRow(++rowNum);
                        fillMetaTypeCell(metaTypeRow,metaType,metaTypeCellStyle,hyperLinkStyle,sheet,sheet2,rowNum);
                    }
                }
            }
        }else{
            log.error("获取[表2ESB元数据规范]页失败！数据未导出！");
        }

    }

    /**
     * 填充元数据规范中一行数据
     * @param metaTypeRow 行
     * @param metaType 数据
     * @param metaTypeCellStyle 单元格样式
     */
    public void fillMetaTypeCell(XSSFRow metaTypeRow,MetaType metaType,XSSFCellStyle metaTypeCellStyle,
                                 XSSFCellStyle hyperLinkStyle,XSSFSheet sheet,XSSFSheet sheet2,int rowNum){
        metaTypeRow.setHeightInPoints((float)13.5);
        if(null != metaType){
            setCellValue(metaTypeRow.createCell(0),metaTypeCellStyle,metaType.getLabel());//标签
            setCellValue(metaTypeRow.createCell(1),metaTypeCellStyle,metaType.getClassify());//一级分类
            setCellValue(metaTypeRow.createCell(2),metaTypeCellStyle,metaType.getChineseName());
            setCellValue(metaTypeRow.createCell(3),metaTypeCellStyle,metaType.getName());
            setCellValue(metaTypeRow.createCell(4),metaTypeCellStyle,metaType.getEnAllName());
            setCellValue(metaTypeRow.createCell(5),metaTypeCellStyle,metaType.getUseGuide());
            setCellValue(metaTypeRow.createCell(6),metaTypeCellStyle,metaType.getStandardChName());
            setCellValue(metaTypeRow.createCell(7),metaTypeCellStyle,metaType.getStandardEnName());
            setCellValue(metaTypeRow.createCell(8),metaTypeCellStyle,metaType.getStandardAlias());
            setCellValue(metaTypeRow.createCell(9),metaTypeCellStyle,metaType.getMeaning());
            if("代码型".equals(metaType.getType())){
                setTypeLink(metaTypeRow.createCell(10),hyperLinkStyle,rowNum,metaType.getStandardChName(),sheet,sheet2);
            }else{
                setCellValue(metaTypeRow.createCell(10),metaTypeCellStyle,metaType.getType());
            }
            setCellValue(metaTypeRow.createCell(11),metaTypeCellStyle,metaType.getFormat());
            setCellValue(metaTypeRow.createCell(12),metaTypeCellStyle,metaType.getValueRange());
            setCellValue(metaTypeRow.createCell(13),metaTypeCellStyle,metaType.getMeasurement());
            setCellValue(metaTypeRow.createCell(14),metaTypeCellStyle,metaType.getSusceptibility());
            setCellValue(metaTypeRow.createCell(15),metaTypeCellStyle,metaType.getRelevantStandard());
            setCellValue(metaTypeRow.createCell(16),metaTypeCellStyle,metaType.getInformationRelation());
            setCellValue(metaTypeRow.createCell(17),metaTypeCellStyle,metaType.getCodeRule());
            setCellValue(metaTypeRow.createCell(18),metaTypeCellStyle,metaType.getInformationRule());
            setCellValue(metaTypeRow.createCell(19),metaTypeCellStyle,metaType.getAccordingBy());
            setCellValue(metaTypeRow.createCell(20),metaTypeCellStyle,null);//权威系统发文
            setCellValue(metaTypeRow.createCell(21),metaTypeCellStyle,metaType.getStandardSource());
            setCellValue(metaTypeRow.createCell(22),metaTypeCellStyle,metaType.getApplyTo());
            setCellValue(metaTypeRow.createCell(23),metaTypeCellStyle,metaType.getRelevanceMotif());
            setCellValue(metaTypeRow.createCell(24),metaTypeCellStyle,null);
            setCellValue(metaTypeRow.createCell(25),metaTypeCellStyle,null);
            setCellValue(metaTypeRow.createCell(26),metaTypeCellStyle,null);
            setCellValue(metaTypeRow.createCell(27),metaTypeCellStyle,metaType.getVersionDate());
            setCellValue(metaTypeRow.createCell(28),metaTypeCellStyle,metaType.getRemark());
        }
    }

    /**
     * 对类型是链接的进行处理
     * @param
     * @return
     */
    public void setTypeLink(XSSFCell cell,XSSFCellStyle hyperLinkStyle,int rowNum,String name,XSSFSheet sheet,XSSFSheet sheet2){
        CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
        Hyperlink hyperlink = creationHelper.createHyperlink(Hyperlink.LINK_URL);//超链接类型
        String typeLink = "#附1标准代码枚举值!B";
        if(null != codeEnumMap){
            for(String key:codeEnumMap.keySet()){
                if(codeEnumMap.get(key).equals(name)){
                    typeLink += key;//在Map中获得枚举值的行号
                    setCodeEnumReturn(sheet2,rowNum,key);//同时在枚举值中加入返回
                    break;
                }
            }
        }
        hyperlink.setAddress(typeLink);
        cell.setHyperlink(hyperlink);
        cell.setCellStyle(hyperLinkStyle);
        cell.setCellValue("代码型");
    }

    /**
     * 填充代码枚举值页面
     * @param allMetaTypeCodeEnum 所有代码枚举值
     * @param workbook 文档
     */
    public void fillCodeEnum(List<MetaTypeCodeEnum> allMetaTypeCodeEnum,XSSFWorkbook workbook,XSSFCellStyle metaTypeCodeEnumStyle,
                             XSSFCellStyle codeEnumHyperLinkStyle){
        XSSFSheet sheet = workbook.getSheet("附1标准代码枚举值");
        int rowNum = 1;
        int colNum = 0;
        for(MetaTypeCodeEnum metaTypeCodeEnum:allMetaTypeCodeEnum){
            XSSFRow row = sheet.createRow(++rowNum);
            codeEnumMap.put(rowNum+"",metaTypeCodeEnum.getStandardChName());
            fillMetaTypeCodeEnum(row, metaTypeCodeEnum, metaTypeCodeEnumStyle,codeEnumHyperLinkStyle);
        }
    }

    /**
     * 填充枚举值中的一行
     * @param row 一行
     * @param metaTypeCodeEnum 一条数据
     * @param metaTypeCodeEnumStyle 样式
     */
    public void fillMetaTypeCodeEnum(XSSFRow row,MetaTypeCodeEnum metaTypeCodeEnum,XSSFCellStyle metaTypeCodeEnumStyle,
                                     XSSFCellStyle codeEnumHyperLinkStyle){
        row.setHeightInPoints((float)13.5);
        if(null != metaTypeCodeEnum){
            setCellValue(row.createCell(0),metaTypeCodeEnumStyle,metaTypeCodeEnum.getStandarNum());
            setCellValue(row.createCell(1),metaTypeCodeEnumStyle,metaTypeCodeEnum.getStandardChName());
            setCellValue(row.createCell(2),metaTypeCodeEnumStyle,metaTypeCodeEnum.getCodeValue());
            setCellValue(row.createCell(3),metaTypeCodeEnumStyle,metaTypeCodeEnum.getCodeMeaning());
            setCellValue(row.createCell(4),metaTypeCodeEnumStyle,metaTypeCodeEnum.getOrther());
            setCellValue(row.createCell(5),metaTypeCodeEnumStyle,metaTypeCodeEnum.getSource());
            setCellValue(row.createCell(6),codeEnumHyperLinkStyle,"返回");
            setCellValue(row.createCell(7),metaTypeCodeEnumStyle,metaTypeCodeEnum.getDate());
            setCellValue(row.createCell(8),metaTypeCodeEnumStyle,metaTypeCodeEnum.getPersonInCharge());
        }
    }

    /**
     * 单独设置枚举值的链接返回
     * @param sheet 代码型枚举值页面
     * @param metaTypeRow 元数据规范行号
     * @param codeEnumRow 代码型枚举值行号
     */
    public void setCodeEnumReturn(XSSFSheet sheet,int metaTypeRow,String codeEnumRow){
        int codeEnumRowInt = 0;
        //根据value获得所有的key值
        List<String> codeEnumRowList = getKeyByValue(codeEnumRow);
        if(null != codeEnumRowList && 0 < codeEnumRowList.size()){
            for(String codeEnumRowStr:codeEnumRowList){
                try{
                    codeEnumRowInt = Integer.parseInt(codeEnumRowStr);//将行号转换为Int类型
                }catch (NumberFormatException e){
                    log.error("行号转换为Int类型失败！", e);
                }
                if(codeEnumRowInt != 0){
                    XSSFRow row = sheet.getRow(codeEnumRowInt);//获得行
                    XSSFCell cell = row.getCell(6);//获得列
                    CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
                    Hyperlink hyperlink = creationHelper.createHyperlink(Hyperlink.LINK_URL);//超链接类型
                    String typeLink = "#表2ESB元数据规范!K"+metaTypeRow;
                    hyperlink.setAddress(typeLink);
                    if(null != cell){
                        cell.setHyperlink(hyperlink);
                    }else{
                        log.error("代码型枚举值单元格获取失败！行列号：G"+codeEnumRowStr);
                    }
                }else{
                    log.error("填充代码型枚举值[链接返回]失败！行列号：G" + codeEnumRowStr);
                }
            }
        }
    }

    /**
     * 根据value获得所有的key
     * @param codeEnumRow
     * @return
     */
    public List<String> getKeyByValue(String codeEnumRow){
        List<String> list = new ArrayList<String>();
        if(null != codeEnumRow && null != codeEnumMap){
            for(String key:codeEnumMap.keySet()){
                if(codeEnumMap.get(key).equals(codeEnumMap.get(codeEnumRow))){
                    list.add(key);
                }
            }
        }
        return list;
    }

    /**
     * 填充一格单元格的值
     * @param cell
     * @param cellStyle
     * @param value
     */
    public void setCellValue(XSSFCell cell, XSSFCellStyle cellStyle, String value){
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

    /**
     * 读取模版版
     * @param templatePath 模版路径
     * @return 模版
     */
    public XSSFWorkbook getTempalteWb(String templatePath){
        File file = new File(templatePath);
        XSSFWorkbook wb = null;
        BufferedInputStream in = null;
        InputStream is;
        try {
            is = new FileInputStream(file);
            wb = new XSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 获取元数据规范行属性
     * @param wb
     * @return
     */
    public static XSSFCellStyle getMetaTypeRowStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(new XSSFColor(new Color(217,217,217)));
        XSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 获取元数据规范单元格属性
     * @param wb
     * @return
     */
    public static XSSFCellStyle getMetaTypeCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(new XSSFColor(new Color(204,255,255)));
        XSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 获取枚举值单元格类型
     * @param wb
     * @return
     */
    public static XSSFCellStyle getMetaTypeCodeEnumCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        XSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 设置单元格中链接类型
     * @param wb
     * @return
     */
    public static XSSFCellStyle getMetaTypeHyperLinkCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(new XSSFColor(new Color(204,255,255)));//RGB自定义单元格颜色
        XSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(IndexedColors.BLUE.getIndex());//字体颜色
        font.setFontHeightInPoints((short) 9);
        font.setUnderline(Font.U_SINGLE);//单条下划线
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 设置枚举值中单元格链接类型
     * @param wb
     * @return
     */
    public static XSSFCellStyle getCodeEnumHyperLinkCellStyle(XSSFWorkbook wb){
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        XSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(IndexedColors.BLUE.getIndex());//字体颜色
        font.setFontHeightInPoints((short) 9);
        font.setUnderline(Font.U_SINGLE);//单条下划线
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

}

























