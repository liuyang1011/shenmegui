
package com.dc.esb.servicegov.wsdl.extensions.http;

import com.dc.esb.servicegov.wsdl.Constants;
import com.dc.esb.servicegov.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.http.HTTPUrlReplacement;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

public class HTTPUrlReplacementSerializer implements ExtensionSerializer,
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
        HTTPUrlReplacement httpUrlReplacement = (HTTPUrlReplacement) extension;

        if (httpUrlReplacement != null) {
            String tagName =
                    DOMUtils.getQualifiedValue(HTTPConstants.NS_URI_HTTP,
                            "urlReplacement",
                            def);

            pw.print("        <" + tagName);

            Boolean required = httpUrlReplacement.getRequired();

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
        HTTPUrlReplacement httpUrlReplacement =
                (HTTPUrlReplacement) extReg.createExtension(parentType, elementType);
        String requiredStr = DOMUtils.getAttributeNS(el,
                Constants.NS_URI_WSDL,
                Constants.ATTR_REQUIRED);

        if (requiredStr != null) {
            httpUrlReplacement.setRequired(new Boolean(requiredStr));
        }

        return httpUrlReplacement;
    }
}