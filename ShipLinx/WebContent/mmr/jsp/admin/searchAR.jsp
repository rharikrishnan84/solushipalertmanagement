<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head> 
    <sx:head />
    <title><s:text name="edi.title"/></title> 
	    <style type="text/css">
#customersautocomplete,#auto{ background-position: 145px 4px; background-size:8px 8px; }
</style> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function submitform()
	{	
	 document.searchARForm.action = "searchAR.action?method=new";
	 document.searchARForm.submit();
	 //return false;
	}

</SCRIPT>

<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="searchAR" name="searchARForm">

<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="menu.admin.searchAR"/></div>
								<div class="form_buttons" >	
									<a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a>
								</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.customer.name" /></label>
										<div class="controls">
											<span>:</span>
											<input id="custId" type="hidden" value="" name="id">
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

 	<div id="formResult">
	   <s:include value="arList.jsp"></s:include>
	</div>
</s:form>

<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>

</div>
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


