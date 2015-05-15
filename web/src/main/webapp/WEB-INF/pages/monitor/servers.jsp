<%@ include file="/common/taglibs.jsp"%>

<head>
<title>weblogic server status</title>
<meta name="menu" content="MonitorMenu" />
</head>

<script type="text/javascript">
	var table;
	$(document).ready(function() {
		table = $('#serverTable').DataTable({
			"ajax" : {
				"url" : "/monitor/serversInfo",
				"dataSrc" : ""
			},
			"columns" : [ {
				"data" : "name"
			}, {
				"data" : "health"
			}, {
				"data" : "clusterName"
			}, {
				"data" : "machineName"
			}, {
				"data" : "status"
			}, {
				"data" : "uptime"
			} ],
  			"dom": 	"<'row'<'col-xs-4'l><'#toolbar.col-xs-4'><'col-xs-4'f>r>" + 
					"t" +
					"<'row'<'col-xs-6'i><'col-xs-6'p>>",
			initComplete: function () {
				$("#toolbar").append('<label>refresh</label>');
				$("#toolbar").append('<input id="time" value="20" "></input>');
				$("#toolbar").append('<label>seconds</label>');
				$("#time").on('input', function () {
					var time = $("#time").val();
					if (id != null && id != undefined) {
						clearInterval(id);
					}
					id = setInterval(function() {
						table.ajax.reload(null, false); // user paging is not reset on reload
					}, time * 1000 );
				} );
				var id = setInterval(function() {
					table.ajax.reload(null, false); // user paging is not reset on reload
				}, $("#time").val() * 1000);
			}
		});
		new $.fn.dataTable.FixedHeader(table);
	});
</script>
<div class="col-sm-10">

	<table width="100%" class="display" id="serverTable" cellspacing="0">
		<thead>
			<tr>
				<th><fmt:message key="weblogic.name" /></th>
				<th><fmt:message key="weblogic.health" /></th>
				<th><fmt:message key="weblogic.clusterName" /></th>
				<th><fmt:message key="weblogic.machineName" /></th>
				<th><fmt:message key="weblogic.state" /></th>
				<th><fmt:message key="weblogic.uptime" /></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
