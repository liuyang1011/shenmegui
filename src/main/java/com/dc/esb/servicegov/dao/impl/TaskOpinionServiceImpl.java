package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.TaskOpinion;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lmd on 2016/1/27.
 */
@Service
@Transactional
public class TaskOpinionServiceImpl extends AbstractBaseService<TaskOpinion, Long>{

    @Autowired
    private TaskOpinionDAOImpl TaskOpinionDAO;
    @Override
    public HibernateDAO<TaskOpinion,Long> getDAO() {
        return TaskOpinionDAO;
    }

    public TaskOpinion getEntiyById(long taskId){
        return TaskOpinionDAO.getEntity(taskId);
    }

    public List<TaskOpinion> getOpinionByProId(String proID){
        long proInsId = Long.valueOf(proID).longValue();
        String hql = "from TaskOpinion  where processInstanceId = ?";
        Session session = getDAO().getSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,proInsId);
        return query.list();
    }

}