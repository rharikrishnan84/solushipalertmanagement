<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head>
<sx:head/>
    <title><s:text name="user.form.title"/></title> 
	</head> 

<body> 

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

var count=0;
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
	 			document.userform.action = "save.business.action";
	 			document.userform.submit();
	}
	
	function resetform(){
			
			document.userform.action = "adduser.action";
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
	
	 function getAddressSuggestBusiness() {
		 
			var form = document.userform;
		  	var selectedCountry = form.elements['business.address.countryCode'].value;
		    if(selectedCountry != 'US' && selectedCountry != 'CA'){
		    	return;
		    }
		    alert("Dd");
		    document.getElementById("loading-img-cust").style.display = 'block';
			var error = false;
			form.elements['business.address.postalCode'].value = (form.elements['business.address.postalCode'].value).replace(/\s/g,"");
				if((form.elements['business.address.postalCode'].value).length == 5){
					var mask = new RegExp('^\\d{5}$');
					if(mask.exec(form.elements['business.address.postalCode'].value) == null) {
						alert('Postal Code should be in US format #####');
						error = true;
					}else{
						form.elements['business.address.countryCode'].value = 'US';
					}
				}
				else if((form.elements['business.address.postalCode'].value).length == 6){
						var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
						if(mask.exec(form.elements['business.address.postalCode'].value) == null) {
							alert('Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
							error = true;
						}else{
							form.elements['business.address.countryCode'].value = 'CA';
						}
					
				}else{
					alert('Postal code should be only of US or Canada');
					error = true;
					document.getElementById("loading-img-cust").style.display = 'none';
				}
				showStateCustomer();	
				if(!error){
					//alert("Not error 1");
					ajax_City=ajaxFunction();
					ajax_City.onreadystatechange=function()
					  {
						   if(ajax_City.readyState==4)
							{
							//alert("Not error 2");
							reponse=ajax_City.responseText;
							//alert(reponse);
							form.elements['business.address.city'].value = reponse;
							showStateCustomer();
							document.getElementById("loading-img-cust").style.display = 'none';
							}
					  }
					  toPostalCode = document.getElementById('custPostalCode').value;
					  toCountry = form.elements['business.address.countryCode'].value
						param="postalCode="+toPostalCode+"&countryCode="+toCountry;
						url=contextPath+"/getAddressSuggestCustomer.action?" + param;
						//alert(url);
					  //+"&country="+toCountry;
						ajax_City.open("GET",url,true);
						ajax_City.send(this);
				}
				else{
					document.getElementById("loading-img-cust").style.display = 'none';
				}
		}
	
	 
	 //business filter ajax functions
		
		function setcountry(partnerId){
		// alert(partnerId);
	  if(partnerId != "" && partnerId!= "-1"){
	    
			ajax_Carrier=ajaxFunction();
			ajax_Carrier.onreadystatechange=function()
		  	{   if(count==1){
		  		 if(ajax_Carrier.readyState==4)
					{
				 	   reponse=ajax_Carrier.responseText;
				  	  chargeCodeElement = document.getElementById("countyCode1");
				  	   
				   	  chargeCodeElement.innerHTML= reponse;
				   	 
				  	document.getElementById("cid").value=country;
				   	 //setBranch(country);
					}
		  		}
		  	else if(count==2){
		  		
		  		 if(ajax_Carrier.readyState==4)
					{
				 	   reponse=ajax_Carrier.responseText;
				  	  chargeCodeElement = document.getElementById("countyCode2");
				   	  chargeCodeElement.innerHTML= reponse;
						listChargeName(chargeCodeElement.value);
						
					}
		  		
		  	}
		 	 };
		url="listCountryName.action?partnerId="+partnerId;
			ajax_Carrier.open("GET",url,true);
			ajax_Carrier.send(this);
			
			if(count==1){
				  reponse="<select><option value=\"-1\">Select</option></select>";
			  	  chargeCodeElement = document.getElementById("branchCode1");
			   	 // chargeCodeElement.innerHTML= reponse;
						
			}else if(count==2){
			  reponse="<select><option value=\"-1\">Select</option></select>";
		  	  chargeCodeElement = document.getElementById("branchCode2");
	   	  chargeCodeElement.innerHTML= reponse;
		
			}
		}else{
			
			if(count==1){
				 reponse="<select id='cid'><option value=\"-1\">Select</option></select>";
			  	  chargeCodeElement = document.getElementById("countyCode1");
		   	  chargeCodeElement.innerHTML= reponse;
		}else if(count==2){
		  reponse="<select id='cid'><option value=\"-1\">Select</option></select>";
		  	  chargeCodeElement = document.getElementById("countyCode2");
		   	  chargeCodeElement.innerHTML= reponse;
			}
			setBranch("");
			
			
		}
		
	}
	
		
		function setBranch(country){
			
			 
			partner=$("#pid").val();
			 
	//		alert(partner);
			//alert(chargecode);
			if(country != "" && country !="-1"){
				//partner=partnerId;
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{   if(count==1){
			  		
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					  	  chargeCodeElement = document.getElementById("branchCode1");
					   	  chargeCodeElement.innerHTML= reponse;
					   	  
					   	 if(branch!=-1){
					  	document.getElementById("bid").value=country;
					  	
					  	
					   	 }
						}
			  		}
			  	else if(count==2){
			  		 if(ajax_Carrier.readyState==4)
						{
					 	  reponse=ajax_Carrier.responseText;
					  	  chargeCodeElement = document.getElementById("branchCode2");
					   	  chargeCodeElement.innerHTML= reponse;
					   	  listChargeName(chargeCodeElement.value);
							
						}
			  		
			  	}
			 	 };
				url="listBranchName.action?countryPartnerId="+country+"&partnerId="+partner;
				ajax_Carrier.open("GET",url,true);
				ajax_Carrier.send(this);
			}else{
				if(count==1){
					  reponse="<select id='bid'><option value=\"-1\">Select</option></select>";
				  	  chargeCodeElement = document.getElementById("branchCode1");
				   	  chargeCodeElement.innerHTML= reponse;
					
				}else if(count==2){
				  reponse="<select id='bid'><option value=\"-1\">Select</option></select>";
			  	  chargeCodeElement = document.getElementById("branchCode2");
			   	  chargeCodeElement.innerHTML= reponse;
				}
			}
			
		}
/* 	
		
		 function addfilter(){
	         
		    var country = document.getElementById("cid");
		    var branchid = document.getElementById("bid");
			var partnerid = document.getElementById("firstBox");
			var partnerval = partnerid.options[partnerid.selectedIndex].value;
			var partnertxt= partnerid.options[partnerid.selectedIndex].text;
			var countryval = country.options[country.selectedIndex].value;
			var countrytxt = country.options[country.selectedIndex].text;
			var branchval = branchid.options[branchid.selectedIndex].value;
			var branchtxt = branchid.options[branchid.selectedIndex].text;
			var elem = document.getElementById('filterdiv');
			 
			var id1="sel"+cou;
			var id2="but"+cou;
			var plid="p"+cou;
			var coid="c"+cou;
			var brid="b"+cou;
			var idd="seld"+cou;
			var ids=partnerval+","+countryval+","+branchval;
			alert(ids);
			var html1="<div id='"+idd+"'  class=\"well\"><select multiple=\"true\" name=\"customers\" id='"+id1+"'>"+options+"</select></div>";
			var div = $("<div/>");
			div.attr("class", "rows");
			//<div class=\"fields1\"><input  type=\"checkbox\" value='"+ids+"' name=\"filterchk\"/>
			div.html("</div><div class=\"fields1\"><label>PARTNER :</label>"+
			 "<label>"+partnertxt+"</label><input type=\"hidden\" value='"+partnerval+"' id='"+plid+"'/></div><div class=\"fields1\">"+
			"<label >COUNTRY :</label><label>"+countryval+"</label><input type=\"hidden\" value='"+countryval+"' id='"+coid+"'/>"+
			"</div>	<div class=\"fields1\"><label>BRANCH :</label><label>"+branchtxt+"</label><input type=\"hidden\" value='"+branchval+"' id='"+brid+"'/></div>");
			 $("#filterdiv").append(div);
			 //cou++;
					 
		
		//  functionIHadToChange1();

		}
	 */
</script> 

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="content">
<div class="content_body">
<s:form action="createUser" name="userform" style="margin-bottom:0px"  enctype="multipart/form-data">
	<s:if test="#session.edit == 'true'">
    	<s:hidden name="method" value="update"/>
    	
    <script type="text/javascript">
    count=1;
    </script>
    </s:if> 
    <s:else>
    <script type="text/javascript">
    count=2;
    </script>
    
    	<s:hidden name="method" value="add"/>
     </s:else>
    <s:hidden name="businessid" value="%{business.id}" />
    <s:hidden name="addressid" value="%{business.address.addressId}" />
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title">
							 	BUSINESS DETIALS
								</div>
								
							</div>
							<div class="cont_data_body">
								<div class="rows">
								<div class="fields">
										<label><mmr:message messageId="label.business.shortCode"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.shortCode}"/>
												</label>
										
									</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.name"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.name}"/>
												</label>
									</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.systemName"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.systemName}"/>
												</label>
												</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.logOutUrl"/> </label>
										<div  class="controls"><span>:</span>
										
										<label> <s:property value="%{business.logoutURL}"/>
												</label>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.business.storeCC"/> </label>
										<div  class="controls"><span>:</span><s:checkbox key="business.storeCC"
			name="business.storeCC" cssClass="text_02" disabled="true"/></div>
									</div>
								
								
								<div class="fields">
										<label><mmr:message messageId="label.business.logoUrl"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.storeCC}"/>
												</label>
										 </div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.subDomain"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.subdomain}"/>
												</label>
										</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.supportUrl"/> </label>
										<label>
												<s:property value="%{business.supportURL}"/>
												</label>
										</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.termsUrl"/> </label>
										
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.termsURL}"/>
												</label>
										</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpHost"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.smtpHost}"/>
												</label>
										</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpUsername"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.smtpUsername}"/>
												</label>
										</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpPassword"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.smtpPassword}"/>
												</label>
										</div>
										
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.smtpPort"/> </label>
										<div  class="controls"><span>:</span>
										<label>
												<s:property value="%{business.smtpPort}"/>
												</label>
										</div>
									</div>
								</div>
								<!-- <div class="rows" >
									<p id="rules" style="color: #000066; font-size: 12px; font-weight: bold;"></p>
								</div>	 -->
							</div>
						</div>
						
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.business.address"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.business.abbreviationName"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.abbreviationName}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.addressone"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.address1}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.addresstwo"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.address2}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.city"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.city}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.phoneno"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.phoneNo}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.faxno"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.faxNo}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.mobilePhoneNumber"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.mobilePhoneNo}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.emailAddress"/> </label>
										<div class="controls"><span>:</span>
										
										<label>
												<s:property value="%{label.business.emailAddress}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.contactName"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.contactName}"/>
												</label>
									</div>
									</div>
										
									<div class="fields">
										<label><mmr:message messageId="label.business.postalCode"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.postalCode}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.url"/> </label>
										<div class="controls"><span>:</span>
										<label>
												<s:property value="%{business.address.url}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.residential"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.residential" name="business.address.residential" disabled="true" cssClass="text_02" />
										
									</div>
									</div>
										<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.country"/> </label>
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01" listKey="countryCode" listValue="countryName" name="business.address.countryCode" headerKey="-1"  headerValue="Select" list="#session.CountryList" 
	                         onchange="javascript:showShipFromState();"  id="firstBox"  theme="simple"  disabled="true"/>
											</div>
										</div>
											<%-- <div class="fields">
											<label><mmr:message messageId="label.customer.postalCode"/> </label>
											<div class="controls"><span>:</span><s:textfield  id="custPostalCode" key="business.address.postalCode" name="business.address.postalCode" onblur="getAddressSuggestBusiness()"  />
											<img id="loading-img-cust" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0"></div>
										</div> --%>
									<div class="fields">
										<label><mmr:message messageId="label.business.provinceCode"/> </label>
										<div class="controls"><span>:</span>
										 
										
										<label>
												<s:property value="%{business.address.provinceCode}"/>
												</label>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.defaultFrom"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.defaultFromAddress" name="business.address.defaultFromAddress"  cssClass="text_02" disabled="true" />
										
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.defaultTo"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.defaultToAddress" name="business.address.defaultToAddress" cssClass="text_02"  disabled="true"/>
									</div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.business.sendNotification"/> </label>
										<div class="controls"><span>:</span>
										<s:checkbox key="business.address.sendNotification" disabled="true" name="business.address.sendNotification" cssClass="text_02" />
									</div>
									</div>
								</div>
								
							</div>
						
						</div>
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.role.roleMenu"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields1">
										<label><mmr:message messageId="label.role.roleMenu"/> :</label>
										<div class="controls">
										<s:select   name="business.menuIds" value="%{selectedMenuIds}" disabled="true"  listKey="id" listValue="name" cssClass="changerole"
										headerKey="-1"  headerValue="--Select Menu--" list="menuVo" theme="simple"  id="idLocale" multiple="true"
										cssStyle="width:150px;height:100px"/>
									</div>
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
    
    
 