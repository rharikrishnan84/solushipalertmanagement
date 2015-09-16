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
     <script>
$(function() {
$('.dropdown-toggle').click(function(){
  $(this).next('.dropdown').toggle();
});

$(document).click(function(e) {
  var target = e.target;
  if (!$(target).is('.dropdown-toggle') && !$(target).parents().is('.dropdown-toggle')) {
    $('.dropdown').hide();
  }
});

});
</script> 
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
		function editMenu(){
			




			var editMarkupId = document.getElementsByName("searchMenu");
			var i1,txt1 = 0;
		   for (i1=0;i1<editMarkupId.length;i1++){
			if (editMarkupId[i1].checked){
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
		//	alert('Do you really want to apply the selected markup to all customers?');
				var i,txt;
				for (i=0;i<editMarkupId.length;i++){
					if (editMarkupId[i].checked){
						txt = editMarkupId[i].value ;					
					}
				}
				
				//document.getElementById("markupFormId").action ="applyToAllCustomersMarkup.action?method=applyToAll&amp;serviceId=2013&amp;fromCountryCode=ANY&amp;toCountryCode=ANY"+txt;
				//document.getElementById("markupFormId").submit();
				document.getElementById("searchUserFormId").action = "edit.menu.action?menuId="+txt;
				document.getElementById("searchUserFormId").submit();
			}
		}
	</script>
	<script type="text/javascript">
		function addStore(){
			document.getElementById("searchUserFormId").action = "newEcom.store.action";
			document.getElementById("searchUserFormId").submit();
			
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

<script type="text/javascript">
		function editStore(){
			var editBusinessId = document.getElementsByName("searchMenu");
			var i1,txt1 = 0;
		   for (i1=0;i1<editBusinessId.length;i1++){
			if (editBusinessId[i1].checked){
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
		//	alert('Do you really want to apply the selected markup to all customers?');
				var i,txt;
				for (i=0;i<editBusinessId.length;i++){
					if (editBusinessId[i].checked){
						txt = editBusinessId[i].value ;					
					}
				}
				document.getElementById("searchUserFormId").action = "editEcom.store.action?storeId="+txt;
				document.getElementById("searchUserFormId").submit();
			}
		}
	</script>
	
	<script type="text/javascript">
		function deleteStore(){
			var editBusinessId = document.getElementsByName("searchMenu");
			var i1,txt1 = 0;
		   for (i1=0;i1<editBusinessId.length;i1++){
			if (editBusinessId[i1].checked){
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
		//	alert('Do you really want to apply the selected markup to all customers?');
				var i,txt;
				for (i=0;i<editBusinessId.length;i++){
					if (editBusinessId[i].checked){
						txt = editBusinessId[i].value ;					
					}
				}
				document.getElementById("searchUserFormId").action = "deleteEcom.store.action?storeId="+txt;
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
	<div id="srchusr_res"><span> <mmr:message messageId="label.list.EcommerceStores"/></span></div>
		<%-- <div class="form_buttons" style="float:right;">
			<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
			<s:if test="%{#session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}">
						<li><a href="#" onclick="addStore();"> <mmr:message messageId="label.add.ecom.store"/></a></li>
						<li><a href="#" onclick="deleteStore();"><mmr:message messageId="label.list.delete"/></a></li>
			</s:if>
			<li><a href="#" onclick="editStore();"><mmr:message messageId="label.list.edit"/></a></li>
			</ul>
		</div> --%>	
		<div id="action_nav">
		<a class="dropdown-toggle" href="#"><label><mmr:message messageId="label.action"/></label><span style="font-size:14px; padding-left:5px;">&#9660;</span></a>
		<ul class="dropdown" >
		<s:if test="%{#session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}">
						<li><a href="#" onclick="addStore();"> <mmr:message messageId="label.add.ecom.store"/></a></li>
						<li><a href="#" onclick="deleteStore();"><mmr:message messageId="label.list.delete"/></a></li>
			</s:if>
			<li><a href="#" onclick="editStore();"><mmr:message messageId="label.list.edit"/></a></li>
		</ul>
 		</div>	
		
	</div>
       <div id="result_tbl">
       
       <table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
		<thead>
			<tr height="25px">
			<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" /></th>
 			<th style="width: 185px !important; text-align: left;"> <mmr:message messageId="label.ghead.name"/></th>
			<th style="width: 185px !important; text-align: left;"> <mmr:message messageId="label.ecom.store.url"/></th>
			<th style="width: 230px ; text-align: left;"> <mmr:message messageId="ecommerce.domain"/></th>
			
			</tr>
		</thead>
		<tbody>
			
          <s:iterator id="artable" value="ecommerceStoreList" status="rowstatus">
             <tr>
				   <td style="width:30px; text-align:center;">
		          <input  id="" class="dataTable-checkbox" type="checkbox" name="searchMenu" value="<s:property value="ecommerceStoreId"/>"/>  </td>
 
	           <td style="text-align: left;"><span title="<s:property value="ecomCustomer.name"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="ecomCustomer.name"/></div></td>
			  <td><s:property value="url"/></td>
			<td><s:property value="ecommerceDomain"/></td>
             </tr>			 

            </s:iterator>

</tbody>
</table>	  
	  	
</div>
</div>
<div id="res_tbl_end"></div>
</div>
</s:form>
</div>
 
		


