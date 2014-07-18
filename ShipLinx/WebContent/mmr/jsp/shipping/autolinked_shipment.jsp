<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE>
<html> 

    <title><s:text name="user.form.title"/></title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table2.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
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
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	<head> 
		<sx:head />
	   <sj:head jqueryui="true" />
	   </head>
<style type="text/css">
#customerautocomplete,#auto{ background-position: 285px 4px; background-size:8px 8px; }
</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sample1').dataTable({"iDisplayLength":-1});  
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-	").removeAttr("checked");
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
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
			
			
			$('.grid_table_body').css('overflow-x','scroll');
		});
		

	</script>
	<SCRIPT language="JavaScript">
		function reassignCustomer()
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
		   var i1,shipmentid,value_checked,stored_value="";
			 for (i1=0;i1<uploadMarkupId.length;i1++){
		   if (uploadMarkupId[i1].checked){
			shipmentid = uploadMarkupId[i1].value ;
			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
			var customerId = document.getElementById("custId").value;
			   stored_value = stored_value  + value_checked+ "," ;
			 
			     }
			     }
			   document.searchform.action = "reassigncustomer.shipment.action?shipmentIdList="+stored_value+"&customerId="+customerId;
			   document.searchform.submit();
		}
		}
		
		function acceptShipments1() {
		
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
		   var i1,shipmentid,value_checked,stored_value="";
			 for (i1=0;i1<uploadMarkupId.length;i1++){
		   if (uploadMarkupId[i1].checked){
			shipmentid = uploadMarkupId[i1].value ;
			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
			stored_value = stored_value  + value_checked+ "," ;
			
		   }
		   }
			document.searchform.action = "accept.shipment.action?shipmentIdList="+stored_value;
			document.searchform.submit();
		}	
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
 	<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  $('#wrapper_new').css('float','left');
  });
	</script>
<body>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

	
<div class="content">	
<div class="content_body" > 
  <s:form id="searchform" action="list.shipment.action" name="searchform">
       <div class="content_table"  > 
        <div class="content_header">
         <div class="cont_hdr_title"><mmr:message messageId="label.autolinked.shipments"/></div>
         
        </div>  
       <div class="cont_data_body">
         <div class="rows">
          <div class="fieldsl" style="width:525px !important;">
           <label><strong><mmr:message messageId="label.customer.name" /></strong></label>
		    <s:url id="customerList" action="listCustomers" />
            <div class="controls" style="width:325px !important;"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocomplete" style="width:300px !important"/>
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
		<div id="srchusr_res"><span>Auto Linked Shipments</span></div>
		<div class="form_buttons">
		<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
          <li><a href="javascript: reassignCustomer()" title="<mmr:message messageId="label.reassign.customer"/>" >RE-ASSIGN</a> </li>
          <li><a href="javascript: acceptShipments1()"title="<mmr:message messageId="label.accept.customer"/>" >ACCEPT</a></li>
</ul>		  
         </div>
		</div>			
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%" >
    <thead>
		<tr>
			<th style="width:30px; text-align:center;"><input type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<th>Company</strong></th>
			<th>Order #</th>
			<th>Tracking #</th>
			<th>Date Created</th>
			<th>Service</th>
			<th>Total</th>
			<th>Addresses</th>
			<th>Billing Status</th>
		</tr>
	</thead>
	<tbody>
		<s:set var="index" value="0" />
		<s:iterator id="shipmentsTable" value="shipments" status="rowstatus">
		 <tr>
			<td style="width:30px; text-align:center;">
			<input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='id'/>"  value="<s:property value='id'/>" />
			</td>
			<td style="text-align: left;" <span title="<s:property value="customer.name"/>"></span><div style="width:70px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customer.name"/></div></td>	
			<td ><s:property value="id"/></td>
			<td><s:property value="masterTrackingNum"/></td>
			<td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
			<td><s:property value="service.name"/></td>
			<td> <sx:tree  cssClass="text_01" label="<b>Total : %{shipments[#index].totalChargeActual} / %{shipments[#index].totalCostActual}</b>" >
	  
						<s:iterator  value="%{shipments[#index].actualCharges}">
						 <sx:treenode cssClass="text_01" label="%{name} : %{charge} / %{cost}" />
						</s:iterator>
					 </sx:tree></td>
			<td>
			<sx:tree label="From : %{shipments[#index].fromAddress.abbreviationName}">
					<sx:treenode label="%{shipments[#index].fromAddress.address1} : %{shipments[#index].fromAddress.city}" />                       
			</sx:tree>
			<sx:tree label="To : %{shipments[#index].toAddress.abbreviationName}">
					<sx:treenode label="%{shipments[#index].toAddress.address1} : %{shipments[#index].toAddress.city}" />                       
			</sx:tree></td>
			<td style="text-align: left;" <span title="<s:property value="billingStatusText"/>"></span><div style="width:100px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="billingStatusText"/></div></td>	
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






