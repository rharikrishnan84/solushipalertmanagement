<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<s:set name="cName" value="%{#session.shippingOrder.customsInvoice.buyerAddress.countryCode}"/>
	<s:if test ='%{#cName ==  "CA"}'>
		<span>:</span>
 		<s:select key="shippingOrder.customsInvoice.buyerAddress.state" name="shippingOrder.customsInvoice.buyerAddress.provinceCode"  cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.buyerProvinces"/>
   </s:if>
   	<s:elseif test ='%{#cName ==  "US"}'>
		<span>:</span>
 		<s:select key="shippingOrder.customsInvoice.buyerAddress.state" name="shippingOrder.customsInvoice.buyerAddress.provinceCode"  cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.buyerProvinces"/>
   </s:elseif>
   <s:else>
		<span>:</span>
   		<s:textfield  key="shippingOrder.customsInvoice.buyerAddress.state" name="shippingOrder.customsInvoice.buyerAddress.provinceCode"  cssClass="controls" value="%{shippingOrder.customsInvoice.buyerAddress.provinceCode}"/>
   </s:else>
   
</body>
</html>