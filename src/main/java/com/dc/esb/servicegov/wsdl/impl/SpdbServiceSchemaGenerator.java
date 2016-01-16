package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.wsdl.WSDLGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-20
 * Time: 上午2:07
 */
//TODO 需要修改
@Component
public class SpdbServiceSchemaGenerator implements WSDLGenerator<List<Service>> {
    private Log logger = LogFactory.getFactory().getLog(SpdbServiceSchemaGenerator.class);
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private MetadataServiceImpl metadataService;

    private static final Log log = LogFactory.getLog(SpdbServiceSchemaGenerator.class);
    private static final Namespace NS_URI_XMLNS = new Namespace("x", "http://www.w3.org/2001/XMLSchema");
    private static final QName QN_ELEM = new QName("element", NS_URI_XMLNS);
    private static final QName QN_COMPLEX_TYPE = new QName("complexType", NS_URI_XMLNS);
    private static final QName QN_SEQ = new QName("sequence", NS_URI_XMLNS);

    private Map<String, String> uniqueMap = new HashMap<String, String>();

    @Override
    public boolean generate(List<Service> services) throws Exception {
        return false;
    }

    private List<Service> getAbstractService(Service service){
//        return  serviceManager.getParentService(service);
        return null;
    }

    public boolean generate(Service serviceDO, String dirPath) {

        String serviceId = serviceDO.getServiceId();
        String tmpServiceId = "S" + serviceId;
        BufferedOutputStream schemaOut = null;
        try {
//            List<Service> sHeadServices = serviceManager.getServiceById("SHEAD");
//            List<Service> sHeadServices = getAbstractService(serviceDO);
            Document document = DocumentHelper.createDocument();
            Element schemaRoot = document.addElement(new QName("schema", new Namespace("x", "http://www.w3.org/2001/XMLSchema")));
            schemaRoot.addNamespace("d", "http://esb.spdbbiz.com/metadata");
            schemaRoot.addNamespace("s", "http://esb.spdbbiz.com/services/" + tmpServiceId);
            schemaRoot.addAttribute("targetNamespace", "http://esb.spdbbiz.com/services/" + tmpServiceId);
            schemaRoot.addAttribute("elementFormDefault", "qualified");
            schemaRoot.addAttribute("attributeFormDefault", "qualified");
            Element metadataImportElem = schemaRoot.addElement(new QName("import", NS_URI_XMLNS));
            metadataImportElem.addAttribute("namespace", "http://esb.spdbbiz.com/metadata");
            metadataImportElem.addAttribute("schemaLocation", "SoapHeader.xsd");

            Element reqHeaderElem = schemaRoot.addElement(QN_ELEM);
            reqHeaderElem.addAttribute("name", "ReqSysHead");
            reqHeaderElem.addAttribute("type", "d:ReqSysHeadType");
            Element rspHeaderElem = schemaRoot.addElement(QN_ELEM);
            rspHeaderElem.addAttribute("name", "RspSysHead");
            rspHeaderElem.addAttribute("type", "d:RspSysHeadType");

            Element reqAppheadComplexType = schemaRoot.addElement(QN_COMPLEX_TYPE);
            reqAppheadComplexType.addAttribute("name", "ReqAppHeadType");
            Element reqSequence = reqAppheadComplexType.addElement(QN_SEQ);
            SDA reqAppheadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqChildren = sdaService.getChildren(reqAppheadSDA);
            for(SDA reqChild : reqChildren){
                renderAppHeadSDA(reqChild, reqSequence);
            }

            Element resAppheadComplexType = schemaRoot.addElement(QN_COMPLEX_TYPE);
            resAppheadComplexType.addAttribute("name", "RspAppHeadType");
            Element resSequence = resAppheadComplexType.addElement(QN_SEQ);
            SDA resAppheadSDA = sdaService.getByStructName(Constants.ServiceHead.DEFAULT_APPHEAD_ID, Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> resChildren = sdaService.getChildren(resAppheadSDA);
            for(SDA resChild : resChildren){
                renderAppHeadSDA(resChild, resSequence);
            }

            List<Operation> operations = operationService.getOperationByServiceId(serviceDO.getServiceId());
            for (Operation operationDO : operations) {
                log.error("开始处理场景["+operationDO.getServiceId()+"]");
                String operationId = operationDO.getOperationId();
                //Todo
                String tmpOperationId = "Op" + handleDupOperationIdIssue(operationId);
                Element reqOperation = schemaRoot.addElement(QN_ELEM);
                reqOperation.addAttribute("name", "Req" + tmpOperationId);
                String reqOperationTypeName = "s:Req" + tmpOperationId + "Type";
                reqOperation.addAttribute("type", reqOperationTypeName);
                Element reqOperationType = schemaRoot.addElement(QN_COMPLEX_TYPE);
                reqOperationType.addAttribute("name", "Req" + tmpOperationId + "Type");
                Element reqOperationTypeSeq = reqOperationType.addElement(QN_SEQ);
                Element reqSvcHeaderElem = reqOperationTypeSeq.addElement(QN_ELEM);
                reqSvcHeaderElem.addAttribute("name", "ReqAppHead");
                reqSvcHeaderElem.addAttribute("type", "s:ReqAppHeadType");
                Element reqSvcBodyElem = reqOperationTypeSeq.addElement(QN_ELEM);
                reqSvcBodyElem.addAttribute("name", "AppBody");
                reqSvcBodyElem.addAttribute("minOccurs", "0");
                Element reqSvcBodyComplexType = reqSvcBodyElem.addElement(QN_COMPLEX_TYPE);
                Element reqSvcBodyTypeSeq = reqSvcBodyComplexType.addElement(QN_SEQ);

                SDA reqSDA = sdaService.getByStructName(operationDO.getServiceId(), operationDO.getOperationId(), Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqBodyChildren = sdaService.getChildren(reqSDA);
                for(SDA reqBodyChild : reqBodyChildren){
                    renderAppBodydSDA(reqBodyChild, reqSvcBodyTypeSeq, schemaRoot);
                }
                //rsp

                Element rspOperation = schemaRoot.addElement(QN_ELEM);
                rspOperation.addAttribute("name", "Rsp" + tmpOperationId);
                String rspOperationTypeName = "s:Rsp" + tmpOperationId + "Type";
                rspOperation.addAttribute("type", rspOperationTypeName);
                Element rspOperationType = schemaRoot.addElement(QN_COMPLEX_TYPE);
                rspOperationType.addAttribute("name", "Rsp" + tmpOperationId + "Type");
                Element rspOperationTypeSeq = rspOperationType.addElement(QN_SEQ);
                Element rspSvcHeaderElem = rspOperationTypeSeq.addElement(QN_ELEM);
                rspSvcHeaderElem.addAttribute("name", "RspAppHead");
                rspSvcHeaderElem.addAttribute("type", "s:RspAppHeadType");
                Element rspSvcBodyElem = rspOperationTypeSeq.addElement(QN_ELEM);
                rspSvcBodyElem.addAttribute("minOccurs", "0");
                rspSvcBodyElem.addAttribute("name", "AppBody");
                Element rspSvcBodyComplexType = rspSvcBodyElem.addElement(QN_COMPLEX_TYPE);
                Element rspSvcBodyTypeSeq = rspSvcBodyComplexType.addElement(QN_SEQ);
                SDA resSDA = sdaService.getByStructName(operationDO.getServiceId(), operationDO.getOperationId(), Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resBodyChildren = sdaService.getChildren(resSDA);
                for(SDA resBodyChild : resBodyChildren){
                    renderAppBodydSDA(resBodyChild, rspSvcBodyTypeSeq, schemaRoot);
                }
//                List<SDA> rspChildSDAs = rspSDA.getChildNode();
//                SDA rspSvcBodySDA = null;
//                for (SDA rspChildSDA : rspChildSDAs) {
//                    if ("svcbody".equalsIgnoreCase(rspChildSDA.getStructName())) {
//                        rspSvcBodySDA = rspChildSDA;
//                        break;
//                    }
//                }
//                if (null != rspSvcBodySDA) {
//                    List<SDA> svcBodySubSDAs = rspSvcBodySDA.getChildNode();
//                    if (null != svcBodySubSDAs) {
//                        for (SDA svcBodySubSDA : svcBodySubSDAs) {
//                            renderSDA(svcBodySubSDA, rspSvcBodyTypeSeq, schemaRoot);
//                        }
//                    } else {
//                        log.warn("svc body 没有内容！");
//                    }
//                }
            }
            // 美化格式
            File schemaFile = new File(dirPath + File.separator + tmpServiceId + ".xsd");
            if (!schemaFile.exists()) {
                schemaFile.createNewFile();
            }
//            File wsdlFile = new File(serviceId + ".wsdl");
            schemaOut = new BufferedOutputStream(new FileOutputStream(schemaFile));
//            BufferedOutputStream wsdlOut = new BufferedOutputStream(new FileOutputStream(wsdlFile));

            XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new XMLWriter(schemaOut, format);
            writer.write(document);

            return true;
        } catch (DataException e) {
            log.error(e, e);
        } catch (UnsupportedEncodingException e) {
            log.error(e, e);
        } catch (IOException e) {
            log.error(e, e);
        } finally {
            uniqueMap.clear();
            if (null != schemaOut) {

                try {
                    schemaOut.close();
                } catch (IOException e) {
                    log.error(e,e);
                }
            }
        }
        return true;
    }
    public void renderAppHeadSDA(SDA sda,  Element parentElem){
        if(null != sda){
            Element element = parentElem.addElement(new QName("element", NS_URI_XMLNS));
            element.addAttribute("name", sda.getStructName());
            if("array".equalsIgnoreCase(sda.getType())){
                element.addAttribute("maxOccurs", "unbounded");
            }
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children && 0 < children.size()){
                Element complexType = element.addElement(new QName("complexType", NS_URI_XMLNS));
                Element sequence = complexType.addElement(new QName("sequence", NS_URI_XMLNS));

                for(SDA child : children){
                    renderAppHeadSDA(child, sequence);
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
    public void renderAppBodydSDA(SDA sda,  Element parentElem, Element rootElement){
        if(null != sda){
            Element element = parentElem.addElement(new QName("element", NS_URI_XMLNS));
            if("array".equalsIgnoreCase(sda.getType())){
                element.addAttribute("maxOccurs", "unbounded");
            }
            List<SDA> children = sdaService.getChildren(sda);
            if(null != children && 0 < children.size()){
                element.addAttribute("name", sda.getStructName());
                Element complexType = parentElem.addElement(new QName("complexType", NS_URI_XMLNS));
                Element sequence = complexType.addElement(new QName("sequence", NS_URI_XMLNS));

                for(SDA child : children){
                    renderAppBodydSDA(child, sequence, rootElement);
                }
            }else{
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(null != metadata){
                    if(!uniqueMap.containsKey(metadata.getMetadataId())){
                        Element cacheElement = rootElement.addElement(QN_ELEM);
                        cacheElement.addAttribute("name", sda.getStructName());
                        if ("number".equalsIgnoreCase(metadata.getType())) {
                            cacheElement.addAttribute("type", "x:decimal");
                        } else if ("int".equalsIgnoreCase(metadata.getType())) {
                            cacheElement.addAttribute("type", "x:int");
                        } else if ("double".equalsIgnoreCase(metadata.getType())) {
                            cacheElement.addAttribute("type", "x:double");
                        } else {
                            cacheElement.addAttribute("type", "x:string");
                        }
                    }
                    element.addAttribute("ref", "s:" + metadata.getMetadataId());
                }
            }
            if("Y".equalsIgnoreCase(sda.getRequired())){
                element.addAttribute("minOccurs", "1");
            }else {
                element.addAttribute("minOccurs", "0");
            }
        }
    }
    private Element parseStruct(String nodeName, Element element, Element typeParentElem) {

//        List<MetaStructNode> metaStructNodes = metaStructNodeDAO.findBy("structId", nodeName);
//        for (MetaStructNode metaStructNode : metaStructNodes) {
//            MetadataViewBean metadata = null;
////            MetadataViewBean metadata = metadataManager.getMetadataById(metaStructNode.getElementId());
//            String metadataId = metadata.getMetadataId();
//            String metadataType = metadata.getType();
//            Element nodeElement = element.addElement(QN_ELEM);
//            nodeElement.addAttribute("ref", "s:" + metadataId);
//            if("N".equalsIgnoreCase(metaStructNode.getRequired())) {
//                nodeElement.addAttribute("minOccurs", "0");
//            }
//            if (!uniqueMap.containsKey(metadataId)) {
//                Element nodeTypeElement = typeParentElem.addElement(QN_ELEM);
//                nodeTypeElement.addAttribute("name", metadataId);
//                if ("number".equalsIgnoreCase(metadataType)) {
//                    nodeTypeElement.addAttribute("type", "x:decimal");
//                } else if ("string".equalsIgnoreCase(metadataType)) {
//                    nodeTypeElement.addAttribute("type", "x:string");
//                } else {
//                    nodeTypeElement.addAttribute("type", "x:int");
//                }
//                uniqueMap.put(metadataId, null);
//            } else {
//                log.error(metadataId + "重复");
//            }
//        }


        return null;

    }

    private void renderType(SDA sda, Element parentElem, Element typeParentElem) throws DataException {
//        SDANode sdaNode = sda.getValue();
//        if (null != sdaNode) {
//            Element nodeElement = null;
//            if (null != parentElem) {
//                nodeElement = parentElem.addElement(QN_ELEM);
//            }
//            String nodeType = sdaNode.getType().trim();
//            String required = sdaNode.getRequired();
//            boolean isComplexNode = null != sda.getChildNode() && sda.getChildNode().size() > 0;
//            if ("array".equalsIgnoreCase(nodeType) || isComplexNode) {
//                if (null != parentElem) {
//                    nodeElement.addAttribute("name", sdaNode.getStructName());
//                    if ("array".equalsIgnoreCase(nodeType)) {
//                        nodeElement.addAttribute("maxOccurs", "unbounded");
//                    }
//                    nodeElement.addAttribute("type", "s:" + sdaNode.getStructName() + "Type");
//                }
//                if (!uniqueMap.containsKey(sdaNode.getStructName())) {
//                    Element arrayTypeElement = typeParentElem.addElement(QN_COMPLEX_TYPE);
//                    arrayTypeElement.addAttribute("name", sdaNode.getStructName() + "Type");
//                    Element arrayTypeSeq = arrayTypeElement.addElement(QN_SEQ);
//
//                    List<SDA> childSDAs = sda.getChildNode();
//                    if (null != childSDAs) {
//                        for (SDA childSDA : childSDAs) {
//                            renderType(childSDA, arrayTypeSeq, typeParentElem);
//                        }
//                    }
//                    uniqueMap.put(sdaNode.getStructName(), null);
//                } else {
//                    log.error(sdaNode.getStructName() + "重复");
//                }
//
//            } else {
//                String metadataId = sdaNode.getMetadataId();
//                if (null != metadataId) {
//                    metadataId = metadataId.trim();
//                    MetadataViewBean metadata = null;
////                    MetadataViewBean metadata = metadataManager.getMetadataById(metadataId);
//                    String metadataType = metadata.getType();
//                    if (null != parentElem) {
//                        nodeElement.addAttribute("ref", "s:" + metadataId);
//                    }
//
//                    if (!uniqueMap.containsKey(sdaNode.getStructName())) {
//                        Element nodeTypeElement = typeParentElem.addElement(QN_ELEM);
//                        nodeTypeElement.addAttribute("name", metadataId);
//                        if ("number".equalsIgnoreCase(metadataType)) {
//                            nodeTypeElement.addAttribute("type", "x:decimal");
//                        } else if ("string".equalsIgnoreCase(metadataType)) {
//                            nodeTypeElement.addAttribute("type", "x:string");
//                        } else {
//                            nodeTypeElement.addAttribute("type", "x:int");
//                        }
//                        uniqueMap.put(sdaNode.getStructName(), null);
//                    } else {
//                        log.error(sdaNode.getStructName() + "重复");
//                    }
//
//                } else {
//                    throw new DataException("sda非法，服务字段几点必须有元数据！");
//                }
//            }
//
//            if ("Y".equalsIgnoreCase(required)) {
//                if (null != parentElem) {
//                    nodeElement.addAttribute("minOccurs", "1");
//                }
//            } else {
//                if (null != parentElem) {
//                    nodeElement.addAttribute("minOccurs", "0");
//                }
//
//            }
//
//        }
    }

    public boolean generate(String serviceId, String dirPath) {

//        List<Service> services = serviceManager.getServiceById(serviceId);
        List<Service> services = null;
        for (Service service : services) {
            generate(service, dirPath);
        }
        return true;
    }

    public String handleDupOperationIdIssue(String operationId){
        if(operationId.indexOf("-") > -1){
            String tmpOperationId = operationId.substring(0, operationId.indexOf("-"));
            return tmpOperationId;
        }else{
            return operationId;
        }
    }
}
