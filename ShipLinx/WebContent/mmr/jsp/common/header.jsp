<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>SHIPLINX</title>
	
	<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ie.css" />
<![endif]-->
	
	<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>	
	<%-- <link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css" media="screen and (min-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/> --%>
	<!-- <link rel="stylesheet" id="browsercheck" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css" media="screen and (min-width:1024px)"/>-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/laptop.css"/>
	<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphoneMini.css" media="screen and (min-width:240px) and (max-width:319px)"/>-->
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>

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
			<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
 <!-- remove 3rd level boreder -->   
 <script>
 $(document).ready(function(){
  var countli = $('.nextlevelmenu ul li').size();
  if(countli == 0){
   $('.nextlevelmenu ul').css('border','0px');
  }
 });
 </script>
 
 
 
 
 	<script>	
var ALERT_TITLE = "Oops!";
var ALERT_BUTTON_TEXT = "Ok";
var Confirm_BUTTON_TEXT = "Cancel";




if(document.getElementById) {
	window.alert = function(txt) {
		createCustomAlert(txt);
	}
	
}

function createCustomAlert(txt) {
	d = document;

	if(d.getElementById("modalContainer")) return;

	mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
	mObj.id = "modalContainer";
	mObj.style.height = d.documentElement.scrollHeight + "px";
	
	alertObj = mObj.appendChild(d.createElement("div"));
	alertObj.id = "alertBox";
	if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
	var leftMargin = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2;
	alertObj.style.left = (leftMargin-40) + "px";
	alertObj.style.visiblity="visible";

	/*h1 = alertObj.appendChild(d.createElement("h1"));
	h1.appendChild(d.createTextNode(ALERT_TITLE));*/

	msg = alertObj.appendChild(d.createElement("p"));
	//msg.appendChild(d.createTextNode(txt));
	msg.innerHTML = txt;

	btn = alertObj.appendChild(d.createElement("a"));
	btn.id = "closeBtn";
	btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
	btn.href = "#";
	btn.focus();
	btn.onclick = function() { removeCustomAlert();return false; }

	alertObj.style.display = "block";
	
}


function createCustomConfirm(txt) {

}

function removeCustomAlert() {
	document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
}

	</script>
	<script>
		$(document).ready(function(){ 
		
			$('#help').click(function(){
				$('#helpBody').css('display','block');
			});
			$('#closeBtn').click(function(){
				$('#helpBody').css('display','none');
			});
		});
	
	 function helpFile() {
			var url="<%=request.getContextPath()%>/helpMenu.action";
			window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
		} 
	 
	  function supportURL(){   
		   window.open("http://soluship.fastsupport.com",'','width=860,height=540,left=100,top=100,scrollbars=1'); 
		  }
	</script>
	
	
	
	<!--	<style>
			#helpBody{ z-index:1000; display:none; background-color:rgba(0,0,0,0.2); width:100%; height:100%; position:fixed;}
			#helpBody .helpPopup{ width:500px; height:320px; margin:50px auto; background-color:#FFF;}
			#helpBody .popupContentBody{ padding:10px;}
			.closeBtnBody{ width:100%; float:left; height:30px; background-color:#000;}
			.popupContent{ width:100%; float:left; height:260px; overflow-y:scroll; font-size:12px;}
			.closeBtnBody span{ width:400px; color:#FFF; padding:7px 5px;  float:left; font-size:16px; font-weight:600;height:20px;}
			.closeBtnBody img{ float:right; padding:3px 5px; cursor:pointer;}
		</style>
</head>
<body>

	<!-- help message shiplinx.war
	<div id="helpBody">
		<div class="helpPopup">
			<div class="popupContentBody">
				<div class="closeBtnBody">
					<span >Help Document</span>
					<img id="closeBtn" src="<%=request.getContextPath()%>/mmr/images/close.png" alt="img" height="25" width="25" />
				</div>
			<s:iterator value="#session.TOP_LEVEL_MENUS" id="menu"
			status="top_lvl_mnu_itr">
		<s:if test="%{#session.HighLightMenu == name}">			
				<div class="popupContent">
				<s:if test="%{name == 'Admin'}">
				<mmr:message messageId="label.help.admin"/>

				</s:if>
		<s:elseif test="%{name == 'EDIs'}">
				<mmr:message messageId="label.help.edis"/>
		</s:elseif>
		<s:elseif test="%{name == 'Accounts'}">
				<mmr:message messageId="label.help.accounts"/>
		</s:elseif>
		<s:elseif test="%{name == 'Shipping'}">
				<mmr:message messageId="label.help.shipping"/>
		</s:elseif>
		<s:elseif test="%{name == 'Reports'}">
				<mmr:message messageId="label.help.report"/>
		</s:elseif>
		<s:elseif test="%{name == 'Address'}">
				<mmr:message messageId="label.help.address"/>
		</s:elseif>
				</div>
				</s:if>
				
			</s:iterator>
			</div>
		</div>
	</div>   -->

	<div id="wrapper">
		<div class="top_line"></div>
		<div class="header-top">
			<div class="top_header_inner">
				<div class="header_logo_body">
					<div class="logo"><img src="<%=request.getContextPath()%>/mmr/images/logo.png"></div>
					<div class="top_navi">
						<ul>
							<li><a href="">CONTACT US</a></li>
							<li><a href="">WEBSITE FEEDBACK</a></li>
							<li><a href="">FRANCAIS</a></li>
							<li><a href="javascript:helpFile();">HELP</a></li>
							<li id="logout"><a href="<%=request.getContextPath()%>/logout.action">LOG OUT</a></li>
						</ul>
						
					</div>
					<div class="live_chat">
						<a href="#" onclick="javascript:supportURL();"><img src="<%=request.getContextPath()%>/mmr/images/livechat.png"></a>
					</div>
				</div>
				<div class="navigation">
					<div class="naviinner">
					<ul>
							<s:iterator value="#session.TOP_LEVEL_MENUS" id="menu"
			status="top_lvl_mnu_itr">
		<s:if test="%{#session.HighLightMenu == name}">
					<li>
							<a
						href="<%=request.getContextPath()%>/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />"style="color: #ffffff; background: #990000;">
							&nbsp;&nbsp;<s:property value="name" /> </a></li>
				
				</s:if>
				<s:else>
			<li>
							<a
								href="<%=request.getContextPath()%>/highLightMenu.action?value=<s:property value='url' />&menu=<s:property value='name' />">
							&nbsp;&nbsp;<s:property value="name" /> </a>
</li>
				</s:else>
			
		</s:iterator>
						
					</ul>
					<div class="search_body">
						<form method="post" action="">
							<input type="text" placeholder="Enter search keyword here">
							<input type="submit" value=" ">
						</form>
						<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
						<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.placeholder.js"></script>
						  <script>
						   $(function() {
							$('input, textarea').placeholder();
						   });
						  </script>
						
					</div>
					</div>
				</div>
				<div class="navi_icon"><img src="<%=request.getContextPath()%>/mmr/images/menuicon.png" widt="30" height="30"/></div>
				<div class="navi_shadow">
				</div>
			</div>
		</div>
		
		<div class="wrapper_btm">
			<div class="wrapper_btm_inner">
				<div class="wrapper_body">
					<div class="wrapper_navi">
						<ul>
							<li><s:iterator value="#session.LEVEL_ONE_MENUS" id="menu"
					status="lvl_one_menus">
					&nbsp;&nbsp;&nbsp;&nbsp;<li> <a
						href="<s:url value="%{url}" includeContext="true" includeParams="none" />">
					<s:property value="name" /> </a> &nbsp;&nbsp;&nbsp;&nbsp; <s:if
						test="%{#lvl_one_menus.last != true}">
						</li>
					</s:if>
				</s:iterator>
							
						</ul>
						<p>WELCOME&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#session['username']" />
		</p>
					</div>
				
					<div class="nextlevelmenu">
						<ul>
							<s:iterator value="#session.LEVEL_TWO_MENUS" id="menu"
						status="lvl_two_mnu">
						<li>
						<div id="four_lvl_nav"><a
							href="<s:url value="%{url}" includeContext="true" includeParams="none" />">
						<s:property value="name" /> </a>
							</div>
							</li>
					</s:iterator>
							
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>