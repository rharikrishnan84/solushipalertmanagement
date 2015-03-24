<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<sx:head/>
    <title><s:text name="user.form.title"/></title> 
	</head> 

<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
<script>

  $(document).ready(function(){
   $('.navi_icon').click(function(){
    $('.navigation ul').slideToggle();
   });

  });
 </script>
 <script type="text/javascript">
 $(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
   </script>
<script type="text/javascript">


var default_address_id_from =0;
var default_address_id_to =0;

window.onload = function() {

    	var e = document.getElementById("idRoles");
		var strRole = e.options[e.selectedIndex].value;
		if(strRole=='sales')
		document.getElementById("sales_div").style.display= "block";
		else
		document.getElementById("sales_div").style.display= "none";
		
		showHideSalesDiv('user.userRole');
		
};

	function submitform()
	{
		 
		document.userform.action = "createNation.action";
	     document.userform.submit();
	}
	
	function resetform(){
			
			document.userform.action = "addPartner.action";
	 		document.userform.submit();
	}		
	
	function showHideSalesDiv(valname)
	{
		//alert(valname);
		var e = document.getElementsByName(valname);
		//alert(e);
		var strRole = e[0].options[e[0].selectedIndex].value;
		//alert(strRole);
		if(strRole == 'sales'){		
			document.getElementById("sales_div").style.display= "block";
			}
		else{
		document.getElementById("sales_div").style.display= "none";
		}
		if(strRole == 'customer_admin')
		{
			document.getElementById("rules").innerHTML="- Unrestricted access to all functions within the customer account.<br/>";	
		}
		else if(strRole == 'customer_base')
		{
			document.getElementById("rules").innerHTML="- Access to Shipping information, including create new shipments and view all shipments created under the customer account.<br/> - Access to Address Book functionality. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - No access to invoicing module and other admin functions (such as user management).";	
		}		
		else if(strRole == 'customer_shipper')
		{
			document.getElementById("rules").innerHTML="- Restricted access to Shipping information.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Can create new shipments.<br/>- Can view only shipments created by this user.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- No access to invoicing module and other admin functions (such as user management).";	
		}
		else
		{
			document.getElementById("rules").innerHTML="";	
		}
		
		
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
		
	jQuery(function() {
		$("#defaultFromAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultFromAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("user.defaultFromAddressId").value=data[1];
			default_address_id_from = data[1];
			//document.getElementById("user.defaultFromAddressText").value=data[0];
			});
			
		$("#defaultToAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultToAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("user.defaultToAddressId").value=data[1];
			default_address_id_to = data[1];
			//document.getElementById("user.defaultToAddressText").value=data[0];
			});
		
		// $("#defaultFromAddText").click(function(){$("#defaultFromAddText").val('')});
         $("#defaultFromAddText").blur(function(){if($("#defaultFromAddText").val()==''){
         $("#defaultFromAddText").val('None');
         document.getElementById("user.defaultFromAddressId").value=0;
         }});
          
        // $("#defaultToAddText").click(function(){$("#defaultToAddText").val('')});
         $("#defaultToAddText").blur(function(){if($("#defaultToAddText").val()==''){
         $("#defaultToAddText").val('None');
         document.getElementById("user.defaultToAddressId").value = 0;
         }});		
			
		});
</script> 

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="content">
<div class="content_body">
<s:form action="createUser" name="userform" style="margin-bottom	:0px">
								<div class="content_table">
					<div class="content_header">
								<div class="cont_hdr_title">
									NATION
								</div>
							</div>
							
							
							<div class="cont_data_body">
							
							<div class="rows">
								<div class="fields">
										<label> 
										PARTNER LIST
										 </label>
										<div class="controls"><span>:</span>
									  	 <s:select cssClass="text_01" listKey="partnerId" listValue="partnerName" name="countrypartner.partner.partnerId" headerKey="-1"  headerValue="Select" list="partnerList" 
	                                onchange="" value="countrypartner.partnerId" disabled="true"  id="firstBox"  theme="simple"/> 
									 </div> 
									</div>
																
				</div>	
				</div>
				</div>
				 
					<div class="content_table">
				
					<div class="content_header">
					<div class="cont_hdr_title">
									ADDRESS
								</div>
								<div class="cont_hdr_title">
							</div>
							</div>
							<div class="cont_data_body">
							<div class="rows">
							
									 	
									<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address1"/></label>
											<div class="controls"><span>:</span>
											<label>
												<s:property value="%{countrypartner.countryAddress.address1}"/>
												</label>
											</div>
										</div>
									 <div class="fields">
											<label><mmr:message messageId="label.shippingOrder.address2"/></label>
											<div class="controls"><span>:</span>
												 <label>
												<s:property value="%{countrypartner.countryAddress.address2}"/>
												</label>
											</div>
										</div>
											<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.zip"/></label>
											<div class="controls"><span>:</span>
				                             	<label>
												<s:property value="%{countrypartner.countryAddress.postalCode}"/>
												</label>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="countrypartner.countryAddress.countryCode" headerKey="-1" headerValue="Select" list="#session.CountryList" 
	                  onchange="javascript:showShipFromState();"  id="firstBox" disabled="%{countrypartner.countryAddress.countryCode!=null}"  theme="simple"/>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.shipToId.state"/></label>
											<div class="controls" id="stateid" > 
													<label>
												<s:property value="%{countrypartner.countryAddress.provinceCode}"/>
												</label>
												</div>
											</div>
											 <div class="fields">
           <label><mmr:message messageId="label.shippingOrder.city"/></label>
           <div class="controls"> <span>:</span>
            										<label>
												<s:property value="%{countrypartner.countryAddress.city}"/>
												</label>
            
            </div>
           </div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.phone"/></label>
											<div class="controls"><span>:</span>
												<label>
												<s:property value="%{countrypartner.countryAddress.phoneNo}"/>
												</label>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.email"/></label>
											<div class="controls"><span>:</span>
																								<label>
												<s:property value="%{countrypartner.countryAddress.email}"/>
												</label>
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.attention"/></label>
											<div class="controls"><span>:</span>
												
											 <label>
												<s:property value="%{countrypartner.countryAddress.contactName}"/>
												</label>
											</div>
										</div>
					 
									
							</div>
							</div>
							</div> 
		</s:form> 
	</div>
</div>	

<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</body>
</html>
    
    
  