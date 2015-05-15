<%@ include file="/common/taglibs.jsp"%>

<head>
<title>esbtrace</title>
<meta name="menu" content="MonitorMenu" />
</head>

<script type="text/javascript">
	var table;
	$(document)
			.ready(
					function() {
						table = $('#serverTable')
								.DataTable(
										{
											"ajax" : {
												"url" : "/monitor/esbtrace",
												"dataSrc" : ""
											},
											"columns" : [ {
												"data" : "csmId"
											}, {
												"data" : "esbTmsIn"
											}, {
												"data" : "csmSeqNo"
											}, {
												"data" : "svcId"
											}, {
												"data" : "pvdId"
											}, {
												"data" : "esbTmsOut"
											}, {
												"data" : "rspIfn"
											} ],
											"dom" : "<'row'<'col-xs-4'l><'#toolbar.col-xs-4'><'col-xs-4'f>r>"
													+ "t"
													+ "<'row'<'col-xs-6'i><'col-xs-6'p>>",
											initComplete : function() {
												$("#toolbar")
														.append(
																'<label>refresh</label>');
												$("#toolbar")
														.append(
																'<input id="time" value="20" "></input>');
												$("#toolbar")
														.append(
																'<label>seconds</label>');
												$("#time")
														.on(
																'input',
																function() {
																	var time = $(
																			"#time")
																			.val();
																	if (id != null
																			&& id != undefined) {
																		clearInterval(id);
																	}
																	id = setInterval(
																			function() {
																				table.ajax
																						.reload(
																								null,
																								false); // user paging is not reset on reload
																			},
																			time * 1000);
																});
												var id = setInterval(
														function() {
															table.ajax
																	.reload(
																			null,
																			false); // user paging is not reset on reload
														},
														$("#time").val() * 1000);
											}
										});
						new $.fn.dataTable.FixedHeader(table);
					});
</script>
<div class="col-sm-10">

	<table width="100%" class="display" id="serverTable" cellspacing="0">
		<thead>
			<tr>
				<th><fmt:message key="esbtrace.csmid" /></th>
				<th><fmt:message key="esbtrace.esbtmsin" /></th>
				<th><fmt:message key="esbtrace.csmseqno" /></th>
				<th><fmt:message key="esbtrace.svcid" /></th>
				<th><fmt:message key="esbtrace.pvdid" /></th>
				<th><fmt:message key="esbtrace.esbtmsout" /></th>
				<th><fmt:message key="esbtrace.repifn" /></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
