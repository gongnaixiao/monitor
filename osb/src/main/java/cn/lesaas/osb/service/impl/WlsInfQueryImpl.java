package cn.lesaas.osb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.jws.WebService;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weblogic.health.HealthState;
import cn.lesaas.osb.model.WlsInf;
import cn.lesaas.osb.service.WlsInfQuery;
import cn.lesaas.util.DateUtil;

@Service("WlsInfQuery")
@WebService(serviceName = "UserService", endpointInterface = "cn.lesaas.service.UserService")
public class WlsInfQueryImpl implements WlsInfQuery {

	@Autowired
	private OSBJMXConnection OSBconnector;

	@Override
	public List<WlsInf> getWlsInfs() throws Exception {
		List<WlsInf> list = new ArrayList<WlsInf>();
		ObjectName service = new ObjectName(
				"com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");

		MBeanServerConnection connection = null;
		try {
			connection = OSBconnector.getConnection()
					.getMBeanServerConnection();
		} catch (IOException e) {
			OSBconnector.setConncetion(null);
			throw e;
		}

		ObjectName[] serverRT = (ObjectName[]) connection.getAttribute(service,
				"ServerRuntimes");
		for (ObjectName server : serverRT) {
			WlsInf wlsInf = new WlsInf();

			String name = (String) connection.getAttribute(server, "Name");
			wlsInf.setName(name);
			String state = (String) connection.getAttribute(server, "State");
			wlsInf.setStatus(state);
			String currentMachine = (String) connection.getAttribute(server,
					"CurrentMachine");
			wlsInf.setMachineName(currentMachine);
			long activationTime = (Long) connection.getAttribute(server,
					"ActivationTime");
			long duration = Calendar.getInstance().getTimeInMillis()
					- activationTime;
			String uptime = DateUtil.convertMsecsToString(duration);
			wlsInf.setUptime(uptime);

			ObjectName clusterRT = (ObjectName) connection.getAttribute(server,
					"ClusterRuntime");
			String clusterName = "";
			if (clusterRT != null) {
				clusterName = (String) connection.getAttribute(clusterRT,
						"Name");
			}
			wlsInf.setClusterName(clusterName);

			weblogic.health.HealthState healthState = (HealthState) connection
					.getAttribute(server, "HealthState");

			String health = "";
			int hState = healthState.getState();
			switch (hState) {
			case HealthState.HEALTH_OK:
				health = "OK";
				break;
			case HealthState.HEALTH_WARN:
				health = "Warning";
				break;
			case HealthState.HEALTH_CRITICAL:
				health = "Critical";
				break;
			case HealthState.HEALTH_FAILED:
				health = "Failed";
				break;
			case HealthState.HEALTH_OVERLOADED:
				health = "Overloaded";
				break;
			default:
				health = "Undefined";
			}
			wlsInf.setHealth(health);

			list.add(wlsInf);
		}

		return list;
	}
}
