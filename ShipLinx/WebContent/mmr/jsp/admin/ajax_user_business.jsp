 
		<s:set var="index" value="count" />
		<s:iterator value="userBusiness.businessFilterList" status="status">
	 
	<tr id="ub<s:property value="%{id}"/>">
				<td class="odd1" width="2%"><input class="dataTable-checkbox"
					type="checkbox" name="salesUseCheckBox"
					value="<s:property value='%{id}'/>" /> <s:hidden
						value="%{id}" id="index_%{custsalesid}" /></td>
				
				<td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{parentBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{parentBus.id}'/>" name="parentBusIds" id=""/>
				</td>
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{partnerBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{partnerBus.id}'/>" name="partnerBusIds" id=""/>
				</td>
				 
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{nationBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{nationBus.id}'/>" name="nationBusIds" id=""/>
				</td>
				 
				 <td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<s:property value="%{branchBus.name}"/>
				</label>
				<input type="hidden" value="<s:property value='%{branchBus.id}'/>" name="branchBusIds" id=""/>
				</td>
				
				<td class="tablerow3" align="center" style="width: 250px;">
				<label>
				<a href="#" id="ahref<s:property value="%{id}"/>"  onclick="editEmailType(<s:property value="%{id}"/>)">
				
				  <s:if test="%{allEmailType}">
				   SPD,LTL,CHB,FWD,FPA
				</s:if><s:else>
				<s:if test="%{spdEnabled}">
					SPD,
					
				</s:if>
				<s:if test="%{ltlEnabled}">
				LTL,
				
				</s:if>
				<s:if test="%{chbEnabled}">
				CHB,
			
				</s:if>
				<s:if test="%{fwdEnabled}">
				FWD,
				
				</s:if>
				<s:if test="%{fpaEnabled}">
					FPA
				</s:if>
			 
				</s:else>
				
				</a>
				<input type="hidden" value="<s:property value='%{spdEnabled}'/>" name="spdEnabled" id="spdEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{fpaEnabled}'/>" name="fpaEnabled" id="fpaEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{fwdEnabled}'/>" name="fwdEnabled" id="fwdEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{chbEnabled}'/>" name="chbEnabled" id="chbEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{ltlEnabled}'/>" name="ltlEnabled" id="ltlEnabled<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{allEmailType}'/>" name="allEmailType" id="allEmailType<s:property value="%{id}"/>"/>
				 
				 <input type="hidden" value="<s:property value='%{parentBus.id}'/>" name="parentBusIds1" id="bufpare<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{branchBus.id}'/>" name="branchBusIds1" id="bufbranch<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{nationBus.id}'/>" name="nationBusIds1" id="bufnation<s:property value="%{id}"/>"/>
				 <input type="hidden" value="<s:property value='%{partnerBus.id}'/>" name="partnerBusIds1" id="bufpart<s:property value="%{id}"/>"/>
			 
 				</label>
				</td>
				 
		</tr>
		 
	</s:iterator>
	 
