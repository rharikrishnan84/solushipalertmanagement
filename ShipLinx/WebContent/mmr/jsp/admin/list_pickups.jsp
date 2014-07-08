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
	
	$(window).load(function() {
	
	$('#sample1').dataTable();
	
	$("#check_all").click(function(){
				var temp=$(this).attr("checked");
				if(temp){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
});
  

	</script>
	<script type="text/javascript">
	
	$(window).load(function() {

	$('#sample1').dataTable(); 
	
   
   /*var height = $('.grid_table_body').height(); height = 350+height; if(wndo > height){ var doct = -41;$('.footer').css({position:'absolute',bottom:'0px'});}else{$('.footer').css({float:'left'});}*/
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

	

	<s:set var="sortable">
		<s:if test="%{#request.fromCart != null && #request.fromCart == 'false'}">
			true
		</s:if>
		<s:else>
			false
		</s:else>
	</s:set>
	<div class="content">

	<div class="content_table" style="background-color:#fff;">
<div id="srchusr_res"><span>List of Pickups </span></div>
<div id="rate_results">
<div class="form_buttons">
<a href="javascript: cancelPickup();">CANCEL</a>
</div>
	</div>
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
		<tr style="height:25px">
			<th style="width:30px; text-align:center;"><input id="check_all" onclick="checkUncheck('check_uncheck_row')" type="checkbox" /></th>
			<th>Customer</th>
			<th>Pickup Date</th>
			<th>Pickup Time</th>
			<th>Carrier</th>
			<th>Confirmation No</th>
			<th>Pickup Address</th>
			
			<th>Status</th>
		
		</tr>
	</thead>
	<tbody>
		 <s:iterator id="pickupsTable" value="pickups" status="rowstatus">
             <tr>
				<td style="width:30px; text-align:center;"><s:checkbox cssClass="dataTable-checkbox" type="checkbox" name="check_uncheck"   fieldValue="%{pickupId}"/>
				<s:hidden name="status%{pickupId}" value="%{status}" />
				</td>
				
				<td title="<s:property value="customer.name"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customer.name"/></div></td>
				<td title="<s:date name="pickupDate"/>"><s:date name="pickupDate" format="dd/MM/yyyy" /></td>
				<td>
					<s:property value="readyHour"/>:<s:property value="readyMin"/>&nbsp;-&nbsp;<s:property value="closeHour"/>:<s:property value="closeMin"/>
				</td>
				<td title="<s:property value="carrier.name"/>"><div style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="carrier.name"/></div></td>
				
				<td><s:property value="confirmationNum"/></td>
				<td title="<s:property value="address.longAddress"/>"><div style="width:250px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="address.longAddress"/></div></td>
				
				<td>
				<s:if test="%{status=='10'}">
						<mmr:message messageId="pickup.status.active"/>
				</s:if>	
				<s:elseif test="%{status=='40'}">
						<mmr:message messageId="status.cancelled"/>
				</s:elseif>
				</td>
							
			
            </tr>			
            </s:iterator>
	</tbody>
</table>	


		<div class="content_table" >
		&nbsp;
		</div>

	


</div>

<div id="srchinv_res_tbl_end"></div>
</div>
