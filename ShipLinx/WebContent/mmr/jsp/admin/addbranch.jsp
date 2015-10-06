<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<sx:head/>
    <title><s:text name="user.form.title"/></title> 
	</head> 

<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
<script>

  $(document).ready(function(){
   $('.navi_icon').click(function(){
    $('.navigation ul').slideToggle();
   });

  });
 </script>
 <script type="text/javascript">
 $(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
   </script>
<script type="text/javascript">


var default_address_id_from =0;
var default_address_id_to =0;

window.onload = function() {

    	var e = document.getElementById("idRoles");
		var strRole = e.options[e.selectedIndex].value;
		if(strRole=='sales')
		document.getElementById("sales_div").style.display= "block";
		else
		document.getElementById("sales_div").style.display= "none";
		
		showHideSalesDiv('user.userRole');
		
};

	function submitform()
	{
		 
		document.userform.action = "saveBranch.action";
	     document.userform.submit();
	}
	
	function resetform(){
			
			document.userform.action = "addBranch.action";
	 		document.userform.submit();
	}		
	
	function showHideSalesDiv(valname)
	{
		//alert(valname);
		var e = document.getElementsByName(valname);
		//alert(e);
		var strRole = e[0].options[e[0].selectedIndex].value;
		//alert(strRole);
		if(strRole == 'sales'){		
			document.getElementById("sales_div").style.display= "block";
			}
		else{
		document.getElementById("sales_div").style.display= "none";
		}
		if(strRole == 'customer_admin')
		{
			document.getElementById("rules").innerHTML="- Unrestricted access to all functions within the customer account.<br/>";	
		}
		else if(strRole == 'customer_base')
		{
			document.getElementById("rules").innerHTML="- Access to Shipping information, including create new shipments and view all shipments created under the customer account.<br/> - Access to Address Book functionality. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - No access to invoicing module and other admin functions (such as user management).";	
		}		
		else if(strRole == 'customer_shipper')
		{
			document.getElementById("rules").innerHTML="- Restricted access to Shipping information.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Can create new shipments.<br/>- Can view only shipments created by this user.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- No access to invoicing module and other admin functions (such as user management).";	
		}
		else
		{
			document.getElementById("rules").innerHTML="";	
		}
		
		
	}
	
	function typenumbers(e,filterString)
	{
		var key, keychar;
		key = getkey(e);
		if (key == null) 
		return true;
		
		// get character
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();
		// control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==27) )
		return true;
		// alphas and numbers
		else if ((filterString.indexOf(keychar) > -1))	
		return true;
		else
		return false;
	}
	
	function getkey(e){
		if (window.event)
		  return window.event.keyCode;
		else if (e)
		  return e.which;
		else
		  return null;
	}
		
	jQuery(function() {
		$("#defaultFromAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultFromAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("user.defaultFromAddressId").value=data[1];
			default_address_id_from = data[1];
			//document.getElementById("user.defaultFromAddressText").value=data[0];
			});
			
		$("#defaultToAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultToAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("user.defaultToAddressId").value=data[1];
			default_address_id_to = data[1];
			//document.getElementById("user.defaultToAddressText").value=data[0];
			});
		
		// $("#defaultFromAddText").click(function(){$("#defaultFromAddText").val('')});
         $("#defaultFromAddText").blur(function(){if($("#defaultFromAddText").val()==''){
         $("#defaultFromAddText").val('None');
         document.getElementById("user.defaultFromAddressId").value=0;
         }});
          
        // $("#defaultToAddText").click(function(){$("#defaultToAddText").val('')});
         $("#defaultToAddText").blur(function(){if($("#defaultToAddText").val()==''){
         $("#defaultToAddText").val('None');
         document.getElementById("user.defaultToAddressId").value = 0;
         }});		
			
		});
</script> 

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="content">
<div class="content_body">
<s:form action="createUser" name="userform" style="margin-bottom	:0px">
	<s:if test="#session.editBusiness == 'true'">	
	  <s:hidden name="addressid" value="%{business.address.addressId}" />
	  <s:hidden name="businessid" value="%{business.id}" />
    	<s:hidden name="method" value="update"/>
    </s:if> 
    <s:else>
    	<s:hidden name="method" value="add"/>
     </s:else>
    <s:hidden name="cid" value="%{user.customerId}" />
					 
								<div class="content_table">
				
					<div class="content_header">
								<div class="cont_hdr_title">
									<s:if test="#session.edit == 'true'">
								     <mmr:message messageId="label.partner.edit.partner"/>
									 
									</s:if>
									<s:else>
								  ADD BRANCH
									 
								</s:else>
								</div>
								<div class="form_buttons">
									<!--<s:submit onclick="submitform()" value="SAVE"/>
									<s:submit onclick="resetform()" value="RESET"/>-->
									
									<a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a> 
								</div>
							</div>
							
							
							<div class="cont_data_body">
							
							<div class="rows">
								<div class="fields">
										<label><mmr:message messageId="label.partner.partnername"/>
										  BUSINESS NAME										
										 </label>
										<div class="controls"><span>:</span>
										<s:if test="#session.edit == 'true'">
										<s:textfield   readonly="true" id="partnerName" key="partner.partnerName" name="partner.partnerName"/>
										</s:if>
										<s:else>
										<s:textfield   id="menuName" key="business.name" name="business.name"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
									</s:else></div>
									</div>
									
									
							 	   <div class="fields">
										<label><mmr:message messageId="label.business.shortCode"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield   id="menuName" key="business.shortCode" name="business.shortCode"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
									</div>
									</div>
								<div class="fields">
										<label><mmr:message messageId="label.business.systemName"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="business.systemName" name="business.systemName" /></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.logOutUrl"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.logoutURL" name="business.logoutURL" /></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.storeCC"/> </label>
										<div  class="controls"><span>:</span><s:checkbox key="business.storeCC"
			name="business.storeCC" cssClass="text_02" /></div>
									</div>
								
								
								<div class="fields">
										<label><mmr:message messageId="label.business.logoUrl"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.logoUrl" name="business.logoUrl" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.subDomain"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.subdomain" name="business.subdomain" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.supportUrl"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.supportURL" name="business.supportURL" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.termsUrl"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.termsURL" name="business.termsURL" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpHost"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.smtpHost" name="business.smtpHost" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpUsername"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.smtpUsername" name="business.smtpUsername" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpPassword"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.smtpPassword" name="business.smtpPassword" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpPort"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="business.smtpPort" name="business.smtpPort" /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.invoiceLevel"/></label>
										<div  class="controls"><span>:</span>
									<s:select  name="business.invoiceLevel" headerKey="SYSTEM" headerValue="SYSTEM"
										list="#{ 'BUSINESS':'BUSINESS'}" value="business.invoiceLevel" required="true" cssClass="text_01" />
								</div>
									</div>
									
				</div>	
				</div>
				</div>
				
						
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.business.address"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.business.abbreviationName"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.abbreviationName" name="business.address.abbreviationName" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.addressone"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.address1" name="business.address.address1" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.addresstwo"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.address2" name="business.address.address2" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.city"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.city" name="business.address.city" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.phoneno"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.phoneNo" name="business.address.phoneNo" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.faxno"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.faxNo" name="business.address.faxNo" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.mobilePhoneNumber"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.mobilePhoneNo" name="business.address.mobilePhoneNo" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.emailAddress"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.emailAddress" name="business.address.emailAddress" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.contactName"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.contactName" name="business.address.contactName" />
									</div>
									</div>
										
									<div class="fields">
										<label><mmr:message messageId="label.business.postalCode"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.postalCode" name="business.address.postalCode" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.url"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.url" name="business.address.url" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.residential"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.residential" name="business.address.residential" cssClass="text_02" />
									</div>
									</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="business.address.countryCode" headerKey="-1"  headerValue="Select" list="#session.CountryList" 
	                          id="firstBox"  theme="simple"/>
											</div>
										</div>
											<%-- <div class="fields">
											<label><mmr:message messageId="label.customer.postalCode"/> </label>
											<div class="controls"><span>:</span><s:textfield  id="custPostalCode" key="business.address.postalCode" name="business.address.postalCode" onblur="getAddressSuggestBusiness()"  />
											<img id="loading-img-cust" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0"></div>
										</div> --%>
									<div class="fields">
										<label><mmr:message messageId="label.business.provinceCode"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="business.address.provinceCode" name="business.address.provinceCode" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.defaultFrom"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.defaultFromAddress" name="business.address.defaultFromAddress" cssClass="text_02" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.defaultTo"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.defaultToAddress" name="business.address.defaultToAddress" cssClass="text_02" />
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.sendNotification"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.sendNotification" name="business.address.sendNotification" cssClass="text_02" />
									</div>
									</div>
								</div>
								
							</div>
						
						</div>
						<s:if test="#session.editBusiness == 'true'">
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.role.roleMenu"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields1">
										<label><mmr:message messageId="label.role.roleMenu"/> :</label>
										<div class="controls">
										<s:select   name="business.menuIds" value="%{selectedMenuIds}"  listKey="id" listValue="name" cssClass="changerole"
										headerKey="-1"  headerValue="--Select Menu--" list="menuVo" theme="simple"  id="idLocale" multiple="true"
										cssStyle="width:150px;height:100px"/>
									</div>
									</div>
									
									
								</div>
								
							</div>
						
						</div>
						</s:if>
						<s:if test="#session.editBusiness != 'true'">
							
							  <div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.default.loader"/> </div>
							</div>
							<div class="cont_data_body">
								 								<div class="rows">
						<%-- 		 	<div class=fields1>
										
											<s:checkboxlist  list="defaultLoaders" label="defaultLoaders" name="selectedLoaders" value="true"/>
											</div> --%>	
											 </div>
										 
									<s:iterator value="defaultLoaders" status="carriercount">
											 
										<s:if test="%{#carriercount.index % 3 == 0}">
									
										</s:if>
										<div class="fieldscheckgroup">
											<div class="checkgroupctrls" >
												<s:checkbox name="select[%{#carriercount.index}]" value="true"  />
											</div>	
											<label><s:property/></label>
										</div>	
									</s:iterator>
									
								</div>
							</div> 
							</s:if>
							
							<div class="content_table">
	 
					<div >
							<div class="content_header">
								<div class="cont_hdr_title">Business Contacts</div></div>
								</div>
						
					 
						 <div class="cont_data_body">
							
								<div class="rows">
								 
									<div class="fieldsl">
									<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.businesssAbbrivation"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.businesssAbbrivation" key="business.businessContact.businesssAbbrivation"/>
										 
								</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.quickStartUrl"/> </label>
									<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.quickStartUrl" key="business.businessContact.quickStartUrl"/>
										 
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.adminEmail"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.adminEmail" key="business.businessContact.adminEmail"/>
										 
								</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.arContact"/> </label>
									<div class="controls"><span>:</span>
										 
										<s:textarea cssStyle="width: 150px; height: 20px;"   name="business.businessContact.arContact" key="business.businessContact.arContact"/>
										 
									</div>
									</div>
									<div class="fieldsl">
									<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.arEmail"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.arEmail" key="business.businessContact.arEmail"/>
										 
									</div>
									</div>
								<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.arPhone"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.arPhone" key="business.businessContact.arPhone"/>
										 
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.ltlContact"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textarea cssStyle="width: 150px; height: 20px;"  name="business.businessContact.ltlContact" key="business.businessContact.ltlContact"/>
									 
									</div>
									 
									</div>
										<div class="fieldsl">
									<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.ltlEmail"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.ltlEmail" key="business.businessContact.ltlEmail"/>
									 
									</div>
									</div>
										<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.ltlPhone"/> </label>
									<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.ltlPhone" key="business.businessContact.ltlPhone"/>
										 
									</div>
									</div>
										<div class="fieldsl">
									<label style="width:200px !important;"><mmr:message messageId="label.buisness.contact.businessContactUrl"/> </label>
										<div class="controls"><span>:</span>
										 
										<s:textfield  name="business.businessContact.businessContactUrl" key="business.businessContact.businessContactUrl"/>
										 
									</div>
									</div>
								
								 
						</div>
						</div>
						
						</div>
		</s:form> 
	</div>
</div>	

<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</body>
</html>
    
    
  