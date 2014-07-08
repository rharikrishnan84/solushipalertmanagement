
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<tiles:useAttribute name="moreCss" id="moreCss" classname="java.util.List" ignore="true"/>

<%@page import="java.util.ArrayList"%>
<%@page import="com.meritconinc.mmr.model.common.MenuItemVO"%>
<%@page import="java.util.List"%>
<html>

<head>
<title><s:property value="%{#session.business.systemName}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="cache-control" content="no-store">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma"        content="no-cache">
<meta http-equiv="Expires"       content="-1">

<!--    <link rel="stylesheet" type="text/css" href="<s:url value='/mmr/styles/shiplinx_closeWindow_styles.css' includeContext="true"/>" /> -->
	
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/loadCSS.js"></script>
<%
String strHeaderKey = "";
strHeaderKey = String.valueOf(session.getAttribute("headerkey"));
System.out.println("strheaderkey is:"+strHeaderKey);
%>
<body>
<div id="wrapper">
	<table border="0">
	<tr><td>	
		<s:include value="%{#session.business.headerKey}"></s:include>	
	</td></tr>
	<tr><td>	
			<tiles:insertAttribute name="topnav" />
	</td></tr>
	<tr><td>
<!--  	<s:property value="%{#session.ROLE}"/>-->
	<s:if test="%{#session.ROLE.contains('sysadmin')}">sysadmin Login
	<%
		response.sendRedirect("addcustomer.action"); 

	%>
	</s:if>
	<s:if test="%{#session.ROLE.contains('admin')}">admin Login
	<%
	response.sendRedirect("admin/searchuser.action"); 

	%>
	</s:if>
	<s:if test="%{#session.ROLE.contains('public')}">User Login
	<%
	response.sendRedirect("new.shipment.action"); 

	%>
	
	</s:if>
	</td></tr>
	<tr><td>
	</td></tr>
	<tr><td>
	</td></tr>
	<tr><td>
	</td></tr>
	<tr><td height="400px;">
			
			</td></tr>
	<tr><td>	
			
	</td></tr>
	<tr><td>
			<tiles:insertAttribute  name="footer" />
			</td></tr>
	</table>
	</div>	
</body>

</html>
	