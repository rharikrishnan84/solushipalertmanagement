<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<!--  
<div id="messages">
<jsp:include page="../common/action_messages.jsp"/>
</div>-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	
	
	
<script>
	$(window).load(function() {
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
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

<div class="content">
<div class="content_body" >	
	<div class="content_table" style="background-color:#fff;">
		<div id="srchinv_results">	
		<div id="srchusr_res"><span><mmr:message messageId="label.searchar.invoicedetails"/></span></div></div>	
<div id="srchinv_result_tbl" >
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" width="100%">
		<thead>
			<tr height="25px">
			<th style="width:30px; text-align:center;"><input id="check_all" type="checkbox" /></th>
		<th style="width: 175px !important;"><mmr:message messageId="label.searchar.company"/> </th>
			<th style="width: 175px !important;"> <mmr:message messageId="label.searchar.invoicenumber"/></th>
			<th style="width: 220px ;"><mmr:message messageId="label.searchar.remittancedate"/></th>
			<th style="text-align:right;padding-right:10px;width: 75px ;"><mmr:message messageId="label.searchar.amount"></mmr:message></th>
			<th style="width: 75px ;"><mmr:message messageId="label.searchar.paidby"/><mmr:message messageId="Payment Ref#"/></th>
			<th style="width: 175px !important;"><mmr:message messageId="label.searchar.paymentref"></mmr:message></th>
			</tr>
		</thead>
		<tbody>
			
          <s:iterator id="artable" value="arTransactions" status="rowstatus">
             <tr>
				   <td style="width:30px; text-align:center;">
		          <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/>  
	   			</td>
					  <td style="text-align: left;"<span title="<s:property value="customer.name"/>"></span><div style="width:170px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="customer.name"/></div>
	   			 </td>
	           <td><s:property value="invoiceNum"/></td>
	           <td><s:property value="paymentDate"/></td>
			   <td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="amount" /></s:text></td>
			   <td><s:property value="modeOfPayment" /></td>
			   <td><s:property value="paymentRefNum" /></td>
            </tr>			

            </s:iterator>

</tbody>
</table>
</div>
</div>
</div>
</div>
<div id="srchinv_res_tbl_end"></div>



