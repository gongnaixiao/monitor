package cn.lesaas.osb.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.lesaas.osb.model.EsbTraceInfo;
import cn.lesaas.osb.service.EsbTraceDAO;
import cn.lesaas.osb.service.EsbTraceMapper;
import cn.lesaas.osb.util.Constants;
import cn.lesaas.osb.util.LoadPopertiesFile;

public class EsbTraceDaoImpl implements EsbTraceDAO {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<EsbTraceInfo> listEsbTrance() {
		String SQL = "select id,esbsvcseqno,csmid,csmtms,csmseqno,svcid,pvdid,resptms,rspifn,appbody,apphead,optid,verno,exthead,rspcode,pvdSeqNo,rspSrc,esbTmsIn,esbTmsOut,svctype from esbtrace LIMIT 0,1000 ";
		List<EsbTraceInfo> esblist1 = jdbcTemplateObject.query(SQL,
				new EsbTraceMapper());
		List<EsbTraceInfo> esblist2 = esblist1;
		List<EsbTraceInfo> esblist3 = new ArrayList<EsbTraceInfo>();

		for (EsbTraceInfo esbtrace1 : esblist1) {
			if (esbtrace1.getId().equals("1")) {
				if ("1".equals(esbtrace1.getSvcType())) {
					// 判断appbody是否为空
					if (esbtrace1.getAppBody() == null) {
						esbtrace1.setAppBody("<appBody></appBody>");
					}
					// 判断apphead是否为空
					if (esbtrace1.getAppHead() == null) {
						esbtrace1.setAppHead("<appHead></appHead>");
					}
					// 判断exthead是否为空
					if (esbtrace1.getExtHead() == null) {
						esbtrace1.setExtHead("<appExtHead></appExtHead>");
					}

					if (esbtrace1.getCsmId() == null)
						esbtrace1.setCsmId("");
					if (esbtrace1.getRspIfn() == null)
						esbtrace1.setRspIfn("");
					esbtrace1.setReqtMessage("<reqt><svcHead><SvcId>"
							+ esbtrace1.getSvcId() + "</SvcId><OptId>"
							+ esbtrace1.getOptId() + "</OptId><VerNo>"
							+ esbtrace1.getVerNo() + "</VerNo><CsmId>"
							+ esbtrace1.getCsmId() + "</CsmId><CsmTms>"
							+ esbtrace1.getCsmTms() + "</CsmTms><CsmSeqNo>"
							+ esbtrace1.getCsmSeqNo() + "</CsmSeqNo></svcHead>"
							+ esbtrace1.getExtHead() + esbtrace1.getAppHead()
							+ esbtrace1.getAppBody() + "</reqt>");
				}
				if ("2".equals(esbtrace1.getSvcType())) {
					esbtrace1.setReqtMessage(esbtrace1.getAppBody());
				}
				for (EsbTraceInfo esbtrace2 : esblist2) {
					if (esbtrace2.getId().equals("2")
							&& esbtrace1.getEsbSvcSeqNo().equals(
									esbtrace2.getEsbSvcSeqNo())) {
						if ("1".equals(esbtrace2.getSvcType())) {
							// 判断appbody是否为空
							if (esbtrace1.getAppBody() == null) {
								esbtrace1.setAppBody("<appBody></appBody>");
							}
							// 判断apphead是否为空
							if (esbtrace1.getAppHead() == null) {
								esbtrace1.setAppHead("<appHead></appHead>");
							}
							// 判断exthead是否为空
							if (esbtrace1.getExtHead() == null) {
								esbtrace1
										.setExtHead("<appExtHead></appExtHead>");
							}

							if (esbtrace2.getCsmId() == null)
								esbtrace2.setCsmId("");
							if (esbtrace2.getRspIfn() == null)
								esbtrace2.setRspIfn("");
							if ("000000".equals(esbtrace2.getRspCode())) {
								esbtrace1.setRspCode("成功");
								esbtrace1
										.setRespMessage("<resp><svcHead><SvcId>"
												+ esbtrace2.getSvcId()
												+ "</SvcId><OptId>"
												+ esbtrace2.getOptId()
												+ "</OptId><EsbSvcSeqNo>"
												+ esbtrace2.getEsbSvcSeqNo()
												+ "</EsbSvcSeqNo><PvdId>"
												+ esbtrace2.getPvdId()
												+ "</PvdId><RspSrc>"
												+ esbtrace2.getRspSrc()
												+ "</RspSrc><RspCode>"
												+ esbtrace2.getRspCode()
												+ "</RspCode><RspInf>"
												+ esbtrace2.getRspIfn()
												+ "</RspInf><EsbTms>"
												+ esbtrace2.getEsbTmsOut()
												+ "</EsbTms></svcHead>"
												+ esbtrace2.getExtHead()
												+ esbtrace2.getAppHead()
												+ esbtrace2.getAppBody()
												+ "</resp>");
							} else {
								esbtrace1.setRspCode("失败");
								esbtrace1
										.setRespMessage("<ns:resp  	xmlns:ns="
												+ "\"http://esb.xjrccb.com/"
												+ esbtrace2.getSvcId()
												+ "\"><ns:svcHead><ns:SvcId>"
												+ esbtrace2.getSvcId()
												+ "</ns:SvcId><ns:OptId>"
												+ esbtrace2.getOptId()
												+ "</ns:OptId><ns:EsbSvcSeqNo>"
												+ esbtrace2.getEsbSvcSeqNo()
												+ "</ns:EsbSvcSeqNo><ns:PvdId>"
												+ esbtrace2.getPvdId()
												+ "</ns:PvdId><ns:RspSrc>"
												+ esbtrace2.getRspSrc()
												+ "</ns:RspSrc><ns:RspCode>"
												+ esbtrace2.getRspCode()
												+ "</ns:RspCode><ns:RspInf>"
												+ esbtrace2.getRspIfn()
												+ "</ns:RspInf><ns:EsbTms>"
												+ esbtrace2.getEsbTmsOut()
												+ "</ns:EsbTms></ns:svcHead></ns:resp>");
							}
						}
						if("2".equals(esbtrace2.getSvcType())){
							esbtrace1.setRespMessage(esbtrace2.getAppBody());
						}
						if (esbtrace1.getCsmId() == null)
							esbtrace1.setCsmId(esbtrace2.getCsmId());
						if (esbtrace1.getEsbTmsIn() == null)
							esbtrace1.setEsbTmsIn(esbtrace2.getEsbTmsIn());
						if (esbtrace1.getCsmSeqNo() == null)
							esbtrace1.setCsmSeqNo(esbtrace2.getCsmSeqNo());
						if (esbtrace1.getSvcId() == null)
							esbtrace1.setSvcId(esbtrace2.getSvcId());
						if (esbtrace1.getPvdId() == null)
							esbtrace1.setPvdId(esbtrace2.getPvdId());
						if (esbtrace1.getEsbTmsOut() == null)
							esbtrace1.setEsbTmsOut(esbtrace2.getEsbTmsOut());
						if (esbtrace1.getRspIfn() == null)
							esbtrace1.setRspIfn(esbtrace2.getRspIfn());
					}
				}

				String filePath = "system.properties";
				LoadPopertiesFile.loadSystemFile(filePath);
				if (esbtrace1.getCsmId() != null) {

					esbtrace1.setCsmId(Constants.loadSystemMap.get(esbtrace1
							.getCsmId().toUpperCase()));
				}
				if (esbtrace1.getPvdId() != null) {

					esbtrace1.setPvdId(Constants.loadSystemMap.get(esbtrace1
							.getPvdId().toUpperCase()));
				}
				esblist3.add(esbtrace1);
			}
		}
		return esblist3;
	}
}
