package com.dc.esb.servicegov.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SLA_TEMPLATE")
public class SLATemplate implements Serializable {

	private static final long serialVersionUID = 1L;
   
	@Id
	@Column(name = "SLA_TEMPLATE_ID")
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String slaTemplateId;
	
	@Column(name="DESCRIPTION")
	private String desc;
	
	@Column(name="TEMPLATE_NAME")
	private String templateName;

	@Column(name="UPDATE_TIME")
	private String updateTime;

	@Column(name="CREAT_USER")
	private String creatUser;

	@Column(name="UPDATE_USER")
	private String updateUser;

	@Column(name="TEMPLATE_NO")
	private String templateNo;

	public String getTemplateNo() {return templateNo;}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}

	public String getUpdateUser() {return updateUser;}

	public void setUpdateUser(String updateUser) {this.updateUser = updateUser;}

	public String getCreatUser() {return creatUser;}

	public void setCreatUser(String creatUser) {this.creatUser = creatUser;}

	public String getUpdateTime() {return updateTime;}

	public void setUpdateTime(String updateTime) {this.updateTime = updateTime;}

	public String getTemplateName() {return templateName;}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getSlaTemplateId() {
		return slaTemplateId;
	}

	public void setSlaTemplateId(String slaTemplateId) {
		this.slaTemplateId = slaTemplateId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {this.desc = desc;}
	
	
	
}
