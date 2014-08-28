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
								<div class="cont_hdr_title">View/Edit Invoice:</div>
								<div class="cont_hdrtitle_l">
										Invoice Details for #&nbsp;<s:property value="%{invoice.invoiceId}" />
								</div>
								<div class="form_buttons">
									<s:if test="%{#session.ROLE.contains('busadmin')}">
									<%-- <a href="invoice.action" style="cursor: pointer;" ><mmr:message messageId="label.navigation.back.results"/></a> --%>
									<a href="javascript:backInvoice()" >Back</a>
									</s:if>
									<a href="javascript:updateInvoice()" >Update</a>
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
							<th >NAME</th>
							<th >COST</th>
							<th >CHARGE</th>
							<th ></th>
						</tr>
				</thead>
			
			<s:iterator id="shipments" value="invoice.orders" status="row">
				
					<tbody>
					<s:if test="chargesForInvoice.size !=0">
						<div id="edit_inv_table">
						
							<tr style="font-size:12px;">
								<td class="editinv_title" >Order #:<s:property value="%{id}" /></td>
								<td class="editinv_title" >Tracking #:<s:property value="%{masterTrackingNum}" /></td>
								<td class="editinv_title" >Company:<s:property value="%{fromAddress.abbreviationName}" /></td>
								<td></td>
							</tr>				
						
						</div>
						
						</s:if>
								
						<div id="charges_div_table">
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
							<td class="ordrdtl_title_val" width="35%">
								<s:hidden id="actualChargeIds" name="actualChargeIds" value="%{id}" /> 
								<s:hidden name="trackingNumbers" value="%{masterTrackingNum}" />
								<s:textfield size="8" key="actualCharge" name="actualCharge"
													value="%{charge}" cssClass="text_02" />
							</td>
							
							<td class="ordrdtl_title_val" width="35%">
							<s:a href="#"  onclick="return confirm('%{id}')"  cssStyle="padding:3px 10px; position:relative; right:121px; background-color:#990000; color:#FFF; text-decoration:none; font-size:12px; font-weight:bold;">
							DELETE</s:a>
							</td>
						</tr>
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

