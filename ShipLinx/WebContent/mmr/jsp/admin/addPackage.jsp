<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
	
<head>
<sx:head/>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>

<script language="JavaScript">
	
		function submitform(val)
	{
		var bsubmit=true;
		if(document.getElementById("pgname").value=="")
			{
				alert("Please enter Package Name");
				bsubmit=false;
			}
			else if(document.getElementById("pglen").value=="")
			{
				alert("Please enter Package Length");
				bsubmit=false;
			}
			else if(document.getElementById("phwid").value=="")
			{
				alert("Please enter Package Width");
				bsubmit=false;
			}
			else if(document.getElementById("pghht").value=="")
			{
				alert("Please enter Package Height");
				bsubmit=false;
			}
			else if(document.getElementById("pgwgt").value=="")
			{
				alert("Please enter Package Weight");
				bsubmit=false;
			}
			else if(document.getElementById("pgdes").value=="")
			{
				alert("Please enter Package Description");
				bsubmit=false;
			}
			
		if(bsubmit)
		{	
			if(val=='add')
	 			document.addpackageform.action = "addnewpackage.action";
	 			//alert('add');
	 		else
	 			document.addpackageform.action = "addnewpackage.action?edit='true'";
	 			//alert('edit');
 			
 			document.addpackageform.submit();
	 	}
	}
	function getAccountInformation(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
	}
	function resetform() {
		document.addpackageform.action = "searchcustomer.action?method=reset";
	 	document.addpackageform.submit();
	}	
	
	function addNewSearch()
	{
		document.addpackageform.action = "admin/addcustomer.action?";
	 	document.addpackageform.submit();
	}
	
	function emailNotify(custid)
	{
		if(confirm("Would you like to send account notification/message to customer?")) {
			 document.addpackageform.action = "send.customer.notification.action?&id="+custid;
			 document.addpackageform.submit();			
 
 function reset1form(){
   
   document.addpackageform.action = "search.packages.action";
    document.addpackageform.submit();
 }
		}
	}
	function cancelform(){
   
   //document.addpackageform.action = "search.packages.action";
    //document.addpackageform.submit();
	window.location = "search.packages.action";
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
<html> 
<head> 
 	<title><s:text name="user.form.title"/></title>
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<div class="content">
<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>

<div class="content_body ">
<s:form action="searchCustomer" name="addpackageform"  style="margin-bottom:0px">
							<div class="content_table" >
								<div class="content_header">
									<div class="cont_hdr_title">
									<s:if test="#session.edit != 'true'">
										<mmr:message messageId="menu.add.package"/>
										</s:if>
										<s:else>
										<mmr:message messageId="label.edit.package"/>
										</s:else>
									</div>
									<div class="form_buttons">
									<s:if test="#session.edit != 'true'">
										<a href="javascript: submitform('add')"><mmr:message messageId="label.btn.save"/></a>
										</s:if>
										<s:else>
										<a href="javascript: submitform('edit')"><mmr:message messageId="label.btn.save"/></a>
										</s:else>
									<s:if test="%{#session.role=='busadmin'}">
									   <a href="goToManageProducts.action"><mmr:message messageId="label.btn.cancel"/></a>
								    </s:if>
									 <s:else>
										 <a href="search.packages.action"><mmr:message messageId="label.btn.cancel"/></a>   
									 </s:else>
									
									</div>
								</div>
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields" >
											<label><mmr:message messageId="label.package.name"/></label>
											<div class="controls"><span>:</span><s:textfield name="packageTypes.packageName" id="pgname"  value="%{packageTypes.packageName}"/></div>
										</div>
									
								
										<div class="fields">
											<label><mmr:message messageId="label.length.integer"/></label>
											<div class="controls"><span>:</span>
											<s:if test="%{packageTypes.packageLength > 1}">
													<s:textfield id="pglen" size="24" name="packageTypes.packageLength"/>
												</s:if>
												<s:else>
													<s:textfield id="pglen" size="24" name="packageTypes.packageLength"  value="1"/>
												</s:else>
											
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.width.integer"/></label>
											<div class="controls"><span>:</span>
											<s:if test="%{packageTypes.packageWidth > 1}">
												<s:textfield id="phwid" name="packageTypes.packageWidth" />
											</s:if>
											<s:else>
												<s:textfield id="phwid" name="packageTypes.packageWidth"  value="1"/>
											</s:else>
											
											
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.height.integer"/></label>
											<div class="controls"><span>:</span>
											<s:if test="%{packageTypes.packageHeight > 1}">
												<s:textfield id="pghht"  name="packageTypes.packageHeight" />
											</s:if>
											<s:else>
												<s:textfield id="pghht"  name="packageTypes.packageHeight"  value="1"/>
											</s:else>
											
											
											
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.weight.integer"/></label>
											<div class="controls"><span>:</span>
											<s:if test="%{packageTypes.packageHeight > 1}">
												<s:textfield id="pgwgt" size="24" name="packageTypes.packageWeight" cssClass="text_02_tf_small"/>
											</s:if>
											<s:else>
												<s:textfield id="pgwgt" size="24" name="packageTypes.packageWeight" cssClass="text_02_tf_small" value="1"/>
											</s:else>
											
											
											</div>
										</div>
								
									
										<div class="fields">
											<label><mmr:message messageId="label.package.desc"/></label>
											<div class="controls"><span>:</span><s:textfield name="packageTypes.packageDesc" id="pgdes"  /></div>
										</div>
									</div>
								</div>	
							</div>
							</s:form>
                           </div>								
							
					
</div>
</body>
</html>	


