<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div id="messages" style="background-color:#FFF;">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form>


<div class="content_body" >	
	<div class="content_table" id="right_left_new" > 
		<div id="bottom_table" class="text_01" style="padding:5px;">
			<s:label key="distribution.list.name"/>
		</div>

		<s:iterator id="addresstable" value="distributionList" status="rowstatus">
			<div>
				<div>
				<s:set name="distributionListName" value="%{distributionList.get(#rowstatus.index).getDistributionId()}" />
				<s:a onclick="return confirm('Do you really want to delete the selected address?')" href="delete.distributionList.action?distributionListId=%{distributionListName}"> <img src="<s:url value="/mmr/images/delete.png" includeContext="true" />" alt="Delete Distribution List" border="0"> </s:a>
				</div>
				<div class="text_01"><s:property value="distributionId"/></div>
					
			</div>
		</s:iterator>		
				

</div>
</div>
<div class="content_body">
		<div class="content_table" >
		&nbsp;
		</div>
</div>
</s:form>
</div>
