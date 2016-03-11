package com.dc.esc.jmx.register;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PublishServiceProxy implements PublishServiceMBean {
	protected final static Log log = LogFactory
			.getLog(PublishServiceProxy.class);

	public final static String PUBLISHSERVICE = "esc.agent:name=PublishServiceMBean";

	public static String getJMXURL(String ip, int jmxRmtPort, int jmxRegPort) {

		return "service:jmx:rmi://" + ip + ":" + jmxRmtPort + "/jndi/rmi://"
				+ ip + ":" + jmxRegPort + "/jmxrmi";
	}

	private PublishServiceMBean getMBean() {
		PublishServiceMBean bean = null;
		JMXServiceURL url;
		try {
			int regPort = 1608;
			int rmtPort = 2909;

			String jmxUrl = PublishServiceProxy.getJMXURL("127.0.0.1", rmtPort,
					regPort);
			url = new JMXServiceURL(jmxUrl);
			JMXConnector conn = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mbsc = conn.getMBeanServerConnection();
			ObjectName mbeanName = new ObjectName(
					PublishServiceProxy.PUBLISHSERVICE);
			bean = JMX
					.newMBeanProxy(mbsc, mbeanName, PublishServiceMBean.class);
		} catch (Exception e) {
			log.error("JMX Exception,connector error", e);
		}
		return bean;
	}

	public String pubConsumer(String serviceId, String onlineVers, String lastVers,
			String consSys, String transId, String packageType, String toFile,
			String toData, String fromFile, String fromData, String userId) {

		PublishDataBean bean = new PublishDataBean(serviceId, onlineVers,lastVers,
				consSys, transId, packageType, toFile, toData, fromFile,
				fromData, userId);
		return getMBean().pubConsumer(bean);
	}

	public String pubProvider(String serviceId, String serviceName,
			String prvdSys, String onlineVers, String lastVers, String transId,
			String packageType, String servicDefinition, String defenitionData,
			String toFile, String toData, String fromFile, String fromData,
			String userId) {

		PublishDataBean bean = new PublishDataBean(serviceId, serviceName,
				prvdSys, onlineVers, lastVers, transId, packageType,
				servicDefinition, defenitionData, toFile, toData, fromFile,
				fromData, userId);
		return getMBean().pubProvider(bean);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PublishServiceProxy proxy = new PublishServiceProxy();
		proxy.pubProvider("serviceId", "serviceName", "prvdSys", "onlineVers",
				"lastVers", "transId", "packageType", "servicDifinition",
				"xml1", "toFile", "xml2", "fromFile", "xml3", "userId");
//		proxy.pubConsumer("serviceId", "onlineVers", "consSys", "transId",
//				"packageType", "toFile", "xml1", "fromFile", "xml2", "userId");
	}

	@Override
	public String pubProvider(PublishDataBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pubConsumer(PublishDataBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
