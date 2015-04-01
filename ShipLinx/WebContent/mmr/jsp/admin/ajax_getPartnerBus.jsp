<%@ taglib prefix="s" uri="/struts-tags"%>
 	<s:select    listKey="id" listValue="name" 
												name="partnerBusId" headerKey="-1"  headerValue="ANY" list="businessList" 
												onchange="setNationBus(this.value)"  id="pid" theme="simple"/>
