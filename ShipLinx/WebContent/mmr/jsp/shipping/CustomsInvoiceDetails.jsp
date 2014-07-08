<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/style.css">
<html>
<head>
<title><s:text name="user.form.title"/></title>

<script type="text/javascript">
window.onload = function() {
     var e = document.getElementById("customs_invoice_checkbox");
     if(e.checked)
     {
      document.getElementById("radio_billto").style.display= "block";
      document.getElementById("customs_invoice_panel").style.display= "block";
     }
  
};
</script>
	
<script>
	window.onload=function(){
		$(".form_buttons_radio input[type='radio']").wrap("<div class='radiocontrols'></div>");
		$("div.radiocontrols").each(function() {
			$(this).append($(this).next());
		});
		/*$('#customs_invoice_checkbox').click(function(){
			$('#checkbox_header').css('margin-top','1px');
		});*/
		
	/*	$('#customs_invoice_checkbox').click(function(){
	
			if($('#customs_invoice_checkbox').attr('checked')){
				$('#checkbox_header').css('margin-top','0px');
			}else{
				$('#checkbox_header').css('margin-top','-8px');
			}
		});*/
		
		
   }
</script>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript">
function assignCompany()
		{
			var dojos = getElementsByClassName("dojoComboBox");
			var p_desc = dojos[4].value;			
			document.getElementById("shippingOrder.customsInvoice.brokerAddress.abbreviationName").value=dojos[4].value;
			var item = document.getElementById("br.abbreviationName");			
			document.getElementById("shippingOrder.customsInvoice.brokerAddress.abbreviationName").value=item.value;
		}
		
		dojo.event.topic.subscribe("/value_address", function(value, key, text, widget)
		{
			//fetch only the company name and set it to the autocompleter after ajax
			dojoAdd_key = key;
			setAddress("brokerAddress");
			var companyto = value;
			companyto = companyto.substring(0,companyto.indexOf(","));			
		});
		
		function setAddress(type)
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
							js_stateid=document.getElementById("hide_broker_address");
							js_stateid.innerHTML= reponse;
							var br_abbreviationName= document.getElementById("br_abbreviationName").value;
							document.getElementById("shippingOrder.customsInvoice.brokerAddress.abbreviationName").value=br_abbreviationName;
							var br_address1= document.getElementById("br_address1").value;
							document.getElementById("ci.address1").value = br_address1;
							var br_address2= document.getElementById("br_address2").value;
							document.getElementById("ci.address2").value = br_address2;	
							var br_postalCode= document.getElementById("br_postalCode").value;
							document.getElementById("ci.postcalCode").value = br_postalCode;	
							var br_city= document.getElementById("br_city").value;
							document.getElementById("ci.city1").value = br_city;	
							var br_countryCode= document.getElementById("br_countryCode").value;
							document.getElementById("firstBoxb").value = br_countryCode;	
							showShipToStateb();
							var br_phoneNo= document.getElementById("br_phoneNo").value;
							document.getElementById("ci.phoneNo").value = br_phoneNo;	
							var br_faxNo= document.getElementById("br_faxNo").value;
							document.getElementById("ci.faxNo").value = br_faxNo;	
							var br_emailAddress= document.getElementById("br_emailAddress").value;
							document.getElementById("ci.emailid").value = br_emailAddress;	
							var br_contactName= document.getElementById("br_contactName").value;
							document.getElementById("ci.contactName").value = br_contactName;
						}
			 	}
				var url="<%=request.getContextPath()%>/admin/selectShippingAddress.action?addressid="+value+"&type="+type;
				ajax_ChangeTo.open("GET",url,true);
				ajax_ChangeTo.send(this);
				//end of ajax call --
		}
		function clearBrokerInformation(){
		var confirmationMessage=confirm('Are you sure want to clear "Broker Information"?');
		if(confirmationMessage==true){
		var dojos = getElementsByClassName("dojoComboBox");
		dojos[4].value="";		
		document.getElementById("shippingOrder.customsInvoice.brokerAddress.abbreviationName").value="";
		document.getElementById("ci.address1").value="";
		document.getElementById("ci.address2").value="";
		document.getElementById("ci.postcalCode").value="";
		document.getElementById("ci.city1").value="";
		document.getElementById("firstBoxb").value="";
		document.getElementById("shippingOrder.customsInvoice.brokerAddress.provinceCode").innerHTML="";
		document.getElementById("ci.phoneNo").value="";
		document.getElementById("ci.faxNo").value="";
		document.getElementById("ci.emailid").value="";
		document.getElementById("ci.contactName").value="";		
		}
		}
</script>
		
<script type="text/javascript">

var desc_event=dojo.event.topic.subscribe("/value_desc", function(value, key, text, widget){
		
	 ajax_Country_desc=ajaxFunction();	 
	ajax_Country_desc.onreadystatechange=function()
	  {
		   if(ajax_Country_desc.readyState==4 && ajax_Country_desc.status==200)
			{
						
			response_desc=ajax_Country_desc.responseText;
			js_stateid=document.getElementById("hide_this");
			js_stateid.innerHTML= response_desc;
			
			var sess_desc= document.getElementById("desc_id").value;
			dojo.widget.byId("autoproductdesc").setValue(sess_desc);
			
			var sess_hcode= document.getElementById("hcode_id").value;
			document.getElementById("new_prod_hcode").value=sess_hcode;
			//dojo.widget.byId("autoproducthcode").setValue(sess_hcode);
						
			var sess_uprice= document.getElementById("uprice_id").value;
			document.getElementById("new_prod_uprice").value=sess_uprice;
				
			var sess_country= document.getElementById("country_id").value;
			var dd_country= document.getElementById("new_productOrigin");
			for(var i=0;i < dd_country.options.length;i++)
			{
				if (dd_country.options[i].value == sess_country )
					 dd_country.options[i].selected = true;
			}
				//desc_event=null;
				//alert("done");
				//e.stopPropagation();
				
				//alert("done-desc");
			}			
			
	  }	,
	  
		url="populateProducts.action?productId="+key+"&product_desc="+value;
		ajax_Country_desc.open("GET",url,true);
		ajax_Country_desc.send(this);	   
});

</script>

<SCRIPT type="text/javascript">
var contextPath = "<%=request.getContextPath()%>";

 
function toShoworHide(checked)
	{		
		if(checked)
		{
			//alert("Checked");
			document.getElementById("customs_invoice_panel").style.display = 'block';
			document.getElementById("radio_billto").style.display = 'block';
						
		}
		else
		{
			//alert("UnChecked");
			document.getElementById("customs_invoice_panel").style.display = 'none';
			document.getElementById("radio_billto").style.display = 'none';			
		}


			
	}
	
	function changeBillTo(sel)
	{
		if(sel == '2')
		{			
			document.getElementById("accnt_number_lbl").style.display= 'none';
			document.getElementById("accnt_number_txt").style.display= 'none';	
			radioselected=2;	
		}
		else if(sel == '1')
		{			
			document.getElementById("accnt_number_lbl").style.display= 'none';
			document.getElementById("accnt_number_txt").style.display= 'none';
			radioselected=1;
		}
		else if(sel == '3')
		{			
			document.getElementById("accnt_number_lbl").style.display= 'block';
			document.getElementById("accnt_number_txt").style.display= 'block';		
			radioselected=3;	
		}
		if(sel!= '3')
		{
			document.getElementById("loading-img-billto").style.display = 'block';
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
	 		{
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("customs_invoice_billto_address");
				js_stateid.innerHTML= reponse;
				document.getElementById("loading-img-billto").style.display = 'none';
				document.getElementById("consigneeTax").value = document.getElementById("taxIdC").value;
				}
	  		}
			url="setBillToAddress.action?selected="+sel;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);

		}
	}
	
    
     function assignTotal()
     {
	    var p_qty = document.getElementById("new_prod_quantity").value;
		var p_uprice = document.getElementById("new_prod_uprice").value;
		p_uprice = parseFloat(p_uprice).toFixed(2);
		if(p_qty!='' && (!isAllDigits(p_qty) || p_qty <= 0)) 
		{
			alert('You need to provide a valid quantity');
			document.getElementById("new_prod_quantity").value='';
			document.getElementById("new_prod_quantity").focus();
		}
		if(p_uprice!='' && (!isAllDigits(p_uprice) || p_uprice <= 0)) 
		{
			alert('You need to provide a valid price');
			document.getElementById("new_prod_uprice").value='';
			document.getElementById("new_prod_uprice").focus();
		}		
		var pprice = parseFloat(p_uprice);
		if(isNaN(pprice) || isNaN(p_qty))
			document.getElementById("new_prod_tprice").value= '0.0';
		else
		{
			document.getElementById("new_prod_tprice").value= (p_qty*p_uprice).toFixed(2);
			checkToAdd();
		}		
    }  
    
    function isAllDigits(argvalue) {
        argvalue = argvalue.toString();
        var validChars = "0123456789.";
        var startFrom = 0;
        if (argvalue.substring(0, 2) == "0x") {
           validChars = "0123456789abcdefABCDEF";
           startFrom = 2;
        } else if (argvalue.charAt(0) == "0") {
           startFrom = 1;
        } else if (argvalue.charAt(0) == "-") {
            startFrom = 1;
        }

        for (var n = startFrom; n < argvalue.length; n++) {
            if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
        }
        return true;
    }
    
     function checkToAdd(){
   // alert("Inside checkToAdd");
    var dojos = getElementsByClassName("dojoComboBox");
	var p_qty = document.getElementById("new_prod_quantity").value;
	var p_uprice = document.getElementById("new_prod_uprice").value;
	p_uprice = parseFloat(p_uprice).toFixed(2);
	//var p_desc = dojo.widget.byId("autoproductdesc").getSelectedValue();	
	var p_desc = dojos[6].value;	
	//alert("P_DESC:::"+p_desc);
	var p_hcode = document.getElementById("new_prod_hcode").value;
	//alert(p_hcode);
	var check = true;
	
	if(!p_desc.length > 0)
	{
		alert('Please enter Product Description');
		//dojo.widget.byId("autoproductdesc").setValue='';
		document.getElementById("new_prod_quantity").value='';		
		document.getElementById('new_prod_tprice').value='';
		check = false;
	}		
	else if(!p_qty.length > 0)
	{
		alert('Please enter quantity');
		document.getElementById("new_prod_quantity").value='';
		document.getElementById('new_prod_tprice').value='';
		document.getElementById("new_prod_quantity").focus();
		check = false;
	}
	else if(!p_uprice.length > 0)
	{
		alert('Please enter Unit Price');
		document.getElementById("new_prod_uprice").value='';
		document.getElementById("new_prod_quantity").value='';
		document.getElementById('new_prod_tprice').value='';
		document.getElementById("new_prod_uprice").focus();
		check = false;
	}
	else if(!isAllDigits(p_qty) || p_qty <= 0) 
	{
		alert('You need to provide a valid quantity');
		document.getElementById("new_prod_quantity").value='';
		document.getElementById('new_prod_tprice').value='';
		document.getElementById("new_prod_quantity").focus();
		check = false;
	}
	else if(!isAllDigits(p_uprice) || p_qty < 0) 
	{
			alert('You need to provide a valid unit price');
			document.getElementById("new_prod_uprice").value='';
			document.getElementById("new_prod_quantity").value='';
			document.getElementById('new_prod_tprice').value='';
			document.getElementById("new_prod_uprice").focus();
			check = false;
	}
		if(check)
		{
			//alert("inside check");
			addProduct();
		}
	}
	
	function addProduct(){
	//alert("Inside addProduct");
	//alert(contextPath);
	var p_desc= dojo.widget.byId("autoproductdesc").getSelectedValue();
	var p_hcode= document.getElementById("new_prod_hcode").value;
	var e= document.getElementById("new_productOrigin");
	var p_country = e.options[e.selectedIndex].value;
	var p_quantity= document.getElementById("new_prod_quantity").value;
	var p_uprice= document.getElementById("new_prod_uprice").value;
	var p_weight="";
	p_uprice = parseFloat(p_uprice).toFixed(2);
	
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {	
	  		//alert("ajax_Country.readyState::"+ajax_Country.readyState);
		   if(ajax_Country.readyState==4)
			{
			response_add=ajax_Country.responseText;
			//alert(response);
			js_stateid=document.getElementById("productDetails");
			js_stateid.innerHTML= response_add;
			resetProductFields();
			}
	  }
		url="addProductInformation.action?desc="+p_desc+"&hcode="+p_hcode+"&origin_country="+p_country+"&quantity="+p_quantity+"&unit_price="+p_uprice+"&weight="+p_weight;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
	
	
	//var blank="";
	//dojo.widget.byId("autoproductdesc").setValue(blank);	
	//resetField('new_prod_hcode');
//	resetField('new_prod_quantity');
	//resetField('new_prod_uprice');
	//resetField('new_prod_tprice');	
	}
	
	function resetProductFields()
	{
		//alert("Inside resetProductFields");
		var dojos = getElementsByClassName("dojoComboBox");
		document.getElementById('new_prod_hcode').value='';
		document.getElementById('new_prod_uprice').value='';
		document.getElementById('new_prod_quantity').value='';
		document.getElementById('new_prod_tprice').value='';
		document.getElementById('desc_id').value='';
		//alert(dojos[0].value);
		//alert(dojos[1]);
		//alert(dojos[2].value);
		//dojo.widget.byId("autoproductdesc").setValue("");
		dojos[6].value="";
		//dojo.widget.byId("autoproductdesc").setValue("test");
	}
	
	function deleteProduct(index)
	{
	
		var rate = document.getElementsByName("delete_element");
	
		var txt = 0;
		var count =0;
		var i;
		for (i=0;i<rate.length;i++){
			if (rate[i].checked){
				txt = txt + rate[i].value ;
				count++;
			}
		}
		
		if(count>1){
			alert("please delete only one");
			return false;
		}
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
	  	{
		   if(ajax_Country.readyState==4)
			{
			response_del=ajax_Country.responseText;
			js_stateid=document.getElementById("productDetails");
			js_stateid.innerHTML= response_del;
			}
	  	}
		
		url="delete.customsinvoice.product.action?index="+txt;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
	
	}
	
	function resetField(eid)
	{
		document.getElementById(eid).value='';
	}
	
	function resetACField(eid)
	{
		dojo.widget.byId(eid).setValue("");
	}
	
	function typenumbers(e,filterString)

	{
		var key, keychar;
		key = getkey(e);
		if (key == null)
		return true;
		
		// get character
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();
		
		// control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==27) )
		return true;
		
		// alphas and numbers
		else if ((filterString.indexOf(keychar) > -1))
		return true;
		else
		return false;
	}

	function getkey(e)
	{
		if (window.event)
		return window.event.keyCode;
		else if (e)
		return e.which;
		else
		return null;
	}
	
</SCRIPT>


<div class="content_body" style="padding-bottom:1px;" >	
							<div class="content_table"  >
								<div class="content_header" id="checkbox_header" >
									<div class="cont_hdrtitlelarge" style="width:470px !important;">
										<mmr:message messageId="label.like.customs.invoice"/>
										<s:checkbox name="shippingOrder.customsInvoiceRequired"  value="%{shippingOrder.customsInvoiceRequired}" id="customs_invoice_checkbox" onclick="toShoworHide(this.checked);"/>
									</div>
								</div>
							</div>
						</div>
	
	<div id="customs_invoice_panel">
	
		<div id="buyer_table_div">
				<div class="content_body" >	
							<div class="content_table" > 
				<div class="content_header" >
									<div class="cont_hdr_title"><mmr:message messageId="label.buyer.info"/></div>
										
		
								</div>
								</div>
								</div>
				
		</div>
		<s:include value="buyer_if_not_consignee.jsp"/> 
	
		<div id="radio_table_div">
			<div class="content_body" >	
				<div class="content_table"> 		
					<div class="content_header">
						<div class="cont_hdr_title"><mmr:message messageId="label.bill.to"/></div>	
												
							<div id="radio_billto">
								<div id="loading-billto"><img id="loading-img-billto" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0"></div>
				
									<div class="form_buttons_radio">
										<s:radio id="radio_address" list="#{'1':'Shipper','2':'Consignee','3':'Third Party'}" value="2" onclick="changeBillTo(this.value);" name="shippingOrder.customsInvoice.billTo"  />				
									</div>
							</div>
					</div>
				</div>
			</div>				
		</div>
		<s:include value="CustomsInvoice_BillToAddress.jsp"/> 
		
		<div class="content_body" >	
							<div class="content_table"> 
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.customs.invoice.info"/></div>
									<div class="form_buttons">
										<p style="margin-left:133px"><span>*</span><mmr:message messageId="label.mandatory.fields"/></p>
									</div>
								</div>		
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.reference.no"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.abbreviationName" name="shippingOrder.customsInvoice.reference" value="%{shippingOrder.customsInvoice.reference}"  />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.currency"/>&nbsp;<span>*</span>  </label>
											<div class="controls" id="currency"><span>:</span>
												<sx:autocompleter name="shippingOrder.customsInvoice.currency"  id="ci.currency"cssStyle=" width:160	px;  " list="{'CAD','USD'}"  autoComplete="false" preload="true"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.total.value"/>&nbsp;<span>*</span> </label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.totalvalue" name="shippingOrder.customsInvoice.totalValue" value="%{shippingOrder.customsInvoice.totalValue}"  onkeypress="return typenumbers(event,\'0123456789.\')"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.total.weight"/>&nbsp;<span>*</span> </label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.totalweight" name="shippingOrder.customsInvoice.totalWeight" value="%{shippingOrder.customsInvoice.totalWeight}"  onkeypress="return typenumbers(event,\'0123456789.\')"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.incoTerms"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.incoTerms" name="shippingOrder.customsInvoice.incoTerms" value="%{shippingOrder.customsInvoice.incoTerms}"  />
											</div>
										</div>
										<div class="fields">
										<div id="consigneetax_lbl">
											<label><mmr:message messageId="label.tax.id"/></label>
										</div>	
											<div class="controls" id="customs_invoice_billto_addressTax"><span>:</span>
												<s:textfield id="consigneeTax"  name="shippingOrder.customsInvoice.billToAddress.taxId" value="%{shippingOrder.customsInvoice.billToAddress.taxId}"  />
											</div>
										</div>
										<div class="fieldsvl" >
											<label style="width:213px !important; padding-top:7px;"><mmr:message messageId="label.shippingOrder.exportData"/></label>
											<div class="controls" style="width:174px !important;"><span>:</span>
												<s:textfield  id="ci.exportData" cssStyle="width:160px; height:22px; padding-left:5px;" name="shippingOrder.customsInvoice.exportData" value="%{shippingOrder.customsInvoice.exportData}"  />
											</div>
										</div>
										
									</div>	
								</div>
							</div>
						</div>	
		
	
			<div class="content_body" >	
							<div class="content_table" > 
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.broker.info"/></div>
									<div class="form_buttons">
										<a href="javascript:clearBrokerInformation();" >CLEAR</a>
									</div>
								</div>		
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/> </label>
											<div class="controls"><span>:</span>
												<s:hidden name="shippingOrder.customsInvoice.brokerAddress.abbreviationName" id="shippingOrder.customsInvoice.brokerAddress.abbreviationName"/>		        
												<s:url id="addressList" action="listAddresses" >
												<s:param name="customerId" value="%{shippingOrder.customerId}"/>	
												<s:param name="shipToCountryCode" value="%{shippingOrder.toAddress.countryCode}"/>	         
												</s:url>
												<sx:autocompleter id="br.abbreviationName" cssStyle=" width:160px;" keyName="address.addressId" name="searchString" href="%{addressList}" dataFieldName="addressSearchResult" loadOnTextChange="true"  value="%{shippingOrder.customsInvoice.brokerAddress.abbreviationName}" onkeyup="javascript: assignCompany();" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_address"  preload="true"/>
											</div>
										</div>
										<%-- <s:include value="customsBrokerInformations.jsp"/> --%>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.address1" key="shippingOrder.customsInvoice.brokerAddress.address1" name="shippingOrder.customsInvoice.brokerAddress.address1" value="%{shippingOrder.customsInvoice.brokerAddress.address1}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.address2" key="shippingOrder.customsInvoice.brokerAddress.address2" name="shippingOrder.customsInvoice.brokerAddress.address2" value="%{shippingOrder.customsInvoice.brokerAddress.address2}" />
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.customsInvoice.brokerAddress.postalCode" onblur="javascript:getSuggestBrokerAddress();"  id="ci.postcalCode" name="shippingOrder.customsInvoice.brokerAddress.postalCode"   />
												<img id="loading-img-to" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/> </label>
											<div class="controls"><span>:</span>
												<s:textfield id="ci.city1" key="shippingOrder.customsInvoice.brokerAddress.city" name="shippingOrder.customsInvoice.brokerAddress.city" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select   listKey="countryCode" listValue="countryName" key="shippingOrder.customsInvoice.brokerAddress.countryCode" name="shippingOrder.customsInvoice.brokerAddress.countryCode" list="#session.CountryList" onchange="javascript:showShipToStateb();" headerKey="-1"  id="firstBoxb" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls"><span>:</span>
												<s:include value="customsInvoiceBrokerProvince.jsp"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.phoneNo" key="shippingOrder.customsInvoice.brokerAddress.phoneNo"  name="shippingOrder.customsInvoice.brokerAddress.phoneNo" value="%{shippingOrder.customsInvoice.brokerAddress.phoneNo}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.user.fax"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.faxNo" key="shippingOrder.customsInvoice.brokerAddress.faxNo"  name="shippingOrder.customsInvoice.brokerAddress.faxNo" value="%{shippingOrder.customsInvoice.brokerAddress.faxNo}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.emailid" key="shippingOrder.customsInvoice.brokerAddress.emailAddress" name="shippingOrder.customsInvoice.brokerAddress.emailAddress" value="%{shippingOrder.customsInvoice.brokerAddress.emailAddress}" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customsInvoice.brokerName"/>&nbsp;<span>*</span> </label>
											<div class="controls"><span>:</span>
												<s:textfield  id="ci.contactName" key="shippingOrder.customsInvoice.brokerAddress.contactName" name="shippingOrder.customsInvoice.brokerAddress.contactName" value="%{shippingOrder.customsInvoice.brokerAddress.contactName}" />
											</div>
										</div>
										
										
									</div>	
								</div>
							</div>
							<div id="hide_broker_address" style="display: none;">
								<s:include value="customsBrokerInformations.jsp"/>
							</div>
			</div>
			
<script>
	function callfun(index){
		//createCustomConfirm;
				var ALERT_BUTTON_TEXT = 'Ok';
				var CANCEL_BUTTON_TEXT = 'Cancel';
				d = document;
				if(d.getElementById("modalContainer")) return;
				mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
				mObj.id = "modalContainer";
				mObj.style.height = d.documentElement.scrollHeight + "px";
				alertObj = mObj.appendChild(d.createElement("div"));
				alertObj.id = "alertBox";
				if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
				var leftMargin = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2;
				alertObj.style.left = (leftMargin-40) + "px";
				alertObj.style.visiblity="visible";
				/*h1 = alertObj.appendChild(d.createElement("h1"));
				h1.appendChild(d.createTextNode(ALERT_TITLE));*/
				msg = alertObj.appendChild(d.createElement("p"));
				//msg.appendChild(d.createTextNode(txt));
				msg.innerHTML = "Do you really want to delete the selected Product?";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','30px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','30px');
				
				
				
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					deleteProduct(index);
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
	}
</script>
	
	<div id="div_bill_section_four_hdr">
			<div class="content_body" >	
							<div class="content_table" > 
								<div class="content_header">
									<div class="cont_hdr_title">Product Information</div>
									<div class="form_buttons" >
										<a href="javascript:assignTotal();" >ADD</a>
										<s:a href="javascript: callfun(%{#counterIndex.index})">DELETE</s:a>
									</div>
								</div>	
								
								
							
							
							</div>
							
			</div>				
	</div>
	
	<s:include value="populateProducts.jsp"/> 
  	<s:include value="ProductDetails.jsp"/> 
	
	</div>
	
	<s:include value="hidden.jsp"/> 


</body>
</html>