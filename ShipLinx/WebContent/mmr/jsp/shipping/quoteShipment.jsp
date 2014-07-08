<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head>	
	<title><s:text name="user.form.title"/></title> 
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/style.css" />

<script type="text/javascript">  
  var contextPath = "<%=request.getContextPath()%>";  
</script>  
<SCRIPT language="JavaScript">
	  var userrole = "<%=request.getAttribute("USERROLE") %>";
	function preloader()
	{
		var pallet = "<%=request.getAttribute("pallet")%>";
		var pallet_opt1 = document.getElementById("hide_this_one_quote");
		var pallet_opt2 = document.getElementById("hide_this_two_quote");
		var pallet_opt3 = document.getElementById("hide_this_three_quote");
		if(pallet!='null')
		{
			if(navigator.appName.indexOf("Microsoft") > -1)
			{
				disableEnableComponents(pallet_opt1, 'block');
				disableEnableComponents(pallet_opt2, 'block');
				disableEnableComponents(pallet_opt3, 'table-row');
			}
			else 
			{
				disableEnableComponents(pallet_opt1, 'block');
				disableEnableComponents(pallet_opt2, 'block');
				disableEnableComponents(pallet_opt3, 'block');
			}
			resetwidths();
		}
		else
		{
			setwidths();
		}
		document.getElementById("loading-img-from").style.display = 'none'; 
    	document.getElementById("loading-img-to").style.display = 'none';         
    	
    	if(userrole!='null')//disabling the address fields for role 'customer_shipper'
	 	{
	 		//alert("disabling all the address fields of the table");
	 		var quote_add_table = document.getElementById("quote_address_tbl");
	 		//disabling all input fields of the table
	 		var ft_inputs = quote_add_table.getElementsByTagName('input');
	 		 for(var i=0; i<ft_inputs.length; ++i)
        		ft_inputs[i].disabled=true;
        	//disabling all select fields of the table
        	var ft_selects = quote_add_table.getElementsByTagName('select');
	 		 for(var i=0; i<ft_selects.length; ++i)
        		ft_selects[i].disabled=true;
	 	}  
    }

	function searchFromAddress()
	{
		var value= document.getElementById("addressFromId").value;
		var url="search.customer.from.address.action?searchId="+value+"&type=fromAddress";
		displaySearchAddressFrom(url);
	}
	
	function searchToAddress()
	{
		var value= document.getElementById("addressToId").value;
		var url="search.customer.to.address.action?searchId="+value+"&type=toAddress";
		displaySearchAddressFrom(url);
	}
		  
	function submitform(method)
	{
	 document.userform.action = "packageInformation.action?method="+method;
	 document.userform.submit();
	}     
	
	function getRates()
	{
		document.userform.action = "shipment.stageThree.quote.action";
	 	document.userform.submit();
	}
	
	

//	function update_packagetype(caller){
//	
//		ajax_Country=ajaxFunction();
//		ajax_Country.onreadystatechange=function()
//		  {
//			   if(ajax_Country.readyState==4)
//				{
	//			reponse=ajax_Country.responseText;
	//			js_stateid=document.getElementById("additionalServices");
	//			js_stateid.innerHTML= reponse;
	//			}
//		  }
		 
//			url="shipment.additionalservices.action?value="+caller.id;
//			ajax_Country.open("GET",url,true);
//			ajax_Country.send(this);
		
//	}
	    
	function submitform(method)
	{
	 document.packageform.action = "stageTwo.action?method="+method;
	 document.packageform.submit();
	} 
	<% 
    HttpSession httpSession = request.getSession();
    com.meritconinc.shiplinx.model.ShippingOrder shippingOrder=(com.meritconinc.shiplinx.model.ShippingOrder)httpSession.getAttribute("shippingOrder");
	%>
	var prevQuantity=<%=shippingOrder.getQuantity()%>;
 	function modifyQuantity(){
		var existingPackageArray = new Array();				
	    var i;
	    for(i=0;i<prevQuantity;i++){
	    	var obj=new Object();
		if(document.getElementById("packageTypes"+i)){
			if(document.getElementById("packageTypes"+i)){
    		  obj.type=document.getElementById("packageTypes"+i).value;
	      	}

		}
	    	obj.length=document.getElementById("shippingOrder.packageArray["+i+"].length").value;
	    	obj.width=document.getElementById("shippingOrder.packageArray["+i+"].width").value;
	    	obj.height=document.getElementById("shippingOrder.packageArray["+i+"].height").value;
	    	obj.weight=document.getElementById("shippingOrder.packageArray["+i+"].weight").value;
	    	obj.codAmount=document.getElementById("shippingOrder.packageArray["+i+"].codAmount").value;
	    	obj.insuranceAmount=document.getElementById("shippingOrder.packageArray["+i+"].insuranceAmount").value;
			var packType1=document.getElementById("packType").value;
			if(packType1=='type_pallet'){
			    if((document.getElementById("shippingOrder_packages_"+i+"__freightClass"))!= null){
					obj.freightClass=document.getElementById("shippingOrder_packages_"+i+"__freightClass").value;
				}
				if(document.getElementById("shippingOrder_packages_"+i+"__type")!= null){
					obj.type1=document.getElementById("shippingOrder_packages_"+i+"__type").value;
				}
	    	}
	    	obj.description=document.getElementById("shippingOrder.packageArray["+i+"].description").value;
	    	existingPackageArray.push(obj);
	    }

		var quantity= document.getElementById("quantity").value;
		var type= document.getElementById("packType").value;
		var pallet_opt1 = document.getElementById("hide_this_one_quote");
		var pallet_opt2 = document.getElementById("hide_this_two_quote");
		//var pallet_opt3 = document.getElementById("hide_this_three_quote");
		if(type=='type_env' || type=='type_pak')
			document.getElementById("quantity").value = 1;
		if(type=='type_pallet')
		{
			resetwidths();
			if(navigator.appName.indexOf("Microsoft") > -1)
			{
				disableEnableComponents(pallet_opt1, 'block');
				disableEnableComponents(pallet_opt2, 'block');
				//disableEnableComponents(pallet_opt3, 'block');
			}
			else 
			{
				disableEnableComponents(pallet_opt1, 'block');
				disableEnableComponents(pallet_opt2, 'block');
				//disableEnableComponents(pallet_opt3, 'block');
			}
		}
		else
		{
			setwidths();
			disableEnableComponents(pallet_opt1, 'none');
			disableEnableComponents(pallet_opt2, 'none');
			//disableEnableComponents(pallet_opt3, 'none');
		}
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("dimensions");
				js_stateid.innerHTML= reponse;
				var count=parseInt(prevQuantity);
				if(parseInt(quantity)<parseInt(prevQuantity)){
					count=parseInt(quantity);
				}
				for(i=0;i<count;i++){
				if(document.getElementById("packageTypes"+i)){
					if(document.getElementById("packageTypes"+i)){
						document.getElementById("packageTypes"+i).value=existingPackageArray[i].type;
						}
				}
					document.getElementById("shippingOrder.packageArray["+i+"].length").value=existingPackageArray[i].length;
					document.getElementById("shippingOrder.packageArray["+i+"].width").value=existingPackageArray[i].width;
					document.getElementById("shippingOrder.packageArray["+i+"].height").value=existingPackageArray[i].height;
					document.getElementById("shippingOrder.packageArray["+i+"].weight").value=existingPackageArray[i].weight;
					document.getElementById("shippingOrder.packageArray["+i+"].codAmount").value=existingPackageArray[i].codAmount;
					document.getElementById("shippingOrder.packageArray["+i+"].insuranceAmount").value=existingPackageArray[i].insuranceAmount;
					var packType1=document.getElementById("packType").value;
					if(packType1=='type_pallet'){
						if((document.getElementById("shippingOrder_packages_"+i+"__freightClass"))!= null){
							document.getElementById("shippingOrder_packages_"+i+"__freightClass").value=existingPackageArray[i].freightClass;
						}
						if(document.getElementById("shippingOrder_packages_"+i+"__type")!= null){
							document.getElementById("shippingOrder_packages_"+i+"__type").value=existingPackageArray[i].type1;
						}
					}
					document.getElementById("shippingOrder.packageArray["+i+"].description").value=existingPackageArray[i].description;
				}
					prevQuantity = document.getElementById("quantity").value;

				}
		  }
			url="dimensionInformation.action?quantity="+quantity+"&type="+type;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
		
		}
	
	window.onload = preloader;
	
	function setwidths()
	{
		//dynamic width implementation
		var choose = document.getElementById("choose_pckg");
		var choosetext = document.getElementById("choose_pckg_text");
		var quantity = document.getElementById("qty_pckg");
		var refcode = document.getElementById("refcode_pckg");
		var docsonly = document.getElementById("docs_pckg");
		var pckgtable = document.getElementById("pckg_tbl");
			//alert(choose);
			choose.width = '70px';
			choosetext.width = '170px';
			//alert(quantity);
			quantity.width = '70px';
			//alert(refcode);
			refcode.width = '70px';
			//alert(docsonly);
			docsonly.width = '120px';
			pckgtable.width = '1000px';
	}
	
	function resetwidths()
	{
		//dynamic width implementation
		var choose = document.getElementById("choose_pckg");
		var quantity = document.getElementById("qty_pckg");
		var refcode = document.getElementById("refcode_pckg");
		var docsonly = document.getElementById("docs_pckg");
		var pckgtable = document.getElementById("pckg_tbl");
		//alert(choose);
			choose.width = '150px';
			//alert(quantity);
			quantity.width = '140px';
			//alert(refcode);
			refcode.width = '140px';
			//alert(docsonly);
			docsonly.width = '130px';
			pckgtable.width = '1090px';
		
	}
	
	function disableEnableComponents(palletopts, val)
	{
		palletopts.style.display = val;
	}
	
	
</SCRIPT> 
	<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
    $('#wrapper_new').css('float','left');
  });
	</script>
<script>
// Start Autocomplete Script
		jQuery(function() {
		$("#tocity").autocomplete("<s:url action="getCitySuggest.action"/>", {extraParams:{addressType:'to'}});
		$("#tocity").result(function(event, data, formatted) {
                                var strSelectedCityZip = data[0];
								var strSelectedCity = "";
								var strSelectedZip = "";
								// If city, zip in proper format								
								if(strSelectedCityZip.split(",").length == 2)
								{
		                            strSelectedCity = strSelectedCityZip.split(",")[0];
		                            strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');

	                                strSelectedZip = strSelectedCityZip.split(",")[1];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                                
	                            }
								else if(strSelectedCityZip.split(",").length == 1)
								{
		                            strSelectedCity = strSelectedCityZip.split(",")[0];
		                            strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
									strSelectedZip = "";
								}
								else
								{
									// If mutliple commas found in city name
		                            strSelectedCity = strSelectedCityZip.substr(0, strSelectedCityZip.lastIndexOf(","));
		                            strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
									strSelectedZip = strSelectedCityZip.substr(strSelectedCityZip.lastIndexOf(",") + 1, strSelectedCityZip.length);
									strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
								}
                                $("#tocity").val(strSelectedCity);   
                                $("#toPostalCode").val(strSelectedZip);

				
                            });		
                            
		$("#toPostalCode").autocomplete("<s:url action="getZipSuggest.action"/>", {extraParams:{addressType:'to'}});
		$("#toPostalCode").result(function(event, data, formatted) {
                                var strSelectedZipCity = data[0];

								var strSelectedCity = "";
								var strSelectedZip = "";

								if(strSelectedZipCity.split(",").length == 2)
								{
	                                strSelectedZip = strSelectedZipCity.split(",")[0];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                                strSelectedCity = strSelectedZipCity.split(",")[1];
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
	                            }
								else if(strSelectedZipCity.split(",").length == 1)
								{
									// If Zip, City combination doesn't found
	                                strSelectedZip = strSelectedZipCity.split(",")[0];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
									strSelectedCity = "";
								}
								else
								{
									// If multiple commas found
	                                strSelectedZip = strSelectedZipCity.substr(0, strSelectedZipCity.indexOf(","));
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                                strSelectedCity = strSelectedZipCity.substr(strSelectedZipCity.indexOf(",") + 1, strSelectedZipCity.length);
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
								}
                                $("#tocity").val(strSelectedCity);   
                                $("#toPostalCode").val(strSelectedZip);   
				
                            });		
        

		$("#fromcity").autocomplete("<s:url action="getCitySuggest.action"/>", {extraParams:{addressType:'from'}});
		$("#fromcity").result(function(event, data, formatted) {
                                var strSelectedCityZip = data[0];
								var strSelectedCity = "";
								var strSelectedZip = "";

                                if(strSelectedCityZip.split(",").length == 2)
                                {
	                                strSelectedCity = strSelectedCityZip.split(",")[0];
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
	                                strSelectedZip = strSelectedCityZip.split(",")[1];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                            }
								else if(strSelectedCityZip.split(",").length == 1)
								{
									// If City, Zip combination doesn't found
	                                strSelectedCity = strSelectedCityZip.split(",")[0];
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
									strSelectedZip = "";
								}
								else
								{
									// If mutliple commas found in city name
		                            strSelectedCity = strSelectedCityZip.substr(0, strSelectedCityZip.lastIndexOf(","));
		                            strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
									strSelectedZip = strSelectedCityZip.substr(strSelectedCityZip.lastIndexOf(",") + 1, strSelectedCityZip.length);
									strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
								}	

                                $("#fromcity").val(strSelectedCity);   
                                $("#fromPostalCode").val(strSelectedZip);
                                
                                   
                            });		
                            
		$("#fromPostalCode").autocomplete("<s:url action="getZipSuggest.action"/>", {extraParams:{addressType:'from'}});
		$("#fromPostalCode").result(function(event, data, formatted) {
                                var strSelectedZipCity = data[0];
								var strSelectedCity = "";
								var strSelectedZip = "";

								if(strSelectedZipCity.split(",").length == 2)
								{
	                                strSelectedZip = strSelectedZipCity.split(",")[0];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                                strSelectedCity = strSelectedZipCity.split(",")[1];
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
	                            }
								else if(strSelectedZipCity.split(",").length == 1)
								{
									// If Zip, City combination doesn't found
	                                strSelectedZip = strSelectedZipCity.split(",")[0];
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
									strSelectedCity = "";
								}
								else
								{
									// If multiple commas found
	                                strSelectedZip = strSelectedZipCity.substr(0, strSelectedZipCity.indexOf(","));
	                                strSelectedZip = strSelectedZip.replace(/^\s+|\s+$/g, '');
	                                strSelectedCity = strSelectedZipCity.substr(strSelectedZipCity.indexOf(",") + 1, strSelectedZipCity.length);
	                                strSelectedCity = strSelectedCity.replace(/^\s+|\s+$/g, '');
								}


                                $("#fromcity").val(strSelectedCity);   
                                $("#fromPostalCode").val(strSelectedZip);   

                            });		

	});
	
	function backtoship()
	{
		document.userform.action  = "backToShipment.action?switch=true";
		document.userform.submit();
	}
// End Autocomplete Script		
</script>

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="shipment.stageThree.quote" name="userform" theme="simple" >
	<div id="div_ship_from_quote">
	<div class="content">
	<div class="content_body" >	
							<div class="content_table" id="quote_address_tbl"> 
								<div class="content_header">
									<div class="cont_hdr_title">Ship From</div>
									<div class="form_buttons">
										<a href="javascript:backtoship();">Switch to Ship Mode</a>	
									</div>
									
								</div>		
								<div class="cont_data_body">
									<div class="rows">	
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/>:</label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="shippingOrder.fromAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
	                  onchange="javascript:showShipFromState();"  id="firstBox" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
											<s:textfield size="20" key="shippingOrder.postalCode" onblur="javascript:getAddressSuggestFrom();" id="fromPostalCode" name="shippingOrder.fromAddress.postalCode"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.postalCode}"/>
	                  <img id="loading-img-from" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="shippingOrder.shipFromId.residential"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.fromAddress.residential}"  name="shippingOrder.fromAddress.residential"/>
											</div>
										</div>
										<div class="fields" id="fromCity">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" id="fromcity" key="shippingOrder.city" name="shippingOrder.fromAddress.city"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.city}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="stateid">
												<s:include value="../admin/shippingFromProvienceList.jsp"/>
											</div>
										</div>

									</div>	
								</div>
							</div>
						</div>
			</div>	
<div class="content">
			<div class="content_body" >	
							<div class="content_table" > 
								<div class="content_header">
									<div class="cont_hdr_title">Ship To</div>
									
								</div>		
								<div class="cont_data_body">
									<div class="rows">										
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01"  listKey="countryCode" listValue="countryName" name="shippingOrder.toAddress.countryCode" list="#session.CountryList" onchange="javascript:showShipToState();" headerKey="-1"  id="firstBox2" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.postalCode" name="shippingOrder.toAddress.postalCode" id="toPostalCode" onblur="javascript:getAddressSuggestTo();"  cssClass="text_02_tf" value="%{shippingOrder.toAddress.postalCode}"/>
					  <img id="loading-img-to" style="display:none;margin-top:-25px"  src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">
											</div>
										</div>
											<div class="fields">
											<label><mmr:message messageId="shippingOrder.shipToId.residential"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.toAddress.residential}"  name="shippingOrder.toAddress.residential"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.city"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.city" name="shippingOrder.toAddress.city" id="tocity" cssClass="text_02_tf" value="%{shippingOrder.toAddress.city}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.state"/></label>
											<div class="controls" id="stateid2">
												<s:include value="../admin/shippingToProvienceList.jsp"/>
											</div>
										</div>

									</div>	
								</div>
							</div>
						</div>
					</div>
				<div class="content">	
				<div class="content_body" >	
							<div class="content_table" id="pckg_tbl"> 
								<div class="content_header" id="choose_pckg">
									<div class="cont_hdr_title">Service</div>
								</div>		
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.choosePackage"/></label>
											<div class="controls" id="choose_pckg_text"><span>:</span>
												<s:select cssClass="text_01_combo_big" listKey="type" listValue="name" id="packType" name="shippingOrder.packageTypeId.type" 
			list="#session.listPackages" onchange="javascript:modifyQuantity();" headerKey="-1" theme="simple"/>
											</div>
										</div>
										
									<!--	<div class="fields" id="qty_pckg">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.quantity"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_small" name="shippingOrder.quantity" id="quantity" onchange="modifyQuantity()" cssStyle="width: 136px;" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35}"  cssClass="text_01_combo_small"></s:select>
											</div>
										</div>-->
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.scheduledShipDate"/></label>
											<div class="controls"><span>:</span>
												<s:textfield name="shippingOrder.scheduledShipDate_web" id="f_date_c" size="10"
													cssClass="text_02_tf" readonly="readonly" value="%{#session.shippingOrder.scheduledShipDate_web}" onClick="selectDate('f_date_c','f_trigger_c');"/>
											</div>
										</div>
										<div class="fields" id="docs_pckg">
											<label><mmr:message messageId="label.shippingOrder.docsOnly"/></label>
											<div class="controls"><span>:</span>
												 <s:checkbox name="shippingOrder.docsOnly"  value="%{#session.shippingOrder.docsOnly}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.satDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.satDelivery"  value="%{#session.shippingOrder.satDelivery}"/>
											</div>
										</div>
										<div class="fields" id="refcode_pckg">
											<label><s:label key="shippingOrder.refCode"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.refCode" name="shippingOrder.referenceCode" cssClass="text_02_tf" value="%{shippingOrder.referenceCode}"/>	
											</div>
										</div>

										<s:set name="cName" value="%{#session.shippingOrder.fromAddress.countryName}"/>
						<s:if test ='%{#cName ==  "CA"}'>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequiredCanada"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired" list="#{'1':'No','2':'Yes'}"  cssClass="text_01" ></s:select>
											</div>
										</div>
										</s:if>
										<s:else>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequired"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired"  list="#{'1':'No','2':'Del. Confirmation','3':'Signature','4':'Adult Signature'}"  cssClass="text_01" ></s:select>
											</div>
										</div>
										</s:else>
										<div class="fields">
										<label>Unit Of Measure</label>
          								 <div class="controls"><span>:</span>
          								 <s:select id="measure" name="shippingOrder.unitmeasure"  list="#session.UOM" listKey="unitOfMeasureId" listValue="unitOfMeasure" disabled="false"/></td>
          								 </div>
										</div>
					
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.dangerousGoods"/></label>
											<div class="controls"><span>:</span>
												<s:select id="dg_field" name="shippingOrder.dangerousGoods"  cssClass="text_01_combo_big" list="#{'0':'None','1':'Limited Quantity','2':'500 Kg Exemption','3':'Fully Regulated'}" disabled="false"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.dutiable.amount"/></label>
											<div class="controls"><span>:</span>
												<s:textfield id="shippingOrder.dutiableAmount" name="shippingOrder.dutiableAmount"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.dutiableAmount}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.holdForPickupRequired"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.holdForPickupRequired" value="%{#session.shippingOrder.holdForPickupRequired}" />
											</div>
										</div>												
										<div id="hide_this_one_quote" style="display:none">
										<div class="fields" id="hide_this_one_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.tradeShowPickup"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.tradeShowPickup" value="%{#session.shippingOrder.tradeShowPickup}" />
											</div>
										</div>
										<div class="fields" id="hide_this_one_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.tradeShowDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.tradeShowDelivery"  value="shippingOrder.tradeShowDelivery"/>
											</div>
										</div>
										<div class="fields" id="hide_this_one_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.insidePickup"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.insidePickup"  value="shippingOrder.insidePickup"/>
											</div>
										</div>
										<div class="fields" id="hide_this_two_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentPickup"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.appointmentPickup" value="%{#session.shippingOrder.appointmentPickup}" />
											</div>
										</div>
										<div class="fields" id="hide_this_two_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.appointmentDelivery"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.appointmentDelivery"  value="shippingOrder.appointmentDelivery"/>
											</div>
										</div>
										<div class="fields" id="hide_this_two_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.fromTailgate"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.fromTailgate"  value="shippingOrder.fromTailgate"/>
											</div>
										</div>
										<div class="fields" id="hide_this_two_quote">
											<label><mmr:message messageId="label.shippingOrder.additionalServices.toTailgate"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox name="shippingOrder.toTailgate" value="%{#session.shippingOrder.toTailgate}" />
											</div>
										</div>
										</div>
									</div>	
								</div>
							</div>
						</div>
				</div>		
</div>


<div id="pckg_choose_quote"></div>
<div id="pckg_panel_quote">

</div>

<div id="pckg_div_quote" >
<s:include value="packageDimention.jsp"/>
</div>

<div class="content">
<div class="content_body" >	
	<div class="content_table" style=" border-top:1px solid #555555; padding:5px 0px;">
		<div class="form_buttons id="" style="width:300px;">
			<div class="form_buttons" id="img_get_rates_quote">
				<a href="javascript:getRates()" onclick="return (validateOrder(3,1))"style="">Get Rates</a>	
			</div>
		</div>
	</div>
</div>	
</div>	
<!--  <div id="res_tbl_end_quote"></div>-->
		
	</s:form> 

</body>
</html>