package com.dc.esb.servicegov.service;

import com.dc.esb.servicegov.entity.Interface;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.support.BaseService;
import com.dc.esb.servicegov.util.TreeNode;

import java.util.List;

public interface ServiceIdentifyService extends BaseService<Interface, String> {
    public boolean judgeByMetadataId(String interfaceId);
    public boolean uniqueServiceId(String serviceId);
    public boolean uniqueOperationId(String serviceId, String operationId);
    public void changeInterfaceState(String interfaceId, String state);
    public List<Object>  getSystemId(String serviceId, String operationId);

}
