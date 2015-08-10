package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.InterfaceInvokeDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.InterfaceInvoke;
import com.dc.esb.servicegov.service.InterfaceInvokeService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiangqi on 2015/8/10.
 */
@Service
@Transactional
public class InterfaceInvokeServiceImpl extends AbstractBaseService<InterfaceInvoke, String> implements InterfaceInvokeService{

    @Autowired
    private InterfaceInvokeDAOImpl interfaceInvokeDAOImpl;
    @Override
    public HibernateDAO<InterfaceInvoke, String> getDAO() {
        return interfaceInvokeDAOImpl;
    }

}
