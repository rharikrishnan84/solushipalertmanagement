<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	session.invalidate();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}"/></title>


</head>

<body>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<script language="JavaScript" src="<s:url value='/mmr/scripts/submitEnter.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/utils.js' includeContext="true"/>"></script>
<script language="JavaScript" src="<s:url value='/mmr/scripts/cookies.js' includeContext="true"/>"></script>
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
<div id="bottom_left">
<form action="j_acegi_security_check" method="post" id="loginForm" name="loginForm">
<s:hidden name="nextAction" />
<div id="text"><table width="200" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="4" class="text_01"><div id="title_text">Login</div></td>
  </tr>
  
  <tr>
	<td colspan="4" class="text_01"><mmr:message messageId="label.prompt.username"/>:</td>
    </tr>
  <tr>
	 <td colspan="4" class="text_02">
			<s:if test = "acegiLogin">
				<s:textfield name="j_username" id="username" 
					size="25" maxlength="50" onkeypress="return keyPressSubmitRememberPassword(this,event)" cssClass="text_02"  />
			</s:if>
			<s:else>
				<s:textfield name="j_username" id="username" 
					size="25" maxlength="50" onkeypress="return keyPressSubmit(this,event)" cssClass="text_02"  />
			</s:else>
	 </td>
    </tr>
  <tr>
    <td colspan="4" class="text_01"><mmr:message messageId="label.prompt.password"/>:</td>
    </tr>
  <tr>
    <td colspan="4" class="text_02">
		<s:if test = "acegiLogin">
				<s:password id="password" name="j_password" maxlength="25"
					size="20" onkeypress="return keyPressSubmitRememberPassword(this,event)" cssClass="text_02" maxlength="20"/>
			</s:if>
			<s:else>
				<s:password id="password" name="j_password" maxlength="25"
					size="20" onkeypress="return keyPressSubmit(this,event)" cssClass="text_02" maxlength="20"/>
			</s:else>
	</td>
    </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4"  class="text_01">
		<s:if test = "acegiLogin">
			<s:checkbox  name="_acegi_security_remember_me"  cssClass="text_01" value="checkbox"/>
		</s:if>
		<s:else>
			<input name="_acegi_security_remember_me" type="checkbox" id="rememberMe"
				value="rememberMe" onkeypress="return keyPressSubmit(this,event)" type="checkbox" class="text_01" value="checkbox"/>
		</s:else>
	&nbsp;&nbsp;<mmr:message messageId="label.remember.me"/></td>
    </tr>
	
  <tr>
    <td colspan="4">&nbsp;</td>
    </tr>
  <tr valign="bottom">
    <td width="65"><img src="<%=request.getContextPath()%>/mmr/images/icon_submit.gif" alt="Submit" width="21" height="24" /><a onclick="loginSubmitACEGI()" href="#" >Submit</a></td>
    <td width="10"><img src="<%=request.getContextPath()%>/mmr/images/spacer_white.gif" width="10" height="10" /></td>
    <td width="149" colspan="2"><img src="<%=request.getContextPath()%>/mmr/images/icon_reset.gif" alt="Reset" width="20" height="24" /><a href="viewLogon.action">Reset</a><a href="#"></a></td>
    </tr>
  
  <tr valign="bottom" class="text_01">
    <td colspan="4"><div id="title_text">&nbsp;</div></td>
  </tr>
  <tr valign="bottom">
    <td colspan="4"><div id="title_text1">Its easy to switch to Shiplinx. Know how...</div></td>
  </tr>
  <tr valign="bottom" class="text_01">
    <td colspan="4" class="title_text">New to Shiplinx? </td>
  </tr>
  <tr valign="bottom">
    <td colspan="4" class="text_01">Start Today. Download a Shiplinx welcome kit and learn how to ship.<br />
      Choose your account options.</td>
  </tr>
  <tr valign="bottom">
    <td colspan="4"><img src="<%=request.getContextPath()%>/mmr/images/icon_sign_up.gif" width="25" height="24" />
    <a href="<%=request.getContextPath()%>/signup.action">Sign Up</a></td>
  </tr>
</table>
</div>
</form>
</div>

</body>
</html>