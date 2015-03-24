<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
    <style type="text/css">
    .multiselectable { width:500px; display:block; overflow: hidden; width: 100%; }
.multiselectable select, .multiselectable div { width: 200px; height: 400px; float:left; }
.multiselectable div * { display: block; margin: 0 auto; }
.multiselectable div { display: inline; }
.multiselectable .m-selectable-controls { margin-top: 3em; width: 50px; }
.multiselectable .m-selectable-controls button { margin-top: 1em; }
</style>
<sx:head/>
    <title><s:text name="partner.form.title"/></title> 
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
	$( document ).on( "dblclick", "#lecturers option", function () 
			{
				var element = $("#lecturers option:selected");
				var value = element.val();
				var txt=element.text();
		  
				element.remove();
				var values = value.split(";")
				
				$("#selected_lecturers").append('<option value="' + value + '">' + txt  + '</option>');
				
			});
			$( document ).on( "dblclick", "#selected_lecturers option", function () 
			{
				var element = $("#selected_lecturers option:selected");
				var value = element.val();
				element.remove();
				var txt=element.text();
				var values = value.split(";")
				
				$("#lecturers").append('<option value="' + value + '">' +txt+ '</option>');
				
			}); 
 
 
 });
   </script>
<script type="text/javascript">

function add1(){
	 
	/* 	 var element1 = $("#lecturers option:selected");
		 var n=element1.length;
		 for(var i=0;i<n;i++){
			 
			 var value = element1[i].val();
			 alert(value);
			 var txt=element1[i].text();
			 element1[i].remove();
			 var values = value.split(";")
			 $("#selected_lecturers").append('<option value="' + value + '">' + txt  + '</option>');
			 } */
			 var selectedArray = new Array();
			 var selectedtxt = new Array();
			 
		 

		  var selObj = document.getElementById('lecturers');
		  var i;
		  var count = 0;
		  for (i=0; i<selObj.options.length; i++) {
		    if (selObj.options[i].selected) {
		      selectedArray[count] = selObj.options[i].value;
		      selectedtxt[count]=selObj.options[i].text;
		      //selObj.remove(i);
		      count++;
		    }
		  }
		  
		  
		  
		    var select_element = document.getElementById("lecturers");
	        var options = select_element.options;
	        var i = options.length;
	        while (i--) {
	            var current = options[i];
	            if (current.selected) {
	                // Do something with the selected option
	                current.parentNode.removeChild(current);
	            }
	        }
	        
	        for (i=0; i<selectedArray.length; i++) {
	        	$("#selected_lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
	        }
	     
	 }
	 function remove1(){
		/*  var element2 = $("#selected_lecturers option:selected");
		 var n=element2.length;
		 for(var i=0;i<n;i++){
		 var value = element2[i].val();
		 var txt=element2[i].text();
		 element2[i].remove();
		 var values = value.split(";")
		 $("#lecturers").append('<option value="' + value + '">' + txt  + '</option>');
		 } */
		 var selectedArray = new Array();
		 var selectedtxt = new Array();
		 
	 

	  var selObj = document.getElementById('selected_lecturers');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	    if (selObj.options[i].selected) {
	      selectedArray[count] = selObj.options[i].value;
	      selectedtxt[count]=selObj.options[i].text;
	      //selObj.remove(i);
	      count++;
	    }
	  }
	  
	  
	  
	    var select_element = document.getElementById("selected_lecturers");
	    var options = select_element.options;
	    var i = options.length;
	    while (i--) {
	        var current = options[i];
	        if (current.selected) {
	            // Do something with the selected option
	            current.parentNode.removeChild(current);
	        }
	    }
	    
	    for (i=0; i<selectedArray.length; i++) {
	    	$("#lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
	    }
	 }

var default_address_id_from =0;
var default_address_id_to =0;
var countrylist="";
var count=0;
var deletecountryls="";
var cou="";

function setcountry1(carrierId){
	//alert(chargecode);
	if(carrierId != ""){
		alert(carrierId);
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{
		  	 if(ajax_Carrier.readyState==4)
				{
// 			 	   reponse=ajax_Carrier.responseText;
// 			  	  chargeCodeElement = document.getElementById("chargeCode");
// 			   	 chargeCodeElement.innerHTML= reponse;
// 					listChargeName(chargeCodeElement.value);
				}
	 	 };
		url="listCountryCode.action?countrycode="+carrierId;
		
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#chargeCode select").html("");
		$("#chargeCode select").html("<option>Select</option>");
		$("#chargeName select").html("");
		$("#chargeName select").html("<option>Select</option>");
	}
	
}
function setcountry1(){
	
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   
	  }
	  firstBox = document.getElementById('firstBox');
	  url="<%=request.getContextPath()%>/listCountryCode.action?cn="+firstBox.value;

		//param="objName=ref_state&country_id="+country_id;
		
	
}
function addcountry(){
  
    	count++;
    	
    	var div = $("<div/>");
    div.attr("class", "rows");
	div.html("&nbsp;&nbsp;<div  id=\"div"+count+"\"><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
	"<td><input type=\"label\"  id=\"country"+count+"\" value=\"\" name=\"countryName1\"   readonly />"+"</td><td> <div class=\"form_buttons\"> <div class=\"fields\"><a href=\"#\" onclick=\"deletecountry("+count+")\">DELETE </a></div></div></td></tr></div>"
	);

	$("#ediStandardTable").append(div);
	setvalue();
    }
 
	
	
	function countval(){
		
		count++;
	}

	function checkIfArrayIsUnique() 
    { 
		
		myArray=document.getElementsByName("countryName1");
        for (var i = 0; i < myArray.length; i++) 
        {
            for (var j = 0; j < myArray.length; j++) 
            {
                if (i != j) 
                {  
                    if (myArray[i] == myArray[j]) 
                    {
                        return false; // means there are duplicate values
                    }
                }
            }
        }
        alert(myArray.length);
        return true; // means there are no duplicate values.
    }
	
	
function validate(){
	if(count>=1){
	var countryarr1=document.getElementsByName("country");
	var reval="success";
	var len=countryarr1.length;
	for(var i=0;i<n;i++){
		if(n>=1){
		if(countryarr1[i+1].value == countryarr1[i].value){
		  reval="input";
		}
		}	
	}
	}
	return reval;
}
function setvalue(){
	
	var cnt=document.getElementById("firstBox").value;
	
	if(cnt != ""){
		
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{
		  	 if(ajax_Carrier.readyState==4)
				{
// 			 	   reponse=ajax_Carrier.responseText;
// 			  	  chargeCodeElement = document.getElementById("chargeCode");
// 			   	 chargeCodeElement.innerHTML= reponse;
// 					listChargeName(chargeCodeElement.value);
				}
	 	 };
		url="listCountryCode.action?countrycode="+cnt;
		
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}
	document.getElementById("country"+count).value=cnt;
	
}
function deletecountry(count1){
	deletecountryls += document.getElementById("country"+count1).value+",";
	document.getElementById("deleteCountryList").value=deletecountryls;
	document.getElementById("div"+count1).style.display="none";
	
}




window.onload = function() {

    	var e = document.getElementById("idRoles");
		var strRole = e.options[e.selectedIndex].value;
		if(strRole=='sales')
		document.getElementById("sales_div").style.display= "block";
		else
		document.getElementById("sales_div").style.display= "none";
		
		showHideSalesDiv('partner.userRole');
		
};
var arr="";

	function submitform()
	{
		 var selectedArray = new Array();
		 var selectedtxt = new Array();
		 $("#selected_lecturers option:not(:selected)").each(function () {
			    var $this = $(this);
			    if ($this.length) {
			        var selText = $this.val();
			        arr+= selText+",";
			    }
		 });
		
		  var selObj = document.getElementById('selected_lecturers');
		  var i;
		  var count = 0;
		  for (i=0; i<selObj.options.length; i++) {
		    if (selObj.options[i].selected) {
		      selectedArray[count] = selObj.options[i].value;
		      selectedtxt[count]=selObj.options[i].text;
		      //selObj.remove(i);
		      count++;
		    }
		  }
		  
	    for(i=0;i<selectedArray.length;i++){
	    	arr+=selectedArray[i]+",";
	    }
	   		 document.getElementById("selectedfil").value=arr;
		    document.partnerform.action = "createPartner.action";
			document.partnerform.submit();
		
	}
	
	function resetform(){
			
			document.partnerform.action = "addPartner.action";
	 		document.partnerform.submit();
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
			document.getElementById("rules").innerHTML="- Access to Shipping information, including create new shipments and view all shipments created under the customer account.<br/> - Access to Address Book functionality. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - No access to invoicing module and other admin functions (such as partner management).";	
		}		
		else if(strRole == 'customer_shipper')
		{
			document.getElementById("rules").innerHTML="- Restricted access to Shipping information.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Can create new shipments.<br/>- Can view only shipments created by this partner.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- No access to invoicing module and other admin functions (such as partner management).";	
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
			document.getElementById("partner.defaultFromAddressId").value=data[1];
			default_address_id_from = data[1];
			//document.getElementById("partner.defaultFromAddressText").value=data[0];
			});
			
		$("#defaultToAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultToAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("partner.defaultToAddressId").value=data[1];
			default_address_id_to = data[1];
			//document.getElementById("partner.defaultToAddressText").value=data[0];
			});
		
		// $("#defaultFromAddText").click(function(){$("#defaultFromAddText").val('')});
         $("#defaultFromAddText").blur(function(){if($("#defaultFromAddText").val()==''){
         $("#defaultFromAddText").val('None');
         document.getElementById("partner.defaultFromAddressId").value=0;
         }});
          
        // $("#defaultToAddText").click(function(){$("#defaultToAddText").val('')});
         $("#defaultToAddText").blur(function(){if($("#defaultToAddText").val()==''){
         $("#defaultToAddText").val('None');
         document.getElementById("partner.defaultToAddressId").value = 0;
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
<s:form action="addPartner" name="partnerform" style="margin-bottom	:0px">
<div id="stateid">
</div>
<input type="hidden" id="selectedfil" name="partnerCs">
	<s:if test="#session.edit == 'true'">
	<input type="hidden" id="deleteCountryList" name="deletecountls">
	
    	<s:hidden name="method" value="update"/>
    </s:if> 
    <s:else>
    	<s:hidden name="method" value="add"/>
    		<input type="hidden" id="deleteCountryList" name="deletecountls" value="">
    		<input type="hidden" id="countryListId" name="countryListName" value="">
    		
     </s:else>
    <s:hidden name="cid" value="%{partner.partnerId}" />
						
				<input type="hidden" id="deleteCountryList" name="deletecountls" value="">
    		<input type="hidden" id="countryListId" name="countryListName" value="">
    				
				<div class="content_table">
				
					<div class="content_header">
								<div class="cont_hdr_title">
									<s:if test="#session.edit == 'true'">
									<mmr:message messageId="label.businessFilter.editpartner"/>
									</s:if>
									<s:else>
									<mmr:message messageId="label.businessFilter.addnewPartner"/>
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
										<label><mmr:message messageId="label.partner.partnername"/> </label>
										<div class="controls"><span>:</span>
										<s:if test="#session.edit == 'true'">
										<s:textfield   readonly="true" id="partnerName" key="partner.partnerName" name="partner.partnerName"/>
										</s:if>
										<s:else>
										<s:textfield   id="partnerName" key="partner.partnerName" name="partner.partnerName"  />
									</s:else></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.first.name"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="partner.firstName" name="partner.firstName" /></div>
									</div>
								    
									<div class="fields">
										<label><mmr:message messageId="label.last.name"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="partner.lastName" name="partner.lastName"  /></div>
									</div>
									
									<div class="fields">
										<label><mmr:message messageId="label.partner.shortCode"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="partner.shortCode" name="partner.shortCode" /></div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.user.phone"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="partner.phoneNumber" id="phoneNumber" name="partner.phoneNumber"  /></div>
									</div>
										<div class="fields">
										<label><mmr:message messageId="label.user.fax"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="partner.fax" name="partner.fax"  /></div>
									</div>
								   <div class="fields">
										<label><mmr:message messageId="label.user.email"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="partner.email" name="partner.email" id="email"/></div>
									</div>
						
								
				</div>	
				</div>
				</div>
				<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title">
									
									<mmr:message messageId="label.businessFilter.addCountry"/>
							
								</div>
							
							</div>
							
							<div class="cont_data_body" id="ediStandardTable">
					    <div class="mult">
				 <div class="multiselectable">
					    <div class="m-selectable-from"><label for="lecturers">ALL COUNTRIES</label>
					 <s:select listKey="countryCode" class="m-selectable-from" multiple="true" id="lecturers" listValue="countryName" list="allCountryList" name="allCountryList" size="5" >
					  </s:select> 
					  </div>
					 <div class="m-selectable-controls">
					<button type="button" class="multis-right" id="add" onclick="add1(); return false;">+</button> 
					<button type="button" class="multis-left" id="remove" onclick="remove1(); return false;">-</button> 
				</div>
			<div class="m-selectable-to"><label for="m-selected">PARTNER COUNTRY</label>
						 <s:select listKey="countryCode" class="m-selectable-to" id="selected_lecturers" listValue="countryName" multiple="true" list="partnerCountryList" name="listint" size="2">
					   </s:select> 
</div>						
							</div>
					
							</div>
						</div>

						
								<div class="content_table">
								<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title"><mmr:message messageId="menu.address"/>:</div>
									
									
								</div>
							</div>
						</div>
					</div>	
		<jsp:include page="partner_Address.jsp"/>
				</div>
		</s:form> 
	</div>
</div>
						
<s:if test="#session.edit == 'true'">

	 <script type="text/javascript">
	 remrr();
	// alert("dfdf");
	function remrr(){
		//alert("ddf");
		 var remarr = new Array();
		 var selObj = document.getElementById('selected_lecturers');
		 var i;
		  var count = 0;
		  
		  for (i=0; i<selObj.options.length; i++) {
		  
		      remarr[count] = selObj.options[i].value;
		      count++;
		    
		  }

		  var selectobject=document.getElementById("lecturers");
		  for (var i=0; i<selectobject.length; i++){
			  for(var k=0;k<remarr.length;k++){
			  if (selectobject.options[i].value == remarr[k])
			     selectobject.remove(i);
			  }
		  }

		 
}
  
	 </script>
	 </s:if>
