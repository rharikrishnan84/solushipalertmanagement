<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


	<div class="content_body" >	
							<div class="content_table" > 
										
								<div class="cont_data_body borderLeftRight">
									<div class="rows">
									
									
									
									
			<div class="rows" style="height:15px !important;">	
			  <div class="sl_no" style=" width:30px;">&nbsp;</div>
			  <div class="largeFields">
			   <div class="controls">
				<mmr:message messageId="label.product.description"/>
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
				<mmr:message messageId="label.harmonized.code"/>
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
				<mmr:message messageId="label.country.origin"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
			   <mmr:message messageId="label.unit.price"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
			   <mmr:message messageId="label.quantity.integer"/>
			   </div>
			  </div>
			  <div class="smallFields">         
			   <div class="controls">
				<mmr:message messageId="label.total.price"/>
			   </div>
			  </div>
			  <div class="fields_topdown_s">         
			   <div class="controls">
			  
			   </div>
			  </div>
			</div>	
			
			<div class="rows">	
			  <div class="sl_no" style=" width:30px;">&nbsp;</div>
			  <div class="largeFields">
			   <div class="controls" id="prod_desc">
					<s:url id="productdescList" action="listProducts" >
					<s:param name="field">desc</s:param>					
					</s:url>
					<s:hidden name="custid" value="%{shippingOrder.customerId}" id="hidden_cid"/>
					<sx:autocompleter id="autoproductdesc"  keyName="products.product_id" name="searchString" href="%{productdescList}" dataFieldName="productSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_desc"  preload="true"/>
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
					<s:url id="productdescList" action="listProducts" >
					<s:param name="field">hcode</s:param>					
					</s:url>
					<!--	<sx:autocompleter id="autoproducthcode" keyName="products.product_id" name="searchString" href="%{productdescList}" dataFieldName="productSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true" valueNotifyTopics="/value_hcode" forceValidOption="true" cssStyle="width:150px;" preload="true"/>-->
					<!-- <img id="loading-img-hcode" style="display:none;" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0">-->
					<s:textfield id="new_prod_hcode" name="new_prod_hcode"  theme="simple" cssStyle="text-align:right;padding-right:5px" />
			   </div>
			  </div>
			  <div class="largeFields">
			   <div class="controls">
					<s:select id="new_productOrigin"  listKey="countryCode" listValue="countryName" name="products.originCountry" list="#session.CountryList" headerKey="-1"  theme="simple"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
					<s:textfield id="new_prod_uprice"  name="products.unitPrice"  theme="simple"  onkeypress="return typenumbers(event,\'0123456789.\')" cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="smallFields">
			   <div class="controls">
					<s:textfield id="new_prod_quantity" name="new_prod_quantity"  theme="simple"   onkeypress="return typenumbers(event,\'0123456789\')" cssStyle="text-align:right;padding-right:5px"/>
			   </div>
			  </div>
			  <div class="smallFields">         
			   <div class="controls">
				<s:textfield id="new_prod_tprice" cssStyle="height:22px;text-align:right;padding-right:5px" name="new_prod_tprice"  theme="simple" readonly="true"/>
			   </div>
			  </div>
			  <div class="fields_topdown_s">         
			   <div class="controls">
			  
			   </div>
			  </div>
			</div>	
			  	
									</div>	
								</div>
							</div>
						</div>