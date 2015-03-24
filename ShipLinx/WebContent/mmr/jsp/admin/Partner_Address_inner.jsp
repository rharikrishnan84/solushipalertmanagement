<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



						
									
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.partnerAddress.address1" name="partner.partnerAddress.address1"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.partnerAddress.address2" name="partner.partnerAddress.address2"  cssClass="text_02_tf" value="%{partner.partnerAddress.address2}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.partnerAddress.postalCode" onblur="javascript:getAddressSuggestFrom();"  id="fromPostalCode" name="partner.partnerAddress.postalCode"  cssClass="text_02_tf" value="%{partner.partnerAddress.postalCode}"/>
	    	<img id="loading-img-from" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="partner.partnerAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
	                  onchange="javascript:showShipFromState();"  id="firstBox"  theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.shipToId.state"/></label>
											<div class="controls" id="stateid" > 
												<jsp:include page="../admin/shippingFromProvienceList.jsp"/>
												</div>
											</div>
											 <div class="fields">
           <label><mmr:message messageId="label.shippingOrder.city"/></label>
           <div class="controls"> <span>:</span>
            <s:textfield size="20" id = "fromcity" key="partner.partnerAddress.city" name="partner.partnerAddress.city"  cssClass="text_02_tf" value="%{partner.partnerAddress.city}"/>
            </div>
           </div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.partnerAddress.phoneNo" name="partner.partnerAddress.phoneNo"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.email" name="partner.partnerAddress.emailAddress"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.partnerAddress.contactName" name="partner.partnerAddress.contactName"  cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.Instructions"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  rows="1" key="partner.partnerAddress.instruction" name="partner.fromInstructions"  cssClass="text_02"/>
											</div>
										</div>
										