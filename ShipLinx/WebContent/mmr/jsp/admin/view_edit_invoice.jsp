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
	</head>
	<body>
		<SCRIPT language="JavaScript">
			function updateInvoice() {
				document.editform.action = "update.invoice.action";
				document.editform.submit();
			}
		</SCRIPT>

		<div id="messages">
			<jsp:include page="../common/action_messages.jsp" />
		</div>

		<div class="form-container">
			<s:form id="editform" action="update.invoice.action" name="editform">
			<s:token/>
			<div id="div_bck_link">
					<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />"
						border="0" style="margin-bottom: -3px;">&nbsp;
					
					<s:if test="%{#session.ROLE.contains('busadmin')}"> <!-- Condition to display the Back button -->
						<img src="<s:url value="/mmr/images/back.png" includeContext="true" />"	border="0">&nbsp;					
						<a href="invoice.action" style="cursor: pointer;">
							<mmr:message messageId="label.navigation.back.results"/>
						</a>
						&nbsp;&nbsp;
						<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />"
							border="0" style="margin-bottom: -3px;">
					</s:if>
					
					<s:a href="javascript:updateInvoice()"
					cssStyle="text-decoration: none;">
					<img src="<s:url value="/mmr/images/edit.png" includeContext="true" />"
						border="0" style="margin-bottom: -3px;">&nbsp;&nbsp;Update
					</s:a>
									
					
				</div>
			

			<s:hidden name="invoiceId" value="%{invoice.invoiceId}" /> 
			<s:set name="invoiceId" value="%{invoice.invoiceId}" /> 
			<div id="edit_inv_heading">&nbsp;&nbsp;View/Edit Invoice:
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<font color="#000066" size="3" style="font: Arial; font-variant: small-caps">
					Invoice Details for #&nbsp;<s:property value="%{invoice.invoiceId}" />
				</font>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				
			</div>
			<div id="edit_inv_tbl_hdng">
				<table width="940px" cellpadding="3" cellspacing="0" style="margin-left: 10px;" >
					<tr>
						<td class="ordrdtl_title_hdng" colspan="2" style="text-align: center; background-color: #ffffff;"><strong>NAME</strong></td>
						<td class="ordrdtl_title_hdng" colspan="1" style="text-align: center; background-color: #ffffff;"><strong>COST</strong></td>
						<td class="ordrdtl_title_hdng" colspan="1" style="text-align: center; background-color: #ffffff;"><strong>CHARGE</strong></td>
					</tr>
				</table>
			</div>
			<s:iterator id="shipments" value="invoice.orders" status="row">
				<div id="edit_inv_table">
					<table cellpadding="3" cellspacing="0">
						<tr>
							<td class="editinv_title" width="8%">Order #:</td>
							<td class="editinv_title_val" width="30%"><s:property value="%{id}" /></td>
							<td class="editinv_title" width="1%">&nbsp;</td>
							<td class="editinv_title" width="8%">Tracking #:</td>
							<td class="editinv_title_val" width="20%"><s:property value="%{masterTrackingNum}" /></td>
							<td class="editinv_title" width="1%">&nbsp;</td>
							<td class="editinv_title" width="8%">Company:</td>
							<td class="editinv_title_val" width="30%"><s:property value="%{fromAddress.abbreviationName}" /></td>
						</tr>						
					</table>
				</div>
				<div id="charges_div_table">
					<table width="940px" cellpadding="3" cellspacing="0" style="margin-left: 20px;">
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
								<s:hidden name="actualChargeIds" value="%{id}" /> 
								<s:hidden name="trackingNumbers" value="%{masterTrackingNum}" />
								<s:textfield size="8" key="actualCharge" name="actualCharge"
													value="%{charge}" cssClass="text_02" />
							</td>
						</tr>
						</s:iterator>						
					</table>
				</div>
				<div id="edit_inv_bottom">&nbsp;</div>
			</s:iterator>
			</s:form>
		</div>
	</body>
</html>

