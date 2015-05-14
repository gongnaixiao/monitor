<%@ include file="/common/taglibs.jsp"%>

<head>
<title>esbtrace</title>
<script>
	var xmlHttp;
	var id;
	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlHttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function doStart() {
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = pollCallback;
		xmlHttp.open("GET", "monitor/esbtrace", true);
		xmlHttp.send();
	}

	function pollCallback() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				var list = JSON.parse(text);
				var table = document.getElementById("dynamicUpdateArea");
				var rowCount = table.rows.length;
				if (rowCount > 1) {
					for (var i = 1; i < rowCount; i++) {
						table.deleteRow(1);
					}
				}

				for (var j = 0; j < list.length; j++) {
					var newRow = table.insertRow(j + 1);
					newRow.insertCell(0).innerHTML = list[j].csmId;
					newRow.insertCell(1).innerHTML = list[j].esbTmsIn;
					newRow.insertCell(2).innerHTML = list[j].csmSeqNo;
					newRow.insertCell(3).innerHTML = list[j].svcId;
					newRow.insertCell(4).innerHTML = list[j].pvdId;
					newRow.insertCell(5).innerHTML = list[j].esbTmsOut;
					newRow.insertCell(6).innerHTML = list[j].rspCode;
				}
			}
		}
	}

	function refreshTime() {
		var time = document.getElementById("time").value;
		if (id != null && id != undefined) {
			clearInterval(id);
		}
		id = setInterval("doStart()", time * 1000);
	}
</script>
</head>

<body id="monitoredEsbTrace">
	<div class="col-sm-10">
		<label><fmt:message key="monitor.refresh" /></label> <input id="time"
			type="time" value="5" onchange="refreshTime()"></input>
		<table id="dynamicUpdateArea"
			class="table table-condensed table-striped table-hover">
			<tr id="row0">
				<th><fmt:message key="esbtrace.csmid" /></th>
				<th><fmt:message key="esbtrace.esbtmsin" /></th>
				<th><fmt:message key="esbtrace.csmseqno" /></th>
				<th><fmt:message key="esbtrace.svcid" /></th>
				<th><fmt:message key="esbtrace.pvdid" /></th>
				<th><fmt:message key="esbtrace.esbtmsout" /></th>
				<th><fmt:message key="esbtrace.repifn" /></th>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		id = setInterval("doStart()",
				document.getElementById("time").value * 1000);
	</script>
</body>
