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
<script type="text/javascript">  
  var contextPath = "<%=request.getContextPath()%>";
  
  window.onload = function()
  {
  	  var add1 = document.getElementsByName("shippingOrder.fromAddress.address1");
	  var anchorfrom = getElementsByClassName("edit_shipfrom");
	  var lblto = document.getElementById("labelto");
	   if(add1[0].value.length <1)
	   {
	   		document.getElementById("div_ship_from").style.display = 'block';
	   		document.getElementById("labelfrom").style.display = 'none';
	   		anchorfrom[0].innerHTML = '[-]';
	   }
	   else
	   {
	   		document.getElementById("div_ship_from").style.display = 'none';
	   		document.getElementById("labelfrom").style.display = 'block';
	   		anchorfrom[0].innerHTML = '[+]';
	   }	
	   lblto.innerHTML = '&nbsp;';
  }  	
</script>  

<SCRIPT language="JavaScript">
var prodkey = "";


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

	function modifyQuantity(){
	var quantity= document.getElementById("quantity").value;
	var type= document.getElementById("packType").value;
	if(type=='type_env' || type=='type_pak')
		document.getElementById("quantity").value = 1;
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("dimensions");
			js_stateid.innerHTML= reponse;
			}
	  }
		url="dimensionInformation.action?quantity="+quantity+"&type="+type;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
	
	}


	function setAddress(type)
	{	
		var autoCompleter = dojo.widget.byId("autoaddress");
		var value = autoCompleter.getSelectedKey();
		document.userform.action = "selectShippingAddress.action?addressid="+value+"&type="+type;
		document.userform.submit();

	}
	
	function searchFAddress()
	{
		var autoCompleter = dojo.widget.byId("autoaddress");
     alert("autoCompleter is::"+autoCompleter); //get this value
     //key (in the states example above, "AL")
     var key = autoCompleter.getSelectedKey();
     alert("Key is::"+key);
     
     //value (in the states example above, "Alabama")
     var value = autoCompleter.getSelectedValue();
     alert("Value is::"+value);
     
      var value = autoCompleter.getText();
     alert("Text is::"+value);
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
		document.userform.action = "shipment.stageThree.action";
	 	document.userform.submit();
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
		if(anchorto[0].innerHTML != '[-]')
		{
			shipto.style.display = 'block';
			lblto.innerHTML = '&nbsp;';
			anchorto[0].innerHTML = '[-]';
		}
		else
		{
			shipto.style.display = 'none';
			setLabelvalues('to');
			lblto.style.display = 'block';
			anchorto[0].innerHTML = '[+]';	
		}		
	}
	
	function showAddFromEdit()
	{	
		//alert("Inside showAddFromEdit()");	
		var anchorfrom = getElementsByClassName("edit_shipfrom");
		var shipfrom = document.getElementById("div_ship_from");
		var lblfrom = document.getElementById("labelfrom");
		//alert(anchorfrom[0].innerHTML);
		if(anchorfrom[0].innerHTML != '[-]')
		{
			shipfrom.style.display = 'block';
			//lblfrom.style.display = 'none';
			lblfrom.innerHTML = '&nbsp;';
			anchorfrom[0].innerHTML = '[-]';
		}
		else
		{
			shipfrom.style.display = 'none';
			setLabelvalues('from');
			lblfrom.style.display = 'block';
			anchorfrom[0].innerHTML = '[+]';	
		}			
	}
	
	function setLabelvalues(ft) //from or to?
	{
		if(ft=='from')
		{
			var labelfrom= "";
			var ctry = document.getElementById("firstBox");
			var lblfrom = document.getElementById("labelfrom");
			var province = document.getElementsByName("shippingOrder.fromAddress.provinceCode")[0];
			labelfrom = document.getElementsByName("shippingOrder.fromAddress.abbreviationName")[0].value+", "+document.getElementsByName("shippingOrder.fromAddress.address1")[0].value+" "+document.getElementsByName("shippingOrder.fromAddress.address2")[0].value+", "+document.getElementsByName("shippingOrder.fromAddress.city")[0].value+", "+province.options[province.selectedIndex].value+", "+document.getElementById("fromPostalCode").value+", "+ctry.options[ctry.selectedIndex].value;
			lblfrom.innerHTML= labelfrom;			
		}
		else
		{
			var labelto= "";
			var ctry = document.getElementById("firstBox2");
			var lblto = document.getElementById("labelto");
			var province = document.getElementsByName("shippingOrder.toAddress.provinceCode")[0];
			labelto = document.getElementsByName("shippingOrder.toAddress.abbreviationName")[0].value+", "+document.getElementsByName("shippingOrder.toAddress.address1")[0].value+" "+document.getElementsByName("shippingOrder.toAddress.address2")[0].value+", "+document.getElementsByName("shippingOrder.toAddress.city")[0].value+", "+province.options[province.selectedIndex].value+", "+document.getElementById("toPostalCode").value+", "+ctry.options[ctry.selectedIndex].value;
			lblto.innerHTML= labelto;		
		}	
	}
	
	function toShoworHide(checked)
	{		
		if(checked)
		{
			document.getElementById("pickup_div_panel").style.display = 'block';			
		}
		else
		{
			document.getElementById("pickup_div_panel").style.display = 'none';			
		}		
	}
	
	dojo.event.topic.subscribe("/value_prodl", function(value, key, text, widget){
		dojo.event.topic.publish("/value_product_listen");
		});
		
	dojo.event.topic.subscribe("/value_product", function(value, key, text, widget){
	prodkey = key;
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("Product_summary_div");
				js_stateid.innerHTML= reponse;
				}
		  }
			var url="<%=request.getContextPath()%>/admin/show.product.details.action?prodid="+prodkey;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	});
		
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
	
	function submitToWarehouse()
	{
		if(document.getElementById("product_qty").value.length==0)
			alert("Please enter the Quantity of Product to Shipment");
		else
		{	
			document.userform.action = "shipment.to.warehouse.action";
		 	document.userform.submit();	
		}
	}
	
	function addProductToShipment()
	{
		var prodline = dojo.widget.byId("prodlineSelected");
		var prods = dojo.widget.byId("autoproducts");
		var productname = prods.getSelectedValue();
		var qty = document.getElementById("product_qty").value;
		
		if(prodline.getSelectedValue().length==0)
			alert("Please select the Product Line");
		
		else if(prods.getSelectedValue().length==0)
			alert("Please select the Product");
			
		else if(document.getElementById("product_qty").value.length==0 || document.getElementById("product_qty").value <= 0)
			alert("Please enter the Quantity of Product to Shipment");
		else
		{
			ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				var resp = reponse;
				if(resp.length >2)
				{
					js_stateid=document.getElementById("addedProducts");
					js_stateid.innerHTML= reponse;
				}
				else
					alert("The Product \'"+productname+"\' has already been added to the cart");
				}
		  }
			var url="<%=request.getContextPath()%>/admin/add.product.order.action?prodid="+prodkey+"&quantity="+qty;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
			
		}
	}
	
	function deleteProductfromOrder(index)
	{
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("addedProducts");
				js_stateid.innerHTML= reponse;
				}
		  }
			var url="<%=request.getContextPath()%>/admin/delete.product.order.action?index="+index;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	}
	
</SCRIPT> 


<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="shipment.stageThree" name="userform" id="userform" theme="simple" >
<div class="form-container" >
	<div class="newshipment">
	<div id="pckg_choose">
	<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0" style="margin-bottom:-3px;">
	&nbsp;&nbsp;  
	<a href="new.shipment.action?shipment=true"><mmr:message messageId="label.regular.shipment"/></a>
	</div>
	<s:include value="order_SearchQuickAddress.jsp"/>
	
	<div id="fromAdd_header">		
		<table cellpadding="0" cellspacing="0" width="1050px" id="fromaddtab">
			<tr>
				<td align="left" class="fromAdd_header_table" valign="middle" width="124px"><mmr:message messageId="label.shippingOrder.shipFrom"/>:&nbsp;<a href="#" class="edit_shipfrom" onclick="showAddFromEdit()">[+]</a></td>
				<td>
				<p id="labelfrom">
				<s:property value="%{shippingOrder.fromAddress.abbreviationName}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.address1}"/>&nbsp;<s:property value="%{shippingOrder.fromAddress.address2}"/>,&nbsp;
				<s:property value="%{shippingOrder.fromAddress.city}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.provinceCode}"/>,&nbsp;<s:property value="%{shippingOrder.fromAddress.postalCode}"/>,&nbsp;<s:property value="%{#session.shippingOrder.fromAddress.countryCode}"/>.</p>
				</td>
				<td align="right" class="fromAdd_header_table" valign="middle"></td>
			</tr>
		</table>	
	</div>	
	<s:include value="order_SelectedFromAddress.jsp"/>	
	<div id="toAdd_header">		
		<table cellpadding="0" cellspacing="0" width="1050px" id="toaddtab">
			<tr>
				<td align="left" class="toAdd_header_table" valign="middle" width="124px"><mmr:message messageId="label.shippingOrder.shipTo"/>:&nbsp;<a href="#" class="edit_shipto" onclick="showAddToEdit()">[-]</a></td>
				<td>
				<p id="labelto">
					<s:property value="%{shippingOrder.toAddress.abbreviationName}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.address1}"/>&nbsp;<s:property value="%{shippingOrder.toAddress.address2}"/>,&nbsp;
					<s:property value="%{shippingOrder.toAddress.city}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.provinceCode}"/>,&nbsp;<s:property value="%{shippingOrder.toAddress.postalCode}"/>,&nbsp;<s:property value="%{#session.shippingOrder.toAddress.countryCode}"/>.</p>			
				</td>
				<td align="right" class="toAdd_header_table" valign="middle"></td>
			</tr>
		</table>		
	</div>	
	
		
	<s:include value="order_SelectedToAddress.jsp"/>
	<s:include value="add_products_shipment.jsp"/>
	<div id="Product_summary_div">
		<s:include value="product_summary.jsp"/>
	</div>
	<div id="addedProducts">
		<s:include value="added_products_shipment.jsp"/>
	</div>
	
	<div id="pckg_panel_add_info">	
		<table width="1020px" cellpadding="0" cellspacing="0">
		<tr><td valign="middle" align="left" class="fromAdd_header_table" colspan="9" height="30px">&nbsp;&nbsp;&nbsp;<mmr:message messageId="label.additional.information"/></tr>
		<tr>
			<td width="30px">&nbsp;</td>
			<td class="text_03" width="91px"><mmr:message messageId="label.shippingOrder.additionalServices.scheduledShipDate"/></td>
			<td width="195px">
				<s:textfield name="shippingOrder.scheduledShipDate_web" id="f_date_c" size="10" cssStyle="width: 87px;"
				cssClass="text_02_tf" readonly="readonly" value="%{#session.shippingOrder.scheduledShipDate_web}"/>
				<img src="<%=request.getContextPath()%>/mmr/images/icon_Appt.gif" id="f_trigger_c" style="cursor: pointer;"	title="Date selector" border="0" onClick="selectDate('f_date_c','f_trigger_c');">
			</td>
			<td class="text_03" width="90px"><mmr:message messageId="label.reference.one"/>:</td>
			<td width="200px"><s:textfield size="20" key="shippingOrder.referenceOne" name="shippingOrder.referenceOne"  cssClass="text_02_tf"/></td>
			<td class="text_03" width="100px"><mmr:message messageId="label.reference.two"/>:</td>
			<td width="200px"><s:textfield size="20" key="shippingOrder.referenceTwo" name="shippingOrder.referenceTwo"  cssClass="text_02_tf"/></td>
			<td class="text_03" width="120px">&nbsp;</td>
			<td width="65px">&nbsp;</td>
		</tr>
		</table>
	</div>
	<s:if test="%{#session.ROLE.contains('busadmin') && !shippingOrder.isAdditionalFieldsEditable() == true}">
		<s:include value="shipping_additional_fields.jsp"/>
		<div id="img_save_shipment">
			<a href="javascript:updateShipment()">
				<img src="<s:url value="/mmr/images/save_shipment.png" includeContext="true" />" alt="Save Shipment" border="0">
			</a>
		</div>	
	</s:if>
	<s:else>
		<div id="img_submit_warehouse">
			<a href="javascript: submitToWarehouse()" onclick="return (validateOrder(3,1))"><img src="<s:url value="/mmr/images/submitToWarehouse.png" includeContext="true" />" border="0"></a>
		</div>	
	</s:else>
	
  	</div>
  	</s:form> 

</body>
</html>
    
    
 