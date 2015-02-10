package cn.lesaas.osb;

import java.net.MalformedURLException;
import java.util.Hashtable;

import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import org.junit.Test;

public class JMXconnectTest {

	@Test
	public void jmxCon() {
		String usename = "weblogic";
		String password = "welcome2";
		String jmxurl = "service:jmx:rmi:///jndi/iiop://113.31.18.99:7001/weblogic.management.mbeanservers.domainruntime";
		try {
			JMXServiceURL serviceURL = new JMXServiceURL(jmxurl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hashtable<String, String> h = new Hashtable<String, String>();

		h.put(Context.SECURITY_PRINCIPAL, usename);
		h.put(Context.SECURITY_CREDENTIALS, password);
	}
}
