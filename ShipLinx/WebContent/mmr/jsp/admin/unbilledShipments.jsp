<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.meritconinc.shiplinx.utils.ShiplinxConstants"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:100px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
 </div>
	<!--<style type="text/css">
		#srchusr_result_tbl table {
			margin-left: 0;
			padding-top: 2;
		}
		#srchusr_result_tbl{
			z-index: 2;
		}
		#srchusr_results {
		height:45px;}
		#checkboxDatatableBodyId{
			margin-left: -4;
		}
		
		#check_all{
			margin: 7px 10px;
		}
		.dataTables_filter input[type="text"]{
		margin-right:0px;
		
		}
		.dataTables_filter{
			padding: 3px 0px 3px 0px;
		}
		.dataTables_wrapper{ overflow:hidden;}
		#sample1_length{
		position: relative;
		top: 2;
		}
		
	</style>-->
	
	
	
 
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

<script type="text/javascript">
		
		function submitform()
		{
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
			   else{
				 var i1,shipmentid,value_checked,stored_value="";
			 for (i1=0;i1<uploadMarkupId.length;i1++){
		   if (uploadMarkupId[i1].checked){
			shipmentid = uploadMarkupId[i1].value ;
			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
			stored_value = stored_value  + value_checked+ "," ;
			
		}
		}
			 $('#loader').css('display','block');
			 $('#loaderImg').css('display','block');
		document.generateInvoiceForm.action = "invoice.create.action?InvoiceIdList="+stored_value;
		 document.generateInvoiceForm.submit();
		}
}
		
		function autoGen()
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
		if(confirm("Would you like to run the auto-gen process?")) {
			var i1,shipmentid,value_checked,stored_value="";
			 for (i1=0;i1<uploadMarkupId.length;i1++){
		   if (uploadMarkupId[i1].checked){
			shipmentid = uploadMarkupId[i1].value ;
			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
			stored_value = stored_value  + value_checked+ "," ;
		     }
			 }
			 document.generateInvoiceForm.action = "invoice.autogen.action?InvoiceIdList1="+stored_value;
			// document.generateInvoiceForm.action = "invoice.autogen.action";
			 document.generateInvoiceForm.submit();
			 $('#loader').css('display','block');
			 $('#loaderImg').css('display','block');
		}
		}
		}
		
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
 
<div class="content">
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
		<div id="srchinv_results">	
		<div id="srchusr_res"><span><mmr:message messageId="label.generateInvoice.shipments"/>  </span></div>
		<div class="form_buttons">
        <a href="#" id="actiondown" ><mmr:message messageId="label.action"/> <span style="font-size:14px;">&#9660;</span></a>
		<a href="#" id="actionup" ><mmr:message messageId="label.action"/> <span style="font-size:14px;">&#9650;</span></a>
			
			<ul id="actionmenu">
				<li><a href="javascript:autoGen()"><mmr:message messageId="label.invoice.auto.generate"/></a></li>
				<li><a href="javascript:submitform()"><mmr:message messageId="label.invoice.bill.selected"/></a></li>
			</ul>
		</div>
		</div>
<s:set var="checkAll">
    <input type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" />
</s:set>
<div id="unbilled_shpmnts_result_tbl">
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
		<thead>
		<tr height="25px">
		<th style="width: 30px !important;"> <input type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
		<th style="width: 250px !important;"><mmr:message messageId="label.generateInvoice.company"/></strong></th>
		<th style="width: 120px ;"><mmr:message messageId="label.generateInvoice.order"/></th>
		<th style="width: 120px ;"><mmr:message messageId="label.generateInvoice.datecreated"/></th>
		<th style="width: 120px ;"><mmr:message messageId="label.generateInvoice.costcharge"/></th>
		<th style="width: 60px !important;"><mmr:message messageId="label.generateInvoice.billed"/></th>
		</tr>
		</thead>
		<tbody>
			<s:set var="index" value="0" />
            <s:iterator id="ordersTable" value="orders" status="rowstatus">
             <tr>
				<td> <input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='id'/>"  value="<s:property value='id'/>" /> </td>
				<td style="text-align: left;"<span title="<s:property value="customerName"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customerName"/></div></td>
				<td><s:property value="id"/></td>
				<td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
				<td><s:property value="%{totalToCost}"/> / <s:property value="%{totalToCharge}"/></td>
				<td><s:text name="format.money" ><s:param name="value" value="%{previouslyBilled}" /></s:text></td>
				
            </tr>			
			<s:set var="index" value="#index+1" />	
            </s:iterator>

</tbody>
</table>
 </div>
</div>
</div> 
</div>
<div id="unbilled_shpmnts_res_tbl_end"></div>


