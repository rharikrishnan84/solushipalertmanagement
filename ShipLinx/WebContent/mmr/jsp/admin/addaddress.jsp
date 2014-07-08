<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head> 

    <title><s:text name="address.form.title"/></title> 
</head> 

<body>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>

<SCRIPT language="JavaScript">
	function submitform()
	{
	 document.addressform.action = "createAddress.action";
	 document.addressform.submit();
	}
</SCRIPT>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div class="form-container"> 
	<div class="content">
		<div class="content_body">
			<div class="content_table">
			<div class="error" style="color:#FF0000;padding:3px 20px; font-size:14px; background-color: #F8ECE0;">
				<s:fielderror />
			</div> 
			</div>
		</div>
	</div>
    <s:form action="createAddress" name="addressform" theme="simple">
    <s:hidden name="addressid" value="%{address.addressId}" />
    <div class="content">
<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
							<s:if test="#session.edit != 'true'">
								<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.addAddress" /></div>
							</s:if>
							<s:else>
								<div class="cont_hdr_title"><mmr:message messageId="label.btn.edit" />&nbsp;&nbsp;<mmr:message messageId="label.address.line" /></div>
							</s:else>
								<div class="form_buttons" >	
									<a href="javascript:submitform()"><mmr:message messageId="label.btn.save"/></a>
								<s:if test="#session.edit != 'true'">								
									<a href=""><mmr:message messageId="label.btn.reset"/></a>
								</s:if>
								<s:else>	
									<a href="addressbook.list.req.action"><mmr:message messageId="label.btn.cancel"/></a>
									</s:else>
								</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.consigneeName" /></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.consigneeName" name="address.abbreviationName"  />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.address1"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.address1" name="address.address1" />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.address2"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.address2" name="address.address2" />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.postalCode"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" id="addPostalCode" key="addressbook.postalCode" name="address.postalCode"  onblur="javascript:getAddressSuggestNewAddress();"/>
											<img id="loading-img-add" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.city"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.city" name="address.city" />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.countryName"/></label>
										<div class="controls">
											<span>:</span>
											<s:select  listKey="countryCode" listValue="countryName" name="address.countryCode" headerKey="-1" list="#session.CountryList" onchange="javascript:showState();"  id="firstBox" theme="simple"/>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.province"/></label>
										<div class="controls" id="stateid">
											<span>:</span>
											<s:select  listKey="provinceCode" listValue="provinceName" name="address.provinceCode" headerKey="1" list="#session.provinces" id="secondBox"  theme="simple"  cssClass="text_01"/>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.phone"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.phone" name="address.phoneNo"  />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.contactName"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.contactName" name="address.contactName"  />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.fax"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.faxNo" name="address.faxNo"  />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.contactEmail"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.contactEmail" name="address.emailAddress"  />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.addressbook.shipperType"/></label>
										<div class="controls">
											<span>:</span>
											<s:select 
											listKey="brokerCode" listValue="brokerName"
											name="address.brokerCode" headerKey="-1" list="#session.brokers"
											id="thirdBox" theme="simple" />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.tax.id"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield size="24" key="addressbook.taxId" name="address.taxId"  />
										</div>
									</div>
								</div>
								<div class="rows">	
									<div class="fieldsl">
										<label><mmr:message messageId="label.addressbook.residential"/></label>
										<div class="controlscheck">
											<span>:</span>
											<s:checkbox key="addressbook.residential" name="address.residential"  />
										</div>
									</div>
									<div class="fieldsl">
										<label><mmr:message messageId="label.addressbook.defaultFromAddress"/></label>
										<div class="controlscheck">
											<span>:</span>
											<s:checkbox key="addressbook.defaultFromAddress" value="%{address.defaultFromAddress}" name="address.defaultFromAddress"  />
										</div>
									</div>
									<div class="fieldsl">
										<label><mmr:message messageId="label.send.notification"/></label>
										<div class="controlscheck">
											<span>:</span>
											<s:checkbox key="addressbook.sendNotification" name="address.sendNotification"  />
										</div>
									</div>
									<div class="fieldsl">
										<label><mmr:message messageId="label.addressbook.defaultToAddress"/></label>
										<div class="controlscheck">
											<span>:</span>
											<s:checkbox key="addressbook.defaultToAddress" name="address.defaultToAddress" value="%{address.defaultToAddress}"   />
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
<div class="content">
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</div>
			
    </s:form> 
    </div>
	
			
	
   </body>
</html>
    
    
 