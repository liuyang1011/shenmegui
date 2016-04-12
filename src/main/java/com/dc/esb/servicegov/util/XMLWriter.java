package com.dc.esb.servicegov.util;

import org.dom4j.Document;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by xhx104 on 2016/3/8.
 */
public class XMLWriter extends org.dom4j.io.XMLWriter {

    @Override
    public void write(Document doc) throws IOException {
        writeDeclaration();

        if (doc.getDocType() != null) {
            indent();
            writeDocType(doc.getDocType());
        }

        for (int i = 0, size = doc.nodeCount(); i < size; i++) {
            Node node = doc.node(i);
            writeNode(node);
        }

        flush();
    }

    public XMLWriter(OutputStream out, OutputFormat format)
            throws UnsupportedEncodingException {
        super(out, format);
    }
}
