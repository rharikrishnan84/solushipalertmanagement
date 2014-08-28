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
		$(document).ready(function(){
			
		$('#upload_edi,#upload_edi_later').click(function(){
				$('#loader').css('display','block');
				$('#loaderImg').css('display','block');
				
		});
		});
	</script>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
</div>
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
									<a href="javascript: processNow()" id="upload_edi" ><mmr:message messageId="label.edi.process.now"/></a>
									<a href="javascript: processLater()" id="upload_edi_later" onclick="return false"><mmr:message messageId="label.edi.process.later"/></a>
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
