<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}" /></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
 
 
<link
	href="<s:url value='/mmr/styles/Shiplinx.css' includeContext="true"/>"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css" media="screen and (min-width:1024px)"/>
 
   <style>
   #wrapper_new{
   height: 100%;
   }
   .footer               			{ height:41px; width:100%;  background-color:#000; margin-top:5px; float: left;position:absolute;
	bottom:0;
	left:0; } 

.footer_body    			{ height:40px; width:1024px; margin:0px auto; background-color:#000;}
.footer_inner  		        { height:15px; width:960px; margin:0px auto; padding:13px 0px; color:#FFF; font-size:10px;}
.footer_inner p			{ padding:0px; margin:0px; float:left;  width:50%; }
.footer_inner p:nth-child(1){ text-align:left;}
.footer_inner p:nth-child(2){ text-align:right;}
.cont_hdrtitle_c3		{ height:15px; align:center; font-weight:bold; text-transform: uppercase; width:550px;  float:left;  color:#FFF; padding:5px; margin-left:250px; font-size:14px;}

   </style>
</head>

<body>


<script>
	$(document).ready(function(){ 
		$('.referenceOpen,.referenceBody').css('display','none');
		$('.referenceClose').click(function(){
			$(this).css('display','none');
			$('.referenceOpen,.referenceBody').css('display','block');
		});
		$('.referenceOpen').click(function(){
			$(this).css('display','none');
			$('.referenceClose').css('display','block')
			$('.referenceOpen,.referenceBody').css('display','none');
		});
	});
</script>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>

	<script language="JavaScript"
		src="<s:url value='/mmr/scripts/submitEnter.js' includeContext="true"/>"></script>
	<script language="JavaScript"
		src="<s:url value='/mmr/scripts/utils.js' includeContext="true"/>"></script>
	<script language="JavaScript"
		src="<s:url value='/mmr/scripts/cookies.js' includeContext="true"/>"></script>
	<script language="JavaScript">

	function submitSignups() {    
	    if(!document.signupForm.elements['checkbox'].checked){
			alert('Please read and accept the terms and conditions listed');
			//return false;
		}
		else if(document.signupForm.elements['customer.username'].value==""){
		    alert('Username is required');  
		}
		else if(document.signupForm.elements['customer.password'].value==""){
		    alert('Password is required');  
		}	
		else if(document.signupForm.elements['customer.retypePassword'].value==""){
		    alert('Re-type password is required');  
		}
		else if(!document.signupForm.elements['customer.password'].value == document.signupForm.elements['customer.retypePassword'].value){
		    alert('Password is not matched');  
		}	
		else if(document.signupForm.elements['customer.name'].value==""){
		    alert('Company name is required');  
		}
		else if(document.signupForm.elements['customer.address.contactName'].value==""){
		    alert('Contact name is required');  
		}
		else if(document.signupForm.elements['customer.address.address1'].value==""){
		    alert('Address1 is required');  
		}
		else if(document.signupForm.elements['customer.address.city'].value==""){
		    alert('City is required');  
		}
		else if(document.signupForm.elements['customer.address.postalCode'].value==""){
		    alert('Postal code is required');  
		}
		else if(document.signupForm.elements['customer.address.phoneNo'].value==""){
		    alert('Phone number is required');  
		}
		else if(!phonenumber()){
		    alert('Not a valid phone no, special characters not allowed. Enter only numbers');
		}
		else if(document.signupForm.elements['customer.address.emailAddress'].value==""){
		    alert('email Address is required');  
		}
		else if(!validateEmail()){
		    alert('Not a valid email address');
		}		
		else
		{
			document.signupForm.action = "doSignup.action";
			document.signupForm.submit();
		}
		//	document.forms.loginForm.submit();	
	}

	function validateEmail() {
	    var emailText = document.signupForm.elements['customer.address.emailAddress'].value;
	    var pattern = /^[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]+)*@[a-z0-9]+(\-[a-z0-9]+)*(\.[a-z0-9]+(\-[a-z0-9]+)*)*\.[a-z]{2,4}$/;
	    if (pattern.test(emailText)) {
	        return true;
	    } else {        
	        return false;
	    }
	}
	function phonenumber()
	{
	  var phoneno = /^\d{10}$/;
	  if((document.signupForm.elements['customer.address.phoneNo'].value.match(phoneno)))
	        {
	      return true;
	        }
	      else
	        {
	        return false;
	        }
	}

	// use this function to enable ACEGI rememberme service

	function keyPressSubmitRememberPassword(myfield,e) {
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;

		if (keycode == 13) {
		   myfield.form.submit();
		   return false;
	    } else {
	       return true;
	    }
	}
function loginSubmitACEGI() {
	var theForm = document.getElementById("loginForm");
	theForm.submit();
	//	document.forms.loginForm.submit();
}
function showState() {
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("stateid");
			js_stateid.innerHTML= reponse;
			}
	  }
		firstBox = document.getElementById('firstBox');
	  url="<%=request.getContextPath()%>/customer.listProvince.action?value="+firstBox.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
} // End function showState()	



// use this functions to enable ACEGI rememberme service

function loginSubmitACEGI() {
	var theForm = document.getElementById("loginForm");
	theForm.submit();
	//	document.forms.loginForm.submit();
}

function getAddressSuggestFrom1() {	
	  
	var form = document.userform;
  	var selectedCountry = form.elements['shippingOrder.fromAddress.countryCode'].value;
 
    if(selectedCountry != 'US' && selectedCountry != 'CA'){
    	return;
    }
	document.getElementById("loading-img-from").style.display = 'block';
	var error = false;
	form.elements['shippingOrder.fromAddress.postalCode'].value = (form.elements['shippingOrder.fromAddress.postalCode'].value).replace(/\s/g,"");
		if((form.elements['shippingOrder.fromAddress.postalCode'].value).length == 5){
			var mask = new RegExp('^\\d{5}$');
			if(mask.exec(form.elements['shippingOrder.fromAddress.postalCode'].value) == null) {
				alert('(Ship From) Postal Code should be in US format #####');
				error = true;
			}else{
				form.elements['shippingOrder.fromAddress.countryCode'].value = 'US';
			}
		}
		else if((form.elements['shippingOrder.fromAddress.postalCode'].value).length == 6){
				var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
				if(mask.exec(form.elements['shippingOrder.fromAddress.postalCode'].value) == null) {
					alert('(Ship From) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
					error = true;
				}else{
					form.elements['shippingOrder.fromAddress.countryCode'].value = 'CA';
				}
			
		}else{
			alert('Postal code should be only of US or Canada');
			error = true;
			document.getElementById("loading-img-from").style.display = 'none';
		}
		showShipFromState();			
		if(!error){
			ajax_City=ajaxFunction();
			ajax_City.onreadystatechange=function()
			  {
				   if(ajax_City.readyState==4)
					{
					reponse=ajax_City.responseText;
					form.elements['shippingOrder.fromAddress.city'].value = reponse;
					showShipFromState();
					document.getElementById("loading-img-from").style.display = 'none';
					}
			  }
			  fromPostalCode = document.getElementById('fromPostalCode').value;
			  fromCountry = form.elements['shippingOrder.fromAddress.countryCode'].value
				param="postalCode="+fromPostalCode+"&countryCode="+fromCountry+"&type=from";;
				url=contextPath+"/getAddressSuggest.action?" + param;
			  //+"&country="+fromCountry;
				ajax_City.open("GET",url,true);
				ajax_City.send(this);
		}
		else{
			document.getElementById("loading-img-from").style.display = 'none';
		}
}

function getAddressSuggestTo() {	
	var form = document.userform;
  	var selectedCountry = form.elements['shippingOrder.toAddress.countryCode'].value;
    if(selectedCountry != 'US' && selectedCountry != 'CA'){
    	return;
    }
    document.getElementById("loading-img-to").style.display = 'block';
	var error = false;
	form.elements['shippingOrder.toAddress.postalCode'].value = (form.elements['shippingOrder.toAddress.postalCode'].value).replace(/\s/g,"");
		if((form.elements['shippingOrder.toAddress.postalCode'].value).length == 5){
			var mask = new RegExp('^\\d{5}$');
			if(mask.exec(form.elements['shippingOrder.toAddress.postalCode'].value) == null) {
				alert('(Ship To) Postal Code should be in US format #####');
				error = true;
			}else{
				form.elements['shippingOrder.toAddress.countryCode'].value = 'US';
			}
		}
		else if((form.elements['shippingOrder.toAddress.postalCode'].value).length == 6){
				var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
				if(mask.exec(form.elements['shippingOrder.toAddress.postalCode'].value) == null) {
					alert('(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
					error = true;
				}else{
					form.elements['shippingOrder.toAddress.countryCode'].value = 'CA';
				}
			
		}else{
			alert('Postal code should be only of US or Canada');
			error = true;
			document.getElementById("loading-img-to").style.display = 'none';
		}
		showShipToState();			
		if(!error){
			ajax_City=ajaxFunction();
			ajax_City.onreadystatechange=function()
			  {
				   if(ajax_City.readyState==4)
					{
					reponse=ajax_City.responseText;
					form.elements['shippingOrder.toAddress.city'].value = reponse;
					showShipToState();
					document.getElementById("loading-img-to").style.display = 'none';
					}
			  }
			  toPostalCode = document.getElementById('toPostalCode').value;
			  toCountry = form.elements['shippingOrder.toAddress.countryCode'].value
				param="postalCode="+toPostalCode+"&countryCode="+toCountry+"&type=to";
				url=contextPath+"/getAddressSuggest.action?" + param;
			  //+"&country="+toCountry;
				ajax_City.open("GET",url,true);
				ajax_City.send(this);
		}
		else{
			document.getElementById("loading-img-to").style.display = 'none';
		}
}
function showShipFromState() {
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("stateid");
			js_stateid.innerHTML= reponse;
			}
	  }
	  firstBox = document.getElementById('firstBox');
	  url=contextPath+"/shipFrom.listFromProvience.action?value="+firstBox.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
		
		// To empty cache for autocomplete
		$('#fromcity').flushCache();
		$('#fromPostalCode').flushCache();
		
		
} 


function submitform(){
	document.userform.action="saveEcomDetail.action";
	document.userform.submit();
}
function showBillingState() {
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("billingstateid");
			js_stateid.innerHTML= reponse;
			}
	  }
		billingCountry = document.getElementById('billingCountry');
	  url="<%=request.getContextPath()%>/billingcustomer.listProvince.action?value="+billingCountry.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
} // End function showState()	

//use this functions if just user name will be remembered until checkbox is marked off

</script>
		
	 	
		
		  
		<div class="top_line"></div>
		<div class="header-top">
			<div class="top_header_inner">
				<div class="header_logo_body">
					<div class="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo.png"></div>
							<div class="login_box">
						 
					 
						
					 
		</div>
		</div>
					 
			 		
			 
				
				<div class="navigation2">
					<div class="naviinner">
							<ul>	
							<li><a href="viewLogon.action" style="color: #ffffff; "><mmr:message messageId="menu.home" /> </a></li>
							<li><a href="ecomSignup.action" style="color: #ffffff; background-color:#990000;"><mmr:message messageId="menu.signup" /> </a></li>
							
							</ul>
					<div class="search_body">
						<form method="post" action="">
							<input type="text" placeholder="Enter search keyword here">
							<input type="submit" value=" ">
						</form>
						
					 
						
					</div>
					</div>
				</div>
				
				<div class="navi_icon"><img src="" width="30" height="30"/></div>
				<div class="navi_shadow">
				</div>
			</div>
		</div>
		
		<div class="wrapper_btm">
			<div class="wrapper_btm_inner">
				<div class="wrapper_body">
					<div class="wrapper_navi">
						 	<div class="cont_hdrtitle_c3" align="center">
									Welcome to SOLUSHIP ECOMMERCE API  
								</div>
								
					</div>
					
					<div class="nextlevelmenu">
						<ul>	
							<li>
								<div id="four_lvl_nav"><a href="" includeContext="true" includeParams="none" >&nbsp;
									</a>
								</div>
							</li>
						</ul>
					</div>
					
				 
				
				</div>
			</div>
		</div>
	
	 <div class="content">
<div class="content_body">
<s:form action="createUser" name="userform" style="margin-bottom	:0px">
	<s:if test="#session.editBusiness == 'true'">	
    	<s:hidden name="method" value="update"/>
    	  <s:hidden name="addressid" value="%{business.address.addressId}" />
    	  <s:hidden name="businessid" value="%{business.id}" />
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
								  STORE DETAILS
									 
								</s:else>
								</div>
								<div class="form_buttons">
									<!--<s:submit onclick="submitform()" value="SAVE"/>
									<s:submit onclick="resetform()" value="RESET"/>-->
									
									<a href="#" onclick="submitform()" ><mmr:message messageId="menu.signup"/></a> 
									<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a> 
								</div>
							</div>
							
							
							<div class="cont_data_body">
							
							<div class="rows">
							<div class="fields">
										<label>Store Name</label>
										<div class="controls"><span>:</span>
										<s:textfield  key="ecomStore.name" name="ecomStore.name" readonly="true"/>
									</div>
									</div>
									 
									<div class="fields">
										<label>Web Site </label>
										<div class="controls"><span>:</span>
										<s:textfield  key="ecomStore.url" name="ecomStore.url" readonly="true" />
									</div>
									</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.default.currency"/></label>
											<div class="controls"><span>:</span><s:select
												list="#{'CAD':'CAD', 'USD':'USD','CNY':'CNY','EUR':'EUR'}"
												name="ecomStore.defaultCurrency" headerKey="" headerValue=""
												  /> </div>
										</div>	
										<div class="fields">
											<label><mmr:message messageId="label.customer.username"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="ecomStore.username" name="ecomStore.username" value="%{ecomStore.username}" />
											<s:textfield  key="ecomStore.username" cssStyle="display:none" name="ecomStore.username" value="%{ecomStore.username}" class="hidden" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.userpassword"/></label>
											<div class="controls"><span>:</span><s:password  key="ecomStore.password" name="ecomStore.password"  />
											<s:password  key="ecomStore.password" cssStyle="display:none" name="ecomStore.password"  class="hidden"/></div>
										</div>
								 
				</div>	
				</div>
				</div>
				
						
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title">SHIP FROM <mmr:message messageId="label.business.address"/>&nbsp;&nbsp;&nbsp;(for shipping)</div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									 
								 
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomCustomer.address.address1" name="ecomCustomer.address.address1"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomCustomer.address.address2" name="ecomCustomer.address.address2"  cssClass="text_02_tf" value="%{ecomCustomer.address.address2}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomCustomer.postalCode" onblur="getAddressSuggestFrom2();"  id="fromPostalCode" name="ecomCustomer.address.postalCode"  cssClass="text_02_tf" value="%{ecomCustomer.address.postalCode}"/>
	    	<img id="loading-img-from" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="ecomCustomer.address.countryCode" headerKey="-1"  list="#session.CountryList" 
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
            <s:textfield size="20" id = "fromcity" key="ecomCustomer.address.city" name="ecomCustomer.address.city"  cssClass="text_02_tf" value="%{ecomCustomer.address.city}"/>
            </div>
           </div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomecomCustomer.address.phoneNo" name="ecomCustomer.address.phoneNo"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomCustomer.email" name="ecomCustomer.address.emailAddress"  cssClass="text_02_tf" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="ecomCustomer.address.contactName" name="ecomCustomer.address.contactName"  cssClass="text_02_tf"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.Instructions"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  rows="1" key="ecomCustomer.address.instruction" name="ecomCustomer.fromInstructions"  cssClass="text_02"/>
											</div>
										</div>
									 
										
										<div class="fields">
											<label><mmr:message messageId="label.notify.shipper"/> </label>
											<div class="controls"><span>:</span>
												<s:checkbox  cssClass="text_01" value="%{ecomCustomer.address.sendNotification}"  name="ecomCustomer.address.sendNotification"/>
											</div>
										</div>
									 
								
							</div>
						
						</div>
					 
						 
		</s:form> 
	</div>
</div>	
	<div class="footer" id="wrapper_new1">
		<div class="footer_body">
			<div class="footer_inner">
				<p>&copy;2014 Integrated Carriers. All Rights Reserved</p>
				<p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p>
			</div>
		</div>
	</div>
 
</body>

</html>
