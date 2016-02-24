package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetadataMappingDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetadataMapping;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service
@Transactional
public class MetadataMappingServiceImpl extends AbstractBaseService<MetadataMapping, String>{
    @Autowired
    private MetadataMappingDAOImpl mappingDAO;

    @Override
    public HibernateDAO<MetadataMapping, String> getDAO() {
        return mappingDAO;
    }
}
