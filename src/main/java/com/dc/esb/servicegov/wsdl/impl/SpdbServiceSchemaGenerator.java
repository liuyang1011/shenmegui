package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.wsdl.WSDLGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.apache.bcel.classfile.Constant;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.System;
import java.util.*;

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
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private InterfaceHeadRelateServiceImpl interfaceHeadRelateService;
    @Autowired
    private IdaServiceImpl idaService;
    @Autowired
    private InterfaceHeadServiceImpl interfaceHeadService;
    @Autowired
    private LocalHeadGenerator localHeadGenerator;
    @Autowired
    private SystemServiceImpl systemService;

//    private static String[] LOCAL_HEAD_SYSTEM = {"B78", "B90"}; //需要加入Localhead的系统
    private static String[] NO_LOCAL_HEAD_SYSTEM = {"B48"}; //需要加入Localhead的系统

    private static final Log log = LogFactory.getLog(SpdbServiceSchemaGenerator.class);
    private static final Namespace NS_URI_XMLNS = new Namespace("x", "http://www.w3.org/2001/XMLSchema");
    private static final QName QN_ELEM = new QName("element", NS_URI_XMLNS);
    private static final QName QN_COMPLEX_TYPE = new QName("complexType", NS_URI_XMLNS);
    private static final QName QN_SEQ = new QName("sequence", NS_URI_XMLNS);

    private List<String> uniqueList = new ArrayList<String>();

    @Override
    public boolean generate(List<Service> services) throws Exception {
        return false;
    }

    public boolean generate(String serviceId, List<OperationPK> operationPKs, String dirPath) {

        String tmpServiceId = serviceId ;
        BufferedOutputStream schemaOut = null;
        try {
            uniqueList.clear();
//            List<Service> sHeadServices = serviceManager.getServiceById("SHEAD");
//            List<Service> sHeadServices = getAbstractService(serviceDO);
            Document document = DocumentHelper.createDocument();
            Element schemaRoot = document.addElement(new QName("schema", new Namespace("x", "http://www.w3.org/2001/XMLSchema")));
            schemaRoot.addNamespace("d", "http://esb.dcitsbiz.com/metadata");
            schemaRoot.addNamespace("s", "http://esb.dcitsbiz.com/services/" + tmpServiceId);
            schemaRoot.addAttribute("targetNamespace", "http://esb.dcitsbiz.com/services/" + tmpServiceId);
            schemaRoot.addAttribute("elementFormDefault", "qualified");
            schemaRoot.addAttribute("attributeFormDefault", "qualified");
            Element metadataImportElem = schemaRoot.addElement(new QName("import", NS_URI_XMLNS));
            metadataImportElem.addAttribute("namespace", "http://esb.dcitsbiz.com/metadata");
            metadataImportElem.addAttribute("schemaLocation", "SoapHeader.xsd");
            //引入localhead命名空间
            Date start = new Date();
            importReqLocalHeadNamespace(operationPKs, schemaRoot, dirPath);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>导入报文头命名空间耗时：" + (new Date().getTime() - start.getTime()) + "ms<<<<<<<<<<<<<<<<<<<<<<<<<<");

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
            log.info("开始处理服务[" + tmpServiceId + "]");
//            String operationId = operationPK.getOperationId();
            //Todo
//            String tmpOperationId = "Op" + operationPK.getOperationId();
            for(OperationPK operationPK : operationPKs){
                Date start2 = new Date();
                Element reqOperation = schemaRoot.addElement(QN_ELEM);
                reqOperation.addAttribute("name", "Req" + tmpServiceId + operationPK.getOperationId());
                String reqOperationTypeName = "s:Req" + tmpServiceId + operationPK.getOperationId() + "Type";
                reqOperation.addAttribute("type", reqOperationTypeName);
                Element reqOperationType = schemaRoot.addElement(QN_COMPLEX_TYPE);
                reqOperationType.addAttribute("name", "Req" + tmpServiceId + operationPK.getOperationId() + "Type");
                Element reqOperationTypeSeq = reqOperationType.addElement(QN_SEQ);

                Element reqHeaderElem = reqOperationTypeSeq.addElement(QN_ELEM);
                reqHeaderElem.addAttribute("name", "ReqSysHead");
                reqHeaderElem.addAttribute("type", "d:ReqSysHeadType");

                Element reqSvcHeaderElem = reqOperationTypeSeq.addElement(QN_ELEM);
                reqSvcHeaderElem.addAttribute("name", "ReqAppHead");
                reqSvcHeaderElem.addAttribute("type", "s:ReqAppHeadType");

                //localHead
                addLocalHeadElem(operationPK, reqOperationTypeSeq, "Req");
//                Element reqLocalHeadElem = reqOperationTypeSeq.addElement(QN_ELEM);
//                reqLocalHeadElem.addAttribute("name", "ReqLocalHead");
//                Element reqLocalHeadComplexType = reqLocalHeadElem.addElement(QN_COMPLEX_TYPE);
//                Element reqLocalHeadTypeSeq = reqLocalHeadComplexType.addElement(QN_SEQ);
//                //如果提供方是非标，则在body中加入报文头元素
//                interfaceHeadDeal(operationPK, Constants.ElementAttributes.REQUEST_NAME, reqLocalHeadTypeSeq, schemaRoot);

                Element reqSvcBodyElem = reqOperationTypeSeq.addElement(QN_ELEM);
                reqSvcBodyElem.addAttribute("name", "ReqAppBody");
                reqSvcBodyElem.addAttribute("minOccurs", "0");
                Element reqSvcBodyComplexType = reqSvcBodyElem.addElement(QN_COMPLEX_TYPE);
                Element reqSvcBodyTypeSeq = reqSvcBodyComplexType.addElement(QN_SEQ);
                //没有在LocalHeadSystem中的系统如果含有报文头则加入body
                interfaceHeadDeal(operationPK, Constants.ElementAttributes.REQUEST_NAME, reqSvcBodyTypeSeq, schemaRoot);
                SDA reqSDA = sdaService.getByStructName(operationPK.getServiceId(), operationPK.getOperationId(), Constants.ElementAttributes.REQUEST_NAME);
                List<SDA> reqBodyChildren = sdaService.getChildren(reqSDA);
                for(SDA reqBodyChild : reqBodyChildren){
                    // renderAppBodydSDA(reqBodyChild, reqSvcBodyTypeSeq, schemaRoot);
                    renderAppHeadSDA(reqBodyChild, reqSvcBodyTypeSeq);
                }
                //rsp

                Element rspOperation = schemaRoot.addElement(QN_ELEM);
                rspOperation.addAttribute("name", "Rsp" + tmpServiceId + operationPK.getOperationId());
                String rspOperationTypeName = "s:Rsp" + tmpServiceId + operationPK.getOperationId() + "Type";
                rspOperation.addAttribute("type", rspOperationTypeName);
                Element rspOperationType = schemaRoot.addElement(QN_COMPLEX_TYPE);
                rspOperationType.addAttribute("name", "Rsp" + tmpServiceId + operationPK.getOperationId() + "Type");
                Element rspOperationTypeSeq = rspOperationType.addElement(QN_SEQ);

                Element rspHeaderElem = rspOperationTypeSeq.addElement(QN_ELEM);
                rspHeaderElem.addAttribute("name", "RspSysHead");
                rspHeaderElem.addAttribute("type", "d:RspSysHeadType");


                Element rspSvcHeaderElem = rspOperationTypeSeq.addElement(QN_ELEM);
                rspSvcHeaderElem.addAttribute("name", "RspAppHead");
                rspSvcHeaderElem.addAttribute("type", "s:RspAppHeadType");

                //localHead
                //localHead
                addLocalHeadElem(operationPK, rspOperationTypeSeq, "Rsp");
//                Element rspLocalHeadElem = rspOperationTypeSeq.addElement(QN_ELEM);
//                rspLocalHeadElem.addAttribute("name", "RspLocalHead");
//                Element rspLocalHeadComplexType = rspLocalHeadElem.addElement(QN_COMPLEX_TYPE);
//                Element rspLocalHeadTypeSeq = rspLocalHeadComplexType.addElement(QN_SEQ);
//                //如果提供方是非标，则在body中加入报文头元素
//                interfaceHeadDeal(operationPK, Constants.ElementAttributes.RESPONSE_NAME, rspLocalHeadTypeSeq, schemaRoot);

                Element rspSvcBodyElem = rspOperationTypeSeq.addElement(QN_ELEM);
                rspSvcBodyElem.addAttribute("minOccurs", "0");
                rspSvcBodyElem.addAttribute("name", "RspAppBody");
                Element rspSvcBodyComplexType = rspSvcBodyElem.addElement(QN_COMPLEX_TYPE);
                Element rspSvcBodyTypeSeq = rspSvcBodyComplexType.addElement(QN_SEQ);
                //如果提供方是非标，则在body中加入报文头元素
                interfaceHeadDeal(operationPK, Constants.ElementAttributes.RESPONSE_NAME, rspSvcBodyTypeSeq, schemaRoot);
                SDA resSDA = sdaService.getByStructName(operationPK.getServiceId(), operationPK.getOperationId(), Constants.ElementAttributes.RESPONSE_NAME);
                List<SDA> resBodyChildren = sdaService.getChildren(resSDA);
                for(SDA resBodyChild : resBodyChildren){
                    // renderAppBodydSDA(resBodyChild, rspSvcBodyTypeSeq, schemaRoot);
                    renderAppHeadSDA(resBodyChild, rspSvcBodyTypeSeq);
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>处理场景耗时：" + (new Date().getTime() - start2.getTime()) + "ms<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }

            // 美化格式
            File schemaFile = new File(dirPath + File.separator + tmpServiceId + ".xsd");
            if (!schemaFile.exists()) {
                schemaFile.createNewFile();
            }
//            File wsdlFile = new File(serviceId + ".wsdl");
            schemaOut = new BufferedOutputStream(new FileOutputStream(schemaFile));
//            BufferedOutputStream wsdlOut = new BufferedOutputStream(new FileOutputStream(wsdlFile));

            com.dc.esb.servicegov.util.XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new com.dc.esb.servicegov.util.XMLWriter(schemaOut, format);
            writer.write(document);

            return true;
        } catch (DataException e) {
            log.error(e, e);
        } catch (UnsupportedEncodingException e) {
            log.error(e, e);
        } catch (IOException e) {
            log.error(e, e);
        } finally {
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

    public void interfaceHeadDeal(OperationPK operationPK, String structName, Element bodyElement, Element rootElement ){
    //查询非标提供者
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.operationId=? and si.serviceId = ? and type = ? " +
                "and isStandard = ?";
        List interfaces = serviceInvokeService.find(hql, operationPK.getOperationId(), operationPK.getServiceId(), Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if(null != interfaces && 0 < interfaces.size()){
            //如果有多个非标借口提供者
            for(Object inter : interfaces){
                String interfaceId = inter.toString();
                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                if(null != relates && 0 < relates.size()){
                    for(InterfaceHeadRelate interfaceHeadRelate : relates){
                        String headId = interfaceHeadRelate.getHeadId();
                        InterfaceHead interfaceHead = interfaceHeadService.getById(headId);
                        //查询系统信息
                        if(!Arrays.asList(NO_LOCAL_HEAD_SYSTEM).contains(interfaceHead.getSystemId())){
                           return;
                        }
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("headId", headId);
                        params.put("structName", structName);
//                    String hql2 = " from Ida where headId = ? and structName = ?";
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
                                renderAppBodyHeadSDA(sda, bodyElement);
                            }
                        }

                    }
                }
            }

        }
    }

    public void renderAppHeadSDA(SDA sda,  Element parentElem){
        if(null != sda){
            Element element = parentElem.addElement(new QName("element", NS_URI_XMLNS));
            element.addAttribute("name", sda.getStructName());
            if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()))){
                element.addAttribute("maxOccurs", "unbounded");

                List<SDA> children = sdaService.getChildren(sda);
                if(null != children && 0 < children.size()){
                    Element complexType = element.addElement(new QName("complexType", NS_URI_XMLNS));
                    Element sequence = complexType.addElement(new QName("sequence", NS_URI_XMLNS));

                    for(SDA child : children){
                        renderAppHeadSDA(child, sequence);
                    }
                }
            }
            else{
                if(StringUtils.isNotEmpty(sda.getType())){
                    if (sda.getType().toLowerCase().contains("number")) {
                        element.addAttribute("type", "x:decimal");
                    } else if (sda.getType().toLowerCase().contains("int")) {
                        element.addAttribute("type", "x:int");
                    } else if (sda.getType().toLowerCase().contains("double")) {
                        element.addAttribute("type", "x:double");
                    } else {
                        element.addAttribute("type", "x:string");
                    }
                }

//                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
//                if(null != metadata){
//                    if ("number".equalsIgnoreCase(metadata.getType())) {
//                        element.addAttribute("type", "x:decimal");
//                    } else if ("int".equalsIgnoreCase(metadata.getType())) {
//                        element.addAttribute("type", "x:int");
//                    } else if ("double".equalsIgnoreCase(metadata.getType())) {
//                        element.addAttribute("type", "x:double");
//                    } else {
//                        element.addAttribute("type", "x:string");
//                    }
//                }
                if("Y".equalsIgnoreCase(sda.getRequired())){
                    element.addAttribute("minOccurs", "1");
                }else{
                    element.addAttribute("minOccurs", "0");
                }
            }
        }
    }

    public void renderAppBodyHeadSDA(SDA sda,  Element parentElem){
        if(null != sda){
            if(!"SYS_HEAD".equalsIgnoreCase(sda.getConstraint()) && !"APP_HEAD".equalsIgnoreCase(sda.getConstraint())){
                Element element = parentElem.addElement(new QName("element", NS_URI_XMLNS));
                element.addAttribute("name", sda.getStructName());
                if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()))){
                    element.addAttribute("maxOccurs", "unbounded");

                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
//                    element.addAttribute("name", sda.getStructName());
                        Element complexType = element.addElement(new QName("complexType", NS_URI_XMLNS));
                        Element sequence = complexType.addElement(new QName("sequence", NS_URI_XMLNS));

                        for(SDA child : children){
                            renderAppBodyHeadSDA(child, sequence);
                        }
                    }
                }
                else{
                    if(StringUtils.isNotEmpty(sda.getType())){
                        if (sda.getType().toLowerCase().contains("number")) {
                            element.addAttribute("type", "x:decimal");
                        } else if (sda.getType().toLowerCase().contains("int")) {
                            element.addAttribute("type", "x:int");
                        } else if (sda.getType().toLowerCase().contains("double")) {
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

    public void renderAppBodydSDA(SDA sda,  Element parentElem, Element rootElement){
        if(null != sda){
            if(!"SYS_HEAD".equalsIgnoreCase(sda.getConstraint()) && !"APP_HEAD".equalsIgnoreCase(sda.getConstraint())){
                Element element = parentElem.addElement(new QName("element", NS_URI_XMLNS));

                if("array".equalsIgnoreCase(sda.getType())){
                    element.addAttribute("name", sda.getStructName());

                    List<SDA> children = sdaService.getChildren(sda);
                    if(null != children && 0 < children.size()){
                        element.addAttribute("type", "s:"+sda.getStructName());
                        element.addAttribute("nillable", "false");
                        element.addAttribute("maxOccurs", "unbounded");

                        Element appBodyComplexType = rootElement.addElement(QN_COMPLEX_TYPE);
                        appBodyComplexType.addAttribute("name", sda.getStructName());
                        Element sequence = appBodyComplexType.addElement(QN_SEQ);
                        for(SDA child : children){
                            renderAppBodydSDA(child, sequence, rootElement);
                        }

                    }
                }
                else{
                    Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                    if(null != metadata){
                        if(!uniqueList.contains(metadata.getMetadataId())){//如果未存在root下
                            uniqueList.add(metadata.getMetadataId());
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
    }
    //引入localhead命名空间
    public void importReqLocalHeadNamespace(List<OperationPK> operationPKs, Element schemaRoot, String dirPath){
        List<String> systemCache = new ArrayList<String>();
        for(OperationPK operationPK : operationPKs) {
            String hql = "select distinct si.interfaceId from ServiceInvoke si where si.operationId=? and si.serviceId = ? and type = ? " +
                    "and isStandard = ?";
            //查询提供方非标接口
            List interfaces = serviceInvokeService.find(hql, operationPK.getOperationId(), operationPK.getServiceId(), Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);

            if (null != interfaces && 0 < interfaces.size()) {
                for (Object inter : interfaces) {
                    String interfaceId = inter.toString();
                    List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                    if (null != relates && 0 < relates.size()) {
                        for (InterfaceHeadRelate interfaceHeadRelate : relates) {
                            String headId = interfaceHeadRelate.getHeadId();
                            InterfaceHead interfaceHead = interfaceHeadService.getById(headId);
                            //查询系统信息
                            com.dc.esb.servicegov.entity.System system = systemService.getById(interfaceHead.getSystemId());
                            if(!Arrays.asList(NO_LOCAL_HEAD_SYSTEM).contains(system.getSystemId())){
                                String systemAb = system.getSystemAb();
                                if(null != interfaceHead){
                                    if(!systemCache.contains(interfaceHead.getSystemId())){
                                        systemCache.add(interfaceHead.getSystemId());
                                        schemaRoot.addNamespace(systemAb, "http://esb.dcitsbiz.com/" + systemAb);

                                        Element metadataImportElem = schemaRoot.addElement(new QName("import", NS_URI_XMLNS));
                                        metadataImportElem.addAttribute("namespace", "http://esb.dcitsbiz.com/" + systemAb);
                                        metadataImportElem.addAttribute("schemaLocation",  systemAb+".xsd");

                                        localHeadGenerator.generate(interfaceHead.getSystemId(), dirPath);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param operationPK
     * @param parentElem
     * @param type Req或Rsp
     */
    public void addLocalHeadElem(OperationPK operationPK, Element parentElem, String type){
        List<String> cacheHeadList = new ArrayList<String>();//报文头缓存，防止在一个场景中一个系统有多个接口关联相同报文头
        String hql = "select distinct si.interfaceId from ServiceInvoke si where si.operationId=? and si.serviceId = ? and type = ? " +
                "and isStandard = ?";
        //查询提供方非标接口
        List interfaces = serviceInvokeService.find(hql, operationPK.getOperationId(), operationPK.getServiceId(), Constants.INVOKE_TYPE_PROVIDER, Constants.INVOKE_TYPE_STANDARD_N);
        if (null != interfaces && 0 < interfaces.size()) {
            for (Object inter : interfaces) {
                String interfaceId = inter.toString();
                List<InterfaceHeadRelate> relates = interfaceHeadRelateService.findBy("interfaceId", interfaceId);
                if (null != relates && 0 < relates.size()) {
                    for (InterfaceHeadRelate interfaceHeadRelate : relates) {
                        String headId = interfaceHeadRelate.getHeadId();
                        if(!cacheHeadList.contains(headId)){
                            InterfaceHead interfaceHead = interfaceHeadService.getById(headId);
                            if (null != interfaceHead) {
                                //查询系统信息
                                com.dc.esb.servicegov.entity.System system = systemService.getById(interfaceHead.getSystemId());
                                String systemAb = system.getSystemAb();
                                if(!Arrays.asList(NO_LOCAL_HEAD_SYSTEM).contains(system.getSystemId())){
                                    Element reqHeaderElem = parentElem.addElement(QN_ELEM);
                                    String elemName = type + "LocalHead";
                                    reqHeaderElem.addAttribute("name", elemName);
                                    reqHeaderElem.addAttribute("type", systemAb + ":" + type + "LocalHeadType");
                                }
                            }
                            cacheHeadList.add(headId);
                        }
                    }
                }
            }
        }
    }
}
