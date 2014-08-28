
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
	<head> 
	    <title><s:text name="search.shipment.title"/></title> 
	    <sj:head jqueryui="true" />
	    <sx:head />
		

<style type="text/css">
.autocomplete-suggestions {
border: 1px solid #999;
background: #FFF;
cursor: default;
width:300px !important;
overflow: auto;
-webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
-moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
}
#customerautocomplete,#auto{ background-position: 145px 4px; background-size:8px 8px; }
</style>	
		
	
	</head> 
<body> 


	<SCRIPT language="JavaScript">
	
		function disableChk()
		{
			if(document.getElementById("searchform_shippingOrder_statusId").value > 1)
				document.getElementById("cancelled").disabled = true;
			else
				document.getElementById("cancelled").disabled = false;
		}
		function searchShipment()
		{
				 document.searchform.action = "list.shipment.action";
			 	document.searchform.submit();
		}
		function saveShipmentList() {
			document.searchform.action = "save.shipment.action";
			document.searchform.submit();
		}	
		
		function getAccountInformation(url){
			window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
		}
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
			  url="<%=request.getContextPath()%>/markup.listService.action?value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
			  	ajax_Service.open("GET",url,true);
			  	ajax_Service.send(this);
		} // End function showState()	
		
		window.onload = disableChk;
		
		function repeatOrder()
		
		{

			var editUserId = document.getElementsByName("check_uncheck");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please select at least one');
			}
			else if(txt1 > 1){
				alert('Please check atmost one');
			}
			else{
				var temp = "";
								for (var i=0;i<editUserId.length;i++){
									if (editUserId[i].checked){
										temp = editUserId[i].value ;					
									}
								}
								
								var customs = document.getElementById("customsinvoice"+temp).value;
								if(customs != ""){
								var ALERT_BUTTON_TEXT = 'Ok';
								var CANCEL_BUTTON_TEXT = 'No';
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
								
								msg.innerHTML = "would you like to recreate the commercial invoice details with this shipment?";
								
								
								btnbodyObj = alertObj.appendChild(d.createElement("div"));
								btnbodyObj.id = "btnBody";
								
								
								btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
								btnconfirm.id = "confirmBtn";
								
								btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
								btnconfirm.href = "#";
								btnconfirm.focus();
								$('#confirmBtn').css('margin-left','127px');
								
								btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
								btnconfirmCancel.id = "confirmCancelBtn";
								btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
								btnconfirmCancel.href = "#";
								btnconfirmCancel.focus();
								$('#confirmCancelBtn').css('margin-right','111px');
								
								
								
								$('#confirmBtn').click(function(){
									removeCustomConfirm();
								
								
								
								
								
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
					}
				}
				
				document.searchform.action = "repeat.order.action?order_id="+txt;
				document.searchform.action = "repeat.order.action?order_id="+txt+"&customsinvoice=true";
				document.searchform.submit();
			
								});
												$('#confirmCancelBtn').click(function(){
													var i,txt;
													for (i=0;i<editUserId.length;i++){
														if (editUserId[i].checked){
															txt = editUserId[i].value ;					
														}
													}
													document.searchform.action = "repeat.order.action?order_id="+txt+"&customsinvoice=false";
													document.searchform.submit();
													
												});
												alertObj.style.display = "block";
												function removeCustomConfirm() {
												document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
												}
			//alert(oid);
								}
												else{
													
													var i,txt;
													for (i=0;i<editUserId.length;i++){
														if (editUserId[i].checked){
															txt = editUserId[i].value ;					
														}
													}
													document.searchform.action = "repeat.order.action?order_id="+txt;
													document.searchform.submit();
												}
										}
		}
	</SCRIPT>
		<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>

	<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>

	<div class="form-container"> 
		<s:form id="searchform" action="list.shipment.action" name="searchform">
		
		<div class="content_body" >	
							<div class="content_table" > 
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.search.shipments"/></div>
									<div class="form_buttons">
										<a href="search.shipment.action" >RESET</a>
										<a href="javascript: saveShipmentList()" >SAVE LIST</a>
										<a href="javascript: searchShipment()" >SEARCH</a>
									</div>	
								</div>
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields">
											<label>From Date </label>
											<div class="controls"><span>:</span>
											
												<s:textfield name="shippingOrder.fromDate" onClick="selectDate('f_date_c','f_trigger_c');" id="f_date_c" 
											 readonly="readonly"/>
											</div>
										</div>
										<div class="fields">
											<label>To Date </label>
											<div class="controls"><span>:</span>
											
												<s:textfield name="shippingOrder.toDate" onClick="selectDate('t_date_c','t_trigger_c');" id="t_date_c" 
											 readonly="readonly"/>
											</div>
										</div>
										<div class="fields">
											<label>Shipment Order # </label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.id" name="shippingOrder.id" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.track.carrier"/></label>
											<div class="controls"><span>:</span>
												<s:select   listKey="id" listValue="name" 
												name="shippingOrder.carrierId" headerKey="" headerValue=""  list="#session.CARRIERS" 
												onchange="javascript:showState();"  id="firstBox" theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.markup.service"/></label>
											<div class="controls" id="stateid"><span>:</span>
												<s:select  listKey="id" listValue="name"
												name="shippingOrder.serviceId" list="#session.SERVICES" 
												headerKey="-1" id="secondBox" theme="simple"/>		
											</div>
										</div>
										<div class="fields">
											<label>Tracking # </label>
											<div class="controls"><span>:</span>
												<s:textfield key="shippingOrder.masterTrackingNum" name="shippingOrder.masterTrackingNum" 
												 />
											</div>
										</div>
										 <s:if test="%{#session.ROLE.contains('busadmin')}">
										 <div class="fields">
											<label><mmr:message messageId="label.edi.invoiceNumber"/></label>
											<div class="controls"><span>:</span>
												<s:textfield key="shippingOrder.ediInvoiceNumber" name="shippingOrder.ediInvoiceNumber" 
												  />
											</div>
										</div>
										</s:if>
									<s:else>
									</s:else>
										<div class="fields">
											<label>Batch ID</label>
											<div class="controls"><span>:</span>
												<s:textfield  key="shippingOrder.batchId" name="shippingOrder.batchId"   />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.track.status"/></label>
											<div class="controls"><span>:</span>
												<s:select listKey="id"  listValue="name"    name="shippingOrder.statusId" headerKey="-1"  
												 list="#session.orderStatusList" theme="simple" onchange="disableChk()"/>
											</div>
										</div>
										<s:if test="%{#session.ROLE.contains('busadmin')}">
										<div class="fields">
											<label><mmr:message messageId="label.billing.status"/></label>
											<div class="controls"><span>:</span>
												<s:select  listKey="billingStatusId"  listValue="billingStatusText"  name="shippingOrder.billingStatus" headerKey="-1"  
												 list="#session.billingStatusList" theme="simple"/>
											</div>
										</div>
										</s:if>
										<div class="fields">
											<label><mmr:message messageId="label.reference"/></label>
											<div class="controls"><span>:</span>
												<s:textfield key="shippingOrder.referenceCode" name="shippingOrder.referenceCode" 
												 />
											</div>
										</div>
										<s:if test="%{#session.ROLE.contains('busadmin')}">
										<div class="fields">
											<label><mmr:message messageId="label.customer.name" /></label>
											<s:url id="customerList" action="listCustomers" />
												<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" name="shippingOrder.customerId"/>			

				<s:textfield id="customerautocomplete" />
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>
										</div>
										</s:if>
										<div class="fields">
											<label><mmr:message messageId="label.search.OrderBy" /></label>
											<div class="controls"><span>:</span>
														<s:select  
														name="shippingOrder.order" headerKey="1"
														list="{'Asc','Desc'}" cssStyle="width:65px;"/>
														<s:select 
														name="shippingOrder.orderBy" headerKey="1"
														list="{'Order #','Shipment Date'}" cssStyle="width:68px; margin-right:5px;"/>
														
											
												
											</div>
										</div>
										<div class="fields">
											<label style="width:180px !important;"><mmr:message messageId="label.include.cancelled.shipments"/></label>
											<div class="controls" style="width:35px !important; "><span>:</span>
												<s:checkbox id="cancelled" cssStyle="width:20px; margin:5px 0px 0px 0px; float:right;" key="shippingOrder.showCancelledShipments"
												name="shippingOrder.showCancelledShipments" />
											</div>
										</div>	
									</div>	
								</div>
							</div>
						</div>
					
		
		<div id="formResult">
		   <s:include value="list_shipment.jsp"></s:include>
		</div>
			
			
			
	</s:form>
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
	
</body>
</html>
					