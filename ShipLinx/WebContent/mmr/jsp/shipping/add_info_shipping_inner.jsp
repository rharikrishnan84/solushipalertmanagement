<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<!DOCTYPE html>
<html> 
<head>
<sj:head jqueryui="true" />
    <title><s:text name="user.form.title"/></title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>
	<style type="text/css">
		
	</style>
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
			/* $('table').wrap('<div class="grid_table_body"></div>'); */
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
			/* $('.grid_table_body').css('overflow-x','scroll'); */
					
		});
	
	</script>
</head>
<body>

<div id="comment_table">

<div id="add_info_shipping_result_tbl">		



<div class="form-container">
<s:form id="searchUserFormId" cssClass="form" >

	<div id="srchusr_results">
		<div id="srchusr_res"><span>Status Updates </span></div>
	</div>
			<s:if test="%{selectedOrder.customer.paymentType==2}">
			<div id="sample1_wrapper" class="dataTables_wrapper" role="grid">
			<div class="box-cont1"><div id="sample1_length" class="dataTables_length"><label>Show <select size="1" name="sample1_length" aria-controls="sample1"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option><option value="-1">All</option></select> entries</label></div><div class="dataTables_filter" id="sample1_filter"><label>Search: <input type="text" aria-controls="sample1"></label></div></div>
			</s:if>	
	<div class="grid_table_body" style="overflow-x: scroll;">
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left; width:100%; height:auto;">
    <thead>
		<tr>
			<th><span style="width:50px !important; float:left;"><input id="check_all" type="checkbox" /></span></th>
			<th><span style="width:180px !important; float:left;">Date Time</span></th>
			<th><span style="width:250px !important; float:left;">Status</span></th>
			<th><span style="width:200px !important; float:left;">Message</span></th>
			<th><span style="width:300px !important; float:left;">Log</span></th>
				<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
			<th><span style="width:150px !important; float:left;">UpdatedBy</span></th>
			<th><span style="width:110px !important; float:left;">Private</span></th>
			<th><span style="width:110px !important; float:left;">Delete</span></th>
			</s:if>
		</tr>
	</thead>
	<tbody>
            <s:iterator id="comment" value="loggedList" status="rowstatus">
             <tr class="odd">
	         <td class="odd1" width="2%">
			 <input  class="dataTable-checkbox" type="checkbox" name="searchUserCheckBox" value="<s:property value="username"/>"/> 
	             </td>
				<td><s:date name="eventDateTime"/></td>
				<td>
					<s:if test="entityType==10">
					<mmr:message messageId="label.shipping.status.name.shipping"/>
					</s:if>
					<s:if test="entityType==20">
					<mmr:message messageId="label.shipping.status.name.transit"/>
					</s:if>
					<s:if test="entityType==30">
					<mmr:message messageId="label.shipping.status.name.delivered"/>
				</s:if>
					<s:if test="entityType==40">
					<mmr:message messageId="label.shipping.status.name.cancelled"/>
					</s:if>
					<s:if test="entityType==80">
					<mmr:message messageId="label.shipping.status.name.process"/>
					</s:if>
					<s:if test="entityType==11">
					<mmr:message messageId="label.shipping.status.name.dispatched"/>
					</s:if>
					<s:if test="entityType==12">
					<mmr:message messageId="label.shipping.status.name.pickup"/>
				</s:if>
					<s:if test="entityType==13">
					<mmr:message messageId="label.shipping.status.name.dropped"/>
					</s:if>
					<s:if test="entityType==14">
					<mmr:message messageId="label.shipping.status.name.board"/>
					</s:if>
					<s:if test="entityType==15">
					<mmr:message messageId="label.shipping.status.name.depart"/>
					</s:if>
					<s:if test="entityType==21">
					<mmr:message messageId="label.shipping.status.name.route"/>
					</s:if>
					<s:if test="entityType==22">
					<mmr:message messageId="label.shipping.status.name.appointment"/>
					</s:if>
					<s:if test="entityType==23">
					<mmr:message messageId="label.shipping.status.name.delivery"/>
					</s:if>
					<s:if test="entityType==24">
					<mmr:message messageId="label.shipping.status.name.pending"/>
				</s:if>
					<s:if test="entityType==25">
					<mmr:message messageId="label.shipping.status.name.customs"/>
					</s:if>
					<s:if test="entityType==26">
					<mmr:message messageId="label.shipping.status.name.terminal"/>
					</s:if>
				<s:if test="entityType==50">
					<mmr:message messageId="label.shipping.status.exception"/>
					</s:if>
				 
				</td>
				   <td style="text-align: left;"><s:property value="message"/></td>
				      <td style="text-align: left;"><s:property value="systemLog"/></td>
					  	<s:if test="%{#session.ROLE.contains('busadmin')||#session.ROLE.contains('solutions_manager')}">
					   <td style="text-align: left;"><s:property value="eventUsername"/></td>
					     <td style="text-align: left;"><s:property value="Private"/>
						 <s:if test="%{privateMessage != true}">
				<img src="<s:url value="/mmr/images/cross.png" includeContext="true" />" alt="Cross" border="0">
			</s:if>
			<s:else>
				<img src="<s:url value="/mmr/images/tick.png" includeContext="true" />" alt="Tick" border="0">
			</s:else>
			</td>
		  <td style="text-align: left;"><s:property value="Delete"/>
		  <s:if test="%{deletedMessage != true}">
				<s:a onclick="return confirm('Do you really want to delete the selected Product?')" href="Javascript: deleteComment(%{id});">
					<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Customer Account Info" border="0"> </s:a>
			</s:if>
			<s:else>
					<img src="<s:url value="/mmr/images/tick.png" includeContext="true" />" alt="Tick" border="0">
			</s:else>
			</td>
			</s:if>
            </tr>			
            </s:iterator>
</tbody>
</table>
</div>
</div>
<div class="exportlinks" style="float:left; width:100%; height:30px;font-size:12px; text-align:right;"> 
 Export to: &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <a href="download.viewshipment.action?type=csv&viewShipmentId=<s:property value='%{selectedOrder.id}'/>"><span class="exportcsv">&nbsp;&nbsp;&nbsp;&nbsp; CSV </span>&nbsp;&nbsp;|</a>&nbsp;
  <a href="download.viewshipment.action?type=xl&viewShipmentId=<s:property value='%{selectedOrder.id}'/>"><span class="exportexcel">&nbsp;&nbsp;&nbsp;&nbsp; Excel </span>&nbsp;&nbsp; |</a>&nbsp;
  <a href="download.viewshipment.action?type=xml&viewShipmentId=<s:property value='%{selectedOrder.id}'/>"><span class="exportxml">&nbsp;&nbsp;&nbsp;&nbsp; XML </span>&nbsp;&nbsp;|</a>
</div>

  <div class="content_table">&nbsp;</div>

</s:form>
</div>

</div>
</div>



		

<div id="add_info_shipping_res_tbl_end"></div>
</body>	
</html>

