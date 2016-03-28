package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.SLATemplateDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.SLATemplate;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.SLATemplateDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.SLATemplate;
import com.dc.esb.servicegov.service.support.BaseService;

@Service
@Transactional
public class SLATemplateServiceImpl extends AbstractBaseService<SLATemplate, String> {
	
	@Autowired
	private SLATemplateDAOImpl slaTemplateDAOImpl;

	@Override
	public HibernateDAO<SLATemplate, String> getDAO() {
		return slaTemplateDAOImpl;
	}
	public boolean uniqueValid1(String templateNo) {
		SLATemplate entity = findUniqueBy("templateNo",templateNo);
		if (entity != null) {
			return false;
		}
		return true;
	}

	public boolean uniqueValid3(String templateName) {
		SLATemplate entity = findUniqueBy("templateName",templateName);
		if (entity != null) {
			return false;
		}
		return true;
	}


}
