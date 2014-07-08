<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<span>:</span>
<s:select key="customer.address.provinceCode" name="customer.address.provinceCode"  
		cssClass="controls" value="%{customer.address.provinceCode}" 
		listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
</body>
</html>