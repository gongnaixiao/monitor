package cn.lesaas.osb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weblogic.management.jmx.MBeanServerInvocationHandler;
import cn.lesaas.osb.model.ServiceInf;
import cn.lesaas.osb.service.MonitoredServices;

import com.bea.common.security.xacml.context.Result;
import com.bea.wli.config.Ref;
import com.bea.wli.monitoring.InvalidServiceRefException;
import com.bea.wli.monitoring.MonitoringException;
import com.bea.wli.monitoring.MonitoringNotEnabledException;
import com.bea.wli.monitoring.ResourceStatistic;
import com.bea.wli.monitoring.ResourceType;
import com.bea.wli.monitoring.ServiceDomainMBean;
import com.bea.wli.monitoring.ServiceResourceStatistic;
import com.bea.wli.monitoring.StatisticType;
import com.bea.wli.monitoring.StatisticValue;

@Service("MonitoredServices")
public class MonitoredServicesImpl implements MonitoredServices {
	@Autowired
	private OSBJMXConnection OSBconnector;

	private ServiceDomainMBean serviceDomainMBean;
	private ObjectName on;

	public long resetStatistics(Ref[] serivceRefs) throws MonitoringException {
		// TODO Auto-generated method stub
		return serviceDomainMBean.resetStatistics(serivceRefs);
	}

	public long resetAllStatistics() throws MonitoringException {
		// TODO Auto-generated method stub
		return serviceDomainMBean.resetAllStatistics();
	}

	public String[] getServerNames() throws MonitoringException {
		return serviceDomainMBean.getServerNames();
	}

	private Object findServiceDomain() throws MalformedObjectNameException,
			IOException {
		if (on == null) {
			on = new ObjectName(ServiceDomainMBean.OBJECT_NAME);
		}

		if (serviceDomainMBean == null) {
			try {
				MBeanServerConnection connection = OSBconnector.getConnection()
						.getMBeanServerConnection();
				serviceDomainMBean = (ServiceDomainMBean) MBeanServerInvocationHandler
						.newProxyInstance(connection, on);
			} catch (IOException e) {
				OSBconnector.setConncetion(null);
				serviceDomainMBean = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
		}
		return serviceDomainMBean;
	}

	@Override
	public List getStatisticsForMonitoredServices() throws Exception {
		ServiceDomainMBean sdmb = (ServiceDomainMBean) findServiceDomain();

		List<ServiceInf> monitoredService = new ArrayList<ServiceInf>();

		int typeFlag;
		HashMap<Ref, ServiceResourceStatistic> resourcesMap = null;
		// BusinessService
		try {
			Ref[] serviceRefs = sdmb.getMonitoredBusinessServiceRefs();
			// Create a bitwise map for desired resource types.
			typeFlag = 0;
			typeFlag = typeFlag | ResourceType.SERVICE.value();
			typeFlag = typeFlag | ResourceType.WEBSERVICE_OPERATION.value();
			typeFlag = typeFlag | ResourceType.URI.value();
			resourcesMap = sdmb.getBusinessServiceStatistics(serviceRefs,
					typeFlag, null);
			monitoredService.addAll(parser(resourcesMap));
		} catch (Exception e) {
			serviceDomainMBean = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		// ProxyService

		try {
			Ref[] serviceRefs = sdmb.getMonitoredProxyServiceRefs();
			System.out.println(serviceRefs[0].getFullName());

			typeFlag = 0;
			typeFlag = typeFlag | ResourceType.SERVICE.value();
			typeFlag = typeFlag | ResourceType.FLOW_COMPONENT.value();
//			typeFlag = typeFlag | ResourceType.WEBSERVICE_OPERATION.value();
			resourcesMap = sdmb.getProxyServiceStatistics(serviceRefs,
					typeFlag, null);
			monitoredService.addAll(parser(resourcesMap));
		} catch (Exception e) {
			serviceDomainMBean = null; // TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return monitoredService;
	}

	private List<ServiceInf> parser(
			HashMap<Ref, ServiceResourceStatistic> resourcesMap)
			throws InvalidServiceRefException, MonitoringNotEnabledException,
			MonitoringException {
		List<ServiceInf> list = new ArrayList<ServiceInf>(); 
		Set<Map.Entry<Ref, ServiceResourceStatistic>> set = resourcesMap
				.entrySet();
		for (Map.Entry<Ref, ServiceResourceStatistic> mapEntry : set) {
			ServiceInf service = new ServiceInf();
			ServiceResourceStatistic statistic = mapEntry.getValue();
			Ref ref = mapEntry.getKey();
			service.setName(ref.getLocalName());
			service.setPath(ref.getFullName());
			service.setServiceType(ref.getTypeId());
			ResourceStatistic[] resStatsArray = statistic
					.getAllResourceStatistics();
			for (ResourceStatistic resStats : resStatsArray) {
				ResourceType resourceType = resStats.getResourceType();

				if (ResourceType.SERVICE.equals(resourceType)) {
					StatisticValue[] statValues = resStats.getStatistics();
					for (StatisticValue value : statValues) {
						if ("response-time".equalsIgnoreCase(value.getName())) {
							StatisticValue.IntervalStatistic is = (StatisticValue.IntervalStatistic) value;
							service.setAvgRespTime(is.getAverage());
							service.setMinRespTime(is.getMin());
							service.setMaxRespTime(is.getMax());
						}
						if ("message-count".equalsIgnoreCase(value.getName())) {
							StatisticValue.CountStatistic cs = (StatisticValue.CountStatistic) value;
							service.setMessagesCounts(cs.getCount());
						}
						if ("error-count".equalsIgnoreCase(value.getName())) {
							StatisticValue.CountStatistic cs = (StatisticValue.CountStatistic) value;
							service.setErrosCounts(cs.getCount());
						}
					}
				}
			}
			list.add(service);
		}
		return list;
	}
}
