function mOvr(src,clrOver){ 
	if (!src.contains(event.fromElement)){ 
		src.bgColor = clrOver; 
	} 
} 
function mOut(src,clrIn){ 
	if (!src.contains(event.toElement)){ 
		src.style.cursor = 'default'; 
		src.bgColor = clrIn; 
	} 
} 
function mClk(src){ 
	if(event.srcElement.tagName=='TD')
		src.children.tags('A')[0].click();
}

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function NewWindow(mypage, myname, w, h, scroll) {
var winl = (screen.width - w) / 2;
var wint = (screen.height - h) / 2;
winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars='+scroll+',resizable'
win = window.open(mypage, myname, winprops)
if (parseInt(navigator.appVersion) >= 4) { win.window.focus(); }
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function MM_callJS(jsStr) { //v2.0
  return eval(jsStr)
}

function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
function MM_popupMsg(msg) { //v1.0
  alert(msg);
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}



/*
 * DateFormat.js
 * Formats a Date object into a human-readable string
 *
 * Copyright (C) 2001 David A. Lindquist (http://www.gazingus.org)
 */

Date.MONTHS = [
  'January', 'February', 'March', 'April', 'May', 'June', 'July',
  'August', 'September', 'October', 'November', 'December'
];

Date.DAYS = [
  'Sunday', 'Monday', 'Tuesday', 'Wednesday',
  'Thursday', 'Friday', 'Saturday'
];

Date.SUFFIXES = [
  'st','nd','rd','th','th','th','th','th','th','th',
  'th','th','th','th','th','th','th','th','th','th',
  'st','nd','rd','th','th','th','th','th','th','th',
  'st'
];

Date.prototype.format = function( mask ) {
  var formatted     = ( mask != null ) ? mask : 'DD-MMM-YY';
  var letters       = 'DMYHdhmst'.split( '' );
  var temp          = new Array();
  var count         = 0;
  var regexA;
  var regexB        = /\[(\d+)\]/;

  var day           = this.getDay();
  var date          = this.getDate();
  var month         = this.getMonth();
  var year          = this.getFullYear().toString();
  var hours         = this.getHours();
  var minutes       = this.getMinutes();
  var seconds       = this.getSeconds();

  var formats       = new Object();
  formats[ 'D' ]    = date;
  formats[ 'd' ]    = date + Date.SUFFIXES[ date - 1 ];
  formats[ 'DD' ]   = ( date < 10 ) ? '0' + date : date;
  formats[ 'DDD' ]  = Date.DAYS[ day ].substring( 0, 3 );
  formats[ 'DDDD' ] = Date.DAYS[ day ];
  formats[ 'M' ]    = month + 1;
  formats[ 'MM' ]   = ( month + 1 < 10 ) ? '0' + ( month + 1 ) : month + 1;
  formats[ 'MMM' ]  = Date.MONTHS[ month ].substring( 0, 3 );
  formats[ 'MMMM' ] = Date.MONTHS[ month ];
  formats[ 'Y' ]    = ( year.charAt( 2 ) == '0' ) ? year.charAt( 3 ) : year.substring( 2, 4 );
  formats[ 'YY' ]   = year.substring( 2, 4 );
  formats[ 'YYYY' ] = year;
  formats[ 'H' ]    = hours;
  formats[ 'HH' ]   = ( hours < 10 ) ? '0' + hours : hours;  
  formats[ 'h' ]    = ( hours > 12 || hours == 0 ) ? Math.abs( hours - 12 ) : hours;
  formats[ 'hh' ]   = ( formats[ 'h' ] < 10 ) ? '0' + formats[ 'h' ] : formats[ 'h' ];
  formats[ 'm' ]    = minutes;
  formats[ 'mm' ]   = ( minutes < 10 ) ? '0' + minutes : minutes;
  formats[ 's' ]    = seconds;
  formats[ 'ss' ]   = ( seconds < 10 ) ? '0' + seconds : seconds;
  formats[ 't' ]    = ( hours < 12 ) ?  'A' : 'P';
  formats[ 'tt' ]   = ( hours < 12 ) ?  'AM' : 'PM';

  for ( var i = 0; i < letters.length; i++ ) {
    regexA = new RegExp( '(' + letters[ i ] + '+)' );
    while ( regexA.test( formatted ) ) {
      temp[ count ] = RegExp.$1;
      formatted = formatted.replace( RegExp.$1, '[' + count + ']' );
      count++;
    }
  }

  while ( regexB.test( formatted ) ) {
    formatted = formatted.replace( regexB, formats[ temp[ RegExp.$1 ] ] );
  }

  return formatted;
}
// Date script ends



/* Rotation image script START */
var currentdate = 0;
var core = 0;

function initArray() {

this.length = initArray.arguments.length;
  for (var i = 0; i < this.length; i++) {
  this[i] = initArray.arguments[i];
  }
}

image = new initArray(
"images/mainPic_01.jpg",
"images/mainPic_02.jpg",
"images/mainPic_03.jpg",
"images/mainPic_04.jpg",
"images/mainPic_05.jpg",
"images/mainPic_06.jpg",
"images/mainPic_07.jpg",
"images/mainPic_08.jpg",
"images/mainPic_09.jpg",
"images/mainPic_10.jpg",
"images/mainPic_11.jpg",
"images/mainPic_12.jpg",
"images/mainPic_13.jpg",
"images/mainPic_14.jpg",
"images/mainPic_15.jpg",
"images/mainPic_16.jpg",
"images/mainPic_17.jpg",
"images/mainPic_18.jpg",
"images/mainPic_19.jpg",
"images/mainPic_20.jpg"
);


var currentdate = new Date();
var core = currentdate.getSeconds() % image.length;
var ranimage = image[core];

/* Rotation image script  ENDS */

/*  Script for left nav menu, mouse over show image event   */
if (document.images){

var ico_off = new Image();
ico_off.src = "images/blackBullet.gif";

var ico_on = new Image();
ico_on.src = "images/redBullet.gif";
}

function rolla(imgName,imgsrc){
if (document.images)document.images[imgName].src = eval("ico"+imgsrc+".src");
}

/* script for pop box*/
function popUp(URL) {
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=750,height=680,left = 265,top = 172');");
}

/*script for date stamp*/


  function returnDate() {
    var this_month = new Array(12);
    this_month[0]  = "January";
    this_month[1]  = "February";
    this_month[2]  = "March";
    this_month[3]  = "April";
    this_month[4]  = "May";
    this_month[5]  = "June";
    this_month[6]  = "July";
    this_month[7]  = "August";
    this_month[8]  = "September";
    this_month[9]  = "October";
    this_month[10] = "November";
    this_month[11] = "December";

    var this_day = new Array(7);
    this_day[0]  = "Sunday";
    this_day[1]  = "Monday";
    this_day[2]  = "Tuesday";
    this_day[3]  = "Wednesday";
    this_day[4]  = "Thursday";
    this_day[5]  = "Friday";
    this_day[6]  = "Saturday";

    var today = new Date();
    var day   = today.getDate();
    var month = today.getMonth();
    var year  = today.getYear();
    var weekday = today.getDay();
    if (year< 1900) 
      year += 1900;
    SrcStr = (this_day[weekday]+", "+day+" "+this_month[month]+", "+year);
    document.write(SrcStr);	  
  }
  
  

/*   Script for date stamp small version */
function makeArray(n) {
this.length = n
return this
}
monthNames = new makeArray(12)
monthNames[1] = "January"
monthNames[2] = "February"
monthNames[3] = "March"
monthNames[4] = "April"
monthNames[5] = "May"
monthNames[6] = "June"
monthNames[7] = "July"
monthNames[8] = "August"
monthNames[9] = "September"
monthNames[10] = "October"
monthNames[11] = "November"
monthNames[12] = "December"
function dateString(oneDate) {
var theMonth = monthNames[oneDate.getMonth() + 1]
var theYear = oneDate.getFullYear()
return theMonth + " " + oneDate.getDate() + ", " + theYear
}


/*  Image swapping script  */

if (document.images) {
var image1 = new Image();
  image1.src = 'images/iconNo.gif';
var image2 = new Image();
  image2.src = 'images/iconYes.gif';
}
function swapImage(buttonName) {
	if (document[buttonName].src != image1.src)
	{
	   document[buttonName].src = image1.src;
	}
	else
	{
	   document[buttonName].src = image2.src;
	}
}

/*  Image swapping script  */

if (document.images) {
var image3 = new Image();
  image3.src = 'images/summaryIcon_no.png';
var image4 = new Image();
  image4.src = 'images/summaryIcon_yes.png';
}
function swapImageSummary(buttonName) {
	if (document[buttonName].src != image3.src)
	{
	   document[buttonName].src = image3.src;
	}
	else
	{
	   document[buttonName].src = image4.src;
	}
}

/*  Image swapping script  on encounter page for plus */

if (document.images) {
var image5 = new Image();
  image5.src = 'images/iconNo.gif';
var image6 = new Image();
  image6.src = 'images/icon_Minus.png';
}
function swapPlus(buttonName) {
	if (document[buttonName].src != image5.src)
	{
	   document[buttonName].src = image5.src;
	}
	else
	{
	   document[buttonName].src = image6.src;
	}
}

/*  Image swapping script  on encounter page for minus */

if (document.images) {
var image7 = new Image();
  image7.src = 'images/iconNo.gif';
var image8 = new Image();
  image8.src = 'images/icon_Plus.png';
}
function swapMinus(buttonName) {
	if (document[buttonName].src != image7.src)
	{
	   document[buttonName].src = image7.src;
	}
	else
	{
	   document[buttonName].src = image8.src;
	}
}

/* Script for pop box centered in the window  */
// Author: Eric King Url: http://redrival.com/eak/index.shtml
// This script is free to use as long as this info is left in
// Courtesy of SimplytheBest.net - http://simplythebest.net/scripts/
var win = null;
function NewWindow(mypage,myname,w,h,scroll){
LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
settings =
'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable'
win = window.open(mypage,myname,settings)
}
/* closes the popup and opens a new window in the main parent window*/
function ChangeMenu(file){
    top.opener.window.location.href = file;
	window.close();
}


/*  Script for left nav menu, mouse over show image event   */
if (document.images){

var ico_off = new Image();
ico_off.src = "images/bulletOff.gif";

var ico_on = new Image();
ico_on.src = "images/bulletOn.gif";
}

function rolla(imgName,imgsrc){
if (document.images)document.images[imgName].src = eval("ico"+imgsrc+".src");
}


/*Script for drop down tips used in encounter flowsheet */

if (document.getElementById){
document.write('<style type="text/css">\n')
document.write('.dropcontent{display:block;}\n')
document.write('</style>\n')
}

function contractall(){
if (document.getElementById){
var inc=0
while (document.getElementById("dropmsg"+inc)){
document.getElementById("dropmsg"+inc).style.display="none"
inc++
}
}
}

function expandone(){
if (document.getElementById){
var selectedItem=document.dropmsgform.dropmsgoption.selectedIndex
contractall()
document.getElementById("dropmsg"+selectedItem).style.display="block"
}
}

//if (window.addEventListener)
//window.addEventListener("load", expandone, false)
//else if (window.attachEvent)
//window.attachEvent("onload", expandone)


/*  Hightlihgt form element script ============================================  */

var highlightcolor="#ffe2a5"

var ns6=document.getElementById&&!document.all
var previous=''
var eventobj

//Regular expression to highlight only form elements
var intended=/INPUT|TEXTAREA|SELECT|OPTION/

//Function to check whether element clicked is form element
function checkel(which){
if (which.style&&intended.test(which.tagName)){
if (ns6&&eventobj.nodeType==3)
eventobj=eventobj.parentNode.parentNode
return true
}
else
return false
}

//Function to highlight form element
function highlight(e){
eventobj=ns6? e.target : event.srcElement
if (previous!=''){
if (checkel(previous))
previous.style.backgroundColor=''
previous=eventobj
if (checkel(eventobj))
eventobj.style.backgroundColor=highlightcolor
}
else{
if (checkel(eventobj))
eventobj.style.backgroundColor=highlightcolor
previous=eventobj
}
}

/*  Hightlihgt form element script ends ============================================  */


/* changing background color of specified table cells by clicking on them   */
function toggleCells(el){
rCells=document.getElementsByTagName('tr');
for (i = 0; i < rCells.length; i++)
if (rCells[i].style.backgroundColor=='khaki')
rCells[i].style.backgroundColor=''
el.style.backgroundColor='khaki'
}

/* ====================================== end  */


function currencyFormatted(amount)
{
	var i = parseFloat(amount);
	if(isNaN(i)) { i = 0.00; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if(s.indexOf('.') < 0) { s += '.00'; }
	if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
	s = minus + s;
	return s;
}
// end of function CurrencyFormatted()

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
