<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<sx:head/>
    <title><s:text name="user.form.title"/></title> 
    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

    
	</head> 

<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
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
		$(document).ready(function(){
			
			$('#actiondown').click(function(event){
				event.preventDefault();
				$('#actionup').css('display','block');
				$('#actiondown').css('display','none');
				$('#actionmenu').css('display','block');
			});
			$('#actionup').click(function(event){
				event.preventDefault();
				$('#actionup').css('display','none');
				$('#actiondown').css('display','block');
				$('#actionmenu').css('display','none');
			});
		});
	  	
 
	</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

<script>

  
 </script>
 <script type="text/javascript">
 $(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
   </script>
<script type="text/javascript">
function addPackageMap()
{
	var product=document.getElementById("productId").value;
	var pack=document.getElementById("packageId").value;
	var quanity=document.getElementById("quanity").value;
	
	if(product==-1 || pack==-1){
		alert("Please Select Product and Package");
	}else{
		 
			 document.userform.action = "addPackageMap.action";
			 document.userform.submit();
		}
}

function savePackageMap(){
	 document.userform.action = "addPackageMap.action";
	 document.userform.submit();
}
function editPackageMap(){
	var deleteUserId = document.getElementsByName("searchUserCheckBox");
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
		document.getElementById("searchUserFormId").action = "edit.packageMap.action?PackageMapId="+txt;
		document.getElementById("searchUserFormId").submit();
   }
	
}
function deletePackageMap(){
	var deleteUserId = document.getElementsByName("searchUserCheckBox");
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
		 
		//createCustomConfirm;
		var ALERT_BUTTON_TEXT = 'Ok';
		var CANCEL_BUTTON_TEXT = 'Cancel';
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
		/*h1 = alertObj.appendChild(d.createElement("h1"));
		h1.appendChild(d.createTextNode(ALERT_TITLE));*/
		msg = alertObj.appendChild(d.createElement("p"));
		//msg.appendChild(d.createTextNode(txt));
		msg.innerHTML = "Do you really want to delete the selected Package Map";
		//var status;	
		
		btnbodyObj = alertObj.appendChild(d.createElement("div"));
		btnbodyObj.id = "btnBody";
		
		
		btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
		btnconfirm.id = "confirmBtn";
		btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
		btnconfirm.href = "#";
		btnconfirm.focus();
		$('#confirmBtn').css('margin-left','30px');
		
		btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
		btnconfirmCancel.id = "confirmCancelBtn";
		btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
		btnconfirmCancel.href = "#";
		btnconfirmCancel.focus();
		$('#confirmCancelBtn').css('margin-right','30px');
		
		
		//btnconfirm.onclick = function() {removeCustomAlert();return false; }
		$('#confirmBtn').click(function(){
			removeCustomConfirm();
			document.getElementById("searchUserFormId").action = "delete.packageMap.action?PackageMapId="+txt;
			document.getElementById("searchUserFormId").submit();
		});
		$('#confirmCancelBtn').click(function(){
			removeCustomConfirm();
			
		});
		alertObj.style.display = "block";
		function removeCustomConfirm() {
		//document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
		}
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
   <s:if test="#session.editPackageMap == 'true'">
   <input type="hidden" name="productPackageId1" id="packageMapId1" value="<s:property value="productPackageMap.productPackageId"/>"/> 
           </s:if>
 <s:form action="createUser" name="userform"  id="searchUserFormId" style="margin-bottom	:0px">

       <div class="content_table">
        <div class="content_header">
         <div class="cont_hdr_title"><mmr:message messageId="product.package.map"/></div>
		 		 
         <div class="form_buttons">
            <s:if test="#session.editPackageMap != 'true'">
          <a href="javascript: addPackageMap()"><mmr:message messageId="btn.addnew"/></a>
           </s:if>
           <s:else>
             <a href="javascript: savePackageMap()"><mmr:message messageId="btn.update"/></a>
           </s:else>
		 
          </div>
        </div>
        <div class="cont_data_body">
         <div class="rows">
          <div class="fields">
           <label><mmr:message messageId="package.products"/> </label>
           <div class="controls"><span>:</span>
            <s:select   listKey="productId" listValue="productName" 
							name="productPackageMap.productId" headerValue="select Product" headerKey="-1"  list="productList" 
								onchange="javascript:showServices();" cssStyle="height:25px"  id="productId" theme="simple" size="1"   
							disabled="#session.editPackageMap == 'true'"
								/>
           </div>
          </div>
            <div class="fields">
           <label><mmr:message messageId="label.heading.package"/> </label>
           <div class="controls"><span>:</span>
            <s:select   listKey="packageTypeId" listValue="packageName" 
							name="productPackageMap.packageId" headerValue="select Package" headerKey="-1"  list="packageTypesList" 
								   id="packageId" theme="simple" size="1"  cssStyle="height:25px" />
           </div>
          </div>
            <div class="fields">
           <label><mmr:message messageId="label.pieces.number"/> </label>
           <div class="controls"><span>:</span>
            <s:textfield name="productPackageMap.quanity" key="productPackageMap.quanity" id="quanity" value="%{productPackageMap.quanity}"></s:textfield>
           </div>
          </div>
           </div>
		 
         </div>
        </div>
        </s:form>
       </div>
   <s:if test="#session.editPackageMap != 'true'">
     
<div class="content_body">
		<div class="content_table" >
			<div class="form-container" style="background-color:#FFF;"> 
				
				<div id="mrkup_srch_panel">
				</div>
				<div id="markup_table">			
				</div>
			
				<div id="srchusr_results">
					<div id="srchusr_res"><span><mmr:message messageId="product.package.map.list"/></span></div>
				<!--<img src="<s:url value="/mmr/images/panelResults_top.png" includeContext="true" />" style="margin-left: 52px;margin-top: 16px;width: 853px;position: relative;bottom: 30px;height:31px;" alt="logo"> 	-->
					<div class="form_buttons">
					<a href="#" id="actiondown" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
						<!--<input type="button" value="EDIT" onclick="editMarkup();" />
						<input type="button" value="DELETE" onclick="deleteMarkup();" />
						<input type="button" value="UPLOAD" onclick="uploadMarkup();" />-->
						<li><a href="#" onclick="editPackageMap();"><mmr:message messageId="label.list.edit"/></a></li>
						<li><a href="#" onclick="deletePackageMap();"><mmr:message messageId="label.list.delete"/></a></li>
						 </ul>
					</div>
				</div>
				

<div id="accnt_bottom_table"  style="background-color:#FFF;" >
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left;  background-color:#FFF; width:100%; height:auto;">
    <thead>
	 <tr>
			<th><input id="check_all" type="checkbox" /></th>
			<th><span style="width:250px !important; float:left;"><mmr:message messageId="label.product.name"/></span></th>
			<th><span style="width:250px !important; float:left;"><mmr:message messageId="label.package.name"/></span></th>
			<th><span style="width:120px !important; float:left;"><mmr:message messageId="label.pieces.number"/></span></th>
			
		</tr>
	</thead>	
	<tbody>
<s:set var="index" value="0" />
	
	<s:iterator id="usertable" value="productPackageMapList" status="rowstatus">
             <tr>
            <s:if test="#rowstatus.even1 == true">
	         <td class="odd1" width="2%">
			 <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="productPackageId"/>"/> 
	             </td>
			  
			   <td ><s:property value="product.productName"/></td>
				<td><s:property value="packages.packageName"/></td>
	            <td><s:property value="quanity"/></td>
	            
	            </s:if>
			<s:else>
		        <td class="even" width="2%">
		        <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="productPackageId"/>"/> 
	             </td>
			  
			   <td ><s:property value="product.productName"/></td>
				<td><s:property value="packages.packageName"/></td>
	            <td><s:property value="quanity"/></td>
	            
			</s:else>
            </tr>			
            </s:iterator>
	
			 </tbody>
			 </table>
            
</div>
		<div id="markup_res_tbl_end"></div>
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

</body>
</html>
    
    
 