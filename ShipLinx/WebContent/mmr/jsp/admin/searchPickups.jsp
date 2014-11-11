

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

	    <title><s:text name="search.shipment.title"/></title> 
	    <sj:head jqueryui="true" />
	    <sx:head />
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
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
}
#customerautocomplete,#auto{ background-position: 145px 4px; background-size:8px 8px; }
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
	
	
	

	</head>
<body>
<SCRIPT language="JavaScript">
	
		function showState() {
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
			  firstBox = document.getElementById('firstBox');
			  url="<%=request.getContextPath()%>/markup.listService.action?value="+firstBox.value+"&pickup='true'";
				//param="objName=ref_state&country_id="+country_id;
			  	ajax_Service.open("GET",url,true);
			  	ajax_Service.send(this);
		} // End function showState()
		
		function searchShipment()
		{
			document.searchpickupform.action = "list.pickups.action";
			document.searchpickupform.submit();
		}
		
		function cancelPickup()
		{

			var editUserId = document.getElementsByName("check_uncheck");
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
				var i,txt,status,pickupid;
				for (i=0;i<editUserId.length;i++){
					if (editUserId[i].checked){
						txt = editUserId[i].value ;	
						
						status = document.getElementsByName("status"+txt)[0].value;						
							
					}
				}
			if(confirm("Would you like to cancel this pickup request?"))
			{
			document.searchpickupform.action = "cancelPickup.action?pickupid="+txt;
			document.searchpickupform.submit();
					}
		}
		}
		
		function goToCreatePickup()
		{
			//alert("Create Pickup");
			document.searchpickupform.action = "gotoCreate.pickup.action";
			document.searchpickupform.submit();
		}
		
		
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
  var wndo = window.innerHeight;
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
<div class="content">
	<div id="messages">
		<jsp:include page="../common/action_messages.jsp"/>
	</div>

	<div class="content_body" >	
	<div class="content_table" > 
		<div class="form-container"> 
			<s:form id="searchpickupform" action="search.pickups.action" name="searchpickupform">
		
			<div id="srch_pickups_panel">
				<div class="content_header">
					<div class="cont_hdr_title" id="srch_crtra"><mmr:message messageId="label.search.pickup"/></div>
					<div class="form_buttons" >
						<a href="javascript: goToCreatePickup()"><mmr:message messageId="label.create.pickup"/></a>
						<a href="javascript: searchShipment()"><mmr:message messageId="label.searchpickup.search"/></a>							
					</div>
				</div>
			</div>
			<div class="cont_data_body"   id="srch_pickups_table" style="background-color:#E7E7E7;">
				<div class="rows" >
					<div class="fields">
						<label><mmr:message messageId="label.from.date"/> </label>
						<div class="controls"><span>:</span>
								
							<s:textfield name="pickup.fromDate" onClick="selectDate('f_date_c','f_trigger_c');" id="f_date_c" 
											 readonly="readonly"/>	
						</div>
					</div>
					
					<div class="fields">
						<label><mmr:message messageId="label.to.date"/> </label>
						<div class="controls"><span>:</span>
							
							<s:textfield name="pickup.toDate" onClick="selectDate('t_date_c','t_trigger_c');" id="t_date_c" 
											 readonly="readonly"/>
						</div>
					</div>
					
					<div class="fields">
						<label><mmr:message messageId="label.track.carrier"/></label>
						<div class="controls"><span>:</span>
							<s:select   listKey="id" listValue="name" 
									name="pickup.carrierId" list="#session.CARRIERS" 
									id="firstBox" theme="simple"/>
						</div>
					</div>
					
					<div class="fields">
						<label><mmr:message messageId="label.searchpickup.carrierconfirmation"/> </label>
						<div class="controls"><span>:</span>
							<s:textfield  name="pickup.confirmationNum" />
						</div>
					</div>
					
					<div class="fields">
						<label><mmr:message messageId="label.status"/></label>
						<div class="controls"><span>:</span>
							<s:select   name="pickup.status" 
									 list="#{'-1':'---Select---','10':'ACTIVE','40':'CANCELLED'}" theme="simple"/>
						</div>
					</div>
					<s:if test="%{#session.ROLE.contains('busadmin')}">
						<div class="fields">
							<label><mmr:message messageId="label.customer.name" /></label>
								<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocomplete"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
										</div>
						</div>
					</s:if>
					
				</div>
			</div>	
		
		<div id="formResult">
		  <jsp:include page="list_pickups.jsp"/>
		   
		
		</div>
				
			
	</s:form>

		
	</div>
	</div>
	</div>		
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/autocomplete.js"></script>
<script type="text/javascript">

var customers = {
		<s:iterator value='#session.customersList'>
		"<s:property value='value' />": "<s:property value='key' />",
      </s:iterator>
 };

	delete customers["0"];
	var customersArray = $.map(customers, function (value, key) { return { value: value, data: key }; });

	
	// Initialize autocomplete with local lookup:
      $('#customerautocomplete').newautocomplete({
       lookup: customersArray,
		minChars: 0,
		onSelect: function (suggestion) {
		if(suggestion.value != ""){
           $('#custId').val(suggestion.data);
			changeCustomer();
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
		
