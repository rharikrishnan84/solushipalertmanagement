 <%@ taglib prefix="s" uri="/struts-tags"%>
 	<s:select    listKey="id" listValue="name" 
												name="nationBusId" headerKey="-1"  headerValue="ANY" list="businessList" 
												onchange="setBranchBus(this.value)"  id="cid" theme="simple"/>
 