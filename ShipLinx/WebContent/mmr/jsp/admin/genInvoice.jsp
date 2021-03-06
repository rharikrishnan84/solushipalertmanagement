<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head> 
    <sj:head jqueryui="true" />
    <sx:head cache="true"/>
    <title></title> 
<style type="text/css">

#customersautocomplete{ 
background-position: 145px 4px; background-size:8px 8px; }
</style> 
</head> 
<SCRIPT language="JavaScript">
	
	function search_invoice(action)
	{
	document.generateInvoiceForm.action = action;
			 document.generateInvoiceForm.submit();
	}
	
		
	
	
	
			$(window).load(function() {
		  var wndo = $(window).height();
		  wndo -=46;
		  $('#wrapper_new').css('min-height',wndo);
		  });
</SCRIPT>

<body>
<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
	</div>
  

<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="invoice.create" name="generateInvoiceForm">
<s:token/>

					<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="menu.admin.genInvoice"/></div>
								<div class="form_buttons" id="srch_actions" >	
									
									<a href="javascript:search_invoice('invoiceable.list.action')"><mmr:message messageId="label.search.btn.search"/></a>
								</div>
							</div>		
							<div class="cont_data_body" id="geninv_srch_table">
								<div class="rows">
										
								</div>
							</div>
						</div>
					</div>
	   <div id="formResult">
	  	 <jsp:include page="unbilledShipments.jsp"/>
	   </div>
</s:form>

<div class="content_body">
		<div class="content_table" >
		&nbsp;
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
</body>
</html>