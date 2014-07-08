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
	<script type="text/javascript">
		function editSearchUser(){
			var editUserId = document.getElementsByName("searchUserCheckBox");
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
			document.getElementById("searchUserFormId").action = "edit.user.action?method=edit&name="+txt;
			document.getElementById("searchUserFormId").submit();
			}
		}
	</script>
		<script type="text/javascript">
		function deleteSearchUser(){
			var deleteUserId = document.getElementsByName("searchUserCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteUserId.length;i1++){
			if (deleteUserId[i1].checked){
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
				msg.innerHTML = "Do you really want to delete the selected user";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','30px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','30px');
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.getElementById("searchUserFormId").action = "delete.user.action?name="+txt;
					document.getElementById("searchUserFormId").submit();
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

	</head> 
<body>

<div id="messages">
	<div class="message_inner">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>	
</div>
<div class="content">
<div class="content_body">
<div class="content_table" style="background-color:#fff">
<div class="form-container">
<s:form id="searchUserFormId" cssClass="form" >
	<div id="srchusr_results">	
		<div id="srchusr_res"><span>User List </span></div>
		<div class="form_buttons" style="float:right;">
		<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
			<li><a href="javascript: editSearchUser();" >EDIT</a></li>
			<!--<s:submit value="DELETE" onclick="deleteSearchUser();" /> -->
			<li><a href="#" onclick="deleteSearchUser();"> DELETE </a></li>
			<li><a href="adduser.action">ADD USER </a></li>
			</ul>
		</div>	
	</div>
<div id="srchusr_result_tbl" >


<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left; width:100%; height:auto;">
    <thead>
		<tr>
			<th><input id="check_all" type="checkbox" /></th>
			<th></th>
			<th><span style="width:250px !important; float:left;">USER NAME</span></th>
			<th><span style="width:250px !important; float:left;">EMAIL</span></th>
			<th><span style="width:110px !important; float:left;">DATE CREATED</span></th>
			<th><span style="width:80px !important; float:left;">ENABLED</span></th>
			<th style="width:70px !important;"><span style="width:70px !important; float:left;">USER ROLE</span></th>
		</tr>
	</thead>
	<tbody>
            <s:iterator id="usertable" value="userList" status="rowstatus">
             <tr>
            <s:if test="#rowstatus.even1 == true">
	         <td class="odd1" width="2%">
			 <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/> 
	             </td>
				 <td width="2%">
		            <s:a href="logInAs.action?username=%{username}"> 
					<img src="<s:url value="/mmr/images/red_arrow.gif" includeContext="true" />" alt="Log In As" title="Log In As" border="0">
					</s:a>
	   			</td>
			   <td><span title="<s:property value="username"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="username"/></div></td><td ><s:property value="email"/></td>
				<td><span title="<s:property value="email"/>"></span><div style="width:200px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="email"/></div></td>
	            <td><s:date name="createdAt" format="dd/MM/yyyy" /></td>
	            <td><s:property value="enabled"/></td>
				<td><span title="<s:property value="userRole"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="userRole"/></div></td></s:if>
			<s:else>
		        <td class="odd1" width="2%">
		        <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/>  
	   			</td>
				 <td width="2%">
		            <s:a href="logInAs.action?username=%{username}"> 
					<img src="<s:url value="/mmr/images/red_arrow.gif" includeContext="true" />" alt="Log In As" title="Log In As" border="0">
					</s:a>
	   			</td>
				<td style="text-align: left;"<span title="<s:property value="username"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="username"/></div></td>
	            <td style="text-align: left;" <span title="<s:property value="email"/>"></span><div style="width:200px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="email"/></div></td>
	            <td style="text-align: left;"><s:date name="createdAt" format="dd/MM/yyyy" /></td>
	            <td style="text-align: left;"><s:property value="enabled"/></td>
				<td style="text-align: left;" <span title="<s:property value="userRole"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="userRole"/></div></td>
			</s:else>
            </tr>			
            </s:iterator>
</tbody>
</table>

</s:form>
</div>

</div>
</div>
</div>

<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</div>

</body>
</html>