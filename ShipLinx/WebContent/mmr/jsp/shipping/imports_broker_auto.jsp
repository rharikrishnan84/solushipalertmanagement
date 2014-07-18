<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
										<%-- <s:include value="customsBrokerInformations.jsp"/> --%>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
											<s:textfield  id="ci.address1" key="shippingOrder.customsInvoice.brokerAddress.address1" name="shippingOrder.customsInvoice.brokerAddress.address1" value="%{shippingOrder.customsInvoice.brokerAddress.address1}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.address2" key="shippingOrder.customsInvoice.brokerAddress.address2" name="shippingOrder.customsInvoice.brokerAddress.address2" value="%{shippingOrder.customsInvoice.brokerAddress.address2}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.customsInvoice.brokerAddress.postalCode" onblur="javascript:getSuggestBrokerAddress();"  id="ci.postcalCode" name="shippingOrder.customsInvoice.brokerAddress.postalCode"   />
												<img id="loading-img-to" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/> </label>
											<div class="controls"><span>:</span>
												<s:textfield id="ci.city1" key="shippingOrder.customsInvoice.brokerAddress.city" name="shippingOrder.customsInvoice.brokerAddress.city" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select   listKey="countryCode" listValue="countryName" id="firstBoxb" key="shippingOrder.customsInvoice.brokerAddress.countryCode" name="shippingOrder.customsInvoice.brokerAddress.countryCode" list="#session.CountryList" onchange="javascript:showShipToStateb();" headerKey="-1"  id="firstBoxb" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls"><span>:</span>
												<s:include value="customsInvoiceBrokerProvince.jsp"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.phoneNo" key="shippingOrder.customsInvoice.brokerAddress.phoneNo"  name="shippingOrder.customsInvoice.brokerAddress.phoneNo" value="%{shippingOrder.customsInvoice.brokerAddress.phoneNo}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.user.fax"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.faxNo" key="shippingOrder.customsInvoice.brokerAddress.faxNo"  name="shippingOrder.customsInvoice.brokerAddress.faxNo" value="%{shippingOrder.customsInvoice.brokerAddress.faxNo}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.emailid" key="shippingOrder.customsInvoice.brokerAddress.emailAddress" name="shippingOrder.customsInvoice.brokerAddress.emailAddress" value="%{shippingOrder.customsInvoice.brokerAddress.emailAddress}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customsInvoice.brokerName"/>&nbsp;<span>*</span> </label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.contactName" key="shippingOrder.customsInvoice.brokerAddress.contactName" name="shippingOrder.customsInvoice.brokerAddress.contactName" value="%{shippingOrder.customsInvoice.brokerAddress.contactName}" />
											</div>
										</div>
								
		