<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="content">
<div class="content_body">	
<div id="div_ship_to">
	<div class="content_table" id="to_add_inner_table">
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/> </label>
											<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
										 <s:param name="shippingOrder.customerId" value="%{shippingOrder.customerId}"/>
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custIdto" />			

				<s:textfield id="customerautocompto"value="%{shippingOrder.toAddress.abbreviationName}" name="shippingOrder.toAddress.abbreviationName"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>	</div>
										<div id="ship_to">
					<div class="fields">
											<label><mmr:message messageId="label.address.button.save"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.saveFromAddress}"  name="shippingOrder.saveFromAddress"/>
											</div>
					</div>
											
				
					<div id="toAdd_inner">
						<s:include value="toAddress_inner.jsp"/>
					</div>
				</div>	
				</div>
			</div>
		</div>
	</div>
</div>
</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

var customers = {
		<s:iterator value='#session.usersList'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocompto').newautocomplete({
       lookup: customersArray,
       triggerSelectOnValidInput: false,
		minChars: 0,
		onSelect: function (suggestion) {			
		if(suggestion.value != ""){
			 var resto = suggestion.value.split(",");
			 $('#customerautocompto').val(resto[0]);
           $('#custIdto').val(suggestion.data);
          
			searchto();
			}
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