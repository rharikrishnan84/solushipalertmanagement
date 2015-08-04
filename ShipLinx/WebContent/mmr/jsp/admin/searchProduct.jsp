<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head> 
<sj:head jqueryui="true" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/shiplinx_closeWindow_styles_Chrome.css" />-->
		
	<script type="text/javascript">
	
		$(document).ready(function() {
			$('#samplesearchproduct').dataTable(); 
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
			
			/*$('.gridedit').click(function(){
				var check = $(".dataTable-checkbox:checked").length;
				if(check < 1){
					alert('Please check atleast one checkbox');
					location.href="admin/search.products.action?method=new#";
				}
				
			});*/
			
			
			
			
		} );
	</script>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<sx:head/>
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 
<div id="loader" style="height:100%; width:100%; position:fixed; display:none; background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div id="loaderImg" style="width:100px; height:100px; margin:200px auto; z-index:1000; background-image:url('../mmr/images/ajax-loader2.gif');"> 
    </div></div>
<SCRIPT language="JavaScript">

var vkey = "";
	function submitform()
	{
		var autoCompleter = dojo.widget.byId("customerName");
		if(autoCompleter!=undefined)
		{
			var value = autoCompleter.getSelectedValue();
			var key = autoCompleter.getSelectedKey();
			if (value == null || value == "") 
			{
				alert("Please select a Customer");			
			}
			else
			{
				//call some action
				document.searchform.action = "get.products.action?cid="+key;
				document.searchform.submit();
			}
		}
		else
		{
			document.searchform.action = "get.products.action";
			document.searchform.submit();
		}
	}
	
	function addNewProduct()
	{	
		var autoCompleter = dojo.widget.byId("customerName");
		if(autoCompleter!=undefined)
		{
			document.searchform.action = "addnewproduct.action";
			document.searchform.submit();
		
		}
		else
		{			
			document.searchform.action = "addnewproduct.action";
			document.searchform.submit();
			
		}
	}
	function resetform()
	{
		document.searchform.action = "get.products.action?method=reset";
		document.searchform.submit();
	}
		
	
	function editProduct(){
		
			var editUserId = document.getElementsByName("searchProductCheckBox");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please check atleast one');
			}
			 else if(txt1 > 1){
			alert('Please check atmost one');
		   }
			else{
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
					}
				}
				document.getElementById("searchProductFormId").action = "editproduct.action?productId="+txt;
				document.getElementById("searchProductFormId").submit();
			}
			
			
			
		}
		
		function deleteProduct(){
	
			
			
			var editUserId = document.getElementsByName("searchProductCheckBox");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please check atleast one');
			}
			 else if(txt1 > 1){
			alert('Please check atmost one');
		   }
			else{
				/*var del=confirm("Do you really want to delete the selected Product?");
				if(del==true){
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
					}
				}
				document.getElementById("searchProductFormId").action = "deleteProduct.action?productId="+txt;
				document.getElementById("searchProductFormId").submit();
				}*/
				
				
				
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
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
				msg.innerHTML = "Are you want to delete the Record";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.getElementById("searchProductFormId").action = "deleteProduct.action?productId="+txt;
					document.getElementById("searchProductFormId").submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}
			
			
			
		}
	
	function addNewProductLine()
	{
		var autoCompleter = dojo.widget.byId("customerName");
		if(autoCompleter!=undefined)
		{
			var value = autoCompleter.getSelectedValue();
			var key = autoCompleter.getSelectedKey();
			if (value == null || value == "") 
			{
				alert("Please select a Customer");			
			}
			else
			{
				//call some action
				document.searchform.action = "goToAddProductLine.action?cid="+key;
				document.searchform.submit();
			}
		}
		else
		{
			document.searchform.action = "goToAddProductLine.action";
			document.searchform.submit();
		}
	}
	
	dojo.event.topic.subscribe("/value_name", function(value, key, text, widget){
		vkey = key;		
		});
	
	
	 function syncThread(){
		          $('#loader').css('display','block');
		          $('#loaderImg').css('display','block');
		          ajax_Carrier=ajaxFunction();
		          ajax_Carrier.onreadystatechange=function()
		            {    
		             
		           
		               if(ajax_Carrier.readyState==4)
		              {
		                reponse=ajax_Carrier.responseText;
		                chargeCodeElement = document.getElementById("ajaxTable");
		                  chargeCodeElement.innerHTML= reponse;
		                location.reload();
		                $('#loader').css('display','none');
		              $('#loaderImg').css('display','none');
		              }
		              
		           
		           };
		          url="synchShopifyProduct.action";
		          ajax_Carrier.open("GET",url,true);
		          ajax_Carrier.send(this);
		          
		        }
		        
		    
		</SCRIPT>
		<script>
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
<script>
  $(document).ready(function(){
   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });

		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#samplesearchproduct_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
 
  });
  

		
		
 </script>
		<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
<div id="messages">
	<div class="message_inner">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>	
</div>

<div class="content">
<div class="content_body">
<div class="content_table" style="background-color:#fff">
<div class="form-container"> 
<s:form action="searchCustomer" cssClass="form" name="searchform" id="searchProductFormId" >


	
	<div id="results" >
		<div id="res">
			<span><mmr:message messageId="label.heading.customerproductlist" /> </span>
		</div>
		<div id="rslt_stmnt">
			<div class="form_buttons" style="float:right;">
			 <a href="#" onclick="syncThread()" > <mmr:message messageId="menu.product.sych"/></a>
			<a href="#" id="actiondown" ><p style="font-size:12px; float:left;width:auto; height:15px; padding:0px;"><mmr:message messageId="label.action" /> </p> <p style="font-size:12px; float:left; padding:0px; width:15px; height:15px;">&#9660;</p></a>
			<a href="#" id="actionup" ><p style="font-size:12px; float:left;width:auto; height:15px; padding:0px;"><mmr:message messageId="label.action" /> </p>  <p style="font-size:12px; float:left; padding:0px; width:15px; height:15px;">&#9650;</p></a>
			<ul id="actionmenu">
				<li><a href="<%=request.getContextPath()%>/admin/addnewproduct.action" ><mmr:message messageId="label.list.addproduct"/></a></li>
				<li><s:a onclick="editProduct();" href="#" cssClass="gridedit" ><mmr:message messageId="label.list.edit"/> </s:a></li>
				<li><s:a onclick="deleteProduct()" href="#" cssClass="griddelete"><mmr:message messageId="label.list.delete"/> </s:a></li>
			</ul>
			</div>		
		</div>
	</div>
	
<div id="result_tbl">
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="samplesearchproduct" width="100%">	
<thead>
		<tr>
			
			<th style="width:39px ! important;"><input id="check_all" type="checkbox"/></th>
			<th><mmr:message messageId="label.product.name"/></th>
			<th style="width: 400;!important"><mmr:message messageId="label.product.description"/></th>
			<th><mmr:message messageId="label.product.hcode"/></th>
			<th style="width: 160px;!important"><mmr:message messageId="label.product.origincountry"/> </th>
			<th style="width: 115px;!important"><mmr:message messageId="label.product.unitprice"/> </th>
		</tr>
    </thead>
<tbody id="ajaxTable"> 	
		<s:iterator id="product"  status="rowstatus" value="productList">	
			<tr>	
			<s:hidden name="products.pId" value="%{products.productId}"/>
			<s:hidden name="products.cid" value="%{products.customerId}"/>
			<td><input  class="dataTable-checkbox" type="checkbox" name="searchProductCheckBox"  value="<s:property value="productId"/>"/> </td>
			<td><s:property value="productName"/></td>
			<td title="<s:property value="productDescription"/>"><div style="width:380px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="productDescription"/></div></td>
			<td><s:property value="productHarmonizedCode"/></td>
			<td><s:property value="originCountry"/></td>
			<td style="text-align: right;"><s:property value="unitPrice"/></td>
			
		</tr>
		</s:iterator>
		</tbody>
		</table>
		
		
</div>
   
</s:form>

</div>

<div class="exportlinks">    
	<mmr:message messageId="label.bottom.exportto"/>: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="product.download.action?type=csv"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
 <a href="product.download.action?type=xl"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
 <a href="product.download.action?type=xml"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
</div>


</div>
</div>
</div>


		


