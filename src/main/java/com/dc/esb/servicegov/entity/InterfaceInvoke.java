package com.dc.esb.servicegov.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by jiangqi on 2015/8/10.
 */
@Entity
@Table(name = "INTERFACE_INVOKE")
public class InterfaceInvoke {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;

    @Column(name = "PROVIDER_INVOKE_ID")
    private String providerInvokeId;

    @Column(name = "CONSUMER_INVOKE_ID")
    private String consumerInvokeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsumerInvokeId() {
        return consumerInvokeId;
    }

    public void setConsumerInvokeId(String consumerInvokeId) {
        this.consumerInvokeId = consumerInvokeId;
    }

    public String getProviderInvokeId() {
        return providerInvokeId;
    }

    public void setProviderInvokeId(String providerInvokeId) {
        this.providerInvokeId = providerInvokeId;
    }
}
