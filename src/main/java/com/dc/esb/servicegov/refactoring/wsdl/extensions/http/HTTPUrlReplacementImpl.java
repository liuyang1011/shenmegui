
package com.dc.esb.servicegov.refactoring.wsdl.extensions.http;

import javax.wsdl.extensions.http.HTTPUrlReplacement;
import javax.xml.namespace.QName;

public class HTTPUrlReplacementImpl implements HTTPUrlReplacement {
    protected QName elementType = HTTPConstants.Q_ELEM_HTTP_URL_REPLACEMENT;
    // Uses the wrapper type so we can tell if it was set or not.
    protected Boolean required = null;

    public static final long serialVersionUID = 1;

    /**
     * Set the type of this extensibility element.
     *
     * @param elementType the type
     */
    public void setElementType(QName elementType) {
        this.elementType = elementType;
    }

    /**
     * Get the type of this extensibility element.
     *
     * @return the extensibility element's type
     */
    public QName getElementType() {
        return elementType;
    }

    /**
     * Set whether or not the semantics of this extension
     * are required. Relates to the wsdl:required attribute.
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * Get whether or not the semantics of this extension
     * are required. Relates to the wsdl:required attribute.
     */
    public Boolean getRequired() {
        return required;
    }

    public String toString() {
        StringBuffer strBuf = new StringBuffer();

        strBuf.append("HTTPUrlReplacement (" + elementType + "):");
        strBuf.append("\nrequired=" + required);

        return strBuf.toString();
    }
}