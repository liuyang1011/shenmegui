package com.dc.esb.servicegov.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM")
public class System implements Serializable{

	private static final long serialVersionUID = 1L;


	//系统序号
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "SYSTEM_ID")
	private String systemId;
	//系统编号
	@Column(name = "SYSTEM_NO")
	private String systemNo;
	//系统简称
	@Column(name = "SYSTEM_AB")
	private String systemAb;
	//	系统中文名
	@Column(name = "SYSTEM_CHINESE_NAME")
	private String systemChineseName;
	//	更新用户
	@Column(name = "UPDATE_USER")
	private String updateUser;
	//	更新时间
	@Column(name = "UPDATE_DATE")
	private String updateDate;
	//系统描述
	@Column(name = "SYSTEM_DESC")
	private String systemDesc;
	//系统分类
	@Column(name = "SYSTEM_ClASSIFY")
	private String systemClassify;
	//系统负责人
	@Column(name = "SYSTEM_PRINCIPAL")
	private String systemPrincipal;
	//负责人电话
	@Column(name = "PRINCIPAL_TEL")
	private String principalTel;
	//负责人邮箱
	@Column(name = "PRINCIPAL_EMAIL")
	private String principalEmail;
	//关联公司
	@Column(name = "CONN_COMPANY")
	private String connCompany;
	//创建用户
	@Column(name = "CREATE_USER")
	private String createUser;

	private String protocolName;

	public static String[] simpleFields(){
		String[] names = {"systemId", "systemChineseName"};
		return names;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "system")
	//@JoinColumn(name = "SYSTEM_ID",referencedColumnName = "SYSTEM_ID",insertable=true,updatable = true)
	private List<SystemProtocol> systemProtocols;
	
	@OneToMany(mappedBy="system",cascade = CascadeType.ALL)
	private List<ServiceInvoke> serviceInvokes;


	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemAb() {
		return systemAb;
	}

	public void setSystemAb(String systemAb) {
		this.systemAb = systemAb;
	}

	public String getSystemChineseName() {
		return systemChineseName;
	}

	public void setSystemChineseName(String systemChineseName) {
		this.systemChineseName = systemChineseName;
	}



	public List<ServiceInvoke> getServiceInvokes() {
		return serviceInvokes;
	}

	public void setServiceInvokes(List<ServiceInvoke> serviceInvoke) {
		this.serviceInvokes = serviceInvoke;
	}

	public List<SystemProtocol> getSystemProtocols() {
		return systemProtocols;
	}

	public void setSystemProtocols(List<SystemProtocol> systemProtocols) {
		this.systemProtocols = systemProtocols;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getSystemDesc() {
		return systemDesc;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}


	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getSystemClassify() {
		return systemClassify;
	}

	public void setSystemClassify(String systemClassify) {
		this.systemClassify = systemClassify;
	}

	public String getSystemPrincipal() {
		return systemPrincipal;
	}

	public void setSystemPrincipal(String systemPrincipal) {
		this.systemPrincipal = systemPrincipal;
	}

	public String getPrincipalTel() {
		return principalTel;
	}

	public void setPrincipalTel(String principalTel) {
		this.principalTel = principalTel;
	}

	public String getPrincipalEmail() {
		return principalEmail;
	}

	public void setPrincipalEmail(String principalEmail) {
		this.principalEmail = principalEmail;
	}

	public String getConnCompany() {
		return connCompany;
	}

	public void setConnCompany(String connCompany) {
		this.connCompany = connCompany;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getSystemNo() {
		return systemNo;
	}

	public void setSystemNo(String systemNo) {
		this.systemNo = systemNo;
	}
}
