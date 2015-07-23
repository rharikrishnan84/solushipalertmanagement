<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--   <div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
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
		
	</style>
	-->
	
	
 
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
		
		$('.grid_table_body').css('overflow-x','scroll');
	} );
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
function submitform()
	{
	var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
	/*this code is to pass the value into transaction table */
		var modeOfPay=document.getElementsByClassName("text_01_combo_medium");
	 	var payRefNum=document.getElementsByClassName("text_02_tf_small1");
	 	var payAmount=document.getElementsByClassName("text_02_tf_small");
	 	var paymentDate=document.getElementsByClassName("text_02_tf");
	 	var modeBoolean=true;
	 	var paymentDateBoolean=true;
	 	var payRefNumBoolean=true;
	 	//alert("selected value "+modeOfPay+""+payRefNum);
			var i,txt = 0;
			for (i=0;i<uploadMarkupId.length;i++){
				if (uploadMarkupId[i].checked){
					if(paymentDate[i].value==""){
						paymentDateBoolean=false;
						alert("Please Enter Date Remitted");
						}		
						 if(modeOfPay[i].options[modeOfPay[i].selectedIndex].value==""){
							 modeBoolean=false;
							 alert("Please Enter Mode of Payment");
							 }
						 if(payRefNum[i].value==""){
							 payRefNumBoolean=false;
							 alert("Please Enter Payment Ref Number");
						 }
					txt += 1;						
				}
			}
			if(txt < 1){
				alert('Please select at least one');
			}
			else if(modeBoolean==true && paymentDateBoolean==true && payRefNumBoolean==true){
				//var i1,shipmentid,value_checked,stored_value="",modeofpay="",payrefnum="";
				var i1,shipmentid,value_checked,stored_value="",modeofpay="",payrefnum="",payamount="";
			 for (i1=0;i1<uploadMarkupId.length;i1++){
		   if (uploadMarkupId[i1].checked){
			shipmentid = uploadMarkupId[i1].value ;
			modeofpay=modeofpay + modeOfPay[i1].value+",";
						payrefnum= payrefnum+ payRefNum[i1].value+",";
			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
			stored_value = stored_value  + value_checked+ "," ;
			payamount=payamount+payAmount[i1].value+",";
			
		   }
		   }
			// document.updateARForm.action = "processAR.action?InvoiceIdList="+stored_value+"&modeOfPay="+modeofpay+"&payRefNum="+payrefnum;
			document.updateARForm.action = "processAR.action?InvoiceIdList="+stored_value+"&modeOfPay="+modeofpay+"&payRefNum="+payrefnum+"&payAmounts="+payamount;
		 document.updateARForm.submit();
	}
	}
	
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>




<div class="content">
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
		<div id="srchinv_results">	
		<div id="srchusr_res"><span><mmr:message messageId="label.updatear.unpaidlist" /></span></div>
		<div class="form_buttons" >	
			<a href="javascript:submitform()"><mmr:message messageId="menu.admin.processAR"/></a>
		</div>	
		</div>	
		
		
<div id="srchinv_result_tbl" >
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
	<tr height="25px">
			<th><input type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<th><mmr:message messageId="label.updatear.invoice" /> </th>
			<th style="width:82px;"><mmr:message messageId="label.updatear.company" /></th>
			<th><mmr:message messageId="label.updatear.amount" /> </th>
			<th><mmr:message messageId="label.updatear.tax" /></th>
			<th><mmr:message messageId="label.updatear.total" /></th>
			<th><span style="float:left;text-align:right;padding-right:5px;"><mmr:message messageId="label.updatear.paid" /></span></th>
			<th><span style="float:left;text-align:right;padding-right:5px;width:100px"><mmr:message messageId="label.updatear.balancedue" /></span></th>
			<th><span style="float:left;padding-right:5px"><mmr:message messageId="label.updatear.amount" /></span></th>
			<th><mmr:message messageId="label.updatear.dateremitted" /> </th>
			<th><mmr:message messageId="label.updatear.mode" />  </th>
			<th><span style="width:105px !important; float:left;"><mmr:message messageId="label.updatear.paymentref" /></span></th>
	</tr>
	</thead>
	<tbody>
			<s:set var="index" value="0" />
            <s:iterator id="arTable" value="invoices" status="rowstatus">
             <tr>
	           <td>  <input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='invoiceId'/>"  value="<s:property value='invoiceId'/>" /></td>
			    <td><s:property value="invoiceNum" /></td>
				 <td style="text-align:left;"><span title="<s:property value="customer.name"/>"></span><div style="width:80px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customer.name"/></div></td>
				 <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="invoiceAmount" /></s:text></td>
				 <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="invoiceTax" /></s:text></td>
				 <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="totalInvoiceCharge" /></s:text></td>
				 <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="paidAmount" /></s:text></td>
				 <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="balanceDue" /></s:text></td>
				<td><s:textfield name="selectedInvoices[%{#index}].arTransaction.amount" value="%{balanceDue}" size="6" cssClass="text_02_tf_small" cssStyle="text-align:right;" />
				</td>
				<td><s:textfield name="selectedInvoices[%{#index}].arTransaction.paymentDate_web" id="remit_date_c%{#index}" cssStyle="width: 75px;"
											cssClass="text_02_tf" readonly="readonly" onclick= "selectDate('remit_date_c%{#index}','remit_date_c%{#index}')"/></td>
				<td><s:select 
									headerKey="" headerValue="" required="true"
									list="#{'Cheque':'Cheque', 'Credit Card':'Credit Card', 'Cash':'Cash', 'Money Order':'Money Order'}" 
									name="selectedInvoices[%{#index}].arTransaction.modeOfPayment" cssClass="text_01_combo_medium" cssStyle="width: 100px; height: 22px;"
								/></td>
				<td><s:textfield name="selectedInvoices[%{#index}].arTransaction.paymentRefNum" value="" size="6" cssClass="text_02_tf_small1" /></td>

						</tr>	
							<s:set var="index" value="#index+1" />			
			</s:iterator>

</tbody>
</table>
</div>
</div>
</div>
</div>
<div id="srchinv_res_tbl_end"></div>



