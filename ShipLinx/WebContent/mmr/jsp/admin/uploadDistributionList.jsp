<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<SCRIPT language="JavaScript">
	function submitform()
	{
	if(document.getElementById("address_name").value==""){
	alert("please enter name");
	}
	else{
	if(document.getElementById("uploadBox").value != "") {
	 document.dlistform.action = "upload.distribution.list.action";
	 document.dlistform.submit();
	}
	else
	{
	alert("please select the file to upload");
	}
	}
	}
</SCRIPT> 
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div class="form-container">
<s:form action="upload.distribution.list" method="post" enctype="multipart/form-data" name="dlistform">


<div class="content_body" >	
	<div class="content_table" id="right_left_new" > 
		<div class="content_header">
			<div class="cont_hdr_title" id="dlupload_text"><mmr:message messageId="label.shippingOrder.uploadDistribution" /></div>
			<div class="form_buttons" id="dlupload_actions" >	
				<a href="javascript:submitform()"><mmr:message messageId="label.btn.save"/></a>
				<a href="upload.distribution.init.action"><mmr:message messageId="label.btn.reset"/></a>
			</div>
		</div>	
		<div class="cont_data_body">
			<div class="rows" id="dlupload_panel">
				<div class="fields_topdown">
					<label><s:label key="Name"/></label>
					<div class="controls">
						<s:textfield size="24" id="address_name" cssClass="text_02_tf" key="name" name="name" theme="simple" />
					</div>
				</div>

				<div class="fields_topdown">
					<label><s:label key="File"/></label>
					<div class="controls" style="width:250px;">
						<s:file name="upload" id="uploadBox" label="File" key="upload" cssStyle="width:200px;"  theme="simple" cssClass="edi_text_03"/>
					</div>
				</div>	
			</div>
			
			<div id="dlupload_pnlicon">
			&nbsp;
			</div>

		</div>
	</div>
</div>	

</s:form>
</div>