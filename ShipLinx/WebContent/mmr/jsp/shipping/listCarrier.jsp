<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
	<div class="controls">
		<span>:</span>
		<s:select listKey="id" listValue="name" name="businessMarkup.carrierId"
			headerValue="ANY" headerKey="-1" list="#session.CARRIERS"
			onchange="javascript:showServices();" id="thirdBox" theme="simple"
			size="1" />
	</div>
</body>
</html>