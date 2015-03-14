<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html> 
<head> 
    <title><s:text name="customer.form.title"/></title> 
	
</head> 
<body onload="doOnLoad()"> 

<script>
	
	$(window).load(function() {
	
  var wndo = $(window).height();
  wndo -=46;
  $('#wrapper_new').css('min-height',wndo);
  });
	</script>
	
	
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/countryProvince.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.js"></script>
<s:if test="%{#session.ROLE.contains('busadmin')}">
	<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
		});
		
		
		
	</script>
  </s:if>
  <s:else>
	<script>
		$(document).ready(function(){
		   $('.navi_icon').click(function(){
			$('.navigation ul').slideToggle();
		   });
		   // for grid
			$('table').wrap('<div class="grid_table_body"></div>');
			$("#sample1_length").wrap("<div class='box-cont1'></div>");
			$("div.box-cont1").each(function() {
			  $(this).append($(this).next());
			});
			$(".dataTables_info").wrap("<div class='box-cont2'></div>");
			$("div.box-cont2").each(function() {
			  $(this).append($(this).next());
			});
		});
		
		
		
	</script>
 </s:else>
  
  
  
<SCRIPT language="JavaScript">
var CCSectionActive = false;
var active = "<%=request.getAttribute("active_cc")%>";
var add = "<%=request.getAttribute("add")%>";
var newcc = "<%= request.getAttribute("new_cc")%>";

window.onload = doOnload;

	function doOnload()
	{
		//alert(newcc);
		if(newcc!='null')
			showCC('block');
	}
	
	function showBillingState() {
	ajax_Country=ajaxFunction();
	ajax_Country.onreadystatechange=function()
	  {
		   if(ajax_Country.readyState==4)
			{
			reponse=ajax_Country.responseText;
			js_stateid=document.getElementById("billingstateid");
			js_stateid.innerHTML= reponse;
			}
	  }
		firstBox = document.getElementById('billingCountry');
	  url="<%=request.getContextPath()%>/customer.listProvince.action?value="+billingCountry.value;
		//param="objName=ref_state&country_id="+country_id;
		ajax_Country.open("GET",url,true);
		ajax_Country.send(this);
} // End function showState()

	function submitform()
	{
		//local boolean check if everything is ok, then submit the form
		var check = true;
		var isShowRef = false;
				var salesCheck = '<s:property value="%{role}"/>';
				if(salesCheck != "sales")
			     isShowRef=document.getElementById("showref").checked;
		if(CCSectionActive) //if the credit card section is active or the user has selected to enter or update the credit card info, then check.
		{
			if(document.getElementById("ccid").value == '')
			{
				alert("Please enter the credit card Number");
				check = false;
			}
			else if(document.getElementById("cvdcid").value == '')
			{
				alert("Please enter the CVD Code");
				check = false;
			}
		}
		if(document.customerform.method.value=='edit')
		{
			document.customerform.action = "editcustomerinfo.action?showref="+isShowRef;
		}
		else
		{
			document.customerform.action = "createCustomer.action?showref="+isShowRef;
		}
		if(check)//if everything is fine, then submit the form.
		document.customerform.submit();
	}
	
	function submitcarrier()
	{
	 document.customerform.action = "createAccount.action";
	 document.customerform.submit();
	}
	
    function showState() {
		ajax_Country=ajaxFunction();
		ajax_Country.onreadystatechange=function()
		  {
			   if(ajax_Country.readyState==4)
				{
				reponse=ajax_Country.responseText;
				js_stateid=document.getElementById("stateid");
				js_stateid.innerHTML= reponse;
				}
		  }
		  firstBox = document.getElementById('firstBox');
		  url="<%=request.getContextPath()%>/customer.listProvince.action?value="+firstBox.value;
			//param="objName=ref_state&country_id="+country_id;
			ajax_Country.open("GET",url,true);
			ajax_Country.send(this);
	} // End function showState()	
	
	function checkBothSalesAgent()
	{	
		var e = document.getElementById("salesAgent");
		var strSalesUser = e.options[e.selectedIndex].value;
		
		var e2 = document.getElementById("salesAgent2");
		var strSalesUser2 = e2.options[e2.selectedIndex].value;
		
		if(strSalesUser==strSalesUser2)
		{
			alert("Both the selected Sales Agent should not be same.");
			e.selectedIndex = 0;
			e2.selectedIndex = 0;
		}		
	}
	
	function resetform(){
		document.customerform.action = "addcustomer.action";
		document.customerform.submit();
	}	
		
	function cancelform(){
		document.customerform.action = "searchcustomer.action";
		document.customerform.submit();
	}	
	
	function resetform1(){
		document.customerform.action = "myaccountinfo.action";
		document.customerform.submit();
	}	
	
	
	function ShowCCForCCType(val)
	{
		if(val==2)
		{
			if(add!='null')
			showCC('block');

		}
		else
		{
			if(add!='null')
			showCC('none');
		}		
	}
	
	function showCC(val){
		if(val=='block')
			CCSectionActive = true;
		else
			CCSectionActive = false;
		document.getElementById("payment_info_pnl").style.display = val;
	}
	
	function setPrevAdd()
	{
		setValue('customer.newCreditCard.billingAddress.contactName',document.customerform.ccactive_contactname.value);
		setValue('customer.newCreditCard.billingAddress.address1',document.customerform.ccactive_address1.value);
		setValue('customer.newCreditCard.billingAddress.address2',document.customerform.ccactive_address2.value);
		setValue('customer.newCreditCard.billingAddress.city',document.customerform.ccactive_city.value);
		setValue('customer.newCreditCard.billingAddress.postalCode',document.customerform.ccactive_postalCode.value);
		setValue('customer.newCreditCard.billingAddress.countryCode',document.customerform.ccactive_countryCode.value);
		setValue('customer.newCreditCard.billingAddress.provinceCode',document.customerform.ccactive_provinceCode.value);
	}
	
	function setValue(name, val)
	{
		document.getElementsByName(name)[0].value = val;
	}
</SCRIPT> 
<script>
	$(document).ready(function(){
	$("#apiname").val(" ");
	});
</script>
	
<div id="messages">
<div class="message_inner">
	<jsp:include page="../common/action_messages.jsp"/>
</div>	
</div>
<div class="form-container" > 
<s:form action="createCustomer" name="customerform" style="margin-bottom:0px" id="apiname">
<s:hidden name="ccactive_contactname" value="%{customer.creditCardActive.billingAddress.contactName}"/>
<s:hidden name="ccactive_address1" value="%{customer.creditCardActive.billingAddress.address1}"/>
<s:hidden name="ccactive_address2" value="%{customer.creditCardActive.billingAddress.address2}"/>
<s:hidden name="ccactive_city" value="%{customer.creditCardActive.billingAddress.city}"/>
<s:hidden name="ccactive_postalCode" value="%{customer.creditCardActive.billingAddress.postalCode}"/>
<s:hidden name="ccactive_countryCode" value="%{customer.creditCardActive.billingAddress.countryCode}"/>
<s:hidden name="ccactive_provinceCode" value="%{customer.creditCardActive.billingAddress.provinceCode}"/>
<div class="content_body">
							<div class="content_table" id="contenttbl">
								<div class="content_header">
									<div class="cont_hdr_title">
									<s:if test="#session.edit == 'true'">
										<mmr:message messageId="label.heading.editcustomer"/>  
										<s:hidden name="method" value="edit"/>
										<s:hidden name="customer.businessId" />
									</s:if> 
									<s:else>
										<mmr:message messageId="label.heading.addcustomer"/> 
										<s:hidden name="method" value="add"/>
									</s:else>
									</div>
									<div class="cont_hdrtitle_w"><s:property value="customer.name"/></div>
									<div class="form_buttons">
									<a href="#" onclick="submitform();" ><mmr:message messageId="label.btn.save"/></a>
									<!--<s:submit onclick="submitform();" value="SAVE"/>-->
									<s:if test="#session.edit != 'true'">
									<a href="#" onclick="resetform();" ><mmr:message messageId="label.btn.reset"/></a>
									<!--<s:submit onclick="resetform();" value="RESET"/>-->
									</s:if>										
									
									<s:else>
									
									
										<a href="#" onclick="cancelform();" ><mmr:message messageId="label.btn.cancel"/></a>
										<!--<s:submit onclick="cancelform();" value="CANCEL"/>-->
									</s:else>
									<s:if test="#session.edit == 'true'">
								<!--	<a href="manage.sales.users.action">Manage Sales Users</a>  -->
									</s:if> 
									
									</div>
								</div>
								<div class="cont_data_body" >
									<div class="rows">
									<s:if test="#session.edit != 'true'">
										<div class="fields">
											<label><mmr:message messageId="label.customer.username"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.username" name="customer.username" value="%{customer.username}" />
											<s:textfield  key="customer.username" cssStyle="display:none" name="customer.username" value="%{customer.username}" class="hidden" />
											</div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.userpassword"/></label>
											<div class="controls"><span>:</span><s:password  key="customer.password" name="customer.password"  />
											<s:password  key="customer.password" cssStyle="display:none" name="customer.password"  class="hidden"/></div>
										</div>
									</s:if> 
										<div class="fields">
											<label><mmr:message messageId="label.customer.name"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.name" name="customer.name" id="apiname"  /></div>
										</div>
									<s:if test="%{#session.ROLE.contains('busadmin')}">
										<div class="fields">
											<label><mmr:message messageId="label.customer.accountNumber"/> </label>
											<div class="controls"><span>:</span><s:textfield size="24" key="customer.accountNumber" name="customer.accountNumber"  /></div>
										</div>
									</s:if>
										<div class="fields">
											<label><mmr:message messageId="label.customer.contact"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.contactName" name="customer.address.contactName"   /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.country"/> </label>
											<div class="controls"><span>:</span><s:select    listKey="countryCode" listValue="countryName" 
												name="customer.address.countryCode" headerKey="-1"  list="#session.CountryList" 
												onchange="javascript:showState();"  id="firstBox" theme="simple"/></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.address1"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.address1" name="customer.address.address1"  /></div>
										</div>
								
										<div class="fields">
											<label><mmr:message messageId="label.customer.address2"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.address2" name="customer.address.address2"  /></div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.customer.postalCode"/> </label>
											<div class="controls"><span>:</span><s:textfield  id="custPostalCode" key="customer.address.postalCode" name="customer.address.postalCode" onblur="javascript:getAddressSuggestCustomer();"  />
											<img id="loading-img-cust" style="display:none;margin-top:-25px" src="<s:url value="/mmr/images/loading.gif" includeContext="true" />" border="0"></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.email"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.emailAddress" name="customer.address.emailAddress"   /></div>
										</div>
										
										
										<div class="fields">
											<label><mmr:message messageId="label.customer.phone"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.phoneNo" name="customer.address.phoneNo"   /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.city"/> </label>
											<div class="controls"><span>:</span><s:textfield  key="customer.address.city" name="customer.address.city"  /></div>
										</div>
										
										
										 <s:if test="%{#session.ROLE.contains('busadmin')}">
										<div class="fields">
											<label><mmr:message messageId="label.customer.apiusername"/> </label>
											<div  class="controls"><span>:</span><s:textfield   key="customer.apiusername" name="customer.apiUsername" value="%{customer.apiUsername}" id="apiname" />
											<s:textfield   cssStyle="display:none"  value=" "  class="hidden"/>
											</div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.customer.apipassword"/> </label>
											<div class="controls"><span>:</span><s:password  key="customer.apipassword" name="customer.apiPassword"   showPassword="true" />
											<s:password   cssStyle="display:none"    showPassword="true" /></div>
										</div>
										</s:if>
										<s:else>
										<div class="fields">
											<label></label>
											<div class="controls"></div>
										</div>	
										<div class="fields">
											<label></label>
											<div class="controls"></div>
										</div>	
										</s:else>
										<div class="fields" >
											<label><mmr:message messageId="label.customer.province"/> </label>
											<div id="stateid" class="controls"><span>:</span>
											<s:select key="customer.address.provinceCode" name="customer.address.provinceCode"  
											listKey="provinceCode" listValue="provinceName" list="#session.provinces"/>
											</div>
										</div>
										 <s:if test="%{#session.ROLE.contains('busadmin')}">
									
										<div class="fields">
											<label><mmr:message messageId="label.customer.timezone"/> </label>
											<div class="controls"><span>:</span><s:select key="customer.timezone"    name="customer.timeZone" headerKey="1"   
												listKey="id" listValue="name" list="#session.timeZones"/></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.payableDays"/> </label>
											<div class="controls"><span>:</span><s:textfield size="24" key="customer.payableDays" name="customer.payableDays" cssStyle="text-align:right;padding-right:5px;"  /></div>
										</div>
									
									
										<div class="fields">
											<label><mmr:message messageId="label.customer.active"/> </label>
											<div class="controls"><span>:</span><s:select key="customer.active"   name="customer.active" headerKey="1" list="#{'true':'Yes','false':'No'}"  value="customer.active"  /></div>
										</div>
										
										<div class="fields">
											<label><mmr:message messageId="label.customer.paymentType"/> </label>
											<div class="controls"><span>:</span><s:select id="ptid" key="customer.paymentType" name="customer.paymentType" headerKey="1" list="#{'1':'On Credit','2':'Credit Card'}"     onchange="ShowCCForCCType(this.value);"/></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.creditLimit"/> </label>
											<div class="controls"><span>:</span><s:textfield size="24" key="customer.creditLimit" name="customer.creditLimit" cssStyle="text-align:right;padding-right:5px;"  /></div>
										</div>
										<s:if test="%{#session.ROLE.contains('busadmin')}">
										<div class="fields">
											<label><mmr:message messageId="label.customer.default.currency"/></label>
											<div class="controls"><span>:</span><s:select
												list="#{'CAD':'CAD', 'USD':'USD','CNY':'CNY','EUR':'EUR'}"
												name="customer.defaultCurrency" headerKey="" headerValue=""
												  /> </div>
										</div>	
										<!-- this code in under test -->
										<div class="fields">
										<label><mmr:message messageId="label.customer.hold.terms"/></label>
											<div class="controls"><span>:</span><s:textfield size="24" key="customer.holdTerms" name="customer.holdTerms" cssStyle="text-align:right;padding-right:5px;"  /></div>
										</div>
										<div class="fields">
           										<label><mmr:message messageId="label.customer.showref"/></label>
           										<div class="controls"><span>:</span>
            									<s:checkbox name="customer.reference" value="%{customer.reference}" key="customer.reference" id="showref"/>             
            									</div>
           								</div>
           								<div class="fields">
           										<label><mmr:message messageId="label.customer.chbCustomer"/></label>
           										<div class="controls"><span>:</span>
            									<s:checkbox name="customer.chbCustomer" value="%{customer.chbCustomer}" key="customer.chbCustomer" id="chbcus"/>             
            									</div>
           								</div>
           								<s:if test="#session.edit != 'true'">
           								<div class="fields">
           								<label><mmr:message messageId="add.label.status.customer"/></label>
           								<div class="controls"><span>:</span>
           							
           								
           								<s:select list="#{'Prospect':'Prospect','Customer':'Customer'}" name="customer.status"  disabled="true" />
           								</div></div></s:if>
           								<s:if test="#session.edit == 'true'">
           								<div class="fields">
           								<label><mmr:message messageId="add.label.status.customer"/></label>
           								<div class="controls"><span>:</span>
           							        								
           								<s:select list="#{'Prospect':'Prospect','Customer':'Customer'}" name="customer.status" value="%{customer.status}" />
           								</div></div></s:if>
										</s:if>
									</div>
									</s:if>
								</div>
								
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.heading.accounting"/></div>
									<s:if test="#session.edit == 'true'">
										<div class="cont_hdrtitle_w" style="padding-left:470px;"><mmr:message messageId="label.customer.availableCredit"/>&nbsp;<s:property value="#session.currency"/><s:property value="#session.availableCredit"/></div>
									</s:if>								
								</div>
								<div class="cont_data_body">
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.customer.apcontact"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.apcontactName" name="customer.apcontactName"  /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.invoice.email"/></label>
											<div class="controls"><span>:</span><s:textfield key="customer.invoicingemail" name="customer.invoicingEmail"   /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.customer.new.apphone"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.apPhone" name="customer.apPhone"  /></div>
										</div>
									</div>
								</div>	
								
								<s:if test="#session.edit == null && #session.edit != 'true'">
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.carriers.setup"/></div>								
								</div>
								<div class="cont_data_body" style="padding-bottom:10px; font-size:12px;" >
									<div class="rows">
										<div class="fields">
											<label><mmr:message messageId="label.monthly.spend"/></label>
											<div class="controls"><span>:</span><s:textfield size="24" key="customer.monthlySpend" name="customer.monthlySpend" cssStyle="text-align:right;padding-right:5px;"  /></div>
										</div>
										
									</div>
									
									<s:iterator value="#session.CARRIERS" status="carriercount">
											<s:set name="carrier" value="%{#session.CARRIERS.get(#carriercount.index)}"/>
										<s:if test="%{#carriercount.index % 3 == 0}">
									
										</s:if>
										<div class="fieldscheckgroup">
											<div class="checkgroupctrls" >
												<s:checkbox name="select[%{#carriercount.index}]" value="select[%{#carriercount.index}]"  />
											</div>	
											<label><s:property value="#carrier.name"/></label>
										</div>	
									</s:iterator>
									
									
								</div>	
								</s:if>
								
								<s:if test="%{#session.edit != null && #session.business.defaultPaymentTypeLevel!=0}">	
								<div class="content_header">
									<div class="cont_hdr_title"><mmr:message messageId="label.billing.section"/> :</div>
								</div>
								<div class="cont_data_body">
									<div class="rows">
									<s:if test="%{#session.ActiveCC==null}">
										<div class="fields">
											<s:a href="#" onclick="showCC('block');"><mmr:message messageId="add.payment.information"/></s:a>
										</div>	
									</s:if>	
									<s:else>
									<div class="fields">
									<s:a href="#" onclick="showCC('block');"><mmr:message messageId="update.payment.information"/></s:a>
									</div>	
									</s:else>
									<div class="fields">
									</div>	
									<s:if test="%{#session.ActiveCC!=null}">
									<div class="rows">
										<div class="credit_cardimg"><img src="<s:url value="/mmr/images/credit_cards_64.png" includeContext="true" />" border="0"> </div>
										<div class="fields">
											<label><mmr:message messageId="label.last.4.digits"/> :</label>
											<span><s:property value="%{customer.creditCardActive.ccLast4}"/></span>
										</div>	
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.expiryMonthYear"/> :</label>
											<span><s:property value="%{customer.creditCardActive.ccExpiryMonth}"/></span>
										</div>	
										<div class="fieldsvl">
											<label><mmr:message messageId="label.creditcard.billingAddress1"/> :</label>
											<span><s:property value="%{customer.creditCardActive.billingAddress.longAddress}"/> </span>
										</div>
									</div>	
									</s:if>
									<s:else>
									<div class="rows">
										<p><mmr:message messageId="no.credit.card.information"/></p>
									</div>
									</s:else>
								</div>
								</s:if>	
								</div>
								
								
								
							<div class="content_body">
							<div class="content_table">	
								
								<div id="payment_info_pnl">
								
								<div class="content_header" >
									<div class="cont_hdr_title"><mmr:message messageId="label.credit.card"/> :</div>
									<div class="cont_hdrtitle_w"><mmr:message messageId="label.enter.credit.card.details"/></div>
									<div class="form_buttons">
										
										<s:submit onclick="submitform();" value="SAVE" />	
										<s:submit onclick="resetform1();" value="CANCEL" />										
									</div>
								</div>
								<div class="cont_data_body" style="background-color:#e5e5e5">
									<div class="rows">
									<s:if test="%{#session.ActiveCC!=null}">
									<div class="rows">
										<label style="width:200px; float:left; padding:5px;"><mmr:message messageId="label.same.billing.address" /> :</label>
										
										<s:checkbox name="" id="chk_add" onclick="setPrevAdd();" style="margin-top:7px; float:left;"/>
									</div>
									</s:if>
									
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.number"/> </label>
											<div class="controls"><span>:</span><s:textfield  id="ccid" key="customer.newCreditCard.ccNumber" name="customer.newCreditCard.ccNumber" /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.cvdCode"/></label>
											<div class="controls"><span>:</span><s:textfield  id="cvdcid" key="customer.newCreditCard.cvd" name="customer.newCreditCard.cvd"  /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.billingCountry"/></label>
											<div class="controls"><span>:</span><s:select    listKey="countryCode" listValue="countryName" 
												name="customer.newCreditCard.billingAddress.countryCode" headerKey="-1"  list="#session.CountryList" 
												onchange="javascript:showBillingState();"  id="billingCountry" theme="simple"/></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.expiryMonthYear"/></label>
											<div class="controls"><span>:</span>
											<s:select 
											list="#{'01':'Jan', '02':'Feb', '03':'Mar', '04':'Apr', '05':'May', '06':'Jun', '07':'Jul', '08':'Aug', '09':'Sep', '10':'Oct', '11':'Nov', '12':'Dec'}"
											key="customer.newCreditCard.ccExpiryMonth"
											name="customer.newCreditCard.ccExpiryMonth"
											  cssStyle="width: 68px; "/>
											<s:select
											list="#{'2012':'2012', '2013':'2013', '2014':'2014', '2015':'2015', '2016':'2016', '2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020'}"
											key="customer.newCreditCard.ccExpiryYear"
											name="customer.newCreditCard.ccExpiryYear"
											  cssStyle="width: 68px;  margin-right:2px;"/>
											</div>
										</div>
									
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.nameOnCard"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.newCreditCard.billingAddress.contactName" name="customer.newCreditCard.billingAddress.contactName"  /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.billingPostalCode"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.newCreditCard.billingAddress.postalCode" name="customer.newCreditCard.billingAddress.postalCode"  /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.billingAddress1"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.newCreditCard.billingAddress.address1" name="customer.newCreditCard.billingAddress.address1"  /></div>
										</div>
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.billingAddress2"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.newCreditCard.billingAddress.address2" name="customer.newCreditCard.billingAddress.address2"  /></div>
										</div>
									
										<div class="fields">
											<label><mmr:message messageId="label.creditcard.billingCity"/></label>
											<div class="controls"><span>:</span><s:textfield  key="customer.newCreditCard.billingAddress.city" name="customer.newCreditCard.billingAddress.city"  /></div>
										</div>
										
									</div>	
									<div class="rows">
										
										<div class="fields">
											<label></label>
											<div class="controls"></div>
										</div>	
										<div class="fields">
											<label></label>
											<div class="controls"></div>
										</div>
										
										<div class="fields" >
											<label><mmr:message messageId="label.creditcard.billingProvince"/></label>
											<div id="billingstateid" class="controls"><span>:</span><s:select key="customer.newCreditCard.billingAddress.provinceCode" name="customer.newCreditCard.billingAddress.provinceCode"     
												listKey="provinceCode" listValue="provinceName" list="#session.provinces"/></div>
										</div>
										
									</div>
								</div>
								
								</div>
							</div>
						</div>							
								
								
								
							</div>
						</div>
						
		</div>
		</s:form>
		</div>
	</body>	  
</html>