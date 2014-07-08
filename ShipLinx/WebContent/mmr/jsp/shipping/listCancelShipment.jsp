<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html> 
<head> 
    <title></title> 
</head> 
<body> 
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form>
<div id="right_left_new">
<div id="bottom_tabs_new">
		 <table width="50%" border="0" cellspacing="0" cellpadding="0">

						<tr>
		 			  <td width="10%" align="right" ><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"/></td>
			 			  <td width="70%"  align="center" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="#">Cancel/Edit Shipment</a></td>
			  <td width="5%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			  <td width="15%" >&nbsp;</td>
			  
			  
			</tr>
			
		  </table>
	</div>

<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="5" cellspacing="0">
	<tr>
	<td class="text_01" width="7%" align="left" valign="top"></td>
		<td class="text_01" width="13%" align="left" valign="top"><strong><mmr:message messageId="label.track.reference.code"/></strong></td>
		<td class="text_01" width="15%" align="left" valign="top"><strong><mmr:message messageId="label.track.carrier"/></strong></td>
		<td class="text_01" width="15%" align="left" valign="top"><strong><mmr:message messageId="label.track.service"/></strong></td>
		<td class="text_01" width="10%" align="left" valign="top" > <strong>Ship Date</strong></td>
		<td class="text_01" width="20%" align="left" valign="top"><strong><mmr:message messageId="label.track.shipping.from"/></strong></td>
		<td class="text_01" width="20%" align="left" valign="top"><strong><mmr:message messageId="label.track.shipping.to"/></strong></td>

		</tr>
 <s:if test="#session.TODAYSORDERLISTSIZE > 0 ">		
          <s:iterator id="customertable" value="orderList" status="rowstatus">
            <tr>
			<TD width="7%" align="left" valign="top">
			
			<s:a href="edit.shipment.action?order_id=%{id}"> <img src="<s:url value="/mmr/images/edit.png" includeContext="true" />" alt="Edit Shipment" border="0"> </s:a>
            <s:a onclick="return confirm('Do you really want to delete the selected Shipment?')" href="delete.shipment.action?order_id=%{id}"> <img src="<s:url value="/mmr/images/delete.png" includeContext="true" />" alt="Delete Shipment" border="0"> </s:a>
			</TD>
                    <td width="13%" class="text_01" align="left" valign="top"><s:property value="referenceCode"/></td>
                    <td width="15%" class="text_01" align="left" valign="top"><s:property value="carrierName"/></td>
                    <td width="15%" class="text_01" align="left" valign="top"><s:property value="serviceName"/></td>
                    <td width="10%" class="text_01" align="left" valign="top"><s:property value="scheduledShipDate"/></td>
                    <td width="20%" class="text_01" align="left" valign="top"><s:property value="shippingFromAddress"/></td>
                    <td width="20%" class="text_01" align="left" valign="top"><s:property value="shippingToAddress"/></td>
			</tr>
            </s:iterator>
 </s:if>
</table>
</div>

  </div>
  
</s:form>
</div>