package cn.lesaas.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesaas.osb.model.WlsInf;
import cn.lesaas.osb.service.WlsInfQuery;


@Controller
@RequestMapping("/monitor/serversInfo")
public class ServersController {
	@Autowired
	private WlsInfQuery service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List handleRequest() throws Exception {
		List<WlsInf> list = service.getWlsInfs();
		for (WlsInf inf : list) {
			System.out.println(inf.getName());
		}
		return list;
	}
}
