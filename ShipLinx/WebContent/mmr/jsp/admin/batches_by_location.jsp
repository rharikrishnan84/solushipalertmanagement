<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="%{#request.prodid!=null && #request.index!=null}">
	<s:select id="batches_%{#request.prodid}_%{#request.index}" name="batches" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches" headerKey="-1" headerValue="--Select--"/>
</s:if>
<s:else>
<s:select id="batches" name="batches" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches" headerKey="-1" headerValue="--Select--"/>
</s:else>					