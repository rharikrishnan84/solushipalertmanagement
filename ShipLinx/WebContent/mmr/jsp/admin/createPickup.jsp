<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html>
<head> 
	    <title><s:text name="search.shipment.title"/></title> 
	    <sj:head jqueryui="true" />
	    <sx:head />
	    <script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
	</head>
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
<body>

<SCRIPT language="JavaScript">
	var dojoAdd_key = 0;
		function showState() {
			ajax_Service=ajaxFunction();
			ajax_Service.onreadystatechange=function()
			  {
				   if(ajax_Service.readyState==4)
					{
					reponse=ajax_Service.responseText;
					js_stateid=document.getElementById("stateid");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox');
			  url="<%=request.getContextPath()%>/markup.listService.action?value="+firstBox.value+"&pickup='true'";
				//param="objName=ref_state&country_id="+country_id;
			  	ajax_Service.open("GET",url,true);
			  	ajax_Service.send(this);
		} // End function showState()
		
		function searchShipment()
		{
			document.searchpickupform.action = "list.pickups.action";
			document.searchpickupform.submit();
		}
		
		function searchfrom()
			{
				//alert(customerval);
				var abbreviationName=document.getElementById("customerautocompto").value;
				abbreviationName=abbreviationName.split(',')[0];
				document.getElementById("customerautocompto").value=abbreviationName;
				ajax_ChangeTo=ajaxFunction();
					ajax_ChangeTo.onreadystatechange=function()
				  	{
						   if(ajax_ChangeTo.readyState==4)
							{
							reponse=ajax_ChangeTo.responseText;
							js_stateid=document.getElementById("pickup_address");
							js_stateid.innerHTML= reponse;
							}
				 	}
					  var addressId=document.getElementById("custIdto").value;
					  var url = "selectShippingAddress.action?addressid=" + addressId+"&type=pickupNew";
					ajax_ChangeTo.open("GET",url,true);
					ajax_ChangeTo.send(this);		
			}
		
	function submitPickup(){
		//alert("Pickup Submitted");
		document.searchpickupform.action = "createPickup.action";
		document.searchpickupform.submit();
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
	
	function getkey(e){
		if (window.event)
		  return window.event.keyCode;
		else if (e)
		  return e.which;
		else
		  return null;
	}
	function changeCustomer()
			{
			//alert(customerval);
				var customerId=document.getElementById("custId").value;
				document.searchpickupform.action = "shipment.setcustomer.action?customerId=" + customerId+"&type=pickup";
				document.searchpickupform.submit();
				
			}
</SCRIPT>
<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
		
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
			
		});
		
		
		
	</script>
	
<script>
	$(window).load(function() {
	
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div class="content">
	<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
</div>

	<div class="form-container"> 
		<s:form id="searchpickupform" action="search.pickups.action" name="searchpickupform">
		<!-- <div class="newshipment" id="contenttbl"> -->
	<!-- <div class="content">	 -->
		
			<div class="content_body" >	
							<div class="content_table"> 
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.create.pickup"/></div>
									<div class="form_buttons">
										<a href="search.pickups.action"><mmr:message messageId="label.btn.cancel"/></a>	
										<a href="javascript: submitPickup()"><mmr:message messageId="label.submit"/></a>
									</div>
								</div>	
								<s:if test="%{#session.ROLE.contains('sysadmin')}">
											
								<!--for change customer  -->
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
                        </s:if>	
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.pickup.date"/></label>
											<div class="controls"><span>:</span>
											
												<s:textfield name="pickup.pickupDate_web" onClick="selectDate('f_date_c','f_trigger_c');" id="f_date_c" 
											 readonly="readonly"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.pickup.readytime"/></label>
											<div class="controlspickup"><span>:</span>
												
													<s:select value="%{pickup.readyHour}" name="pickup.readyHour" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}" cssStyle="width: 46px;"  cssStyle="width: 46px;" ></s:select>
													&nbsp;<s:select value="%{pickup.readyMin}" name="pickup.readyMin" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}" cssStyle="width: 46px;"  ></s:select>
												
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pickup.closetime"/></label>
											<div class="controlspickup"><span>:</span>
												<s:select value="%{pickup.closeHour}" name="pickup.closeHour" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}" cssStyle="width: 46px;" class="text_01" ></s:select>
								&nbsp;<s:select value="%{pickup.closeMin}" name="pickup.closeMin" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}" cssStyle="width: 46px;"  ></s:select>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pickup.preferred.location"/></label>
											<div class="controls"><span>:</span>
												<s:select value="%{pickup.pickupLocation}" name="pickup.pickupLocation" list="{'Shipping','Back Door','Downstairs','Front Door','Garage','Guard House','Mail Room','Office','Receiving','Reception','Side Door','Upstairs'}"   ></s:select>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.pickup.reference"/></label>
											<div class="controls"><span>:</span>
												<s:textfield  key="pickup.pickupReference" name="pickup.pickupReference"   value="%{pickup.pickupReference}"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.track.carrier"/></label>
											<div class="controls"><span>:</span>
												<s:select   listKey="id" listValue="name" 
												name="pickup.carrierId" list="#session.CARRIERS" 
												onchange="javascript:showState();"  id="firstBox" theme="simple"/>
											</div>
										</div>
										<div class="fieldsvvl">
											<label><mmr:message messageId="label.shippingOrder.company"/></label>
											<div class="controlsvvl"><span>:</span>
												<s:url id="customerList" action="listCustomersWithOrphan" />
										 <s:param name="shippingOrder.customerId" value="%{shippingOrder.customerId}"/>
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custIdto" />			

				<s:textfield id="customerautocompto" value="%{pickup.address.abbreviationName}" name="pickup.address.abbreviationName"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>	</div>
										<div id="pickup_address">
									<div class="fields" >
										<label><mmr:message messageId="label.markup.service"/></label>
										<div class="controls" id="stateid"><span>:</span>
											<s:select   listKey="id" listValue="name"
												name="pickup.serviceId" list="#session.SERVICES" 
													headerKey="-1" id="secondBox" theme="simple"/>		
											</div>
										</div>
					<s:if test="%{!#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">				
					<div id="pickup_inner">
						<jsp:include page="pickupAddress_inner.jsp"/>
					</div>
					</s:if>
		
		</div>
				
			
	</s:form>	
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">


		/*// var customers = {
		<s:iterator value='customerSearchResults'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 
 }; */
 
// var c2;
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
		 <script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
		 <script type="text/javascript"> 
		 		     	     
		   var c2;
 ajaxJson();

	/* delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; }); */
	
	function ajaxJson(){
				
					/* ajax_Service=ajaxFunction();
				ajax_Service.onreadystatechange=function(){
			 		if(ajax_Service.readyState==4){
			 			//alert(ajax_Service.readyState+"4");
			 		reponse=ajax_Service.responseText;
				 			//alert(reponse+" response");
				 		//alert("res "+reponse);
				 		var res = document.getElementById('ca1');
		 		res.innerHTML = response;
			 			}
			 		}
		 		url="new.shipment.action?loadajax=true";
			 		ajax_Service.open("GET",url,true);
				 	ajax_Service.send(this); */
				 	var xmlhttp;
			 	if (window.XMLHttpRequest)
				 	  {// code for IE7+, Firefox, Chrome, Opera, Safari
				 	  xmlhttp=new XMLHttpRequest();
				 	  }
				 	else
			 	  {// code for IE6, IE5
				 	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				 	  }
				 	xmlhttp.onreadystatechange=function()
				 	  {
				 	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
				 	    {
				 	var x=xmlhttp.responseText;
			 	    //alert(xmlhttp.responseText);
				 	   //alert(x);
				 	    c3=$.parseJSON(x);
				 	    //c2=x;
				 	    }
				 	  }
				 /* var url="new.shipment.action?loadajax=true"; */
				 var url="new.shipment.action?loadajax=true"+"&type=pickup";
				 	xmlhttp.open("GET",url,false);
				 	xmlhttp.send();
			}
		delete c3["0"];
		
			var customersArray3 = $.map(c3, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocompto').newautocomplete({
       /* lookup: customersArray, */
       lookup: customersArray3,
       triggerSelectOnValidInput: false,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
           $('#custIdto').val(suggestion.data);
           searchfrom();
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