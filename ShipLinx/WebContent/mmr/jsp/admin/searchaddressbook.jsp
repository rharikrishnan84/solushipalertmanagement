<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<html> 
<head> 
    <sj:head jqueryui="true" />
    <title><s:text name="edi.title"/></title> 
</head> 
<body> 
<script type="text/javascript">

function submitForm()
{
	document.addressform.action="search.address.action";
	document.addressform.submit();
}
</script>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<style>

</style>

<div class="content">
<div class="content_body"  >

<div id="messages" style="overflow:hidden !important; background-color: #F8ECE0;float:left;">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

</div>

<div class="content_body" >
<div class="content_table" style="background-color:#fff">
<div id="formResult" style="float:left;">
   <s:include value="addressList.jsp"></s:include>
</div>

</div>
</div>
</div>
