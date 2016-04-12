package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetaTypeDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetaType;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * Created by xhx113 on 2016/3/4.
 */
@Service
@Transactional
public class MetaTypeServiceImpl extends AbstractBaseService<MetaType, String> {
    private static final Log log = LogFactory.getLog(MetaTypeServiceImpl.class);
    @Autowired
    private MetaTypeDAOImpl metaTypeDAO;

    @Override
    public HibernateDAO<MetaType, String> getDAO() {
        return metaTypeDAO;
    }

    /**
     * 新增一个元数据
     *
     * @param metaType
     * @return
     */
    public boolean addMetaType(MetaType metaType) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        metaType.setOptUser(userName);
        metaType.setOptDate(DateUtils.format(new Date()));
        metaTypeDAO.save(metaType);
        return true;
    }

    /**
     * 唯一性的认证：元数据英文名称
     *
     * @param metaType
     * @return
     */
    public boolean uniqueValName(String metaType) {
        MetaType entity = findUniqueBy("name", metaType);
        if (entity != null) {
            return false;
        }
        return true;
    }

    /**
     * 批量删除
     * @param metaTypeIds
     * @return
     */
    public boolean deleteMetaTypes(String metaTypeIds){
        String[] ids = metaTypeIds.split("\\,");
        for(String id:ids){
            if(id==null||"".equals(id)){
                return false;
            }
            deleteById(id);
            metaTypeDAO.deleteAnywhere(id);//删除所有表中的元数据规范
        }
        return true;
    }
}














