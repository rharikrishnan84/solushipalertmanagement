<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<html> 
<head>
<sj:head jqueryui="true" />
    
    <title><s:text name="user.form.title"/></title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/shiplinx_closeWindow_styles_Chrome.css" />
<style type="text/css">
.top_line{ margin-top:15px;} 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$('#sample2').dataTable();
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
</script>
	<script>

		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample2_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
		});
		
	</script>
  </script>
		<script type="text/javascript">
		function editPackages(){
		
			var editUserId = document.getElementsByName("searchPackageCheckBox");
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
				
				document.getElementById("searchPackageFormId").action = "editpackage.action?pid="+txt;
				document.getElementById("searchPackageFormId").submit();
			}
		}
	</script>
		<script type="text/javascript">
		function deletePackages(){
		
			var deleteUserId = document.getElementsByName("searchPackageCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteUserId.length;i1++){
			if (deleteUserId[i1].checked){
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
				for (i=0;i<deleteUserId.length;i++){
					if (deleteUserId[i].checked){
						txt = deleteUserId[i].value ;					
					}
				}
				//createCustomConfirm;
				
				var ALERT_BUTTON_TEXT = 'Ok';
				var CANCEL_BUTTON_TEXT = 'Cancel';
				d = document;
				if(d.getElementById("modalContainer")) return;
				mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
				mObj.id = "modalContainer";
				mObj.style.height = d.documentElement.scrollHeight + "px";
				alertObj = mObj.appendChild(d.createElement("div"));
				alertObj.id = "alertBox";
				if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
				var leftMargin = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2;
				alertObj.style.left = (leftMargin-40) + "px";
				alertObj.style.visiblity="visible";
				/*h1 = alertObj.appendChild(d.createElement("h1"));
				h1.appendChild(d.createTextNode(ALERT_TITLE));*/
				msg = alertObj.appendChild(d.createElement("p"));
				//msg.appendChild(d.createTextNode(txt));
				msg.innerHTML = "Are you want to delete the Record";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.getElementById("searchPackageFormId").action = "delete.package.action?pid="+txt;
					document.getElementById("searchPackageFormId").submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}
		}
	</script>
	<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=31;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
	
	

	
	
 
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

<script type="text/javascript">
	
		$(document).ready(function() {
			$('#sample2').dataTable(); 
		} );
	</script>
</head> 
<body> 

<div id="messages">
	<div class="message_inner">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>	
</div>

<div class="content">
<div class="content_body" >
<div class="content_table" style="background-color:#fff;">
<div class="form-container" >
<s:form id="searchPackageFormId"> 


	
	
	<div id="srchusr_results"> 	
		<div id="srchusr_res"><span ><mmr:message messageId="label.heading.customerpackagelist" /></span></div>
		<div class="form_buttons" style="float:right;">
		<a href="#" id="actiondown" ><mmr:message messageId="label.action" /> <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action" /> <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
			<!--<input type=submit value="EDIT" onclick="editPackages();" />
			<input type=submit value="DELETE" onclick="deletePackages();" />-->
			<li><a href="#" onclick="editPackages()" ><mmr:message messageId="label.list.edit" /> </a></li>
			<li><a href="#" onclick="deletePackages()" ><mmr:message messageId="label.list.delete" /> </a></li>
			<li><a href="goToAddNewPackage.action" ><mmr:message messageId="menu.add.package" /></a></li>
			</ul>
			
		</div>
	</div>


<div id="srchusr_result_tbl" >
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample2" width="100%">
	<thead>
	<tr>
	<th ><input id="check_all" type="checkbox"/></th>
	
	<th style="width: 341px;!important"><mmr:message messageId="label.ghead.packagename" /> </th>
	
	<th style="width: 250px;!important" ><mmr:message messageId="label.ghead.packagedesc" /> </th>
	<%-- <th style="width: 100px;!important; text-align:right; padding-right:10px;"><mmr:message messageId="label.ghead.length" /> </th>
	<th style="width: 100px;!important; text-align:right; padding-right:10px;"><mmr:message messageId="label.ghead.width" /> </th>
	<th style="width: 100px;!important; text-align:right; padding-right:10px;"><mmr:message messageId="label.ghead.height" /> </th>
	
		<th style="width: 110px;!important; text-align:right; padding-right:10px;" ><mmr:message messageId="label.ghead.weight" /> (INT)</strong></th> --%>
	<th style="width: 100px;!important; text-align:left; padding-right:10px;"><mmr:message messageId="label.ghead.length" /> </th>
	<th style="width: 100px;!important; text-align:left; padding-right:10px;"><mmr:message messageId="label.ghead.width" /> </th>
	<th style="width: 100px;!important; text-align:left; padding-right:10px;"><mmr:message messageId="label.ghead.height" /> </th>
	<th style="width: 110px;!important; text-align:left; padding-right:10px;" ><mmr:message messageId="label.ghead.weight" /> (INT)</strong></th>
	
 	
	
	</tr>
	</thead>
	<tbody>
			
            <s:iterator id="usertable" value="packageTypesList" status="rowstatus">
            <tr>
            <s:if test="#rowstatus.even1 == true">
	        	<td class="even1" width="1%"><input class="dataTable-checkbox" type="checkbox" name="searchPackageCheckBox" value="<s:property value='packageTypeId'/>"/></td>
	            <td style="text-align: left;"><s:property value="packageName"/></td>
				<td style="text-align: left;" <span title="<s:property value="packageDesc"/>"></span><div style="width:250px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="packageDesc"/></div>></td>
	            <td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageLength"/></fmt:formatNumber>  </td>
            <td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageWidth"/></fmt:formatNumber></td>
				<td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageHeight"/></fmt:formatNumber></td>
				<td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageWeight"/></fmt:formatNumber></td>
 			
			</s:if>
			<s:else>
				 <td class="odd1" width="1%"><input class="dataTable-checkbox" type="checkbox" name="searchPackageCheckBox" value="<s:property value='packageTypeId'/>"/></td>
	            <td style="text-align: left;"><s:property value="packageName"/></td>
			 	<td style="text-align: left;"<span title="<s:property value="packageDesc"/>"></span><div style="width:350px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="packageDesc"/></div></td>
 	          	<td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageLength"/></fmt:formatNumber>  </td>
	            <td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageWidth"/></fmt:formatNumber></td>
				<td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageHeight"/></fmt:formatNumber></td>
				<td ><fmt:formatNumber pattern="###.##" minFractionDigits="2" maxFractionDigits="2"><s:property value="packageWeight"/></fmt:formatNumber></td>
 			
			
			</s:else>

            </tr>
            </s:iterator>

</tbody>
</table>
  
</s:form>
</div>
</div>
</div>
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</body>
</html>