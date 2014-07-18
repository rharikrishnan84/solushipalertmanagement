<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<s:set name="cName" value="%{#session.shippingOrder.customsInvoice.brokerAddress.countryCode}"/>
	<s:if test ='%{#cName ==  "CA"}'>
 		<s:select key="shippingOrder.state" name="shippingOrder.customsInvoice.brokerAddress.provinceCode"
 		  cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" id="stateidb" list="#session.brokerProvinces"/>
   </s:if>
   	<s:elseif test ='%{#cName ==  "US"}'>
 		<s:select key="shippingOrder.state" name="shippingOrder.customsInvoice.brokerAddress.provinceCode"  
 		 cssClass="controls" 
  		  listKey="provinceCode" listValue="provinceName" id="stateidb" list="#session.brokerProvinces"/>
   </s:elseif>
   <s:else>
   		<s:textfield  key="shippingOrder.state" id="stateidb" name="shippingOrder.customsInvoice.brokerAddress.provinceCode"
   		  cssClass="controls" value="%{shippingOrder.customsInvoice.brokerAddress.provinceCode}"/>
   </s:else>
   
</body>
</html>
