package com.dc.esb.servicegov.excel.support;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.awt.Color;

/**
 * Created by wang on 2015/8/17.
 */
public class CellStyleSupport {
    public static HSSFCellStyle commonStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        //生成一个字体
        HSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
    public static HSSFCellStyle arrayStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
//        cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        //生成一个字体
        HSSFFont font=wb.createFont();
        font.setFontName("宋体");
//        font.setColor(HSSFColor.DARK_YELLOW.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
//        cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
//        cellStyle.setFillForegroundColor(HSSFColor.DARK_YELLOW.index);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public static HSSFCellStyle leftStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//居中
        //生成一个字体
        HSSFFont font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public static CellStyle commonStyle(Workbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
        //生成一个字体
        Font font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(Font.COLOR_NORMAL);//XSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
    public static CellStyle arrayStyle(Workbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
//        cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        //生成一个字体
        Font font=wb.createFont();
        font.setFontName("宋体");
//        font.setColor(XSSFColor.DARK_YELLOW.index);//XSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
//        cellStyle.setFillBackgroundColor(XSSFColor.GREEN.index);
//        cellStyle.setFillForegroundColor(XSSFColor.DARK_YELLOW.index);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public static CellStyle leftStyle(Workbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        //生成一个字体
        Font font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(Font.COLOR_NORMAL);//XSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public static CellStyle hyperLinkStyle(Workbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//居中
        //生成一个字体
        Font font=wb.createFont();
        font.setFontName("宋体");
        font.setColor(IndexedColors.BLUE.getIndex());//XSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
}
