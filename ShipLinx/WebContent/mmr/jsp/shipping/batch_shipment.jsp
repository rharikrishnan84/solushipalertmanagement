<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html> 
<head> 
    <title><s:text name="batch.shipment.title"/></title> 
    <sj:head jqueryui="true" />
    <sx:head />
</head> 
<body> 

<SCRIPT language="JavaScript">
	function upload() {
	if(document.getElementById("uploadBox").value != "") {
//		alert('Uploading Batch Shipment File...');
		document.uploadBatchForm.action = "batch.shipment.upload.file.action";
		document.uploadBatchForm.submit();
		}
		else{
			alert("please select the file to upload");
		}
	}
	function createShipments() {
		document.uploadBatchForm.action = "batch.create.shipments.action";
		document.uploadBatchForm.submit();
	}	
	function processShipments() {
		document.uploadBatchForm.action = "batch.process.shipments.action";
		document.uploadBatchForm.submit();
	}
    function showState() {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function()
		  {
			   if(ajax_Service.readyState==4)
				{
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById("stateid");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox');
		  url="<%=request.getContextPath()%>/markup.listService.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
		  	ajax_Service.open("GET",url,true);
		  	ajax_Service.send(this);
	} // End function showState()	
	
	function repeatOrder()
		
		{

			var editUserId = document.getElementsByName("check_uncheck");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please check atleast one');
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
				
				document.searchform.action = "repeat.order.action?order_id="+txt;
				document.searchform.submit();
			
			}
			//alert(oid);
			
		}
</SCRIPT>
<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
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
<div class="content">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>
 
<s:form action="batch.shipment.upload.file" method="post" cssClass="form" enctype="multipart/form-data" name="uploadBatchForm">
	<s:token/>
<div class="content_body" >	
							<div class="content_table"  > 
								<div class="content_header" id="batchupld_panel">
									<div class="cont_hdr_title"><mmr:message messageId="menu.admin.batchshipment"/></div>
									<div class="form_buttons" id="batchupld_srch_actions">
										<a href="javascript: upload()"><mmr:message messageId="label.upload.file"/></a>
										<a href="javascript: createShipments()"><mmr:message messageId="label.create.shipments"/></a>
										<a href="javascript: processShipments()"><mmr:message messageId="label.process.shipments"/></a>
									</div>
								</div>		
								<div class="cont_data_body" id="batchupld_srch_table">
									<div class="rows">
										<div class="fieldsvl">
											<label style="padding-top: 6px; !important; padding-right:10px;"><s:label key="Batch Shipments File"/></label>
											<div class="controls"><span style=" padding-top:2px;">:</span>
												<s:file id="uploadBox" name="upload" label="File" key="upload"  theme="simple" cssClass="text_01_select_file"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.track.carrier"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_big"  listKey="id" listValue="name" 
							name="batchShipmentInfo.carrierId" headerKey="" headerValue=""  list="#session.CARRIERS" 
								onchange="javascript:showState();"  id="firstBox" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label ><mmr:message messageId="label.markup.service"/></label>
											<div class="controls" id="stateid"><span>:</span>
												<s:select cssClass="text_01_combo_big" listKey="id" listValue="name"
							name="batchShipmentInfo.serviceId" list="#session.SERVICES" 
								headerKey="-1" id="secondBox" theme="simple"/>
											</div>
										</div>

									</div>	
								</div>
							</div>
						</div>
</s:form>
<div id="formResult">
 <jsp:include page="list_shipment.jsp"/>
</div>

