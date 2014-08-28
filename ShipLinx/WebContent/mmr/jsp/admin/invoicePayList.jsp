<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
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
		$('#invoicesTable').dataTable(); 
	});
</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sample1').dataTable(); 
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
     alert('Please check atleast one checkbox');
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
  });
	</script>
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
		<div id="srchusr_results">	
			<div id="srchusr_res">
				<span>
					<s:if test="%{#request.postPayment == true}">
						<s:if test="%{transaction.status == 30}">
							<div>
								PROCESSED
							</div>
						</s:if>
					</s:if>
					<s:if test="%{#request.postPayment == false}">
						<div>
						Select invoices to pay
						</div>
					</s:if>	
					<s:else>
						<div>Payment Results</div>
					</s:else>
				</span>
			</div>
		</div>
		<div id="srchinvpaylist_tab"></div>
		<div id="srchinvpaylist_results" ></div>
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="invoicesTable" width="100%">
    <thead>
	<tr style="height:25px">

	<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" /></th>
	<th >Invoice #</th>
	<th>Company</th>
	<th>Date Created</th>
	<th>Amount</th>
	<th>Tax</th>
	<th>Total</th>
	<th>Paid Amount</th>
	<th>Balance Due</th>
			
		
		
		
	</tr>
	</thead>
	<tbody>
	<s:set var="index" value="0" />
	<s:iterator id="invoicesTable" value="invoices" status="rowstatus">	
	<tr>
	<s:if test="%{#request.postPayment == false}">
		<!-- <td>
		 <input  class="dataTable-checkbox" type="checkbox" name="select[%{#index}]" value="select[%{#index}]"/> 
		 <input type="hidden"  name="selectedInvoices[%{#index}].invoiceId" value="%{invoiceId}" />
		</td> -->
		<td> <input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='invoiceId'/>"  value="<s:property value='invoiceId'/>" /> </td>
	</s:if>
		<td><s:property value="invoiceNum" /></td>
		<td><s:property value="customer.name" /></td>
		<td><s:property value="dateCreated" /></td>
		<td>$<s:property value="invoiceAmount" /></td>
		<td>$<s:property value="invoiceTax" /></td>
		<td>$<s:property value="totalInvoiceCharge" /></td>
		<td>$<s:property value="paidAmount" /></td>
		<td>$<s:property value="balanceDue" /></td>
		
		<s:set var="index" value="#index+1" />
	</s:iterator>
	</tr>
	</tbody>
	</table>
</div>
</div>


