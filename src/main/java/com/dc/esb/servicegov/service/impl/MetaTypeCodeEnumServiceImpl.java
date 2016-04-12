package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.MetaTypeCodeEnumDaoImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.MetaTypeCodeEnum;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * Created by xhx113 on 2016/3/23.
 */
@Service
@Transactional
public class MetaTypeCodeEnumServiceImpl extends AbstractBaseService<MetaTypeCodeEnum, String> {
    private static final Log log = LogFactory.getLog(MetaTypeCodeEnumServiceImpl.class);
    @Autowired
    private MetaTypeCodeEnumDaoImpl metaTypeCodeEnumDao;
    @Override
    public HibernateDAO<MetaTypeCodeEnum, String> getDAO() {
        return metaTypeCodeEnumDao;
    }

    /**
     * 根据中文名称拿到数据进行处理
     * @param chName
     * @return
     */
    public String getCodeData(String chName){
        List<MetaTypeCodeEnum> metaTypeCodeEnums = metaTypeCodeEnumDao.getListByStaChName(chName);
        String codeData = "";
        if(null != metaTypeCodeEnums){
            for(MetaTypeCodeEnum codeEnum:metaTypeCodeEnums){
                codeData += codeEnum.getCodeValue()+"："+codeEnum.getCodeMeaning();
            }
        }else{
            log.error("根据标准中文名称["+chName+"]获取数据失败！");
        }
        return codeData;
    }

    /**
     * 根据中文名称获得整个实体
     * @param chName
     * @return
     */
    public List<MetaTypeCodeEnum> getAll(String chName){
        return metaTypeCodeEnumDao.getListByStaChName(chName);
    }

}



















