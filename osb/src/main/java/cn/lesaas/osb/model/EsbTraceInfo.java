package cn.lesaas.osb.model;

import java.sql.Timestamp;



public class EsbTraceInfo {
	// 流水类型标识
	private String id;
	// ESB流水号(主键)
	private String esbSvcSeqNo;
	// 服务请求方
	private String csmId;
	// 服务请求时间
	private Timestamp csmTms;
	// 服务请求方流水号
	private String csmSeqNo;
	// 服务名
	private String svcId;
	// 服务提供方
	private String pvdId;
	// 服务返回时间
	private Timestamp respTms;
	// 响应信息
	private String rspIfn;
	
	private String appBody;
	
	private String appHead;
	
	private String optId;
	
	private String verNo;
	
	private String extHead;
	
	private String rspCode;
	
	private String reqtMessage;
	
	private String respMessage;
	
	private String pvdSeqNo;
	
	private String rspSrc;
	
	private Timestamp esbTmsOut;
	
	private Timestamp esbTmsIn;
	
	private String svcType;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;

	}

	public void setEsbSvcSeqNo(String esbSvcSeqNo) {
		this.esbSvcSeqNo = esbSvcSeqNo;
	}

	public String getEsbSvcSeqNo() {
		return this.esbSvcSeqNo;
	}

	public void setCsmId(String csmId) {
		this.csmId = csmId;
	}

	public String getCsmId() {
		return this.csmId;
	}

	public void setCsmTms(Timestamp csmTms) {
		this.csmTms = csmTms;
	}

	public Timestamp getCsmTms() {
		return this.csmTms;

	}

	public void setCsmSeqNo(String csmSeqNo) {
		this.csmSeqNo = csmSeqNo;
	}

	public String getCsmSeqNo() {
		return this.csmSeqNo;
	}

	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}

	public String getSvcId() {
		return this.svcId;
	}

	public void setRespTms(Timestamp respTms) {
		this.respTms = respTms;
	}

	public Timestamp getRespTms() {
		return this.respTms;

	}

	public void setPvdId(String pvdId) {
		this.pvdId = pvdId;
	}

	public String getPvdId() {
		return this.pvdId;

	}

	public void setRspIfn(String rspIfn) {
		this.rspIfn = rspIfn;
	}

	public String getRspIfn() {
		return this.rspIfn;
	}
	
	public void setAppBody(String appBody) {
		this.appBody = appBody;
	}

	public String getAppBody() {
		return this.appBody;
	}
	
	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptId() {
		return this.optId;
	}
	
	public void setVerNo(String verNo) {
		this.verNo = verNo;
	}

	public String getVerNo() {
		return this.verNo;
	}
	
	public void setExtHead(String extHead) {
		this.extHead = extHead;
	}

	public String getExtHead() {
		return this.extHead;
	}
	
	public void setAppHead(String appHead) {
		this.appHead = appHead;
	}

	public String getAppHead() {
		return this.appHead;
	}
	
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspCode() {
		return this.rspCode;
	}
	
	public void setReqtMessage(String reqtMessage) {
		this.reqtMessage = reqtMessage;
	}

	public String getReqtMessage() {
		return this.reqtMessage;
	}
	
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public String getRespMessage() {
		return this.respMessage;
	}
	
	public void setPvdSeqNo(String pvdSeqNo) {
		this.pvdSeqNo = pvdSeqNo;
	}

	public String getPvdSeqNo() {
		return this.pvdSeqNo;
	}
	
	public void setRspSrc(String rspSrc) {
		this.rspSrc = rspSrc;
	}

	public String getRspSrc() {
		return this.rspSrc;
	}
	
	public void setEsbTmsOut(Timestamp esbTmsOut) {
		this.esbTmsOut = esbTmsOut;
	}

	public Timestamp getEsbTmsOut() {
		return this.esbTmsOut;
	}
	
	
	public void setEsbTmsIn(Timestamp esbTmsIn) {
		this.esbTmsIn = esbTmsIn;
	}

	public Timestamp getEsbTmsIn() {
		return this.esbTmsIn;
	}
	
	public void setSvcType(String svcType) {
		this.svcType = svcType;
	}

	public String getSvcType() {
		return this.svcType;
	}
}
