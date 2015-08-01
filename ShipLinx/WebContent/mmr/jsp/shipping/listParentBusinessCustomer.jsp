<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
           <div class="controls"><span>:</span>
            <s:select   listKey="id" listValue="name" 
							name="business.parentCustomerId" headerValue="" headerKey="-1"  list="#session.CUSTOMERS" 
								 id="secondBox" theme="simple" size="1"  />
           </div>
</body>
</html>