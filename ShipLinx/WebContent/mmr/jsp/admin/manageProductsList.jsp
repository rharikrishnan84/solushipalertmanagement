<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<body>
<div id="manageproducts">
<div id="tab"><br/></div>
	<div id="res"><mmr:message messageId="label.search.results"/></div>
	<div id="results">	
	<s:if test="%{productsList.size()>1}">
	<div id="rslt_stmnt"><br/><s:property value="productsList.size()" /><mmr:message messageId="label.search.results.items"/></div>
	</s:if>
	<s:elseif test="%{productsList.size()==1}">
	<div id="rslt_stmnt"><br/><s:property value="productsList.size()" /><mmr:message messageId="label.search.results.item"/></div>
	</s:elseif>
	<s:else>
	<div id="rslt_stmnt"><br/><mmr:message messageId="label.search.results.noitems"/></div>
	</s:else>
	</div>
	
<div id="result_tbl">		
		<display:table id="product" uid="row" name="productsList" pagesize="100" export="true" requestURI="listCustomerProducts.action" cellspacing="0" cellpadding="4" class="srch_tbl">
		<s:hidden name="products.productId" value="%{#attr.row.productId}"/>		
		  <s:if test="%{#request.role!='busadmin'}">
		     <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
	            <s:a href="editproduct.action?productId=%{#attr.row.productId}"> 
				<img src="<s:url value="/mmr/images/edit_pencil.png" includeContext="true" />" alt="Edit Customer" border="0"> </s:a>
	   		</display:column>
             <display:column headerClass="tableTitle2" title="" style="text-align:center;" > 
			  	<s:a onclick="return confirm('Do you really want to delete the selected Product?')" href="deleteProduct.action?productId=%{#attr.row.productId}">
				<img src="<s:url value="/mmr/images/delete.gif" includeContext="true" />" alt="Customer Account Info" border="0"> </s:a>
            </display:column>
            </s:if>
            
			<display:column headerClass="tableTitle2_product" property="productName" title="Name" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="productDescription" title="Description" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="productHarmonizedCode" title="Harmonized Code" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="unitPrice" title="Unit Price" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="originCountry" title="Origin Country" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="productLine.productlineName" title="Product Line" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="locationCount" title="Location Count" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="wipCount" title="WorkInProgress Count" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="quarantineCount" title="Quarantine Count" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="inqueueCount" title="InQueue Count" style="text-align:center;"/>	
			<display:column headerClass="tableTitle2_product" property="backOrderCount" title="BackOrder Count" style="text-align:center;"/>
			<display:column headerClass="tableTitle2_product" property="totalCount" title="Total Count" style="text-align:center;"/>
		</display:table>
	
	
	
</div>
<div id="res_tbl_end"></div>
</div>
</body>
</html>