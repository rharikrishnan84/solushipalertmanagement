<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
	<head>
		<title><s:text name="viewedit.invoice.title" /></title>
		<sj:head jqueryui="true" />
		<sx:head />
		<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
 $('#wrapper_new').css('min-height',wndo);
  });
  
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/jquery.alerts.css">
	<script>
	function confirm(id){
		var wait = false;
jConfirm('You want to delete the charge?', 'Yes', 'No', function(confirmationMessage) {
						if(confirmationMessage==true){							
							wait = true;	
							document.editform.action ="delete.actual.charge.editinvoice.action?method=deletetCharge&id="+id;
							document.editform.submit();				
						}
						
						});
	}
	</script>
	</head>
	<body>
		<SCRIPT language="JavaScript">
		var taxes = new Array();
				var count=0;
				function checkInvoice(index) {		
					var txt1 = document.getElementById("editTaxActualCharge"+index).checked;
				    if(txt1){
				    	 document.getElementById("editTaxActualCharges"+index).readOnly=false;
				         	taxes[count]=txt1;
					    	count++;
					    }else{
					    	document.getElementById("editTaxActualCharges"+index).readOnly=true;
					    }
					}
			function updateInvoice() {
				document.editform.action = "update.invoice.action";
				document.editform.submit();
			}
			function backInvoice() {
				document.editform.action = "invoice.action";
				document.editform.submit();
			}
		</SCRIPT>

		<div id="messages">
			<jsp:include page="../common/action_messages.jsp" />
		</div>

		<div class="form-container">
			<s:form id="editform" action="update.invoice.action" name="editform">
			
			

			<s:hidden name="invoiceId" value="%{invoice.invoiceId}" /> 
			<s:set name="invoiceId" value="%{invoice.invoiceId}" /> 
			<s:token/>
			<div class="content">
					<div class="content_body">
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.vieweditinvoice"/> :</div>
								<div class="cont_hdrtitle_l">
										<mmr:message messageId="label.heading.invoicedetails"/> #&nbsp;<s:property value="%{invoice.invoiceId}" />
								</div>
								<div class="form_buttons">
									<s:if test="%{#session.ROLE.contains('busadmin')}">
									<%-- <a href="invoice.action" style="cursor: pointer;" ><mmr:message messageId="label.navigation.back.results"/></a> --%>
									<a href="javascript:backInvoice()" ><mmr:message messageId="btn.back"/> </a>
									</s:if>
									<a href="javascript:updateInvoice()" ><mmr:message messageId="btn.update"/> </a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="content">
					<div class="content_body">
						<div class="content_table">
			<div class="cont_data_body">
			<div id="edit_inv_tbl_hdng">
				<table width="960px" cellpadding="3" cellspacing="0" >
					<thead>
						<tr style="font-size:14px; text-align:left; background-color:#d1d1d1;">
							<th ><mmr:message messageId="label.ghead.name"/> </th>
							<th ><mmr:message messageId="label.ghead.cost"/> </th>
							<th ><span style="width:230px; float:left;"><mmr:message messageId="label.ghead.charge"/></span> </th>
							<th ></th>
							<th></th>
						</tr>
				</thead>
			
			<s:iterator id="shipments" value="invoice.orders" status="row">
				
					<tbody>
					<s:if test="chargesForInvoice.size !=0">
						<div id="edit_inv_table">
						
							<tr style="font-size:12px;">
								<td class="editinv_title" ><mmr:message messageId="label.ghead.order"/> #:<s:property value="%{id}" /></td>
								<td class="editinv_title" ><mmr:message messageId="label.ghead.tracking"/> #:<s:property value="%{masterTrackingNum}" /></td>
								<td class="editinv_title" ><mmr:message messageId="label.ghead.company"/> :<s:property value="%{fromAddress.abbreviationName}" /></td>
								<td></td>
							</tr>				
						
						</div>
						
						</s:if>
								
						<div id="charges_div_table">
						<s:set var="index" value="0"/>
					<s:iterator id="actualCharges" value="chargesForInvoice" status="row">
					<tr>
						<td class="ordrdtl_title_val" width="40%">
							<s:textfield size="30" key="actualChargeName" name="actualChargeName"
																	value="%{name}" cssClass="text_02" />
						</td>
							<td class="ordrdtl_title_val" width="35%">
								<s:textfield size="8" key="actualChargeCost" name="actualChargeCost"
																value="%{cost}" cssClass="text_02" />
							</td>
							<s:if test="%{isTax}">
							  <td class="ordrdtl_title_val tax" width="20%">
								<s:hidden id="actualChargeIds" name="actualChargeIds" value="%{id}" /> 
								<s:hidden name="trackingNumbers" value="%{masterTrackingNum}" />
								<s:textfield size="8" key="actualCharge" name="actualCharge"
													value="%{charge}" cssClass="text_02" id="editTaxActualCharges%{#index}" readOnly="readonly" />
							</td>
							
							<%-- <td class="ordrdtl_title_val" width="35%">
							<s:a href="#"  onclick="return confirm('%{id}')"  cssStyle="padding:3px 10px; position:relative; right:121px; background-color:#990000; color:#FFF; text-decoration:none; font-size:12px; font-weight:bold;">
							<mmr:message messageId="btn.delete"/> </s:a>
							</td> --%>
							</s:if>
							<s:else>
							<td  class="ordrdtl_title_val" style="float:left; width:120px;" >
							<s:hidden id="actualChargeIds" name="actualChargeIds" value="%{id}"/>
							 <s:hidden name="trackingNumbers" value="%{masterTrackingNum}" /> 
							 <s:textfield size="8" 	key="actualCharge" name="actualCharge"
								value="%{charge}" cssClass="text_02" id="editactualCharge[[%{#index}]" />
						</td>
						</s:else>
						<td class="ordrdtl_title_val" width="55%">
							<span style="width:120px; float:left;"><s:a href="#"  onclick="return confirm('%{id}')"  cssStyle="padding:3px 10px; position:relative; right:121px; float:left; width:auto !important; background-color:#990000; color:#FFF; text-decoration:none; font-size:12px; font-weight:bold;">
							<mmr:message messageId="btn.delete"/> </s:a></span>
						</td>
     					<td>
     					<s:hidden name="tax_ver" value="%{isTax}"/>
						 <s:if test="%{isTax}">					
				     	  <div class="fields" style="width:130px!important;text-align:center;"> 
						  <label style="width:80px !important">
						  <mmr:message messageId="label.navigation.caltax" />:</label>
						  
						  <div class="controls" style="width:20px !important"> 													
					    	<s:checkbox style="width:22px !important" name="caltax" cssClass="check_uncheck_row"
						   id="editTaxActualCharge%{#index}" onclick="checkInvoice('%{#index}')" />
						</div> 
						 </div> 
						  </s:if>  
						   </td>
						</tr>
						<s:set var="index" value="#index+1"/>
						</s:iterator>
						</div>						
					</tbody>
				
			</s:iterator>
			</table>
			</div>
			</div>
			</div>
			</div>
			</div>
			</s:form>
		</div>
	</body>
</html>

