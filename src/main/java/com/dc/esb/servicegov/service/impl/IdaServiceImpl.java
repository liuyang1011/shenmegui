package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.IdaDAOImpl;
import com.dc.esb.servicegov.dao.impl.SDADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Ida;
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

	@Override
	public HibernateDAO<Ida, String> getDAO() {
		return idaDAOImpl;
	}

	public void deletes(String [] ids){
		for(String id:ids){
			idaDAOImpl.delete(id);
		}
	}

	@Override
	public void saveOrUpdate(Ida[] idas) {
		for(Ida ida:idas){
			if(StringUtils.isEmpty(ida.getHeadId())){
				ida.setHeadId(null);
			}
			idaDAOImpl.save(ida);
		}
	}

    @Override
    public boolean updateMetadataId(String metadataId, String id) {
        String hql = " update "+ Ida.class.getName() + " set metadataId = ? where id = ?";
        idaDAOImpl.batchExecute(hql, metadataId, id);
        return true;
    }

	public boolean deleteList(List<Ida> list){
		idaDAOImpl.delete(list);
		return true;
	}

	public List<IdaMappingBean> findIdaMappingBy(Map<String,String> map,String orderByProperties,String serviceId, String operationId){
		List<Ida> idas = findBy(map,orderByProperties);
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("serviceId",serviceId);
		map2.put("operationId",operationId);
		List<SDA> sda = sdadao.findBy(map2);
		List<IdaMappingBean> list = new ArrayList<IdaMappingBean>();
		for (int i = 0; i < idas.size(); i++) {
			if(null == idas.get(i).getMetadataId()){
				list.add(new IdaMappingBean(idas.get(i)));
				continue;
			}
			for (SDA per : sda){
				if(null == per.getMetadataId()) continue;
				if ( per.getMetadataId().equals(idas.get(i).getMetadataId())){
					list.add(new IdaMappingBean(idas.get(i),per));
				}
			}
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
}
