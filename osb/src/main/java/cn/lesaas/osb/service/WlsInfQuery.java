package cn.lesaas.osb.service;

import java.util.List;

import javax.jws.WebService;

import cn.lesaas.osb.model.WlsInf;

@WebService
public interface WlsInfQuery {

	/**
	 * Retrieves a list of all wls info.
	 *
	 * @return List
	 * @throws Exception
	 */
	List<WlsInf> getWlsInfs() throws Exception;

}
