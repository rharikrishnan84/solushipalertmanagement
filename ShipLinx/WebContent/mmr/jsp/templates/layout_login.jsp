
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
	href="<s:url value='/mmr/styles/shiplinx_login02_styles.css' includeContext="true"/>" />
	

<body>
<div id="wrapper">
	<table border="0">
	<tr><td>
			<tiles:insertAttribute name="header" />
	
			<tiles:insertAttribute name="topnav" />
	</td></tr>
	<tr>
	<table border="0">
		<tr colspan="2">
			<td rowspan="2">
				<tiles:insertAttribute name="mainBody" />
			</td>
			<td>
			<div id="bottom_right">
				<tiles:insertAttribute name="rightMenu" />
				<div id="right">
				<div id="right_left">
					<tiles:insertAttribute name="rightMenu_bottom1" />
				</div>
				<div id="right_right">
					<tiles:insertAttribute name="rightMenu_bottom2" />
				</div>
			</div>
			</div>

			</td>
		</tr>
	</table>
	
	
	<tr><td>
			<tiles:insertAttribute  name="footer" />
			</td></tr>
	<table>
	<div>	
</body>

</html>
