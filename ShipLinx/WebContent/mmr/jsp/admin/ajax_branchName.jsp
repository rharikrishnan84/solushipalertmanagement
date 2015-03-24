<%@ taglib prefix="s" uri="/struts-tags"%>
 	<s:select    listKey="branchId" listValue="branchName" 
												name="filter.branchId" headerKey="-1"  headerValue="All" list="branchList" 
												onchange="" name="user.userFilter.branchId" id="bid" theme="simple"/>
