package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class StandardWebServiceConfigExportGenerator extends ConfigExportGenerator{
    private Log log = LogFactory.getLog(StandardWebServiceConfigExportGenerator.class);
    //模板文件
    private  String reqPath = "template/config_export/soap/channel_service_standard_soap.xml";
    private  String resPath = "template/config_export/soap/service_system_standard_soap.xml";

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
    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateRequest(ServiceInvoke serviceInvoke, String path){
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(reqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //填充reqsyshead
            Element header = rootElement.element(ReqHeader);
            Element reqSysHead = header.addElement(ReqSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                renderReqSDA(reqSysHead, reqSysHeadChild);
            }
            //填充body
            Element bodyElement = rootElement.element(ReqBody);
            String serviceId = serviceInvoke.getServiceId();
            String operationId = serviceInvoke.getOperationId();
            Element operationElement = bodyElement.addElement("Reqop" + operationId);
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
    public void  generateResponse(ServiceInvoke serviceInvoke, String path){
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
            rootElement.addAttribute("xmlns:s", serviceId + operationId);
            //填充rspsyshead
            Element header = rootElement.element(RspHeader);
            Element reqSysHead = header.addElement(RspSysHead);
            SDA reqSysHeadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> reqSysHeadChildren = sdaService.getChildren(reqSysHeadSDA);
            for(SDA reqSysHeadChild : reqSysHeadChildren){
                renderRspSysHeadSDA(reqSysHead, reqSysHeadChild);
            }
            //填充body
            Element bodyElement = rootElement.element(RspBody);
            Element operationElement = bodyElement.addElement("Rspop" + operationId);
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
    public void renderReqSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement(sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderReqSDA(element, child);
            }
        }
    }

    public void renderRspSysHeadSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement("d:" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderReqSDA(element, child);
            }
        }
    }
    public void renderRspSysAppSDA(Element parentElement, SDA sda){
        Element element = parentElement.addElement("s:" + sda.getStructName());
        element.addAttribute("metadataid", sda.getMetadataId());
        List<SDA> children = sdaService.getChildren(sda);
        if(null != children &&  0 < children.size()){
            for(SDA child : children){
                renderReqSDA(element, child);
            }
        }
    }

}
