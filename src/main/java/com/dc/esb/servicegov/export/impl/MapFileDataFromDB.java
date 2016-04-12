package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
@Service
@Transactional
public class MapFileDataFromDB {
    @Autowired
    ServiceInvokeDAOImpl serviceInvokeDAO;
    @Autowired
    InterfaceInvokeDAOImpl interfaceInvokeDAO;
    @Autowired
    IdaDAOImpl idaDAO;
    @Autowired
    SDADAOImpl sdaDAO;
    @Autowired
    OperationDAOImpl operationDAO;


    /*获取场景下所有接口关系*/
    public List<ServiceInvoke> getInvokeByOperationPK(OperationPK pk){
        String hql = " from ServiceInvoke where serviceId=? and operationId=? and type=?";
        List<ServiceInvoke> list = serviceInvokeDAO.find(hql, pk.getServiceId(), pk.getOperationId(), Constants.INVOKE_TYPE_PROVIDER);
        return list;
    }

    /**
     * 根据接口报文头id获取指定名称的元素
     * @param headId
     * @param structName
     * @return
     */
    public Ida getHeadIda(String headId, String structName){
        String hql = " from Ida where headId = ? and structName = ?";
        Ida ida = idaDAO.findUnique(hql, headId, structName);
        return ida;
    }

    /**
     * 获取接口下指定名称的根元素
     * @param interfaceId
     * @param structName
     * @return
     */
    public Ida getIda(String interfaceId, String structName){
        String hql = " from Ida where interfaceId = ? and structName = ?";
        Ida ida = idaDAO.findUnique(hql, interfaceId, structName);
        return ida;
    }

    /**
     * 获取子节点
     * @param parentId
     * @return
     */
    public List<Ida> getIdas(String parentId){
        String hql = " from Ida where _parentId = ? order by seq asc";
        List<Ida> list = idaDAO.find(hql, parentId);
        return list;
    }
    /**
     * 获取接口所有报文头id
     * @param interfaceId
     * @return
     */
    public List<String> getInterfaceHeadIds(String interfaceId){
        String hql = "select headId from InterfaceHeadRelate where interfaceId = ?";
        List<String> list = interfaceInvokeDAO.findFree(hql, interfaceId);
        return list;
    }
    /**
     * 获取服务报文头指定名称的元素
     * @param serviceHeadId
     * @param structName
     * @return
     */
    public SDA getSDA(String serviceHeadId, String structName){
        String hql = " from SDA where serviceHeadId = ? and structName = ?";
        SDA sda = sdaDAO.findUnique(hql, serviceHeadId, structName);
        return sda;
    }
    public String[] getServiceHeadIds(String serviceId, String opeartionId){
        String hql = " select headId from Operation where serviceId = ? and operationId = ?";
        String headIds = sdaDAO.findUnique(hql, serviceId, opeartionId);
        if(StringUtils.isEmpty(headIds)){
            headIds = Constants.ServiceHead.DEFAULT_HEAD_ID;
        }
        String[] headIdArray = headIds.split("\\,");
        return headIdArray;
    }
    /**
     *获取
     * @param servceId
     * @param opeartionId
     * @param structName
     * @return
     */
    public SDA getSDA(String servceId, String opeartionId, String structName){
        String hql = " from SDA where serviceId = ? and operationId = ? and structName = ?";
        SDA sda = sdaDAO.findUnique(hql, servceId, opeartionId, structName);
        return sda;
    }

    public List<SDA> getSDAs(String sdaId){
        List<SDA> list = sdaDAO.findBy("parentId", sdaId);
        return list;
    }

    public Metadata getMetadata(String metadataId){
        String hql = " from Metadata where metadataId = ?";
        Metadata metadata = sdaDAO.findUnique(hql, metadataId);
        return metadata;
    }

    public Operation getOperation(String serviceId, String operationId){
        return operationDAO.getBySO(serviceId, operationId);
    }
}
