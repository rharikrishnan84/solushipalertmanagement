 <%@ taglib prefix="s" uri="/struts-tags" %>
 	
		<s:iterator id="product"  status="rowstatus" value="productList">	
		 <s:if test="#rowstatus.odd == true">
			<tr class="odd">
		</s:if>
		<s:else>
		<tr class="even">
		</s:else>	
			
			<s:hidden name="products.pId" value="%{products.productId}"/>
			<s:hidden name="products.cid" value="%{products.customerId}"/>
			<td><input  class="dataTable-checkbox" type="checkbox" name="searchProductCheckBox"  value="<s:property value="productId"/>"/> </td>
			<td><s:property value="productName"/></td>
			<td title="<s:property value="productDescription"/>"><div style="width:380px;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="productDescription"/></div></td>
			<td><s:property value="productHarmonizedCode"/></td>
			<td><s:property value="originCountry"/></td>
			<td style="text-align: right;"><s:property value="unitPrice"/></td>
			
		</tr>
		</s:iterator>
		 