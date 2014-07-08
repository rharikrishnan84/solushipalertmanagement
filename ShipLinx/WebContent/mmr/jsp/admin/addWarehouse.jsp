<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<SCRIPT language="JavaScript">
 function showState() {
        
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
		  url="warehouse.listProvience.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()
	
	function submitform()
	{
		if(document.warehouseform.wid!=null)
		{
			var wid=document.getElementById("wid").value;
		
			 document.warehouseform.action = "createWarehouse.action?id="+wid;
		}else{

			 document.warehouseform.action = "createWarehouse.action";
		}

	 document.warehouseform.submit();
	}
</SCRIPT>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container" >

<s:form action="createWarehouse" name="warehouseform">

<div id="add_warehouse_hdr">
<table cellpadding="4" cellspacing="0" width="500px">
<tr>
<td>
<s:if test="#session.edit == 'true'">
		Edit Warehouse 
    	<s:hidden name="method" value="edit"/>
    	<s:hidden name="warehouse.businessId" />
    	<s:hidden name="warehouse.warehouseId" id="wid"/>
    </s:if> 
    <s:else>
 		Add Warehouse
    	<s:hidden name="method" value="add"/>
    </s:else>

</td>
<td align="left">
<s:if test="#session.edit == 'true'">
<div id="add_warehouse_name"><s:property value="warehouse.warehouseName"/></div>
</s:if>
</td>
</tr>
</table>
</div>
 
<div id="add_warehouse_hdr_imgs">
<table width="130px">
<tr>
<s:if test="#session.edit != 'true'">
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img border="0" src="<%=request.getContextPath()%>/mmr/images/reset_icon.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if> 
<s:else>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img border="0" src="<%=request.getContextPath()%>/mmr/images/cancel.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:else>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />" border="0">&nbsp;</td>
</tr>
</table>
</div>
<div id="add_warehouse_hdr_actions">
<table width="150px">
<tr>
<s:if test="#session.edit != 'true'">
<td><a href="addWarehouse.action">&nbsp;&nbsp;Reset</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if> 
<s:else>
<td><a href="listWarehouses.action"><mmr:message messageId="label.btn.cancel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:else>
<td><a href="javascript: submitform()"> <mmr:message messageId="label.btn.save"/></a> &nbsp;</td>
</tr>
</table>
</div>

<div id="add_warehouse_bttm_tbl">
<table cellpadding="2" cellspacing="3" width="950px">
<tr>
<td class="warehouse_tbl_font"><mmr:message messageId="label.warehouse.code"/>:</td>
<td><s:textfield size="24" name="warehouse.warehouseCode" cssClass="text_02_tf"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.warehouse.name"/>:</td>
<td><s:textfield size="24" name="warehouse.warehouseName" cssClass="text_02_tf"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.address1"/>:</td>
<td><s:textfield size="24" name="warehouse.address.address1" cssClass="text_02_tf"/></td>

</tr>
<tr>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.address2"/>:</td>
<td><s:textfield size="24" name="warehouse.address.address2" cssClass="text_02_tf"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.city"/>:</td>
<td><s:textfield size="24" name="warehouse.address.city" cssClass="text_02_tf"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.postalCode"/>:</td>
<td><s:textfield size="24" name="warehouse.address.postalCode" cssClass="text_02_tf"/></td>

</tr>
<tr>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.country"/>:</td>
<td>
<s:select cssClass="text_01_combo_big" cssStyle="width:158px;" listKey="countryCode" listValue="countryName" name="warehouse.address.countryCode" headerKey="-1" list="#session.CountryList" onchange="javascript:showState();"  id="firstBox" theme="simple"/>
</td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.province"/>:</td>
<td id="stateid">
<s:select cssClass="text_01" cssStyle="width:158px;" listKey="provinceCode" listValue="provinceName" key="warehouse.address.provinceCode" name="warehouse.address.provinceCode" headerKey="1" list="#session.provinces" id="secondBox"  theme="simple"  cssClass="text_01_combo_big"/>
</td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.phone"/>:</td>
<td><s:textfield size="24" name="warehouse.address.phoneNo" cssClass="text_02_tf"/></td>

</tr>
<tr>
<td class="warehouse_tbl_font"><mmr:message messageId="label.customer.email"/>:</td>
<td><s:textfield size="24" name="warehouse.address.emailAddress" cssClass="text_02_tf"/></td>

<td>&nbsp;</td>
<td></td>

<td>&nbsp;</td>
<td></td>

</tr>
</table>

</div>







</s:form>
</div>
</body>
</html>