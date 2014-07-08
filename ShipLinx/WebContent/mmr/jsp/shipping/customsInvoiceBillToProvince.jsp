<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<s:set name="cName" value="%{#session.shippingOrder.customsInvoice.billToAddress.countryCode}"/>
	<s:if test ='%{#cName ==  "CA"}'>
 		<s:select key="shippingOrder.state" name="shippingOrder.customsInvoice.billToAddress.provinceCode"  cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.billToProvinces"/>
   </s:if>
   	<s:elseif test ='%{#cName ==  "US"}'>
 		<s:select key="shippingOrder.state" name="shippingOrder.customsInvoice.billToAddress.provinceCode"  cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.billToProvinces"/>
   </s:elseif>
   <s:else>
   		<s:textfield  key="shippingOrder.state" name="shippingOrder.customsInvoice.billToAddress.provinceCode"  cssClass="controls" value="%{shippingOrder.customsInvoice.billToAddress.provinceCode}"/>
   </s:else>
   
</body>
</html>