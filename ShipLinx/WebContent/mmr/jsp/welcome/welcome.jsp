<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head> 
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 

<div class="form-container"> 
<s:form>
<div id="right_left">
<div id="bottom_tabs">
		 <table width="50%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td width="8"><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif" width="8" height="30" /></td>
			  <td width="130" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="addcustomer.action">Sign Up Customer</a></td>
			  <td width="20"><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			  
			  <td width="8"><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif" width="8" height="30" /></td>
			  <td width="130" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="searchcustomer.action">Search Customer</a></td>
			  <td width="20"><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			</tr>
		  </table>
	</div>
<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="5" cellspacing="0">
             
                <tr>
                  <td width="160">&nbsp;</td>
                  <td width="530">&nbsp;</td>
                </tr>
                <tr>
                  <td class="text_01"><mmr:message messageId="label.customer.search.businessName"/>:</td>
                  <td><s:textfield size="20" key="searchcustomer.name" name="customer.name" cssClass="text_02"/></td>
                </tr>
                <tr>
                  <td class="text_01"><mmr:message messageId="label.customer.search.accountNumber"/>:</td>
                  <td><s:textfield size="20" key="searchcustomer.accountNumber" name="customer.accountNumber" cssClass="text_02"/></td>
                </tr>
			<tr>
			<td>&nbsp;</td>
			<td class="icon_btns"><a href="javascript: submitform()"><img src="<%=request.getContextPath()%>/mmr/images/icon_save.gif"  border="0"/>&nbsp;&nbsp;Search</a>
			</td>
			</tr>
</table>
</div>
 <s:if test="#session.CUSTOMERLISTSIZE > 0 ">
<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="5" cellspacing="0">
	<tr>
		<td></td>
		<td class="text_01"><strong><s:label key="customer.name" /></strong></td>
		<td class="text_01"><strong><s:label key="customer.dateCreated"/></strong></td>
		<td class="text_01"><strong><s:label key="customer.contact"/></strong></td>
		<td class="text_01"><strong><s:label key="customer.phone"/></strong></td>
		<td class="text_01"><strong><s:label key="customer.email"/></strong></td>
		</tr>
		
	<tr>
		<td valign="top">
           <s:iterator id="customertable" value="customerList" status="rowstatus">
            <tr>
            <td>
            <s:a href="#"> <img src="<s:url value="/mmr/images/edit.png" includeContext="true" />" alt="Edit User" border="0"> </s:a>
            <s:a onclick="return confirm('Do you really want to delete the selected user?')" href="#"> <img src="<s:url value="/mmr/images/delete.png" includeContext="true" />" alt="Delete User" border="0"> </s:a></td>
                     <td class="text_01"><s:property value="businessName"/></td>
                    <td class="text_01"><s:property value="dateCreated"/></td>
                    <td class="text_01"><s:property value="contact"/></td>
                    <td class="text_01"><s:property value="phone"/></td>
                    <td class="text_01"><s:property value="email"/></td>
                	</tr>
            </s:iterator>

</tr>
</table>
</div>
 </s:if>
  </div>
   
</s:form>
</div>
</div>








