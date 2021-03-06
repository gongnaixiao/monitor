<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base" value="${pageContext.request.contextPath}" />

<!--
		<link rel="stylesheet" type="text/css"
			href="${base}/webjars/bootswatch/3.0.0/assets/bootstrap.min.css" />
		-->
<link rel="stylesheet" type="text/css"
	href="${base}/styles/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${base}/styles/style.css" />

<%-- <script type="text/javascript"
	src="${base}/webjars/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${base}/webjars/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${base}/webjars/jquery-cookie/1.3.1/jquery.cookie.js"></script> --%>

<script type="text/javascript" src="${base}/scripts/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="${base}/scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="${base}/scripts/bootstrap.min.js"></script>


<script type="text/javascript" src="${base}/scripts/script.js"></script>


<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="${base}/DataTables-1.10.6/media/css/jquery.dataTables.min.css">

<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="${base}/DataTables-1.10.6/media/js/jquery.dataTables.min.js"></script>
	
<link rel="stylesheet" type="text/css"
	href="${base}/FixedHeader-2.1.2/css/dataTables.fixedHeader.min.css">

<script type="text/javascript" charset="utf8"
	src="${base}/FixedHeader-2.1.2/js/dataTables.fixedHeader.min.js"></script>
