<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

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
	
	
		
	<script type="text/javascript">
 $(document).ready(function() {
  $('#sample1').dataTable({
		  aoColumnDefs: [
		                   {
		                      bSortable: false,
		                      "bSearchable": false,
		                      aTargets: [ 0 ]
		                   }
		                 ]
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
<SCRIPT language="JavaScript">
// Didn't work
	function selectAll(box, classStyle) {
	    var checked = box.checked;
	    document.getElementsByClassName(classStyle).each( alert('checkbox') );
	    document.getElementsByClassName(classStyle).each( function(checkbox){
	            checkbox.checked = checked;
	        });
	}
	
	function atleastOneShipmentChecked(cname)
	{
		//alert(document.searchform.autoprint.value);
		
		var count =0;
		var arrOrders = new Array();
		var aa = getElementsByClassName(cname);
		for(var i=0; i < aa.length; i++)
	    {
	 		if(aa[i].checked == true)
	 		{
	 			/* var hidden_id = "searchform_selectedShipments_"+i+"__id";
	 			var oid = document.getElementById(hidden_id).value; */
	 			var oid=aa[i].value;
	 			arrOrders[i] = oid;
	      		count ++;
	      	}
	    }
	    if(count==0)
	    {
	    	alert("Please select atleast one Shipment item.");
	    }
	    else
	    {
			//Call the action for Printing Label for the selected Shipments.    
	    	if(confirm("Would you like to print label for selected Shipments?"))
	    	{
	    		var slcopies = document.getElementById("label_copies").value;
	    		var ccopies = document.getElementById("customsinv_copies").value;
	    		var url="getShippingLabel.action?slcopies="+slcopies+"&cicopies="+ccopies+"&arrayOrders="+arrOrders;
	    		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
		 	}
	    }
	}
	
	function updateShipment(orderid)
	{
		if(confirm('Acknowledge the Order?'))
		{
			var classnm = "ajax_status_"+orderid;
		
			ajax_Service=ajaxFunction();
			ajax_Service.onreadystatechange=function()
			  {
				   if(ajax_Service.readyState==4)
					{
					reponse=ajax_Service.responseText;
					js_stateid=getElementsByClassName(classnm);
					js_stateid[0].innerHTML= reponse;
					}
			  }
	 		url="updateShipmentStatus.action?order_id="+orderid;
		  	ajax_Service.open("GET",url,true);
		  	ajax_Service.send(this);
		  }
	}
	
	function editorder(){
  
  
		var userrole = document.getElementById("role").value;
  
   var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
   
   var i1,txt1 = 0;
     for (i1=0;i1<uploadMarkupId.length;i1++){
   if (uploadMarkupId[i1].checked){
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
    var i,statusid,id;
    for (i=0;i<uploadMarkupId.length;i++){
     if(uploadMarkupId[i].checked){
    	 statusid = document.getElementById("selectedShipments["+i+"].statusId").value;
      id=uploadMarkupId[i].value;
     }
    }
   
    if(!(userrole == "customer_admin")){
    if((userrole == "busadmin")||(statusid==60)||(statusid==80)||(userrole == "solutions_manager")){
      
      window.location.href="process.shipment.action?order_id="+id;
     }
    }else if((userrole == "customer_admin") && (statusid==80)){
    	 window.location.href="process.shipment.action?order_id="+id;
    }
   else{
      alert("you dont have permission to edit");
     }
  }
  }
</SCRIPT>
<script>
function midlandEOD(){
	var fromDate=document.getElementById("f_date_c").value;
	var toDate=	document.getElementById("t_date_c").value;
	var carrierId = document.getElementById("firstBox").value;
	window.location.href="list.shipment.action?shippingOrder.toDate="+toDate+"&shippingOrder.fromDate="+fromDate+"&d-16544-e=5&shippingOrder.carrierId="+carrierId;	
}

function manifestEOD(){
		var fromDate=document.getElementById("f_date_c").value;
		var toDate=	document.getElementById("t_date_c").value;
		var carrierId = document.getElementById("firstBox").value;
		window.location.href="list.shipment.action?shippingOrder.toDate="+toDate+"&shippingOrder.fromDate="+fromDate+"&d-16544-e=5&shippingOrder.carrierId="+carrierId;	
	}
	
	
function download_files(type) {
	  var orderIdList = document.getElementsByClassName("check_uncheck_row");
	  var i, txt = 0;
	  for (i = 0; i < orderIdList.length; i++) {
	   if (orderIdList[i].checked) {
	    txt += 1;
	   }
	  }
	  if (txt < 1) {
	   alert('Please select at least one');
	  }
	  else if(txt>50){
		  alert('Please select atmost 50');
	  }
	  else {
	   var i1, shipmentid, value_checked, stored_value = "";
	   for (i1 = 0; i1 < orderIdList.length; i1++) {
	    if (orderIdList[i1].checked) {
	     shipmentid = orderIdList[i1].value;
		 //orderId = document.getElementById("searchform_selectedShipments_"+shipmentid+"__id").value;
	     //value_checked = document.getElementById("shipmentcheckbox"+shipmentid).value;
	     //confirm("this is value is value checked variable "+value_checked+"  this is value is from shipmentid "+shipmentid);
	     //stored_value = stored_value + value_checked + ",";
	     stored_value=stored_value+shipmentid+",";
	    }
	   }
	   window.location.href = "shipment.download.action?type="+type+"&orderList="+ stored_value;

	  }
	 }
	
function loadCachedPage(selPage){
	document.searchform.action = "list.shipment.action?cacheId="+selPage.value;
	document.searchform.submit();
}

function loadTrackingURL(url){
	window.open(url,"_blank","directories=no, status=no,width=1400, height=870,top=0,left=0");
}
</script>	
<style>
	.width150{ width:150px !important; }
</style>


<!--	<div id="srchshipmnt_results">	
	<s:if test="%{shipments.size()>1}">
	<div id="rslt_stmnt"><br/><s:property value="shipments.size()" /><mmr:message messageId="label.search.results.items"/></div>
	</s:if>
	<s:elseif test="%{shipments.size()==1}">
	<div id="rslt_stmnt"><br/><s:property value="shipments.size()" /><mmr:message messageId="label.search.results.item"/></div>
	</s:elseif>
	<s:else>
	<div id="rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/></div>
	</s:else>
	</div>	-->

	<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
  <s:hidden value="busadmin" id="role" />
 </s:if>
 <s:elseif test="%{#session.ROLE.contains('customer_admin')}">
  <s:hidden value="customer_admin" id="role" />
 </s:elseif>
 <s:else>
  <s:hidden value="" id="role" />
 </s:else>




	<s:set var="sortable">
		<s:if test="%{#request.fromCart != null && #request.fromCart == 'false'}">
			true
		</s:if>
		<s:else>
			false
		</s:else>
	</s:set>
	<style>
			#srchshipmnt_result_tbl_print_label{margin-left: 0px; float: right; width:auto; margin-right:10px;}
		.form_buttons{ float:right;}
		
	</style>
	
	
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;margin-top:1px">
	<div id="srchusr_res"><span><mmr:message messageId="label.track.header.listshipment"/></span></div>
	<div id="rates_result_tbl" >
		<div id="rate_results">
			<div class="form_buttons">
			<a href="#" id="actiondown" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
				<li><s:a href="Javascript: repeatOrder();"><mmr:message messageId="label.list.repeat"/></s:a></li>
				<li><s:a href="javascript: editorder();"><mmr:message messageId="label.list.edit"/></s:a></li>
				<li><a href="javascript: atleastOneShipmentChecked('check_uncheck_row');"><mmr:message messageId="label.print.label"/></a></li>
				</ul>
				
			</div>
			
			
			
			<div id="srchshipmnt_result_tbl_print_label">
			<s:if test="%{#request.current_page != null}">
				<font class="srchshipmnt_text_03"> <mmr:message messageId="label.display.records"/></font>&nbsp;
				<s:select id="cached_pages" cssClass="text_01_combo_small" list="cachedList" listKey="key" listValue="value" value="%{#request.current_page}" onchange="loadCachedPage(this)"/>
			</s:if>
			<font class="srchshipmnt_text_03"><mmr:message messageId="label.copies.shipping.label"/></font>&nbsp;
			<s:if test="%{#request.no_of_lbls != null}">
				<s:select id="label_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{#request.no_of_lbls}"/>
			</s:if>
			<s:else>
				<s:select id="label_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="1"/>
			</s:else>
				&nbsp;<font class="srchshipmnt_text_03"><mmr:message messageId="label.copies.customsinvoice"/></font>&nbsp;
			<s:if test="%{#request.no_of_ci != null}">
				<s:select id="customsinv_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{#request.no_of_ci}"/>
			</s:if>
			<s:else>
				<s:select id="customsinv_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="3"/>
			</s:else>
			
		</div>
		
		</div>
	</div>	
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
		<tr>
			<th><input id="check_all" type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('sales')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">  
			<th><span style="width:90px !important; float:left;"><mmr:message messageId="label.ghead.company"/></th>
			</s:if>
			<th><span style="width:80px !important; float:left;"><mmr:message messageId="label.ghead.order"/> #</span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.ghead.tracking"/> #</span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.ghead.shipdate"/></span></th>
			<s:if test="%{#request.fromCart != null && #request.fromCart != 'false'}">
			<s:if test="%{carrierName != null}">
			<th><span style="width:70px !important; float:left;"><mmr:message messageId="label.ghead.carrier"/></span></th>
			</s:if>
			<s:else>
				<th><span style="width:70px !important; float:left;"><mmr:message messageId="label.ghead.carrier"/></span></th>
			</s:else>
			</s:if>
			<s:else>
			<th><span style="width:70px !important; float:left;"><mmr:message messageId="label.ghead.carrier"/></span></th>
				</s:else>
			<th><span style="width:80px !important; float:left;"><mmr:message messageId="label.ghead.service"/></span></th>
			<th ><span style="width:100px !important; float:left; " title="Quoted/Billed Weight"><mmr:message messageId="label.ghead.qb"/>/<mmr:message messageId="label.ghead.weight"/></span></th>
			<th><span style="width:120px !important; float:left;"><mmr:message messageId="label.ghead.quotedcharge"/></span></th>
			<th ><span style="width:120px !important; float:left;"><mmr:message messageId="label.ghead.billedcharge"/></span></th>
			<th><span style="width:120px !important; float:left;"><mmr:message messageId="label.ghead.fromaddress"/></span></th>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.ghead.toaddress"/></span></th>
			<s:if test="%{statusName == 'Sent to Warehouse' && #session.ROLE.contains('busadmin')||statusName == 'Sent to Warehouse' && #session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
			<th><span style="width:70px !important; float:left;"><mmr:message messageId="label.ghead.status"/></span></th>
			</s:if>
			<s:else>
			<th><span style="width:70px !important; float:left;"><mmr:message messageId="label.ghead.status"/></span></th>
			</s:else>
			<th><span style="width:100px !important; float:left;"><mmr:message messageId="label.ghead.billingstatus"/></span></th>
			
		</tr>
	</thead>
	<tbody>
	<s:set var="index" value="0" />
		 <s:iterator id="shipmentsTable" value="shipments" status="rowstatus">
             <tr>
				<td>
				<s:hidden name="selectedShipments[%{#index}].id" value="%{id}"/>
					<s:checkbox name="select[%{index}]" fieldValue="%{id}" value="select[%{#index}]" cssClass="check_uncheck_row" />
					  <s:hidden name="selectedShipments[%{#index}].statusId" id="selectedShipments[%{#index}].statusId" value="%{statusId}"/>
					<input type="hidden" type="checkbox" id="customsinvoice${id}" value="<s:property value="customsInvoiceId"/>" />
    <input type="hidden" type="checkbox" id="id_order" value="<s:property value="id"/>" />
				
				</td>
				<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('sales')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
					<td title="<s:property value="customerName"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customerName"/></div></td>
					
				</s:if>
				<td>
				<s:a href="view.shipment.action?viewShipmentId=%{id}"> 
				<s:property value="id"/>
				</s:a>
				</td>
				
				<td style="width:100px !important;height:30px;overflow:hidden;text-overflow: ellipsis">
				<s:if test="%{trackingURL!=null && trackingURL.length()!=0 }">
					<a href="javascript:loadTrackingURL('<s:property value="trackingURL"/>')"><s:property value="masterTrackingNum"/> </a>
				</s:if>
				<s:elseif test="%{masterTrackingNum ==id}">
				    <s:a href="view.shipment.action?notrackurl='true'&viewShipmentId=%{id}&ltLState='true'"> 
				<s:if test="%{masterTrackingNum!=null}">
								<s:property value="masterTrackingNum"/>
							</s:if>
					</s:a>
				</s:elseif>
				<s:else>
					<s:a href="view.shipment.action?notrackurl='true'&viewShipmentId=%{id}"> 
					<s:if test="%{masterTrackingNum!=null}">
							<s:if test="%{masterTrackingNum.length()!=0}">
								<s:property value="masterTrackingNum"/>
							</s:if>
							<%-- <s:else>
								<s:property value="id"/>
							</s:else> --%>
						</s:if>
						<%-- <s:else>
							<s:property value="id"/>
						</s:else> --%>
					</s:a>
				</s:else>
				</td>
				
				<td title="<s:date name="scheduledShipDate" format="dd/MM/yyyy" />"><s:date name="scheduledShipDate" format="dd/MM/yyyy" /></td>
					
				
				<s:if test="%{#request.fromCart != null && #request.fromCart != 'false'}">
				<s:if test="%{carrierName != null}">
				<td title="<s:property value="carrierName"/>"><div style="width:70px !IMPORTANT;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="carrierName"/></div></td>
				
				</s:if>
				<s:else>
				<td title="<s:property value="service.masterCarrier.name"/>"><div style="width:70px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="service.masterCarrier.name"/></div></td>
					
				</s:else>
				</s:if>
				<s:else>
				<td title="<s:property value="masterCarrierName"/>"><div style="width:70px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="masterCarrierName"/></div></td>
				
				</s:else>
				<td title="<s:property value="serviceName"/>"><div style="width:80px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="serviceName"/></div></td>
				
				<td style="width:100px; "><s:property value="quotedWeight" />/<s:property value="billedWeight" /></td>
				<td style="width:70px">
				<s:hidden name="mohan" value="%{shipments[#index].quoteTotalCharge}"/>
				<s:hidden name="mohan1" value="%{shipments[#index].quoteTotalCost}"/>
					<s:if test="%{shipments[#index].quoteTotalCharge !=null}">
						<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/><s:text name="format.customMoney" ><s:param value="%{shipments[#index].quoteTotalCharge}"/></s:text>
					</s:if>
					<s:else>
					<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>0.00
					</s:else>
					<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
					<s:if test="%{shipments[#index].quoteTotalCost !=null}">
					 /<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/><s:text name="format.customMoney" ><s:param value="%{shipments[#index].quoteTotalCost}"/></s:text>
					</s:if>
					<s:else>
					 /<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>0.00
					</s:else>
					</s:if>
					</td>
					<td>
					<s:if test="%{shipments[#index].actualTotalCharge !=null}">
					<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/><s:text name="format.customMoney" ><s:param value="%{shipments[#index].actualTotalCharge}"/></s:text>
					</s:if>
					<s:else>
					<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>0.00
					</s:else>
					<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager') ||  #session.ROLE.contains('sysadmin')}">
					<s:if test="%{shipments[#index].actualTotalCost !=null}">
				/ <s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>
					<s:text name="format.customMoney" ><s:param value="%{shipments[#index].actualTotalCost}"/></s:text>
					</s:if>
					<s:else>
					/ <s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>0.00
					</s:else>
					</s:if>
					</td>
				<%-- <td title="<s:property value="fromAddress.longAddress"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="fromAddress.longAddress" /></div></td>
				
				<td title="<s:property value="toAddress.longAddress"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="toAddress.longAddress"/></div></td> --%>
				<td title="<s:property value="fromAddressLong"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="fromAddressLong" /></div></td>
				
				<td title="<s:property value="toAddressLong"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="toAddressLong"/></div></td>
				
				<s:if test="%{statusName == 'Sent to Warehouse' && #session.ROLE.contains('busadmin')||statusName == 'Sent to Warehouse' && #session.ROLE.contains('solutions_manager')}">
					<td title="<s:property value="statusName"/><img src="<s:url value="/mmr/images/stamp_check.png" includeContext="true" />" alt="Update" border="0" onclick="javascript:updateShipment('<s:property value="id"/>');" style="cursor: pointer;">"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="statusName"/><img src="<s:url value="/mmr/images/stamp_check.png" includeContext="true" />" alt="Update" border="0" onclick="javascript:updateShipment('<s:property value="id"/>');" style="cursor: pointer;"></div></td>
					
					</td>
				</s:if>
				<s:else>
				<td title="<s:property value="statusName"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="statusName"/></div></td>
					
				</s:else>
				<td title="<s:property value="billingStatusText"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="billingStatusText"/></div></td>
				
								
			 <s:set var="index" value="#index+1" />	
            </tr>			
            </s:iterator>
	</tbody>
</table>	

<div class="exportlinks" style="float:left; width:100%; height:30px;font-size:12px; text-align:right;"> 
	<mmr:message messageId="label.bottom.exportto"/>: &nbsp;&nbsp;&nbsp;&nbsp;<span class="arrowPackage">|</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href=" javascript: download_files('csv');" class="arrowPackage"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
 	<a href="javascript: download_files('xl');" class="arrowPackage"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
 	<a href="javascript: download_files('xml');" class="arrowPackage"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
 	<s:if test="%{#request.shippingOrder.carrierId == 80}" >
 	&nbsp;<a href="javascript:midlandEOD();" class="arrowPackage"><span class="exportpdf">&nbsp;&nbsp;&nbsp;&nbsp; Midland EOD </span>&nbsp;&nbsp;|</a>
 	</s:if>
 	<s:if test="%{#request.shippingOrder.carrierId != 80 && #request.shippingOrder.carrierId!=null && !(#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin'))||#request.shippingOrder.carrierId != 80 && #request.shippingOrder.carrierId!=null && !(#session.ROLE.contains('solutions_manager'))}" >
 	&nbsp;<a href="javascript:manifestEOD();" class="arrowPackage"><span class="exportpdf">&nbsp;&nbsp;&nbsp;&nbsp; EOD </span>&nbsp;&nbsp;|</a>
 	</s:if>
</div>

</div>
</div>
