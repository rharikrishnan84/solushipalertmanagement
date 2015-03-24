<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select cssClass="text_01_combo_big"   cssStyle="width:120px;"    headerKey="-1" headerValue="All" name="user.userFilter.countryPartnerId" listKey="countryPartnerId" listValue="countryName" list="ajaxCountryPartnerList" 
										 theme="simple" onchange="setBranch(this.value)"  id="cid"/>
