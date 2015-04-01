 
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
				 
			</tr>
			 
		</s:iterator>
	 
