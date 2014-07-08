<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
<sx:head/>
<title><s:text name="user.form.title" /></title>

<script type="text/javascript">

</script>
</head>

<body>
<SCRIPT language="JavaScript">
	function validate()
	{
		var error="";
		var cartNameValue = document.getElementById('cartsList').value;
		var urlName = document.getElementById('urlName').value;
		
		if(cartNameValue == -1)
		{
			var msg =  'Please select a Cart.\n';
			error += msg;
		}
		
		if(urlName == ''){
			var msg =  'Please enter a Store URL.';
			error += msg;
		}
		
	 	if(error != '') {
			alert(error);
			return false;
		}else{
			document.cartform.action = "createCart.action?step1=true";
	 		document.cartform.submit();
		}
	}
	
	
</SCRIPT>

<div id="messages"><jsp:include
	page="../common/action_messages.jsp" /></div>
<div class="form-container">

<s:form action="createCart" name="cartform">
	<s:if test="#session.edit == 'true'">
		<s:hidden name="method" value="update" />
	</s:if>
	<s:else>
		<s:hidden name="method" value="add" />
	</s:else>

	<div id="addcart_crtra">
	<table width="935px;">
		<tr>
			<td class="srch_crt"><s:if test="#session.edit == 'true'">
				<mmr:message messageId="label.add.new.cart" />
			</s:if> <s:else>
				<mmr:message messageId="label.add.new.cart" />
			</s:else></td>
		</tr>
	</table>
	</div>

	<div id="addcart_srch_panel">
	<table border="0" cellspacing="0" cellpadding="2" width="800px">
		<tr>
			<td class="markup_tbl_font"><mmr:message
				messageId="label.select.cart" />:</td>
			
			<td>						
      			<s:select cssClass="text_01_combo_big" cssStyle="width:135px;" name="cart.CartName"  listKey="CartName" listValue="CartName" 
	  				headerKey="-1"  headerValue="--Select Cart--" list="availableCarts" theme="simple" id="cartsList"/><br/>
			</td>
			
			<td class="markup_tbl_font"><mmr:message messageId="label.store.url" />:</td>
			<td><s:textfield size="20" key="cart.urlName" name="cart.urlName" cssClass="text_02_tf_bigger" id="urlName" /></td>
			<td class="markup_tbl_font"></td>
			<td align="left"><a onclick="return validate();"><img src="<s:url value="/mmr/images/launch_shop_btn.png" includeContext="true" />" border="0"></a></td>
		</tr>
	</table>
	</div>
	
	<s:include value="searchCart.jsp"/>
</s:form></div>
</body>
</html>


