// Global variables.
domain		= '';
path		= '/';
secure		= 0;

// Function to save a field.
function save_field(obj) {
	var cookie_value = '';
	var objType = new String(obj.type);
	switch(objType.toLowerCase()) {
		case "checkbox" :
			if (obj.checked) cookie_value = obj.name + '=[1]'
			else cookie_value = obj.name + '=[0]'
			break;
		case "undefined" :
			// a.k.a. radio field.
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].checked) cookie_value = obj[i].name + '=[' + i + ']'
			}
			break;
		case "select-one" :
			cookie_value = obj.name + '=[' + obj.selectedIndex + ']';
			break;
		case "select-multiple" :
			cookie_value = obj.name + '=[';
			for (var i = 0; i < obj.options.length; i++) {
				if (obj.options[i].selected) cookie_value += '+' + i
			}
			cookie_value += ']';
			break;
		default :
			// We assume all other fields will have
			// a valid obj.name and obj.value
			cookie_value = obj.name + '=[' + obj.value + ']';
		//alert("cookie_value: " + cookie_value);
	}
	if (cookie_value) {
		var expires = new Date();
		expires.setYear(expires.getFullYear() + 1);
		//document.cookie = '';
		//alert("document.cookie: " + document.cookie);
		document.cookie = cookie_value +
		((domain.length > 0) ? ';domain=' + domain : '') +
		((path) ? ';path=' + path : '') +
		((secure) ? ';secure' : '') +
		';expires=' + expires.toGMTString();
//		alert("document.cookie: " + document.cookie);
	}
	return 1;
}

// Function to retrieve a field.
function retrieve_field(obj) {
	var cookie = '', real_value = '';
	cookie = document.cookie;
	//alert("cookie: " + cookie);
	var objType = new String(obj.type);
	if (obj.name)
		var objName = new String(obj.name);
	else
		var objName = new String(obj[0].name);
	var offset_start = cookie.indexOf(objName + '=[');
	if (offset_start == -1) return 1;
	var offset_start_length = objName.length + 2;
	offset_start = offset_start + offset_start_length;
	var offset_end = cookie.indexOf(']', offset_start);
	real_value = cookie.substring(offset_start, offset_end);
	switch(objType.toLowerCase()) {
		case "checkbox" :
			if (real_value == '1') obj.checked = 1
			else obj.checked = 0
			break;
		case "undefined" :
			obj[real_value].checked = 1;
			break;
		case "select-one" :
			obj.selectedIndex = real_value;
			break;
		case "select-multiple" :
			for (var i = 0; i < obj.options.length; i++) {
				if ((real_value.indexOf('+' + i)) > -1)
					obj.options[i].selected = 1;
				else
					obj.options[i].selected = 0;
			}
			break;
		default :
			obj.value = real_value;
			break;
	}
	return 1;
}
// End JavaScript -->

function delete_cookie(obj) {
	var cookie_value = '';
	var objType = new String(obj.type);
	switch(objType.toLowerCase()) {
		case "checkbox" :
			if (obj.checked) cookie_value = obj.name + '=[1]'
			else cookie_value = obj.name + '=[0]'
			break;
		case "undefined" :
			// a.k.a. radio field.
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].checked) cookie_value = obj[i].name + '=[' + i + ']'
			}
			break;
		case "select-one" :
			cookie_value = obj.name + '=[' + obj.selectedIndex + ']';
			break;
		case "select-multiple" :
			cookie_value = obj.name + '=[';
			for (var i = 0; i < obj.options.length; i++) {
				if (obj.options[i].selected) cookie_value += '+' + i
			}
			cookie_value += ']';
			break;
		default :
			// We assume all other fields will have
			// a valid obj.name and obj.value
			cookie_value = obj.name + '=[' + obj.value + ']';
		//alert("cookie_value: " + cookie_value);
	}
	if (cookie_value) {
		var expires = new Date();
		expires.setYear(expires.getFullYear() - 1);
		//document.cookie = '';
		//alert("document.cookie: " + document.cookie);
		document.cookie = cookie_value +
		((domain.length > 0) ? ';domain=' + domain : '') +
		((path) ? ';path=' + path : '') +
		((secure) ? ';secure' : '') +
		';expires=' + expires.toGMTString();
	}
	return 1;

}
