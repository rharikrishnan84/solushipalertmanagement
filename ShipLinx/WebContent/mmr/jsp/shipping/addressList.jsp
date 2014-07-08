<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>




<div class="form-container">
<s:form>
<div id="right_left_new" valign="top"  >
		<div id="bottom_tabs_shippment">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			   <td width="5%" align="right" valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif"/></td>
			  <td width="5%"   valign="top" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
				<a href="new.shipment.action"><mmr:message messageId="label.shippingOrderTab.order"/></a>
			  </td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 

			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="backToPackageInformation.action">
                  	<mmr:message messageId="label.shippingOrderTab.package"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="backToDimension.action">
                  	<mmr:message messageId="label.shippingOrderTab.dimension"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 

			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="20%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.repeatLastOrder"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			 <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.rateList"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_blue_left.gif" /></td>
			  <td width="14%"  valign="top" align="center" background="<%=request.getContextPath()%>/mmr/images/new_blue_middle.gif" class="tab_text"><a href="search.address.action"><mmr:message messageId="label.shippingOrderTab.addresList"/></a></td>
			  <td valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_blue_right.gif" /></td>
			</tr>
		  </table>
	</div>
<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="2" cellspacing="0">
	<tr>
	<td></td>
	<td class="text_01"><strong><mmr:message messageId="label.addressbook.consigneeName" /></strong></td>
	<td class="text_01"><strong><mmr:message messageId="label.addressbook.city"/></strong></td>
	<td class="text_01"><strong><mmr:message messageId="label.addressbook.province"/></strong></td>
	<td class="text_01"><strong><mmr:message messageId="label.addressbook.contactName"/></strong></td>
	<td class="text_01"><strong><mmr:message messageId="label.addressbook.contactEmail"/></strong></td>
	</tr>
	<tr>
		<td valign="top">
			<s:iterator id="addresstable" value="addressList" status="rowstatus">
            <tr >
            <td>
             <s:a href="set.shippingAddress.action?addressid=%{id}&type=%{#session.type}"> Select </s:a>
           	</td>
                    <td class="text_01"><s:property value="abbreviationName"/></td>
                    <td class="text_01"><s:property value="city"/></td>
                    <td class="text_01"><s:property value="province"/></td>
                    <td class="text_01"><s:property value="contactName"/></td>
                    <td class="text_01"><s:property value="contactEmail"/></td>
                	</tr>
            </s:iterator>
</tr>
</table>
</div>
</div>
</s:form>
</div>






