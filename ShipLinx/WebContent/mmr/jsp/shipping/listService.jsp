<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
 
 
   <div class="controls"><span>:</span>
    <s:select  listKey="id"
    listValue="name" name="markup.serviceId" list="#session.SERVICES" 
     headerKey="-1" id="service" theme="simple"/> 
    </div>
 </div>
</body>
</html>