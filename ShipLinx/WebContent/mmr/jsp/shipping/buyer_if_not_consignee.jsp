<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<SCRIPT type="text/javascript">

	
	function submitform(method)
	{
	 //alert("AHA");	
	 document.orderForm.action = "shipment.stageThree.action?method="+method;
	 document.orderForm.submit();
	}

	/*function showBuyerState() {
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("buyerProvince");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox3');
		  url=contextPath+"/shipTo.listToProvience.action?type=buyer&value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()      
   */
   
   function assignBuyerCompany()
		{	
			var dojos = getElementsByClassName("dojoComboBox");
			var p_desc = dojos[0].value;			
			document.getElementById("shippingOrder.customsInvoice.buyerAddress.abbreviationName").value=dojos[0].value;
			var item = document.getElementById("buyerAbbreviationName");			
			document.getElementById("shippingOrder.customsInvoice.buyerAddress.abbreviationName").value=item.value;
		}
		
		dojo.event.topic.subscribe("/value_buyerAddress", function(value, key, text, widget)
		{
			//fetch only the company name and set it to the autocompleter after ajax
			dojoAdd_key = key;
			setBuyerAddress("buyerAddress");
			var companyto = value;
			companyto = companyto.substring(0,companyto.indexOf(","));			
		});
		
		function setBuyerAddress(type)
		{	
			var autoCompleter="";
			var value =0;				
				value = dojoAdd_key;				
				ajax_ChangeTo=ajaxFunction();
				ajax_ChangeTo.onreadystatechange=function()
			  	{
					   if(ajax_ChangeTo.readyState==4)
						{
							reponse=ajax_ChangeTo.responseText;
							js_stateid=document.getElementById("hide_buyer_address");
							js_stateid.innerHTML= reponse;
							var buyer_abbreviationName= document.getElementById("buyer_abbreviationName").value;
							document.getElementById("shippingOrder.customsInvoice.buyerAddress.abbreviationName").value=buyer_abbreviationName;
							var buyer_address1= document.getElementById("buyer_address1").value;
							document.getElementById("buyerAddress1").value = buyer_address1;
							var buyer_address2= document.getElementById("buyer_address2").value;
							document.getElementById("buyerAddress2").value = buyer_address2;	
							var buyer_postalCode= document.getElementById("buyer_postalCode").value;
							document.getElementById("buyerPostalCode").value = buyer_postalCode;	
							var buyer_city= document.getElementById("buyer_city").value;
							document.getElementById("buyercity").value = buyer_city;	
							var buyer_countryCode= document.getElementById("buyer_countryCode").value;
							document.getElementById("firstBox3").value = buyer_countryCode;	
							showBuyerState();
							var buyer_taxId= document.getElementById("buyer_taxId").value;
							document.getElementById("buyerTax").value = buyer_taxId;
						}
			 	}
				var url="<%=request.getContextPath()%>/admin/selectShippingAddress.action?addressid="+value+"&type="+type;
				ajax_ChangeTo.open("GET",url,true);
				ajax_ChangeTo.send(this);
				//end of ajax call --
		}
</script>
<style>
img.dojoComboBox{display:none}
</style>

	
	<div class="content_body" >	
							<div class="content_table" style="margin-top:1px;" > 
										
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/></label>
											<div class="controls" ><span>:</span>
												<s:hidden name="shippingOrder.customsInvoice.buyerAddress.abbreviationName" id="shippingOrder.customsInvoice.buyerAddress.abbreviationName"/>		        
												<s:url id="addressList" action="listAddresses" >
												<s:param name="customerId" value="%{shippingOrder.customerId}"/>
												<s:param name="shipperType" value="'CN'"/>	 
												</s:url>
												<sx:autocompleter id="buyerAbbreviationName" cssStyle=" width:160px; " keyName="address.addressId" name="searchString" href="%{addressList}" dataFieldName="addressSearchResult" loadOnTextChange="true"  value="%{shippingOrder.customsInvoice.buyerAddress.abbreviationName}" onkeyup="javascript: assignBuyerCompany();" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_buyerAddress"  preload="true"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="buyerAddress1" key="shippingOrder.customsInvoice.buyerAddress.address1" name="shippingOrder.customsInvoice.buyerAddress.address1" value="%{shippingOrder.customsInvoice.buyerAddress.address1}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="buyerAddress2" key="shippingOrder.customsInvoice.buyerAddress.address2" name="shippingOrder.customsInvoice.buyerAddress.address2" value="%{shippingOrder.customsInvoice.buyerAddress.address2}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.postalCode" onblur="javascript:getAddressSuggestBuyerTo();"  id="buyerPostalCode" name="shippingOrder.customsInvoice.buyerAddress.postalCode"   />
													<img id="loading-img-to" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield id="buyercity" key="shippingOrder.customsInvoice.buyerAddress.city" name="shippingOrder.customsInvoice.buyerAddress.city" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select  listKey="countryCode" listValue="countryName" name="shippingOrder.customsInvoice.buyerAddress.countryCode" list="#session.CountryList" onchange="javascript:showBuyerState();" headerKey="-1" theme="simple" id="firstBox3"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="buyerProvince">
												<s:include value="customsInvoiceBuyerProvince.jsp"/>
											</div>
										</div>
										<div class="fields" >
											<label><div id="tax_lbl"><mmr:message messageId="label.tax.id"/></div></label>
											<div class="controls"><span>:</span>
												<s:textfield id="buyerTax"  key="shippingOrder.customsInvoice.taxId" name="shippingOrder.customsInvoice.taxId" value="%{shippingOrder.customsInvoice.taxId}"  />
											</div>
										</div>
										
									</div>	
								</div>
							</div>
						</div>
			<div id="hide_buyer_address" style="display: none;">
				<s:include value="customsBrokerInformations.jsp"/>
			</div>
		
		
	