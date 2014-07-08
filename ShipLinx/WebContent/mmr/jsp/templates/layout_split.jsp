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
	href="<s:url value='/mmr/styles/mainBlue.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<c:forEach var="css" items="${moreCss}">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>${css}" >	
</c:forEach>
<script type='text/javascript' src='<s:url value='/mmr/scripts/menu.js' includeContext="true"/>'></script>
<body>

<table id="page" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="3">
			<tiles:insertAttribute name="header" />
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="3" >
			<tiles:insertAttribute name="topnav" />
		</td>
	</tr>
	<tr>
		<td id="content" valign="top" width="80%">
			<table class="mainContainer" cellspacing="0">
				<tr>
					<td class="leftSection" valign="top">
						<tiles:insertAttribute name="leftMenu" />
					</td>
					<td class="contentContainer" width="40%" valign="top">
						<tiles:insertAttribute name="leftBody" />
					</td>
					<td class="contentContainer" width="40%" valign="top">
						<tiles:insertAttribute name="rightBody" />
					</td>
				</tr>
			</table>
		</td>
		<td id="content" align="left" valign="top" width="20%">
			<tiles:insertAttribute name="rightMenu" />
		</td>
	</tr>
	<tr >
		<td id="footer" colspan="3" align="right">
			<tiles:insertAttribute  name="footer" />
		</td>
	</tr>
</table>
</body>

</html>
