

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<div class="fields" >
											<label><mmr:message messageId="label.markup.service"/></label>
											<div class="controls" id="stateid"><span>:</span>
												<s:select   listKey="id" listValue="name"
												name="pickup.serviceId" list="#session.SERVICES" 
													headerKey="-1" id="secondBox" theme="simple"/>		
											</div>
										</div>
					<s:if test="%{!#session.ROLE.contains('busadmin') ||  #session.ROLE.contains('sysadmin')}">					
					<div id="pickup_inner">
						<jsp:include page="pickupAddress_inner.jsp"/>
					</div>
					</s:if>

