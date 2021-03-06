<%@ include file="/common/taglibs.jsp"%>

<head>
<title>monitor</title>
<meta name="menu" content="MonitorMenu" />
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
		xmlHttp.open("GET", "/monitor/servicesInfo", true);
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

<div class="col-sm-10">
	<label><fmt:message key="monitor.refresh" /></label> <input id="time"
		type="time" value="5" onchange="refreshTime()"></input>
	<table id="dynamicUpdateArea"
		class="table table-condensed table-striped table-hover">
		<tr id="row0">
			<th>Name</th>
			<th>Path</th>
			<th>Service Type</th>
			<th>Avg. Resp. Time</th>
			<th>Min. Resp. Time</th>
			<th>Max. Resp. Time</th>
			<th>Messages</th>
			<th>Errors</th>
		</tr>
	</table>
</div>

<script type="text/javascript">
	id = setInterval("doStart()", document.getElementById("time").value * 1000);
</script>
