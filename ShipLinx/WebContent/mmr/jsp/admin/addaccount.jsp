<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html>
<head>
<title><s:text name="address.form.title" /></title>
</head>

<SCRIPT language="JavaScript">
	function submitform()
	{
	 document.carrierform.action = "saveCarrierAccount.action";
	 document.carrierform.submit();
	}
</SCRIPT>
<div class="form-container">
<div class="error"><s:fielderror /></div>
<s:form action="saveCarrierAccount" name="carrierform">
	<div id="right_left_new" align="left" >

<!--
<s:if test="%{#session.ROLE.contains('sysadmin')}">
-->
 <div id="bottom_tabs_new" valign="top" >
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td width="5%" align="right" ><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"/></td>
			  <td width="20%"  align="center" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text">

				<a href="addcustomer.action"><mmr:message messageId="label.shippingOrder.signUpCustomer"/></a>
			  </td>
			  <td width="3%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			  
			  <td  width="2%"  align="right"  >
				 <img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"  /></td>
			  <td width="20%"  align="center"  background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="searchcustomer.action">Search Customer</a></td>
			  <td width="3%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>

			  <td  width="2%"  align="right"  >
				 <img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"  /></td>
			  <td width="20%" align="center"   background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="#">Customer Account</a></td>
			  <td width="25%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>

			</tr>
		  </table>
	</div>
<!--
</s:if>
<s:if test="%{#session.ROLE.contains('admin')}">
-->
<div id="bottom_tabs_new">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td width="5%" align="right" ><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"/></td>
			  <td width="20%"  align="center" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text">
				<a href="customer.info.action"><mmr:message messageId="label.shippingOrder.editCustomerInfo"/></a>
			  </td>
			  <td width="3%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			  
			   <td width="3%" align="right" ><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"/></td>
			  <td width="20%"  align="center" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text">
				<a href="#">Customer Account</a>
			  </td>
			  <td width="45%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			</tr>
		  </table>
	</div>
<!--
</s:if>
-->

	<div id="bottom_table" class="text_01">
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
		<tr>
			<td width="5%">&nbsp;</td>
			<td width="5%">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>

		<!--
		<tr>
			<td class="text_03"><s:label key="customerCarrier.carrierName" />:</td>
			<td><s:select cssStyle="width:100px;"
				name="customer.customerCarrier.carrierName" headerKey="1"
				list="{'Fedex','UPS'}" /></td>
		</tr>
		-->

		<tr>
			<td class="text_03">
				<s:select cssClass="text_01" cssStyle="width:100px;" listKey="id" 
					listValue="name" name="customer.customerCarrier.carrierId" list="#session.CARRIERS" 
					 headerKey="-1" id="carrier" theme="simple"/>							
			</td>				
		</tr>		


		<tr>
			<td class="text_03"><s:label key="customerCarrier.property2" />:</td>
			<td><s:textfield size="24" key="customerCarrier.property2"
				name="customer.customerCarrier.property2" cssClass="text_02" /></td>
		</tr>


		<tr>
			<td class="text_03"><s:label
				key="customerCarrier.property3" />:</td>
			<td><s:password size="24" key="customerCarrier.property3"
				name="customer.customerCarrier.property3" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03"><s:label key="customerCarrier.property1" />:</td>
			<td><s:textfield size="24" key="customerCarrier.property1"
				name="customer.customerCarrier.property1" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03"><s:label key="customerCarrier.accountNumber1" />:</td>
			<td><s:textfield size="24" key="customerCarrier.accountNumber1"
				name="customer.customerCarrier.accountNumber1" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03">Property #1:</td>
			<td><s:textfield size="24" key="customerCarrier.property1"
				name="customer.customerCarrier.property1" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03">Property #2:</td>
			<td><s:textfield size="24" key="customerCarrier.property2"
				name="customer.customerCarrier.property2" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03">Property #3:</td>
			<td><s:textfield size="24" key="customerCarrier.property3"
				name="customer.customerCarrier.property3" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td class="text_03">Account #1:</td>
			<td><s:textfield size="24" key="customerCarrier.accountNumber1"
				name="customer.customerCarrier.accountNumber1" cssClass="text_02" /></td>
		</tr>

		<td class="text_03"><s:label key="customerCarrier.defaultaccount" />:
		</td>
		<td><s:checkbox key="customerCarrier.defaultaccount"
			name="customer.customerCarrier.defaultAccount" cssClass="text_02" /></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td class="icon_btns"><s:submit type="image"
				src="%{#session.ContextPath}/mmr/images/icon_save.gif" />&nbsp;&nbsp;<a
				href="javascript: submitform()"><mmr:message messageId="label.btn.save"/></a>
		</td>
			
		</tr>
	</table>
	</div>
	</div>
	
</s:form></div>

<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="5" cellspacing="0"  style="margin-top:2px;">
	<tr>
		<td class="text_03" width="130"><strong>Carrier</strong></td>
		<td class="text_03" width="130"><strong>Account #1</strong></td>
		<td class="text_03" width="130"><strong>Property #1</strong></td>
		<td class="text_03" width="110"><strong>Default</strong></td>
		</tr>
          <s:iterator value="customerCarrierAccountList" status="accountStatus">
			 <tr>
			   <s:set name="carrierAccount" value="%{customerCarrierAccountList.get(#accountStatus.index)}"/>
				<td class="text_01"><s:property value="%{#carrierAccount.carrierName}"/></td>
				<td class="text_01"><s:property value="%{#carrierAccount.accountNumber1}"/></td>
				<td class="text_01"><s:property value="%{#carrierAccount.property1}"/></td>
				<td class="text_01"><s:property value="%{#carrierAccount.defaultAccount}"/></td>
			</tr>
		</s:iterator>

</table>
</div>
</body>
</html>


