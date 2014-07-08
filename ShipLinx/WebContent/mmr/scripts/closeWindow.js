var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();

var div_name='';
var checked = false;

function getElementsByClassName(className) {
  var found = [];
  var elements = document.getElementsByTagName("*");
  for (var i = 0; i < elements.length; i++) {
    var names = elements[i].className.split(' ');
    for (var j = 0; j < names.length; j++) {
      if (names[j] == className) found.push(elements[i]);
    }
  }
  return found;
}


function checkUncheck(cname)
	{
		var aa = getElementsByClassName(cname);

		if(checked == false)
	    {
	    	checked = true;
	    }
	    else
	    {
	        checked = false;
	    }
	    for(var i=0; i < aa.length; i++)
        {
        	aa[i].checked = checked;
        }
	}
	
function atleastOneChecked(cname)
{
	var count =0;
	var aa = getElementsByClassName(cname);
	for(var i=0; i < aa.length; i++)
    {
 		if(aa[i].checked == true)
      	count ++;
    }
    if(count==0)
    {
    	alert("Please select at least one Invoice item.");
    }
    else
    {
		//Call the action for Sending the Notification Email for the selected Invoice Items.    
    	if(confirm("Would you like to send email notification to customers for selected invoices?"))
    	{
    		document.searchInvoiceForm.action = "sendNotification.email.action";
	 		document.searchInvoiceForm.submit();
	 	}
    }
}




function update_packagetype2(packType, namespace){
		ajax_Pack_Function=ajaxFunction();
		ajax_Pack_Function.onreadystatechange =function()
		  {
		//	alert("55");
			//alert(navigator.appName);
			   if(ajax_Pack_Function.readyState==4)
				{
				reponse=ajax_Pack_Function.responseText;
				js_stateid=document.getElementById("additionalServices");
				js_stateid.innerHTML= reponse;
				}
		  }
		 //alert("66");
			url=namespace+"/shipment.additionalservices.action?type="+packType;
			ajax_Pack_Function.open("GET",url,true);
			ajax_Pack_Function.send(this);
		//("77");
	}
	
		 function ajaxFunction()
  {
  	var xmlHttp;
  	try
    {
    // Firefox, Opera 8.0+, Safari
    xmlHttp=new XMLHttpRequest();
    }
  	catch (e)
    {
    // Internet Explorer
    try
      {
      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
      }
    catch (e)
      {
      try
        {
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
      catch (e)
        {
        alert("Your browser does not support AJAX!");
        return false;
        }
      }
    }
	return xmlHttp;
  }    
function putWindowStatus(windowStatus) {
	ajax_close_window_status=ajaxCallingFunction();
	ajax_close_window_status.onreadystatechange=function()
	{
	   if(ajax_close_window_status.readyState==4)
		{
			reponse=ajax_close_window_status.responseText;
			js_testAjax=document.getElementById("testAjax");
			js_testAjax.innerHTML= "";

		}
	}
	url="close.window.status.action?name="+windowStatus;
	//param="objName=ref_state&country_id="+country_id;
	ajax_close_window_status.open("GET",url,true);
	ajax_close_window_status.send(this);
} // End function showState()
	        
   function ajaxCallingFunction()
  {
  	var xmlHttpWindow;
  	try
    {
    // Firefox, Opera 8.0+, Safari
    xmlHttpWindow=new XMLHttpRequest();
    }
  	catch (e)
    {
    // Internet Explorer
    try
      {
      xmlHttpWindow=new ActiveXObject("Msxml2.XMLHTTP");
      }
    catch (e)
      {
      try
        {
        xmlHttpWindow=new ActiveXObject("Microsoft.XMLHTTP");
        }
      catch (e)
        {
        alert("Your browser does not support AJAX!");
        return false;
        }
      }
    }
	return xmlHttpWindow;
  }      

function changeStyle(){
	d=document.getElementById("right_window_first");
	d.style.display="none";
	d.style.height="0%";
	d.style.width="0%";
	d1=document.getElementById("layoutId_first");
	d1.style.width="95%";
	d2=document.getElementById("layoutId_second");
	d2.style.width="5%";
	d3=document.getElementById("right_window_second");
	d3.style.display="block";
		d3.style.height="100%";
		d3.style.width="100%";

	d4=document.getElementById("addressInfo");
	if(d4!=null){
		d4.style.display="none";
		d4.style.height="0%";
		d4.style.width="0%";
	}


	
}

function changeWindowStyleWithHideStatus(){
	d=document.getElementById("right_window_first");
	d.style.display="none";
	d.style.height="0%";
	d.style.width="0%";
	d1=document.getElementById("layoutId_first");
	d1.style.width="95%";
	d2=document.getElementById("layoutId_second");
	d2.style.width="5%";
	d3=document.getElementById("right_window_second");
	d3.style.display="block";
	d3.style.height="100%";
	d3.style.width="100%";

	putWindowStatus('hide');
}

function changeWindowStyleWithShowStatus(){
	d=document.getElementById("right_window_first");
	d.style.display="block";
	d.style.height="100%";
	d.style.width="100%";
	d1=document.getElementById("layoutId_first");
	d1.style.width="70%";
	d2=document.getElementById("layoutId_second");
	d2.style.width="30%";
	d3=document.getElementById("right_window_second");
	d3.style.display="none";
	d3.style.height="0%";
	d3.style.width="0%";
	putWindowStatus('show');
}

/*function getOnloadStatus(status,packType,namespace, htag, stag)
{
	if(status!=null && status=='hide'){
		//changeStyle();
	}

//update_packagetype2(packType, namespace);

loadHelpDiv(htag,stag);
}

function getOnloadStatusForShipping(status,packType,namespace, htag)
{	
	if(status!=null && status=='hide'){
		//changeStyle();
	}

//update_packagetype2(packType, namespace);

loadHelpDivForShipping(htag);
}
*/

function displaySearchAddressFrom(url){
	d=document.getElementById("right_window_first");
	d.style.display="none";
	d.style.height="0%";
	d.style.width="0%";

	d1=document.getElementById("layoutId_first");
	d1.style.width="70%";
	d2=document.getElementById("layoutId_second");
	d2.style.width="30%";
	d3=document.getElementById("right_window_second");
	d3.style.display="none";
	d3.style.height="0%";
	d3.style.width="0%";
	d4=document.getElementById("addressInfo");
	d4.style.display="block";
	d4.style.height="100%";
	d4.style.width="100%";
	ajax_close_window_status=ajaxCallingFunction();
	ajax_close_window_status.onreadystatechange=function()
	{
	   if(ajax_close_window_status.readyState==4)
		{
			reponse=ajax_close_window_status.responseText;
			js_testAjax=document.getElementById("addressInfo");
			js_testAjax.innerHTML= reponse;
		}
	}
	//param="objName=ref_state&country_id="+country_id;
	ajax_close_window_status.open("GET",url,true);
	ajax_close_window_status.send(this);
}




function changeShippingWindowStyleWithHideStatus(){
	d=document.getElementById("right_window_first");
	d.style.display="none";
	d.style.height="0%";
	d.style.width="0%";

	var isAddressSearch=document.getElementById("isAddressSearch");
	if(isAddressSearch!=null && isAddressSearch.value=='0'){
		d1=document.getElementById("layoutId_first");
		d1.style.width="70%";
		d2=document.getElementById("layoutId_second");
		d2.style.width="30%";

		d3=document.getElementById("right_window_second");
		d3.style.display="none";
		d3.style.height="0%";
		d3.style.width="0%";
		d4=document.getElementById("addressInfo");
		d4.style.display="block";
		d4.style.height="100%";
		d4.style.width="100%";

	}else{
		d1=document.getElementById("layoutId_first");
		d1.style.width="95%";
		d2=document.getElementById("layoutId_second");
		d2.style.width="5%";
		d3=document.getElementById("right_window_second");
		d3.style.display="block";
		d3.style.height="100%";
		d3.style.width="100%";
		d4=document.getElementById("addressInfo");
		d4.style.display="none";
		d4.style.height="0%";
		d4.style.width="0%";
	}

	putWindowStatus('hide');
}

function changeShippingWindowStyleWithShowStatus(){
	d=document.getElementById("right_window_first");
	d.style.display="block";
	d.style.height="100%";
	d.style.width="100%";

	d1=document.getElementById("layoutId_first");
	d1.style.width="70%";
	d2=document.getElementById("layoutId_second");
	d2.style.width="30%";

	d3=document.getElementById("right_window_second");
	d3.style.display="none";
	d3.style.height="0%";
	d3.style.width="0%";

		d4=document.getElementById("addressInfo");
		d4.style.display="none";
		d4.style.height="0%";
		d4.style.width="0%";

	putWindowStatus('show');
}


function selectAddress(url,winStatus){

	if(winStatus!=null && winStatus=='show'){

		d=document.getElementById("right_window_first");
		d.style.display="block";
		d.style.height="100%";
		d.style.width="100%";

		d1=document.getElementById("layoutId_first");
		d1.style.width="70%";
		d2=document.getElementById("layoutId_second");
		d2.style.width="30%";

		d3=document.getElementById("right_window_second");
		d3.style.display="none";
		d3.style.height="0%";
		d3.style.width="0%";

		d4=document.getElementById("addressInfo");
		d4.style.display="none";
		d4.style.height="0%";
		d4.style.width="0%";

	}else{
		d4=document.getElementById("addressInfo");
		d4.value="";
		d4.innerHTML="";
		d4.style.display="none";
		d4.style.height="0%";
		d4.style.width="0%";

		d3=document.getElementById("right_window_second");
		d3.style.display="block";
		d3.style.height="100%";
		d3.style.width="100%";

		d5=document.getElementById("layoutId_first");
		d5.style.width="95%";
		d6=document.getElementById("layoutId_second");
		d6.style.width="5%";
	}
	document.getElementById("isAddressSearch").value=1;
/*	var urlFromAddressObj=document.getElementById('search_customer_from_address_');
	if(urlFromAddressObj!=null){
		url=urlFromAddressObj.href;
		urlFromAddressObj.href="#";
	}
	alert('2');
	var urlToAddressObj=document.getElementById('search_customer_to_address_');
	if(urlToAddressObj!=null){
		url=urlToAddressObj.href;
	urlToAddressObj.href="#";
	}*/
	//if(urlFromAddressObj!=null ){
		var index=url.lastIndexOf('fromAddress');
		if(index>0){
			url=url.replace('set.shippingAddress','select.customer.from.address');
			setSelectedFromAddress(url);
		}


	//}
	
	//if(urlToAddressObj!=null){
		var index=url.lastIndexOf('toAddress');
		if(index>0){
			url=url.replace('set.shippingAddress','select.customer.to.address');
			setSelectedToAddress(url);
		}


	//}

}

	function track(orderId){
		
		var url=contextPath+"/track.shipment.action?id="+orderId;
		window.open(url,"_blank","directories=no, status=no,width=1400, height=870,top=0,left=0");
	}

function setSelectedFromAddress(url){
	ajax_close_window_status=ajaxCallingFunction();
	ajax_close_window_status.onreadystatechange=function()
	{
	   if(ajax_close_window_status.readyState==4)
		{
			reponse=ajax_close_window_status.responseText;
			js_testAjax=document.getElementById("bottom_table2");
			js_testAjax.innerHTML= reponse;
		}
	}
	//param="objName=ref_state&country_id="+country_id;
	ajax_close_window_status.open("GET",url,true);
	ajax_close_window_status.send(this);


}

function setSelectedToAddress(url){
	ajax_close_window_status=ajaxCallingFunction();
	ajax_close_window_status.onreadystatechange=function()
	{
	   if(ajax_close_window_status.readyState==4)
		{
			reponse=ajax_close_window_status.responseText;
			js_testAjax=document.getElementById("shipTo");
			js_testAjax.innerHTML= reponse;
		}
	}
	//param="objName=ref_state&country_id="+country_id;
	ajax_close_window_status.open("GET",url,true);
	ajax_close_window_status.send(this);
}

function searchOrderFromAddress(type){
		var url="";
		var contact= document.getElementById("contact").value;
		var value= document.getElementById("addressId").value;
		var provinceValue= document.getElementById("state").value;
		var postalCodeValue= document.getElementById("postalCode").value;
		var cityValue= document.getElementById("city").value;
		var consigneeNameValue= document.getElementById("consigneeName").value;
		var idValue= document.getElementById("id").value;
		
		if(type=="clear"){
			url="search.customer.from.address.action?searchId="+''+"&type=fromAddress&contact="+''+"&state="+''+"&postalCode="+''+"&city="+''+"&consigneeName="+''+"&id="+'';
		}
		else if(type=="fromAddress"){
			url="search.customer.from.address.action?searchId="+value+"&type=fromAddress&contact="+contact+"&state="+provinceValue+"&postalCode="+postalCodeValue+"&city="+cityValue+"&consigneeName="+consigneeNameValue+"&id="+idValue;
		}else{
			url="search.customer.to.address.action?searchId="+value+"&type=toAddress&contact="+contact+"&state="+provinceValue+"&postalCode="+postalCodeValue+"&city="+cityValue+"&consigneeName="+consigneeNameValue+"&id="+idValue;
		}
		displaySearchAddressFrom(url);
}


function jsClockTimeZone(){

var weekday=new Array(7);
weekday[0]="Sunday";
weekday[1]="Monday";
weekday[2]="Tuesday";
weekday[3]="Wednesday";
weekday[4]="Thursday";
weekday[5]="Friday";
weekday[6]="Saturday";


  // Copyright 1999 - 2001 by Ray Stott
  // OK to use if this copyright is included
  // Script available at http://www.crays.com/jsc
  var TimezoneOffset = +5.50 // adjust for time zone
  var localTime = new Date()
  var ms = localTime.getTime() 
             + (localTime.getTimezoneOffset() * 60000)
             + TimezoneOffset * 3600000
  var time =  new Date(ms) 
  var hour = time.getHours() 
  var minute = time.getMinutes()
  var second = time.getSeconds()
    var day = time.getDay();
    var date = time.getDate();
    var month = time.getMonth()+1;
    var year = time.getFullYear();
	
  var temp = "" + ((hour > 12) ? hour - 12 : hour)
  if(hour==0) temp = "12"
  if(temp.length==1) temp = " " + temp
  temp += ((minute < 10) ? ":0" : ":") + minute
  temp += ((second < 10) ? ":0" : ":") + second
  temp += (hour >= 12) ? " PM" : " AM"

var currentDate="";
    currentDate+=date+"/";
  currentDate+=month+"/";
  currentDate+=year+", ";
  currentDate+=weekday[day]+",";

  document.getElementById('clockFormTimeZone').innerHTML  = currentDate+temp
	  
  setTimeout("jsClockTimeZone()",1000);
  }
  
 // function loadHelpDiv(htag,stag)
 // {
  //	var support_div = document.getElementById("support_txt");
  //	support_div.innerHTML = stag;
  	
  //	var help_div = document.getElementById("help_txt");
  //	help_div.innerHTML = htag;
  //	alert("The htag to be loaded is:"+htag);
  //  alert("The stag to be loaded is:"+stag);
 // }
  /*
   function loadHelpDivForShipping(htag)
  {
  	//alert(htag);
  	var help_div = document.getElementById("ship_help_txt");
  	help_div.innerHTML = htag;  	
  //	alert("The htag to be loaded is:"+htag);
  //  alert("The stag to be loaded is:"+stag);
  }
  */
  function selectDate(datec,id)
	{
		//alert(datec);
		//alert(id);
		//myPos = findPos(document.getElementById(datec));
		//x = myPos[0];
		//y = myPos[1];
		
		Calendar.setup({
	        inputField     :    datec,     // id of the input field
	        ifFormat       :    "%Y-%m-%d",      // format of the input field
	        button         :    id,  // trigger for the calendar (button ID)
	        align          :    "bl",           // alignment (defaults to "Bl")
	        singleClick    :    true
	       // position	   :    [x-70,y+20]		//position the calendar on load based on the location of 'datec' element 
	    });
	}
	
  function getAddressSuggestFrom() {	
	  
		var form = document.userform;
	  	var selectedCountry = form.elements['shippingOrder.fromAddress.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
		document.getElementById("loading-img-from").style.display = 'block';
		var error = false;
		form.elements['shippingOrder.fromAddress.postalCode'].value = (form.elements['shippingOrder.fromAddress.postalCode'].value).replace(/\s/g,"");
			if((form.elements['shippingOrder.fromAddress.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['shippingOrder.fromAddress.postalCode'].value) == null) {
					alert('(Ship From) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['shippingOrder.fromAddress.countryCode'].value = 'US';
				}
			}
			else if((form.elements['shippingOrder.fromAddress.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['shippingOrder.fromAddress.postalCode'].value) == null) {
						alert('(Ship From) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['shippingOrder.fromAddress.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-from").style.display = 'none';
			}
			showShipFromState();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['shippingOrder.fromAddress.city'].value = reponse;
						showShipFromState();
						document.getElementById("loading-img-from").style.display = 'none';
						}
				  }
				  fromPostalCode = document.getElementById('fromPostalCode').value;
				  fromCountry = form.elements['shippingOrder.fromAddress.countryCode'].value
					param="postalCode="+fromPostalCode+"&countryCode="+fromCountry+"&type=from";;
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+fromCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-from").style.display = 'none';
			}
	}

  function getAddressSuggestTo() {	
		var form = document.userform;
	  	var selectedCountry = form.elements['shippingOrder.toAddress.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-to").style.display = 'block';
		var error = false;
		form.elements['shippingOrder.toAddress.postalCode'].value = (form.elements['shippingOrder.toAddress.postalCode'].value).replace(/\s/g,"");
			if((form.elements['shippingOrder.toAddress.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['shippingOrder.toAddress.postalCode'].value) == null) {
					alert('(Ship To) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['shippingOrder.toAddress.countryCode'].value = 'US';
				}
			}
			else if((form.elements['shippingOrder.toAddress.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['shippingOrder.toAddress.postalCode'].value) == null) {
						alert('(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['shippingOrder.toAddress.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-to").style.display = 'none';
			}
			showShipToState();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['shippingOrder.toAddress.city'].value = reponse;
						showShipToState();
						document.getElementById("loading-img-to").style.display = 'none';
						}
				  }
				  toPostalCode = document.getElementById('toPostalCode').value;
				  toCountry = form.elements['shippingOrder.toAddress.countryCode'].value
					param="postalCode="+toPostalCode+"&countryCode="+toCountry+"&type=to";
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-to").style.display = 'none';
			}
	}
	
	 function getAddressSuggestCustomer() {
		var form = document.customerform;
	  	var selectedCountry = form.elements['customer.address.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-cust").style.display = 'block';
		var error = false;
		form.elements['customer.address.postalCode'].value = (form.elements['customer.address.postalCode'].value).replace(/\s/g,"");
			if((form.elements['customer.address.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['customer.address.postalCode'].value) == null) {
					alert('Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['customer.address.countryCode'].value = 'US';
				}
			}
			else if((form.elements['customer.address.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['customer.address.postalCode'].value) == null) {
						alert('Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['customer.address.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-cust").style.display = 'none';
			}
			showStateCustomer();	
			if(!error){
				//alert("Not error 1");
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						//alert("Not error 2");
						reponse=ajax_City.responseText;
						//alert(reponse);
						form.elements['customer.address.city'].value = reponse;
						showStateCustomer();
						document.getElementById("loading-img-cust").style.display = 'none';
						}
				  }
				  toPostalCode = document.getElementById('custPostalCode').value;
				  toCountry = form.elements['customer.address.countryCode'].value
					param="postalCode="+toPostalCode+"&countryCode="+toCountry;
					url=contextPath+"/getAddressSuggestCustomer.action?" + param;
					//alert(url);
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-cust").style.display = 'none';
			}
	}
	
	 function getAddressSuggestNewAddress() {
		var form = document.addressform;
	  	var selectedCountry = form.elements['address.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-add").style.display = 'block';
		var error = false;
		form.elements['address.postalCode'].value = (form.elements['address.postalCode'].value).replace(/\s/g,"");
			if((form.elements['address.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['address.postalCode'].value) == null) {
					alert('Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['address.countryCode'].value = 'US';
				}
			}
			else if((form.elements['address.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['address.postalCode'].value) == null) {
						alert('Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['address.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-add").style.display = 'none';
			}
			showStateCustomer();	
			if(!error){
				//alert("Not error 1");
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						//alert("Not error 2");
						reponse=ajax_City.responseText;
						//alert(reponse);
						form.elements['address.city'].value = reponse;
						showStateCustomer();
						document.getElementById("loading-img-add").style.display = 'none';
						}
				  }
				  toPostalCode = document.getElementById('addPostalCode').value;
				  toCountry = form.elements['address.countryCode'].value
					param="postalCode="+toPostalCode+"&countryCode="+toCountry;
					url=contextPath+"/getAddressSuggestNewAddress.action?" + param;
					//alert(url);
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-add").style.display = 'none';
			}
	}
	
	 function getAddressSuggestPickup() {	
	  
		var form = document.searchpickupform;
	  	var selectedCountry = form.elements['pickup.address.countryCode'].value;
	  	//alert(selectedCountry);
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
		document.getElementById("loading-img-from").style.display = 'block';
		var error = false;
		form.elements['pickup.address.postalCode'].value = (form.elements['pickup.address.postalCode'].value).replace(/\s/g,"");
			if((form.elements['pickup.address.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['pickup.address.postalCode'].value) == null) {
					alert('(Ship From) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['pickup.address.countryCode'].value = 'US';
				}
			}
			else if((form.elements['pickup.address.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['pickup.address.postalCode'].value) == null) {
						alert('(Ship From) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['pickup.address.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-from").style.display = 'none';
			}
			showStatePickup();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['pickup.address.city'].value = reponse;
						//alert("Inside Ajax");
						showStatePickup();
						document.getElementById("loading-img-from").style.display = 'none';
						}
				  }
				  fromPostalCode = document.getElementById('fromPostalCode').value;
				  fromCountry = form.elements['pickup.address.countryCode'].value;
					param="postalCode="+fromPostalCode+"&countryCode="+fromCountry+"&type=pickup";
					//alert("Param:::"+param);
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+fromCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-from").style.display = 'none';
			}
	}

  function showShipFromState() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("stateid");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox');
			  url=contextPath+"/shipFrom.listFromProvience.action?value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);
				
				// To empty cache for autocomplete
				$('#fromcity').flushCache();
				$('#fromPostalCode').flushCache();
				
				
		} // End function showState()
		
		 function showStateCustomer() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("stateid");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox');
			  url=contextPath+"/customer.listProvince.action?value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);
		} // End function showState()
		
		 function showStatePickup() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("stateidP");
					//alert(js_stateid);
					//alert(reponse);
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBoxPickup').value;
			 // alert(firstBox);
			  url=contextPath+"/pickup.listProvince.action?value="+firstBox;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);
		} // End function showState()
		        
		  function showShipToState() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("stateid2");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox2');
			  url=contextPath+"/shipTo.listToProvience.action?value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);

				// To empty cache for autocomplete
				$('#tocity').flushCache();
				$('#toPostalCode').flushCache();
		} // End function showState() 		
		
		function getAddressSuggestBuyerTo() {	
		var form = document.userform;
	  	var selectedCountry = form.elements['shippingOrder.customsInvoice.buyerAddress.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-to").style.display = 'block';
		var error = false;
		form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value = (form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value).replace(/\s/g,"");
			if((form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value) == null) {
					alert('(Ship To) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['shippingOrder.customsInvoice.buyerAddress.countryCode'].value = 'US';
				}
			}
			else if((form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['shippingOrder.customsInvoice.buyerAddress.postalCode'].value) == null) {
						alert('(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['shippingOrder.customsInvoice.buyerAddress.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-to").style.display = 'none';
			}
			showBuyerState();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['shippingOrder.customsInvoice.buyerAddress.city'].value = reponse;
						showBuyerState();
						document.getElementById("loading-img-to").style.display = 'none';
						}
				  }
				  buyerPostalCode = document.getElementById('buyerPostalCode').value;
				  toCountry = form.elements['shippingOrder.customsInvoice.buyerAddress.countryCode'].value
				  param="postalCode="+buyerPostalCode+"&countryCode="+toCountry+"&type=buyer";
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-to").style.display = 'none';
			}
	}
		
		  function showBuyerState() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("buyerProvince");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox3');
			  url=contextPath+"/shipTo.listToProvience.action?type=buyer&value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);

				// To empty cache for autocomplete
				/*$('#buyercity').flushCache();
				$('#buyerPostalCode').flushCache();*/
		} // End function showState() 
		
	function getAddressSuggestBillTo() {	
		var form = document.userform;
	  	var selectedCountry = form.elements['shippingOrder.customsInvoice.billToAddress.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-to").style.display = 'block';
		var error = false;
		form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value = (form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value).replace(/\s/g,"");
			if((form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value) == null) {
					alert('(Ship To) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['shippingOrder.customsInvoice.billToAddress.countryCode'].value = 'US';
				}
			}
			else if((form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['shippingOrder.customsInvoice.billToAddress.postalCode'].value) == null) {
						alert('(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['shippingOrder.customsInvoice.billToAddress.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-to").style.display = 'none';
			}
			showBillToState();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['shippingOrder.customsInvoice.billToAddress.city'].value = reponse;
						showBillToState();
						document.getElementById("loading-img-to").style.display = 'none';
						}
				  }
				  billToPostalCode = document.getElementById('billToPostalCode').value;
				  toCountry = form.elements['shippingOrder.customsInvoice.billToAddress.countryCode'].value
					param="postalCode="+billToPostalCode+"&countryCode="+toCountry+"&type=to";
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-to").style.display = 'none';
			}
	}
		
		  function showBillToState() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("billToProvince");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBox2');
			  url=contextPath+"/shipTo.listToProvience.action?value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);

				// To empty cache for autocomplete
				/*$('#buyercity').flushCache();
				$('#buyerPostalCode').flushCache();*/
		} // End function showState() 
		
	function getSuggestBrokerAddress() {	
		var form = document.userform;
	  	var selectedCountry = form.elements['shippingOrder.customsInvoice.brokerAddress.countryCode'].value;
	    if(selectedCountry != 'US' && selectedCountry != 'CA'){
	    	return;
	    }
	    document.getElementById("loading-img-to").style.display = 'block';
		var error = false;
		form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value = (form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value).replace(/\s/g,"");
			if((form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value).length == 5){
				var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value) == null) {
					alert('(Ship To) Postal Code should be in US format #####');
					error = true;
				}else{
					form.elements['shippingOrder.customsInvoice.brokerAddress.countryCode'].value = 'US';
				}
			}
			else if((form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value).length == 6){
					var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
					if(mask.exec(form.elements['shippingOrder.customsInvoice.brokerAddress.postalCode'].value) == null) {
						alert('(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' );
						error = true;
					}else{
						form.elements['shippingOrder.customsInvoice.brokerAddress.countryCode'].value = 'CA';
					}
				
			}else{
				alert('Postal code should be only of US or Canada');
				error = true;
				document.getElementById("loading-img-to").style.display = 'none';
			}
			showShipToStateb();			
			if(!error){
				ajax_City=ajaxFunction();
				ajax_City.onreadystatechange=function()
				  {
					   if(ajax_City.readyState==4)
						{
						reponse=ajax_City.responseText;
						form.elements['shippingOrder.customsInvoice.brokerAddress.city'].value = reponse;
						showShipToStateb();
						document.getElementById("loading-img-to").style.display = 'none';
						}
				  }
				  billToPostalCode = document.getElementById('ci.postcalCode').value;
				  toCountry = form.elements['shippingOrder.customsInvoice.brokerAddress.countryCode'].value
					param="postalCode="+billToPostalCode+"&countryCode="+toCountry+"&type=to";
					url=contextPath+"/getAddressSuggest.action?" + param;
				  //+"&country="+toCountry;
					ajax_City.open("GET",url,true);
					ajax_City.send(this);
			}
			else{
				document.getElementById("loading-img-to").style.display = 'none';
			}
	}
			
	function showShipToStateb() {
			ajax_Country=ajaxFunction();
			ajax_Country.onreadystatechange=function()
			  {
				   if(ajax_Country.readyState==4)
					{
					reponse=ajax_Country.responseText;
					js_stateid=document.getElementById("stateidb");
					js_stateid.innerHTML= reponse;
					}
			  }
			  firstBox = document.getElementById('firstBoxb');
			  url=contextPath+"/shipTo.listToProvience.action?type=broker&value="+firstBox.value;
				//param="objName=ref_state&country_id="+country_id;
				ajax_Country.open("GET",url,true);
				ajax_Country.send(this);
		} // End function showState()  
		
		
 