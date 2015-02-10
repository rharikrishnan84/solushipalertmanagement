<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<html> 
<head> 
    <sj:head jqueryui="true" />
    <sx:head />
    <title><s:text name="edi.title"/></title> 
</head> 
<body> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
</script>	
	
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
		} );
	</script>
<SCRIPT language="JavaScript">
	function submitform()
	{	
	var inclCancelledInvoiceCheckBox=document.getElementById("incl_can_inv");
	var incl_can_inv;
	if(inclCancelledInvoiceCheckBox!= null){
		incl_can_inv=inclCancelledInvoiceCheckBox.checked;
	}
	 document.InvoiceBreakdownForm.action = "invoiceBreakdown.action?inclCancelledInv="+incl_can_inv;
	 document.InvoiceBreakdownForm.submit();
	 //return false;
	}
	function invoiceBreakDownFile(type){
				var inclCancelledInvoiceCheckBox=location.search.split('inclCancelledInv=')[1];
				window.location.href="invoiceBreakdown.download.action?type="+type+"&inclCancelldInv="+inclCancelledInvoiceCheckBox;
		}
	function pdf(){
	var editUserId = document.getElementsByClassName("check_uncheck_row");
			
			var i1,txt1 = 0;
			/* var salesUser = document.getElementById("salesAgent").value; */
		   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please select at least one');
		   }
		   else{
			var i,txt;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
					txt = editUserId[i].value ;	
				}
			}
			var invoiceId=txt;
			//document.getElementById("addresslist").action = "edit.address.action?addressid="+addressid;
			//document.getElementById("addresslist").submit();
			window.location.href="print.invoice.action?invoiceId="+invoiceId;
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
		
		function ontimevalidate(val,perc,rowType,index){

    if(val!=0){

          var evenPercId = "evenPercId"+index;

          var evenFlatId = "evenFlatId"+index;

         var oddPercId = "oddPercId"+index;

         var oddFlatId = "oddFlatId"+index;

		    if(rowType=="even"){

				var percentageEven=document.getElementById(evenPercId.toString()).value;

				var flatEven=document.getElementById(evenFlatId).value;

				if(perc=="perc" && flatEven!=0){

					alert("Either Percentage or Flat should have the value greater than 0.");

					document.getElementById(evenFlatId).value =0;

				}else if(percentageEven!=0){

					alert("Either Percentage or Flat should have the value greater than 0.");

					document.getElementById(evenPercId).value =0

				}

			}else{

				var percentageOdd=document.getElementById(oddPercId).value;

				var flatOdd=document.getElementById(oddFlatId).value;

				if(perc=="perc" && flatOdd!=0){

				alert("Either Percentage or Flat should have the value greater than 0.");

				document.getElementById(oddFlatId).value =0;

				}else if(percentageOdd!=0){

					alert("Either Percentage or Flat should have the value greater than 0.");

					document.getElementById(oddPercId).value =0;

				}

			}

		}

 }
	</script>
	
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div class="content">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="invoice.list" name="InvoiceBreakdownForm" cssClass="form">
<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.report.invoicebreakdown"/></div>
								<div class="form_buttons" >	
									<a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a>
								</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields_topdown">
										<label><mmr:message messageId="label.from.date" /></label>
										<div class="controls">
											<s:textfield name="commission.fromDate_web" id="f_date_c" onClick="selectDate('f_date_c','f_trigger_c');"  
											 readonly="readonly"/>
										</div>
									</div>
									<div class="fields_topdown">
										<label>To Date</label>
										<div class="controls">
											<s:textfield name="commission.toDate_web" id="t_date_c" onClick="selectDate('t_date_c','t_trigger_c');"  
											 readonly="readonly"/>
										</div>
									</div>
									<div class="fields_topdown">
					<label><mmr:message messageId="label.currency" /></label>
					<div class="controls">
						<s:select
		list="currencyList"
		name="commission.currency" listKey="currencyCode" listValue="currencyCode"
		cssClass="text_01_combo_medium" />
		
					</div>
				</div>
									<div class="fields_topdown" >
										<div class="controls"  style="width:180px ! important; margin-top: 18px;" >
											<input type="checkbox" name="commission.includeCancelledInv" id="incl_can_inv" style="width:14px ! important;margin:0px ! important;">
											<span style="padding: 3px 0px 0px 5px; float: left;"><mmr:message messageId="label.included.cancelledinvoice"/> </span><br>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
</s:form>
</div>




<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
	<div id="srchusr_res"><span><mmr:message messageId="label.invoice.breakdown"/></span></div>
		<div id="srchinv_results">	
			<div class="form_buttons">
				<a href="javascript:pdf()"><mmr:message messageId="label.pdf"/></a>
				<!-- <a href="#">PDF</a> --> 
			</div>
		</div>
	<div id="srchinv_result_tbl" >
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
	<tr height="25px">
			<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<th style="width: 90px !important;text-align:left;padding-right:10px"><mmr:message messageId="invoice.breakedown.inv"/>#</th>
			<th style="width: 175px !important;"><mmr:message messageId="invoice.breakedown.company"/></strong></th>
			<th style="width: 100px !important;text-align:left;padding-right:10px"><mmr:message messageId="invoice.breakedown.datecreated"/> </th>
			<th style="width: 100px !important;text-align:left;padding-right:10px"><mmr:message messageId="label.currency"/> </th>
			<th style="width: 70px !important;text-align:right;padding-right:10px"><mmr:message messageId="invoice.breakedown.Amount"/></th>
			<th style="width: 70px !important;text-align:right;padding-right:10px"><mmr:message messageId="invoice.breakedown.Tax"/></th>
			<th style="width: 70px !important;text-align:right;padding-right:10px"><mmr:message messageId="invoice.breakedown.SPD"/></th>
			<th style="width: 100px !important;text-align:right;padding-right:10px"><mmr:message messageId="invoice.breakedown.LTL"/></th>
			<th style="width: 100px !important;text-align:left;padding-right:10px;"><span style=" padding-left:70px;"><mmr:message messageId="invoice.breakedown.CHB"/></span></th>
 		    <th style="width: 100px !important;text-align:right;padding-right:10px"><span style=" padding-left:70px;"><mmr:message messageId="invoice.breakedown.FWD"/></span></th>
 		    <th style="width: 100px !important;text-align:right;padding-right:10px"><span style=" padding-left:70px;"><mmr:message messageId="invoice.breakedown.FPA"/></span></th>
 		
			<!-- <th style="width: 155px !important;text-align:center;padding-right:10px"></th> -->
	</tr>
	</thead>
	<tbody>
	
			<s:set var="index" value="0" />
				<s:set var="count" value="0" />
				<s:set var="totalSpd" value="0" />
				<s:set var="totalLtl" value="0" />
				<s:set var="totalChb" value="0" />
				<s:set var="totalfwd" value="0" />
				<s:set var="totalfpa" value="0" />
				<s:set var="totalinvoiceAmt" value="0" />
				<s:set var="totalInvoiceTax" value="0" />
            <s:iterator id="invoicetable" value="invoiceBreakdown" status="rowstatus">
             <tr>
			 	<td style="width:30px; text-align:center;"> <input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='invoiceId'/>"  value="<s:property value='invoiceId'/>" /> </td>
			 	<td><s:property value="invoiceNum"/></td>
			 	<td style="text-align:left;"><s:property value="customer.Name" />
	         	<td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
	         	<td><s:property value="currency"/></td>
		     	<%-- <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="totalSPD" /></s:text></td> --%>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{invoiceAmount}" /></s:text></td>
		        <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{invoiceTax}" /></s:text></td>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{totalSPD}" /></s:text></td>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{totalLTL}" /></s:text></td>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{totalCHB}" /></s:text></td>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{totalFWD}" /></s:text></td>
		     	<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{totalFPA}" /></s:text></td>
			 	<%-- <td style="text-align:right;padding-right:20px">$<s:property value="totalCHB" /></td> --%>
			 	
			</tr>	
				 <s:set var="index" value="#index+1" />	
				 <s:set var="totalLtl" value="%{#totalLtl+totalLTL}"/>
				 <s:set var="totalSpd" value="%{#totalSpd+totalSPD}"/>
				 <s:set var="totalChb" value="%{#totalChb+totalCHB}"/>  
				 <s:set var="totalfwd" value="%{#totalfwd+totalFWD}"/>
				 <s:set var="totalfpa" value="%{#totalfpa+totalFPA}"/>
				 <s:set var="totalinvoiceAmt" value="%{#totalinvoiceAmt+invoiceAmount}"/>  
				 <s:set var="totalInvoiceTax" value="%{#totalInvoiceTax+invoiceTax}"/>  
			</s:iterator>
</tbody>
  <tfoot>
    <tr>
      <td><mmr:message messageId="invoice.breakdown.total"/></td>	
      <td></td>
	  <td></td>
	  <td></td>
	  <td></td>
      <%-- <td><s:text name="format.money" ><s:param name="value" value="%{#totalSpd}" /></s:text></td> --%>
      <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalinvoiceAmt}" /></s:text></td>
      <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalInvoiceTax}" /></s:text></td>
      <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalSpd}" /></s:text></td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalLtl}" /></s:text></td>
	  <td style="text-align:right;"><span style=" padding-left:64px;"><s:text name="format.money" ><s:param name="value" value="%{#totalChb}" /></s:text></span></td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalfwd}" /></s:text></td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalfpa}" /></s:text></td>
   </tr>
  </tfoot>
</table>
</div>
<div class="exportlinks"> 
	<mmr:message messageId="label.export.to"/>: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:invoiceBreakDownFile('csv');"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; <mmr:message messageId="label.csv"/> </span>&nbsp;&nbsp;|</a>&nbsp;
 <a href="javascript:invoiceBreakDownFile('xl');"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; <mmr:message messageId="label.excel"/> </span>&nbsp;&nbsp; |</a>&nbsp;
 <a href="javascript:invoiceBreakDownFile('xml');"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; <mmr:message messageId="label.xml"/> </span>&nbsp;&nbsp;|</a>

</div>
<div id="srchinv_res_tbl_end_commission"></div>
</div>
</div>
</div>
