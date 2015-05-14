package cn.lesaas.osb.model;

public class ServiceInf {
	private String name;
	private String path;
	private String serviceType;
	private long avgRespTime;
	private long minRespTime;
	private long maxRespTime;
	private long messagesCounts;
	private long errosCounts;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceType() {
		return this.serviceType;
	}
	
	public void setAvgRespTime(long avgRespTime) {
		this.avgRespTime = avgRespTime;
	}
	public long getAvgRespTime() {
		return this.avgRespTime;
	}
	public void setMinRespTime(long minRespTime) {
		this.minRespTime = minRespTime;
	}
	public long getMinRespTime() {
		return this.minRespTime;
	}
	public void setMaxRespTime(long maxRespTime) {
		this.maxRespTime = maxRespTime;
	}
	public long getMaxRespTime() {
		return this.maxRespTime;
	}
	
	public void setMessagesCounts(long messagesCoutns) {
		this.messagesCounts = messagesCoutns;
	}
	public long getMessagesCounts() {
		return this.messagesCounts;
	}
	
	public void setErrosCounts(long errosCounts) {
		this.errosCounts = errosCounts;
	}
	public long getErroCounts() {
		return this.errosCounts;
	}
}
