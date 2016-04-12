package com.dc.esb.servicegov.wsdl.impl;

import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.entity.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
 * Date: 14-6-23
 * Time: 下午7:03
 */
@Component
public class ESBServiceDescriptorGenerator {
    private static final Log log = LogFactory.getLog(ESBServiceDescriptorGenerator.class);

    public boolean generate(String serviceId, List<OperationPK> operationPKs,String dirPath) {
        BufferedOutputStream descOut = null;
        try {
            String tmpServiceId = serviceId;
            Document document = DocumentHelper.createDocument();
            Element metadatasElement = document.addElement("metadatas");
            Element metadataElement = metadatasElement.addElement("metadata");
            Element serviceIdElement = metadataElement.addElement("serviceid");
            serviceIdElement.addText(tmpServiceId);
            Element wsdlElement = metadataElement.addElement("wsdl");
            for (OperationPK operationPK : operationPKs) {
                String tmpOperationId = "op" + operationPK.getOperationId();
                Element operationElement = wsdlElement.addElement("operation");
                operationElement.addText(tmpOperationId);
            }
            Element fileElement = wsdlElement.addElement("file");
            fileElement.addText(tmpServiceId + ".wsdl");
            Element xsdElement = metadataElement.addElement("xsd");
            xsdElement.addElement("common").addText(tmpServiceId + ".xsd");

            Element instanceElement = metadataElement.addElement("instance");
            instanceElement.addElement("in");
            instanceElement.addElement("out");

            File descDir = new File(dirPath + File.separator + "Description");
            descDir.mkdirs();
            File descFile = new File(descDir.getAbsolutePath() + File.separator + tmpServiceId + ".xml");
            descOut = new BufferedOutputStream(new FileOutputStream(descFile));
//            BufferedOutputStream wsdlOut = new BufferedOutputStream(new FileOutputStream(wsdlFile));

            com.dc.esb.servicegov.util.XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            writer = new com.dc.esb.servicegov.util.XMLWriter(descOut, format);
            writer.write(document);
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            if (null != descOut) {
                try {
                    descOut.close();
                } catch (IOException e) {
                    log.error(e, e);
                }
            }
        }
        return true;
    }

}
