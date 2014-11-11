<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



									
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
								
				