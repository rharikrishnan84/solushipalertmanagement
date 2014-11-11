<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<html> 
<head> 
    <sj:head jqueryui="true" />
    <sx:head />
    <title><s:text name="edi.title"/></title> 
<style type="text/css">

#customersautocomplete,#auto{ background-position: 115px 4px; background-size:8px 8px;  }
</style> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
</head> 
<body> 
<SCRIPT language="JavaScript">
	function searchInvoice()
	{
	 document.searchform.action = "invoice.action";
	 document.searchform.submit();
	}
		
	function submitform()
	{	
		document.searchInvoiceForm.action = "invoice.list.action?method=new";
	 document.searchInvoiceForm.submit();
	 //return false;
	}
	
	function showID(name)
	{
		alert(name);
	}
	
	function setInvoiceNum()
	{
		var inum_autocompleter = dojo.widget.byId("auto_invoice_num");
		var invnum_val = inum_autocompleter.getText();
		//alert(invnum_val);
		document.getElementById("invoice.invoiceNum").value = invnum_val;		
	}
</SCRIPT>
		
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
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="invoice.list" name="searchInvoiceForm">


<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.invoice.search"/></div>
								<div class="form_buttons" >		
									<a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a>
									a href="invoice.action"><mmr:message messageId="label.btn.reset"/></a>
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
									<s:if test="%{#session.ROLE.contains('busadmin')}">
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



	            	