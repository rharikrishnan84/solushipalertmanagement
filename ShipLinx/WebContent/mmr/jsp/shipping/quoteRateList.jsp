<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
</script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/style.css">

<head>
<sx:head />
</head>

<SCRIPT language="JavaScript">
	function setRateIndex(val) {	
		document.userform.elements['shippingOrder.rateIndex'].value = val;
	
	}
	
	function test(val){
		alert(val);
		var value= val;
	}
	
</script>
<html>
	<head>
	
		<title></title>

	</head>
	
	<body>
	<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form action="shipment.save" name="userform">
	<div id="right_left_new" valign="top"  >
		<div id="bottom_tabs_shippment">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td width="5%" align="right" valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif"/></td>
			  <td width="5%"   valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
				<a href="new.quote.action">Quote</a>
			  </td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.dimension"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			
			 <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_blue_left.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_blue_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.rateList"/>
                  </a></td>
			  <td width="60%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_blue_right.gif" /></td>
			 
			 
			</tr>
		  </table>
	</div>
	<div style="height:425px; width:100%;">
		<div id="bottom_table_New" class="text_01">
			  <table border="0" cellspacing="0" width="100%" cellpadding="2" style="margin-left:-10px;">
			  <tr>
                  <td width="20" class="text_01">&nbsp;</td>
                 
					
					 <td colspan="10" align="right" class="icon_btns" style="padding-right:15px; padding-top:10px;">
				
                  <s:submit type="image" src="%{#session.ContextPath}/mmr/images/icon_save.gif"/> 
                  &nbsp;Submit&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <s:if test='%{shippingOrder.packageTypeId.type == "type_env" || shippingOrder.packageTypeId.type == "type_pak"}' >
	                  <s:a href="new.quote.action"> 
	                  <img src="<%=request.getContextPath()%>/mmr/images/hand.gif" border="0"/>
	                  &nbsp;<mmr:message messageId="label.navigation.cancel"/> </s:a>
                  </s:if>
                  <s:else>
	                  <s:a href="new.quote.action"> 
	                  <img src="<%=request.getContextPath()%>/mmr/images/hand.gif" border="0"/>
	                  &nbsp;<mmr:message messageId="label.navigation.cancel"/> </s:a>
                  </s:else>
                  
                </tr>
				 <tr>
				
                 
				  <td width="10">&nbsp;</td>
                  <td width="100">&nbsp;</td>
                  <td width="100">&nbsp;</td>
                  <td width="100">&nbsp;</td>
				  <td width="100">&nbsp;</td>
				  <td width="100">&nbsp;</td>
				  <td width="100">&nbsp;</td>
				  <td width="80">&nbsp;</td>
				  <td width="10">&nbsp;</td>
				
				</tr>
               </table>
			
			</div>
				<div id="bottom_table_New_rate" class="text_03">
				<div>
	<display:table width="100%" id="order_table"  name="shippingOrder.rateList" export="false" uid="row">
		<display:column class="text_01" property="id" sortable="true" title="#"/>
		<display:column class="text_01" property="carrierName"  sortable="true" title="Carrier" />
		<display:column class="text_01" property="serviceName"  sortable="true" title="Service" />
		<display:column class="text_01"  sortable="true" title="Estimated Transit days">1</display:column>

	<display:column class="text_01"  sortable="true" title="Total">
		  <sx:tree  cssClass="text_01" label="<b>Total : %{shippingOrder.rateList[#attr.row_rowNum-1].total}</b>" >
			<s:iterator  value="%{shippingOrder.rateList[#attr.row_rowNum-1].quotedCharges}">
			 <sx:treenode cssClass="text_01" label="%{name} : %{charge}" />
			</s:iterator>
		 </sx:tree>	 
		  
	</display:column>

		<display:column title="">
			<s:radio name="rateIndexOption" list="%{#session.RateOption[#attr.row_rowNum-1]}" 
				 value="%{#session.RateOption[0]}" onclick="javascript:setRateIndex(%{#attr.row_rowNum-1});" />
				 <s:property  value="%{getText('format.number',{orderItem.price})}" />
		</display:column>
	</display:table>
	<s:hidden name="shippingOrder.rateIndex" />
  	</div>
              
	</div>
  		</div>

		</s:form> 
		</div>

	</body>
</html>