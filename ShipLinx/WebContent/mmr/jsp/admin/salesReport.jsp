<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<html> 
<head> 
    <sj:head jqueryui="true" />
    <sx:head />
    <title><s:text name="edi.title"/></title> 
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
#customersautocomplete,#auto{ background-position: 115px 4px; background-size:8px 8px; }
</style> 
</head> 
<body> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

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
	
	
<SCRIPT language="JavaScript">
	function submitform()
	{	
	 document.searchInvoiceForm.action = "salesReport.action";
	 document.searchInvoiceForm.submit();
	 //return false;
	}
</SCRIPT>
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
<div class="content">
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form action="invoice.list" name="searchInvoiceForm" cssClass="form">
<div class="content_body" >	
	<div class="content_table" > 
		<div class="content_header">
			<div class="cont_hdr_title" id="srch_crtra"><mmr:message messageId="menu.admin.salesReport"/></div>
			<div class="form_buttons" id="searchinv_actions" >	
				<a href="javascript: submitform()"><mmr:message messageId="label.search.btn.search"/></a>
			</div>
		</div>		
		<div class="cont_data_body" id="geninv_srch_table">
			<div class="rows">
				<div class="fields_topdown">
					<label><mmr:message messageId="label.calendar.year" /></label>
					<div class="controls">
						<s:select
		list="#{'2012':'2012', '2013':'2013', '2014':'2014', '2015':'2015', '2016':'2016', '2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}"
		name="salesRecord.year"
		cssClass="text_01_combo_medium" />
					</div>
				</div>

				<div class="fields_topdown">
					<label><mmr:message messageId="label.calendar.month" /></label>
					<div class="controls">
						<s:select cssClass="text_01_combo_medium" cssStyle="width:123px;" list="months" 
				id="salesAgent" theme="simple" name="salesRecord.monthName" headerKey="0" headerValue="All" />
					</div>
				</div>

				<div class="fields_topdown">
					<label><mmr:message messageId="label.currency" /></label>
					<div class="controls">
						<s:select
		list="currencyList"
		name="salesRecord.currency" listKey="currencyCode" listValue="currencyCode"
		cssClass="text_01_combo_medium" />
		
					</div>
				</div>

						<div class="fields_topdown">
										<label><mmr:message messageId="label.commission.customername"/> </label>
										<s:url id="customerList" action="listCustomers" />
										<div class="controls">
											<input id="custId" type="hidden" value="" name="salesRecord.customerId">
<input id="customersautocomplete" type="text"  value="" name="customerName" style="width:300px;background-position: 286px 4px;"autocomplete="off">
										</div>
									</div>
				</div>		
			</div>
		</div>
	</div>
</div>	
</s:form>
	
	
				
<div class="content_body">
<div class="content_table" style="background-color:#fff">
<div id="srchusr_res"><span><mmr:message messageId="label.commission.salesresults"/> </span></div>	
	<div id="srchinv_results">	</div>	
<div id="srchinv_result_tbl">
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
    <thead>
	<tr height="25px">
	<th style="width:20px; text-align:center;"><input id="check_all" type="checkbox" /></th>
	<th style="width: 175px !important;"><mmr:message messageId="label.commission.year"/></strong></th>
	<th style="width: 220px ;"><mmr:message messageId="label.commission.month"/></th>
	<th style="text-align:right;padding-right:10px"><mmr:message messageId="label.commission.totalcost"/> </th>
	<th style="width: 175px !important;text-align:right;padding-right:10px"><mmr:message messageId="label.commission.totalrevenue"/> </th>
	</tr>
	</thead>
	<tbody>
	<s:set var="total" value="0" />
	<s:set var="totals" value="0" />
            <s:iterator id="salesRecordtable" value="salesRecords" status="rowstatus">
             <tr>
	             <td style="width:20px; text-align:center;"> <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/> </td>	   
	            <td><s:property value="year"/></td>
	            <td><s:property value="monthName"/></td>
	            <td style="text-align:right;"><s:label name="curr" value="%{#session.salesCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="totalCost" /></s:text></td>
				<td style="text-align:right;"><s:label name="curr" value="%{#session.salesCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="totalAmount" /></s:text></td>		 
				</tr>
				 <s:set var="total" value="%{#total+totalCost}"/> 
				  <s:set var="totals" value="%{#totals+totalAmount}"/> 
            </s:iterator>
			</tbody>
			<tfoot>
    <tr>
      <td><span style="float:left; width:65px;"><mmr:message messageId="label.commission.total"/>:</span></td>
      <td></td>
      <td></td>
	  <td style="text-align:right;"><s:label name="curr" value="%{#session.salesCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="%{#total}" /></s:text></td>
	  <td style="text-align:right;"><s:label name="curr" value="%{#session.salesCurrencySymbol}"/><s:text name="format.customMoney" ><s:param name="value" value="%{#totals}" /></s:text></td>
     
    </tr>
  </tfoot>
			
			
			
</table>
</div>


<div class="exportlinks"> 
       
		<mmr:message messageId="label.bottom.exportto"/>: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="sales.download.action?type=csv"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
<a href="sales.download.action?type=xl"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
<a href="sales.download.action?type=xml"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
  </div>
</div>
</div>
</div>
<div id="srchinv_res_tbl_end_sales"></div>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">
var customers = {
		<s:iterator value='customers' status='customersStatus'>
		"<s:property value='id' />": "<s:property escape='false' value='name' />"<s:if test="!#customersStatus.last">,</s:if>
      </s:iterator>
 };

	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });
	// Initialize autocomplete with local lookup:
     $('#customersautocomplete').newautocomplete({
        lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
            $('#custId').val(suggestion.data);
			}
		},		
		        onInvalidateSelection: function() {
		            $('#custId').val("");
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

