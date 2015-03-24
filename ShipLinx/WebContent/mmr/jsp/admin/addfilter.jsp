<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head>
<sx:head/>
    <title><s:text name="partner.form.title"/></title> 
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/style/laptop.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<style type="text/css">
		
	</style>
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
    <style type="text/css">
    .multiselectable { width:70%; display:block;   margin-left: auto;
    margin-right: auto; }
.multiselectable select, .multiselectable div { width: 280px; height: 400px; float:left; }
.multiselectable div * { display: block; margin: 0 auto; }
.multiselectable div { display: inline; }
.multiselectable .m-selectable-controls { margin-top: 3em; width: 50px; }
.multiselectable .m-selectable-controls button { margin-top: 1em; }

.white-popup {
  position: relative;
  background: #FFF;
  padding: 20px;
  width:auto;
  max-width: 250px;
  margin: 20px auto;
}


    #lean_overlay {
    position: fixed;
    z-index:100;
    top: 0px;
    left: 0px;
    height:100%;
    width:100%;
    background: #000;
    display: none;
}
.total_label:hover{ text-decoration:underline; cursor:pointer;}
.total_label{ position:relative;}
.popup_text{display:none; position:absolute;  height:auto; padding:5px 10px 5px 5px  ; width:200px; left:-30px; color:#000;	 background-color:#fff; border:1px solid #999; z-index:1000;  }

.well {
    
        display:none;
    
    }
    .fields1					{ width:200px; margin-left:5px; float:left; padding:1px 0px;  font-size:14px;}
.hide1					{ display: none;}
</style>

	</head> 

<body> 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"      type="text/javascript"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.autocomplete.js"></script>
 <link rel="stylesheet" href="http://getbootstrap.com/dist/css/bootstrap.min.css" />


    <!-- Font Awesome icons -->
    <link rel="stylesheet" href="http://weloveiconfonts.com/api/?family=fontawesome">
    <style>
        [class*="fontawesome-"]:before {
          font-family: 'FontAwesome', sans-serif;
        }
    </style>

    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

    <!-- jQuery Popup Overlay -->
    <script src="<%=request.getContextPath()%>/mmr/scripts/jquery.popupoverlay.js"></script>

<script type="text/javascript">
(function($){$.fn.extend({leanModal:function(options){var defaults={top:100,overlay:0.5,closeButton:null};var overlay=$("<div id='lean_overlay'></div>");$("body").append(overlay);options=$.extend(defaults,options);return this.each(function(){var o=options;$(this).click(function(e){var modal_id=$(this).attr("href");$("#lean_overlay").click(function(){close_modal(modal_id)});$(o.closeButton).click(function(){close_modal(modal_id)});var modal_height=$(modal_id).outerHeight();var modal_width=$(modal_id).outerWidth();
$("#lean_overlay").css({"display":"block",opacity:0});$("#lean_overlay").fadeTo(200,o.overlay);$(modal_id).css({"display":"block","position":"fixed","opacity":0,"z-index":11000,"left":50+"%","margin-left":-(modal_width/2)+"px","top":o.top+"px"});$(modal_id).fadeTo(200,1);e.preventDefault()})});function close_modal(modal_id){$("#lean_overlay").fadeOut(200);$(modal_id).css({"display":"none"})}}})})(jQuery);
</script>
<%--  <script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/multiselectable.js"></script>--%>

<script>
 
   $(document).ready(function () {
	   $('.navi_icon').click(function(){
		    $('.navigation ul').slideToggle();

		  });
	   $('#sel').click(function(){
		   alert("dsf");
		   $('#sel').css("display","block");
		    $('#sel').slideToggle();

		  });
	    $('#sel0').popup({
	        
	        type: 'tooltip',
	       vertical: 'top',
	        transition: '0.3s all 0.1s',
	       tooltipanchor: $('#sel0')
	    });
	    

		$('#actiondown').click(function(event){
			event.preventDefault();
			$('#actionup').css('display','block');
			$('#actiondown').css('display','none');
			$('#actionmenu').css('display','block');
		});
		$('#actionup').click(function(event){
			event.preventDefault();
			$('#actionup').css('display','none');
			$('#actiondown').css('display','block');
			$('#actionmenu').css('display','none');
		});
		$('#sample1').dataTable(); 
		$("#check_all").click(function(){
			var temp=$(".dataTable-checkbox").attr("checked");
			if(temp == null){
		    $(".dataTable-checkbox").attr("checked","checked");
			}
			else{
			$(".dataTable-checkbox").removeAttr("checked");
			}
		});
		$('table').wrap('<div class="grid_table_body"></div>');
		$("#sample1_length").wrap("<div class='box-cont1'></div>");
		$("div.box-cont1").each(function() {
		  $(this).append($(this).next());
		});
		$(".dataTables_info").wrap("<div class='box-cont2'></div>");
		$("div.box-cont2").each(function() {
		  $(this).append($(this).next());
		});
			

	});
 </script>
 	
 	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

 <script type="text/javascript">
 
 var popupid="";
 var cou=0;
 $(window).load(function() {
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
	$( document ).on( "dblclick", "#lecturers option", function () 
			{
				var element = $("#lecturers option:selected");
				var value = element.val();
				var txt=element.text();
		  
				element.remove();
				var values = value.split(";")
				
				$("#selected_lecturers").append('<option value="' + value + '">' + txt  + '</option>');
				
			});
			$( document ).on( "dblclick", "#selected_lecturers option", function () 
			{
				var element = $("#selected_lecturers option:selected");
				var value = element.val();
				element.remove();
				var txt=element.text();
				var values = value.split(";")
				
				$("#lecturers").append('<option value="' + value + '">' +txt+ '</option>');
				
			});
			
			
			
		
 });
 
 $('.look').click(function(){
	    $(this).hide();
	    $(this).siblings('.touch').show().focus().focusout(function(){
	        $(this).hide();
	        $(this).siblings('.look').show();
	    });
	});



 
 var custo="";
 var Selecttxt=[];
 var Selectval=[];
 function add(){
	 var element = $("#lecturers option:selected");
	 var n=element.length;
	// alert(n);
	 
 }

 function add1(){
	 
/* 	 var element1 = $("#lecturers option:selected");
	 var n=element1.length;
	 for(var i=0;i<n;i++){
		 
		 var value = element1[i].val();
		 alert(value);
		 var txt=element1[i].text();
		 element1[i].remove();
		 var values = value.split(";")
		 $("#selected_lecturers").append('<option value="' + value + '">' + txt  + '</option>');
		 } */
		 var selectedArray = new Array();
		 var selectedtxt = new Array();
		 
	 

	  var selObj = document.getElementById('lecturers');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	    if (selObj.options[i].selected) {
	      selectedArray[count] = selObj.options[i].value;
	      selectedtxt[count]=selObj.options[i].text;
	      //selObj.remove(i);
	      count++;
	    }
	  }
	  
	  
	  
	    var select_element = document.getElementById("lecturers");
        var options = select_element.options;
        var i = options.length;
        while (i--) {
            var current = options[i];
            if (current.selected) {
                // Do something with the selected option
                current.parentNode.removeChild(current);
            }
        }
        
        for (i=0; i<selectedArray.length; i++) {
        	$("#selected_lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
        }
     
 }
 function remove1(){
	/*  var element2 = $("#selected_lecturers option:selected");
	 var n=element2.length;
	 for(var i=0;i<n;i++){
	 var value = element2[i].val();
	 var txt=element2[i].text();
	 element2[i].remove();
	 var values = value.split(";")
	 $("#lecturers").append('<option value="' + value + '">' + txt  + '</option>');
	 } */
	 var selectedArray = new Array();
	 var selectedtxt = new Array();
	 
 

  var selObj = document.getElementById('selected_lecturers');
  var i;
  var count = 0;
  for (i=0; i<selObj.options.length; i++) {
    if (selObj.options[i].selected) {
      selectedArray[count] = selObj.options[i].value;
      selectedtxt[count]=selObj.options[i].text;
      //selObj.remove(i);
      count++;
    }
  }
  
  
  
    var select_element = document.getElementById("selected_lecturers");
    var options = select_element.options;
    var i = options.length;
    while (i--) {
        var current = options[i];
        if (current.selected) {
            // Do something with the selected option
            current.parentNode.removeChild(current);
        }
    }
    
    for (i=0; i<selectedArray.length; i++) {
    	$("#lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
    }
 }
 
 
 function addfilter(){
	         $('#filterdiv1').css('display','block');
	        // $('#savebut').css('display','block');
		    var country = document.getElementById("cid");
		    var branchid = document.getElementById("bid");
			var partnerid = document.getElementById("firstBox");
			var partnerval = partnerid.options[partnerid.selectedIndex].value;
			var partnertxt= partnerid.options[partnerid.selectedIndex].text;
			var countryval = country.options[country.selectedIndex].value;
			var countrytxt = country.options[country.selectedIndex].text;
			var branchval = branchid.options[branchid.selectedIndex].value;
			var branchtxt = branchid.options[branchid.selectedIndex].text;
			var elem = document.getElementById('filterdiv');
			var options=appendcustomers();
			custo=options;
			var id1="sel"+cou;
			var id2="but"+cou;
			var plid="p"+cou;
			var coid="c"+cou;
			var brid="b"+cou;
			var idd="seld"+cou;
			var ids=partnerval+","+countryval+","+branchval;
			// alert(countrytxt);
			//var html1="<div id='"+idd+"'  class=\"well\"><select multiple=\"true\" name=\"customers\" id='"+id1+"'>"+options+"</select></div>";
			var div = $("<div/>");
			div.attr("class", "rows");
			var hml="<tr id=\'r"+cou+"'><td class=\"odd1\" width=\"2%\"><input  class=\"dataTable-checkbox\" type=\"checkbox\" name=\"searchUserCheckBox\" id=\'"+ids+"' value=\'"+count+"'/>"+  
   			"</td><td style=\"text-align: left;\"><div style=\"width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis\">"+partnertxt+"</div>"+
			"<input type=\"hidden\" id='"+plid+"' value="+partnerval+" name=\"hpartner\"/></td>"+
			"<td style=\"text-align: left;\"><div style=\"width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis\">"+countrytxt+"</div>"+
			"<input type=\"hidden\" id='"+coid+"' value='"+countryval+"'/></td>"	+
			"<td style=\"text-align: left;\"><div style=\"width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis\">"+branchtxt+"</div>"+
			"<input type=\"hidden\" id='"+brid+"' value="+branchval+" name=\"hpartner\"/></td>"+
			"<td style=\"text-align: left;\"><a href=\"#sel0\" id='"+id2+"' onclick=\"showhide()\" >CUSTOMERS</a> <div id='"+idd+"'  class=\"well\"><select multiple=\"true\" name=\"customers\" id='"+id1+"'>"+options+"</select></div></td>";
			//<div class=\"fields1\"><input  type=\"checkbox\" value='"+ids+"' name=\"filterchk\"/>
		/* 	div.html("</div><div class=\"fields1\"><label>PARTNER :</label>"+
			 "<label>"+partnertxt+"</label><input type=\"hidden\" value='"+partnerval+"' id='"+plid+"'/></div><div class=\"fields1\">"+
			"<label >COUNTRY :</label><label>"+countryval+"</label><input type=\"hidden\" value='"+countryval+"' id='"+coid+"'/>"+
			"</div>	<div class=\"fields1\"><label>BRANCH :</label><label>"+branchtxt+"</label><input type=\"hidden\" value='"+branchval+"' id='"+brid+"'/></div><div  class=\"fields1\"><a href=\"#sel0\" id='"+id2+"' onclick=\"showhide()\" >CUSTOMERS</a></div>"
			+html1
			); */
			div.html(hml);
		     $("#filterdiv").append(div);
			 cou++;
					 
			movelist();
		  functionIHadToChange1();

		}
 function movelist(){
	 var ar="";
	 var selectedArray = new Array();
	 var selectedtxt = new Array();
/* 	 $("#selected_lecturers option:not(:selected)").each(function () {
		    var $this = $(this);
		    if ($this.length) {
		        var selText = $this.txt();
		        arr+=selText+",";
		    }
	 });
	 alert(ar); */
	   var selObj = document.getElementById('selected_lecturers');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	    
	      selectedArray[count] = selObj.options[i].value;
	      selectedtxt[count]=selObj.options[i].text;
	      //selObj.remove(i);
	      count++;
	    
	  }
	    var select_element = document.getElementById("selected_lecturers");
	    var options = select_element.options;
	    var i = options.length;
	    while (i--) {
	          var current = options[i];
	        
	            // Do something with the selected option
	            current.parentNode.removeChild(current);
	        
	    }
	    for (i=0; i<selectedArray.length; i++) {
	    	$("#lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
	    }
	   


 }
 function savefilter(){
	 $(rowid).css("background-color","white");
	 document.getElementById(chkid).disabled = false;
	    var country = document.getElementById("cid");
	    var branchid = document.getElementById("bid");
		var partnerid = document.getElementById("firstBox");
		var partnerval = partnerid.options[partnerid.selectedIndex].value;
		var partnertxt= partnerid.options[partnerid.selectedIndex].text;
		var countryval = country.options[country.selectedIndex].value;
		var countrytxt = country.options[country.selectedIndex].text;
		var branchval = branchid.options[branchid.selectedIndex].value;
		var branchtxt = branchid.options[branchid.selectedIndex].text;
		    
		var all="";
		all=getcus();
	    var pbc="";
	    pbc=partnerval+","+countryval+","+branchval;
		     document.getElementById("selectedfil").value=all;
		     document.getElementById("pbc").value=pbc;
		    document.branchform.action = "saveFilter.action";
		    document.branchform.submit(); 
 }
 function getcus(){
	
	 var arr1="";
	 var selectedArray = new Array();
	 var selectedtxt = new Array();
	 $("#selected_lecturers option:not(:selected)").each(function () {
		    var $this = $(this);
		    if ($this.length) {
		        var selText = $this.val();
		        arr1+= selText+",";
		    }
	 });
	  var selObj = document.getElementById('selected_lecturers');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	      selectedArray[count] = selObj.options[i].value;
	      selectedtxt[count]=selObj.options[i].text;
	      //selObj.remove(i);
	      count++;
	    
	  }
	  
    for(i=0;i<selectedArray.length;i++){
    	arr1+=selectedArray[i]+",";
    }
    
   var htm="";
    for(var i=0;i<selectedArray.length;i++){
	   htm+=selectedArray[i]+",";
   }
    return htm;
 }
	function editRelPart(){
		$("#editbutton").css("display","block");
	     addnew=false;
		 var ar="";
		 var selectedArray = new Array();
		 var selectedtxt = new Array();
		var editUserId = document.getElementsByName("searchUserCheckBox");
		
		var i1,txt1 = 0;
		var i,txt;
		var onedit=false;
	   for (i1=0;i1<editUserId.length;i1++){
		if (editUserId[i1].checked){
		 txt1 += 1;      
		}
	   }
	   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].disabled===true){
			 onedit=true;     
			}
		   }
	   if(onedit===false){
	   if(txt1 < 1){
		alert('Please select at least one');
	   }
	    else if(txt1 > 1){
		alert('Please check atmost one');
	   }
	   else{
		
		for (i=0;i<editUserId.length;i++){
			if (editUserId[i].checked){
				txt = editUserId[i].value ;					
			}
		}
		
		}
	   var sid="sel"+txt;
	   
	   var selObj = document.getElementById(sid);
		  var i;
		  var count1 = 0;
		  for (i=0; i<selObj.options.length; i++) {
		    
		      selectedArray[count1] = selObj.options[i].value;
		      selectedtxt[count1]=selObj.options[i].text;
		      //selObj.remove(i);
		      count1++;
		    
		  }
		 /*    var select_element = document.getElementById("lecturers");
		    var options = select_element.options;
		    var i = options.length;
		    while (i--) {
		        var current = options[i];
		        if (current.selected) {
		            // Do something with the selected option
		            current.parentNode.removeChild(current);
		        }
		    } */
		  for(var i=0;i<selectedArray.length;i++){
		  $("#lecturers option[value="+selectedArray[i]+"]").remove();
		  }
		    for (i=0; i<selectedArray.length; i++) {
		    	$("#selected_lecturers").append('<option value="' + selectedArray[i] + '">' + selectedtxt[i]  + '</option>');
		    }
	   var p=document.getElementById("p"+txt).value;
	   var b=document.getElementById("b"+txt).value;
	   var c=document.getElementById("c"+txt).value;
	   
		/* document.getElementById('cid').value = "";
		document.getElementById('bid').value = "-1"; */
		
	

		
		document.getElementById('firstBox').value = p;
		setcountry(p);
		partnerName=p;
		country=c;
		branch=b;
		 chkid="chk"+txt;
		 rowid="#r"+txt;
		$('#r'+txt).css("background-color","Gray");
		$('#chk'+txt).click();
		 document.getElementById("chk"+txt).disabled = true;
	}
	   else{
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
					txt = editUserId[i].value ;					
				}
			}
			$('#chk'+txt).click();
		   alert("SAVE THE FILTER");
	   }

}
	
	function deleteRelPart(){
		var deleteUserId = document.getElementsByName("searchUserCheckBox");
		var i1,txt1 = 0;
	   for (i1=0;i1<deleteUserId.length;i1++){
		if (deleteUserId[i1].checked){
		 txt1 += 1;      
		}
	   }
	   if(txt1 < 1){
		alert('Please select at least one');
	   }
	    else if(txt1 > 1){
		alert('Please check atmost one');
	   }
	   else{
			var i,txt;
			for (i=0;i<deleteUserId.length;i++){
				if (deleteUserId[i].checked){
					txt = deleteUserId[i].value ;					
				}
			}
			
		alert(txt);
		
	}
	}
	
function deleteFilter(){

	var id=document.getElementsByName("filterchk");
	var ids=id.split(",");
	var pid=ids[0];
	var countryval=ids[1];
	var bid=ids[2];
	
}
function editFilter(){
	var editUserId = document.getElementsByName("filterchk");
	var id=editUserId;
	var i1,txt1 = 0;
   for (i1=0;i1<editUserId.length;i1++){
	if (editUserId[i1].checked){
	 txt1 += 1;      
	}
   }
   if(txt1 < 1){
	alert('Please select at least one');
   }
    else if(txt1 > 1){
	alert('Please check atmost one');
   }
   else{
	var i,txt;
	for (i=0;i<editUserId.length;i++){
		if (editUserId[i].checked){
			txt = editUserId[i].value ;
			id=txt;
			
		}
	}
	
	}
  // alert(id);
	var ids=id.split(",");
	var pid=ids[0];
	var countryval=ids[1];
	alert(countryval);
	var bid=ids[2];
	// document.getElementById('firstBox').value = pid;
	 setcountry(pid);
  //   document.getElementById("cid").value = countryval;
     setBranch(countryval);
	// document.getElementById('bid').value = bid;
	
	$("#firstbox").val(pid);
	$("#cid").val(countryval);
	$("#bid").val(bid);
	
}
 function showhide(){
	 if(custo!=""){
  var html1="<select size=\"5\" name=\"customers\">"+custo+"</select>";
  alert(html1);
	 }
  }
 function getcustomers(id1,id2){
	    
	 $("#"+id2).leanModal({ top : 100, overlay : 0.4, closeButton: ".modal_close" });
 }
 function popups(id1){
	 jQuery('#'+cou).live('click', function(event) {        
         jQuery('#'+id1).toggle('show');
    });
	 
 }
 function appendcustomers(){
	 var arr1="";
	 var selectedArray = new Array();
	 var selectedtxt = new Array();
	 $("#selected_lecturers option:not(:selected)").each(function () {
		    var $this = $(this);
		    if ($this.length) {
		        var selText = $this.val();
		        arr1+= selText+",";
		    }
	 });
	  var selObj = document.getElementById('selected_lecturers');
	  var i;
	  var count = 0;
	  for (i=0; i<selObj.options.length; i++) {
	      selectedArray[count] = selObj.options[i].value;
	      selectedtxt[count]=selObj.options[i].text;
	      //selObj.remove(i);
	      count++;
	    
	  }
	  
    for(i=0;i<selectedArray.length;i++){
    	arr1+=selectedArray[i]+",";
    }
    
   var htm="";
    for(var i=0;i<selectedArray.length;i++){
	   htm+="<option value="+selectedArray[i]+" id="+selectedArray[i]+">"+selectedtxt[i]+"</option>";
   }
    return htm;
 }

	</script>
<script type="text/javascript">
var default_address_id_from =0;
var default_address_id_to =0;
var countrylist="";
var count=0;
var partner="";
var partnerName="";
var country="";
var branch="";
var chkid="";
var rowid="";
var addnew=true;
function saveCountry(co){
	
	country=co;
	
}
function setcountry(partnerId){
	//alert(chargecode);
	
	if(partnerId != ""){
		partner=partnerId;
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{   if(count==1){
	  		 if(ajax_Carrier.readyState==4)
				{
			 	   reponse=ajax_Carrier.responseText;
			  	  chargeCodeElement = document.getElementById("countyCode1");
			   	  chargeCodeElement.innerHTML= reponse;
			   	  if(addnew===false){
			   	    setBranch(country);
			   	  }
			     	listChargeName(chargeCodeElement.value);
				}
	  		}
	  	else if(count==2){
	  		 if(ajax_Carrier.readyState==4)
				{
			 	   reponse=ajax_Carrier.responseText;
			  	  chargeCodeElement = document.getElementById("countyCode2");
			   	  chargeCodeElement.innerHTML= reponse;
				//	listChargeName(chargeCodeElement.value);
					
				}
	  		
	  	}
	 	 };
		url="listCountryName.action?partnerId="+partnerId;
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#chargeCode select").html("");
		$("#chargeCode select").html("<option>Select</option>");
		$("#chargeName select").html("");
		$("#chargeName select").html("<option>Select</option>");
	}
	
}


function setBranch(country){
	if(country != "-1"){
		//partner=partnerId;
		ajax_Carrier=ajaxFunction();
		ajax_Carrier.onreadystatechange=function()
	  	{   if(count==1){
			{ //alert(partner);
	  		 if(ajax_Carrier.readyState==4)

			 	   reponse=ajax_Carrier.responseText;
			  	   chargeCodeElement = document.getElementById("branchCode2");
			  	  //alert(response);
			   	   chargeCodeElement.innerHTML= reponse;
			   		functionIHadToChange();
					//listChargeName(chargeCodeElement.value);
				}
	  		}
	  	else if(count==2){
	  		 if(ajax_Carrier.readyState==4)
				{
			 	   reponse=ajax_Carrier.responseText;
			  	  chargeCodeElement = document.getElementById("branchCode2");
			   	  chargeCodeElement.innerHTML= reponse;
			   	  listChargeName(chargeCodeElement.value);
					
				}
	  		
	  	}
	 	 };
		url="listBranchName.action?countryName="+country+"&partnerId="+partner;
		ajax_Carrier.open("GET",url,true);
		ajax_Carrier.send(this);
	}else{
		$("#chargeCode select").html("");
		$("#chargeCode select").html("<option>Select</option>");
		$("#chargeName select").html("");
		$("#chargeName select").html("<option>Select</option>");
	}
	
}

var arSelected = new Array();
function getMultiple(ob) { 
	while (ob.selectedIndex != -1) 
	{ if (ob.selectedIndex != 0) 
		arSelected.push(ob.options[ob.selectedIndex].value);
	ob.options[ob.selectedIndex].selected = false;
	} // You can use the arSelected array for further processing.

}



	function submitform()
	{   /*    
		 $("#selected_lecturers option:not(:selected)").each(function () {
		    var $this = $(this);
		    if ($this.length) {
		        var selText = $this.val();
		        arr+= selText+",";
		    }
		   
		    
		});  */
		    /* alert("dsf");
			document.getElementById("selectedfil").value=arr; */
			var arr="";
			var all="";
			 var parr=new Array();
			 var barr=new Array();
			 var carr=new Array();
			 var keys=new Array();
			 var cids=new Array();
		
		 var   i=0;
		      for(i=0;i<cou;i++){
		    	parr[i]=document.getElementById("p"+i).value;
		    	barr[i]=document.getElementById("b"+i).value;
		    	carr[i]=document.getElementById("c"+i).value;
		    				  
		     } 
		      //alert(parr[0]);
		     if(parr.length>=0){
		    	 //alert(parr[0]);
		    	 for(i=0;i<parr.length;i++){
		    	 var selectedArray = new Array();
				 var selectedtxt = new Array();
				  var selObj = document.getElementById('sel'+i);
				  var i;
				  var count = 0;
				 
				  for (var d=0; d<selObj.options.length; d++) {
				      selectedArray[count] = selObj.options[d].value;
				      selectedtxt[count]=selObj.options[d].text;
				      count++;
				  }
				  var arr1="";
			    for(var k=0;k<count;k++){
			    	arr1+=selectedArray[k]+",";
			    }
			   cids[i]=arr1;
		    	 }
		    	  for(var dd=0;dd<parr.length;dd++){
		    		  
				    	keys[dd]=parr[dd]+","+carr[dd]+","+barr[dd]+"%"+cids[dd]+"&";
				    }
		          all="";
		           for(var w=0;w<keys.length;w++){
				    	all+=keys[w];
				    }
		           alert(all);
		     }
		     
		     else{
		     }
		     alert(all);
		    document.getElementById("selectedfil").value=all;
		    document.branchform.action = "saveFilter.action";
		   document.branchform.submit(); 
		
	}
	
	function resetform(){
			
			document.branchform.action = "addFilter.action";
	 		document.branchform.submit();
	}		
	
	function showHideSalesDiv(valname)
	{
		//alert(valname);
		var e = document.getElementsByName(valname);
		//alert(e);
		var strRole = e[0].options[e[0].selectedIndex].value;
		//alert(strRole);
		if(strRole == 'sales'){		
			document.getElementById("sales_div").style.display= "block";
			}
		else{
		document.getElementById("sales_div").style.display= "none";
		}
		if(strRole == 'customer_admin')
		{
			document.getElementById("rules").innerHTML="- Unrestricted access to all functions within the customer account.<br/>";	
		}
		else if(strRole == 'customer_base')
		{
			document.getElementById("rules").innerHTML="- Access to Shipping information, including create new shipments and view all shipments created under the customer account.<br/> - Access to Address Book functionality. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - No access to invoicing module and other admin functions (such as partner management).";	
		}		
		else if(strRole == 'customer_shipper')
		{
			document.getElementById("rules").innerHTML="- Restricted access to Shipping information.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Can create new shipments.<br/>- Can view only shipments created by this partner.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- No access to invoicing module and other admin functions (such as partner management).";	
		}
		else
		{
			document.getElementById("rules").innerHTML="";	
		}
		
		
	}
	
	function typenumbers(e,filterString)
	{
		var key, keychar;
		key = getkey(e);
		if (key == null) 
		return true;
		
		// get character
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();
		// control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==27) )
		return true;
		// alphas and numbers
		else if ((filterString.indexOf(keychar) > -1))	
		return true;
		else
		return false;
	}
	 
	function getkey(e){
		if (window.event)
		  return window.event.keyCode;
		else if (e)
		  return e.which;
		else
		  return null;
	}
	
	 function toggle_visibility(id) {
		 
	      var e = document.getElementById(id);
	       /*if(e.style.display == 'block')
	          e.style.display = 'none';
	       else{
	          e.style.display = 'block';
	    
	       } */
	       var inhtml=e.innerHTML;
	       alert(inhtml);
	       
	       }
		
	jQuery(function() {
		$("#defaultFromAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultFromAddText").result(function(event, data, formatted) {
			//alert(data[0]); 
			//alert(data[1]);
			document.getElementById("partner.defaultFromAddressId").value=data[1];
			default_address_id_from = data[1];
			//document.getElementById("partner.defaultFromAddressText").value=data[0];
			});
			
		$("#defaultToAddText").autocomplete("<s:url action="listAddresses.action"/>", {extraParams:{customerId: document.userform.cid.value}});
		$("#defaultToAddText").result(function(event, data, formatted) {
			//alert(data[0]);
			//alert(data[1]);
			document.getElementById("partner.defaultToAddressId").value=data[1];
			default_address_id_to = data[1];
			//document.getElementById("partner.defaultToAddressText").value=data[0];
			});
		
		// $("#defaultFromAddText").click(function(){$("#defaultFromAddText").val('')});
         $("#defaultFromAddText").blur(function(){if($("#defaultFromAddText").val()==''){
         $("#defaultFromAddText").val('None');
         document.getElementById("partner.defaultFromAddressId").value=0;
         }});
          
        // $("#defaultToAddText").click(function(){$("#defaultToAddText").val('')});
         $("#defaultToAddText").blur(function(){if($("#defaultToAddText").val()==''){
         $("#defaultToAddText").val('None');
         document.getElementById("partner.defaultToAddressId").value = 0;
         }});		
			
		});
	
	
	
	function functionIHadToChange1() {
		  // other code
		document.getElementById('cid').value = "";
		document.getElementById('bid').value = "-1";
		  
		document.getElementById('firstBox').value = "-1"; 
			}
	function functionIHadToChange() {
		  // other code
		
		if(addnew===false){
			document.getElementById('cid').value = country;
			document.getElementById('bid').value = branch;
			document.getElementById('firstBox').value = partnerName; 
		 document.getElementById("cid").disabled = true;
		  document.getElementById("bid").disabled = true;
		  document.getElementById("firstBox").disabled = true;
		}
			}
</script> 

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>

<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>

<div class="content">
<div class="content_body">


<s:form action="addbranch" name="branchform" style="margin-bottom	:0px">
<input type="hidden" id="selectedfil" name="selectedCus">
<input type="hidden" id="pbc" name="filterparam">
	<s:if test="#session.edit == 'true'">
	<input type="hidden" id="deleteCountryList" name="deletecountls">
    	<s:hidden name="method1" value="update"/>
    </s:if> 
    <s:else>
    	<s:hidden name="method1" value="add"/>
    		
     </s:else>
    <s:hidden name="cid" value="%{partner.partnerId}" />
						
    		
    	
				<div class="content_table">
					<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title">ADD FILTER DETAILS:</div>
									
										<div class="form_buttons">
									
									
									  <a href="#" id="savebut" onclick="submitform()"><mmr:message messageId="label.btn.save"/></a> 
									 
									<a href="#" onclick="resetform()"><mmr:message messageId="label.btn.reset"/></a> 
								</div>
								</div>
								
								<div class="cont_data_body">
								
								
					
										<div class="rows">
											<div class="fields">
											<label>FILTER NAME</label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="filter.filterName" name="filter.filterName"  cssClass="text_02_tf" value="%{filter.filterName}"/>
											</div>
										</div>
												<div class="fields">
											<label>DESCRIPTION</label>
											<div class="controls"><span>:</span>
												<s:textfield size="20" key="filter.description" name="filter.description"  cssClass="text_02_tf" value="%{filter.description}"/>
											</div>
										</div>
										</div>
									
								</div>
								<div class="hide1" id="filterdiv1">
								<div class="cont_data_body" >
								<div class="content">
								<div class="content_table" style="background-color:#fff">
<div class="form-container">
								<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title"> FILTER DETAILS:
										
									</div>
									<div class="form_buttons">
									
									<s:if test="#session.edit == 'true'">
									<a href="#" onclick="editRelPart()"><mmr:message messageId="label.btn.edit"/></a>
									</s:if> 
									<a href="#" onclick="deleteRelPart()">DELETE</a> 
								</div>
									</div>
									<table cellpadding="0" cellspacing="0"  border="0px" class="display"  style="float:left; width:100%; height:auto;">
									    <thead>
		<tr>
			<th><input id="check_all" type="checkbox" /></th>
			<th><span style="width:250px !important; float:left;">PARTNER NAME</span></th>
			<th><span style="width:250px !important; float:left;">COUNTRY </span></th>
			<th><span style="width:250px !important; float:left;">BRANCH NAME</span></th>
			<th><span style="width:250px !important; float:left;">CUSTOMERS</span></th>
		</tr>
	</thead>
									</table>
								<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="filterdiv" style="float:left; width:100%; height:auto;">

	<tbody>
            <s:iterator id="usertable" value="Relpartnerfils" status="rowstatus">
            <s:iterator value="customers">
            </s:iterator>
             <tr id="r<s:property value="relPartnerFilId"/>">
                 
       
		        <td class="odd1" width="2%">
		        <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" id="chk<s:property value="relPartnerFilId"/>" value="<s:property value="relPartnerFilId"/>"/>  
	   			</td>
				 
				<td style="text-align: left;"><span title="<s:property value="partner.partnerName"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="partner.partnerName"/></div>
				<input type="hidden" id="p<s:property value="relPartnerFilId"/>" value="<s:property value="partner.partnerId"/>" name="hpartner"/>
				
				</td>
	            <td style="text-align: left;"><span title="<s:property value="countryName"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="countryName"/></div>
	            <input type="hidden" id="c<s:property value="relPartnerFilId"/>" value="<s:property value="countryName"/>" name="hcountry"/>
				
	            </td>
	            <td style="text-align: left;"><span title="<s:property value="branch.branchName"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="branch.branchName"/></div>
	            <input type="hidden" id="b<s:property value="relPartnerFilId"/>" value="<s:property value="branch.branchId"/>" name="hbranch"/>
				
	            </td>
	              <td style="text-align: left;">
	              <a href="#" onclick="toggle_visibility('cus<s:property value="relPartnerFilId"/>')">SHOW CUSTOMERS</a>
	              <div style="display: none;" id="cus<s:property value="relPartnerFilId"/>">
                 <s:select  listValue="name" list="customers" size="5" id="%{'sel'+relPartnerFilId}"  listKey="id"/>
       
            </div>
        </td>
            </tr>			
            </s:iterator> 
</tbody>
</table>
</div>
</div>

</div>
								
														<div id="filtrdiv">
										<%-- 	<div class="fields">
											<label>PARTNER :</label>
											<div class="controls"><span>:</span>
											<input type="text" name="partner" value=""/>
											</div>
										</div>
												<div class="fields">
											<label>COUNTRY :</label>
											<div class="controls"><span>:</span>
											<input type="text" name="country" value=""/>
											</div>
										</div>
										<div class="fields">
											<label>BRANCH :</label>
											<div class="controls"><span>:</span>
											<input type="text" name="branch" value=""/>
											</div>
										</div> --%>
										</div>
									
								</div>
								</div>
								
								<div class="content_header" style="margin-bottom:1px;">
									<div class="cont_hdr_title">SELECT FILTER DETAILS:
									</div>
									<div class="form_buttons">
									<a href="#" id="addbut" onclick="addfilter()"><mmr:message messageId="label.btn.add"/></a>
									 <s:if test="#session.edit == 'true'">
									 <a href="#" id="editbutton" onclick="savefilter()" style="display: none;">SAVE</a>
									 </s:if> 
									</div>
									</div>
									
								<div class="cont_data_body">
							
							<div class="rows">
							      <s:if test="#session.edit == 'true'">
							       <div class="fields">

										<label><mmr:message messageId="label.partner.partnername"/>  </label>
										<div  class="controls"><span>:</span>
									<s:select   listKey="partnerId" listValue="partnerName" 
												name="filter.partnerId" headerKey="-1"  headerValue="Select"  list="partnerList" 
												onchange="setcountry(this.value);"  id="firstBox" theme="simple"/></div>
										</div>
							         <script type="text/javascript">
							         $('#filterdiv1').css('display','block');
							       //  countryn='<s:property value="filter.partnerId"/>';
							             count=1;
							          $('#savebut').css('display','none');
							          $('#addbut').css('display','none');
							            //setcountry(countryn);
							           //document.getElementById("cid").value=country;
							           
							      
							         </script>
							         	
										<div class="fields">
										<label><mmr:message messageId="label.customer.country"/></label>
										<div id="countyCode1">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newActualCharge.chargeCode" list="{}" 
										 theme="simple"/>
								       </div>
										</div>
										 <div class="fields">
										<label>Branch Name :</label>
										<div id="branchCode2">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newActualCharge.chargeCode" list="{}" 
										 theme="simple"/>
								       </div>
										</div>
							      </s:if>
							      <s:else>
							       <script type="text/javascript">
							        count=2;
							        $('#savebut').css('display','block');
							       </script>
							          <div class="fields">

										<label><mmr:message messageId="label.partner.partnername"/> : </label>
										<div  class="controls"><span>:</span>
								     	<s:select    listKey="partnerId" listValue="partnerName" 
												name="filter.partnerId" headerKey="-1"  headerValue="Select" list="partnerList" 
												onchange="setcountry(this.value);"  id="firstBox" theme="simple"/></div>
											
										</div>	
										<div class="fields">
										<label><mmr:message messageId="label.customer.country"/> :</label>
										<div id="countyCode2">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newActualCharge.chargeCode" list="{}" 
										 theme="simple"/>
								       </div>
										</div>	
								      <div class="fields">
										<label>Branch Name :</label>
										<div id="branchCode2">
										<s:select cssClass="text_01_combo_big" cssStyle="width:120px;" headerKey="" headerValue="Select" name="newActualCharge.chargeCode" list="{}" 
										 theme="simple"/>
								       </div>
										</div>	
							      </s:else>
									
								
								
										
									</div>
									
															
					    <div class="mult">
				 <div class="multiselectable">
					    <div class="m-selectable-from"><label for="lecturers">ALL CUSTOMERS</label>
					 <s:select listKey="id" class="m-selectable-from" multiple="true" id="lecturers" listValue="name" list="allCustomerList" name="allCustomerList" size="5" >
					  </s:select> 
					  </div>
					 <div class="m-selectable-controls">
					<button type="button" class="multis-right" id="add" onclick="add1(); return false;">+</button> 
					<button type="button" class="multis-left" id="remove" onclick="remove1(); return false;">-</button> 
				</div>
			<div class="m-selectable-to"><label for="m-selected">FILTERED CUSTOMERS</label>
						 <s:select listKey="id" class="m-selectable-to" id="selected_lecturers" listValue="name" multiple="true" list="branchCustomerList" name="listint" size="2" >
					   </s:select> 
</div>						
							</div>
						
							</div>
							</div>
					
				
	
						
						
						</div>
							         	
							         	
		</s:form> 



</div>
</div>
</body>
</html>




