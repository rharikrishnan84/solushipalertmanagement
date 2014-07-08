<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>


<SCRIPT type="text/javascript">

	
	function submitform(method)
	{
	 //alert("AHA");	
	 document.orderForm.action = "shipment.stageThree.action?method="+method;
	 document.orderForm.submit();
	}
	
    
   
</script>


		<div  id="productDetails">
		
			<div  id="div_bill_section_four_tbl">
			<div class="content_body">
			<div class="content_table" >
			 
			<s:iterator value="%{shippingOrder.customsInvoice.products}" status="counterIndex">	
				<div class="rows">
				<s:set name="custInvId" value="%{customsInvoice.products[#counterIndex.index].customsInvoiceId}"/>
				<s:set name="prodId" value="%{customsInvoice.products[#counterIndex.index].productId"/>
				
          <div class="sl_no" style=" width:30px;"><s:property value="%{#counterIndex.index+1}"/></div>
			  <div class="largeFields">
			   <div class="controls">
				<s:textfield id="cip[#counterIndex.index].productDesc"  name="shippingOrder.customsInvoice.products[#counterIndex.index].productDesc"   theme="simple"  value="%{shippingOrder.customsInvoice.products[#counterIndex.index].productDesc}" readonly="true"/>
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
				<s:textfield id="cip[#counterIndex.index].productHC" name="shippingOrder.customsInvoice.products[#counterIndex.index].productHC"   theme="simple"  value="%{shippingOrder.customsInvoice.products[#counterIndex.index].productHC}"
				cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
				<s:select id="cip[#counterIndex.index].productOrigin"  listKey="countryCode" listValue="countryName" name="shippingOrder.customsInvoice.products[#counterIndex.index].productOrigin" list="#session.CountryList" onchange="javascript:showShipToState();" headerKey="-1" theme="simple"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
			   <s:textfield id="cip[#counterIndex.index].unitPrice" name="shippingOrder.customsInvoice.products[#counterIndex.index].unitPrice"   theme="simple"  value="%{shippingOrder.customsInvoice.products[#counterIndex.index].unitPrice}"
			   cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
			   <s:textfield id="cip[#counterIndex.index].productQuantity" name="shippingOrder.customsInvoice.products[#counterIndex.index].productQuantity"  theme="simple" value="%{shippingOrder.customsInvoice.products[#counterIndex.index].productQuantity}"
			   cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="smallFields">         
			   <div class="controls">
				<s:textfield id="cip[#counterIndex.index].totalPrice" name="customerSalesCommission"   theme="simple" size="3" value="%{shippingOrder.customsInvoice.products[#counterIndex.index].productQuantity*shippingOrder.customsInvoice.products[#counterIndex.index].unitPrice}" cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="fields_topdown_s">         
			   <div class="controls">
			  <input type="checkbox" value="<s:property value="%{#counterIndex.index}"/>" name="delete_element" />
				</div>
			  </div>
			  </div>	
			</s:iterator>
		
					
	
	</div>
</div>
</div>

</div>