<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html> 
<head> 
    <title><s:text name="edi.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function processNow() {
	if(document.getElementById("uploadBox").value != "") {
		alert('Processing File Now...');
		document.uploadEdiform.action = "uploadAndProcess.action";
		document.uploadEdiform.submit();
	}
	else{
	alert("please select the file to upload");
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
<div class="content">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="uploadedi" cssClass="form" method="post" enctype="multipart/form-data" name="uploadEdiform">
	<s:token/>
<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="menu.admin.uploadedi"/></div>
								<div class="form_buttons" >	
									<a href="javascript: processNow()" ><mmr:message messageId="label.edi.process.now"/></a>
									<a href="javascript: processLater()" onclick="return false"><mmr:message messageId="label.edi.process.later"/></a>
								</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields_topdown">
										<label><mmr:message messageId="label.track.carrier"/></label>
										<div class="controls">
											<s:select  listKey="id" 
											listValue="name" name="ediInfo.carrierId" list="#session.CARRIERS" 
											headerKey="-1" id="carrier" theme="simple"/>
										</div>
									</div>
									<div class="fields_topdown">
										<s:label key="File"/>
										<div class="controls" style="width:250px;">
											<s:file name="upload" id="uploadBox" label="File" cssStyle="width:200px;" key="upload"  theme="simple" cssClass="text_01_select_file"/>
										</div>
									</div>
								</div>
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
