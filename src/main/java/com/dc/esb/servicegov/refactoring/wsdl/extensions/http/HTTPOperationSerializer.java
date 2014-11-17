
package com.dc.esb.servicegov.refactoring.wsdl.extensions.http;

import com.dc.esb.servicegov.wsdl.Constants;
import com.dc.esb.servicegov.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.http.HTTPOperation;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

public class HTTPOperationSerializer implements ExtensionSerializer,
        ExtensionDeserializer,
        Serializable {
    public static final long serialVersionUID = 1;

    public void marshall(Class parentType,
                         QName elementType,
                         ExtensibilityElement extension,
                         PrintWriter pw,
                         Definition def,
                         ExtensionRegistry extReg)
            throws WSDLException {
        HTTPOperation httpOperation = (HTTPOperation) extension;

        if (httpOperation != null) {
            String tagName =
                    DOMUtils.getQualifiedValue(HTTPConstants.NS_URI_HTTP,
                            "operation",
                            def);

            pw.print("      <" + tagName);

            DOMUtils.printAttribute(Constants.ATTR_LOCATION,
                    httpOperation.getLocationURI(),
                    pw);

            Boolean required = httpOperation.getRequired();

            if (required != null) {
                DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED,
                        required.toString(),
                        def,
                        pw);
            }

            pw.println("/>");
        }
    }

    public ExtensibilityElement unmarshall(Class parentType,
                                           QName elementType,
                                           Element el,
                                           Definition def,
                                           ExtensionRegistry extReg)
            throws WSDLException {
        HTTPOperation httpOperation =
                (HTTPOperation) extReg.createExtension(parentType, elementType);
        String locationURI = DOMUtils.getAttribute(el, Constants.ATTR_LOCATION);
        String requiredStr = DOMUtils.getAttributeNS(el,
                Constants.NS_URI_WSDL,
                Constants.ATTR_REQUIRED);

        if (locationURI != null) {
            httpOperation.setLocationURI(locationURI);
        }

        if (requiredStr != null) {
            httpOperation.setRequired(new Boolean(requiredStr));
        }

        return httpOperation;
    }
}