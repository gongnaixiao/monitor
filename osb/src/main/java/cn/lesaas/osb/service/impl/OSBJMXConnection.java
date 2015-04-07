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

public class OSBJMXConnection {
	private String jmxurl;
	private String username;
	private String password;

	public void setJmxurl(String jmxurl) {
		this.jmxurl = jmxurl;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
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
			conn = getConnection(jmxurl, username, password);
		}
		return this.conn;
	}

	public synchronized void setConncetion(JMXConnector conn) {
		this.conn = conn;
	}

	public synchronized void setConncetion(String jmxurl, String username,
			String password) throws IOException {
		this.username = username;
		this.password = password;
		this.jmxurl = jmxurl;

		conn = getConnection(jmxurl, username, password);
	}
}
