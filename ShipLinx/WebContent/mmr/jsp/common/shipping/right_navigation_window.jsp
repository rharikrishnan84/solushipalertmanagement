<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>

<SCRIPT language="JavaScript">

	var shipHistoryFlag = true;

	function display(){
	if(shipHistoryFlag){
	document.getElementById('shipHistory').style.display = 'block';
	document.getElementById('quickLinks').style.display = 'none';
	}
	}
</SCRIPT> 

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
/* Start:Logic to hide Summary for other pages except for Get Rates Page*/
	var pathname = String(window.location.pathname);
	if(pathname.indexOf("/shiplinx/admin/shipment.stageThree.quote")== -1)
	{		
		$(".flipsupport img").hide();
		$(".s_div").hide();		
	}
/* End:Logic to hide Summary for other pages except for Get Rates Page*/
$(".fliphelp img").click(function(){
	$(".h_div").slideToggle("slow");   	
    $(".s_div").slideUp("slow");    
  });
$(".flipsupport img").click(function(){
    $(".s_div").slideToggle("slow");
    $(".h_div").slideUp("slow");     
  });

});

</script>


	<div id="shppng_help_btn" class="fliphelp">
		<img src="<%=request.getContextPath()%>/mmr/images/help_btn_rnd.png"/>
	</div>	
	<div id="h_div" class="h_div">
		<div id="ship_help_div_start">&nbsp;</div>	
		<div id="ship_help_txt" class="hlp_sprt"></div>
		<div id="ship_help_div_end">&nbsp;</div>
	</div>
	
	<div id="summary_btn" class="flipsupport">
		<img src="<%=request.getContextPath()%>/mmr/images/summary_btn_rnd.png"/>
	</div>
	
	<div id="ship_div" class="s_div">
	
	
	<div id="summary_div_start">&nbsp;</div>
	<div id="summary_txt">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td class="hlp_sprt_hdng">SHIP TO:</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt_hdng">SHIP FROM:</td>
			</tr>
			<tr>
				<td class="hlp_sprt">
					<s:if test='%{shippingOrder.toAddress.abbreviationName != ""}'>
						<s:property value="%{shippingOrder.toAddress.abbreviationName}"/>
					</s:if>
					<s:else>
						---
					</s:else>
				</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt">
					<s:if test='%{shippingOrder.fromAddress.abbreviationName != ""}'>	
						<s:property value="%{shippingOrder.fromAddress.abbreviationName}"/>
					</s:if>
					<s:else>
						---
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="hlp_sprt">
					<s:if test='%{shippingOrder.toAddress.city != "" && shippingOrder.toAddress.postalCode == ""}'>
						<s:property value="%{shippingOrder.toAddress.city}"/>&nbsp;,&nbsp;---
					</s:if>
					<s:elseif test='%{shippingOrder.toAddress.city == "" && shippingOrder.toAddress.postalCode != ""}'>
						---&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.postalCode}"/>
					</s:elseif>
					<s:elseif test='%{shippingOrder.toAddress.city != "" && shippingOrder.toAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.toAddress.city}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.postalCode}"/>
					</s:elseif>  
					<s:else>
						---,---
					</s:else>
				</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt">				
					<s:if test='%{shippingOrder.fromAddress.city != "" && shippingOrder.fromAddress.postalCode == ""}'>
						<s:property value="%{shippingOrder.fromAddress.city}"/>&nbsp;,&nbsp;---  
					</s:if>
					<s:elseif test='%{shippingOrder.fromAddress.city == "" && shippingOrder.fromAddress.postalCode != ""}'>
						---&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.postalCode}"/>
					</s:elseif>
					<s:elseif test='%{shippingOrder.fromAddress.city != "" && shippingOrder.fromAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.fromAddress.city}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.postalCode}"/>
					</s:elseif>
					<s:else>
						---&nbsp;,&nbsp;---
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="hlp_sprt">
					<s:if test='%{shippingOrder.toAddress.province !="" && shippingOrder.toAddress.countryName == ""}'>
						<s:property value="%{shippingOrder.toAddress.province}"/>&nbsp;,&nbsp;---
					</s:if>
					<s:elseif test='%{shippingOrder.toAddress.province == "" && shippingOrder.toAddress.countryName != ""}'>
						---&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.countryName}"/>
					</s:elseif>
					<s:elseif test='%{shippingOrder.toAddress.province != "" && shippingOrder.toAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.toAddress.province}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.countryName}"/>
					</s:elseif>
					<s:else>
						---&nbsp;,&nbsp;---
					</s:else>					
				</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt">
					<s:if test='%{shippingOrder.fromAddress.province != "" && shippingOrder.fromAddress.countryName == ""}'>
						<s:property value="%{shippingOrder.fromAddress.province}"/>&nbsp;,&nbsp;---
					</s:if>	
					<s:elseif test='%{shippingOrder.fromAddress.province == "" && shippingOrder.fromAddress.countryName != ""}'>
						---&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.countryName}"/>
					</s:elseif>
					<s:elseif test='%{shippingOrder.fromAddress.province != "" && shippingOrder.fromAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.fromAddress.province}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.countryName}"/>
					</s:elseif>
					<s:else>
						---&nbsp;,&nbsp;---
					</s:else>					
				</td>
			</tr>
			<tr>
				<td class="hlp_sprt">
				<s:if test='%{shippingOrder.toAddress.contactEmail != ""}'>
					<s:property value="%{shippingOrder.toAddress.contactEmail}"/>
				</s:if>
				<s:else>
					---
				</s:else>
				</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt">
				<s:if test='%{shippingOrder.fromAddress.contactEmail != ""}'>
					<s:property value="%{shippingOrder.fromAddress.contactEmail}"/>
				</s:if>
				<s:else>
					---
				</s:else>	
				</td>
			</tr>
			<tr>
				<td class="hlp_sprt_hdng">PACKAGE DETAIL:</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="hlp_sprt">
				<s:if test='%{shippingOrder.packageTypeId.name != ""}'>	
					<s:property value="%{shippingOrder.packageTypeId.name}"/>
				</s:if>				
				<s:else>
					---
				</s:else>
				</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt">
				<s:if test='%{shippingOrder.quantity != ""}'>
					<s:property value="%{shippingOrder.quantity}"/>
				</s:if>
				<s:else>
					---				
				</s:else>
				</td>
			</tr>
			<s:iterator value="shippingOrder.packages" status="counterIndex">
			<tr>
				<td class="hlp_sprt"><s:label id="shippingOrder.packageArray[%{#counterIndex.index}].length" name="shippingOrder.packages[%{#counterIndex.index}].length" value="%{#session.shippingOrder.packages[#counterIndex.index].length}"/>&nbsp;x&nbsp;<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].width" name="shippingOrder.packages[%{#counterIndex.index}].width" value="%{#session.shippingOrder.packages[#counterIndex.index].width}"/>&nbsp;x&nbsp;<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].height" name="shippingOrder.packages[%{#counterIndex.index}].height" value="%{#session.shippingOrder.packages[#counterIndex.index].height}"/>&nbsp;&nbsp;(L x W x H)</td>
				<td>&nbsp;</td>
				<td class="hlp_sprt"><s:label id="shippingOrder.packageArray[%{#counterIndex.index}].weight" name="shippingOrder.packages[%{#counterIndex.index}].weight" value="%{#session.shippingOrder.packages[#counterIndex.index].weight}"/>&nbsp;lbs</td>
			</tr>			
			</s:iterator>
			
			
		</table>
	</div>
	<div id="summary_div_end">&nbsp;</div>
</div>





<div id="addressInfo" valign="center" align="center" height="161px" width="100%" style="display:none; " cellspacing="0" cellpadding="0" border="0">
	
<table valign="center" align="center" height="100%" width="100%" >
<tr valign="center" align="center">
<td height="100%" width="100%" valign="center" align="center">
<img src="<%=request.getContextPath()%>/mmr/images/ajax-loader.gif" border="0"  />
</td>
</tr>
</table>


</div>

<div id="testAjax" style="width:0; height:0;">
</div>
</html>