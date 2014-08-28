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
  $('#sample1').dataTable(); 
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
	 			var hidden_id = "searchform_selectedShipments_"+i+"__id";
	 			var oid = document.getElementById(hidden_id).value;
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
      statusid = document.getElementById("status").value;
      id=uploadMarkupId[i].value;
      
      
     }
     
     
     
    }
    if((userrole == "busadmin")||(statusid==60)||(statusid==80)||(userrole == "solutions_manager")){
      
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

	<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
  <s:hidden value="busadmin" id="role" />
 </s:if>
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
	
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;margin-top:1px">
	<div id="srchusr_res"><span>List of Shipments </span></div>
	<div id="rates_result_tbl">
		<div id="srchshipmnt_result_tbl_print_label">
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
		<div id="rate_results" >
			<div class="form_buttons">
			<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
				<li><s:a href="Javascript: repeatOrder();">Repeat</s:a></li>
				<li><s:a href="javascript: editorder();">EDIT</s:a></li>
				<li><a href="javascript: atleastOneShipmentChecked('check_uncheck_row');"><mmr:message messageId="label.print.label"/></a></li>
				</ul>
				
			</div>
		</div>
	</div>	
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
		<tr>
			<th><input id="check_all" type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('sales')||#session.ROLE.contains('solutions_manager')}"> 
			<th>Company</th>
			</s:if>
			<th><span style="width:60px !important; float:left;">Order #</span></th>
			<th><span style="width:80px !important; float:left;">Tracking #</span></th>
			<th><span style="width:70px !important; float:left;">Ship Date</span></th>
			<s:if test="%{#request.fromCart != null && #request.fromCart != 'false'}">
			<s:if test="%{carrierName != null}">
			<th><span style="width:70px !important; float:left;">Carrier</span></th>
			</s:if>
			<s:else>
				<th><span style="width:70px !important; float:left;">Carrier</span></th>
			</s:else>
			</s:if>
			<s:else>
			<th><span style="width:70px !important; float:left;">Carrier</span></th>
				</s:else>
			<th><span style="width:80px !important; float:left;">Service</span></th>
			<th ><span style="width:50px !important; float:left; text-align:right;" title="Quoted/Billed Weight">Q/B</span></th>
			<th ><span style="width:50px !important; float:left; text-align:left;" title="Quoted/Billed Weight">Weight</span></th>
			<th><span style="width:100px !important; float:left;">Quoted Charge</span></th>
			<th ><span style="width:100px !important; float:left;">Billed Charge</span></th>
			<th><span style="width:100px !important; float:left;">From Address</span></th>
			<th><span style="width:100px !important; float:left;">To Address</span></th>
			<s:if test="%{statusName == 'Sent to Warehouse' && #session.ROLE.contains('busadmin')||statusName == 'Sent to Warehouse' && #session.ROLE.contains('solutions_manager')}">
			<th><span style="width:100px !important; float:left;">Status</span></th>
			</s:if>
			<s:else>
			<th><span style="width:100px !important; float:left;">Status</span></th>
			</s:else>
			<th><span style="width:100px !important; float:left;">Billing Status</span></th>
			
		</tr>
	</thead>
	<tbody>
	<s:set var="index" value="0" />
		 <s:iterator id="shipmentsTable" value="shipments" status="rowstatus">
             <tr>
				<td>
				<s:hidden name="selectedShipments[%{#index}].id" value="%{id}"/>
					<s:checkbox name="select[%{index}]" fieldValue="%{id}" value="select[%{#index}]" cssClass="check_uncheck_row" name="check_uncheck" />
					<input type="hidden" type="checkbox" id="status" value="<s:property value="statusId"/>" />
					<input type="hidden" type="checkbox" id="customsinvoice${id}" value="<s:property value="customsInvoice.id"/>" />
    <input type="hidden" type="checkbox" id="id_order" value="<s:property value="id"/>" />
				
				</td>
				<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('sales')||#session.ROLE.contains('solutions_manager')}">
					<td title="<s:property value="customer.name"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customer.name"/></div></td>
				
				</s:if>
				<td>
				<s:a href="view.shipment.action?viewShipmentId=%{id}"> 
				<s:property value="id"/>
				</s:a>
				</td>
				
				<td style="width:100px !important;height:30px;overflow:hidden;text-overflow: ellipsis">
				<s:if test="%{trackingURL!=null}">
					<a href="javascript:track('<s:property value="id"/>')"><s:property value="masterTrackingNum"/> </a>
				</s:if>
				<s:else>
					<s:a href="view.shipment.action?notrackurl='true'&viewShipmentId=%{id}"> 
					<s:property value="masterTrackingNum"/>
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
				<td title="<s:property value="service.masterCarrier.name"/>"><div style="width:70px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="service.masterCarrier.name"/></div></td>
				
				</s:else>
				<td title="<s:property value="service.name"/>"><div style="width:80px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="service.name"/></div></td>
				
				<td style="width:70px; text-align:right;"><s:property value="quotedWeight" /></td>
				<td style="width:70px; text-align:right;"><s:property value="billedWeight" />
				</td>
				<td style="width:70px">
					$<s:property value="%{shipments[#index].quoteTotalCharge}"/>
					
					<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
					 /$<s:property value="%{shipments[#index].quoteTotalCost}"/>
					</s:if>
					</td>
					<td>
					$<s:property value="%{shipments[#index].actualTotalCharge}"/>
					<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
					/ $
					<s:property value="%{shipments[#index].actualTotalCost}"/>
					</s:if>
					</td>
				<td title="<s:property value="fromAddress.longAddress"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="fromAddress.longAddress" /></div></td>
				
				<td title="<s:property value="toAddress.longAddress"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="toAddress.longAddress"/></div></td>
				
				
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
	Export to: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="shipment.download.action?type=csv"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
 	<a href="shipment.download.action?type=xl"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
 	<a href="shipment.download.action?type=xml"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
 	<s:if test="%{#request.shippingOrder.carrierId == 80}" >
 	&nbsp;<a href="javascript:midlandEOD();"><span class="exportpdf">&nbsp;&nbsp;&nbsp;&nbsp; Midland EOD </span>&nbsp;&nbsp;|</a>
 	</s:if>
 	<s:if test="%{#request.shippingOrder.carrierId != 80 && #request.shippingOrder.carrierId!=null && !(#session.ROLE.contains('busadmin'))||#request.shippingOrder.carrierId != 80 && #request.shippingOrder.carrierId!=null && !(#session.ROLE.contains('solutions_manager'))}" >
 	&nbsp;<a href="javascript:manifestEOD();"><span class="exportpdf">&nbsp;&nbsp;&nbsp;&nbsp; EOD </span>&nbsp;&nbsp;|</a>
 	</s:if>
</div>

</div>
</div>
