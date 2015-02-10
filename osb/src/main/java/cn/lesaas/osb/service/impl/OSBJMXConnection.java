package cn.lesaas.osb.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import org.springframework.stereotype.Component;

@Component
public class OSBJMXConnection {
	private static Properties prop = new Properties();
	private final static String JMXPROPNAME = "jmx.prop";
	static {
		InputStream in = ClassLoader.getSystemResourceAsStream(JMXPROPNAME);
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private JMXConnector conn;

	public JMXConnector getConnection(String jmxurl, String username,
			String password) throws IOException {

		JMXServiceURL serviceURL = new JMXServiceURL(jmxurl);
		Hashtable<String, String> h = new Hashtable<String, String>();

		h.put(Context.SECURITY_PRINCIPAL, username);
		h.put(Context.SECURITY_CREDENTIALS, password);
		h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
				"weblogic.management.remote");
		return JMXConnectorFactory.connect(serviceURL, h);
	}

	public JMXConnector getConnection() throws IOException {
		if (conn == null) {
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			String jmxurl = prop.getProperty("jmxurl");
			conn = getConnection(jmxurl, username, password);
		}
		return this.conn;
	}

	public synchronized void setConncetion(JMXConnector conn) {
		this.conn = conn;
	}

	public synchronized void setConncetion(String jmxurl, String username,
			String password) throws IOException {
		prop.setProperty("username", username);
		prop.setProperty("password", password);
		prop.setProperty("jmxurl", jmxurl);

		conn = getConnection(jmxurl, username, password);
	}
}
