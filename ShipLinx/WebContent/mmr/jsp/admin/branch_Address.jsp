<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<style>
                                    .autocomplete-suggestions{
	                              width:300px !important;
                                      }
                                  </style>
<div class="content">
	
	<div class="content_body">
		<div id="div_ship_from">
							<div class="content_table">
								<div class="cont_data_body borderLeftRight" >
									<div class="rows" >
									<!--	<div class="fields">
											<label><mmr:message messageId="label.shippingOrder.company"/></label>
											<div class="controls"><span>:</span>
										 <s:url id="customerList" action="listCustomersWithOrphan" />
										 <s:param name="shippingOrder.customerId" value="%{shippingOrder.customerId}"/>
               		<%-- <s:select key="shippingOrder.webCustomerId" cssClass="text_01_combo_big" cssStyle="height:20px; width: 150px;" 

					listKey="value" listValue="key" list="#session.customersList" onchange="changeCustomer(this.value);"/>	 --%>

				 	<s:hidden id="custId" />			

				<s:textfield id="customerautocompletes" value="%{shippingOrder.fromAddress.abbreviationName}" name="shippingOrder.fromAddress.abbreviationName"/>
			 <%-- <s:url id="customerList" action="listCustomers" />


                <sx:autocompleter keyName="shippingOrder.webCustomerId" name="searchString" id="customerName"

                    href="%{customerList}" dataFieldName="customerSearchResult" delay="false" preload="true"

                 cssStyle="height:20px; width: 250px;" loadOnTextChange="true" loadMinimumCount="1" autoComplete="true"  valueNotifyTopics="/value_name" onchange="changeCustomer(this.value);"/> --%>

                
 										</div>	</div> -->
										<div id="ship_from">
					
					
					
									
								
						<div id="fromAdd_inner">
							 <jsp:include page="Branch_Address_inner.jsp"/> 
						</div>
		</div>
					</div>
				</div>
			</div>
		</div>
	</div>						
</div>	

						