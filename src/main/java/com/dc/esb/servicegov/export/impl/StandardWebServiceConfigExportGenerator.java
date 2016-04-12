package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.service.impl.InterfaceHeadServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class StandardWebServiceConfigExportGenerator extends ConfigExportGenerator{

    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;

    @Autowired
    private SystemServiceImpl systemService;

    private Log log = LogFactory.getLog(StandardWebServiceConfigExportGenerator.class);
    //模板文件
    private  String reqPath = "template/config_export/soap/channel_service_standard_soap.xml";
    private  String resPath = "template/config_export/soap/service_system_standard_soap.xml";

    private String xmlns = "http://esb.dcitsbiz.com/services/";
    private String xmlnsShort = "http://esb.dcitsbiz.com/";

    private String ReqHeader = "Header";
    private String ReqSysHead = "ReqSysHead";
    private String ReqBody = "Body";
    private String ReqAppHead = "ReqAppHead";
    private String ReqLocalHead = "ReqLocalHead";
    private String ReqAppBody = "ReqAppBody";

    private String RspHeader = "Header";
    private String RspSysHead = "s:RspSysHead";
    private String RspBody = "Body";
    private String RspAppHead = "s:RspAppHead";
    private String RspLocalHead = "s:RspLocalHead";
    private String RspAppBody = "s:RspAppBody";

    /**
     * 生成服务定义文件
     * @param serviceInvoke
     * @param path
     * @return
     */
    @Override
    public void genrateServiceFile(ServiceInvoke serviceInvoke, String path){
        try {
            long time = java.lang.System.currentTimeMillis();
            List<String> allMetadataList = new ArrayList<String>();
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
    public void renderServiceSDARsp(Element parent, SDA sda){
        if(null != sda){
//            if(uniqueList.contains(sda.getStructName())){
//                return;
//            }
//            uniqueList.add(sda.getStructName());
            Element element = parent.addElement("s:" + sda.getStructName());
            element.addAttribute("metadataid", sda.getMetadataId());
            if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                element.addAttribute("type", "array");
                if("array".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("is_struct", "false");
                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        Element sdoElemtn = element.addElement("sdo");
                        for(SDA chid : children){
                            renderServiceSDA(sdoElemtn, chid);
                        }
                    }
                }
                if("struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("is_struct", "true");
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
    /**
=======
>>>>>>> .r10127
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        try {
            long time = java.lang.System.currentTimeMillis();
            List<String> allMetadataList = new ArrayList<String>();
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(reqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            Element header = rootElement.element(ReqHeader);

            Element bodyElement = rootElement.element(ReqBody);
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();

            Element operationElement = bodyElement.addElement("Req" + serviceId + operationId);
            //填充reqsyshead
            Element reqSysHeadElement = operationElement.addElement(ReqSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            if(null != reqSysHeadChildren && reqSysHeadChildren.size() > 0){
                for(SDA reqSysHeadChild : reqSysHeadChildren){
                    allMetadataList.addAll(renderReqSysHeadSDA(reqSysHeadElement, reqSysHeadChild));
                }
            }
            Element reqAppHeadElement = operationElement.addElement(ReqAppHead);
            //填充reqapphead
            SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
            if(null != reqAppHeadChildren && reqAppHeadChildren.size() > 0){
                for(SDA reqAppHeadChild : reqAppHeadChildren){
                    allMetadataList.addAll(renderReqSysHeadSDA(reqAppHeadElement, reqAppHeadChild));
                }
            }
            Element reqLocalHeadElement = operationElement.addElement(ReqLocalHead);
            //如果提供方是非标，并有LocalHead,则加入报文头元素
            allMetadataList.addAll(interfaceLocalHeadDealReq(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, operationElement, reqLocalHeadElement));
            //填充reqappbody
            //获取报文头元数据集合
//            Set<String> allMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.REQUEST_NAME, serviceId, operationId);
            Element appBodyElemnet = operationElement.addElement(ReqAppBody);

            //如果提供方是非标，则在body中加入报文头元素
            allMetadataList.addAll(interfaceHeadDealReq(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, appBodyElemnet));

            SDA reqSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqSDA);
            if(null != reqChildren && reqChildren.size() > 0){
                for(SDA reqChild : reqChildren){
                    renderReqSDAExistMetadata(appBodyElemnet, reqChild, allMetadataList);
                }
            }
            //生成文件
            String fileName = getReqFilePath(serviceInvoke, path);
            long useTime = java.lang.System.currentTimeMillis() - time;
            log.info("填充In端请求或Out端响应：耗时"+useTime+"毫秒！");
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成request文件失败！", e);
        }
    }
    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInResponse(ServiceInvoke serviceInvoke, String path){
        try {
            long time = java.lang.System.currentTimeMillis();
            List<String> allMetadataList = new ArrayList<String>();
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(resPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //增加命名空间属性
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            rootElement.addNamespace("s", xmlns + serviceId);
            // 获取系统SystemID
            String hql = " from ServiceInvoke si where si.operationId=? and si.serviceId = ? and type = ? " +
                    "and isStandard = ?";
            //查询提供方非标接口
            List<ServiceInvoke> serviceInvokes = serviceInvokeService.find(hql, operationId, serviceId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
            String systemAb = null;
            String systemId = null;
            if(null != serviceInvokes && serviceInvokes.size()>0){
                systemId = serviceInvokes.get(0).getSystemId();
            }
            if(systemId!=null && !"".equals(systemId)){
                System system = systemService.findUniqueBy("systemId", systemId);
                if(system != null){
                    systemAb = system.getSystemAb();
                }
                if(systemAb!=null && !"".equals(systemAb)){
                    rootElement.addNamespace(systemAb, xmlnsShort + systemAb);
                }
            }
            Element header = rootElement.element(RspHeader);

            Element bodyElement = rootElement.element(RspBody);
            Element operationElement = bodyElement.addElement("s:Rsp" + serviceId + operationId);
            //填充rspsyshead
            Element rspSysHeadElement = operationElement.addElement(RspSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            if(reqSysHeadChildren != null && reqSysHeadChildren.size() > 0){
                for(SDA reqSysHeadChild : reqSysHeadChildren){
                    allMetadataList.addAll(renderRspSysHeadSDA(rspSysHeadElement, reqSysHeadChild));
                }
            }
            Element rspAppHeadElement = operationElement.addElement(RspAppHead);
            //填充rspapphead
            SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);

            if(null != reqAppHeadChildren && reqAppHeadChildren.size() > 0){
                for(SDA reqAppHeadChild : reqAppHeadChildren){
                    allMetadataList.addAll(renderRspSysAppSDA(rspAppHeadElement, reqAppHeadChild));
                }
            }
            Element rspLocalHeadElement = operationElement.addElement(RspLocalHead);
            //如果提供方是非标，并有LocalHead,则加入报文头元素
            List<String> localHeadMetadata = interfaceLocalHeadDealRsp(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, operationElement, rspLocalHeadElement, systemAb);
            if(null == localHeadMetadata){//表示没有localHead
                operationElement.remove(rspLocalHeadElement);//移除元素
            }else{
                allMetadataList.addAll(localHeadMetadata);//加入localHead元数据
            }
            //填充appbody
            //获取报文头元数据集合
//            Set<String> allMetadatas = getAllHeadMetadatas(Constants.ElementAttributes.RESPONSE_NAME, serviceId, operationId);
            Element appBodyElemnet = operationElement.addElement(RspAppBody);
            //如果提供方是非标，则在body中加入报文头元素
            allMetadataList.addAll(interfaceHeadDealRsp(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, appBodyElemnet));
            SDA reqSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqSDA);
            if(null != reqChildren && reqChildren.size() > 0){
                for(SDA reqChild : reqChildren){
                    renderRspSysAppSDAExistMetadataV1(appBodyElemnet, reqChild, allMetadataList);
                }
            }
            //生成文件
            String fileName = getResFilePath(serviceInvoke, path);
            long useTime = java.lang.System.currentTimeMillis() - time;
            log.info("生成标准WebService响应，耗时："+useTime+"毫秒");
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成response文件失败！", e);
        }
    }

    @Override
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
        generateInRequest(serviceInvoke, path);
    }

    @Override
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
        generateInResponse(serviceInvoke, path);
    }

    public List<String> renderReqSDA(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        if(null != sda){
            if(!Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())){
                Element element = parentElement.addElement(sda.getStructName());
                element.addAttribute("metadataid", sda.getMetadataId());
                list.add(sda.getMetadataId());
                if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("type", "array");
                    if("array".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "false");
                        element.addAttribute("metadataid", sda.getMetadataId()+"_array");
                        list.add(sda.getMetadataId());
                        List<SDA> children = sdaService.getChildren(sda);
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDA(element, chid);
                            }
                        }
                    }
                    if("struct".equalsIgnoreCase(sda.getType())){
                        element.addAttribute("is_struct", "true");
                        List<SDA> children = sdaService.getChildren(sda);
                        if(null != children && 0 < children.size()){
                            for(SDA chid : children){
                                renderReqSDA(element, chid);
                            }
                        }
                    }
                }
            }

        }
        return list;
    }


    public void renderReqSDAExistMetadata(Element parentElement, SDA sda, List<String> allMetadatas){
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


    public List<String> renderRspSysHeadSDA(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement("d:" + sda.getStructName());
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
            for(SDA child : children){
                renderRspSysHeadSDA(element, child);
            }
        }
        return list;
    }


    public List<String> renderRspLocalHeadSDA(Element parentElement, SDA sda, String systemAb){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement(systemAb +":" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        list.add(sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            list.add(sda.getMetadataId());
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    renderRspLocalHeadSDA(element, child, systemAb);
                }
            }
        }
        return list;
    }

    public List<String> renderRspSysAppSDA(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement("s:" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        list.add(sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            list.add(sda.getMetadataId());
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    renderRspSysAppSDA(element, child);
                }
            }
        }

        return list;
    }

    public void renderRspSysAppSDAExistMetadataV1(Element parentElement, SDA sda, List<String> allMetadatas){
        Element element = parentElement.addElement("s:" + sda.getStructName());
        if(allMetadatas.contains(sda.getMetadataId())){
            sda.setMetadataId(sda.getMetadataId()+"_C");
            allMetadatas.add(sda.getMetadataId()+"_C");
        }
        element.addAttribute("metadataid", sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    renderRspSysAppSDAExistMetadataV1(element, child, allMetadatas);
                }
            }
        }
        if(null != sda && (sda.getType().trim().toLowerCase().indexOf("double")==0)){

            element.addAttribute("required", "false");
        }
    }


    public void renderServiceSysHeadSDA(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        list.add(sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            list.add(sda.getMetadataId());
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                Element sdoElemtn = element.addElement("sdo");
                for(SDA child : children){
                    renderServiceSysHeadSDA(sdoElemtn, child);
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
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                Element sdoElemtn = element.addElement("sdo");
                for(SDA child : children){
                    renderServiceSysHeadSDA(sdoElemtn, child);
                }
            }
        }

        return list;
    }


    public List<String> renderReqSysHeadSDA(Element parentElement, SDA sda){
        List<String> list = new ArrayList<String>();
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        list.add(sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("metadataid", sda.getMetadataId()+"_array");
            list.add(sda.getMetadataId());
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    renderReqSysHeadSDA(element, child);
                }
            }
        }

        return list;
    }

    public List<String> interfaceLocalHeadDealReq(String serviceId, String operationId, String structName, Element parentElement, Element localHeadElement ){
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
                                    if (sda.getConstraint().trim().equalsIgnoreCase("local_head")){
                                        list.addAll(renderReqSDA(localHeadElement, sda));
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
        return list;
    }


    public List<String> interfaceHeadDealReq(String serviceId, String operationId, String structName, Element appBodyElement ){
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
                                        list.addAll(renderReqSDA(appBodyElement, sda));
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

    public List<String> interfaceLocalHeadDealRsp(String serviceId, String operationId, String structName, Element parentElement, Element localHeadElement, String systemAb){
        List<String> list = new ArrayList<String>();
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
//                                if(null != sda && !Constants.ServiceHead.DEFAULT_SYSHEAD_ID.equalsIgnoreCase(sda.getConstraint()) && !Constants.ServiceHead.DEFAULT_APPHEAD_ID.equalsIgnoreCase(sda.getConstraint())) {
//                                    if(sda.getConstraint().trim().equalsIgnoreCase("local_head")){
//                                        list.addAll(renderRspLocalHeadSDA(localHeadElement, sda, systemAb));
//                                    }else{
//                                        parentElement.remove(localHeadElement);
//                                        parentElement.getParent().getParent().remove(Namespace.get(systemAb, xmlnsShort + systemAb));
//                                    }
//                                }else{
//                                    parentElement.remove(localHeadElement);
//                                    parentElement.getParent().getParent().remove(Namespace.get(systemAb, xmlnsShort + systemAb));
//                                }
                                if(sda.getConstraint().trim().equalsIgnoreCase("local_head")){
                                    list.addAll(renderRspLocalHeadSDA(localHeadElement, sda, systemAb));
                                }
                            }
                        }

                    }
                }
            }

        }
        return list;
    }

    public List<String> interfaceHeadDealRsp(String serviceId, String operationId, String structName, Element bodyElement ){
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
                                        list.addAll(renderRspSysAppSDA(bodyElement, sda));
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
        if(null != reqAppHeadChildren && reqAppHeadChildren.size() > 0){
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
        if(null != type && ("array".equalsIgnoreCase(type) || "struct".equalsIgnoreCase(type))){
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children &&  0 < children.size()){
                for(SDA child : children){
                    inputMetadata(metadataList, child);
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
