<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<style>
                                    .autocomplete-suggestions{
	                              width:300px !important;
                                      }
                                  </style>
<div class="content">
	
	<div class="content_body">
		<div id="div_ship_from">
							<div class="content_table">
								<div class="cont_data_body borderLeftRight" >
									<div class="rows" >
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/></label>
											<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
										 <s:param name="shippingOrder.customerId" value="%{shippingOrder.customerId}"/>
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocompletes" value="%{shippingOrder.fromAddress.abbreviationName}" name="shippingOrder.fromAddress.abbreviationName"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>	</div>
										<div id="ship_from">
					<div class="fields">
											<label> <mmr:message messageId="label.address.button.save"/> </label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.saveFromAddress}"  name="shippingOrder.saveFromAddress"/>
											</div>
					</div>
					
					
									
								
						<div id="fromAdd_inner">
							<jsp:include page="fromAddress_inner.jsp"/>
						</div>
		</div>
					</div>
				</div>
			</div>
		</div>
	</div>						
</div>	
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

/* var customers = {
		<s:iterator value='#session.usersList'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; }); */

	var c;
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
		 	  // alert(x);
			 	  c=$.parseJSON(x);
			 	   //c=x;
			 	    }
			 	  }
			 	
			 var url="new.shipment.action?loadajax=true";
				
		 	xmlhttp.open("GET",url,false);
			 	xmlhttp.send();
		}
		//delete c["0"];
		var customersArray1 = $.map(c, function (value, key) { return { value: value, data: key }; });
	// Initialize autocomplete with local lookup:
      $('#customerautocompletes').newautocomplete({
       /* lookup: customersArray, */
       lookup: customersArray1,
       triggerSelectOnValidInput: false,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
			var resfrom = suggestion.value.split(",");
			 $('#customerautocompletes').val(resfrom[0]);
           $('#custId').val(suggestion.data);
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
						