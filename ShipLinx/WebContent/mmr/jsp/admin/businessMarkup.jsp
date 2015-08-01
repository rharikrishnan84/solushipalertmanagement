<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
<head> 
    <title><s:text name="markup.title"/></title> 
</head> 

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
<style type="text/css">
.autocomplete-suggestions {
border: 1px solid #999;
background: #FFF;
cursor: default;
overflow: auto;
-webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
-moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);
height:200px;
display:none;
}
#customersautocomplete,#auto{ background-position: 146px 5px; background-size:8px 8px; }
#customerautocomplete,#auto{ background-position: 146px 4px; background-size:8px 8px; }
</style> 	

<body> 

<script type="text/javascript">
	
		$(document).ready(function() {
			$('#sample1').dataTable(
			
			
			); 
			
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
			}
				else{
				$(".dataTable-checkbox").removeAttr("checked");

				}
			});
		} );

		
		$(document).ready(function() {
						$('#sample2').dataTable(); 
						
						$("#check_all1").click(function(){
							var temp=$(".dataTable-checkbox1").attr("checked");
							if(temp == null){
						    $(".dataTable-checkbox1").attr("checked","checked");
						}
						else{
							$(".dataTable-checkbox1").removeAttr("checked");
			
							}
						});
					} );
		function ontimevalidate(val,perc,rowType,index){
			var evenPercId = "evenPercId"+index;
							            var evenFlatId = "evenFlatId"+index;
							            var oddPercId = "oddPercId"+index;
						            var oddFlatId = "oddFlatId"+index;
						            
							            if(val==0){
											if(rowType=="even"){
							                var percentageEven=parseInt(document.getElementById(evenPercId.toString()).value);
							                var flatEven=parseInt(document.getElementById(evenFlatId).value);
									if(percentageEven==0 && flatEven==0)
											alert("Either Percentage or Flat should have the value greater than 0.");
											}
											else{
							                var percentageOdd=parseInt(document.getElementById(oddPercId).value);
							                var flatOdd=parseInt(document.getElementById(oddFlatId).value);
											if(percentageOdd==0 && flatOdd==0)
											alert("Either Percentage or Flat should have the value greater than 0.");
											}
									}
			if(val!=0){
				if(rowType=="even"){
										var percentageEven=document.getElementById(evenPercId.toString()).value;
                var flatEven=document.getElementById(evenFlatId).value;
                if(perc=="perc" && flatEven!=0){
                    alert("Either Percentage or Flat should have the value greater than 0.");
                    document.getElementById(evenFlatId).value =0;
                }else if(perc=="flat"  && percentageEven!=0){
                    alert("Either Percentage or Flat should have the value greater than 0.");
                    document.getElementById(evenPercId).value =0
                }
            }else{
                var percentageOdd=document.getElementById(oddPercId).value;
                var flatOdd=document.getElementById(oddFlatId).value;
                if(perc=="perc" && flatOdd!=0){
                    alert("Either Percentage or Flat should have the value greater than 0.");
                    document.getElementById(oddFlatId).value =0;
                }else if(perc=="flat"  && percentageOdd!=0){
                    alert("Either Percentage or Flat should have the value greater than 0.");
                    document.getElementById(oddPercId).value =0
                }
            }
        }
    }
	
		function editMarkup(){
		
			var editMarkupId = document.getElementsByName("markupCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<editMarkupId.length;i1++){
			if (editMarkupId[i1].checked){
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
			alert('Do you really want to apply the selected markup to all customers?');
				var i,txt;
				for (i=0;i<editMarkupId.length;i++){
					if (editMarkupId[i].checked){
						txt = editMarkupId[i].value ;					
					}
				}
				
				document.getElementById("markupFormId").action ="applyToAllCustomersMarkup.action?method=applyToAll&amp;serviceId=2013&amp;fromCountryCode=ANY&amp;toCountryCode=ANY"+txt;
				document.getElementById("markupFormId").submit();
			}
		}
	</script>
<script type="text/javascript">
		function deleteMarkup(){
			var deleteMarkupId = document.getElementsByName("markupCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteMarkupId.length;i1++){
			if (deleteMarkupId[i1].checked){
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
				var i,serviceId, fromCountryCode, toCountryCode,checked,businessId,businessToId;
				for (i=0;i<deleteMarkupId.length;i++){
					if (deleteMarkupId[i].checked){
						checked = deleteMarkupId[i].value ;
						serviceId=document.getElementById("serviceId"+checked).value
						fromCountryCode = document.getElementsByName("fromCountryCode"+serviceId)[0].value;
						toCountryCode = document.getElementsByName("toCountryCode"+serviceId)[0].value;	
						businessId = document.getElementsByName("businessId"+serviceId)[0].value;
						businessToId = document.getElementsByName("businessToId"+serviceId)[0].value;
					}
				}
		   if(fromCountryCode != "ANY" && toCountryCode != "ANY")
		   {
			/*	var del=confirm("Do you really want to delete the selected product?");
				if(del==true){
					document.getElementById("markupFormId").action ="deleteMarkup.action?method=deletetMarkup&serviceId="+serviceId+"&fromCountryCode="+fromCountryCode+"&toCountryCode="+toCountryCode;
					document.getElementById("markupFormId").submit();
				}*/
				
				
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
				msg.innerHTML = "Do you really want to delete the selected product?";
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
					document.getElementById("markupFormId").action ="deleteBusinessMarkup.action?method=deletetMarkup&serviceId="+serviceId+"&fromCountryCode="+fromCountryCode+"&toCountryCode="+toCountryCode+"&businessId="+businessId+"&businessToId="+businessToId;
					document.getElementById("markupFormId").submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}

			else{
					alert('You CANNOT DELETE THIS RECORD');
				}
			
		}
	}
</script>
<script type="text/javascript">
	function uploadMarkup(){
			var uploadMarkupId = document.getElementsByName("markupCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<uploadMarkupId.length;i1++){
			if (uploadMarkupId[i1].checked){
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
				var i,serviceId,customerId,serviceType;
				for (i=0;i<uploadMarkupId.length;i++){
					if (uploadMarkupId[i].checked){
						serviceId = uploadMarkupId[i].value ;
						customerId = document.getElementsByName("customerId"+serviceId)[0].value;
						serviceType= document.getElementsByName("serviceType"+serviceId)[0].value;
					}
				}
			
		   if(serviceType==1 || serviceType==2)
		   {
				alert('Do you really want to upload');
				document.getElementById("markupFormId").action ="viewUploadRateTemplate.action?method=viewUploadRateTemplate&serviceId="+serviceId+"&customerId="+customerId;
				document.getElementById("markupFormId").submit();
			}
			
			else{
					alert('You CANNOT UPLOAD THIS RECORD');
				}
			
		}
	}
</script>
<script>
	$(document).ready(function(){
	
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
	});
</script>
<SCRIPT language="JavaScript">
	/* function searchMarkup()
	{
	 document.searchform.action = "searchMarkup.action";
	 document.searchform.submit();
	} */
	
	function searchBusinessMarkup(customerId)
	 {
	  var carrierId = document.getElementById('secondBox');
	  var cc=carrierId.value;
	 /*  if(carrierId != null && carrierId.value == 6){
	   document.searchform.action = "searchEshipPlusMarkup.action?carrierId="+carrierId.value+"&customerId="+customerId;
	   document.searchform.submit();
	  }else{ */
	    document.searchform.action = "searchBusinessMarkup.action?call=search";
	    document.searchform.submit();
	 /*  }
	  document.searchform.action = "searchEshipPlusMarkup.action?carrierId="+carrierId.value+"&customerId="+customerId;
	  document.searchform.submit(); */
	 }
	function addBusinessMarkup()
	{
		document.searchform.action = "addBusinessMarkup.action";
		document.searchform.submit();
	}	
	/* function saveMarkupList()
	{
		var savemarkup=document.getElementsByName("markupCheckBox");
					var i1,txt1 = 0;
							   for (i1=0;i1<savemarkup.length;i1++){
								if (savemarkup[i1].checked){
							 txt1 += 1;      
								}
							   }
							   if(txt1 < 1)
								alert('Please select at least one');		
							   else{
									var i,selectedItem="",percentage="",flat="",disabledflag="",variable="";
										for(i=0;i<savemarkup.length;i++){
											if(savemarkup[i].checked){
												selectedItem=selectedItem+savemarkup[i].value+",";
												percentage=percentage+document.getElementsByName("markupPercentage")[i].value+",";
												flat=flat+document.getElementsByName("markupFlat")[i].value+",";
												disabledflag=disabledflag+document.getElementsByName("disabledFlag")[i].value+",";					
												variable=variable+document.getElementsByName("variable")[i].value+",";
								}			
						}
					  	document.searchform.action = "saveMarkupList.action?selectedItem="+selectedItem+"&percentage="+percentage+"&flat="+flat+"&disabledFlag="+disabledflag+"&variable="+variable;
				     	document.searchform.submit();
					}
	}
	
 */
 
 function saveMarkupList()
 {
  
  var carrierIdStr = document.getElementById('firstBox');
    var carrierId;
    if(carrierIdStr!=null){
        carrierId=carrierIdStr.value; 
    }
    var savemarkupCarrier=document.getElementsByName("carrierCheckBox");
    var txt2=0;
    if(savemarkupCarrier!=null && carrierId!=null && carrierId==6){
     for (i1=0;i1<savemarkupCarrier.length;i1++){
      if (savemarkupCarrier[i1].checked){
        txt2 += 1;      
      }
     }
    }
  var savemarkup=document.getElementsByName("markupCheckBox");
     var i1,txt1 = 0;
          for (i1=0;i1<savemarkup.length;i1++){
        if (savemarkup[i1].checked){
        txt1 += 1;      
        }
          }
          if(txt1 < 1 && txt2 <1 )
        alert('Please select at least one');  
          else{
         var i,selectedItem="",percentage="",flat="",disabledflag="",variable="";
          for(i=0;i<savemarkup.length;i++){
           if(savemarkup[i].checked){
            selectedItem=selectedItem+savemarkup[i].value+",";
            percentage=percentage+document.getElementsByName("markupPercentage")[i].value+",";
            flat=flat+document.getElementsByName("markupFlat")[i].value+",";
            disabledflag=disabledflag+document.getElementsByName("disabledFlag")[i].value+",";     
            variable=variable+document.getElementsByName("variable")[i].value+",";
           }   
                     }
                     
            if(carrierId==6){
              
              
              if(savemarkupCarrier!=null){
               if(txt2>0){
                var i=0,carrierSelectedItem="",carrierName="",carrierInactive="",indexTemp;
                for(i=0;i<savemarkupCarrier.length;i++){
                 if(savemarkupCarrier[i].checked){
                  carrierSelectedItem=carrierSelectedItem+savemarkupCarrier[i].value+",";
               //   carrierName=carrierName+document.getElementById("eshipCarrier_"+savemarkupCarrier[i].value).innerText+",";
               carrierName=carrierName+jQuery("#eshipCarrier_"+savemarkupCarrier[i].value).text()+",";

                  carrierInactive=carrierInactive+document.getElementsByName("carrierDisabledFlag")[i].value+",";
                 }
               }
                carrierName=carrierName.replace("&", "~");
               }
              }   
      }
      document.searchform.action = "saveBusinessMarkupList.action?selectedItem="+selectedItem+"&percentage="+percentage+"&flat="+flat+"&disabledFlag="+disabledflag+"&variable="+variable+"&carrierId="+carrierId+"&carrierName="+carrierName+"&carrierInactive="+carrierInactive;
          document.searchform.submit();
     }
 }
 function defaultmarkup(customerId)
	{
 	 document.searchform.action = "businessMarkup.action?customerId="+customerId;
	 document.searchform.submit();
	}
	function copyCustomerMarkup()
	{
	 document.searchform.action = "copyCustomerMarkup.action";
	 document.searchform.submit();
	}	




	function copyCustomerMarkup()
	{
	 document.searchform.action = "copyCustomerMarkup.action";
	 document.searchform.submit();
	}	




	
	function getAccountInformation(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
	}
	function showCustomer() {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function(){
			if(ajax_Service.readyState==4){
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById("customerid");
				js_stateid.innerHTML= reponse;
				}
			}
			firstBox = document.getElementById('firstBox');
			url="markup.listCustomers.action?value="+firstBox.value;
			ajax_Service.open("GET",url,true);
			ajax_Service.send(this);
		}
	function showCarriers() {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function(){
			if(ajax_Service.readyState==4){
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById("carrierid");
				js_stateid.innerHTML= reponse;
				}
			}
			secondBox = document.getElementById('secondBox');
			url="markup.listCarriers.action?value="+secondBox.value;
			ajax_Service.open("GET",url,true);
			ajax_Service.send(this);
		}
    function showServices() {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function()
		  {
			   if(ajax_Service.readyState==4)
				{
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById("stateid");
				js_stateid.innerHTML= reponse; 
				}
		  }
		  thirdBox = document.getElementById('thirdBox');
		  url="<%=request.getContextPath()%>/markup.listService.action?value="+thirdBox.value+"&call=business";
			//param="objName=ref_state&country_id="+country_id;
		  	ajax_Service.open("GET",url,true);
		  	ajax_Service.send(this);
	} // End function showState()	
	
    function showProvince(call) {
		ajax_Service=ajaxFunction();
		ajax_Service.onreadystatechange=function(){
			if(ajax_Service.readyState==4){
				reponse=ajax_Service.responseText;
				js_stateid=document.getElementById(call+"Provinceid");
				js_stateid.innerHTML= reponse;
				}
		  }
		if(call=="toCountry"){
			Country = document.getElementById('toCountry');
		}else{
			Country = document.getElementById('fromCountry');
		}
		url="markup.listProvinces.action?value="+Country.value+"for"+call;
		ajax_Service.open("GET",url,true);
		ajax_Service.send(this);
	} // End function showState()	
	
</SCRIPT>
	
	<script>
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


		});
		
		
	</script>
<script>
	$(window).load(function() {
		$('#wrapper_new').css('float','left');
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>

	<script>
 $(document).ready(function(){

  $('#eshipCarrierFilter').click(function(){
   
   $('#eshipcarrierList').toggle();
  });
 });
</script>





<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<s:form action="searchMarkup" cssClass="form" name="searchform" id="markupFormId">
<div class="content">
	<div class="content_body">
       <div class="content_table">
        <div class="content_header">
         <div class="cont_hdr_title"><mmr:message messageId="label.heading.markups"/></div>
		 <div class="cont_hdrtitle_s"><s:property value="%{businessMarkup.customerBusName}"/></div>		 
         <div class="form_buttons">
          <a href="javascript: addBusinessMarkup()"><mmr:message messageId="btn.addnew"/></a>
          <a href="javascript: defaultmarkup('<s:property value="%{markup.customerId}"/>')"><mmr:message messageId="label.btn.reset"/></a>
          <a href="javascript: searchBusinessMarkup('<s:property value="%{markup.customerId}"/>')"><mmr:message messageId="label.search.btn.search"/></a> 
         </div>
        </div>
        <div class="cont_data_body">
         <div class="rows">
         <s:hidden  name="businessMarkup.businessId" value="%{businessMarkup.businessId}"/>
         <div class="fields">
         <label><mmr:message messageId="label.menu.business"/> </label>
         <div class="controls"><span>:</span>
           <s:select   listKey="id" listValue="name" 
				name="businessMarkup.businessToId" headerValue="" headerKey="-1"  list="#session.BUSINESS" 
				onchange="javascript:showCustomer();"  id="firstBox" theme="simple" size="1"  />
           </div>
          </div>
           <div class="fields">
           <label><mmr:message messageId="label.heading.customer"/> </label>
           <div id="customerid">
           <div class="controls"><span>:</span>
            <s:select   listKey="id" listValue="name" 
							name="businessMarkup.customerId" headerValue="" headerKey="-1"  list="#session.CUSTOMERS" 
								onchange="javascript:showCarriers();"  id="secondBox" theme="simple" size="1"  />
           </div>
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.track.carrier"/> </label>
           <div id="carrierid">
           <div class="controls"><span>:</span>
            <s:select   listKey="id" listValue="name" 
							name="businessMarkup.carrierId" headerValue="" headerKey="-1"  list="#session.CARRIERS" 
								onchange="javascript:showServices();"  id="thirdBox" theme="simple" size="1"  />
           </div>
          </div>
          </div>
           <div class="fields">
           <label><mmr:message messageId="label.markup.service"/></label>
           <div id="stateid">
           <div class="controls"><span>:</span>
           <s:select  listKey="id" listValue="name" name="businessMarkup.serviceId" list="#session.SERVICES" 
							 headerKey="-1" id="service" theme="simple"  />
           </div>
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.markup.fromCountry"/></label>
           <div  class="controls"><span>:</span>
           <s:select  listKey="countryCode" 
							listValue="countryName" name="businessMarkup.fromCountryCode" list="#session.COUNTRIES" 
							 headerKey="ANY" onchange="javascript:showProvince('fromCountry');" headerValue="ANY" id="fromCountry" theme="simple"  />
           </div>
          </div>
         <%--  <div id="fromProvinceid">
          <div class="fields">
           <label><mmr:message messageId="customer.province"/></label>
           <div  class="controls"><span>:</span>
           <s:select list="#session.FROM_PROVINCE"  name="businessMarkup.fromCountryProvince" 
							 headerKey="ANY" headerValue="ANY" id="fromCountry" theme="simple"  />
							listValue="countryName" name="markup.fromCountryCode" list="#session.FROM_PROVINCE" 
							 headerKey="ANY" headerValue="ANY" id="fromCountry" theme="simple"  />
           </div>
          </div>
          </div> --%>
          <div class="fields">
           <label><mmr:message messageId="label.markup.toCountry"/> </label>
           <div class="controls"><span>:</span>
           	<s:select  listKey="countryCode" 
							listValue="countryName" name="businessMarkup.toCountryCode" list="#session.COUNTRIES" 
							 headerKey="ANY"  onchange="javascript:showProvince('toCountry');" headerValue="ANY" id="toCountry" theme="simple"  />
           </div>
          </div>
         <%--  <div id="toProvinceid">
          <div class="fields">
           <label><mmr:message messageId="customer.province"/> </label>
           <div class="controls"><span>:</span>
           	<s:select  listKey="countryCode" list="#session.TO_PROVINCE"   name="businessMarkup.toCountryProvince" 
							 headerKey="ANY" headerValue="ANY" id="toCountry" theme="simple"  />
							listValue="countryName" name="markup.toCountryCode" list="#session.TO_PROVINCE" 
							 headerKey="ANY" headerValue="ANY" id="toCountry" theme="simple"  />
          </div>
          </div>
          </div> --%>
		  <div class="fields">
           <label><mmr:message messageId="label.markup.type"/></label>
           <div class="controls"><span>:</span>
           	<s:select value="%{businessMarkup.typeText}" id="type"
				name="businessMarkup.typeText" list="{'Markup','Markdown'}" theme="simple" />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.cost"/></label>
           <div class="controls"><span>:</span>
            <s:textfield  key="businessMarkup.fromCost" name="businessMarkup.fromCost"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.cost"/> </label>
           <div class="controls"><span>:</span>
            <s:textfield  key="businessMarkup.toCost" name="businessMarkup.toCost"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.markup.percentage"/> </label>
           <div class="controls"><span>:</span>
            <s:textfield  key="businessMarkup.markupPercentage" name="businessMarkup.markupPercentage"  />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.markup.flat"/> </label>
           <div class="controls"><span>:</span>
            <s:textfield  key="markup.markupFlat" name="businessMarkup.markupFlat"   />
           </div>
          </div>
		  <div class="fields">
           <label><mmr:message messageId="label.markup.variable"/> </label>
           <div class="controls"><span>:</span>
           <s:select value="%{businessMarkup.variable}" name="businessMarkup.variable" list="#{'0':'Weight/Skid','1':'Cost $'}" theme="simple"  disabled="false" />
   			</div>
									
           </div>
		<%-- <s:if test='%{businessMarkup.customerBusName != "Default"}' >
		<div class="rows">
		<div class="fields">
			<label> </label>
			<div class="controls">
				<s:a cssStyle=" float:left; margin-left:60px; font-size:14px;" onclick="return confirm('Do you really want to copy markup(s) from selected customer ?')"  href="javascript: copyCustomerMarkup()"> 
								Copy Markup 
				</s:a> 
			</div>
		</div>
			<div class="fields">
										<label>From</label>
										<div class="controls"><span>:</span>
										<s:url id="customerList" action="listCustomers" />
		               <s:hidden id="custId" name="businessMarkup.sourceCustomerId"/>			
		               						<s:textfield id="customerautocomplete" />
										</div>
									</div>
		<div class="fields">
			<label style="width:30px; font-weight:bold;">To</label>
          
            <s:label key="businessMarkup.customerBusName" cssStyle="text-transform:capitalize; font-weight:bold;"/>
           
         </div>
		 </div> 
		</s:if> --%>
         </div>
        </div>
       </div>
	</div>
	<div class="content_body">
		<div class="content_table" >
			<div class="form-container" style="background-color:#FFF;"> 
				
				<div id="mrkup_srch_panel">
				</div>
				<div id="markup_table">			
				</div>
			
				<div id="srchusr_results">
					<div id="srchusr_res"><span><mmr:message messageId="label.heading.businessmarkup"/></span></div>
				<!--<img src="<s:url value="/mmr/images/panelResults_top.png" includeContext="true" />" style="margin-left: 52px;margin-top: 16px;width: 853px;position: relative;bottom: 30px;height:31px;" alt="logo"> 	-->
					<div class="form_buttons">
					<a href="#" id="actiondown" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action"/><span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
						<!--<input type="button" value="EDIT" onclick="editMarkup();" />
						<input type="button" value="DELETE" onclick="deleteMarkup();" />
						<input type="button" value="UPLOAD" onclick="uploadMarkup();" />-->
						<li><a href="#" onclick="editMarkup();"><mmr:message messageId="label.list.applyall"/></a></li>
						<li><a href="#" onclick="deleteMarkup();"><mmr:message messageId="label.list.delete"/></a></li>
						<li><a href="#" onclick="uploadMarkup();"><mmr:message messageId="menu.upload"/></a></li>
						 <li><a href="javascript: saveMarkupList()"><mmr:message messageId="label.propertylist.save"/></a></li>
						 </ul>
					</div>
				</div>
				
<%-- <s:if test="markup.carrierId==6 && #session.eshipCarrierFound.contains('true')">
	<div class="content_header">
		<div class="cont_hdrtitle_L" style="margin-left:200px;">Eship Carriers Filter.</div>
			<div class="form_buttons"><a href="javascript:return false;"  id="eshipCarrierFilter">ESHIP CARRIERS</a></div>
	</div>
	</s:if> --%>
<%-- 	<div class="rows" id="eshipcarrierList" style="display:none;">
		<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample2" style="float:left;  background-color:#FFF; width:100%; height:auto;">
	    <thead>
				<tr height="25px">
				<th ><input id="check_all1" type="checkbox" /></th>
				<th>CARRIER NAME</th>
					<th>INACTIVE</th>
				</tr>
			</thead>	
		<tbody>
			<s:set var="index1" value="0" />
	 			<s:iterator id="carrierTable" value="eshipCarrierList" status="rowstatus">
					<tr height="25px">
					
						<input type="hidden" id="eshipCarrierId" name="eshipCarrierId" value="<s:property value='eshipCarrierId'/>" />
						<!-- <td ><input id="check_box" type="checkbox" +name="check_box"/></td> -->
					<td><input class="dataTable-checkbox1" type="checkbox" id="checkboxDatatableBodyId" name="carrierCheckBox" value="<s:property value='#rowstatus.index'/>"/></td>
						<td id="eshipCarrier_<s:property value="#rowstatus.index" />"><s:property value="eshipCarrierName" /></td>
						<td><s:select value="disabledFlag"  id="evenInactiveId%{index1}"  name="carrierDisabledFlag" headerKey="1" list="{'true','false'}"   cssClass="text_01_combo_small disabled%{serviceId}" cssStyle="width: 60px"/></td>
				</tr>
		</s:iterator>
			</tbody>

	</table>	
	</div> --%>
<div id="accnt_bottom_table"  style="background-color:#FFF;" >
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left;  background-color:#FFF; width:100%; height:auto;">
    <thead>
	<tr height="25px">
	<th ><input id="check_all" type="checkbox" /></th>
	<th><span style="width:40px !important; float:left;"><mmr:message messageId="label.ghead.id"/></span></th>
	<th><span style="width:40px !important; float:left;"><mmr:message messageId="label.ghead.id"/></span></th>
	<th><mmr:message messageId="label.ghead.carrier"/></th>
	<th><mmr:message messageId="label.ghead.service"/></th>
	<th><span style="width:60px !important; float:left;"><mmr:message messageId="label.ghead.oco"/></span></th>
	<th><span style="width:60px !important; float:left;"><mmr:message messageId="label.ghead.dco"/></span></th>
	<th><span style="width:60px !important; float:left;"><mmr:message messageId="label.ghead.type"/></span></th>
	<th><span style="width:65px !important; float:left;"><mmr:message messageId="label.ghead.from"/></span></th>
	<th><span style="width:60px !important; float:left;"><mmr:message messageId="label.ghead.to"/></span></th>

<th><span style="width:85px !important; float:left;"><mmr:message messageId="label.ghead.variable"/></span></th>
	<th style="text-align:right;padding-right:18px">%</th>
	<th style="text-align:right;padding-right:18px">$</th>
		<th><span style="width:82px !important; float:left;"><mmr:message messageId="label.ghead.inactive"/></span></th>
 
	</tr>
	</thead>	
	<tbody>
<s:set var="index" value="0" />
	 <s:iterator id="markupTable" value="#session.MARKUPLIST" status="rowstatus">
								<tr>
								<s:if test="#rowstatus.even == true">
								<td><input class="dataTable-checkbox" type="checkbox" id="checkboxDatatableBodyId" name="markupCheckBox" value="<s:property value='#index'/>"/>
									<input type="hidden" id="serviceId<s:property value='#index'/>" name="serviceId<s:property value='#index'/>" value="<s:property value='serviceId'/>" />
									<input type="hidden" name="fromCountryCode<s:property value='serviceId'/>" value="<s:property value='fromCountryCode'/>" />
									<input type="hidden"  name="toCountryCode<s:property value='serviceId'/>" value="<s:property value='toCountryCode'/>" />
									<input type="hidden"  name="customerId<s:property value='serviceId'/>" value="<s:property value='customerId'/>" />
									<input type="hidden"  name="serviceType<s:property value='serviceId'/>" value="<s:property value='serviceType'/>" />
									<input type="hidden"  name="businessId<s:property value='serviceId'/>" value="<s:property value='businessId'/>" />
									<input type="hidden"  name="businessToId<s:property value='serviceId'/>" value="<s:property value='businessToId'/>" />

								</td>								
								<td ><s:property value="businessId"/></td>
								<td ><s:property value="businessToId"/></td>
								<td  style="text-align: left;"><span title="<s:property value="carrierName"/>"></span><div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="carrierName"/></div></td>
								<td  style="text-align: left;"><span title="<s:property value="serviceName"/>"></span><div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="serviceName"/></div></td>
								
								<td ><s:property value="fromCountryCode"/></td>
								<td ><s:property value="toCountryCode"/></td>
								<td >
									<s:if test='%{type == 1}' >
										Markup
									</s:if>														
									<s:elseif test='%{type == 2}' >
										Markdown
									</s:elseif>
								</td>
								<td ><s:property value="fromCost"/></td>
								<td ><s:property value="toCost"/></td>
								<td><s:select value="%{variable}" id="evenVarId%{index}" name="variable" list="#{'0':'Weight/Skid','1':'Cost $'}" theme="simple"  disabled="false" /></td>
								<td ><s:textfield size="5" id="evenPercId%{index}"   key="markupPercentage" name="markupPercentage" onchange="ontimevalidate(this.value,'perc','even',%{index});" cssClass="text_02_tf_small percentage%{serviceId}" cssStyle="text-align:right; padding-right:5px;"/></td>
								<td><s:textfield size="5"  id="evenFlatId%{index}"   key="markupFlat" name="markupFlat" onchange="ontimevalidate(this.value,'flat','even',%{index});" cssClass="text_02_tf_small flat%{serviceId}" cssStyle="text-align:right; padding-right:5px;"/></td>
								<td ><s:select key="disabledFlag"   id="evenInactiveId%{index}"  name="disabledFlag" headerKey="1" list="{'true','false'}"   cssClass="text_01_combo_small disabled%{serviceId}" cssStyle="width: 60px"/></td>		
								</s:if>
								<s:else>
								<td><input class="dataTable-checkbox" type="checkbox" id="checkboxDatatableBodyId" name="markupCheckBox" value="<s:property value='#index'/>"/>
									<input type="hidden" id="serviceId<s:property value='#index'/>" name="serviceId<s:property value='#index'/>" value="<s:property value='serviceId'/>" />
									<input type="hidden" name="fromCountryCode<s:property value='serviceId'/>" value="<s:property value='fromCountryCode'/>" />
									<input type="hidden"  name="toCountryCode<s:property value='serviceId'/>" value="<s:property value='toCountryCode'/>" />
									<input type="hidden"  name="customerId<s:property value='serviceId'/>" value="<s:property value='customerId'/>" />
									<input type="hidden"  name="serviceType<s:property value='serviceId'/>" value="<s:property value='serviceType'/>" />
									<input type="hidden"  name="businessId<s:property value='serviceId'/>" value="<s:property value='businessId'/>" />
									<input type="hidden"  name="businessToId<s:property value='serviceId'/>" value="<s:property value='businessToId'/>" />
								
								</td>
									<td ><s:property value="businessId"/></td>
									<td ><s:property value="businessToId"/></td>
									
								<td  style="text-align: left;"><span title="<s:property value="carrierName"/>"></span><div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="carrierName"/></div></td>	
								<td style="text-align: left;"><span title="<s:property value="serviceName"/>"></span><div style="width:150px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="serviceName"/></div></td>
								<td ><s:property value="fromCountryCode"/></td>
								<td ><s:property value="toCountryCode"/>
								</td>
								<td>
									<s:if test='%{type == 1}' >
										Markup
									</s:if>														
									<s:elseif test='%{type == 2}' >
										Markdown
									</s:elseif>

								</td>
								<td><s:property value="fromCost"/></td>
								<td><s:property value="toCost"/></td>									

									<td><s:select value="%{variable}" id="oddVarId%{index}" name="variable" list="#{'0':'Weight/Skid','1':'Cost $'}" theme="simple"  disabled="false" /></td>
								<td ><s:textfield size="5" id="oddPercId%{index}" class="perc"  onchange="ontimevalidate(this.value,'perc','odd',%{index});" key="markupPercentage" name="markupPercentage" cssClass="text_02_tf_small percentage%{serviceId}" cssStyle="text-align:right; padding-right:5px;"/></td>
                                <td><s:textfield size="5"  id="oddFlatId%{index}" class="flat"  onchange="ontimevalidate(this.value,'flat','odd',%{index});" key="markupFlat" name="markupFlat" cssClass="text_02_tf_small flat%{serviceId}" cssStyle="text-align:right; padding-right:5px;"/></td>
                                <td ><s:select key="disabledFlag"  id="oddInactiveId%{index}" class="inactive"  name="disabledFlag" headerKey="1" list="{'true','false'}"   cssClass="text_01_combo_small disabled%{serviceId}" cssStyle="width: 60px;"/></td>
								</s:else>								
							</tr>
							<s:set var="index" value="#index+1" />
						</s:iterator>
			 </tbody>
			 </table>
            
</div>
		<div id="markup_res_tbl_end"></div>
	</div>
	</div>
</s:form>


<div class="content_table" >
	&nbsp;
</div>
		
		
</div>
</div>
</div>
</div>
		
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>


<script type="text/javascript">
var customers = {
		<s:iterator value='#session.fromcustomersList'>
		"<s:property escape='false' value='value' />": "<s:property escape='false' value='key' />",
      </s:iterator>
 };
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });
	// Initialize autocomplete with local lookup:
     $('#customerautocomplete').newautocomplete({
        lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
			$('#custId').val(suggestion.data);
			}
		}
		    });
	
	function compare(a,b) {
	  if (a.value < b.value)
		 return -1;
	  if (a.value > b.value)
		return 1;
	  return 0;
	}

</script>

		
	