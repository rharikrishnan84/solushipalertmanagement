
<s:select name="user.address.countryCode" onkeypress="return submitEnter(this,event)"
	list="#request.countries"
	listKey="countryCode"
	listValue="countryName"
	headerKey="" headerValue="%{getText('country.list.notes')}" />
