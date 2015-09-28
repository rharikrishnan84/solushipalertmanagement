<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<html> 
<head> 
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
    <sj:head jqueryui="true" />
    <sx:head />
    <title><s:text name="edi.title"/></title> 
<style type="text/css">

#customersautocomplete,#auto{ background-position: 115px 4px; background-size:8px 8px;  }
</style> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
</head> 
<script language="JavaScript">


	function submitform()
	{	
	 document.getElementById("searchInvoice").action ="invoice.action?method=new";
		document.getElementById("searchInvoice").submit();
	 //return false;
	}
	
	function setInvoiceNum()
	{
		var inum_autocompleter = dojo.widget.byId("auto_invoice_num");
		var invnum_val = inum_autocompleter.getText();
		//alert(invnum_val);
		document.getElementById("invoice.invoiceNum").value = invnum_val;		
	}
	
</script>

<script>
 $(document).ready(function(){
  var wndo = window.innerHeight;
    wndo -=46;
    $('#wrapper_new').css('min-height',wndo);
 });
</script>
<style>
	input.dojoComboBox{ width:105px !important;}
</style>
<body>
<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
	</div>
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="invoice.list" name="searchInvoiceForm" id="searchInvoice">


<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.invoice.search"/></div>
								<%-- <s:if test="%{#session.ROLE.contains('customer_admin')}"> --%>
								<s:if test="%{#session.ROLE.contains('customer_admin') && #session.creditLimit > 0}">
										<div class="cont_hdrtitle_w" style="padding-left:330px;"><mmr:message messageId="label.customer.availableCredit"/>&nbsp;<s:property value="#session.currencyS"/><s:property value="#session.availableCredit"/></div>	
									</s:if>
								<div class="form_buttons" >		
									<a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a>
									<a href="invoice.action"><mmr:message messageId="label.btn.reset"/></a>
								</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields_topdown">
										<label><mmr:message messageId="label.from.date" /></label>
										<div class="controls">
										<s:textfield name="invoice.fromInvoiceDate_web" id="f_date_c" 
											cssClass="text_02_tf" readonly="readonly"   onClick="selectDate('f_date_c','f_trigger_c');"/>
										</div>
									</div>
									<div class="fields_topdown">
										<label><mmr:message messageId="label.to.date" /></label>
										<div class="controls">
											 <s:textfield name="invoice.toInvoiceDate_web" id="t_date_c" 
											cssClass="text_02_tf" readonly="readonly"  onClick="selectDate('t_date_c','t_trigger_c');"/>
										</div>
									</div>
									<%-- <s:if test="%{#session.ROLE.contains('busadmin')}"> --%>
									<s:if test="%{#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">
									<div class="fields_topdown">
										<label><mmr:message messageId="label.customer.name"/></label>
										<s:url id="customerList" action="listCustomers" />
										<div class="controls">
									
											<input id="custId" type="hidden" value="" name="invoice.customerId" >
					<input id="customersautocomplete" type="text"  value="" name="customerName" autocomplete="off" >
										</div>
									</div>
									</s:if>
									<div class="fields_topdown">
										<label><mmr:message messageId="label.invoice.number" /></label>
										<div class="controls">
										<s:textfield key="invoice.invoiceNum" name="invoice.invoiceNum" />
										</div>
									</div>	
									<div class="fields_topdown">
										<label><mmr:message messageId="label.invoice.paymentStatus"/></label>
										<div class="controls" style="width:130px; overflow:hidden;">
											<s:select cssClass="text_01_combo_big_customer" cssStyle="width:130px; background-position: 115px 4px; background-size:8px 8px;background-color: #FFFFFF;" listKey="id" listValue="statusName" name="invoice.paymentStatusList" headerKey="0" headerValue="" list="statusList" id="secondBox"  theme="simple"  />
										</div>
									</div>
									<div class="fields_topdown" style=" margin-left:5px;">
									<label><mmr:message messageId="label.display.cancelled"/></label>
									<div class="controls">
										<s:checkbox name="invoice.cancelled" id="cancelled" cssStyle="width:15px !important; padding:0px; margin:0px;" />
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>	

	
	<div id="formResult">
	   <jsp:include page="invoiceList.jsp"/>
	</div>	
	
	<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>			
</s:form>	
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>



<script type="text/javascript">
var customers = {
		<s:iterator value='customers' status='customersStatus'>
		"<s:property value='id' />": "<s:property escape='false' value='name' />"<s:if test="!#customersStatus.last">,</s:if>
      </s:iterator>
 };

	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });
	
	// Initialize autocomplete with local lookup:
     $('#customersautocomplete').newautocomplete({
        lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
            $('#custId').val(suggestion.data);
			}
		},		
		        onInvalidateSelection: function() {
		            $('#custId').val("");
		        }
		    });
	
	function compare(a,b) {
	  if (a.value < b.value)
		 return -1;
	  if (a.value > b.value)
		return 1;
	  return 0;
	}

	
</script>



	            	