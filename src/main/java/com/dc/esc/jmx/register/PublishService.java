package com.dc.esc.jmx.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PublishService implements PublishServiceMBean {
	protected final static Log log = LogFactory.getLog(PublishService.class);

	@Override
	public String pubProvider(PublishDataBean bean) {
		log.info("---------------start pubProvider---------------");
		log.info("serviceId=" + bean.serviceId);
		log.info("serviceName=" + bean.serviceName);
		log.info("prvdSys=" + bean.prvdSys);
		log.info("onlineVers=" + bean.onlineVers);
		log.info("lastVers=" + bean.lastVers);
		log.info("transId=" + bean.transId);
		log.info("packageType=" + bean.packageType);
		log.info("servicDefinition=" + bean.servicDefinition);
		log.info("definitionData=" + bean.definitionData);
		log.info("toFile=" + bean.toFile);
		log.info("toData=" + bean.toData);
		log.info("fromFile=" + bean.fromFile);
		log.info("fromData=" + bean.fromData);
		log.info("userId=" + bean.userId);
		log.info("---------------end pubProvider---------------");
		return "OK";
	}

	@Override
	public String pubConsumer(PublishDataBean bean) {
		log.info("---------------start pubConsumer---------------");
		log.info("serviceId=" + bean.serviceId);
		log.info("onlineVers=" + bean.onlineVers);
		log.info("consSys=" + bean.consSys);
		log.info("transId=" + bean.transId);
		log.info("packageType=" + bean.packageType);
		log.info("toFile=" + bean.toFile);
		log.info("toData=" + bean.toData);
		log.info("fromFile=" + bean.fromFile);
		log.info("fromData=" + bean.fromData);
		log.info("userId=" + bean.userId);
		log.info("---------------end pubConsumer---------------");
		return "OK";
	}
}
