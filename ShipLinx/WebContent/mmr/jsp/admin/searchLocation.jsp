<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
<SCRIPT language="JavaScript">

var vkey = "";
	function submitform()
	{
		var whid = document.getElementById("warehouses").value;
		var aisle = document.getElementById("aisle").value;
		var row = document.getElementById("row").value;
		if(whid == -1)
		{
			alert("Please select Warehouse Name");
		}
		else 
		{
			document.searchform.action = "searchWarehouseLocations.action";
			document.searchform.submit();
		}
	}
	
	function addNewWarehouseLocation()
	{	
		document.searchform.action = "goToaddOrEditWarehouseLocations.action?method=new";
		document.searchform.submit();
	}
	
	function resetform()
	{
		document.searchform.action = "goToManageLocations.action";
		document.searchform.submit();
	}
	
	 function populateAisle(ar)
	  {
		  var aislerow=ar;
		  ajax_Country=ajaxFunction();
				ajax_Country.onreadystatechange=function()
				  {
					   if(ajax_Country.readyState==4)
						{
						reponse=ajax_Country.responseText;
						if(aislerow=='aisle')
							js_stateid=document.getElementById("aisleId");
						else
							js_stateid=document.getElementById("rowId");
						js_stateid.innerHTML= reponse;
						}
				  }
				  if(aislerow=='aisle')
				  	firstBox = document.getElementById('warehouses');
				  else
				  	firstBox = document.getElementById('aisle');
				  url="showAisleOrRow.action?method="+aislerow+"&value="+firstBox.value;
					ajax_Country.open("GET",url,true);
					ajax_Country.send(this);
	  }  
		
</SCRIPT>

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="searchCustomer" name="searchform">
<div id="srch_location_panel">
<table>
<tr>
<td><div id="srch_crtra"><mmr:message messageId="label.search.locations"/></div></td>
<td>&nbsp;</td>
</tr>
</table>


<div id="srchlocationactions_imgs">
<table>
<tr>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/search_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/addNew_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0"></td>
<td><img src="<s:url value="/mmr/images/reset_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>
<div id="srchlocation_actions">
<table>
<tr>
<td><a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
<a href="javascript: addNewWarehouseLocation()"><mmr:message messageId="label.search.addnew"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><a href="javascript: resetform()"><mmr:message messageId="label.btn.reset"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>

<div id="srchlocation_table">
<table>
<tr>
<td class="hdr"><mmr:message messageId="label.warehouse.name"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.aisle"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.row"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
<td><s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="warehouseId" 
					listValue="warehouseName" name="warehouseLocation.warehouseId" list="#session.WHLIST" headerValue="--Select--"
						headerKey="-1" id="warehouses" theme="simple" onchange="populateAisle('aisle')"/> </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td id="aisleId">
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="aisle" 
					listValue="aisle" name="warehouseLocation.aisle" list="#session.AISLELIST" headerValue="--ALL--" 
						headerKey="-1" id="aisle" theme="simple" onchange="populateAisle('row')"/>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td id="rowId">
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="row" 
					listValue="row" name="warehouseLocation.row" list="#session.AISLELIST" headerValue="--ALL--"
						headerKey="-1" id="row" theme="simple"/>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>

</div>
</div>

<div id="tab"><br/></div>
	<div id="res"><mmr:message messageId="label.search.results"/></div>
	<div id="results">	
	<s:if test="%{warehouseLocationList.size()>1}">
	<div id="rslt_stmnt"><br/><s:property value="warehouseLocationList.size()" /><mmr:message messageId="label.search.results.items"/>
	</div>
	</s:if>
	<s:elseif test="%{warehouseLocationList.size()==1}">
	<div id="rslt_stmnt"><br/><s:property value="warehouseLocationList.size()" /><mmr:message messageId="label.search.results.item"/>
	</div>
	</s:elseif>
	<s:else>
	<div id="rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/>
	</div>
	</s:else>
	</div>
	
<div id="result_tbl">		
		<display:table id="product" uid="row" name="warehouseLocationList" pagesize="100" export="true" cellspacing="0" cellpadding="4" class="srch_tbl">
		<s:hidden name="locationId" value="%{#attr.row.locationId}"/>		
		   <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
		   		<s:a href="goToaddOrEditWarehouseLocations.action?locationId=%{#attr.row.locationId}"> 
				<img src="<s:url value="/mmr/images/edit_pencil.png" includeContext="true" />" alt="Edit Customer" border="0"> </s:a>
	   		</display:column>
            <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
			  	<s:a onclick="return confirm('Do you really want to delete the selected Product?')" href="deletewarehouseLocation.action?locationId=%{#attr.row.locationId}">
				<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Customer Account Info" border="0"> </s:a>
            </display:column>
			<display:column headerClass="tableTitle2_product" property="warehouse.warehouseName" title="Name" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="aisle" title="Aisle" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="row" title="Row" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="level" title="Level" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="section" title="Section" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="locationName" title="Location" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="warehouseLocationType.warehouseLocationTypeName" title="Location Type" style="text-align:center;"/>	
		</display:table>
</div>
<div id="res_tbl_end"></div>
   
</s:form>
</div>
</body>
</html>