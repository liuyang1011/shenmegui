package com.dc.esb.servicegov.rsimport.impl;

import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.Version;
import com.dc.esb.servicegov.rsimport.IResourceParser;
import com.dc.esb.servicegov.rsimport.support.ExcelUtils;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.VersionServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.BatchUpdateException;

@Component
public class MetadataXlsxParserImpl implements IResourceParser {

    private static final Log log = LogFactory.getLog(MetadataXlsxParserImpl.class);

    private static final String SHEET_NAME = "数据字典";
    private static final int START_ROW_NUM = 2;
    private static final int DATA_CATEGORY_COLUMN = 0;
    private static final int BUZZ_CATEGORY_COLUMN = 2;
    private static final int METADATA_ID_COLUMN = 3;
    private static final int CHINESE_NAME_COLUMN = 4;
    private static final int METADATA_NAME_COLUMN = 5;
    private static final int CATEGORY_WORD_ID = 6;
    private static final int TYPE_COLUMN = 7;
    private static final int DATA_FORMULA_COLUMN = 8;
    private static final int OPT_DATE_COLUMN = 13;
    private static final int OPT_USER_COLUMN = 14;
    @Autowired
    private MetadataServiceImpl metadataService;
    @Autowired
    private VersionServiceImpl versionService;
    @Override
    public void parse(Workbook workbook) {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        parseSheet(sheet);
    }

    private void parseSheet(Sheet sheet) {
//		List<Metadata> metadatas = new ArrayList<Metadata>();
        for (int rowNum = START_ROW_NUM; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Metadata metadata = parseRow(row);
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            metadata.setOptUser(userName);
            try {
                metadataService.addMetadata(metadata);
                versionService.addVersion(Constants.Version.TARGET_TYPE_METADATA, metadata.getMetadataId(), Constants.Version.TYPE_ELSE);//创建版本
            } catch (NonUniqueObjectException e) {
                log.error("元数据[" + metadata.getMetadataId() + "]重复,执行覆盖！", e);
                Metadata metadataToDel = metadataService.getById(metadata.getMetadataId());
                metadataService.delete(metadataToDel);
                metadataService.save(metadata);
                if(StringUtils.isEmpty(metadataToDel.getVersionId())){
                    versionService.addVersion(Constants.Version.TARGET_TYPE_METADATA, metadata.getMetadataId(), Constants.Version.TYPE_ELSE);//创建版本
                }else{
                    versionService.editVersion(metadata.getVersionId());//编辑版本
                }
            } catch (Exception e) {
                log.error("导入元数据[" + metadata.getMetadataId() + "]失败", e);
            }
        }
    }

    private Metadata parseRow(Row row) {
        Metadata metadata = new Metadata();
        metadata.setMetadataId(getValueFromCell(row, METADATA_ID_COLUMN));
        metadata.setChineseName(getValueFromCell(row, CHINESE_NAME_COLUMN));
        metadata.setMetadataName(getValueFromCell(row, METADATA_NAME_COLUMN));
        metadata.setCategoryWordId(getValueFromCell(row, CATEGORY_WORD_ID));
        metadata.setDataCategory(getValueFromCell(row, DATA_CATEGORY_COLUMN));
        metadata.setBuzzCategory(getValueFromCell(row, BUZZ_CATEGORY_COLUMN));
        String dataFormula = getValueFromCell(row, DATA_FORMULA_COLUMN);
        //TODO 本地化修改
        String[] str = dataFormula.split("[()]+");
        String type = getTypeFromFormula(str[0]);
        String length = "";
        String scale = "";
        if (str.length > 1) {
            length = getLengthFromFormula(str[1].replaceAll("，", ","));
            scale = getScaleFromFormula(str[1].replaceAll("，", ","));
        }
        metadata.setType(type);
        metadata.setLength(length);
        metadata.setScale(scale);
        metadata.setOptDate(getValueFromCell(row, OPT_DATE_COLUMN));
        metadata.setOptUser(getValueFromCell(row, OPT_USER_COLUMN));
        metadata.setStatus(Constants.Metadata.STATUS_UNAUDIT);
        return metadata;
    }

    public static String getTypeFromFormula(String formula) {
       /* String type = "String";
        if (null != formula) {
            if (StringUtils.containsIgnoreCase(formula, "a")) {
                type = "String";
            } else if (StringUtils.containsIgnoreCase(formula, "n")) {
                type = "Number";
            }
        }*/
        return formula;
    }

    public static String getLengthFromFormula(String formula) {
        String length = "";
        if (null != formula) {
            String str[] = formula.split(",");
            length = str[0];
        }
        /*if (null != formula) {
            int indexOfSeparator = formula.indexOf("!");
            if (indexOfSeparator < 0) {
                indexOfSeparator = formula.indexOf("n");
            }
            if (indexOfSeparator > 0) {
                String lengthStr = formula.substring(0, indexOfSeparator);
                if (StringUtils.isNumeric(lengthStr)) {
                    length = lengthStr;
                }
            }
        }*/
        return length;
    }

    public static String getScaleFromFormula(String formula) {
        String scale = "";

        if (null != formula) {
            String str[] = formula.split(",");
            if (str.length > 1) {
                scale = str[1];
            }
        }

        /*if (null != formula) {
            int startOfScale = formula.indexOf("(");
            int endOfScale = formula.indexOf(")");
            if (startOfScale > 0 && endOfScale > 0 && endOfScale > startOfScale) {
                String tmp = formula.substring(startOfScale + 1, endOfScale);
                if (StringUtils.isNumeric(tmp)) {
                    scale = tmp;
                }
            }
        }*/
        return scale;
    }

    public static String getValueFromCell(Row row, int column) {
        return ExcelUtils.getValue(row.getCell(column));
    }


}
