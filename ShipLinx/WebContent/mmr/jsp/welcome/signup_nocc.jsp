<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}"/></title>
<style type="text/css">
<!-- @import url("styles/shiplinx_login01_styles.css"); -->
</style>
<link href="<s:url value='/mmr/styles/Shiplinx.css' includeContext="true"/>" rel="stylesheet" type="text/css" />

</head>

<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>

<script language="JavaScript" src="<s:url value='/mmr/scripts/submitEnter.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/utils.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/cookies.js' includeContext="true"/>"></script>
<script language="JavaScript">

function submitSignUp() {
	var on = document.signupForm.elements['checkbox'].checked;	
	if(!on){
		alert('Please read and accept the terms and conditions listed');
		//return false;
	}
	else
	{
		var theForm = document.getElementById("signupForm");
		theForm.submit();
	}
	//	document.forms.loginForm.submit();
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
		firstBox = document.getElementById('billingCountry');
	  url="<%=request.getContextPath()%>/customer.listProvince.action?value="+billingCountry.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
} // End function showState()	

//use this functions if just user name will be remembered until checkbox is marked off

</script>

<form action="doSignupNoCC.action" method="post" id="signupForm" name="signupForm">
<div id="wrapper">
	
	<div id="logo" style="margin: auto;">
		<s:if test="%{#session.logoURL!=null}">
			<s:set name="url" value="%{#session.logoURL}" />
			<img src="<s:property value="%{url}"/>" border="0" width="310px" height="82px" includeContext="true"/>
		</s:if> 
		<s:else>
			<a href="<s:property value="%{#session.business.logoutURL}"/>" target="_blank"><img src="<%=request.getContextPath()%>/mmr/images/<s:property value="%{#session.business.logoFileName}"/>" style="height: 90px; width: 300px;"/></a>
		</s:else>
	</div>
	<hr style="color:#00A53C; height:1px; width: 675px;">
	<h3 style="text-align: center;"><font color="blue" style="font-family: Helvetica,sans-serif;">Sign up &amp; Start Shipping!</font></h3>
	<div id="messages">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>
	<div id="text">	
	<table width="120%" border="0" cellspacing="0" cellpadding="5">
	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.username"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.username" name="customer.username" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.prompt.password"/>:</td>    
	    <td width="25%"><s:password size="24" key="customer.password" name="customer.password" cssClass="text_02_tf"/></td>
	   </tr>
	  <tr>
	    <td width="20%"><mmr:message messageId="label.retype.password"/>:</td>
	    <td width="25%"><s:password size="24" key="customer.retypePassword" name="customer.retypePassword" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.company"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.name" name="customer.name" cssClass="text_02_tf"/></td>
	  </tr>
	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.contact"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.contactName" name="customer.address.contactName" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.customer.address1"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.address1" name="customer.address.address1" cssClass="text_02_tf"/></td>
	  </tr>
	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.address2"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.address2" name="customer.address.address2" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.customer.city"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.city" name="customer.address.city" cssClass="text_02_tf"/></td>
	  </tr>
	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.postalCode"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.postalCode" name="customer.address.postalCode" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.customer.country"/>:</td>
	    <td width="25%">						
	    	<s:select cssClass="text_01_combo_big" cssStyle="width:135px;" listKey="countryCode" listValue="countryName" 
								name="customer.address.countryCode" headerKey="-1"  list="#session.CountryList" 
									onchange="javascript:showState();"  id="firstBox" theme="simple"/>
	  	</td>
	  </tr>

	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.province"/>:</td>
	    <td id="stateid" width="25%">						
	    	<s:select key="customer.address.provinceCode" name="customer.address.provinceCode"  cssClass="text_01_combo_big" cssStyle="width:135px;" 
								listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
	  	</td>
	    <td width="5%">&nbsp;</td> 
	    <td width="20%"><mmr:message messageId="label.customer.phone"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.phoneNo" name="customer.address.phoneNo" cssClass="text_02_tf"/></td>
	  </tr>
	  <tr>
	    <td width="20%"><mmr:message messageId="label.customer.email"/>:</td>
	    <td width="25%"><s:textfield size="24" key="customer.address.emailAddress" name="customer.address.emailAddress" cssClass="text_02_tf"/></td>
	    <td width="5%">&nbsp;</td>
	    <td width="20%"><mmr:message messageId="label.customer.timezone"/>:</td>
	    <td width="25%"><s:select key="customer.timezone" cssStyle="width:140px;" cssClass="text_01_combo_big" name="customer.timeZone" headerKey="1"   
							listKey="id" listValue="name" list="#session.timeZones"/></td>
	  </tr>
	
	  <tr>
	    <td width="20%"><mmr:message messageId="label.enter.captcha"/>:</td>
	   	<td width="15%"><input type="text" size="8" name="turing" value=""/></td>
	  </tr>
	  <tr>
	  	<td width="20%"/>
	   	<td align="left" colspan="3"><img src="<%=request.getContextPath()%>/jcaptcha" />	   	
	   	</td>	  	
	  </tr>
  	  <tr>
  		<td colspan="5" width="50%" align="center">
			<input type="checkbox" name="checkbox" value="checkbox" class="accept"/>
  	   		<label for="accept" class="acceptLabel">I accept the terms of agreement listed <a href="<s:url value="%{#session.business.termsURL}"  />" target="_blank" style="color: blue;">here</a> </label>
  	   	</td>
  	  </tr>  
	  <tr>
	    <td colspan="5" width="50%" align="center"><input onclick="submitSignUp()"  type="button" name="Sign Up" value="Sign Up Now!"/></td>
	  </tr>
	</table>

	</div>
	</div>
	
	<br/>
	<div id="copy">Copyright © 2012    |   <s:property value="%{#session.business.name}"/>    |    All rights reserved</div>
	

</form>
</body>
</html>
