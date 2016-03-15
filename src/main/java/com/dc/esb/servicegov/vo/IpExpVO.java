package com.dc.esb.servicegov.vo;

import com.dc.esb.servicegov.entity.Operation;

/**
 * Created by wang on 2015/8/11.
 */
public class IpExpVO {
    private String serviceId;
    private String serviceName;
    private String operationId;
    private String operationName;
    private String consumer;
    private String consumerAb;
    private String conIp;
    private String provider;
    private String providerAb;
    private String proIp;
    public IpExpVO(){}

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getConIp() {
        return conIp;
    }

    public void setConIp(String conIp) {
        this.conIp = conIp;
    }

    public String getProIp() {
        return proIp;
    }

    public void setProIp(String proIp) {
        this.proIp = proIp;
    }

    public String getConsumerAb() {
        return consumerAb;
    }

    public void setConsumerAb(String consumerAb) {
        this.consumerAb = consumerAb;
    }

    public String getProviderAb() {
        return providerAb;
    }

    public void setProviderAb(String providerAb) {
        this.providerAb = providerAb;
    }
}
