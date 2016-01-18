package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-24
 * Time: 上午10:04
 */
@Component
public class MetadataSchemaGenerator {
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private MetadataServiceImpl metadataService;

    private static final Log log = LogFactory.getLog(MetadataSchemaGenerator.class);

    private static Namespace NS_XML = new Namespace("x", "http://www.w3.org/2001/XMLSchema");

    public File generate(String dirPath, String serviceId) {
        Document document = DocumentHelper.createDocument();
        Element schemaRoot = document.addElement(new QName("schema", NS_XML));
        schemaRoot.addNamespace("tns", "http://esb.spdbbiz.com/metadata");
        schemaRoot.addNamespace("d", "http://esb.spdbbiz.com/metadata");
        schemaRoot.addAttribute("targetNamespace", "http://esb.spdbbiz.com/metadata");
        schemaRoot.addAttribute("elementFormDefault", "qualified");
        schemaRoot.addAttribute("attributeFormDefault", "qualified");
        Element reqComplexType = schemaRoot.addElement(new QName("complexType", NS_XML));
        reqComplexType.addAttribute("name", "ReqSysHeadType");
        Element reqSequence = reqComplexType.addElement(new QName("sequence", NS_XML));
        //SYSHEAD request
        SDA reqSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
        List<SDA> reqChildren = sdaService.getChildren(reqSDA);
        for(SDA reqChild : reqChildren){
            renderSDA(reqChild, reqSequence);
        }
//        Element reqMacElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqMacElement.addAttribute("name", "Mac");
//        reqMacElement.addAttribute("type", "x:string");
//        Element reqMacOrgIdElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqMacOrgIdElement.addAttribute("name", "MacOrgId");
//        reqMacOrgIdElement.addAttribute("type", "x:string");
//        Element reqMsgIdElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqMsgIdElement.addAttribute("name", "MsgId");
//        reqMsgIdElement.addAttribute("type", "x:string");
//        Element reqSourceSysIdElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqSourceSysIdElement.addAttribute("name", "SourceSysId");
//        reqSourceSysIdElement.addAttribute("type", "x:string");
//        Element reqConsumerIdElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqConsumerIdElement.addAttribute("name", "ConsumerId");
//        reqConsumerIdElement.addAttribute("type", "x:string");
//        Element reqServiceAdrElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqServiceAdrElement.addAttribute("name", "ServiceAdr");
//        reqServiceAdrElement.addAttribute("type", "x:string");
//        Element reqServiceActionElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqServiceActionElement.addAttribute("name", "ServiceAction");
//        reqServiceActionElement.addAttribute("type", "x:string");
//        Element reqReplyAdrElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqReplyAdrElement.addAttribute("name", "ReplyAdr");
//        reqReplyAdrElement.addAttribute("type", "x:string");
//        reqReplyAdrElement.addAttribute("minOccurs", "0");
//        Element reqExtendContentElement = reqSequence.addElement(new QName("element", NS_XML));
//        reqExtendContentElement.addAttribute("name", "ExtendContent");
//        reqExtendContentElement.addAttribute("type", "x:string");
//        reqExtendContentElement.addAttribute("minOccurs", "0");

        Element rspComplexType = schemaRoot.addElement(new QName("complexType", NS_XML));
        rspComplexType.addAttribute("name", "RspSysHeadType");
        Element rspSequence = rspComplexType.addElement(new QName("sequence", NS_XML));
        SDA resSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
        List<SDA> resChildren = sdaService.getChildren(resSDA);
        for(SDA resChild : resChildren){
            renderSDA(resChild, rspSequence);
        }
//
//        Element macElement = rspSequence.addElement(new QName("element", NS_XML));
//        macElement.addAttribute("name", "Mac");
//        macElement.addAttribute("type", "x:string");
//        Element macOrgIdElement = rspSequence.addElement(new QName("element", NS_XML));
//        macOrgIdElement.addAttribute("name", "MacOrgId");
//        macOrgIdElement.addAttribute("type", "x:string");
//        Element msgIdElement = rspSequence.addElement(new QName("element", NS_XML));
//        msgIdElement.addAttribute("name", "MsgId");
//        msgIdElement.addAttribute("type", "x:string");
//        Element targetSysIdElement = rspSequence.addElement(new QName("element", NS_XML));
//        targetSysIdElement.addAttribute("name", "TargetSysId");
//        targetSysIdElement.addAttribute("type", "x:string");
//        Element relatedMsgIdElement = rspSequence.addElement(new QName("element", NS_XML));
//        relatedMsgIdElement.addAttribute("name", "RelatedMsgId");
//        relatedMsgIdElement.addAttribute("type", "x:string");
//        relatedMsgIdElement.addAttribute("minOccurs", "0");
//        Element serviceAdrElement = rspSequence.addElement(new QName("element", NS_XML));
//        serviceAdrElement.addAttribute("name", "ServiceAdr");
//        serviceAdrElement.addAttribute("type", "x:string");
//        serviceAdrElement.addAttribute("minOccurs", "0");
//        Element serviceActionElement = rspSequence.addElement(new QName("element", NS_XML));
//        serviceActionElement.addAttribute("name", "ServiceAction");
//        serviceActionElement.addAttribute("type", "x:string");
//        serviceActionElement.addAttribute("minOccurs", "0");
//        Element extendContentElement = rspSequence.addElement(new QName("element", NS_XML));
//        extendContentElement.addAttribute("name", "ExtendContent");
//        extendContentElement.addAttribute("type", "x:string");
//        extendContentElement.addAttribute("minOccurs", "0");

        BufferedOutputStream schemaOut = null;
        try {
            // 美化格式
            File schemaFile = new File(dirPath + File.separator  + "SoapHeader.xsd");
            if (!schemaFile.exists()) {

                schemaFile.createNewFile();
            }
            schemaOut = new BufferedOutputStream(new FileOutputStream(schemaFile));

            XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new XMLWriter(schemaOut, format);
            writer.write(document);
        } catch (IOException e) {
            log.error(e,e);
        } finally{
            if(null != schemaOut){
                try {
                    schemaOut.close();
                } catch (IOException e) {
                    log.error(e,e);
                }
            }
        }

        return null;
    }

    public void renderSDA(SDA sda,  Element parentElem){
        if(null != sda){
            Element element = parentElem.addElement(new QName("element", NS_XML));
            element.addAttribute("name", sda.getStructName());
            if("array".equalsIgnoreCase(sda.getType())){
                element.addAttribute("maxOccurs", "unbounded");
            }
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children && 0 < children.size()){
                Element complexType = element.addElement(new QName("complexType", NS_XML));
                Element sequence = complexType.addElement(new QName("sequence", NS_XML));

                for(SDA child : children){
                    renderSDA(child, sequence);
                }
            }else{
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(null != metadata){
                    if ("number".equalsIgnoreCase(metadata.getType())) {
                        element.addAttribute("type", "x:decimal");
                    } else if ("int".equalsIgnoreCase(metadata.getType())) {
                        element.addAttribute("type", "x:int");
                    } else if ("double".equalsIgnoreCase(metadata.getType())) {
                        element.addAttribute("type", "x:double");
                    } else {
                        element.addAttribute("type", "x:string");
                    }
                }
                if("Y".equalsIgnoreCase(sda.getRequired())){
                    element.addAttribute("minOccurs", "1");
                }else{
                    element.addAttribute("minOccurs", "0");
                }
            }
        }
    }
}
