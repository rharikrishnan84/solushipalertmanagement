<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<html> 
<head>
    <title><s:text name="user.form.title"/></title> 
</head> 
<body> 
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<SCRIPT language="JavaScript">
	
	function generateLabel(url){
		window.open(url,'','width=760,height=540,left=100,top=100');

	}
</SCRIPT>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<s:form action="new.shipment" name="packageform" theme="simple" >
<div class="form-container" valign="top" >
	
	<div style="height:425px; width:100%;">
	<table align="left">
		<tr align="left">
		<td align="left">
			<div id="bottom_table2_new" class="text_01">
			  <table  border="0" cellspacing="0" cellpadding="2">
			  
			   <tr>
                  <td width ="350" colspan="4" class="shipto" style="padding-top:3px;" class="text_01">Order Detail</td>
                 
                  <td width ="350" colspan="4" align="right" class="icon_btns" style="padding-right:15px; padding-top:10px;">
                  <s:submit type="image" src="<%=request.getContextPath()%>/mmr/images/truck.gif"/> 
                  &nbsp;<mmr:message messageId="label.navigation.next"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:a href="stageOne.action"><img src="<%=request.getContextPath()%>/mmr/images/hand.gif" border="0"/>&nbsp;<mmr:message messageId="label.navigation.cancel"/></s:a></td>
                </tr>
				 <tr>
                  <td colspan="8">&nbsp;</td>
                </tr>
                <tr>
					<s:set name="oid" value="%{shippingOrder.id}"/>
						<td colspan="8" class="text_01"><s:a href="javascript:generateLabel('getShippingLabel.action?id=%{#oid}')">Generate Label</s:a></td>
				</tr>
<tr>
<td align="left"  valign="top"  colspan="4"  class="text_01">
<fieldset>
        <legend class="contentTitle"><strong>Pick Up From </strong></legend>
        <table border="0" cellspacing="0" cellpadding="3"  class="text_01">
          <tr>
            <td>Company:</td>
            <td><strong><s:property value="%{shippingOrder.fromAddress.consigneeName}"/></strong></td>
          </tr>
          <tr>
            <td>Address:&nbsp;</td>
            <td><strong><s:property value="%{shippingOrder.fromAddress.address1}"/><br/><s:property value="%{shippingOrder.fromAddress.city}"/> ,<s:property value="%{shippingOrder.fromAddress.province}"/> ,<s:property value="%{shippingOrder.fromAddress.postalCode}"/></strong></td>
          </tr>
          <tr>
            <td>Phone:</td>
            <td><strong><s:property value="%{shippingOrder.fromAddress.phone}"/></strong></td>
          </tr>
          <tr><td>Email:</td>
            <td><strong><s:property value="%{shippingOrder.fromAddress.contactEmail}"/></strong></td>
          </tr>
          <tr>
            <td>Attn:</td>
            <td><strong> <s:property value="%{shippingOrder.fromAddress.contactName}"/> </strong></td>
          </tr>
          <tr><td>Instructions:</td>
            <td><strong>  <s:property value="%{shippingOrder.fromAddress.instruction}"/>  
              </strong></td>
          </tr>
        </table>
   </fieldset>
   </td>
      <td align="left"  colspan="4"  valign="top" class="text_01"><fieldset>
        <legend class="contentTitle"><strong>Ship To</strong></legend>
        <table border="0" cellspacing="0" cellpadding="3"  class="text_01">
          <tr>
            <td>Company:</td>
             <td><strong><s:property value="%{shippingOrder.toAddress.consigneeName}"/></strong></td>
          </tr>
          <tr>
            <td>Address:&nbsp;</td>
           <td><strong><s:property value="%{shippingOrder.toAddress.address1}"/><br/><s:property value="%{shippingOrder.toAddress.city}"/> ,<s:property value="%{shippingOrder.toAddress.province}"/> ,<s:property value="%{shippingOrder.toAddress.postalCode}"/></strong></td>
          </tr>
          <tr>
            <td>Phone:</td>
            <td><strong><s:property value="%{shippingOrder.toAddress.phone}"/></strong></td>
          </tr>

          <tr>
            <td>Email:</td>
            <td><strong><s:property value="%{shippingOrder.toAddress.contactEmail}"/></strong></td>
          </tr>
          <tr><td>Attn:</td>
            <td><strong> <s:property value="%{shippingOrder.toAddress.contactName}"/>  </strong></td>
          </tr>
          <tr>
            <td>Instructions:</td>
            <td><strong>  <s:property value="%{shippingOrder.toAddress.instruction}"/>    </strong></td>
          </tr>

        </table>
        </fieldset>
         </td>

				</tr>
				</table>


<table  border="0" cellspacing="0" cellpadding="0">
	<tr>
      <td align="left" valign="top"  colspan="4">
      <table  class="text_01">
          <tr>
            <td>Transaction #</td>
            <td><s:property value="%{shippingOrder.id}"/></td>
          </tr>
          <tr>
            <td>Carrier</td>
            <td><s:property value="%{shippingOrder.carrierName}"/></td>
          </tr>
          <tr>
            <td>Service</td>
            <td><s:property value="%{shippingOrder.serviceName}"/></td>
          </tr>
          <tr>
            <td>Pick up Confirmation #</td>
            <td><s:property value="%{shippingOrder.carrierName}"/></td>
          </tr>
          <tr>
            <td>Packaging</td>
            <td>Packaging</td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td> <s:property value="%{shippingOrder.quantity}"/></td>
          </tr>
          <tr>
            <td colspan="2" class="text_01"><a href="#" onClick="if (hidden) { 	document.getElementById('hide').style.display = 'block'; hidden = false; } else { document.getElementById('hide').style.display = 'none'; hidden = true; }">Package Dimensions (inches)</a></td>
          </tr>
		<tbody id="hide" style="display: none">	 
           	<tr>
				<td align="center" colspan="2">1 x 1 x 1</td>
			</tr>			
		</tbody>
          <tr>
            <td>Weight (Actual/Dim)</td>
            <td>1 lbs / 1 lbs </td>
          </tr>
          <tr> <td>COD</td>
            <td>   N/A </td>
          </tr>
          <tr>
            <td>Ins. Value</td>
            <td>$0.00</td>
          </tr>
          <tr>
            <td>Dangerous Goods</td>
            <td> <s:property value="%{#session.shippingOrder.dangerousGoods}"/></td>
          </tr>
       </table>
      </td>
    
      <td align="left" valign="top" colspan="4" class="text_01">
		<table width="100%" class="text_01">
          <tr>
            <td nowrap="nowrap">Shipment Date</td>
            <td nowrap="nowrap"><strong><s:property value="%{shippingOrder.scheduledShipDate}"/></strong></td>
          </tr>
          
        </table>
        <br />
        <table width="100%" border="0" cellpadding="3" cellspacing="0" class="text_01">
          		<tr>
            		<td align="left" valign="middle" nowrap="nowrap">Base Charge </td>
            		<td align="left" valign="middle">$</td>
            		<td align="right" valign="middle"><strong><s:property value="%{shippingOrder.baseCharge}"/></strong></td>
          		</tr>
          		<tr>
            		<td align="left" valign="middle" nowrap="nowrap">Fuel Charge </td>
            		<td align="left" valign="middle">$</td>
            		<td align="right" valign="middle"><strong><s:property value="%{shippingOrder.fuelCharges}"/>1.06</strong></td>
          		</tr>
           		
          		<tr>
            		<td height="1" colspan="3" align="left" valign="middle"><hr size="1" /></td>
          		</tr>
          		<tr>
            		<td align="left" valign="middle" nowrap="nowrap"><strong>Total Charges</strong> </td>
            		<td align="left" valign="middle">CAD $</td>
            		<td align="right" valign="middle"><strong><s:property value="%{shippingOrder.totalCharge}"/></strong></td>
          		</tr>
        </table>
        
	</td>
 </tr>
 
  <tr>
     <td class="text_01" colspan="8" align="left" valign="middle" bgcolor="#F7F7F7">Carrier Tracking # <strong><s:property value="%{shippingOrder.masterTrackingNumber}"/></strong></td>
   </tr>
   
   <tr>
		      <td colspan="4" class="text_01" align="left" valign="top"><fieldset>
		        <legend class="contentTitle">References</legend>
		        <table class="text_01" border="0" cellspacing="0" cellpadding="3">
		          <tr>
		                <td nowrap="nowrap">Reference:</td>
		            <td width="50%"><strong></strong></td>
		          </tr>
		        </table>
		        </fieldset></td>
		    </tr>
		    
		    <tr>
		      <td colspan="2" align="left" valign="top" class="text_01"><fieldset>
		        <legend class="contentTitle"><strong>Order Status History</strong></legend>
		        <table border="0" cellspacing="0" cellpadding="3" class="text_01">
		            <tr>
		              <td width="10%" nowrap="nowrap">Status:</td>
		              <td width="20%"><strong><s:property value="%{shippingOrder.orderStatus}"/> </strong></td>
		              <td width="10%" nowrap="nowrap">Assigned By:</td>
			              	<td width="60%" width="505"><strong>SYSTEM</strong></td>
		            </tr>
		            <tr>
		              <td width="10%"  nowrap="nowrap">Date:</td>
		              <td width="20%" ><strong><s:date format="dd-MMM-yyyy" name="%{shippingOrder.scheduledShipDate}" /><s:property value="%{shippingOrder.scheduledShipDate}"/> </strong></td>
		              <td  width="10%" nowrap="nowrap">Comment:</td>
		              <td width="60%" ><strong></strong></td>
		            </tr>
		            <tr>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            </tr>
		            <tr>
		              <td width="10%" nowrap="nowrap">Status:</td>
		              <td width="20%"><strong>
		                CANCELLED </strong></td>
		              <td width="10%" nowrap="nowrap">Assigned By:</td>
				              <td width="60%" width="505"><strong>sumasoft</strong></td>
		            </tr>
		            <tr>
		              <td width="10%"  nowrap="nowrap">Date:</td>
		              <td width="20%" ><strong>08/08/2009</strong></td>
		              <td  width="10%" nowrap="nowrap">Comment:</td>
		              <td width="60%" ><strong></strong></td>
		            </tr>
		            <tr>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            </tr>
		        </table>
		        </fieldset></td>
		    </tr>
   
</table>





				
				
			</div>
		</td>
		</tr>
		
		<tr align="left">
		<td align="left" colspan="8">

		</td>
		</tr>
	</table>
	</div>
	

</div>
</s:form> 
</body>
</html>
    
    
 