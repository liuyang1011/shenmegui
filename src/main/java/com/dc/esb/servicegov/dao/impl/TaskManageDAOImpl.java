package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.TaskManage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by lmd on 2016/1/27.
 */
@Repository
@Transactional
public class TaskManageDAOImpl extends HibernateDAO<TaskManage, String>{
}
