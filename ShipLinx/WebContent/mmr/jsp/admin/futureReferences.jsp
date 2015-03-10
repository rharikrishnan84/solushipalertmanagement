
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!-- Start: Code to handle Export Data -->
<%@ page buffer="16kb"%>
<!-- End: Code to handle Export Data -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/mmr/styles/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/mmr/styles/ipad.css"
	media="screen and (min-width:768px) and (max-width:1024px)" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/mmr/styles/smartphone.css"
	media="screen and (min-width:320px) and (max-width:767px)" />
<style type="text/css">
</style>
<script
	src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
<title><s:text name="customer.search.title" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>
	
	
	<style>
	.fgt_password:hover{ color:#ccc;}
	.fgt_password{color: #ffffff; text-decoration:none; font-weight:600;}
	#wrapper_logo{ 
		min-height:320px;
		background-repeat:no-repeat;
		background-position: center center;
		background-size:900px auto;
	}
	.login_box{
		width:auto; 
		height:70px;
		float:right;
		padding-right:32px;
	}
	.login_box ul{ float:left; height:auto; width:auto; list-style-type:none;}
	.login_box ul li{ height:25px;}
	.login_box ul li label{ width:130px; padding-top:3px; padding-right:5px; float:left; font-size:10px; text-align:right; }
	.login_box ul li select,.login_box ul li input{ float:left;  padding:0px;}
	.login_box ul li select{ height:22px; width:122px; }
	.login_box ul li input{ height:20px; width:120px; }
	.login_box input[type="submit"]{border:0px; background:transparent; font-size:10px; padding:0px;  float:right; margin-top:52px;}
	
	.footer{ position:absolute; bottom:0px;}
	
	
</style>
	
	
</head>
<body>

	<script>
		$(document).ready(function() {
			$('#actiondown').click(function(event) {
				event.preventDefault();
				$('#actionup').css('display', 'block');
				$('#actiondown').css('display', 'none');
				$('#actionmenu').css('display', 'block');
			});
			$('#actionup').click(function(event) {
				event.preventDefault();
				$('#actionup').css('display', 'none');
				$('#actiondown').css('display', 'block');
				$('#actionmenu').css('display', 'none');
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sample1').dataTable();
			$("#check_all").click(function() {
				var temp = $(".dataTable-checkbox").attr("checked");
				if (temp == null) {
					$(".dataTable-checkbox").attr("checked", "checked");
				} else {
					$(".dataTable-checkbox").removeAttr("checked");
				}
			});
		});
	</script>
	<script type="text/javascript">
		function deleteCustomer() {

			var uploadFutureId = document
					.getElementsByClassName("dataTable-checkbox");

			var i1, txt1 = 0;
			for (i1 = 0; i1 < uploadFutureId.length; i1++) {
				if (uploadFutureId[i1].checked) {
					txt1 += 1;
				}
			}
			if (txt1 < 1) {
				alert('Please select at least one');
			} else {
				var i1, futureid, value_checked, stored_value = "";
				for (i1 = 0; i1 < uploadFutureId.length; i1++) {
					if (uploadFutureId[i1].checked) {
						futureid = uploadFutureId[i1].value;
						value_checked = document
								.getElementsByName("futCustCheckBox" + futureid)[0].value;
						stored_value = stored_value + value_checked + ",";

					}
				}
				document.getElementById("fut_CustomerId").action = "futRef1.action?futureRefId="+ stored_value;
				document.getElementById("fut_CustomerId").submit();

			}

		}
	</script>
	<script type="text/javascript">
	function viewCustomer()
	{
		var viewFutureId = document.getElementsByClassName("dataTable-checkbox");
		
	
	var i1,txt1 = 0;
   for (i1=0;i1<viewFutureId.length;i1++){
	if (viewFutureId[i1].checked){
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
	var i,txt,id,futId;
	for (i=0;i<viewFutureId.length;i++){
		if (viewFutureId[i].checked){
		futId = viewFutureId[i].value ;
		id = document.getElementsByName("futCustCheckBox"+futId)[0].value;					
		}
	}
	
	//document.getElementById("fut_CustomerId").action ="getcustomerinfo.action?method=update&id="+id;
	document.getElementById("fut_CustomerId").action ="futRef2.action?id="+id;
	//futRef2.action?id=${futureReferenceId}"
	document.getElementById("fut_CustomerId").submit();
}
	}
	</script>
	
	<div class="content">
		<div class="content_body">
			<div class="content_table" style="background-color: #fff">
				<!-- <div class="form-container" style="width:87%;margin-left:90px;">  -->
				<div class="form-container">
					<s:form action="searchFutureCustomer" name="searchfutCustomer"
						id="fut_CustomerId">

						<div id="srchusr_results">
							<div id="srchusr_res">
								<span><label>Future Reference List</label></span>
							</div>
							<div class="form_buttons" style="float: right;">
								<a href="#" id="actiondown"><label><mmr:message
											messageId="label.action" /></label> <span style="font-size: 14px;">&#9660;</span></a>
								<a href="#" id="actionup"><label><mmr:message
											messageId="label.action" /></label> <span style="font-size: 14px;">&#9650;</span></a>
								<ul id="actionmenu">

									<li><a href="javascript: deleteCustomer();"><mmr:message
												messageId="label.add.delete" /></a></li>
												<li><a href="javascript:viewCustomer();"/><mmr:message
												messageId="label.add.view" /></a></li>
												
								</ul>
							</div>
						</div>

						<div id="result_tbl">
							 <table cellpadding="0" cellspacing="0" border="0px"
								class="display" id="sample1"
								style="float: left; width: 100%; height: auto;"> 
								
								<thead>
									<tr style="height: 28px;">
										<th style="width: 30px; text-align: center;"><input
											id="check_all" type="checkbox" /></th>
										<th></th>
										<!-- <th style="text-align:left; padding-left:10px;"><label>Customer_id</label></th> -->
										<th style="text-align: left; padding-left: 10px;"><label
											style="width: 90px; float: left;"><mmr:message
													messageId="label.add.name" /> </label></th>
										<!-- <th style="text-align:left;padding-left:10px;"><label>FromCity</label></th> -->
										<th
											style="text-align: left; padding-left: 10px; width: 80px !important;"><label
											style="width: 80px !important; float: left;"><mmr:message
													messageId="label.add.fromstate" /></label></th>
										<th style="text-align: left; padding-left: 10px;"><label><mmr:message
													messageId="label.add.fromcountry" /></label></th>
										<!-- <th style="text-align:left;padding-left:10px;"><label style="width:65px; float:left;">ToCity</label></th> -->
										<th style="text-align: left; padding-left: 10px;"><label
											style="width: 65px; float: left;"><mmr:message
													messageId="label.add.tostate" /></label></th>
										<th style="text-align: left; padding-left: 10px;"><label
											style="width: 65px; float: left;"><mmr:message
													messageId="label.add.tocountry" /></label></th>

									</tr>
								</thead>
								<tbody>
									<s:iterator id="customer" value="fCustomers" status="rowstatus">
										<tr>
											<td class="odd1" width="2%"
												style="width: 30px; text-align: center;"><input
												class="dataTable-checkbox" type="checkbox"
												name="futCustCheckBox<s:property value="futureReferenceId"/>"
												value="<s:property value="futureReferenceId"/>" /> <input
												type="hidden"
												name="Fid<s:property value='futureReferenceId'/>"
												value="<s:property value='futureReferenceId'/>" /> <input
												type="hidden"
												name="customerId<s:property value='customerId'/>"
												value="<s:property value='customerId'/>" /></td>
											 <td>
												<!-- <a href="logInAs.action?id=${customerId}"> --> <%-- <a
												href="futRef2.action?id=${futureReferenceId}"> <img
													src="<s:url value="/mmr/images/red_arrow.gif" includeContext="true" />"
													alt="Log In As" title="Log In As" border="0"></a>
 --%>											</td> 


											<!--   <td><s:property value="customerId"/></td> -->
											<td><s:property value="name" /></td>
											<%-- <td><s:property value="fromCity"/></td> --%>
											<td><s:property value="fromState" /></td>
											<td><s:property value="fromCountry" /></td>
											<%-- <td><s:property value="toCity"/></td> --%>
											<td><s:property value="toState" /></td>
											<td><s:property value="toCountry" /></td>


										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</s:form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
