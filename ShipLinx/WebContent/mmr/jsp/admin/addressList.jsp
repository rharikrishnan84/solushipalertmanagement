<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="mmr" uri="/mmr-tags" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/jsp/shipping/laptop.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/demo_table.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/ipad.css" media="screen and (min-width:768px) and (max-width:1024px)"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/mmr/styles/smartphone.css" media="screen and (min-width:320px) and (max-width:767px)"/>

<script src="http://datatables.net/release-datatables/media/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mmr/scripts/jquery.dataTables.js"></script>

<script type="text/javascript">
	
		$(document).ready(function() {
			$('#sample1').dataTable(); 
			
			$("#check_all").click(function(){
				var temp=$(".dataTable-checkbox").attr("checked");
				if(temp == null){
			    $(".dataTable-checkbox").attr("checked","checked");
				}
				else{
				$(".dataTable-checkbox").removeAttr("checked");
				}
			});
		
		} );
		
		
		function editAddress(){
		
			var editUserId = document.getElementsByName("address");
			
			var i1,txt1 = 0;
		   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please check atleast one');
		   }
		   else{
			
			
			
			
			
			var i,txt;
			for (i=0;i<editUserId.length;i++){
				if (editUserId[i].checked){
					txt = editUserId[i].getAttribute("addressid") ;					
				}
			}
			var addressid=txt;
			
			//document.getElementById("addresslist").action = "edit.address.action?addressid="+addressid;
			//document.getElementById("addresslist").submit();
			window.location.href="edit.address.action?addressid="+addressid;
			}
		}
		
		function deleteAddress(){
		
			var editUserId = document.getElementsByName("address");
			
			var i1,txt1 = 0;
		   for (i1=0;i1<editUserId.length;i1++){
			if (editUserId[i1].checked){
			 txt1 += 1;      
			}
		   }
		   if(txt1 < 1){
			alert('Please check atleast one');
		   }
		   else if(txt1>1){
				alert('Please check atmost one');
			}
		   else{
				/*var del=confirm("Do you really want to delete the selected Address?");
				if(del==true){
					var i,txt;
					for (i=0;i<editUserId.length;i++){
						if (editUserId[i].checked){
							txt = editUserId[i].getAttribute("addressid") ;					
						}
					}
					var addressid=txt;
					window.location.href="delete.address.action?addressid="+addressid;
				}*/
				
				
				//createCustomConfirm;
				
				var ALERT_BUTTON_TEXT = 'Ok';
				var CANCEL_BUTTON_TEXT = 'Cancel';
				d = document;
				if(d.getElementById("modalContainer")) return;
				mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
				mObj.id = "modalContainer";
				mObj.style.height = d.documentElement.scrollHeight + "px";
				alertObj = mObj.appendChild(d.createElement("div"));
				alertObj.id = "alertBox";
				if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
				var leftMargin = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2;
				alertObj.style.left = (leftMargin-40) + "px";
				alertObj.style.visiblity="visible";
				
				msg = alertObj.appendChild(d.createElement("p"));
				
				msg.innerHTML = "Do you really want to delete the selected Address?";
		
				
				btnbodyObj = alertObj.appendChild(d.createElement("div"));
				btnbodyObj.id = "btnBody";
				
				
				btnconfirm = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirm.id = "confirmBtn";
				btnconfirm.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
				btnconfirm.href = "#";
				btnconfirm.focus();
				$('#confirmBtn').css('margin-left','40px');

				
				btnconfirmCancel = btnbodyObj.appendChild(d.createElement("a"));
				btnconfirmCancel.id = "confirmCancelBtn";
				btnconfirmCancel.appendChild(d.createTextNode(CANCEL_BUTTON_TEXT));
				btnconfirmCancel.href = "#";
				btnconfirmCancel.focus();
				$('#confirmCancelBtn').css('margin-right','40px');

				
				
				
				$('#confirmBtn').click(function(){
					removeCustomConfirm();
					var i,txt;
					for (i=0;i<editUserId.length;i++){
						if (editUserId[i].checked){
							txt = editUserId[i].getAttribute("addressid") ;					
						}
					}
					var addressid=txt;
					window.location.href="delete.address.action?addressid="+addressid;
					
				});
				$('#confirmCancelBtn').click(function(){
					removeCustomConfirm();
					
				});
				alertObj.style.display = "block";
				function removeCustomConfirm() {
				document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
				}
			}
			
			
			
		}
		</script>
			<script>
	$(document).ready(function(){
	
		$('#actiondown').click(function(event){
			event.preventDefault();
			$('#actionup').css('display','block');
			$('#actiondown').css('display','none');
			$('#actionmenu').css('display','block');
		});
		$('#actionup').click(function(event){
			event.preventDefault();
			$('#actionup').css('display','none');
			$('#actiondown').css('display','block');
			$('#actionmenu').css('display','none');
		});
	});
</script>
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
		

<div class="content_table" style="background-color:#fff">
	<div id="srchaddrss_results">
	<div id="srchusr_res">
	<span><mmr:message messageId="label.address"/></span>
	</div>
		<div class="form_buttons">
		<a href="#" id="actiondown" >ACTION <span style="font-size:14px;">&#9660;</span></a>
			<a href="#" id="actionup" >ACTION <span style="font-size:14px;">&#9650;</span></a>
			<ul id="actionmenu">
			<li><a href="new.address.action"><mmr:message messageId="label.search.addnew"/></a></li>
			<li><a href="javascript:editAddress()">EDIT</a></li>
			<li><a href="javascript:deleteAddress()">DELETE</a></li>	
			</ul>
		</div>	
	</div>
<div id="srchaddrss_result_tbl">
	<table cellpadding="0" cellspacing="0"  border="0px" class="display" id="sample1" >
	<thead>
		<tr height="25px">
		<th style="text-align:center;"><input id="check_all" type="checkbox" style=""/></th>
			<th>Company</th>
			<th>City</th>
			<th>State/Province</th>
			<th>Contact Name</th>
			<th style="width:278px !important">Email</th>
			<th>Zip Code</th>
		</tr>
	</thead>
	<tbody>
	<s:set var="index" value="0" />
	<s:iterator id="addresstable" value="addressList" status="rowstatus">
	<tr>
		<s:set name="addressId" value="addressList[#index].getAddressId()" />
			<td style="width:29px; text-align:center"><input  class="dataTable-checkbox"  addressId="<s:property value='%{#addressId}' />" name="address" type="checkbox" /> 
				
			</td>
			<td style="text-align: left;" <span title="<s:property value="abbreviationName"/>"></span><div style="width:100px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="abbreviationName"/></div></td>
			<td style="text-align: left;" <span title="<s:property value="city"/>"></span><div style="width:100px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="city"/></div></td>
		
			<td><s:property value="provinceCode" /></td>
			<td style="text-align: left;" <span title="<s:property value="contactName"/>"></span><div style="width:100px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="contactName"/></div></td>
			
			<td style="text-align: left;" <span title="<s:property value="emailAddress"/>"></span><div style="width:200px !important;overflow:hidden;white-space:nowrap;text-overflow: ellipsis"><s:property value="emailAddress"/></div></td>
			
			<td><s:property value="postalCode" /></td>
			
		</tr>
		<s:set var="index" value="#index+1" />
		</s:iterator>
	</tbody>
	</table>	
</div>
</div>

		<div class="content_table" >
		&nbsp;
</div>




