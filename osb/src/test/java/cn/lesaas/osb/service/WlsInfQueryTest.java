package cn.lesaas.osb.service;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.lesaas.osb.model.WlsInf;

@ContextConfiguration(locations = { "classpath*:/**/applicationContext.xml",
		"classpath:/applicationContext-resources.xml" })
public class WlsInfQueryTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private WlsInfQuery wlsInfQuery;

	@Test
	public void testGetWlsInfs() throws Exception {
		Logger log = LoggerFactory.getLogger(WlsInfQueryTest.class);

		long lbeg = System.currentTimeMillis();
		List<WlsInf> list = wlsInfQuery.getWlsInfs();
		long lend = System.currentTimeMillis();

		log.debug(String.valueOf(lend - lbeg));
		log.debug(list.get(0).getName());
	}
}
