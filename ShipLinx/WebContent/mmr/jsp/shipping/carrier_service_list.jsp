<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<span>:</span>
<s:select cssClass="text_01" listKey="id" listValue="name" name="shippingOrder.serviceId" headerKey="1" list="#session.services" id="secondBox"/>
</body>
</html>