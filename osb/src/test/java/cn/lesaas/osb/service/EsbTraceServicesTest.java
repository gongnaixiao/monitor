package cn.lesaas.osb.service;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import cn.lesaas.osb.model.EsbTraceInfo;


@ContextConfiguration(locations = { "classpath*:/**/applicationContext.xml",
		"classpath:/applicationContext-resources.xml" })
public class EsbTraceServicesTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EsbTraceDAO service;

	@Test
	public void getServiceDomainMBeanTest() throws Exception {
		List<EsbTraceInfo> list = service.listEsbTrance();
		int i=0;
	     for(EsbTraceInfo esb : list){  
	    	 	i++;
             	System.out.println("流水号              :"+esb.getEsbSvcSeqNo());
	            System.out.println("请求方              :"+esb.getCsmId());
	            System.out.println("请求时间          :"+esb.getEsbTmsIn());
	            System.out.println("请求方流水号  :"+esb.getCsmSeqNo());
	            System.out.println("服务号              :"+esb.getSvcId());
	            System.out.println("提供方              :"+esb.getPvdId());
	            System.out.println("返回时间          :"+esb.getEsbTmsOut());
	            System.out.println("返回信息          :"+esb.getRspCode());
	            System.out.println("请求报文          :"+esb.getReqtMessage());
	            System.out.println("响应报文          :"+esb.getRespMessage());
	            System.out.println("-----------------------------------------------------------------------");
	        }  
	     System.out.println(i);

	}
}
