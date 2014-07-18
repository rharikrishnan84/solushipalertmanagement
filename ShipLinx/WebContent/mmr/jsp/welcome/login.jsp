<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}"/></title>
<style type="text/css">
<!--
@import url("styles/shiplinx_login01_styles.css");
-->
</style>
<!--   <link href="<s:url value='/mmr/styles/shiplinx_login01_styles.css' includeContext="true"/>" rel="stylesheet" type="text/css" />-->
<link
	href="<s:url value='/mmr/styles/Shiplinx.css' includeContext="true"/>"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
	<script language="JavaScript"
	src="<s:url value='/mmr/scripts/submitEnter.js' includeContext="true"/>"></script>
<script language="JavaScript"
	src="<s:url value='/mmr/scripts/utils.js' includeContext="true"/>"></script>
<script language="JavaScript"
	src="<s:url value='/mmr/scripts/cookies.js' includeContext="true"/>"></script>
<script>
	/*$(document).ready(function(){
		var wndo = $(window).height();
		wndo -=277;
		$('#wrapper_logo').css('min-height',wndo);
	});*/
</script>
<script language="JavaScript">

function setRememberUserName() {
	if(document.getElementById("username").value.length  > 0) {
		document.getElementById("rememberMe").checked = true;
	}
} 

// use this functions to enable ACEGI rememberme service

function loginSubmitACEGI() {
	var theForm = document.getElementById("loginForm");
	theForm.submit();
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


//use this functions if just user name will be remembered until checkbox is marked off

function loginSubmit() {
	if(document.getElementById("rememberMe").checked) {
		save_field(document.getElementById("username"));	
	} else {
		delete_cookie(document.getElementById("username"));
	}
	var theForm = document.getElementById("loginForm");
	theForm.submit();
}

function keyPressSubmit(myfield,e) {
	var keycode;
	if (window.event) keycode = window.event.keyCode;
	else if (e) keycode = e.which;
	else return true;
	
	if (keycode == 13) {
		if(document.getElementById("rememberMe").checked) {
			save_field(document.getElementById("username"));	
		} else {
			delete_cookie(document.getElementById("username"));
		}
	   myfield.form.submit();
	   return false;
    } else {
       return true;
    }
}
</script>


<style>
	.fgt_password:hover{ color:#ccc;}
	.fgt_password{color: #ffffff; text-decoration:none; font-weight:600;}
	#wrapper_logo{ 
		min-height:320px;
		background-repeat:no-repeat;
		background-position: center center;
		background-size:900px auto;
	}
	.login_box{
		width:290px; 
		height:70px;
		float:right;
		padding-right:32px;
	}
	.login_box ul{ float:left; height:auto; width:auto; list-style-type:none;}
	.login_box ul li{ height:25px;}
	.login_box ul li label{ width:70px; padding-top:3px; float:left; font-size:10px; }
	.login_box ul li select,.login_box ul li input{ float:left;  padding:0px;}
	.login_box ul li select{ height:22px; width:122px; }
	.login_box ul li input{ height:20px; width:120px; }
	.login_box input[type="submit"]{border:0px; background:transparent; font-size:10px;  float:left; margin-top:52px;}
	
	.footer{ position:absolute; bottom:0px;}
	
	
</style>
</head>
<body>
<form action="j_acegi_security_check" method="post" id="loginForm"
	name="loginForm"><s:hidden name="nextAction" />
	
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
						<input onclick="loginSubmitACEGI()" type="submit" value="LOG IN"/>
						</form>
					</div>
					
				</div>
				<div class="navigation2">
					<div class="naviinner">
						<ul>	
							<li><a href="viewLogon.action" style="color: #ffffff;background-color:990000; ">HOME</a></li>
							<li><a href="<%=request.getContextPath()%>/signup.action" style="color: #ffffff; ">SIGN UP</a></li>
							
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
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="content_body_index" >
			<div id="wrapper_logo" style=" width:100%; float:left; text-align:center" > 
    <s:if test="%{#session.logoURL!=null}">
       <s:set name="url" value="%{#session.logoURL}" />
       <img src="<s:property value="%{url}"/>" border="0" width="960" height="360" includeContext="true"/>
     </s:if> 
     <s:else>
       <img src="<%=request.getContextPath()%>/mmr/images/ic-logo-helping.png" width="960" height="360">
     </s:else> 
     
    <div id="messages">
    <jsp:include page="../common/action_messages.jsp" /></div>
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
</form>
</html>	