<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<SCRIPT language="JavaScript">


	function validateOrder(packageType,numPackages) {	
		//var packageType = packageType;
		var numPackages = numPackages;
		//var packageType = ${shippingOrder.packageTypeId};
		//var numPackages = %{shippingOrder.quantity};	
		
		var error = '';
			
		for(var x=0;x<numPackages;x++) {
			
			var lengthName = 'shippingOrder.packageArray[' + x + '].length';
			var widthName = 'shippingOrder.packageArray[' + x + '].width';
			var heightName = 'shippingOrder.packageArray[' + x + '].height';
			var weightName = 'shippingOrder.packageArray[' + x + '].weight';
			var descrName = 'shippingOrder.packageArray[' + x + '].description';
			var codName = 'shippingOrder.packageArray[' + x + '].codAmount';
			var insName = 'shippingOrder.packageArray[' + x + '].insuranceAmount';
			
			var length = document.getElementById(lengthName).value;
			var width = document.getElementById(widthName).value;
			var height = document.getElementById(heightName).value;
			var weight = document.getElementById(weightName).value;
			var descr = document.getElementById(descrName).value;
			var cod = document.getElementById(codName).value;
			var ins = document.getElementById(insName).value;

			//alert(length + '/' + width + '/' + height + '/' + weight + '/' + descr);								
			var packageNumber = x+1;
			
			if(length.charAt(0) == '' || !isAllDigits(length) || length<=0) {	
				
				var msg =  'Package #' + packageNumber + ' has an invalid length\n';
				error += msg;
				
			}
			if(width.charAt(0) == ''  || !isAllDigits(width) || width<=0 ) {
				var msg =  'Package #' + packageNumber + ' has an invalid width\n';
				error += msg;
				
			}
			if(height.charAt(0) == '' || !isAllDigits(height) || height<=0) {
				var msg =  'Package #' + packageNumber + ' has an invalid height\n';
				error += msg;
			}
			if(weight.charAt(0) == ''  || !isAllDigits(weight) || weight<=0) {
				var msg =  'Package #' + packageNumber + ' has an invalid weight\n';
				error += msg;
			}
			if(cod.charAt(0) == ''  || !isAllDigits(cod) || cod<=0) {
				var msg =  'Package #' + packageNumber + ' has an invalid cod\n';
				error += msg;
			}
			if(ins.charAt(0) == ''  || !isAllDigits(ins) || ins<=0) {
				var msg =  'Package #' + packageNumber + ' has an invalid ins\n';
				error += msg;
			}
			//pallet		
			//if(packageType == 4) {
			//	if(descr == '') {
			//		var msg =  'Package #' + packageNumber + ' has no description\n';
			////		error += msg;
			//	}
			//}	
				
		}
		
		if(error != '') {
			alert(error);
			return false;
		}
	
		return true;	
	}
	
	function submitform(method)
	{
	 document.orderForm.action = "shipment.stageThree.action?method="+method;
	 document.orderForm.submit();
	}  
</script>


<html>
	<head>
		<title></title>
	</head>
	<body>
	<div>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container">
<s:form action="shipment.stageThree.quote" name="orderForm" >
	<div id="right_left_new" valign="top"  >
		<div id="bottom_tabs_shippment">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			   <td width="5%" align="right" valign="top"><img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif"/></td>
			  <td width="5%"   valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text">
				<a href="#">Quote</a>
			  </td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			  
			  <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_blue_left.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_blue_middle.gif" class="tab_text"><a href="#">
                  	<mmr:message messageId="label.shippingOrderTab.dimension"/>
                  </a></td>
			  <td width="3%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_blue_right.gif" /></td>
			 
			 <td  width="2%"  align="right" valign="top" >
				 <img src="<%=request.getContextPath()%>/mmr/images/new_grey_left_big.gif" /></td>
			  <td width="10%"  height="100%" valign="center" align="center"  background="<%=request.getContextPath()%>/mmr/images/new_grey_middle.gif" class="tab_text"><a href="shipment.stageThree.quote.action">
                  	<mmr:message messageId="label.shippingOrderTab.rateList"/>
                  </a></td>
			  <td width="60%" valign="top" ><img src="<%=request.getContextPath()%>/mmr/images/new_grey_right_big.gif" /></td>
			 
			 
			</tr>
		  </table>
	</div>
	<div style="height:425px; width:100%;">
		<div id="bottom_table" class="text_01">
			  <table border="0" width="100%" cellspacing="0" cellpadding="2" style="margin-left:-10px;">
			  <tr  align="right">
                  <td  colspan="4"  align="left" class="shipto" style="padding-top:3px; padding-left:15px;">Dimension</td>
					 <td colspan="10" align="right" class="icon_btns"  align="right" style="padding-right:15px; padding-top:10px;">
				<set name="packageTypeId" value="shippingOrder.packageTypeId.packageTypeId"/>
                  <s:submit type="image" src="%{#session.ContextPath}/mmr/images/truck.gif" onClick="return (validateOrder(3,1))"/> 
                  &nbsp;<mmr:message messageId="label.navigation.next"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:a href="#"> <img src="<%=request.getContextPath()%>/mmr/images/hand.gif" border="0"/>
                  &nbsp;<mmr:message messageId="label.navigation.cancel" /> </s:a></td>
                </tr>
                <tr>
				<s:if test='%{shippingOrder.packageTypeId.type == "type_pallet"}'>
                  <td width="20">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                  <td width="40">&nbsp;</td>
                  <td width="40">&nbsp;</td>
				  <td width="40">&nbsp;</td>
				  <td width="40">&nbsp;</td>
				  <td width="40">&nbsp;</td>
				  <td width="40">&nbsp;</td>
				  <td width="40">&nbsp;</td>
				  <td width="120">&nbsp;</td>
				  <td width="60">&nbsp;</td>
				</s:if>
				<s:else>

				  <td width="20">&nbsp;</td>
                  <td width="60">&nbsp;</td>
                  <td width="60">&nbsp;</td>
                  <td width="60">&nbsp;</td>
				  <td width="70">&nbsp;</td>
				  <td width="70">&nbsp;</td>
				  <td width="70">&nbsp;</td>
				  <td width="180">&nbsp;</td>
				 <td width="80">&nbsp;</td>
				 </s:else>
                </tr>
               

                  <tr bgcolor="#cccccc">
				  
				 
				
					<td class="text_03">#</td>
	                <td class="text_03">L(in)</td>
	                <td class="text_03">W(in)</td>
	                <td class="text_03">H(in)</td>
	                <td class="text_03">Wt (lbs)</td>
	                <td class="text_03">COD Amt ($)</td>
	                <td class="text_03">Ins Amt ($)</td>
					<s:if test='%{shippingOrder.packageTypeId.type == "type_pallet"}'>
                    	<td class="text_03">Freight Class</td>
                    	<td class="text_03">Type</td>
					</s:if>    
					<td class="text_03">Description</td>
                	<td class="text_03"></td>
               
				
				</tr>
                
				<s:bean name="org.apache.struts2.util.Counter" var="counter">
					<s:param name="last" value="%{shippingOrder.quantity}" />
				</s:bean>

				<s:set name="packageType" value="%{shippingOrder.packageTypeId.type}"/>
				<s:iterator  value="%{#session.shippingOrder.packages}"  status="counterIndex">
 				<tr>
					<td class="text_01"><s:property value="%{#counterIndex.index+1}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].length" name="shippingOrder.packages[%{#counterIndex.index}].length"  cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].length}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].width" name="shippingOrder.packages[%{#counterIndex.index}].width"  cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].width}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].height" name="shippingOrder.packages[%{#counterIndex.index}].height"  cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].height}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].weight" name="shippingOrder.packages[%{#counterIndex.index}].weight"  cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].weight}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].codAmount" name="shippingOrder.packages[%{#counterIndex.index}].codAmount" cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].codAmount}"/></td>
	                <td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].insuranceAmount" name="shippingOrder.packages[%{#counterIndex.index}].insuranceAmount"  cssClass="text_02" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].insuranceAmount}"/></td>
					<!-- Package Type Pallet = 4 -->
					<s:if test='%{#packageType == "type_pallet"}'>
                    	<td class="text_01">
                    	<s:select cssClass="text_01" name="shippingOrder.packages[%{#counterIndex.index}].freightClass" list="{55,60,65,70,77,77.5,85,92.5,100,110,125,150,175,200,250,300,400}" value="%{#session.shippingOrder.packages[#counterIndex.index].freightClass}"/>
                    		
      					</td>
                    	
                    	<td class="text_01">
                    	<s:select cssClass="text_01" name="shippingOrder.packages[%{#counterIndex.index}].type" list="{'Drum','Boxes','Rolls','Pipes/TubesBales','Bags','Cylinder','Pails,Reels'}" value="%{#session.shippingOrder.packages[#counterIndex.index].type}"/>
                    	</td>
					</s:if>      
					<td class="text_01"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].description" name="shippingOrder.packages[%{#counterIndex.index}].description" value="%{#session.shippingOrder.packages[#counterIndex.index].description}" cssClass="text_02" theme="simple" size="20"/></td>
                 	<td class="text_01">
					
					<s:if test="%{#counterIndex.index == 0}">
						<a href="javascript:void(0)" onClick="allTheSame2('35')"/>All same</a>
					</s:if>
					
					<s:else>
					
					<a href="javascript:void(0)" onClick="sameAsAbove2('<s:property value="%{#counterIndex.index}"/>')"  />As above
					<s:param name="view" value="<s:property value='%{#counterIndex.index}'/>"  />
					</a> 
					</s:else>
					</td>
                </tr>
                </s:iterator>
              </table>
			
			</div>
	</div>
  		</div>
		</s:form> 
		</div>
</div>
	</body>
</html>