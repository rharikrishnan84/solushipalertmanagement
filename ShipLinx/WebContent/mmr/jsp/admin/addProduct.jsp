<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<head>
<sx:head/>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>

<script language="JavaScript">
	var plkey=0;
	var custkey=0; 
	var primarylockey = 0;
	var reqprimarylocId = 0;
	var reqplkey = 0;
	var reqcustkey = 0;
	
	window.onload=init;
	
	function init() 
	{
		reqprimarylocId = "<%=request.getAttribute("primarylocId") %>";
		reqplkey = "<%=request.getAttribute("plnameid") %>";
		reqcustkey = "<%=request.getAttribute("cnameid") %>";
	}	
	
	function submitform()
	{
		var bsubmit=true;
		var autoCompleter = dojo.widget.byId("customerName");
		//var autoCompleter2 = dojo.widget.byId("prodlineSelected");
		//var primaryloc_ac = dojo.widget.byId("primarylocationNames");
		
			if(document.getElementById("pname").value=="")
			{
				alert("Please enter Product Name");
				bsubmit=false;
			}
			else if(document.getElementById("pdesc").value=="")
			{
				alert("Please enter Product Description");
				bsubmit=false;
			}
			else if(document.getElementById("phcode").value=="")
			{
				alert("Please enter Product Harmonized Code");
				bsubmit=false;
			}
			else if(document.getElementById("pcode").value=="")
			{
				alert("Please enter Product Code");
				bsubmit=false;
			}
			//else if(autoCompleter2!=undefined)
			//{
				//var value2 = autoCompleter2.getSelectedValue();
				//if (value2 == null || value2 == "") 
				//{
					//alert("Please select a Product Line");	
					//bsubmit=false;		
				//}
			//}
			//else if(primaryloc_ac!=undefined)	//BusinessAdmin Role
			//{
				//var primaryvalue = primaryloc_ac.getSelectedValue();
			
				//if (primaryvalue == null || primaryvalue == "") 
				//{
					//alert("Please select a Primary Location");	
					//bsubmit=false;		
				//}
			//}
		if(bsubmit)
		{	
		//busadmin role
			//if(reqprimarylocId=='null' && reqplkey=='null' && reqcustkey=='null' && primarylockey>0 && plkey>0 && custkey>0)
			//{
	 			document.searchform.action = "updateProduct.action?cid="+custkey+"&plid="+plkey+"&primaryloc="+primarylockey;
	 			document.searchform.submit();
	 		//}
	 		//else if(reqprimarylocId!='null' && reqplkey!='null' && reqcustkey!='null' && primarylockey==0 && plkey==0 && custkey==0)
	 		//{
	 			//alert("case 2");
	 			//document.searchform.action = "updateProduct.action?cid="+reqcustkey+"&plid="+reqplkey+"&primaryloc="+reqprimarylocId;
	 			//document.searchform.submit();
	 		//}
	 		//else if(reqprimarylocId!='null' && reqplkey!='null' && reqcustkey!='null' && primarylockey>0 && plkey==0 && custkey==0)
	 		//{
	 			//alert("case 3");
	 			//document.searchform.action = "updateProduct.action?cid="+reqcustkey+"&plid="+reqplkey+"&primaryloc="+primarylockey;
	 			//document.searchform.submit();
	 		//}
	 		//else if(reqprimarylocId!='null' && reqplkey!='null' && reqcustkey!='null' && primarylockey==0 && plkey>0 && custkey==0)
	 		//{
	 			//alert("case 4");
	 			//document.searchform.action = "updateProduct.action?cid="+reqcustkey+"&plid="+plkey+"&primaryloc="+reqprimarylocId;
	 			//document.searchform.submit();
	 		//}
	 		//customer admin role
	 		//else if(reqplkey=='null' && plkey>0)
			//{
				//alert("case 5");
	 			//document.searchform.action = "updateProduct.action?cid="+custkey+"&plid="+plkey+"&primaryloc="+primarylockey;
	 			//document.searchform.submit();
	 		//}
	 		//else if(reqplkey!='null' && plkey==0)
	 		//{
	 			//alert("case 6");
	 			//document.searchform.action = "updateProduct.action?cid="+reqcustkey+"&plid="+reqplkey+"&primaryloc="+reqprimarylocId;
	 			//document.searchform.submit();
	 		//}
	 	}
	}
	function cancelform(){
	
		document.searchform.action = "goToManageProducts.action";
		document.searchform.submit();
		}
		
		function cancelform1(){
	
		document.searchform.action = "search.products.action?method=new";
		document.searchform.submit();
		}
	
	function getAccountInformation(url){
		window.open(url,'','width=760,height=540,left=100,top=100,scrollbars=1');
	}
	function resetform() {
		document.searchform.action = "searchcustomer.action?method=reset";
	 	document.searchform.submit();
	}	
	
	function addNewSearch()
	{
		document.searchform.action = "admin/addcustomer.action?";
	 	document.searchform.submit();
	}
	
	function emailNotify(custid)
	{
		if(confirm("Would you like to send account notification/message to customer?")) {
			 document.searchform.action = "send.customer.notification.action?&id="+custid;
			 document.searchform.submit();
		}
	}
	
	dojo.event.topic.subscribe("/value_cust", function(value, key, text, widget){
		custkey = key;
		});
		
	dojo.event.topic.subscribe("/value_prodl", function(value, key, text, widget){
		plkey = key;			
		});
		
	dojo.event.topic.subscribe("/value_primary", function(value, key, text, widget){
		primarylockey = key;
		});
	
	
</script>
<script>
  $(document).ready(function(){
   $('.navi_icon').click(function(){
    $('.navigation ul').slideToggle();
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

<div id="messages">
	<jsp:include page="../common/action_messages.jsp"/>
</div>
<div class="form-container"> 
<s:form action="searchCustomer" name="searchform" style="margin-bottom:0px">
<div class="content_body">
							<div class="content_table" >
								<div class="content_header">
									<div class="cont_hdr_title">
									<s:if test="#session.edit != 'true'">
									<mmr:message messageId="menu.add.product"/>
									</s:if>
									<s:else>
									<mmr:message messageId="label.edit.product"/>
									</s:else>
									</div>
									<div class="form_buttons">
										  <a href="javascript:submitform()"  >SAVE</a>
										 <s:if test="%{#session.role=='busadmin'}">
										 <a href="javascript:cancelform()"  >CANCEL</a>
										  
										 </s:if>
										 <s:else>
										 <a href="javascript:cancelform1()"  >CANCEL</a>
										  
										 </s:else>
										  
										 </div>
								</div>
								
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.product.name"/> </label>
											<div class="controls"><span>:</span>
											<s:textfield id="pname"  key="searchproduct.name" name="products.productName"  />
											</div>
										</div>
								
										<div class="fields">
											<label><mmr:message messageId="label.product.description"/> </label>
											<div class="controls"><span>:</span>
											<s:textfield id="pdesc"  key="searchproduct.desc" name="products.productDescription"  />
											</div>
										</div>
								
										<div class="fields">
											<label><mmr:message messageId="label.product.price"/></label>
											<div class="controls"><span>:</span>
											<s:textfield  key="searchproduct.hcode" name="products.unitPrice"  />
											</div>
										</div>
									</div>
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.product.code"/> </label>
											<div class="controls"><span>:</span>
											<s:textfield id="pcode"  key="searchproduct.code" name="products.productCode"  />
											</div>
										</div>
								
										<div class="fields">
											<label><mmr:message messageId="label.product.hcode"/> </label>
											<div class="controls"><span>:</span>
											<s:textfield id="phcode"  key="searchproduct.hcode" name="products.productHarmonizedCode"  />
											</div>
										</div>
								<div class="fields">
											<label><mmr:message messageId="label.country.origin"/> </label>
											<div class="controls"><span>:</span>
											<s:select id="new_productOrigin"    listKey="countryCode" listValue="countryName" name="products.originCountry" list="#session.CountryList" headerKey="-1"  theme="simple"/>
											</div>
										</div>
										<%-- <div class="fields">
                     <label>SKU Id </label>
                     <div class="controls"><span>:</span>
                     <s:textfield id="skuId"  key="searchproduct.skuId" name="products.skuId"  />
                     </div>
                   </div> --%>
                   <div class="fields">
                     <label>Length </label>
                     <div class="controls"><span>:</span>
                     <s:textfield id="length"  key="searchproduct.length" name="products.length"  />
                     </div>
                   </div>
                     <div class="fields">
                     <label>Width </label>
                     <div class="controls"><span>:</span>
                     <s:textfield id="width"  key="searchproduct.width" name="products.width"  />
                     </div>
                   </div>
                     <div class="fields">
                   <label>Height </label>
                     <div class="controls"><span>:</span>
                     <s:textfield id="height"  key="searchproduct.height" name="products.height"  />
                     </div>
                   </div>
							<div class="fields">
                   <label><mmr:message messageId="label.package.unitmeasure"/></label>
                          <div class="controls"><span>:</span>
                          <s:select id="measure" name="products.unitmeasureId"  list="#session.UOM" listKey="unitOfMeasureId" listValue="unitOfMeasure" disabled="false" cssStyle="height :25px;"/>
                          </div>
                  </div>
                  
               </div>
								</div>
									
								
							</div>
						</div>
						</s:form>
</div>
</body>
</html>	


