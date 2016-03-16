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
public class NbcbJsonGenerator extends ConfigExportGenerator {
    String channel_service_path = "template/config_export/nbcb/json_channel_service_template.xml";//请求文件模板路径
    String service_channel_path = "template/config_export/nbcb/json_service_channel_template.xml";//响应文件模板路径
    private static final String ENCODING = "GBK";

    @Override
    public String getChannelServicePath() {
        return channel_service_path;
    }

    @Override
    public String getServiceChannelPath() {
        return service_channel_path;
    }
}