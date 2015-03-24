<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html>
<head>
<title>Insert title here</title>
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
</head>
<body> 
<SCRIPT language="JavaScript">
 function submitinform()
 {
	//alert(document.getElementById("comment_area").value);
	//alert(document.getElementById("private").checked);
	//alert(document.viewform.orderId.value);
	var comment = document.getElementById("comment_area").value;
	comment = comment.replace(/(\r\n|\n|\r)/gm," ");
	var private= document.getElementById("private").checked;
	var orderId = document.viewform.orderId.value;
	var statusid = document.getElementById("status_id").value;
	if(statusid == -1)
	{
		alert("Please Select a Status to update")
	}
	else if(!comment.length>0)
	{
		alert("Please enter Comment");
	}	
	else
	{
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		{
		   if(ajax_Country.readyState==4)
			{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("comment_table");
				js_stateid.innerHTML= reponse;
				$('#sample1').dataTable(); 
				$("#check_all").click(function(){
					var temp=$(".dataTable-checkbox").attr("checked");
					if(temp == null){
					$(".dataTable-checkbox").attr("checked","checked");
					}
					else{
					$(".dataTable-checkbox").removeAttr("checked");
					}
				});
			
				
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
		url="add.comment.info.action?comment="+comment+"&pvt="+private+"&order_id="+orderId+"&statusId="+statusid;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);		
		
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
		
</SCRIPT>
<div class="cont_data_body">
 <s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
	<div id="add_comment_panel">

				<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.add.comment"/></div>
								<div class="cont_hdrtitle_l" style="width:300px">ORDER #<s:property value="%{selectedOrder.id}" /></div>
								<div class="form_buttons" id="add_info_shipping_actions" >	
									<a
				href="javascript: submitinform()"
				style="text-decoration: none;"><mmr:message messageId="label.update"/></a>

								</div>
				</div>	
				<div class="cont_data_body" id="add_info_shipping_table">
								<div class="rows">
								<div class="fields">
										<label><mmr:message messageId="label.status"/></label>
										<div class="controls">
											<span>:</span>
											<s:select cssClass="text_01_combo_biggest" listKey="id" listValue="name" 
											name="selectedOrder.statusId" list="#session.orderStatusOptionsList" headerKey="-1"  id="status_id" theme="simple"/>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.feedback.comment"/>:</label>
										<div class="controls">
											<span>:</span>
											<s:textarea cols="50" rows="2" id="comment_area"></s:textarea> 
										</div>
									</div>
									<div class="fields">
										<div class="controls">
											<span>:</span>
											<s:checkbox name="" key="" id="private"/>
										</div>
										<label><mmr:message messageId="label.mark.private"/></label>
										
									</div>
					</div>
	</div>
</s:if>
</div>
	<jsp:include page="add_info_shipping_inner.jsp"/>
   
</body>
</html>