<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="hasErrors()">
<div id="errorMsgContainer">
<div class="errorMsgs">
<s:actionerror cssClass="actionErrorStyle" />
<s:fielderror cssClass="fieldErrorStyle" />
</div>
</div>
</s:if>
<s:actionmessage /> 
