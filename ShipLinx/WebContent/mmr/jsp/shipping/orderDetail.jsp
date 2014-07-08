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
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="new.shipment" name="packageform" theme="simple" >
<div class="form-container" valign="top" >

	<div id="right_left_new" valign="top"  >
		<div id="bottom_tabs_shippment">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			 <td width="5%" align="right" valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif"/></td>
			  <td width="15%"   valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
				<a href="stageOne.action"><mmr:message messageId="label.shippingOrderTab.newShipment"/></a>
			  </td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			 <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_blue_left.gif" /></td>
			  <td width="15%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_blue_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.orderDetail"/>
                  </a></td>
			  <td width="60%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_blue_right.gif" /></td>
			</tr>
		  </table>
		</div>
	</div>
	
	<s:include value="/mmr/jsp/shipping/shipmentDetail.jsp"></s:include>

</div>
</s:form> 
</body>
</html>
    
    
 