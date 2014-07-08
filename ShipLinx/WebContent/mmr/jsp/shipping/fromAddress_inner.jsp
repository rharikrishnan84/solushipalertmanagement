<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



						
									
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
												<s:include value="../admin/shippingFromProvienceList.jsp"/>
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
											<label>Instructions</label>
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
										