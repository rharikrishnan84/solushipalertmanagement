<%@ taglib prefix="s" uri="/struts-tags" %>
<html> 
<body> 
	<table width="100%" border="0" cellpadding="5" cellspacing="0"  style="margin-top:2px;">
		<tr>
			<td class="text_03">From Date:</td>
			<td><s:label key="shippingOrder.fromDate"/></td>
			<td class="text_03">To Date:</td>
			<td><s:label key="shippingOrder.toDate"/></td>
            <td class="text_03">Tracking #:</td>  
			<td><s:label key="shippingOrder.masterTrackingNumber"/></td>
            <td class="text_03">Shipment Order #:</td>  
            <td><s:label key="shippingOrder.id"/></td>
		</tr>	
		<tr>					
			<td class="text_03"><strong><mmr:message messageId="label.track.carrier"/></strong></td>
			<td><s:label key="shippingOrder.carrierId"/></td>
			<td class="text_03"><strong><mmr:message messageId="label.markup.service"/></strong></td>
			<td><s:label key="shippingOrder.serviceId"/></td>
			<td class="text_03"><strong><mmr:message messageId="label.customer.name" /></strong></td>
        	<s:url id="customerList" action="listCustomers" />
        	<td><s:label key="shippingOrder.customerId"/></td>
		</tr>	
	</table>
</body>
</html>
