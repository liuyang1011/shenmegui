package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.AttFunctionDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.AttFunction;
import com.dc.esb.servicegov.entity.CategoryWord;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
@Transactional
public class AttFunctionServiceImpl extends AbstractBaseService<AttFunction, String> {
    private static final Log log = LogFactory.getLog(AttFunctionServiceImpl.class);
            @Autowired
                    AttFunctionDAOImpl attFunctionDAO;

    @Override
    public HibernateDAO<AttFunction, String> getDAO() {
        return attFunctionDAO;
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
                if(key.equals("chineseName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        try {
                            hql += " and a.chineseName like '%" + URLDecoder.decode(values.get(key)[0], "utf-8") + "%' ";
                        }catch (UnsupportedEncodingException e) {
                            log.error(e,e);
                            log.error("中文名称转码错误！");
                        }
                    }
                }
                if(key.equals("metadataId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and upper(a.metadataId) like upper('%" + values.get(key)[0] + "%') ";
                    }
                }
                if(key.equals("metadataAlias") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.metadataAlias like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("status") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.status like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("version") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.version like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("optUser") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optUser like '%" + values.get(key)[0] + "%' ";
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

                if(key.equals("categoryWordId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.categoryWordId ='" + values.get(key)[0] + "' ";
                    }
                }
                if(key.equals("dataCategory") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        try {
                            hql += " and a.dataCategory like '%" + URLDecoder.decode(values.get(key)[0], "utf-8") + "%' ";
                        }catch (UnsupportedEncodingException e) {
                            log.error(e,e);
                            log.error("数据项分类转码错误！");
                        }
                    }
                }
            }
        }
        return hql;
    }

    public long queryCount(Map<String, String[]> values){
        String hql = "select count(*) from AttFunction a where 1=1 ";
        hql += genderHql(values);
        return (Long)attFunctionDAO.findUnique(hql);
    }
    //关联categoryWord表，显示chineseWord
    public List<AttFunction> queryByCondition(Map<String, String[]> values, Page page){
//        String hql = "select a,b.chineseWord from Metadata a, CategoryWord b where 1=1 and a.categoryWordId=b.englishWord ";
        String hql = "from AttFunction a where 1=1 ";
        hql += genderHql(values);
        hql += " order by a.id";
        List<AttFunction> list = attFunctionDAO.findBy(hql, page);
        for(AttFunction attFunction : list){

        }
        return list;
    }
}
