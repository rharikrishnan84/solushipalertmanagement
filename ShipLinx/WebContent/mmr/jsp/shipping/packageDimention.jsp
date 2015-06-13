<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<SCRIPT language="JavaScript">
F
	function sameAsAbove2(pos) {
		pos = pos-1;
			
			var packageTypesName = 'packageTypes'+pos;
			var lengthName = 'shippingOrder.packageArray[' + pos + '].length';
			var widthName = 'shippingOrder.packageArray[' + pos + '].width';
			var heightName = 'shippingOrder.packageArray[' + pos + '].height';
			var weightName = 'shippingOrder.packageArray[' + pos + '].weight';
			var descrName = 'shippingOrder.packageArray[' + pos + '].description';
			var codName = 'shippingOrder.packageArray[' + pos + '].codAmount';
			var insName = 'shippingOrder.packageArray[' + pos + '].insuranceAmount';
			
			//alert(document.getElementById(packageTypesName));
			if(document.getElementById(packageTypesName)!=null)
			{
				var packageTypeElement = document.getElementById(packageTypesName);
				var packageType = packageTypeElement.options[packageTypeElement.selectedIndex].value;
			}
			var length = document.getElementById(lengthName).value;
			var width = document.getElementById(widthName).value;
			var height = document.getElementById(heightName).value;
			var weight = document.getElementById(weightName).value;
			var descr = document.getElementById(descrName).value;
			var cod = document.getElementById(codName).value;
			var ins = document.getElementById(insName).value;
	  				
			var x = pos+1;
			if(document.getElementById(packageTypesName)!=null)
				document.getElementById('packageTypes'+x).value = packageType;
			document.getElementById('shippingOrder.packageArray[' + x + '].length').value = length;
			document.getElementById('shippingOrder.packageArray[' + x + '].width').value = width;
			document.getElementById('shippingOrder.packageArray[' + x + '].height').value = height;
			document.getElementById('shippingOrder.packageArray[' + x + '].weight').value = weight;
			document.getElementById('shippingOrder.packageArray[' + x + '].description').value = descr;
			document.getElementById('shippingOrder.packageArray[' + x + '].codAmount').value = cod;
			document.getElementById('shippingOrder.packageArray[' + x + '].insuranceAmount').value = ins;
  		}

	function allTheSame2(rows) {
			var type = document.getElementById("pallet").value;	
			var packageTypesName = 'packageTypes'+0;
			var lengthName = 'shippingOrder.packageArray[' + 0 + '].length';
			var widthName = 'shippingOrder.packageArray[' + 0 + '].width';
			var heightName = 'shippingOrder.packageArray[' + 0 + '].height';
			var weightName = 'shippingOrder.packageArray[' + 0 + '].weight';
			var descrName = 'shippingOrder.packageArray[' + 0 + '].description';
			var codName = 'shippingOrder.packageArray[' + 0 + '].codAmount';
			var insName = 'shippingOrder.packageArray[' + 0 + '].insuranceAmount';
			if(type == 'type_pallet'){
				var friClass='shippingOrder_packages_'+ 0 +'__freightClass';
			}
			//alert(document.getElementById(packageTypesName));
			if(document.getElementById(packageTypesName)!=null)
			{
				var packageTypeElement = document.getElementById(packageTypesName);
				var packageType = packageTypeElement.options[packageTypeElement.selectedIndex].value;
			}
			var length = document.getElementById(lengthName).value;
			var width = document.getElementById(widthName).value;
			var height = document.getElementById(heightName).value;
			var weight = document.getElementById(weightName).value;
			var descr = document.getElementById(descrName).value;
			var cod = document.getElementById(codName).value;
			var ins = document.getElementById(insName).value;
			if(type == 'type_pallet'){
				var fri= document.getElementById(friClass).value;
				}
	  		
	  		for(var x=1;x<rows;x++) {
	  			if(document.getElementById(packageTypesName)!=null)
	  				document.getElementById('packageTypes'+x).value = packageType;
	  			document.getElementById('shippingOrder.packageArray[' + x + '].length').value = length;
  				document.getElementById('shippingOrder.packageArray[' + x + '].width').value = width;
  				document.getElementById('shippingOrder.packageArray[' + x + '].height').value = height;
  				document.getElementById('shippingOrder.packageArray[' + x + '].weight').value = weight;
  				document.getElementById('shippingOrder.packageArray[' + x + '].description').value = descr;
				document.getElementById('shippingOrder.packageArray[' + x + '].codAmount').value = cod;
				document.getElementById('shippingOrder.packageArray[' + x + '].insuranceAmount').value = ins;
				if(type == 'type_pallet'){
					document.getElementById('shippingOrder_packages_'+ x +'__freightClass').value=fri;
				}
				
	  		}
	  	}
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
	 //alert("AHA");	
	 document.orderForm.action = "shipment.stageThree.action?method="+method;
	 document.orderForm.submit();
	}  
	
	function populateDimensions(index, val){
		var lengthid = "shippingOrder.packageArray["+index+"].length";
		var widthid = "shippingOrder.packageArray["+index+"].width";
		var heightid = "shippingOrder.packageArray["+index+"].height";
		var weightid = "shippingOrder.packageArray["+index+"].weight";
		var descid = "shippingOrder.packageArray["+index+"].description";
		
		//alert('show::'+index+"| value::"+val);
		
		ajax_Country_desc=ajaxFunction();	 
		ajax_Country_desc.onreadystatechange=function()
	  	{
		   if(ajax_Country_desc.readyState==4 && ajax_Country_desc.status==200)
			{
						
			response_desc=ajax_Country_desc.responseText;
			//alert("Response: \n"+response_desc);
			js_stateid=document.getElementById("hide_this");
			//alert(js_stateid);
			js_stateid.innerHTML= response_desc;
			
			var sess_length= document.getElementById("pt_length").value;
			//alert("sess_length::"+sess_length);
			document.getElementById(lengthid).value=sess_length;
			
			var sess_width= document.getElementById("pt_width").value;
			//alert("sess_width::"+sess_width);
			document.getElementById(widthid).value=sess_width;
						
			var sess_height= document.getElementById("pt_height").value;
			//alert("sess_height::"+sess_height);
			document.getElementById(heightid).value=sess_height;
			
			var sess_weight= document.getElementById("pt_weight").value;
			//alert("sess_weight::"+sess_weight);
			document.getElementById(weightid).value=sess_weight;
			
			var sess_desc= document.getElementById("pt_desc").value;
			//alert("sess_desc::"+sess_desc);
			document.getElementById(descid).value=sess_desc;
			}			
			
	  }	
	  	if(val > 0)
	  	{
			url="populatePackageTypes.action?index="+val;
			ajax_Country_desc.open("GET",url,true);
			ajax_Country_desc.send(this);
		}
		else //reset the values
		{
			document.getElementById(lengthid).value="1";
			document.getElementById(widthid).value="1";
			document.getElementById(heightid).value="1";
			document.getElementById(weightid).value="1.0";
			document.getElementById(descid).value="";
		}
		
			   
		}
</script>

<div class="content">
<div id="dimensions">
<div id="pckg_result_tbl">
	<jsp:include page="hidden_packageTypes.jsp"/>		 


                 <div class="content_body">	
							<div class="content_table">
								<div class="cont_data_body borderLeftRight">
									<div class="logo_mini"><div id="pckg_results" style="">
										<span class="package3"><mmr:message messageId="label.package.packagedetails"/>:</span>	
									</div>
									
									<div class="domention">
										<div class="fields" id="qty_pckg">
											<label><mmr:message messageId="label.package.numofpackages"/></label> 
											<div class="controls"><span>:</span>
												<s:select cssClass="text_01_combo_big" name="shippingOrder.quantity" id="quantity" onchange="modifyQuantity()" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35}"  cssClass="text_01_combo_big"></s:select>
											</div>
										</div>
									</div>
									</div>
								</div>
							</div>
				</div>
									 <!--
									</div>
									<div class="rows">
										<div class="sl_no">#</div>
										<s:if test="%{#session.PackageTypes.size > 0}">
										<div class="fields_topdown" >
											<label>Choose a package type </label>
										</div>
											</s:if>
										<div class="fields_topdown_s">
											<label>Length </label>	
										</div>
										<div class="fields_topdown_s">
											<label>Width </label>	
										</div>
										<div class="fields_topdown_s">
											<label>Height </label>
										</div>
										<div class="fields_topdown_s">
											<label>Weight </label>
										</div>
										<div class="fields_topdown_s">
											<label>COD ($) </label>	
										</div>
										<div class="fields_topdown_s">
											<label>Insurance ($) </label>
										</div>
										<s:if test='%{shippingOrder.packageTypeId.type == "type_pallet"}'>
										<div class="fields_topdown_s">
											<label>Freight Class</label>
										</div>
										<div class="fields_topdown_s">
											<label>Type</label>
										</div>
										</s:if>    
										<div class="fields_topdown">Description</div>
										<div class="fields_topdown_s"></div>
										
									</div>	
										
				<s:set name="packageType" value="%{shippingOrder.packageTypeId.type}"/>
				<s:iterator  value="shippingOrder.packages"  status="counterIndex">
 				<div class="rows" style="background-color:#FFF;">
					<div class="sl_no"><s:property value="%{#counterIndex.index+1}"/></div>
					<s:if test="%{#session.PackageTypes.size > 0}">
						<div class="fields_topdown" >
							<div class="controls">
						<s:select id="packageTypes%{#counterIndex.index}" name="shippingOrder.packages[%{#counterIndex.index}].type" cssClass="text_01_combo_big" cssStyle="width:124px;" listKey="packageTypeId" listValue="packageName" list="#session.PackageTypes" headerKey="-1" headerValue=" " onchange="populateDimensions(%{#counterIndex.index}, this.value);"/>
							</div>
						</div>
					</s:if>
	                <div class="fields_topdown_s">
						<div class="controls">
					<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].length" name="shippingOrder.packages[%{#counterIndex.index}].length"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].length}" />
						</div>
					</div>
	                <div class="fields_topdown_s">
						<div class="controls">
						<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].width" name="shippingOrder.packages[%{#counterIndex.index}].width"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].width}" />
						</div>
					</div>		
					<div class="fields_topdown_s">
						<div class="controls">
						<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].height" name="shippingOrder.packages[%{#counterIndex.index}].height"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].height}"/>
						</div>
					</div>	
	                <div class="fields_topdown_s">
						<div class="controls"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].weight" name="shippingOrder.packages[%{#counterIndex.index}].weight"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].weight}"/>	</div>
					</div>
	                <div class="fields_topdown_s">
						<div class="controls"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].codAmount" name="shippingOrder.packages[%{#counterIndex.index}].codAmount" cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].codAmount}" />
						</div>
					</div>		
	                <div class="fields_topdown_s">
						<div class="controls">
						<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].insuranceAmount" name="shippingOrder.packages[%{#counterIndex.index}].insuranceAmount"  cssClass="text_02_tf_small" theme="simple" size="3" value="%{#session.shippingOrder.packages[#counterIndex.index].insuranceAmount}" />
						</div>
					</div>		
					<!-- Package Type Pallet = 4 -->
					<!--<s:hidden name="pallet" id="pallet" value="%{#packageType}" />
					<s:if test='%{#packageType == "type_pallet"}'>
                    	<div class="fields_topdown_s">
						<div class="controls">
                    	<s:select cssClass="text_01_combo_big" id="shippingOrder_packages_%{#counterIndex.index}__freightClass" name="shippingOrder.packages[%{#counterIndex.index}].freightClass" list="{'',50,55,60,65,70,77.5,85,92.5,100,110,125,150,175,200,225,250,300,400,500}" value="%{#session.shippingOrder.packages[#counterIndex.index].freightClass}" cssStyle="width: 45px;"/>
                    		
      					</div>
					</div>
                    	
                    	<div class="fields_topdown_s">
						<div class="controls">
                    	<s:select cssClass="text_01_combo_big" name="shippingOrder.packages[%{#counterIndex.index}].type" list="{'Pallet','Carton','Crate','Drum','Boxes','Rolls','Pipes/TubesBales','Bags','Cylinder','Pails,Reels', 'Other'}" value="%{#session.shippingOrder.packages[#counterIndex.index].type}" cssStyle="width: 60px;"/>
                    	</div>
					</div>
					</s:if>      
					<div class="fields_topdown">
						<div class="controls"><s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].description" name="shippingOrder.packages[%{#counterIndex.index}].description" value="%{#session.shippingOrder.packages[#counterIndex.index].description}" cssClass="text_02_tf" theme="simple" size="20"/></div>
					</div>
                 	<div class="fields_topdown_a" >
					<s:if test="%{#counterIndex.index == 0}">
						<a href="javascript:void(0)" onClick="allTheSame2('35')"  /> All same  </a>
					</s:if>
					
					<s:else>
					
					<a href="javascript:void(0)" onClick="sameAsAbove2('<s:property value="%{#counterIndex.index}"/>')" />As above
					<s:param name="view" value="<s:property value='%{#counterIndex.index}'/>"  />
					</a> 
					</s:else>
					</div>
                </div>
                </s:iterator>
             			
	
	</div>
	</div>
	</div>
	-->
	<div class="content_body">	
		<div class="content_table">
			<div class="cont_data_body borderLeftRight">
				<table style="width:100%; font-size:10px; text-align:left;" id="dimention_grid">
					<thead>
						<tr>
							<th>#</th>
							<s:if test="%{#session.PackageTypes.size > 0}">
							<th style="width:150px !important;"><mmr:message messageId="label.ghead.choosepackage"/></th>
							</s:if>
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
						<s:set name="packageType" value="%{shippingOrder.packageTypeId.type}"/>
						<s:iterator  value="shippingOrder.packages"  status="counterIndex">
							<tr>
								<td><s:property value="%{#counterIndex.index+1}"/></td>
								<s:if test="%{#session.PackageTypes.size > 0}">
								<td>
									
										<s:select id="packageTypes%{#counterIndex.index}" name="shippingOrder.packages[%{#counterIndex.index}].type" cssClass="text_01_combo_big" cssStyle="width:94%;" listKey="packageTypeId" listValue="packageName" list="#session.PackageTypes" headerKey="-1" headerValue=" " onchange="populateDimensions(%{#counterIndex.index}, this.value);"/>
									
								</td>
								</s:if>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].length" name="shippingOrder.packages[%{#counterIndex.index}].length"  cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].length}" />
								</td>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].width" name="shippingOrder.packages[%{#counterIndex.index}].width"  cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].width}" />
								</td>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].height" name="shippingOrder.packages[%{#counterIndex.index}].height"  cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].height}"/>
								</td>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].weight" name="shippingOrder.packages[%{#counterIndex.index}].weight"  cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].weight}"/>
								</td>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].codAmount" name="shippingOrder.packages[%{#counterIndex.index}].codAmount" cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].codAmount}" />
								</td>
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].insuranceAmount" name="shippingOrder.packages[%{#counterIndex.index}].insuranceAmount"  cssClass="text_02_tf_small" theme="simple" size="3" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].insuranceAmount}" />
								</td>
								
									<s:hidden name="pallet" id="pallet" value="%{#packageType}" />
									<s:if test='%{#packageType == "type_pallet"}'>
								<td>	
									<s:select cssClass="text_01_combo_big" id="shippingOrder_packages_%{#counterIndex.index}__freightClass" name="shippingOrder.packages[%{#counterIndex.index}].freightClass" list="{'',50,55,60,65,70,77.5,85,92.5,100,110,125,150,175,200,225,250,300,400,500}" value="%{#session.shippingOrder.packages[#counterIndex.index].freightClass}" cssStyle="width: 90%;"/>
								</td>
								<td>								
									<s:select cssClass="text_01_combo_big" name="shippingOrder.packages[%{#counterIndex.index}].type" list="{'Pallet','Carton','Crate','Drum','Boxes','Rolls','Pipes/TubesBales','Bags','Cylinder','Pails,Reels', 'Other'}" value="%{#session.shippingOrder.packages[#counterIndex.index].type}" cssStyle="width: 90%;"/>
								</td>
									</s:if>  
								
								<td>
									<s:textfield id="shippingOrder.packageArray[%{#counterIndex.index}].description" name="shippingOrder.packages[%{#counterIndex.index}].description" cssStyle="width: 90%;"value="%{#session.shippingOrder.packages[#counterIndex.index].description}" cssClass="text_02_tf" theme="simple" size="20"/>
								</td>	
								<s:if test="%{#counterIndex.index == 0}">
								<td>
									<a href="javascript:void(0)" class="package2" onClick="allTheSame2('35')"  /> <mmr:message messageId="label.package.allsame"/>  </a>
								</td>	
								</s:if>								
								<s:else>
								<td>
								<a href="javascript:void(0)" class="package1" onClick="sameAsAbove2('<s:property value="%{#counterIndex.index}"/>')"  onClick="allTheSame2('35')" /><mmr:message messageId="label.package.allabove"/>
								<s:param name="view" value="<s:property value='%{#counterIndex.index}'/>"  />
								</a> 
								</td>
								</s:else>	
					
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
	    </div>
	</div>	
	<div id="pckg_res_tbl_end"></div>
	</div>
</div>	