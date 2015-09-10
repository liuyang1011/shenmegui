package com.dc.esb.servicegov.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.InterfaceInvoke;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.InterfaceExVO;
import com.dc.esb.servicegov.vo.OperationExpVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.InterfaceDAOImpl;
import com.dc.esb.servicegov.entity.Interface;
import com.dc.esb.servicegov.service.InterfaceService;
@Service
@Transactional
public class InterfaceServiceImpl extends AbstractBaseService<Interface, String> implements InterfaceService{
	@Autowired
	private InterfaceDAOImpl interfaceDAOImpl;

	/**
	 * TODO 这里为什么要这么做
	 * @return
	 */
	@Override
	public HibernateDAO<Interface, String> getDAO() {
		return interfaceDAOImpl;
	}

	public Interface getInterfaceById(String hql,String interfaceId){

		List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
		SearchCondition search = new SearchCondition();
		search.setField("interfaceId");
		search.setFieldValue(interfaceId);
		searchConds.add(search);
		List<Interface> inters = interfaceDAOImpl.findBy(hql, searchConds);
		System.out.print(inters.size()+"========================================");
		Interface inter = inters.get(0);
		System.out.print(inter);
		return inter;
	}

	public List<Interface> getBySystemId(String systemId){
		String hql = "select distinct i from " + Interface.class.getName() + " as i , "+ ServiceInvoke.class.getName()
				+" as si where i.interfaceId = si.interfaceId and si.systemId = ?";
		List<Interface> list = interfaceDAOImpl.find(hql, systemId);
		return list;
	}
	public List<Interface> findByConditions(String condition){
		condition = "%" + condition + "%";
		StringBuffer hql = new StringBuffer("select t from Interface t where t.interfaceId like ?");
		hql.append(" or t.desc like ?");
		hql.append(" or t.interfaceName like ?");
		hql.append(" or t.ecode like ?");
		List<Interface> list = interfaceDAOImpl.find(hql.toString(), condition, condition, condition,condition);
		return list;
	}

	@Override
	public List<InterfaceExVO> queryByCondition(Map<String, String[]> values, Page page) {
		return new ArrayList<InterfaceExVO>();
	}
}
