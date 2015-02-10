package cn.lesaas.osb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class LoadResTest {

	@Test
	public void getPropTest() throws IOException {
		InputStream in = ClassLoader.getSystemResourceAsStream("jmx.prop");
		
		Properties prop = new Properties();
//		prop.load(in);
		System.out.println(prop.getProperty("usename") + " " + prop.getProperty("password") + " " + prop.getProperty("jmxurl"));
	}
}
