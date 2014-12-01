<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html> 
<head> 
<sj:head jqueryui="true" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/shiplinx_closeWindow_styles_Chrome.css" />-->
	
	<script type="text/javascript">
	
		$(document).ready(function() {

			$('#samplesearchproduct').dataTable(); 
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
		
function submitChargeForm()
{
	document.searchchargeform.action="searchCharges.action?search=true";
	document.searchchargeform.submit();
}


function editCharge(){
		
			var editUserId = document.getElementsByName("searchCharge");
			
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please Select at least one');
			}
			 else if(txt1 > 1){
			alert('Please check atmost one');
		   }
			else{
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
					}
				}
			document.getElementById("searchchargeform").action = "editCharges.action?method=edit&id="+txt;
			document.getElementById("searchchargeform").submit();
		}
		}
		
		function deleteCharge(){
			
			var editUserId = document.getElementsByName("searchCharge");
			var i1,txt1 = 0;
			for (i1=0;i1<editUserId.length;i1++){
				if (editUserId[i1].checked){
					txt1 += 1;						
				}
			}
			if(txt1 < 1){
				alert('Please Select at least one');
			}
			 else if(txt1 > 1){
			alert('Please check atmost one');
		   }
			else{
				var del=confirm("Do you really want to delete the selected Charge?");
				if(del==true){
				var i,txt;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;					
					}
				}
			
			document.getElementById("searchchargeform").action = "deleteCharge.action?id="+txt;
			document.getElementById("searchchargeform").submit();
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
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<sx:head/>
    <title><s:text name="customer.search.title"/></title> 
</head> 
<body> 
<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#samplesearchproduct_length").wrap("<div class='box-cont1'></div>");
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

<div class="content">
<div class="content_body" >
<div class="content_table" >
<div class="form-container"  > 
<s:form action="searchCharges" id="searchchargeform" >

	
        <div class="content_header">
         <div class="cont_hdr_title"><mmr:message messageId="label.search.charge"/></div>
         <div class="form_buttons">
          <a href="javascript: submitChargeForm()" >SEARCH</a>
          <a href="goToAddNewCharge.action"><mmr:message messageId="label.search.addnew"/></a>
         </div>
        </div>
		
        <div class="cont_data_body"  >
         <div class="rows">
          <div class="fields">
           <label><mmr:message messageId="label.track.carrier"/> </label>
           <div class="controls"><span>:</span>
		   <s:select  listKey="id" listValue="name"    
			name="carrierChargeCode.carrierId" list="#session.CARRIERS" theme="simple" headerValue="--Select--" headerKey="-1" id="firstbox" value="--Select--"/>
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.charge.code"/> </label>
           <div class="controls"><span>:</span><s:textfield size="24" id="prod_name" name="carrierChargeCode.chargeCode"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.charge.code.2"/> </label>
           <div class="controls"><span>:</span><s:textfield size="24" id="prod_desc" name="carrierChargeCode.chargeCodeLevel2"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.charge.name"/> </label>
           <div class="controls"><span>:</span><s:textfield size="24" id="prod_hcode" name="carrierChargeCode.chargeName"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.charge.desc"/> </label>
           <div class="controls"><span>:</span><s:textfield size="24" id="prod_code" name="carrierChargeCode.chargeDesc"   />
           </div>
          </div>
          <div class="fields">
           <label><mmr:message messageId="label.charge.group"/></label>
           <div class="controls"><span>:</span><s:select id="chargegroup" name="carrierChargeCode.groupId" list="#session.listChargeGroups"    headerValue="--Select--"  headerKey="-1" theme="simple"/>
           </div>
          </div>
          
         </div>
        </div>
       </div>
      </div>

</div>

<div class="content_body" >
<div class="content_table" style="background-color:#fff">
<div class="form-container">
<div id="tab"></div>
	<div id="results">
	<div id="srchusr_res"><span><mmr:message messageId="label.heading.accessorialcharge"/></span></div>
		<div id="rslt_stmnt" class="form_buttons">
		<a href="#" id="actiondown" ><p style="font-size:12px; float:left;width:auto; height:15px; padding:0px;"><mmr:message messageId="label.action"/></p> <p style="font-size:12px; float:left; padding:0px; width:15px; height:15px;">&#9660;</p></a>
			<a href="#" id="actionup" ><p style="font-size:12px; float:left;width:auto; height:15px; padding:0px;"><mmr:message messageId="label.action"/></p>  <p style="font-size:12px; float:left; padding:0px; width:15px; height:15px;">&#9650;</p></a>
			<ul id="actionmenu">
			<li><a href="#" onclick="editCharge();" href="#" ><mmr:message messageId="label.list.edit"/></a></li>
			<li><a href="#" onclick="deleteCharge()" href="#"><mmr:message messageId="label.list.delete"/></a></li>
			</ul>			
		</div>
	</div>
	
<div id="result_tbl">
<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="samplesearchproduct" width="100%">	
<thead>
		<tr>
			
			<th style="width:30px"><input id="check_all" type="checkbox" /></th>
			<th style="width:150px"><mmr:message messageId="label.track.carrier"/></th>
			<th style="width:150px"><mmr:message messageId="label.charge.code"/></th>
			<th style="width:100px !important;"><mmr:message messageId="label.charge.name"/></th>
			<th style="width:150px !important;text-align:right;padding-right:10px;"><mmr:message messageId="label.ghead.charge"/></th>
			<th style="width:150px !important;text-align:right;padding-right:20px;"><mmr:message messageId="label.ghead.cost"/></th>
			
		</tr>
    </thead>
<tbody>	
		<s:iterator id="chargetable" value="carrierChargecodeList" status="rowstatus">
            <tr>
	            <td><input class="dataTable-checkbox" name="searchCharge" type="checkbox" value="<s:property value='id'/>"/></td>
	            <td><s:property value="carrierName"/></td>
	            <td><s:property value="chargeCode"/></td>
	            <td><s:property value="chargeName"/> </td>
				<td style="text-align:right;"><s:text name="format.money" ><s:param name="value" value="carrierCharge" /></s:text></td>
				<td style="text-align:right;padding-right:20px;;"><s:text name="format.money" ><s:param name="value" value="carrierCost" /></s:text></td>

            </tr>
            </s:iterator>
		</tbody>
		</table>		
</div>
</s:form>

<div class="content_table" >
	&nbsp;
</div>
		
		
</div>
</div>
</div>		
</div>

		

