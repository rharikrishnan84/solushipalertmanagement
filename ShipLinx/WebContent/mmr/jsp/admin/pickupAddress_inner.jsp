<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


									
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.address1" name="pickup.address.address1"   />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.address2" name="pickup.address.address2"   value="%{pickup.address.address2}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select  name="pickup.address.countryCode" listKey="countryCode" listValue="countryName" list="#session.CountryList" 
												onchange="javascript:showStatePickup();"  id="firstBoxPickup" theme="simple"/>
											</div>
										</div>
									
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.email" name="pickup.address.emailAddress"   />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.phoneNo" name="pickup.address.phoneNo"   />
											</div>
										</div>
											<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.postalCode" onblur="javascript:getAddressSuggestPickup();"  id="fromPostalCode" name="pickup.address.postalCode"   value="%{pickup.address.postalCode}"/>
												<img id="loading-img-from" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pieces.number"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.quantity" name="pickup.quantity"   onkeypress="return typenumbers(event,\'0123456789\')"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.contactName" name="pickup.address.contactName"  />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.address.city" name="pickup.address.city"   value="%{pickup.address.city}"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="shippingOrder.shipFromId.residential"/></label>
											<div class="controlscheck"><span style="float:left;margin-top:2px">:</span>
												<s:checkbox  value="%{pickup.address.residential}"  name="pickup.address.residential" style="margin-left:9px;margin-top:5px"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.destination.country"/></label>
											<div class="controls"><span>:</span>
												<s:select  listKey="countryCode" listValue="countryName" name="pickup.destinationCountryCode" list="#session.CountryList" 
												id="firstBoxPickup" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="stateidP"><span>:</span>
												<s:select key="pickup.address.provinceCode" name="pickup.address.provinceCode"  
												 value="%{pickup.address.provinceCode}" 
												listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.pieces.oversize"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.oversizeQuantity" name="pickup.oversizeQuantity"   onkeypress="return typenumbers(event,\'0123456789\')"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.total.weight"/></label>
											<div class="controls"><span>:</span>
											<s:textfield  key="pickup.totalWeight" name="pickup.totalWeight"   onkeypress="return typenumbers(event,\'0123456789.\')"/>	
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.weight.unit"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{pickup.weightUnit}" name="pickup.weightUnit" list="{'LBS','KGS'}"  ></s:select>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pickup.trackingNumber"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.masterTrackingNum" name="pickup.masterTrackingNum"  />
											</div>
										</div>
										<div class="fieldsvvl">
											<label><mmr:message messageId="label.pickup.instructions"/></label>
											<div class="controlsvvl"><span>:</span>
												<s:textfield   rows="1" key="pickup.instructions" name="pickup.instructions"  />
											</div>
										</div>
								
</div>
