<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<s:set name="cName" value="%{#session.shippingOrder.fromAddress.countryCode}"/>
	<s:if test ='%{#cName ==  "CA"}'>
	<span>:</span>
 		<s:select key="shippingOrder.state" name="shippingOrder.fromAddress.provinceCode"  cssClass="text_02" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.Fromprovinces"/>
   </s:if>
   	<s:elseif test ='%{#cName ==  "US"}'>
	<span>:</span>
 		<s:select key="shippingOrder.state" name="shippingOrder.fromAddress.provinceCode"  cssClass="text_02" 
  		  listKey="provinceCode" listValue="provinceName" list="#session.Fromprovinces"/>
   </s:elseif>
   <s:else>
   <span>:</span>
   		<s:textfield size="20" key="shippingOrder.state" name="shippingOrder.fromAddress.provinceCode"  cssClass="text_02_tf" value="%{shippingOrder.fromAddress.provinceCode}"/>
   </s:else>
   
</body>
</html>