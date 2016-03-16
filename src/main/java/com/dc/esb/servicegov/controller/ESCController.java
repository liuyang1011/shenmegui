package com.dc.esb.servicegov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/esc")
public class ESCController {
    @RequestMapping(method = RequestMethod.POST, value = "/testEsc", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String,String> testEsc(@RequestBody Map<String, String> req) {
        Map<String, String> result = new HashMap<String, String>();
        String rspMessage = "";
        String protocol = req.get("protocol");
        String encoding = req.get("encoding");
        String address = req.get("address");
        String reqMessage = req.get("reqMessage");
        String headLen = req.get("headLen");

        Socket client = null;
        OutputStream out = null;
        InputStream in = null;
        ByteArrayOutputStream byteOut = null;
        try {
            if ("TCP".equalsIgnoreCase(protocol)) {
                if (null != address) {
                    if (address.indexOf(":") < 0) {
                        rspMessage = "地址格式[" + address + "]不正确";
                        result.put("result", rspMessage);
                        return result;
                    } else {
                        String[] addressInfo = address.split(":");
                        String ip = addressInfo[0];
                        String portStr = addressInfo[1];
                        int port = Integer.parseInt(portStr);
                        if (null != reqMessage) {
                            if (null != encoding) {
                                int headLength = Integer.parseInt(headLen);
                                byte[] content = reqMessage.getBytes(encoding);
                                int msgLength = content.length;
                                String head = String.valueOf(msgLength);
                                client = new Socket(ip, port);
                                out = client.getOutputStream();
                                if(headLength >= head.length()){
                                    int rawLength = head.length();
                                    for(int i = 0; i < (headLength - rawLength); i++){
                                        head = "0" + head;
                                    }
                                    out.write(head.getBytes());
                                }
                                out.write(content);
                                out.flush();
                                client.shutdownOutput();
                                int size = 0;
                                byteOut = new ByteArrayOutputStream();
                                in = client.getInputStream();
                                byte[] buffer = new byte[1024];
                                while ((size = in.read(buffer)) > 0) {
                                    byteOut.write(buffer,0,size);
                                }
                                rspMessage = new String(byteOut.toByteArray(),encoding);
                            } else {
                                rspMessage = "编码格式为[" + encoding + "]";
                            }
                        } else {
                            rspMessage = "报文为[" + reqMessage + "]";
                        }
                    }
                } else {
                    rspMessage = "地址[" + address + "]不支持";
                }
            }
        } catch (Exception e) {
            rspMessage = e.getMessage();
            rspMessage += "\n" + e.getStackTrace();
        }
        result.put("result", rspMessage);
        return result;
    }
}
