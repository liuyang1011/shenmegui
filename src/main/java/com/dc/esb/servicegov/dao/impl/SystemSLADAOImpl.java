package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.SystemSLA;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2016/3/8 0008.
 */
@Repository
@Transactional
public class SystemSLADAOImpl extends HibernateDAO<SystemSLA, String> {
}
