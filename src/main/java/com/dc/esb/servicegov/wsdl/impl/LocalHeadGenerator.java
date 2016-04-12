package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-24
 * Time: 上午10:04
 */
@Component
public class LocalHeadGenerator {
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private MetadataServiceImpl metadataService;
    @Autowired
    private InterfaceHeadServiceImpl interfaceHeadService;
    @Autowired
    private SystemServiceImpl systemService;
    @Autowired
    private IdaServiceImpl idaService;

    private static final Log log = LogFactory.getLog(LocalHeadGenerator.class);

    private static File cacheFile;//避免多次重复生成

    private static Namespace NS_XML = new Namespace("x", "http://www.w3.org/2001/XMLSchema");

    private List<String> uniqueList = new ArrayList<String>();

    public File generate(String systemId, String dirPath) {
        //查询系统信息
        com.dc.esb.servicegov.entity.System system = systemService.getById(systemId);
        String systemAb = system.getSystemAb();
        //在一次导出操作中，同名localhead文件只生成一次
        if(null != cacheFile && cacheFile.exists()){
            String fileName = systemAb + ".xsd";
            if(cacheFile.getName().equalsIgnoreCase(fileName)){
                File destFile = new File( dirPath + File.separator  + systemAb + ".xsd");
                try {
                    FileUtil.copyFile(cacheFile, destFile);
                    return destFile;
                }catch (IOException e){
                    log.error("复制localHead缓存文件失败", e);
                }
            }
        }
        uniqueList.clear();


        Document document = DocumentHelper.createDocument();
        Element schemaRoot = document.addElement(new QName("schema", NS_XML));
        schemaRoot.addNamespace("tns", "http://esb.dcitsbiz.com/" + systemAb);
        schemaRoot.addAttribute("targetNamespace", "http://esb.dcitsbiz.com/" + systemAb);
        schemaRoot.addAttribute("elementFormDefault", "qualified");
        schemaRoot.addAttribute("attributeFormDefault", "qualified");
        //根据系统id查询所有报文头
        List<InterfaceHead> heads = interfaceHeadService.findBy("systemId", systemId);
        for(InterfaceHead interfaceHead : heads){

            //req
            Element reqComplexType = schemaRoot.addElement(new QName("complexType", NS_XML));
            reqComplexType.addAttribute("name", "ReqLocalHeadType");
            Element reqSequence = reqComplexType.addElement(new QName("sequence", NS_XML));

            String headId = interfaceHead.getHeadId();
            Map<String, String> params = new HashMap<String,String>();
            params.put("headId", headId);
            params.put("structName", Constants.ElementAttributes.REQUEST_NAME);
            Ida ida = idaService.findUniqueBy(params);
            if(null != ida){
                String hql3 = " from Ida where _parentId = ? and structName is not null";
                List<Ida> children = idaService.find(hql3, ida.getId());
                for(Ida child : children){
                    params.clear();
                    params.put("headId", headId);
                    params.put("xpath", child.getXpath());
                    if(StringUtils.isNotEmpty(child.getSdaId())){
                        params.put("id", child.getSdaId());
                    }
                    SDA sda = sdaService.findUniqueBy(params);
                    //renderSDA(sda, reqSequence, schemaRoot);
                    renderOldSDA(sda, reqSequence);
                }
            }
            //rsp
            Element rspComplexType = schemaRoot.addElement(new QName("complexType", NS_XML));
            rspComplexType.addAttribute("name", "RspLocalHeadType");
            Element rspSequence = rspComplexType.addElement(new QName("sequence", NS_XML));

            params.clear();
            params.put("headId", headId);
            params.put("structName", Constants.ElementAttributes.RESPONSE_NAME);
            Ida rspIda = idaService.findUniqueBy(params);
            if(null != rspIda){
                String hql3 = " from Ida where _parentId = ? and structName is not null";
                List<Ida> children = idaService.find(hql3, rspIda.getId());
                for(Ida child : children){
                    params.clear();
                    params.put("headId", headId);
                    params.put("xpath", child.getXpath());
                    if(StringUtils.isNotEmpty(child.getSdaId())){
                        params.put("id", child.getSdaId());
                    }
                    SDA sda = sdaService.findUniqueBy(params);
                    //renderSDA(sda, rspSequence, schemaRoot);
                    renderOldSDA(sda, rspSequence);
                }
            }
        }

        BufferedOutputStream schemaOut = null;
        try {
            // 美化格式
            File schemaFile = new File(dirPath + File.separator  + systemAb + ".xsd");
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
            cacheFile = null;
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

    public void renderSDA(SDA sda, Element parentElem, Element rootElement){
        if(null != sda) {
            if (!"SYS_HEAD".equalsIgnoreCase(sda.getConstraint()) && !"APP_HEAD".equalsIgnoreCase(sda.getConstraint())) {
                Element element = parentElem.addElement(new QName("element", NS_XML));
                element.addAttribute("name", sda.getStructName());
                if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()))){
                    element.addAttribute("maxOccurs", "unbounded");
                    element.addAttribute("type", "tns:"+sda.getStructName());
                    element.addAttribute("nillable", "false");

                    List<SDA> children = sdaService.getChildren(sda);
                    if (null != children && 0 < children.size()) {
                        if(!uniqueList.contains(sda.getStructName())) {
                            Element appBodyComplexType = rootElement.addElement(new QName("complexType", NS_XML));
                            appBodyComplexType.addAttribute("name", sda.getStructName());
                            uniqueList.add(sda.getStructName());
                            Element sequence = appBodyComplexType.addElement(new QName("sequence", NS_XML));
                            for (SDA child : children) {
                                renderSDA(child, sequence, rootElement);
                            }
                        }

                    }
                }
                 else {
                    Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                    if (null != metadata) {
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
                    if ("Y".equalsIgnoreCase(sda.getRequired())) {
                        element.addAttribute("minOccurs", "1");
                    } else {
                        element.addAttribute("minOccurs", "0");
                    }
                }
            }
        }
    }

    /**
     * 数组元素放在数组头里面
     * @param sda
     * @param parentElem
     */
    public void renderOldSDA(SDA sda,  Element parentElem){
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
                        renderOldSDA(child, sequence);
                    }
                }
            }
            else{
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(null != metadata){
                    if ("number".equalsIgnoreCase(metadata.getType()) || "decimal".equalsIgnoreCase(metadata.getType())) {
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
        LocalHeadGenerator.cacheFile = cacheFile;
    }
}
