<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


	<div id="update_shipment">
		<div id="update_shipment_label">
		<font color="#000066" size="3"
				style="font-family: Arial; font-variant: small-caps; font-weight: bold; margin-left: 370px;"><mmr:message messageId="label.shippingOrderTab.order"/>&nbsp;#&nbsp;<s:property value="%{selectedOrder.id}" /></font>
		</div>
		<table width="900px">
			<tr>
				<td><font style="color: #000066; font-family: Arial; font-size: 13px; font-weight: bold;"><mmr:message messageId="label.fulfilled.warehouse"/>&nbsp;: </font></td>
				<td>&nbsp;</td>
				<td>
					<s:url id="ListAllWH" action="listAllWarehouses">
						<s:param name="any">any</s:param>
					</s:url>
					<s:if test="%{#request.def_wh!=null}">
						<sx:autocompleter id="warehouseNames" name="searchString" href="%{ListAllWH}" dataFieldName="WHSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  cssStyle="width:140px;" preload="true" valueNotifyTopics="loadLocations" keyName="toWareHouseKey" headerKey="-1" headerValue="--ANY--" cssClass="dojoComboBox" value="%{#request.def_wh}"/>
					</s:if>
					<s:else>
						<sx:autocompleter id="warehouseNames" name="searchString" href="%{ListAllWH}" dataFieldName="WHSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  cssStyle="width:140px;" preload="true" valueNotifyTopics="loadLocations" keyName="toWareHouseKey" headerKey="-1" headerValue="--ANY--" cssClass="dojoComboBox"/>
					</s:else>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<div id="summary_products_added">
		<s:iterator value="selectedOrder.warehouseProducts" status="counterpIndex">
		<s:set name="prod_index" value="%{#counterpIndex.index}"/>
		<s:set var="divid">
			inner_div_<s:property value="%{selectedOrder.warehouseProducts[#counterpIndex.index].productId}"/>
		</s:set>
		<s:set var="divclass">
			show_summ_<s:property value="%{#counterpIndex.index}"/>
		</s:set>
		<s:set var="divsummclass">
			summ_class_<s:property value="%{#counterpIndex.index}"/>
		</s:set>
		<div id="summary_product_each" class="${divsummclass}">
			<table width="300px">
						<tr>
							<td align="left"><font color="#000066"><s:property value="%{#counterpIndex.count}"/>&nbsp;:</font>&nbsp;<s:property value="%{selectedOrder.warehouseProducts[#counterpIndex.index].productName}"/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td align="right"><mmr:message messageId="label.shippingOrder.summary"/> &nbsp;<a href="#" class="${divclass}" onclick="showOrHideProductSummary('<s:property value="%{#counterpIndex.index}"/>','<s:property value="%{selectedOrder.warehouseProducts[#counterpIndex.index].productId}"/>')" style="text-decoration: none; color: #000066;">[&nbsp;+&nbsp;]</a></td>
						</tr>
			</table>
		</div>
		<div id="${divid}" style="display: none;">
			<s:include value="view_shipment_products_summary_inner.jsp"></s:include>
		</div>
		</s:iterator>
	</div>

