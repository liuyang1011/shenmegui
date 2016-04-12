package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.util.FileUtil;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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

    private static File cacheFile;
    private static Namespace NS_XML = new Namespace("x", "http://www.w3.org/2001/XMLSchema");

    public File generate(String dirPath) {
        //在一次导出操作中，同名localhead文件只生成一次
        if(null != cacheFile && cacheFile.exists()){
            String fileName = "SoapHeader.xsd";
            if(cacheFile.getName().equalsIgnoreCase(fileName)){
                File destFile = new File(dirPath + File.separator  + fileName);
                try {
                    FileUtil.copyFile(cacheFile, destFile);
                    return destFile;
                }catch (IOException e){
                    log.error("复制localHead缓存文件失败", e);
                }
            }
        }


        Document document = DocumentHelper.createDocument();
        Element schemaRoot = document.addElement(new QName("schema", NS_XML));
        schemaRoot.addNamespace("tns", "http://esb.dcitsbiz.com/metadata");
        schemaRoot.addNamespace("d", "http://esb.dcitsbiz.com/metadata");
        schemaRoot.addAttribute("targetNamespace", "http://esb.dcitsbiz.com/metadata");
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

        Element rspComplexType = schemaRoot.addElement(new QName("complexType", NS_XML));
        rspComplexType.addAttribute("name", "RspSysHeadType");
        Element rspSequence = rspComplexType.addElement(new QName("sequence", NS_XML));
        SDA resSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_SYSHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
        List<SDA> resChildren = sdaService.getChildren(resSDA);
        for(SDA resChild : resChildren){
            renderSDA(resChild, rspSequence);
        }

        BufferedOutputStream schemaOut = null;
        try {
            // 美化格式
            File schemaFile = new File(dirPath + File.separator  +"SoapHeader.xsd");
            if (!schemaFile.exists()) {

                schemaFile.createNewFile();
            }
            schemaOut = new BufferedOutputStream(new FileOutputStream(schemaFile));

            com.dc.esb.servicegov.util.XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new com.dc.esb.servicegov.util.XMLWriter(schemaOut, format);
            writer.write(document);

            cacheFile = schemaFile;
        } catch (IOException e) {
            log.error(e,e);
            cacheFile = null;
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
            if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()))){
                element.addAttribute("maxOccurs", "unbounded");

                List<SDA> children = sdaService.getChildren(sda);
                if(null != children && 0 < children.size()){
                    Element complexType = element.addElement(new QName("complexType", NS_XML));
                    Element sequence = complexType.addElement(new QName("sequence", NS_XML));

                    for(SDA child : children){
                        renderSDA(child, sequence);
                    }
                }
            }
            else{
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

    public static File getCacheFile() {
        return cacheFile;
    }

    public static void setCacheFile(File cacheFile) {
        MetadataSchemaGenerator.cacheFile = cacheFile;
    }
}
