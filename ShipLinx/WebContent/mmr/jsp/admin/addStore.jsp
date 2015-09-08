<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<style type="text/css">
.fieldsF					{ width:300px; margin-left:5px; float:left; padding:3px 0px; height:25px;font-size:12px;}
.fieldsF .controlsF				{ width:174px; float:left;font-size:12px;}
.fieldsF label				{ width:139px; float:left; padding-top:3px;font-size:12px;}
.fieldsF .controlsF p{ font-size:12px;}
.fieldsF .controlsF span { padding-top:3px; padding-right:10px; float:left;}
	.fieldsF .controlsF input[type="text"], .fields .controls input[type="password"],.fields .controls select{
				width:146px !important;
</style>
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
		 
		document.userform.action = "saveEcom.store.action";
	     document.userform.submit();
	}
	
	function resetform(){
			
			document.userform.action = "newEcom.store.action";
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
	
	
	
	
	
		
				function updateFreeshipLable(element) {
					var option_user_selection = element.options[ element.selectedIndex ].text;
					document.getElementById("freeshipLabel").innerHTML=option_user_selection;
				}
				
				function updateMarkupLabel(element) {
					var option_user_selection = element.options[ element.selectedIndex ].text;
					document.getElementById("markupLabel").innerHTML=option_user_selection;
					
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
				
				
				function toShoworHidemax(checked)
				{ 
					if(checked)
					{
						//alert("Show");
						document.getElementById("maxpack").style.display = 'block';			
					}
					else
					{
						//alert("Hide");
						document.getElementById("maxpack").style.display = 'none';			
					}		
				}
				
				 
		
</script> 

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="content">
<div class="content_body">
<s:form action="createUser" name="userform" style="margin-bottom	:0px">

<s:if test="#session.edit == 'true'">	
     	<s:hidden name="method" value="update"/>
    </s:if> 
    <s:else>
    	<s:hidden name="method" value="add"/> 
    	<s:hidden name="ecommerceStoreId" value="%{ecommerceStore.ecommerceStoreId}"/>
     </s:else>
 					  
								 <s:if test="%{#session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}">
								 
								 
								 <div class="content_table">
								 
				
				
				
					<div class="content_header">
					
								<div class="cont_hdr_title">
									<mmr:message messageId="ecommerce.ship.customize"/>
								</div>
								<div class="cont_hdrtitle_l" style="width:auto;">
								 <s:property value="ecommerceStore.url"/>
								</div>
							 <div class="form_buttons">
									<!--<s:submit onclick="submitform()" value="SAVE"/>
									<s:submit onclick="resetform()" value="RESET"/>-->
									
									<a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a> 
								</div>
							</div>
							
							
							<div class="cont_data_body">
							
							<div class="rows">
 						<s:if test="%{#session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}">
									  	<div class="fields">
										<label><mmr:message messageId="ecommerce.customer"/></label>
										<div class="controls"><span>:</span>
													<s:select key="ecommerceStore.customerId" cssClass="text_01_combo_big" cssStyle="height:25px; width: 150px;" 

				     	                       disabled="%{#session.ROLE.contains('customer_admin')}"   headerValue="Select Customer" headerKey="-1"   listKey="id" listValue="name" list="customerList" />	  
									</div>
									</div> 
									 
										<div class="fields">
										<label><mmr:message messageId="label.ecom.store.url"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield size="15"   id="menuName" key="ecommerceStore.url" name="ecommerceStore.url" />
									</div>
									</div>
									 </s:if>
									 <s:hidden name="ecommerceStore.ecommerceStoreId" value="%{ecommerceStore.ecommerceStoreId}"></s:hidden>

									<div class="fields">
										<label><mmr:message messageId="ecommerce.rate.url"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield  size="15" readonly="%{#session.ROLE.contains('customer_admin')}"  id="menuName" key="ecommerceStore.rateServiceUrl" name="ecommerceStore.rateServiceUrl" />
									</div>
									</div>
									<div class="fields">
										<label> <mmr:message messageId="ecommerce.createship.url"/>  </label>
										<div class="controls"><span>:</span>
										<s:textfield size="15" readonly="%{#session.ROLE.contains('customer_admin')}"   id="menuName" key="ecommerceStore.createShipmentUrl" name="ecommerceStore.createShipmentUrl" />
									</div>
									</div>
									<div class="fields">
										<label> <mmr:message messageId="ecommerce.cancelship.url"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield size="15" readonly="%{#session.ROLE.contains('customer_admin')}"   id="cancelShipmentUrl" key="ecommerceStore.cancelShipmentUrl" name="ecommerceStore.cancelShipmentUrl" />
									</div>
									</div>
										<div class="fields">
										<label> <mmr:message messageId="ecommerce.domain"/></label>
										<div class="controls"><span>:</span>
										<s:textfield  size="15" readonly="%{#session.ROLE.contains('customer_admin')}"  id="menuName" key="ecommerceStore.ecommerceDomain" name="ecommerceStore.ecommerceDomain" />
									</div>
									</div>
									
									
									</div>
								
				</div>	
				</div>
									</s:if>
				
				<s:if test="#session.edit == 'true'">
				<s:if test="%{#session.ROLE.contains('customer_admin')}">	
				<div class="content">
	<div id="pickup_div_hdr">
	<div class="content_body">
	<div class="content_table">
		<div class="content_header">
		<div class="cont_hdr_title"><mmr:message messageId="free.ship.setting"/>  
<s:checkbox name="ecommerceStore.freeshipRequired"  value="%{ecommerceStore.freeshipRequired}" id="pickup_checkbox" onclick="toShoworHide(this.checked);"/>
	
	
	 
	</div>
	
	<div class="cont_hdrtitle_l" style="width:auto;">
								 <s:property value="ecommerceStore.url"/>
	 	</div>
	 
	 <div class="form_buttons">
							  
									<a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									 
								</div>
	</div>
	<div id="pickup_div_panel" style="display: none;">
 							<div class="cont_data_body">
							<div class="rows">
								<div class="fields">
									<label>Free Shipment By 
									</label>
									<div class="controls">
										<span>:</span>
										<s:select value="%{ecommerceStore.freeShipType}" name="ecommerceStore.freeShipType"
											list="#{'1':'Weight (lb)','2':'Cost $'}" theme="simple" 
											 disabled="#session.ROLE.contains('sysadmin')" onchange="updateFreeshipLable(this)" cssStyle="height:28px;"/>
										
									</div>
								</div>
								<div class="fields" style="width:auto;">
								<label style="width:0px;">   
									</label> 
									<div class="controls">
											<s:select value="%{ecommerceStore.compareFreeship}" name="ecommerceStore.compareFreeship"
											list="#{'1':'Less Than','2':'Greater Than'}" theme="simple"
											 disabled="#session.ROLE.contains('sysadmin')"  cssStyle="height:28px;" />
									</div>
								</div>
								<div class="fields">
									<label id="freeshipLabel">
									<s:if test="%{#session.edit == 'true'}">
									<s:property value="freeShipLable"/> 
									</s:if> 
									<s:else>
									Weight (lb)  
									</s:else>
									</label> 
									<div class="controls">
										<span>:</span> 
										<s:if test="#session.ROLE.contains('customer_admin')">
										<s:textfield  id="freeShip" key="ecommerceStore.flatRate" name="ecommerceStore.flatRate" value="%{ecommerceStore.flatRate}"/>
										</s:if>
										<s:else><s:property value="ecommerceStore.flatRate"/>
										</s:else>
									</div>
								</div>
			 
							</div>
						</div>
					</div>
 
	</div>
	
	
	
	</div>
	</div>
	</div> 
					
					<div class="content_table">
					<div class="content_header">
					
								<div class="cont_hdr_title">
									Markup Setting:
								</div>
								<div class="cont_hdrtitle_l" style="width:auto;">
								Currency :  <s:property value="customer.defaultCurrency"/>
								</div>
						 </div>
							<div class="cont_data_body">
							
							
				<div class="rows">
								<div class="fields">
									<label>Mark up By
									</label>
									<div class="controls">
										<span>:</span>
										<s:select value="%{ecommerceStore.markupLevel}" name="ecommerceStore.markupLevel"
											list="#{'1':'Flat Rate ($)','2':'Percentage (%)'}" theme="simple" 
									 	 disabled="#session.ROLE.contains('sysadmin')"  onchange="updateMarkupLabel(this)" cssStyle="height:28px;" />
											
									</div>
								</div>
								<div class="fields" style="width:auto;">
									<div class="controls" >
											<s:select value="%{ecommerceStore.markupType}" name="ecommerceStore.markupType"
											list="#{'1':'Mark Up','2':'Mark Down'}" theme="simple"
											 disabled="#session.ROLE.contains('sysadmin')"  cssStyle="width:133px;height:28px;" />
									</div>
								</div>
								<div class="fields">
									<label id="markupLabel">
									<s:if test="%{#session.edit == 'true'}">
									<s:property value="markupLable"/>
									</s:if> 
									<s:else>
									Flat Rate ($) 
									</s:else>
									</label>
									<div class="controls">
										<span>:</span>
										<s:if test="#session.ROLE.contains('customer_admin')">
										<s:textfield     id="markupValue" key="ecommerceStore.flatMarkup" name="ecommerceStore.flatMarkup"  value="%{ecommerceStore.flatMarkup}"/>
										</s:if>
										<s:else>
										<s:property value="ecommerceStore.flatMarkup"/>
										</s:else>
									</div>
								</div>
								


								 
				</div>	

				
				</div>
				
					</div> 	
					</s:if>
 </s:if>
				
						
			
			<div class="content_table">
					<div class="content_header">
								<div class="cont_hdr_title">
									Custom Shipping Setting:
								</div>
						 </div>
							<div class="cont_data_body">
							
							
				<div class="rows">
								<div class="fields">
									<label>
									Simple Package System
									</label>
									<div class="controls">
										<span>:</span>
											<s:checkbox id="checkboxmax" name="ecommerceStore.singlePack"  value="%{ecommerceStore.singlePack}" onclick="toShoworHidemax(this.checked)"/>
									</div>
								</div> 
								<s:if test="%{#session.ROLE.contains('sysadmin')}">
								<div class="fields" id="maxpack" style="display: none;">
									<label>
									Maximum Package Weight
									</label>
									<div class="controls" >
										<span>:</span>
											<s:textfield  id="maxPackWeihgt" key="ecommerceStore.maxPackWeight" name="ecommerceStore.maxPackWeight" value="%{ecommerceStore.maxPackWeight}"/>
									</div>
								</div> 
								</s:if><s:else>
									<div class="fields" id="maxpack" style="display: none;">
									<label>
									Maximum Package Weight
									</label>
									<div class="controls">
										<span>:</span>
											<s:textfield  id="maxPackWeihgt" key="ecommerceStore.maxPackWeight" name="ecommerceStore.maxPackWeight" value="%{ecommerceStore.maxPackWeight}"  />
									</div>
								</div>
								</s:else>
				</div>	
				
				<div class="rows">
								 <div class="fields_radio" style="margin-left: 5px;">	
										 <label> <mmr:message messageId="shipping.service"/></label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span>:</span> 
											<s:radio name="shipCustomerFlag" list="#{'1':'Cheapest','2':'Fastest','3':'Both Services'}" value="%{shipCustomerFlag}"  id="visibility1"/>
											 <input type="hidden" id="hiddenVis" name="scope" > 
										<br>
									</div>
									
									
<br>
								  <div class="fields_radio" style="margin-left: 5px;">
										 <label> <mmr:message messageId="menu.product.pack.map"/> </label>
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 											<span>:</span> 
										  
											<s:radio name="packageMapFlag" list="#{'1':'Enable','2':'Disable'}" value="%{packageMapFlag}"  id="visibility1"/>									
											 <input type="hidden" id="hiddenVis" name="scope" > 
										<br>
									</div><br></div> 
				</div>
					</div> 				
					 
		</s:form> 
	</div>
</div>	
<script type="text/javascript">
var idq='<s:property value="ecommerceStore.singlePack"/>';
	 
if(idq=="true"){
//	alert(id);
 	document.getElementById("maxpack").style.display = 'block';
}else if(idq=="false"){
		//alert(id);
		document.getElementById("maxpack").style.display = 'none';
}

</script>
<s:if test="#session.edit == 'true'">	
  <script type="text/javascript">
 
		
 		var id='<s:property value="ecommerceStore.freeshipRequired"/>';
 		
		if(id=="true"){
		//	alert(id);
		 	document.getElementById("pickup_div_panel").style.display = 'block';
		}else if(id=="false"){
				//alert(id);
				document.getElementById("pickup_div_panel").style.display = 'none';
		}
	
	 
  </script>
	 </s:if>
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</body>
</html>
    
    
  
