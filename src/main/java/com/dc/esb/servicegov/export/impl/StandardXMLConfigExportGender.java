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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class StandardXMLConfigExportGender extends ConfigExportGenerator{
    private Log log = LogFactory.getLog(StandardXMLConfigExportGender.class);
    /**
     * 生成请求文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        try {
            log.info("导入In端请求报文... ");
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Operation operation = operationService.getOperation(serviceId, operationId);

            String fileName = getReqFilePath(serviceInvoke, path);

            Document doc = DocumentHelper.createDocument();
            Element serviceElement = doc.addElement("service");//根节点
            addAttribute(serviceElement, "package_type", "xml");
            addAttribute(serviceElement, "store-mode", "UTF-8");
            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);
            Element localHeadElement = serviceElement.addElement("LOCAL_HEAD");
            fillPackageParserLocalHead(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, serviceElement, localHeadElement);
            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);

            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成请求文件失败！", e);
        }
    }

    /**
     * 生成相应
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInResponse(ServiceInvoke serviceInvoke, String path){
        try {
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Operation operation = operationService.getOperation(serviceId, operationId);

            String fileName = getResFilePath(serviceInvoke, path);

            Document doc = DocumentHelper.createDocument();
            Element serviceElement = doc.addElement("service");//根节点
            addAttribute(serviceElement, "package_type", "xml");
            addAttribute(serviceElement, "store-mode", "UTF-8");
            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);
            Element localHeadElement = serviceElement.addElement("LOCAL_HEAD");
            fillPackageParserLocalHead(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, serviceElement, localHeadElement);
            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);

            createFile(doc,fileName);
        }catch (Exception e){
            log.error("生成响应文件失败！", e);
        }
    }
    @Override
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
        try {
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Operation operation = operationService.getOperation(serviceId, operationId);

            String fileName = getResFilePath(serviceInvoke, path);

            Document doc = DocumentHelper.createDocument();
            Element serviceElement = doc.addElement("service");//根节点
            addAttribute(serviceElement, "package_type", "xml");
            addAttribute(serviceElement, "store-mode", "UTF-8");
            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);
            Element localHeadElement = serviceElement.addElement("LOCAL_HEAD");
            fillPackageParserLocalHead(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, serviceElement, localHeadElement);
            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.REQUEST_NAME, false);

            createFile(doc,fileName);
        }catch (Exception e){
            log.error("生成响应文件失败！", e);
        }
    }

    @Override
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
        try {
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Operation operation = operationService.getOperation(serviceId, operationId);

            String fileName = getReqFilePath(serviceInvoke, path);

            Document doc = DocumentHelper.createDocument();
            Element serviceElement = doc.addElement("service");//根节点
            addAttribute(serviceElement, "package_type", "xml");
            addAttribute(serviceElement, "store-mode", "UTF-8");
            fillPackageParserServiceHead(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);
            Element localHeadElement = serviceElement.addElement("LOCAL_HEAD");
            fillPackageParserLocalHead(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, serviceElement, localHeadElement);
            fillPackageParserBody(operation, serviceInvoke.getInterfaceId(), serviceElement, Constants.ElementAttributes.RESPONSE_NAME, false);

            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成请求文件失败！", e);
        }
    }
    public void fillPackageParserServiceHead(Operation operation, String interfaceId, Element targetElement, String targetName, boolean arrayFlag){
        String[] headIds = operation.getHeadId().split("\\,");//服务报文头
        for(String headId : headIds){
            Element headElement = targetElement.addElement(headId.toUpperCase());//添加服务报文头标签 例：<SYS_HEAD> <APP_HEAD>
            SDA reServiceHeadSDA = sdaService.getByStructName(headId, targetName);
            if(null != reServiceHeadSDA){
                List<SDA> sdas = sdaService.getServiceHeadAll(headId, reServiceHeadSDA.getId());//徽商业务:显示全部syshead，apphead
                fillPackageParserElement(headElement, sdas);
            }
        }

        // 判断是否有系统自己的报文头“local_head”


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
                Element childElement = parentElement.addElement(child.getStructName());
                String metadataId = child.getMetadataId();
                if("array".equalsIgnoreCase(child.getType()) && !metadataId.contains("_array")){
                    metadataId +="_array";
                }
                addAttribute(childElement, "metadataid", metadataId);
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

    public void fillPackageParserLocalHead(String serviceId, String operationId, String structName, Element parentElement, Element localHeadElement ){
        //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
            for(Object inter : interfaces){
                String interfaceId = inter.toString();
                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                if(null != relates && 0 < relates.size()){
                    for(InterfaceHeadRelate interfaceHeadRelate : relates){
                        String headId = interfaceHeadRelate.getHeadId();
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("headId", headId);
                        params.put("structName", structName);
                        Ida ida = idaService.findUniqueBy(params);
                        if(null != ida){
                            String hql3 = " from Ida where _parentId = ? and structName is not null";
                            List<Ida> children = idaService.find(hql3, ida.getId());
                            for(Ida child : children){
                                params.clear();
                                params.put("headId", headId);
                                params.put("xpath", child.getXpath());
                                SDA sda = sdaService.findUniqueBy(params);
                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
                                    if(null != sda.getConstraint() && sda.getConstraint().trim().equalsIgnoreCase("local_head")){
                                        renderLocalHeadSDA(localHeadElement, sda);
                                    }else{
                                        parentElement.remove(localHeadElement);
                                    }
                                }else{
                                    parentElement.remove(localHeadElement);
                                }
                            }
                        }
                    }
                    break;
                }
            }

        }
    }

    public void renderLocalHeadSDA(Element parent, SDA sda){
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parent.addElement(sda.getStructName());
                String metadataId = sda.getMetadataId();
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", sda.getType().toLowerCase());
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        if(!metadataId.contains("_array")){
                            metadataId += "_array";
                        }
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                    }
                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        for(SDA chid : children){
                            renderLocalHeadSDA(element, chid);
                        }
                    }
                }
                element.addAttribute("metadataid", metadataId);
            }
        }
    }
}
