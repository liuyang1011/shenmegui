package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.service.impl.IdaServiceImpl;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xhx109 on 2016/3/30.
 */
@Component
public class MappingFileDataFromDB {
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private IdaServiceImpl idaService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private OperationServiceImpl operationService;

    public Service getService(String serviceId){
        String hql = " from Service where serviceId = ? ";
        List<Service> list = serviceService.find(hql, serviceId);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public Operation getOperation(String serviceId, String operationId){
        String hql = " from Operation where serviceId = ? and operationId = ?";
        List<Operation> list = operationService.find(hql, serviceId, operationId);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public SDA findByXpath(String serviceId, String operationId, String xpath){
        String hql = " from SDA where serviceId = ? and operationId = ? and xpath = ?";
        List<SDA> list  = sdaService.find(hql,serviceId,operationId, xpath);
        if(list.size() > 0 ){
            return list.get(0);
        }
        return null;
    }

    public List<SDA> getChildren(SDA sda){
        String hql = " from SDA where parentId = ? order by seq asc";
        List<SDA> children = sdaService.find(hql, sda.getId());
        return children;
    }

    public List<Ida> findByParentIdOrder(String id) {
        String hql = " from Ida where _parentId=? order by seq asc";
        List<Ida> children = idaService.find(hql, id);
        return children;
    }

    public List<Ida> getIdaByParentName(String interfaceId, String parentName) {
        String hql = " from " + Ida.class.getName() + " as i where i._parentId in(" +
                " select i2.id from " + Ida.class.getName() + " as i2 where i2.interfaceId = ? and structName = ?" +
                ") order by i.seq asc";
        List<Ida> list = idaService.find(hql, interfaceId, parentName);
        return list;
    }

    /**
     * @param serviceId
     * @param operationId
     * @param parentName
     * @return
     */
    public List<SDA> getSDAByParentName(String serviceId, String operationId, String parentName) {
        String hql = " from " + SDA.class.getName() + " as s where s.parentId in( " +
                "select s2.id from " + SDA.class.getName() + " as s2 where s2.serviceId=? and s2.operationId=? and s2.structName=? " +
                ") order by s.seq asc";
        List<SDA> list = sdaService.find(hql, serviceId, operationId, parentName);
        return list;
    }
}
