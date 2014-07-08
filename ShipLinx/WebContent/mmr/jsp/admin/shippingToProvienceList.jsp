<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<s:hidden name="ssss" id="aaa" value="%{#session.shippingOrder.toAddress.countryCode}"/>
	<s:set name="cName" value="%{#session.shippingOrder.toAddress.countryCode}"/>
	<s:if test ='%{#cName ==  "CA"}'>
	<span>:</span>
	    <s:select key="shippingOrder.state" name="shippingOrder.toAddress.provinceCode"  cssClass="text_02" 
	     listKey="provinceCode" listValue="provinceName" list="#session.Toprovinces"/>
   </s:if>
   	<s:elseif test ='%{#cName ==  "US"}'>
	<span>:</span>
	     <s:select key="shippingOrder.state" name="shippingOrder.toAddress.provinceCode"  cssClass="text_02" 
	   	  listKey="provinceCode" listValue="provinceName" list="#session.Toprovinces"/>
   </s:elseif>
   <s:else>
   <span>:</span>
   		<s:textfield size="20" key="shippingOrder.state" name="shippingOrder.toAddress.provinceCode"  cssClass="text_02_tf" value="%{shippingOrder.toAddress.provinceCode}"/>
   </s:else>
   
</body>
</html>