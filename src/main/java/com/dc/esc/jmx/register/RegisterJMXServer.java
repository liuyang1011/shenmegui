package com.dc.esc.jmx.register;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RegisterJMXServer {

	protected final static Log log = LogFactory.getLog(RegisterJMXServer.class);

	private static RegisterJMXServer instance = null;

	public static synchronized RegisterJMXServer getInstance() {
		if (instance == null) {
			instance = new RegisterJMXServer();
		}
		return instance;
	}

	public void register() {

		MBeanServer server = MBeanServerFactory.createMBeanServer();
		try {

			int regPort = 1608;
			int rmtPort = 2909;

			String jmxUrl = PublishServiceProxy.getJMXURL("127.0.0.1", rmtPort,
					regPort);
			log.info(jmxUrl);
			JMXServiceURL url = new JMXServiceURL(jmxUrl);

			log.info("registerMBean " + PublishServiceProxy.PUBLISHSERVICE);
			server.registerMBean(new PublishService(), new ObjectName(
					PublishServiceProxy.PUBLISHSERVICE));

			JMXConnectorServer jmxServer = JMXConnectorServerFactory
					.newJMXConnectorServer(url, null, server);

			LocateRegistry.createRegistry(regPort);
			jmxServer.start();
			log.info("jmx server is start.");
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RegisterJMXServer server = new RegisterJMXServer();
		server.register();

	}
}
