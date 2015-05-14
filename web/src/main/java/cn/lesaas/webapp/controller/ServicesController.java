package cn.lesaas.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesaas.osb.model.ServiceInf;
import cn.lesaas.osb.service.MonitoredServices;

@Controller
@RequestMapping("/monitor/services")
public class ServicesController {
	@Autowired
	private MonitoredServices service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List handleRequest() throws Exception {
		List<ServiceInf> list = service.getStatisticsForMonitoredServices();
		for (ServiceInf inf : list) {
			System.out.println(String.valueOf(inf.getName()));
		}
		return list;
	}
}
