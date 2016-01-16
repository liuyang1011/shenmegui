package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class ConfigExportGenerator {
    private Log log = LogFactory.getLog(ConfigExportGenerator.class);
    @Autowired
    OperationServiceImpl operationService;
    @Autowired
    SDAServiceImpl sdaService;
    @Autowired
    InterfaceServiceImpl interfaceService;
    @Autowired
    InterfaceHeadRelateServiceImpl interfaceHeadRelateService;
    @Autowired
    IdaServiceImpl idaService;
    public void generate(ServiceInvoke serviceInvoke, String path){
        if(Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType())){
            //生成文件路径
            path = path + File.separator + "in_config";
        }
        if(Constants.INVOKE_TYPE_PROVIDER.equals(serviceInvoke.getType())){
            path = path + File.separator + "out_config";
        }
        generateRequest(serviceInvoke, path);
        genrateServiceFile(serviceInvoke, path);
        generateResponse(serviceInvoke, path);
    }
    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateRequest(ServiceInvoke serviceInvoke, String path){
    }

    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateResponse(ServiceInvoke serviceInvoke, String path){
    }

    /**
     * 生成服务定义文件
     * @param serviceInvoke
     * @param path
     * @return
     */
    public void genrateServiceFile(ServiceInvoke serviceInvoke, String path){
        try {
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Operation operation = operationService.getOperation(serviceId, operationId);
            com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();
            String fileName = path + File.separator + "service_" + serviceId + operationId + ".xml";

            Document doc = DocumentHelper.createDocument();
            Element rootElement = doc.addElement("S" + serviceId + operationId);//根节点
            Element requestElement = rootElement.addElement("request");
            Element reqSdoRoottElement = requestElement.addElement("sdoroot");
            Element responseElement = rootElement.addElement("response");
            Element resSdoRoottElement = responseElement.addElement("sdoroot");
            fillServiceHead(operation, serviceInvoke.getInterfaceId(), reqSdoRoottElement, Constants.ElementAttributes.REQUEST_NAME, true);
            fillServiceHead(operation, serviceInvoke.getInterfaceId(),resSdoRoottElement, Constants.ElementAttributes.RESPONSE_NAME, true);
            fillBody(operation, serviceInvoke.getInterfaceId(), reqSdoRoottElement, Constants.ElementAttributes.REQUEST_NAME, true);
            fillBody(operation, serviceInvoke.getInterfaceId(), resSdoRoottElement, Constants.ElementAttributes.RESPONSE_NAME, true);

            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成服务定义文件失败！", e);
        }
    }

    /**
     * 填充服务报文头元素
     * @param operation
     * @param targetElement
     */
    public void fillServiceHead(Operation operation, String interfaceId, Element targetElement, String targetName, boolean arrayFlag){
        String[] headIds = operation.getHeadId().split("\\,");//服务报文头
        for(String headId : headIds){
            Element headElement = targetElement.addElement(headId.toUpperCase());//添加服务报文头标签 例：<SYS_HEAD> <APP_HEAD>
            SDA reServiceHeadSDA = sdaService.getByStructName(headId, targetName);
//            List<SDA> sdas = sdaService.getServiceHeadRequired(headId, reServiceHeadSDA.getId());//服务报文头必输SDA
            List<SDA> sdas = sdaService.getServiceHeadAll(headId, reServiceHeadSDA.getId());//徽商服务头全量;
//            SDA reOperationSDA = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), targetName);
//            List<SDA> operationHeadSDAs = sdaService.getOperationHeadSDAs(operation.getServiceId(), operation.getOperationId(), headId, reOperationSDA.getId());//场景sda中约束条件为相应报文头的元素
////            sdas.addAll(operationHeadSDAs);
//            addListContent(sdas, operationHeadSDAs);
//            if(StringUtils.isNotEmpty(interfaceId)){//查询接口报文头中对应约束元素syshead，apphead的加入对应头标签，其他的加入body标签
//                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
//
//                InterfaceHeadRelate relate =interfaceHeadRelateService.findUniqueBy("interfaceId", interfaceId);
//                if(null != relate){
//                    List<SDA> interfaceheadSDAs = sdaService.getByInterfaceHeadSDAs(relate.getHeadId(), targetName, headId );
//                    addListContent(sdas, interfaceheadSDAs);
//                }
//            }
            fillElement(headElement, sdas, arrayFlag);
        }
    }

    /**
     * 填充body元素
     * @param operation
     * @param targetElement
     */
    public void fillBody(Operation operation,String interfaceId, Element targetElement, String structName, boolean arrayFlag){
        Element bodyElement = targetElement.addElement("BODY");
        SDA reSDA = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), structName);
        List<SDA> sdas = sdaService.getChildExceptServiceHead(reSDA.getId(), operation.getHeadId());
        if(StringUtils.isNotEmpty(interfaceId)){//查询接口报文头中对应约束元素syshead，apphead的加入对应头标签，其他的加入body标签
            List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
            for(InterfaceHeadRelate relate : relates){
                InterfaceHead interfaceHead = relate.getInterfaceHead();
                List<SDA> interfaceheadSDAs = sdaService.getByInterfaceHeadBodySDAs(relate.getHeadId(), reSDA.getStructName() );
                addListContent(sdas, interfaceheadSDAs);
            }
        }
        fillElement(bodyElement, sdas, arrayFlag);
    }
    public void fillElement(Element parentElement, List<SDA> children, boolean arrayFlag ){
        for(SDA child : children){
            if("array".equalsIgnoreCase(child.getType()) || "struct".equalsIgnoreCase(child.getType())){//处理有子节点的情况，CRCB：添加array节点
                Element element;
                if(arrayFlag){
                    element = parentElement.addElement("array");
                }else{
                    element = parentElement;
                }
                Element childElement = element.addElement(child.getStructName());
                addAttribute(childElement, "metadataid", child.getMetadataId());
                addAttribute(childElement, "type", child.getType());
                if("struct".equalsIgnoreCase(child.getType().toLowerCase())){
                    addAttribute(childElement, "is_struct", "true");
                }else{
                    addAttribute(childElement, "is_struct", "false");
                }
                Element sdoElement = childElement.addElement("sdo");
                List<SDA> subChildren = sdaService.getChildren(child);
                fillElement(sdoElement, subChildren, arrayFlag);
            }else{
                Element childElement = parentElement.addElement(child.getStructName());
                addAttribute(childElement, "metadataid", child.getMetadataId());
                addAttribute(childElement, "chinese_name", child.getStructAlias());
            }
        }
    }
    public void addAttribute(Element element, String name, String value){
        if(StringUtils.isEmpty(value)){
            value = "";
        }
        element.addAttribute(name, value);
    }

    public void addListContent(List<SDA> parentList, List<SDA> childList){
        for(SDA child : childList){
            if(!parentList.contains(child)){
                boolean exsitFlag = false;
                for(SDA parent : parentList){
                    if(parent.getMetadataId().equals(child.getMetadataId())){
                        exsitFlag = true;
                        break;
                    }
                }
                if(!exsitFlag) parentList.add(child);
            }
        }
    }
    public String getReqFilePath(ServiceInvoke serviceInvoke, String path){
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();

        String fileName = path + File.separator + "channel_" + system.getSystemAb() + "_service_" + serviceId + operationId + ".xml";
        return  fileName;
    }

    public String getResFilePath(ServiceInvoke serviceInvoke, String path){
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();

        String fileName = path + File.separator + "service_" + serviceId + operationId + "_system_" + system.getSystemAb() + ".xml";
        return  fileName;
    }

    public void createFile(Document doc, String fileName){
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            FileOutputStream fos = new FileOutputStream(fileName);
            XMLWriter writer = new XMLWriter(fos, format);
            writer.write(doc);
            writer.close();
        } catch (IOException e) {
            log.error(e, e);
        }
    }
}
