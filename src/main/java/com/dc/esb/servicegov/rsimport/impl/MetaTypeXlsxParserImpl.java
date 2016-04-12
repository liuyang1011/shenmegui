package com.dc.esb.servicegov.rsimport.impl;

import com.dc.esb.servicegov.controller.MetaTypeImportController;
import com.dc.esb.servicegov.entity.MetaType;
import com.dc.esb.servicegov.rsimport.support.ExcelUtils;
import com.dc.esb.servicegov.service.impl.LogInfoServiceImpl;
import com.dc.esb.servicegov.service.impl.MetaTypeServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xhx113 on 2016/3/9.
 */
@Component
public class MetaTypeXlsxParserImpl {
    @Autowired
    private MetaTypeServiceImpl metaTypeService;
    @Autowired
    LogInfoServiceImpl logInfoService;

    private static final Log log = LogFactory.getLog(MetaTypeXlsxParserImpl.class);

    private static final String SHEET_NAME = "表2ESB元数据规范";
    private static final String SHEET_NAME2 = "元数据规范";
    private static final int START_ROW_NUM = 2;

    private static final String LABEL = "标签";
    private static final String CLASSIFY = "一级分类";
    private static final String NAME = "元数据标准名称";
    private static final String ENGLISH_NAME = "元数据标准英文简称";
    private static final String ENGLISH_NAME_ESB = "ESB标准英文全称";
    private static final String USEGUIDE = "使用指引";
    private static final String STANDARD_CHNAME = "标准中文名称";
    private static final String STANDARD_ENNAME = "标准英文名称";
    private static final String STANDARD_ALIAS = "标准别名";
    private static final String MEANING = "业务含义";
    private static final String TYPE = "数据类型";
    private static final String FORMAT = "数据格式";
    private static final String VALUE_RANGE = "取值范围";
    private static final String MEASUREMENT = "度量单位";
    private static final String SUSCEPTIBILITY = "敏感度";
    private static final String RELEVANT_STANDARD = "相关标准";
    private static final String INFORMATION_RELATION = "与相关信息项关系";
    private static final String CODE_RULE = "代码编码规则";
    private static final String INFORMATION_RULE = "信息项业务规则";
    private static final String ACCORDINGBY = "制定依据";
//    private static final String AUTHORITY_SYSTEM = "权威系统/发文";
    private static final String STANDARD_SOURCE = "标准初始来源";
    private static final String APPLYTO = "业务应用领域";
    private static final String RELEVANCEMOTIF = "相关关联主题";
//    private static final String MADESTANDARDUSER = "标准定义者";
//    private static final String MANAGESTANDARDUSER = "标准管理者";
//    private static final String USESTANDARDUSER = "标准使用者";
    private static final String VERSIONDATE = "版本日期";
    private static final String REMARK = "备注";

    private static int LABEL_COLUMN = 0;
    private static int CLASSIFY_COLUMN = 1;
    private static int NAME_COLUMN = 2;
    private static int ENGLISH_NAME_COLUMN = 3;
    private static int ENGLISH_NAME_ESB_COLUMN = 4;
    private static int USEGUIDE_COLUMN = 5;
    private static int STANDARD_CHNAME_COLUMN = 6;
    private static int STANDARD_ENNAME_COLUMN = 7;
    private static int STANDARD_ALIAS_COLUMN = 8;
    private static int MEANING_COLUMN = 9;
    private static int TYPE_COLUMN = 10;
    private static int FORMAT_COLUMN = 11;
    private static int VALUE_RANGE_COLUMN = 12;
    private static int MEASUREMENT_COLUMN = 13;
    private static int SUSCEPTIBILITY_COLUMN = 14;
    private static int RELEVANT_STANDARD_COLUMN = 15;
    private static int INFORMATION_RELATION_COLUMN = 16;
    private static int CODE_RULE_COLUMN = 17;
    private static int INFORMATION_RULE_COLUMN = 18;
    private static int ACCORDINGBY_COLUMN = 19;
//    private static int AUTHORITY_SYSTEM_COLUMN = 20;
    private static int STANDARD_SOURCE_COLUMN = 21;
    private static int APPLYTO_COLUMN = 22;
    private static int RELEVANCEMOTIF_COLUMN = 23;
//    private static int MADESTANDARDUSER_COLUMN = 24;
//    private static int MANAGESTANDARDUSER_COLUMN = 25;
//    private static int USESTANDARDUSER_COLUMN = 26;
    private static int VERSIONDATE_COLUMN = 27;
    private static int REMARK_COLUMN = 28;


    /**
     * 根据页名拿到元数据一页
     * @param workbook
     */
    public void parse(Workbook workbook) {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if(null == sheet){
            sheet = workbook.getSheet(SHEET_NAME2);
        }
        if(sheet != null){
            parseSheet(sheet);
        }
    }

    /**
     *
     * @param sheet
     */
    private void parseSheet(Sheet sheet) {
        initIndexColnum(sheet);
        for (int rowNum = START_ROW_NUM; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if(fitration(row)){//对分割行进行过滤
                MetaType metaType = parseRow(row, rowNum);//将数据放入metaType
                if(null == metaType) continue;
                if(!metaTypeService.uniqueValName(metaType.getName())) continue;//对重复元素进行筛选
                try{
                    String userName = (String) SecurityUtils.getSubject().getPrincipal();//获得当前用户
                    metaType.setOptUser(userName);
                    metaType.setOptDate(DateUtils.format(new Date()));//获得当前时间
                    metaTypeService.save(metaType);
                }catch (Exception e){
                    log.error("导入元数据[" + metaType.getName() + "]失败", e);
                    logInfoService.saveLog("第"+(rowNum+1)+"行导入[" + metaType.getName() + "]失败！"+e.getMessage(), "表2ESB元数据规范");
                }
            }
        }
    }
    /**
     * 对分割行进行过滤
     * @param row
     * @return
     */
    public boolean fitration(Row row){
        String flag = getValueFromCell(row,1);
        if(null==flag||"".equals(flag)){//若此行第2个单元格为空-
            return false;
        }
        return true;
    }

    /**
     * 读取一行数据，将其写入元数据表
     * @param row
     * @param rowNum
     * @return
     */
    public MetaType parseRow(Row row,int rowNum){
        try {
            MetaType metaType = new MetaType();
            metaType.setLabel(getValueFromCell(row,LABEL_COLUMN));
            metaType.setName(getValueFromCell(row, ENGLISH_NAME_COLUMN));
            metaType.setChineseName(getValueFromCell(row, NAME_COLUMN));
            metaType.setClassify(getValueFromCell(row, CLASSIFY_COLUMN));
            metaType.setEnAllName(getValueFromCell(row, ENGLISH_NAME_ESB_COLUMN));
            metaType.setStandardChName(getValueFromCell(row, STANDARD_CHNAME_COLUMN));
            metaType.setStandardEnName(getValueFromCell(row, STANDARD_ENNAME_COLUMN));
            metaType.setStandardAlias(getValueFromCell(row, STANDARD_ALIAS_COLUMN));
            metaType.setMeaning(getValueFromCell(row, MEANING_COLUMN));
            metaType.setType(getValueFromCell(row, TYPE_COLUMN));
            metaType.setTypeLink(setLink(row, TYPE_COLUMN));//对代码型类型进行二次处理
            metaType.setFormat(getValueFromCell(row, FORMAT_COLUMN));
            metaType.setValueRange(getValueFromCell(row, VALUE_RANGE_COLUMN));
            metaType.setMeasurement(getValueFromCell(row, MEASUREMENT_COLUMN));
            metaType.setSusceptibility(getValueFromCell(row,SUSCEPTIBILITY_COLUMN));
            metaType.setRelevantStandard(getValueFromCell(row,RELEVANT_STANDARD_COLUMN));
            metaType.setInformationRelation(getValueFromCell(row,INFORMATION_RELATION_COLUMN));
            metaType.setCodeRule(getValueFromCell(row,CODE_RULE_COLUMN));
            metaType.setInformationRule(getValueFromCell(row,INFORMATION_RULE_COLUMN));
            metaType.setAccordingBy(getValueFromCell(row,ACCORDINGBY_COLUMN));
//            metaType.setAuthoritySytem(getValueFromCell(row,AUTHORITY_SYSTEM_COLUMN));
            metaType.setStandardSource(getValueFromCell(row,STANDARD_SOURCE_COLUMN));
            metaType.setApplyTo(getValueFromCell(row,APPLYTO_COLUMN));
            metaType.setRelevanceMotif(getValueFromCell(row,RELEVANCEMOTIF_COLUMN));
//            metaType.setMadeStandardUser(getValueFromCell(row,MADESTANDARDUSER_COLUMN));
//            metaType.setManageStandardUser(getValueFromCell(row,MANAGESTANDARDUSER_COLUMN));
//            metaType.setUseStandardUser(getValueFromCell(row,USESTANDARDUSER_COLUMN));
            metaType.setVersionDate(getValueFromCell(row,VERSIONDATE_COLUMN));
            metaType.setRemark(getValueFromCell(row,REMARK_COLUMN));
            //后加的字段
            metaType.setUseGuide(getValueFromCell(row,USEGUIDE_COLUMN));

            return metaType;
        }catch (Exception e){
            log.error(e, e);
            logInfoService.saveLog("第"+(rowNum+1)+"行解析数据失败！", "表2ESB元数据规范");
        }
        return null;
    }

    /**
     * 从单元格中拿到值并作一些处理
     * @param row
     * @param column
     * @return
     */
    public  String getValueFromCell(Row row, int column) {
        String cellValue = ExcelUtils.getValue(row.getCell(column));
        if(cellValue.endsWith("代码型\")")){
            cellValue = "代码型";
        }
        return cellValue;
    }

    /**
     * 对单元格中链接类型处理
     * @return
     */
    public String setLink(Row row, int column){
        String cellValue = ExcelUtils.getValue(row.getCell(column));
        return cellValue;
    }

    /**
     * 初始化字段序号
     * @param sheet
     */
    public void initIndexColnum(Sheet sheet){
        if(sheet != null){
            Row row = sheet.getRow(1);//对第二行进行操作
            for(int i = 0; i < row.getLastCellNum(); i++){
                String content = row.getCell(i).getStringCellValue();
                if(LABEL.equals(content)){
                    LABEL_COLUMN = i;
                }
                if(CLASSIFY.equals(content)){
                    CLASSIFY_COLUMN = i;
                }
                if(NAME.equals(content)){
                    NAME_COLUMN = i;
                }
                if(ENGLISH_NAME.equals(content)){
                    ENGLISH_NAME_COLUMN = i;
                }
                if(ENGLISH_NAME_ESB.equals(content)){
                    ENGLISH_NAME_ESB_COLUMN = i;
                }
                if(STANDARD_CHNAME.equals(content)){
                    STANDARD_CHNAME_COLUMN = i;
                }
                if(STANDARD_ENNAME.equals(content)){
                    STANDARD_ENNAME_COLUMN = i;
                }
                if(STANDARD_ALIAS.equals(content)){
                    STANDARD_ALIAS_COLUMN = i;
                }
                if(MEANING.equals(content)){
                    MEANING_COLUMN = i;
                }
                if(TYPE.equals(content)){
                    TYPE_COLUMN = i;
                }
                if(FORMAT.equals(content)){
                    FORMAT_COLUMN = i;
                }
                if(VALUE_RANGE.equals(content)){
                    VALUE_RANGE_COLUMN = i;
                }
                if(MEASUREMENT.equals(content)){
                    MEASUREMENT_COLUMN = i;
                }
                if(SUSCEPTIBILITY.equals(content)){
                    SUSCEPTIBILITY_COLUMN = i;
                }
                if(RELEVANT_STANDARD.equals(content)){
                    RELEVANT_STANDARD_COLUMN = i;
                }
                if(INFORMATION_RELATION.equals(content)){
                    INFORMATION_RELATION_COLUMN = i;
                }
                if(CODE_RULE.equals(content)){
                    CODE_RULE_COLUMN = i;
                }
                if(INFORMATION_RULE.equals(content)){
                    INFORMATION_RULE_COLUMN = i;
                }
                if(ACCORDINGBY.equals(content)){
                    ACCORDINGBY_COLUMN = i;
                }
//                if(AUTHORITY_SYSTEM.equals(content)){
//                    AUTHORITY_SYSTEM_COLUMN = i;
//                }
                if(STANDARD_SOURCE.equals(content)){
                    STANDARD_SOURCE_COLUMN = i;
                }
                if(APPLYTO.equals(content)){
                    APPLYTO_COLUMN = i;
                }
                if(RELEVANCEMOTIF.equals(content)){
                    RELEVANCEMOTIF_COLUMN = i;
                }
//                if(MADESTANDARDUSER.equals(content)){
//                    MADESTANDARDUSER_COLUMN = i;
//                }
//                if(USESTANDARDUSER.equals(content)){
//                    USESTANDARDUSER_COLUMN = i;
//                }
//                if(MANAGESTANDARDUSER.equals(content)){
//                    MANAGESTANDARDUSER_COLUMN = i;
//                }
                if(VERSIONDATE.equals(content)){
                    VERSIONDATE_COLUMN = i;
                }
                if(REMARK.equals(content)){
                    REMARK_COLUMN = i;
                }
                if(USEGUIDE.equals(content)){
                    USEGUIDE_COLUMN = i;
                }
            }
        }
    }

}

































