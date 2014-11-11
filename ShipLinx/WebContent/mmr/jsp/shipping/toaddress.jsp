<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="fields">
											<label><mmr:message messageId="label.address.button.save"/></label>
											<div class="controls"><span>:</span>
												<s:checkbox cssClass="text_01" value="%{shippingOrder.saveToAddress}"  name="shippingOrder.saveToAddress"/>
											</div>
					</div>
						<div class="fields">
						&nbsp;
						</div>						
				
					<div id="toAdd_inner">
						<jsp:include page="toAddress_inner.jsp"/>
					</div>