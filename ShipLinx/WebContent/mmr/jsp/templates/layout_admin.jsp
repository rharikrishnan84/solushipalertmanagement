
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<tiles:useAttribute name="moreCss" id="moreCss" classname="java.util.List" ignore="true"/>
<html>

<head>
<title><s:property value="%{#session.business.systemName}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma"        content="no-cache">
<meta http-equiv="Expires"       content="-1">

<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/shiplinx_page01_styles.css' includeContext="true"/>" />
	

<body>
<div id="wrapper">
	<table border="0">
	<tr><td>
			<tiles:insertAttribute name="header" />
	
			<tiles:insertAttribute name="topnav" />
	</td></tr>
	
		<td>
			<tiles:insertAttribute name="mainBody" />
		
		<div id="right_right">
			<tiles:insertAttribute name="rightMenu" />
		</div>
		</td>
		
		</tr>
	
	<tr><td>
			<tiles:insertAttribute  name="footer" />
			</td></tr>
	</table>
	</div>	
</body>

</html>
