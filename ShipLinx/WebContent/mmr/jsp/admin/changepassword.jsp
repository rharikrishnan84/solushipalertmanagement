<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<SCRIPT LANGUAGE="JavaScript" SRC="<s:url value='/wbts/js/submitEnter.js' includeContext="true"/>"></SCRIPT>

<s:if test="forwardFlag">
	<head>
    	<META HTTP-EQUIV="Refresh" CONTENT="<s:property value="delayTime"/>;URL=<s:url value="%{nextAction}" includeContext="true" includeParams="none"/>">
	</head>
</s:if>
<s:else>
	<div class="browseBox">
	<h1><wbts:message messageId="label.change.password"/></h1>
	</div>
	<s:form action="changePassword" method="post" id="changePasswordForm"
		name="changePasswordForm">
		<s:hidden name="user.username" />
		<table border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td colspan="2" align="center">
				<h2><wbts:message messageId="note.change.password"/></h2>
				</td>
			</tr>
			<tr>
				<th><wbts:message messageId="label.existing.password"/></th>
				<td><s:password name="existingPassword" 
					maxlength="25" size="25" value="" onkeypress="return submitEnter(this, event)" /></td>
			</tr>
			<tr>
				<th><wbts:message messageId="label.prompt.password"/></th>
				<td><s:password name="user.password" maxlength="25" 
					size="25" value="" onkeypress="return submitEnter(this, event)"  /></td>
			</tr>
			<tr>
				<th><wbts:message messageId="label.retype.password"/></th>
				<td><s:password name="user.retypePassword" 
					maxlength="25" size="25" value="" onkeypress="return submitEnter(this, event)" /></td>
			</tr>
			<tr>
				<td>
				<h3>&nbsp;</h3>
				</td>
				<td><span class="button_2"><a href="javascript:document.forms.changePasswordForm.submit();"><wbts:message messageId="label.change.password"/></a></span></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</s:form>
	
	<script language="JavaScript">
		document.forms.changePasswordForm.existingPassword.focus();
	</script>
</s:else>