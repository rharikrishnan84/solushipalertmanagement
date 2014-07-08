<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

		<div id="div_added_prods_tbl">
		<table cellpadding="2" cellspacing="0" class="text_01" width="1050px">		
			<tr><td colspan="5" class="hdr" align="center"><mmr:message messageId="heading.list.products.added"/></td></tr>
				<tr>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.sl.no"/></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.product.name"/></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.product.line"/></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.shippingOrder.additionalServices.quantity"/></td>
					<td class="hdr_prod_summary"></td>
				</tr>		
			<s:iterator value="%{shippingOrder.warehouseProducts}" status="counterIndex">	
				<s:set name="prodId" value="%{shippingOrder.warehouseProducts[#counterIndex.index].productId}"/>
				<s:if test="#counterIndex.odd">
				<tr height="8px">
					<td class="odd" align="center"><b><s:property value="%{#counterIndex.index+1}"/></b></td>
			        <td class="odd" align="center"><s:property value="%{shippingOrder.warehouseProducts[#counterIndex.index].productName}"/></td>
			        <td class="odd" align="center"><s:property value="%{shippingOrder.warehouseProducts[#counterIndex.index].productLine.lineName}"/></td>
			        <td class="odd" align="center"><s:property value="shippingOrder.warehouseProducts[#counterIndex.index].orderedQuantity"/></td>
					<td class="odd" align="center">
					<s:a href="javascript: if(confirm('Do you really want to delete the added Product?')){deleteProductfromOrder(%{#counterIndex.index});}">
					<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" border="0">
					</s:a></td>
				</tr>	
				</s:if>
				<s:else>
					<tr height="8px">
					<td class="even" align="center"><b><s:property value="%{#counterIndex.index+1}"/></b></td>
			        <td class="even" align="center"><s:property value="%{shippingOrder.warehouseProducts[#counterIndex.index].productName}"/></td>
			        <td class="even" align="center"><s:property value="%{shippingOrder.warehouseProducts[#counterIndex.index].productLine.lineName}"/></td>
			        <td class="even" align="center"><s:property value="shippingOrder.warehouseProducts[#counterIndex.index].orderedQuantity"/></td>
					<td class="even" align="center">
					<s:a href="javascript: if(confirm('Do you really want to delete the added Product?')){deleteProductfromOrder(%{#counterIndex.index});}">
					<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" border="0">
					</s:a></td>
				</tr>
				</s:else>
			</s:iterator>		
		</table>
	</div>
	
