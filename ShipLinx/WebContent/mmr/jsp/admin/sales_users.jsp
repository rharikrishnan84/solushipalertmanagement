<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@page import="java.io.*"%>
<html> 
<head> 
    <title><s:text name="customer.form.title"/></title> 
    <sj:head jqueryui="true" />
    <sx:head />
</head> 

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript">
	
		$(document).ready(function() {
			$('#sample1').dataTable(
			{
		                "bPaginate": false
		            }
			
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
		</script>
<SCRIPT language="JavaScript">
var pplistr = "<%= request.getAttribute("ppList")%>";
var pplists= pplistr.substring(1,pplistr.length-1);
var pplist = pplists.split(",");

var pclistr = "<%= request.getAttribute("pcList")%>";
var pclists= pclistr.substring(1,pclistr.length-1);
var pclist = pclists.split(",");

var pslistr = "<%= request.getAttribute("psList")%>";
var pslists= pslistr.substring(1,pslistr.length-1);
var pslist = pslists.split(",");

	function submitform()
	{
		if(document.customerform.method.value=='edit'){
			 document.customerform.action = "editcustomerinfo.action";
		}else{

			 document.customerform.action = "createCustomer.action";
		}

	 document.customerform.submit();
	}
	function submitcarrier()
	{
	 document.customerform.action = "createAccount.action";
	 document.customerform.submit();
	}
	
    function showState() {
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
		  url="<%=request.getContextPath()%>/customer.listProvince.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()	
	
	function checkBothSalesAgent()
	{	
		var e = document.getElementById("salesAgent");
		var strSalesUser = e.options[e.selectedIndex].value;
		
		var e2 = document.getElementById("salesAgent2");
		var strSalesUser2 = e2.options[e2.selectedIndex].value;
		
		if(strSalesUser==strSalesUser2)
		{
			alert("Both the selected Sales Agent should not be same.");
			e.selectedIndex = 0;
			e2.selectedIndex = 0;
		}		
	}
	
	function checkAllSalesAgent(valSelected)
	{	
		//alert(pplist);
		//alert(pplists.length);
		//alert(pplistr.length);
		var elem = document.getElementById(valSelected);
		var actVal = elem.options[elem.selectedIndex].value;
		//alert(elem.selectedIndex);
		var e = document.getElementsByName("csListSalesCommission");
		var count=0;
		for(var i=0; i < e.length; i++)
		{
			var strSalesUser = e[i].value;			
			if(actVal==strSalesUser)
				count++;
		}
		//alert(pplist[elem.selectedIndex-1]);
		//alert(pplist.length);
		//alert(pplist);
		if(count>0)
		{
			alert("Commissions have already been set for this sales person. Please update the existing values.");
			elem.selectedIndex = 0;
		}	
		else
		{
			//alert("check");
			//calling ajax function to populate the commission percentages
			if(elem.selectedIndex > 0){
				document.getElementById('new_comm_percentage_ps').value = pclist[elem.selectedIndex-1];
				document.getElementById('new_comm_percentage_pp').value = pplist[elem.selectedIndex-1];
				document.getElementById('new_comm_percentage_chb').value = pslist[elem.selectedIndex-1];
			}
			else
			{
				document.getElementById('new_comm_percentage_ps').value = '0.0';
				document.getElementById('new_comm_percentage_pp').value = '0.0';
				document.getElementById('new_comm_percentage_chb').value = '0.0';
			}
		}		
	}
	
	function checkToAdd()
	{
		var e= document.getElementById("salesAgent");
		var cp = document.getElementById("new_comm_percentage");
		var salesagent = e.options[e.selectedIndex].value;
		
		var commission_ps = document.getElementById("new_comm_percentage_ps").value;
		var commission_pp = document.getElementById("new_comm_percentage_pp").value;
		var commission_chb = document.getElementById("new_comm_percentage_chb").value;
		var customerId = location.search.split('id=')[1];
		if(e.selectedIndex==0)
			alert("Please select a Sales User to add.");
		else if(commission_ps.value > 100 || commission_pp>100 || commission_chb>100)
			alert("Commission percentage cannot be more than 100");
		else
		{	// call the action.
		 	document.customerform.action="add.new.customersales.action?SalesAgent="+salesagent+"&Commission_Percentage_PS="+commission_ps+"&Commission_Percentage_PP="+commission_pp+"&Commission_Percentage_CHB="+commission_chb+"&id="+customerId;
		 	document.customerform.submit();
		}
	}

</SCRIPT> 

<script>
	$(window).load(function() {
		$('#wrapper_new').css('float','left');
	  var wndo = $(window).height();
	  wndo -=46;
	  $('#wrapper_new').css('min-height',wndo);
	});
</script>
		<script type="text/javascript">
		function deleteSaleUser(){
			var deleteUserId = document.getElementsByName("salesUseCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteUserId.length;i1++){
			if (deleteUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please check atleast one');
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
				msg.innerHTML = "Would you like to delete this sales person from the customer account?";
				//var status;	
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','100px');
				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','100px');
				
				var customerId = location.search.split('id=')[1];
				//btnconfirm.onclick = function() {removeCustomAlert();return false; }
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					document.getElementById("customerform").action ="delete.customer.sales.agent.action?cs_id="+txt+"&id="+customerId;
				document.getElementById("customerform").submit();
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
				
				
				
				
				
				
				
			/*	var del=confirm('Would you like to delete this sales person from the customer account?');
				var customerId = location.search.split('id=')[1];
				if (del==true){
				document.getElementById("customerform").action ="delete.customer.sales.agent.action?cs_id="+txt+"&id="+customerId;
				document.getElementById("customerform").submit();
				}  */
			}
			
		}
		
	</script>
					<script type="text/javascript">
		function updateSalesUser(){
			var deleteUserId = document.getElementsByName("salesUseCheckBox");
			var i1,txt1 = 0;
		   for (i1=0;i1<deleteUserId.length;i1++){
			if (deleteUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please check atleast one');
		   }
		    else if(txt1 > 1){
			alert('Please check atmost one');
		   }
		   else{
				var i,id;
				for (i=0;i<deleteUserId.length;i++){
					if (deleteUserId[i].checked){
						id = deleteUserId[i].value ;					
					}
				}
				var index = document.getElementById("index_" + id).value;
				var ps=document.getElementById("csListSalesCommission[" + index + "]_comm_ps");
		var cp_ps =ps.value;
		var cp_pp=document.getElementById("csListSalesCommission[" + index + "]_comm_pp");
		var valcp_pp = cp_pp.value;
		var cp_chb=document.getElementById("csListSalesCommission[" + index + "]_comm_chb");
		var valcp_chb = cp_chb.value;

		if(cp_ps>100 || valcp_pp>100 || valcp_chb>100)
			alert("Commission percentage cannot be more than 100");
		else
		{
			document.customerform.action="update.customer.sales.agent.action?cs_id="+id+"&cp_ps="+cp_ps+"&cp_pp="+valcp_pp+"&cp_chb="+valcp_chb;
			document.customerform.submit();
		}
			}
			
		}
		
	</script>


<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="createCustomer" id="customerform">
<div class="content">
	<div class="content_body">
       <div class="content_table">
        <div class="content_header">
         <div class="cont_hdr_title" ><!--<mmr:message messageId="label.manage.sales.users"/>-->
			Customer Sales Team
		 </div>
		 <!--<div class="cont_hdrtitle_s"><s:property value="customer.name"/></div>		 -->
         <div class="form_buttons" id="sales_user_bck">
         <a href="searchcustomer.action">
		 <!--<mmr:message messageId="label.back.customer.edit" />-->back
		 </a>
		  <a href="#" onclick=" checkToAdd();">Add</a>
         </div>
        </div>
        <div class="cont_data_body">
         <div class="rows">
          <div class="fields">
           <label>Sales Agent</label>
           <div class="controls"><span>:</span>
            <s:select cssClass="text_01_combo_big" listKey="username" listValue="fullName" 
							name="customer.salesAgent1" headerKey=""  headerValue="---Select---" list="salesUsers" 
								id="salesAgent" theme="simple" onchange="checkAllSalesAgent(this.id)"/>
           </div>
          </div>
          <div class="fieldsvvs" id="stateid">
           <label>SPD %</label>
           <div class="controls"><span>:</span>
          <s:textfield id="new_comm_percentage_ps" name="new_comm_percentage_ps"  cssClass="text_02_tf_small" theme="simple" size="3" value="0.0" maxlength="5"/>
           </div>
          </div>
          <div class="fieldsvvs">
           <label>LTL %</label>
           <div  class="controls"><span>:</span>
           <s:textfield id="new_comm_percentage_pp" name="new_comm_percentage_pp"  cssClass="text_02_tf_small" theme="simple" size="3" value="0.0" maxlength="5"/>
           </div>
          </div>
          <div class="fieldsvvs">
           <label>CHB %</label>
           <div class="controls"><span>:</span>
           	<s:textfield id="new_comm_percentage_chb" name="new_comm_percentage_chb"  cssClass="text_02_tf_small" theme="simple" size="3" value="0.0" maxlength="5"/>
           </div>
          </div>

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
				
				<div id="srchusr_res">
				<span>Customer Sales</span>
				</div>



					<div class="form_buttons">
						<a href="#" onclick="updateSalesUser();">UPDATE</a>
						<a href="#" onclick="deleteSaleUser();">DELETE</a>
					</div>
				</div>
				

<div id="accnt_bottom_table"  style="background-color:#FFF;" >
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" style="float:left;  background-color:#FFF; width:100%; height:auto;">
    <thead>
	<tr height="25px">
	<th ><input id="check_all" type="checkbox" name="salesUseCheckBox"/></th>
	<th style="text-align:left; padding-left:10px;">#</th>
	<th style="text-align:left; padding-left:10px;"><mmr:message messageId="label.salesAgent"/></th>
	<th style="text-align:left; padding-left:10px;"><!--<mmr:message messageId="label.commission.percentage"/>-->SPD %</th>
	<th style="text-align:left; padding-left:10px;"><!--<mmr:message messageId="label.commission.percentage.pp"/>-->LTL %</span></th>
	<th style="text-align:left; padding-left:10px;"><!--<mmr:message messageId="label.commission.percentage.ps"/>-->CHB %</th>	
	</tr>
	</thead>	
	<tbody>
<s:set var="index" value="0" />
	<s:iterator value="customer.CustomerSalesUser" status="counterIndex">	
			<s:set name="custsalesid" value="%{customer.CustomerSalesUser[#counterIndex.index].id}"/>
			<tr>
				 <td class="odd1" width="2%">
				 <input  class="dataTable-checkbox" type="checkbox" name="salesUseCheckBox" value="<s:property value='%{#custsalesid}'/>"/> 
				 <s:hidden value="%{#counterIndex.index}" id="index_%{#custsalesid}"/>
					 </td>
				<td><s:property value="%{#counterIndex.index+1}"/></td>
		        <td class="tablerow3" align="center"  style="width:250px;"><s:textfield id="csListSalesCommission[#counterIndex.index]" name="csListSalesCommission"  cssClass="text_02_tf" theme="simple" size="30" value="%{customer.CustomerSalesUser[#counterIndex.index].salesAgent}" readonly="true"/></td>
		        <td class="tablerow3" align="center"><s:textfield id="csListSalesCommission[%{#counterIndex.index}]_comm_ps" name="customerSalesCommissionPS"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{customer.CustomerSalesUser[#counterIndex.index].commissionPercentagePerSkidService}"/></td>
		        <td class="tablerow3" align="center"><s:textfield id="csListSalesCommission[%{#counterIndex.index}]_comm_pp" name="customerSalesCommissionPP"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{customer.CustomerSalesUser[#counterIndex.index].commissionPercentagePerPalletService}"/></td>
		        <td class="tablerow3" align="center"><s:textfield id="csListSalesCommission[%{#counterIndex.index}]_comm_chb" name=""  cssClass="text_02_tf_small" theme="simple" size="3" value="%{customer.CustomerSalesUser[#counterIndex.index].commisionPercentagePerCHB}"/></td>

			</tr>	
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

		
	