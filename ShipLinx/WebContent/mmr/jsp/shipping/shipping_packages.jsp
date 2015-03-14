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
		<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.additionalServices"/>:</div>
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
          								 <s:select id="measure" name="shippingOrder.unitmeasure"  list="#session.UOM" listKey="unitOfMeasureId" listValue="unitOfMeasure" disabled="false"/></td>
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
											<label><mmr:message messageId="label.viewship.link.to.shipid"/></label>
										<div class="controls"><span>:</span>
												<s:textfield id="shippingOrder.linkToOrder" name="shippingOrder.linkToOrder"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{shippingOrder.linkToOrder}"/>
											</div>
										</div>
									</div>
									
									<div class="rows" id="hide_this_one" style="display:none;">
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
												<s:checkbox name="shippingOrder.insidePickup"  value="shippingOrder.insidePickup"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentPickup"/> </label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.appointmentPickup" value="%{#session.shippingOrder.appointmentPickup}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.appointmentDelivery"  value="shippingOrder.appointmentDelivery"/>
											</div>
										</div>
										<div class="fields" id="hide_this_three">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.fromTailgate"/>:</label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.fromTailgate"  value="shippingOrder.fromTailgate"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.toTailgate"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.toTailgate" value="%{#session.shippingOrder.toTailgate}" />
											</div>
										</div>
									</div>
								</div>
							</div>
		<div>
</div>		
<!--   <div id="pckg_tab"><br/></div>-->



