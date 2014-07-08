		function clearShipTo(form) {
			form.elements['order.shipToAddress.company'].value='';
			form.elements['order.shipToAddress.addressBookId'].value='';
			form.elements['order.shipToAddress.address1'].value='';
			form.elements['order.shipToAddress.address2'].value='';
			form.elements['order.shipToAddress.city'].value='';
			form.elements['order.shipToAddress.phone'].value='';
			form.elements['order.shipToAddress.email'].value='';
			form.elements['order.shipToAddress.instructions'].value='';
			form.elements['order.shipToAddress.id'].value='';
			form.elements['order.shipToAddress.province'].value='';
			form.elements['order.shipToAddress.postalCode'].value='';
			form.elements['order.shipToAddress.attention'].value='';
			form.elements['order.shipToAddress.res'].checked=false;
			form.elements['order.shipToAddress.tailgate'].checked=false;
			form.elements['order.shipToAddress.notifyRecipient'].checked=false;
		}

		function clearShipFrom(form) {
			form.elements['order.shipFromAddress.company'].value='';
			form.elements['order.shipFromAddress.addressBookId'].value='';
			form.elements['order.shipFromAddress.address1'].value='';
			form.elements['order.shipFromAddress.address2'].value='';
			form.elements['order.shipFromAddress.city'].value='';
			form.elements['order.shipFromAddress.phone'].value='';
			form.elements['order.shipFromAddress.email'].value='';
			form.elements['order.shipFromAddress.instructions'].value='';
			form.elements['order.shipFromAddress.id'].value='';
			form.elements['order.shipFromAddress.province'].value='';
			form.elements['order.shipFromAddress.postalCode'].value='';
			form.elements['order.shipFromAddress.attention'].value='';
			form.elements['order.shipFromAddress.res'].checked=false;
			form.elements['order.shipFromAddress.tailgate'].checked=false;
			form.elements['order.shipFromAddress.confirmDelivery'].checked=false;
		}	
		
		function validateForm(form) {
			if(!validateOrderForm(form)) {
				return false;
			}
			if(!validatePostalCode(form)) {
				return false;
			}
			return true;
		}
		
		function validatePostalCode(form) {
			var error = false;
			var msg = '';
			if(form.elements['order.shipToAddress.country'].value == 'CA') {
				var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
				if(mask.exec(form.elements['order.shipToAddress.postalCode'].value) == null) {
					msg = msg + '(Ship To) Postal Code should be in Canadian format A#A#A# or A#A #A# ' + '\n';
					error = true;
				}
			} else if(form.elements['order.shipToAddress.country'].value == 'US') {
			var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['order.shipToAddress.postalCode'].value) == null) {
					msg = msg + '(Ship To) Postal Code should be in US format #####' + '\n';
					error = true;
				}
			}
			if(form.elements['order.shipFromAddress.country'].value == 'CA') {
				var mask = new RegExp('^[a-zA-Z]\\d[a-zA-Z][ ]{0,1}\\d[a-zA-Z]\\d$');
				if(mask.exec(form.elements['order.shipFromAddress.postalCode'].value) == null) {
					msg = msg + '(Ship From) Postal Code should be in Canadian format A#A#A# or A#A #A#' + '\n';
					error = true;
				}
			} else if(form.elements['order.shipFromAddress.country'].value == 'US') {
			var mask = new RegExp('^\\d{5}$');
				if(mask.exec(form.elements['order.shipFromAddress.postalCode'].value) == null) {
					msg = msg + '(Ship From) Postal Code should be in US format #####' + '\n';
					error = true;
				}
			}
			if(error == false)
	    		return true;
	    	else{
	    		alert(msg);
	    		return false;
	    	}
		}

		function update_packagetype() {
			var form = document.orderForm;
			form.method.value='stage1';
			form.submit();
		}
		
		function update_packagetype2() {
			var form = document.orderForm;
			var options = form.elements['order.packageTypeId'];
			var packageType = 1;
			for(var x=0;x<options.length;x++) {
				if(options[x].checked == true) {
					packageType = options[x].value;
				}
			}
			var divs = document.getElementsByTagName('div');
			for(var x=0;x<divs.length;x++) {
				if(divs[x].id.indexOf('addl_') != -1) {
					if(divs[x].id == 'addl_' + packageType) {
						divs[x].style.display='block';
					} else {
						divs[x].style.display='none';
					}
				}
			}
		}
		  	function allTheSame(rows) {
		  		var length = document.orderForm.elements['packages[0].length'].value;
		  		var width = document.orderForm.elements['packages[0].width'].value;
		  		var height = document.orderForm.elements['packages[0].height'].value;
		  		var weight = document.orderForm.elements['packages[0].weight'].value;
		  		var descr = document.orderForm.elements['packages[0].description'].value;
		  		
		  		for(var x=1;x<rows;x++) {
		  			document.orderForm.elements['packages[' + x + '].length'].value = length;
	  				document.orderForm.elements['packages[' + x + '].width'].value = width;
	  				document.orderForm.elements['packages[' + x + '].height'].value = height;
	  				document.orderForm.elements['packages[' + x + '].weight'].value = weight;
	  				document.orderForm.elements['packages[' + x + '].description'].value = descr;
		  		}
		  	}
		  	
	  		function sameAsAbove(pos) {
	  			pos = pos-1;

		  		var length = document.orderForm.elements['packages[' + pos + '].length'].value;
		  		var width = document.orderForm.elements['packages[' + pos + '].width'].value;
		  		var height = document.orderForm.elements['packages[' + pos + '].height'].value;
		  		var weight = document.orderForm.elements['packages[' + pos + '].weight'].value;
		  		var descr = document.orderForm.elements['packages[' + pos + '].description'].value;
				
				var x = pos+1;
				document.orderForm.elements['packages[' + x + '].length'].value = length;
	  			document.orderForm.elements['packages[' + x + '].width'].value = width;
	  			document.orderForm.elements['packages[' + x + '].height'].value = height;
	  			document.orderForm.elements['packages[' + x + '].weight'].value = weight;
	  			document.orderForm.elements['packages[' + x + '].description'].value = descr;
	  		}
		function submitOrderForm(val) {
			document.orderForm.method.value = val;
			document.orderForm.submit();
		}	  				
		
		function saveToAddress() {
			document.orderForm.method.value = 'save_to_address';
			document.orderForm.submit();
		}
		
		function saveFromAddress() {
			document.orderForm.method.value = 'save_from_address';
			document.orderForm.submit();
		}
		
		function hold_for_pickup_toggle() {
			var checked = document.orderForm.elements['order.holdForPickupRequired'].checked;
			if(checked == true) {
				var shipToName = document.orderForm.elements['order.shipToAddress.attention'].value;
				var shipToPhone = document.orderForm.elements['order.shipToAddress.phone'].value;
				if(shipToName == '') {
					alert("Recipient Name is required");
					document.orderForm.elements['order.holdForPickupRequired'].checked = false;
					return false;
				}
				if(shipToPhone == '') {
					alert("Recipient Phone # is required");
					document.orderForm.elements['order.holdForPickupRequired'].checked = false;
					return false;
				}
			}
			
		}
		
		function validateQuantity(form){
			var from = document.orderForm.elements['order.shipFromAddress.country'].value;
			var quantity = document.orderForm.elements['order.quantity'].value;
			
			if(from=="US" && quantity > 1){
				alert("Multi-piece option is not supported for US shipments. Please create separate orders for each package.");
				document.orderForm.elements['order.quantity'].value = 1;
			}
			
		}
		
		