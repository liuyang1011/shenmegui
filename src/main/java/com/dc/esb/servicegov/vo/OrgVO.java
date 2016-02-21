package com.dc.esb.servicegov.vo;

import com.dc.esb.servicegov.entity.Organization;
/**
 * Created by lmd on 2016/1/25.
 */
public class OrgVO {
    private String orgId;
    private String orgName;
    private String orgAB;
    private String orgStatus;

    public OrgVO(String orgId,String orgName,String orgAB,String orgStatus){
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgAB = orgAB;
        this.orgStatus = orgStatus;
    }

    public OrgVO(Organization org){
        this.orgId = org.getOrgId();
        this.orgName = org.getOrgName();
        this.orgAB = org.getOrgAB();
        this.orgStatus = org.getOrgStatus();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAB() {
        return orgAB;
    }

    public void setOrgAB(String orgAB) {
        this.orgAB = orgAB;
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }
}
