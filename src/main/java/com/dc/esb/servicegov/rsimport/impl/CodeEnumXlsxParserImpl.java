package com.dc.esb.servicegov.rsimport.impl;

import com.dc.esb.servicegov.dao.impl.MetaTypeCodeEnumDaoImpl;
import com.dc.esb.servicegov.entity.MetaTypeCodeEnum;
import com.dc.esb.servicegov.rsimport.support.ExcelUtils;
import com.dc.esb.servicegov.service.impl.LogInfoServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by xhx113 on 2016/3/21.
 */
@Component
public class CodeEnumXlsxParserImpl {
    @Autowired
    MetaTypeCodeEnumDaoImpl metaTypeCodeEnumDao;
    @Autowired
    LogInfoServiceImpl logInfoService;

    private static final Log log = LogFactory.getLog(CodeEnumXlsxParserImpl.class);

    private static final String SHEET_NAME = "附1标准代码枚举值";
    private static final int START_ROW_NUM = 2;
    private static final String STANDARD_NUM = "标准编号";
    private static int STANDARD_NUM_NUM = 0;
    private static final String STANDARD_CHNAME = "标准名称";
    private static int STANDARD_CHNAME_NUM = 1;
    private static final String CODE_VALUE = "代码值";
    private static int CODE_VALUE_NUM = 2;
    private static final String CODE_MEANING = "代码含义";
    private static int CODE_MEANING_NUM = 3;
    private static final String ORTHER = "其他";
    private static int ORTHER_NUM = 4;
    private static final String SOURCE = "来源";
    private static int SOURCE_NUM = 5;
    private static final String LINK = "链接";
    private static int LINK_NUM = 6;
    private static final String DATE = "更新日期";
    private static int DATE_NUM = 7;
    private static final String PERSON_IN_CHARGE = "责任人";
    private static int PIC_NUM = 8;


    /**
     * 根据sheet名拿到页面
     * @param workbook 整个元数据规范文件
     */
    public void parse(Workbook workbook) {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if(sheet != null){
            parseSheet(sheet);
        }
    }

    /**
     * 对页面内容进行处理
     * @param sheet 存放代码型枚举值的一页
     */
    public void parseSheet(Sheet sheet){
        initIndexColnum(sheet.getRow(1));
        for(int rowNum = START_ROW_NUM;rowNum <= sheet.getLastRowNum();rowNum++){
            Row row = sheet.getRow(rowNum);
            MetaTypeCodeEnum codeEnum = parseRow(row,rowNum);
            if(null == codeEnum) continue;
            if(!uniqueCodeEnum(codeEnum.getStandardChName(),codeEnum.getCodeValue())) continue;
            try{
                metaTypeCodeEnumDao.save(codeEnum);
            }catch(Exception e){
                log.error("导入元数据代码型枚举值失败["+codeEnum.getStandardChName()+"]失败",e);;
                logInfoService.saveLog("第"+(rowNum+1)+"行导入["+codeEnum.getStandardChName()+"]失败"+e.getMessage(),"附1标准代码枚举值");
            }
        }
    }

    /**
     * 判断唯一性
     * @param standardChName
     * @return
     */
    public boolean uniqueCodeEnum(String standardChName,String codeValue){
        List<MetaTypeCodeEnum> entitys = metaTypeCodeEnumDao.getCodeEnumByNameAndCode(standardChName, codeValue);
        if(entitys != null){
            if(entitys.size() > 1){
                logInfoService.saveLog("中文名称："+entitys.get(0).getStandardChName()+"，代码值："+entitys.get(0).getCodeValue(),"附1标准代码枚举值重复值");
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    /**
     * 将一行数据转化为一个实体
     * @param row 一行数据
     * @param rowNum 行号
     * @return 实体
     */
    public MetaTypeCodeEnum parseRow(Row row,int rowNum){
        try{
            MetaTypeCodeEnum codeEnum = new MetaTypeCodeEnum();
//            codeEnum.setStandarNum(getValueFromCell(row,STANDARD_NUM_NUM));
            codeEnum.setStandardChName(getValueFromCell(row,STANDARD_CHNAME_NUM));
            codeEnum.setCodeValue(getValueFromCell(row,CODE_VALUE_NUM));
            codeEnum.setCodeMeaning(getValueFromCell(row,CODE_MEANING_NUM));
            codeEnum.setOrther(getValueFromCell(row,ORTHER_NUM));
            codeEnum.setSource(getValueFromCell(row,SOURCE_NUM));
            codeEnum.setLink(getValueFromCell(row,LINK_NUM));
            codeEnum.setDate(getValueFromCell(row,DATE_NUM));
            codeEnum.setPersonInCharge(getValueFromCell(row,PIC_NUM));
            return codeEnum;
        }catch(Exception e){
            log.error(e, e);
            logInfoService.saveLog("第"+(rowNum+1)+"行解析数据失败！", "附1标准代码枚举值");
        }


        return null;
    }

    /**
     * @param row  行数据
     * @param cellNum  列号
     * @return 单元格内的值
     */
    public String getValueFromCell(Row row,int cellNum){
        return ExcelUtils.getValue(row.getCell(cellNum));
    }

    /**
     * 初始化所有字段
     * @param row 列名称一行
     */
    public void initIndexColnum(Row row){
        for(int i = 0;i < row.getLastCellNum();i++){
            String content = row.getCell(i).getStringCellValue();//将Cell中的值转化为String并拿到
            if(STANDARD_NUM.equals(content)){STANDARD_NUM_NUM = i;}
            if(STANDARD_CHNAME.equals(content)){STANDARD_CHNAME_NUM = i;}
            if(CODE_VALUE.equals(content)){CODE_VALUE_NUM = i;}
            if(CODE_MEANING.equals(content)){CODE_MEANING_NUM = i;}
            if(ORTHER.equals(content)){ORTHER_NUM = i;}
            if(SOURCE.equals(content)){SOURCE_NUM = i;}
            if(LINK.equals(content)){LINK_NUM = i;}
            if(DATE.equals(content)){DATE_NUM = i;}
            if(PERSON_IN_CHARGE.equals(content)){PIC_NUM = i;}
        }
    }

}
