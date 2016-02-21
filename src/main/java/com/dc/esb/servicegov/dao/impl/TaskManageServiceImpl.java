package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.impl.ProcessContextDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.TaskManage;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmd on 2016/1/27.
 */
@Service
@Transactional
public class TaskManageServiceImpl extends AbstractBaseService<TaskManage, String>{

    @Autowired
    private TaskManageDAOImpl TaskManageDAO;
    @Override
    public HibernateDAO<TaskManage, String> getDAO() {
        return TaskManageDAO;
    }

}
