package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class StandardXMLConfigExportGender extends ConfigExportGenerator{
    private Log log = LogFactory.getLog(StandardXMLConfigExportGender.class);
//    /**
//     * 生成请求文件
//     * @param serviceInvoke
//     */
//    @Override
//    public void  generateRequest(ServiceInvoke serviceInvoke, String fileName){
//        try {
//            String serviceId = serviceInvoke.getServiceId();
//            String operationId = serviceInvoke.getOperationId();
//            Operation operation = operationService.getOperation(serviceId, operationId);
//
//            Document doc = DocumentHelper.createDocument();
//            Element serviceElement = doc.addElement("service");//根节点
//            addAttribute(serviceElement, "package_type", "xml");
//            addAttribute(serviceElement, "store-mode", "UTF-8");
//            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);
//            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);
//
//            createFile(doc, fileName);
//        }catch (Exception e){
//            log.error("生成请求文件失败！", e);
//        }
//    }
//
//    /**
//     * 生成out文件
//     * @param serviceInvoke
//     */
//    @Override
//    public void  generateResponse(ServiceInvoke serviceInvoke, String fileName){
//        try {
//            String serviceId = serviceInvoke.getServiceId();
//            String operationId = serviceInvoke.getOperationId();
//            Operation operation = operationService.getOperation(serviceId, operationId);
//
//            Document doc = DocumentHelper.createDocument();
//            Element serviceElement = doc.addElement("service");//根节点
//            addAttribute(serviceElement, "package_type", "xml");
//            addAttribute(serviceElement, "store-mode", "UTF-8");
//            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);
//            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);
//
//            createFile(doc,fileName);
//        }catch (Exception e){
//            log.error("生成响应文件失败！", e);
//        }
//    }

    public void fillPackageParserServiceHead(Operation operation, String interfaceId, Element targetElement, String targetName, boolean arrayFlag){
        String[] headIds = operation.getHeadId().split("\\,");//服务报文头
        for(String headId : headIds){
            Element headElement = targetElement.addElement(headId.toUpperCase());//添加服务报文头标签 例：<SYS_HEAD> <APP_HEAD>
            SDA reServiceHeadSDA = sdaService.getByStructName(headId, targetName);
            List<SDA> sdas = sdaService.getServiceHeadAll(headId, reServiceHeadSDA.getId());//徽商业务:显示全部syshead，apphead
            fillPackageParserElement(headElement, sdas);
        }
    }

    /**
     * 填充body元素
     * @param operation
     * @param targetElement
     */
    public void fillPackageParserBody(Operation operation, String interfaceId, Element targetElement, String structName, boolean arrayFlag){
        Element bodyElement = targetElement.addElement("BODY");
        SDA reSDA = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), structName);
        List<SDA> sdas = sdaService.getChildExceptServiceHead(reSDA.getId(), operation.getHeadId());
        if(StringUtils.isNotEmpty(interfaceId)){//查询接口报文头中对应约束元素syshead，apphead的加入对应头标签，其他的加入body标签
            List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
            for(InterfaceHeadRelate relate : relates){
                List<SDA> interfaceheadSDAs = sdaService.getByInterfaceHeadBodySDAs(relate.getHeadId(), structName);
                addListContent(sdas, interfaceheadSDAs);
            }
        }
        fillPackageParserElement(bodyElement, sdas);
    }

    public void fillPackageParserElement(Element parentElement, List<SDA> children){
        for(SDA child : children){
            if("array".equalsIgnoreCase(child.getType()) || "struct".equalsIgnoreCase(child.getType())){//处理有子节点的情况，CRCB：添加array节点
                Element arrayElement = parentElement.addElement("array");
                Element childElement = arrayElement.addElement(child.getStructName());
                addAttribute(childElement, "metadataid", child.getMetadataId());
                addAttribute(childElement, "type", child.getType());
                if("struct".equalsIgnoreCase(child.getType().toLowerCase())){
                    addAttribute(childElement, "is_struct", "true");
                }else{
                    addAttribute(childElement, "is_struct", "false");
                }
                List<SDA> subChildren = sdaService.getChildren(child);
                fillPackageParserElement(childElement, subChildren);
            }else{
                Element childElement = parentElement.addElement(child.getStructName());
                addAttribute(childElement, "metadataid", child.getMetadataId());
                addAttribute(childElement, "chinese_name", child.getStructAlias());
            }
        }
    }
}
