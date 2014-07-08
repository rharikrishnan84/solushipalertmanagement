<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html> 
<head> 
	 <sx:head/>
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">
	function submitform()
	{
		document.searchform.action = "get.products.action";
		document.searchform.submit();
	}
	
	dojo.event.topic.subscribe("/value_name", function(value, key, text, widget){
		var vkey = key;
		 ajax_Country_desc=ajaxFunction();	 
		ajax_Country_desc.onreadystatechange=function()
	  	{
		   if(ajax_Country_desc.readyState==4 && ajax_Country_desc.status==200)
			{
				response_desc=ajax_Country_desc.responseText;
				js_stateid=document.getElementById("manageproducts");
				js_stateid.innerHTML= response_desc;
			}			
	  	}	
		url="listCustomerProducts.action?cid="+key;
		ajax_Country_desc.open("GET",url,true);
		ajax_Country_desc.send(this);	
		});
	
	function addNewProductLine()
	{
		var autoCompleter = dojo.widget.byId("customerName");
		var value = autoCompleter.getSelectedValue();
		var key = autoCompleter.getSelectedKey();
		
		if (value == null || value == "") {
			alert("Please select a Customer");			
		}
		else
		{
			document.searchform.action = "goToAddProductLine.action?cid="+key;
			document.searchform.submit();
		}
	}
		
</SCRIPT>

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="searchCustomer" name="searchform">

<s:if test="%{#request.role=='busadmin'}">
<div id="srch_panel">
<table>
<tr>
<td><div id="srch_crtra"><mmr:message messageId="menu.search.product"/></div></td>
<td>&nbsp;</td>
</tr>
</table>
</div>

<div id="srchproductactions_imgs">
<table>
<tr>
<td><!-- <img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">-->&nbsp;</td>
<td><!--<img src="<s:url value="/mmr/images/search_icon.png" includeContext="true" />" border="0">-->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/addNew_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<!--  <td><img src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>-->  
</tr>
</table>
</div>
<div id="srchproduct_actions">
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript: addNewProductLine()"><mmr:message messageId="label.addnew.product.line"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<!--   <td><a href="#"><mmr:message messageId="label.btn.save"/></a>&nbsp;&nbsp;&nbsp;</td>-->
</tr>
</table>
</div>

<div id="srchproduct_table">
<table>
<tr>
<td class="hdr"><mmr:message messageId="label.account.customer"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
<s:url id="customerList" action="listCustomers" />
<sx:autocompleter id="customerName" name="searchString" href="%{customerList}" dataFieldName="customerSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  valueNotifyTopics="/value_name" cssStyle="width:250px;" preload="true"/>
</td>
</tr>
<tr><td colspan="2">&nbsp;</td></tr>
</table>
</div>
</s:if>
<s:include value="manageProductsList.jsp"></s:include>



   
</s:form>
</div>
		


