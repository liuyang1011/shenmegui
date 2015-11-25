package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.service.ConfigExportService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;

/**
 * Created by Administrator on 2015/11/24.
 */
public class ConfigExportServiceImpl  extends AbstractBaseService implements ConfigExportService{

    @Override
    public HibernateDAO getDAO() {
        return null;
    }
    //生成服务场景的配置文件
    public boolean generateBy(String path , OperationPK operationPK){

        return true;
    }

}
