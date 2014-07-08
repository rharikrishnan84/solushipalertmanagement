<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html>
<html> 

    <title>Unlinked Shipments</title> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
</script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />	
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>	

	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
</script>

		<head> 
	   <sj:head jqueryui="true" />
	   <sx:head />
	   </head>
	   <style type="text/css">
#customerautocomplete,#auto{ background-position: 145px 4px; background-size:8px 8px; }

</style>
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
 
	<SCRIPT language="JavaScript">
		function assignCustomer()
		{
			var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
			var i,txt = 0;
			for (i=0;i<uploadMarkupId.length;i++){
				if (uploadMarkupId[i].checked){
					txt += 1;						
				}
			}
			if(txt < 1){
				alert('Please select at least one');
			}
			else{
				var i1,txt1 = 0,shipmentid,value_checked,stored_value="";
									 for (i1=0;i1<uploadMarkupId.length;i1++){
								   if (uploadMarkupId[i1].checked){
									shipmentid = uploadMarkupId[i1].value ;
									value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
									var customerId = document.getElementById("custId").value;
									stored_value = stored_value  + value_checked+ "," ;
									
								   }
								   }
													
									 if(customerId.length >0){
								           document.searchform.action = "assigncustomer.shipment.action?shipmentIdList="+stored_value +"&customerId="+customerId;
								      document.searchform.submit();
								       }
								       else{
								       
								      alert('Please Select The Customer');   
								  
								    }
								}
						}
		
		
		function saveShipmentList() {
			document.searchform.action = "save.shipment.action";
			document.searchform.submit();
		}	
		
		function getAccountInformation(url){
			window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
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
  $('#wrapper_new').css('float','left');
  });
	</script>
	
	<script type="text/javascript">
 
  $(document).ready(function() {
   
   $("#check_all").click(function(){
    var temp=$(this).attr("checked");
    if(temp){
       $(".dataTable-checkbox").attr("checked","checked");
    }
    else{
    $(".dataTable-checkbox").removeAttr("checked");
    }
   });
   
   /*$('.gridedit').click(function(){
    var check = $(".dataTable-checkbox:checked").length;
    if(check < 1){
     alert('Please select at least one checkbox');
     location.href="admin/search.products.action?method=new#";
    }
    
   });*/
   
   
   
   
  } );
 </script>
 
<body>
	<div id="messages">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>
	
	
<div class="content" >	
   <div class="content_body" > 
   <s:form id="searchform" action="list.shipment.action" name="searchform">
       <div class="content_table" > 
        <div class="content_header">
         <div class="cont_hdr_title"><mmr:message messageId="label.unlinked.shipments"/></div>
         
        </div>  
       <div class="cont_data_body">
         <div class="rows">
          <div class="fieldsl">
           <label><strong><mmr:message messageId="label.customer.name" /></strong></strong></label>
		  <s:url id="customerList" action="listCustomers" />
          <div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocomplete" />
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>
          </div>
         </div>
        </div>
       </div>
	</div>   


<div class="content_body"  > 	  
<div class="content_table" style="background-color:#FFF;" > 
	<div class="form-container">		

		<div id="srchusr_results">
		<div id="srchusr_res"><span>Unlinked Shipments</span></div>
		<div class="form_buttons">
		
          <a href="javascript: assignCustomer()" title="<mmr:message messageId="label.assign.customer"/>">ASSIGN</a>     
         </div>
		
		</div>
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1"style=" width:100%;" >
    <thead>
		<tr>
			<th style="width:30px; text-align:center;"><input type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<th>Order #</th>
			<th>Tracking #</th>
			<th>Ref #</th>
			<th ><span style="width:120px !important; float:left;">Date Created</span></th>
			<th>Service</th>
			<th>Addresses</th>
			<th><span style="width:120px !important; float:left;">Billing Status</span></th>
	</tr>
	</thead>
	<tbody>
			<s:set var="index" value="0" />
            <s:iterator id="shipmentsTable" value="shipments" status="rowstatus">
             <tr>
	           <td style="width:30px; text-align:center;"> 
					
				  	<input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='id'/>"  value="<s:property value='id'/>" />
					
			    </td>	
				<td class="tbl_order"><s:property value="id"/></td>
				 <td><s:property value="masterTrackingNum"/></td>
				  <td><s:property value="referenceOne"/></td>
	            <td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
				<td style="text-align: left;" <span title="<s:property value="service.name"/>"></span><div style="width:100px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="service.name"/></div></td>			 
				<td>
						 <sx:tree label="From : %{shipments[#index].fromAddress.abbreviationName}">
								<sx:treenode label="%{shipments[#index].fromAddress.address1} : %{shipments[#index].fromAddress.city}" />                       
						</sx:tree>
						<sx:tree label="To : %{shipments[#index].toAddress.abbreviationName}">
						 		<sx:treenode label="%{shipments[#index].toAddress.address1} : %{shipments[#index].toAddress.city}" />                       
						</sx:tree>
				</td>
						<td><s:property value="billingStatusText"/></td>			
						</tr>	
				<s:set var="index" value="#index+1" />
            </s:iterator> 
</tbody>
</table>
<div class="content_table" >
	&nbsp;
</div>
		
</div>
</div>
</div>
</div>
	</s:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

var customers = {
		<s:iterator value='#session.customersList'>
		"<s:property value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocomplete').newautocomplete({
       lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
           $('#custId').val(suggestion.data);
			changeCustomer();
			}
        }
   });
	
	function compare(a,b) {
	  if (a.value < b.value)
		 return -1;
	  if (a.value > b.value)
		return 1;
	  return 0;
	}
</script>
		

</body>
</html>













