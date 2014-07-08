<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"> -->
<head>
<meta charset="utf-8">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> -->
<!--[if lt IE 9]>    <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

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
			//Start : Sumanth Kulkarni 07 Oct 2011
			
			function changeImage(x)
			{
				x.className=(x.className=="first")?"second":(x.className=="second")?"first":"second";
			}			
			
			//End : Sumanth Kulkarni 07 Oct 2011
	
		</script>

</head>


<body onload="MM_preloadImages('<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/icon_search.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/icon_truck.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/btn_track.gif','<%=request.getContextPath()%>/mmr/<%=request.getContextPath()%>/mmr/images/btn_find_locations.gif')">
<form action="highLightMenu">
<div id="wrapper_header"><!-- Start  -->
<div id="top_bubble2" style="background-image: url(<%=request.getContextPath()%>/mmr/images/topBar.png); background-repeat:no-repeat;">
<div id="top_icons_text">
<table cellspacing="0" cellpadding="0">
	<tr>
		<td>
		<div id="imghome">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<s:url value="%{#session.business.address.url}"  />" target="_blank">Home</a>	
		</td>
	<!--  	<td>
		<div id="imgprint">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="<%=request.getContextPath()%>/viewLogon.action">Print</a></div>
		</td>-->
		<td>
		<div id="imgsupport">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<s:url value="%{#session.business.supportURL}"  />" target="_blank">Support</a>	</td>
		</td>
		<td>
		<div id="imghelp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="#">Help</a></div>
		</td>
	</tr>
</table>

</div>
<div id="business_logo_div" style="width: 180px; height : 45px;">
	<img src="<%=request.getContextPath()%>/mmr/images/<s:property value="%{session.business.logoHiResFileName}" />" style="height: 45px; width: 190px;"/>
</div>
<div id="top_bubble_tabs2">
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<s:iterator value="#session.TOP_LEVEL_MENUS" id="menu"
			status="top_lvl_mnu_itr">
			<td><s:if test="%{#top_lvl_mnu_itr.first == true}">
				<s:if
					test="%{#top_lvl_mnu_itr.count==1 && #session.HighLightMenu != name}">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td class="firstTab" align="left">
							<div id="firstTab">&nbsp;&nbsp;&nbsp;</div>
							</td>
							<td class="firstTabMid" align="left">
							<div id="ftabimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="firstTabMid" align="left">
							<div id="firstTabMid"><a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							&nbsp;&nbsp;<s:property value="name" /> </a></div>
							</td>
							<td class="firstTabEnd" align="left">
							<div id="firstTabEnd"></div>
							</td>
						</tr>
					</table>
				</s:if>
				<s:elseif
					test="%{#top_lvl_mnu_itr.count==1 && #session.HighLightMenu == name}">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<td class="firstTabLoad" align="left">
							<div id="firstTabLoad">&nbsp;&nbsp;</div>
							</td>
							<td class="firstTabLoadMid" align="left">
							<div id="ftabloadimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="firstTabLoadMid" align="left">
							<div id="firstTabLoadMid">&nbsp;&nbsp; <a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							<s:property value="name" /> </a></div>
							</td>
							<td class="firstTabLoadEnd" align="left">
							<div id="firstTabLoadEnd"></div>
							</td>
						</tr>
					</table>
				</s:elseif>
			</s:if></td>
			<td><s:elseif test="%{#top_lvl_mnu_itr.last == true}">

				<s:if
					test="%{#session.TOP_LEVEL_MENUS.size() == #top_lvl_mnu_itr.count && #session.HighLightMenu == name}">
					<table cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td class="lastTabLoad">
							<div id="lastTabLoad">&nbsp;&nbsp;&nbsp;</div>
							</td>
							<td class="lastTabLoadMid">
							<div id="ltabloadmidimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="lastTabLoadMid">
							<div id="lastTabLoadMid">&nbsp; <a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							<s:property value="name" /> </a></div>
							</td>
							<td class="lastTabLoadEnd">
							<div id="lastTabLoadEnd"></div>
							</td>
						</tr>
					</table>
				</s:if>
				<s:elseif
					test="%{#session.TOP_LEVEL_MENUS.size() == #top_lvl_mnu_itr.count && #session.HighLightMenu != name}">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td class="lastTab">
							<div id="lastTab">&nbsp;&nbsp;&nbsp;</div>
							</td>
							<td class="lastTabMid">
							<div id="ltabmidimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="lastTabMid">
							<div id="lastTabMid">&nbsp; <a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							<s:property value="name" /> </a></div>
							</td>
							<td class="lastTabEnd">
							<div id="lastTabEnd"></div>
							</td>
						</tr>
					</table>
				</s:elseif>
			</s:elseif></td>
			<td><s:else>
				<s:if test="%{#session.HighLightMenu == name}">
					<table cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td class="midTabLoad">
							<div id="midTabLoad">&nbsp;&nbsp;&nbsp;&nbsp;</div>
							</td>
							<td class="midTabLoadMid">
							<div id="mtabloadmidimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="midTabLoadMid">
							<div id="midTabLoadMid"><a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							&nbsp;&nbsp;<s:property value="name" /> </a></div>
							</td>
							<td class="midTabLoadEnd">
							<div id="midTabLoadEnd"></div>
							</td>
						</tr>
					</table>
				</s:if>
				<s:else>
					<table cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td class="midTab">
							<div id="midTab">&nbsp;&nbsp;&nbsp;&nbsp;</div>
							</td>
							<td class="midTabMid">
							<div id="mtabmidimg"><img
								src="<%=request.getContextPath()%><s:property value="image" />" /></div>
							</td>
							<td class="midTabMid">
							<div id="midTabMid"><a
								href="/shiplinx/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							&nbsp;&nbsp;<s:property value="name" /> </a></div>
							</td>
							<td class="midTabEnd">
							<div id="midTabEnd"></div>
							</td>
						</tr>
					</table>
				</s:else>
			</s:else></td>
		</s:iterator>


	</tr>
</table>

<div id="plug_table2">
<table cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<div id="two_level_bckgd_1px_divider_1"><img
			src="<%=request.getContextPath()%>/mmr/images/divider.png" /></div>
		</td>

		<td>
		<div id="logout"><a
			href="<%=request.getContextPath()%>/logout.action"><img
			src="<%=request.getContextPath()%>/mmr/images/plug_in.png" /></a></div>
		</td>
		<td>
		<div id="two_level_bckgd_1px_divider_2"><img
			src="<%=request.getContextPath()%>/mmr/images/divider.png" /></div>
		</td>
		<!--  <td valign="top">&nbsp;&nbsp;<img
			src="<%=request.getContextPath()%>/mmr/images/textRadioBn_1.png" />
		</td>-->
	</tr>
</table>
</div>
</div>

</div>
<div id="three_level"></div>
<s:if test="%{#session.HighLightMenu != null}">
<div id="three_level_btn">
</s:if> <s:else>
<div id="three_level_btn_null">
</s:else>
<table cellpadding="0" cellspacing="1">
	<tr>
		<td align="right" width="180px;">
		<div id="level3_btn_display"><s:if
			test="%{#session.HighLightMenu != null}">
			<%=request.getSession().getAttribute("HighLightMenu") %>
		</s:if></div>
		</td>
		<td>
		<div id="sub_btns" cellspacing="0" cellpadding="0">
		<table border="0">
			<tr>
				<s:iterator value="#session.LEVEL_ONE_MENUS" id="menu"
					status="lvl_one_menus">
					<td colspan="1" class="text_01" style="padding-bottom: 20px;"
						cellspacing="0" cellpadding="0">&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="<s:url value="%{url}" includeContext="true" includeParams="none" />">
					<s:property value="name" /> </a> &nbsp;&nbsp;&nbsp;&nbsp; <s:if
						test="%{#lvl_one_menus.last != true}">
						<img
							src="<%=request.getContextPath()%>/mmr/images/3level_divider.png" />
					</s:if></td>
				</s:iterator>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
<div id="welcome_msg">
<table width="97%">
	<tr>
	<!--  	<td>
		<div id="welcome">Welcome</div>
		</td>
		<td>
		<div id="admin_icon"><img
			src="<%=request.getContextPath()%>/mmr/images/admin_icon.png" /></div>
		</td>-->
		<td align="right">
		<div id="value_name">Welcome&nbsp;&nbsp;<img style="margin-top:3px;" 
			src="<%=request.getContextPath()%>/mmr/images/admin_icon.png"/>&nbsp;&nbsp;<s:property value="#session['username']" />
		</div>
		</td>
	</tr>
</table>
</div>
<s:if test="%{#session.LEVEL_TWO_MENUS != null}">
	<div id="four_lvl_menus">
	<table>
		<tr>
			<td class="text_01_2level">

			<div id="child_btns">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<s:iterator value="#session.LEVEL_TWO_MENUS" id="menu"
						status="lvl_two_mnu">
						<td class="text_01" align="center">
						<div id="four_lvl_nav">&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="<s:url value="%{url}" includeContext="true" includeParams="none" />">
						<s:property value="name" /> </a> &nbsp;&nbsp;&nbsp;&nbsp;
						<div id="four_lvl_nav_img"><img
							src="<%=request.getContextPath()%>/mmr/images/4level_nav_divider.png" />
						</div>

						</div>
						</td>

					</s:iterator>
				</tr>
			</table>
			</div>

			</td>
		</tr>
	</table>
	</div>
</s:if> <s:else>
	<div id="four_lvl_menus_else">
	<table>
		<tr>
			<td class="text_01_2level">

			<div id="child_btns">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="text_01" align="center">&nbsp;</td>

				</tr>
			</table>
			</div>

			</td>
		</tr>
	</table>
	</div>
</s:else></div>
<!-- End  -->
</form>
</body>


</html>
