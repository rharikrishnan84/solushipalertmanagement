<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="hasErrors()">
<div class="content">	
<div class="content_body" >	
	<div class="content_table" id="contenttbl" style="background-color:#FFF;width:960px !important;">
		<div id="errorMsgContainer">
			<div Style="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;">
				<s:actionerror cssClass="actionErrorStyle" cssStyle="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;"/>
				<s:fielderror cssClass="fieldErrorStyle" />
			</div>
		</div>
	</div>
</div>
</div>
</s:if>
<s:else>
<div class="content" >	
<div class="content_body" >	
	<div class="content_table" id="contenttbl" style="background-color:#FFF;">
		<div id="errorMsgContainer" >
			<div>
				<strong><s:actionmessage cssClass="actionErrorStyle" cssStyle="color:#009900; font-size:14px; padding:3px 20px; background-color: #F8ECE0;"/></strong>
			</div>
		</div>
	</div>
</div>
</div>	
</s:else>
