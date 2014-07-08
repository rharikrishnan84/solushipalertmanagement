
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<title><s:property value="%{#session.business.systemName}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma"        content="no-cache">
<meta http-equiv="Expires"       content="-1">

<link rel="icon" href="<s:url value='/mmr/images/icons/td.ico' includeContext="true"/>" type="image/x-icon" />
<link rel="shortcut icon" href="<s:url value='/mmr/images/icons/td.ico' includeContext="true"/>" type="image/x-icon" />

<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/welcome.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/search.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/calendar.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/about_us.css' includeContext="true"/>" />

</head>

<body>

<table id="page" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="topNavContainer" width="100%"colspan="2">
			<tiles:insertAttribute name="header" />
		</td>
	</tr>
	<tr>
		<td class="topNavContainer" width="100%" colspan="2">
			<tiles:insertAttribute name="topnav" />
		</td>
	</tr>
	<tr>
		<td id="content" valign="top" width="80%">
			<tiles:insertAttribute name="mainBody" />
		</td>
		<td class="rightNavContainer" align="left" valign="top" width="20%">
			<tiles:insertAttribute name="rightMenu" />
		</td>
	</tr>
	<tr>
		<td class="footerContainer" colspan="2" align="right">
			<tiles:insertAttribute name="footer" />
		</td>
	</tr>
</table>
</body>

</html>
