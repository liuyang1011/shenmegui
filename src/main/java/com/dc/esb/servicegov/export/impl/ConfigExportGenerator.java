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
import java.util.*;

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
    ServiceInvokeServiceImpl serviceInvokeService;

    public List<String> uniqueList = new ArrayList<String>();

    public void generate(ServiceInvoke serviceInvoke, String path){
        if(Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType())){
            //生成文件路径
            path = path + File.separator + "in_config";
            generateInRequest(serviceInvoke, path);
            generateInResponse(serviceInvoke, path);
        }
        if(Constants.INVOKE_TYPE_PROVIDER.equals(serviceInvoke.getType())){
            path = path + File.separator + "out_config";
            generateOutRequest(serviceInvoke, path);
            generateOutResponse(serviceInvoke, path);
        }
        genrateServiceFile(serviceInvoke, path);

    }
    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        log.info("generateInRequest");
    }

    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
    }
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
    }

    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    public void  generateInResponse(ServiceInvoke serviceInvoke, String path){
        log.info("generateInRequest");
    }


    /**
     * 生成服务定义文件
     * @param serviceInvoke
     * @param path
     * @return
     */
    public void genrateServiceFile(ServiceInvoke serviceInvoke, String path){
        try {
            uniqueList.clear();
            String serviceId = null;
            String operationId = null;
            if(null != serviceInvoke){
                serviceId = serviceInvoke.getServiceId();
                operationId = serviceInvoke.getOperationId();
            }
            if(null != serviceId && null != operationId){
                String fileName = path + File.separator + "service_" + serviceId + operationId + ".xml";
                Document doc = DocumentHelper.createDocument();
                Element rootElement = doc.addElement("S" + serviceId + operationId);//根节点
                Element requestElement = rootElement.addElement("request");
                Element reqSdoRoottElement = requestElement.addElement("sdoroot");

                //填充syshead
                Element reqSysHeadElement = reqSdoRoottElement.addElement("SYS_HEAD");
                SDA reqServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqSysHeadSdas = sdaService.getChildren(reqServiceHeadSDA);
                if(null != reqSysHeadSdas){
                    for(SDA sda : reqSysHeadSdas){
                        renderServiceSysHeadSDA(reqSysHeadElement, sda);
                    }
                }else{
                    log.error("无法获取syshead！");
                }
                //填充apphead
                Element reqAppHeadElement = reqSdoRoottElement.addElement("APP_HEAD");
                SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqAppHeadSdas = sdaService.getChildren(reqAppHeadSDA);
                if(null != reqAppHeadSdas){
                    for(SDA sda : reqAppHeadSdas){
                        renderServiceSysHeadSDA(reqAppHeadElement, sda);
                    }
                }else{
                    log.error("无法获取apphead！");
                }
                //如果提供方有LocalHead，则在Root中加入LocalHead报文头元素
                Element reqLocalHeadElement = reqSdoRoottElement.addElement("LOCAL_HEAD");
                interfaceLocalHeadDeal(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, reqSdoRoottElement, reqLocalHeadElement);

                //填充body
                //获取报文头元数据集合
                Set<String> allReqMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.REQUEST_NAME, serviceId, operationId);
                Element reqBodyElement = reqSdoRoottElement.addElement("BODY");
                //如果提供方是非标，则在body中加入报文头元素
                interfaceHeadDeal(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, reqBodyElement);
                SDA reqSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqSdas = sdaService.getChildren(reqSDA);
                for(SDA sda : reqSdas){
                    renderServiceSDAExistMetadata(reqBodyElement, sda, allReqMetadatas);
                }

                uniqueList.clear();
                Element responseElement = rootElement.addElement("response");
                Element resSdoRoottElement = responseElement.addElement("sdoroot");

                //填充syshead
                Element resSysHeadElement = resSdoRoottElement.addElement("SYS_HEAD");
                SDA resServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resSysHeadSdas = sdaService.getChildren(resServiceHeadSDA);
                for(SDA sda : resSysHeadSdas){
                    renderServiceSysHeadSDA(resSysHeadElement, sda);
                }

                //填充apphead
                Element resAppHeadElement = resSdoRoottElement.addElement("APP_HEAD");
                SDA resAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> reAppHeadSdas = sdaService.getChildren(resAppHeadSDA);
                for(SDA sda : reAppHeadSdas){
                    renderServiceSysHeadSDA(resAppHeadElement, sda);
                }

                //如果提供方有LocalHead，则在Root中加入LocalHead报文头元素
                Element resLocalHeadElement = resSdoRoottElement.addElement("LOCAL_HEAD");
                interfaceLocalHeadDeal(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, resSdoRoottElement, resLocalHeadElement);

                //填充body
                //获取报文头元数据集合
                Set<String> allResMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.RESPONSE_NAME, serviceId, operationId);
                Element resBodyElement = resSdoRoottElement.addElement("BODY");
                //如果提供方是非标，则在body中加入报文头元素
                interfaceHeadDeal(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, resBodyElement);
                SDA resSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resSdas = sdaService.getChildren(resSDA);
                for(SDA sda : resSdas){
                    renderServiceSDAExistMetadata(resBodyElement, sda, allResMetadatas);
                }
                createFile(doc, fileName);
            }
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

        //如果提供方是非标，则在body中加入报文头元素
        interfaceHeadDeal(operation.getServiceId(), operation.getOperationId(), structName, bodyElement);

        SDA reSDA = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), structName);
        List<SDA> sdas = sdaService.getChildExceptServiceHead(reSDA.getId(), operation.getHeadId());

//        if(StringUtils.isNotEmpty(interfaceId)){//查询接口报文头中对应约束元素syshead，apphead的加入对应头标签，其他的加入body标签
//            List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
//            for(InterfaceHeadRelate relate : relates){
//                List<SDA> interfaceheadSDAs = sdaService.getByInterfaceHeadBodySDAs(relate.getHeadId(), reSDA.getStructName() );
//                addListContent(sdas, interfaceheadSDAs);
//            }
//        }
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
                if(null != child.getType()){
                    addAttribute(childElement, "type", child.getType().toLowerCase());
                }
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


    public void interfaceLocalHeadDeal(String serviceId, String operationId, String structName, Element parentElement, Element localHeadElement ){
        //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
            log.warn("场景中存在非标提供者！");
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
                                        renderServiceSDA(localHeadElement, sda);
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


    public void interfaceHeadDeal(String serviceId, String operationId, String structName, Element bodyElement ){
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
                                    if(null != sda.getConstraint() && sda.getConstraint().trim().equalsIgnoreCase("body")){
                                        renderServiceSDA(bodyElement, sda);
                                    }
                                }
                            }
                        }

                    }
                }
            }

        }
    }

    public void renderServiceSDA(Element parent, SDA sda){
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parent.addElement(sda.getStructName());
                element.addAttribute("metadataid", sda.getMetadataId());
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", sda.getType().toLowerCase());
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        element.addAttribute("metadataid", sda.getMetadataId()+"_array");
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                    }
                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        Element sdoElemtn = element.addElement("sdo");
                        for(SDA chid : children){
                            renderServiceSDA(sdoElemtn, chid);
                        }
                    }
                }
            }
        }
    }

    public void renderServiceSysHeadSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
        }
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            Element sdoElemtn = element.addElement("sdo");
            for(SDA child : children){
                renderServiceSysHeadSDA(sdoElemtn, child);
            }
        }
    }

    public List<String> renderServiceSysHeadSDAWithList(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        list.add(sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            list.add(sda.getMetadataId());
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
        }
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            Element sdoElemtn = element.addElement("sdo");
            for(SDA child : children){
                renderServiceSysHeadSDA(sdoElemtn, child);
            }
        }
        return list;
    }


    /**
     * 获取报文头所有元数据集合
     * @author yehu
     * @param structName
     * @param serviceId
     * @param operationId
     * @return
     */
    public Set<String> getAllHeadMetadatas(String structName, String serviceId, String operationId){
        Set<String> allHeadMetadatas =  new HashSet<String>();


        //填充reqsyshead
        SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, structName);
        List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
        for(SDA reqSysHeadChild : reqSysHeadChildren){
            inputMetadata(allHeadMetadatas, reqSysHeadChild);
        }

        //填充reqapphead
        SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, structName);
        List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
        for(SDA reqAppHeadChild : reqAppHeadChildren){
            inputMetadata(allHeadMetadatas, reqAppHeadChild);
        }

        //填充localhead
        getLocalHeadMetadata(serviceId, operationId, structName, allHeadMetadatas);

        return allHeadMetadatas;
    }

    /**
     * 插入对应的元数据
     * @author yehu
     * @param metadataList
     * @param sda
     */
    public void inputMetadata(Set<String> metadataList, SDA sda){
        metadataList.add(sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                inputMetadata(metadataList, child);
            }
        }
    }

    /**
     * 获取系统自己报文头元数据
     * @author yehu
     * @param serviceId
     * @param operationId
     * @param structName
     * @param metadataList
     */
    public void getLocalHeadMetadata(String serviceId, String operationId, String structName, Set<String> metadataList){
        //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
            log.warn("场景中存在非标提供者！");
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
                                    inputMetadata(metadataList, sda);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void renderServiceSDAExistMetadata(Element parent, SDA sda, Set<String> allMetadatas){
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parent.addElement(sda.getStructName());
                if(allMetadatas.contains(sda.getMetadataId())){
                    sda.setMetadataId(sda.getMetadataId()+"_C");
                    allMetadatas.add(sda.getMetadataId()+"_C");
                }
                element.addAttribute("metadataid", sda.getMetadataId());
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", "array");
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        element.addAttribute("metadataid", sda.getMetadataId()+"_array");
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                    }
                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        Element sdoElemtn = element.addElement("sdo");
                        for(SDA chid : children){
                            renderServiceSDAExistMetadata(sdoElemtn, chid, allMetadatas);
                        }
                    }
                }
            }

        }
    }


    public List<String> interfaceLocalHeadDealWithList(String serviceId, String operationId, String structName, Element parentElement, Element localHeadElement ){
        List<String> localHeadList = new ArrayList<String>();
        //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
//            log.warn("场景中存在非标提供者！");
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
//                                params.clear();
//                                params.put("headId", headId);
//                                params.put("xpath", child.getXpath());
                                String sdaId = child.getSdaId();
                                SDA sda = null;
                                if(null != sdaId){
                                    sda = sdaService.getById(child.getSdaId());
                                }
                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
                                    if(sda.getConstraint().trim().equalsIgnoreCase("local_head")){
                                        localHeadList = renderServiceSDAWithList(localHeadElement, sda);
                                    }else{
                                        parentElement.remove(localHeadElement);
                                    }
                                }else{
                                    parentElement.remove(localHeadElement);
                                }
                            }
                        }
                    }
                }
            }
        }
        return localHeadList;
    }


    public List<String> interfaceHeadDealWithList(String serviceId, String operationId, String structName, Element bodyElement ){
        //查询非标提供者
        List<String> list = new ArrayList<String>();
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
//            log.warn("场景中存在非标提供者！");
            for(Object inter : interfaces){
                String interfaceId = inter.toString();
                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                if(null != relates && 0 < relates.size()){
                    for(InterfaceHeadRelate interfaceHeadRelate : relates){
                        String headId = interfaceHeadRelate.getHeadId();
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("headId", headId);
                        params.put("structName", structName);
//                    String hql2 = " from Ida where headId = ? and structName = ?";
                        Ida ida = idaService.findUniqueBy(params);
                        if(null != ida){
                            String hql3 = " from Ida where _parentId = ? and structName is not null";
                            List<Ida> children = idaService.find(hql3, ida.getId());
                            for(Ida child : children){
//                                params.clear();
//                                params.put("headId", headId);
//                                params.put("xpath", child.getXpath());
//                                SDA sda = sdaService.findUniqueBy(params);
                                String sdaId = child.getSdaId();
                                SDA sda = null;
                                if(null != sdaId){
                                    sda = sdaService.getById(child.getSdaId());
                                }
                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
                                    if(sda.getConstraint().trim().equalsIgnoreCase("body")){
                                        list.addAll(renderServiceSDAWithList(bodyElement, sda));
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return list;
    }

    public List<String> renderServiceSDAWithList(Element parent, SDA sda){
        List<String> list = new ArrayList<String>();
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                //            if(uniqueList.contains(sda.getStructName())){
//                return;
//            }
//            uniqueList.add(sda.getStructName());
                Element element = parent.addElement(sda.getStructName());
                element.addAttribute("metadataid", sda.getMetadataId());
                list.add(sda.getMetadataId());
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", "array");
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        element.addAttribute("metadataid", sda.getMetadataId()+"_array");
                        list.add(sda.getMetadataId());
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                    }
                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        Element sdoElemtn = element.addElement("sdo");
                        for(SDA chid : children){
                            renderServiceSDA(sdoElemtn, chid);
                        }
                    }
                }
            }

        }
        return list;
    }
}
