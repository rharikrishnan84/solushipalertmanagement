<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" listKey="chargeCode" listValue="chargeCode" headerKey="" headerValue="Select" name="%{newChargeType}.chargeCode" list="ajaxCarreierChargeList" 
										 theme="simple" onchange="%{newChargeType}ListChargeName(this.value)"/>