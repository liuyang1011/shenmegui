package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class MetaTypeDAOImpl extends HibernateDAO<MetaType, String> {
    @Autowired
    private MetadataDAOImpl metadataDAO;

    /**
     * 根据ID删除数据库中所有与之相关的数据
     * @param id
     */
    public void deleteAnywhere(String id){
        MetaType metaType = findUniqueBy("id", id);
        if(null != metaType){
            String name = metaType.getName();
            metadataDAO.setMetaType(name);
        }
    }
}
