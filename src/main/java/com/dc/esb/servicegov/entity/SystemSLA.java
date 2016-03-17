package com.dc.esb.servicegov.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYSTEM_SLA")
public class SystemSLA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SLA_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String slaId;

    @Column(name="SYSTEM_ID")
    private String systemId;

    @Column(name = "SLA_NAME")
    private String slaName;

    @Column(name = "SLA_VALUE")
    private String slaValue;

    @Column(name = "SLA_DESC")
    private String slaDesc;

    @Column(name = "SLA_REMARK")
    private String slaRemark;

    @Column(name = "VERSION")
    private String version;

    /*@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SLA)) {
            return false;
        }
        SLA another = (SLA) obj;

        return ((null == this.serviceId) ? (null == another.getServiceId()) : (this.serviceId.equals(another.getServiceId()))) &&
                ((null == this.operationId) ? (null == another.getOperationId()) : (this.operationId.equals(another.getOperationId()))) &&
                ((null == this.slaName) ? (null == another.getSlaName()) : (this.slaName.equals(another.getSlaName())));
    }*/
    public SystemSLA(){}

    public SystemSLA(SystemSLA temSLA){
        this.slaName = temSLA.getSlaName();
        this.slaValue = temSLA.getSlaValue();
        this.slaDesc = temSLA.getSlaDesc();
        this.slaRemark = temSLA.getSlaRemark();
    }
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    public String getSlaId() {
        return slaId;
    }

    public void setSlaId(String slaId) {
        this.slaId = slaId;
    }

    public String getSlaName() {
        return slaName;
    }

    public void setSlaName(String slaName) {
        this.slaName = slaName;
    }

    public String getSlaValue() {
        return slaValue;
    }

    public void setSlaValue(String slaValue) {
        this.slaValue = slaValue;
    }

    public String getSlaDesc() {
        return slaDesc;
    }

    public void setSlaDesc(String slaDesc) {
        this.slaDesc = slaDesc;
    }

    public String getSlaRemark() {
        return slaRemark;
    }

    public void setSlaRemark(String slaRemark) {
        this.slaRemark = slaRemark;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
