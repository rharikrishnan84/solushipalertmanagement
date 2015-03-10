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
	
	<script type="text/javascript">
	
	function backToReference()
	{
		document.viewform.action="futRef.action";
		document.viewform.submit();
		
	}
	</script>
	
</head>
<body>

	<div class="content">
		<div class="content_body">
			<div class="content_table" style="background-color: #fff">
			<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.add.view.future.refenence" /></div>
									<div class="form_buttons">	
					<a id="show_ref" href="javascript: backToReference();"><label><mmr:message messageId="label.add.backfr" />
									</label></a>
				</div>
								</div>

				<!-- <div class="form-container" style="width:90%;margin-left:90px;">  -->
				<div class="form-container">
				<s:form action="showReference" name="viewform" id="viewId">
				 
						<div class="cont_data_body">
							<div class="rows">

								<div class="fields">
									<label><mmr:message messageId="label.add.customerid" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.customerId" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.name" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.name" /></label>

									</div>
								</div>
							</div>
							
							 <div class="rows">
								<div class="fields">
									<label><mmr:message messageId="label.add.datecreated" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.dateCreated" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.createdby" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.createdBy" /></label>

									</div>
								</div>
							</div>
							<div class="content_header">
									<div class="cont_hdr_title"><label><mmr:message messageId="label.add.ship.from" />
									</label><span>:</span> </div>
																	
									
								</div>
								<div class="rows">
								<div class="fields">
									<label><mmr:message messageId="label.add.shipfrom.company" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromCompany" /></label>

									</div>
								</div>
								   
								<div class="fields">
									<label><mmr:message messageId="label.add.shipfrom.residential" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox   name="fc.fromResidential" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.from.postal.zipcode" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromPostalCode" /></label>

									</div>
								</div>
								
						 
								</div>
						 	<div class="rows">
								<div class="fields">
									<label><mmr:message messageId="label.add.fromcity" /> </label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromCity" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.fromstate" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromState" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label><mmr:message messageId="label.add.from.attention" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromContactName" /></label>

									</div>
								</div>
								
							</div>
							<div class="rows">
								<div class="fields">
									<label><mmr:message messageId="label.add.fromcountry" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.fromCountry" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label><mmr:message messageId="label.add.shipfrom.address" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipFromAddress" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label> <mmr:message messageId="label.add.notify.shipper" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.notifyShipper" name="fc.notifyShipper" /></label>

									</div>
								</div>
								
							</div>
							<div class="rows">
								<div class="fields">
									<label> <mmr:message messageId="label.add.from.email" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipFromEmail" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.from.phoneno" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipFromPhone" /></label>

									</div>
								</div>
								
							</div>
							<div class="rows">
								
															
								
								</div>
							<div class="content_header">
									<div class="cont_hdr_title"> <label><mmr:message messageId="label.add.shipto" /></label><span>:</span></div>
									
								</div>
								
								<div class="rows">
								<div class="fields">
									<label> <mmr:message messageId="label.add.shipto.company" /> </label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toCompany" /></label>

									</div>
								</div>
								<div class="fields">
									<label> <mmr:message messageId="label.shipto.residential" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.toresidential" name="fc.toresidential" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.to.postal.zipcode" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toPostalCode" /></label>

									</div>
								</div>
								
								
								</div>
								
							<div class="rows">
							<div class="fields">
									<label><mmr:message messageId="label.add.tocity" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toCity" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.tostate" /> </label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toState" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.toattention" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toContactName" /></label>

									</div>
								</div>
								
							</div>
							<div class="rows">
							<div class="fields">
									<label><mmr:message messageId="label.add.tocountry" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.toCountry" /></label>

									</div>
								</div>
							<div class="fields">
									<label><mmr:message messageId="label.add.shipto.address" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipToAddress" /></label>

									</div>
							</div>
							<div class="fields">
									<label> <mmr:message messageId="label.add.notify.consignee" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.notifyConsignee" name="fc.notifyConsignee" /></label>

									</div>
								</div>
							</div>
							
							<div class="rows">
								<div class="fields">
									<label> <mmr:message messageId="label.add.shipto.email" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipToEmail" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.shipto.phoneno" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipToPhone" /></label>

									</div>
								</div>
								
								
								
							</div>
							
							<div class="rows">
								
								
								
								
								</div>
							
							<div class="content_header">
									<div class="cont_hdr_title"> <mmr:message messageId="label.add.future.services" />:</div>
									
								</div>
								
								<div class="rows">
							<div class="fields">
									<label> <mmr:message messageId="label.add.service.type" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.serviceType" /></label>

									</div>
								</div>
							<div class="fields">
									<label><mmr:message messageId="label.add.ship.date" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.shipScheduleDate" /></label>

									</div>	
							</div>
							
							<div class="fields">
									<label>  <mmr:message messageId="label.add.dangerous.goods" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:label key="fc.dangerousGoods" name="fc.dangerousGoods" /></label>

									</div>
								</div>
							
							</div>
							<div class="rows">
							<div class="fields">
									<label> <mmr:message messageId="label.add.documents.only" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.documentsOnly" name="fc.documentsOnly" /></label>

									</div>
								</div>
								<div class="fields">
									<label> <mmr:message messageId="label.add.trade.show.pickup" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.tradeShowPickup" name="fc.tradeShowPickup" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label><mmr:message messageId="label.add.ref.code" />  </label>
									<div class="controls">
										<span>:</span> <label> <s:label key="fc.refCode" name="fc.refCode" /></label>

									</div>
								</div>
								
								</div>
								
								<div class="rows">
							<div class="fields">
									<label> <mmr:message messageId="label.add.appointment.pickup" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.appointmentPickup" name="fc.appointmentPickup" /></label>

									</div>
								</div>
								<div class="fields">
									<label>  <mmr:message messageId="label.add.taligate.delivery" /></label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.taligateDelivery" name="fc.taligateDelivery" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label> <mmr:message messageId="label.add.hold.pickup" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.holdForPickup" name="fc.holdForPickup" /></label>

									</div>
								</div>
								
								
								
								</div>
							
							<div class="rows">
							<div class="fields">
									<label> <mmr:message messageId="label.add.trade.show.delivery" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.tradeShowDelivery" name="fc.tradeShowDelivery" /></label>

									</div>
								</div>
								<div class="fields">
									<label> <mmr:message messageId="label.add.appointment.delivery" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.appointmentDelivery" name="fc.appointmentDelivery" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label> <mmr:message messageId="label.add.saturday.delivery" />  </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.saturdayDelivery" name="fc.saturdayDelivery" /></label>

									</div>
								</div>
								
								</div>
								
								<div class="rows">
							<div class="fields">
									<label> <mmr:message messageId="label.add.inside.pickup" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.insidePickup" name="fc.insidePickup" /></label>

									</div>
								</div>
								<div class="fields">
									<label> <mmr:message messageId="label.add.taligate.pickup" /> </label>
									<div class="controls">
										<span>:</span> <label> <s:checkbox key="fc.taligatePickup" name="fc.taligatePickup" /></label>

									</div>
								</div>
								
								<div class="fields">
									<label><mmr:message messageId="label.add.signature.required" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.signatureRequired" /></label>

									</div>	
							</div>
								
								</div>
								<div class="rows">
								<div class="fields">
									<label><mmr:message messageId="label.add.dutiable.code" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.dutiableCode" /></label>

									</div>	
							</div>
							</div>
								
							<div class="content_header">
									<div class="cont_hdr_title"> <mmr:message messageId="label.add.package.details" />:</div>
									
								</div>
							<div class="rows">
							<div class="fields">
									<label><mmr:message messageId="label.add.no.of.packages" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.quantity" /></label>

									</div>
								</div>  
								</div>
								<div class="content">	
		<div class="content_table" style="background-color: #fff">
			<div class="cont_data_body borderLeftRight" >
				<table style="width:100%; font-size:10px; text-align:left; padding:0px;" id="dimention_grid" >
					<thead>
						<tr>
							
							<th><mmr:message messageId="label.ghead.length"/></th>
							<th><mmr:message messageId="label.ghead.width"/></th>
							<th><mmr:message messageId="label.ghead.height"/></th>
							<th><mmr:message messageId="label.ghead.weight"/></th>
							<th><mmr:message messageId="label.ghead.cod"/> ($)</th>
							<th style="width:100px !important;margin-left: 20px;"><mmr:message messageId="label.ghead.insurance"/> ($)</th>
							<s:if test='%{shippingOrder.packageTypeId.type == "type_pallet"}'>
							<th ><span style="width:100px !important;"><mmr:message messageId="label.ghead.freightclass"/></span></th>
							<th style="width:70px !important;"><mmr:message messageId="label.ghead.type"/></th>
							</s:if>
							<th style="width:150px !important;"><mmr:message messageId="label.ghead.description"/></th>
							<th style="width:100px !important;">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					
					<s:iterator id="customer" value="showfutRefPackageList" status="rowstatus">
					<tr>
					
					<td> <s:property value="packLength" /></td>
					<td> <s:property value="packWidth" /></td>
					<td> <s:property value="packHeight" /></td>
					<td> <s:property value="packWeight" /></td>
					<td> <s:property value="packCodAmount" /></td>
					<td> <s:property value="packInsuranceAmount" /></td>
					<td> <s:property value="packDescription" /></td>
				    
					
					</tr>
					
					
					</s:iterator>
					
					</tbody>
					</table>
					</div>
					</div>
					</div>
			 <div class="content_header">
									<div class="cont_hdr_title"><label><mmr:message messageId="label.add.quick.ship" />
									</label><span>:</span> </div>
								</div>
								<div class="rows">

								<div class="fields">
									<label><mmr:message messageId="label.add.ready.time" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.readyTime" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.close.time" /></label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.closeTime" /></label>

									</div>
								</div>
							</div>
							
							<div class="rows">

								<div class="fields">
									<label><mmr:message messageId="label.add.quickship.location" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.pickupLocation" /></label>

									</div>
								</div>
								<div class="fields">
									<label><mmr:message messageId="label.add.quickship.instruction" />
									</label>
									<div class="controls">
										<span>:</span> <label><s:property value="fc.pickupInstruction" /></label>

									</div>
								</div>
								</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
