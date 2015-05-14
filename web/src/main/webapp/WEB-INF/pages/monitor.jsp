<%@ include file="/common/taglibs.jsp"%>

<head>
<title>monitor</title>
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
		xmlHttp.open("GET", "monitor/services", true);
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
					newRow.insertCell(0).innerHTML = list[j].name;
					newRow.insertCell(1).innerHTML = list[j].path;
					newRow.insertCell(2).innerHTML = list[j].serviceType;
					newRow.insertCell(3).innerHTML = list[j].avgRespTime;
					newRow.insertCell(4).innerHTML = list[j].minRespTime;
					newRow.insertCell(5).innerHTML = list[j].maxRespTime;
					newRow.insertCell(6).innerHTML = list[j].messagesCounts;
					newRow.insertCell(7).innerHTML = list[j].erroCounts;
					newRow.insertCell(8).innerHTML = list[j].successRate;
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

<body id="monitoredServices">
	<div class="col-sm-10">
		<fmt:message key="monitor.refresh" /> <input id="time"
			type="time" value="5" onchange="refreshTime()"></input>
		<table id="dynamicUpdateArea"
			class="table table-condensed table-striped table-hover">
			<tr id="row0">
				<th><fmt:message key="monitor.name" /></th>
				<th><fmt:message key="monitor.path" /></th>
				<th><fmt:message key="monitor.serviceType"/></th>
				<th><fmt:message key="monitor.avgRespTime"/></th>
				<th><fmt:message key="monitor.minRespTime"/></th>
				<th><fmt:message key="monitor.maxRespTime" /></th>
				<th><fmt:message key="monitor.messagesCounts" /></th>
				<th><fmt:message key="monitor.erroCounts" /></th>
				<th><fmt:message key="monitor.successRate" /></th>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		id = setInterval("doStart()",
				document.getElementById("time").value * 1000);
	</script>
</body>
