<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><s:property value="%{#session.business.systemName}"/></title>
<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
function highLightMenu(url,menu) {
		var form = document.forms[0];


		form.action = "highLightMenu.action?value="+url+"&menu="+menu;
			form.submit();
		
	} 
</script>
</head>
<body onload="MM_preloadImages('<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/icon_search.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/icon_truck.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/btn_track.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/btn_find_locations.gif')" topmargin="0" leftmargin="0">
<div id="wrapper">
	<div id="top">
		<div id="top_left">
			<div id="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo_shiplinx.jpg" /></div>
			<div id="top_buttons">
				<div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/icon_home.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/icon_support.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/icon_help.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/icon_logout.gif" /></div>
				
				<div id="top_icons_text">&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/viewLogon.action">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Support</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Help</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/logout.action">Logout</a></div>
			</div>
		</div>
		
		<div id="top_right">	
			<div id="main_btns" cellspacing="0" cellpadding="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
   
    <td cellspacing="0" cellpadding="0">
	<table border="0" cellspacing="0" cellpadding="0">
	<tr>
	<s:iterator value="#session.MENU" id="menu">
<!--		<s:if test="subMenuItems">-->
<!--			<img src="<%=request.getContextPath()%>/mmr/images/red_triangle.gif" />-->
<!--				<a href="<s:url value="%{url}" includeContext="true" includeParams="none" />"  onmouseover="cssdropdown.dropit(this,event,'dropmenu<s:property value="#menu.id"/>')"><s:property value="name" /></a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--		</s:if>-->
<!--		<s:else>-->
		<td class="text_01" align="left" cellspacing="0" cellpadding="0">
		
<s:if test="%{#session.HighLightMenu == name}" >
	
		<a href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />" >
		<img src="<%=request.getContextPath()%><s:property value='imageOver' />"  
		name="Image14" border="0" align="center" onClick="javascript:highLightMenu('<s:property value='url' />',&menu=<s:property value='name' />');"/>
	 </a>
		</s:if>
		<s:else>
	
	<a href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />" >
		<img src="<%=request.getContextPath()%><s:property value='image' />"  
		name="Image14" border="0" align="center" onClick="javascript:highLightMenu('<s:property value='url' />',&menu=<s:property value='name' />');"/>
	 </a>
		</s:else>
	
		</td>

		</td>

	

<!--		</s:else>-->
	</s:iterator>

	
	<td>
	<div style="width:100px;height:5px;float:left;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;font-weight:bold;color:#9e2725;text-align:center;padding-bottom:2px;padding-left:5px;padding-top:2px;">Welcome </div><br/>
	<div style="width:100px;height:5px;float:left;font-family:Verdana, Arial, Helvetica, sans-serif;font-size:10px;font-weight:bold;color:#9e2725;text-align:center;padding-bottom:2px;padding-left:5px;padding-top:3px;"><s:property value="%{session.ACEGI_SECURITY_LAST_USERNAME}" /></div>
	</td>
	</tr>
	</table>
	<div id="sub_btns" cellspacing="0" cellpadding="0">
	<table style="margin-top:-3px;margin-left:-3px;">
	<tr colspan="1">
		<td class="text_01" align="left"  style="margin-top:0px;" cellspacing="0" cellpadding="0" >
			<img src="<%=request.getContextPath()%>/mmr/images/sub_btns_left.gif" />
		</td>
	<s:iterator value="#session.CHILD_MENU" id="menu">
		<td colspan="1" class="text_01" align="left"  style="margin-left:2px;" cellspacing="0" cellpadding="0">
			&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/red_triangle.gif" />
			<a href="<s:url value="%{url}" includeContext="true" includeParams="none" />">
			<s:property value="name" />
			</a>
		</td>
	</s:iterator>

	</tr>
	</table>
	</td>
  </tr>
 
</table>
</div>
 </div>
		
		</div>
		</div>
	</div>
	
	<div id="breadcrumbs">
	</div>
</div>
</body>
</html>