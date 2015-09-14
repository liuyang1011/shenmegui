package com.dc.esb.servicegov.service.impl;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.impl.IdaDAOImpl;
import com.dc.esb.servicegov.dao.impl.IdaHISDAOImpl;
import com.dc.esb.servicegov.dao.impl.InterfaceHISDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.DateUtils;
import com.dc.esb.servicegov.vo.InterfaceExVO;
import com.dc.esb.servicegov.vo.OperationExpVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.InterfaceDAOImpl;
import com.dc.esb.servicegov.service.InterfaceService;
@Service
@Transactional
public class InterfaceServiceImpl extends AbstractBaseService<Interface, String> implements InterfaceService{
	private static final Log log = LogFactory.getLog(InterfaceServiceImpl.class);
	@Autowired
	private InterfaceDAOImpl interfaceDAOImpl;
	@Autowired
	private InterfaceHISDAOImpl interfaceHISDAO;
	@Autowired
	private IdaDAOImpl idaDAO;
	@Autowired
	private IdaHISDAOImpl idaHISDAO;
	@Autowired
	private VersionServiceImpl versionServiceImpl;

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

	/**
	 *
	 * @param interfaceIds 接口id用‘,’拼接的字符串
	 * @return
	 */
	@Override
	public boolean releaseBatch(String interfaceIds, String versionDesc) {
		if(StringUtils.isNotEmpty(interfaceIds)){
			String[] ids = interfaceIds.split("\\,");
			for(int i = 0; i < ids.length; i++){
				try {
					release(ids[i], versionDesc);
				}catch(Exception e){
					log.error(e,e);
					continue;
				}
			}
		}
		return true;
	}

	/**
	 * @param interfaceId 接口id
	 * @param versionDesc 版本描述
	 * @return
	 */
	public boolean release(String interfaceId, String versionDesc){
		//对于每一个接口备份interfaceHis，versionHis，idaHis,（interfaceHead,  protocolHis关系暂时不加入版本管理）
		Interface inter = interfaceDAOImpl.findUniqueBy("interfaceId", interfaceId);
		InterfaceHIS interfaceHis = new InterfaceHIS(inter);
		interfaceHISDAO.save(interfaceHis);//备份interfaceHis
		String versionHisId = versionServiceImpl.releaseVersion(inter.getVersionId(), interfaceHis.getAutoId(), versionDesc);//生成发布版本，返回versionHisId
		interfaceHis.setVersionHisId(versionHisId);
		interfaceHISDAO.save(interfaceHis);//关联接口历史版本
		String hql = " from " + Ida.class.getName() + " where interfaceId = ?";
		List<Ida> idas = idaDAO.find(hql, inter.getInterfaceId());
		for(int i = 0; i < idas.size(); i++){//生成ida历史
			IdaHIS idaHIS = new IdaHIS(idas.get(i));
			idaHIS.setInterfaceHisId(interfaceHis.getAutoId());
			idaHISDAO.save(idaHIS);
		}
		return true;
	}

	/**
	 * @param inter 接口
	 * @param addSave 新增/修改
	 */
	@Override
	public void save(Interface inter, boolean addSave){
		//新增操作
		if(addSave){
			String versionId = versionServiceImpl.addVersion(Constants.Version.TARGET_TYPE_INTERFACE, inter.getInterfaceId(),Constants.Version.TYPE_ELSE);
			inter.setVersionId(versionId);

		}
		else{//修改操作
			versionServiceImpl.editVersion(inter.getVersionId());
		}
		inter.setOptUser(SecurityUtils.getSubject().getPrincipal().toString());
		inter.setOptDate(DateUtils.format(new Date()));
		interfaceDAOImpl.save(inter);
	}
}
