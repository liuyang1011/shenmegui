package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Created by yehu on 2016/3/1.
 */
@Component
public class MixXMLConfigExportGenerator extends ConfigExportGenerator {
    // 引用的服务
    @Autowired
    private IdaServiceImpl idaService;


    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;


    private Log log = LogFactory.getLog(MixXMLConfigExportGenerator.class);

    // 定义模版文件路径
    private String outReqTempPath = "mix_template/out_config/service_serviceId_system_systemId.xml";
    private String outResTempPath = "mix_template/out_config/channel_systemId_service_serviceId.xml";

    private List<String> uniqueList = new ArrayList<String>();

    /**
     * 生成in_config/metadata/channelToservice.xml
     * @param serviceInvoke
     * @param path
     */
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        generateOutResponse(serviceInvoke, path);
    }


    /**
     * 生成in_config/metadata/serviceTosystem.xml
     * @param serviceInvoke
     * @param path
     */
    public void  generateInResponse(ServiceInvoke serviceInvoke, String path){
        generateOutRequest(serviceInvoke, path);
    }


    /**
     * 生成out_config/metadata/serviceTosystem.xml
     * @param serviceInvoke
     * @param path
     */
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
        try {
            // 加载对应的模版文件
            ClassLoader loader = this.getClass().getClassLoader();
            String templatePath = loader.getResource(this.outReqTempPath).getPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(templatePath);

            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();

            // 获取root节点
            Element sdoRootElement = doc.getRootElement();
            Element rootElement = sdoRootElement.element("root");

            // 填充comm_req
            Element comReqElement = rootElement.addElement("comm_req");
            interfaceHeadDealReq(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, comReqElement);

            // 填充input
            Element inputElement = rootElement.addElement("input");
            //获取报文头元数据集合
            Set<String> allMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.REQUEST_NAME, serviceId, operationId);
            SDA reqSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqSDA);
            if(null != reqChildren && reqChildren.size() > 0){
                for(SDA reqChild : reqChildren){
                    renderReqSDAExistMetadata(inputElement, reqChild, allMetadatas);
                }
            }
            //生成文件
            String fileName = getResFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成out request文件失败！", e);
        }
    }

    /**
     * out_config/metadata/channelToservice.xml
     * @param serviceInvoke
     * @param path
     */
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
        try {
            log.info("开始In端请求......");
            ClassLoader loader = this.getClass().getClassLoader();
            String templatePath = loader.getResource(this.outResTempPath).getPath();
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(templatePath);

            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();

            // 获取root节点
            Element sdoRootElement = doc.getRootElement();
            Element rootElement = sdoRootElement.element("root");

            // 填充comm_req
            Element comResElement = rootElement.addElement("comm_res");
            interfaceHeadDealReq(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, comResElement);

            // 填充output
            Element outputElement = rootElement.addElement("output");
            //获取报文头元数据集合
            Set<String> allMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.RESPONSE_NAME, serviceId, operationId);
            SDA resSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> resChildren = sdaService.getChildren(resSDA);
            if(null != resChildren && resChildren.size() > 0){
                for(SDA reqChild : resChildren){
                    renderReqSDAExistMetadata(outputElement, reqChild, allMetadatas);
                }
            }
            //生成文件
            String fileName = getReqFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成out response文件失败！", e);
        }
    }

    /**
     * 生成服务定义文件
     * @param serviceInvoke
     * @param path
     * @return
     */
    public void genrateServiceFile(ServiceInvoke serviceInvoke, String path){
        try {
            //消费方
            long time = java.lang.System.currentTimeMillis();
            List<String> allMetadataList = new ArrayList<String>();
            uniqueList.clear();
            String serviceId = null;
            String operationId = null;
            if(null != serviceInvoke){
                serviceId = serviceInvoke.getServiceId();
                operationId = serviceInvoke.getOperationId();
            }
            if(null != serviceId && null != serviceId){
                String fileName = path + File.separator + "service_" + serviceId + operationId + ".xml";

                Document doc = DocumentHelper.createDocument();
                Element rootElement = doc.addElement("S" + serviceId + operationId);//根节点
                Element requestElement = rootElement.addElement("request");
                Element reqSdoRoottElement = requestElement.addElement("sdoroot");

                //填充请求syshead
                Element reqSysHeadElement = reqSdoRoottElement.addElement("SYS_HEAD");
                SDA reqServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqSysHeadSdas = sdaService.getChildren(reqServiceHeadSDA);//sys_head_request...go1
                if(null != reqSysHeadSdas && reqSysHeadSdas.size() > 0){
                    for(SDA sda : reqSysHeadSdas){
                        allMetadataList.addAll(renderServiceSysHeadSDAWithList(reqSysHeadElement, sda));
                    }
                }
                //填充apphead
                Element reqAppHeadElement = reqSdoRoottElement.addElement("APP_HEAD");
                SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqAppHeadSdas = sdaService.getChildren(reqAppHeadSDA);
                if(null != reqAppHeadSdas && reqAppHeadSdas.size() > 0){
                    for(SDA sda : reqAppHeadSdas){
                        allMetadataList.addAll(renderServiceSysHeadSDAWithList(reqAppHeadElement, sda));
                    }
                }
                //如果提供方有LocalHead，则在Root中加入LocalHead报文头元素
                Element reqLocalHeadElement = reqSdoRoottElement.addElement("LOCAL_HEAD");
                allMetadataList.addAll(interfaceLocalHeadDealWithList(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, reqSdoRoottElement, reqLocalHeadElement));
                //填充body
                //获取报文头元数据集合

//            Set<String> allReqMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.REQUEST_NAME, serviceId, operationId);
                Element reqBodyElement = reqSdoRoottElement.addElement("BODY");
                //如果提供方是非标，则在body中加入报文头元素
                allMetadataList.addAll(interfaceHeadDealWithList(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, reqBodyElement));
                SDA reqSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqSdas = sdaService.getChildren(reqSDA);
                if(null != reqSdas && reqSdas.size() > 0){
                    for(SDA sda : reqSdas){
                        renderServiceSDAExistMetadata(reqBodyElement, sda, allMetadataList);
                    }
                }
                uniqueList.clear();
                allMetadataList.clear();

                Element responseElement = rootElement.addElement("response");
                Element resSdoRoottElement = responseElement.addElement("sdoroot");

                //填充syshead
                Element resSysHeadElement = resSdoRoottElement.addElement("SYS_HEAD");
                SDA resServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resSysHeadSdas = sdaService.getChildren(resServiceHeadSDA);
                if(null != resSysHeadSdas && resSysHeadSdas.size() > 0){
                    for(SDA sda : resSysHeadSdas){
                        allMetadataList.addAll(renderServiceSysHeadSDAWithList(resSysHeadElement, sda));
                    }
                }
                //填充apphead
                Element resAppHeadElement = resSdoRoottElement.addElement("APP_HEAD");
                SDA resAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> reAppHeadSdas = sdaService.getChildren(resAppHeadSDA);
                if(null != reAppHeadSdas && reAppHeadSdas.size() > 0){
                    for(SDA sda : reAppHeadSdas){
                        allMetadataList.addAll(renderServiceSysHeadSDAWithList(resAppHeadElement, sda));
                    }
                }
                //如果提供方有LocalHead，则在Root中加入LocalHead报文头元素
                Element resLocalHeadElement = resSdoRoottElement.addElement("LOCAL_HEAD");
                allMetadataList.addAll(interfaceLocalHeadDealWithList(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, resSdoRoottElement, resLocalHeadElement));
                //填充body
                //获取报文头元数据集合

//            Set<String> allResMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.RESPONSE_NAME, serviceId, operationId);
                Element resBodyElement = resSdoRoottElement.addElement("BODY");

                //如果提供方是非标，则在body中加入报文头元素
                allMetadataList.addAll(interfaceHeadDealWithList(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, resBodyElement));
                SDA resSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resSdas = sdaService.getChildren(resSDA);
                if(null != resSdas && resSdas.size() > 0){
                    for(SDA sda : resSdas){
                        renderServiceSDAExistMetadata(resBodyElement, sda, allMetadataList);
                    }
                }
                long useTime = java.lang.System.currentTimeMillis() - time;
                log.info("填充服务定义，耗时"+useTime+"毫秒");
                createFile(doc, fileName);
            }
        }catch (Exception e){
            log.error("生成服务定义文件失败！", e);
        }

    }

    public void renderReqSDA(Element parentElement, SDA sda){
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parentElement.addElement(sda.getStructName());
                element.addAttribute("metadataid", sda.getMetadataId());
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", "array");
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        element.addAttribute("metadataid", sda.getMetadataId()+"_array");
                        List<SDA> children = sdaService.getChildren(sda);//go3...
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDA(element, chid);
                            }
                        }
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                        List<SDA> children = sdaService.getChildren(sda);//go3...
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDA(element, chid);
                            }
                        }
                    }
                }
            }

        }
    }

    public void renderReqSDAExistMetadata(Element parentElement, SDA sda, Set<String> allMetadatas){
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parentElement.addElement(sda.getStructName());
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
                        List<SDA> children = sdaService.getChildren(sda);
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDAExistMetadata(element, chid, allMetadatas);
                            }
                        }
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                        List<SDA> children = sdaService.getChildren(sda);
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDAExistMetadata(element, chid, allMetadatas);
                            }
                        }
                    }
                }
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
            List<SDA> children = sdaService.getChildren(sda);//无需查找子节点，报文长度,渠道标识.....go2
            if(null != children &&  0 < children.size()){
                Element sdoElemtn = element.addElement("sdo");
                for(SDA child : children){
                    renderServiceSysHeadSDA(sdoElemtn, child);
                }
            }
        }
        return list;
    }

    public void renderServiceSysHeadSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);//无需查找子节点，报文长度,渠道标识.....go2
            if(null != children &&  0 < children.size()){
                Element sdoElemtn = element.addElement("sdo");
                for(SDA child : children){
                    renderServiceSysHeadSDA(sdoElemtn, child);
                }
            }
        }
    }

    public void interfaceHeadDealReq(String serviceId, String operationId, String structName, Element bodyElement ){
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
//                                SDA sda = sdaService.findUniqueBy(params);
                                String sdaId = child.getSdaId();
                                SDA sda = null;
                                if(null != sdaId){
                                    sda = sdaService.getById(child.getSdaId());
                                }
                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
                                    renderReqSDA(bodyElement, sda);
                                }
                            }
                        }
                    }
                }
            }

        }
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
        if(null != reqSysHeadChildren && reqSysHeadChildren.size() > 0){
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                inputMetadata(allHeadMetadatas, reqSysHeadChild);
            }
        }
        //填充reqapphead
        SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, structName);
        List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
        if(null != reqAppHeadChildren && reqAppHeadChildren.size() > 0 ){
            for(SDA reqAppHeadChild : reqAppHeadChildren){
                inputMetadata(allHeadMetadatas, reqAppHeadChild);
            }
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
        String type = sda.getType();
        if(null !=  type &&("array".equalsIgnoreCase(type)||"struct".equalsIgnoreCase(type))){
            List<SDA> children = sdaService.getChildren(sda);//go4...
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    metadataList.add(child.getMetadataId());
//                    inputMetadata(metadataList, child);
                }
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
//                                SDA sda = sdaService.findUniqueBy(params);
                                String sdaId = child.getSdaId();
                                SDA sda = null;
                                if(null != sdaId){
                                    sda = sdaService.getById(child.getSdaId());
                                }
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

    public void renderServiceSDAExistMetadata(Element parent, SDA sda, List<String> allMetadatas){
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
                        List<SDA> children = sdaService.getChildren(sda);
                        if(null != children && 0 < children.size()){
                            Element sdoElemtn = element.addElement("sdo");
                            for(SDA chid : children){
                                renderServiceSDAExistMetadata(sdoElemtn, chid, allMetadatas);
                            }
                        }
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
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
    }

}
