package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.IdaServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jboss.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class UnStandardXMLConfigExportGender extends ConfigExportGenerator{
    private Log log = LogFactory.getLog(UnStandardXMLConfigExportGender.class);

    @Override
    public void  generateRequest(ServiceInvoke serviceInvoke, String path){
        try {
            Interface inter = serviceInvoke.getInter();
            if(null != inter) {
                String serviceId = serviceInvoke.getServiceId();
                String operationId = serviceInvoke.getOperationId();

                String fileName = getReqFilePath(serviceInvoke, path);

                Document doc = DocumentHelper.createDocument();
                Element serviceElement = doc.addElement("service");//根节点
                addAttribute(serviceElement, "package_type", "xml");
                addAttribute(serviceElement, "store-mode", "UTF-8");

                Ida reqestIda = idaService.getByInterfaceIdStructName(inter.getInterfaceId(), Constants.ElementAttributes.REQUEST_NAME);
                List<Ida> children = idaService.getNotEmptyByParentId(reqestIda.getId());
                fillContent(serviceElement, children, serviceId, operationId);

                createFile(doc, fileName);
            }
        }catch (Exception e){
            log.error(e, e);
        }
    }

    @Override
    public void  generateResponse(ServiceInvoke serviceInvoke, String path) {
        try {
            Interface inter = serviceInvoke.getInter();
            if (null != inter) {
                String serviceId = serviceInvoke.getServiceId();
                String operationId = serviceInvoke.getOperationId();

                String fileName = getResFilePath(serviceInvoke, path);

                Document doc = DocumentHelper.createDocument();
                Element serviceElement = doc.addElement("service");//根节点
                addAttribute(serviceElement, "package_type", "xml");
                addAttribute(serviceElement, "store-mode", "UTF-8");

                Ida reqsponseIda = idaService.getByInterfaceIdStructName(inter.getInterfaceId(), Constants.ElementAttributes.RESPONSE_NAME);
                List<Ida> children = idaService.getNotEmptyByParentId(reqsponseIda.getId());
                fillContent(serviceElement, children, serviceId, operationId);

                createFile(doc, fileName);
            }

        } catch (Exception e) {
            log.error(e, e);
        }
    }

    /**
     * 填充
     */
    public void fillContent(Element element, List<Ida> idas, String serviceId, String operationId){
        for(Ida ida : idas){
            Element idaElement = fillContentTag(element, ida, serviceId, operationId);
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            fillContent(idaElement, children, serviceId, operationId);
        }
    }

    public Element fillContentTag(Element element, Ida ida, String serviceId, String operationId){
        Element idaElement = element.addElement(ida.getStructName());
        Map<String, String> params = new HashMap<String, String>();
        params.put("serviceId", serviceId);
        params.put("operationId", operationId);
        params.put("xpath", ida.getXpath());
        SDA sda = sdaService.findUniqueBy(params);
        if(null != sda){
            addAttribute(idaElement, "metadataId", sda.getMetadataId());
        }
        addAttribute(idaElement, "chinese_name", ida.getStructAlias());;
        return idaElement;


    }
}
