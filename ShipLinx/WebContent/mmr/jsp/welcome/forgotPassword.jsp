<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}"/></title>
<style type="text/css">

	/* sign up */
	
	.fgt_password:hover{ color:#ccc;}
	.fgt_password{color: #ffffff; text-decoration:none; font-weight:600;}
.fields_signup					{ width:373px; margin-left:5px; float:left; padding:3px 0px; height:25px;}
.fields_signup label				{ width:139px; float:left; padding-top:3px;}
.fields_signup .controls				{ width:174px; float:left;}
.fields_signup .controls span { padding-top:3px; padding-right:10px; float:left;}
.fields_signup .controls input[type="text"],.fields_signup .controls input[type="password"]{height:20px; width:152px; padding-left:5px; float:left;}
.fields_signup .controls input[type="checkbox"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }
.fields_signup .controls select			{ height:20px; width:75px; float:left;}
.fields_signup .controls textarea		{ height:20px; width:150px; float:left;}
.fields_signup a{ color:#990000;}


.userverify					{ width:600px; margin-left:5px; float:left; padding:3px 0px; height:auto;}
.userverify label				{ width:139px; float:left; padding-top:3px;}
.userverify .controls				{ width:300px; float:left;}


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
.fields2 .controls input[type='password'],.fields2 .controls input[type='text']{height:20px; width:152px; padding-left:5px; float:left;}


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
	.login_box ul li label{ width:130px; padding-top:3px; padding-right:5px; float:left; font-size:10px; text-align:right; }
	.login_box ul li select,.login_box ul li input{ float:left;  padding:0px;}
	.login_box ul li select{ height:22px; width:122px; }
	.login_box ul li input{ height:20px; width:120px; }
	.login_box input[type="submit"]{border:0px; background:transparent; font-size:10px; padding:0px;  float:left; margin-top:52px;}
</style>
<link href="<s:url value='/mmr/styles/shiplinx_login01_styles.css' includeContext="true"/>" rel="stylesheet" type="text/css" />

<link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css" media="screen and (min-width:1024px)"/>
</head>

<body>

<script language="JavaScript" src="<s:url value='/mmr/scripts/submitEnter.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/utils.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/cookies.js' includeContext="true"/>"></script>
<script language="JavaScript">

function submitForgotPassword() {
	var theForm = document.getElementById("loginForm");
	theForm.submit();
	//	document.forms.loginForm.submit();
}

// use this functions to enable ACEGI rememberme service

function loginSubmitACEGI() {
	var theForm = document.getElementById("toploginForm");
	theForm.submit();
	//	document.forms.loginForm.submit();	
}



// use this function to enable ACEGI rememberme servic

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


//use this functions if just user name will be remembered until checkbox is marked off

</script>













<div id="wrapper">
		<div class="top_line"></div>
		<div class="header-top">
			<div class="top_header_inner">
				<div class="header_logo_body">
					<div class="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo.png"></div>
							<div class="login_box">
						<form method="post" id="toploginForm" name="toploginForm" action="j_acegi_security_check">
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
						<input onclick="loginSubmitACEGI()" type="submit" style="cursor: pointer; float:right; padding-left: 5px;position: relative;bottom: 4px;font-size: 11px;" value="<mmr:message messageId="label.login" />"/>
						</form>
					</div>
			</form>		
				</div>
				<div class="navigation2">
					<div class="naviinner">
						<ul>	
							<li><a href="viewLogon.action" style="color: #ffffff;background-color:990000; "><mmr:message messageId="menu.home" /> </a></li>
							<li><a href="<%=request.getContextPath()%>/signup.action" style="color: #ffffff; "><mmr:message messageId="menu.signup" /> </a></li>
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
						<p><a href="forgotPassword.action" class="fgt_password" >FORGET PASSWORD</a></p>
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
								<div class="cont_hdrtitle_c2" style="width:500px;">
									<mmr:message messageId="label.forgotPassword.enterDetails"/>
								</div>
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<style>
			.fpassword_body{ margin:10px auto; height:auto; width:960px; }
			.fpassword{ height:auto; width:400px; margin:10px auto; font-size:12px; }
			.fields_signup_captcha					{ width:373px; margin-left:5px; float:left; padding:3px 0px; height:auto;}
.fields_signup_captcha label				{ width:139px; float:left; padding-top:3px;}
.fields_signup_captcha .controls				{ width:228px; float:left;}
.fields_signup_captcha .controls span { padding-top:3px; padding-right:10px; float:left;}
.fields_signup_captcha .controls input[type="text"],.fields_signup_captcha .controls input[type="password"]{height:20px; width:152px; padding-left:5px; float:left;}
.fields_signup_captcha .controls input[type="checkbox"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }
.fields_signup_captcha .controls select			{ height:20px; width:75px; float:left;}
.fields_signup_captcha .controls textarea		{ height:20px; width:150px; float:left;}
.fields_signup_captcha a{ color:#990000;}

.footer{ position:absolute; bottom:0px;}
		</style>
	
		<div class="content_body" style=" padding-bottom:20px !important;" >
			<!--<div class="fpassword_body">-->
				<div id="messages">
					<jsp:include page="../common/action_messages.jsp"/>
				</div>
		<form action="doForgotPassword.action" method="post" id="loginForm" name="loginForm">
				<div class="fpassword">
					
					
					
					<div class="fields_signup">
						<label><mmr:message messageId="label.prompt.username"/></label>
						<div class="controls">
							<span>:</span>
							<s:textfield name="j_username" id="username" class="text_01" maxlength="50" size="35" onkeypress="return keyPressSubmitRememberPassword(this,event)"/>
						</div>
					</div>
					
					<div class="fields_signup">
						<label><mmr:message messageId="label.email"/></label>
						<div class="controls">
							<span>:</span>
							<s:textfield id="email" name="j_email" class="text_01" maxlength="50" size="35" onkeypress="return keyPressSubmitRememberPassword(this,event)"/>
						</div>
					</div>
					
					<div class="fields_signup">
						<label><mmr:message messageId="label.enter.captcha"/></label>
						<div class="controls">
							<span>:</span>
							<input type="text" size="8" name="turing" value=""/>
						</div>
					</div>
					<div class="fields_signup_captcha">
						<label>&nbsp;</label>
						<div class="controls">
							<span>&nbsp;</span>
							<img src="<%=request.getContextPath()%>/jcaptcha" />
						</div>
					</div>

					<div class="fields_signup">
						<label>&nbsp;</label>
						<div class="controls">
							<span>&nbsp;</span>
							<input onclick="submitForgotPassword()"  type="submit" name="retrievePassword" value="Reset Password"/>
						</div>
					</div>

					
				</div>
			</form>
			<!--</div>-->
		
		</div>
	</div>	

	<div class="footer">
		<div class="footer_body">
			<div class="footer_inner">
				<!-- <p>&copy;2014 Integrated Carriers. All Rights Reserved</p>
				<p>Powered by ICU Leading the way. Designed by Integrated Carriers.</p> -->
				<p>&copy;<mmr:message messageId="message.footer.integratedcarriers"/></p>
				 <p><mmr:message messageId="message.footer.icu"/></p>   
			</div>
		</div>
	</div>











<!--

<div id="wrapper">
	<div id="top">&nbsp;</div>
	<div id="middle">
	<div id="logo">
		<s:if test="%{#session.business!=null}">
			<img src="<%=request.getContextPath()%>/mmr/images/<s:property value="%{#session.business.logoFileName}"/>" />
		</s:if>
		<s:else>
			<img src="<%=request.getContextPath()%>/mmr/images/logo_shiplinx.gif"/>
		</s:else>
	</div>
	<div id="text">
	<div id="messages">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>
	<table width="380" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="10"><strong><mmr:message messageId="label.forgotPassword.enterDetails"/></strong></td>
    </tr>
  <tr>
  <tr>
    <td colspan="10"><mmr:message messageId="label.prompt.username"/>:</td>
    </tr>
  <tr>
    <td colspan="10"><s:textfield name="j_username" id="username" class="text_01" maxlength="50" size="35" onkeypress="return keyPressSubmitRememberPassword(this,event)"/></td>
    </tr>
  <tr>
    <td colspan="10"><mmr:message messageId="label.email"/>:</td>
    </tr>
  <tr>
    <td colspan="10"><s:textfield id="email" name="j_email" class="text_01" maxlength="50" size="35" onkeypress="return keyPressSubmitRememberPassword(this,event)"/></td>
   </tr>

  <tr>
    <td colspan="10"><mmr:message messageId="label.enter.captcha"/>:</td>
    </tr>
  <tr>
  	<tr>
  		<td colspan="10"><input type="text" size="8" name="turing" value=""/></td>
  	</tr>
	<tr>
  	<td><img src="<%=request.getContextPath()%>/jcaptcha" /></td>
  	</tr>
  <tr>
    <td width="68"><input onclick="submitForgotPassword()"  type="submit" name="retrievePassword" value="Reset Password"/>
  </tr>
</table>

	</div>
	</div>
	
	<div id="bottom">&nbsp;</div>
	<div id="copy">Copyright © 2012    |   <s:property value="%{#session.business.systemName}"/>    |    All rights reserved</div>
	</div>
-->


</body>
</html>
