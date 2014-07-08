<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:hidden name="custid" value="%{shippingOrder.customerId}" id="hidden_cid"/>
	<div id="products_tab_main_div">
		<div id="products_tab_div">&nbsp;</div>
			<div id="products_tab_div_tbl">
				<div id="add_prods_hdr">
					<table>
						<tr>
							<td class="fromAdd_header_table">
								<mmr:message messageId="label.product.cart"/>
							</td>
						</tr>
					</table>
				</div>
				<table cellpadding="2" cellspacing="1" width="1000px">
					<tr>
						<td class="hdr"><mmr:message messageId="label.product.line"/></td>
						<td>
							<s:url id="prodlineList" action="listProductLines" />
							<sx:autocompleter id="prodlineSelected" name="searchString" href="%{prodlineList}" dataFieldName="productLineSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false" valueNotifyTopics="/value_prodl" cssStyle="height:20px; width: 150px;" preload="true" formId="userform" keyName="productLineId"/>
						</td>
						<td>&nbsp;</td>
						<td class="hdr"><mmr:message messageId="label.product.name"/></td>
						<td>
							<s:url id="productList" action="listProductsByProductLine" />
							<sx:autocompleter id="autoproducts" name="searchString" href="%{productList}" dataFieldName="productByProductLineSearchResult" loadOnTextChange="true" loadMinimumCount="1" autoComplete="false"  valueNotifyTopics="/value_product" listenTopics="/value_product_listen" cssStyle="width:250px;" preload="true" formId="userform" keyName="productLineId"/>
						</td>
						<td>&nbsp;</td>
						<td class="hdr"><mmr:message messageId="label.shippingOrder.additionalServices.quantity"/></td>
						<td>&nbsp;</td>
						<td>
						<s:textfield id="product_qty" name="product_quantity" cssClass="text_02_tf_small" theme="simple" maxlength="4" onkeypress="return typenumbers(event,\'0123456789.\')"/>
						</td>
						<td>&nbsp;</td>
						<td><img src="<s:url value="/mmr/images/add_new_btn.png" includeContext="true" />" border="0" style="cursor: pointer;" onclick="addProductToShipment();"></td>
					</tr>
				</table>
			</div>
	</div>	
</body>
</html>