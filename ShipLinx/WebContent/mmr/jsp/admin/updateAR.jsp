<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head> 
	<sj:head jqueryui="true" />
    <sx:head />
    <title><s:text name="edi.title"/></title>
<style type="text/css">
#customersautocomplete,#auto{ background-position: 145px 4px; background-size:8px 8px; }
</style> 	
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<SCRIPT language="JavaScript">
	
function searchar(){				
	document.updateARForm.action = "updateAR.action?method=new";
	 document.updateARForm.submit();
	}
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
</SCRIPT>
	


<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="updateAR" name="updateARForm" cssClass="form">
<s:token/>

<div class="content_body" id="srch_panel" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="menu.admin.updateAR"/></div>
								<div class="cont_hdrtitle_w">Select the invoices to update and fill in the appropriate details, then click "Process A/R"</div>
								<div class="form_buttons" >	
									
									<a href="javascript:searchar()"><mmr:message messageId="label.search.btn.search"/></a> 									
								</div>
							</div>		
							<div class="cont_data_body" id="update_ar_srch_table" >
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.customer.name" /></label>
										<div class="controls">
										<span>:</span>
											<input id="custId" type="hidden" value="" name="invoice.customerId">
<input id="customersautocomplete" type="text" value="" name="customerName" autocomplete="off">
										</div>
									</div>	
									<div class="fields">
										<label><mmr:message messageId="label.invoice.number" /></label>
										<div class="controls">
										<span>:</span>
		            		<s:textfield key="invoice.invoiceNum" name="invoice.invoiceNum" />
										</div>
									</div>	
								</div>
							</div>
						</div>
					</div>
					
</div>


        
	<div id="formResult">
	   <s:include value="updateARInvoiceList.jsp"></s:include>
	</div>
	
</s:form>
</div>
</div>


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



	            	
