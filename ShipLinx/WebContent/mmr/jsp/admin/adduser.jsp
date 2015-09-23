<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<sx:head/>
     <title><s:text name="user.form.title"/></title>
    
    <style>
      
      #popup {
        width: 300px;
        height: 200px;
        position: absolute;
        color: #000000;
        background-color: #ffffff;
        top: 50%;
        left: 50%;
        margin-top: -100px;
        margin-left: -150px;
      }
    </style>
	</head> 

<body> 

 <div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
 <div class="content_table"
						style="margin-top:30%; height: 50px;color: #000000;  left: 150.9px; display: none;" id="emailType1">
						<div class="content_header">

							<div class="cont_hdr_title">EMAIL TYPE</div>
							<input type="hidden" name="objectId" id="objectID">
							<div class="form_buttons">
									<a href="#" onClick="hideEmailType()">UPDATE</a>
								</div>
						</div>
						<div class="cont_data_body">

							<div class="rows">
								<div class="fieldsl">
									<label style="width: 200px !important;">SPD </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user2.spdEnabled" name="user2.spdEnabled"
											id="userspdEnabled1" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">LTL </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user2.ltlEnabled" name="user2.ltlEnabled"
											id="userltlEnabled1" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">CHB </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user2.chbEnabled" name="user2.chbEnabled"
											id="userchbEnabled1" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">FWD </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user2.fwdEnabled" name="user2.fwdEnabled"
											id="userfwdEnabled1" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">FPA </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user2.fpaEnabled" name="user2.fpaEnabled"
											id="userfpaEnabled1" />
									</div>
								</div>

							</div>

						 	 

						</div>

					</div>
					
     </div>

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
		var username=document.getElementById("userName").value;
				
				
				var spd= document.getElementById("userspdEnabled").checked;
				var chb= document.getElementById("userchbEnabled").checked;
				var ltl= document.getElementById("userltlEnabled").checked;
				var fwd= document.getElementById("userfwdEnabled").checked;
				var fpa= document.getElementById("userfpaEnabled").checked;
		
		 if(rv != "" && rv!= "-1"){
			    
				ajax_Carrier=ajaxFunction();
				ajax_Carrier.onreadystatechange=function()
			  	{    
			  		 if(ajax_Carrier.readyState==4)
						{
					 	   reponse=ajax_Carrier.responseText;
					 	  if(reponse!="0"){
					 		 					 		  $("#rowuser").append(reponse);
					 		 					 		  
					 		 					 		   document.getElementById("userspdEnabled").checked = false;
					 		 					 		document.getElementById("userchbEnabled").checked = false;
					 		 					 		document.getElementById("userltlEnabled").checked = false;
					 		 					 		document.getElementById("userfwdEnabled").checked = false;
					 		 					 		document.getElementById("userfpaEnabled").checked = false;  
					 		 					 		  
					 		 					 		  
					 		 					 	   }else{
					 		 					 		   alert("Already Added.")
					 		 					 	   }
					   	 
					  	
						}
			  		 
			  	 
			 	 };
			 	var url="new.userBus.action?root="+rv+"&partner="+pv+"&nation="+nv+"&branch="+bv+"&username1="+username+"&spd="+spd+"&chb="+chb+"&ltl="+ltl+"&fwd="+fwd+"&fpa="+fpa;
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
			
			ajax_Carrier=ajaxFunction();
						ajax_Carrier.onreadystatechange=function()
					  	{    
					  		 if(ajax_Carrier.readyState==4)
								{
							 	   reponse=ajax_Carrier.responseText;
							 	    
								}
					 	 };
					 	var url="new.userBus.action?deleteId="+txt;
						ajax_Carrier.open("GET",url,true);
					ajax_Carrier.send(this);
						
		
	}
	}
	
	
	function editEmailType(objId){
				
		  
				document.getElementById("objectID").value=objId;
			    pop("emailType1");
			    document.getElementById("loader").style.display = 'block';
		
				var spd= document.getElementById("spdEnabled"+objId).value;
				var chb= document.getElementById("chbEnabled"+objId).value;
			    
				var ltl= document.getElementById("ltlEnabled"+objId).value;
				var fwd= document.getElementById("fwdEnabled"+objId).value;
				var fpa= document.getElementById("fpaEnabled"+objId).value;
				var alle= document.getElementById("allEmailType"+objId).value;
		 
				
				if(spd=="true"){
					document.getElementById("userspdEnabled1").checked=true;
				}else{
					document.getElementById("userspdEnabled1").checked=false;
				}
				  
				if(chb=="true"){
					document.getElementById("userchbEnabled1").checked = true;
				}else{
					document.getElementById("userchbEnabled1").checked = false;
				}
				
				if(ltl=="true"){
					document.getElementById("userltlEnabled1").checked = true;
				}else{
					document.getElementById("userltlEnabled1").checked = false;
				}
				if(fwd=="true"){
					document.getElementById("userfwdEnabled1").checked = true;
				}else{
					document.getElementById("userfwdEnabled1").checked = false;
				}
				
				if(fpa=="true"){
		
					document.getElementById("userfpaEnabled1").checked = true;
				}else{
					document.getElementById("userfpaEnabled1").checked = false;
				}
				
				if(alle=="true"){
					document.getElementById("userfpaEnabled1").checked = true;
					document.getElementById("userfwdEnabled1").checked = true;
					document.getElementById("userltlEnabled1").checked = true;
					document.getElementById("userchbEnabled1").checked = true;
					document.getElementById("userspdEnabled1").checked=true;
					
				}
				
		}
		
		
			function hideEmailType(){
				
				  var objectId=document.getElementById("objectID").value;
				  var username=document.getElementById("userName").value;
				  
			    document.getElementById("loader").style.display = 'none';
			    document.getElementById("emailType1").style.display = 'none';
			    
				var spd= document.getElementById("userspdEnabled1").checked;
				var chb= document.getElementById("userchbEnabled1").checked;
				var ltl= document.getElementById("userltlEnabled1").checked;
				var fwd= document.getElementById("userfwdEnabled1").checked;
				var fpa= document.getElementById("userfpaEnabled1").checked;
				
				var rv=document.getElementById("bufpare"+objectId).value;
				var pv=document.getElementById("bufpart"+objectId).value;
				var nv=document.getElementById("bufnation"+objectId).value;
				var bv=document.getElementById("bufbranch"+objectId).value;
				
				  document.getElementById("spdEnabled"+objectId).value=spd;
				  document.getElementById("chbEnabled"+objectId).value=chb;
				  document.getElementById("ltlEnabled"+objectId).value=ltl;
				  document.getElementById("fwdEnabled"+objectId).value=fwd;
				  document.getElementById("fpaEnabled"+objectId).value=fpa;
				
				   
					  
					  
					  
					  
		
						ajax_Carrier=ajaxFunction();
						ajax_Carrier.onreadystatechange=function()
					  	{    
					  		 if(ajax_Carrier.readyState==4)
								{
							 	   reponse=ajax_Carrier.responseText;
							 	    
								}
					 	 };
					 	var url="new.userBus.action?root="+rv+"&partner="+pv+"&nation="+nv+"&branch="+bv+"&username1="+username+"&spd="+spd+"&chb="+chb+"&ltl="+ltl+"&fwd="+fwd+"&fpa="+fpa+"&objectId="+objectId;
					  //alert(objectId);
						ajax_Carrier.open("GET",url,true);
						ajax_Carrier.send(this);
						
					  
					  
						 
					  
				  if( !document.getElementById("userspdEnabled1").checked
						 &&  !document.getElementById("userchbEnabled1").checked 
				         &&  !document.getElementById("userltlEnabled1").checked
				         &&  !document.getElementById("userfwdEnabled1").checked
				         &&  !document.getElementById("userfpaEnabled1").checked){
					  
					  
					  document.getElementById("allEmailType"+objectId).value="true";
					  document.getElementById("ahref"+objectId).innerHTML="ALL EMAIL TYPES.";
					  
					 }else if(
							 document.getElementById("userspdEnabled1").checked
							 &&  document.getElementById("userchbEnabled1").checked 
					         &&  document.getElementById("userltlEnabled1").checked
					         &&  document.getElementById("userfwdEnabled1").checked
					         &&  document.getElementById("userfpaEnabled1").checked		 
					 ){
						  document.getElementById("allEmailType"+objectId).value="true";
						  document.getElementById("ahref"+objectId).innerHTML="SPD, LTL, CHB, FWD, FPA ";
				  }else{
				  
					  document.getElementById("allEmailType"+objectId).value="false";
						var hef="";
						if(document.getElementById("userspdEnabled1").checked){
						  hef="SPD ,";
						} 
						if(document.getElementById("userchbEnabled1").checked){
						hef+="CHB, "; 
						} 
						if(document.getElementById("userfwdEnabled1").checked){
							hef+="FWD, "; 
						} 
						if(document.getElementById("userltlEnabled1").checked){
							hef+="LTL , "; 
						} 
						if( document.getElementById("userfpaEnabled1").checked){
							hef+="FPA, "; 
						} 
						document.getElementById("ahref"+objectId).innerHTML=hef;
				  }
					document.getElementById("userchbEnabled1").checked = false;
					document.getElementById("userltlEnabled1").checked = false;
					document.getElementById("userfwdEnabled1").checked = false;
					document.getElementById("userfpaEnabled1").checked = false;
					document.getElementById("userspdEnabled1").checked=false;
					
					
					  
					
			}
			
		 
			
				 function pop(div) {
			        document.getElementById(div).style.display = 'block';
			      }
			      function hide(div) {
			        document.getElementById(div).style.display = 'none';
			      }
			      //To detect escape button
			      document.onkeydown = function(evt) {
			        evt = evt || window.event;
			        if (evt.keyCode == 27) {
			        	 document.getElementById("loader").style.display = 'none';
			     	    document.getElementById("emailType1").style.display = 'none';
			          
			        }
			      };
			      
			      
			      function getStyle()
					{
						var bothColor;
						<% String buttonColor=(String)request.getSession().getAttribute("buttonColor");
						String barSecondColor=(String)request.getSession().getAttribute("barSecondColor");
						
						if(buttonColor==null)
						{
							buttonColor="#990000";
						}
					
						if(barSecondColor==null)
						{
							barSecondColor="#000000";
						}
						
						%>
						
						
						bothColor=document.getElementById("buttonColor").value+","+document.getElementById("barSecondColor").value;
						return bothColor;
					}
			      
			      
		$(document).ready(function(){
		   
			 var colorCode=getStyle();
			 var colorCodeSplit=colorCode.split(",");
			 var buttonColor=colorCodeSplit[0];
			 var barSecondColor=colorCodeSplit[1];
		$('.navi4 ul li:first-child').css('background-color',buttonColor);  
		$('.navi4 ul li:first-child').css('display',"block");
		$('#box2').css('display','none');
									 
		$('.navi4 ul li').click(function(){
			
			 
			  $(this).css('background-color',buttonColor);
			$(this).siblings().css('background-color',barSecondColor);  
			/* 
			  $(this).css('background-color','#990000');
				$(this).siblings().css('background-color','#000000');   */
			 
			var indexval = $(this).index();
			if(indexval == 0){
				$('#box1').css('display','block');
				$('#box2').css('display','none');
			}
			//alert(indexval);
			if(indexval == 1){
				$('#box2').css('display','block');
				$('#box1').css('display','none');
			}
			 
		});
		 
								
		});
			  
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
<div class="content" style="margin-bottom:10px;">
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
    <s:if test="%{#session.ROLE.contains('sysadmin')}">
    <div class="navi4">
							<ul style="float:left; width:400px; border:0px;">
					<li><mmr:message messageId="menu.admin.adduser"/></li>
					<li><mmr:message messageId="label.user.business"/></li>

				</ul>
				<div class="form_buttons">
				<a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
				<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a>
				</div>
						</div></s:if>
    </div>
    
    <input type="hidden" id="buttonColor" value=<%= buttonColor %> />	
	 <input type="hidden" id="barSecondColor" value=<%= barSecondColor %> />
							
<div id="box1">
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
									
									<s:if test="%{#session.ROLE.contains('sysadmin')}">
									 </s:if><s:else>
									  <a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a>
								</s:else> 
									
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
									<div class="fields">
										<label><mmr:message messageId="label.summary.label"/> </label>
										<div class="controls"><s:checkbox key="user.summaryLabel" name="user.summaryLabel" /></div>
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
									<div class="fieldsl">
										<label><mmr:message messageId="label.summary.label"/> </label>
										<div class="controls"><span>:</span><s:checkbox key="user.summaryLabel" name="user.summaryLabel" /></div>
									</div>
								</div>
								
							</div>	
							</s:else>
						</div>
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
						</div>
						
						 		</s:form>
		
		
    
     
	</div>
	
	<s:if test="%{#session.ROLE.contains('sysadmin')}"> 
						 <div class="content" >
						 <div class="content_body">
						<div id="box2">
					<div class="content_table" >
					
						<div class="form-container" style="background-color: #FFF;" id="userbustable">
							<div id="srchusr_results">
								<div id="srchusr_res">
									<span><mmr:message messageId="" /><mmr:message messageId="label.user.business"/></span>
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
											<th style="text-align: left; padding-left: 10px;"><mmr:message messageId="lable.email.type"/></th>
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
				
				
				<td class="tablerow3" align="center" style="width: 250px;">
				<label>
				 
				<a href="#" class="form_buttons" style="float: left;" id="ahref<s:property value="%{id}"/>"  onclick="editEmailType(<s:property value="%{id}"/>)">
				
				  <s:if test="%{allEmailType}">
				SPD,LTL,CHB,FWD,FPA
				</s:if>
				<s:else>
				 
				<s:if test="%{spdEnabled}">
					SPD,
					
				</s:if>
				<s:if test="%{ltlEnabled}">
				LTL,
				
				</s:if>
				<s:if test="%{chbEnabled}">
				CHB,
				
				</s:if>
				<s:if test="%{fwdEnabled}">
				FWD,
				
				</s:if>
				<s:if test="%{fpaEnabled}">
					FPA
				</s:if>
			 
				</s:else>
				
				</a>
				<input type="hidden" value="<s:property value='%{spdEnabled}'/>" name="spdEnabled" id="spdEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{fpaEnabled}'/>" name="fpaEnabled" id="fpaEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{fwdEnabled}'/>" name="fwdEnabled" id="fwdEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{chbEnabled}'/>" name="chbEnabled" id="chbEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{ltlEnabled}'/>" name="ltlEnabled" id="ltlEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{allEmailType}'/>" name="allEmailType" id="allEmailType<s:property value="%{id}"/>"/>
				 
				 <input type="hidden" value="<s:property value='%{parentBus.id}'/>" name="parentBusIds1" id="bufpare<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{branchBus.id}'/>" name="branchBusIds1" id="bufbranch<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{nationBus.id}'/>" name="nationBusIds1" id="bufnation<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{partnerBus.id}'/>" name="partnerBusIds1" id="bufpart<s:property value="%{id}"/>"/>
				 
 				</label>
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
									<label style="width:200px !important;"><mmr:message messageId="label.Root.business"/> </label>
										<div class="controls"><span>:</span>
										 <s:select id="rid" list="userBusiness.businessFilter.ParentBusList" listKey="id" listValue="name"  headerKey="-1" headerValue="ANY" onchange="setPartnerBus(this.value)"></s:select>
									</div></div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.partner.business"/> </label>
										<div class="controls"><span>:</span>
										<div id="partnerBus">
										<s:select id="pid" list="userBusiness.businessFilter.partnerBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"></s:select>
										</div>
									</div>
									</div>
									<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.nation.business"/></label>
										<div class="controls"><span>:</span>
										<div id="nationBus">
										 <s:select id="cid" list="userBusiness.businessFilter.nationBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"></s:select>
										 </div>
									</div>
									</div>
							    	<div class="fieldsl">
										<label style="width:200px !important;"><mmr:message messageId="label.branch.business"/> </label>
										<div class="controls"><span>:</span>
										<div id="branchBus">
										 <s:select id="bid"list="userBusiness.businessFilter.branchBusList" listKey="id" listValue="name" headerKey="-1" headerValue="ANY"> </s:select>
										 </div>
									</div>
									</div>
									
									
								</div>
								
						 

							<div class="rows">
								<div class="fieldsl">
									<label style="width: 200px !important;">SPD </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user1.spdEnabled" name="user1.spdEnabled"
											id="userspdEnabled" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">LTL </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user1.ltlEnabled" name="user1.ltlEnabled"
											id="userltlEnabled" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">CHB </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user1.chbEnabled" name="user1.chbEnabled"
											id="userchbEnabled" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">FWD </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user1.fwdEnabled" name="user1.fwdEnabled"
											id="userfwdEnabled" />
									</div>
								</div>
								<div class="fieldsl">
									<label style="width: 200px !important;">FPA </label>
									<div class="controls">
										<span>:</span>
										<s:checkbox key="user1.fpaEnabled" name="user1.fpaEnabled"
											id="userfpaEnabled" />
									</div>
								</div>

							</div>

						 	 

						 </div>
						</div>
</div>
					
					</div>
					</div>
						</s:if>
		 
</div>	
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</div>
</body>
</html>
    
    
