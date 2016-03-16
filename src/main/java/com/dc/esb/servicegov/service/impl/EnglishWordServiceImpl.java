package com.dc.esb.servicegov.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.EnglishWordDAOImpl;
import com.dc.esb.servicegov.entity.EnglishWord;
import com.dc.esb.servicegov.service.EnglishWordService;

@Service
@Transactional
public class EnglishWordServiceImpl extends AbstractBaseService<EnglishWord, String> implements EnglishWordService {

	@Autowired
	private EnglishWordDAOImpl englishWordDAOImpl;

	@Override
	public HibernateDAO getDAO() {
		return englishWordDAOImpl;
	}

	/**
	 * 前端唯一性验证
	 * @param englishWord
	 * @return
	 */
	public boolean uniqueValid(String englishWord) {
		EnglishWord entity = findUniqueBy("englishWord",englishWord);
		if (entity != null) {
			return false;
		}
		return true;
	}

	public List<EnglishWord> getId(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		return englishWordDAOImpl.findBy(params);
	}

}
