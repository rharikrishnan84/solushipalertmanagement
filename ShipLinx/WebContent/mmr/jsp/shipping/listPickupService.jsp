<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<div class="controls"><span>:</span>
	<s:select cssClass="text_01_combo_big" listKey="id"
		listValue="name" name="pickup.serviceId" list="#session.SERVICES" 
		 headerKey="-1" id="service" theme="simple"/>	
</body>
</html>