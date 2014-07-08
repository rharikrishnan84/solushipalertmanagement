<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html> 
<head> 
    <title><s:text name="upload.rate.template.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function processNow() {
		alert('Processing Rate Template File Now...');
		document.uploadRateTemplateForm.action = "uploadAndProcessRateTemplate.action";
		document.uploadRateTemplateForm.submit();
	}
</SCRIPT>

<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="uploadRateTemplate" method="post" enctype="multipart/form-data" name="uploadRateTemplateForm">
	<s:token/>
	<div id="rateupld_panel">
		<table>
		<tr>
		<td><div id="srch_crtra"><mmr:message messageId="menu.admin.uploadratetemplate"/></div></td>
		<td>&nbsp;</td>
		</tr>
		</table>
	</div>

<div id="rateupld_srch_table">
<table width="930px" border="0" cellpadding="4" cellspacing="0">
				<tr>
					<td width="300px"></td>
					<td width="350px"></td>
					<td width="400px"></td>
					<td width="350px"></td>
					<td width="350px"></td>
					<td width="15px"><!--<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />"
							border="0" style="margin-bottom: -3px;">&nbsp;--></td>
					<td width="100px" valign="middle" align="center"><a href="javascript: history.go(-1)"><mmr:message messageId="label.navigation.back"/></a></td>
							<td width="15px"><!-- &nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />"
							border="0" style="margin-bottom: -3px;"> --></td>
					<td width="250px" align="center" valign="middle"><a href="javascript: processNow()"><mmr:message messageId="label.edi.process.now"/></a></td>
				</tr>
				<tr>
					<td class="rate_upload_text_03"><mmr:message messageId="label.track.carrier"/>:</td>
					<td class="rate_upload_text_03"><mmr:message messageId="label.track.service"/>:</td>
					<td class="rate_upload_text_03"><mmr:message messageId="label.customer.name"/>:</td>
					<td class="rate_upload_text_03"><s:label key="File"/>:</td>
					<td class="rate_upload_text_03">Overwrite:</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>	
				<tr>
					<td class="rate_upload_text_03_val"><s:property value="%{ltlService.carrier.name}"/></td>
					<td class="rate_upload_text_03_val"><s:property value="%{ltlService.name}"/></td>
					<td class="rate_upload_text_03_val"><s:property value="%{markup.customerBusName}"/></td>				
					<td width="350px" align="left"> 
						<s:file name="upload" label="File" key="upload"  theme="simple" cssClass="text_01_select_file"/>
					</td>
					<td class="rate_upload_text_03_val"><s:checkbox key="checkboxOverwrite" name="checkboxOverwrite" /></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
</div>

</s:form>
</div>
</div>
							