<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>


<script type="text/javascript">


function showOrHideException()
{
	var anchor = getElementsByClassName("show_summ1");
	var inner = anchor[0].innerHTML;
	
	if(inner != '<img src="<%=request.getContextPath()%>/mmr/images/show.png">')
	{		
	
		 document.getElementById("errorMsgContainer").style.display = 'none'; 
		
		anchor[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/show.png">';
	}
	else
	{
		
		 document.getElementById("errorMsgContainer").style.display = 'block'; 
		anchor[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">';
	}
}

</script>

<style>
		.show_summ1 img{ width:25px; height:25px;}
		#loader{display:none;}
	</style>
	
	
	 <s:if test="hasErrors()">
	 <s:if test="actionErrors.size>2||fieldErrors.size>2" >
	 <div class="content">	
<div class="content_body" >	

	<div class="content_table" id="showbutton" style="background-color:#FFF;width:960px !important;">
<div class="content_header" style="margin-bottom:1px;" >
									<div class="cont_hdr_title"><mmr:message messageId="add.label.error.exception"/></div>
									<div class="form_buttons">	
					<a href="#" class="show_summ1" onclick="showOrHideException()"  style="background-color:transparent; padding:0px;"><img src="<%=request.getContextPath()%>/mmr/images/show.png"></a>
				</div></div>
				</div>
				</div>
				</div>
<div class="content" >	
<div class="content_body" >	
	<div class="content_table" id="contenttbl" style="background-color:#FFF;width:960px !important;">
		<div id="errorMsgContainer"  style="display:none;">
			<div Style="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;">
				<s:actionerror cssClass="actionErrorStyle" cssStyle="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;"/>
				<s:fielderror cssClass="fieldErrorStyle" />
			</div>
		</div>
	</div>
</div>
</div>
</s:if>
<s:else>
<div class="content">	
<div class="content_body" >	
	<div class="content_table" id="contenttbl" style="background-color:#FFF;width:960px !important;">
		<div id="errorMsgContainer">
			<div Style="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;">
				<s:actionerror cssClass="actionErrorStyle" cssStyle="color:#FF0000;padding:3px 20px; font-size:14px;  background-color: #F8ECE0;"/>
				<s:fielderror cssClass="fieldErrorStyle" />
			</div>
		</div>
	</div>
</div>
</div>
</s:else>
</s:if>
<s:else>
<div class="content" >	
<div class="content_body" >	
	<div class="content_table" id="contenttbl"  style="background-color:#FFF;">
		<div id="errorMsgContainer">
				<strong><s:actionmessage cssClass="actionErrorStyle" cssStyle="color:#009900; font-size:14px; padding:3px 20px; background-color: #F8ECE0;"/></strong>
		</div>
	</div>
</div>
</div>	
</s:else>
