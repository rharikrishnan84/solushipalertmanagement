<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<script>
	function toggleAddresschecklist() {
		var contentBtnId = document.getElementById("addressChkList2");
		// Toggle 
		addressChkList2.style.display == "block" ? addressChkList2.style.display = "none"
				: addressChkList2.style.display = "block";
	}
</script>

									
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.toAddress.address1" name="shippingOrder.toAddress.address1"  cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.toAddress.address2" name="shippingOrder.toAddress.address2"  cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.postalCode" onblur="javascript:getAddressSuggestTo();"  id="toPostalCode" name="shippingOrder.toAddress.postalCode"  cssClass="text_02_tf" />
        	<img id="loading-img-to" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01"  listKey="countryCode" listValue="countryName" name="shippingOrder.toAddress.countryCode" list="#session.CountryList" onchange="javascript:showShipToState();" headerKey="-1"  id="firstBox2" theme="simple"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="stateid2">
												<jsp:include page="../admin/shippingToProvienceList.jsp"/>
												
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" id="tocity" key="shippingOrder.toAddress.city" name="shippingOrder.toAddress.city"  cssClass="text_02_tf"/> 
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.toAddress.phoneNo" name="shippingOrder.toAddress.phoneNo"   cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.email" name="shippingOrder.toAddress.emailAddress"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.toAddress.contactName" name="shippingOrder.toAddress.contactName"  cssClass="text_02_tf"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.Instructions"/></label>
											<div class="controls"><span>:</span>
												<s:textarea  key="shippingOrder.toAddress.instruction" name="shippingOrder.toInstructions" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="shippingOrder.shipToId.residential"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.toAddress.residential}"  name="shippingOrder.toAddress.residential"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.notify.consignee"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox  cssClass="text_01" value="%{shippingOrder.toAddress.sendNotification}"  name="shippingOrder.toAddress.sendNotification"/>
										</div>
									</div>
							<div class="adressCheckList">
	<div class="content_header">
		<s:if test="shippingOrder.toAddressCheckList.checkListActivated==true">
			<div class="cont_hdrtitle_L" style="margin-left: 200px;">The
				Address Checklist has been completed for this address.</div>
		</s:if>
		<s:else>
			<div class="cont_hdrtitle_L" style="margin-left: 200px;">The
				Address Checklist has not been completed for this address.</div>
		</s:else>
		<div class="form_buttons">
			<a href="javascript:return false;" id="addressChkListBtn2"
				onclick="toggleAddresschecklist();">ADDRESS CHECKLIST</a>
		</div>
	</div>

	<div class="rows" id="addressChkList2" style="display: none">
		<div class="fields" style="width: 330px !important;">
			<label style="width: 230px !important;">Is this address a
				commercial business?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.commercialBusiness}"
					name="shippingOrder.toAddressCheckList.commercialBusiness" />
			</div>
		</div>
		<div class="fields" style="width: 600px !important;">
			<label style="width: 400px !important;">If not please select
				the most appropriate description of this address type</label>
			<div class="controls" style="width: 200px !important;">
				<span>:</span>
				<s:select cssClass="text_01" listKey="chargeCode"
					listValue="chargeName"
					name="shippingOrder.toAddressCheckList.description"
					list="#session.AccessorialServicesListTo" headerKey=""
					headerValue="" id="shippingOrder.toAddressCheckList.description"
					theme="simple" />
			</div>
		</div>
		<div class="fields" style="width: 415px !important;">
			<label style="width: 350px !important;">Is an appointment
				required to pickup or deliver to the address?</label>
			<div class="controls" style="width: 60px !important;">
			<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.pickupOrDeliver}"
					name="shippingOrder.toAddressCheckList.pickupOrDeliver" />
			</div>
		</div>
		<div class="fields" style="width: 520px !important;">
		<label style="width: 390px !important;">Must someone be
				notified to prior to pickup of deliver to this address?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.priorToPickup}"
					name="shippingOrder.toAddressCheckList.priorToPickup" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;">
			<label style="width: 200px !important;">Does this facility
				have a dock level?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.dockLevel}"
					name="shippingOrder.toAddressCheckList.dockLevel" />
			</div>
		</div>
		<div class="fields" style="width: 227px !important;">
			<label style="width: 156px !important;">Is a pallet jack
				required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.palletJack}"
					name="shippingOrder.toAddressCheckList.palletJack" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;">
			<label style="width: 188px !important;">Is a power tailgate
				required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.powerTailgate}"
					name="shippingOrder.toAddressCheckList.powerTailgate" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;"></div>
		<div class="fields" style="width: 290px !important;">
			<label style="width: 222px !important;">Is a inside pickup or
				delivery required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.toAddressCheckList.insidePickup}"
					name="shippingOrder.toAddressCheckList.insidePickup" />
			</div>
		</div>
		<div class="fields" style="width: 350px !important;">
			<label style="width: 281px !important;">If so what floor is
				the pickup or delivery required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>:</span>
				<s:select cssClass="text_01_combo_small" style="width: 39px !important"
					name="shippingOrder.toAddressCheckList.floorNo"
					list="#{'-1':'','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}"
					headerKey="-1" id="shippingOrder.toAddressCheckList.floorNo"
					theme="simple" />
			</div>
		</div>

		<div class="content_table "
			style="overflow: auto; width: 958px !important; border-right: 1px solid #c4c2c2; padding: 0px 0px 10px 0px;">
			<div class="form_buttons" id="img_get_rates"
				style="width: 100px; float: right !important;">
				<s:hidden name="toAddressId" id="toAddressId"
					value="%{shippingOrder.toAddress.addressId}" />
				<div align="left" style="float: left !important;" id="get_rates_td">
					<a href="javascript:toUpdateCheckList()"
						onclick="return (validateOrder(3,1))">UPDATE</a>
				</div>
			</div>
	</div>

	</div>
</div>

				