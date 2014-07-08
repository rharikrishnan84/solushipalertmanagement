<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html> 
<head> 
    <title><s:text name="track.shipment.title"/></title> 
</head> 
<body> 
<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/common.css' includeContext="true"/>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/CarrierServices.js"></script>
<SCRIPT language="JavaScript">
	function submitform()
	{
	 document.trackingShipment.action = "search.track.shipment.action";
	 document.trackingShipment.submit();
	}

	function trackShipment(url){
		window.open(url,'','toolbar=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,directories=no,location=no,width=760,height=540,left=100,top=100');

	}
	
	function generateOrderDetail(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');

	}
</SCRIPT>
<head>
  <sx:head />
</head>
<div class="form-container"> 
<s:form action="search.track.shipment.action" name="trackingShipment">
<div id="right_left_new">
<div id="bottom_tabs_new">
		 <table width="50%" border="0" cellspacing="0" cellpadding="0">

						<tr>
		 			  <td width="10%" align="right" ><img src="<%=request.getContextPath()%>/mmr/images/blue_left.gif"/></td>
			 			  <td width="70%"  align="center" background="<%=request.getContextPath()%>/mmr/images/blue_middle.gif" class="tab_text"><a href="#"><mmr:message messageId="label.track.shipment"/></a></td>
			  <td width="5%" ><img src="<%=request.getContextPath()%>/mmr/images/blue_right.gif" /></td>
			  <td width="15%" >&nbsp;</td>
			  
			  
			</tr>
			
		  </table>
	</div>
<div id="bottom_table" class="text_01">
<table width="100%" border="0" cellpadding="2" cellspacing="0">
             
               <tr>
                  <td width="140">&nbsp;</td>
                  <td width="150">&nbsp;</td>
                </tr>
				 <tr>
                  <td colspan="2">&nbsp;</td>

            	</tr>
                <tr>
                  <td class="text_03"><mmr:message messageId="label.track.carrier"/>:</td>
                  <td><s:select cssClass="text_01" cssStyle="width:160px;" listKey="carrierId" listValue="carrierName" name="shippingOrder.carrierId" list="#session.carriers" onchange="showCarrierServices();" headerKey="-1" id="firstBox" theme="simple"/></td>
                </tr>
             <tr>
                  <td class="text_03"><mmr:message messageId="label.track.service"/>:</td>
                  <td id="servicesId"><s:select cssClass="text_01" cssStyle="width:160px;" listKey="id" listValue="name" name="shippingOrder.serviceId" headerKey="1" list="#session.services" id="secondBox"  theme="simple"  cssClass="text_01"/></td>
                </tr>

			 <tr>
                  <td class="text_03"><mmr:message messageId="label.track.status"/>:</td>
                  <td><s:select listKey="id"  listValue="name" cssStyle="width:160px;"  name="shippingOrder.statusId" headerKey="1"  cssClass="text_02" list="#session.orderStatus"/></td>
                </tr>

			 <tr>
                  <td class="text_03"><mmr:message messageId="label.track.track.number"/>:</td>
                  <td><s:textfield size="20"  id="shippingOrder.masterTrackingNum" key="shippingOrder.masterTrackingNum" name="shippingOrder.masterTrackingNum" cssClass="text_02"/></td>
               </tr>
               
               <tr>
                  <td class="text_03">From Date:</td>
                  <td><sx:datetimepicker displayFormat="yyyy-MM-dd" name="shippingOrder.fromDate"  cssClass="text_02"/></td>

				  <td class="text_03">To Date:</td>
                  <td><sx:datetimepicker displayFormat="yyyy-MM-dd" name="shippingOrder.toDate"  cssClass="text_02"/></td>
               </tr>
              

			 <tr>
                  <td class="text_03"></td>
                  <td><s:submit cssStyle="height:25px;width:25px;" type="image" src="%{#session.ContextPath}/mmr/images/Search.jpg" /><a href="javascript: submitform();" class="text_03">Search</a></td>
                </tr>
				
</table>
</div>
 <s:if test="#session.ORDERLISTSIZE > 0 ">
<div id="bottom_table_New_rate" class="text_03" align="center">
<div>
<display:table width="100%" id="order_table"  name="orderList" export="false" uid="row" sort="list"  pagesize="15" requestURI="/search.track.shipment.action">

	<display:setProperty name="paging.banner.items_name" value=""></display:setProperty>
	<display:setProperty name="paging.banner.some_items_found" value=""/>
	<display:setProperty name="paging.banner.all_items_found" value=""></display:setProperty>
	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
	<display:setProperty name="paging.banner.group_size" value="3"></display:setProperty>

		<s:set name="oId" value="orderList[#attr.row_rowNum-1].getId()" />
		<s:set name="trackingURL" value="orderList[#attr.row_rowNum-1].getTrackingURL()" />
		<display:column class="text_01" property="referenceCode" sortable="true" title="#"/>
		<display:column class="text_01" property="carrierName"  sortable="true" title="Carrier" />
		<display:column class="text_01" property="serviceName"  sortable="true" title="Service Name" />
		<display:column class="text_01" property="orderStatus"  sortable="true" title="Order Status" />
		<display:column  class="text_01" property="shippingFromAddress"  sortable="true" title="Ship From Address"  maxLength=""/>
		<display:column class="text_01" property="shippingToAddress"  sortable="true" title="Ship To Address" />
		
		<display:column title="" >
			<s:a title="Order Detail" href="javascript:generateOrderDetail('track.shipment.detail.action?id=%{#oId}')"><img title="" height="20px;" width="20px;" src="<%=request.getContextPath()%>/mmr/images/Details.jpg" border="0" alt="order details"/></s:a>
		</display:column>
		<display:column title="">
			<s:a title="Track Shipment" href="javascript:trackShipment('%{trackingURL}')" > <img height="20px;" width="20px;" src="<%=request.getContextPath()%>/mmr/images/Track-order.jpg" border="0" alt="trackOrder"/></s:a>
		</display:column>
	</display:table>
</div></div>
 </s:if>

  </div>
   
</s:form>
</div>
</div>
