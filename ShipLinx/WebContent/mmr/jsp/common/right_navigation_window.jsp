<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
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


	<div id="help_btn" class="fliphelp">
		<img src="<%=request.getContextPath()%>/mmr/images/help_btn_rnd.png" style="width: 220px;"/>
	</div>
	<div id="h_div" class="h_div">
		<div id="help_div_start">&nbsp;</div>	
		<div id="help_txt" class="hlp_sprt"></div>
		<div id="help_div_end">&nbsp;</div>
	</div>
	
	<div id="support_btn" class="flipsupport">
		<img src="<%=request.getContextPath()%>/mmr/images/support_btn_rnd.png" style="width: 220px;"/>
	</div>
	<div id="s_div" class="s_div">
		<div id="support_div_start">&nbsp;</div>
		<div id="support_txt" class="hlp_sprt"></div>
		<div id="support_div_end">&nbsp;</div>
	</div>



				