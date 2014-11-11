<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}" /></title>

<style type="text/css">
<!--
@import url("styles/shiplinx_login01_styles.css");
-->

	
	/* sign up */
	.fields_signup_captcha					{ width:373px; margin-right:-58px; margin-top:42px; float:right; padding:5px 0px; height:25px;}
	.fields_signup_captcha label				{ width:139px; float:left; padding-top:3px;}
	.fields_signup_captcha .controls				{ width:174px; float:left;}
	.fields_signup_captcha .controls span { padding-top:3px; padding-right:10px; float:left;}
	.fields_signup_captcha .controls input[type="text"] {height:20px; width:160px; padding-left:5px; float:left;}
.fields_signup					{ width:373px; margin-left:5px; float:left; padding:3px 0px; height:25px;}
.fields_signup label				{ width:139px; float:left; padding-top:3px;}
.fields_signup .controls				{ width:174px; float:left;}
.fields_signup .controls span { padding-top:3px; padding-right:10px; float:left;}
.fields_signup .controls input[type="text"],.fields_signup .controls input[type="password"]{height:20px; width:152px; padding-left:5px; float:left;}
.fields_signup .controls input[type="checkbox"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }
#month { height:20px; width:92px; float:left;}
#year { height:20px; width:60px; float:left;}}
.fields_signup .controls textarea		{ height:20px; width:150px; float:left;}
.fields_signup a{ color:#990000;}


.userverify					{ width:354px; margin-left:5px; float:left; padding:42px 0px; height:auto;}
.userverify label				{ width:139px; float:left; padding-top:3px;}
.fields2 .controls select			{ height:23px; width:152px; float:left;}


.rows					{ width:100%; height:auto; float:left; padding:3px 0px; font-size:12px;}
.fields2					{ width:315px; margin-left:5px; float:left; padding:3px 0px; height:25px;}
.fields2 label				{ width:139px; float:left; padding-top:3px;}
.fields2 .controls				{ width:174px; float:left;}
.fields2 .controls span { padding-top:3px; padding-right:10px; float:left;}

.fields2 .controls input[type="checkbox"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }
.fields2 .controls select			{ height:23px; width:160px; float:left;}
.fields2 .controls textarea		{ height:23px; width:160px; float:left;}
.fields2 a{ color:#990000;}
#messages ul{ padding:0px; margin:0px; width:700px;}
.fields2 .controls input[type='password'],.fields2 .controls input[type='text']{height:20px; width:160px; padding-left:5px; float:left;}


/* header footer */
#wrapper_logo{ 
		background:url('ic-logo-helping everyone 1024 50.png') !important;
		background-repeat:no-repeat;
		background-position: center center;
		background-size:900px auto;
	}
	.login_box{
		width:auto; 
		height:70px;
		float:right;
		padding-right:32px;
	}
	.login_box ul{ float:left; height:auto; width:auto; list-style-type:none;}
	.login_box ul li{ height:25px;}
	.login_box ul li label{ width:130px; padding-top:3px; padding-right:5px; float:left; font-size:10px; text-align:right }
	.login_box ul li select,.login_box ul li input{ float:left;  padding:0px;}
	.login_box ul li select{ height:22px; width:122px; }
	.login_box ul li input{ height:20px; width:120px; }
	.login_box input[type="submit"]{border:0px; background:transparent; font-size:10px; padding:0px; float:right; margin-top:52px;}
</style>
<link
	href="<s:url value='/mmr/styles/Shiplinx.css' includeContext="true"/>"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css" media="screen and (min-width:1024px)"/>

</head>

<body>
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
		<style>
			.fgt_password:hover{ color:#ccc;}
		
			.fgt_password{color: #ffffff; text-decoration:none; font-weight:600;}
			body{ height:100%; width:100%; margin:0px; padding:0px; font-family:arial; }
			#signup{ width:700px; height:auto; margin:0px  auto;  }
			.signup_header{ float:left; height:auto; width:100%; text-align:center; border-bottom:2px solid #00A53C; padding:8px 0px; }
			.signup_header  img{}
			#signup h2{ width:100%; text-align:center; float:left; color:#990000; }
			.aligncenter{text-align:center; width:390px; float:right; padding:5px 0px; font-size:12px; margin-top:-110px;}
			.aligncenter_signup{text-align:center; width:390px; float:right; padding:5px 0px; font-size:12px; margin-top:-73px; margin-right:132px;}
			.aligncenter_signup  span{ color:#CCC; font-size:10px;}
			.aligncenter_signup a{text-decoration:none;}
		</style>
		
	<form action="j_acegi_security_check" method="post" id="loginForm"
	name="loginForm">	
		
		<div id="wrapper">
		<div class="top_line"></div>
		<div class="header-top">
			<div class="top_header_inner">
				<div class="header_logo_body">
					<div class="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo.png"></div>
							<div class="login_box">
						<form>
						<ul>
							<li>
								<label style="font-size:12px"><mmr:message messageId="label.prompt.username" /></label>
								<s:textfield
			name="j_username" id="username" class="text_01"
			onkeypress="return keyPressSubmitRememberPassword(this,event)" />
							</li>
							<li>
								<label style="font-size:12px"><mmr:message messageId="label.prompt.password" /></label>
								<s:password
			id="password" name="j_password" class="text_01" maxlength="20"
			onkeypress="return keyPressSubmitRememberPassword(this,event)" />
							</li>
						</ul>
						<input onclick="loginSubmitACEGI()" type="submit" style="cursor: pointer;padding-left: 5px;position: relative;bottom: 4px;font-size: 11px;" value="<mmr:message messageId="label.login" />"/>
						</form>
					</div>
			</form>		
				</div>
				<div class="navigation2">
					<div class="naviinner">
							<ul>	
							<li><a href="viewLogon.action" style="color: #ffffff; "><mmr:message messageId="menu.home" /> </a></li>
							<li><a href="signup.action" style="color: #ffffff; background-color:#990000;"><mmr:message messageId="menu.signup" /> </a></li>
							
							</ul>
					<div class="search_body">
						<form method="post" action="">
							<input type="text" placeholder="Enter search keyword here">
							<input type="submit" value=" ">
						</form>
						
						<script type="text/javascript" src=""></script>
						  <script>
						   $(function() {
							$('input, textarea').placeholder();
						   });
						  </script>
						
					</div>
					</div>
				</div>
				
				<div class="navi_icon"><img src="" widt="30" height="30"/></div>
				<div class="navi_shadow">
				</div>
			</div>
		</div>
		
		<div class="wrapper_btm">
			<div class="wrapper_btm_inner">
				<div class="wrapper_body">
					<div class="wrapper_navi">
						<ul>
							<li><a href=""></a> </li>						
						</ul>
						<p><a href="forgotPassword.action" class="fgt_password" ><mmr:message messageId="menu.forgetpassword" /></a></p>
					</div>
					
					<div class="nextlevelmenu">
						<ul>	
							<li>
								<div id="four_lvl_nav"><a
									href="" includeContext="true" includeParams="none" />&nbsp;
									</a>
								</div>
							</li>
						</ul>
					</div>
					
					<div style="width:100%; height:auto; overflow:auto; ">
						<div style=" margin:0px auto; height:auto; width:960px;">
							<div class="content_header">
								<div class="cont_hdrtitle_c3">
									<mmr:message messageId="label.heading.startsignup" />  
								</div>
								
								
								<div class="form_buttons">
									<a href="javascript:submitSignup()">
										<input type="button" name="Sign Up" value="Sign Up" style="padding:0px !important;" />
									</a>
								</div>
								
								
								
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="content_body" >
		
		<div class="cont_data_body borderLeftRightTopDown" style="margin:0px 32px 20px 32px; " >
	    <div id="wrapper_logo" style=" width:100%; float:left;">
			
			
			
			
			
	<form action="doSignup.action" method="post" id="signupForm"
		name="signupForm">
		<div id="signup">
			<!--<div class="signup_header">
					<s:if test="%{#session.logoURL!=null}">
					<s:set name="url" value="%{#session.logoURL}" />
					<img src="<s:property value="%{url}"/>" border="0" width="310px"
						height="82px" includeContext="true" />
				</s:if>
				<s:else>
					<a href="<s:property value="%{#session.business.logoutURL}"/>"
						target="_blank"><img
						src="<%=request.getContextPath()%>/mmr/images/<s:property value="%{#session.business.logoFileName}"/>"
						style="height: 90px; width: 300px;" /></a>
				</s:else>
			</div>
			<h2>Sign up & Start Shipping!</h2>-->
			
				
			
			<div id="messages" style="float:left; width:700px; overflow:hidden;">
					<jsp:include page="../common/action_messages.jsp" />
			</div>
			<div class="rows">
				<div class="fields_signup">
					<label><mmr:message	messageId="label.customer.username" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="customer.username" name="customer.username" />
					</div>	
				</div>	
				
				<div class="fields2">
					<label><mmr:message
									messageId="label.prompt.password" /></label>
					<div class="controls">
						<span>:</span>
						<s:password size="24"
									key="customer.password" name="customer.password" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message
									messageId="label.retype.password" /></label>
					<div class="controls">
						<span>:</span>
						<s:password size="24"
									key="customer.retypePassword" name="customer.retypePassword" />
					</div>	
				</div>
				<div class="fields2">
					<label><mmr:message messageId="label.company" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.name" name="customer.name" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.customer.contact" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="customer.address.contactName" name="customer.address.contactName" />
					</div>	
				</div>	

				<div class="fields2">
					<label><mmr:message
									messageId="label.customer.address1" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.address.address1" name="customer.address.address1" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.customer.address2" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.address.address2" name="customer.address.address2" />
					</div>	
				</div>
				<div class="fields2">
					<label><mmr:message messageId="label.customer.city" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.address.city" name="customer.address.city" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.customer.postalCode" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.address.postalCode" name="customer.address.postalCode" />
					</div>	
				</div>
				<div class="fields2">
					<label><mmr:message messageId="label.customer.country" /></label>
					<div class="controls">
						<span>:</span>
						<s:select listKey="countryCode"  listValue="countryName" name="customer.address.countryCode"
									headerKey="-1" list="#session.CountryList"
									onchange="javascript:showState();" id="firstBox" theme="simple" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.customer.province" /></label>
					<div class="controls">
						<span>:</span>
						<s:select key="customer.address.provinceCode"
									name="customer.address.provinceCode"
									listKey="provinceCode" listValue="provinceName"
									list="#session.provinces"  cssStyle="width:152px;height:23px;"/>
					</div>	
				</div>
				<div class="fields2">
					<label><mmr:message messageId="label.customer.phone" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="customer.address.phoneNo" name="customer.address.phoneNo" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.customer.email" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="customer.address.emailAddress"
									name="customer.address.emailAddress" />
					</div>	
				</div>		
				<div class="fields2">
					<label><mmr:message messageId="label.customer.timezone" /></label>
					<div class="controls">
						<span>:</span>
						<s:select key="customer.timezone"
									name="customer.timeZone" headerKey="1" listKey="id"
									listValue="name" list="#session.timeZones" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.creditcard.number" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"	key="creditCard.ccNumber" name="creditCard.ccNumber" />
					</div>	
				</div>	
				<div class="fields2">
					<label><mmr:message	messageId="label.creditcard.cvdCode" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="creditCard.cvd" name="creditCard.cvd" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.creditcard.expiryMonthYear" /></label>
					<div class="controls">
						<span>:</span>
						<s:select required="true"
									list="#{'01':'Jan', '02':'Feb', '03':'Mar', '04':'Apr', '05':'May', '06':'Jun', '07':'Jul', '08':'Aug', '09':'Sep', '10':'Oct', '11':'Nov', '12':'Dec'}"
									key="creditCard.ccExpiryMonth" name="creditCard.ccExpiryMonth" id="month"
									cssClass="width:90px;" /> <s:select
									list="#{ '2014':'2014', '2015':'2015', '2016':'2016', '2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}"
									key="creditCard.ccExpiryYear" name="creditCard.ccExpiryYear" id="year"
									cssClass="width:0px !important;" />
					</div>	
				</div>	
				<div class="fields2">
					<label><mmr:message messageId="label.creditcard.nameOnCard" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="creditCard.billingAddress.contactName"
									name="creditCard.billingAddress.contactName" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.creditcard.billingAddress1" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="creditCard.billingAddress.address1"
									name="creditCard.billingAddress.address1" />
					</div>	
				</div>	
				<div class="fields2">
					<label><mmr:message messageId="label.creditcard.billingAddress2" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="creditCard.billingAddress.address2"
									name="creditCard.billingAddress.address2" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.creditcard.billingCity" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24" key="creditCard.billingAddress.city"
									name="creditCard.billingAddress.city" />
					</div>	
				</div>	
				<div class="fields2">
					<label><mmr:message messageId="label.creditcard.billingPostalCode" /></label>
					<div class="controls">
						<span>:</span>
						<s:textfield size="24"
									key="creditCard.billingAddress.postalCode"
									name="creditCard.billingAddress.postalCode" />
					</div>	
				</div>	
				<div class="fields_signup">
					<label><mmr:message messageId="label.creditcard.billingCountry" /></label>
					<div class="controls">
						<span>:</span>
						<s:select cssStyle="width:152px;height:23px;" listKey="countryCode"
									listValue="countryName"
									name="creditCard.billingAddress.countryCode" headerKey="-1"
									list="#session.CountryList"
									onchange="javascript:showBillingState();" id="billingCountry"
									theme="simple"  />
					</div>	
				</div>	
				<div class="fields2">
					<label><mmr:message messageId="label.creditcard.billingProvince" />e</label>
					<div class="controls">
						<span>:</span>
						<s:select key="creditCard.billingAddress.provinceCode"
									name="creditCard.billingAddress.provinceCode"
									listKey="provinceCode" listValue="provinceName"
									list="#session.provinces" />
					</div>	
				</div>	
				<div class="fields_signup_captcha">
					<label><mmr:message messageId="label.enter.captcha" /></label>
					<div class="controls">
						<span>:</span>
						<input type="text" size="8" name="turing" value="" />								
					</div>	
				</div>	
				
				<div class="userverify">
					<label>&nbsp;</label>
					<div class="controls">
					<img src="<%=request.getContextPath()%>/jcaptcha" />
					</div>	
				</div>	
			</div>
			<div class="aligncenter">
				<input
								type="checkbox" name="checkbox" value="checkbox" class="accept" />
								<label for="accept" class="acceptLabel"><mmr:message messageId="label.acceptagreement" />  <a
									href="<s:url value="%{#session.business.termsURL}"  />"
									target="_blank" style="color: blue;"><mmr:message messageId="label.acceptagreementhere" /> </a>
							</label>
			</div>	
			<div class="aligncenter_signup form_buttons">
								
							<input type="button" name="Sign Up" value="<mmr:message messageId="label.btn.signup"/>"
								 onClick="javascript:submitSignups()"/>
			</div>
		</div>


	</form>
	
	
	
	
	
	</div>
		</div>
		</div>
	</div>	
	<div class="footer">
		<div class="footer_body">
			<div class="footer_inner">
				<p>&copy;2014 Integrated Carriers. All Rights Reserved</p>
				<p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p>
			</div>
		</div>
	</div>

</body>

</html>
