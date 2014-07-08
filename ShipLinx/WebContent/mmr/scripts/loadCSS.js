/* Created by: Sumanth Kulkarni 
 * File : loadCSS.js
 * Created for: Loading the css file for the appropriate Client Browser 
*/

function loadCssFile(namespace)
{
	if(navigator.appName.indexOf("Internet Explorer")!= -1)
	{
		//alert('Browser is Internet Explorer');
		var headID = document.getElementsByTagName("head")[0];         
		var cssNode = document.createElement('link');
		cssNode.type = 'text/css';
		cssNode.rel = 'stylesheet';
		cssNode.href = namespace+'/mmr/styles/shiplinx_closeWindow_styles_IE.css';		
		headID.appendChild(cssNode);
	}
	else
	{
		//alert('Browser is NOT Internet Explorer');
		var headID = document.getElementsByTagName("head")[0];         
		var cssNode = document.createElement('link');
		cssNode.type = 'text/css';
		cssNode.rel = 'stylesheet';
		cssNode.href = namespace+'/mmr/styles/shiplinx_closeWindow_styles.css';			
		headID.appendChild(cssNode);
	}
}