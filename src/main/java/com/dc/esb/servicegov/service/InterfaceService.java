package com.dc.esb.servicegov.service;

import com.dc.esb.servicegov.entity.Interface;
import com.dc.esb.servicegov.service.support.BaseService;

import java.util.List;

public interface InterfaceService  extends BaseService<Interface, String> {
    public Interface getInterfaceById(String hql,String interfaceId);
    public List<Interface> getBySystemId(String systemId);
    public List<Interface> findByConditions(String condition);
}
