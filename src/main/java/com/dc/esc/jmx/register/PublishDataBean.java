package com.dc.esc.jmx.register;

import java.io.Serializable;

public class PublishDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	String serviceId;
	String serviceName;
	String consSys;
	String prvdSys;
	String onlineVers;
	String lastVers;
	String transId;
	String packageType;
	String servicDefinition;
	String definitionData;
	String toFile;
	String toData;
	String fromFile;
	String fromData;
	String userId;

	public PublishDataBean(String serviceId, String onlineVers,String lastVers, String consSys,
			String transId, String packageType, String toFile, String toData,
			String fromFile, String fromData, String userId) {
		this.type = "cons";
		this.serviceId = serviceId;
		this.consSys = consSys;
		this.onlineVers = onlineVers;
		this.lastVers = lastVers;
		this.transId = transId;
		this.packageType = packageType;
		this.toFile = toFile;
		this.toData = toData;
		this.fromFile = fromFile;
		this.fromData = fromData;
		this.userId = userId;

	}

	public PublishDataBean(String serviceId, String serviceName,
			String prvdSys, String onlineVers, String lastVers, String transId,
			String packageType, String servicDefinition, String definitionData,
			String toFile, String toData, String fromFile, String fromData,
			String userId) {

		this.type = "prvd";
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.prvdSys = prvdSys;
		this.onlineVers = onlineVers;
		this.lastVers = lastVers;
		this.transId = transId;
		this.packageType = packageType;
		this.servicDefinition = servicDefinition;
		this.definitionData = definitionData;
		this.toFile = toFile;
		this.toData = toData;
		this.fromFile = fromFile;
		this.fromData = fromData;
		this.userId = userId;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
