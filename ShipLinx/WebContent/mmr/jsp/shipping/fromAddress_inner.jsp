<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<script>
function toggleFromAddresschecklist(){
	var contentBtnId = document.getElementById("addressCheckList");
	// Toggle 
	contentBtnId.style.display == "block" ? contentBtnId.style.display = "none" : contentBtnId.style.display = "block"; 
}
</script>							
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.fromAddress.address1" name="shippingOrder.fromAddress.address1"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.fromAddress.address2" name="shippingOrder.fromAddress.address2"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.address2}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.postalCode" onblur="javascript:getAddressSuggestFrom();"  id="fromPostalCode" name="shippingOrder.fromAddress.postalCode"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.postalCode}"/>
	    	<img id="loading-img-from" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="shippingOrder.fromAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
	                  onchange="javascript:showShipFromState();"  id="firstBox"  theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="stateid" > 
												<jsp:include page="../admin/shippingFromProvienceList.jsp"/>
												</div>
											</div>
											 <div class="fields">
           <label><mmr:message messageId="label.shippingOrder.city"/></label>
           <div class="controls"> <span>:</span>
            <s:textfield size="20" id = "fromcity" key="shippingOrder.fromAddress.city" name="shippingOrder.fromAddress.city"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.city}"/>
            </div>
           </div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.fromAddress.phoneNo" name="shippingOrder.fromAddress.phoneNo"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.email" name="shippingOrder.fromAddress.emailAddress"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.fromAddress.contactName" name="shippingOrder.fromAddress.contactName"  cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.Instructions"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  rows="1" key="shippingOrder.fromAddress.instruction" name="shippingOrder.fromInstructions"  cssClass="text_02"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="shippingOrder.shipFromId.residential"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.fromAddress.residential}"  name="shippingOrder.fromAddress.residential"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.notify.shipper"/> </label>
											<div class="controls"><span>:</span>
												<s:checkbox  cssClass="text_01" value="%{shippingOrder.fromAddress.sendNotification}"  name="shippingOrder.fromAddress.sendNotification"/>
											</div>
										</div>
<div class="adressCheckList">
	<div class="content_header">
		<s:if
			test="shippingOrder.fromAddressCheckList.checkListActivated==true">
			<div class="cont_hdrtitle_L" style="margin-left: 200px;">The
				Address Checklist has been completed for this address.</div>
		</s:if>
		<s:else>
			<div class="cont_hdrtitle_L" style="margin-left: 200px;">The
				Address Checklist has not been completed for this address.</div>
		</s:else>

		<div class="form_buttons">
			<a href="javascript:return false;" id="addressChkListBtn"
				onclick="toggleFromAddresschecklist();">ADDRESS CHECKLIST</a>
		</div>
	</div>

	<div class="rows" id="addressCheckList" style="display: none;">
		<div class="fields" style="width: 330px !important;">
			<label style="width: 230px !important;">Is this address a
				commercial business?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.commercialBusiness}"
					name="shippingOrder.fromAddressCheckList.commercialBusiness" />
			</div>
		</div>
		<div class="fields" style="width: 600px !important;">
			<label style="width: 400px !important;">If not please select
				the most appropriate description of this address type</label>
			<div class="controls" style="width: 200px !important;">
			<span>:</span>
				<s:select cssClass="text_01" listKey="chargeCode"
					listValue="chargeName"
					name="shippingOrder.fromAddressCheckList.description"
					list="#session.AccessorialServicesListFrom" headerKey=""
					headerValue="" id="shippingOrder.fromAddressCheckList.description"
					theme="simple" />
			</div>
		</div>

		<div class="fields" style="width: 415px !important;">
			<label style="width: 350px !important;">Is an appointment
				required to pickup or deliver to the address?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.pickupOrDeliver}"
					name="shippingOrder.fromAddressCheckList.pickupOrDeliver" />
			</div>
		</div>
		<div class="fields" style="width: 520px !important;">
			<label style="width: 390px !important;">Must someone be
				notified to prior to pickup of deliver to this address?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.priorToPickup}"
					name="shippingOrder.fromAddressCheckList.priorToPickup" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;">
			<label style="width: 200px !important;">Does this facility
				have a dock level?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.dockLevel}"
					name="shippingOrder.fromAddressCheckList.dockLevel" />
			</div>
		</div>
		<div class="fields" style="width: 227px !important;">
			<label style="width: 156px !important;">Is a pallet jack
				required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.palletJack}"
					name="shippingOrder.fromAddressCheckList.palletJack" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;">
			<label style="width: 188px !important;">Is a power tailgate
				required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.powerTailgate}"
					name="shippingOrder.fromAddressCheckList.powerTailgate" />
			</div>
		</div>
		<div class="fields" style="width: 270px !important;"></div>
	<div class="fields" style="width: 290px !important;">
			<label style="width: 222px !important;">Is a inside pickup or
				delivery required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>Yes :</span>
				<s:checkbox cssClass="text_01"
					value="%{shippingOrder.fromAddressCheckList.insidePickup}"
					name="shippingOrder.fromAddressCheckList.insidePickup" />
			</div>
		</div>
		<div class="fields" style="width: 350px !important;">
			<label style="width: 281px !important;">If so what floor is
				the pickup or delivery required?</label>
			<div class="controls" style="width: 60px !important;">
				<span>:</span>

				<s:select cssClass="text_01_combo_small" style="width: 39px !important"  name="shippingOrder.fromAddressCheckList.floorNo"
					list="#{'-1':'','0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}"
					headerKey="-1" id="shippingOrder.fromAddressCheckList.floorNo"
					theme="simple" />
			</div>
		</div>
		<div class="content_table "
			style="overflow: auto; width: 958px !important; border-right: 1px solid #c4c2c2; padding: 0px 0px 10px 0px;">
			<div class="form_buttons" id="img_get_rates"
				style="width: 100px; float: right !important;">
				<s:hidden name="fromAddressId" id="fromAddressId"
					value="%{shippingOrder.fromAddress.addressId}" />
				<div align="left" style="float: left !important;" id="get_rates_td">
					<a href="javascript:formUpdateCheckList()"
						onclick="return (validateOrder(3,1))">UPDATE</a>
				</div>
			</div>
		</div>

	</div>
</div>
										