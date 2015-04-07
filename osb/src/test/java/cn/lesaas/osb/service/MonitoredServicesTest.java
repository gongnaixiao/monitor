package cn.lesaas.osb.service;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.lesaas.osb.model.ServiceInf;

import com.bea.wli.monitoring.MonitoringException;

@ContextConfiguration(locations = { "classpath*:/**/applicationContext.xml",
		"classpath:/applicationContext-resources.xml" })
public class MonitoredServicesTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MonitoredServices service;

	@Test
	public void getServiceDomainMBeanTest() throws Exception {
		try {
			List<ServiceInf> list = service.getStatisticsForMonitoredServices();
			Iterator<ServiceInf> it = list.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().getName());
			}
		} catch (MonitoringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
