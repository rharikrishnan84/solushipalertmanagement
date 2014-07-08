<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{#request.row == null}">
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="aisle" 
					listValue="aisle" name="warehouseLocation.aisle" list="#session.AISLELIST" headerValue="--ALL--"
						headerKey="-1" id="aisle" theme="simple" onchange="populateAisle('row')"/>
</s:if>
<s:else>
<s:select cssClass="text_01_combo_big" cssStyle="width:140px;" listKey="row" 
					listValue="row" name="warehouseLocation.row" list="#session.AISLELIST" headerValue="--ALL--" 
						headerKey="-1" id="row" theme="simple"/>
</s:else>