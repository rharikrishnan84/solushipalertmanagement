
function utils() {
}

//================================================================================================

utils.version = 0.1;

//================================================================================================

/*
utils.setFormValues = function(form, bean) {


  function setFormElementValue(formElement, value) {
    switch (formElement.type) {
    case "checkbox":
    case "check-box":
    case "radio":
      formElement.checked = (value == true);
      return;
    default:
      formElement.value = value;
      return;
    }
  }

  for (property in bean) {
    var formElement = form.elements[property];
    if(formElement) {
      var value = bean[property];
      if(formElement.length && value.length && formElement.length == value.length) {
        for(var i=0; i<formElement.length; i++) {
          setFormElementValue(formElement[i], value[i]);
        }
      } else {
        setFormElementValue(formElement, bean[property]);
      }
    }
  }
}
*/

utils.setFormValues = function(form, bean) {
  function setFormElementValue(formElement, value) {
    //alert(formElement.name + ":" + formElement.type + "=" + formElement.value);
    switch (formElement.type) {
    case "checkbox":
    case "check-box":
    case "radio":
      var elements = formElement.form.elements[formElement.name];
      if(elements.length) {
        for(var i=0; i<elements.length; i++) {
          if(elements[i].value == value) {
            elements[i].checked = true;
          }
        }
      } else {
        if(elements.value == value) {
          elements.checked = true;
        } else if (typeof value == 'boolean') {
          elements.checked = value;
        }
      }
      return;
    case "select":
    case "select-one":
      var options = formElement.options;
      if(options.length) {

        for(var i=0; i<options.length; i++) {
          if(options[i].value == value) {
            formElement.selectedIndex = i;
            break;
          }
        }
      } else {
        if(options.value == value) {
          options.selected = true;
        }
      }
      return;
    default:
      formElement.value = value;
      return;
    }
  }

  for (property in bean) {
    var formElement = form.elements[property];
    if(formElement) {
      var value = bean[property];
      //alert(property + ":" + (typeof value) + "=" + value + ":" + (value instanceof Array));
//      if(formElement instanceof Array && value instanceof Array) {
      if(formElement.length && value instanceof Array) {
        for(var i=0; i<formElement.length && i<value.length; i++) {
          setFormElementValue(formElement[i], value[i]);
        }
      } else {
        setFormElementValue(formElement, bean[property]);
      }
    }
  }
}

//================================================================================================

utils.setElementValues = function(bean) {
  for (property in bean) {
    var targetElement = document.getElementById(property);
    if(targetElement && targetElement.innerHTML) {
      targetElement.innerHTML = bean[property];
    }
  }
}

//================================================================================================

utils.addEventHandler = function(targetElement, eventName, callbackFunction) {
  if (typeof targetElement == "string") {
    targetElement = document.getElementById(targetElement);
  }

  var oldCallbackFunction = targetElement[eventName];
  if (typeof oldCallbackFunction != "function") {
    targetElement[eventName] = callbackFunction;
  } else {
    targetElement[eventName] = function(e) {
      oldCallbackFunction(e);
      callbackFunction(e);
    }
  }
}

//================================================================================================

utils.callOnWindowLoad = function(callbackFunction) {
  utils.addEventHandler(window, "onload", callbackFunction);
};

//================================================================================================

utils.callOnClick = function(targetElement, callbackFunction) {
  utils.addEventHandler(targetElement, "onclick", callbackFunction);
};

//================================================================================================

/*
utils.focusInput = function() {
    function _focusInput(tagName) {
      var elements = document.getElementsByTagName(tagName);
      for (var i=0; i<elements.length; i++) {
          elements[i].onfocus = function() {
              this.className += " focus";
          }
          elements[i].onblur = function() {
              this.className = this.className.replace(new RegExp(" focus\\b"), "");
          }
      }
    }

    _focusInput("INPUT");
    _focusInput("TEXTAREA");
    _focusInput("SELECT");
}
*/

utils.boxChecked = function() {

  function _highlightInput() {
    if (typeof event == 'undefined'){
      event = window.event;
    }

    if(event.propertyName == "checked") {
      if(this.checked) {
        this.className += " checked";
      } else {
        this.className = this.className.replace(new RegExp(" checked\\b"), "");
      }
    }
  }

  function _boxChecked(tagName) {
    var elements = document.getElementsByTagName(tagName);
    for (var i=0; i<elements.length; i++) {
      var e = elements[i];
      if(e.type == "checkbox" || e.type == "check-box" || e.type == "radio"){
        utils.addEventHandler(e, "onpropertychange", _highlightInput);
        //e.watch("checked", _highlightInput);
      }
    }
  }

  _boxChecked("INPUT");
}

//================================================================================================

/*
 
Correctly handle PNG transparency in Win IE 5.5 & 6.
http://homepage.ntlworld.com/bobosola. Updated 18-Jan-2006.

Use in <HEAD> with DEFER keyword wrapped in conditional comments:
<!--[if lt IE 7]>
<script defer type="text/javascript" src="pngfix.js"></script>
<![endif]-->

*/


utils.fixPngTransparency = function() {
  var arVersion = navigator.appVersion.split("MSIE")
  var version = parseFloat(arVersion[1])

  if ((version >= 5.5) && (document.body.filters)) 
  {
     for(var i=0; i<document.images.length; i++)
     {
        var img = document.images[i]
        var imgName = img.src.toUpperCase()
        if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
        {
           var imgID = (img.id) ? "id='" + img.id + "' " : ""
           var imgClass = (img.className) ? "class='" + img.className + "' " : ""
           var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
           var imgStyle = "display:inline-block;" + img.style.cssText 
           if (img.align == "left") imgStyle = "float:left;" + imgStyle
           if (img.align == "right") imgStyle = "float:right;" + imgStyle
           if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
           var strNewHTML = "<span " + imgID + imgClass + imgTitle
           + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
           + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
           + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
           img.outerHTML = strNewHTML
           i = i-1
        }
     }
  }
}

//================================================================================================

utils.setCheckboxes = function(name, value) {
  var elements = document.getElementsByName(name);
  for (var i=0; i<elements.length; i++) {
    var e = elements[i];
    if(e.type == "checkbox" || e.type == "check-box" || e.type == "radio"){
      e.checked = value;
      //event.propertyName = "checked";
      e.fireEvent("onpropertychange");  // maunally fire event to un/highlight checkboxes
    }
  }
}

//================================================================================================

utils.getEventTarget = function(event) {
    var target;
	if (event.target){
	    target = event.target;
	} else if (event.srcElement) {
        target = event.srcElement;
    }	 
	if (target.nodeType == 3) { // defeat Safari bug
		target = target.parentNode;
    }
    return target;
}



//================================================================================================

utils.log = function(message, overwrite) {
  /*
  var e = document.getElementById("log");
  if(e) {
    if(overwrite) {
      e.innerHTML = "<BR>" + message.replace("\n", "<BR>");
    } else {
      e.innerHTML += "<BR>" + message.replace("\n", "<BR>");
    }
  }
*/
}

//================================================================================================


//utils.callOnWindowLoad(utils.focusInput);
//utils.callOnWindowLoad(utils.boxChecked);
//utils.callOnWindowLoad(utils.fixPngTransparency);
utils.callOnWindowLoad(function(){window.focus();});

