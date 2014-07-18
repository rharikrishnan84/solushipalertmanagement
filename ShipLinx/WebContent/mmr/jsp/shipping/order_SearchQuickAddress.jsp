<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!--  
<div id="pckg_choose">
<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0" style="margin-bottom:-3px;">
&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/fast_forward.png" includeContext="true" />" border="0" style="margin-bottom:-3px;">&nbsp;&nbsp;  
<a href="backToShipment.action?switch=true">Switch to Quote Mode</a>
</div>
-->
<style type="text/css">
.autocomplete-suggestions {
border: 1px solid #999;
background: #FFF;
cursor: default;
overflow: auto;
-webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
-moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
}
#customerautocomplete,#auto{ background-position: 262px 4px; background-size:10px 10px; }
</style>
<div class="content">
<div id="srch_address">
<s:if test="%{#session.ROLE.contains('busadmin')}">
<div class="content_body">
							<div class="content_table">
								<div class="content_header">
									<div class="cont_hdrtitle_c"><mmr:message messageId="label.enter.shipping.details"/><s:if test="%{shippingOrder.id > 0}">&nbsp;---&nbsp;<mmr:message messageId="label.shippingOrderTab.order"/>#&nbsp;<font color="white"><s:property value="shippingOrder.id" /></font></s:if>
	<s:else>
	
	<mmr:message messageId="label.enter.new.shipping.details"/></s:else>
	<s:hidden name="shippingOrder.customerId" />
	</div>
	<div class="form_buttons" style="width:175px !important;">
										<a href="javascript:backToShipment();">Switch to Quote Mode</a>
									</div>
								</div>
								
							<div class="cont_data_body borderLeftRight" >
								<div class="rows">
									<div class="fieldsTextLong">
										<label ><mmr:message messageId="label.customer.set"/> </label>
										<div class="controls"><span>:</span>
											<s:if test="%{shippingOrder.customer!=null}"> <!-- Put the logic of condition check for customer if selected and retrieved in the called Action -->
					<font color="#990000" style="font-family: Arial; font-weight: bold; font-size: 11;"><s:label key="shippingOrder.customer.name" cssStyle="width:280px;" /></font> <!-- Put the logic of displaying the customer that is set in the called Action -->
				</s:if>
				<s:else>
					<font color="#990000" style="margin-left: 8px; padding-top:3px; float:left;"><mmr:message messageId="label.customer.notset"/></font>
				</s:else>
										</div>
									</div>
									<div class="fieldsTextLong" style="margin-left:83px;">
										<label style=""><mmr:message messageId="label.customer.changeto"/></label>
										<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocomplete" class="customerautocmpt" style="width:280px; background-color: #F2F2F2;  border: 1px solid #c7c6c6;"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>
									</div>
								</div>
							</div>
							</div>
						</div>	
</s:if>
<s:else>
				<div class="content_body">
				<div class="content_table">
								<div class="content_header">
									<div class="cont_hdrtitle_c"><mmr:message messageId="label.enter.shipping.details"/><s:if test="%{shippingOrder.id > 0}">&nbsp;---&nbsp;<mmr:message messageId="label.shippingOrderTab.order"/>#&nbsp;<font color="white"><s:property value="shippingOrder.id" /></font></s:if>
	<s:else><mmr:message messageId="label.enter.new.shipping.details"/></s:else></div>
									<div class="form_buttons" style="width:175px">
										<a href="javascript:backToShipment();">Switch to Quote Mode</a>
									</div>
								</div>
							</div>	
							</div>
</s:else>
				<s:hidden name="shippingOrder.webCustomerId" />
</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

var customers = {
		<s:iterator value='#session.customersList'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocomplete').newautocomplete({
       lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
           $('#custId').val(suggestion.data);
			changeCustomer();
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
