<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html> 	
<head>
    <sx:head/>
    <title><s:text name="user.form.title"/></title> 
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>

<script type="text/javascript">  
  var contextPath = "<%=request.getContextPath()%>";
  var userrole = "<%=request.getAttribute("USERROLE") %>";
  window.onload = function()
  {
	  	var packaging_type = document.getElementById("packType").value;
  		var pallet_opt1 = document.getElementById("hide_this_one");
		var pallet_opt2 = document.getElementById("hide_this_two");
		var reference = document.getElementById("reference").value;
				if(reference=="true"){
					$(this).css('display','none');
					$('.referenceClose').hide();
					$('.referenceOpen,.referenceBody').css('display','block');
					
				}else{
					$(this).css('display','none');
					$('.referenceClose').css('display','block');
					$('.referenceOpen,.referenceBody').css('display','none');
				}
		if(packaging_type=='type_pallet')
		{
			if(navigator.appName.indexOf("Microsoft") > -1)
			{
				disableEnableComponents(pallet_opt1, 'block');
				disableEnableComponents(pallet_opt2, 'block');
			}
			else 
			{
				disableEnableComponents(pallet_opt1, 'table-row');
				disableEnableComponents(pallet_opt2, 'table-row');
			}
		}
  	  var add1 = document.getElementsByName("shippingOrder.fromAddress.address1");
	  var anchorfrom = getElementsByClassName("edit_shipfrom");
	  var lblto = document.getElementById("labelto");
	   if(add1[0].value.length <1)
	   {
	   		document.getElementById("div_ship_from").style.display = 'block';
	   		document.getElementById("labelfrom").style.display = 'none';
	   		anchorfrom[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">';
	   }
	   else
	   {
	   		document.getElementById("div_ship_from").style.display = 'none';
	   		document.getElementById("labelfrom").style.display = 'block';
	   		anchorfrom[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/show.png">';
	   }	
	   lblto.innerHTML = '&nbsp;';
	   
//	   if(document.getElementById("qkship_checkbox").checked)
	 //  {
		   	document.getElementById("qkship_div_panel").style.display = 'block';
		   document.getElementById("qkship_div_panel2").style.display = 'none';
//		   	document.getElementById("get_rates_td").innerHTML = "<a href='javascript:submitQuickShip()' onclick='return (validateOrder(3,1))'><img src='<s:url value='/mmr/images/quick_ship_btn.png' includeContext='true' />' border='0'></a>";
	//   }
	   if(document.getElementById('firstBoxCarrier').value > 0)
		{
	 		if(navigator.appName.indexOf("Microsoft") > -1)
				document.getElementById("qkship_div_panel2").style.display="block";
			else
	 			document.getElementById("qkship_div_panel2").style.display="table-row";
	 	}
	 	changeOtherFields(document.getElementById('billToType').value);
	 	//alert("userrole---->"+userrole);
	 	if(userrole!='null')//disabling the address fields for role 'customer_shipper'
	 	{
	 		//alert("disabling all the address fields of the table");
	 		var from_add_table = document.getElementById("from_add_inner_table");
	 		//disabling all input fields of the table
	 		var f_inputs = from_add_table.getElementsByTagName('input');
	 		 for(var i=0; i<f_inputs.length; ++i)
        		f_inputs[i].disabled=true;
        	//disabling all select fields of the table
        	var f_selects = from_add_table.getElementsByTagName('select');
	 		 for(var i=0; i<f_selects.length; ++i)
        		f_selects[i].disabled=true;
	 		//alert(from_add_table);
	 		var to_add_table = document.getElementById("to_add_inner_table");
	 		//disabling all input fields of the table
	 		var t_inputs = to_add_table.getElementsByTagName('input');
	 		 for(var i=0; i<t_inputs.length; ++i)
        		t_inputs[i].disabled=true;
        	//disabling all select fields of the table
        	var t_selects = to_add_table.getElementsByTagName('select');
	 		 for(var i=0; i<t_selects.length; ++i)
        		t_selects[i].disabled=true;
	 		//alert(to_add_table);
	 		//alert(document.getElementById("autoaddresst"));
	 		document.getElementById("autoaddresst").disabled = true;
	 		//alert(document.getElementById("autoaddressf"));
	 		document.getElementById("autoaddressf").disabled = true;
	 	}
  }  	
</script>  

<SCRIPT language="JavaScript">

var dojos = getElementsByClassName("dojoComboBox");


//	function update_packagetype(){
//	var type= document.getElementById("packType").value;
//	alert(type);
//	ajax_Country=ajaxFunction();
//	ajax_Country.onreadystatechange=function()
//	  {
//		   if(ajax_Country.readyState==4)
//			{
//			reponse=ajax_Country.responseText;
//			js_stateid=document.getElementById("additionalServices");
//			js_stateid.innerHTML= reponse;
//			}
//	  }
//		url="<%=request.getContextPath()%>/shipment.additionalservices.action?type="+type;
//		ajax_Country.open("GET",url,true);
//		ajax_Country.send(this);
	
//	}

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
    	obj.type=document.getElementById("packageTypes"+i).value;
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
	var pallet_opt1 = document.getElementById("hide_this_one");
	if(type=='type_env' || type=='type_pak')
	{
		document.getElementById("quantity").value = 1;
		//document.getElementById('dg_field').selectedIndex=0;
		//document.getElementById("dg_field").disabled = true;
	}
	else if(type=='type_pallet')
	{
		if(navigator.appName.indexOf("Microsoft") > -1)
		{
			disableEnableComponents(pallet_opt1, 'block');
		}
		else 
		{
			disableEnableComponents(pallet_opt1, 'table-row');
		}
		//document.getElementById("dg_field").disabled = false;
	}
	else
	{
		disableEnableComponents(pallet_opt1, 'none');
		//document.getElementById("dg_field").disabled = false;
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
										document.getElementById("packageTypes"+i).value=existingPackageArray[i].type;
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
	
	function disableEnableComponents(palletopts, val)
	{
		palletopts.style.display = val;
	}
	
	
	function searchFAddress()
	{
		var autoCompleter = dojo.widget.byId("autoaddress");
    // alert("autoCompleter is::"+autoCompleter); //get this value
     //key (in the states example above, "AL")
     var key = autoCompleter.getSelectedKey();
     //alert("Key is::"+key);
     
     //value (in the states example above, "Alabama")
     var value = autoCompleter.getSelectedValue();
     //alert("Value is::"+value);
     
      var value = autoCompleter.getText();
     //alert("Text is::"+value);
	}
	
	function searchToAddress()
	{		
		var value= document.getElementById("addressToId2").value;
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("addressTo");
				js_stateid.innerHTML= reponse;
			

				}
		  }
			var url="<%=request.getContextPath()%>/select.customer.from.address.action?addressid="+value+"&type=toAddress";
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);

	}
	function submitform(method)
	{
		document.userform.action = "shipment.stageThree.action?method=" + method;
	 	document.userform.submit();
	} 	 
	function getRates()
	{
		var myParam = location.search.split('customsinvoice=')[1];
				if(myParam=="true"){
				document.userform.action = "shipment.stageThree.action?customs="+myParam;
	 	document.userform.submit();
				}else{
							document.userform.action = "shipment.stageThree.action?customs="+myParam;
						 	document.userform.submit();
						}
	} 	
	function quickShip()
	{   
	    var toZip=document.getElementById("toPostalCode").value;
	    var fromZip=document.getElementById("fromPostalCode").value
	    if(toZip !=null && fromZip!=null ){
	    var carrierId = document.getElementById("firstBoxCarrier").value;
		var serviceId = document.getElementById("secondBox").value;
		var quick = document.getElementById("qkship_fastest").checked;
		if(carrierId==10 && serviceId>0){
	    var key="1";	
		document.userform.action = "shipment.stageThree.action?key="+key;
	 	document.userform.submit();
		}else if(carrierId!= 10){
			alert("Quick Ship Only for Generic Carrier");
		}else{
		   alert("Please select Carrier and Service");
		}
		}else{
	    	alert("Please Fill all mandatory fields");
	    }
		 	
} 	 
	function updateShipment()
	{
		var custid = document.getElementsByName("shippingOrder.customerId")[0].value;
		var webcustid = document.getElementsByName("shippingOrder.webCustomerId")[0].value;
//		alert('WebCustID:' + webcustid);
		
//		if (webcustid == 'shippingOrder.webCustomerId' || webcustid == 'undefined') {
//			document.getElementsByName("shippingOrder.webCustomerId")[0].value = custid;
//			alert('shippingOrder.webCustomerId:' + document.getElementsByName("shippingOrder.webCustomerId")[0].value);
//		}
		
		if (custid != webcustid) {
			if(confirm("Are you sure, you want to change customer from " + custid + " to " + webcustid + " ?")) {
				document.userform.action = "shipment.update.action";
			 	document.userform.submit();
			}
		} else {
			document.userform.action = "shipment.update.action";
		 	document.userform.submit();
		}
	}	
	function saveCurrentShipment()
	{
		document.userform.action = "save.current.shipment.action";
	 	document.userform.submit();
	}	
	function setCustomer()
	{
		var autoCompleter = dojo.widget.byId("customerSelected");
		var value = autoCompleter.getSelectedKey();
//		autoCompleter.setSelectedKey("4134");
//		alert(value);	
		document.userform.action = "shipment.setcustomer.action?customerId=" + value;
	 	document.userform.submit();

	} 
	
	function showPackage(id)
	{
		var tabContainer = dojo.widget.byId("accordion");
     	tabContainer.selectTab(id);		
	}
	
		function showAddToEdit()
		{
		//alert("Inside showAddToEdit()");	
		var anchorto = getElementsByClassName("edit_shipto");
		var shipto = document.getElementById("div_ship_to");
		var lblto = document.getElementById("labelto");
		//alert(anchorto[0].innerHTML);
		if(anchorto[0].innerHTML != '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">')
		{
			shipto.style.display = 'block';
			//lblto.style.display = 'none';
			lblto.innerHTML = '&nbsp;';
			anchorto[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">';
		}
		else
		{
			shipto.style.display = 'none';
			lblto.style.display = 'block';
			anchorto[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/show.png">';
			setLabelvalues('to');
		}		
	}
	
	function showAddFromEdit()
	{	
		//alert("Inside showAddFromEdit()");	
		var anchorfrom = getElementsByClassName("edit_shipfrom");
		var shipfrom = document.getElementById("div_ship_from");
		var lblfrom = document.getElementById("labelfrom");
		//alert(anchorfrom[0].innerHTML);
		if(anchorfrom[0].innerHTML != '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">')
		{
			shipfrom.style.display = 'block';
			lblfrom.style.display = 'none';
			//lblfrom.innerHTML = '&nbsp;';
			anchorfrom[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">';
		}
		else
		{
			shipfrom.style.display = 'none';			
			lblfrom.style.display = 'block';
			anchorfrom[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/show.png">';	
			setLabelvalues('from');
		}			
	}
	function setLabelvalues(ft) //from or to?
	{
		var dojos = getElementsByClassName("dojoComboBox");		
		if(ft=='from')
		{
			var labelfrom= "";
			var ctry = document.getElementById("firstBox");
			//alert("ctry:::"+ctry);
			var lblfrom = document.getElementById("labelfrom");
			//alert("lblfrom:::"+lblfrom);
			var province = document.getElementsByName("shippingOrder.fromAddress.provinceCode")[0];
			//alert("province:::"+province);
			//labelfrom = dojos[0].value+", "+document.getElementsByName("shippingOrder.fromAddress.address1")[0].value+" "+document.getElementsByName("shippingOrder.fromAddress.address2")[0].value+", "+document.getElementsByName("shippingOrder.fromAddress.city")[0].value+", "+province.options[province.selectedIndex].value+", "+document.getElementById("fromPostalCode").value+", "+ctry.options[ctry.selectedIndex].value;
			 labelfrom = document.getElementById("customerautocompletes").value+", "+document.getElementsByName("shippingOrder.fromAddress.address1")[0].value+" "+document.getElementsByName("shippingOrder.fromAddress.address2")[0].value+", "+document.getElementsByName("shippingOrder.fromAddress.city")[0].value+", "+province.options[province.selectedIndex].value+", "+document.getElementById("fromPostalCode").value+", "+ctry.options[ctry.selectedIndex].value;
			lblfrom.innerHTML= labelfrom;			
		}
		else
		{
			var labelto= "";
			var ctry = document.getElementById("firstBox2");
			var lblto = document.getElementById("labelto");
			var province = document.getElementsByName("shippingOrder.toAddress.provinceCode")[0];
		
			labelto = document.getElementById("customerautocompto").value+", "+document.getElementsByName("shippingOrder.toAddress.address1")[0].value+" "+document.getElementsByName("shippingOrder.toAddress.address2")[0].value+", "+document.getElementsByName("shippingOrder.toAddress.city")[0].value+", "+province.options[province.selectedIndex].value+", "+document.getElementById("toPostalCode").value+", "+ctry.options[ctry.selectedIndex].value;
			lblto.innerHTML= labelto;		
		}	
	}
	
	function toShoworHide(checked)
	{
		if(checked)
		{
			//alert("Show");
			document.getElementById("pickup_div_panel").style.display = 'block';			
		}
		else
		{
			//alert("Hide");
			document.getElementById("pickup_div_panel").style.display = 'none';			
		}		
	}
	
	function toShoworHideQuickShip(checked)
	{
		if(checked)
		{
			document.getElementById("qkship_div_panel").style.display = 'block';	
			document.getElementById("get_rates_td").innerHTML = "<a href='javascript:submitQuickShip()' onclick='return (validateOrder(3,1))'><img src='<s:url value='/mmr/images/quick_ship_btn.png' includeContext='true' />' border='0'></a>";	
		}
		else
		{
			//alert("Hide");
			document.getElementById("qkship_div_panel").style.display = 'none';	
			document.getElementById("get_rates_td").innerHTML ="<a href='javascript:getRates()' onclick='return (validateOrder(3,1))'><img src='<s:url value='/mmr/images/get_rates_btn.png' includeContext='true' />' border='0'></a>";			
		}		
	}
	
	function submitQuickShip()
	{
		//alert(document.getElementById("firstBoxCarrier").value);
		//alert(document.getElementById("secondBox").value);
		//alert(document.getElementById("qkship_fastest").checked);
		//alert(document.getElementById("qkship_cheapest").checked);
		if(document.getElementById("firstBoxCarrier").value <= 0 && document.getElementById("secondBox").value <= 0 && !document.getElementById("qkship_fastest").checked && !document.getElementById("qkship_cheapest").checked)
			alert("Please select atleast Fastest or Cheapest method if you dont wish to select the Carrier or Service");
		else
		{
			//alert("done");
			document.userform.action = "shipment.stageThree.action";
	 		document.userform.submit();
		}
	}
	
	dojo.event.topic.subscribe("/valueChangedTo", function(value, key, text, widget){
	//alert("changedto::"+value);
	setAddress('toAddress');
	//fetch only the company name and set it to the autocompleter after ajax
	var companyto = value;
	companyto = companyto.substring(0,companyto.indexOf(","));
	//alert(companyto);
	var dojost = getElementsByClassName("dojoComboBox");
	dojost[2].value = companyto;
	document.getElementById("shippingOrder.toAddress.abbreviationName").value=companyto;
		});
		
	dojo.event.topic.subscribe("/valueChangedFrom", function(value, key, text, widget){
	//alert("changedfrom::"+value);
	setAddress('fromAddress');
	//fetch only the company name and set it to the autocompleter after ajax
	var companyfrom = value;
	companyfrom = companyfrom.substring(0,companyfrom.indexOf(","));
	//alert(companyfrom);
	var dojosf = getElementsByClassName("dojoComboBox");
	dojosf[0].value = companyfrom;
	document.getElementById("shippingOrder.fromAddress.abbreviationName").value=companyfrom;
		});	
		
	function setAddress(type)
	{	
		var autoCompleter="";
		var value =0;
		//alert(type);
		if(type=='toAddress')
		{
			autoCompleter = dojo.widget.byId("autoaddresst");
			value = autoCompleter.getSelectedKey();
			//alert("To::"+value);
			//ajax call for setting the address
			ajax_ChangeTo=ajaxFunction();
			ajax_ChangeTo.onreadystatechange=function()
		  	{
				   if(ajax_ChangeTo.readyState==4)
					{
					reponse=ajax_ChangeTo.responseText;
					js_stateid=document.getElementById("toAdd_inner");
					js_stateid.innerHTML= reponse;
					}
		 	}
			var url="<%=request.getContextPath()%>/admin/selectShippingAddress.action?addressid="+value+"&type="+type;
			ajax_ChangeTo.open("GET",url,true);
			ajax_ChangeTo.send(this);
			//end of ajax call --
		}
		else
		{
			autoCompleter = dojo.widget.byId("autoaddressf");
			value = autoCompleter.getSelectedKey();
			//alert("From::"+value);
			//ajax call for setting the address
			ajax_ChangeFrom=ajaxFunction();
			ajax_ChangeFrom.onreadystatechange=function()
		  	{
				   if(ajax_ChangeFrom.readyState==4)
					{
					reponse=ajax_ChangeFrom.responseText;
					js_stateid=document.getElementById("fromAdd_inner");
					js_stateid.innerHTML= reponse;
					}
		  	}
			var url="<%=request.getContextPath()%>/admin/selectShippingAddress.action?addressid="+value+"&type="+type;
			ajax_ChangeFrom.open("GET",url,true);
			ajax_ChangeFrom.send(this);
	//end of ajax call --
		}
		//document.userform.action = "selectShippingAddress.action?addressid="+value+"&type="+type;
		//document.userform.submit();

	}
	
	
	function assignCompany(val)
	{
	//alert(val);
		var dojos = getElementsByClassName("dojoComboBox");
		//alert(dojos);
		//alert("Company:::"+dojos[0].value);
		if(val=='to')
		{
			var ccvalt = dojos[2].value;
			//alert("TO:"+ccvalt);
			document.getElementById("shippingOrder.toAddress.abbreviationName").value=ccvalt;
		}
		else
		{
			var ccvalf = dojos[0].value;
			//alert("FROM:"+ccvalf);
			document.getElementById("shippingOrder.fromAddress.abbreviationName").value=ccvalf;
		}
	}
	
	function changeCustomer()
	{
		//alert(customerval);
		var customerId=document.getElementById("custId").value;
		document.userform.action = "shipment.setcustomer.action?customerId=" + customerId;
	 	document.userform.submit();
	}
	
	
	function lookup()
	{
		//alert("Looking up...");
		if(document.getElementById("lookupid").value=="" || document.getElementById("lookupid").value.length == 0)
			alert("Please enter Reference value");
		else
		{
			document.userform.action = "list.reference.order.action";
	 		document.userform.submit();
	 	}
	}
	 function showServices() {
	 //alert(document.getElementById('firstBoxCarrier').value);
	 	if(document.getElementById('firstBoxCarrier').value==-1)
	 		document.getElementById("qkship_div_panel2").style.display="none";
	 	else
	 	{
	 		if(navigator.appName.indexOf("Microsoft") > -1)
				document.getElementById("qkship_div_panel2").style.display="block";
			else
	 			document.getElementById("qkship_div_panel2").style.display="table-row";
	 	}
			ajax_Service=ajaxFunction();
			ajax_Service.onreadystatechange=function()
			  {
				   if(ajax_Service.readyState==4)
					{
					reponse=ajax_Service.responseText;
					js_stateid=document.getElementById("stateid_ship");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBoxCarrier');
			  url="<%=request.getContextPath()%>/carrier.services.list.action?value="+firstBox.value+"&quickship=true";
				//param="objName=ref_state&country_id="+country_id;
			  	ajax_Service.open("GET",url,true);
			  	ajax_Service.send(this);
		} // End function showState()	
		
</SCRIPT> 
	<script>
	
	$(window).load(function() {
 var wndo = window.innerHeight;
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
	<script>
		$(document).ready(function(){
			
		$('#get_rates_td').click(function(){
				$('#loader').css('display','block');
				$('#loaderImg').css('display','block');
				
		});
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
// End Autocomplete Script		

	function changeOtherFields(val)
	{
		//alert('|'+val+'|');
		if(val == "Third Party" || val == "Collect")
		{
			//alert('enabling...');
			document.getElementById("billToANumber").disabled = false;
			document.getElementById("billToACountry").disabled = false;
			document.getElementById("billToPostalCode").disabled = false;
		}
		else
		{
			//alert('disabling...');
			document.getElementById("billToANumber").value = '';
			document.getElementById("billToACountry").value = 'CA';
			document.getElementById("billToPostalCode").value = '';
			document.getElementById("billToANumber").disabled = true;
			document.getElementById("billToACountry").disabled = true;
			document.getElementById("billToPostalCode").disabled = true;	
		}
	}
	
	function backToShipment(){
		document.userform.action  = "backToShipment.action?switch=true";
		document.userform.submit();
	}
	
</script>
<style>
	.edit_shipfrom img,.edit_shipto img,.referenceOpen img,.referenceClose img { width:25px; height:25px; }

</style>
<script>
	$(document).ready(function(){ 
		$('.referenceOpen,.referenceBody').css('display','none');
		$('.referenceClose').click(function(){
			$(this).css('display','none');
			$('.referenceOpen,.referenceBody').css('display','block');
		});
		$('.referenceOpen').click(function(){
			$(this).css('display','none');
			$('.referenceClose').css('display','block')
			$('.referenceOpen,.referenceBody').css('display','none');
		});
	});
</script>
<script type="text/javascript"> 
					function searchfrom()
	{
		//alert(customerval);
		ajax_ChangeTo=ajaxFunction();
			ajax_ChangeTo.onreadystatechange=function()
		  	{
				   if(ajax_ChangeTo.readyState==4)
					{
					reponse=ajax_ChangeTo.responseText;
					js_stateid=document.getElementById("ship_from");
					js_stateid.innerHTML= reponse;
					}
		 	}
			  var addressId=document.getElementById("custId").value;
			  var url = "shipment.searchfrom.action?addressId=" + addressId;
			ajax_ChangeTo.open("GET",url,true);
			ajax_ChangeTo.send(this);		
	}
	
	 function searchto()
	{
		ajax_ChangeTo=ajaxFunction();
			ajax_ChangeTo.onreadystatechange=function()
		  	{
				   if(ajax_ChangeTo.readyState==4)
					{
					reponse=ajax_ChangeTo.responseText;
					js_stateid=document.getElementById("ship_to");
					js_stateid.innerHTML= reponse;
					}
		 	}
			var addressId=document.getElementById("custIdto").value;
			var url = "shipment.searchto.action?addressId=" + addressId;
	 	
			ajax_ChangeTo.open("GET",url,true);
			ajax_ChangeTo.send(this);	
	} 
	</script> 
	<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
</div>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="shipment.stageThree" cssClass="form" name="userform" id="userform" theme="simple" >
<div class="form-container" >
	<div class="newshipment" id="contenttbl">
	<s:include value="order_SearchQuickAddress.jsp"/>
	<div class="content">	
		<div class="content_body" >
						<div class="form-container" style="background-color:#E7E7E7;" >
							<div class="content_table" id="contenttbl">
								<div class="content_header" style="margin-top:1px; margin-bottom:1px;">
									<div class="cont_hdr_title"><mmr:message messageId="label.search.reference"/></div>
									<div class="cont_hdrtitle_l" style="width:650px">( REFERENCE AVAILABLE ONLY FROM PAST SHIPMENTS OR IMPORTED SHIMPMENTS WITHIN THIS SYSTEM )</div>
									<div class="form_buttons">
										<a class="referenceOpen" onclick="javascript:return false;" style="padding:0px; background-color:transparent;" href="">
										<img src="<%=request.getContextPath()%>/mmr/images/hide.png"/>
											
										</a>
										<a class="referenceClose" onclick="javascript:return false;" style="padding:0px; background-color:transparent;" href="">
										<img src="<%=request.getContextPath()%>/mmr/images/show.png"/>
											
										</a>
									</div>
								</div>
								<div class="cont_data_body referenceBody borderLeftRight">
									<div class="rows">
										
										<div class="fields">
											<label><mmr:message messageId="label.reference"/> </label>
											<div class="controls"><span>:</span>
											<s:hidden name="reference" id="reference" value="%{customer.reference}"/>
												<s:textfield id="lookupid" size="20" key="shippingOrder.referenceValue" name="shippingOrder.referenceValue" onblur="Javascript: lookup();"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					<div class="content_table">
								<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.shipFrom"/>:</div>
									<div class="cont_hdrtitle_L" id="labelfrom" style="width: 344; overflow:hidden;white-space:nowrap;text-overflow: ellipsis; margin-left: 10;display: block; ">
				<s:property value="%{shippingOrder.fromAddress.abbreviationName}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.address1}"/>&nbsp;<s:property value="%{shippingOrder.fromAddress.address2}"/>,&nbsp;
				<s:property value="%{shippingOrder.fromAddress.city}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.provinceCode}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.postalCode}"/>,&nbsp;<s:property value="%{#session.shippingOrder.fromAddress.countryCode}"/></div>
									<div class="form_buttons">
									<a href="javascript:showAddFromEdit();" style="padding:0px; background-color:transparent;" class="edit_shipfrom" ><img src="<%=request.getContextPath()%>/mmr/images/hide.png"/></a>
									</div>
								</div>
							</div>
						</div>
					</div>	
		<s:include value="order_SelectedFromAddress.jsp"/>	
			<div class="content">
				<div class="content_body">
					<div class="content_table">
								<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.shipTo"/>:</div>
									<div class="cont_hdrtitle_L" id="labelto" style="width: 344;margin-left: 10;display: block;">
				<s:property value="%{shippingOrder.toAddress.abbreviationName}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.address1}"/>&nbsp;<s:property value="%{shippingOrder.toAddress.address2}"/>,&nbsp;
					<s:property value="%{shippingOrder.toAddress.city}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.provinceCode}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.postalCode}"/>,&nbsp;<s:property value="%{#session.shippingOrder.toAddress.countryCode}"/></div>
									<div class="form_buttons">
										<a href="javascript: showAddToEdit();"style="padding:0px; background-color:transparent;" class="edit_shipto"><img src="<%=request.getContextPath()%>/mmr/images/hide.png"/></a>
									</div>
								</div>
					</div>
					</div>
	</div>					
	<s:include value="order_SelectedToAddress.jsp"/>
		
	
	<s:include value="shipping_packages.jsp"/>
	<div id="pckg_div_quote">
	<s:include value="packageDimention.jsp"/>
	</div>
	
	<div class="content">
	<div id="pickup_div_hdr">
	<div class="content_body">
	<div class="content_table">
		<div class="content_header">
		<div class="cont_hdr_title"><mmr:message messageId="label.like.pickup.schedule"/>
	<s:checkbox name="shippingOrder.pickup.pickupRequired"  value="%{shippingOrder.pickup.pickupRequired}" id="pickup_checkbox" onclick="toShoworHide(this.checked);"/></div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div class="content">
	<div id="pickup_div_panel" style="display:none;">
<div class="content_body">	
							<div class="content_table">
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.pickup.readytime"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{shippingOrder.pickup.readyHour}" name="shippingOrder.pickup.readyHour" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}" cssStyle="width: 46px; " class="text_01" ></s:select>
												<s:select value="%{shippingOrder.pickup.readyMin}" name="shippingOrder.pickup.readyMin" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}" cssStyle="width: 46px;" class="text_01" ></s:select>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.pickup.closetime"/></label>
											<div class="controls"><span>:</span>
														<s:select value="%{shippingOrder.pickup.closeHour}" name="shippingOrder.pickup.closeHour" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}" cssStyle="width: 46px; " class="text_01" ></s:select>

												<s:select value="%{shippingOrder.pickup.closeMin}" name="shippingOrder.pickup.closeMin" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}" cssStyle="width: 46px;" class="text_01" ></s:select>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.pickup.preferred.location"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{shippingOrder.pickup.pickupLocation}" name="shippingOrder.pickup.pickupLocation" list="{'Shipping','Back Door','Downstairs','Front Door','Garage','Guard House','Mail Room','Office','Receiving','Reception','Side Door','Upstairs'}"  class="text_01" ></s:select>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.pickup.reference"/></label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="shippingOrder.pickup.pickupReference" name="shippingOrder.pickup.pickupReference"  cssClass="text_02_tf" value="%{shippingOrder.pickup.pickupReference}"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pickup.instructions"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  rows="1" key="shippingOrder.pickup.instructions" name="shippingOrder.pickup.instructions"  cssClass="text_02"/>
											</div>
										</div>
									</div>	
								</div>
							</div>
						</div>
	</div>
	</div>
	<!--Start: Implementation of Quick Ship UI -->
	<div class="content">
	<div id="qkship_div_hdr">
	<div class="content_body">
<div class="content_table" style="margin-top:1px;">
			<div class="content_header">
				<div class="cont_hdr_title"><mmr:message messageId="label.quick.ship"/>?</div>
				<div class="cont_hdrtitle_l" style="width:514px">(PLEASE SELECT A METHOD TO CREATE A SHIPMENT WITHOUT FIRST GETTING RATES)</div>
		</div>
		</div>
	</div>
	</div>
	<div id="qkship_div_panel">
	<div class="content_body">	
							<div class="content_table">
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fieldss">
											<label><mmr:message messageId="label.fastest.method"/></label>
											<div class="controlscheck"><span>:</span>
												<s:checkbox name="shippingOrder.fastestMethod"  value="%{shippingOrder.fastestMethod}" id="qkship_fastest"/>
											</div>
										</div>
										
										<div class="fieldss">
											<label><mmr:message messageId="label.cheapest.method"/></label>
											<div class="controlscheck"><span>:</span>
												<s:checkbox name="shippingOrder.cheapestMethod"  value="%{shippingOrder.cheapestMethod}" id="qkship_cheapest"/>
											</div>
										</div>
										
										<div class="fields" style=" padding-left:24px;">
											<label><mmr:message messageId="label.track.carrier"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_big" cssStyle="" listKey="id" listValue="name" headerKey="-1" headerValue="ANY" name="shippingOrder.carrierId_web" list="#session.CARRIERS" onchange="javascript:showServices();" 
									id="firstBoxCarrier" theme="simple"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.markup.service"/></label>
											<div class="controls" id="stateid_ship"><span>:</span>
												<s:select cssClass="text_01_combo_big"  listKey="id" listValue="name" name="shippingOrder.serviceId_web" list="#session.SERVICES" 
										headerKey="-1" headerValue="ANY" id="secondBox" theme="simple" value="%{shippingOrder.serviceId_web}"/>
											</div>
										</div>
										
									
									<div id="qkship_div_panel2" style="display:none; width:auto; float:right; width:666px; ">
											
											<div class="fields">
											<label><mmr:message messageId="label.customer.accountNumber"/> </label>
											<div class="controls"><span>:</span>
												<s:textfield id="billToANumber" name="shippingOrder.billToAccountNum" cssClass="text_02_tf_medium" maxlength="10" disabled="true"/>
											</div>
											</div>
											
											<div class="fieldsll">
												<label><mmr:message messageId="label.bill.to"/></label>
												<div class="controls"><span>:</span>
													<s:select id="billToType" list="{'Soluship Acct','Third Party','Collect'}" name="shippingOrder.billToType"  onchange="changeOtherFields(this.value);"/>
												</div>
											</div>
											
											<div class="fields">
											<label><mmr:message messageId="label.country.account"/></label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" cssStyle="" listKey="countryCode" listValue="countryName" name="shippingOrder.billToAccountCountry" headerKey="-1"  list="#session.CountryList" 
	                  id="billToACountry" theme="simple" disabled="true"/>
											</div>
											</div>
											<div class="fieldsll">
												<label><mmr:message messageId="label.account.zipPostalCode"/></label>
												<div class="controls"><span>:</span>
													<s:textfield id="billToPostalCode" name="shippingOrder.billToAccountPostalCode" cssClass="text_02_tf" maxlength="10"  disabled="true"/>
												</div>
											</div>
											
									</div>
									
									</div>
								</div>
							</div>
						</div>
	</div>
</div>	
	<!-- End: Implementation of Quick Ship UI-->
	<div class="content">
		<s:if test="%{(#session.ROLE.contains('busadmin') && shippingOrder.isAdditionalFieldsEditable() != false || #session.ROLE.contains('solutions_manager') && shippingOrder.isAdditionalFieldsEditable() != false}">
		<s:include value="shipping_additional_fields.jsp"/>
	
		<div class="content_body">	
			<div class="content_table">
				<div class="cont_data_body borderLeftRight" style="margin-bottom:10px;  border-bottom:1px solid #c4c4c4;">
					<div class="form_buttons id="img_save_shipment" style=" padding-right:3px; padding-bottom:5px;">
						<a href="javascript:updateShipment()"> Save Shipment</a>
					</div>
				</div>
			</div>	
		</div>
		
	</s:if>
	</div>
	<s:else>
	<div class="content">
	<div class="content_body">	
		<div class="content_table borderLeftRight " style=" overflow:auto; width:955px !important; padding:0px 3px 10px 0px; ">
			<div class="form_buttons id="img_get_rates" style=" width:200px; float:right !important;">
				<s:if test="%{(#session.ROLE.contains('busadmin') || #session.ROLE.contains('solutions_manager')) }">
					<div align="left" style="float:right !important;"><a  href="javascript:quickShip()" onclick="return (validateOrder(3,1))">Create</a></div>
				</s:if>
			</div>
		</div>
		
		
		<div class="content_table borderLeftRight borderOnlyBottom" style=" overflow:auto; width:958 px !important; padding:0px 0px 10px 0px; margin-bottom:20px;">
			<div class="form_buttons" id="img_get_rates" style=" width:210px; float:right !important;">
				<div align="right" style="float:left !important;"><a href="javascript:saveCurrentShipment()">Save Shipment</a></div>
				<div align="left" style="float:left !important;" id="get_rates_td"><a id="getratesBtn" href="javascript:getRates()" onclick="return (validateOrder(3,1))">Get Rates</a></div>
				
			</div>
		</div>
		
		
	</div>	
	</div>
	
	</s:else>
	
	
	
  	</div>
  	</s:form> 
	
</div>
</body>
</html>
    
    
 