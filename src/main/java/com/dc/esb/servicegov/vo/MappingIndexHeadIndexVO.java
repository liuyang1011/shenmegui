package com.dc.esb.servicegov.vo;

import com.dc.esb.servicegov.util.ExcelTool;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;

/**
 * 用于根据index页头属性名称排序
 */
public class MappingIndexHeadIndexVO {
    final String  interfaceIdHead = "接口代码";
    final String  interfaceNameHead = "交易名称";
    final String  serviceNameHead = "服务名称";
    final String  operationIdHead = "原子服务ID";
    final String  operationNameHead = "原子服务名称";
    final String  consumerNamesHead = "调用方系统名称";
    final String  consumerAbsHead = "调用方";
    final String  providerAbsHead = "提供方";
    final String  typeHead = "接口方向";
    final String  interfaceProIdHead = "接口提供";
    final String  messageHead = "报文类型";
    final String  optUserHead = "处理人";
    final String  optDateHead = "更新时间";
    final String  protocolTranHead = "报文转换方向";
    final String  isUsedHead = "是否已有调用";
    final String  fileHead = "参考文档";
    final String  moduleHead = "模块划分";
    final String  isPentHead = "是否穿透";
    final String  interfaceHeadHead = "业务报文头编号";
    final String  interfaceStateHead = "交易状态";
    final String  operationStateHead = "场景状态";
    final String  isStandardHead = "是否标准";
    final String  remarkHead = "备注";
    final String  protocolHead = "关联协议";

    Integer  interfaceIdIndex = 0;
    Integer  interfaceNameIndex= 1;
    Integer  serviceNameIndex = 2;
    Integer  operationIdIndex = 3;
    Integer  operationNameIndex = 4;
    Integer  consumerNamesIndex = 5;
    Integer  consumerAbsIndex = 6;
    Integer  providerAbsIndex = 7;
    Integer  typeIndex = 8;
    Integer  interfaceProIdIndex = 9;
    Integer  messageIndex = 10;
    Integer  optUserIndex = 11;
    Integer  optDateIndex = 12;
    Integer  protocolTranIndex = 13;
    Integer  isUsedIndex = 14;
    Integer  fileIndex = 15;
    Integer  moduleIndex = 16;
    Integer  isPentIndex = 17;
    Integer  interfaceHeadIndex = 18;
    Integer  interfaceStateIndex = 19;
    Integer  operationStateIndex = 20;
    Integer  isStandardIndex = 21;
    Integer  remarkIndex = 22;
    Integer  protocolIndex = 23;

    public MappingIndexHeadIndexVO(Row row){
        for(int i = 0; i <= row.getLastCellNum(); i++) {//遍历第1行所有单元格
            String content = ExcelTool.getInstance().getCellContent(row.getCell(i));
            if(StringUtils.isEmpty(content)) continue;
            if (interfaceIdHead.equals(content)) {
                interfaceIdIndex = i;
            }
            if (interfaceNameHead.equals(content)) {
                interfaceNameIndex = i;
            }
            if (serviceNameHead.equals(content)) {
                serviceNameIndex = i;
            }
            if (operationIdHead.equals(content)) {
                operationIdIndex = i;
            }
            if (operationNameHead.equals(content)) {
                operationNameIndex = i;
            }
            if (consumerNamesHead.equals(content)) {
                consumerNamesIndex = i;
            }
            if (consumerAbsHead.equals(content)) {
                consumerAbsIndex = i;
            }
            if (providerAbsHead.equals(content)) {
                providerAbsIndex = i;
            }
            if (typeHead.equals(content)) {
                typeIndex = i;
            }
            if (content.contains(interfaceProIdHead)) {//接口提供系统id中可能有换行，以保函‘接口提供’字段为标准
                interfaceProIdIndex = i;
            }
            if(messageHead.equals(content)){
                messageIndex = i;
            }
            if (protocolHead.equals(content)) {
                protocolIndex = i;
            }
            if (optUserHead.equals(content)) {
                optUserIndex = i;
            }
            if (optDateHead.equals(content)) {
                optDateIndex = i;
            }
            if (protocolTranHead.equals(content)) {
                protocolTranIndex = i;
            }
            if (isUsedHead.equals(content)) {
                isUsedIndex = i;
            }
            if (fileHead.equals(content)) {
                fileIndex = i;
            }
            if (moduleHead.equals(content)) {
                moduleIndex = i;
            }
            if (isPentHead.equals(content)) {
                isPentIndex = i;
            }
            if (interfaceHeadHead.equals(content)) {
                interfaceHeadIndex = i;
            }
            if (interfaceStateHead.equals(content)) {
                interfaceStateIndex = i;
            }
            if (operationStateHead.equals(content)) {
                operationStateIndex = i;
            }
            if (isStandardHead.equals(content)) {
                isStandardIndex = i;
            }
            if (remarkHead.equals(content)) {
                remarkIndex = i;
            }
        }
    }
}
