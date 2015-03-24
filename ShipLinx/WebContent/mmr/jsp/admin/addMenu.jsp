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
	 			document.userform.action = "save.menu.action";
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
	var lev="";
	var level1='<s:property value="menuScreenVO.menulevel"/>';
	
	var toplev='<s:property value="topLevelId"/>';
	var lev1='<s:property value="firstlevelId"/>';
	var locale='<s:property value="menuScreenVO.locale"/>';
    
	function setmenulevel(level){
	    switch(level){
	    
	    case "TOP":
		document.getElementById("menuLevelList").value="10";
		break;
	    case "LEVEL_1":
	    	document.getElementById("menuLevelList").value="20";
			break;
	    case "LEVEL_2":
	    	document.getElementById("menuLevelList").value="30";
			break;
		default :
			document.getElementById("menuLevelList").value="-1";
		break;
	    }
	    showMenuLevel1();
	}
	
	function showMenuLevel1() {
	//	alert(level1);
		document.getElementById("menulevel").value=level1;
		businessId = document.getElementById('idBusiness').value;
		//alert(businessId);
		 
		if(businessId == "-1"){
			alert("Please Select any business");
			return;
		}
		firstBox = document.getElementById('menuLevelList');
	 
		
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("levelOneMenu");
				js_stateid.innerHTML= reponse;
				 document.getElementById("idTopLevel").value=toplev;
				 document.getElementById("toplevel").value=toplev;
				  document.getElementById("idTopLevel").disabled = true;
		        showSecondMenuLevel1();
		         
				}
		  }
		  url=contextPath+"/menu.listTopMenu.action?level=top&levelValue="+firstBox.value+"&businessId="+businessId+"&locale="+locale;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	}
	
	 
	function showSecondMenuLevel1() {
		
		businessId = document.getElementById('idBusiness').value;
		topId = document.getElementById('idTopLevel').value;
		//alert(topId);
		if(businessId == "-1"){
			alert("Please Select any business");
			return;
		}
		firstBox = document.getElementById('menuLevelList');
		if(firstBox.value == '20'){
			//alert("test");
			return;
		}
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("levelTwoMenu");
				js_stateid.innerHTML= reponse;
				document.getElementById("idFirstLevel").value=lev1;
				document.getElementById("firstlevel").value=lev1;
			 document.getElementById("idFirstLevel").disabled = true;
			// document.getElementById("menuLevelList").disabled = true;	
				}
		  }
		  url=contextPath+"/menu.listTopMenu.action?level=second&levelValue="+firstBox.value+"&businessId="+businessId+"&topLevelId="+topId;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
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
    	<input type="hidden" name="Toplevel" id="toplevel"/>
    	<input type="hidden" name="Firstlevel" id="firstlevel"/>
    	<input type="hidden" name="Menulevel" id="menulevel"/>
    </s:if> 
    <s:else>
    
    	<s:hidden name="method" value="add"/>
     </s:else>
     
     		
    <s:hidden name="cid" value="%{user.customerId}"/>
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title">
									<s:if test="#session.edit == 'true'">
									<mmr:message messageId="label.menu.addNewMenu"/>
									</s:if>
									<s:else>
									<mmr:message messageId="label.menu.addNewMenu"/>
								</s:else>
								</div>
								<div class="form_buttons">
									<!--<s:submit onclick="submitform()" value="SAVE"/>
									<s:submit onclick="resetform()" value="RESET"/>-->
									
									<a href="#" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									<a href="#" onclick="resetform()"> <mmr:message messageId="btn.reset"/></a> 
								</div>
							</div>
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.menu.menuName"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield   id="menuName" key="menuScreenVO.menuName" name="menuScreenVO.menuName"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
									</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.menu.menuUrl"/> </label>
										<div class="controls"><span>:</span><s:textfield  key="menuScreenVO.menuUrl" name="menuScreenVO.menuUrl" /></div>
									</div>
							
					
									<div class="fields">
										<label><mmr:message messageId="label.menu.menuLevel"/> </label>
										<div  class="controls"><span>:</span>
										<s:if test="#session.edit == 'true'">
										<s:select name="menuScreenVO.menulevel" list="#{'-1':'Select One', '10':'Top', '20':'Level One','30':'Level Two'}" 
								 id="menuLevelList" value="statusId"  disabled="true"  onchange="showMenuLevel1();"/>
								 </s:if>
								 <s:else>
								 <s:select name="menuScreenVO.menulevel" list="#{'-1':'Select One', '10':'Top', '20':'Level One','30':'Level Two'}" 
								 id="menuLevelList" value="statusId"  onchange="javascript:showMenuLevel();"/>
								 
								 </s:else>
								 </div>
									</div>
									
											<div  id="levelOneMenu"></div> 
											<div  id="levelTwoMenu"></div>
									<div class="fields">
										<label><mmr:message messageId="label.menu.displayOrder"/> </label>
										<div  class="controls"><span>:</span><s:textfield  key="menuScreenVO.displayOrder" name="menuScreenVO.displayOrder" /></div>
									</div>
								
								</div>
								<!-- <div class="rows" >
									<p id="rules" style="color: #000066; font-size: 12px; font-weight: bold;"></p>
								</div>	 -->
							</div>
						</div>
						
						
						
						<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.menu.resourcebundle"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields">
										<label><mmr:message messageId="label.menu.msgkey"/> </label>
										<div class="controls"><span>:</span>
										<s:if test="#session.edit == 'true'">
										<s:textfield   id="userName" key="menuScreenVO.msgKey" readonly="true" name="menuScreenVO.msgKey"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
										</s:if>
										<s:else>
										<s:textfield   id="userName" key="menuScreenVO.msgKey" name="menuScreenVO.msgKey"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
										</s:else>
									</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.menu.msgContent"/> </label>
										<div class="controls"><span>:</span>
										<s:textfield   id="userName" key="menuScreenVO.msgContent" name="menuScreenVO.msgContent"  onkeypress="return typenumbers(event,\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.@\')"/>
									</div>
									</div>
									<div class="fields">
										<label><mmr:message messageId="label.menu.msgLocale"/> </label>
										<div class="controls"><span>:</span>
										<s:select   name="menuScreenVO.locale"  listKey="locale" listValue="localeText" cssClass="changerole"
										headerKey="-1"  headerValue="--Select Locale--" list="localeList" theme="simple"  id="idLocale" />
									</div>
									</div>
									
								</div>
								
							</div>
						
						</div>
						
						
					<div class="content_table">
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="label.menu.menuRole"/></div>
							</div>
							<div class="cont_data_body">
							
								<div class="rows">
									<div class="fields1">
										<label><mmr:message messageId="label.menu.role"/>:</label>
										<div class="controls"> 
										<s:select   name="menuScreenVO.menuRole"  listKey="role" listValue="role" cssClass="changerole"
										  list="roleList" theme="simple"  id="idRole" multiple="true"
										cssStyle="width:150px;height:100px"/>
									</div>
									</div>
									
									<div class="fields1">
										<label><mmr:message messageId="label.menu.business"/> :</label>
										<div class="controls"> 
										<s:select   name="menuScreenVO.business" value="%{selectedBusinessIds}"  listKey="id" listValue="name" cssClass="changerole"
										  list="businessList" theme="simple" onchange="showHideSalesDiv(this.name);" id="idBusiness" multiple="true"
										cssStyle="width:150px;height:100px"/>
									</div>
									</div>
								</div>
								
							</div>
						
						</div>	
						<s:if test="#session.edit == 'true'">
									<script type="text/javascript">
									count=2;
									
									lev='<s:property value="menuScreenVO.menulevel"/>';
									setmenulevel(lev);
									
									</script>
									</s:if>
									<s:else>
										 <script type="text/javascript">
									count=1;
									var d=0;
									</script>
									</s:else>
						
		</s:form> 
	</div>
</div>	

</body>
</html>
    
    
 