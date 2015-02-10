package cn.lesaas.osb.service;

import java.util.List;

import com.bea.wli.config.Ref;
import com.bea.wli.monitoring.MonitoringException;
import com.bea.wli.monitoring.ServiceDomainMBean;

public interface MonitoredServices {
	public List getStatisticsForMonitoredServices() throws Exception;
}
