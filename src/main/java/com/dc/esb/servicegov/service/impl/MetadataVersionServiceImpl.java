package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetadataVersionDaoImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.MetadataVersion;
import com.dc.esb.servicegov.service.MetadataVersionService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/31.
 */
@Service
@Transactional
public class MetadataVersionServiceImpl extends AbstractBaseService<MetadataVersion, String> implements MetadataVersionService{
    final static String initVersionNo = "V1.0.0";
    @Autowired
    private MetadataVersionDaoImpl metadataVersionDao;
    @Override
    public HibernateDAO<MetadataVersion, String> getDAO() {
        return metadataVersionDao;
    }

    public String getLastVersion(){
        String hql = " from " + MetadataVersion.class.getName() + " as m order by  m.optDate desc ";
        Query query = metadataVersionDao.getSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(1);

        List<MetadataVersion> list = query.list();
        if(null == list || list.size() == 0){
            return initVersionNo;
        }
        return list.get(0).getVersionNo();
    }
    public String genderHql(Map<String, String[]> values){
        String hql = "";
        if(values != null && values.size() > 0){
            for(String key:values.keySet()){
                if(key.equals("metadataName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and upper(a.metadataName) like upper('%" + values.get(key)[0] + "%') ";
                    }
                }
                if(key.equals("startDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optDate >'" + values.get(key)[0] + "' ";
                    }
                }
                if(key.equals("endDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optDate <'" + values.get(key)[0] + " 23:59:59' ";
                    }
                }
            }
        }
        return hql;
    }


    public long queryCount(Map<String, String[]> values){
        String hql = "select count(*) from MetadataVersion a where 1=1 ";
        hql += genderHql(values);
        return (Long)metadataVersionDao.findUnique(hql);
    }
    public List<MetadataVersion> queryByCondition(Map<String, String[]> values, Page page){
        String hql = " from MetadataVersion a where 1=1 ";
        hql += genderHql(values);
        hql += " order by optDate desc";
        return metadataVersionDao.findBy(hql, page, new ArrayList<SearchCondition>());
    }

    public MetadataVersion getBefore(String date){
        String hql = " from MetadataVersion a where optDate < ? order by optDate desc";
        List<MetadataVersion> list = metadataVersionDao.find(hql, date);
        if(null != list && 0 < list.size()){
            return list.get(0);
        }
        return null;
    }
}
