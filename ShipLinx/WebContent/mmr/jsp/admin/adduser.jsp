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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$('#sample1').dataTable(); 
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
		} );
	</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

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
var ecount=0;

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
		var msg = "";
		if(document.getElementById("user_timeout").value < 30)
			alert("Session timeout for the User should range from 30 and greater.");
		else
		{
			if(default_address_id_to > 0 && default_address_id_to==default_address_id_from)
				alert("Default To and From addresses cannot be the same")
			else
			{
	 			document.userform.action = "createUser.action";
	 			document.userform.submit();
	 		}
	 	}
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
	
	
	
function setPartnerBus(rootbusid){
	       
		
		resetfrpartner();
			 
		  if(rootbusid != "" && rootbusid!= "-1"){
		    
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{    
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					  	  chargeCodeElement = document.getElementById("partnerBus");
					  	   
					   	  chargeCodeElement.innerHTML= reponse;
					  	
						}
			  		 
			  	 
			 	 };
			url="ajax.getchildBus.action?rootBusId="+rootbusid;
				ajax_Carrier.open("GET",url,true);
				ajax_Carrier.send(this);
							 
			}/* else{
				
				if(count==1){
					 reponse="<select id='cid'><option value=\"-1\">ANY</option></select>";
				  	  chargeCodeElement = document.getElementById("countyCode1");
			   	  chargeCodeElement.innerHTML= reponse;
			}else if(count==2){
			  reponse="<select id='cid'><option value=\"-1\"></option></select>";
			  	  chargeCodeElement = document.getElementById("countyCode2");
			   	  chargeCodeElement.innerHTML= reponse;
				}
				setBranch("");
				
				
			} */
			
		 
		
	}	
	
	function setNationBus(partnerId){
	 
			 resetfrNation();
		  if(partnerId != "" && partnerId!= "-1"){
		    
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{    
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					  	  chargeCodeElement = document.getElementById("nationBus");
					  	   
					   	  chargeCodeElement.innerHTML= reponse;
					  	
						}
			  		
			  	 
			 	 };
			url="ajax.getchildBus.action?partnerBusId="+partnerId;
				ajax_Carrier.open("GET",url,true);
				ajax_Carrier.send(this);
							 
			}
		 
		
	}	
		
	
	function setBranchBus(nationId){
		 
		resetBranch();
		  if(nationId != "" && nationId!= "-1"){
		    
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{    
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					  	  chargeCodeElement = document.getElementById("branchBus");
					  	   
					   	  chargeCodeElement.innerHTML= reponse;
					  	
						}
			  		 
			  	 
			 	 };
			url="ajax.getchildBus.action?nationBusId="+nationId;
				ajax_Carrier.open("GET",url,true);
				ajax_Carrier.send(this);
							 
			}
		 
		
	}	
		
	function checkToAddAjax(){
		if(document.getElementById("userbustable").style.display==="none"){
		document.getElementById("userbustable").style.display = "block";
		}
		 
		var root1= document.getElementById("rid");
		 
		var partner= document.getElementById("pid");
		var nation= document.getElementById("cid");
	
		var branch = document.getElementById("bid");
	
	
		var rv = root1.options[root1.selectedIndex].value;
		 
		var pv=partner.options[partner.selectedIndex].value;
	
		var nv=nation.options[nation.selectedIndex].value;
		var bv=branch.options[branch.selectedIndex].value;
		 
		 if(rv != "" && rv!= "-1"){
			    
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{    
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					 	  $("#rowuser").append(reponse);
					   	 
					  	
						}
			  		 
			  	 
			 	 };
			 	var url="new.userBus.action?root="+rv+"&partner="+pv+"&nation="+nv+"&branch="+bv;
				ajax_Carrier.open("GET",url,true);
				ajax_Carrier.send(this);
							 
			}else{
				
				alert("Select Any Root Business...")
			}
		
		 
		}
	function deleteuserBus(){
		var xmlhttp;
		var deleteUserId = document.getElementsByName("salesUseCheckBox");
		var i1,txt1 = 0;
	   for (i1=0;i1<deleteUserId.length;i1++){
		if (deleteUserId[i1].checked){
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
			var i,txt;
			for (i=0;i<deleteUserId.length;i++){
				if (deleteUserId[i].checked){
					txt = deleteUserId[i].value ;					
				}
			}
			
			var rowid="ub"+txt;
			var element = document.getElementById(rowid);
			element.outerHTML = "";
		
	}
	}
	
	
function	resetfrpartner(){
		reponse="<select id='pid'><option value=\"-1\">ANY</option></select>";
	  	  chargeCodeElement = document.getElementById("partnerBus");
 	  chargeCodeElement.innerHTML= reponse;
 	  
 	 reponse="<select id='cid'><option value=\"-1\">ANY</option></select>";
 	  chargeCodeElement = document.getElementById("nationBus");
	  chargeCodeElement.innerHTML= reponse;
	  
	  
	  reponse="<select id='bid'><option value=\"-1\">ANY</option></select>";
  	  chargeCodeElement = document.getElementById("branchBus");
	  chargeCodeElement.innerHTML= reponse;
	  
	  
	}
	function resetfrNation(){
	 	  
	 	 reponse="<select id='cid'><option value=\"-1\">ANY</option></select>";
	 	  chargeCodeElement = document.getElementById("nationBus");
		  chargeCodeElement.innerHTML= reponse;
		  
		  
		  reponse="<select id='bid'><option value=\"-1\">ANY</option></select>";
	  	  chargeCodeElement = document.getElementById("branchBus");
		  chargeCodeElement.innerHTML= reponse;
	}
	function resetBranch(){
		 
		  reponse="<select id='bid'><option value=\"-1\">ANY</option></select>";
	  	  chargeCodeElement = document.getElementById("branchBus");
		  chargeCodeElement.innerHTML= reponse;
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
     </s:else>
    <s:hidden name="cid" value="%{user.customerId}" />
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title">
									<s:if test="#session.edit == 'true'">
									<mmr:message messageId="label.shippingOrder.editUser"/>
									</s:if>
									<s:else>
									<mmr:message messageId="label.shippingOrder.addNewUser"/>
								</s:else>
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
									<div class="fields">
										<label><mmr:message messageId="label.user.username"/> </label>
										<div class="controls"><span>:</span>
										<s:if test="#session.edit == 'true'">
										<s:textfield   readonly="true" id="userName" key="user.username" name="user.username" onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
										</s:if>
										<s:else>
										<s:textfield   id="userName" key="user.username" name="user.username"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
									</s:else></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.password"/> </label>
										<div class="controls"><span>:</span><s:password  key="user.password" name="user.password"  /></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.first.name"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="user.firstName" name="user.firstName" /></div>
									</div>
								
									<div class="fields">
										<label><mmr:message messageId="label.last.name"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="user.lastName" name="user.lastName"  /></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.phone"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="user.phoneNumber" id="phoneNumber" name="user.phoneNumber"   onkeypress="return typenumbers(event,\'0123456789+-()\')"/></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.fax"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="user.fax" name="user.fax"  /></div>
									</div>
								
									<div class="fields">
										<label><mmr:message messageId="label.user.email"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="user.email" name="user.email" id="email"/></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.enabled"/> </label>
										<div class="controls"><span>:</span>
										<s:select key="user.enabled" name="user.enabled" headerKey="1" list="{'true','false'}"    />
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.roles"/> </label>
										<div class="controls"><span>:</span>
										<s:select   name="user.userRole"  listKey="role" listValue="description" cssClass="changerole"
										headerKey="-1"  headerValue="--Select Role--" list="#session.availableRoles" theme="simple" onchange="showHideSalesDiv(this.name);" id="idRoles"/>
										</div>
									</div>
								
									<div class="fields">
										<label><mmr:message messageId="label.user.GL"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="user.userGLOrRefNumber" name="user.userGLOrRefNumber"/></div>
									</div>
									<div class="fields">
									<label><mmr:message messageId="label.adduser.unitmeasure"/></label>
									<div  class="controls"><span>:</span><s:select id="measure" name="user.unitmeasure" value="%{user.unitmeasure}"  list="#{'1':'Imperial(Pounds/Inches)','2':'Metric(Kilograms/Centimetres)'}"  disabled="false"/></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.session.timeout"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="user.sessionTimeout" name="user.sessionTimeout" id="user_timeout"  onkeypress="return typenumbers(event,\'0123456789\')"/></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.user.selectlocale"/></label>
										<div class="controls"><span>:</span><s:select name="user.locale" value="%{user.locale}" headerKey="-1"  headerValue="--Select Locale--" listKey="locale" listValue="description" list="#session.localeList" ></s:select></div>
									</div>
									
								</div>
								<div class="rows" >
									<p id="rules" style="color: #000066; font-size: 12px; font-weight: bold;"></p>
								</div>	
							</div>
						</div>
						
						<div class="content_table">
					<div class="cont_data_body" id="sales_div" style="display: none;background-color: #e5e5e5;">
						<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.shippingOrder.salescheckin"/></div>
							</div>
						<div class="rows">
							<div class="fields">
								<label><!--<mmr:message messageId="label.commission.percentage"/>-->SPD %: </label>
								<div class="controls"><span>:</span>
								<s:textfield size="10" key="user.commissionPercentagePS" name="user.commissionPercentagePS"  />
								</div>
							</div>
							<div class="fields">
								<label><!--<mmr:message messageId="label.commission.percentage.pp"/>-->LTL %: </label>
								<div class="controls"><span>:</span>
								<s:textfield size="10" key="user.commissionPercentagePP" name="user.commissionPercentagePP"  />
								</div>
							</div>
							<div class="fields">
								<label><!--<mmr:message messageId="label.commission.percentage.ps"/>-->CHB %: </label>
								<div class="controls"><span>:</span>
								<s:textfield size="10" key="user.commissionPercentageCHB" name="user.commissionPercentageCHB"  />
								</div>
							</div>
						</div>
						<div class="rows">
							<div class="fields">
								<label><mmr:message messageId="label.user.userCode"/> </label>
								<div class="controls"><span>:</span>
								<s:textfield size="10" key="user.userCode" name="user.userCode"   />
								</div>
							</div>
							<div class="fields">
								<label><mmr:message messageId="label.user.logoURL"/> </label>
								<div class="controls"><span>:</span>
								<s:textfield size="10" key="user.logoURL" name="user.logoURL"  />
								</div>
							</div>
							
						</div>
					</div>
					
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.print.setup"/></div>
							</div>
							<div class="cont_data_body">
							<s:if test="#session.edit == 'true'">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.copies.shipping.label"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="label_copies"   name="user.printNoOfLabels" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{user.printNoOfLabels}"/>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.copies.customsinvoice"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="customsinv_copies"   name="user.printNoOfCI" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="%{user.printNoOfCI}"/>
										</div>
									</div>
									
								</div>
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.preferred.label.size"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="label_size"  name="user.preferredLabelSize" list="{'--Select--','4 x 6','8 x 11'}"/>
										</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.auto.print"/> </label>
										<div class="controls"><s:checkbox key="user.autoPrint" name="user.autoPrint" /></div>
									</div>
									
								</div>
								
							</div>
							</s:if>
							<s:else>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fieldsl">
										<label><mmr:message messageId="label.copies.shipping.label"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="label_copies"  name="user.printNoOfLabels" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="1"/>
										</div>
									</div>
									<div class="fieldsl">
										<label><mmr:message messageId="label.copies.customsinvoice"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="customsinv_copies"   name="user.printNoOfCI" list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" value="3"/>
										</div>
									</div>
									
								</div>
								<div class="rows">
									<div class="fieldsl">
										<label><mmr:message messageId="label.preferred.label.size"/> </label>
										<div class="controls"><span>:</span>
										<s:select id="label_size"  name="user.preferredLabelSize" list="{'--Select--','4 x 6','8 x 11'}" value="%{user.preferredLabelSize}"/>
										</div>
									</div>
									<div class="fieldsl">
										<label><mmr:message messageId="label.auto.print"/> </label>
										<div class="controls"><span>:</span><s:checkbox key="user.autoPrint" name="user.autoPrint" /></div>
									</div>
									
								</div>
								
							</div>	
							</s:else>
						</div>
						
						
						
						
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.addressbook.defaultAddress"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fieldsl">
										<label><mmr:message messageId="label.addressbook.defaultFromAddress"/> </label>
										<div class="controls"><span>:</span>
										<s:hidden name="user.defaultFromAddressId" id="user.defaultFromAddressId"/>
										<s:if test="%{user.defaultFromAddressId > 0}">
										<s:textfield name="user.defaultFromAddressText" id="defaultFromAddText"/>
										</s:if>
										<s:else>
										<s:textfield name="user.defaultFromAddressText" id="defaultFromAddText"  value="None"/>
										</s:else>
										</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.addressbook.defaultToAddress"/> </label>
										<div class="controls"><span>:</span>
										<s:hidden name="user.defaultToAddressId" id="user.defaultToAddressId"/>
										<s:if test="%{user.defaultToAddressId > 0}">
										<s:textfield name="user.defaultToAddressText" id="defaultToAddText" />
										</s:if>
										<s:else>
										<s:textfield name="user.defaultToAddressText" id="defaultToAddText" value="None"/>
										</s:else>
										</div>
									</div>
									
								</div>
								
							</div>
						
						</div>
						
						  <s:if test="%{#session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}">
						<div class="content_table">
							<div class="content_header">
							
								<div class="cont_hdr_title">SELECT DIVISION</div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fieldsl">
									<label style="width:200px !important;">SPD  </label>
										<div class="controls"><span>:</span>
										 <s:checkbox key="user.spdEnabled" name="user.spdEnabled" />
									</div></div>
									<div class="fieldsl">
										<label style="width:200px !important;">LTL </label>
										<div class="controls"><span>:</span>
										 <s:checkbox key="user.ltlEnabled" name="user.ltlEnabled" />
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;">CHB </label>
										<div class="controls"><span>:</span>
										 <s:checkbox key="user.chbEnabled" name="user.chbEnabled" />
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;">FWD </label>
										<div class="controls"><span>:</span>
										 <s:checkbox key="user.fwdEnabled" name="user.fwdEnabled" />
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;">FPA </label>
										<div class="controls"><span>:</span>
										 <s:checkbox key="user.fpaEnabled" name="user.fpaEnabled" />
									</div>
									</div>
									
								</div>
								
							</div>
						
						</div>
						</s:if>
						
						<s:if test="%{#session.ROLE.contains('sysadmin')}">
					<div class="content_table">
						<div class="form-container" style="background-color: #FFF;" id="userbustable">
							<div id="srchusr_results">
								<div id="srchusr_res">
									<span><mmr:message messageId="" />User Business</span>
								</div>

								<div class="form_buttons">	<s:if test="#session.edit == 'true'">
									 
											</s:if> <a href="#"
										onclick="deleteuserBus();"><mmr:message
											messageId="btn.delete" /></a>
								</div>
							</div>

							<div id="accnt_bottom_table" style="background-color: #FFF;">
								<table cellpadding="0" cellspacing="0" border="0px" class="display" id="bdmtable"
									style="float: left; background-color: #FFF; width: 100%; height: auto;">
									<thead>
										<tr height="25px">
											<th><input id="check_all" type="checkbox" name="salesUseCheckBox" /></th>
											
											<th style="text-align: left; padding-left: 10px;"><mmr:message messageId="menu.admin.rootBusiness" /> </th>
											<th style="text-align: left; padding-left: 10px;"><mmr:message messageId="menu.admin.partner" /> <mmr:message messageId="label.business" /></th>
											<th style="text-align: left; padding-left: 10px;"><mmr:message messageId="menu.admin.countrypartner" /> <mmr:message messageId="label.business" /></th>
											<th style="text-align: left; padding-left: 10px;"><mmr:message messageId="menu.admin.branch" /> <mmr:message messageId="label.business" /></th>
										</tr>
									</thead>
									
									<tbody id="rowuser">
										<s:set var="index" value="0" />
										<s:iterator value="userBusiness.businessFilterList" status="status">
	 
	<tr id="ub<s:property value="%{id}"/>">
				<td class="odd1" width="2%"><input class="dataTable-checkbox"
					type="checkbox" name="salesUseCheckBox"
					value="<s:property value='%{id}'/>" /> <s:hidden
						value="%{id}" id="index_%{custsalesid}" /></td>
				
				<td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{parentBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{parentBus.id}'/>" name="parentBusIds" id=""/>
				</td>
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{partnerBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{partnerBus.id}'/>" name="partnerBusIds" id=""/>
				</td>
				 
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{nationBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{nationBus.id}'/>" name="nationBusIds" id=""/>
				</td>
				 
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{branchBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{branchBus.id}'/>" name="branchBusIds" id=""/>
				</td>
				 
			</tr>
			 
		</s:iterator>
	
									</tbody>
								</table>
							</div>
						</div>
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.user.business"/></div>
								<div class="form_buttons" id="sales_user_bck">
							<a href="searchcustomer.action"><mmr:message messageId="btn.back" /></a>
							 <a href="#" onclick=" checkToAddAjax();"><mmr:message messageId="btn.add"/></a>
						</div>
							</div>
						
						<div class="form-container" style="background-color: #FFF;">
							 <div class="cont_data_body">
							
								<div class="rows">
									<div class="fieldsl">
									<label style="width:200px !important;">Root Business:  </label>
										<div class="controls"><span>:</span>
										 <s:select id="rid" list="userBusiness.businessFilter.ParentBusList" listKey="id" listValue="name"  headerKey="-1" headerValue="ANY" onchange="setPartnerBus(this.value)"></s:select>
									</div></div>
									<div class="fieldsl">
										<label style="width:200px !important;">Partner Business: </label>
										<div class="controls"><span>:</span>
										<div id="partnerBus">
										<s:select id="pid" list="userBusiness.businessFilter.partnerBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"></s:select>
										</div>
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;">Nation Business: </label>
										<div class="controls"><span>:</span>
										<div id="nationBus">
										 <s:select id="cid" list="userBusiness.businessFilter.nationBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"></s:select>
										 </div>
									</div>
									</div>
							    	<div class="fieldsl">
										<label style="width:200px !important;">Branch Business: </label>
										<div class="controls"><span>:</span>
										<div id="branchBus">
										 <s:select id="bid"list="userBusiness.businessFilter.branchBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"> </s:select>
										 </div>
									</div>
									</div>
									
									
								</div>
								
							</div>
						</div>
						
					</div>
						</s:if>
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
    
    
 