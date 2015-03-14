<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<title><s:text name="search.shipment.title" /></title>
<sj:head jqueryui="true" />
<sx:head />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

</head>
<body>
<SCRIPT language="JavaScript">
var def_wh_id=0;
var product_id=0; 

	window.onload = showAutoPrintLabel;

	function showAutoPrintLabel()
	{
		//alert(document.viewform.orderId.value);
		var autoprint = "<%=request.getAttribute("autoprint")%>";
		var popup = document.getElementById("hiddenAutoPrint").value;
		if(autoprint == 'true' && popup != 'false')
		{
			//alert("Auto Print");
			generateLabel(document.viewform.orderId.value);	
			//window.open('getShippingLabel.action?id='+document.viewform.orderId.value);
		}	
	}
	
	 function getSelectedText(elementId) {
	      var elt = document.getElementById(elementId);
	      if (elt.selectedIndex == -1)
	          return null;
	      return elt.options[elt.selectedIndex].text;
	  }
	  function isCurrencyDifferent(elementId){
	   var main_currency = document.getElementById('ordCurrency').value;
	   var line_currency = getSelectedText(elementId);
	   return main_currency === line_currency;
	  }
	  
	  
	  function addActualCharge() {
		   if(isCurrencyDifferent('viewform_newActualCharge_chargecurrency')){
		    document.viewform.action = "add.actual.charge.shipment.action?carrierChargeCodeId="+document.getElementById('newActualCharge_chargeId').value;
		    document.viewform.submit();
		   }else{
		    var main_currency = document.getElementById('ordCurrency').value;
		    alert("<mmr:message messageId='shipment.error.invalidactualchargecurrency'/>");
		   }
		 }
	  function addQuotedCharge() {
		   if(isCurrencyDifferent('viewform_newQuotedCharge_chargecurrency')){
		    document.viewform.action = "add.quoted.charge.shipment.action?carrierChargeCodeId="+document.getElementById('newQuotedCharge_chargeId').value;
		    document.viewform.submit();
		   }else{
		    var main_currency = document.getElementById('ordCurrency').value;
		    alert("<mmr:message messageId='shipment.error.invalidquotedchargecurrency'/>");
		   }
		  }
		  function isCurrenciesDifferent(elementName){
		   var bcurncy = "";
		   var actcharge = document.getElementsByName(elementName);
		   var length = actcharge.length;
		   for (i = 0; i < length; i++) {
		    selobj = actcharge[i];
		    if ((selobj.selectedIndex > 0)){
		     if (bcurncy == "" || bcurncy == selobj.options[selobj.selectedIndex].text){
		      bcurncy = selobj.options[selobj.selectedIndex].text;
		     }else{
		      return false;
		     }
		    }else{
		     return false;
		    }
		   }
		   return bcurncy;
		  }
		  function updateActualCharge() {
		   var rest = isCurrenciesDifferent("actualchargecurrency");
		   if (typeof rest === 'boolean'){
		    if (rest){
		     document.viewform.action = "update.actual.charge.shipment.action";
		     document.viewform.submit();
		    }else{
		     alert("<mmr:message messageId='shipment.error.invalidchargecurrency'/>");
		    }
		   }else if (typeof rest === 'string'){
		    var main_currency = document.getElementById('ordCurrency').value;
		    if (main_currency === rest){
		     document.viewform.action = "update.actual.charge.shipment.action";
		     document.viewform.submit();
		    }else{
		     var ALERT_BUTTON_TEXT = "<mmr:message messageId='shipment.alert.accept'/>";
		     var CANCEL_BUTTON_TEXT = "<mmr:message messageId='shipment.alert.cancel'/>";
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
		     msg = alertObj.appendChild(d.createElement("p"));
		     msg.innerHTML = "<mmr:message messageId='shipment.alert.actualcurrencychange'/>";
		     btnbodyObj = alertObj.appendChild(d.createElement("div"));
		     btnbodyObj.id = "btnBody";
		     
		     btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
		     btnconfirm.id = "confirmBtn";
		     btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
		     btnconfirm.href = "#";
		     btnconfirm.focus();
		     $('#confirmBtn').css('margin-left','91px');
		     
		     btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
		     btnconfirmCancel.id = "confirmCancelBtn";
		     btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
		     btnconfirmCancel.href = "#";
		     btnconfirmCancel.focus();
		     $('#confirmCancelBtn').css('margin-right','66px');
		     
		     $('#confirmBtn').click(function(){
			  document.getElementById('ordCurrency').value = rest;
		      removeCustomConfirm();
		      document.viewform.action = "update.actual.charge.shipment.action";
		      document.viewform.submit();
		     });
		     $('#confirmCancelBtn').click(function(){
		      removeCustomConfirm();
		     });
		     alertObj.style.display = "block";
		     function removeCustomConfirm() {
		     document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
		     }
		    }
		   }
		  } 
		  function updateQuotedCharge() {
		   var rest = isCurrenciesDifferent("quotedchargecurrency");
		   if (typeof rest === 'boolean'){
		    if (rest){
		     document.viewform.action = "update.quoted.charge.shipment.action";
		     document.viewform.submit();
		    }else{
		     alert("<mmr:message messageId='shipment.error.invalidchargecurrency'/>");
		    }
		   }else if (typeof rest === 'string'){
		    var main_currency = document.getElementById('ordCurrency').value;
		    if (main_currency === rest){
		     document.viewform.action = "update.quoted.charge.shipment.action";
		     document.viewform.submit();
		    }else{
		     var ALERT_BUTTON_TEXT = "<mmr:message messageId='shipment.alert.accept'/>";
		     var CANCEL_BUTTON_TEXT = "<mmr:message messageId='shipment.alert.cancel'/>";
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
		     msg = alertObj.appendChild(d.createElement("p"));
		     msg.innerHTML = "<mmr:message messageId='shipment.alert.quotedcurrencychange'/>";
		     btnbodyObj = alertObj.appendChild(d.createElement("div"));
		     btnbodyObj.id = "btnBody";
		     
		     btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
		     btnconfirm.id = "confirmBtn";
		     btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
		     btnconfirm.href = "#";
		     btnconfirm.focus();
		     $('#confirmBtn').css('margin-left','91px');
		     
		     btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
		     btnconfirmCancel.id = "confirmCancelBtn";
		     btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
		     btnconfirmCancel.href = "#";
		     btnconfirmCancel.focus();
		     $('#confirmCancelBtn').css('margin-right','66px');
		     
		     $('#confirmBtn').click(function(){
		      document.getElementById('ordCurrency').value = rest;
		      removeCustomConfirm();
		      document.viewform.action = "update.quoted.charge.shipment.action";
		      document.viewform.submit();
		     });
		     $('#confirmCancelBtn').click(function(){
		      removeCustomConfirm();
		     });
		     alertObj.style.display = "block";
		     function removeCustomConfirm() {
		     document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
		     }
		    }
		   }
		  }
		function clearExceptionStatus() {
			document.viewform.action = "clear.exception.status.action";
			document.viewform.submit();
		}		
		function processPayment() 
		{
			var submit = true;
			//if the checkbox is not null, then the storecc of the business is true.
			if(document.getElementById("storecc_id") != null)
			{
				if(!document.getElementById("storecc_id").checked)
				{
					alert("Please click the Note Check box to agree to the condition stated");
					submit = false;
				}
			}
			if(submit)
			{
				document.viewform.action = "processPayment.action";
				document.viewform.submit();
				$('#loader').css('display','block');
				$('#loaderImg').css('display','block');
			}
		}		
		function generateLabel(id){
		//alert("label::"+id);
		var arrOrders = new Array();
		arrOrders[0] = id;
			var slcopies = document.getElementById("label_copies").value;
			if(document.getElementById("customsinv_copies")!=null) //for international shipments
	    	var ccopies = document.getElementById("customsinv_copies").value;
	    	else // for domestic shipments
	    	var ccopies = 0;
	    	var url="getShippingLabel.action?slcopies="+slcopies+"&cicopies="+ccopies+"&arrayOrders="+arrOrders;
	    	//alert(url);
			window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');

		}
		function cancelShipment() {
			var clientIP;
							       // alert(ip);
		    try
		    {
			if (window.XMLHttpRequest) xmlhttp = new XMLHttpRequest();
	        else xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	
	         xmlhttp.open("GET","https://freegeoip.net/json/",false); 
	        
	       xmlhttp.send();
			 var json = JSON.parse(xmlhttp.responseText);
			 clientIP=json["ip"];
		    }
		    catch(err)
		    {
		    	//alert(err+"error ocured in ");
		    	clientIP="error";
		    }
		   	 document.viewform.action = "cancelShipment.action?ip="+clientIP; 
			//document.viewform.action = "cancelShipment.action";
			document.viewform.submit();
		}	

		function backtoListShipment(){
					document.viewform.action = "list.shipment.action";
						document.viewform.submit();
					}
			
		function showPackage(id)
		{
			var tabContainer = dojo.widget.byId("order_detail_page");
     		tabContainer.selectTab(id);		
		}
		
		function showOrHideProductSummary(index,pid)
		{
			var divclss = "show_summ_"+index;
			var anchor = getElementsByClassName(divclss);
			var inner = anchor[0].innerHTML;
			var divid = "inner_div_"+pid;
			var cid= document.viewform.cid.value;
			if(inner != '[&nbsp;+&nbsp;]')
			{
				document.getElementById(divid).style.display = 'none';
				anchor[0].innerHTML = '[&nbsp;+&nbsp;]';
			}
			else
			{
				product_id=pid;
				//ajax implemantation
				 ajax_prods=ajaxFunction();
				ajax_prods.onreadystatechange=function()
				  {
					   if(ajax_prods.readyState==4)
						{
						reponse=ajax_prods.responseText;
						js_innerdiv=document.getElementById(divid);
						js_innerdiv.innerHTML= reponse;
						}
				  }
				  	url="goToProductInventory.action?productId="+pid+"&cid="+cid+"&productsummary=true";
					ajax_prods.open("GET",url,true);
					ajax_prods.send(this);
					
				document.getElementById(divid).style.display = 'block';
				anchor[0].innerHTML = '[&nbsp;-&nbsp;]';
			}
		}
		
		function populateBatches(pid, index)
		{
			var sid="wiplocs_"+pid+"_"+index;
			var locid=document.getElementById(sid).value;
			var vid = "b_"+pid+"_"+index;
			callBatchesAjaxFunc(locid, vid, pid, index);
		}
		
		function callBatchesAjaxFunc(key, tdid, prodid, index)
		{
			ajax_batches=ajaxFunction();
			ajax_batches.onreadystatechange=function()
		  	{
			   if(ajax_batches.readyState==4)
				{
				reponse=ajax_batches.responseText;
				js_batchByLocId=document.getElementById(tdid);
				js_batchByLocId.innerHTML= reponse;
				}
		  	}
		 	url="<%=request.getContextPath()%>/admin/listBatchesByLocId.action?locId="+key+"&prodid="+prodid+"&index="+index;
		  	ajax_batches.open("GET",url,true);
		  	ajax_batches.send(this);
			
		}
		
		function moveProductToWIP(wid,ix,fromlocId,frombatch)
		{
			//alert("Product Id::"+product_id);
			var errorcount=0;
			var divid = "inner_div_"+product_id;
			var cid= document.viewform.cid.value;
			//alert("Product Id::"+product_id);
			//alert("Index::"+ix);
			var qtyid = "qty_"+product_id+"_"+ix;
			var qty = document.getElementById(qtyid).value;
			//alert("after qty");
			if(qty=='')
			{
				alert("Please enter the Quantity of Units to Move");
				return false;
				errorcount++;
			}
				
			var wipslocid = "wiplocs_"+product_id+"_"+ix;
			var wipsloc = document.getElementById(wipslocid).value;
			if(wipsloc=='-1')
			{
				alert("Please select the WIP Location to Move");
				return false;
				errorcount++;
			}
			var batchid = "batches_"+product_id+"_"+ix;
			var batchloc = document.getElementById(batchid).value;
			
			//alert("Quantity::"+qty);
			//alert("Warehouse ID::"+wid);
			//alert("From Location ID::"+fromlocId);
			//alert("To Location Id::"+wipsloc);
			//alert("To Batch Id::"+batchloc);	
			//alert("From Batch Id::"+frombatch);	
			if(errorcount==0)
			//Call Ajax implementation to move product in Inventory
			{
				//ajax implemantation
				 ajax_prods=ajaxFunction();
				ajax_prods.onreadystatechange=function()
				  {
					   if(ajax_prods.readyState==4)
						{
						reponse=ajax_prods.responseText;
					//	alert(reponse);
						//alert(divid);
						js_innerdiv=document.getElementById(divid);
						//js_innerdiv.innerHTML= "123";
						js_innerdiv.innerHTML= reponse;
						}
				  }
				  	url="moveProductInInventoryAjax.action?flocid="+fromlocId+"&twhid="+wid+"&tlocid="+wipsloc+"&qty="+qty+"&batch="+frombatch+"&batchto="+batchloc+"&productId="+product_id+"&cid="+cid+"&productsummary=true";
					ajax_prods.open("GET",url,true);
					ajax_prods.send(this);
			}
		}
			
	function showBillingState() {
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("billingstateid");
			js_stateid.innerHTML= reponse;
			}
	  }
		firstBox = document.getElementById('billingCountry');
	  	url="<%=request.getContextPath()%>/customer.listProvince.action?value="+firstBox.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
	} // End function showState()

	

	function copyToActual()
		{
			if(document.getElementById("copy_to_actual").value=='')
				alert("Please choose status option for actual charges");
			
				
				else
				{
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
				msg.innerHTML = "Would you like to copy these charges into the actual charges?";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','91px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','66px');
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.viewform.action = "copy.actual.charge.action";
				document.viewform.submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}
				
				
					
				}
			
		

	function copyTheActual()
	{
		if(document.getElementById("copy_the_actual").value=='')
			alert("Please choose status option for actual charges");
		else{
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
				msg.innerHTML = "Would you like to copy these charges into the actual charges?";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','91px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','66px');
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.viewform.action = "copy.actual.charge.action?quotedChargeStatusText=";
					document.viewform.submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}
			
		}
	
	</SCRIPT>
					<script>
						$(document).ready(function(){
							<%
														 String isLTL=(String)request.getAttribute("isLTL");
														 %>
														var isLTL = <%=isLTL%>;
														if(isLTL == "1"){
															$('#box3').css('display','block');
									   						$('#box1,#box2').css('display','none');
									   						$('#box3').css('background-color','#990000');
									   						$('.navi4 ul li:last-child').css('background-color','#990000');
														}
														else{
							$('.navi4 ul li:first-child').css('background-color','#990000');
							$('#box2,#box3').css('display','none');
														}
							$('.navi4 ul li').click(function(){
								$(this).css('background-color','#990000');
								$(this).siblings().css('background-color','#000000');
								var indexval = $(this).index();
								if(indexval == 0){
									$('#box1').css('display','block');
									$('#box2,#box3').css('display','none');
								}
								if(indexval == 1){
									$('#box2').css('display','block');
									$('#box1,#box3').css('display','none');
								}
								if(indexval == 2){
									$('#box3').css('display','block');
									$('#box2,#box1').css('display','none');
								}
							});
							
							$('#vw_shpmnt_nxt').click(function(){
								$('#box2').css('display','block');
								$('#box1,#box3').css('display','none');
							});
							$('#vw_shpmnt_nxt1').click(function(){
								$('#box1').css('display','block');
								$('#box2,#box3').css('display','none');
							});
							
						});
					</script>
					<script type="text/javascript">
function listChargeCode(carrierId){
	//alert(chargecode);
	if(carrierId != ""){
		
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{
		  	 if(ajax_Carrier.readyState==4)
				{
			 	   reponse=ajax_Carrier.responseText;
			  	  chargeCodeElement = document.getElementById("chargeCode");
			   	 chargeCodeElement.innerHTML= reponse;
					listChargeName(chargeCodeElement.value);
				}
	 	 };
		url="listChargeCode.action?carrierId="+carrierId+"&newChargeType=newActualCharge";
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#chargeCode select").html("");
		$("#chargeCode select").html("<option>Select</option>");
		$("#chargeName select").html("");
		$("#chargeName select").html("<option>Select</option>");
	}
	
}
function newActualChargeListChargeName(chargeCode){
	//alert(chargecode);chargeCode
	if(chargeCode != ""){
		carrierId = document.getElementById("carrierId").value;
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
		  {
		   if(ajax_Carrier.readyState==4)
			{
			    reponse=ajax_Carrier.responseText;
				document.getElementById("chargeName").innerHTML= reponse;
			}
	 	 };
		url="listChargeName.action?carrierId="+carrierId+"&chargeCode="+chargeCode+"&newChargeType=newActualCharge";
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#chargeName select").html("");
		$("#chargeName select").html("<option>Select</option>");
	}
	
}
function quoteListChargeCode(carrierId){
	//alert(chargecode);
	if(carrierId != ""){
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{
		   	if(ajax_Carrier.readyState==4)
			{
			    reponse=ajax_Carrier.responseText;
			    chargeCodeElement = document.getElementById("quoteChargeCode");
			    chargeCodeElement.innerHTML= reponse;
				listChargeName(chargeCodeElement.value);
			}
	 	 };
		url="listChargeCode.action?carrierId="+carrierId+"&newChargeType=newQuotedCharge";
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#quoteChargeCode select").html("");
		$("#quoteChargeCode select").html("<option>Select</option>");
		$("#quoteChargeName select").html("");
		$("#quoteChargeName select").html("<option>Select</option>");
		
	}
	
}
function newQuotedChargeListChargeName(chargeCode){
	//alert(chargecode);chargeCode
	if(carrierId != ""){
		carrierId = document.getElementById("quoteCarrierId").value;
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{
		   if(ajax_Carrier.readyState==4)
			{
			    reponse=ajax_Carrier.responseText;
				document.getElementById("quoteChargeName").innerHTML= reponse;
			}
	 	 };
		url="listChargeName.action?carrierId="+carrierId+"&chargeCode="+chargeCode+"&newChargeType=newQuotedCharge";
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#quoteChargeName select").html("");
		$("#quoteChargeName select").html("<option>Select</option>");
	}
	
}

function deletecharge(action){
	
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
				msg.innerHTML = "Do you really want to delete the selected charge?";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','50px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','39px');
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
				removeCustomConfirm();
				document.viewform.action = action;
					document.viewform.submit();
				
				
				
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
<style>
#additional_service_detail_div_start{ padding:5px; font-weight:600}
#order_detail_table .adddtl_title{ width:228px; float:left; height:auto; padding:3px 5px; font-size:12px; }
</style>
						<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
	<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div>
</div>
			<style> 
				.srchshipmnt_text_04{color:#fff; font-size:12px;padding-left:5px;}
				#cancel_shipment,#back_shipment_list{ 
				 float:right;
				 text-decoration:none;
				 background-color:#990000;
				 font-size:12px;
				 color:#FFF;
				 font-weight:bold;
				 padding:5px 10px;
				 text-transform:uppercase;
				}
				#back_shipment_list{ margin-right:1px;}
				
				
				
				
				.fields .controls input[type="text"], .fields .controls input[type="password"],.fields .controls select{
					width:146px !important;
				}
				
				
							</style>	
<div id="messages"><jsp:include
	page="../common/action_messages.jsp" /></div>

<div class="form-container"><s:form id="viewform"
	action="update.charge.shipment.action" name="viewform">
	<s:hidden name="cid" value="%{selectedOrder.customer.id}"/>
	<s:hidden name="ordCurrency" id="ordCurrency" value="%{selectedOrder.currency}" />
	<s:token/>
					<div class="content">
					<div class="content_body" >	
						<div class="content_table">
						<div class="navi4">
							<ul style="float:left; width:400px; border:0px;">
								<li><mmr:message messageId="menu.orderdetails"/></li>
        <li><mmr:message messageId="menu.packagedetails"/></li>
        <li><mmr:message messageId="menu.statusupdate"/></li>
							</ul>
							<s:if test="%{selectedOrder.statusId!=40}">
								<a id="cancel_shipment" href="javascript:cancelShipment()"><mmr:message messageId="menu.cancel.shipment" /> </a>
							</s:if>
							<a id="back_shipment_list" href="javascript:backtoListShipment()"><mmr:message messageId="btn.back" /> </a>
						</div>
						</div>
						<div class="content_table" id="box1" > 
							  	<s:if test="%{selectedOrder.paymentRequired==true}"> 
				<!-- Condition to Show or Hide the Payment Panel , Hides: If the Customer need not Pay - Shows: If the Customer is required to make the Payment.-->
										
										
										
										
										
				<div class="content_header">
					<div class="cont_hdrtitle_l" style=" color:#FFF; width:200px; font-size:12px; font-weight:bold; margin:1px 0px 0px 400px;"><mmr:message messageId="label.heading.paymentrequired"/> :</div>
							<!-- <div id="payment_rqd_top" style=" color:#FFF; width:200px; font-size:12px; font-weight:bold; margin:6px 0px 0px 400px;">Payment Required:-->
								<div id="payment_actions">
									<div class="form_buttons">
											<div align="left" style="float:left !important;" id="process_Payment_td"><a href="javascript: processPayment()"><mmr:message messageId="label.pay.now" /></a></div>
											<a href="backToShipment.action"><mmr:message messageId="label.shipment.edit" /></a>
									</div>
								</div>
												
				</div>
				
				
				
				<div class="cont_data_body borderLeftRight">
									<div class="fields">
										<label><mmr:message	messageId="label.creditcard.number" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="24" key="creditCard.ccNumber" name="selectedOrder.creditCard.ccNumber" cssClass="text_02_tf" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message
							messageId="label.creditcard.expiryMonth" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:select required="true"
							list="#{'01':'Jan', '02':'Feb', '03':'Mar', '04':'Apr', '05':'May', '06':'Jun', '07':'Jul', '08':'Aug', '09':'Sep', '10':'Oct', '11':'Nov', '12':'Dec'}"
							key="creditCard.ccExpiryMonth"
							name="selectedOrder.creditCard.ccExpiryMonth"
							cssClass="text_01_combo_medium" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.expiryYear" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:select list="#{'2012':'2012', '2013':'2013', '2014':'2014', '2015':'2015', '2016':'2016', '2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}"
							key="creditCard.ccExpiryYear" name="selectedOrder.creditCard.ccExpiryYear" cssClass="text_01_combo_medium" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message
							messageId="label.creditcard.cvdCode" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="5" key="creditCard.cvd"
							name="selectedOrder.creditCard.cvd" cssClass="text_02_tf_small" /></p>
										</div>
									</div>
								

									<div class="fields">
										<label><mmr:message
							messageId="label.pay.amount" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
							value="selectedOrder.totalChargeQuoted" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.nameOnCard"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:select list="#{'Master Card':'Master Card', 'Visa':'Visa'}"
key="selectedOrder.creditCard.billingAddress.contactName" name="selectedOrder.creditCard.billingAddress.contactName" cssClass="text_01_combo_medium" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingAddress1"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="24" key="selectedOrder.creditCard.billingAddress.address1" name="selectedOrder.creditCard.billingAddress.address1" cssClass="text_02_tf"/></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingAddress2"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="24" key="selectedOrder.creditCard.billingAddress.address2" name="selectedOrder.creditCard.billingAddress.address2" cssClass="text_02_tf"/></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingCity"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="24" key="selectedOrder.creditCard.billingAddress.city" name="selectedOrder.creditCard.billingAddress.city" cssClass="text_02_tf"/></p>
										</div>
									</div>
										<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingPostalCode"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:textfield size="24" key="selectedOrder.creditCard.billingAddress.postalCode" name="selectedOrder.creditCard.billingAddress.postalCode" cssClass="text_02_tf"/></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingCountry"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:select cssClass="text_01_combo_big" cssStyle="width:135px;" listKey="countryCode" listValue="countryName" 
								name="selectedOrder.creditCard.billingAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
									onchange="javascript:showBillingState();"  id="billingCountry" theme="simple"/></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.creditcard.billingProvince"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:select key="selectedOrder.creditCard.billingAddress.provinceCode" name="selectedOrder.creditCard.billingAddress.provinceCode"  cssClass="text_01_combo_big" cssStyle="width:135px;" 
										listKey="provinceCode" listValue="provinceName" list="#session.provinces"/></p>
										</div>
									</div>
									
									
				<s:if test="%{selectedOrder.business.storeCC == true}">
				<div id="note_div"><s:checkbox name="storecc" id="storecc_id"/>&nbsp;<mmr:message messageId="label.storecc.note"/> </div>
			</s:if>	
				
				<div id="payment_rqd_end">&nbsp;</div>
	 		</s:if> 
							<div class="content_table">
						
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.pickupfrom"/> :</div>
								<s:hidden name="onpop" id="hiddenAutoPrint" value="%{#session.AutoPrintAgain}" />
								<s:hidden name="orderId" value="%{selectedOrder.id}" /> 
								<s:set name="oid" value="%{selectedOrder.id}" /> 
								<div class="cont_hdrtitle_l" style="width:300px"><mmr:message messageId="label.heading.orderdetails"/> 
			 #&nbsp;<s:property value="%{selectedOrder.id}" /></div>
								<div class="form_buttons" >										
								 
								</div>
							</div>			
							<div class="cont_data_body borderLeftRight" id="order_detail_from_to_table">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.viewship.company"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.fromAddress.abbreviationName}" /></p>
										</div>
									</div>
									<div class="fields_long">
										<label><mmr:message messageId="label.viewship.address"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property value="%{selectedOrder.fromAddress.address1}" />:&nbsp; 
						<s:property	value="%{selectedOrder.fromAddress.city}" /> , 
						<s:property	value="%{selectedOrder.fromAddress.provinceCode}" /> , 
						<s:property	value="%{selectedOrder.fromAddress.postalCode}" />, 
						<s:property	value="%{selectedOrder.fromAddress.countryName}" /></p>
										</div>
									</div>
									<s:if test="%{selectedOrder.fromAddress.residential == 1}">
									<div class="adddtl_title" width="12%"><mmr:message messageId="label.viewship.fromres"/> </div>
									</s:if>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.phone"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.fromAddress.phoneNo}" /></p>
										</div>
									</div>
									<div class="fields_long">
										<label><mmr:message messageId="label.viewship.email"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.fromAddress.emailAddress}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.attention"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.fromAddress.contactName}" /></p>
										</div>
									</div>
								</div>
							</div>
							
							
							
							
							
			<div class="content_header" id="order_detail_to_table_hdng">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.shipto"/> :</div>
											<s:if test="%{selectedOrder.paymentRequired!=true && selectedOrder.statusId!=40}">
				<!-- Condition to Show or Hide the Generate Label Link, Hides: If Customer has not paid - Shows: If the Customer has made the Payment-->
				<div class="form_buttons" >	
									<a href="javascript:generateLabel('<s:property value="%{#oid}" />')"><mmr:message messageId="label.print.label"/> </a>
								</div>	
				<div style="float:right; padding-right:15px;">
				<font class="srchshipmnt_text_04"><mmr:message messageId="label.copies.shipping.label"/></font>&nbsp;
				<s:if test="%{#request.no_of_lbls != null}">
					<s:select id="label_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{#request.no_of_lbls}"/>
				</s:if>
				<s:else>
					<s:select id="label_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="1"/>
				</s:else>
				&nbsp;
				<s:if test="%{selectedOrder.isInternationalShipment==true}">
				<font class="srchshipmnt_text_04">
				<mmr:message messageId="label.copies.customsinvoice"/></font>&nbsp;
				<s:if test="%{#request.no_of_ci != null}">
					<s:select id="customsinv_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{#request.no_of_ci}"/>
				</s:if>
				<s:else>
					<s:select id="customsinv_copies" cssStyle="width:39px;" cssClass="text_01_combo_small" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="3"/>
				</s:else>
				</s:if>
				</div>
								
				</s:if>	
								
							</div>
							
							<div class="cont_data_body borderLeftRight">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.viewship.company"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.toAddress.abbreviationName}" /></p>
										</div>
									</div>
									<div class="fields_long">
										<label><mmr:message messageId="label.viewship.address"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property value="%{selectedOrder.toAddress.address1}" />:&nbsp; 
						<s:property	value="%{selectedOrder.toAddress.city}" /> , 
						<s:property	value="%{selectedOrder.toAddress.provinceCode}" /> , 
						<s:property	value="%{selectedOrder.toAddress.postalCode}" />, 
						<s:property	value="%{selectedOrder.toAddress.countryName}" /></p>
										</div>
									</div>
						<s:if test="%{selectedOrder.toAddress.residential == 1}">
					<div class="adddtl_title" width="12%"><mmr:message messageId="label.viewship.toresidential"/> </div>
					</s:if>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.phone"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.toAddress.phoneNo}" /></p>
										</div>
									</div>
									<div class="fields_long">
										<label><mmr:message messageId="label.viewship.email"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.toAddress.emailAddress}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.attention"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.toAddress.contactName}" /></p>
										</div>
									</div>
								</div>
							</div>
							
<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.orderdetails"/> :</div>
							</div>
							<div class="cont_data_body borderLeftRight" style="border-bottom:1px solid #c4c2c2; margin-bottom:10px;" id="order_detail_table">
								<div class="rows">
								<div class="fields">
										<label><mmr:message messageId="label.viewship.customer"/></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.customer.name}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.zonefromto"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.fromZone}" /> / <s:property
						value="%{selectedOrder.toZone}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.email"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.currency}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.carrier"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.service.masterCarrier.name}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.service"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.service.name}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.pickupconfirm"/> #</label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.pickup.confirmationNum}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.carriertrack"/> #</label>
										<div class="controls">
											<span>:</span>
											<p><strong><s:property
						value="%{selectedOrder.masterTrackingNum}" /></strong></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.ref1"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.referenceCode}" /></p>
										</div>
									</div>
								<div class="fields">
										<label><mmr:message messageId="label.viewship.ref2"/> </label>
										<div class="controls">
											<span>:</span>
											<p style="width:160px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property
						value="%{selectedOrder.referenceOne}" /> <s:property
						value="%{selectedOrder.referenceTwo}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.weight"/> </label>
										<div class="controls">
											<span>:</span>
											<p>	<s:property	value="%{selectedOrder.quotedWeight}" /> <s:property value="%{selectedOrder.quotedWeightUOM}"/> 
						<s:if test="%{selectedOrder.billedWeight > 0}">
							/ <s:property	value="%{selectedOrder.billedWeight}" /> <s:property value="%{selectedOrder.billedWeightUOM}"/>
						</s:if>
						<s:if test="%{selectedOrder.ratedAsWeight > 0}">
							<br>Rated As: <s:property value="%{selectedOrder.ratedAsWeight}"/> <s:property value="%{selectedOrder.billedWeightUOM}"/>
						</s:if></p>
										</div>
									</div>	
						<div class="fields">
										<label><mmr:message messageId="label.viewship.shipdate"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.scheduledShipDate}" /></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.billstatus"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.billingStatusText}" />
						<s:if test="%{selectedOrder.billToType =='Third Party' || selectedOrder.billToType=='Collect'}">
							: <s:property value="%{selectedOrder.billToAccountNum}" />
						</s:if></p>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.viewship.status"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.statusName}" /></p>
										</div>
									</div>	
							<s:if test="%{selectedOrder.statusId==50}"> 
							<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('solutions_manager'))">
							<div class="fields">
										<label><s:a 
									href="javascript: clearExceptionStatus()" 
									cssStyle="text-decoration: none;"><mmr:message messageId="label.viewship.clearexception"/>
							</s:a></label>
										<div class="controls">
											<span>:</span>
											<p> <s:select cssClass="text_01" cssStyle="width:158px;" listKey="id" listValue="name" name="status_id" list="#session.orderStatusList" headerKey="-1"  id="status_id" theme="simple"/></p>
										</div>
									</div>
									</s:if>
								</s:if>
								
										<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
						<s:if test="%{selectedOrder.markType == 1}">
							<div class="fields"><mmr:message messageId="label.viewship.markup"/> </div>
						</s:if>
						<s:if test="%{selectedOrder.markType == 2}">
							<div class="fields"><mmr:message messageId="label.viewship.markdown"/> </div>
						</s:if>
						
						<div class="fields"><s:property
							value="%{selectedOrder.markPercent}" /> %</div>
						<div width="1%">&nbsp;</div>		
						
									
					</s:if>
							</div>
							<div class="fields">
											<label><mmr:message messageId="label.viewship.link.to.shipid"/></label>
										<div class="controls"><span>:</span>
											<s:if test="%{selectedOrder.linkToOrder != null && selectedOrder.linkToOrder != 0}">
										<s:property value="%{selectedOrder.linkToOrder}" />
										</s:if>
											</div>
										</div>
								<div id="payment_rqd_end">&nbsp;</div>
<s:if test='%{selectedOrder.tradeShowPickup == 1 || selectedOrder.tradeShowDelivery == 1 || selectedOrder.insidePickup == 1 ||
			selectedOrder.insideDelivery == 1 || selectedOrder.appointmentPickup == 1 || selectedOrder.appointmentDelivery == 1 ||
			selectedOrder.fromTailgate == 1 || selectedOrder.toTailgate == 1 || selectedOrder.satDelivery == 1 || selectedOrder.holdForPickupRequired == 1 || selectedOrder.CODValue > 0 || selectedOrder.insuranceValue > 0}'>
			<div id="additional_service_detail_div_start"><mmr:message messageId="label.heading.additionalservice"/> :</div>
			
			
			
			<div id="order_detail_table">
			<div>
				<div>
					<s:if test='%{selectedOrder.tradeShowPickup == 1}'>
					<div  class="adddtl_title"><mmr:message messageId="label.viewship.tradeshowpickup"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.tradeShowDelivery == 1}'>
					<div class="adddtl_title" ><mmr:message messageId="label.viewship.tradeshowdelivery"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.insidePickup == 1}'>
					<div class="adddtl_title" ><mmr:message messageId="label.viewship.insidepickup"/> </div>
					</s:if>
					 <s:if test='%{selectedOrder.insideDelivery == 1}'>
					<div class="adddtl_title" ><mmr:message messageId="label.viewship.insidedelivery"/> </div>
					</s:if> 
				</div>
				<div>
					<s:if test='%{selectedOrder.appointmentPickup == 1}'>
					<div class="adddtl_title"><mmr:message messageId="label.viewship.appointmentpickup"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.appointmentDelivery == 1}'>
					<div class="adddtl_title"><mmr:message messageId="label.viewship.appointmentdelivery"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.fromTailgate == 1}'>
					<div class="adddtl_title"><mmr:message messageId="label.viewship.tailgatepickup"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.toTailgate == 1}'>
					<div class="adddtl_title" ><mmr:message messageId="label.viewship.tailgatedelivery"/> </div>
					</s:if>
				</tr>
				<tr>
				<s:if test='%{selectedOrder.satDelivery == 1}'>
					<div class="adddtl_title" align="left" valign="middle"><mmr:message messageId="label.viewship.saturdaydelivery"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.holdForPickupRequired == 1}'>
					<div class="adddtl_title"><mmr:message messageId="label.viewship.holdforpickup"/> </div>
					</s:if>
					<s:if test='%{selectedOrder.CODValue > 0}'>
					<div class="adddtl_title">
						<span class="adddtl_title" style=" width:70px; float:left;"><mmr:message messageId="label.viewship.cod"/> </span>
						<span class="ordrdtl_title_val" style=" margin-left:25px; float:left;">$ :<s:property
							value="%{selectedOrder.CODValue}" /></span>
					</div>
					</s:if>
<s:if test='%{selectedOrder.insuranceValue > 0}'>
					<div class="adddtl_title">
						<span class="adddtl_title" style=" width:60px; float:left;"><mmr:message messageId="label.viewship.insurance"/> </span>
						<span class="ordrdtl_title_val" style=" margin-left:16px; float:left;">$ :<s:property
							value="%{selectedOrder.insuranceValue}" /></span>
					</div>
</s:if>
				</div>
 
     </div>
			</div>
			</div>
			</div>
			</s:if>
							<div class="cont_data_body borderLeftRight">
									
									<s:if test="%{selectedOrder.quotedCharges.size > 0}">
									<div class="content_header" style="width:958px !important">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.quotecharges"/> :</div>
								<div class="cont_hdrtitle_l" style="width:200px">
								<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
								<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>
								<s:text name="format.customMoney" ><s:param name="value" value="%{selectedOrder.totalChargeQuoted}" /></s:text>
								</s:if>	
								</div>
								<s:if test="%{selectedOrder.actualCharges.size ==0 && #session.ROLE.contains('busadmin') || selectedOrder.actualCharges.size ==0 && #session.ROLE.contains('solutions_manager')}">
								
								
					<div id="copy2actual" class="" style="float:right; width:321px; margin-top:-2px; ">
						<div class="fields">
							<label style="padding-left:28px !important; width:auto !important; margin-right:5px;">
								<a href="javascript: copyToActual();"  style="text-decoration: none; color:#FFF; font-size:12px; ">
									<mmr:message messageId="label.heading.copytoactual"/> 
								</a>
							</label>
							
								<div class="controls" style="text-align:right;">
									<s:select id="copy_to_actual" cssStyle=" float:left; width:176px !important;" 
													cssClass="text_01_combo_big"
														name="quotedChargeStatusText"
															list="{'','Pending Release','Ready to Invoice','Quick Invoice'}" theme="simple" />
								</div>
						</div>		
					</div>
					</s:if>
							</div>
							<div style="width:958px;height:auto;float:left;overflow-x:scroll;">
						   <table width="950px" cellpadding="2" cellspacing="0" style="font-size:12px; background-color:#e7e7e7;">
							<tr style="background-color:#d1d1d1; width:960px;  font-size:12px;">
								<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.carrier"/> </strong></td>
							    <td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.code"/> </strong></td>
   								<td class="ordrdtl_title_hdng"><strong style="width:auto; min-width:120px; float:left;"><mmr:message messageId="label.ghead.chargename"/> </strong></td>
								<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
									<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.tariff"/> </strong></td>
									<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.cost"/> </strong></td>
									<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.cur"/> </strong></td>
								</s:if>
								<!-- this is to hide the charge for customer_shipper user-->
								<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
								<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.charge"/> </strong></td>
								</s:if>
								 <s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
                                <td class="ordrdtl_title_hdng"><strong><mmr:message messageId="label.ghead.cur"/> </strong></td>
                               <td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.exrate"/> </strong></td>
                               </s:if>
								<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.status"/> </strong></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td valign="top"><s:iterator id="quotedCharges"
									value="selectedOrder.quotedCharges" status="row">
									<tr>
										<!-- status=30=Billed -- >
										<!-- Unbilled charges will only be displayed to Business Admin -->
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
											<td class="ordrdtl_title_val">
													<s:property value="carrierName" />
											</td>
										</s:if>
										<s:else>
										    <td class="ordrdtl_title_val">
													<s:property value="%{selectedOrder.service.masterCarrier.name}" />
											</td>
											
										</s:else>
										<td  class="ordrdtl_title_val">
										<s:if test="%{name == 'Freight'}">
											<s:if test="%{#session.ROLE.contains('busadmin') && selectedOrder.slaveServiceId !=null && selectedOrder.slaveServiceId>0||#session.ROLE.contains('solutions_manager')&& selectedOrder.slaveServiceId !=null && selectedOrder.slaveServiceId>0}">
										    	<s:property value="selectedOrder.slaveServiceId" />
										    </s:if>
										    <s:else>
												<s:property value="selectedOrder.serviceId" />
											</s:else>
										</s:if>
										<s:else>
											<s:property value="chargeCode" />
										</s:else>
											</td>

										<s:if test="%{status != 30 && #session.ROLE.contains('busadmin') ||status != 30 && #session.ROLE.contains('solutions_manager')}">
											<td class="ordrdtl_title_val">
											<s:if test="%{selectedOrder.actualCharges.size ==0}">
												<s:textfield size="10"
													key="quotedChargeName" name="quotedChargeName"
													value="%{name}" cssClass="text_02" />
													</s:if>
													<s:else><s:property value="name" /></s:else>
											</td>
								</td>
									<td class="ordrdtl_title_val">
												<s:property value="tariffRate" />

											<td class="ordrdtl_title_val">
											<s:if test="%{selectedOrder.actualCharges.size ==0}">
											<s:textfield size="5"
												key="quotedChargeCost" name="quotedChargeCost"
												value="%{cost}" cssClass="text_02" cssStyle="text-align:right;" />
												</s:if>
												<s:else><s:property value="cost" /></s:else>
												</td>
										<%--	<td class="ordrdtl_title_val">
												<s:property value="tariffRate" />
											</td>  --%>
											
											<td class="ordrdtl_title_val">
											<s:if test="%{selectedOrder.actualCharges.size ==0}">
                                               <s:select 
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                        name="quotedcostcurrency" value="%{costcurrency}" headerKey="0" headerValue=" "
                                                           list="currencyList" listKey="id" listValue="currencyCode" theme="simple" />
                                                           </s:if>
                                                           <s:else>
                                                            <s:if test="%{costcurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{costcurrency==2}">
                                           
                                           USD
                                           
                                            </s:elseif>
                                            <s:else>
                                            CAD
                                            </s:else>
                                                           </s:else>
                                               </td>
											
												 <td class="ordrdtl_title_val">
											<s:hidden
												name="quotedChargeIds" value="%{id}" /> 
												<s:if test="%{selectedOrder.actualCharges.size ==0}">
												<s:textfield
												size="5" key="quotedCharge" name="quotedCharge"
												value="%{charge}" cssClass="text_02" cssStyle="text-align:right;"/>
												
												</s:if>
												<s:else><s:property value="charge" /></s:else></td>
											
												<td class="ordrdtl_title_val">
												<s:if test="%{selectedOrder.actualCharges.size ==0}">
                                               <s:select 
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                       name="quotedchargecurrency" value="%{chargecurrency}" headerKey="0" headerValue=" "
                                                           list="currencyList" listKey="id" listValue="currencyCode"  theme="simple" />
                                                           </s:if>
                                                           <s:else>
                                                           
                                                           <s:if test="%{chargecurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{chargecurrency==2}">
                                           
                                           USD
                                           </s:elseif>
                                           <s:else>
                                            CAD
                                            </s:else>
                                                           
                                                           </s:else>
                                               </td>
                                           <td class="ordrdtl_title_val">
                                            <s:if test="%{selectedOrder.actualCharges.size ==0}">
                                               <s:textfield size="5"
                                                    name="quotedexchangerate" value="%{exchangerate}"
                                                    cssClass="text_02" />
                                                     </s:if>
                                                    <s:else><s:property value="exchangerate" /></s:else>
                                           </td>   
											<td class="ordrdtl_title_val">
											<s:if test="%{cancelledInvoice=='Yes'}">
											<mmr:message messageId="status.cancelled"/>
											</s:if>
											<s:else>
											<s:if test="%{selectedOrder.actualCharges.size ==0}">
												<s:select value="%{statusText}"
													cssClass="text_01_combo_big" cssStyle="width:140px;"
														name="quotedChargeStatusText"
															list="{'Pending Release','Ready to Invoice'}" theme="simple" />
															</s:if>
															<s:else><s:property value="statusText" /></s:else>
												</s:else>
											</td>	
											<s:if test="%{selectedOrder.actualCharges.size ==0}">
										<td class="ordrdtl_title_val">
												<span style="width:110px; float:left;">
												<s:a onclick="return confirm('Do you really want to delete the selected charge?')"  cssStyle="padding:3px 10px; background-color:#990000; color:#FFF; text-decoration:none; font-size:12px; font-weight:bold;" href="delete.quoted.charge.shipment.action?method=deletetCharge&id=%{id}">
												
													<mmr:message messageId="btn.delete"/> 
													<!--<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Delete Charge" border="0"> -->
												</s:a>
												</span>
										</td>													
											</s:if>		
										</s:if>
										<s:else>
											<td class="ordrdtl_title_val"><s:property value="name" /></td>
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
											<td class="ordrdtl_title_val"><s:property
												value="tariffRate" /></td>	
												<td class="ordrdtl_title_val"><s:property value="cost" /></td>
												<td class="ordrdtl_title_val">
                                               <s:if test="%{costcurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{costcurrency==2}">
                                           
                                           USD
                                           
                                            </s:elseif>
											</td>
																					
											</s:if>
											<!-- this to hide the charge for user customer_shipper -->
											<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
 											<td class="ordrdtl_title_val"><s:property value="charge" /></td>
											</s:if>
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
                                          
                                           <td class="ordrdtl_title_val">
                                           <s:if test="%{chargecurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{chargecurrency==2}">
                                           
                                           USD
                                           </s:elseif>
										   <td class="ordrdtl_title_val">
                                           <s:property value="exchangerate" />
                                           </td>
										   </s:if>
											<td class="ordrdtl_title_val">												
 													<s:if test="%{cancelledInvoice=='Yes'}">
											<mmr:message messageId="status.cancelled"/> 
											</s:if>
											<s:else>
 												<s:property	value="statusText" />
											</s:else>												
											</td>
											<td></td>
										</s:else>

									</tr>
								</s:iterator></td>
							</tr>
							<s:if test="%{selectedOrder.actualCharges.size ==0}">
							<s:if test="%{selectedOrder.quotedCharges.size()>0 && status != 30 && #session.ROLE.contains('busadmin')||selectedOrder.quotedCharges.size()>0 && status != 30 && #session.ROLE.contains('solutions_manager')}">
							<tr>							
									<td align="left" colspan="6" class="ordrdtl_title_val" style="padding:10px 3px;">
										<a href="javascript: updateQuotedCharge()" style="padding:3px 10px; color:#FFF; background-color:#990000;font-weight:bold; font-size:12px; text-decoration:none; margin:3px 0px;">
											<mmr:message messageId="btn.savecharge"/>  
										<!--<img border="0" src="<s:url value="/mmr/images/update_charge_btn.png" includeContext="true" />"	>-->
										
										
										</a>
									</td>
									
							</tr>
							</s:if>
							</s:if>
							</table>
							<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
							<s:if test="%{selectedOrder.actualCharges.size ==0}"> 
						 <table width="940px" cellpadding="2" cellspacing="0" style="font-size:12px; background-color:#e7e7e7;">
							<tr style="background-color:#d1d1d1; width:100%;">
									<td  class="ordrdtl_title" ><strong><mmr:message messageId="label.ghead.carrier"/> :</strong></td>
									<td  class="ordrdtl_title" ><strong><mmr:message messageId="label.ghead.code"/> :</strong></td>
									<td  class="ordrdtl_title"><strong><mmr:message messageId="label.ghead.newcharge"/> :</strong></td>
									<td> </td>
									<td  class="ordrdtl_title" ><strong style="width:65px !important; float:left;"><mmr:message messageId="label.ghead.cost"/> :</strong></td>
									<td class="ordrdtl_title" width="12"><strong><mmr:message messageId="label.ghead.cur"/> :</strong></td>
									<!-- this is to hide the charge for customer_shipper user-->
									<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
									<!-- this is to hide the charge for customer_shipper user-->
								<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
 								 <td class="ordrdtl_title"><strong style="width:85px !important; float:left;"><mmr:message messageId="label.ghead.charge"/> :</strong></td>
								</s:if>
									</s:if>
									<td class="ordrdtl_title" width="12"><strong><mmr:message messageId="label.ghead.cur"/> :</strong></td>
                                <td class="ordrdtl_title"><strong style="width:75px !important; float:left;"><mmr:message messageId="label.ghead.exrate"/> :</strong></td>
							   <td class="ordrdtl_title" ><strong>&nbsp;</strong></td>
							   <td class="ordrdtl_title" ><strong>&nbsp;</strong></td>

							</tr>
							<tr>
								 <td>
								    	<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" listKey="id" listValue="name" headerKey="" headerValue="Select" name="newQuotedCharge.carrierId" list="#session.CARRIERS" 
										id="quoteCarrierId" theme="simple" onchange="quoteListChargeCode(this.value)"/>
									</td>									
								    <td align="left">
										<div id="quoteChargeCode">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newQuotedCharge.chargeCode" list="{}"
										 theme="simple"/>
								       </div>
								    </td>
									<s:url id="carrierChargesList" action="listCarrierCharges" />
									                                   <td align="left" width="35%">
										<div id="quoteChargeName">
										<s:select cssClass="text_01_combo_big" id="quoteSearchString" cssStyle="width:120px;" headerKey="" headerValue="Select"  name="newQuotedCharge.name" list="{}" 
										 theme="simple"/>
								       </div>
									</td>
									
									<!-- <s:url id="carrierChargesList" action="listCarrierCharges" />
		

									<td class="ordrdtl_title_val"><sx:autocompleter keyName="newQuotedCharge.chargeId"
										name="searchString" href="%{carrierChargesList}"
										dataFieldName="carrierChargesSearchResult"
										cssStyle="width:190px;" loadOnTextChange="true"
										loadMinimumCount="3" />
									</td>-->

									<td>&nbsp;</td>

									<td class="ordrdtl_title_val">
										<s:textfield size="5"
										key="newQuotedCharge.cost" name="newQuotedCharge.cost"
										cssClass="text_02" />
									</td>
									<td class="ordrdtl_title_val">
                                               <s:select value="%{statusText}"
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                       name="newQuotedCharge.costcurrency" headerKey="1" 
                                                           list="currencyList" listKey="id" listValue="currencyCode" theme="simple" />
                                               </td>
									<!-- <td>&nbsp;</td> -->
									<td class="ordrdtl_title_val">
										<s:textfield size="5"
										key="newQuotedCharge.charge" name="newQuotedCharge.charge"
										cssClass="text_02" />
									</td>
									<td class="ordrdtl_title_val">
                                   
                                               <s:select  cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                        name="newQuotedCharge.chargecurrency" headerKey="1" 
                                                           list="currencyList" listKey="id" listValue="currencyCode"  theme="simple" />
                                               </td>
                                               
                                   <td class="ordrdtl_title_val">
                                               <s:textfield size="5"
                                                    name="newQuotedCharge.exchangerate"
                                                    cssClass="text_02" />
                                           </td>    
									<td class="ordrdtl_title_val"><s:select
										value="%{newQuotedCharge.statusText}" id="status"
										cssClass="text_01_combo_big" cssStyle="width:140px;"
										name="newQuotedCharge.statusText"
										list="{'Pending Release','Ready to Invoice'}" theme="simple" />
									</td>
									<td class="ordrdtl_title_val">
									<span style="width:100px; float:left;">
										<a href="javascript: addQuotedCharge()" style="padding:3px 10px; background-color:#990000; width:auto; color:#FFF;font-weight:bold; FONT-SIZE:12PX; text-decoration:none; float:left;">
											<mmr:message messageId="btn.add"/> 
										<!--<img border="0" src="<s:url value="/mmr/images/add_product.png" includeContext="true" />-->
										
</a>
										</span>
										</td>
								</tr>
								
							</s:if>
							</s:if>
							</table>
							</div>
							<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
								<!--<table width="960px" cellpadding="3" cellspacing="0" style="margin-left: 10px;">
								
								
							</table>-->
						</s:if>						
			</s:if> 
							</div>
								<div class="content_header  borderLeftRight">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.actualcharges"/>  :</div>
								<div class="cont_hdrtitle_l" style="width:200px">
								<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
								<s:label name="curr" value="%{#session.DefaultCurrencySymbol}"/>
								<s:text name="format.customMoney" ><s:param name="value" value="%{selectedOrder.totalChargeActual}" /></s:text>
								</s:if>
					</div>
					<s:if test="%{selectedOrder.actualCharges.size >0 && #session.ROLE.contains('busadmin')||selectedOrder.actualCharges.size >0 && #session.ROLE.contains('solutions_manager')}">
					<div id="copy2actual" style="float:right; width:285px; margin-top:-2px; ">
						<div class="fields" >
							<label style=" width:130px !important; text-align:right; padding-right:5px;">
		<a href="javascript: copyTheActual();"  style="text-decoration: none; color:#FFF; font-size:12px;"><mmr:message messageId="label.heading.copycharges"/> </a>
							</label>	
							<div class="ordrdtl_title_val controls"><s:select id="copy_the_actual"  
												cssClass="text_01_combo_big" cssStyle="width:140px;"
													name="actualChargeStatusText"
														list="{'Ready to Invoice','Pending Release','Cancelled'}" theme="simple" />
							</div>
							
						</div>
					</div>
					</s:if>
					</div>
					<div style="width:958px;height:auto;float:left;overflow-x:scroll;">
						<table width="958px" cellpadding="2" cellspacing="0" style="font-size:12px; background-color:#e7e7e7;">
							
							
							<tr style="background-color:#d1d1d1; width:100%;  font-size:12px;">
								<td class="ordrdtl_title_hdng" ><strong style="width:102px !important; float:left;"><mmr:message messageId="label.ghead.carrier"/> </strong></td>
							   <td class="ordrdtl_title_hdng" ><strong style="width:58px !important; float:left;"><mmr:message messageId="label.ghead.code"/> </strong></td>
								<td class="ordrdtl_title_hdng" ><strong style="width:110px; float:left;"><mmr:message messageId="label.ghead.chargename"/></strong></td>

								<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
									<td class="ordrdtl_title_hdng"  ><strong style="width:60px; float:left;"><mmr:message messageId="label.ghead.tariff"/> </strong></td>
									<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.cost"/> </strong></td>
									<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.cur"/> </strong></td>
 								</s:if>

								<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.charge"/> </strong></td>
								<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
                               <td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.cur"/> </strong></td>
                               <td class="ordrdtl_title_hdng" ><strong style="width:80px; float:left;"><mmr:message messageId="label.ghead.exrate"/> </strong></td>
                               </s:if>
								<td class="ordrdtl_title_hdng" ><strong><mmr:message messageId="label.ghead.status"/> </strong></td>
								<td class="ordrdtl_title_hdng" ><strong style="width:80px; float:left;"><mmr:message messageId="label.ghead.invoice"/> #</strong></td>
								<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
									<td class="ordrdtl_title_hdng" ><strong>&nbsp;<mmr:message messageId="label.ghead.edi"/> #</strong></td>
								</s:if>
								<td class="ordrdtl_title_hdng" >&nbsp;</td>

							</tr>
							<tr>
								<td valign="top">
						<s:set var="index" value="0" />		
								<s:iterator id="actualCharges"
									value="selectedOrder.actualCharges" status="row">
									<tr>
										<!-- status=30=Billed -- >
										<!-- Unbilled charges will only be displayed to Business Admin -->
                                        <td class="ordrdtl_title_val">
												<s:property value="carrierName" />
										</td>
										<!-- <td style="text-align: center" class="ordrdtl_title_val">
												<s:property value="chargeCode" />
									          </td> -->
										<td style="text-align: left" class="ordrdtl_title_val">
											<s:if test="%{name == 'Freight'}">
												<s:if test="%{#session.ROLE.contains('busadmin') && selectedOrder.slaveServiceId !=null && selectedOrder.slaveServiceId > 0||#session.ROLE.contains('solutions_manager')&& selectedOrder.slaveServiceId !=null && selectedOrder.slaveServiceId > 0}">
										    	<s:property value="selectedOrder.slaveServiceId" />
										    </s:if>
										    <s:else>
												<s:property value="selectedOrder.serviceId" />
											</s:else>
											</s:if>
											<s:else>
												<s:property value="chargeCode" />
											</s:else>
										</td>
										<s:if test="%{status != 30 && #session.ROLE.contains('busadmin') && cancelledInvoice!='Yes'||status != 30 && #session.ROLE.contains('solutions_manager')&& cancelledInvoice!='Yes'}">
											<td style="width:100px !important; ">
												<s:textfield size="10"
													key="actualChargeName" name="actualChargeName"
													value="%{name}" cssClass="text_02" />
											</td>
										<%--	 <td class="ordrdtl_title_val">
												<s:property value="tariffRate" />
											</td> --%>

											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
											<td class="ordrdtl_title_val"  >
												<s:property value="tariffRate" />
											</td>
											<td class="ordrdtl_title_val"><s:textfield size="5"
												key="actualChargeCost" name="actualChargeCost"
												value="%{cost}" cssClass="text_02" cssStyle="text-align:right; padding-right:5px;" /></td>
												<td class="ordrdtl_title_val">
                                               <s:select value="%{statusText}"
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                       name="actualcostcurrency" value="%{costcurrency}" headerKey="0" headerValue=" "
                                                           list="currencyList" listKey="id" listValue="currencyCode"  theme="simple" />
                                               </td>
											
											</s:if>
											<td class="ordrdtl_title_val"><s:hidden
												name="actualChargeIds" value="%{id}" /> <s:textfield
												size="5" key="actualCharge" name="actualCharge"
												value="%{charge}" cssClass="text_02" cssStyle="text-align:right; padding-right:5px;"/></td>
												<td class="ordrdtl_title_val">
                                   
                                               <s:select  cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                       name="actualchargecurrency" value="%{chargecurrency}" headerKey="0" headerValue=" "
                                                           list="currencyList" listKey="id" listValue="currencyCode"   theme="simple" />
                                               </td>   
                                               <td class="ordrdtl_title_val">
                                               <s:textfield size="5"
                                                    name="actualexchangerate" value="%{exchangerate}"
                                                    cssClass="text_02" />
											<td class="ordrdtl_title_val">
											<s:if test="%{cancelledInvoice=='Yes'}">
											<mmr:message messageId="status.cancelled"/>
											</s:if>
											<s:else>
												
												<s:select value="%{statusText}"
													cssClass="text_01_combo_big" cssStyle="width:126px;"
														name="actualChargeStatusText"
															list="{'Pending Release','Ready to Invoice'}" theme="simple" />
												</s:else>
											</td>	
											<td class="ordrdtl_title_val">
												<s:property value="invoiceNum" />
											</td>
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
												<s:if test="%{ediInvoiceNumber == null || ediInvoiceNumber ==''}">
													<td class="ordrdtl_title_val"><s:textfield size="4" key="actualEdiInvoiceNumber" 
													name="actualEdiInvoiceNumber" value="%{ediInvoiceNumber}"/></td>
 												</s:if>
												<s:else>
												<s:hidden name="actualEdiInvoiceNumber" value="%{ediInvoiceNumber}"/>
 													<td class="ordrdtl_title_val"><s:property value="ediInvoiceNumber" /></td>
												</s:else>
 											</s:if>

											<td>
												<span style="width:110px; float:left;">
												<s:a onclick="return confirm('Do you really want to delete the selected charge?')" cssStyle="background-color:#990000; color:#FFF; padding:3px 10px;font-weight:bold; width:auto;  text-align:center; float:left; font-size:12px; text-decoration:none;"href="delete.actual.charge.shipment.action?method=deletetCharge&id=%{id}">
													<mmr:message messageId="btn.delete"/> 
													
													<!--<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Delete Charge" border="0"> -->
												</s:a>
												</span>
											</td>
										</s:if>
										<s:else>
											<td ><s:property value="name" /></td>
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
											<td class="ordrdtl_title_val">
												<s:property value="tariffRate" />
											</td>
												<td class="ordrdtl_title_val"><s:property value="cost" /></td>
											<td class="ordrdtl_title_val">
                                               <s:if test="%{costcurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{costcurrency==2}">
                                           
                                           USD
                                           </s:elseif>
                                               </td>
											</s:if>
											<!-- this is to hide the charges for customer_shipper user-->
											
											<s:if test="%{!#session.ROLE.contains('customer_shipper')}">
 											<td class="ordrdtl_title_val"><s:property value="charge" /></td>
											</s:if>
											 <s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
                                           
                                           <td class="ordrdtl_title_val">
                                           <s:if test="%{chargecurrency==1}">
                                           CAD
                                           </s:if>
                                           <s:elseif test="%{chargecurrency==2}">
                                           
                                           USD
                                           </s:elseif>
                                           </td>
                                           <td class="ordrdtl_ratetitle_val">
                                           <s:property value="exchangerate" />
                                           </td>
											
											</s:if>
											<td class="ordrdtl_title_val">											
 													<s:if test="%{cancelledInvoice=='Yes'}">
												<mmr:message messageId="status.cancelled"/>
											</s:if>
											<s:else>
 												<s:property	value="statusText" />
											</s:else>										
											</td>
											<td class="ordrdtl_title_val">
												<s:property value="invoiceNum" />
											</td>	
											<!--<s:if test="%{#session.ROLE.contains('busadmin')}">
												<td class="ordrdtl_title_val"><s:property value="ediInvoiceNumber" /></td>
											</s:if>-->
											<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
												<s:if test="%{ediInvoiceNumber == null || ediInvoiceNumber ==''}">
													<td class="ordrdtl_title_val"><s:textfield size="4" key="actualEdiInvoiceNumber" 
													name="actualEdiInvoiceNumber" value="%{ediInvoiceNumber}"/></td>
 												</s:if>
												<s:else>
												<s:hidden name="actualEdiInvoiceNumber" value="%{ediInvoiceNumber}"/>
 													<td class="ordrdtl_title_val"><s:property value="ediInvoiceNumber" /></td>
												</s:else>
 											</s:if>
											<td></td>
											
										</s:else>


									</tr>
							<s:set var="index" value="#index+1" />
								</s:iterator></td>
							</tr>
							</table>
							</div>
							<div style="width:958px;height:auto;float:left;overflow-x:scroll;">
                           <table width="958px" cellpadding="2" cellspacing="0" style="font-size:12px;">
                           <s:if test="%{selectedOrder.actualCharges.size()>0 && status != 30 && #session.ROLE.contains('busadmin')||selectedOrder.actualCharges.size()>0 && status != 30 && #session.ROLE.contains('solutions_manager')}">
							<tr>							
									<td align="left" colspan="7" class="ordrdtl_title_val" style="padding:10px 5px;"><a
										href="javascript: updateActualCharge()" style="background-color:#990000; color:#fff;font-weight:bold; font-size:12px; text-decoration:none; padding:3px 10px;">
										<mmr:message messageId="btn.savecharge"/> 
										
										<!--<img border="0"
										src="<s:url value="/mmr/images/update_charge_btn.png" includeContext="true" />"
										style="padding-bottom: 3px;">-->
										
										
										</a></td>
							</tr>
							</s:if>
							
							<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
								<tr style="background-color:#d1d1d1;">
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.carrier"/> :</strong></td>
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.code"/> :</strong></td>
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.newcharge"/> :</strong></td>
									<td class="ordrdtl_title">&nbsp;</td>
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.cost"/> :</strong></td>
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.cur"/> :</strong></td>	
									<td  class="ordrdtl_title" align=""><strong style="width:80px; float:left;"><mmr:message messageId="label.ghead.charge"/> :</strong></td>
									<td  class="ordrdtl_title" align=""><strong><mmr:message messageId="label.ghead.cur"/> :</strong></td>
                                    <td  class="ordrdtl_title" align=""><strong style="width:80px; float:left;"><mmr:message messageId="label.ghead.exrate"/> :</strong></td>
									<td class="ordrdtl_title">&nbsp;</td>
									<td class="ordrdtl_title">&nbsp;</td>
									<td class="ordrdtl_title">&nbsp;</td>
									<td class="ordrdtl_title">&nbsp;</td>
								</tr>
								<tr>
							<%-- 		<s:url id="carrierChargesList" action="listCarrierCharges" />
									<td align="left" width="35%"><sx:autocompleter keyName="newActualCharge.chargeId"
										name="searchString" href="%{carrierChargesList}"
										dataFieldName="carrierChargesSearchResult"
										cssStyle="width:190px;" loadOnTextChange="true"
										loadMinimumCount="3" /> --%>
									<td>
								    	<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" listKey="id" listValue="name" headerKey="" headerValue="Select" name="newActualCharge.carrierId" list="#session.CARRIERS" 
										id="carrierId" theme="simple" onchange="listChargeCode(this.value)"/>
	
									</td>
							<%--	<td align="left">
										<s:textfield size="7"
										key="newActualCharge.cost" name="newActualCharge.cost"
										cssClass="text_02" /> --%>
							    <td align="left">
										<div id="chargeCode">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newActualCharge.chargeCode" list="{}" 
										 theme="simple"/>
								       </div>
							    </td>
									<s:url id="carrierChargesList" action="listCarrierCharges" />
									<td align="left" width="35%">
										<div id="chargeName">
										<s:select cssClass="text_01_combo_big" cssStyle="width:115px;" headerKey="" headerValue="Select"  name="newActualCharge.name" list="{}" 
										 theme="simple"/>
								       </div>

									</td>
									<td>&nbsp;</td>
									<td align="left">
									  <s:textfield size="5"
										key="newActualCharge.cost" name="newActualCharge.cost"
										cssClass="text_02" cssStyle="text-align:right; padding-right:5px;" />
									</td>
									<td>
                                               <s:select value="%{statusText}"
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                       name="newActualCharge.costcurrency" 
                                                           list="currencyList" listKey="id" listValue="currencyCode" theme="simple" />
                                               </td>
									<td align="left">
									<s:textfield size="5"
										key="newActualCharge.charge" cssStyle="text-align:right; padding-right:5px;"  name="newActualCharge.charge"
										cssClass="text_02" />
									</td>
									<td>
                                               <s:select value="%{statusText}"
                                                   cssClass="text_01_combo_big" cssStyle="width:61px;"
                                                        name="newActualCharge.chargecurrency" 
                                                           list="currencyList" listKey="id" listValue="currencyCode" theme="simple" />
                                               </td>
                                   <td >
                                               <s:textfield size="5"
                                                    name="newActualCharge.exchangerate"
                                                    cssClass="text_02" />
                                           </td>
									<td align="left" colspan="2"><s:select
										value="%{newActualCharge.statusText}" id="status"
										cssClass="text_01_combo_big" cssStyle="width:128px;"
										name="newActualCharge.statusText"
										list="{'Pending Release','Ready to Invoice'}" theme="simple" />
									</td>
									<td align="left" colspan="2">
									<span style="width:75px; float:left;">
									<a href="javascript: addActualCharge()" style="background-color:#990000; color:#fff;font-weight:bold; font-size:12px; width:auto; text-align:center; text-decoration:none; padding:3px 10px; float:left;">	
										<mmr:message messageId="btn.add"/>
										<!--<img border="0"
										src="<s:url value="/mmr/images/add_product.png" includeContext="true" />"
										>-->
										</a>
										</span>
										</td>
								</tr>
						</s:if>
					</table></div>
						<div id="payment_rqd_end">&nbsp;</div>
			
			<!-- Start: Payment Info Module -->
			<s:if test="%{selectedOrder.ccTransactions.size > 0}"> <!--  Condition to display the Payment Info Panel, Shows if there are CCtransactions, else doesnt show. -->
			
			<div id="payment_info_table">
			<div class="content_header">
			<div class="cont_hdrtitle_l" id="payment_inform" style=" color:#FFF; width:200px; font-size:12px; font-weight:bold; margin:1px 0px 0px 400px;">&nbsp;&nbsp;<mmr:message messageId="label.heading.paymentinfo"/> </div>
			</div>
					<%-- <display:table id="payment_info" name="selectedOrder.ccTransactions" export="false" uid="row" cellspacing="0" cellpadding="3">
					<display:column headerClass="payment_info_tableTitle" sortable="true" title="" />
					<display:column headerClass="payment_info_tableTitle" property="authNum"  sortable="true" title="Auth #" ></display:column>
					<display:column headerClass="payment_info_tableTitle" property="statusString"  sortable="true" title="Status" ></display:column>
					<display:column headerClass="payment_info_tableTitle" format="{0,number,currency}" property="amount"  sortable="true" title="Amount" ></display:column>
					<display:column headerClass="payment_info_tableTitle" property="referenceNumber"  sortable="true" title="Ref #" ></display:column>
					<display:column headerClass="payment_info_tableTitle" property="processorTransactionId"  sortable="true" title="Processor Ref #" ></display:column>
					<display:column headerClass="payment_info_tableTitle" property="cardNumCharged"  sortable="true" title="CC #" ></display:column>
					<!-- Implementation of Refund Charge based on Role and Status: Status should be 30 for 'Processed' -->
					<s:if test="%{#session.ROLE.contains('busadmin') && #status==30||#session.ROLE.contains('solutions_manager') && #status==30}">
						<display:column headerClass="payment_info_tableTitle" sortable="true" title="">
						<a href="">Refund Charge</a> <!-- Implementation of Refund Charge Logic. -->
						</display:column>
					</s:if>					
				</display:table> --%>
				<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left; width:100%; height:auto;">
    <thead>
		<tr style="height:20px">
			<th><span style="width:200px !important; color:#fff !important;font-weight: bold; 
			"><mmr:message messageId="label.ghead.auth"/> #</span></th>
			<th><span style="width:200px !important;color:#fff !important;font-weight: bold;
			"><mmr:message messageId="label.ghead.status"/> </span></th>
			<th><span style="width:110px !important; float:left;color:#fff !important;font-weight: bold;
			"><mmr:message messageId="label.ghead.amount"/> </span></th>
			<th><span style="width:80px !important; float:left;color:#fff !important;font-weight: bold;
			"><mmr:message messageId="label.ghead.ref"/> #</span></th>
			<th><span style="width:180px !important; float:left;color:#fff !important;font-weight: bold;
			"><mmr:message messageId="label.ghead.processorref"/> #</span></th>
			<th style="width:70px !important;"><span style="width:70px !important; float:left;color:#fff;font-weight: bold;
			"><mmr:message messageId="label.ghead.cc"/> #</span></th>
			<s:if test="%{#session.ROLE.contains('busadmin') && #status==30||#session.ROLE.contains('solutions_manager') && #status==30}">
			<th></th>
			</s:if>
		</tr>
	</thead>
	<tbody>
            <s:iterator id="ccTransactions" value="selectedOrder.ccTransactions" status="rowstatus">
             <tr>
                 <td  style="padding-left: 39px;background-color: #E7E7E7 !important;"><s:property value="authNum"/></td>
 <td style="padding-left: 60px;background-color: #E7E7E7 !important;"><s:property value="statusString"/></td>
 <td style="padding-left: 37px;background-color: #E7E7E7 !important;"><s:property value="amount"/></td>
 <td style="padding-left: 3px;background-color: #E7E7E7 !important;"><s:property value="referenceNumber"/></td>
 <td style="padding-left: 55px;background-color: #E7E7E7 !important;"><s:property value="processorTransactionId"/></td>
 <td style="padding-left: 15px;background-color: #E7E7E7 !important;"><s:property value="cardNumCharged"/></td> 
<s:if test="%{#session.ROLE.contains('busadmin') && #status==30||#session.ROLE.contains('solutions_manager') && #status==30}">
	<td><a href=""><mmr:message messageId="label.btn.refundcharge"/></a></td> 
	</s:if>		
            </tr>			
            </s:iterator>
</tbody>
</table>
			</div>
		
		
			</s:if>
			<!-- End: Payment Info Module -->
			</div>	
							
						</div>
						</div>
						<div class="content_table" id="box2" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.heading.package"/> :</div>
								<div class="cont_hdrtitle_l" style="width:300px"><mmr:message messageId="label.heading.packagedetails"/> 
			 #&nbsp;<s:property value="%{selectedOrder.id}" /></div>
								<div class="form_buttons" >	
				<a   id="vw_shpmnt_nxt1" style="cursor:pointer"><mmr:message messageId="btn.back"/> </a>

								</div>
							</div>		
							<div class="cont_data_body" style="border-left: 1px solid #c4c2c2; border-right: 1px solid #c4c2c2; border-bottom:1px solid #c4c2c2; margin-bottom:10px; width:958.3px !important;">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.viewship.package"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.packageTypeId.name}" /></p>
										</div>
									</div>	
											<div class="fields">
										<label><mmr:message messageId="label.viewship.quantity"/> </label>
										<div class="controls">
											<span>:</span>
											<p><s:property
						value="%{selectedOrder.quantity}" /></p>
										</div>
									</div>
									<s:iterator value="selectedOrder.packages" status="counterIndex">
										<div class="fields">
										<label><mmr:message messageId="label.viewship.dimensionspack"/> <s:property
							value="%{#counterIndex.index+1}" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
							value="getText('{0,number,#,##0.0}',{selectedOrder.packages[#counterIndex.index].height})" />
						 <s:property
							value="getText('{0,number,#,##0.0}',{selectedOrder.packages[#counterIndex.index].width})" />
						 <s:property
							value="getText('{0,number,#,##0.0}',{selectedOrder.packages[#counterIndex.index].length})" /></p>
							</div>
							</div>
								<div class="fields">
										<label><mmr:message messageId="label.viewship.weightpack"/> <s:property
							value="%{#counterIndex.index+1}" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
							value="getText('{0,number,#,##0.0}',{selectedOrder.packages[#counterIndex.index].weight})" />
						lbs</p>
										</div>
									</div>	
										<div class="fields">
										<label><mmr:message messageId="label.viewship.carrierdimpack"/> <s:property
							value="%{#counterIndex.index+1}" /></label>
										<div class="controls">
											<span>:</span>
											<p><s:property
							value="%{selectedOrder.packages[#counterIndex.index].dimmedString}" /></p>
										</div>
									</div>
									
								</s:iterator>
									
								
							</div>
						</div>
						</div>
						<div class="content_table" id="box3" > 
						<jsp:include page="add_info_shipping.jsp"/>
						</div>
</div>
					</div>
</s:form>					
					</div>
			
</body>
</html>


