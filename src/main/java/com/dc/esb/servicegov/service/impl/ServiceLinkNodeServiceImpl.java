package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.ServiceLinkNodeDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.ServiceLinkNode;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincentfxz on 15/9/7.
 */
@Service
@Transactional
public class ServiceLinkNodeServiceImpl extends AbstractBaseService<ServiceLinkNode, String> {

    @Autowired
    private ServiceLinkNodeDAOImpl serviceLinkNodeDAO;

    @Override
    public HibernateDAO<ServiceLinkNode, String> getDAO() {
        return serviceLinkNodeDAO;
    }
}
