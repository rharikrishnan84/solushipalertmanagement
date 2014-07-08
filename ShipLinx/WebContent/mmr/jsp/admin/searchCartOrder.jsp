<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<sx:head />
<title><s:text name="customer.search.title" /></title>
</head>
<body>
<SCRIPT language="JavaScript">

	function saveShipment() {
		document.searchCartOrderForm.action = "createShipments.action";
		document.searchCartOrderForm.submit();
	}	
	
	function processNow() {
		document.searchCartOrderForm.action = "processShipmentNow.action";
		document.searchCartOrderForm.submit();
	}
	
	 function showState() {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function()
		  {
			   if(ajax_Service.readyState==4)
				{
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById("stateid");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox');
		  url="<%=request.getContextPath()%>/markup.listService.action?value="+firstBox.value;
		  	ajax_Service.open("GET",url,true);
		  	ajax_Service.send(this);
	} // End function showState()	

</SCRIPT>
<div id="messages"><jsp:include
	page="../common/action_messages.jsp" /></div>

<div class="form-container"><s:form action="searchCart"
	name="searchCartOrderForm">

	<s:token />
	<div id="unshippedOrder_panel">
	<table>
		<tr>
			<td><mmr:message messageId="unshipped.cart.order" /></td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>

	<div id="unshippedOrder_imgs">
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><img
				src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />"
				border="0">&nbsp;</td>
			<td><s:if
				test="%{#request.fromCart != null && #request.fromCart == 'cartCreateShipment'}">
				<img
					src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />"
					border="0">
			</s:if> <s:else>
				<img
					src="<s:url value="/mmr/images/process_now.png" includeContext="true" />"
					border="0">
			</s:else>&nbsp;</td>
		</tr>
	</table>
	</div>
	<div id="unshippedOrder_actions">
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

			<td><s:if
				test="%{#request.fromCart != null && #request.fromCart == 'cartCreateShipment'}">
				<a href="javascript: saveShipment()"><mmr:message
					messageId="label.create.shipments" /></a>
			</s:if> <s:else>
				<a href="javascript: processNow()"><mmr:message
					messageId="label.process.shipments" /></a>
			</s:else></td>
		</tr>
	</table>
	</div>

	<div id="unshippedOrder_srch_table">
	<table width="1090px" border="0" cellpadding="4" cellspacing="0"
		style="margin-top: 2px; margin-left: 50px;">
		<tr>

			<s:if
				test="%{#request.fromCart != null && #request.fromCart == 'cartCreateShipment'}">
				<td width="50%">&nbsp;</td>
				<td width="50%">&nbsp;</td>
				<td width="50%">&nbsp;</td>
				<td width="50%">&nbsp;</td>
			</s:if>
			<s:else>
				<td class="hdr" width="7%"><strong><mmr:message
					messageId="label.track.carrier" />:</strong></td>
				<td class="text_01" width="15%"><s:select
					cssClass="text_01_combo_big" cssStyle="width:135px;" listKey="id"
					listValue="name" name="cart.carrierId" headerKey="" headerValue=""
					list="#session.CARRIERS" onchange="javascript:showState();"
					id="firstBox" theme="simple" /></td>
				<td class="hdr" width="7%"><strong><mmr:message
					messageId="label.markup.service" />:</strong></td>
				<td class="text_01" id="stateid" width="15%"><s:select
					cssClass="text_01_combo_big" listKey="id"
					listValue="name" name="cart.serviceId" list="#session.SERVICES"
					headerKey="-1" id="secondBox" theme="simple" /></td>
			</s:else>

			<td width="50%">&nbsp;</td>
		</tr>
		<tr>
			<s:if test="%{#request.fromCart != null && #request.fromCart == 'cartCreateShipment'}">
				<td class=unshipperOrder_note colspan="5" align="center"><strong><mmr:message
					messageId="unshipped.order.create.shipment.msg" /></strong></td>
			</s:if>
			<s:else>
				<td class=unshipperOrder_note colspan="5"><strong><mmr:message
					messageId="unshipped.order.process.shipment.msg" /></strong></td>
			</s:else>
		</tr>
	</table>
	</div>
	<div id="cartformResult">
	<s:include value="/mmr/jsp/shipping/list_shipment.jsp"></s:include>
	</div>

</s:form></div>