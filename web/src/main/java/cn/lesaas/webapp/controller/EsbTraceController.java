package cn.lesaas.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lesaas.osb.model.EsbTraceInfo;
import cn.lesaas.osb.service.EsbTraceDAO;

@Controller
@RequestMapping("/monitor/esbtrace")
public class EsbTraceController {
	@Autowired
	private EsbTraceDAO esb;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List esbTraceRequest() throws Exception {
		List<EsbTraceInfo> list = esb.listEsbTrance();
		int i = 0;
		for (EsbTraceInfo inf : list) {
			i++;
			System.out.println(String.valueOf(inf.getCsmId()));
		}
		return list;
	}
}
