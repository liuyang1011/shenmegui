package com.dc.esb.servicegov.dao.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetaTypeCodeEnum;
import org.springframework.stereotype.Repository;
import java.util.*;
/**
 * Created by xhx113 on 2016/3/21.
 */
@Repository
public class MetaTypeCodeEnumDaoImpl extends HibernateDAO<MetaTypeCodeEnum, String> {
    /**
     * 根据中文名和代码值获得唯一的对象，不排除重复的情况
     * @param name
     * @param code
     * @return
     */
    public List<MetaTypeCodeEnum> getCodeEnumByNameAndCode(String name,String code){
        String hql = "from MetaTypeCodeEnum a where a.standardChName = ? and a.codeValue = ?";
        List<MetaTypeCodeEnum> codeEnums = find(hql,name,code);
        return codeEnums;
    }

    /**
     * 根据中文名获取对象
     * @param name
     * @return
     */
    public List<MetaTypeCodeEnum> getListByStaChName(String name){
        String hql = "from MetaTypeCodeEnum a where a.standardChName = ?";
        List<MetaTypeCodeEnum> codeEnums = find(hql,name);
        return codeEnums;
    }
}
