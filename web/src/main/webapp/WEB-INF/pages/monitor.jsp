<%@ include file="/common/taglibs.jsp"%>

<head>
<title></title>
<script>
	var xmlHttp;
	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function doStart() {
		createXMLHttpRequest();

		var queryString = "WS.asmx/DynamicUpdate?task=reset";
		xmlHttp.onreadystatechange = handleStateChange;
		xmlHttp.open("GET", queryString, true);
		xmlHttp.send(null);
	}
	function handleStateChange() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				setTimeout("pollServer", 5000);
				refreshTime();
			}
		}
	}
	function pollServer() {
		var queryString = "WS.asmx/DynamicUpdate?task=foo";
		xmlHttp.onreadystatechange = pollCallback;
		xmlHttp.open("GET", queryString, true);
		xmlHttp.send(null);
	}
	function refreshTime() {
		var time_span = document.getElementById("time");
		var time_val = time_span.innerHTML;
		var int_val = parseInt(time_val);
		var new_int_val = int_val - 1;
		if (new_int_val > -1) {
			setTimeout("refreshTime()", 1000);
			time_span.innerHTML = new_int_val;
		} else {
			time_span.innerHTML = 5
		}
	}

	function pollCallback() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var message = xmlHttp.responseXML
						.getElementsByTagName("message")[0].firstChild.data;
				if (message == "done") {
					var new_row = createRow(message);
					var table = document.getElementById("dynamicUpdateArea");
					var table_body = table.getElementsByTagName("tbody")
							.item(0);
					var first_row = table_body.getElementsByTagName("tr").item(
							1);
					table_body.insertBefore(new_row, first_row);
					setTimeout("pollServer", 5000);
					refreshTime();

				}
			}
		}
	}
	function createRow(message) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		var cell_data = document.createTextNode(message);
		cell.appendChild(cell_data);
		return row;
	}
</script>
</head>

<body id="monitoredServices">

	<div class="col-sm-10"></div>
</body>
