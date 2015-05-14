package cn.lesaas.osb.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rsa.certj.x.e;

import weblogic.xml.saaj.mime4j.field.datetime.DateTime;
import cn.lesaas.osb.model.EsbTraceInfo;


public class EsbTraceMapper implements RowMapper<EsbTraceInfo> {
	@Override
	public EsbTraceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		EsbTraceInfo esbTrace = new EsbTraceInfo();
		esbTrace.setId(rs.getString("ID"));
		esbTrace.setEsbSvcSeqNo(rs.getString("ESBSVCSEQNO"));
		esbTrace.setCsmId(rs.getString("CSMID"));
		esbTrace.setCsmTms(rs.getTimestamp("CSMTMS"));
		esbTrace.setCsmSeqNo(rs.getString("CSMSEQNO"));
		esbTrace.setSvcId(rs.getString("SVCID"));
		esbTrace.setPvdId(rs.getString("PVDID"));
		esbTrace.setRespTms(rs.getTimestamp("RESPTMS"));
		esbTrace.setRspIfn(rs.getString("RSPIFN"));
		esbTrace.setAppBody(rs.getString("APPBODY"));
		esbTrace.setAppHead(rs.getString("APPHEAD"));
		esbTrace.setOptId(rs.getString("OPTID"));
		esbTrace.setVerNo(rs.getString("VERNO"));
		esbTrace.setExtHead(rs.getString("EXTHEAD"));
		esbTrace.setRspCode(rs.getString("RSPCODE"));
		esbTrace.setPvdSeqNo(rs.getString("PVDSEQNO"));
		esbTrace.setRspSrc(rs.getString("RSPSRC"));
		esbTrace.setEsbTmsIn(rs.getTimestamp("EsbTmsIn"));
		esbTrace.setEsbTmsOut(rs.getTimestamp("ESBTMSOUT"));
		esbTrace.setSvcType(rs.getString("SVCTYPE"));
		return esbTrace;
	}
}
