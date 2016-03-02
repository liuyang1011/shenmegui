package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.ServiceIdentifyService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceIdentifyServiceImpl extends AbstractBaseService<Interface, String> implements ServiceIdentifyService {

    private static final Log log = LogFactory.getLog(ServiceIdentifyServiceImpl.class);

    @Autowired
    private InterfaceDAOImpl interfaceDAOImpl;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeServiceImpl;
    @Autowired
    private SystemServiceImpl systemService;
    @Autowired
    private InterfaceDAOImpl interfaceDAO;
    @Autowired
    private ServiceInvokeDAOImpl serviceInvokeDAO;
    public boolean judgeByMetadataId(String interfaceId){
        //查找服务场景列表
        long count =getByMetadataIdCount(interfaceId);
        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean uniqueServiceId(String serviceId) {
       boolean flag=false;
        String hql = "select s.serviceId from Service as s where s.serviceId = '"+serviceId+ "'";
        int size=serviceInvokeServiceImpl.findBy(hql).size();
        if(size>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean uniqueOperationId(String serviceId, String operationId) {
        boolean flag=false;
        String hql = "select o.operationId from Operation as o where o.serviceId = '"+serviceId+ "' and o.operationId = '"+operationId+"'";
        int size=serviceInvokeServiceImpl.findBy(hql).size();
        if(size>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public void changeInterfaceState(String interfaceId, String state) {
        String date=DateUtils.format(new Date());
        String userName = (String) SecurityUtils.getSubject().getPrincipal();

        StringBuffer hql2=new StringBuffer("update Interface set status='"+state+"', optDate='"+date+"',optUser='"+userName+"' where interfaceId = ? ");
        interfaceDAO.exeHql(hql2.toString(),interfaceId);
    }

    @Override
    public List<Object> getSystemId(String serviceId, String operationId) {
        String hql = "select si.systemId from ServiceInvoke as si where si.serviceId = '"+serviceId+ "' and operationId = '"+operationId+"' and si.type = '"+Constants.INVOKE_TYPE_PROVIDER+"'";
        List<Object> list=serviceInvokeDAO.findBy(hql);
        return list;
    }

    public long getByMetadataIdCount(String interfaceId){
        String hql = "select count(*) from ServiceInvoke as si where si.interfaceId = ? ";
        Long count = interfaceDAOImpl.findUnique(hql, interfaceId);
        return count;
    }
    /**
     *
     * @return
     */
    @Override
    public HibernateDAO<Interface, String> getDAO() {
        return interfaceDAOImpl;
    }
}
