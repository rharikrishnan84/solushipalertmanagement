<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<head>
<sx:head />
</head>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	
<script type="text/javascript" src="/shiplinx/mmr/scripts/jquery.js"></script>
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
<SCRIPT language="JavaScript">


	function disp(){
		if (document.getElementById('carrierName').value=='UPS') 
		{ 
			//alert("11");
			document.getElementById('UPSBlock').disabled = false; 
			document.getElementById('FEDEXBlock').disabled = true; 
			document.getElementById('UPSBlockTest').disabled = false; 
			document.getElementById('FEDEXBlockTest').disabled = true; 
		} 
		else { 
			//alert("22");
			document.getElementById('UPSBlock').disabled = true; 
			document.getElementById('FEDEXBlock').disabled = false; 
			document.getElementById('UPSBlockTest').disabled = true; 
			document.getElementById('FEDEXBlockTest').disabled = false; 
		}
	}
	function edit(id)
	{
	 document.carrierform.action = "editCarrierAccount.action?option=edit&id="+id;
	 document.carrierform.submit();
	}

	function deleteAccount(id)
	{
	 document.carrierform.action = "deleteCarrierAccount.action?id="+id;
	 document.carrierform.submit();
	}
	function submitform()
	{
	 document.carrierform.action = "saveCarrierAccount.action";
	 document.carrierform.submit();
	}
	
	function findAll()
	{
	 document.carrierform.action = "getCustomerAccountInfo.action";
	 document.carrierform.submit();
	 
	}
	function resetform() {
		document.carrierform.action = "getCustomerAccountInfo.action?method=reset";
	 	document.carrierform.submit();
	}	
	var cName = "";
	
	
</SCRIPT> 
<script language="JavaScript">
	
		$(document).ready(function() {
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
		} );
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
	<script type="text/javascript">	
	
		function editCustomerAccount(){
			var editUserId = document.getElementsByName("editCustomerCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please select at least one');
		   }
		   else{
			var i,txt;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
					txt = editUserId[i].value ;				
				}
			}
			
			document.getElementById("carrierformId").action = "editCarrierAccount.action?option=edit&id="+txt;
			document.getElementById("carrierformId").submit();
			}
		}
	</script>
		<script type="text/javascript">
		function deleteCustomerAccount(id){
		
			var deleteUserId = document.getElementsByName("editCustomerCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteUserId.length;i1++){
			if (deleteUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please select at least one');
		   }
		   else{
				var i,txt;
				for (i=0;i<deleteUserId.length;i++){
					if (deleteUserId[i].checked){
						txt = deleteUserId[i].value ;					
					}
				}
				
				/*
				var del=confirm('Do you really want to delete the CustomerAccount?');
				if (del==true){
					document.getElementById("carrierformId").action = "deleteCarrierAccount.action?id="+txt;
					document.getElementById("carrierformId").submit();
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
				msg.innerHTML = "Do you really want to delete the CustomerAccount?";
				//var status;	
				
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
				
				
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.getElementById("carrierformId").action = "deleteCarrierAccount.action?id="+txt;
					document.getElementById("carrierformId").submit();
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
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>

<body onmousemove="javascript:disp();">
<div class="content">
<div class="content_body" >
<div class="content_table" style="background-color:#fff">
<div class="form-container"> 
<s:form action="saveCarrierAccount" name="carrierform" id="carrierformId" style="margin-bottom:0px;">

<s:hidden name="customerCarrierId" value="%{customer.customerCarrier.carrierAccountId}"></s:hidden>




							<div class="content_table">
								<div class="content_header">
									<div class="cont_hdr_title">Customer</div>
									<div class="cont_hdrtitle_w"><s:property value="customer.name"/></div>
									<div class="form_buttons">
										<a href="javascript: findAll()">List All</a>
										<a href="javascript: resetform()">RESET</a>
										<a href="javascript: submitform()">SAVE</a>
										
									</div>
								</div>
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields">
											<label><s:label key="customerCarrier.carrierName"/>
											</label>
											<div class="controls"><span>:</span>
												<s:select   cssStyle="width:140px;" listKey="id" disabled="#session.edit"
												listValue="name" name="customer.customerCarrier.carrierId" list="#session.CARRIERS" 
												headerKey="-1" id="carrier" theme="simple"/>
											</div>
										</div>
										<s:if test="%{#session.edit}">
					<s:hidden value="%{customer.customerCarrier.carrierName}" name="customer.customerCarrier.carrierName" />
				</s:if>
										<div class="fields">
											<label>Country </label>
											<div class="controls"><span>:</span>
												<s:if test="#session.edit">
			<s:select    value="%{customer.customerCarrier.country}" id="countryName"
				name="customer.customerCarrier.country" list="#{'US':'United States','CA':'Canada'}" cssStyle="width:140px;"/>
			</s:if>
			<s:else>
			<s:select   value="%{customer.customerCarrier.country}" id="countryName"
				name="customer.customerCarrier.country" list="#{'US':'United States','CA':'Canada'}" cssStyle="width:140px;"/>
			</s:else>
											</div>
										</div>
									</div>
								</div>
								<div class="content_header">
									<div class="cont_hdr_title"><!--<mmr:message messageId="label.account.details"/>-->			Account Details
									</div>
									<div class="cont_hdrtitle_l">
										Live Credentials 
									</div>
								</div>
								<div class="cont_data_body">
									<!--<div class="rows">
										<div class="fields">
											<label>Live Credentials </label>
										</div>
									</div>-->
									
									<div class="rows">
										<div class="fields">
											<s:label key="customerCarrier.accountNumber1" /> 
											<div class="controls"><span>:</span><s:textfield size="20" key="customer.customerCarrier.accountNumber1"
					name="customer.customerCarrier.accountNumber1" value="%{customer.customerCarrier.accountNumber1}"   /></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.accountNumber2" />
											<div class="controls"><span>:</span><s:textfield size="20" key="customer.customerCarrier.accountNumber2"
					name="customer.customerCarrier.accountNumber2" value="%{customer.customerCarrier.accountNumber2}"   /></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.property1" />
											<div class="controls"><span>:</span><s:textfield size="20"  id="FEDEXBlock" key="customerCarrier.property1"
					name="customer.customerCarrier.property1" value="%{customer.customerCarrier.property1}" cssClass="contorls" disabled="false"/></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.property2" />
											<div class="controls"><span>:</span>
											<s:textfield size="20" key="customer.customerCarrier.property2"
					name="customer.customerCarrier.property2" value="%{customer.customerCarrier.property2}"/>
										</div></div>
										<div class="fields">
											<s:label key="customerCarrier.property3" />
											<div class="controls"><span>:</span><s:textfield size="20" key="customer.customerCarrier.property3" 
					name="customer.customerCarrier.property3" value="%{customer.customerCarrier.property3}"   /></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.property4" />
											<div class="controls"><span>:</span><s:textfield size="20" key="customer.customerCarrier.property4" 
					name="customer.customerCarrier.property4" value="%{customer.customerCarrier.property4}"   /></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.property5" />
											<div class="controls"><span>:</span><s:textfield size="20" key="customer.customerCarrier.property5" 
					name="customer.customerCarrier.property5" value="%{customer.customerCarrier.property5}"   /></div>
										</div>
										<div class="fields">
											<s:label key="customerCarrier.defaultaccount" />
											<div class="controls"><span>:</span><s:checkbox key="customerCarrier.defaultaccount"
						name="customer.customerCarrier.defaultAccount" value="%{customer.customerCarrier.defaultAccount}" cssClass="check"/></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.carrierAccount.house"/></label>
											<div class="controls"><span>:</span><s:checkbox key="customerCarrier.live"
						name="customer.customerCarrier.live" value="%{customer.customerCarrier.live}" cssClass="check"/></div>
										</div>
									</div>
								</div>
								
							</div>
							
					</div>
				</div>
			</div>	
						
<!-- table -->						
<div class="content_body">
<div class="content_table" style="background-color:#fff">
<div class="form-container">						
<div id="srchusr_results" >

<div id="srchusr_res">
<span>Active Carriers</span>
</div>


	<div class="form_buttons" id="button_account" >
	<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
		<!--<s:submit onclick="editCustomerAccount();" value="EDIT"/>
		<s:submit onclick="deleteCustomerAccount();" value="DELETE"/>-->
		<li><a href="#" onclick="editCustomerAccount();">EDIT</a></li>
		<li><a href="#" onclick="deleteCustomerAccount();">DELETE</a></li>
		</ul>
	</div>
	<!--<img src="<s:url value="/mmr/images/panelResults_top.png" includeContext="true" />" style="height:35px;width:848px;margin-top:-24px;" alt="logo"> 	-->
</div>
	<div id="srchusr_rslt_stmnt">
		<div id="accnt_bottom_table" >
			<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
				<thead>
					<tr>
						<th style="width:10px"><input id="check_all" type="checkbox" /></th>
						<th>CARRIER</th>
						<th>COUNTRY</th>
						<th>ACCOUNT#1</th>
						<th>ACCOUNT#2</th>
						<th>DEFAULT</th>
						<th>HOUSE ACOUNT</th>
					</tr>
				</thead>	
				<tbody>
				 <s:iterator id="account_table" value="customerCarrierAccountList" status="rowstatus">
						
					<tr>
						<td class="even1"><input class="dataTable-checkbox" type="checkbox" id="checkboxDatatableBodyId" name="editCustomerCheckBox" value="<s:property value='carrierAccountId'/>"/></td>
						<td> <s:property value="carrierName"/></td>
						<td><s:property value="country"/> </td>
						<td> <s:property value="accountNumber1"/></td>
						<td> <s:property value="accountNumber2"/></td>
						<td><s:property value="defaultAccount"/> </td>
						<td> <s:property value="live"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>        
		</div>
		<s:hidden name="searchAllFlag" id="searchAll" value='true'/>
		</s:form>
		
		<div class="content_table" >
		&nbsp;
		</div>
		
		
	</div>
</div>
</div>
</div>
</div>

		

</body>
