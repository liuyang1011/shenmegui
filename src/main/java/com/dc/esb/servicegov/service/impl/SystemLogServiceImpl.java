package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.OperationLogDAOImpl;
import com.dc.esb.servicegov.dao.impl.OperationLogTypeDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.OperationLogType;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.vo.ReuseRateVO;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2015/8/18.
 */
@Service
@Transactional
public class SystemLogServiceImpl  extends AbstractBaseService<OperationLog,String> {
    @Autowired
    OperationLogDAOImpl operationLogDAO;
    @Autowired
    OperationLogTypeDAOImpl operationLogTypeDAO;
    @Override
    public HibernateDAO<OperationLog, String> getDAO() {
        return operationLogDAO;
    }

    public void insertLogType(OperationLogType operationLogType){
        operationLogTypeDAO.save(operationLogType);
    }

    public long getLogCount(Map<String, String[]> values) {
        String hql = "select count(*) from " + OperationLog.class.getName() + " o where 1=1";
        if(values.get("optUser") != null && values.get("optUser").length > 0){
            if (StringUtils.isNotEmpty(values.get("optUser")[0])){
                hql += " and o.optUser like '%" + values.get("optUser")[0]+"%'";
            }
        }
        if(values.get("startDate") != null && values.get("startDate").length > 0){
            if (StringUtils.isNotEmpty(values.get("startDate")[0])) {
                hql += " and o.optDate > '" + values.get("startDate")[0] + "' ";
            }
        }
        if(values.get("endDate") != null && values.get("endDate").length > 0){
            if (StringUtils.isNotEmpty(values.get("endDate")[0])) {
                hql += " and o.optDate < '" + values.get("endDate")[0] + "' ";
            }
        }
        long count = operationLogDAO.findUnique(hql);
        return count;
    }
    public List<OperationLog> getLogs(Map<String, String[]> values, Page page) {
        String hql = " from " + OperationLog.class.getName() + " o where 1=1";
        if(values.get("optUser") != null && values.get("optUser").length > 0){
            if (StringUtils.isNotEmpty(values.get("optUser")[0])){
                hql += " and o.optUser like '%" + values.get("optUser")[0]+"%'";
            }
        }
        if(values.get("startDate") != null && values.get("startDate").length > 0){
            if (StringUtils.isNotEmpty(values.get("startDate")[0])) {
                hql += " and o.optDate > '" + values.get("startDate")[0] + "' ";
            }
        }
        if(values.get("endDate") != null && values.get("endDate").length > 0){
            if (StringUtils.isNotEmpty(values.get("endDate")[0])) {
                hql += " and o.optDate < '" + values.get("endDate")[0] + "' ";
            }
        }
        List<OperationLog> list = operationLogDAO.find(hql, page);
        return list;
    }
}
