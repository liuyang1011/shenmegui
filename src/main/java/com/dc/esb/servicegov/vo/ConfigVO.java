package com.dc.esb.servicegov.vo;

/**
 * Created by Administrator on 2015/10/26.
 */
public class ConfigVO {
    private   String serviceId;
    private   String serviceName;
    private   String operationId;
    private   String operationName;
    private   String providerId;
    private   String providerName;
    private   String customerId;
    private   String customerName;
    private   String isStandardPro;
    private   String isStandardCon;
    private   String interfaceOrProtocolPro;
    private   String interfaceIdOrProtocolIdPro;
    private   String interfaceOrProtocolCon;
    private   String interfaceIdOrProtocolIdCon;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

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

    public String getIsStandardPro() {
        return isStandardPro;
    }

    public void setIsStandardPro(String isStandardPro) {
        this.isStandardPro = isStandardPro;
    }

    public String getIsStandardCon() {
        return isStandardCon;
    }

    public void setIsStandardCon(String isStandardCon) {
        this.isStandardCon = isStandardCon;
    }

    public String getInterfaceOrProtocolPro() {
        return interfaceOrProtocolPro;
    }

    public void setInterfaceOrProtocolPro(String interfaceOrProtocolPro) {
        this.interfaceOrProtocolPro = interfaceOrProtocolPro;
    }


    public String getInterfaceOrProtocolCon() {
        return interfaceOrProtocolCon;
    }

    public void setInterfaceOrProtocolCon(String interfaceOrProtocolCon) {
        this.interfaceOrProtocolCon = interfaceOrProtocolCon;
    }


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInterfaceIdOrProtocolIdPro() {
        return interfaceIdOrProtocolIdPro;
    }

    public void setInterfaceIdOrProtocolIdPro(String interfaceIdOrProtocolIdPro) {
        this.interfaceIdOrProtocolIdPro = interfaceIdOrProtocolIdPro;
    }

    public String getInterfaceIdOrProtocolIdCon() {
        return interfaceIdOrProtocolIdCon;
    }

    public void setInterfaceIdOrProtocolIdCon(String interfaceIdOrProtocolIdCon) {
        this.interfaceIdOrProtocolIdCon = interfaceIdOrProtocolIdCon;
    }
}
