<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<SCRIPT language="JavaScript">
	
	function generateLabel(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');

	}
</SCRIPT>

<link rel="stylesheet" type="text/css"
	href="<s:url value='/mmr/styles/shiplinx_closeWindow_styles.css' includeContext="true"/>" />
	
<div style="height:425px; width:100%;">
	<table align="left" border="0">
		<tr align="left">
		<td align="left">
			<div id="bottom_table2_new" class="text_01">
			  <table  border="0" cellspacing="0" cellpadding="2">
			  
			   <tr>
                  <td width ="350" colspan="4" class="shipto" class="text_01" style="padding-left:15px; padding-top:10px;">Order Detail</td>
                 
                  <td width ="350" colspan="4" align="right" class="icon_btns" style="padding-right:15px; padding-top:10px;">
                  </td>
                </tr>
				 <tr>
                  <td colspan="8">&nbsp;</td>
                </tr>
                <tr>
					<s:set name="oid" value="%{shippingOrder.id}"/>
						<td colspan="8" align="right" class="text_04">&nbsp;&nbsp;<s:a href="javascript:generateLabel('getShippingLabel.action?id=%{#oid}')">Generate Label</s:a>&nbsp;&nbsp;</td>
				</tr>
<tr>
<td align="left"  valign="top"  colspan="4"  class="text_01">
<fieldset>
        <legend  class="text_03"><strong>Pick Up From </strong></legend>
        <table border="0" cellspacing="0" cellpadding="3"  class="text_01">
          <tr>
            <td class="text_03">Company:</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.fromAddress.consigneeName}"/></strong></td>
          </tr>
          <tr>
            <td class="text_03">Address:&nbsp;</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.fromAddress.address1}"/><br/><s:property value="%{shippingOrder.fromAddress.city}"/> ,<s:property value="%{shippingOrder.fromAddress.province}"/> ,<s:property value="%{shippingOrder.fromAddress.postalCode}"/></strong></td>
          </tr>
          <tr>
            <td class="text_03">Phone:</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.fromAddress.phone}"/></strong></td>
          </tr>
          <tr><td class="text_03">Email:</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.fromAddress.contactEmail}"/></strong></td>
          </tr>
          <tr>
            <td class="text_03">Attn:</td>
            <td class="text_04"><strong> <s:property value="%{shippingOrder.fromAddress.contactName}"/> </strong></td>
          </tr>
          <tr><td class="text_03">Instructions:</td>
            <td class="text_04"><strong>  <s:property value="%{shippingOrder.fromAddress.instruction}"/>  
              </strong></td>
          </tr>
        </table>
   </fieldset>
   </td>
   <td align="left"  colspan="4"  valign="top" class="text_01">
   <fieldset>
        <legend class="text_03"><strong>Ship To</strong></legend>
        <table border="0" cellspacing="0" cellpadding="3"  class="text_01">
          <tr>
            <td class="text_03">Company:</td>
             <td  class="text_04"><strong><s:property value="%{shippingOrder.toAddress.consigneeName}"/></strong></td>
          </tr>
          <tr>
            <td class="text_03">Address:&nbsp;</td>
           <td class="text_04"><strong><s:property value="%{shippingOrder.toAddress.address1}"/><br/><s:property value="%{shippingOrder.toAddress.city}"/> ,<s:property value="%{shippingOrder.toAddress.province}"/> ,<s:property value="%{shippingOrder.toAddress.postalCode}"/></strong></td>
          </tr>
          <tr>
            <td class="text_03">Phone:</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.toAddress.phone}"/></strong></td>
          </tr>

          <tr>
            <td class="text_03">Email:</td>
            <td class="text_04"><strong><s:property value="%{shippingOrder.toAddress.contactEmail}"/></strong></td>
          </tr>
          <tr><td class="text_03">Attn:</td>
            <td class="text_04"><strong> <s:property value="%{shippingOrder.toAddress.contactName}"/>  </strong></td>
          </tr>
          <tr>
            <td class="text_03">Instructions:</td>
            <td class="text_04"><strong>  <s:property value="%{shippingOrder.toAddress.instruction}"/>    </strong></td>
          </tr>

        </table>
   </fieldset>
   </td>
	</tr>
	<tr>
      <td align="left" valign="top"  colspan="4" width ="350" >
	  <fieldset>
        <legend  class="text_03"><strong>Transaction</strong></legend>
      <table border="0" cellspacing="0" cellpadding="3"  class="text_01" >
          <tr>
            <td class="text_03">Transaction#</td>
            <td class="text_04"><s:property value="%{shippingOrder.id}"/></td>
          </tr>
          <tr>
            <td class="text_03">Carrier</td>
            <td class="text_04"><s:property value="%{shippingOrder.carrierName}"/></td>
          </tr>
          <tr>
            <td class="text_03">Service</td>
            <td class="text_04"><s:property value="%{shippingOrder.serviceName}"/></td>
          </tr>
          <tr>
            <td class="text_03">Pick up Confirmation#</td>
            <td class="text_04"><s:property value="%{shippingOrder.confirmDelivery}"/></td>
          </tr>
          <tr>
            <td class="text_03">Packaging</td>
            <td class="text_04"><s:property value="%{shippingOrder.packageTypeId.name}"/></td>
          </tr>
          <tr>
            <td class="text_03">Quantity</td>
            <td class="text_04"> <s:property value="%{shippingOrder.quantity}"/></td>
          </tr>
          <tr>
            <td colspan="1" class="text_03">Package Dimensions</td>
          	<td colspan="2"  class="text_04"><s:property value="%{shippingOrder.packages[0].height}"/> x <s:property value="%{shippingOrder.packages[0].width}"/> x <s:property value="%{shippingOrder.packages[0].length}"/></td>
		  </tr>			
          <tr>
            <td class="text_03">Weight (Actual/Dim)</td>
            <td class="text_04"><s:property value="%{shippingOrder.packages[0].weight}"/>lbs / <s:property value="%{shippingOrder.packages[0].weight}"/> lbs </td>
          </tr>
          <tr> <td class="text_03">COD</td>
            <td class="text_04">   N/A </td>
          </tr>
          <tr>
            <td class="text_03">Ins. Value</td>
            <td class="text_04">$<s:property value="%{shippingOrder.insuredAmount}"/></td>
          </tr>
          <tr>
            <td class="text_03">Dangerous Goods</td>
            <td class="text_04"> <s:property value="%{shippingOrder.dangerousGoods}"/></td>
          </tr>
		   <tr>
			 <td class="text_03" align="left" valign="middle" bgcolor="#cceeff">Carrier Tracking #</td> <td class="text_01" align="left" valign="middle" bgcolor="#cceeff"  class="text_04"><strong><s:property value="%{shippingOrder.masterTrackingNumber}"/></strong></td>
		   </tr>
       </table>
	    </fieldset>
      </td>
    
      <td align="left" valign="top" colspan="4" class="text_01" width ="350"  style="padding-right:0px;padding-left:0px;">
	   <fieldset>
        <legend  class="text_03"><strong>Charges</strong></legend>
		<table border="0" cellspacing="0" cellpadding="3"  width="100%" class="text_01" >
          <tr>
            <td nowrap="nowrap" class="text_03">Shipment Date</td>
            <td nowrap="nowrap"  align="left"  class="text_04"><strong><s:property value="%{shippingOrder.scheduledShipDate}"/></strong></td>
          </tr>
          
        </table>
        <br />
        <table width="100%" border="0" cellpadding="3" cellspacing="0" class="text_01">
          	
          		<tr>
            		<td align="left" valign="middle" nowrap="nowrap" class="text_03">Base Charge </td>
            		<td align="left" valign="middle" class="text_04">$</td>
            		<td align="right" valign="middle"  class="text_04"><strong><s:property value="%{shippingOrder.rateList[0].baseCharge}"/></strong></td>
          		</tr>
          	<s:iterator  value="%{shippingOrder.rateList[0].charges}">
				<tr>
            		<td align="left" valign="middle" nowrap="nowrap" class="text_03"><s:property value="%{name}"/> </td>
            		<td align="left" valign="middle" class="text_04">$</td>
            		<td align="right" valign="middle"  class="text_04"><strong><s:property value="%{charge}"/></strong></td>
          		</tr>
			</s:iterator >
          		
           		
          		<tr>
            		<td height="1" colspan="3" align="left" valign="middle"><hr size="1" /></td>
          		</tr>
          		<tr>
            		<td align="left" valign="middle" nowrap="nowrap" class="text_03"><strong>Total Charges</strong> </td>
            		<td align="left" valign="middle"  class="text_04">
            		<s:if test="%{shippingOrder.fromAddress.countryName =='US'">
            		US $
            		</s:if>
            		<s:else>
            		CAD $
            		</s:else>
            		</td>
            		<td align="right" valign="middle"  class="text_04"><strong><s:property value="%{shippingOrder.rateList[0].totalCost}"/></strong></td>
          		</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
			
				
        </table>
		 </fieldset>
        
	</td>
 </tr>
 
 
   
   <tr>
		      <td colspan="8" class="text_01" align="left" valign="top"><fieldset>
		        <legend class="text_03">References</legend>
		        <table class="text_01" border="0" cellspacing="0" cellpadding="3">
		          <tr>
		                <td nowrap="nowrap" class="text_03">Reference:</td>
		            <td  class="text_04"><strong><s:property value="%{shippingOrder.referenceCode}"/></strong></td>
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