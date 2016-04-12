package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.IdaServiceImpl;
import com.dc.esb.servicegov.service.impl.InterfaceHeadRelateServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.wsdl.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class FmsWebServiceConfigExportGenerator extends ConfigExportGenerator{
    @Autowired
    private IdaServiceImpl idaService;
    @Autowired
    private InterfaceHeadRelateServiceImpl interfaceHeadRelateService;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    private Log log = LogFactory.getLog(FmsWebServiceConfigExportGenerator.class);
    //模板文件
    private  String reqPath = "template/config_export/soap/channel_service_standard_soap.xml";
    private  String resPath = "template/config_export/soap/service_system_standard_soap.xml";
    private  String fmsOutReqPath = "template/out_config/fms_service_system.xml";

    private String xmlns = "http://esb.dcitsbiz.com/services/";

    private String ReqHeader = "Header";
    private String ReqSysHead = "ReqSysHead";
    private String ReqBody = "Body";
    private String ReqAppHead = "ReqAppHead";
    private String ReqAppBody = "ReqAppBody";

    private String RspHeader = "Header";
    private String RspSysHead = "RspSysHead";
    private String RspBody = "Body";
    private String RspAppHead = "RspAppHead";
    private String RspAppBody = "RspAppBody";

    private List<String> uniqueList = new ArrayList<String>();
    /**
     * 生成服务定义文件
     * @param serviceInvoke
     * @param path
     * @return
     */
//    @Override
//    public void genrateServiceFile(ServiceInvoke serviceInvoke, String path){
//        try {
//            uniqueList.clear();
//            String serviceId = serviceInvoke.getServiceId();
//            String operationId = serviceInvoke.getOperationId();
//            String fileName = path + File.separator + "service_" + serviceId + operationId + ".xml";
//
//            Document doc = DocumentHelper.createDocument();
//            Element rootElement = doc.addElement("S" + serviceId + operationId);//根节点
//            Element requestElement = rootElement.addElement("request");
//            Element reqSdoRoottElement = requestElement.addElement("sdoroot");
//            //填充syshead
//            SDA reqServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
//            List<SDA> reqSysHeadSdas = sdaService.getChildren(reqServiceHeadSDA);
//            for(SDA sda : reqSysHeadSdas){
//                renderServiceSDA(reqSdoRoottElement, sda);
//            }
//            //填充apphead
//            SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
//            List<SDA> reqAppHeadSdas = sdaService.getChildren(reqAppHeadSDA);
//            for(SDA sda : reqAppHeadSdas){
//                renderServiceSDA(reqSdoRoottElement, sda);
//            }
//            //如果提供方是非标，则在body中加入报文头元素
//            interfaceHeadDeal(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME, reqSdoRoottElement);
//            //填充body
//            SDA reqSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.REQUEST_NAME);
//            List<SDA> reqSdas = sdaService.getChildren(reqSDA);
//            for(SDA sda : reqSdas){
//                renderServiceSDA(reqSdoRoottElement, sda);
//            }
//            uniqueList.clear();
//            Element responseElement = rootElement.addElement("response");
//            Element resSdoRoottElement = responseElement.addElement("sdoroot");
//            //填充syshead
//            SDA resServiceHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
//            List<SDA> resSysHeadSdas = sdaService.getChildren(resServiceHeadSDA);
//            for(SDA sda : reqSysHeadSdas){
//                renderServiceSDA(resSdoRoottElement, sda);
//            }
//            //填充apphead
//            SDA resAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
//            List<SDA> reAppHeadSdas = sdaService.getChildren(resAppHeadSDA);
//            for(SDA sda : reAppHeadSdas){
//                renderServiceSDA(resSdoRoottElement, sda);
//            }
//            //如果提供方是非标，则在body中加入报文头元素
//            interfaceHeadDeal(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME, resSdoRoottElement);
//            //填充body
//            SDA resSDA = sdaService.getByStructName(serviceId, operationId,  Constants.ElementAttributes.RESPONSE_NAME);
//            List<SDA> resSdas = sdaService.getChildren(resSDA);
//            for(SDA sda : resSdas){
//                renderServiceSDA(resSdoRoottElement, sda);
//            }
//
//            createFile(doc, fileName);
//        }catch (Exception e){
//            log.error("生成服务定义文件失败！", e);
//        }
//    }
//    public void renderServiceSDA(Element parent, SDA sda){
//        if(null != sda){
//            if(uniqueList.contains(sda.getStructName())){
//                return;
//            }
//            uniqueList.add(sda.getStructName());
//            Element element = parent.addElement(sda.getStructName());
//            element.addAttribute("metadataid", sda.getMetadataId());
//            if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
//                element.addAttribute("type", "array");
//                if("array".equalsIgnoreCase(sda.getType())){
//                    element.addAttribute("is_struct", "false");
//                }
//                if("struct".equalsIgnoreCase(sda.getType())){
//                    element.addAttribute("is_struct", "true");
//                }
//                List<SDA> children = sdaService.getChildren(sda);
//                if(null != children && 0 < children.size()){
//                    Element sdoElemtn = element.addElement("sdo");
//                    for(SDA chid : children){
//                        renderServiceSDA(sdoElemtn, chid);
//                    }
//                }
//            }
//        }
//
//    }
//    public void interfaceHeadDeal(String serviceId, String operationId, String structName, Element bodyElement ){
//        //查询非标提供者
//        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.serviceId = ? and si.operationId=? and type = ? " +
//                "and isStandard = ?";
//        List interfaces = serviceInvokeService.find(hql, serviceId, operationId, Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
//        if(null != interfaces && 0 < interfaces.size()){
//            if( 1 < interfaces.size()){
//                //如果有多个非标借口提供者
//                log.warn("场景中存在多个非标提供者！");
//                for(Object inter : interfaces){
//                    String interfaceId = inter.toString();
//                    List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
//                    if(null != relates && 0 < relates.size()){
//                        for(InterfaceHeadRelate interfaceHeadRelate : relates){
//                            String headId = interfaceHeadRelate.getHeadId();
//                            Map<String, String> params = new HashMap<String,String>();
//                            params.put("headId", headId);
//                            params.put("structName", structName);
////                    String hql2 = " from Ida where headId = ? and structName = ?";
//                            Ida ida = idaService.findUniqueBy(params);
//                            if(null != ida){
//                                String hql3 = " from Ida where _parentId = ? and structName is not null";
//                                List<Ida> children = idaService.find(hql3, ida.getId());
//                                for(Ida child : children){
//                                    params.clear();
//                                    params.put("headId", headId);
//                                    params.put("xpath", child.getXpath());
//                                    SDA sda = sdaService.findUniqueBy(params);
//                                    renderServiceSDA(bodyElement,sda);
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//
//        }
//    }
    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(reqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();

            Element header = rootElement.element(ReqHeader);

            //填充body
            Element bodyElement = rootElement.element(ReqBody);
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Element operationElement = bodyElement.addElement("Reqop" + serviceId + operationId);
            //填充reqsyshead
            Element reqSysHead = operationElement.addElement(ReqSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                renderReqSDA(reqSysHead, reqSysHeadChild);
            }
            Element appHeadElement = operationElement.addElement(ReqAppHead);
            //填充apphead
            SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
            for(SDA reqAppHeadChild : reqAppHeadChildren){
                renderReqSDA(appHeadElement, reqAppHeadChild);
            }
            //填充appbody
            Element appBodyElemnet = operationElement.addElement(ReqAppBody);
            SDA reqSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqSDA);
            for(SDA reqChild : reqChildren){
                renderReqSDA(appBodyElemnet, reqChild);
            }
            //生成文件
            String fileName = getReqFilePath(serviceInvoke, path);
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
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(resPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //增加命名空间属性
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            rootElement.addNamespace("s", xmlns + serviceId + operationId);
            //填充rspsyshead
            Element header = rootElement.element(RspHeader);

            //填充body
            Element bodyElement = rootElement.element(RspBody);
            Element operationElement = bodyElement.addElement("Rsp" + serviceId + operationId);
            //填充rspsyshead
            Element reqSysHead = operationElement.addElement(RspSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                renderRspSysHeadSDA(reqSysHead, reqSysHeadChild);
            }

            Element appHeadElement = operationElement.addElement(RspAppHead);
            //填充apphead
            SDA reqAppHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqAppHeadChildren = sdaService.getChildren(reqAppHeadSDA);
            for(SDA reqAppHeadChild : reqAppHeadChildren){
                renderRspSysAppSDA(appHeadElement, reqAppHeadChild);
            }
            //填充appbody
            Element appBodyElemnet = operationElement.addElement(RspAppBody);
            SDA reqSDA = sdaService.getByStructName(serviceId, operationId, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqSDA);
            for(SDA reqChild : reqChildren){
                renderRspSysAppSDA(appBodyElemnet, reqChild);
            }

            //生成文件
            String fileName = getResFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成response文件失败！", e);
        }
    }
    @Override
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
        try {
            String str = "";
            if(null != serviceInvoke.getInter()){
                str = serviceInvoke.getInter().getEcode();
            }
            String ecode = "T" + str;
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(fmsOutReqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //增加命名空间属性
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            rootElement.addNamespace("tns", "http://www.njcb.com.cn/hsfss/" + ecode+"/");

            //填充body
            //填充body
            Element bodyElement = rootElement.element(ReqBody);
            Element t10 = bodyElement.addElement("tns:" + ecode + "Request");
            //HEAD加入
            List<InterfaceHeadRelate> headRelates =interfaceHeadRelateService.findBy("interfaceId", serviceInvoke.getInterfaceId());
            for(InterfaceHeadRelate interfaceHeadRelate : headRelates){
                String headId = interfaceHeadRelate.getHeadId();
                Ida headIda = idaService.getByHeadIdeIdStructName(headId, Constants.ElementAttributes.REQUEST_NAME);
                String hql = " from Ida where _parentId = ? and structName is not null order by seq asc";
                List<Ida> children = idaService.find(hql, headIda.getId());
                for(Ida ida : children){
                    renderIdaSDA(t10, ida, headId);
                }
            }
            String interfaceId = serviceInvoke.getInterfaceId();
            Ida reqIda = idaService.getByInterfaceIdStructName(interfaceId,  Constants.ElementAttributes.REQUEST_NAME);
            String hql = " from Ida where _parentId = ? and structName is not null order by seq asc";
            List<Ida> children = idaService.find(hql, reqIda.getId());
            for(Ida ida : children){
                renderIdaSDA(serviceId, operationId, t10, ida);
            }

            //生成文件
            String fileName = getResFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成out request文件失败！", e);
        }
    }

    @Override
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
        try {
            String str = "";
            if(null != serviceInvoke.getInter()){
                str = serviceInvoke.getInter().getEcode();
            }
            String ecode = "T" + str;
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(reqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //增加命名空间属性
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            //填充body
            Element bodyElement = rootElement.element(ReqBody);
            Element t10 = bodyElement.addElement(ecode + "Response");
            //HEAD加入
            List<InterfaceHeadRelate> headRelates =interfaceHeadRelateService.findBy("interfaceId", serviceInvoke.getInterfaceId());
            for(InterfaceHeadRelate interfaceHeadRelate : headRelates){
                String headId = interfaceHeadRelate.getHeadId();
                Ida headIda = idaService.getByHeadIdeIdStructName(headId, Constants.ElementAttributes.RESPONSE_NAME);
                String hql = " from Ida where _parentId = ? order by seq asc";
                List<Ida> children = idaService.find(hql, headIda.getId());
                for(Ida ida : children){
                    renderIdaSDA(t10, ida, headId);
                }
            }
            String interfaceId = serviceInvoke.getInterfaceId();
            Ida reqIda = idaService.getByInterfaceIdStructName(interfaceId,  Constants.ElementAttributes.RESPONSE_NAME);
            String hql = " from Ida where _parentId = ? order by seq asc";
            List<Ida> children = idaService.find(hql, reqIda.getId());
            for(Ida ida : children){
                renderIdaSDA(serviceId, operationId, t10, ida);
            }
            //生成文件
            String fileName = getReqFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成response文件失败！", e);
        }
    }

    public void renderIdaSDA(Element parentElement, Ida ida, String headId){
        String leftName = ida.getStructName();
        Map<String, String> params = new HashMap<String, String>();
        params.put("headId", headId);
        params.put("xpath", ida.getXpath());
        SDA sda = sdaService.findUniqueBy(params);
        if(null == leftName || "".equalsIgnoreCase(ida.getStructName().trim())){
            if(null != sda){
                if(null != sda && "array".equalsIgnoreCase(sda.getType())){
                    //TODO ?????
                    leftName = "Acc_List";
                }else{
                    return;
                }
            }
           else{
                return;
            }
        }
        Element element = parentElement.addElement(leftName);
        if(null != sda){
            element.addAttribute("metadataid", sda.getMetadataId());
            if( "array".equalsIgnoreCase(sda.getType())){
                element.addAttribute("type", "array");
                element.addAttribute("is_struct", "false");
            }
        }
        String hql = " from Ida where _parentId = ? order by seq asc";
        List<Ida> children = idaService.find(hql, ida.getId());
        if(null != children &&  0 < children.size()){
            for(Ida child : children){
                renderIdaSDA(element, child, headId);
            }
        }
    }
    public void renderIdaSDA( String serviceId, String operationId, Element parentElement, Ida ida){
        String leftName = ida.getStructName();
        Map<String, String> params = new HashMap<String, String>();
        params.put("serviceId", serviceId);
        params.put("operationId", operationId);
        params.put("xpath", ida.getXpath());
        SDA sda = sdaService.findUniqueBy(params);
        if(null == leftName || "".equalsIgnoreCase(ida.getStructName().trim())){
            if(null != sda){
                if("array".equalsIgnoreCase(sda.getType())){
                    //TODO ?????
                    leftName = "Acc_List";
                }else{
                    leftName = sda.getStructName();
                }
            }
        }
        Element element = parentElement.addElement(leftName);
        if(null != sda){
            element.addAttribute("metadataid", sda.getMetadataId());
            if("array".equalsIgnoreCase(sda.getType())){
                element.addAttribute("type", "array");
                element.addAttribute("is_struct", "false");
            }
        }

        List<Ida> children = idaService.findBy("_parentId", ida.getId());
        if(null != children &&  0 < children.size()){
            for(Ida child : children){
                renderIdaSDA(serviceId, operationId, element, child);
            }
        }
    }

    public void renderReqSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
        }
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderReqSDA(element, child);
            }
        }
    }
    public void renderReqIdaSDA(Element parentElement, SDA sda, ServiceInvoke serviceInvoke){
        String leftName = sda.getStructName();
        String hql = " from Ida where interfaceId = ? and xpath = ?";
        List<Ida> list = idaService.find(hql, serviceInvoke.getInterfaceId(), sda.getXpath());
        if(null != list &&  0 < list.size()){
            leftName = list.get(0).getStructName();
        }
        if(null == leftName || "".equalsIgnoreCase(leftName)){
            leftName = sda.getStructName();
        }
        Element element = parentElement.addElement(leftName);
        element.addAttribute("metadataid", sda.getMetadataId());
        if(null != sda && "array".equalsIgnoreCase(sda.getType())){
            element.addAttribute("type", "array");
            element.addAttribute("is_struct", "false");
        }
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderReqIdaSDA(element, child, serviceInvoke);
            }
        }
    }

    public void renderRspSysHeadSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement("d:" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderRspSysHeadSDA(element, child);
            }
        }
    }
    public void renderRspSysAppSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement("s:" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderRspSysAppSDA(element, child);
            }
        }
    }

}
