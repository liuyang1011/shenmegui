package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.entity.SDANode;
import com.dc.esb.servicegov.entity.SDANodeProperty;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.entity.ServiceExtendInfo;
import com.dc.esb.servicegov.exception.DataException;
import com.dc.esb.servicegov.vo.SDA;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-27
 * Time: 上午11:23
 */
@org.springframework.stereotype.Service
public class ServiceManagerImpl {

    private static final Log log = LogFactory.getLog(ServiceManagerImpl.class);

    @Autowired
    private ServiceDAOImpl serviceDAO;
    @Autowired
    private SDANodeDAOImpl sdaNodeDAO;
    @Autowired
    private SDANodePropertyDAOImpl sdaNodePropertyDAO;
    @Autowired
    private MetadataDAOImpl metadataDAO;
    @Autowired
    private ServiceExtendInfoDAOImpl serviceExtendInfoDAO;

    public List<Service> getServiceById(String serviceId){
        List<Service> service = null;
        if(null != serviceId){
            service = serviceDAO.findBy("serviceId", serviceId);
        }
        return service;
    }

    @Transactional
    public SDA getSDAofService(Service service) throws DataException {
        SDA sda = null;
        if(null != service){
            sda = new SDA();
            Map<String, String> params = new HashMap<String, String>();
            if(log.isDebugEnabled()){
                log.debug("查找根节点，服务id为["+service.getServiceId()+"], 父节点为[/]");
            }
            params.put("serviceId", service.getServiceId().trim());
            params.put("parentResourceId", "/");
            List<SDANode> nodes = sdaNodeDAO.findBy(params);
            if(nodes.size() > 1){
                String errorMsg = "一个sda只能有一个root节点";
                log.error(errorMsg);
                throw new DataException(errorMsg);
            }
            //获取node节点
            SDANode node = nodes.get(0);
            if(log.isDebugEnabled()){
                log.debug("获取到根节点["+node+"]");

            }
            //获取node节点的属性
            List<SDANodeProperty> nodeProperties = sdaNodePropertyDAO.findBy("structId", node.getResourceId());
            sda.setValue(node);
            sda.setProperties(nodeProperties);
            List<SDA> childSDA = getChildSDANodes(sda);
            sda.setChildNode(childSDA);
        }
        return sda;
    }

    @Transactional
    public List<SDA> getChildSDANodes(SDA sda) throws DataException {
        List<SDA> childSdas = null;
        if(null != sda){
            SDANode sdaNode = sda.getValue();
            if(null == sdaNode){
                String errorMsg = "sda的数据节点为空!";
                log.error(errorMsg);
                throw new DataException(errorMsg);
            }
            String nodeResourceId = sdaNode.getResourceId();
            //获取所有的数据子节点
            List<SDANode> childNodes = sdaNodeDAO.findBy("parentResourceId", nodeResourceId);
            if(null != childNodes && childNodes.size() > 0){
                childSdas = new ArrayList<SDA>();
            }
            for(SDANode childNode : childNodes){
                //获取子节点的属性信息
                List<SDANodeProperty> childNodeProperties = sdaNodePropertyDAO.findBy("structId", childNode.getResourceId());
                SDA childSDA = new SDA();
                childSDA.setValue(childNode);
                childSDA.setProperties(childNodeProperties);
                //递归获取节点自己的子节点
                List<SDA> grandChildNodes =  getChildSDANodes(childSDA);
                if(null != grandChildNodes){
                    childSDA.setChildNode(grandChildNodes);
                }
                childSdas.add(childSDA);
            }
        }
        return childSdas;
    }

    @Transactional
    public List<Service> getOpertions(String serviceId) throws DataException {
        List<Service> operations = null;
        List<ServiceExtendInfo> serviceExtendInfos = serviceExtendInfoDAO.findBy("superServiceId", serviceId);
        if(null != serviceExtendInfos){
            operations = new ArrayList<Service>();
            for(ServiceExtendInfo serviceExtendInfo : serviceExtendInfos){
                String operationRelationId= serviceExtendInfo.getRelationId();
                if(null != operationRelationId){
                    List<Service> services = serviceDAO.findBy("resourceId", operationRelationId);
                    if(null == services){
                        String errorMsg = "服务["+serviceId+"]的操作["+operationRelationId+"]不存在";
                        log.error(errorMsg);
                        throw new DataException((errorMsg));
                    }
                    if(services.size() == 0){
                        String errorMsg = "服务["+serviceId+"]的操作["+operationRelationId+"]不存在";
                        log.error(errorMsg);
                        throw new DataException(errorMsg);
                    }
                    if(services.size() > 1) {
                        String errorMsg = "服务["+serviceId+"]的中存在重复的操作";
                        log.error(errorMsg);
                        throw new DataException(errorMsg);
                    }
                    operations.add(services.get(0));
                }
            }
        }
        return operations;
    }

    @Transactional
    public List<Service> getServiceOfOperation(Service operation){
        List<Service> superServices = null;
        if(null != operation){
            superServices = new ArrayList<Service>();
            String operationResourceId = operation.getResourceId();
            List<ServiceExtendInfo> serviceExtendInfos = serviceExtendInfoDAO.findBy("relationId", operationResourceId);
            for(ServiceExtendInfo serviceExtendInfo : serviceExtendInfos) {
                String serviceId = serviceExtendInfo.getSuperServiceId();
                List<Service> services = serviceDAO.findBy("serviceId", serviceId);
                superServices.addAll(services);
            }
        }
        return superServices;
    }
}
