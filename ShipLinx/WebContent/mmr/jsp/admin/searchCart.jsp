<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html> 
<head> 
	<sx:head/>
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 
<SCRIPT language="JavaScript">

	function checkOrder(index)
	{
		var textid = "cartOrderId_";
		textid = textid+index;
		var orderId = document.getElementById(textid).value;
		var check = true;
		if(orderId=='')
		{
			alert("Please Enter Order Id");	
			check = false;	
		}
		
		if(check)//if everything is fine, then submit the form.{
		{
			document.searchCartForm.action = "getAllUnshippedOrder.action?orderId="+orderId+"&index="+index;
			document.searchCartForm.submit();
			//alert(document.searchCartForm.action);
		}
	} 

	function typenumbers(e,filterString)
	{
		var key, keychar;
		key = getkey(e);
		if (key == null) 
		return true;
		
		// get character
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();
		// control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==27) )
		return true;
		// alphas and numbers
		else if ((filterString.indexOf(keychar) > -1))	
		return true;
		else
		return false;
	}
	
	function getkey(e){
		if (window.event)
		  return window.event.keyCode;
		else if (e)
		  return e.which;
		else
		  return null;
	}
</SCRIPT>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container"> 
<s:form action="searchCart" name="searchCartForm">
<div id="srchcart_res"><mmr:message messageId="label.search.results"/></div>
	<div id="srchcart_results">	
	<s:if test="%{customerCartList.size()>1}">
	<div id="srchcart_rslt_stmnt"><br/><s:property value="customerCartList.size()" /><mmr:message messageId="label.search.results.items"/></div>
	</s:if>
	<s:elseif test="%{customerCartList.size()==1}">
	<div id="srchcart_rslt_stmnt"><br/><s:property value="customerCartList.size()" /><mmr:message messageId="label.search.results.item"/></div>
	</s:elseif>
	<s:else>
	<div id="srchcart_rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/></div>
	</s:else>
	</div>
<div id="result_tbl">
<s:set var="srNo">
 	<mmr:message messageId="label.cart.srNo" />
</s:set>
<s:set var="cName">
 	<mmr:message messageId="label.cart.cartName" />
</s:set>
<s:set var="shopURL">
 	<mmr:message messageId="label.store.url" />
</s:set>
		<display:table id="cart" uid="row" name="customerCartList" pagesize="100" export="false" requestURI="getCartList.action" cellspacing="0" cellpadding="4" class="srch_tbl">
		<s:hidden name="cart.id" value="%{#attr.row.cartId}"/>
            <display:column headerClass="tableTitle_cart_Sr" title="${srNo}" style="text-align:center;">
            	<s:property value="%{#attr.row_rowNum}"/>
            </display:column>
			<display:column headerClass="tableTitle_cart" property="cartName" title="${cName}" style="text-align:center;" sortable="true"/>
			<display:column headerClass="tableTitle_cart_shopURL" property="urlName" maxLength="60" title="${shopURL}" style="text-align:center;"/>
			<display:column headerClass="tableTitle_cart_getAllUnshipOrder" title="" style="text-align:center;" > 
			  	<s:a href="getAllUnshippedOrder.action?index=%{#attr.row_rowNum-1}">
				<img src="<s:url value="/mmr/images/getUnshippedOrders.png" includeContext="true" />" alt="Get All Unshipped Order" border="0"> </s:a>
            </display:column>
            <display:column  headerClass="tableTitle_cart_Sr" style="text-align:center; width: 50px;">
            <b><mmr:message messageId="label.or" /></b>
            </display:column>
            <display:column headerClass="tableTitle_cart_getSpecificOrder" title="" style="text-align:center;" > 
				<img src="<s:url value="/mmr/images/Order_No_btn.png" includeContext="true" />" alt="Get Specific Order" border="0" onclick="javascript: checkOrder('<s:property value="%{#attr.row_rowNum-1}"/>');" style="cursor: pointer;"><font id="colon"><b>:</b></font>
            </display:column>
            <display:column headerClass="tableTitle_cart_orderId" title="" style="text-align:center;" >
            	<s:textfield size="20" cssClass="text_02_tf_medium" id="cartOrderId_%{#attr.row_rowNum-1}" onkeypress="return typenumbers(event,\'0123456789.\')"/>
            </display:column>
		</display:table>
</div>
<div id="res_tbl_end"></div>
</s:form>
</div>
