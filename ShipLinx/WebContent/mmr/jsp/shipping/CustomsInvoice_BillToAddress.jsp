<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 


<SCRIPT type="text/javascript">

	
	function submitform(method)
	{
	 //alert("AHA");	
	 document.orderForm.action = "shipment.stageThree.action?method="+method;
	 document.orderForm.submit();
	}

	/*function showBillToState() {
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("billToProvince");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox2');
		  url=contextPath+"/shipTo.listToProvience.action?type=billTo&value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()  
	*/

    
   
</script>


	<div id="customs_invoice_billto_address">
						<div class="content_body" >	
							<div class="content_table"> 
							<s:hidden name="taxIdC" value="%{shippingOrder.customsInvoice.billToAddress.taxId}" />
										
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  name="shippingOrder.customsInvoice.billToAddress.abbreviationName" value="%{shippingOrder.customsInvoice.billToAddress.abbreviationName}"  />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  name="shippingOrder.customsInvoice.billToAddress.address1" value="%{shippingOrder.customsInvoice.billToAddress.address1}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  name="shippingOrder.customsInvoice.billToAddress.address2" value="%{shippingOrder.customsInvoice.billToAddress.address2}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.customsInvoice.billToAddress.postalCode" onblur="javascript:getAddressSuggestBillTo();"  id="billToPostalCode" name="shippingOrder.customsInvoice.billToAddress.postalCode"   />
												<img id="loading-img-to" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield id="billToCity" key="shippingOrder.customsInvoice.billToAddress.city" name="shippingOrder.customsInvoice.billToAddress.city" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select   listKey="countryCode" listValue="countryName" name="shippingOrder.customsInvoice.billToAddress.countryCode" list="#session.CountryList" onchange="javascript:showBillToState();" headerKey="-1" theme="simple" id="firstBox2"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="billToProvince"><span>:</span>
												<s:include value="customsInvoiceBillToProvince.jsp"/>
											</div>
										</div>
										<div class="fields">
											<label><div id="accnt_number_lbl"><mmr:message messageId="label.customer.accountNumber"/></div></label>
											<div class="controls"><div id="accnt_number_txt"><span>:</span></div>
												<s:textfield id="billToAccountNum"  name="shippingOrder.customsInvoice.billToAccountNum" value="%{shippingOrder.customsInvoice.billToAccountNum}"  />
											</div>
										</div>
										
									</div>	
								</div>
							</div>
						</div>
						</div>