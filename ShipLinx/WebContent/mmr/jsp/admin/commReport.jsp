<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.meritconinc.mmr.utilities.security.UserUtil" %>
<%@ page import="com.meritconinc.mmr.model.security.User" %>
<%@ page import="com.meritconinc.shiplinx.model.Commission" %>
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
	 document.searchInvoiceForm.action = "commReport.action";
	 document.searchInvoiceForm.submit();
	 //return false;
	}
	function pdf(){
	var editUserId = document.getElementsByClassName("check_uncheck_row");
			
			var i1,txt1 = 0;
			var salesUser = document.getElementById("salesAgent").value;
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
			window.location.href="print.invoice.action?invoiceId="+invoiceId+"&salesUser="+salesUser;
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

					document.getElementById(oddPercId).value =0

				}

			}

		}

 }
 function updateInvoiceStatus()
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
		   else if(txt1>0){
			   			   			   var i1,shipmentid,value_checked,stored_value="";
	 var paymentStatus=document.getElementById("paymentStatus").value;
	 if(paymentStatus==50){
	 var salesRep = document.getElementById("salesAgent").value;
	 for (i1=0;i1<uploadMarkupId.length;i1++){
		 		 	 if (uploadMarkupId[i1].checked){
		 		 			shipmentid = uploadMarkupId[i1].value ;
		 		 			value_checked = document.getElementsByName("shipmentcheckbox"+shipmentid)[0].value;
		 		 			stored_value = stored_value  + value_checked+ "," ;
	 }
	 }
	 	 	 document.searchInvoiceForm.action = "commReport.action?salesRep="+salesRep+"&invoiceIdlist="+stored_value;
	 	 	 	document.searchInvoiceForm.submit();	
}
		   }
		   		   		   else{
		   		   				alert("you can't update report");
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
<s:form action="invoice.list" name="searchInvoiceForm" cssClass="form">

<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.report.commission"/></div>
								<div class="form_buttons" >	
									<a href="javascript: updateInvoiceStatus()">UPDATE STATUS</a>
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
									<s:if test="%{#session.ROLE.contains('busadmin')}">
									<div class="fields_topdown">
										<label><mmr:message messageId="label.salesAgent" /></label>
										<div class="controls">
											<s:select  listKey="username" listValue="fullName" 
							name="commission.salesUser" list="salesUsers" 
								id="salesAgent" theme="simple" />
										</div>
									</div>
									 </s:if>
									<s:else>
									<div class="fields_topdown">
										<label><mmr:message messageId="label.salesAgent" /></label>
										<div class="controls">
									<% String loginUser = UserUtil.getMmrUser().getUsername(); %>
										<s:set var="loginUser"><%=loginUser%></s:set>
										<s:hidden name="commission.salesUser" id="commission.salesUser" value="%{#loginUser}"/>
											<s:property  value="%{#session.username}"/>
										</div>
									</div>
										
									</s:else>
									<div class="fields_topdown">
										<label>Invoice Status</label>
										<div class="controls">
											<s:select list="#{'30':'Paid (by Customer)', '10':'Unpaid (by Customer)','50':'Paid to Rep'}" name="commission.repPaid"  
								 id="paymentStatus" value="statusId"/>								
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
	<div id="srchusr_res"><span>Commission Report</span></div>
		<div id="srchinv_results">	
			<div class="form_buttons">
				<a href="javascript:pdf()">PDF</a>
			</div>
		</div>
	<div id="srchinv_result_tbl" >
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
	<tr height="25px">
			<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" name="check_uncheck" onclick="checkUncheck('check_uncheck_row')" style="margin: 0 0 0 4px" /></th>
			<th>Inv#</th>
			<th style="width: 175px !important;">Company</strong></th>
			<th>Date Created</th>
			<th style="width: 175px !important;text-align:right;padding-right:10px">Commission</strong></th>
			<th style="text-align:right;padding-right:10px">Amount</th>
			 <s:if test="%{#session.ROLE.contains('busadmin')}">
			<th style="text-align:right;padding-right:20px"> Cost </th>
			</s:if>
			<th>Status</th>
			
	</tr>
	</thead>
	<tbody>
	
			<s:set var="index" value="0" />
				<s:set var="total" value="0" />
				<s:set var="totals" value="0" />
				<s:set var="totalAmt" value="0" />
				<s:set var="count" value="0" />
				<s:set var="totalSpd" value="0" />
				<s:set var="totalLtl" value="0" />
				<s:set var="totalChb" value="0" />
           <s:iterator id="invoicetable" value="commissions" status="rowstatus">
			<s:if test="commissionPayable!=0">
             <tr>
			 <td> <input type="checkbox"  Class="check_uncheck_row" name="shipmentcheckbox<s:property value='invoiceId'/>"  value="<s:property value='invoiceId'/>" /> </td>
			 <td><s:property value="invoiceNum"/></td>
				 	 <td style="text-align: left;" <span title="<s:property value="customerName"/>"></span><div style="width:200px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customerName"/></div></td>
	            <td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
				<!-- this is for test 
				this block of code is to suppress the commission if its values is 0.00
				-->
				
				
					<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="commissionPayable" /></s:text></td>
				
				<td style="text-align:right;">$<s:property value="invoiceTotal" />
				<s:if test="%{#session.ROLE.contains('busadmin')}">
				<td style="text-align:right;padding-right:20px">$<s:property value="costTotal" /></td>
				</s:if>
				</td>
				<td><s:property value="paymentStatus" /></td>
				
						</tr>	
				<s:set var="index" value="#index+1" />	
				  <s:set var="total" value="%{#total+commissionPayable}"/> 
				 <s:set var="totals" value="%{#totals+invoiceTotal}"/> 
				 <s:set var="totalAmt" value="%{#totalAmt+costTotal}"/> 
				 
				 <s:set var="totalLtl" value="%{#totalLtl+totalLTL}"/>
				 <s:set var="totalSpd" value="%{#totalSpd+totalSPD}"/>
				 <s:set var="totalChb" value="%{#totalChb+totalCHB}"/> 
				 
				 </s:if>
			</s:iterator>

</tbody>
			<tfoot>
    <tr>
      <td>Total</td>
      <td></td>
	  <td></td>
      <td></td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#total}" /></s:text></td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totals}" /></s:text></td>
     <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalAmt}" /></s:text></td>
    </tr>
     <tr>
    <td></td>
    <td>Total SPD</td>
      <td><s:text name="format.money" ><s:param name="value" value="%{#totalSpd}" /></s:text></td>
	  <td>Total LTL </td>
      <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalLtl}" /></s:text></td>
	  <td style="text-align:right;">Total CHB</td>
	  <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="%{#totalChb}" /></s:text></td>
     
    </tr>
  </tfoot>
</table>
</div>
<div class="exportlinks"> 
	Export to: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="comm.download.action?type=csv"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
 <a href="comm.download.action?type=xl"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
 <a href="comm.download.action?type=xml"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
</div>
<div id="srchinv_res_tbl_end_commission"></div>
</div>
</div>
</div>

