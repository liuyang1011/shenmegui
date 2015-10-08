package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.IdaDAOImpl;
import com.dc.esb.servicegov.dao.impl.InterfaceDAOImpl;
import com.dc.esb.servicegov.dao.impl.SDADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Ida;
import com.dc.esb.servicegov.entity.Interface;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.service.IdaService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.drools.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IdaServiceImpl extends AbstractBaseService<Ida, String> implements IdaService{
	@Autowired
	private IdaDAOImpl idaDAOImpl;
	@Autowired
	private SDADAOImpl sdadao;
	@Autowired
	private InterfaceDAOImpl interfaceDAO;

	@Autowired
	private VersionServiceImpl versionServiceImpl;

	@Override
	public HibernateDAO<Ida, String> getDAO() {
		return idaDAOImpl;
	}

	public void deletes(String [] ids){
		boolean editFlag = true;
		for(String id:ids){
			if(editFlag){
				Ida ida = idaDAOImpl.findUniqueBy("id", id);
				String interfaceId = ida.getInterfaceId();
				if(org.apache.commons.lang.StringUtils.isNotEmpty(interfaceId)){
					Interface inter = interfaceDAO.findUniqueBy("interfaceId", interfaceId);
					//更新版本，只更新一次
					versionServiceImpl.editVersion(inter.getVersionId());
					editFlag = false;
				}
			}
			idaDAOImpl.delete(id);
		}
	}

	@Override
	public void saveOrUpdate(Ida[] idas) {
		boolean editFlag = true;
		for(Ida ida:idas){
			if(StringUtils.isEmpty(ida.getHeadId())){
				ida.setHeadId(null);
			}
			idaDAOImpl.save(ida);
			if(editFlag){

				String interfaceId = ida.getInterfaceId();
				if(org.apache.commons.lang.StringUtils.isNotEmpty(interfaceId)){
					Interface inter = interfaceDAO.findUniqueBy("interfaceId", interfaceId);
					//更新版本，只更新一次
					versionServiceImpl.editVersion(inter.getVersionId());
					editFlag = false;
				}
			}
		}

	}

	/**
	 * 前端唯一性验证
	 * @param structName
	 * @return
	 */
	@Override
	public boolean uniqueValid(String structName) {
		Ida entity = findUniqueBy("structName",structName);
		if (entity != null) {
			return false;
		}
		return true;
	}

	@Override
	public List findIdas(Map<String,String> reqMap, String orderStr){
		List<Ida> idas = findBy(reqMap, orderStr);
		List<IdaMappingBean> beans = new ArrayList<IdaMappingBean>();
		for (int i = 0; i < idas.size(); i++) {
			String metadataId = idas.get(i).getMetadataId();
//			 sdadao.findUniqueBy("metadataId",metadataId);
		}
		return null;
	}

    @Override
    public boolean updateMetadataId(String metadataId, String id) {
        String hql = " update "+ Ida.class.getName() + " set metadataId = ? where id = ?";
        idaDAOImpl.batchExecute(hql, metadataId, id);
		Ida ida = idaDAOImpl.findUniqueBy("id", id);
		if(ida != null){//做一次版本更新
			String interfaceId = ida.getInterfaceId();
			if(org.apache.commons.lang.StringUtils.isNotEmpty(interfaceId)) {
				Interface inter = interfaceDAO.findUniqueBy("interfaceId", interfaceId);
				//更新版本，只更新一次
				versionServiceImpl.editVersion(inter.getVersionId());
			}
		}
        return true;
    }

	public boolean deleteList(List<Ida> list){
		idaDAOImpl.delete(list);
		boolean editFlag = true;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i) != null){
				Ida ida = list.get(i);
				if(ida != null) {
					String interfaceId = ida.getInterfaceId();
					if (org.apache.commons.lang.StringUtils.isNotEmpty(interfaceId)) {
						Interface inter = interfaceDAO.findUniqueBy("interfaceId", interfaceId);
						//更新版本，只更新一次
						versionServiceImpl.editVersion(inter.getVersionId());
						editFlag = false;
					}
				}
			}
			if(!editFlag) break;
		}

		return true;
	}

	public List<IdaMappingBean> findIdaMappingBy(Map<String,String> map,String orderByProperties,String serviceId, String operationId){
		List<Ida> idas = findBy(map,orderByProperties);
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("serviceId",serviceId);
		map2.put("operationId", operationId);
		List<SDA> sda = sdadao.findBy(map2);
		List<IdaMappingBean> list = new ArrayList<IdaMappingBean>();
		for (int i = 0; i < idas.size(); i++) {
//			if(null == idas.get(i).getMetadataId()){
				list.add(new IdaMappingBean(idas.get(i)));
//				continue;
//			}
			/*for (SDA per : sda){
				if(null == per.getMetadataId()) continue;
				if ( per.getMetadataId().equals(idas.get(i).getMetadataId())){
					list.add(new IdaMappingBean(idas.get(i),per));
				}
			}*/
		}
		return list;
	}

	public static class IdaMappingBean{
		private String id;
		private String structName;
		private String structAlias;

		private String metadataId;

		private int seq;

		private String type;

		private String scale;

		private String length;

		private String required;

		private String _parentId;

		private String interfaceId;

		private String potUser;

		private String potDate;

		private String headId;

		private String version;

		private String remark;

		//SDA
		private String SDAStructAlias;
		private String SDAType;
		private String SDARemark;
		private String SDAConstraintAlias;

		public IdaMappingBean(Ida ida,SDA sda){
			setId(ida.getId());
			setStructName(ida.getStructName());
			setStructAlias(ida.getStructAlias());
			setMetadataId(ida.getMetadataId());
			setSeq(ida.getSeq());
			if(ida.getType() != null){
				if(ida.getLength() != null){
					setType(ida.getType() + "("+ida.getLength()+")");
				} else {
					setType(ida.getType());
				}
			}

			setScale(ida.getScale());
			setLength(ida.getLength());
			setRequired(ida.getRequired());
			set_parentId(ida.get_parentId());
			setInterfaceId(ida.getInterfaceId());
			setPotUser(ida.getPotUser());
			setPotDate(ida.getPotDate());
			setHeadId(ida.getHeadId());
			setVersion(ida.getVersion());
			setRemark(ida.getRemark());
			setSDAStructAlias(sda.getStructAlias());
			setSDAConstraintAlias(sda.getConstraint());
			if(sda.getType() != null){
				if(sda.getLength() != null){
					setSDAType(sda.getType() + "(" + sda.getLength() + ")");
				} else {
					setSDAType(sda.getType());
				}
			}
			setSDARemark(sda.getRemark());
		}

		public IdaMappingBean(Ida ida){
			setId(ida.getId());
			setStructName(ida.getStructName());
			setStructAlias(ida.getStructAlias());
			setMetadataId(ida.getMetadataId());
			setSeq(ida.getSeq());
			if(ida.getType() != null){
				if(ida.getLength() != null){
					setType(ida.getType() + "("+ida.getLength()+")");
				} else {
					setType(ida.getType());
				}
			}
			setScale(ida.getScale());
			setLength(ida.getLength());
			setRequired(ida.getRequired());
			set_parentId(ida.get_parentId());
			setInterfaceId(ida.getInterfaceId());
			setPotUser(ida.getPotUser());
			setPotDate(ida.getPotDate());
			setHeadId(ida.getHeadId());
			setVersion(ida.getVersion());
			setRemark(ida.getRemark());
		}

		public String getSDAConstraintAlias() {
			return SDAConstraintAlias;
		}

		public void setSDAConstraintAlias(String SDAConstraintAlias) {
			this.SDAConstraintAlias = SDAConstraintAlias;
		}

		public String getSDAStructAlias() {
			return SDAStructAlias;
		}

		public void setSDAStructAlias(String SDAStructAlias) {
			this.SDAStructAlias = SDAStructAlias;
		}

		public String getSDAType() {
			return SDAType;
		}

		public void setSDAType(String SDAType) {
			this.SDAType = SDAType;
		}

		public String getSDARemark() {
			return SDARemark;
		}

		public void setSDARemark(String SDARemark) {
			this.SDARemark = SDARemark;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getStructName() {
			return structName;
		}

		public void setStructName(String structName) {
			this.structName = structName;
		}

		public String getStructAlias() {
			return structAlias;
		}

		public void setStructAlias(String structAlias) {
			this.structAlias = structAlias;
		}

		public String getMetadataId() {
			return metadataId;
		}

		public void setMetadataId(String metadataId) {
			this.metadataId = metadataId;
		}

		public int getSeq() {
			return seq;
		}

		public void setSeq(int seq) {
			this.seq = seq;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getScale() {
			return scale;
		}

		public void setScale(String scale) {
			this.scale = scale;
		}

		public String getLength() {
			return length;
		}

		public void setLength(String length) {
			this.length = length;
		}

		public String getRequired() {
			return required;
		}

		public void setRequired(String required) {
			this.required = required;
		}

		public String get_parentId() {
			return _parentId;
		}

		public void set_parentId(String _parentId) {
			this._parentId = _parentId;
		}

		public String getInterfaceId() {
			return interfaceId;
		}

		public void setInterfaceId(String interfaceId) {
			this.interfaceId = interfaceId;
		}

		public String getPotUser() {
			return potUser;
		}

		public void setPotUser(String potUser) {
			this.potUser = potUser;
		}

		public String getPotDate() {
			return potDate;
		}

		public void setPotDate(String potDate) {
			this.potDate = potDate;
		}

		public String getHeadId() {
			return headId;
		}

		public void setHeadId(String headId) {
			this.headId = headId;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}

	/**
	 * 将一个节点上移
	 */
	public boolean moveUp(String id) {
		Ida ida = idaDAOImpl.findUnique(" from Ida where id=?", id);
		String hql = " from Ida where _parentId = ? order by seq asc";
		List<Ida> list = idaDAOImpl.find(hql, ida.get_parentId());//查询兄弟节点
		int position = list.indexOf(ida);
		if(position == 0){
			return false;
		}
		for (int i = 0; i < list.size(); i++) {
			Ida node = list.get(i);
			if ( i == position) {
				//于之前的节点seq互换
				Ida before = list.get(i - 1);
				int seq = node.getSeq();
				ida.setSeq(before.getSeq());
				before.setSeq(seq);

				idaDAOImpl.save(ida);
				return true;
			}
			else if(i < position){
				node.setSeq(node.getSeq() - 1);//所有当前节点之前的节点，seq-1；
			}
		}

		return false;
	}

	public boolean moveDown(String id) {
		Ida ida = idaDAOImpl.findUnique(" from Ida where id=?", id);
		String hql = " from Ida where _parentId = ? order by seq asc";
		List<Ida> list = idaDAOImpl.find(hql, ida.get_parentId());//查询兄弟节点
		int position = list.indexOf(ida);
		if(position == (list.size()-1)){
			return false;
		}
		for (int i = list.size() -1; i >= 0; i--) {
			Ida node = list.get(i);
			if ( i == position) {
				//于之前的节点seq互换
				Ida before = list.get(i + 1);
				int seq = node.getSeq();
				ida.setSeq(before.getSeq());
				before.setSeq(seq);

				idaDAOImpl.save(ida);
				return true;
			}
			else if(i > position){
				node.setSeq(node.getSeq() + 1);//所有当前节点之前的节点，seq-1；
			}
		}

		return false;
	}
}
