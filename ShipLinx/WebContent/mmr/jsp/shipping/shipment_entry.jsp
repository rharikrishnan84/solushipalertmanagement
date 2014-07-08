<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<table cellpadding="0" cellspacing="0" class="tds" align="center">
		<tr>
			<th colspan="2">Ship From</th>
		</tr>
		<tr>
			<td>
				<!--  Username / Password -->		
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="invisible2">
					<tr>
						<td>
						
						<mmr:message messageId="label.zipPostalCode"/><s:if test="user.newUser == true">&nbsp;<em>*</em></label></s:if><br>
						<s:password name="user.password" id="password" maxlength="25"
							size="25" value="" onkeypress="return submitEnter(this,event)" /></td>
						<td width="300"><s:if test="user.newUser == true"><label for="retypePassword"></s:if>
						<mmr:message messageId="label.retype.password"/><s:if test="user.newUser == true">&nbsp;<em>*</em></label></s:if><br>
						<s:password name="user.retypePassword" maxlength="25" size="25"
							value="" onkeypress="return submitEnter(this,event)" /></td>
					</tr>
				</table>
			</td>
		</tr>


	
</table>
