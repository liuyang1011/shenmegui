package com.dc.esb.servicegov.dao.impl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import org.springframework.stereotype.Repository;
import com.dc.esb.servicegov.entity.Metadata;
@Repository
public class MetadataDAOImpl extends HibernateDAO<Metadata, String> {

    public boolean setMetaType(String str){
        String hql = "update Metadata set metatype = ? where metatype = ? ";
        boolean result = exeHql(hql,null,str);
        return result;
    }
}
