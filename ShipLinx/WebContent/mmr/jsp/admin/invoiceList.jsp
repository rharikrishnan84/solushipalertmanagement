<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
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
	.grid_table_body{overflow-y:hidden}
	table{overflow-y:hidden}
	</style>
<head>
<sx:head />
<script>
$(function() {
$('.dropdown-toggle').click(function(){
  $(this).next('.dropdown').toggle();
});

$(document).click(function(e) {
  var target = e.target;
  if (!$(target).is('.dropdown-toggle') && !$(target).parents().is('.dropdown-toggle')) {
    $('.dropdown').hide();
  }
});

});
</script>
</head>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
		 
			$("#check_all").click(function(){
				if(this.checked){
					$(".check_uncheck_row").each(function(){
						this.checked=true;		
					});
				}else{
					$(".check_uncheck_row").each(function(){
						this.checked=false;		
					});
				}
			/* alert('sdf');
				var temp=$(this).attr("checked");
				if(temp){
			    $(".check_uncheck_row").attr("checked","checked");
				}
				else{
				$(".check_uncheck_row").removeAttr("checked");
				} */
			});
			
			/*$('.gridedit').click(function(){
				var check = $(".dataTable-checkbox:checked").length;
				if(check < 1){
					alert('Please select at least one checkbox');
					location.href="admin/search.products.action?method=new#";
				}
				
			});*/
			
			
			
			
		} );
		
		function submitform_email() {
					  var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
					  var i, txt = 0;
					  for (i = 0; i < uploadMarkupId.length; i++) {
					   if (uploadMarkupId[i].checked) {
					    txt += 1;
					   }
					  }
					  if (txt < 1) {
					   alert('Please select at least one');
					   
					  } else {
						  $('#loader').css('display','block');
						  $('#loaderImg').css('display','block');  
					   var i1, shipmentid, value_checked, stored_value = "";
					   for (i1 = 0; i1 < uploadMarkupId.length; i1++) {
					    if (uploadMarkupId[i1].checked) {
					     shipmentid = uploadMarkupId[i1].value;
						 invoiceId = document.getElementById("selectedInvoices["+shipmentid+"].invoiceId").value;
					     //value_checked = document.getElementById("shipmentcheckbox"+shipmentid).value;
					     //confirm("this is value is value checked variable "+value_checked+"  this is value is from shipmentid "+shipmentid);
					     //stored_value = stored_value + value_checked + ",";
					     stored_value=stored_value+invoiceId+",";
					    }
					   }
					   window.location.href = "sendNotification.email.action?stored_value="+ stored_value;
			
					  }
					 }
	</script>

<script type="text/javascript">
	
	$(window).load(function() {
	
	$('#sample1').dataTable({
		
		aoColumnDefs: [
	                   {
	                      bSortable: false,
	                      "bSearchable": false,
	                      aTargets: [ 0 ]
	                   }
	                 ]
	}); 
	
	
 });
	</script>
	<script>
	function csv(){			
			
			var editUserId = document.getElementsByClassName("check_uncheck_row");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			
			if(txt1 < 1){
				alert('Please select at least one');
			}
			else if(txt1>1){
				alert('Please check atmost one');
			}
			else{
				
				var i,txt,invoiceId;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;
						invoiceId = document.getElementById("selectedInvoices["+txt+"].invoiceId").value;
					}
				}
				
				//window.location.href="download.csv.invoice.action?invoiceId="+invoiceId;
				var downloadId =new Date().getTime().toString();
				window.location.href = "download.csv.invoice.action?invoiceId="+invoiceId +"&downloadId="+downloadId;
				$('#loader').css('display','block');
				$('#loaderImg').css('display','block');
				//setTimeout(function(){location.reload();}, 15000);
				$('#actionmenu').css('display','none');
				var cookiePattern = new RegExp( ( "downloadID=" + downloadId ), "i" );
				//Set the timer to search the cookie
							var cookieTimer = setInterval(function(){checkCookies()}, 500 );
								//Check the cookie for every five hundred milliSeconds
								function checkCookies() {
				                    if ( document.cookie.search(cookiePattern)>= 0 ) {
				                     //Once find the cookie then turn off the timer and hide the gif loader finally reload the window.
				                        clearInterval( cookieTimer );
				                        $('#loaderImg').hide();
				                        location.reload();
				                        
				                        return(
				                            console.log( "Download complete!!" )
				                        );
				                   }
				                    else{
				                    	 console.log(
				                                 "File still downloading...",
				                                 new Date().getTime()
				                             );
				                    }
								}
				
			}
			
			
			
		}
	
	function pdf2(){
	
		var editUserId = document.getElementsByClassName("check_uncheck_row");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			
			if(txt1 < 1){
				alert('Please select at least one');
			}
			else if(txt1>1){
				alert('Please check atmost one');
			}
			else{
				
				var i,txt,invoiceId;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;
						invoiceId = document.getElementById("selectedInvoices["+txt+"].invoiceId").value;
					}
				}
				
				//window.location.href="print.invoice.action?invoiceId="+invoiceId;
				var downloadId =new Date().getTime().toString();
				window.location.href ="print.invoice.action?invoiceId="+invoiceId+"&downloadId="+downloadId;;
				$('#loader').css('display','block');
				$('#loaderImg').css('display','block');
//				setTimeout(function(){location.reload();}, 15000);
				$('#actionmenu').css('display','none');
								var cookiePattern = new RegExp( ( "downloadID=" + downloadId ), "i" );
								//Set the timer to search the cookie
								var cookieTimer = setInterval(function(){checkCookies()}, 500 );
								//Check the cookie for every five hundred milliSeconds
								function checkCookies() {
				
				                    if ( document.cookie.search(cookiePattern)>= 0 ) {
				                    	//Once find the cookie then turn off the timer and hide the gif loader finally reload the window.
				                        clearInterval( cookieTimer );
				                        $('#loaderImg').hide();
				                        location.reload();
				                        
				                        return(
				                            console.log( "Download complete!!" )
				                        );
				                    }
				                    else{
				                    	 console.log(
				                                 "File still downloading...",
				                                 new Date().getTime()
				                             );
				                    }
								}

			}
			
			
			
		}
	
	function deletepdf(){
						var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
						  var i, txt = 0;
						  for (i = 0; i < uploadMarkupId.length; i++) {
						   if (uploadMarkupId[i].checked) {
						    txt += 1;
						   }
						  }
						  if (txt < 1) {
						   alert('Please select at least one');
						   
						  } else {
							  /* $('#loader').css('display','block');
							  $('#loaderImg').css('display','block');   */
						   var i1, shipmentid, value_checked, stored_value = "";
						   for (i1 = 0; i1 < uploadMarkupId.length; i1++) {
						    if (uploadMarkupId[i1].checked) {
						     shipmentid = uploadMarkupId[i1].value;
							 invoiceId = document.getElementById("selectedInvoices["+shipmentid+"].invoiceId").value;
						     //value_checked = document.getElementById("shipmentcheckbox"+shipmentid).value;
						     //confirm("this is value is value checked variable "+value_checked+"  this is value is from shipmentid "+shipmentid);
						     //stored_value = stored_value + value_checked + ",";
						     stored_value=stored_value+invoiceId+",";
						    }
						   }
						   window.location.href = "delete.invoice.pdf.action?stored_value="+ stored_value;
					
						  }
			}
		
		
		
		function editpayment(){
		
		
		
		
			var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
			
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
				var i,paymentstatus,invoiceid;
				for (i=0;i<uploadMarkupId.length;i++){
					if(uploadMarkupId[i].checked){
					var selectedRecord = uploadMarkupId[i].value;
						invoiceId = document.getElementById("selectedInvoices["+selectedRecord+"].invoiceId").value;
						paymentstatus = document.getElementById("selectedPaymentStatus["+selectedRecord+"]").value;
						
						
					}
					
					
					
				}
				if((paymentstatus==10)||(paymentstatus==20)){
						
						window.location.href="edit.invoice.action?invoiceId="+invoiceId;
					}
			else{
						alert("you dont have permission to edit");
					}
		}
		}
		
		function delete1(){
			var uploadMarkupId = document.getElementsByClassName("check_uncheck_row");
			
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
				/*var del=confirm("Do you really want to delete the selected Invoice?");
				if(del==true){
					var i,paymentstatus,invoiceid;
					for (i=0;i<uploadMarkupId.length;i++){
						if(uploadMarkupId[i].checked){
							invoiceId = document.getElementById("selectedInvoices["+i+"].invoiceId").value;
							paymentstatus = document.getElementById("selectedPaymentStatus["+i+"]").value;	
						}
					}
				}
				if((paymentstatus==10)||(paymentstatus==20)){
					
					window.location.href="invoice.cancel.action?invoiceId="+invoiceId;
				}
				else{
					alert("you dont have permission to delete");
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
				
				msg = alertObj.appendChild(d.createElement("p"));
				
				msg.innerHTML = "Do you really want to delete the selected Invoice?";
				
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','40px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','40px');
				
				
				
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
						
						var i,paymentstatus,invoiceid;
						for (i=0;i<uploadMarkupId.length;i++){
						
							if(uploadMarkupId[i].checked){
							//alert('dghfjg
								var selectedRecord = uploadMarkupId[i].value;
								invoiceId = document.getElementById("selectedInvoices["+selectedRecord+"].invoiceId").value;
								paymentstatus = document.getElementById("selectedPaymentStatus["+selectedRecord+"]").value;	
								
							}
						}
						
						
						if((paymentstatus==10)||(paymentstatus==20)){
							
							window.location.href="invoice.cancel.action?invoiceId="+invoiceId;
							$('#loader').css('display','block');
							$('#loaderImg').css('display','block');
						}
						else{
							alert("you dont have permission to delete");
						}
					
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
<body>
<div class="content">
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
		<div id="srchinv_results">
				<div id="srchusr_res"><span><mmr:message messageId="label.customer.invoice.list"/></span></div>
			<%-- <div class="form_buttons">
			<a href="#" id="actiondown" ><mmr:message messageId="label.action"/> <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" ><mmr:message messageId="label.action"/> <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
				<li><a href="javascript: submitform_email();"><mmr:message messageId="label.send.notification"/></a></li>
				<li><a href="javascript:csv();"  ><mmr:message messageId="label.download.csv"/></a></li>
				<li><a href="javascript:pdf2();"  ><mmr:message messageId="label.pdf"/></a></li>
				<li><a href="javascript:editpayment();"><mmr:message messageId="label.list.edit"/></a></li>
				<li><a href="javascript:delete1();"><mmr:message messageId="label.delete"/></a></li>
				<li><a href="javascript:deletepdf();"><mmr:message messageId="label.delete.pdfFile"/></a></li>
				</ul>
			</div> --%>
			<div id="action_nav">
		<a class="dropdown-toggle" href="#"><label><mmr:message messageId="label.action"/></label><span style="font-size:14px; padding-left:5px;">&#9660;</span></a>
		<ul class="dropdown" >
		<li><a href="javascript: submitform_email();"><mmr:message messageId="label.send.notification"/></a></li>
				<li><a href="javascript:csv();"  ><mmr:message messageId="label.download.csv"/></a></li>
				<li><a href="javascript:pdf2();"  ><mmr:message messageId="label.pdf"/></a></li>
			<li><a href="javascript:editpayment();"><mmr:message messageId="label.list.edit"/></a></li>
				<li><a href="javascript:delete1();"><mmr:message messageId="label.delete"/></a></li>
				<li><a href="javascript:deletepdf();"><mmr:message messageId="label.delete.pdfFile"/></a></li>
				</ul>
 			</div>
		</div>
		<div id="srchinv_result_tbl">
			<table cellpadding="0" cellspacing="0"   border="0px" class="display" id="sample1" width="100%">
				<thead>
					<tr style="height:25px">
						<th style="width:30px !important; text-align:center;"><input type="checkbox" id="check_all"  name="check_uncheck" style="margin: 0 0 0 4px" /></th>
						<th><mmr:message messageId="label.invoices.inv"/></th>
						<th><mmr:message messageId="label.invoices.company"/></th>
						<th><mmr:message messageId="label.invoices.datecreated"/></th>
						<th style="text-align:right;padding-right:10px"><mmr:message messageId="label.invoices.amount"/></th>
						<s:if test="%{#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">
						<th style="text-align:right;padding-right:10px"><mmr:message messageId="label.invoices.cost"/></th>
						</s:if>
						<th style="text-align:right;padding-right:10px"><mmr:message messageId="label.invoices.tax"/></th>
						<th><mmr:message messageId="label.invoices.status"/></th>
					</tr>
				</thead>
				<tbody>
					<s:set var="index" value="0" />
					<s:iterator id="invoicetable" value="invoices" status="rowstatus">
					<tr>
						<s:set name="invoiceId" value="invoices[#index].invoiceId" />
						<td style="width:30px !important; text-align:center;">
						<input type="checkbox"  class="check_uncheck_row" id="shipmentcheckbox<s:property value='invoiceNum'/>" name="shipmentcheckbox<s:property value='invoiceNum'/>"  value="<s:property value='%{#index}'/>" />
						
							<s:hidden name="selectedInvoices[%{#index}].invoiceId" id="selectedInvoices[%{#index}].invoiceId" value="%{invoiceId}"/>
							<s:hidden id="selectedPaymentStatus[%{#index}]" value="%{paymentStatus}"/>

						</td>
						<td><s:property value="invoiceNum" /></td>
						<td><s:property value="customer.name" /></td>
						<td><s:date name="dateCreated" format="dd/MM/yyyy" /></td>
						<td style="text-align:right"><s:label name="curr" value="%{#session.customerDefaultCurrency}" /><s:property value="getText('{0,number,#,##0.00}',{invoiceAmount})"/></td>
						<s:if test="%{#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">
						<td style="text-align:right"><s:label name="curr" value="%{#session.customerDefaultCurrency}" /><s:property value="getText('{0,number,#,##0.00}',{invoiceCost})"/></td>
						</s:if>
		
		
						<td style="text-align:right"><s:label name="curr" value="%{#session.customerDefaultCurrency}" /><s:property value="getText('{0,number,#,##0.00}',{invoiceTax})"/></td>
						<td><s:property value="paymentStatusString" /></td>
					</tr>
					<s:set var="index" value="#index+1" />
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>	
</div>
</div>
</body>