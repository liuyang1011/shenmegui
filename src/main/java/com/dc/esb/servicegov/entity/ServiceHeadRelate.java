package com.dc.esb.servicegov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/11/12.
 */
@Entity
@Table(name = "SERVICE_HEAD_RELATE")
public class ServiceHeadRelate {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "service_head_id")
    private String serviceHeadId;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "operation_id")
    private String operationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceHeadId() {
        return serviceHeadId;
    }

    public void setServiceHeadId(String serviceHeadId) {
        this.serviceHeadId = serviceHeadId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
