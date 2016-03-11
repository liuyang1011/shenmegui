package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.AttFuncParamDAOImpl;
import com.dc.esb.servicegov.dao.impl.AttFunctionDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.AttFuncParam;
import com.dc.esb.servicegov.entity.AttFunction;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
@Transactional
public class AttFuncParamServiceImpl extends AbstractBaseService<AttFuncParam, String> {
    private static final Log log = LogFactory.getLog(AttFuncParamServiceImpl.class);
     @Autowired
     AttFuncParamDAOImpl dao;

    @Override
    public HibernateDAO<AttFuncParam, String> getDAO() {
        return dao;
    }


}
