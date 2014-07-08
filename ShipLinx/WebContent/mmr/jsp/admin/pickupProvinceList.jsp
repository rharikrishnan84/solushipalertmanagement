<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<div class="controls"><span>:</span>
<s:select key="pickup.address.provinceCode" name="pickup.address.provinceCode"  
		 value="%{pickup.address.provinceCode}" 
		listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
		</div>
</body>
</html>