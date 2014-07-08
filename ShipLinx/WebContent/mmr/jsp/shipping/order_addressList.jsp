<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
	
%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<%
String addressId=(String)request.getAttribute("searchId");
if(addressId==null){
	addressId="";
}
%>
<html>
<head>
</head>
<body>
<SCRIPT language="JavaScript">

function showShipFromState() {
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("stateid");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox');
		  url="shipFrom.listFromProvience.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()
	        
	  function showShipToState() {
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("stateid2");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox2');
		  url="shipTo.listToProvience.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()      

	function searchFromAddress()
	{
		var value= document.getElementById("addressFromId").value;
		var url="<%=request.getContextPath()%>/search.customer.from.address.action?searchId="+value+"&type=fromAddress";
		displaySearchAddressFrom(url);
	}
	
	function sendThisAddress(url,status)
	{
		var fullurl="<%=request.getContextPath()%>" + url;
		alert(fullurl);
		selectAddress(fullurl,status);
	}

	function searchToAddress()
	{
		var value= document.getElementById("addressToId").value;
		var url="<%=request.getContextPath()%>/search.customer.to.address.action?searchId="+value+"&type=toAddress";
		displaySearchAddressFrom(url);
	}
		  
	function submitform(method)
	{
	 document.userform.action = "packageInformation.action?method="+method;
	 document.userform.submit();
	}     

	
</SCRIPT> 
<div id="bottom_tabs1_new" align="right">
			  <table width="100%" border="0" align="right" cellspacing="0" cellpadding="0">
	               <tr align="left">
				   <td align="left" >
					<table border="0" cellspacing="0" cellpadding="0" width="250px">
						<tr>
	                 <td width="5%" align="right"><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif" width="8" height="30" /></td>
	                 <td align="center" width="50%" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text">Address List</td>
	                 <td width="5%" align="left"><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
					  <td width="20%">&nbsp;</td>
					  <td width="20%">&nbsp;</td>
						</tr>
					</table>
					</td>
					<td align="right" valign="top">
						<table valign="top" cellspacing="0" cellpadding="0">	
						<tr valign="top">
					  <td align="right" valign="top"><a href="#" onClick="changeShippingWindowStyleWithShowStatus();"><img src="<%=request.getContextPath()%>/mmr/images/icon_close.gif" border="0" width="18" height="18" /></a></td>
					  <td class="icon_btns" align="left" valign="top" ><a href="#" onClick="changeShippingWindowStyleWithShowStatus();">Show</a></td>
  						</tr>
					</table>
					</td>
	              </tr>
		      </table>
</div>
<div id="right_left_customer">

<div id="bottom_table6" class="text_address">

<table width="100%" border="0" cellpadding="2" cellspacing="0">
	<tr style="height:30px;">
	<td align="center" valign="center" colspan="4" class="text_03"><strong>Search Address</strong></td>
	</tr>
<tr valign="center" align="center" style="height:40px;">

<td class="text_03" align="left"><strong>Id: </strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{id}" name="id" id="id" headerValue="%{id}" cssClass="text_01" cssStyle="width:90px;"/> 
</td>

<td class="text_03" align="right"><strong>Company: </strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{abbreviationName}" name="abbreviationName" id="abbreviationName" cssClass="text_01" cssStyle="width:90px;" /> 
</td>


</tr>
<tr valign="center" align="center" style="height:40px;">

<td class="text_03" align="left"><strong>City: </strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{city}" name="city" id="city" cssClass="text_01" cssStyle="width:90px;"/> 
</td>

<td class="text_03" align="right"><strong>Zip: </strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{postalCode}" name="postalCode" id="postalCode" cssClass="text_01"  cssStyle="width:90px;"/> 
</td>


</tr>
<tr valign="center" align="center" style="height:40px;">

<td class="text_03" align="left"><strong>State</strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{state}" name="state" id="state"  cssClass="text_01" cssStyle="width:90px;" /> 
</td>

<td class="text_03" align="right"><strong>Contact Name: </strong></td><td><s:textfield onchange="javascript:searchOrderFromAddress('%{#session.type}');" value="%{contact}" name="contact" id="contact" cssClass="text_01"  cssStyle="width:90px;"/> 
</td>

</tr>

<tr valign="center" align="center" style="height:40px;">
<td class="text_03" colspan="4" align="centrer"><s:submit value="Clear" onclick="javascript:searchOrderFromAddress('clear');"  cssClass="text_01"  cssStyle="width:90px;align:center"/> 
</td>
</tr>

</table>

</div>
<div id="bottom_table6" class="text_03">


<table width="100%" border="0" cellpadding="2" cellspacing="0">
<input type="hidden" name="isAddressSearch" id="isAddressSearch" value="0"/>
<input type="hidden" name="addressId" id="addressId" value="<%=addressId%>"/>


<display:table id="addresstable"  name="addressList" export="false" uid="row">

	<display:setProperty name="paging.banner.items_name" value=""></display:setProperty>
	<display:setProperty name="paging.banner.some_items_found" value=""/>
	<display:setProperty name="paging.banner.all_items_found" value=""></display:setProperty>
	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
	<display:setProperty name="paging.banner.group_size" value="3"></display:setProperty>

		<s:set name="addressId" value="addressList[#attr.row_rowNum-1].getId()" />
		<display:column>
			 <a href="javascript:sendThisAddress('set.shippingAddress.action?addressid=%{addressId}&type=%{#session.type}','%{#session.WINDOW_STATUS}')" > 
			 <font class="text_address">			<img src="<s:url value="/mmr/images/edit.png" includeContext="true" />" alt="Delete Address" border="0"> </font></a>
		</display:column>
		<display:column class="text_01" property="abbreviationName"  sortable="true" title="Company" />
		<display:column class="text_01" property="city"  sortable="true" title="City" />
		<display:column class="text_01" property="provinceCode"  sortable="true" title="State / Province" />
		<display:column class="text_01" property="contactName"  sortable="true" title="Contact Name" />

	</display:table>


	
</table>
</div>
</div>
</body>
</html>

