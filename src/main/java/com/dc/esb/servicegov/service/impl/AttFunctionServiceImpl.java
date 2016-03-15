package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.AttFunctionDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.AttFuncParam;
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

    public String genderHql(Map<String, String[]> values) {
        String hql = "";
        if (values != null && values.size() > 0) {
            for (String key : values.keySet()) {
                if (key.equals("name") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        try {
                            hql += " and a.name like '%" + URLDecoder.decode(values.get(key)[0], "utf-8") + "%' ";
                        } catch (UnsupportedEncodingException e) {
                            log.error(e, e);
                            log.error("中文名称转码错误！");
                        }
                    }
                }
                if (key.equals("funcName") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        hql += " and upper(a.funcName) like upper('%" + values.get(key)[0] + "%') ";
                    }
                }
                if (key.equals("des") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        hql += " and a.des like '%" + values.get(key)[0] + "%' ";
                    }
                }
            }
        }
        return hql;
    }

    public long queryCount(Map<String, String[]> values) {
        String hql = "select count(*) from AttFunction a where 1=1 ";
        hql += genderHql(values);
        return (Long) attFunctionDAO.findUnique(hql);
    }

    //关联categoryWord表，显示chineseWord
    public List<AttFunction> queryByCondition(Map<String, String[]> values, Page page) {
//        String hql = "select a,b.chineseWord from Metadata a, CategoryWord b where 1=1 and a.categoryWordId=b.englishWord ";
        String hql = "from AttFunction a where 1=1 ";
        hql += genderHql(values);
        hql += " order by a.id";
        List<AttFunction> list = attFunctionDAO.findBy(hql, page);
        for (AttFunction attFunction : list) {
            String hql2 = " from AttFuncParam where funcId = ? order by seq asc";
            List<AttFuncParam> params = attFunctionDAO.findFree(hql2, attFunction.getId());
            String paramStr = "";
            String paramNameStr = "";
            if (null != params && 0 < params.size()) {
                for (int i = 0; i < params.size(); i++) {
                    AttFuncParam param = params.get(i);
                    if (i != 0) {
                        paramNameStr += "," + param.getName();
                        paramStr += "," + param.getDefaultValue();
                    } else {
                        paramNameStr += param.getName();
                        paramStr += param.getDefaultValue();
                    }
                }
                paramStr = attFunction.getFuncName() + "(" + paramStr + ")";
            }else{
                paramStr = attFunction.getFuncName() + "()";
            }
            attFunction.setParams(paramStr);
            attFunction.setParamNames(paramNameStr);
        }
        return list;
    }
}
