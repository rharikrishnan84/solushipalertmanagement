<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 




<!--  <div id="pckg_btns">
	<table style="margin-top:-3px;">
	<tr>
		<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0" style="margin-bottom:-4px;">&nbsp;</td>
		<td>
			<s:set name="packageTypeId" value="shippingOrder.packageTypeId.packageTypeId"/>
			<s:submit type="image" src="%{#session.ContextPath}/mmr/images/rate_list.png" onClick="return (validateOrder(3,1))" cssStyle="margin-bottom:-4px;"/> 
                  &nbsp;<a><mmr:message messageId="menu.getRates"/></a>
		</td>
		<td>&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0"  style="margin-bottom:-4px;">&nbsp;</td>
		<td><s:a href="javascript: showPackage('addressFrom');" class="prevTab"> <img src="<%=request.getContextPath()%>/mmr/images/back.png" border="0"  style="margin-bottom:-4px;"/>
                  &nbsp;<mmr:message messageId="label.navigation.back" /> </s:a></td>
	</tr>
</table>
</div>-->
<div class="content">
<div id="pckg_panel_hdr">
<div class="content_body" >
<div class="content_table">
	<div class="content_header">
		<div class="cont_hdr_title"><mmr:message messageId="label.shipping_order.additionalInformation"/>:</div>
	</div>
</div>
</div>
</div>

<div class="content_body" id="pckg_panel">	
							<div class="content_table">
								<div class="cont_data_body borderLeftRight">
									
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.package.servicetype"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_big"listKey="type" listValue="name" id="packType" name="shippingOrder.packageTypeId.type" 
			list="#session.listPackages" onchange="javascript:modifyQuantity();" headerKey="-1" theme="simple"/>
											</div>
										</div>
										<!--<div class="fields" >
											<label><mmr:message messageId="label.shippingOrder.additionalServices.quantity"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_big" name="shippingOrder.quantity" id="quantity" onchange="modifyQuantity()" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35}"  cssClass="text_01_combo_big"></s:select>
											</div>
										</div>-->
										
										<div class="fields">
										<label><mmr:message messageId="label.package.unitmeasure"/></label>
          								 <div class="controls"><span>:</span>
          								 <s:select id="measure" name="shippingOrder.unitmeasure"  list="#session.UOM" listKey="unitOfMeasureId" listValue="unitOfMeasure" disabled="false"/>
          								 </div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.dangerousGoods"/></label>
											<div class="controls"><span>:</span>
												<s:select id="dg_field" name="shippingOrder.dangerousGoods"  cssClass="text_01_combo_big" list="#session.DGList" listKey="dangerousgoodsId" listValue="dangerousGoodsName" disabled="false"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.track.reference.code"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.refCode" name="shippingOrder.referenceCode" cssClass="text_02_tf" value="%{shippingOrder.referenceCode}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.scheduledShipDate"/></label>
											<div class="controls"><span>:</span>
												<s:textfield name="shippingOrder.scheduledShipDate_web" id="f_date_c" size="10" 
				cssClass="text_02_tf" readonly="true" value="%{#session.shippingOrder.scheduledShipDate_web}" onClick="selectDate('f_date_c','f_trigger_c');"/>
											</div>
										</div>
										<s:set name="cName" value="%{#session.shippingOrder.fromAddress.countryName}"/>
			       <s:if test ='%{#cName ==  "CA"}'>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequiredCanada"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired" list="#{'1':'No','2':'Yes'}" cssStyle="width: 156px;" cssClass="text_01_combo_big" ></s:select>
											</div>
										</div>
										</s:if>
										<s:else>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequired"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired" list="#{'1':'No','2':'Del. Confirmation','3':'Signature','4':'Adult Signature'}" cssClass="text_01_combo_big" ></s:select>
											</div>
										</div>
										</s:else>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.dutiable.amount"/></label>
											<div class="controls"><span>:</span>
												<s:textfield id="shippingOrder.dutiableAmount" name="shippingOrder.dutiableAmount"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.dutiableAmount}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.holdForPickupRequired"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.holdForPickupRequired" value="%{#session.shippingOrder.holdForPickupRequired}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.satDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.satDelivery"  value="shippingOrder.satDelivery"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.docsOnly"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.docsOnly"  value="shippingOrder.docsOnly"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.tempControl"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.tempControl"  value="shippingOrder.tempControl"/>
											</div>
										</div>
										<div class="fields">
										<label><mmr:message messageId="label.shippingOrder.temperature"/></label>
										<div class="controls"><span>:</span>
												<s:textfield id="shippingOrder.temperature" name="shippingOrder.temperature"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{shippingOrder.temperature}"/>
											</div>
										</div>
										<%-- <div class="fields">
											<label><mmr:message messageId="label.viewship.link.to.shipid"/></label>
										<div class="controls"><span>:</span>
												<s:textfield id="shippingOrder.linkToOrder" name="shippingOrder.linkToOrder"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{shippingOrder.linkToOrder}"/>
											</div>
										</div>--%>
									</div> 
									
									<div class="rows" id="hide_this_one" style="display:none;">
										<div class="content_header">
											<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.additionalServices"/>:</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.tradeShowPickup"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.tradeShowPickup" value="%{#session.shippingOrder.tradeShowPickup}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.tradeShowDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.tradeShowDelivery"  value="shippingOrder.tradeShowDelivery"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.insidePickup"/>:</label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.fromAddressCheckList.insidePickup == 1}'>
												<s:checkbox name="shippingOrder.insidePickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.insidePickup" value="false"/>
											</s:else>											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.viewship.insideDelivery"/>:</label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.toAddressCheckList.insidePickup == 1}'>
												<s:checkbox name="shippingOrder.insideDelivery" value="true"/>
												</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.insideDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentPickup"/> </label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.fromAddressCheckList.pickupOrDeliver == 1}'>
												<s:checkbox name="shippingOrder.appointmentPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.appointmentPickup" value="false"/>
											</s:else>											</div>
 																				</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.toAddressCheckList.pickupOrDeliver == 1}'>
												<s:checkbox name="shippingOrder.appointmentDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.appointmentDelivery" value="false"/>
											</s:else>											</div>

										</div>
										<div class="fields" id="hide_this_three">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.fromTailgate"/>:</label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.fromAddressCheckList.powerTailgate == 1 || shippingOrder.fromAddressCheckList.description == "IC10009_1"}'>
												<s:checkbox name="shippingOrder.fromTailgate" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.fromTailgate" value="false"/>
											</s:else>											</div>
 										
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.toTailgate"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.toAddressCheckList.powerTailgate == 1 || shippingOrder.toAddressCheckList.description == "IC10009_2"}'>
												<s:checkbox name="shippingOrder.toTailgate" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.toTailgate" value="false"/>
											</s:else>												</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.viewship.fromPalletJack"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.fromAddressCheckList.palletJack == 1 || shippingOrder.fromAddressCheckList.description == "IC10008_1"}'>
												<s:checkbox name="shippingOrder.fromPalletJack" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.fromPalletJack" value="false"/>
											</s:else>											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.viewship.toPalletJack"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.toAddressCheckList.palletJack == 1 || shippingOrder.toAddressCheckList.description == "IC10008_2"}'>
												<s:checkbox name="shippingOrder.toPalletJack" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.toPalletJack" value="false"/>
											</s:else>											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.viewship.fromDockLevel"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.fromAddressCheckList.dockLevel == 1}'>
												<s:checkbox name="shippingOrder.fromDockLevel" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.fromDockLevel" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.viewship.toDockLevel"/></label>
											<div class="controls"><span>:</span>
												<s:if test='%{shippingOrder.toAddressCheckList.dockLevel == 1}'>
												<s:checkbox name="shippingOrder.toDockLevel" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.toDockLevel" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.worshipPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10001_1"}'>
												<s:checkbox name="shippingOrder.worshipPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.worshipPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.worshipDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10001_2"}'>
												<s:checkbox name="shippingOrder.worshipDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.worshipDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.utilityPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10002_1"}'>
												<s:checkbox name="shippingOrder.utilityPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.utilityPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.utilityDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10002_2"}'>
												<s:checkbox name="shippingOrder.utilityDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.utilityDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.governmentPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10003_1"}'>
												<s:checkbox name="shippingOrder.governmentPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.governmentPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.governmentDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10003_2"}'>
												<s:checkbox name="shippingOrder.governmentDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.governmentDelivery" value="false"/>
											</s:else>
											</div>
									</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.mineSitePickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10004_1"}'>
												<s:checkbox name="shippingOrder.mineSitePickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.mineSitePickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.mineSiteDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10004_2"}'>
												<s:checkbox name="shippingOrder.mineSiteDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.mineSiteDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.limitedAccessPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10005_1"}'>
												<s:checkbox name="shippingOrder.limitedAccessPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.limitedAccessPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.limitedAccessDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10005_2"}'>
												<s:checkbox name="shippingOrder.limitedAccessDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.limitedAccessDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.schoolPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10006_1"}'>
												<s:checkbox name="shippingOrder.schoolPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.schoolPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.schoolDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10006_2"}'>
												<s:checkbox name="shippingOrder.schoolDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.schoolDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.insideStreetPickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10007_1"}'>
												<s:checkbox name="shippingOrder.insideStreetPickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.insideStreetPickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.insideStreetDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10007_2"}'>
												<s:checkbox name="shippingOrder.insideStreetDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.insideStreetDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<%-- <div class="fields">
											<label><mmr:message messageId="label.shipping_order.liftGatePickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10009_1"}'>
												<s:checkbox name="shippingOrder.liftGatePickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.liftGatePickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.liftGateDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10009_2"}'>
												<s:checkbox name="shippingOrder.liftGateDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.liftGateDelivery" value="false"/>
											</s:else>
											</div>
										</div> --%>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.selfStoragePickup"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10010_1"}'>
												<s:checkbox name="shippingOrder.selfStoragePickup" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.selfStoragePickup" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.selfStorageDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.toAddressCheckList.description == "IC10010_2"}'>
												<s:checkbox name="shippingOrder.selfStorageDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.selfStorageDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.hazardousDelivery"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10011_0" || shippingOrder.toAddressCheckList.description == "IC10011_0"}'>
												<s:checkbox name="shippingOrder.hazardousDelivery" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.hazardousDelivery" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.protectFreeze"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10012_0" || shippingOrder.toAddressCheckList.description == "IC10012_0"}'>
												<s:checkbox name="shippingOrder.protectFreeze" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.protectFreeze" value="false"/>
											</s:else>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shipping_order.destinationNotify"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10013_0" || shippingOrder.toAddressCheckList.description == "IC10013_0"}'>
												<s:checkbox name="shippingOrder.destinationNotify" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.destinationNotify" value="false"/>
											</s:else>
											</div>
										</div>
										<%-- <div class="fields">
											<label><mmr:message messageId="label.shipping_order.correctionFee"/></label>
											<div class="controls"><span>:</span>
											<s:if test='%{shippingOrder.fromAddressCheckList.description == "IC10014_0" || shippingOrder.toAddressCheckList.description == "IC10014_0"}'>
												<s:checkbox name="shippingOrder.correctionFee" value="true"/>
											</s:if>
											<s:else>
												<s:checkbox name="shippingOrder.correctionFee" value="false"/>
											</s:else>
 											</div>
 										</div> --%>
											</div>
										</div>
									</div>
								</div>
							</div>
		<div>
</div>		
<!--   <div id="pckg_tab"><br/></div>-->



