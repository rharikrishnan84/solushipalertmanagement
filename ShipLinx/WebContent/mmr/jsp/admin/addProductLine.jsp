<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!-- Start: Code to handle Export Data -->
<%@ page buffer = "16kb" %>
<!-- End: Code to handle Export Data -->

<html> 
<head> 
 	<sx:head/>
    <title><s:text name="customer.search.title"/></title>     
</head> 
<body> 
<SCRIPT language="JavaScript">
	function submitform()
	{
		var busadminrole ="";
		if(document.searchform.busadmin!=null)
			busadminrole = document.searchform.busadmin.value;
				
	 if(document.searchform.hiddenEdit!=null && document.searchform.hiddenEdit.value=='true')
	 {
	 	var edit=true;
	 	var pid= document.searchform.pid.value;
	 	if(busadminrole=='true')
	 	{
	 		var cid= document.searchform.cid.value;
	 		document.searchform.action = "saveEditedProductLine.action?productLineId="+pid+"&cid="+cid+"&edit="+edit;
	 	}
	 	else
	 	{
	 		document.searchform.action = "saveEditedProductLine.action?productLineId="+pid+"&edit="+edit;
	 	}
	 }
	 else
	 {
	 	var edit= false;
	 	if(busadminrole=='true')
	 	{
	 		var cid= document.searchform.cid.value;
	 		document.searchform.action = "saveEditedProductLine.action?edit="+edit+"&cid="+cid;
	 	}
	 	else
	 	{
	 		document.searchform.action = "saveEditedProductLine.action?edit="+edit;
	 	}
	 }
	 	document.searchform.submit();
	}
	
	
	
</SCRIPT>

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
<mmr:message messageId="menu.add.productline"/>
</s:if>
<s:else>
<mmr:message messageId="label.edit.product"/>
</s:else>
</div></td>
<td>&nbsp;</td>
</tr>
</table>
</div>

<div id="addproductactions_imgs">
<table>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;</td>
<td><img src="<s:url value="/mmr/images/cancel.png" includeContext="true" />" border="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
</div>
<div id="addproduct_actions">
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;</td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td><a href="javascript: submitform()"><mmr:message messageId="label.btn.save"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
 <s:if test="%{#session.role=='busadmin'}">
	 <a href="goToManageProducts.action"><mmr:message messageId="label.btn.cancel"/></a>&nbsp;&nbsp;&nbsp;
 </s:if>
 <s:else>
	 <a href="search.products.action"><mmr:message messageId="label.btn.cancel"/></a>&nbsp;&nbsp;&nbsp;	   
 </s:else>
</td>
</tr>
</table>
</div>

<div id="addproduct_table">
<table width="940px" cellspacing="2" cellpadding="2">
<tr>
<td class="hdr"><mmr:message messageId="label.product.linename"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield size="24" key="searchproductline.name" name="productLine.lineName" cssClass="text_02_tf"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.product.linecode"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield size="24" key="searchproductline.code" name="productLine.lineCode" cssClass="text_02_tf" maxlength="10"/></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td class="hdr"><mmr:message messageId="label.product.linedesc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><s:textfield size="24" key="searchproductline.desc" name="productLine.lineDescription" cssClass="text_02_tf"/></td>
</tr>
</table>
</div>
<s:if test="%{#session.ROLE.contains('busadmin') || #session.ROLE.contains('sysadmin')}">
<s:hidden name="busadmin" value="true"/>
</s:if>
<s:if test="#session.edit != 'true'">
<s:hidden name="cid" value="%{productLine.customerId}"/>

<div id="tab">&nbsp;</div>
	<div id="res"><mmr:message messageId="label.search.results"/></div>
	<div id="results">	
	<s:if test="%{productLineList.size()>1}">
	<div id="rslt_stmnt"><br/><s:property value="productLineList.size()" /><mmr:message messageId="label.search.results.items"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:if test="%{#session.role=='busadmin'}">
	<font style="color: #000000; font-family: Arial; font-size: 11px; font-weight: bold;"><mmr:message messageId="label.customer.selected"/>:</font>&nbsp;&nbsp;<s:property value="%{#session.customerName}"/>
	</s:if>
	</div>
	</s:if>
	<s:elseif test="%{productLineList.size()==1}">
	<div id="rslt_stmnt"><br/><s:property value="productLineList.size()" /><mmr:message messageId="label.search.results.item"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:if test="%{#session.role=='busadmin'}">
	<font style="color: #000000; font-family: Arial; font-size: 11px; font-weight: bold;"><mmr:message messageId="label.customer.selected"/>:</font>&nbsp;&nbsp;<s:property value="%{#session.customerName}"/>
	</s:if>
	</div>
	</s:elseif>
	<s:else>
	<div id="rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<s:if test="%{#session.role=='busadmin'}">
	<font style="color: #000000; font-family: Arial; font-size: 11px; font-weight: bold;"><mmr:message messageId="label.customer.selected"/>:</font>&nbsp;&nbsp;<s:property value="%{#session.customerName}"/>
	</s:if>
	</div>
	</s:else>
	</div>
	
<div id="result_tbl">		
		<display:table id="product" uid="row" name="productLineList" pagesize="100" export="true" requestURI="listCustomerProducts.action" cellspacing="0" cellpadding="4" class="srch_tbl">
		<s:hidden name="productLine.productLineId" value="%{#attr.row.productLineId}"/>		
		     <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
		       	<s:a href="editproductLine.action?productLineId=%{#attr.row.productLineId}"> 
				<img src="<s:url value="/mmr/images/edit_pencil.png" includeContext="true" />" alt="Edit Customer" border="0"> </s:a>
	   		</display:column>
             <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
             	<s:a onclick="return confirm('Do you really want to delete the selected ProductLine?')" href="deleteProductLine.action?productLineId=%{#attr.row.productLineId}">
				<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Customer Account Info" border="0"> </s:a>
            </display:column>
			<display:column headerClass="tableTitle2_product" property="lineName" title="Line Name" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="lineCode" title="Line Code" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="lineDescription" title="Line Description" style="text-align:center;"/>
		</display:table>
	
	
	
</div>
<div id="res_tbl_end"></div>
</s:if>
<s:else>
<s:hidden name="hiddenEdit" value="true"/>
<s:hidden name="pid" value="%{productLine.productLineId}"/>
<s:hidden name="cid" value="%{productLine.customerId}"/>
</s:else>
   
</s:form>
</div>
		


