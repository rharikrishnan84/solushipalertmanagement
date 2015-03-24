<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head> 
    <title><s:text name="customer.form.title"/></title> 
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>

<SCRIPT language="JavaScript">
	function submitform()
	{
	 document.customerform.action = "customer.info.edit.action";
	 document.customerform.submit();
	}

	function getAccountInformation(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
	}
</SCRIPT> 

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container"> 

<s:form action="customer.info.edit" name="customerform">
<div id="right_left_new" align="left">

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
				<a href="getCustomerAccountInfo.action">Carrier Account</a>
			  </td>
			  <td width="45%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			</tr>
		  </table>
	</div>
	<div id="bottom_table" class="text_01">
       
    <table width="100%" border="0" cellspacing="0" cellpadding="2">
			  
                <tr>
                  <td width="140">&nbsp;</td>
                  <td width="180">&nbsp;</td>
				  <td width="50">&nbsp;</td>
				  <td width="140">&nbsp;</td>
				  <td width="180">&nbsp;</td>
				   <td width="10">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="6">&nbsp;</td>
            	</tr>
  <%--               <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.username"/>:</td>
                  <td><s:textfield size="24" key="customer.username" readonly="true" name="customer.username" cssClass="text_02" /></td>
				  <td>&nbsp;</td>
				   <td class="text_03"><mmr:message messageId="label.customer.name"/>:</td>
                  <td><s:textfield size="24" key="customer.name" name="customer.name" cssClass="text_02"/></td>
                </tr>
                <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.userpassword"/>:</td>
                  <td><s:password size="24" key="customer.userpassword" name="customer.password" cssClass="text_02"/></td>
				  <td >&nbsp;</td>
                  <td class="text_03"><mmr:message messageId="label.customer.contact"/>:</td>
                  <td><s:textfield size="24" key="customer.contact" name="customer.contact" cssClass="text_02"/></td>
                </tr>
                <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.address1"/>:</td>
                  <td><s:textfield size="24" key="customer.address1" name="customer.address1" cssClass="text_02"/></td>
					<td>&nbsp;</td>
                  <td class="text_03"><mmr:message messageId="label.customer.address2"/>:</td>
                  <td><s:textfield size="24" key="customer.address2" name="customer.address2" cssClass="text_02"/></td>
                </tr> --%>
               
               <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.city"/>:</td>
                  <td><s:textfield size="24" key="customer.city" name="customer.city" cssClass="text_02"/></td>
				 <td>&nbsp;</td>
                  <td class="text_03"><mmr:message messageId="label.customer.postalCode"/>: </td>
                  <td><s:textfield size="24" key="customer.postalCode" name="customer.postalCode" cssClass="text_02"/></td>
                </tr>
                <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.countryName"/>:</td>
				  <td><s:select cssClass="text_01" cssStyle="width:160px;" listKey="countryCode" listValue="countryName" name="customer.country" list="#session.CountryList" onchange="javascript:showState();" headerKey="-1" id="firstBox" theme="simple"/></td>
					<td>&nbsp;</td>
                  <td class="text_03"><mmr:message messageId="label.customer.province"/>:</td>
				  <td id="stateid" class="text_01">
					<s:select cssClass="text_01" cssStyle="width:160px;" listKey="provinceCode" listValue="provinceName" name="addressbook.province" headerKey="1" list="#session.provinces" id="secondBox"  theme="simple"  cssClass="text_01"/>
				  </td>
                </tr>
               
                <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.phone"/>: </td>
                  <td> <s:textfield size="24" key="customer.phone" name="customer.phone" cssClass="text_02" /></td>
				  <td>&nbsp;</td>
                  <td class="text_03"><mmr:message messageId="label.customer.email"/>: </td>
                  <td><s:textfield size="24" key="customer.email" name="customer.email" cssClass="text_02" /></td>
                </tr>
                              
                <tr>
                  <td class="text_03"><mmr:message messageId="label.customer.apiusername"/>:</td>
                  <td><s:textfield size="24" key="customer.apiusername" name="customer.apiUsername" cssClass="text_02" /></td>
               	 <td>&nbsp;</td>
				 <td class="text_03"><mmr:message messageId="label.customer.apipassword"/>:</td>
                  <td><s:password size="24" key="customer.apipassword" name="customer.apiPassword" cssClass="text_02"/></td>
                </tr>
               
                <tr>
				
				<td class="text_03"><mmr:message messageId="label.customer.timezone"/>:</td>
				<td><s:select key="customer.timezone" cssStyle="width:160px;"  name="customer.timeZone" headerKey="1"  cssClass="text_02" list="#session.timeZones"/>
				</td>
				 <td class="text_01">&nbsp; </td>
				  <td>&nbsp;</td>
				</tr>
				
                <tr>
                  <td colspan="6">&nbsp;</td>
                
                </tr>
                <tr >
                  
                  <td colspan="6" align="center" class="icon_btns"><s:submit  type="image" src="%{#session.ContextPath}/mmr/images/icon_save.gif" />&nbsp;&nbsp;<a href="javascript: submitform()">Save</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
              </table>
	
	</div>
	</div>
	  	
	
    </s:form> 
    </div>
    </body>
    </html>
    
    
 