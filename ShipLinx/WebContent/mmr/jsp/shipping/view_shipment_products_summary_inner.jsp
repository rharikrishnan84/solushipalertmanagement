<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table cellpadding="2" cellspacing="0" style="border: 1px solid #000000;" width="974px">
	<tr>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.warehouse.name"/></td>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.location.name"/></td>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.locationtype.name"/></td>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.batch.num"/></td>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.customer.dateCreated"/></td>
		<td class="tableTitle2_wh_summary_hdr" align="center"><mmr:message messageId="label.shippingOrder.additionalServices.quantity"/></td>
		<s:if test="%{#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">
				<td class="tableTitle2_wh_summary_hdr" width="30%">&nbsp;</td>
				<td class="tableTitle2_wh_summary_hdr" width="6%">&nbsp;</td>
				<td class="tableTitle2_wh_summary_hdr" width="5%">&nbsp;</td>
		</s:if>
	</tr>
	<s:iterator value="warehouseInventoryList" status="counterIndex">
	<s:set var="batchid">
			b_<s:property value="%{warehouseInventoryList[#counterIndex.index].productId}"/>_<s:property value="%{#counterIndex.index}"/>
	</s:set>
	<s:if test="#counterIndex.odd">
	 <s:hidden name="wh_id" value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseId}" />
		<tr>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseName}"/></td>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.locationName}"/></td>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeName}"/></td>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].batchNum}"/></td>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].dateCreated}"/></td>
			<td class="odd" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].quantity}"/></td>
			<s:if test="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeId == 1 && (#session.ROLE.contains('busadmin')||  #session.ROLE.contains('sysadmin'))}">
				<td class="odd" align="center" valign="middle" width="30%">
					<mmr:message messageId="label.move"/>&nbsp; <s:textfield id="qty_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" cssClass="text_02_tf_small" onkeypress="return typenumbers(event,\'0123456789\')" theme="simple" cssStyle="width: 40px;"/>
					<mmr:message messageId="label.units.to.wip"/>&nbsp;
					<s:if test="%{warehouseLocationList.size > 0}">
						<s:select id="wiplocs_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="%{warehouseInventoryList[#counterIndex.index].mapWHWIPLocations}" listKey="locationId" listValue="locationName" headerValue="--Select--" headerKey="-1" onchange="populateBatches('%{warehouseInventoryList[#counterIndex.index].productId}','%{#counterIndex.index}');" />
					</s:if>
					&nbsp;<mmr:message messageId="label.of.batch"/>
				 </td>
				<td id="${batchid}" class="odd" align="center" width="6%">
						<s:select id="batches_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" name="batchNum" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches"  listKey="batchNum" listValue="batchNum" headerValue="--Select--" headerKey="-1" />
				</td>
				 <td class="odd" align="center" width="5%">
				 <img src="<%=request.getContextPath()%>/mmr/images/move_new_small_btn.png" border="0" style="cursor: pointer;" onclick="moveProductToWIP('<s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseId}"/>','<s:property value="%{#counterIndex.index}"/>','<s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.locationId}"/>','<s:property value="%{warehouseInventoryList[#counterIndex.index].batchNum}"/>');"/>
				 </td>
			</s:if>
			<s:elseif test="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeId != 1 && (#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin'))}">
				<td class="odd" width="28%">&nbsp;</td>
				<td class="odd" width="7%">&nbsp;</td>
				<td class="odd" width="5%">&nbsp;</td>
			</s:elseif>
		</tr>
	</s:if>
	<s:else>
	 <s:hidden name="wh_id" value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseId}" />
		<tr>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseName}"/></td>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.locationName}"/></td>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeName}"/></td>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].batchNum}"/></td>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].dateCreated}"/></td>
			<td class="even" align="center"><s:property value="%{warehouseInventoryList[#counterIndex.index].quantity}"/></td>
			<s:if test="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeId == 1 && (#session.ROLE.contains('busadmin')||  #session.ROLE.contains('sysadmin'))}">
				<td class="even" align="center" valign="middle" width="30%">
					<mmr:message messageId="label.move"/>&nbsp; <s:textfield id="qty_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" cssClass="text_02_tf_small" onkeypress="return typenumbers(event,\'0123456789\')" theme="simple" cssStyle="width: 40px;"/>
					<mmr:message messageId="label.units.to.wip"/>&nbsp;
					<s:if test="%{warehouseLocationList.size > 0}">
						<s:select id="wiplocs_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="%{warehouseInventoryList[#counterIndex.index].mapWHWIPLocations}" listKey="locationId" listValue="locationName" headerValue="--Select--" headerKey="-1" onchange="populateBatches('%{warehouseInventoryList[#counterIndex.index].productId}','%{#counterIndex.index}');" />
					</s:if>
					&nbsp;<mmr:message messageId="label.of.batch"/>
				 </td>
				<td id="${batchid}" class="even" align="center" width="6%">
						<s:select id="batches_%{warehouseInventoryList[#counterIndex.index].productId}_%{#counterIndex.index}" name="batchNum" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches"  listKey="batchNum" listValue="batchNum" headerValue="--Select--" headerKey="-1"/>
				</td>
				 <td class="even" align="center" width="7%">
				 <img src="<%=request.getContextPath()%>/mmr/images/move_new_small_btn.png" border="0" style="cursor: pointer;" onclick="moveProductToWIP('<s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouse.warehouseId}"/>','<s:property value="%{#counterIndex.index}"/>','<s:property value="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.locationId}"/>','<s:property value="%{warehouseInventoryList[#counterIndex.index].batchNum}"/>');"/>
				 </td>
			</s:if>
			<s:elseif test="%{warehouseInventoryList[#counterIndex.index].warehouseLocation.warehouseLocationType.warehouseLocationTypeId != 1 && (#session.ROLE.contains('busadmin')||  #session.ROLE.contains('sysadmin'))}">
				<td class="even" width="28%">&nbsp;</td>
				<td class="even" width="7%">&nbsp;</td>
				<td class="even" width="8%">&nbsp;</td>
			</s:elseif>
	</tr>
	</s:else>
	</s:iterator>
</table>

