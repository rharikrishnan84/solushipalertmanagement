<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select    listKey="partnerId" listValue="partnerName" 
												name="filter.partnerId" headerKey="-1"  headerValue="Select" list="ajaxPartnerList" 
												onchange="setcountry(this.value);"  id="firstBox" theme="simple"/>