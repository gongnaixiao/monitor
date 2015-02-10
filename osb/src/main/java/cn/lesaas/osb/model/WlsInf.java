package cn.lesaas.osb.model;

public class WlsInf {
	private String name;
	private String health;
	private String clusterName;
	private String machineName;
	private String state;
	private String uptime;
	
	public String getName() {
		return this.name;
	}
	
	public String getStatus() {
		return this.state;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatus(String state) {
		this.state = state; 
	}
	
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	
	public String getUptime() {
		return this.uptime;
	}
	
	public void setHealth(String health) {
		this.health = health;
	}
	public String getHealth() {
		return this.health;
	}
	
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getClusterName() {
		return this.clusterName;
	}
	
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineName() { 
		return this.machineName;
	}
}
