<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<s:select key="billingcustomer.address.provinceCode" name="billingcustomer.address.provinceCode"  
		cssClass="controls" value="%{customer.address.provinceCode}" 
		listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
</body>
</html>