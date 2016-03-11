package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.SDA;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/9.
 * 宁波银行fix拆组包生成器
 */
@Component
public class NbcbFixGenerator extends ConfigExportGenerator{
    String channel_service_path = "template/config_export/nbcb/fix_channel_service_template.xml";//fix请求文件模板路径
    String service_channel_path = "template/config_export/nbcb/fix_service_channel_template.xml";//fix响应文件模板路径
    private static final String ENCODING = "GBK";
    @Override
    public String getChannelServicePath(){
        return channel_service_path;
    }
    @Override
    public String getServiceChannelPath(){
        return service_channel_path;
    }

    public void renderInterfaceHeadIda(Element parentElement, Ida ida, String headId){
        if(null != ida && StringUtils.isNotEmpty(ida.getStructName())){
            Element idaElement = parentElement.addElement(ida.getStructName());
            addAttribute(idaElement, "encoding", ENCODING);

            Map<String, String> params = new HashMap<String, String>();
            params.put("headId", headId);
            params.put("xpath", ida.getXpath());
            SDA sda = sdaService.findUniqueBy(params);
            if(null != sda){
                addAttribute(idaElement, "metadataId", sda.getMetadataId());
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(null != metadata){
                    addAttribute(idaElement, "length", metadata.getLength());
                    addAttribute(idaElement, "type", "array".equalsIgnoreCase(metadata.getType()) ? "array" : "string");;
                }

            }
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    renderInterfaceHeadIda(idaElement, child, headId);
                }
            }
        }
    }
    public void renderBodyIda(Element parentElement, Ida ida, String serviceId, String operationId ){
        if(null != ida && StringUtils.isNotEmpty(ida.getStructName())){
            Element idaElement = parentElement.addElement(ida.getStructName());
            addAttribute(idaElement, "encoding", ENCODING);

            Map<String, String> params = new HashMap<String, String>();
            params.put("serviceId", serviceId);
            params.put("operationId", operationId);
            params.put("xpath", ida.getXpath());
            SDA sda = sdaService.findUniqueBy(params);
            if(null != sda){
                addAttribute(idaElement, "metadataId", sda.getMetadataId());
                Metadata metadata = metadataService.findUniqueBy("metadataId", sda.getMetadataId());
                if(null != metadata){
                    addAttribute(idaElement, "length", metadata.getLength());
                    addAttribute(idaElement, "type", "array".equalsIgnoreCase(metadata.getType()) ? "array" : "string");;
                }
            }
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    renderBodyIda(idaElement, child, serviceId, operationId);
                }
            }
        }
    }
}
