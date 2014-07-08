<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head>
    <title><s:text name="user.form.title"/></title> 
</head> 
<body> 
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form>

<div id="srchwarehouse_srchactions_imgs">
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/addNew_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>
<div id="srchwarehouse_srch_actions" >
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><a href="addWarehouse.action"><mmr:message messageId="label.add.warehouse"/></a>&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>


	<div id="srchwarehouse_res"><mmr:message messageId="label.search.results"/></div>
	<div id="srchwarehouse_results">	
	<s:if test="%{warehouseList.size()>1}">
	<div id="srchwarehouse_rslt_stmnt"><br/><s:property value="warehouseList.size()" /><mmr:message messageId="label.search.results.items"/></div>
	</s:if>
	<s:elseif test="%{warehouseList.size()==1}">
	<div id="srchwarehouse_rslt_stmnt"><br/><s:property value="warehouseList.size()" /><mmr:message messageId="label.search.results.item"/></div>
	</s:elseif>
	<s:else>
	<div id="srchwarehouse_rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/></div>
	</s:else>
	</div>


<div id="srchwarehouse_result_tbl">
<table width="100%" border="0" cellpadding="5" cellspacing="0" style="margin-top:7px;">
	<tr>
	<td class="tableTitle2"></td>
	<td class="tableTitle2"></td>
	<td class="tableTitle2"><strong><mmr:message messageId="label.warehouse.name"/></strong></td>
	<td class="tableTitle2"><strong><mmr:message messageId="label.warehouse.code"/></strong></td>
	<td class="tableTitle2"><strong><mmr:message messageId="label.address.line"/></strong></td>
	</tr>
	<tr>		
            <s:iterator id="warehousetable" value="warehouseList" status="rowstatus">
            <tr>
            <s:if test="#rowstatus.even == true">
	            <td class="even" width="3%">
		            <s:a href="editWarehouse.action?id=%{warehouseId}"> <img src="<s:url value="/mmr/images/edit_pencil.png" includeContext="true" />" alt="Edit User" border="0"> </s:a>
		        </td>
		        <td class="even" width="5%">
		        	<s:a onclick="return confirm('Do you really want to delete the selected Warehouse?')" href="deleteWarehouse.action?id=%{warehouseId}"> <img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Delete User" border="0"> </s:a>
		        </td>
	            <td class="even"><s:property value="warehouseName"/></td>
	            <td class="even"><s:property value="warehouseCode"/></td>
	            <td class="even"><s:property value="address.longAddress2"/></td>
			</s:if>
			<s:else>
				<td class="odd" width="3%">
		            <s:a href="editWarehouse.action?id=%{warehouseId}"> <img src="<s:url value="/mmr/images/edit_pencil.png" includeContext="true" />" alt="Edit User" border="0"> </s:a>
		        </td>
		        <td class="odd" width="5%">
		            <s:a onclick="return confirm('Do you really want to delete the selected Warehouse?')" href="deleteWarehouse.action?id=%{warehouseId}"> <img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Delete User" border="0"> </s:a>
		        </td>
	            <td class="odd"><s:property value="warehouseName"/></td>
	            <td class="odd"><s:property value="warehouseCode"/></td>
	            <td class="odd"><s:property value="address.longAddress2"/></td>
			</s:else>
            </tr>
            </s:iterator>
</tr>
</table>

</div>
  <div id="srchwarehouse_res_tbl_end"></div>
</s:form>
</div>
</body>
</html>