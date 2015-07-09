package com.dc.esb.servicegov.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="INTERFACE")
public class Interface {
	@Id
	@Column(name = "INTERFACE_ID")
	@GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
	private String interfaceId;
	
	@Column(name = "INTERFACE_NAME")
	private String interfaceName;
	
	@Column(name = "ECODE")
	private String ecode;
	
	@Column(name = "DESCRIPTION")
	private String desc;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "INTERFACE_HEAD_ID")
	private String interfaceHeadId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "VERSION")
	private String version;
	
	@Column(name = "OPT_USER")
	private String optUser;
	
	@Column(name = "OPT_DATE")
	private String optDate;

	
	@OneToOne(mappedBy="inter")
	private ServiceInvoke serviceInvoke;
	
	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInterfaceHeadId() {
		return interfaceHeadId;
	}

	public void setInterfaceHeadId(String interfaceHeadId) {
		this.interfaceHeadId = interfaceHeadId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public String getOptDate() {
		return optDate;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	public ServiceInvoke getServiceInvoke() {
		return serviceInvoke;
	}

	public void setServiceInvoke(ServiceInvoke serviceInvoke) {
		this.serviceInvoke = serviceInvoke;
	}
	
}
