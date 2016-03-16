package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.export.util.FileUtil;
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
import java.lang.System;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    ServiceHeadServiceImpl serviceHeadService;
    @Autowired
    MetadataServiceImpl metadataService;
    @Autowired
    SDAAttrbuteServiceImpl sdaAttrbuteService;
    @Autowired
    IdaAttrbuteServiceImpl idaAttrbuteService;

    String channel_service_path = "template/config_export/nbcb/channel_service_template.xml";//默认请求文件模板路径
    String service_channel_path = "template/config_export/nbcb/service_system_template.xml";//默认响应文件模板路径

    String sysHeadReq;
    String appHeadReq;
    String localHeadReq;
    String requestText;
    String responseText;

    public void generate(ServiceInvoke serviceInvoke, String path){
        genrateServiceFile(serviceInvoke, path);
        if(Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType())){
            //生成文件路径
            path = path + File.separator + "in_config" + File.separator + serviceInvoke.getSystem().getSystemAb();
            generateInChannelServiceFile(serviceInvoke, path);
            generateInServiceChannelFile(serviceInvoke, path);
        }
        if(Constants.INVOKE_TYPE_PROVIDER.equals(serviceInvoke.getType())){
            path = path + File.separator + "out_config" + File.separator + serviceInvoke.getSystem().getSystemAb();
            generateOutChannelServiceFile(serviceInvoke, path);
            generateOutServiceChannelFile(serviceInvoke, path);
        }
    }
    /**
     *例： channel_ANHUI_service_anhuiServiceV1.xml
     * @param serviceInvoke
     * @param path
     */
    public void  generateInChannelServiceFile(ServiceInvoke serviceInvoke, String path){
        String filePath = getReqFilePath(serviceInvoke, path);
        generateRequest(serviceInvoke, filePath);
    }
    //例：service_2001300000101_system_NWVS.xml
    public void  generateInServiceChannelFile(ServiceInvoke serviceInvoke, String path){
        String filePath = getResFilePath(serviceInvoke, path);
        generateResponse(serviceInvoke, filePath);
    }
    public void  generateOutChannelServiceFile(ServiceInvoke serviceInvoke, String path){
        String filePath = getReqFilePath(serviceInvoke, path);
        generateResponse(serviceInvoke, filePath);
    }
    //例：service_2001300000101_system_NWVS.xml
    public void  generateOutServiceChannelFile(ServiceInvoke serviceInvoke, String path){
        String filePath = getResFilePath(serviceInvoke, path);
        generateRequest(serviceInvoke, filePath);
    }


    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateRequest(ServiceInvoke serviceInvoke, String path){
        Map<String, String> map = new HashMap<String, String>();
        Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
        String[] headIds = operation.getHeadId().split("\\,");//服务报文头
        for(String headId : headIds){
            ServiceHead serviceHead = serviceHeadService.findUniqueBy("headId", headId);
            map.put("${" + serviceHead.getHeadName() + "}$", getsServiceHeadStr(headId, Constants.ElementAttributes.REQUEST_NAME));//模板内容替换按照名称+$
        }
        map.put("${interfaceHead}$", getsInterfaceHeadStr(serviceInvoke, Constants.ElementAttributes.REQUEST_NAME));
        map.put("${standardBody}$", getsStandardBodyStr(serviceInvoke, Constants.ElementAttributes.REQUEST_NAME));
        map.put("${unStandardBody}$", getUnStandardBodyStr(serviceInvoke, Constants.ElementAttributes.REQUEST_NAME));
        try{
            String srcPath = ConfigExportGenerator.class.getResource("/").getPath() + getChannelServicePath();
            FileUtil.copyFile(srcPath, path, map);
        }catch (Exception e){
            log.error("生成请求文件出错", e);
        }

    }

    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateResponse(ServiceInvoke serviceInvoke, String path){
        Map<String, String> map = new HashMap<String, String>();
        Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
        String[] headIds = operation.getHeadId().split("\\,");//服务报文头
        for(String headId : headIds){
            ServiceHead serviceHead = serviceHeadService.findUniqueBy("headId", headId);
            map.put("${" + serviceHead.getHeadName() + "}$", getsServiceHeadStr(headId, Constants.ElementAttributes.RESPONSE_NAME));//模板内容替换按照名称+$
        }
        map.put("${interfaceHead}$", getsInterfaceHeadStr(serviceInvoke, Constants.ElementAttributes.RESPONSE_NAME));
        map.put("${standardBody}$", getsStandardBodyStr(serviceInvoke, Constants.ElementAttributes.RESPONSE_NAME));
        map.put("${unStandardBody}$", getUnStandardBodyStr(serviceInvoke, Constants.ElementAttributes.RESPONSE_NAME));
        try{
            String srcPath = ConfigExportGenerator.class.getResource("/").getPath() + getServiceChannelPath();
            FileUtil.copyFile(srcPath, path, map);
        }catch (Exception e){
            log.error("生成response文件出错", e);
        }

    }

    //根据服务报文头获取字符串
    public String getsServiceHeadStr(String headId, String targetName){
        ServiceHead serviceHead = serviceHeadService.findUniqueBy("headId", headId);
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("utf-8");
        Element element = doc.addElement(serviceHead.getHeadName());

        SDA reServiceHeadSDA = sdaService.getByStructName(headId, targetName);
//            List<SDA> sdas = sdaService.getServiceHeadRequired(headId, reServiceHeadSDA.getId());//服务报文头必输SDA
        List<SDA> sdas = sdaService.getServiceHeadAll(headId, reServiceHeadSDA.getId());//服务头全量;
        for(SDA sda : sdas){
            renderSDA(element, sda);
        }
        String str = getElementChildrenStr(element);
        return str;
    }


    //根据接口报文头获取字符串
    public String getsInterfaceHeadStr(ServiceInvoke serviceInvoke, String targetName){
        String str = "";
        List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", serviceInvoke.getInterfaceId());
        if(null != relates && 0 < relates.size()){
            Document doc = DocumentHelper.createDocument();
            doc.setXMLEncoding("utf-8");
            Element element = doc.addElement("InterfaceHead");
            for(InterfaceHeadRelate relate : relates){
                String headId = relate.getHeadId();
                Ida reInterfaceHeadIda = idaService.getByHeadIdIdStructName(headId, targetName);
                List<Ida> idas = idaService.findBy("_parentId", reInterfaceHeadIda.getId());
                for(Ida ida : idas){
                    renderInterfaceHeadIda(element, ida, headId);
                }
            }
            str = getElementChildrenStr(element);
        }
        return str;
    }
    //获取标准body
    public String getsStandardBodyStr(ServiceInvoke serviceInvoke, String structName){
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding("utf-8");
        Element element = doc.addElement("BODY");

        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        SDA sda = sdaService.getByStructName(serviceId, operationId, structName);
        List<SDA> children = sdaService.getChildren(sda);
        for(SDA child : children){
            renderSDA(element, child);
        }
        String result = getElementChildrenStr(element);
        return result;
    }
    //获取非标body
    public String getUnStandardBodyStr(ServiceInvoke serviceInvoke, String structName){
        String result = "";

        String interfaceId = serviceInvoke.getInterfaceId();
        if(StringUtils.isNotEmpty(interfaceId)){
            Document doc = DocumentHelper.createDocument();
            doc.setXMLEncoding("utf-8");
            Element element = doc.addElement("BODY");
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Ida ida = idaService.getByInterfaceIdStructName(interfaceId, structName);
            if(null != ida){
                List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
                for(Ida child : children){
                    renderBodyIda(element, child, serviceId, operationId);
                }
            }
            result = getElementChildrenStr(element);
        }
        return result;
    }
    //标准输出
    public void renderSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        sdaAttrbuteService.fillAttr(sda.getId(), element);//添加附加属性
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderSDA(element, child);
            }
        }
    }
    //非标报文头输出
    public void renderInterfaceHeadIda(Element parentElement, Ida ida, String headId){
        if(null != ida && StringUtils.isNotEmpty(ida.getStructName())){
            Element idaElement = parentElement.addElement(ida.getStructName());
            idaAttrbuteService.fillAttr(ida.getId(), idaElement);//ida附加属性
            Map<String, String> params = new HashMap<String, String>();
            params.put("headId", headId);
            params.put("xpath", ida.getXpath());
            SDA sda = sdaService.findUniqueBy(params);
            if(null != sda){
                sdaAttrbuteService.fillAttr(sda.getId(), idaElement);//SDA附加属性
                addAttribute(idaElement, "metadataId", sda.getMetadataId());
//                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
//                if(null != metadata){
//                    addAttribute(idaElement, "length", metadata.getLength());
//                    addAttribute(idaElement, "type", "array".equalsIgnoreCase(metadata.getType()) ? "array" : "string");;
//                }
            }
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    renderInterfaceHeadIda(idaElement, child, headId);
                }
            }
        }
    }
    //非标体输出
    public void renderBodyIda(Element parentElement, Ida ida, String serviceId, String operationId ){
        if(null != ida && StringUtils.isNotEmpty(ida.getStructName())){
            Element idaElement = parentElement.addElement(ida.getStructName());
            idaAttrbuteService.fillAttr(ida.getId(), idaElement);//ida附加属性
            Map<String, String> params = new HashMap<String, String>();
            params.put("serviceId", serviceId);
            params.put("operationId", operationId);
            params.put("xpath", ida.getXpath());
            SDA sda = sdaService.findUniqueBy(params);
            if(null != sda){
                sdaAttrbuteService.fillAttr(sda.getId(), idaElement);//sda附加属性
                addAttribute(idaElement, "metadataId", sda.getMetadataId());
//                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
//                if(null != metadata){
//                    addAttribute(idaElement, "length", metadata.getLength());
//                    addAttribute(idaElement, "type", "array".equalsIgnoreCase(metadata.getType()) ? "array" : "string");;
//                }
            }
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    renderBodyIda(idaElement, child, serviceId, operationId);
                }
            }
        }
    }
    //获取节点内容生成字符串
    public String getElementChildrenStr(Element element){
        String str = "";
        if(null != element){
            List<Element> children = element.elements();
            if(null != children && 0 < children.size()){
                String elementName = element.getName();
                str = element.asXML();
                String startStr = "<" + elementName +">";
                String endStr = "</" + elementName + ">";
                str = str.substring(startStr.length());//从前端截取
                if((str.length() - endStr.length()) <= 0){
                    return "";
                }else{
                    str = str.substring(0, str.length() - endStr.length());//从尾端截取
                }
            }
        }


        return str;
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
            List<SDA> sdas = sdaService.getServiceHeadAll(headId, reServiceHeadSDA.getId());//服务头全量;
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
    public static String getReqFilePath(ServiceInvoke serviceInvoke, String path){
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();

        String fileName = path + File.separator + "channel_" + system.getSystemAb() + "_service_" + serviceId + operationId + ".xml";
        return  fileName;
    }

    public static String getResFilePath(ServiceInvoke serviceInvoke, String path){
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
    //获取模板路径，子类重写此方法替换模板
    public String getChannelServicePath(){
        return channel_service_path;
    }
    //获取模板路径，子类重写此方法替换模板
    public String getServiceChannelPath(){
        return service_channel_path;
    }
}
