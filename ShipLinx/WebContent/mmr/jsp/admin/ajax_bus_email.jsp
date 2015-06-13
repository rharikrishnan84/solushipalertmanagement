
<%
String staus=(String)session.getAttribute("redudant");
if(staus!=null && staus.equals("false")){
%>
<s:set var="index" value="0" />
										<s:iterator value="businessEmailList" status="status">
	 
	<tr id="be<s:property value="%{businessEmailId}"/>">
				<td class="odd1" width="2%"><input class="dataTable-checkbox"
					type="checkbox" name="addBusEmailChk"
					value="<s:property value='%{businessEmailId}'/>" /> <s:hidden
						value="%{businessEmailId}" id="index_%{businessEmailId}" /></td>
				
				<td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{emailType}"/>
				<input type="hidden" value="<s:property value='%{businessEmailId}'/>" name="businessEmailIds" id="<s:property value='%{businessEmailId}'/>e"/>
				</label>
				<%-- <input type="hidden" value="<s:property value='%{parentBus.id}'/>" name="parentBusIds" id=""/> --%>
				</td>
				
				 	<td title="Click to View...">
				 	<div id="<s:property value="businessEmailId"/>d" style="width:100px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis" onmouseover="document.getElementById('<s:property value="businessEmailId"/>m').style.display = 'block';"><span  onclick="showhtml('<s:property value="businessEmailId"/>a')" ><s:property value="htmlContent"/>....</span></div>
                 <input type="hidden"  id="<s:property value="businessEmailId"/>a"  value="<s:property value='%{htmlContent}'/>" name="htmlContents" id="htmlContents1"/>
				 	</td>
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{locale}"/>
				<input type="hidden" value="<s:property value='%{locale}'/>" name="locales"  id="<s:property value='%{businessEmailId}'/>l"/> 
				
				</label>
			<%-- 	<input type="hidden" value="<s:property value='%{nationBus.id}'/>" name="nationBusIds" id=""/> --%>
				</td>
			 
				 
			</tr>
			 
		</s:iterator><%}else{%>@r<%}%>