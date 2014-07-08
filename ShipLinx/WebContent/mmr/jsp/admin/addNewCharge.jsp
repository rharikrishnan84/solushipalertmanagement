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

	
	function submitform()
	{
		if(document.chargeform.method.value != 'add')
		{	
			var chid=document.getElementById("chargeid").value;
			 document.chargeform.action = "createOrEditCharge.action?chid="+chid;
		}
		else
		{
			 document.chargeform.action = "createOrEditCharge.action";
		}

	 document.chargeform.submit();
	}
</SCRIPT>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container" >

<s:form action="createWarehouse" name="chargeform">

<div id="add_warehouse_hdr">
<table cellpadding="4" cellspacing="0" width="500px">
<tr>
<td>
<s:if test="#session.edit2 == true">
		<mmr:message messageId="label.edit.charge"/>
    	<s:hidden name="method" value="edit"/>
    	<s:hidden name="carrierChargeCode.id" id="chargeid"/>
    </s:if> 
    <s:else>
 		<mmr:message messageId="label.add.charge"/>
    	<s:hidden name="method" value="add"/>
    </s:else>

</td>
</tr>
</table>
</div>
 
<div id="add_warehouse_hdr_imgs">
<table width="130px">
<tr>
<s:if test="#session.edit2 != true">
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img border="0" src="<%=request.getContextPath()%>/mmr/images/reset_icon.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:if> 
<s:else>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img border="0" src="<%=request.getContextPath()%>/mmr/images/cancel.png"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:else>
<td><img src="<s:url value="/mmr/images/DataGrid_headerSeparatorSkin.png" includeContext="true" />" border="0">&nbsp;<img src="<s:url value="/mmr/images/save_icon.png" includeContext="true" />" border="0">&nbsp;</td>
</tr>
</table>
</div>
<div id="add_warehouse_hdr_actions">
<table width="150px">
<tr>
<s:if test="#session.edit2 != true">
<td><a href="goToAddNewCharge.action">&nbsp;<mmr:message messageId="label.btn.reset"/> </a>&nbsp;&nbsp;</td>
</s:if> 
<s:else>
<td><a href="searchCharges.action"><mmr:message messageId="label.btn.cancel"/></a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</s:else>
<td><a href="javascript: submitform()"> <mmr:message messageId="label.btn.save"/></a> &nbsp;</td>
</tr>
</table>
</div>

<div id="add_warehouse_bttm_tbl">
<table cellpadding="2" cellspacing="3" width="950px">
<tr>
<td class="warehouse_tbl_font"><mmr:message messageId="label.charge.code"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.chargeCode" cssClass="text_02_tf_big"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.charge.code.2"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.chargeCodeLevel2" cssClass="text_02_tf_big"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.charge.name"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.chargeName" cssClass="text_02_tf_big"/></td>

</tr>
<tr>

<td class="warehouse_tbl_font"><mmr:message messageId="label.charge.desc"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.chargeDesc" cssClass="text_02_tf_big"/></td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.charge.group"/>:</td>
<td>
<s:select id="chargegroup" name="carrierChargeCode.groupId" list="#session.listChargeGroups" cssClass="text_01_combo_bigger" theme="simple"/>
</td>

<td class="warehouse_tbl_font"><mmr:message messageId="label.charge"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.carrierCharge" cssClass="text_02_tf"/></td>

</tr>
<tr>
<td class="warehouse_tbl_font"><mmr:message messageId="label.cost"/>:</td>
<td><s:textfield size="24" name="carrierChargeCode.carrierCost" cssClass="text_02_tf"/></td>

<td colspan="4">&nbsp;</td>
</tr>

</table>
</div>
</s:form>
</div>
</body>
</html>