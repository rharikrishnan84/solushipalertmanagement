<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script>	
var ALERT_TITLE = "Oops!";
var ALERT_BUTTON_TEXT = "Ok";
var Confirm_BUTTON_TEXT = "Cancel";




if(document.getElementById) {
	window.alert1 = function(txt,v) {
		createCustomAlert1(txt,v);
	}
	
}

function createCustomAlert1(txt,v) {
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
	btn.onclick = function() { removeCustomAlert1(v);return false; }

	alertObj.style.display = "block";
	
}


function createCustomConfirm(txt) {

}

function removeCustomAlert1(v) {
	document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
	if(v=="totalvalue")
		{
		document.getElementById("ci.totalvalue").focus();
		}
	else
		{
		document.getElementById("currencyautocomplete").focus();
		}
}

	</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
</script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />	
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>	
<style>
/* .total_label{ position:relative;} */
.total_label{ position:relative; width:105%;}
.popup_text{display:none; position:absolute;  height:auto; padding:5px 10px 5px 5px  ; width:'auto'; left:-30px; color:#000;	 background-color:#fff; border:1px solid #999; z-index:1000;  }

</style>
<head>
<sx:head />
</head>
	
	<script type="text/javascript">
		$(document).ready(function(){
		
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
		  
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
			
			
			
			// popup for the grid - total cost field
			$('.total_label').click(function(){
				//alert($(this).attr("index"));
				$('.popup_text').css('display','none');
				var indexval = $(this).attr("index");
				if(indexval >=0){
					$(this).next().css({
						'display':'block',
						'top':'-50px',
						'left':'-180px'
					});
				}
				else{
					$(this).next().css({
						'display':'block',
						'top':'25px',
						'left':'-180px'
					});
				}
			});
			$('.closebtn').click(function(){
				$(this).closest('.popup_text').css('display','none');
			});

			
		});
		
		
	
	</script>
	<script type="text/javascript">
	
		$(document).ready(function() {
		 
			$("#check_all").click(function(){
				var temp=$(this).attr("checked");
				if(temp){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
			
			/*$('.gridedit').click(function(){
				var check = $(".dataTable-checkbox:checked").length;
				if(check < 1){
					alert('Please check atleast one checkbox');
					location.href="admin/search.products.action?method=new#";
				}
				
			});*/
			
			
			
			
		} );
	</script>
		<script>
	
	$(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
	<script type="text/javascript">
	
	function naturalSort(a, b) {
						    var re = /(^-?[0-9]+(\.?[0-9]*)[df]?e?[0-9]?$|^0x[0-9a-f]+$|[0-9]+)/gi,
							        sre = /(^[ ]*|[ ]*$)/g,
							        dre = /(^([\w ]+,?[\w ]+)?[\w ]+,?[\w ]+\d+:\d+(:\d+)?[\w ]?|^\d{1,4}[\/\-]\d{1,4}[\/\-]\d{1,4}|^\w+, \w+ \d+, \d{4})/,
							        hre = /^0x[0-9a-f]+$/i,
							        ore = /^0/,
							        // convert all to strings and trim()
							        x = a.toString().replace(sre, '') || '',
						        y = b.toString().replace(sre, '') || '',
							        // chunk/tokenize
							        xN = x.replace(re, '\0$1\0').replace(/\0$/, '').replace(/^\0/, '').split('\0'),
							        yN = y.replace(re, '\0$1\0').replace(/\0$/, '').replace(/^\0/, '').split('\0'),
							        // numeric, hex or date detection
							        xD = parseInt(x.match(hre)) || (xN.length != 1 && x.match(dre) && Date.parse(x)),
							        yD = parseInt(y.match(hre)) || xD && y.match(dre) && Date.parse(y) || null;
							    // first try and sort Hex codes or Dates
							    if (yD) if (xD < yD) return -1;
							    else if (xD > yD) return 1;
							    // natural sorting through split numeric strings and default strings
							    for (var cLoc = 0, numS = Math.max(xN.length, yN.length); cLoc < numS; cLoc++) {
							        // find floats not starting with '0', string or 0 if not defined (Clint Priest)
							        oFxNcL = !(xN[cLoc] || '').match(ore) && parseFloat(xN[cLoc]) || xN[cLoc] || 0;
							        oFyNcL = !(yN[cLoc] || '').match(ore) && parseFloat(yN[cLoc]) || yN[cLoc] || 0;
							        // handle numeric vs string comparison - number < string - (Kyle Adams)
							        if (isNaN(oFxNcL) !== isNaN(oFyNcL)) return (isNaN(oFxNcL)) ? 1 : -1;
							        // rely on string comparison if different types - i.e. '02' < 2 != '02' < '2'
							        else if (typeof oFxNcL !== typeof oFyNcL) {
							            oFxNcL += '';
							            oFyNcL += '';
							        }
							        if (oFxNcL < oFyNcL) return -1;
							        if (oFxNcL > oFyNcL) return 1;
							    }
							    return 0;
							};
						// Natural Sorting
							jQuery.fn.dataTableExt.oSort['natural-asc'] = function(a, b) {
							    return naturalSort(a, b);
							};
							jQuery.fn.dataTableExt.oSort['natural-desc'] = function(a, b) {
							    return naturalSort(a, b) * -1;
							};
							   $(document).ready(function() {
					
							    $('.table-data').each(function(index, table) {
							       
						        $(this).dataTable({
						        	"columnDefs": [
						        	               {
							        	                   "targets": 8,
							        	                   "visible": false,
							        	                   "searchable": false
							        	               }
							        	           ],
							        	           "aoColumns": [null, null, null, null,null,null,null,{ "iDataSort": 8 },{ "sType": "natural"},null]
							        });
							    });
					
							});  
	</script>
	
		
<%-- <SCRIPT language="JavaScript">
var radioselected = 0;

function submitShipment(){
	var editUserId = document.getElementsByName("check_uncheck");
	 var i1,txt1 = 0;
	 for (i1=0;i1<editUserId.length;i1++){
	  if (editUserId[i1].checked){
	   txt1 += 1;      
	  }
	 }
	 if(txt1 < 1){
	  alert('Please check atleast one');
	 }
	 else if(txt1>1){
	  alert('Please check atmost one');
	 }
	 else{    
	  var jconfirm1=false;
	  var Login_url,ratelist_id,checkbox_value;
	  for (i=0;i<editUserId.length;i++){
	   if (editUserId[i].checked){
	    var carrier=document.getElementById("shipmentCarrierId"+editUserId[i].value).value;
	    checkbox_value = editUserId[i].value;
	   }
	  }
	 }
	 if(carrier==6){
	  var method="check";
	  ajax_Service=ajaxFunction();
	  ajax_Service.onreadystatechange=function(){
	   if(ajax_Service.readyState==4){
	    response=ajax_Service.responseText;
	    autoFreightClass='<%=session.getAttribute("autoFreightClass")%>';
	    <%
	       com.meritconinc.shiplinx.model.ShippingOrder shippingOrder1=(com.meritconinc.shiplinx.model.ShippingOrder)session.getAttribute("shippingOrder");
	    %>  
	    var frieghtClass='<%=shippingOrder1.getPackages().get(0).getFreightClass()%>';
	 
	    if('null' != frieghtClass && frieghtClass!=''){
	     submitShipment2();
	    }
	    else{
	     if(autoFreightClass!=null && autoFreightClass=="false"){
	     jConfirm('We have based this rate on a estimated class and is subject to reclassification, If you would like to put in a accurate class please click YES?<br><input type="checkbox" id="check_auto_detect" value="true" onclick="autoFreightClassCheck()"/>accept terms and conditions for all future shipments</input>','Yes','No', function(r) {
	      if(r){
	      method="update0";
	      ajax_Service=ajaxFunction();
	      ajax_Service.onreadystatechange=function(){
	       if(ajax_Service.readyState==4){
	        response=ajax_Service.responseText;
	        backToShipment(); 
	       }
	      }
	      var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
	        ajax_Service.open("GET",url1,true);
	        ajax_Service.send(this);
	     }
	     else{
	      method="insert";
	      ajax_Service=ajaxFunction();
	      ajax_Service.onreadystatechange=function(){
	       if(ajax_Service.readyState==4){
	        response=ajax_Service.responseText;
	        submitShipment2();
	       }
	      }
	      var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
	        ajax_Service.open("GET",url1,true);
	        ajax_Service.send(this);
	     }
	    });
	    }
	     else{
	      submitShipment2();
	     }
	    }
	   }
	  }
	  var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
	    ajax_Service.open("GET",url1,true);
	    ajax_Service.send(this); 
	 }
	 else{
	  submitShipment2();
	 }
} --%>


<SCRIPT type="text/javascript">
var radioselected = 0;
var autoFreightClass=false;
function submitShipment(){
 var editUserId = document.getElementsByName("check_uncheck");
 var i1,txt1 = 0;
 for (i1=0;i1<editUserId.length;i1++){
  if (editUserId[i1].checked){
   txt1 += 1;      
  }
 }
 if(txt1 < 1){
  alert('Please check atleast one');
 }
 else if(txt1>1){
  alert('Please check atmost one');
 }
 else{    
  var jconfirm1=false;
  var Login_url,ratelist_id,checkbox_value;
  for (i=0;i<editUserId.length;i++){
   if (editUserId[i].checked){
    var carrier=document.getElementById("shipmentCarrierId"+editUserId[i].value).value;
    checkbox_value = editUserId[i].value;
   }
  }
 }
 if(carrier==6){
  var method="check";
  ajax_Service=ajaxFunction();
  ajax_Service.onreadystatechange=function(){
   if(ajax_Service.readyState==4){
    response=ajax_Service.responseText;
    autoFreightClass='<%=session.getAttribute("autoFreightClass")%>';
    <%
       com.meritconinc.shiplinx.model.ShippingOrder shippingOrder1=(com.meritconinc.shiplinx.model.ShippingOrder)session.getAttribute("shippingOrder");
    %>
    var frieghtClass='<%=shippingOrder1.getPackages().get(0).getFreightClass()%>';
    if('null' != frieghtClass && frieghtClass!=''){
     submitShipment2();
    }
    else{
     if(autoFreightClass!=null && autoFreightClass=="false"){
     jConfirm('We have based this rate on a estimated class and is subject to reclassification, If you would like to put in a accurate class please click YES?<br><input type="checkbox" id="check_auto_detect" value="true" onclick="autoFreightClassCheck()"/>accept terms and conditions for all future shipments</input>','Yes','No', function(r) {
      if(r){
      method="update0";
      ajax_Service=ajaxFunction();
      ajax_Service.onreadystatechange=function(){
       if(ajax_Service.readyState==4){
        response=ajax_Service.responseText;
        backToShipment(); 
       }
      }
      var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
        ajax_Service.open("GET",url1,true);
        ajax_Service.send(this);
     }
     else{
      method="insert";
      ajax_Service=ajaxFunction();
      ajax_Service.onreadystatechange=function(){
       if(ajax_Service.readyState==4){
        response=ajax_Service.responseText;
        submitShipment2();
       }
      }
      var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
        ajax_Service.open("GET",url1,true);
        ajax_Service.send(this);
     }
    });
    }
     else{
      submitShipment2();
     }
    }
   }
  }
  var url1="shipment.autoFreightClassCheck.action?autoFreightClass="+autoFreightClass+"&method="+method;
    ajax_Service.open("GET",url1,true);
    ajax_Service.send(this);
 }
 else{
  submitShipment2();
 }
}
	function autoFreightClassCheck() {
			var method = "update1";
			if ($('#check_auto_detect').prop("checked") == true) {
				autoFreightClass = $("#check_auto_detect").val();
				ajax_Service = ajaxFunction();
				ajax_Service.onreadystatechange = function() {
					if (ajax_Service.readyState == 4) {
						response = ajax_Service.responseText;
					}
				}
				var url1 = "shipment.autoFreightClassCheck.action?autoFreightClass="
						+ autoFreightClass + "&method=" + method;
				ajax_Service.open("GET", url1, true);
				ajax_Service.send(this);
			} else {
				method = "update0";
				autoFreightClass = $("#check_auto_detect").val();
				ajax_Service = ajaxFunction();
				ajax_Service.onreadystatechange = function() {
					if (ajax_Service.readyState == 4) {
						response = ajax_Service.responseText;
					}
				}
				var url1 = "shipment.autoFreightClassCheck.action?autoFreightClass="
						+ autoFreightClass + "&method=" + method;
				ajax_Service.open("GET", url1, true);
				ajax_Service.send(this);
			}
		}
	function submitShipment2()
	{
		var editUserId = document.getElementsByName("check_uncheck");
			
			var i1,txt1 = 0;
		   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please check atleast one');
		   }
		   else if(txt1>1){
				alert('Please check atmost one');
			}
			else
			{
			var Login_url,ratelist_id,checkbox_value;
			
			for (i=0;i<editUserId.length;i++){
			if (editUserId[i].checked){
					
				var carrier=document.getElementById("shipmentCarrierId"+editUserId[i].value).value;
												checkbox_value = editUserId[i].value;
												}
												}
			if(carrier==20 && document.getElementById("toCountry").value=="UNITED STATES"  && document.getElementById("fromCountry").value=="CANADA"&& document.getElementById("currencyautocomplete").value==""){												
										error=false;
										alert1("Please enter the Currency and Total value on Customs Invoice Information","currency");
																document.getElementById("customs_invoice_panel").style.display = 'block';		
																document.getElementById("customs_invoice_checkbox").checked=true;
																					document.getElementById("currencyautocomplete").focus();
																					return;
															
														   }
														   
														 else{
						Login_url = document.getElementsByName("check_value"+checkbox_value)[0].value;
						ratelist_id = document.getElementsByName("id_value"+checkbox_value)[0].value;
						var curCarrierId = document.getElementById("shipmentCarrierId"+checkbox_value).value;
						if(Login_url==""){
							Login_url='null';
						}
						
			
			}
			
			
			var url=Login_url;
			var val=ratelist_id;
			
			
	 	
		var error=true;
		var pickup=false;
		var loadCheck=true;		
		if(loadCheck)
		{
			loadCheck=false;
			
			<% 
		    HttpSession httpSession = request.getSession();
		    com.meritconinc.shiplinx.model.ShippingOrder shippingOrder=(com.meritconinc.shiplinx.model.ShippingOrder)httpSession.getAttribute("shippingOrder");
			%>
		var pickupRequest='<%=shippingOrder.getPickup().isPickupRequired()%>';
		if(pickupRequest == "true")
		{
		ajax_Service=ajaxFunction();
			$('#wait').show();
			ajax_Service.onreadystatechange=function()
			  {
				   if(ajax_Service.readyState==4)
					{
					response=ajax_Service.responseText;
					var res = response;	
				if(typeof String.prototype.trim1 !== 'function') {
						String.prototype.trim1 = function() {
						return this.replace(/^\s+|\s+$/g, ''); 
						}
					}
					res = res.trim1();
					if(res === "true"){	
						$('#wait').hide();
						error = false;
						jConfirm('You have a pickup already scheduled for today for this carrier and location. Do you want to create another pickup for the same location?', 'Yes', 'No', function(confirmationMessage) {
						if(confirmationMessage==false){							
							error=true;							 
							shipmentOrder(val,url,pickup);				
						}else{							
							error = true;
							pickup=true;
							shipmentOrder(val,url,pickup);
						}
						
						});
					
				}else{
					$('#wait').hide();					
					pickup=true;
					shipmentOrder(val,url,pickup);
				}
					}
			  }
			
			  firstBox = document.getElementById('firstBox');
			  verifyURL="shipment.verifyPickup.action?carrierId="+curCarrierId;
			  	ajax_Service.open("GET",verifyURL,true);
			  	ajax_Service.send(this);
		   }else{					
					pickup=false;
					shipmentOrder(val,url,pickup);
				}
		}
	}
	}


	function shipmentOrder(val,url,pickup){
	var error=true;
	var shipper;
	var billTo;
	var cur;
	var billToAcc;
	var checkelement = document.getElementById("customs_invoice_checkbox");
		if(checkelement!=null)
		{
			var check = checkelement.checked;
			if(check)
			{
				shipper=document.getElementById("radio_addressShipper").checked;
				if(shipper){
				     billTo=1;
				}else {
				     shipper=document.getElementById("radio_addressConsignee").checked;
					 if(shipper){
					    billTo=2;
					 }else{
					    shipper=document.getElementById("radio_addressThird Party").checked;
						if(shipper){
						  billTo=3;
						}
					}
				}
				cur= document.getElementById("currencyautocomplete").value;
				billToAcc=document.getElementById("billToAccountNum").value; 
				var broker=document.getElementById("imports3").value;
				if(radioselected == '3' && document.getElementById("billToAccountNum").value=='')
				{
					alert("Please enter the Account Number for the Third Party BillTo Address");
					error=false;
				}
				else if(document.getElementById("currencyautocomplete").value=='')
				{
					error=false;
					alert1("Please enter the Currency in Customs Invoice Information","currency");
										document.getElementById("currencyautocomplete").focus();				
				}
				else if(document.getElementById("ci.contactName").value=='' && document.getElementById("imports3").value!='')
				{
					error=false;
					alert("Please enter the Contact Name in Broker Information");					
				}
				else if(document.getElementById("ci.totalvalue").value=='' )
				{
					error= false;
					alert1("Please enter the Total Value in Customs Invoice Information","totalvalue");
					document.getElementById("ci.totalvalue").focus();
				}
				else if(document.getElementById("ci.totalvalue").value>2000 && document.getElementById("consigneeTax").value=='')
				{
				
					//var confirmationMessage=confirm('A tax identification number is required for high valued shipments. Shipping without this will cause a delay in customs.\n Would you like to continue the shipment without the tax id?');
					error = false;
					jConfirm('A tax identification number is required for high valued shipments. Shipping without this will cause a delay in customs.\n Would you like to continue the shipment without the tax id?', 'Yes', 'No', function(confirmationMessage) {
						if(confirmationMessage==false){
							error=false;
							document.getElementById("consigneeTax").focus();
						}else{
							error=true;
						}
						var email=document.getElementById("ci.emailid").value;
						if(!echeck(email))
						{
							error=false;
							alert("Please enter a valid Email Address");
							document.getElementById("ci.emailid").value='';
							document.getElementById("ci.emailid").focus();
						}
						if(error & url=="null")
						{
							$('#loader').css('display','block');
								document.userform.elements['shippingOrder.rateIndex'].value = val;
								document.userform.action= "shipment.save.action?pickupRequired="+pickup+"&billTo="+billTo+"&cur="+cur+"&billToAcc="+billToAcc+"&broker="+broker;
								document.userform.submit();				
						}
						else if(error && url!="null")
						{
							window.open(url);  //This is for LOOMIS Carrier
						}
					});
				}
				else if(document.getElementById("ci.totalweight").value=='')
				{
					error= false;
					alert("Please enter the Total Weight in Customs Invoice Information");
					document.getElementById("ci.totalweight").focus();			
				}
			
				var email=document.getElementById("ci.emailid").value;
				if(!echeck(email))
				{
					error=false;
					alert("Please enter a valid Email Address");
					document.getElementById("ci.emailid").value='';
					document.getElementById("ci.emailid").focus();
				}
			}
		}
		
		if(error && url=='null')
		{
		$('#loader').css('display','block');
		document.userform.elements['shippingOrder.rateIndex'].value = val;
		document.userform.action= "shipment.save.action?pickupRequired="+pickup+"&billTo="+billTo+"&cur="+cur+"&billToAcc="+billToAcc+"&broker="+broker;
			document.userform.submit();			
		}
		else if(error && url!="null")
		{
			window.open(url);  //This is for LOOMIS Carrier
		}
	}
	
	function sendCustomerEmail(){
	var editUserId = document.getElementsByName("check_uncheck");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please check atleast one');
				
			}
			else if(txt1 > 1){
				alert('Please check atmost one');
			}
			else{
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt =parseInt( editUserId[i].value)-1 ;						
					}
				}
				document.userform.action= "shipment.stageThree.rates.mail.action?selected="+txt;
				document.userform.submit();
			}
			
	}
	
	function backToShipment(){
		document.userform.action= "backToShipment.action";
		document.userform.submit();
	}
	
	function showOrHideSummary()
	{
		var anchor = getElementsByClassName("show_summ");
		var inner = anchor[0].innerHTML;
		
		if(inner != '<img src="<%=request.getContextPath()%>/mmr/images/show.png">')
		{
			document.getElementById("summary_div_middle").style.display = 'none';
			document.getElementById("summary_div_bot").style.display = 'none';
			document.getElementById("rate_img").style.display = 'none';
			anchor[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/show.png">';
		}
		else
		{
			document.getElementById("summary_div_middle").style.display = 'block';
			document.getElementById("summary_div_bot").style.display = 'block';
			document.getElementById("rate_img").style.display = 'block';
			anchor[0].innerHTML = '<img src="<%=request.getContextPath()%>/mmr/images/hide.png">';
		}
	}
	
	function echeck(str) 
	{
		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		
		if(str.length > 0)
		{
			if(str.indexOf(at)==-1)
			{
			     return false;
			}
			if(str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr)
			{
			     return false;
			}
			if(str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr)
			{
			   return false;
			}
			if(str.indexOf(at,(lat+1))!=-1)
			{
			   return false;
			}
			if(str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot)
			{
			   return false;
			}
			if(str.indexOf(dot,(lat+2))==-1)
			{
			   return false;
			}
		}
					
 		return true;					
	}
	
</script>

	
	<body>
	<script type="text/javascript">
	 window.onload = function()
  		{
  			var chckbx = document.getElementById("customs_invoice_checkbox");
  			if(chckbx.checked)
  			{  				
  				document.getElementById("customs_invoice_panel").style.display = 'block';	
  			}
  			else
  			{	  				
  				document.getElementById("customs_invoice_panel").style.display = 'none';	
  			}
  		}
	</script>
	
	
	<style>
		.show_summ img{ width:25px; height:25px;}
		#loader{display:none;}
	</style>
	<div id="loader" style="height:100%; width:100%; position:fixed;  background-color:rgba(0,0,0,0.6); z-index:1000;">
  <div style="width:100px; height:100px; margin:200px auto; background-image:url('/shiplinx/mmr/images/ajax-loader2.gif');"> 
  </div>
 </div>
<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="content">
<div class="content_body" >	
	<div class="content_table" style="margin-bottom:1px;" >
		<div class="form-container">
		<s:form action="shipment.save" name="userform">
		<s:token/>
			<div class="content_header" style="margin-bottom:1px">
				<div class="cont_hdr_title" id="srch_crtra"><mmr:message messageId="label.shippingOrder.summary"/></div>
				<div class="form_buttons">	
					<a href="#" class="show_summ" onclick="showOrHideSummary()" style="background-color:transparent; padding:0px;"><img src="<%=request.getContextPath()%>/mmr/images/show.png"></a>
				</div>
				<div class="cont_data_body borderLeftRight">
					<div class="rows">
						<div class="fields">
							<label><mmr:message messageId="label.shippingOrder.shipTo"/></label>
							<div class="controls"><span>:</span>
								<s:textfield  id="ci.abbreviationName" name="shippingOrder.customsInvoice.reference" value="%{shippingOrder.customsInvoice.reference}"  />
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>	
</div>
	
	
	<div class="content_body">
	<div class="content_table" id="summary_div_middle">
	<div class="cont_data_body borderLeftRight" style="padding:13px 0px 0px 5px; width:953px !important; height:auto;" >
         <div style="height:auto; width:545px; float:left; font-size:12px;">
          <div style=" height:auto; width:100%; float:left">
           <label style="width:60px; float:left;"><mmr:message messageId="label.shippingOrder.shipTo"/></label>
           <span style="float:left; margin-left:10px;">:</span>
           <label style="width:450px; margin-left:10px; float:left">
		   <s:if test='%{shippingOrder.toAddress.abbreviationName != ""}'>
						<s:property value="%{shippingOrder.toAddress.abbreviationName}"/>&nbsp;,&nbsp;
					</s:if>
					<s:else>
						
					</s:else>
			<!--  	</td>
				<td class="hlp_sprt" width="15%"> -->
					<s:if test='%{shippingOrder.toAddress.city != "" && shippingOrder.toAddress.postalCode == ""}'>
						<s:property value="%{shippingOrder.toAddress.city}"/>&nbsp;,&nbsp;
					</s:if>
					<s:elseif test='%{shippingOrder.toAddress.city == "" && shippingOrder.toAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.toAddress.postalCode}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:elseif test='%{shippingOrder.toAddress.city != "" && shippingOrder.toAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.toAddress.city}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.postalCode}"/>&nbsp;,&nbsp;
					</s:elseif>  
					<s:else>
						
					</s:else>
			<!--  	</td>
				<td class="hlp_sprt" width="15%"> -->
				<s:hidden value="%{shippingOrder.toAddress.countryName}" id="toCountry"/>
				<s:hidden value="%{shippingOrder.fromAddress.countryName}" id="fromCountry"/>
					<s:if test='%{shippingOrder.toProvinceName !="" && shippingOrder.toAddress.countryName == ""}'>
						<s:property value="%{shippingOrder.toProvinceName}"/>&nbsp;,&nbsp;
					</s:if>
					<s:elseif test='%{shippingOrder.toProvinceName == "" && shippingOrder.toAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.toAddress.countryName}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:elseif test='%{shippingOrder.toProvinceName != "" && shippingOrder.toAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.toProvinceName}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.toAddress.countryName}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:else>
						
					</s:else>					
			<!--  	</td>
				<td class="hlp_sprt" width="15%"> -->
				<s:if test='%{shippingOrder.toAddress.contactEmail != ""}'>
					<s:property value="%{shippingOrder.toAddress.contactEmail}"/>
				</s:if>
				<s:else>
					
				</s:else>
		   </label>
          </div>
          <div style=" height:auto; width:100%; float:left">
           <label style="width:60px; float:left;"><mmr:message messageId="label.shippingOrder.shipFrom"/></label>
           <span style="float:left; margin-left:10px;">:</span>
           <label style="width:450px; margin-left:10px; float:left">
		   <s:if test='%{shippingOrder.fromAddress.abbreviationName != ""}'>	
						<s:property value="%{shippingOrder.fromAddress.abbreviationName}"/>&nbsp;,&nbsp;
					</s:if>
					<s:else>
						
					</s:else>
			<!--  	</td>			
				<td class="hlp_sprt">-->				
					<s:if test='%{shippingOrder.fromAddress.city != "" && shippingOrder.fromAddress.postalCode == ""}'>
						<s:property value="%{shippingOrder.fromAddress.city}"/>&nbsp;,&nbsp;  
					</s:if>
					<s:elseif test='%{shippingOrder.fromAddress.city == "" && shippingOrder.fromAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.fromAddress.postalCode}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:elseif test='%{shippingOrder.fromAddress.city != "" && shippingOrder.fromAddress.postalCode != ""}'>
						<s:property value="%{shippingOrder.fromAddress.city}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.postalCode}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:else>
						
					</s:else>
			<!--  	</td>			
				<td class="hlp_sprt">-->
					<s:if test='%{shippingOrder.fromProvinceName != "" && shippingOrder.fromAddress.countryName == ""}'>
						<s:property value="%{shippingOrder.fromProvinceName}"/>&nbsp;,&nbsp;
					</s:if>	
					<s:elseif test='%{shippingOrder.fromProvinceName == "" && shippingOrder.fromAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.fromAddress.countryName}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:elseif test='%{shippingOrder.fromProvinceName != "" && shippingOrder.fromAddress.countryName != ""}'>
						<s:property value="%{shippingOrder.fromProvinceName}"/>&nbsp;,&nbsp;<s:property value="%{shippingOrder.fromAddress.countryName}"/>&nbsp;,&nbsp;
					</s:elseif>
					<s:else>
						
					</s:else>					
			<!--  </td>			
				<td class="hlp_sprt"> -->
				<s:if test='%{shippingOrder.fromAddress.contactEmail != ""}'>
					<s:property value="%{shippingOrder.fromAddress.contactEmail}"/>
				</s:if>
				<s:else>
					
				</s:else>
		   </label>
          </div>
         </div>
		 
		 
		 
		 
		 <div style="height:auto; width:400px; float:left; font-size:12px;">
          <div style=" height:auto; width:30%; float:left">
           <mmr:message messageId="label.shippingOrder.packageDetail"/>:
          </div>
          <div style="height:auto; width:70%; float:left;">
           <div style=" height:auto; width:100%; float:left">
            <label style="width:100%; float:left;">
			<s:if test='%{shippingOrder.packageTypeId.name != ""}'>	
					<s:property value="%{shippingOrder.packageTypeId.name}"/>
				</s:if>				
				<s:else>
					---
				</s:else>
			<!--  	</td>
				<td class="hlp_sprt"> -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:if test='%{shippingOrder.quantity != ""}'>
					<s:property value="%{shippingOrder.quantity}"/>
				</s:if>
				<s:else>
					---				
				</s:else>
			</label>
           </div>
           <div style=" height:auto; width:100%; float:left">
            <label style="width:100%; float:left;">
			<s:iterator value="shippingOrder.packages" status="counterIndex">
			
				
				<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].length" name="shippingOrder.packages[%{#counterIndex.index}].length" value="%{#session.shippingOrder.packages[#counterIndex.index].length}"/>&nbsp;x&nbsp;<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].width" name="shippingOrder.packages[%{#counterIndex.index}].width" value="%{#session.shippingOrder.packages[#counterIndex.index].width}"/>&nbsp;x&nbsp;<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].height" name="shippingOrder.packages[%{#counterIndex.index}].height" value="%{#session.shippingOrder.packages[#counterIndex.index].height}"/>&nbsp;&nbsp;(L x W x H)
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:label id="shippingOrder.packageArray[%{#counterIndex.index}].weight" name="shippingOrder.packages[%{#counterIndex.index}].weight" value="%{#session.shippingOrder.packages[#counterIndex.index].weight}"/>&nbsp;lbs</td>
						
			</s:iterator>
			</label>
           </div>
          </div>
         </div>
        </div>
	</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	<div id="summary_div_bot"></div>	
	<div id="rate_img"></div>
	<div id="summary_div_tab"></div>
	
	
	<!-- Check if the Shipment is International and if the packagetype is 'Package' OR 'Pallet' -->
	<s:if test="%{shippingOrder.isInternationalShipment==true && (#session.shippingOrder.packageTypeId.packageTypeId==3 || #session.shippingOrder.packageTypeId.packageTypeId==4)}">
	<div id="customInvoiceDetails">
		<jsp:include page="CustomsInvoiceDetails.jsp" />
	</div>
	</s:if>
	
	<div class="content_body">	
		<div class="content_table" style="margin-bottom:1px;">
			<div class="content_header">
				<div class="cont_hdr_title" id="srch_crtra"><mmr:message messageId="label.rates.list"/></div>
				<div class="form_buttons" >	
					<s:a href="javascript: backToShipment();">
	            
	            <mmr:message messageId="label.navigation.back"/> </s:a>
				</div>
			</div>
		</div>
	</div>	
	
	

<div class="content_body" >	
<div class="content_table" style=" background-color:#FFF; ">
							
	<div id="rate_res">
	<div id="srchusr_res"><span><mmr:message messageId="label.heading.estimatedratelist"/></span></div>
		<div class="form_buttons" >
			<a href="javascript: sendCustomerEmail()" title="Email Quote" ><mmr:message messageId="btn.email"/></a>
			<a href="javascript:submitShipment()"><mmr:message messageId="btn.shipnow"/></a>
			
		</div>
	</div>
	<div id="rate_results">	
	<!--<s:if test="%{shippingOrder.rateList.size()>1}">
	<div id="rslt_stmnt"><br/><s:property value="shippingOrder.rateList.size()" /><mmr:message messageId="label.search.results.items"/></div>
	</s:if>
	<s:elseif test="%{shippingOrder.rateList.size()==1}">
	<div id="rslt_stmnt"><br/><s:property value="shippingOrder.rateList.size()" /><mmr:message messageId="label.search.results.item"/></div>
	</s:elseif>
	<s:else>
	<div id="rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/></div>
	</s:else>-->
	</div>	
	<s:set var="sortTotal">
	<s:if test="%{#session.SHIP_MODE=='SHIP'}">
    	<s:a href="/shiplinx/admin/shipment.stageThree.action"><mmr:message messageId="label.total.price"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/></s:a>
    </s:if>
    <s:else>
    	<s:a href="/shiplinx/admin/shipment.stageThree.quote.action"><mmr:message messageId="label.total.price"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/></s:a>
    </s:else>
	</s:set> 
	<s:set var="sortTotalCostCharge">
	<s:if test="%{#session.SHIP_MODE=='SHIP'}">
    	<s:a href="/shiplinx/admin/shipment.stageThree.action"><mmr:message messageId="label.total.costCharge"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/></s:a>
    </s:if>
    <s:else>
    	<s:a href="/shiplinx/admin/shipment.stageThree.quote.action"><mmr:message messageId="label.total.costCharge"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/></s:a>
    </s:else>	
	</s:set>
	<s:set var="sortCarrier">
    	<mmr:message messageId="label.track.carrier"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="sortTransit">
    	<mmr:message messageId="label.transit.days"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="requri">
		<s:if test="%{#session.SHIP_MODE=='SHIP'}">
			/admin/shipment.stageThree.action
		</s:if>
		<s:else>
			/admin/shipment.stageThree.quote.action
		</s:else>
	</s:set>

	<div id="rates_result_tbl">
		<table style="position:relative;" cellpadding="0" cellspacing="0"  border="0px" class="table-data" id="sample1" width="100%">
			<thead style="cursor: pointer;">
				<tr>
				
					<th><input id="check_all" type="checkbox"/></th>
					<th style="width:34px">#</th>
					<th><mmr:message messageId="label.ghead.carrier"/></th>
					<th><mmr:message messageId="label.ghead.service"/></th>
					<th><mmr:message messageId="label.ghead.transitdays"/></th>
					<th style="width:100px"><mmr:message messageId="label.ghead.billwt"/>(LBS)</th>
					<th style="width:60px">Currency</th>
					<s:if test="%{#request.BillToType!=null}">
					<th><mmr:message messageId="label.ghead.billto"/></th>
					<th style="display:none">BT</th>
					</s:if>
					<s:else>
						<%-- <s:if test="%{#session.ADMIN_USER != null}">  --%>
						<s:if test="%{#session.ADMIN_USER != null  || #session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}"> 
						<th><mmr:message messageId="label.ghead.totalcost"/></th>
						<th style="display:none">TC</th>
						</s:if>
						<s:else>
						<th><mmr:message messageId="label.ghead.totalprice"/></th>
						<th style="display:none">TP</th>
						</s:else>
					</s:else>
					<th></th> <!-- for making better  view of request qoute message-->
				</tr>
			</thead>
			<tbody style="position:relative;">
				<s:set var="index" value="0" />
				<s:iterator id="order_table" value="shippingOrder.rateList" status="rowstatus">	
				<tr style="position:relative;">
				
				<td>
				<!-- <input class="dataTable-checkbox" type="checkbox" id="check_value<s:property value='id' />" name="check_uncheck"  style="margin: 0 0 0 4px" value 	= "<s:property value='shippingOrder.rateList[#rowstatus.index].loginURL'  />"/> -->
				<s:checkbox cssClass="dataTable-checkbox"  name="check_uncheck"  cssStyle="margin: 0 0 0 4px" fieldValue="%{id}" />
				<s:hidden  name="id_value%{id}" value="%{#rowstatus.index}" />
				<s:hidden  name="check_value%{id}" value="%{loginURL}" />
				<s:hidden id="shipmentCarrierId%{id}" value="%{carrierId}"/>
				</td>
				
				
				<td><s:property value="id" /></td>
				<td><s:property value="carrierName" /></td>
				<td><s:property value="serviceName" /></td>
				<td>
					
					<s:if test="%{transitDaysMin > 0 && transitDaysMin != transitDays}">
					<s:property value="transitDaysMin" /> to 
					</s:if>
					<s:if test="%{transitDays == 0}">
               			<s:property value="" />
          			 </s:if>
          			 <s:else>
					<s:property value="transitDays" />
					</s:else>
				</td>
				<td style="text-align:left"><s:property value="billWeight" /></td>
				<td><s:property value="chargeCurrencyCode"/></td>
  				<s:if test="%{#request.BillToType!=null}">
				<td style="text-align:right"><s:property value="%{#request.BillToType}"/></td>
				<td style="display:none"><s:property value="%{#request.BillToType}"/></td>
				</s:if>
				<s:else>
				<%-- <s:if test="%{#session.ADMIN_USER != null}">  --%>
				<s:if test="%{#session.ADMIN_USER != null || #session.ROLE.contains('sysadmin') || #session.ROLE.contains('busadmin')}"> 
					<td style="position:relative;"> 
					<div style="position:relative;" class="popup_div">
						<div class="total_label" index="<s:property value="%{#index}"/>" id="top_<s:property value="%{#index}"/>"><b><label><mmr:message messageId="label.ratelist.total"/> :</label> 
						<s:label name="curr" value="%{#session.LocalCurrencySymbol}"/>
			<s:text name="format.customMoney" ><s:param name="value" value="%{totalCostLocalCurrency}" /></s:text> : 
						
       		<s:label name="curr" value="%{#session.LocalCurrencySymbol}"/>
			<s:text name="format.customMoney" ><s:param name="value" value="%{totalChargeLocalCurrency}" /></s:text></b></div>
													<div class="popup_text"
														id="popup_<s:property value="%{#index}"/>">
														<s:iterator value="charges">
														
															<s:property value="%{name}" />:
															<s:if test="%{#session.ADMIN_ROLE.contains('sysadmin')}"><s:label name="curr"
																value="%{#session.LocalCurrencySymbol}" />
															<s:text name="format.customMoney">
																<br />
																<s:param name="value" value="%{tariffInLocalCurrency}" />
															</s:text> :</s:if><s:label name="curr"
																value="%{#session.LocalCurrencySymbol}" />
															<s:text name="format.customMoney">
																<br />
																<s:param name="value" value="%{costInLocalCurrency}" />
															</s:text> :<s:label name="curr"
																value="%{#session.LocalCurrencySymbol}" />
															<s:text name="format.customMoney">
																<br />
																<s:param name="value" value="%{chargeInLocalCurrency}" />
															</s:text>
															<br />
														</s:iterator>

														<s:set var="markupTypeText"
															value="%{shippingOrder.rateList[#rowstatus.index].markupTypeText}" />
														<%-- <s:if test="#markupTypeText.equals('Flat')">
															<s:property value="%{markupTypeText}"/> :<s:label name="curr" value="%{#session.LocalCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="%{markupFlat}" /></s:text>
 --%><%-- <s:if test="#markupTypeText.equals('Flat')">  --%>
															 <%-- <s:property value="%{markupTypeText}"/> --%> <%-- <mmr:message messageId="add.label.flat" /> :<s:label name="curr" value="%{#session.LocalCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="%{markupFlat}" /></s:text> --%>
														<%-- </s:if> --%>

														<%-- <s:else> --%>

															<%-- <s:property value="%{markupTypeText}" /> : <s:property
																value="%{markupPercentage}" />%	 --%>
																<%-- <s:property value="%{markupTypeText}" /> --%>  <%-- <mmr:message messageId="add.label.markup" /> : <s:property
																value="%{markupPercentage}" />%				 --%>

				        <%-- </s:else> --%>
				        
			        <mmr:message messageId="add.label.flat" /> :<s:label name="curr" value="%{#session.LocalCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="%{markupFlat}" /></s:text>
				         								<s:if test="#markupTypeText.equals('Markup')">
														  <s:property value="%{markupTypeText}" />  : <s:property
																value="%{markupPercentage}" />%		
																</s:if>
																<s:else>
																	 <s:property value="%{markupTypeText}" />  : <s:property
																value="%{markupPercentage}" />%	
															</s:else>		
 <s:if test="%{#session.ADMIN_ROLE.contains('sysadmin')}">
  <s:if test="%{businessMarkup == true}">
   <br/>Applied BM :
   <s:if test="%{BMType == 0}">
    <mmr:message messageId="add.label.flat" /> :  <s:label name="curr" value="%{#session.LocalCurrencySymbol}"/>
    <s:text name="format.customMoney" ><s:param name="value" value="%{BMValue}" /></s:text>
   </s:if>
   <s:elseif test="%{BMType == 1}">
    Mark-up :<s:property value="getText('{0,number,#,##0}',{BMValue})"/> %  
   </s:elseif>
   <s:elseif test="%{BMType == 2}">
    Mark-down :<s:property value="getText('{0,number,#,##0}',{BMValue})"/> % 
   </s:elseif>
  </s:if>
 </s:if>

														<div class="closebtn"></div>
													</div>


												</div>	
					</td>
					<td style="display:none"> <s:property value="%{totalCost}"/>:<s:property value="%{total}"/></td> 
				</s:if>
				<s:else>
				    <td style="position:relative;"> 
					<div style="position:relative;" class="popup_div">
						<div class="total_label" index="<s:property value="%{#index}"/>" id="top_<s:property value="%{#index}"/>"><b><label><mmr:message messageId="label.ratelist.total" /> :</label> 
						<%-- <s:text name="format.money" ><s:param name="value" value="%{total}" /></s:text> --%>
						<s:text name="format.customMoney" ><s:param name="value" value="%{totalChargeLocalCurrency}" /></s:text></b></div>
						<div class="popup_text" id="popup_<s:property value="%{#index}"/>">
							<s:iterator  value="charges">
							<s:property value="%{name}"/> 		<s:set var="tariff" value="%{customerTarrifRate}"></s:set>
       <s:set var="charge" value="%{charge}"></s:set>
       <s:if test="#tariff > charge">
       :<%-- <s:text name="format.money"><br/><s:param name="value" value="tariff" /></s:text>  --%>
       <s:label name="curr" value="%{#session.LocalCurrencySymbol}" />
       <s:text name="format.customMoney">
       	<br />
       	<s:param name="value" value="%{tariffInLocalCurrency}" />
       </s:text>
       </s:if>
       :<s:text name="format.money" ><br/><s:param name="value" value="%{charge}" /></s:text> <br/>
							</s:iterator>
						<div class="closebtn"></div>
						</div>
						<td style="display:none"><s:property value="%{total}"/></td>
				</s:else>		  		  
					
				</s:else>
				<!-- <td> --> 
				<td style="white-space: nowrap"> 
     				<s:if test="%{totalCost==0}" >
      					<a style="text-decoration:none; color:red; size:12px; font-weight:bold" href="javascript: sendCustomerEmail()" title="Email Quote">Request Quote</a>
     				</s:if>
    			</td>		
			</tr>
			<s:set var="index" value="#index+1" />
			</s:iterator>
		</tbody>
	</table>
	<div class="grid_table_body">
  <div class="content_table">&nbsp;</div>
  </div>
	<s:hidden name="shippingOrder.rateIndex" />
	</div>
	</div>

	<div id="rates_res_tbl_end"></div>	
</s:form>
</div>
</div>

	

	</body>
</html>