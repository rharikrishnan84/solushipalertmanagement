	

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

					<div class="fields">
											<label> <mmr:message messageId="label.address.button.save"/> </label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.saveFromAddress}"  name="shippingOrder.saveFromAddress"/>
											</div>
					</div>
					
					<div class="fields">
						&nbsp;
					</div>
									
								
						<div id="fromAdd_inner">
							<s:include value="fromAddress_inner.jsp"/>
						</div>
		
			
			
			