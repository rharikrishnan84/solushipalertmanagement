<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



						
										
										
									 <div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
		<s:textfield size="20" key="branch.branchAddress.address1"
			name="branch.branchAddress.address1" cssClass="text_02_tf" />
	</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="branch.branchAddress.address2" name="branch.branchAddress.address2"  cssClass="text_02_tf" value="%{branch.branchAddress.address2}"/>
											</div>
										</div>
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="branch.branchAddress.postalCode" onblur="javascript:getAddressSuggestFrom();"  id="fromPostalCode" name="branch.branchAddress.postalCode"  cssClass="text_02_tf" value="%{branch.branchAddress.postalCode}"/>
	    	<img id="loading-img-from" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<%-- <div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="branch.branchAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
	                  onchange="javascript:showShipFromState();"  value="countrypartner.countryCode" id="firstBox"  theme="simple" disabled="true"/>
	                 <s:hidden name="branch.branchAddress.countryCode" value="%{countrypartner.countryCode}"></s:hidden>
											</div>
										</div> --%>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.shipToId.state"/></label>
											<div class="controls" id="stateid" > 
										 	<jsp:include page="../admin/shippingFromProvienceList.jsp"/>  
												</div>
											</div>
											 <div class="fields">
           <label><mmr:message messageId="label.shippingOrder.city"/></label>
           <div class="controls"> <span>:</span>
            <s:textfield size="20" id = "fromcity" key="branch.branchAddress.city" name="branch.branchAddress.city"  cssClass="text_02_tf" value="%{branch.branchAddress.city}"/>
            </div>
           </div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="branch.branchAddress.phoneNo" name="branch.branchAddress.phoneNo"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="partner.email" name="branch.branchAddress.emailAddress"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="branch.branchAddress.contactName" name="branch.branchAddress.contactName"  cssClass="text_02_tf"/>
											</div>
										</div>
									<%--	<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.Instructions"/></label>
												<div class="controls"><span>:</span>
												<s:textfield  rows="1" key="branch.branchAddress.instruction" name="partner.fromInstructions"  cssClass="text_02"/>
											</div>
										</div>
										 --%>