package com.dc.esb.servicegov.export.impl;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/3/9.
 * 宁波银行fix拆组包生成器
 */
@Component
public class NbcbIiFixGenerator extends ConfigExportGenerator {
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