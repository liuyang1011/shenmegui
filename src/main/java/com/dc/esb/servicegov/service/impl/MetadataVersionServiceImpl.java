package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetadataVersionDaoImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetadataVersion;
import com.dc.esb.servicegov.service.MetadataVersionService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/12/31.
 */
@Service
@Transactional
public class MetadataVersionServiceImpl extends AbstractBaseService<MetadataVersion, String> implements MetadataVersionService{
    @Autowired
    private MetadataVersionDaoImpl metadataVersionDao;
    @Override
    public HibernateDAO<MetadataVersion, String> getDAO() {
        return null;
    }
}
