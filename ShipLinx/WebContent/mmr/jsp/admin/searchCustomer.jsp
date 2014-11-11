<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<!-- Start: Code to handle Export Data -->
<%@ page buffer = "16kb" %>
<!-- End: Code to handle Export Data -->


<mx:Application xmlns:mx="http://www.adobe.com/2006/mxml">
<html> 
<head> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<style type="text/css">
		
	</style>
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function submitform()
	{
	 document.searchform.action = "searchcustomer.action";
	 document.searchform.submit();
	}
	
	function getAccountInformation(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
	}
	function resetform() {
		document.searchform.action = "searchcustomer.action?method=reset";
	 	document.searchform.submit();
	}	
	
	function addNewSearch()
	{
		document.searchform.action = "admin/addcustomer.action?";
	 	document.searchform.submit();
	}
	
</SCRIPT>
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
		<script type="text/javascript">
		function editCustomer(){
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
			var i,txt,id,serviceId;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;					
				}
			}
			
			document.getElementById("searchUserFormId").action ="getcustomerinfo.action?method=update&id="+id;
			document.getElementById("searchUserFormId").submit();
		}
		}
	</script>
	<script type="text/javascript">
		function markupCustomer(){
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
			var i,txt,id,serviceId,customerBusName;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;	
				customerBusName= document.getElementsByName("customerBusName"+serviceId)[0].value;				
				}
			}
		
			document.getElementById("searchUserFormId").action ="searchMarkup.action?method=init&customerId="+id+"&customerBusName="+customerBusName;
			document.getElementById("searchUserFormId").submit();
		}
			}
	</script>
	<script type="text/javascript">
		function userCustomer(){
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
			var i,txt,id,serviceId;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;					
				}
			}
		
			document.getElementById("searchUserFormId").action ="searchuser.action?cid="+id;
			document.getElementById("searchUserFormId").submit();
		}
			}
	</script>
		<script type="text/javascript">
		function carrier(){
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
			var i,txt,id,serviceId;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;					
				}
			}
		
			document.getElementById("searchUserFormId").action ="getCustomerAccountInfo.action?&id="+id;
			document.getElementById("searchUserFormId").submit();
		}
			}
	</script>
	<script type="text/javascript">
	function emailNotify(){
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
		   var i,txt,id,serviceId;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;							
				}
			}
			
		if(confirm("Would you like to send account notification/message to customer?")) {
			 document.searchform.action = "send.customer.notification.action?&custId="+id;
			 document.searchform.submit();
		}
		}
	}
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
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>


	<script type="text/javascript">
		function salesUser(){
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
			var i,txt,id,serviceId;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
				serviceId = editUserId[i].value ;;
				id = document.getElementsByName("id"+serviceId)[i].value;					
				}
			}
			
			document.getElementById("searchUserFormId").action ="manage.sales.users.action?id="+id;
			document.getElementById("searchUserFormId").submit();
		}
		}
	</script>


<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="searchCustomer" name="searchform" id="searchUserFormId">
	<div class="content_body">
<div class="content_table" style="background-color:#fff">
	<div id="srchusr_results">	
	<div id="srchusr_res"><span><label><mmr:message messageId="label.customer.list"/></label></span></div>
		<div class="form_buttons" style="float:right;">
            <a href="#" id="actiondown" ><label><mmr:message messageId="label.action"/></label> <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><label><mmr:message messageId="label.action"/></label> <span style="font-size:14px;">&#9650;</span></a>			
			<ul id="actionmenu">
			<li><a href="javascript: markupCustomer();" ><label><mmr:message messageId="label.list.markup"/></label></a></li>
			<li><a href="#" onclick="editCustomer();"><label><mmr:message messageId="label.list.edit"/></label></a></li>
			<li><a href="#" onclick="userCustomer();"><label><mmr:message messageId="label.list.user"/></label></a></li>
			<li><a href="#" onclick="carrier();"><label><mmr:message messageId="label.list.carriersetup"/></label></a></li>
			<li><a href="#" onclick="emailNotify();"><label><mmr:message messageId="label.list.emailcustomer"/></label></a></li>
			<li><a href="#" onclick="salesUser();"><label><mmr:message messageId="label.list.salesuser"/></label></a></li>
			</ul>
		</div>	
	</div>
       <div id="result_tbl">	  
	  	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left; width:100%; height:auto;">
    <thead>
		<tr style="height:28px; ">
			<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" /></th>
			<th></th>
			<th style="text-align:left; padding-left:10px;"><label><mmr:message messageId="label.ghead.name"/></label></th>
			<th style="text-align:left;padding-left:10px;"><label style="width:90px; float:left;"><mmr:message messageId="label.ghead.account"/> #</label> </th>
			<th style="text-align:left;padding-left:10px;"><label><mmr:message messageId="label.ghead.address"/></label></th>
			<th style="text-align:left;padding-left:10px; width:80px !important;"><label style="width:80px !important; float:left;"><mmr:message messageId="label.ghead.phone"/> #</label> </th>
			<th style="text-align:left;padding-left:10px;"><label><mmr:message messageId="label.ghead.email"/></label></th>
			<th style="text-align:left;padding-left:10px;"><label style="width:65px; float:left;"><mmr:message messageId="label.ghead.status"/></label></th>
			
		</tr>
	</thead>
	<tbody>
            <s:iterator id="customer" value="customerList" status="rowstatus">
             <tr>
			<td class="odd1" width="2%" style="width:30px; text-align:center;">
		        <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/>  
				<input type="hidden" name="id<s:property value='serviceId'/>" value="<s:property value='id'/>" />
				<input type="hidden" name="customerBusName<s:property value='serviceId'/>" value="<s:property value='name'/>" />
				</td>
				<td> <a href="logInAs.action?id=${id}"> 
				<img src="<s:url value="/mmr/images/red_arrow.gif" includeContext="true" />" alt="Log In As" title="Log In As" border="0"></a></td>
				<td style="text-align: left;" <span title="<s:property value="name"/>"></span><div style="width:200px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="name"/></div></td>
				  <td><s:property value="accountNumber"/></td>
				  <td style="text-align: left;"<span title="<s:property value="address.longAddress2"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="address.longAddress2"/></div></td>
				  <td style="text-align: left;" <span title="<s:property value="name"/>"></span><div style="width:80px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="address.phoneNo"/></div></td>
				    
					 <td><s:property value="address.emailAddress"/></td>
					  <td><s:property value="active"/></td>
					 
            </tr>			
            </s:iterator>
</tbody>
</table>
<div class="exportlinks" style="float:left; width:100%; height:30px;font-size:12px; text-align:right;"> 
	<mmr:message messageId="label.bottom.exportto"/>: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="customer.download.action?type=csv"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
 <a href="customer.download.action?type=xl"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
 <a href="customer.download.action?type=xml"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
</div>

<div id="res_tbl_end"></div>
</s:form>
</div>
</div>
</div>
</div>

		


