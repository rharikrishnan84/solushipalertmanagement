<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<s:select name="warehouse.address.provinceCode"  
		cssClass="text_01_combo_big" cssStyle="width:135px;" 
		listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
</body>
</html>