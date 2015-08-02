package com.dc.esb.servicegov.dao.impl;

import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.entity.jsonObj.ServiceInvokeJson;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.dom4j.io.SAXEventRecorder;
import org.springframework.stereotype.Repository;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.BaseLineVersionHisMapping;
import com.dc.esb.servicegov.entity.OperationHis;
import com.dc.esb.servicegov.entity.ServiceInvoke;

@Repository
public class ServiceInvokeDAOImpl  extends HibernateDAO<ServiceInvoke, String> {
	public List<?> getBLInvoke(String baseId) {
        String hql = "select new "+ ServiceInvokeJson.class.getName()+"(invoke) from "+ ServiceInvoke.class.getName() + " as invoke where invoke.invokeId in  (select si.invokeId from " +ServiceInvoke.class.getName() + " as si, "
               +OperationHis.class.getName()+ " as oh where oh.versionHis.autoId in (select bvhm.versionHisId from " + BaseLineVersionHisMapping.class.getName() + " as bvhm where bvhm.baseLineId=?)" +
                "	and si.serviceId = oh.serviceId and si.operationId = oh.operationId)";
        List<?> list = find(hql, baseId);
        return list;
    }

    public void updateBySO(String serviceId, String operationId){
        String hql = " update " + ServiceInvoke.class.getName() + " set serviceId = null, operationId=null where serviceId = ? and operationId = ?";
        super.exeHql(hql, serviceId, operationId  );
    }

    /*
     * @param serviceId
     * @param operationId
     * @return
     */
    public List<?> findJsonBySO(String serviceId, String operationId){
//        String hql = " select new com.dc.esb.servicegov.entity.jsonObj.ServiceInvokeJson("+
//                " s.invokeId, s.systemId, s.isStandard, s.serviceId, s.operationId, s.interfaceId, s.type, s.desc, s.remark,"+
//                " s.interfaceId, s.system.systemChineseName)"+
//                " from "+ ServiceInvoke.class.getName()+" as s " +
//                "where s.serviceId=? and s.operationId=? ";
        String hql = "select new "+ ServiceInvokeJson.class.getName()+"(s) from "+ ServiceInvoke.class.getName()+" as s where s.serviceId=? and s.operationId=? ";
        List<?> list = super.find(hql, serviceId, operationId );
        return list;
    }
    /**
     * 根据消费者查找提供者，或者根据提供者查找消费者
     */
    public ServiceInvoke getByOtherType(String invokeId){
        ServiceInvoke serviceInvoke = this.findUnique("invokeId", invokeId);
        if(StringUtils.isNotEmpty(serviceInvoke.getServiceId()) && StringUtils.isNotEmpty(serviceInvoke.getOperationId()) ){
            String type = serviceInvoke.getType();
            if(StringUtils.isNotEmpty(type)){
                if(type.equals(Constants.INVOKE_TYPE_CONSUMER)){
                    type = Constants.INVOKE_TYPE_PROVIDER;
                }
                if(type.equals(Constants.INVOKE_TYPE_PROVIDER)){
                    type = Constants.INVOKE_TYPE_CONSUMER;
                }
                String hql = " from " + ServiceInvoke.class.getName() + " as si where si.type = ? and si.serviceId = ? and si.operationId = ?";
                List<ServiceInvoke> list = this.find(hql, type, serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
                if(list.size() > 0){
                    return list.get(0);
                }
            }
        }
        return null;
    }
}