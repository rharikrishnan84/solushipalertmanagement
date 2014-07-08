<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html> 
<head>
    <title><s:text name="user.form.title"/></title> 
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<SCRIPT language="JavaScript">
 
		function update_packagetype(caller){
	
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("additionalServices");
				js_stateid.innerHTML= reponse;
				}
		  }
		 
			url="shipment.additionalservices.action?value="+caller.id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
		
	}
	    
	function submitform(method)
	{
	 document.packageform.action = "stageTwo.action?method="+method;
	 document.packageform.submit();
	} 
	 
</SCRIPT> 
<div>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form action="stageTwo" name="packageform">


	<div id="right_left_new" valign="top" >
		<div id="bottom_tabs_shippment">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td width="5%" align="right" valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif"/></td>
			  <td width="5%"   valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
				<a href="javascript: submitform('stageOne')"><mmr:message messageId="label.shippingOrderTab.order"/></a>
			  </td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 

			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_blue_left.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_blue_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.package"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_blue_right.gif" /></td>
			 
			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
			  <a href="javascript: submitform('backToDimension')">
                  	<mmr:message messageId="label.shippingOrderTab.dimension"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="shipment.stageThree.action">
                  	<mmr:message messageId="label.shippingOrderTab.rateList"/>
                  </a></td>
			  <td width="45%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			 
			</tr>
		  </table>
	</div>
	<div style="height:425px; width:100%;">
	<table align="left" border="0"  width="100%">
	<tr align="left">
	<td align="left">
			<div id="bottom_table2_new" class="text_01">
			  <table  border="0" cellspacing="0"  width="100%"  cellpadding="2">
			  
			   <tr>
                  <td width="385"   colspan="4" class="shipto" style="padding-top:3px; padding-left:15px;" class="text_01"><mmr:message messageId="label.shippingOrder.packages"/></td>
                 
                  <td width="385"  colspan="4" align="right" class="icon_btns" style="padding-right:15px; padding-top:10px;">
                  <s:submit type="image" src="%{#session.ContextPath}/mmr/images/truck.gif"/> 
                  &nbsp;<mmr:message messageId="label.navigation.next"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:a href="stageOne.action"><img src="<%=request.getContextPath()%>/mmr/images/hand.gif" border="0"/>&nbsp;<mmr:message messageId="label.navigation.cancel"/></s:a></td>
                </tr>
				 
				 <tr>
                  <td  colspan="8">&nbsp;</td>
                </tr>
                
				<tr>
                 
				  <td valign="top"  colspan="4" style="padding-right:15px;padding-left:15px;"  class="text_03">
				  <fieldset>
					 <legend class="contentTitle"><strong><mmr:message messageId="label.shippingOrder.choosePackage"/>:</strong></legend>
					  <table border="0" cellspacing="0" cellpadding="3"  class="text_03">
						 <tr>
							<td valign="middle" nowrap="nowrap"  class="text_01" >
									 <s:iterator value="%{#session.packageOptions}" status="packStatus">
										<s:radio name="shippingOrder.packageTypeId.type"   list="%{#session.packageOptions.get(#packStatus.index)}"  value="%{shippingOrder.packageTypeId.type}"  onclick="javascript:update_packagetype(this);" id="%{value}"></s:radio><br/>
									  </s:iterator>
								</td>
						</tr>
						</table>
					</fieldset>
 					</td>
				
					<td colspan="3"   colspan="4" style="padding-right:15px;padding-left:15px;"  valign="top" nowrap="nowrap"  class="text_03">
						<fieldset>
							<legend class="contentTitle"><strong><s:label key="shippingOrder.refCode"/>:</strong></legend>
							<table>
								<tr>
									<td class="text_01"  colspan="2"><strong><s:label key="shippingOrder.refCode"/>:</strong></td>
									 <td  colspan="2"><s:textfield size="20" key="shippingOrder.refCode" name="shippingOrder.referenceCode" cssClass="text_02" value="%{shippingOrder.referenceCode}"/></td>
								</tr>
							</table>
						</fieldset>
                   	</td>
                
				</tr>
				</table>
				
				</div>
				</td>
				</tr>
				
				<tr align="left">
				<td align="left">
				<div id="additionalServices">
			
			<div id="bottom_table4" class="text_01" >
 <table border="0" cellspacing="0" width="100%" cellpadding="2" style="padding-right:15px;padding-left:15px;" >
                 <tr>
                  <td width="100" style="padding-top:7px;" class="shipto"><strong><mmr:message messageId="label.shippingOrder.additionalServices"/>
</strong></td>
                  <td width="120">&nbsp;</td>
                  <td width="10">&nbsp;</td>
                  <td width="120"/> &nbsp;</td>
				  <td width="120"/> &nbsp;</td>
                </tr>
				 <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
				  <td>&nbsp;</td>
                </tr>
                <tbody><tr>
                        <td valign="middle" nowrap="nowrap" class="text_03"><mmr:message messageId="label.shippingOrder.satDelivery"/></td>
                        <td valign="middle" align="left" class="text_01">
                        <s:checkbox name="shippingOrder.satDelivery"  value="%{#session.shippingOrder.satDelivery}"/>
                        <td valign="middle" class="text_01">&nbsp;</td>
                        <td valign="middle" nowrap="nowrap" class="text_03"><mmr:message messageId="label.shippingOrder.dangerousGoods"/></td>
                        <td valign="middle" class="text_01">

                       <s:select name="shippingOrder.dangerousGoods"
										style="width: 120px;"  cssClass="text_01" list="{'None','Limited Quantity','500 Kg Exemption','Fully Regulated'}" value="%{#session.shippingOrder.dangerousGoods}"/>           		    
                        </td>

                       
                      </tr>
					  <tr>
					  	<td valign="middle" nowrap="nowrap" class="text_03"><mmr:message messageId="label.shippingOrder.additionalServices.scheduledShipDate"/></td>
							<td valign="middle" nowrap="nowrap">
							<table border="0" cellpadding="0" cellspacing="0" class="text_02">
								<tbody>
									<tr>
										<td valign="middle"><s:textfield name="shippingOrder.scheduledShipDate" id="f_date_c" size="10" cssStyle="width: 120px;"
											cssClass="text_02" readonly="readonly" value="%{#session.shippingOrder.scheduledShipDate}"/>
										<td valign="middle"><img src="<%=request.getContextPath()%>/mmr/images/icon_Appt.gif"
											id="f_trigger_c" style="cursor: pointer;"
											title="Date selector" border="0" onClick="selectDate('f_date_c','f_trigger_c');"> 
				  						</td>
									</tr>
								</tbody>
							</table>
							</td>
							<td valign="middle">&nbsp;</td>
						
						<s:set name="cName" value="%{#session.shippingOrder.fromAddress.countryName}"/>
						<s:if test ='%{#cName ==  "CA"}'>
									<td colspan="1" valign="middle" nowrap="nowrap"  class="text_03" ><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequiredCanada"/></td>
									<td valign="middle">
									<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired" list="#{'1':'No','2':'Yes'}" cssStyle="width: 120px;" cssClass="text_01" ></s:select>
								</td>
								</s:if>
								<s:else>
									<td colspan="1" valign="middle" class="text_03"><mmr:message messageId="label.shippingOrder.additionalServices.signatureRequired"/></td>
									<td valign="middle">
									<s:select value="%{#session.shippingOrder.signatureRequired}" name="shippingOrder.signatureRequired" cssStyle="width: 120px;" list="#{'1':'No','2':'Indirect','3':'Direct','4':'Adult'}" cssStyle="width: 120px;" cssClass="text_01" ></s:select>
									</td>   
						</s:else>	
					  </tr>
					  <tr>
					   
                        <td valign="middle" class="text_03"><mmr:message messageId="label.shippingOrder.additionalServices.holdForPickupRequired"/></td>
                        <td valign="middle" align="left" class="text_01">
                        <s:checkbox name="shippingOrder.holdForPickupRequired" value="%{#session.shippingOrder.holdForPickupRequired}" />
					  </tr>
                      </tbody>
				</table>
</div>
</div>
</div>
		</td></tr></table>
	</div>
	
</s:form> 
</div>
</div>
</body>
</html>
    
    
 