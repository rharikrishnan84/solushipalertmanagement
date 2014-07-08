<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>


	<div id="prod_summ_lbl"><font class="hdr"><mmr:message messageId="label.inventory.information"/></font>
	</div>
		<div id="prod_summary_table">
			<table cellpadding="3" cellspacing="0" class="text_01" width="1060px">
				<tr>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.product.name"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.productName}"/></font></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.inventory"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.locationCount}"/></font></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.wip"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.wipCount}"/></font></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.Quarantine"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.quarantineCount}"/></font></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.inqueue"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.inqueueCount}"/></font></td>
					<td class="hdr_prod_summary" align="center"><mmr:message messageId="label.backorder"/>&nbsp;:&nbsp;<font class="hdr"><s:property value="%{shippingOrder.warehouseProduct.backOrderCount}"/></font></td>
					<td class="hdr_prod_summary">&nbsp;</td>
				</tr>				
		</table>
		</div>
