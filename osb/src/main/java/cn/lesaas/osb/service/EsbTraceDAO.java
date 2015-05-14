package cn.lesaas.osb.service;

import java.util.List;

import javax.sql.DataSource;

import cn.lesaas.osb.model.EsbTraceInfo;

public interface EsbTraceDAO {

	public void setDataSource(DataSource ds);
	public List<EsbTraceInfo> listEsbTrance();
	
}
