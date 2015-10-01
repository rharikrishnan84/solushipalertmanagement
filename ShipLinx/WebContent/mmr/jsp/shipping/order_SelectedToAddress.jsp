<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="content">
<div class="content_body">	
<div id="div_ship_to">
	<div class="content_table" id="to_add_inner_table">
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/> </label>
											<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
										 <s:param name="shippingOrder.customerId" value="%{shippingOrder.customerId}"/>
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custIdto" />			

				<s:textfield id="customerautocompto"value="%{shippingOrder.toAddress.abbreviationName}" name="shippingOrder.toAddress.abbreviationName"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>	</div>
										<div id="ship_to">
					<div class="fields">
											<label><mmr:message messageId="label.address.button.save"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.saveToAddress}"  name="shippingOrder.saveToAddress"/>
											</div>
					</div>
											
				
					<div id="toAdd_inner">
						<jsp:include page="toAddress_inner.jsp"/>
					</div>
				</div>	
				</div>
			</div>
		</div>
	</div>
</div>
</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

/*// var customers = {
		<s:iterator value='#session.usersList'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; }); */
	
	var c2;
		   ajaxJson();
		 
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
				 	    c2=$.parseJSON(x);
				 	    //c2=x;
				 	    }
				 	  }
				 var url="new.shipment.action?loadajax=true";
				 	xmlhttp.open("GET",url,false);
				 	xmlhttp.send();
			}
		delete c2["0"];
		//alert(c);
			
			var customersArray2 = $.map(c2, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocompto').newautocomplete({
       /* lookup: customersArray, */
       lookup: customersArray2,
       triggerSelectOnValidInput: false,
		minChars: 0,
		onSelect: function (suggestion) {			
		if(suggestion.value != ""){
			 var resto = suggestion.value.split(",");
			 $('#customerautocompto').val(resto[0]);
           $('#custIdto').val(suggestion.data);
          
			searchto();
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