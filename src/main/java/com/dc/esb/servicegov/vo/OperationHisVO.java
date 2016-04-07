package com.dc.esb.servicegov.vo;


import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.entity.VersionHis;
import com.dc.esb.servicegov.entity.jsonObj.ServiceInvokeJson;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class OperationHisVO {

    private String autoId;

    private String operationId;

    private String operationName;

    private String operationDesc;

    private Service service;

    private VersionHis versionHis;

    private String customer;

    private String privater;

    private String targetId;

    private String colorType;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OperationHisVO) {
            OperationHisVO vo = (OperationHisVO) obj;
            if (this.getService().getServiceId().equals(vo.getService().getServiceId())
                    && this.getOperationId().equals(vo.getOperationId())
                    &&this.getVersionHis().getCode().equals(vo.getVersionHis().getCode())) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }



    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }


    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public VersionHis getVersionHis() {
        return versionHis;
    }

    public void setVersionHis(VersionHis versionHis) {
        this.versionHis = versionHis;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPrivater() {
        return privater;
    }

    public void setPrivater(String privater) {
        this.privater = privater;
    }
}
