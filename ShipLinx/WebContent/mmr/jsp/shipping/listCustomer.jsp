<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html>
<body>
    <div class="fields">
    	<label><mmr:message messageId="label.heading.customer"/> </label>
        <div class="controls"><span>:</span>
        	<s:select   listKey="id" listValue="name" 
				name="businessMarkup.customerId" headerValue="ANY" headerKey="-1"  list="#session.CUSTOMERS" 
				onchange="javascript:showCarriers();"  id="secondBox" theme="simple" size="1" />
        </div>
   	</div>
    <div class="fields">
    	<label><mmr:message messageId="label.track.carrier"/> </label>
        <div id="carrierid">
        	<div class="controls"><span>:</span>
            	<s:select   listKey="id" listValue="name" 
					name="businessMarkup.carrierId" headerValue="ANY" headerKey="-1"  list="#session.CARRIERS" 
					onchange="javascript:showServices();"  id="thirdBox" theme="simple" size="1"  />
           	</div>
        </div>
    </div>
</body>
</html>