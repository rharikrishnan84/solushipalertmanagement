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
</script>
</head>
<body onload="MM_preloadImages('<%=request.getContextPath()%>/mmr/images/icon_search.gif','<%=request.getContextPath()%>/mmr/images/icon_truck.gif','<%=request.getContextPath()%>/mmr/images/btn_track.gif','<%=request.getContextPath()%>/mmr/images/btn_find_locations.gif')" topmargin="0" leftmargin="0">
<div id="top">
	<div id="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo_login_shiplinx.jpg" /></div>
	  	<div id="top_right">
		<div id="top_links">&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/customerSupport.action">Customer Support</a>&nbsp;&nbsp;&nbsp;
 	  		|&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/shiplinxLocation.action">Shiplinx Locations</a></div>
	  	<div id="main_tabs" style="margin-bottom:5px;">
			<table width="612" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/grey_left_big.gif" width="13" height="33" /></td>
			    <td width="153" background="<%=request.getContextPath()%>/mmr/images/grey_middle_big.gif" class="tab_text"><a href="<%=request.getContextPath()%>/viewLogon.action">Package / Envelope </a></td>
			    <td width="12"><img src="<%=request.getContextPath()%>/mmr/images/grey_right_big.gif" width="12" height="33" /></td>


			    <td width="5"><img src="<%=request.getContextPath()%>/mmr/images/spacer_white.gif" width="5" height="5" /></td>


			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/grey_left_big.gif" width="13" height="33" /></td>
			    <td width="65" background="<%=request.getContextPath()%>/mmr/images/grey_middle_big.gif" class="tab_text"><a href="<%=request.getContextPath()%>/freight.action">Freight</a></td>
			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/grey_right_big.gif" width="13" height="33" /></td>


			    <td width="5"><img src="<%=request.getContextPath()%>/mmr/images/spacer_white.gif" width="5" height="5" /></td>


			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/grey_left_big.gif" width="13" height="33" /></td>
			    <td width="76" background="<%=request.getContextPath()%>/mmr/images/grey_middle_big.gif" class="tab_text"><a href="<%=request.getContextPath()%>/expedited.action">Expedited</a></td>
			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/grey_right_big.gif" width="13" height="33" /></td>


				<td width="5"><img src="<%=request.getContextPath()%>/mmr/images/spacer_white.gif" width="5" height="5" /></td>
				
				
			    <td width="13"><img src="<%=request.getContextPath()%>/mmr/images/red_left_big.gif" width="13" height="33" /></td>
			    <td width="159" background="<%=request.getContextPath()%>/mmr/images/red_middle_big.gif" class="tab_text"><a href="<%=request.getContextPath()%>/officeService.action">Office / Print Services</a></td>
			    <td width="54"><img src="<%=request.getContextPath()%>/mmr/images/red_right_big.gif" width="13" height="33" /></td>
			  </tr>
			</table>
	  	</div>
	</div>
</div>
</body>
</html>