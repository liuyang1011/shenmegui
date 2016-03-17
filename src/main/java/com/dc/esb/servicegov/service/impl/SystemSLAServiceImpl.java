package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.InterfaceInvokeDAOImpl;
import com.dc.esb.servicegov.dao.impl.SystemSLADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.SystemSLAService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
@org.springframework.stereotype.Service
@Transactional
public class SystemSLAServiceImpl extends AbstractBaseService<SystemSLA, String> implements SystemSLAService {
    @Autowired
    private SystemSLADAOImpl systemSLADAOImpl;


    @Override
    public HibernateDAO<SystemSLA, String> getDAO() {
        return systemSLADAOImpl;
    }
}
