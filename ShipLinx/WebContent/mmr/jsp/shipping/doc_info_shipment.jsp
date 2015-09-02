<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<!DOCTYPE html>
<html> 
<head>
<sj:head jqueryui="true" />
    <title><s:text name="user.form.title"/></title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<style type="text/css">
		
	</style>
	
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sample11').dataTable(); 
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
		} );
	</script>
	
	<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			/* $('table').wrap('<div class="grid_table_body"></div>'); */
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
			/* $('.grid_table_body').css('overflow-x','scroll'); */
			
			
			$('#actiondown').click(function(event){
				event.preventDefault();
				$('#actionup').css('display','block');
				$('#actiondown').css('display','none');
				$('#actionmenu').css('display','block');
			});
			$('#actionup').click(function(event){
				event.preventDefault();
				$('#actionup').css('display','none');
				$('#actiondown').css('display','block');
				$('#actionmenu').css('display','none');
			});
		 
		});
		
	
		function markaspublic(){
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
		 
			document.getElementById("uploadForm").action = "change.visbility.doc.action?visibility=public&documentId="+txt+"&orderId="+orderId;
			document.getElementById("uploadForm").submit();
			}
		}

		function markasprivate(){
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
			document.getElementById("uploadForm").action = "change.visbility.doc.action?visibility=private&documentId="+txt+"&orderId="+orderId;
			document.getElementById("uploadForm").submit();
			}
		}
		
		
	</script>
</head>
<body>


<div id="comment_table">

<div id="add_info_shipping_result_tbl">		



<div class="form-container">
<s:form id="docForm" cssClass="form" >

	<div id="srchusr_results">
		<div id="srchusr_res"><span><mmr:message messageId="label.document"/> </span></div>
		
		<div class="form_buttons" style="float:right;">
		<a href="#" id="actiondown" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
			 
		    <li><a href="#" onclick="markaspublic();"><mmr:message messageId="label.doc.mk.public"/></a></li>
		    <li><a href="#" onclick="markasprivate();"><mmr:message messageId="label.doc.mk.private"/></a></li>
		      <li><a href="#" onclick="editDoc();"><mmr:message messageId="label.list.edit"/></a></li>
			</ul>
		</div>	
	</div>
 
	<div class="grid_table_body" style="overflow-x: scroll;">
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample11" style="float:left; width:100%; height:auto;">
    <thead>
		<tr>
			<th><span style="width:10px !important; float:left;"><input id="check_all" type="checkbox" /></span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.feedback.name"/> </span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.ghead.description"/></span></th>
			<th><span style="width:150px !important; float:left;"><mmr:message messageId="label.doc.type"/> </span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.edi.filename"/> </span></th>
			<th><span style="width:110px !important; float:left;"><mmr:message messageId="label.visibility"/></span></th>
			<th><span style="width:110px !important; float:left;"><mmr:message messageId="label.ghead.delete"/> </span></th>
			<th><span style="width:110px !important; float:left;"><mmr:message messageId="label.btn.download"/> </span></th>
			<th><span style="width:150px !important; float:left;"><mmr:message messageId="label.ghead.datetime"/> </span></th>
			
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.uploadedBy"/> </span></th>
			 
		</tr>
	</thead>
	<tbody>
            <s:iterator id="comment" value="documentList" status="rowstatus">
             <tr class="odd">
	         <td class="odd1" width="2%">
			 <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="documentId"/>"/> 
	             </td>
				 
				   <td style="text-align: left;"><s:property value="name"/></td>
				   <td style="text-align: left;"><s:property value="description"/></td>
				   <td style="text-align: left;"><s:property value="docType"/></td>
				     <td style="text-align: left;"><s:property value="fileName"/></td>
				   <s:if test="%{publicEnable}">
				   <td style="text-align: left;"><mmr:message messageId="text.role.public"/></td>
				   </s:if>
				   <s:elseif test="%{privateEnable}">
				   <td style="text-align: left;"><mmr:message messageId="label.ghead.private"/></td>
				   </s:elseif>
				     <td style="text-align: left;">
		   
				<s:a onclick="return confirm('Do you really want to delete the selected Product?')" href="Javascript: deleteDoc(%{documentId});">
					<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Customer Account Info" border="0"> </s:a>
			</td>
			   <td style="text-align: left;"> 
		   
				<s:a href="download.doc.action?documentId=%{documentId}"> 
					<img src="<s:url value="/mmr/images/Downloads.png" includeContext="true" />" alt="Download Document" border="0"> </s:a>
			</td>
				<td><s:date name="uploadedTime"/></td>
				<td style="text-align: left;"><s:property value="uploadedBy"/></td>
				
            </tr>			
            </s:iterator>
</tbody>
</table>
</div>

 
 
</s:form>
</div>
</div>

</div>
 


		
 
</body>	
</html>

