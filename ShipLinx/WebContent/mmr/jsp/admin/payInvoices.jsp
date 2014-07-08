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
<SCRIPT language="JavaScript">
</SCRIPT>

<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
	<s:form action="invoice.pay" id="payInvoiceForm">
	<s:token/>
		<div class="content_body" >	
			<div class="content_table" > 
				<div class="content_header">
					<div class="cont_hdr_title"><mmr:message messageId="menu.admin.payInvoices" /></div>
					<div class="form_buttons" >	
						<a href="<%=request.getContextPath()%>/mmr/jsp/shipping/view_shipment.jsp"><mmr:message messageId="label.pay.now"/></a>								
					</div>
				</div>		
				<div class="cont_data_body">
					<div class="rows">
						<div class="fields_topdown">
							<label><mmr:message messageId="label.creditcard.number" /></label>
							<div class="controls">
								<s:textfield size="24" key="creditCard.ccNumber" name="creditCard.ccNumber" cssClass="text_02_tf"/>
							</div>
						</div>
						<div class="fields_topdown">
							<label><mmr:message messageId="label.creditcard.expiryMonth" /></label>
							<div class="controls">
								<s:select 
						headerKey="-1" headerValue="Select Month" required="true"
						list="#{'01':'Jan', '02':'Feb', '03':'Mar', '04':'Apr', '05':'May', '06':'Jun', '07':'Jul', '08':'Aug', '09':'Sep', '10':'Oct', '11':'Nov', '12':'Dec'}" 
						key="creditCard.ccExpiryMonth"
						name="creditCard.ccExpiryMonth" cssClass="text_01_combo_medium"
						/>
							</div>
						</div>
						<div class="fields_topdown">
							<label><mmr:message messageId="label.creditcard.expiryYear" /></label>
							<div class="controls">
								<s:select 
						headerKey="-1" headerValue="Select Year"
						list="#{'2011':'2011', '2012':'2012', '2013':'2013', '2014':'2014', '2015':'2015', '2016':'2016', '2017':'2017', '2018':'2018'}" 
						key="creditCard.ccExpiryYear"
						name="creditCard.ccExpiryYear" cssClass="text_01_combo_medium"
					/>
							</div>
						</div>
						<div class="fields_topdown">
							<label><mmr:message messageId="label.creditcard.cvdCode" /></label>
							<div class="controls">
								<s:textfield size="5" key="creditCard.cvd" name="creditCard.cvd" cssClass="text_02_tf_small"/>
							</div>
						</div>
						<div class="fields_topdown">
							<label>&nbsp;</label>
							<div class="controls">
								<s:url id="payInvoice" value="invoice.pay.action"/>
									<sj:submit 
										id="formSubmit2"
										href="%{payInvoice}" 
										targets="formResult" 
										value="Pay Now" 
										indicator="indicator"
										button="true"
										/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



<div id="creditcard_pnl">

</div>


	<div id="formResult">
	   <s:include value="invoicePayList.jsp"></s:include>
	   <div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
	</div>
</s:form>
</div>

