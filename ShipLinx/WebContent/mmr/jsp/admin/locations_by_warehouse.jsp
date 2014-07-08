<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<s:property value="%{#request.WHID}"/>
<s:url id="locationsListWH" action="listLocationsByWarehouse">
<s:param name="wid"><s:property value="%{#request.WHID}"/> </s:param>
</s:url>
<sx:autocompleter id="locationNames" name="searchString" href="%{#locationsListWH}" dataFieldName="locationWHSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  cssStyle="width:150px;" preload="true"/>
							