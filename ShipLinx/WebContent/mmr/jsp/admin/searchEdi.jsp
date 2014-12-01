<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<html> 
 
<head> 
	<title><s:text name="edi.title"/></title> 
	<sj:head jqueryui="true" />
	<sx:head />
	
</head> 
<body> 

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/orderManager.js">
	<script type="text/javascript" src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
</script>	
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
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
#customersautocomplete,#auto{ background-position: 175px 4px; background-size:8px 8px; }
#customerautocomplete,#auto{ background-position: 115px 4px; background-size:8px 8px; }
</style> 	

<script type="text/javascript">
	
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

<SCRIPT language="JavaScript">
	function searchEdi()
	{
	 document.searchform.action = "searchedi.action";
	 document.searchform.submit();
	}
	
	function releaseAll() {
		var value = document.getElementById("customersautocomplete").value;
		if (value == null || value == "") {
			alert("Please select EDI File to Release...");
		} else {
			if (confirm("Do you really want to release charges for " + value +" EDI File?")) {
				 document.searchform.action = "release.edi.file.action?method=releaseEdiFile&ediFileName=" + value;
				 document.searchform.submit();	
			}
		}
//		alert("Value is::"+value);
//		var value = autoCompleter.getText();
//		alert("Text is::"+value);		
	}
				function autoLink(){
		
			var editId = document.getElementsByName("ediCheck");
			var i1,txt1 = 0;
		   for (i1=0;i1<editId.length;i1++){
			if (editId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please select at least one');
		   }
		   else{
				var i,ediitemid,invoicenumber;
				for (i=0;i<editId.length;i++){
					if (editId[i].checked){
						ediitemid = editId[i].getAttribute("ediItemId") ;
						invoicenumber = editId[i].getAttribute("invoiceNumber") ;
										
					}
				}
				window.location.href="autolinked.shipment.action?method=autolinkedShipment&ediItemId="+ediitemid+"&ediInvoiceNumber="+invoicenumber;
			}
		}
		
			function unlink(){
		
			var editId = document.getElementsByName("ediCheck");
			var i1,txt1 = 0;
		   for (i1=0;i1<editId.length;i1++){
			if (editId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please select at least one');
		   }
		   else{
				var i,ediitemid,invoicenumber;
				for (i=0;i<editId.length;i++){
					if (editId[i].checked){
						ediitemid = editId[i].getAttribute("ediItemId") ;
						invoicenumber = editId[i].getAttribute("invoiceNumber") ;
										
					}
				}
				window.location.href="unlinked.shipment.action?method=unlinkedShipment&ediItemId="+ediitemid+"&ediInvoiceNumber="+invoicenumber;
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
<div class="content">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form id="searchform" cssClass="form" action="searchedi.action" name="searchform">

<div class="content_body" >	
						<div class="content_table" > 
							<div class="content_header">
								<div class="cont_hdr_title"><mmr:message messageId="menu.admin.searchedi"/></div>
								<div class="form_buttons" >	
									<a href="javascript: searchEdi()"><mmr:message messageId="label.searchedi.search"></mmr:message></a>
									<a href="javascript: releaseAll()"><mmr:message messageId="label.searchedi.releaseall"/> </a>
									<a href="searchedi.action?method=reset"><mmr:message messageId="label.searchedi.reset"/></a>
							</div>
							</div>		
							<div class="cont_data_body">
								<div class="rows">
									<div class="fields_topdown" style="width:133px !important">
										<label><mmr:message messageId="label.searchedi.fromdate"/> </label>
										<div class="controls">
											<s:textfield name="ediItem.fromDate" id="f_date_c" onClick="selectDate('f_date_c','f_trigger_c');" 
											 readonly="readonly"/>
										</div>
									</div>
									<div class="fields_topdown" style="width:133px !important">
										<label><mmr:message messageId="label.searchedi.todate"/> </label>
										<div class="controls">
										<s:textfield name="ediItem.toDate" onClick="selectDate('t_date_c','t_trigger_c');" id="t_date_c" 
											 readonly="readonly"/>
											
										</div>
									</div>
									<div class="fields_topdown" style="width:133px !important">
										<label><mmr:message messageId="label.searchedi.invoice"/> </label>
										<div class="controls">
											<input id="custId" type="hidden" value="" name="id">
<input id="auto" type="text" value="" name="inovice"  autocomplete="off">
										</div>
									</div>
									<div class="fields_topdown" style="width:200px !important">
										<label><mmr:message messageId="label.searchedi.filename"/> </label>
										<div class="controls">
										<s:url id="fileNameList" action="listEdiFileName" />
		               <input id="auto" type="hidden" value="" name="id">
<input id="customersautocomplete" type="text" value="" name="FileName" autocomplete="off" style="width:190px ">
										</div>
									</div>
									<div class="fields_topdown">
										<label><mmr:message messageId="label.searchedi.carrier"/></label>
										<div class="controls">
											<s:select cssClass="text_01_combo_big" cssStyle="width:128px;" listKey="id" listValue="name" 
											name="ediItem.carrierId" headerKey="-1"  list="#session.CARRIERS" 
											id="firstBox" />
										</div>
									</div>
									<div class="fields_topdown">
										<label><mmr:message messageId="label.searchedi.status"/></label>
										<div class="controls">
											<s:select value="%{ediItem.statusText}" id="statusText"  
											name="ediItem.statusText" list="#session.EDI_STATUS_LIST" theme="simple" />
										</div>
									</div>
									<s:hidden id="ediFIleName" name="ediFIleName"/>

								</div>
							</div>
						</div>
					</div>					
</div>




<div class="content_body">
<div class="content_table" style="background-color:#fff">

	<div id="edi_tab"></div>
	<div id="srchinv_results">	
		<div id="srchusr_res"><span>EDI Invoice List</span></div>
		<!--<div id="edi_results" style="float:left; width:200px; margin-left:50px; font-size:14px; padding:20px 0px 0px 0px;"><span id="edi_res"><mmr:message messageId="label.search.results"/></span></div>-->
		<div class="form_buttons" style="float:right;">
        <a href="#" id="actiondown" ><mmr:message messageId="label.searchedi.action"/> <span style="font-size:14px;">&#9660;</span></a>
		<a href="#" id="actionup" ><mmr:message messageId="label.searchedi.action"/> <span style="font-size:14px;">&#9650;</span></a>			
			<ul id="actionmenu">
            <li><a href="javascript:autoLink();"><mmr:message messageId="label.searchedi.autolink"/></a></li>
			<li><a href="javascript:unlink();"><mmr:message messageId="label.searchedi.unlink"/></a></li>
			
			</ul>
		</div>	
	</div>
	
	

	<!--<div id="edi_tab"></div>
	<div id="edi_res"><mmr:message messageId="label.search.results"/></div>
	<div id="edi_results">	
		<div class="form_buttons">
			
				<a href="javascript:autoLink();">AUTOLINK</a>
				<a href="javascript:unlink();">UNLINK</a>
		</div>		
				
	</div>-->
	<div id="edi_result_tbl">
			<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
			<thead>
			<tr>
				<th><input id="check_all" type="checkbox" /></th>
				<th><mmr:message messageId="label.ediinvoice.id"/></th>
				<th><mmr:message messageId="label.ediinvoice.carrier"/></th>
				<th><mmr:message messageId="label.edinvoice.account"/></th>
				<th><mmr:message messageId="label.edinvoice.invoice"/></th>
				<th style="width:130px !important;"><mmr:message messageId="label.ediinvoice.invoicedate"/> </th>
				<th style="width:130px !important;"><mmr:message messageId="label.ediinvoice.processed"/></th>
				<!--<th style="width:150px !important;">Detail Seq. #</th>-->
				<th><mmr:message messageId="label.ediinvoice.amount"/></th>
				<th><mmr:message messageId="label.ediinvoice.filename"/></th>
				<th><mmr:message messageId="label.ediinvoice.status"/></th>
				<th><mmr:message messageId="label.ediinvoice.message"/></th>
				</tr>
			</thead>
		<tbody>	
		<s:iterator id="ediTable" value="ediItemList" status="row">
			<tr>
				<td><input  class="dataTable-checkbox" ediItemId="<s:property value='id'  />" invoiceNumber="<s:property value='invoiceNumber'/>" name="ediCheck" type="checkbox" /> 
				
				</td>
				<td><s:property value="id"/></td>
				<td style="text-align: left;" <span title="<s:property value="%{getCarrierName(carrierId)}"/>"></span><div style="width:70px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="%{getCarrierName(carrierId)}"/></div></td>
				<td><s:property value="accountNumber"/></td>
				<td><s:property value="invoiceNumber"/></td>
				<td style="width:130px !important;"><s:date name="invoiceDate" format="dd/MM/yyyy"/></td>
				<td style="width:130px !important;"><s:date name="processedDate" format="dd/MM/yyyy" /></td>
				<td style="width:100px !important; text-align:right;">
					<s:text name="format.money" ><s:param name="value" value="totInvoiceAmount" /></s:text>
				</td>
				<!--<td style="width:150px !important;"><s:property value="detailSeqNumber"/></td>-->
				<td style="text-align: left;" <span title="<s:property value="ediFileName"/>"></span><div ><s:property value="ediFileName"/></div></td>
				<td><s:property value="%{getEdiItemStatus(status)}"/></td>
				<td style="text-align: left;" <span title="<s:property value="%{getMessage(message)}"/>"></span><div style="width:70px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="%{getMessage(message)}"/></div></td>
			</tr>
			</s:iterator>
		</tbody>
</table>		
		</div>
	<div id="edi_res_tbl_end"></div>
	</s:form>
	<div class="content_table" >
		&nbsp;
	</div>
</div>
</div>
</div>
		
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>

<script type="text/javascript">
var invoices = {
		
		 <s:iterator value='ediItemList' status='invoiceStatus'>
		 "<s:property value='id' />": "<s:property value='invoiceNumber' />"
		 <s:if test="!#invoiceStatus.last">,</s:if>
      </s:iterator>
 };
 delete invoices["0"];
    var invoicesArray = $.map(invoices, function (value, key) { return { value: value, data: key }; });

    // Initialize autocomplete with local lookup:
	$('#auto').newautocomplete({
        lookup: invoicesArray,
        minChars: 0,
        onSelect: function (suggestion) {
        if(suggestion.value != ""){
            $('#ediFIleName').val(suggestion.data);

            }
        },		
                onInvalidateSelection: function() {
                    $('#ediFIleName').val("");
                }
    });


    
</script>

<script type="text/javascript">
var customers = {
		 <s:iterator value='ediItemList' status='invoiceStatus'>
		 "<s:property value='id' />": "<s:property value='ediFileName' />"
		  <s:if test="!#invoiceStatus.last">,</s:if>
      </s:iterator>
 };
	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });
	// Initialize autocomplete with local lookup:
     $('#customersautocomplete').newautocomplete({
        lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
            $('#fileNameList').val(suggestion.data);
			}
		},		
		        onInvalidateSelection: function() {
		            $('#fileNameList').val("");
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

		
