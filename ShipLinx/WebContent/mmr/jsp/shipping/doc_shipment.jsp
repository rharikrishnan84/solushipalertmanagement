<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html>
<head>
<title>Insert title here</title>
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	<style type="text/css">
	.fields_radio {
    width: 314px;
    margin-left: 5px;
    float: left;
    padding: 3px 0px;
    height: auto;
    font-size: 12px;
}
	</style>
</head>
<!-- <body>  -->
<body onload="hideCustomerRadio()"> 

<SCRIPT language="JavaScript">
function hideCustomerRadio()
{
	document.getElementById("customerRadioId").style.display="none";
	}
 
 function uploadDoc()
 { 
	 
	var comment = document.getElementById("comment_area").value;
	var fname = document.getElementById("file_name").value;
	comment = comment.replace(/(\r\n|\n|\r)/gm," ");
	 fname=fname.replace(/(\r\n|\n|\r)/gm," ");
	 var filePath=$('#fileUpload').val();
	 
	 
	var orderId = document.viewform.orderId.value;
	 
	var statusid = document.getElementById("doc_type").value;
	
	if(statusid == -1)
	{
		alert("Please Select a Document Type");
	}else if(filePath ==""){
		alert("Please Select a File");
	} 
	var ext= /[^.]+$/.exec(filePath);
	 
	if(ext=="doc" || ext=="docx" || ext=="pdf" || ext=="xls" || ext=="xlsx" || ext=="odt" ){
 
 
		var vis=$('input[name="document.visibilty"]:checked').val();
		document.getElementById("hiddenVis").value=vis;
		$('#loader1').css('display','block');
		$('#loaderImg1').css('display','block');
		
        document.getElementById("uploadForm").action="upload.document.action";
		document.getElementById("uploadForm").submit();
	  
	}else{
		alert("upload only document like doc,docx,pdf,xls,xlsx")
	}	  
 }
 
 function deleteComment(id)
 {
 	var orderId = document.viewform.orderId.value;
 	 ajax_Country=ajaxFunction();
				ajax_Country.onreadystatechange=function()
				  {
					   if(ajax_Country.readyState==4)
						{
						reponse=ajax_Country.responseText;
						js_stateid=document.getElementById("comment_table");
						js_stateid.innerHTML= reponse;
						$('#sample1').dataTable(); 
						
						$('table').wrap('<div class="grid_table_body"></div>');
						$("#sample1_length").wrap("<div class='box-cont1'></div>");
						$("div.box-cont1").each(function() {
						  $(this).append($(this).next());
						});
						$(".dataTables_info").wrap("<div class='box-cont2'></div>");
						$("div.box-cont2").each(function() {
						  $(this).append($(this).next());
						});
						$('.grid_table_body').css('overflow-x','scroll');
						}
				  }
				  	url="deleteComment.action?commentId="+id+"&oid="+orderId;
					ajax_Country.open("GET",url,true);
					ajax_Country.send(this);
 }
 
 function updateOrder(oid)
 {
 	var orderId = document.viewform.orderId.value;
 	alert(orderId);
 }
	
 function deleteDoc(docid){
  
		var orderId = document.viewform.orderId.value;
		 
	 	 ajax_Country=ajaxFunction();
					ajax_Country.onreadystatechange=function()
					  {
						   if(ajax_Country.readyState==4)
							{
							reponse=ajax_Country.responseText;
							js_stateid=document.getElementById("docList");
							js_stateid.innerHTML= reponse;
							$('#sample11').dataTable(); 
							
							$('table').wrap('<div class="grid_table_body"></div>');
							$("#sample1_length").wrap("<div class='box-cont1'></div>");
							$("div.box-cont1").each(function() {
							  $(this).append($(this).next());
							});
							$(".dataTables_info").wrap("<div class='box-cont2'></div>");
							$("div.box-cont2").each(function() {
							  $(this).append($(this).next());
							});
							$('.grid_table_body').css('overflow-x','scroll');
							}
					  }
					  	url="delete.doc.action?documentId="+docid+"&orderId="+orderId;
						ajax_Country.open("GET",url,true);
						ajax_Country.send(this);
	}
  

	function updateDoc(){
		 
			 
				var vis=$('input[name="document.visibilty"]:checked').val();
				var orderId = document.viewform.orderId.value;
				document.getElementById("hiddenVis").value=vis;
				document.getElementById("uploadForm").action ="update.doc.action?orderId="+orderId;
				document.getElementById("uploadForm").submit();
			  
			}
		
		
	 
	
	function editDoc(){
		var editUserId = document.getElementsByName("searchUserCheckBox");
		var orderId = document.viewform.orderId.value;
		var i1,txt1 = 0;
	   for (i1=0;i1<editUserId.length;i1++){
		if (editUserId[i1].checked){
		 txt1 += 1;      
		}
	   }
	   if(txt1 < 1){
		alert('Please select at least one');
	   }
	    else if(txt1 > 1){
		alert('Please check atmost one');
	   }
	   else{
			var i,txt;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
					txt = editUserId[i].value ;					
				}
			}
			 
				document.getElementById("uploadForm").action = "edit.doc.action?documentId="+txt+"&orderId="+orderId;
				document.getElementById("uploadForm").submit();
			  
			}
		
		}
	
	
</SCRIPT>



<s:form id="uploadDocForm">
<div class="cont_data_body" style="overflow:hidden; margin-bottom:10px;">
 <%-- <s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}"> --%>
 <s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')||#session.ROLE.contains('customer_admin')}">
	<div id="form-container">
				<div class="content_header">
					<s:if test="#session.edit == 'true'">
					<div class="cont_hdr_title"><mmr:message messageId="label.edit.doc"/></div>
					</s:if><s:else><div class="cont_hdr_title"><mmr:message messageId="label.add.doc"/></div></s:else>
								
								<div class="cont_hdrtitle_l" style="width:300px">ORDER #<s:property value="%{selectedOrder.id}" /></div>
								<div class="form_buttons" id="add_info_shipping_actions" >	
									<s:if test="#session.edit != 'true'">
									<a id="uploadDoc"  href="javascript: uploadDoc()"
				style="text-decoration: none;"><mmr:message messageId="label.upload.file"/></a>
				</s:if>
				<s:if test="#session.edit == 'true'">
				<a id="updateDoc"  href="javascript: updateDoc()"
				style="text-decoration: none;"><mmr:message messageId="label.btn.save"/></a> 
				</s:if>

								</div>
				</div>	
				<div class="cont_data_body" id="add_info_shipping_table">
								<div class="rows">
									    
								
				               	<div class="fields">
										<label><mmr:message messageId="label.feedback.name"/></label>
										<div class="controls">
											<span>:</span>
											<s:textfield  name="document.name"  key="document.name" id="file_name"></s:textfield> 
										</div>
									</div>
									 	<div class="fields">
										<label><mmr:message messageId="label.upload.file"/>:</label>
										<div class="controls">
											 
							     	<s:select  headerKey="-1" headerValue="Select Document Type" 
									list="#{'Bill of Lading':'Bill of Lading', 'Commercial Invoice':'Commercial Invoice', 'Packing List':'Packing List', 'POD':'POD','Insurance':'Insurance'}" 
									name="document.docType" value="document.docType" id="doc_type" />
				               	
										</div>
									</div>
									
							<div class="fields">
										<label><mmr:message messageId="label.ghead.description"/>:</label>
										<div class="controls">
											<span>:</span>
											<s:textarea cols="50" rows="2" name="document.description" key="document.description" id="comment_area"></s:textarea> 
										</div>
									</div>
									<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
									<div class="fields_radio">
										 
										<div class="controls">
											 
											 <s:radio name="document.visibilty" list="#{'1':'Public','2':'Private'}" value="%{document.visibilty}"  id="visibility1"/>
											 <input type="hidden" id="hiddenVis" name="scope" > 
										</div><br>
									</div>
									</s:if>
									<s:elseif test="%{#session.ROLE.contains('customer_admin')}">
									<div class="fields_radio" id="customerRadioId">
										 
									<div class="controls">
											 
											 <s:radio name="document.visibilty" list="#{'1':'Public'}" value="1"  id="visibility1" disabled="true"/>
											 <input type="hidden" id="hiddenVis" name="scope" > 
										</div><br>
									</div>
									</s:elseif>
									<s:if test="#session.edit != 'true'">	
				               	 <div class="fields_topdown">
								
										<s:label key="File"/>
										<div class="controls" style="width:250px;">
											<s:file name="document.uploadDoc"   label="File" cssStyle="width:200px;" key="document.uploadDoc"  theme="simple" id="fileUpload" cssClass="text_01_select_file"/>
											</div>
										
								</div>
								</s:if>
											<s:if test="#session.edit == 'true'">
											<s:hidden name="document.documentId" key="document.documentId"></s:hidden>
									</s:if>
					</div>
					</div>
	</div>
</s:if>
	<s:if test="#session.edit != 'true'">
<div id="docList">
	<jsp:include page="doc_info_shipment.jsp"/>
	</div>
	</s:if>
</div>
	
</s:form>
   
</body>
</html>