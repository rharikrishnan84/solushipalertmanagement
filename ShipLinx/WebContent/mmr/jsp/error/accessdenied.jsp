2<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>



<div class="content_body">
		<div class="content_table" >
		
		
<div class="browseBox">
<h2>We apologize, an error occurred while processing your request.</h2>
</div>
<div class ="browseBox" style="background-color:rgb(248,236,224);color:red">
<li class="subLevel"> &nbsp Access Denied</li>
</div>
 &nbsp
<h3>The error has been logged and we will be investigating the issue shortly. <br/><br/>Please try the following:</h3>
<table id="siteMap" width="100%" border="0" cellpadding="0" cellspacing="10">
	<tr>
		<td class="unavailableContainer" align="left" valign="top" >		
		<div class="rightNavList">
			<ul class="last">
			<li class="subLevel">Press F5 to clear your cache and then click OK to confirm you would like to clear.</li>
      		<li class="subLevel">Make sure the address was typed correctly.</li>
			<li class="subLevel">Open the <a target="_top" id="welcome" title="Open Welcome Page" href="./index.jsp"><b>Welcome Page</b></a>, then navigate to your information.</li>
			<li class="subLevel">Click the <a title="Go back" href="javascript:history.back(1)"><b>Back</b></a> button to try again.</li>
</ul>
</div>
</td>
</tr>
</table>



<br/>
<br/>
If the error persists, please contact our support team <a class="TextLink" href="mailTo:%{getText('email')}"><s:text name="email"/></a> and quote the Error ID listed below.
<br/>
<s:fielderror/>

</div>
</div>
<script language="javascript" type="text/javascript">
    //this code handles the F5/Ctrl+F5/Ctrl+R
		document.onkeydown = checkKeycode
    function checkKeycode(e) {
        var keycode;
        if (window.event)
            keycode = window.event.keyCode;
        else if (e)
            keycode = e.which;

        // Mozilla firefox
        if ($.browser.mozilla) {
            if (keycode == 116 ||(e.ctrlKey && keycode == 82)) {
                if (e.preventDefault)
                {	
					e.preventDefault();
                    e.stopPropagation();
  					//var r=confirm("Would you like to clear cache");
						//if(r==true){
						     //alert("test");						  
							 //window.location.href = $('#welcome').attr("href");	
							 //$.cookie("name", null, { path: '/' });
						     //Session.Abandon();
						     //Response.Cookies.Clear();
							 
						   //}						
						   			//createCustomConfirm;
				var ALERT_BUTTON_TEXT = 'Ok';
				var CANCEL_BUTTON_TEXT = 'Cancel';
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
				msg.innerHTML = "Do you really want to delete the selected user";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','30px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','30px');
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					window.location.href = $('#welcome').attr("href");	
					$.cookie("name", null, { path: '/' });
					Session.Abandon();
					Response.Cookies.Clear();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
                }
            }
        } 
       
    }

</script>