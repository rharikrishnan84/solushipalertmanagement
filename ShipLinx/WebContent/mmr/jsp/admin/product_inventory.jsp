<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<sx:head cache="true" />
</head>
<body>
<script type="text/javascript">

dojo.require("dojo.widget.*");

var fromlocId ="";
var towarehouseId="";
var tolocId=-1;
var towhChanged = false;

window.onload = hideImages;

	function hideImages()
	{
		document.getElementById("ajax_loader").style.display = 'none'; 
	}
	
	function showImages()
	{
		document.getElementById("ajax_loader").style.display = 'block'; 
	}
	
	function typenumbers(e,filterString)
	{
		var key, keychar;
		key = getkey(e);
		if (key == null) 
		return true;
		
		// get character
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();
		// control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==27) )
		return true;
		// alphas and numbers
		else if ((filterString.indexOf(keychar) > -1))	
		return true;
		else
		return false;
	}
	
	function getkey(e){
		if (window.event)
		  return window.event.keyCode;
		else if (e)
		  return e.which;
		else
		  return null;
	}
	
	function populateToLocations()
	{
		var warehouseid = document.getElementById("warehouses").value;
		
		ajax_locations=ajaxFunction();
		ajax_locations.onreadystatechange=function()
		  {
		   	if(ajax_locations.readyState==4)
			{
				reponse=ajax_locations.responseText;
				js_LocationsByWarehouse=document.getElementById("LocationsByWarehouse");
				js_LocationsByWarehouse.innerHTML= reponse;
			}
		}
		url="<%=request.getContextPath()%>/admin/goTolistLocationsByWarehouse.action?wid="+warehouseid;
		ajax_locations.open("GET",url,true);
		ajax_locations.send(this);
	}
	
	function addProductToInventory()
	{
		var locnames = dojo.widget.byId("locationNames");
		var batchnames = dojo.widget.byId("batchNames");
		var dojos = getElementsByClassName("dojoComboBox");
		if(document.getElementById("qnty").value == 0)
		{
			alert("Please enter the units of Products");
		}
		else if(locnames.getSelectedValue() == null || locnames.getSelectedValue() == "")
			alert("Please enter or select a Location");
		//else if(batchnames.getSelectedValue() == null || batchnames.getSelectedValue() == "")
			//alert("Please enter or select a Batch");
		else if(document.getElementById("comment_area").value == "")
			alert("Please add comments");
		else
		{
			var locKey=locnames.getSelectedKey();
			var qty = document.getElementById("qnty").value;
			var batch = dojos[2].value;
			var comment = document.getElementById("comment_area").value;
			comment = comment.replace(/(\r\n|\n|\r)/gm," ");
			var private = document.getElementById("private").checked;
			document.prodinvform.action = "addProductToInventory.action?locid="+locKey+"&quantity="+qty+"&batch="+batch+"&comment="+comment+"&private="+private;
			document.prodinvform.submit();
		}
	}
	
	dojo.event.topic.subscribe("populateBatches", function(value, key, text, widget){
		var strkey = key;
		strkey = strkey.substring(strkey.indexOf("-")+1);
		var whnames = dojo.widget.byId("warehouseNames");
		whnames.setSelectedKey(strkey);
		towhChanged = false;
		showImages();
		fromlocId = key;
		var dojos = getElementsByClassName("dojoComboBox");
		var str=text;
		var newstr="";
		if(str!='-----Select Location-----')
		{
			var n=str.indexOf(":: ");
			newstr = str.substring(n+3);
			callBatchesAjaxFunc(key);
			setTimeout("publish()",1000);
			clearTimeout();
		}
		else
			newstr="--Select Warehouse--";
		dojos[6].value=newstr;
		hideImages();
		dojos[8].value="-----Select Location-----";
		});
		
		function callBatchesAjaxFunc(key)
		{
			
			ajax_batches=ajaxFunction();
			ajax_batches.onreadystatechange=function()
		  	{
			   if(ajax_batches.readyState==4)
				{
				reponse=ajax_batches.responseText;
				js_batchByLocId=document.getElementById("batchByLocId");
				js_batchByLocId.innerHTML= reponse;
				document.getElementById("ajax_loader").style.display = 'none';
				}
		  	}
		 	url="<%=request.getContextPath()%>/admin/listBatchesByLocId.action?locId="+key;
		  	ajax_batches.open("GET",url,true);
		  	ajax_batches.send(this);
			
		}
		
		function callBatchesAjaxFunc2(key)
		{
			ajax_batches=ajaxFunction();
			ajax_batches.onreadystatechange=function()
		  	{
			   if(ajax_batches.readyState==4)
				{
				reponse=ajax_batches.responseText;
				js_batchByLocId=document.getElementById("batchByLocId2");
				js_batchByLocId.innerHTML= reponse;
				document.getElementById("ajax_loader").style.display = 'none';
				}
		  	}
		 	url="<%=request.getContextPath()%>/admin/listBatchesByLocId.action?locId="+key;
		  	ajax_batches.open("GET",url,true);
		  	ajax_batches.send(this);
			
		}
		
		
	dojo.event.topic.subscribe("loadLocations", function(value, key, text, widget){
	towhChanged = true;
	tolocId = -1;
	var dojos = getElementsByClassName("dojoComboBox");
	showImages();
	towarehouseId=key;
	publish();
	hideImages();
	dojos[8].value="-----Select Location-----";
	});
	
		
	function publish()
	{
		var dojos = getElementsByClassName("dojoComboBox");
		dojo.event.topic.publish("/loadLocations2");
		dojos[8].value="-----Select Location-----";
		document.getElementById("qtype").disabled = false;
	}
	
	function publish2()
	{
		var dojos = getElementsByClassName("dojoComboBox");
		dojo.event.topic.publish("/loadBatches2");
		dojos[10].value="--Select--";
	}
	
	
	dojo.event.topic.subscribe("toLocation", function(value, key, text, widget){
		var dojos = getElementsByClassName("dojoComboBox");
		tolocId=key;
		if(tolocId!='-1')
		{
			setTimeout("publish2()",1000);
			clearTimeout();
		}
		dojos[10].value="--Select--";
	});
	
	
	function moveProduct()
	{
		//alert("Inside MoveProduct");
		var qty2 = document.getElementById("qty2").value;
		//var fifolifo = document.getElementById("qtype").value;
		var batch = document.getElementById("batches").value;
		//var batchto = document.getElementById("batchesTo").value;
		var towhid = fromlocId.substring(fromlocId.indexOf('-')+1);
		var dojos = getElementsByClassName("dojoComboBox");
		var batchto = dojos[10].value;
		
		if(qty2.length ==0 || qty2==0)
		{
			alert("Please enter the units of Products");
		}
		else if(fromlocId=="" || fromlocId=="-1")
			alert("Please select from-Location");
		else if(tolocId=="" || tolocId=="-1")
		{
			alert("Please select to-Warehouse Location");
		}
		else if(document.getElementById("comment_area").value == "")
			alert("Please add comments");
		else
		{
			//alert("Units::"+qty2);
			//alert("from Loc ID::"+fromlocId);
			if(towhChanged==true)
				towhid = towarehouseId;
			else
				towhid = fromlocId.substring(fromlocId.indexOf('-')+1);
			//alert("To Warehouse ID::"+towhid);
			//alert("To Loc Id::"+tolocId);
			//alert("To Batch ID:"+batchto);
			//alert("FIFO or LIFO::"+fifolifo);
			fromlocId = fromlocId.substring(0,fromlocId.indexOf('-'));
			document.prodinvform.action = "moveProductInInventory.action?flocid="+fromlocId+"&twhid="+towhid+"&tlocid="+tolocId+"&qty="+qty2+"&batch="+batch+"&batchto="+batchto;
			document.prodinvform.submit();
		}
	}
	
	
</script>
<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>

<div class="form-container"> 
<s:form id="prod_inv_form" name="prodinvform">
<sx:tabbedpanel id="product_inventory_tabs">
	<sx:div id="inventory_ctrl" label="Inventory Control">
		<div id="table_prod_inv">
		<div id="prod_inv_hdr">
			<table>
				<tr><td><font color="#000066" style="font: Arial; font-variant: small-caps; font-weight: bold;"><mmr:message messageId="label.product.inventory.details"/>&nbsp;<mmr:message messageId="label.for"/>&nbsp;'<s:property value="warehouseInventory.products.productName"/>'  </font></td></tr>
			</table>
		</div>
		<div id="prod_inv_control">
		<!--  	<table>
				<tr>
					<td><mmr:message messageId="label.control.inventory"/></td>
				</tr>
			</table>-->
			<div id="inv_ctrl_img"><img src="<s:url value="/mmr/images/inventory_control.png" includeContext="true" />" border="0"></div>
			<div id="add_loc">
				<table cellpadding="1" cellspacing="1" width="760px">
					<tr>
						<td><mmr:message messageId="menu.add"/></td>
						<td><s:textfield size="10" id="qnty" key="quantity" name="quantity" cssClass="text_02_tf_small" onkeypress="return typenumbers(event,\'0123456789\')"/></td>
						<td><mmr:message messageId="label.products.to.location"/></td>
						<td>
						<s:url id="locationsList" action="listLocations" />
						<sx:autocompleter id="locationNames" name="searchString" href="%{locationsList}" dataFieldName="locationsSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false" valueNotifyTopics="/value_locnames" cssStyle="width:160px;" preload="true"/>
						</td>
						<td><mmr:message messageId="label.of.batch"/></td>
						<td>
						<s:url id="batchesList" action="listBatches" />
						<sx:autocompleter id="batchNames" name="searchString" href="%{batchesList}" dataFieldName="batchesSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false" valueNotifyTopics="/value_batchnames" cssStyle="width:120px;" preload="true"/>
						</td>
						<td><img src="<s:url value="/mmr/images/add_new_btn.png" includeContext="true" />" border="0" style="cursor: pointer;" onclick="addProductToInventory();"></td>
					</tr>
					</table>
			</div>
			<div id="move_loc">
				<table cellpadding="2" cellspacing="1" width="700px">
				<tr height="1px">
					<td colspan="10" style="color:#000066;" valign="middle">----------------------------------------------------------------------------------------------------------------------------------------------------------------------------</td>
				</tr>
					<tr>
						<td><mmr:message messageId="label.move"/></td>
						<td>&nbsp;</td>
						<td><s:textfield size="10" id="qty2" key="quantity" name="quantity" cssClass="text_02_tf_small" onkeypress="return typenumbers(event,\'0123456789\')"/></td>
						<td>&nbsp;</td>
						<td colspan="1"><mmr:message messageId="label.products.from.location"/></td>
						<td>
						<s:url id="locationsList" action="listLocationsByProduct" />
						<sx:autocompleter id="locationName" name="searchString" href="%{locationsList}" dataFieldName="locationsWarehousesSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  cssStyle="width:180px;" preload="true" valueNotifyTopics="populateBatches" keyName="fromlocation"/>
						</td>
						<td><mmr:message messageId="label.of.batch"/></td>
							<td>&nbsp;</td>
						<td id="batchByLocId">
							<s:select id="batches" name="batchNum" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches"  listKey="batchNum" listValue="batchNum" headerValue="--Select--" headerKey="-1"/>
						</td>
					</tr>
					</table>
					<table cellpadding="1" cellspacing="1" width="755px" style="margin-left: 207px;">
					<tr>
					<td>&nbsp;</td>
					<td align="left"><mmr:message messageId="label.to.warehouse"/></td>
					<td>
						<s:url id="ListAllWH" action="listAllWarehouses"/>
						<sx:autocompleter id="warehouseNames" name="searchString" href="%{ListAllWH}" dataFieldName="WHSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  cssStyle="width:140px;" preload="true" valueNotifyTopics="loadLocations" keyName="toWareHouseKey" headerKey="-1" headerValue="--Select Warehouse--"/>
						<s:hidden name="hidewid" id="hidden_wid" value=""/>
					</td>
					<td><mmr:message messageId="label.of.location"/></td>
					<td>
					<s:url id="locationsListWH" action="listLocationsByWarehouse"/>
						<sx:autocompleter id="locationWHNames" name="searchString" href="%{locationsListWH}" dataFieldName="SortedlocationWHSearchResult" cssStyle="width:150px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false" preload="true" listenTopics="/loadLocations2" valueNotifyTopics="toLocation" formId="prod_inv_form" keyName="locId"/>
					</td>
					<td id="dummy">&nbsp;</td>
					<!--  
					<td><mmr:message messageId="label.as"/></td>
					<td>&nbsp;</td>
					<td> 
					<s:select id="qtype" list="{'FIFO','LIFO'}" cssStyle="width:71px;" cssClass="text_01_combo_medium" headerKey="-1" headerValue="Select"/>
					</td>-->
					<td><mmr:message messageId="label.of.batch"/></td>
					<td>&nbsp;</td>
					<td>
					<!--  <s:select id="batchesTo" name="batchNum" cssClass="text_01_combo_medium" cssStyle="width:80px;" list="#session.LSTBatches"  listKey="batchNum" listValue="batchNum" headerValue="--Select--" headerKey="-1"/>-->
						<s:url id="batchesList" action="listBatches2" />
						<sx:autocompleter id="batchNames" name="searchString" href="%{batchesList}" dataFieldName="batchesSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false" valueNotifyTopics="/value_batchnames" cssStyle="width:80px;" preload="true" listenTopics="/loadBatches2" formId="prod_inv_form" headerKey="-1" headerValue="--Select--"/>
					</td>
					<td>&nbsp;</td>
					<td><img src="<s:url value="/mmr/images/move_new_btn.png" includeContext="true" />" border="0" style="cursor: pointer;" onclick="moveProduct();"></td>
					</tr>
				</table>
				<div id="ajax_loader"><img src="<s:url value="/mmr/images/ajax-loader.gif" includeContext="true" />" border="0" style="display: none;"></div>
			</div>
		</div>
		<div id="add_comment_prod_inv">
			<table>
			<tr>
			<td><div id="add_info_shipping_srch_crtra"><mmr:message messageId="label.shippingOrder.add.comment"/></div></td>
			<td>&nbsp;</td>
			</tr>
			</table>
	
			<div id="add_info_shipping_table">
			<table>
			<tr>
			<td class="hdr" colspan="3" valign="top" align="right"><mmr:message messageId="label.feedback.comment"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="hdr"><s:textarea cols="50" rows="2" id="comment_area" name="loggedEvent.message"></s:textarea> </td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="hdr" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td class="hdr" valign="top"><s:checkbox name="loggedEvent.privateMessage" key="loggedEvent.privateMessage" id="private"/>&nbsp;<mmr:message messageId="label.mark.private"/></td>
			</tr>
			</table>
			</div>
		</div>
		<div id="prod_inv_tab">&nbsp;</div>
		<div id="add_info_shipping_res"><mmr:message messageId="label.search.results"/></div>
		<div id="add_info_shipping_results">	
		<s:if test="%{warehouseInventoryList.size()>1}">
		<div id="add_info_shipping_rslt_stmnt"><br/><s:property value="warehouseInventoryList.size()" /><mmr:message messageId="label.search.results.items"/>
		</div>
		</s:if>
		<s:elseif test="%{warehouseInventoryList.size()==1}">
		<div id="add_info_shipping_rslt_stmnt"><br/><s:property value="warehouseInventoryList.size()" /><mmr:message messageId="label.search.results.item"/>
		</div>
		</s:elseif>
		<s:else>
		<div id="add_info_shipping_rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/>
		</div>
		</s:else>
		</div>
		
		
	<div id="add_info_shipping_result_tbl">		
	<s:set var="wc">
    	<mmr:message messageId="label.warehouse.code"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="locname">
    	<mmr:message messageId="label.location.name"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="loctypenm">
    	<mmr:message messageId="label.locationtype.name"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="batchno">
    	<mmr:message messageId="label.batch.num"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="dtcreated">
    	<mmr:message messageId="label.customer.dateCreated"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
	<s:set var="qty">
    	<mmr:message messageId="label.shippingOrder.additionalServices.quantity"/>&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/mmr/images/sort_icon.png" border="0"/>
	</s:set>
			<display:table id="comment" uid="row" name="warehouseInventoryList" pagesize="100" cellspacing="0" cellpadding="0" class="srch_tbl_ais" requestURI="/admin/goToProductInventory.action">
				<display:column headerClass="tableTitle2_product" property="warehouseLocation.warehouse.warehouseCode" title="${wc}" sortable="true" style="text-align:center;"/>
				<display:column headerClass="tableTitle2_product" property="warehouseLocation.locationName" title="${locname}" sortable="true" style="text-align:center;"/>
				<display:column headerClass="tableTitle2_product" property="warehouseLocation.warehouseLocationType.warehouseLocationTypeName" sortable="true" title="${loctypenm}" style="text-align:center;"/>
				<display:column headerClass="tableTitle2_product" property="batchNum" title="${batchno}" sortable="true" style="text-align:center;"/>
				<display:column headerClass="tableTitle2_product" property="dateCreated" title="${dtcreated}" sortable="true" style="text-align:center;"/>
				<display:column headerClass="tableTitle2_product" property="quantity" title="${qty}" sortable="true" style="text-align:center;"/>
			</display:table>
	</div>
	<div id="add_info_shipping_res_tbl_end"></div>
		
		
		
	</div>
	</sx:div>
	<sx:div id="summary_prod_inv" label="Summary" cssStyle="background-color: #ffffff;">
		<div id="prod_inv_hdr_summary">
			<table>
				<tr><td><font color="#000066" style="font: Arial; font-variant: small-caps; font-weight: bold;"><mmr:message messageId="label.product.summary"/>&nbsp;-&nbsp;'<s:property value="warehouseInventory.products.productName"/>'&nbsp;
				<s:if test="%{warehouseInventorySummaryList.size() > 0}">
					<mmr:message messageId="label.for"/>&nbsp;<s:property value="%{warehouseInventorySummaryList.size()}"/>&nbsp;<mmr:message messageId="label.warehouses"/> </s:if> </font></td></tr>
				
			</table>
			<div id="prod_inv_summary_tab">&nbsp;</div>
				<div id="prod_inv_summary_result_tbl">		
					<display:table id="comment" uid="row" name="warehouseInventorySummaryList" varTotals="totals" sort="list" export="false" cellspacing="0" cellpadding="2">
						<display:column headerClass="tableTitle2_wh_summary_hdr" property="warehouseName" title="Warehouse" style="text-align:center;"/>
						<display:column headerClass="tableTitle2_wh_summary_hdr" property="inventoryItemCount" title="Inventory" maxLength="50" style="text-align:center;" total="true"/>
						<display:column headerClass="tableTitle2_wh_summary_hdr" property="wipItemCount" title="WIP" maxLength="10" style="text-align:center;" total="true"/>
						<display:column headerClass="tableTitle2_wh_summary_hdr" property="quarantineItemCount" title="Quarantine" maxLength="75" style="text-align:center;" total="true"/>
						<display:column headerClass="tableTitle2_totals" property="warehouseSubTotal" title="TOTAL" maxLength="75" style="text-align:center; font-weight: bold; border-left: 1px solid #000000;" total="true"/>
						<display:footer>
					   		 <tr class="tableTitle2_totals">
					   		 	<td align="center" style="border-top: 1px solid #000000;">TOTALS:</td>
					   	   		<td align="center" style="border-top: 1px solid #000000;">
					   	   			<s:property value="%{#attr.totals.column2}" />
					   	   		</td>
					   	   		<td align="center" style="border-top: 1px solid #000000;">
					   	   			<s:property value="%{#attr.totals.column3}" />
					   	   		</td>
					   	   		<td align="center" style="border-top: 1px solid #000000;">
					   	   			<s:property value="%{#attr.totals.column4}" />
					   	   		</td>
					   	   		<td align="center" style="border-top: 1px solid #000000; border-left: 1px solid #000000;">
					   	   			<s:property value="%{#attr.totals.column5}" />
					   	   		</td>
					   		 </tr>
  						</display:footer>
					</display:table>
					<br/>
			</div>
			<div id="add_info_shipping_res_tbl_end"></div>	
		</div>
	</sx:div>
	<sx:div id="log_history" label="Event Logging">
		<div id="prod_inv_hdr_log">
			<table>
				<tr><td><font color="#000066" style="font: Arial; font-variant: small-caps; font-weight: bold;"><mmr:message messageId="label.product.inventory.details"/>&nbsp;<mmr:message messageId="label.for"/>&nbsp;'<s:property value="warehouseInventory.products.productName"/>'  </font></td></tr>
			</table>
		</div>
			<div id="comment_table">
				<div id="add_info_shipping_tab"><br/></div>
				<div id="add_info_shipping_res"><mmr:message messageId="label.search.results"/></div>
				<div id="add_info_shipping_results">	
				<s:if test="%{loggedList.size()>1}">
				<div id="add_info_shipping_rslt_stmnt"><br/><s:property value="loggedList.size()" /><mmr:message messageId="label.search.results.items"/>
				</div>
				</s:if>
				<s:elseif test="%{loggedList.size()==1}">
				<div id="add_info_shipping_rslt_stmnt"><br/><s:property value="loggedList.size()" /><mmr:message messageId="label.search.results.item"/>
				</div>
				</s:elseif>
				<s:else>
				<div id="add_info_shipping_rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/>
				</div>
				</s:else>
				</div>
			
				<div id="add_info_shipping_result_tbl">		
					<display:table id="comment" uid="row" name="loggedList" pagesize="100" export="true" cellspacing="0" cellpadding="0" class="srch_tbl_ais">
						<display:column headerClass="tableTitle2_product_upby" property="eventDateTime" title="Date Time" style="text-align:center;"/>
						<display:column headerClass="tableTitle2_product_upby" property="message" title="Comments" maxLength="50" style="text-align:center;"/>
						<display:column headerClass="tableTitle2_product_upby" property="eventUsername" title="Updated By" maxLength="10" style="text-align:center;"/>
						<display:column headerClass="tableTitle2_product_upby" property="systemLog" title="Event Log" maxLength="75" style="text-align:center;"/>
						<s:if test="%{#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">
						<display:column headerClass="tableTitle2_product_bit" title="Private" style="text-align:center;">
						<s:if test="%{#attr.row.privateMessage != true}">
							<img src="<s:url value="/mmr/images/cross.png" includeContext="true" />" alt="Cross" border="0">
						</s:if>
						<s:else>
							<img src="<s:url value="/mmr/images/tick.png" includeContext="true" />" alt="Tick" border="0">
						</s:else>
						</display:column>
						</s:if>
					</display:table>
			</div>
			<div id="add_info_shipping_res_tbl_end"></div>
		</div>
	</sx:div>
</sx:tabbedpanel>

</s:form>	
</div>
</body>
</html>
					