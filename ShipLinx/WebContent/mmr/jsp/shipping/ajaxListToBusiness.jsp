<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="controls">
		<span>:</span>
		<s:select   listKey="id" listValue="name" 
			name="businessMarkup.businessToId" headerValue="ANY" headerKey="-1"  list="#session.TO_BUSINESS" 
			onchange="javascript:showCustomer();"  id="firstBox" theme="simple" size="1"  />
	</div>
</body>
</html>