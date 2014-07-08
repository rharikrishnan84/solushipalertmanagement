<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head> 
    <title><s:text name="edi.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function processNow() 
	{	
		if(document.getElementById("erase_add_cb_id").checked)
		{
			document.uploadAddform.action= "uploadaddressbook.action?delete=true";
			document.uploadAddform.submit();
		}
		else{
			if(document.getElementById("uploadBox").value != "") {
				document.uploadAddform.action= "uploadaddressbook.action";
				document.uploadAddform.submit();
			}
				else{
				alert("please select the file to upload");
				}
		}
	}
	
	function processLater() {
		document.uploadEdiform.action = "uploadAndProcessLater.action";
		document.uploadEdiform.submit();
	}
	
	
</SCRIPT>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="uploadaddressbook" method="post" enctype="multipart/form-data" name="uploadAddform">
	<s:token/>
<div class="content">
	<div class="content_body">
		<div class="content_table" >
			<div class="content_header" id="addupld_panel">
				<div class="cont_hdr_title" id="srch_crtra"><mmr:message messageId="menu.upload.addressbook"/></div>
				<div class="form_buttons">
					<s:a href="address_book_template.zip" cssStyle="text-decoration: none;">DOWNLOAD</s:a>	
					<a id="upload_icon"  border="0" onclick="processNow();">UPLOAD</a>		
				</div>
			</div>
			<div class="cont_data_body" id="addupld_srch_table">
				<p><mmr:message messageId="line.upload.addressbook"/></p>
				<div class="rows">
					<div class="fieldsvl">
						<label><mmr:message messageId="label.upload.file"/></label>
						<div class="controls">
							<span>:</span>
							<s:file id="uploadBox" name="upload" label="File" key="upload"  theme="simple" cssClass="text_01_select_file"/>
						</div>
					</div>	
					
					<div class="fields_checkleft erase_add">
						<div class="controls">
							<s:checkbox name="erase_add_cb" id="erase_add_cb_id"/>
						</div>
						<span>:</span>
						<label><mmr:message messageId="label.upload.file.eraze.address"/></label>
					</div>
				</div>
			</div>
		</div>
	</div>	
<div id="addupld_upload_icon"></div>	
<div id="addressbook_icon"></div>
	<div class="content_body">
		<div class="content_table" >
			<div class="content_header">
				<div class="cont_hdr_title" id="address_instructions_hdr"><mmr:message messageId="label.addressbook.instruction"/></div>
			</div>
			<div class="cont_data_body" id="address_instructions_mid">
				<p><mmr:message messageId="instructions.upload.addressbook"/></p>
			</div>
			<div id="address_instructions_bot"></div>
		</div>
	</div>
</div>
</s:form>
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>	


</div>
</div>


	
	
	
	
	
	
	
	
	
	
	
	
	
	

