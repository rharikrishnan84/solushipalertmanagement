<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/style.css">

<head>
 	<sx:head/>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<script language="JavaScript">

	function submitform()
	{
		var bsubmit=true;
		var warehouses = document.getElementById("warehouses").value;
		var aisle = document.getElementById("aisleId").value;
		var row = document.getElementById("rowId").value;
		var level = document.getElementById("levelId").value;
		var section = document.getElementById("sectionId").value;
		var locname = document.getElementById("locnameId").value;
		var whloctype = document.getElementById("warehouselocationtypes").value;
		
		if(warehouses.length > 0 && aisle.length > 0 && row.length > 0 && level.length > 0 && section.length > 0 && whloctype != "-1")
			document.getElementById("locnameId").value = aisle+"-"+ row + "-" + level +"-" +section;
		else if(warehouses.length > 0 && aisle.length > 0 && row.length > 0 && level.length > 0 && section.length > 0 && whloctype == "-1")
		{
			alert("Please select a Warehouse Location Type Name");
			bsubmit=false;
		}
		
		if(document.getElementById("warehouses").value=="-1")
		{
			alert("Please select a Warehouse Name");
			bsubmit=false;
		}
		else if(document.getElementById("aisleId").value=="")
		{
			alert("Please enter Aisle");
			bsubmit=false;
		}
		else if(document.getElementById("rowId").value=="")
		{
			alert("Please enter Row");
			bsubmit=false;
		}
		else if(document.getElementById("levelId").value=="")
		{
			alert("Please enter Level");
			bsubmit=false;
		}
		else if(document.getElementById("sectionId").value=="")
		{
			alert("Please enter Section");
			bsubmit=false;
		}
		else if(document.getElementById("locnameId").value=="")
		{
			bsubmit=false;
		}
		else if(document.getElementById("warehouselocationtypes").value=="-1")
		{
			alert("Please select a Warehouse Location Type Name");
			bsubmit=false;
		}
		if(bsubmit)
		{
			if(document.searchform.method.value=='edit')
				document.searchform.action = "addOrEditWarehouseLocations.action?method=edit";
			else
				document.searchform.action = "addOrEditWarehouseLocations.action";
				document.searchform.submit();	 	
	 	}
	}
		
		function resetform()
		{	
			document.searchform.action = "goToaddOrEditWarehouseLocations.action?method=new";
			document.searchform.submit();
		}
		
	
</script>

<html> 
<head> 
 	<title><s:text name="user.form.title"/></title>
 	<script type="text/javascript">
</script>
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container"> 
<s:form action="searchCustomer" name="searchform">
<div id="add_srch_panel">
<table>
<tr>
<td><div id="srch_crtra">
<s:if test="#session.edit != 'true'">
<mmr:message messageId="label.add.locations"/>
</s:if>
<s:else>
<mmr:message messageId="label.edit.locations"/>
<s:hidden name="method" value="edit"/>
</s:else>
</div></td>
<td>&nbsp;</td>
</tr>
</table>
</div>

<div id="addwarehouselocations_imgs">
<table>
<tr>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<s:if test="#session.edit == 'true'">
<td><img src="<s:url value="/mmr/images/cancel.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if>
<s:if test="#session.edit != 'true'">
<td><img src="<s:url value="/mmr/images/reset_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if>  
</tr>
</table>
</div>
<div id="addwarehouselocations_actions">
<table>
<tr>
<td><a href="javascript: submitform()"><mmr:message messageId="label.btn.save"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<s:if test="#session.edit == 'true'">
<td>
	<a href="goToManageLocations.action"><mmr:message messageId="label.btn.cancel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
</td>
</s:if>
<s:if test="#session.edit != 'true'">
<td><a href="javascript: resetform()"><mmr:message messageId="label.btn.reset"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if>
</tr>
</table>
</div>

<div id="addwarehouselocation_table">
<table width="960px" cellspacing="1" cellpadding="1">
<tr>
<td class="hdr"><mmr:message messageId="label.warehouse.name"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="warehouseId" 
					listValue="warehouseName" name="warehouseLocation.warehouseId" list="#session.WHLIST" headerValue="--Select--"
						headerKey="-1" id="warehouses" theme="simple"/>
</td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.aisle"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield id="aisleId" size="24" key="warehouseLocation.aisle" name="warehouseLocation.aisle" cssClass="text_02_tf"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.row"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield id="rowId" size="24" key="warehouseLocation.row" name="warehouseLocation.row" cssClass="text_02_tf"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.level"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield id="levelId" size="24" key="warehouseLocation.level" name="warehouseLocation.level" cssClass="text_02_tf"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
<td class="hdr"><mmr:message messageId="label.section"/> </td>
<td><s:textfield id="sectionId" size="24" key="warehouseLocation.section" name="warehouseLocation.section" cssClass="text_02_tf"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.location.name"/></td>
<td><s:textfield id="locnameId" size="24" key="warehouseLocation.locationName" name="warehouseLocation.locationName" cssClass="text_02_tf" disabled="true"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.locationtype.name"/></td>
<td>
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="warehouseLocationTypeId" 
					listValue="warehouseLocationTypeName" name="warehouseLocation.locationTypeId" list="#session.WHLTList" headerValue="--Select--"
						headerKey="-1" id="warehouselocationtypes" theme="simple"/>
</td>
<td>&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>

  
</s:form>
</div>
</body>
</html>	


