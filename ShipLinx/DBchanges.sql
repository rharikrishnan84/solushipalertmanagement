TO EXECUTE AS IT IS
=======================================================================================================================================================
UPDATE `menu` SET `url`='/admin/searchcustomer.action' WHERE `id`='121';

ALTER TABLE `customer` ADD COLUMN `ap_contact_name` VARCHAR(100) NULL  AFTER `default_currency` , ADD COLUMN `ap_phone` VARCHAR(20) NULL  AFTER `ap_contact_name` ;
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.customer.apcustomerName', 'Contact Name', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.customer.apPhone', 'Phone ', 'en_CA', 1);

ALTER TABLE `customer` 
CHANGE COLUMN `credit_limit` `credit_limit` DECIMAL(10,2) NULL DEFAULT '0.00' ;

ALTER TABLE `customer` 
CHANGE COLUMN `monthly_spend` `monthly_spend` DECIMAL(10,2) NULL DEFAULT '0.00' ;

ALTER TABLE `service` ADD COLUMN `max_length` BIGINT(10) NULL DEFAULT '0'  AFTER `master_service_id` , ADD COLUMN `max_width` BIGINT(10) NULL DEFAULT '0'  AFTER `max_length` , ADD COLUMN `max_height` BIGINT(10) NULL DEFAULT '0'  AFTER `max_width` , ADD COLUMN `max_weight` BIGINT(10) NULL DEFAULT '0'  AFTER `max_height` ;
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '423');

UPDATE `menu` SET `url`='/admin/manage.sales.users.action' WHERE `id`='423';
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1006');

INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('221', 'busadmin');

DELETE FROM `role_menu` WHERE `role_menu_id`='224';
DELETE FROM `role_menu` WHERE `role_menu_id`='156';

ALTER TABLE `package` 
CHANGE COLUMN `length` `length` DECIMAL(10,2) NULL DEFAULT 1.00 ,
CHANGE COLUMN `width` `width` DECIMAL(10,2) NULL DEFAULT 1.00 ,
CHANGE COLUMN `height` `height` DECIMAL(10,2) NULL DEFAULT 1.00 ,
CHANGE COLUMN `weight` `weight` DECIMAL(10,2) NULL DEFAULT 1.00 ,
CHANGE COLUMN `cod_value` `cod_value` DECIMAL(10,2) NULL DEFAULT 0.00 ,
CHANGE COLUMN `insurance_amount` `insurance_amount` DECIMAL(10,2) NULL DEFAULT 0.00 ;
ALTER TABLE `shipping_order` 
CHANGE COLUMN `dutiable_value` `dutiable_value` DECIMAL(10,2) NULL DEFAULT 0.00 ;


ALTER TABLE `charges` ADD COLUMN `carrierId` INT(10) NULL DEFAULT NULL AFTER `type`;

ALTER TABLE `charges` CHANGE COLUMN `carrierId` `carrierId` BIGINT(20) NULL DEFAULT '0' ;

ALTER TABLE `charges` ADD COLUMN `carrier_name` VARCHAR(45) NULL DEFAULT NULL AFTER `carrierId`;

ALTER TABLE `shipping_order` ADD COLUMN `quote_total` DOUBLE NULL DEFAULT NULL AFTER `billing_account`,
ADD COLUMN `quote_cost` DOUBLE NULL DEFAULT NULL AFTER `quote_total`,
ADD COLUMN `actual_total` DOUBLE NULL DEFAULT NULL AFTER `quote_cost`,
ADD COLUMN `actual_cost` DOUBLE NULL DEFAULT NULL AFTER `actual_total`;

ALTER TABLE `charges` ADD COLUMN `quote_charge_id` BIGINT(10) NOT NULL DEFAULT 0  AFTER `type` ;

ALTER TABLE `charges` 
CHANGE COLUMN `carrierId` `carrier_id` BIGINT(20) NULL DEFAULT '0' ;

ALTER TABLE `charges` 
CHANGE COLUMN `carrier_id` `carrierId` BIGINT(20) NULL DEFAULT '0' ;

UPDATE `menu` SET `url`='/admin/search.shipment.action' WHERE `name`='shipping';
ALTER TABLE `charges` ADD COLUMN `quote_charge_id` BIGINT(10) NOT NULL DEFAULT 0  AFTER `type` ;

ALTER TABLE `charges` CHANGE COLUMN `carrierId` `carrier_id` BIGINT(20) NULL DEFAULT '0' ; 

ALTER TABLE `charges` ADD COLUMN `carrier_id` BIGINT(20) NULL DEFAULT NULL AFTER `type`;

ALTER TABLE `user` 
ADD COLUMN `unit_measure` INT(5) NULL DEFAULT 0 AFTER `logo_url`;

ALTER TABLE `user_history` 
ADD COLUMN `unit_measure` INT(5) NULL DEFAULT 0 AFTER `logo_url`;

update user set unit_measure=0 where unit_measure is null

CREATE TABLE `unit_of_measure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uom_id` INT(5) NULL,
  `unit_of_measure` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `unit_of_measure` (

   `id` INT NOT NULL AUTO_INCREMENT,

   `uom_id` INT(5) NULL,

   `unit_of_measure` VARCHAR(255) NULL,

  PRIMARY KEY (`id`));
============================================================================================
INSERT INTO `unit_of_measure` (`id`, `uom_id`, `unit_of_measure`) VALUES ('1', '1', 'Imperial(Pounds/Inches)');

INSERT INTO `unit_of_measure` (`id`, `uom_id`,`unit_of_measure`) VALUES ('2', '2','Metric(Kilograms/Centimetres)');

update user set unit_measure=0 where unit_measure is null
ALTER TABLE `products` 
CHANGE COLUMN `unit_price` `unit_price` DECIMAL(10,2) NULL DEFAULT 0.00 ;

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '227');
INSERT INTO `menu` (`name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`) VALUES ('Ship', '/admin/process.shipment.action', '1', 'LEVEL_1', '1', '202', 'menu.ship');
INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('424', 'busadmin');
INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('424', 'solutions_manager');
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '424');
DELETE FROM `role_menu` WHERE `role_menu_id`='230';
============================================================================================
ALTER TABLE `user` 
ADD COLUMN `time_zone` VARCHAR(255) NULL AFTER `unit_measure`;
ALTER TABLE `charges` 
ADD COLUMN `cost_currency` VARCHAR(45) NULL DEFAULT 0 AFTER `carrier_name`,
ADD COLUMN `charge_currency` VARCHAR(45) NULL DEFAULT 0 AFTER `cost_currency`,
ADD COLUMN `exchange_rate` DECIMAL(10,2) NULL DEFAULT 0 AFTER `charge_currency`;
ALTER TABLE `user_history` 
ADD COLUMN `time_zone` VARCHAR(255) NULL DEFAULT "Canada/Eastern" AFTER `unit_measure`;

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '227');
============================================================================================
ALTER TABLE `charges` 
CHANGE COLUMN `exchange_rate` `exchange_rate` DECIMAL(10,6) NULL DEFAULT '0.000000' ;

ALTER TABLE `charges` ADD COLUMN `exchange_rate` DECIMAL(10,6) NULL DEFAULT 0 AFTER `charge_currency`;

ALTER TABLE `charges` ADD COLUMN `exchange_rate` DECIMAL(10,6) NULL DEFAULT 0 AFTER `charge_currency`;

ALTER TABLE `charges` ADD COLUMN `exchange_rate` DECIMAL(10,6) NULL DEFAULT 0 AFTER `charge_currency`;

ALTER TABLE `user` 
ADD COLUMN `time_zone` VARCHAR(255) NOT NULL  DEFAULT "Canada/Eastern" AFTER `unit_measure`;
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '80');


INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('417', 'busadmin');
DELETE FROM `role_menu` WHERE `role_menu_id`='194';
============================================================================================
ALTER TABLE `customer_sales` 
ADD COLUMN `comm_perc_CHB` DOUBLE NULL DEFAULT '0' AFTER `comm_perc_PS`;
ALTER TABLE `shipping_order` 
ADD COLUMN `slave_service_id` INT(11) NULL DEFAULT NULL AFTER `actual_cost`;
============================================================================================

UPDATE `business` SET `logout_url`='https://soluship.com/viewLogon.action' WHERE `business_id`='1';
ALTER TABLE `customer`
ADD COLUMN `show_ref` BIT NULL DEFAULT 0 AFTER `ap_phone`;

update customer set show_ref=0 where show_ref is null

=========================================================================================================================================================================
ACTION AND MENU ACTION QUERY
=================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('shipment.download', '261', 1, 'Shipment Download', 1);

(To givethe action-id which is generated for above query)

INSERT INTO role_action` (`role`, `action_id`) VALUES ('busadmin', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sales', '1007');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1007');

=========================================================================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('product.download', '406', 1, 'Product Download', 1);

(To give the action-id which is generated for above query)

INSERT INTO role_action` (`role`, `action_id`) VALUES ('busadmin', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sales', '1008');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1008');
============================================================================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('comm.download', '401', 1, 'Comm Report', 1);

(To give the action-id which is generated for above query)

INSERT INTO role_action` (`role`, `action_id`) VALUES ('busadmin', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sales', '1009');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1009');
================================================================================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('sales.download', '402', 1, 'Sales Report', 1);

(To give the action-id which is generated for above query)

INSERT INTO role_action` (`role`, `action_id`) VALUES ('busadmin', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sales', '1010');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1010');
================================================================================================

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`) VALUES ('customer.download', '121', 1, 'CustomerDownload');

(To give the action-id which is generated for above query)

INSERT INTO role_action` (`role`, `action_id`) VALUES ('busadmin', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sales', '1011');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1011');
=======================================================================================


INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('shipTo.listToProvience', '261', 1, 'To Address Provience', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1012');
==========================================================================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`) VALUES ('shipment.searchfrom', '271', 1, 'Search From Address');

(To give the action-id which is generated for above query)

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1013');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1013');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1013);
==========================================================================================
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`) VALUES ('shipment.searchto', '271', 1, 'Search To Address');

(To give the action-id which is generated for above query)

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1014');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1014');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1014');
==========================================================================================
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('helpMenu', '108', 1, 'help', 1);
 
 (To give the action-id which is generated for above query)

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('admin', '1015');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1015');
==========================================================================================

================================================================================================================================================================
//INTEGRATED CODE(TO EXECUTE AS IT IS)
=======================================
ALTER TABLE `service` ADD COLUMN `email_type` VARCHAR(5) NOT NULL DEFAULT 'SPD'  AFTER `max_weight` ;

update resourcebundle set msg_content='

 <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Thank you for using Inegrated Carriers SoluShip&trade;</title>
  <style type="text/css">
  <!--
  body {
  font: 100% Verdana, Arial, Helvetica, sans-serif;
  background: #fff;
  margin: 0;
  padding: 0;
  text-align: center;
  color: #000000;
  }
  .oneColElsCtr #container {
  width: 550px;
  background: #FFFFFF;
  margin: 0 auto;
  border: 1px solid #000000;
  text-align: left;
  height:auto;
  }
  .oneColElsCtr #mainContent {
  padding: 10px 10px 10px 10px;
  font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
  font-size: 12px;
  }
  .oneColElsCtr #mainContent h1 {
  padding: 5px 0px 0px 0px;
  font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
  font-size: 18px;
  color:#990000;
  }
  .oneColElsCtr #mainFooter {
  padding: 5px 0px 0px 10px;
  font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
  font-size: 10px;
  color:#fff;
  background-color:#000000;
  height:35px;
  }
  #mainContent p{
  text-align: left;
  margin-left: 30px;
  }
  ul{
  text-align: left;  }
  -->
  </style>
  </head>
  
  <body class="oneColElsCtr">
  
  <div id="container">

  <img src="http://www.soluship.com/mmr/images/ic-header.jpg" includeContext="true" />
    <div id="mainContent">
      <h1>Thank you for using Integrated Carriers SoluShip&trade;</h1>
      <p>Dear Customer,</p>
      <p>Thank you for your inquiry. We are pleased to present you this quote based on the shipping details below.</p>
      <p>-------------------------------------------------------</p>
      <p>
  <b>Ship From:</b><br/>
  City: %SFROMCITY<br/>
  Zip/Postal Code: %SFROMZIP<br/>
  Province: %SFROMPROVINCE<br/>
  Country: %SFROMCOUNTRY<br/>
      </p>
      <p>-------------------------------------------------------</p>
      <p>
  <b>Ship To:</b><br/>
  City: %STOCITY<br/>
  Zip/Postal Code: %STOZIP<br/>
  Province: %STOPROVINCE<br/>
  Country: %STOCOUNTRY<br/>
      </p>
      <p>-------------------------------------------------------</p>
      <p>Carrier and Service: %CARRIERSERVICE</p>
      <p>-------------------------------------------------------</p>
      <p>Transit Time: %TRANSITDAYS</p>
      <p>-------------------------------------------------------</p>
      <p>Total Weight: %TOTALWEIGHT</p>
      <p>-------------------------------------------------------</p>
      <p>Total Charges: %TOTALCHARGES</p>
      <p>%CHARGEDETAILS</p>
      <p>-------------------------------------------------------</p>
      <p>Package Details:</p>
      <p>%PACKAGEDETAILS</p>
      <p>-------------------------------------------------------</p>
  <p>%ADDITIONALSERVICESTITLE</p>
      <p>%ADDITIONALSERVICES</p>
      <p>%ENDLINE</p>
  <br/>
  
      <p>If you have any questions in regards to this quote please contact us at 416-603-0103 ext %VALUE
  or by email at %MAILADDRESS</p>
      <p>Thank you and enjoy Integrated Carriers SoluShip&trade;.</p><br/>
  <!-- end #mainContent -->
      </div>
      <div id="mainFooter">
     &copy; 2012 Integrated Carriers
      <!-- end #mainFooter -->
      </div>
  <!-- end #container -->
  </div>
  </body>
  </html>' where msg_id='message.send.rates.notification.body';
  
 ======================================================================================
    INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.subject.cancel.shipment.notification', 'Customer Shipment Cancellation Notice', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('mail.cancel.shipment.notification.body', '<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <title>Integrated Carriers SoluShip&trade;</title>
 <style type="text/css">

 <!--
 body {
 	font: 100% Verdana, Arial, Helvetica, sans-serif;
 	background: #fff;
 	margin: 0;
 	padding: 0;
 	text-align: center;
 	color: #000000;
 }
 .oneColElsCtr #container {
 	width: 550px;
 	background: #FFFFFF;
 	margin: 0 auto;
 	border: 1px solid #000000;
 	text-align: left;
 	height:auto;
 }
 .oneColElsCtr #mainContent {
 	padding: 10px 10px 10px 10px;
 	font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 	font-size: 12px;
 }
 .oneColElsCtr #mainContent h1 {
 	padding: 5px 0px 0px 0px;
 	font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 	font-size: 18px;
 	color:#990000;
 }
 .oneColElsCtr #mainFooter {
 	padding: 5px 0px 0px 10px;
 	font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 	font-size: 10px;
 	color:#fff;
 	background-color:#000000;
 	height:35px;
 }
 #mainContent p{
 text-align: left;
 margin-left: 30px;
 }
 ul{
 text-align: left;
 }
 -->
 </style>
 </head>
 
 <body class="oneColElsCtr"> 
 <div id="container">
 <img src="http://www.soluship.com/mmr/images/ic-header.jpg" includeContext="true" />
   <div id="mainContent">
     <h1>Integrated Carriers SoluShip&trade;</h1>
	 <p>A shipment has been cancelled. Details are as follows.</p>
     <p>This e-mail confirms receipt of your shipment request for %ShipDate.</p>
     <p>
 	<b>Ship From:</b><br/>
 	Company: %SFROMCOMPANY<br/>
 	Address: %SFROMADDRESS1, %SFROMADDRESS2%<br/>
 	City: %SFROMCITY<br/>
 	Zip/Postal Code: %SFROMZIP<br/>
 	Province: %SFROMPROVINCE<br/>
 	Country: %SFROMCOUNTRY<br/>
     </p>
     <p>
 	<b>Ship To:</b><br/>
 	Company: %STOCOMPANY<br/>
 	Address: %STOADDRESS1, %STOADDRESS2%<br/>
 	City: %STOCITY<br/>
 	Zip/Postal Code: %STOZIP<br/>
 	Province: %STOPROVINCE<br/>
 	Country: %STOCOUNTRY<br/>
     </p>
     <p><b>Carrier and Service:</b> %CARRIERSERVICE</p>
 	<p><b>Total Pieces:</b> %TOTALPIECES<br/>
     <b>Total Weight:</b> %TOTALWEIGHT</p>
     <p><b>Tracking Details:</b>%TRACKINGURL</br>
 	</p>
 	<p>Did you know you can create and track shipments and get estimates and transit times online at <a href="https://www.integratedcarriers.com/?page_id=143">www.integratedcarriers.com?</a> Visit our website today and learn how our online services can benefit you.</br> 
 	</p>
 	<p>Thank you for choosing Integrated Carriers.</p>  
 	
 	<br/>
 
 	<!-- end #mainContent -->
     </div>
     <div id="mainFooter">
    	&copy; 2013 Integrated Carriers
     <!-- end #mainFooter -->
     </div>
 <!-- end #container -->
 </div>
 </body>
 </html>', 'en_CA', 0);
 ===========================================================================================================================================================
 Resourcebundle Query
 =====================================
 INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.shipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to saveltl@integratedcarriers.com', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.shipment.notification.mail.failure', 'Cancelled Shipment Notification mail could not be sent', 'en_CA', 0);

UPDATE `resourcebundle` SET `msg_content`='Customer Shipment Notification BOL#: %ORDERID' WHERE `msg_id`='label.shipment.notification';

UPDATE `resourcebundle` SET `msg_id`='cancel.ltlshipment.notification.mail.success' WHERE `msg_id`='cancel.shipment.notification.mail.success' and`locale`='en_CA' and`is_fmk`=0;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.spdshipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to save@integratedcarriers.com', 'en_CA', 0);

UPDATE `resourcebundle` SET `msg_content`='Postal/Zip Code' WHERE `msg_id`='label.shippingOrder.zip' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Province/State' WHERE `msg_id`='label.shippingOrder.state' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Customer Shipment Notification BOL#: %ORDERID' WHERE `msg_id`='label.shipment.notification'; 

UPDATE `resourcebundle` SET `msg_id`='cancel.ltlshipment.notification.mail.success' WHERE  `msg_id`='cancel.shipment.notification.mail.success' and`locale`='en_CA' and`is_fmk`=0;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.spdshipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to save@integratedcarriers.com', 'en_CA', 0);
UPDATE `resourcebundle` SET `msg_id`='cancel.ltlshipment.notification.mail.success' WHERE `msg_id`='cancel.shipment.notification.mail.success' and`locale`='en_CA' and`is_fmk`=0;
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.ltlshipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to saveltl@integratedcarriers.com', 'en_CA', 0);

 UPDATE `resourcebundle` SET `msg_content`='Address 1' WHERE `msg_id`='label.shippingOrder.address1' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Address 2' WHERE `msg_id`='label.shippingOrder.address2' and`locale`='en_CA' and`is_fmk`=1;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('error.servicepackage.maxrange', '%CARRIER : No rates available based on packaging details. Please click request quote to verify service availability and rates.', 'en_CA', 1)

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('error.service.zones.notallowed', 'Some of the services for %CARRIER are not  available for the selected zone.', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('error.service.rate.notallowed', 'Some of the rates for %CARRIER are not  available for the selected zone.', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('error.servicepackage.maxrange', '%CARRIER : No rates available based on packaging details. Please click request quote to verify service availability and rates.', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('error.servicepackage.maxrange', '%CARRIER : No rates available based on packaging details. Please click request quote to verify service availability and rates.', 'en_CA', 1);
UPDATE `resourcebundle` SET `msg_content`='Address 1' WHERE `resourcebundle_id`='1110' and`msg_id`='label.shippingOrder.address1' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Address 2' WHERE `resourcebundle_id`='1111' and`msg_id`='label.shippingOrder.address2' and`locale`='en_CA' and`is_fmk`=1;
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.locale', 'Locale', 'en_CA', 1);
UPDATE `resourcebundle` SET `msg_content`='Address 1' WHERE `msg_id`='label.shippingOrder.address1' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Address 2' WHERE `msg_id`='label.shippingOrder.address2' and`locale`='en_CA' and`is_fmk`=1;


insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.shipping','READY FOR SHIPPING','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.transit','IN TRANSIT','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.delivered','DELIVERD','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.cancelled','CANCELLED','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.process','READY TO PROCESS','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.dispatched','TO BE DISPATCHED','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.pickup','OUT FOR PICKUP','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.dropped','TRAILER DROPPED','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.board','ON BOARD','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.depart','SCHEDULED TO DEPART','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.route','ON ROUTE','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.appointment','BOOKING APPOINTMENT','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.delivery','OUT FOR DELIVERY','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.pending','PENDING','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.customs','IN CUSTOMS','en_CA',1);
insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.shipping.status.name.terminal','IN TERMINAL','en_CA',1);

DELETE FROM `role_menu` WHERE `role_menu_id`='193';

INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('417', 'busadmin');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busamin', '411');

UPDATE `menu` SET `url`='/admin/updateAR.action' WHERE `id`='184';

UPDATE `menu` SET `url`='/admin/searchAR.action' WHERE `id`='185';

update action set menu_id=405 where action='addnewproduct'
update action set menu_id=405 where action='updateproduct'
update action set menu_id=405 where action='editproduct'

INSERT INTO `property` (`property_id`,`scope`, `name`, `value`) VALUES ('34','SYSTEM', 'English', '/home/soluship/help/English');
INSERT INTO `property` (`property_id`, `scope`, `name`, `value`) VALUES ('35', 'SYSTEM', 'Francais', '/home/soluship/help/Francais');

ALTER TABLE `charges` 
CHANGE COLUMN `cost_currency` `cost_currency` INT(11) NULL DEFAULT '0' ,
CHANGE COLUMN `charge_currency` `charge_currency` INT(11) NULL DEFAULT '0' ;
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.address', 'Addresses', 'en_CA', 1);
UPDATE `action` SET `menu_id`='403' WHERE `id`='406';
UPDATE `resourcebundle` SET `msg_content`='Addresses' WHERE `resourcebundle_id`='1713' and`msg_id`='label.address' and`locale`='en_CA' and`is_fmk`=1;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.markup.variable', 'variable', 'en_CA', 1, NULL);

ALTER TABLE `customer_markup` 
ADD COLUMN `variable` INT NULL DEFAULT '0' AFTER `to_weight`;
insert into pins values('8', 'CONFIRMATION_NUM', '1001', '9999999', '1001', '1', NULL);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1017');


INSERT INTO `menu` (`id`,`name`,`url`,`display_order`,`level`,`level_no`,`parent_id`,`label_id`,`image`,`image_over`,`help_tag`,`support_tag`) VALUES
 ('427', 'delete address', '/delete.actual.charge.editinvoice.action', '2', 'LEVEL_2', '1', '0', 'temp', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`,`reload_safe`,`id`) VALUES ('delete.actual.charge.editinvoice', 427, 1, 'delete charges in edit invoice',1,'1017');

alter table resourcebundle modify msg_content nvarchar(10000);

ALTER TABLE `charges` 
ADD COLUMN `charge_group_id` INT(11) NULL DEFAULT 0 AFTER `exchange_rate`;
//////////////////////// -----------------   27-08-2014 ------------------------------------------------------------------////////////////
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '261');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '270');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '282');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '271');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '488');


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '1013');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '1014');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '503');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '1004');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '1005');


INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('261', 'solutions_manager');
INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('270', 'solutions_manager');

INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('271', 'solutions_manager');
INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('416', 'solutions_manager');

ALTER TABLE `user_history` 
ADD COLUMN `comm_perc_CHB` DOUBLE NULL DEFAULT '0' AFTER `commission_percentage`;

ALTER TABLE `user` 
ADD COLUMN `comm_perc_CHB` DOUBLE NULL DEFAULT '0' AFTER `commission_percentage`;


ALTER TABLE `user` 
DROP COLUMN `comm_perc_CHB`,
CHANGE COLUMN `commission_percentage` `commission_perc_PS` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `commission_perc_PS` `commission_perc_CHB` DOUBLE NULL DEFAULT '0' ;

ALTER TABLE `user_history` 
DROP COLUMN `comm_perc_CHB`,
CHANGE COLUMN `commission_percentage` `commission_perc_PS` DOUBLE NULL DEFAULT '0' ,
CHANGE COLUMN `commission_perc_PS` `commission_perc_CHB` DOUBLE NULL DEFAULT '0' ;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('text.role.solutions.manager', 'Solutions Manager', 'en_CA', 1);

///////////////////---------------------------------------End ----------------------------------------------------------------////////////////

/// ====================================Stored Procedure=========================================================///

DELIMITER $$
CREATE PROCEDURE `update_quote_total`()
begin
set @count=(select  count(distinct s.order_id) from shipping_order s left join charges c on (s.order_id=c.order_id)
 where s.quote_total is null and c.type=0 );
while @count >0 do
set @orderId=(select  distinct s.order_id from shipping_order s left join charges c on (s.order_id=c.order_id)
 where s.scheduled_ship_date>='2014-05-15' and s.quote_total is null and c.type=0 limit 1);
update shipping_order set quote_total=(select sum(charge) from charges where type=0 and order_id=@orderId) where order_id=@orderId;
set @count=@count-1;
end while;
END $$
DELIMITER ;


////============================END Stored Procedure =============================================================///



///======================= Start Total Quote Charge Trigger ====================================================////

 DELIMITER $$
create Trigger total_quoted_charges
after insert on charges
for each row
begin 
set @orderId=(select order_id from charges order by id desc limit 1);
update shipping_order set quote_total=(select sum(charge) from charges where type=0 and order_id=@orderId) where order_id=@orderId;
end $$
DELIMITER ;

///===================End Total Quote Charge Trigger ===========================================================////

 
 UPDATE `action` SET `menu_id`='181' WHERE `id`='186';

INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('20', 'TAX', 'HST', 'HST', 'Harmonized Sales Tax', '5', '0', '0', '0');

///======================= Start Total Quote Charge Trigger ====================================================////

DELIMITER $$
create Trigger total_quoted_charges_cost
after insert on charges
for each row
begin 
set @orderId=(select order_id from charges order by id desc limit 1);
update shipping_order set quote_total=(select sum(charge) from charges where type=0 and order_id=@orderId) where order_id=@orderId;
update shipping_order set quote_cost=(select sum(cost) from charges where type=0 and order_id=@orderId) where order_id=@orderId;
end $$
DELIMITER ;

///===================End Total Quote Charge Trigger ===========================================================////

////////////////////////---------------------- 12/9/14------------------------------///////////////////

insert into resourcebundle (`msg_id`,`msg_content`,`locale`,`is_fmk`) values ('label.live.button',
'<div id="swifttagcontainer8xy43136qw"><div id="proactivechatcontainer8xy43136qw"></div><div style="display: inline;" id="swifttagdatacontainer8xy43136qw"></div></div> <script type="text/javascript">var swiftscriptelem8xy43136qw=document.createElement("script");swiftscriptelem8xy43136qw.type="text/javascript";var swiftrandom = Math.floor(Math.random()*1001); var swiftuniqueid = "8xy43136qw"; var swifttagurl8xy43136qw="https://soluship.com/kayako/visitor/index.php?/Default/LiveChat/HTML/SiteBadge/cHJvbXB0dHlwZT1jaGF0JnVuaXF1ZWlkPTh4eTQzMTM2cXcmdmVyc2lvbj00LjY0LjEuNDgyNyZwcm9kdWN0PUVuZ2FnZSZzaXRlYmFkZ2Vjb2xvcj13aGl0ZSZiYWRnZWxhbmd1YWdlPWVuJmJhZGdldGV4dD1saXZlaGVscCZvbmxpbmVjb2xvcj0jMTk4YzE5Jm9ubGluZWNvbG9yaG92ZXI9IzVmYWY1ZiZvbmxpbmVjb2xvcmJvcmRlcj0jMTI2MjEyJm9mZmxpbmVjb2xvcj0jYTJhNGFjJm9mZmxpbmVjb2xvcmhvdmVyPSNiZWMwYzUmb2ZmbGluZWNvbG9yYm9yZGVyPSM3MTczNzgmYXdheWNvbG9yPSM3MzdjNGEmYXdheWNvbG9yaG92ZXI9IzllYTQ4MSZhd2F5Y29sb3Jib3JkZXI9IzUxNTczNCZiYWNrc2hvcnRseWNvbG9yPSM3ODhhMjMmYmFja3Nob3J0bHljb2xvcmhvdmVyPSNhMWFlNjYmYmFja3Nob3J0bHljb2xvcmJvcmRlcj0jNTQ2MTE5JmN1c3RvbW9ubGluZT0mY3VzdG9tb2ZmbGluZT0mY3VzdG9tYXdheT0mY3VzdG9tYmFja3Nob3J0bHk9CmU2NTQxZjc0MGQwMzhjNzU4NjBjMmU5Y2EzOTdkYjcxMWQ0YWM2ZmU=";setTimeout("swiftscriptelem8xy43136qw.src=swifttagurl8xy43136qw;document.getElementById("swifttagcontainer8xy43136qw").appendChild(swiftscriptelem8xy43136qw);",1);</script>',
'en_CA',1);
UPDATE `resourcebundle` SET `msg_content`='<div id=\"swifttagcontainer8xy43136qw\"><div id=\"proactivechatcontainer8xy43136qw\"></div><div style=\"display: inline;\" id=\"swifttagdatacontainer8xy43136qw\"></div></div> <script type=\"text/javascript\">var swiftscriptelem8xy43136qw=document.createElement(\"script\");swiftscriptelem8xy43136qw.type=\"text/javascript\";var swiftrandom = Math.floor(Math.random()*1001); var swiftuniqueid = \"8xy43136qw\"; var swifttagurl8xy43136qw=\"https://soluship.com/kayako/visitor/index.php?/Default/LiveChat/HTML/SiteBadge/cHJvbXB0dHlwZT1jaGF0JnVuaXF1ZWlkPTh4eTQzMTM2cXcmdmVyc2lvbj00LjY0LjEuNDgyNyZwcm9kdWN0PUVuZ2FnZSZzaXRlYmFkZ2Vjb2xvcj13aGl0ZSZiYWRnZWxhbmd1YWdlPWVuJmJhZGdldGV4dD1saXZlaGVscCZvbmxpbmVjb2xvcj0jMTk4YzE5Jm9ubGluZWNvbG9yaG92ZXI9IzVmYWY1ZiZvbmxpbmVjb2xvcmJvcmRlcj0jMTI2MjEyJm9mZmxpbmVjb2xvcj0jYTJhNGFjJm9mZmxpbmVjb2xvcmhvdmVyPSNiZWMwYzUmb2ZmbGluZWNvbG9yYm9yZGVyPSM3MTczNzgmYXdheWNvbG9yPSM3MzdjNGEmYXdheWNvbG9yaG92ZXI9IzllYTQ4MSZhd2F5Y29sb3Jib3JkZXI9IzUxNTczNCZiYWNrc2hvcnRseWNvbG9yPSM3ODhhMjMmYmFja3Nob3J0bHljb2xvcmhvdmVyPSNhMWFlNjYmYmFja3Nob3J0bHljb2xvcmJvcmRlcj0jNTQ2MTE5JmN1c3RvbW9ubGluZT0mY3VzdG9tb2ZmbGluZT0mY3VzdG9tYXdheT0mY3VzdG9tYmFja3Nob3J0bHk9CmU2NTQxZjc0MGQwMzhjNzU4NjBjMmU5Y2EzOTdkYjcxMWQ0YWM2ZmU=\";setTimeout(\"swiftscriptelem8xy43136qw.src=swifttagurl8xy43136qw;document.getElementById('swifttagcontainer8xy43136qw').appendChild(swiftscriptelem8xy43136qw);\",1);</script>' WHERE `resourcebundle_id`='1718' and`msg_id`='label.live.button' and`locale`='en_CA' and`is_fmk`=1;

UPDATE `resourcebundle` SET `msg_content`='Card Type' WHERE `resourcebundle_id`='674' and`msg_id`='label.creditcard.nameOnCard' and`locale`='en_CA' and`is_fmk`=0;

////////////////////////---------------------- end------------------------------///////////////////

///////////////////////------------------------ 17/09/14-----------------------///////////////////

ALTER TABLE `ltl_skid_rate` 
ADD COLUMN `account_num` VARCHAR(45) NULL DEFAULT NULL AFTER `service_id`;

ALTER TABLE `ltl_pound_rate` 
ADD COLUMN `account_num` VARCHAR(45) NULL DEFAULT NULL AFTER `service_id`;

ALTER TABLE `shipping_order` 
ADD COLUMN `account_num` VARCHAR(45) NULL DEFAULT NULL AFTER `slave_service_id`;

////////////////////////---------------------- end------------------------------///////////////////

CREATE TABLE `commission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL,
  `customer_name` VARCHAR(255) NULL,
  `invoice_id` INT NULL,
	`invoice_num` INT NULL,
  `user_id` INT NULL,
  `sales_user` VARCHAR(255) NULL,
  `invoice_total` DOUBLE(10,2) NULL DEFAULT 0,
  `cost_total` DOUBLE(10,2) NULL DEFAULT 0,
  `commission_payable` DOUBLE(10,2) NULL DEFAULT 0,
  `customer_paid` INT NULL,
  `rep_paid` INT NULL,
  `notes` VARCHAR(255) NULL,
  `date_created` DATETIME NULL,
  `total_spd` DOUBLE(10,2) NULL DEFAULT 0,
  `total_ltl` DOUBLE(10,2) NULL DEFAULT 0,
  `total_chb` DOUBLE(10,2) NULL DEFAULT 0,
  PRIMARY KEY (`id`));

alter table shipping_order add column signature_image MEDIUMBLOB DEFAULT NULL;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.shipping.status.exception', 'Exception', 'en_CA', 1);

INSERT INTO `order_billing_status` (`billing_status_id`, `billing_status_name`) VALUES ('80', 'Exception');

ALTER TABLE `business` 
ADD COLUMN  `US_address_id` INT NULL DEFAULT NULL AFTER `address_id`;

INSERT INTO `address` (`abbreviation_name`, `address1`, `city`, `phone_no`, `email_address`, `contact_name`, `postal_code`, `url`, `residential`, `country_code`, `province_code`, `customer_id`, `default_from`, `send_notification`, `broker_code`) VALUES ('Integrated Carriers', '1111 Harvester Road', 'West Chicago', '', '', 'Jay Cook', '60185', 'http://www.integratedcarriers.com', 1, 'US', 'IL', '0', '', 0, 'CN');
////////////////////////---------------------- 11/10/14------------------------------///////////////////


ALTER TABLE `charges` ADD COLUMN `service` VARCHAR(255) NULL AFTER `charge_group_id`;

insert into resourcebundle values('message.send.salesrep.addcustomer.notification.body','<html>
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   <title>Thank you for using Inegrated Carriers SoluShip&trade;</title>
   <style type="text/css">
   <!--
   body {
   font: 100% Verdana, Arial, Helvetica, sans-serif;
   background: #fff;
  margin: 0;
   padding: 0;
   text-align: center;
  color: #000000;
   }
   .oneColElsCtr #container {
   width: 550px;
   background: #FFFFFF;
   margin: 0 auto;
   border: 1px solid #000000;
   text-align: left;
   height:auto;
   }
   .oneColElsCtr #mainContent {
   padding: 10px 10px 10px 10px;
   font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
   font-size: 12px;
   }
   .oneColElsCtr #mainContent h1 {
   padding: 5px 0px 0px 0px;
   font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
   font-size: 18px;
   color:#990000;
   }
   .oneColElsCtr #mainFooter {
   padding: 5px 0px 0px 10px;
   font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
   font-size: 10px;
   color:#fff;
   background-color:#000000;
   height:35px;
   }
   #mainContent p{
   text-align: left;
   margin-left: 30px;
+   }
   ul{
   text-align: left;
   }
   -->
   </style>
   </head>
   
   <body class="oneColElsCtr">
   
   <div id="container">
   <img src="http://www.soluship.com/mmr/images/ic-header.jpg" includeContext="true" />
     <div id="mainContent">
       <h1>Thank you for using Integrated Carriers SoluShip&trade;</h1>
       <p>To all concerned,</p>
       <p>Please note that the follow customer has been sucessfully added to SoluShip</p>
       <p>-------------------------------------------------------</p>
       <p>
   <b>New Account:</b><br/>
	  Customer Name: %CUSTOMERNAME <br/>
      Account#: %ACCOUNT<br/>
      Contact Name: %CONTACTNAME<br/>
      Sales Rep Name: %SALESREP<br/>
      Address1: %ADDRESS1<br/>
      City: %CITY<br/>
      Zip/Postal Code: %ZIP<br/>
      Province: %PROVINCE<br/>
      Country: %COUNTRY<br/>
       </p>
       <p>-------------------------------------------------------</p>
  
       <p>Thank you and enjoy Integrated Carriers SoluShip&trade;.</p><br/>
   <!-- end #mainContent -->
       </div>
      <div id="mainFooter">
      &copy; 2012 Integrated Carriers
       <!-- end #mainFooter -->
       </div>
   <!-- end #container -->
   </div>
   </body>
   </html>','en_CA',1,NULL);

ALTER TABLE `invoice` 
ADD COLUMN `commission_calculated` BIT NULL DEFAULT 0 AFTER `sales_username`;


ALTER TABLE `shipping_order` 
CHANGE COLUMN `quote_total` `quote_total` DOUBLE(10,2) NULL DEFAULT '0' ,
CHANGE COLUMN `quote_cost` `quote_cost` DOUBLE(10,2) NULL DEFAULT '0' ,
CHANGE COLUMN `actual_total` `actual_total` DOUBLE(10,2) NULL DEFAULT '0' ,
CHANGE COLUMN `actual_cost` `actual_cost` DOUBLE(10,2) NULL DEFAULT '0' ;

/*-------------- stored procedure for commission---------------------*/

DELIMITER $$

CREATE PROCEDURE `insert_commission_amount`()
begin
declare ic int;
declare sc int;
declare cc int;
set @invoiceCount = (select count(distinct i.invoice_id) from invoice i left join invoice_charges ic on (i.invoice_id=ic.invoice_id)
 where i.commission_calculated=0 and ic.cancelled_invoice='No' and i.invoice_date between '2014-10-10' and '2014-10-12');

while @invoiceCount >0 do   
set ic= @invoiceCount;	
set @invoiceId=(SELECT invoice_id 
FROM (SELECT distinct i.* 
      FROM invoice i left join invoice_charges ic on (i.invoice_id=ic.invoice_id)
      where i.commission_calculated=0 and ic.cancelled_invoice='No' and 
      i.invoice_date between '2014-10-10' and '2014-10-12'
      ORDER BY i.invoice_id 
      DESC LIMIT ic) AS TOP_invoiceId
      ORDER BY invoice_id ASC 
      LIMIT 1);

set @invoiceNumber = (select invoice_num from invoice where invoice_id=@invoiceId);
set @dateCreated = (select invoice_date from invoice where invoice_id=@invoiceId);
set @paymentStatus = (select payment_status from invoice where invoice_id=@invoiceId);
set @invoiceAmount=(select invoice_amount from invoice where invoice_id=@invoiceId);
set @invoiceCost=(select invoice_cost from invoice where invoice_id=@invoiceId);
set @customerId=(select customer_id from invoice where invoice_id=@invoiceId);
set @customerName=(SELECT name FROM customer  where customer_id=@customerId);
set @salesUserCount = (select count(id) from customer_sales where customer_id=@customerId);

while @salesUserCount >0 do
set sc=@salesUserCount;
set @salesUserId=(SELECT distinct id 
FROM (SELECT * 
      FROM customer_sales  where  customer_id=@customerId
      ORDER BY id 
      DESC LIMIT sc) AS TOP_salesUserId
ORDER BY id ASC 
LIMIT 1);
set @salesUserName = (select sales_user from customer_sales where id=@salesUserId);
set @commissionAmountValue =0;
set @commissionCharge =0;
set @totalSPD=0;
set @totalLTL=0;
set @totalCHB=0;
set @chargeCount = (select count(charge_id) from invoice_charges where invoice_id=@invoiceId);

while @chargeCount >0 do
set cc=@chargeCount;
set @chargeId=(SELECT distinct charge_id 
FROM (SELECT * 
      FROM invoice_charges  where  invoice_id=@invoiceId
      ORDER BY charge_id 
      DESC LIMIT cc) AS TOP_chargeId
ORDER BY charge_id ASC 
LIMIT 1);
set @chargeAmount=(select charge from charges where id=@chargeId);
set @costAmount=(select cost from charges where id=@chargeId);
set @orderId=(select order_id from charges where id=@chargeId);
set @serviceType = (select email_type from service where service_id=(select service_id from shipping_order where order_id=@orderId));
set @chargeCode = (select charge_code from charges where id=@chargeId);
set @isTax=(select if(strcmp(@chargeCode,'TAX'),'0','1') from charge_group limit 1);
set @temp = (select is_tax from charge_group where group_code=@chargeCode);
set @taxTemp=(select if(strcmp('',@temp),@temp,'0') from charge_group limit 1);
set @isTaxValue=(select if(strcmp(@isTax,'1'),@taxTemp,'1')from charge_group limit 1);

if (@isTaxValue = 0 or @isTaxValue is null) then
set @commissionPercentage = (select if(strcmp(@serviceType,'SPD'),(select if(strcmp(@serviceType,'LTL'),(select comm_perc_CHB from customer_sales where customer_id=@customerId and id=@salesUserId), (select comm_perc_PP from customer_sales where customer_id=@customerId and id=@salesUserId)) from customer_sales where customer_id=@customerId and id=@salesUserId), (select comm_perc_PS from customer_sales where customer_id=@customerId and id=@salesUserId)) as commissionPercentage from customer_sales where customer_id=@customerId and id=@salesUserId);

set @totalSPD=@totalSPD+(select if(strcmp(@serviceType,'SPD'),0.0,((charge-cost)*@commissionPercentage/100)) from charges where id=@chargeId);

set @totalLTL=@totalLTL+(select if(strcmp(@serviceType,'LTL'),0.0,((charge-cost)*@commissionPercentage/100)) from charges where id=@chargeId);


set @totalCHB=@totalCHB+(select if(strcmp(@serviceType,'CHB'),0.0,((charge-cost)*@commissionPercentage/100)) from charges where id=@chargeId);

set @commissionAmountValue = @commissionAmountValue+ ((@chargeAmount - @costAmount)*@commissionPercentage/100);
end if;
set @chargeCount=@chargeCount-1; 
end while;


 set @commissionCharge = @commissionCharge+@commissionAmountValue;
insert into commission(`customer_id`,`customer_name`,`invoice_id`,`invoice_num`,`user_id`,`sales_user`,`invoice_total`,`cost_total`,`commission_payable`,`customer_paid`,`rep_paid`,`date_created`,`total_spd`,`total_ltl`,`total_chb`)
values(@customerId,@customerName,@invoiceId,@invoiceNumber,@salesUserId,@salesUserName,@invoiceAmount,@invoiceCost,@commissionCharge,@paymentStatus,@paymentStatus,@dateCreated,@totalSPD,@totalLTL,@totalCHB);

set @salesUserCount=@salesUserCount -1; 
end while;

update invoice set commission_calculated=true where invoice_id=@invoiceId;
set @invoiceCount=@invoiceCount-1; 
end while; 
END $$
DELIMITER ;

/*-------------- end ---------------------*/



/*----------------------------------------------- Query for issue 472(28/10/14)------------------*/
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customer.list', 'Customer List', 'en_CA', 0, '');


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.action', 'ACTION', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.markup', 'MARKUP', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.edit', 'EDIT', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.user', 'USER', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.carriersetup', 'CARRIER SETUP', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.emailcustomer', 'EMAIL CUSTOMER', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.salesuser', 'SALES USER', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.name', 'NAME', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.account', 'Account', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.address', 'Address', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.phone', 'Phone', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.email', 'Email', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.status', 'Status', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.switch.quotemode', 'SWITCH TO QUOTE MODE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.search.referenceheading', '( REFERENCE AVAILABLE ONLY FROM PAST SHIPMENTS OR IMPORTED SHIPMENTS WITHIN THIS SYSTEM )', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.shippingOrder.instructions', 'Instructions', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.servicetype', 'Service Type', 'en_CA', 0, '');  

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.unitmeasure', 'Unit Of Measure', 'en_CA', 0, ''); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.packagedetails', 'Package Details', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.numofpackages', 'Number of Packages', 'en_CA', 0, ''); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.choosepackage', 'Choose a package type', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.choosepacktype', 'Choose a package type', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.length', 'Length', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.width', 'Width', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.height', 'Height', 'en_CA', 0, '');


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.cod', 'COD', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.insurance', 'Insurance', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.freightclass', 'Freight Class', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.type', 'Type', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.description', 'Description', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.allsame', 'All same', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.package.allabove', 'All above', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.rateheading', '(PLEASE SELECT A METHOD TO CREATE A SHIPMENT WITHOUT FIRST GETTING RATES)', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.save.shipment', 'Save Shipment', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.getrates', 'GET RATES', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.clear', 'CLEAR', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.header.productinfo', 'Product Information', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.add', 'ADD', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.delete', 'DELETE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.shipnow', 'SHIP NOW', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.email', 'EMAIL', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.estimatedratelist', 'Estimated Rates and Transit Times', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.carrier', 'Carrier', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.service', 'Service', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.transitdays', 'Transit Days', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.billto', 'Bill To', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.totalcost', 'Total Cost', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.totalprice', 'Total Price', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.billwt', 'Bill Wt', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.track.fromdate', 'From Date', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.track.todate', 'To Date', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.track.tracking', 'Tracking', 'en_CA', 0, ''); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.edi.batchid', 'Batch ID', 'en_CA', 0, ''); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.track.header.listshipment', 'List of Shipments', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.repeat', 'REPEAT', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.company', 'Company', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.order', 'Order', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.tracking', 'Tracking', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.track.shiporder', 'Ship Order', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.shipdate', 'Ship Date', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.qb', 'Q/B', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.weight', 'Weight', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.quotedcharge', 'Quoted Charge', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.billedcharge', 'Billed Charge', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.fromaddress', 'From Address', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.toaddress', 'To Address', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.billingstatus', 'Billing Status', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.reset', 'RESET', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.savelist', 'SAVE LIST', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.search', 'SEARCH', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.editcustomer', 'Edit Customer', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.addcustomer', 'Add Customer', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.accounting', 'ACCOUNTING', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customer.showref', 'Show Ref. Sec.', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customer.apcontact', 'AP Contact', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customer.new.apphone', 'AP Phone', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.customer', ' Customer', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.listall', ' List All', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.accountdetails', 'Account Details', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.carrierName', 'Select Carrier', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.countryName', 'Country', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.accountNumber2', 'Account #1', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.accountNumber1', 'Account #2', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.property1', 'Property #1', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.property2', 'Property #2', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.property3', 'Property #3', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.property4', 'Property #4', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.property5', 'Property #5', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.customerCarrier.defaultaccount', 'Default', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.activecarriers', 'Active Carriers', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.delete', 'DELETE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.account1', 'ACCOUNT#1', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.account2', 'ACCOUNT#2', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.default', 'DEFAULT', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.houseaccount', 'HOUSE ACCOUNT', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.userlist', 'User List', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.username', 'USER NAME', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.datecreated', 'DATE CREATED', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.userrole', 'USER ROLE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.enabled', 'ENABLED', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.adduser.unitmeasure', 'Unit Of Measure', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.markups', 'Markups', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.addnew', 'Add New', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.carriermarkup', 'Carrier Markups', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.list.applyall', 'APPLY ALL', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.id', 'ID', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.oco', 'OCO', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.dco', 'DCO', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.from', 'FROM', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.to', 'TO', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.variable', 'VARIABLE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.inactive', 'INACTIVE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.accessorialcharge', 'Accessorial Charges', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.charge', 'Charge', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.ghead.cost', 'Cost', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.customersalesteam', 'Customer Sales Team', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.back', 'BACK', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.salesagent', 'Sales Agent', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.spd', 'SPD', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.ltl', 'LTL', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.chb', 'CHB', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('btn.update', 'UPDATE', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.heading.customersales', 'Customer Sales', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.spd', 'SPD', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.ltl', 'LTL', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.salesuser.chb', 'CHB', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.contactus', 'CONTACT US', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.websitefeedback', 'WEBSITE FEEDBACK', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.francais', 'FRANCAIS', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.help', 'HELP', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.logout', 'LOG OUT', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.bottom.exportto', 'Export to', 'en_CA', 0, ''); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('placehold.entersearchkey', 'Enter  search key here', 'en_CA', 0, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.customer.invoice.list', 'Customer Invoice List', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.download.csv', 'Download CSV', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.pdf', 'PDF', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.delete', 'DELETE', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.inv', 'Inv #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.datecreated', 'Date Created', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.cost', 'Cost', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.tax', 'Tax', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoices.status', 'Status', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.display.cancelled', 'Display Cancelled', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.order', 'Order #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.datecreated', 'Date Created', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.costcharge', 'Cost / Charge', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.billed', 'Billed', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.generateInvoice.shipments', 'Shipments to be Invoiced', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.invoicedetails', 'Invoice Payment Details', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.invoicenumber', 'Invoice Number', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.remittancedate', 'Remittance Date', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.paidby', 'Paid By', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchar.paymentref', 'Payment Ref#', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.processar', 'Select the invoices to update and fill in the appropriate details, then click \"Process A/R\"', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.invoice', 'Invoice#', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.tax', 'Tax', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.total', 'Total', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.paid', 'Paid', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.updatear.balancedue', 'Balance Due', 'en_CA', 0, NULL);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.dateremitted', 'Date Remitted', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.mode', 'Mode of Payment', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.paymentref', 'Payment Ref#', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.updatear.unpaidlist', 'Unpaid Invoice List', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.assign', 'ASSIGN', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.order', 'Order #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.tracking', 'Tracking #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.ref', 'Ref #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.datecreated', 'Date Created', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.service', 'Service', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.total', 'Total', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.addresses', 'Addresses', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.unlinked.billingstatus', 'Billing Status', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.autolinked.company', 'Company', 'en_CA', 0, NULL);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.order ', 'Order #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.tracking ', 'Tracking #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.datecreated', 'Date Created', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.service', 'Service', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.autolinked.total', 'Total', 'en_CA', 0, NULL);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.addresses', 'Addresses', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.billingstatus', 'Billing Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.reassign', 'RE-ASSIGN', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.autolinked.accept', 'ACCEPT', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.search', 'Search', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.customer', 'Customer', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.pickupdate', 'Pickup Date', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.pickuptime', 'Pickup Time', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.pickuplocation', 'Pickup Location', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.carrier', 'Carrier', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.confirmationnum', 'Confirmation No', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.pickupaddress', 'Pickup Address', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.status', 'Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.list', 'List of Pickups', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.cancel', 'CANCEL', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchpickup.carrierconfirmation', 'Carrier Confirmation', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.search', 'Search', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.releaseall', 'Release All', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.reset', 'Reset', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.fromdate', 'From Date', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.todate', 'To Date', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.invoice', 'Invoice #', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.filename', 'File Name', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.carrier', 'Carrier', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.status', 'Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.action', 'ACTION', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.autolink', 'AUTOLINK', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.searchedi.unlink', 'UNLINK', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.id', 'Id', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.carrier', 'Carrier', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.edinvoice.account', 'Account', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.invoicedate', 'Invoice Date', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.processed', 'Processed', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.filename', 'File Name', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.status', 'Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ediinvoice.message', 'Message', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.edinvoice.invoice', 'Invoice', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.updatestatus', 'UPDATE STATUS', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commssion.invoicestatus', 'Invoice Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commssion.commissionreport', 'Commission Report', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.pdf', 'PDF', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.inv', 'Inv#', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.datecreated', 'Date Created', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.commission', 'Commission', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.amount', 'Amount', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.cost', 'Cost', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.status', 'Status', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.total', 'Total', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalspd', 'Total SPD', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalltl', 'Total LTL ', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalchb', 'Total CHB', 'en_CA', 0); 

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.year', 'Year', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.month', 'Month', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.currency', 'Currency', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalcost', 'Total Cost', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalrevenue', 'Total Revenue', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.customername', 'Customer Name', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.salesresults', 'Sales Results', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.commissionreport', 'Commission Report', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.customerpackagelist', 'Package List for Customer', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.packagename', 'Package Name', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.packagedesc', 'Package Description', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.customerproductlist', 'Product Lists for Customer', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.product.origincountry', 'ORIGIN COUNTRY', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.product.unitprice', 'UNIT PRICE', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.list.addproduct', 'ADD PRODUCT', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.invoicepay', 'Select invoices to pay', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.processed', 'PROCESSED', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.paymentresults', 'Payment Results', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.datecreated', 'Date Created', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.invoice', 'Invoice', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.amount', 'Amount', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.tax', 'Tax', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.total', 'Total', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.paidamt', 'Paid Amount', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.balancedue', 'Balance Due', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.uploadDistribution.name', 'Name', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.uploadDistribution.file', 'File', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.quote.shipfrom', 'Ship From', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.quote.switchto', 'Switch to Ship Mode', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.quote.shipto', 'Ship To', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.quote.service', 'Service', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.quote.uom', 'Unit Of Measure', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.edit', 'EDIT', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.delete', 'DELETE', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.company', 'Company', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.city', 'City', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.stateprovince', 'State/Province', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.contactname', 'Contact Name', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.email', 'Email', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.addresslist.zipcode', 'Zip Code', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.btn.download', 'DOWNLOAD', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.btn.upload', 'UPLOAD', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.startsignup', 'Sign up now and start shipping', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.home', 'HOME', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.signup', 'SIGN UP', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.forgetpassword', 'FORGET PASSWORD', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.btn.signup', 'Sign Up Now!', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.acceptagreement', 'I accept the terms of agreement listed', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.acceptagreementhere', 'here', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ratelist.total', 'Total', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.pickupfrom', 'Pick Up From', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.orderdetails', 'Order Details For', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.company', 'Company', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.address', 'Address', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.fromres', 'From Residential', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.phone', 'Phone', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.email', 'Email', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.attention', 'Attention', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.shipto', 'Ship To', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.toresidential', 'To Residential', 'en_CA', 0);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.customer', 'Customer', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.zonefromto', 'Zone From / To', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.email', 'email', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.carrier', 'Carrier', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.service', 'Service', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.pickupconfirm', ' Pick up Confirmation', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.carriertrack', 'Carrier Tracking', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.ref1', 'Reference 1', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.ref2', 'Reference 2', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.weight', 'Weight (Entered / Billed)', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.shipdate', 'Shipment Date', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.billstatus', 'Billing Status', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.status', 'Status', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.markup', 'Mark-up Applied', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.markdown', 'Mark-down Applied', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.additionalservice', 'Additional Services', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.tradeshowpickup', 'Tradeshow Pickup', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.tradeshowdelivery', 'Tradeshow Delivery', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.insidepickup', 'Inside Pickup', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.insidedelivery', 'Inside Delivery', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.appointmentpickup', 'Appointment Pickup', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.appointmentdelivery', 'Appointment Delivery', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.tailgatepickup', 'Tailgate Pickup', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.tailgatedelivery', 'Tailgate Delivery', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.saturdaydelivery', 'Saturday Delivery', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.holdforpickup', 'Hold for Pickup', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.cod', 'COD', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.insurance', 'Insurance', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.quotecharges', 'Quote Charges', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.copytoactual', 'Copy to Actual', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.code', 'Code', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.chargename', 'Charge Name', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.tariff', 'Tariff', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.cur', 'CUR', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.exrate', 'EX Rate', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('btn.savecharge', 'Save Charge', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.newcharge', 'New Charge', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.actualcharges', 'Actual Charges', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.copycharges', 'Copy Charges', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.paymentinfo', 'Payment Info', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.auth', 'Auth', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.ref', 'Reference', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.processorref', 'Processor Ref', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.cc', 'CC', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.btn.refundcharge', 'Refund Charge', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.package', 'Package', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.packagedetails', 'Package Details For', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.package', 'Package', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.quantity', 'Quantity', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.dimensionspack', 'Dimensions of Package', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.weightpack', 'Weight of Package', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.carrierdimpack', 'Carrier Dim Package', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.statusupdates', 'Status Updates', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.datetime', 'Date Time', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.message', 'Message', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.log', 'Log', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.updatedby', 'UpdatedBy', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.private', 'Private', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.delete', 'Delete', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.paymentrequired', 'Payment Required', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.vieweditinvoice', 'View/Edit Invoice', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.invoicedetails', 'Invoice Details for', 'en_CA', 0);


INSERT INTO `bill_duty` (`id`, `code`, `name`, `locale`) VALUES ('1', 'Shipper', 'Shipper', 'en_CA');
INSERT INTO `bill_duty` (`id`, `code`, `name`, `locale`) VALUES ('2', 'Consignee', 'Consignee', 'en_CA');
INSERT INTO `bill_duty` (`id`, `code`, `name`, `locale`) VALUES ('3', 'Third Party', 'Third Party', 'en_CA');


CREATE TABLE `bill_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `locale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*-------------------------------------------End for query 472--------------------------------------------*/

INSERT INTO `service` (`service_id`, `name`, `carrier_id`, `master_carrier_id`, `description`, `code`, `transit_code`, `mode`, `service_type`, `master_service_id`, `max_length`, `max_width`, `max_height`, `max_weight`, `email_type`) VALUES (NULL, 'PurolatorExpressU.S. Import', '20', '20', 'PurolatorExpressU.S. Import', 'PurolatorExpressU.S. Import', '601', '1', '0', '0', '0', '0', '0', '151', 'SPD');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.country', 'Country', 'en_CA', 0);



############################################################

		Need To Go Live
#############################################################

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('message.send.addcustomersalesrep.notification.body', '<html>\n <head>\n <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n <title>Thank you for using Inegrated Carriers SoluShip&trade;</title>\n <style type=\"text/css\">\n <!--\n body {\n font: 100% Verdana, Arial, Helvetica, sans-serif;\n background: #fff;\n margin: 0;\n padding: 0;\n text-align: center;\n color: #000000;\n }\n .oneColElsCtr #container {\n width: 550px;\n background: #FFFFFF;\n margin: 0 auto;\n border: 1px solid #000000;\n text-align: left;\n height:auto;\n }\n .oneColElsCtr #mainContent {\n padding: 10px 10px 10px 10px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 12px;\n }\n .oneColElsCtr #mainContent h1 {\n padding: 5px 0px 0px 0px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 18px;\n color:#990000;\n }\n .oneColElsCtr #mainFooter {\n padding: 5px 0px 0px 10px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 10px;\n color:#fff;\n background-color:#000000;\n height:35px;\n }\n #mainContent p{\n text-align: left;\n margin-left: 30px;\n }\n ul{\n text-align: left;\n }\n -->\n </style>\n </head>\n \n <body class=\"oneColElsCtr\">\n \n <div id=\"container\">\n <img src=\"http://www.soluship.com/mmr/images/ic-header.jpg\" includeContext=\"true\" />\n   <div id=\"mainContent\">\n     <h1>Thank you for using Integrated Carriers SoluShip&trade;</h1>\n     <p>To all concerned,</p>\n     <p>Please note that the follow customer has been sucessfully added to SoluShip</p>\n     <p>-------------------------------------------------------</p>\n     <p>\n <b>New Account:</b><br/>\nCustomer Name: %CUSTOMERNAME <br/>\nAccount#: %ACCOUNT<br/>\nContact Name: %CONTACTNAME<br/>\nSales Rep Name: %SALESREP<br/>\nAddress1: %ADDRESS1<br/>\n City: %CITY<br/>\n Zip/Postal Code: %ZIP<br/>\n Province: %PROVINCE<br/>\n Country: %COUNTRY<br/>\n     </p>\n     <p>-------------------------------------------------------</p>\n\n     <p>Thank you and enjoy Integrated Carriers SoluShip&trade;.</p><br/>\n <!-- end #mainContent -->\n     </div>\n     <div id=\"mainFooter\">\n    &copy; 2012 Integrated Carriers\n     <!-- end #mainFooter -->\n     </div>\n <!-- end #container -->\n </div>\n </body>\n </html>', 'en_CA', 1);

ALTER TABLE resourcebundle CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE country CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

/*------------------ Query for issue 296(28/10/14) -----------------*/

CREATE TABLE `exchange_rate_currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `cur_from` varchar(10) DEFAULT NULL,
  `cur_to` varchar(10) DEFAULT NULL,
  `exch_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
  
CREATE TABLE `currency_symbol` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `currency_code` varchar(45) NOT NULL,
  `country_code` varchar(45) DEFAULT NULL,
  `currency_symbol` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_name` varchar(45) DEFAULT NULL,
  `currency_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `currency_code_UNIQUE` (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `currency_symbol` 
CHANGE COLUMN `country_code` `country_code` VARCHAR(10) NULL DEFAULT NULL ;

  
  
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CAD', '$', 'Canada', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('USD', '$', 'United States', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ALL', 'Lek', 'Albania', 'Lek');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AFN', 'Ø‹', 'Afghanistan', 'Afghani');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ARS', '$', 'Argentina', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AWG', 'Æ’', 'Aruba', 'Guilder');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AUD', '$', 'Australia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AZN', 'Ð¼Ð°Ð½', 'Azerbaijan', 'Manat');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BSD', '$', 'Bahamas', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BBD', '$', 'Barbados', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BYR', 'p.', 'Belarus', 'Ruble');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BZD', 'BZ$', 'Belize', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BMD', '$', 'Bermuda', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BOB', '$b', 'Bolivia', 'Boliviano');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BAM', 'KM', 'Bosnia and Herzegovina', 'Convertible Marka');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BWP', 'P', 'Botswana', 'Pula');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BGN', 'Ð»Ð²', 'Bulgaria', 'Lev');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BRL', 'R$', 'Brazil', 'Real');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BND', '$', 'Brunei', 'Darussalam Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KHR', 'áŸ›', 'Cambodia', 'Riel');

INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KYD', '$', 'Cayman', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CLP', '$', 'Chile', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CNY', 'Â¥', 'China', 'Yuan Renminbi');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('COP', '$', 'Colombia', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CRC', 'â‚¡', 'Costa Rica', 'Colon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HRK', 'kn', 'Croatia', 'Kuna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CUP', 'â‚±', 'Cuba', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CZK', 'KÄ�', 'Czech Republic', 'Koruna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('DKK', 'kr', 'Denmark', 'Krone');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('DOP', 'RD$', 'Dominican Republic', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('XCD', '$', 'East Caribbean', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EGP', 'Â£', 'Egypt', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SVC', '$', 'El Salvador', 'Colon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EEK', 'kr', 'Estonia', 'Kroon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EUR', 'â‚¬', 'Euro Member', 'Euro');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('FKP', 'Â£', 'Falkland Islands', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('FJD', '$', 'Fiji', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GHC', 'Â¢', 'Ghana', 'Cedis');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GIP', 'Â£', 'Gibraltar', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GTQ', 'Q', 'Guatemala', 'Quetzal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GGP', 'Â£', 'Guernsey', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GYD', '$', 'Guyana', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HNL', 'L', 'Honduras', 'Lempira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HKD', '$', 'Hong Kong', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HUF', 'Ft', 'Hungary', 'Forint');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ISK', 'kr', 'Iceland', 'Krona');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('INR', 'à¤°', 'India', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IDR', 'Rp', 'Indonesia', 'Rupiah');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IRR', 'ï·¼', 'Iran', 'Rial');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IMP', 'Â£', 'Isle of Man', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ILS', 'â‚ª', 'Israel', 'Shekel');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JMD', 'J$', 'Jamaica', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JPY', 'Â¥', 'Japan', 'Yen');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JEP', 'Â£', 'Jersey', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KZT', 'Ð»Ð²', 'Kazakhstan', 'Tenge');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KPW', 'â‚©', 'Korea (North)', 'Won');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KRW', 'â‚©', 'Korea (South)', 'Won');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KGS', 'Ð»Ð²', 'Kyrgyzstan', 'Som');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LAK', 'â‚­', 'Laos', 'Kip');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LVL', 'Ls', 'Latvia', 'Lat');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LBP', 'Â£', 'Lebanon', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LRD', '$', 'Liberia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LTL', 'Lt', 'Lithuania', 'Litas');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MKD', 'Ð´ÐµÐ½', 'Macedonia', 'Denar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MYR', 'RM', 'Malaysia', 'Ringgit');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MUR', 'â‚¨', 'Mauritius', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MXN', '$', 'Mexico', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MNT', 'â‚®', 'Mongolia', 'Tughrik');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MZN', 'MT', 'Mozambique', 'Metical');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NAD', '$', 'Namibia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NPR', 'â‚¨', 'Nepal', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ANG', 'Æ’', 'Netherlands', 'Antilles Guilder');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NZD', '$', 'New Zealand', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NIO', 'C$', 'Nicaragua', 'Cordoba');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NGN', 'â‚¦', 'Nigeria', 'Naira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NOK', 'kr', 'Norway', 'Krone');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('OMR', 'ï·¼', 'Oman', 'Rial');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PKR', 'â‚¨', 'Pakistan', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PAB', 'B/.', 'Panama', 'Balboa');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PYG', 'Gs', 'Paraguay', 'Guarani');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PEN', 'S/.', 'Peru', 'Nuevo Sol');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PHP', 'â‚±', 'Philippines', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PLN', 'zÅ‚', 'Poland', 'Zloty');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('QAR', 'ï·¼', 'Qatar', 'Riyal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RON', 'lei', 'Romania', 'New Leu');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RUB', 'Ñ€ÑƒÐ±', 'Russia', 'Ruble');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SHP', 'Â£', 'Saint Helena', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SAR', 'ï·¼', 'Saudi Arabia', 'Riyal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RSD', 'Ð”Ð¸Ð½.', 'Serbia', 'Dinar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SCR', 'â‚¨', 'Seychelles', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SGD', '$', 'Singapore', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SBD', '$', 'Solomon Islands', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SOS', 'S', 'Somalia', 'Shilling');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ZAR', 'S', 'South Africa', 'Rand');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LKR', 'â‚¨', 'Sri Lanka', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SEK', 'kr', 'Sweden', 'Krona');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CHF', 'CHF', 'Switzerland', 'Franc');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SRD', '$', 'Suriname', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SYP', 'Â£', 'Syria', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TWD', 'NT$', 'Taiwan', 'New Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('THB', 'à¸¿', 'Thailand', 'Baht');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TTD', 'TT$', 'Trinidad and Tobago', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TRL', 'â‚¤', 'Turkey', 'Lira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TVD', '$', 'Tuvalu', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UAH', 'â‚´', 'Ukraine', 'Hryvna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GBP', 'Â£', 'United Kingdom', 'Pound');

INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UYU', '$U', 'Uruguay', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UZS', 'Ð»Ð²', 'Uzbekistan', 'Som');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('VEF', 'Bs', 'Venezuela', 'Bolivar Fuerte');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('VND', 'â‚«', 'Viet Nam', 'Dong');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('YER', 'ï·¼', 'Yemen', 'Rial');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ZWD', 'Z$', 'Zimbabwe', 'Dollar');

UPDATE `currency_symbol` SET `country_code`='AF' WHERE `currency_code`='AFN';
UPDATE `currency_symbol` SET `country_code`='AL' WHERE `currency_code`='ALL';
UPDATE `currency_symbol` SET `country_code`='NL' WHERE `currency_code`='ANG';
UPDATE `currency_symbol` SET `country_code`='AR' WHERE `currency_code`='ARS';
UPDATE `currency_symbol` SET `country_code`='AU' WHERE `currency_code`='AUD';
UPDATE `currency_symbol` SET `country_code`='AW' WHERE `currency_code`='AWG';
UPDATE `currency_symbol` SET `country_code`='AZ' WHERE `currency_code`='AZN';
UPDATE `currency_symbol` SET `country_code`='BA' WHERE `currency_code`='BAM';
UPDATE `currency_symbol` SET `country_code`='BB' WHERE `currency_code`='BBD';
UPDATE `currency_symbol` SET `country_code`='BG' WHERE `currency_code`='BGN';
UPDATE `currency_symbol` SET `country_code`='BM' WHERE `currency_code`='BMD';
UPDATE `currency_symbol` SET `country_code`='BN' WHERE `currency_code`='BND';
UPDATE `currency_symbol` SET `country_code`='BO' WHERE `currency_code`='BOB';
UPDATE `currency_symbol` SET `country_code`='BR' WHERE `currency_code`='BRL';
UPDATE `currency_symbol` SET `country_code`='BS' WHERE `currency_code`='BSD';
UPDATE `currency_symbol` SET `country_code`='BW' WHERE `currency_code`='BWP';
UPDATE `currency_symbol` SET `country_code`='BY' WHERE `currency_code`='BYR';
UPDATE `currency_symbol` SET `country_code`='BZ' WHERE `currency_code`='BZD';
UPDATE `currency_symbol` SET `country_code`='CA' WHERE `currency_code`='CAD';
UPDATE `currency_symbol` SET `country_code`='CH' WHERE `currency_code`='CHF';
UPDATE `currency_symbol` SET `country_code`='CL' WHERE `currency_code`='CLP';
UPDATE `currency_symbol` SET `country_code`='CN' WHERE `currency_code`='CNY';
UPDATE `currency_symbol` SET `country_code`='CO' WHERE `currency_code`='COP';
UPDATE `currency_symbol` SET `country_code`='CR' WHERE `currency_code`='CRC';
UPDATE `currency_symbol` SET `country_code`='CU' WHERE `currency_code`='CUP';
UPDATE `currency_symbol` SET `country_code`='CZ' WHERE `currency_code`='CZK';
UPDATE `currency_symbol` SET `country_code`='DK' WHERE `currency_code`='DKK';
UPDATE `currency_symbol` SET `country_code`='DO' WHERE `currency_code`='DOP';
UPDATE `currency_symbol` SET `country_code`='EE' WHERE `currency_code`='EEK';
UPDATE `currency_symbol` SET `country_code`='EG' WHERE `currency_code`='EGP';
UPDATE `currency_symbol` SET `country_code`='FJ' WHERE `currency_code`='FJD';
UPDATE `currency_symbol` SET `country_code`='FK' WHERE `currency_code`='FKP';
UPDATE `currency_symbol` SET `country_code`='GB' WHERE `currency_code`='GBP';
UPDATE `currency_symbol` SET `country_code`='GG' WHERE `currency_code`='GGP';
UPDATE `currency_symbol` SET `country_code`='GH' WHERE `currency_code`='GHC';
UPDATE `currency_symbol` SET `country_code`='GI' WHERE `currency_code`='GIP';
UPDATE `currency_symbol` SET `country_code`='GT' WHERE `currency_code`='GTQ';
UPDATE `currency_symbol` SET `country_code`='GY' WHERE `currency_code`='GYD';
UPDATE `currency_symbol` SET `country_code`='HK' WHERE `currency_code`='HKD';
UPDATE `currency_symbol` SET `country_code`='HN' WHERE `currency_code`='HNL';
UPDATE `currency_symbol` SET `country_code`='HR' WHERE `currency_code`='HRK';
UPDATE `currency_symbol` SET `country_code`='HU' WHERE `currency_code`='HUF';
UPDATE `currency_symbol` SET `country_code`='ID' WHERE `currency_code`='IDR';
UPDATE `currency_symbol` SET `country_code`='IL' WHERE `currency_code`='ILS';
UPDATE `currency_symbol` SET `country_code`='IM' WHERE `currency_code`='IMP';
UPDATE `currency_symbol` SET `country_code`='IN' WHERE `currency_code`='INR';
UPDATE `currency_symbol` SET `country_code`='IR' WHERE `currency_code`='IRR';
UPDATE `currency_symbol` SET `country_code`='IS' WHERE `currency_code`='ISK';
UPDATE `currency_symbol` SET `country_code`='JE' WHERE `currency_code`='JEP';
UPDATE `currency_symbol` SET `country_code`='JM' WHERE `currency_code`='JMD';
UPDATE `currency_symbol` SET `country_code`='JP' WHERE `currency_code`='JPY';
UPDATE `currency_symbol` SET `country_code`='KG' WHERE `currency_code`='KGS';
UPDATE `currency_symbol` SET `country_code`='KH' WHERE `currency_code`='KHR';
UPDATE `currency_symbol` SET `country_code`='KP' WHERE `currency_code`='KPW';
UPDATE `currency_symbol` SET `country_code`='KR' WHERE `currency_code`='KRW';
UPDATE `currency_symbol` SET `country_code`='KY' WHERE `currency_code`='KYD';
UPDATE `currency_symbol` SET `country_code`='KZ' WHERE `currency_code`='KZT';
UPDATE `currency_symbol` SET `country_code`='LA' WHERE `currency_code`='LAK';
UPDATE `currency_symbol` SET `country_code`='LB' WHERE `currency_code`='LBP';
UPDATE `currency_symbol` SET `country_code`='LK' WHERE `currency_code`='LKR';
UPDATE `currency_symbol` SET `country_code`='LR' WHERE `currency_code`='LRD';
UPDATE `currency_symbol` SET `country_code`='LT' WHERE `currency_code`='LTL';
UPDATE `currency_symbol` SET `country_code`='LV' WHERE `currency_code`='LVL';
UPDATE `currency_symbol` SET `country_code`='MK' WHERE `currency_code`='MKD';
UPDATE `currency_symbol` SET `country_code`='MN' WHERE `currency_code`='MNT';
UPDATE `currency_symbol` SET `country_code`='MU' WHERE `currency_code`='MUR';
UPDATE `currency_symbol` SET `country_code`='MX' WHERE `currency_code`='MXN';
UPDATE `currency_symbol` SET `country_code`='MY' WHERE `currency_code`='MYR';
UPDATE `currency_symbol` SET `country_code`='MZ' WHERE `currency_code`='MZN';
UPDATE `currency_symbol` SET `country_code`='NA' WHERE `currency_code`='NAD';
UPDATE `currency_symbol` SET `country_code`='NG' WHERE `currency_code`='NGN';
UPDATE `currency_symbol` SET `country_code`='NI' WHERE `currency_code`='NIO';
UPDATE `currency_symbol` SET `country_code`='NO' WHERE `currency_code`='NOK';
UPDATE `currency_symbol` SET `country_code`='NP' WHERE `currency_code`='NPR';
UPDATE `currency_symbol` SET `country_code`='NZ' WHERE `currency_code`='NZD';
UPDATE `currency_symbol` SET `country_code`='OM' WHERE `currency_code`='OMR';
UPDATE `currency_symbol` SET `country_code`='PA' WHERE `currency_code`='PAB';
UPDATE `currency_symbol` SET `country_code`='PE' WHERE `currency_code`='PEN';
UPDATE `currency_symbol` SET `country_code`='PH' WHERE `currency_code`='PHP';
UPDATE `currency_symbol` SET `country_code`='PK' WHERE `currency_code`='PKR';
UPDATE `currency_symbol` SET `country_code`='PL' WHERE `currency_code`='PLN';
UPDATE `currency_symbol` SET `country_code`='PY' WHERE `currency_code`='PYG';
UPDATE `currency_symbol` SET `country_code`='QA' WHERE `currency_code`='QAR';
UPDATE `currency_symbol` SET `country_code`='RO' WHERE `currency_code`='RON';
UPDATE `currency_symbol` SET `country_code`='RU' WHERE `currency_code`='RUB';
UPDATE `currency_symbol` SET `country_code`='SA' WHERE `currency_code`='SAR';
UPDATE `currency_symbol` SET `country_code`='SB' WHERE `currency_code`='SBD';
UPDATE `currency_symbol` SET `country_code`='SC' WHERE `currency_code`='SCR';
UPDATE `currency_symbol` SET `country_code`='SE' WHERE `currency_code`='SEK';
UPDATE `currency_symbol` SET `country_code`='SG' WHERE `currency_code`='SGD';
UPDATE `currency_symbol` SET `country_code`='SH' WHERE `currency_code`='SHP';
UPDATE `currency_symbol` SET `country_code`='SO' WHERE `currency_code`='SOS';
UPDATE `currency_symbol` SET `country_code`='SR' WHERE `currency_code`='SRD';
UPDATE `currency_symbol` SET `country_code`='SV' WHERE `currency_code`='SVC';
UPDATE `currency_symbol` SET `country_code`='SY' WHERE `currency_code`='SYP';
UPDATE `currency_symbol` SET `country_code`='TH' WHERE `currency_code`='THB';
UPDATE `currency_symbol` SET `country_code`='TR' WHERE `currency_code`='TRL';
UPDATE `currency_symbol` SET `country_code`='TT' WHERE `currency_code`='TTD';
UPDATE `currency_symbol` SET `country_code`='TV' WHERE `currency_code`='TVD';
UPDATE `currency_symbol` SET `country_code`='TW' WHERE `currency_code`='TWD';
UPDATE `currency_symbol` SET `country_code`='UA' WHERE `currency_code`='UAH';
UPDATE `currency_symbol` SET `country_code`='US' WHERE `currency_code`='USD';
UPDATE `currency_symbol` SET `country_code`='UY' WHERE `currency_code`='UYU';
UPDATE `currency_symbol` SET `country_code`='UZ' WHERE `currency_code`='UZS';
UPDATE `currency_symbol` SET `country_code`='VE' WHERE `currency_code`='VEF';
UPDATE `currency_symbol` SET `country_code`='VN' WHERE `currency_code`='VND';
UPDATE `currency_symbol` SET `country_code`='YE' WHERE `currency_code`='YER';
UPDATE `currency_symbol` SET `country_code`='ZA' WHERE `currency_code`='ZAR';
UPDATE `currency_symbol` SET `country_code`='ZW' WHERE `currency_code`='ZWD';

INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('1', '2014-10-25 12:40:04', 'USD', 'INR', '61.28');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2', '2014-10-25 12:40:05', 'INR', 'USD', '0.0163');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('3', '2014-10-25 12:40:15', 'USD', 'CAD', '1.1235');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('4', '2014-10-25 12:40:28', 'CAD', 'USD', '0.8901');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('5', '2014-10-20 17:09:57', 'HKD', 'USD', '0.1289');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('6', '2014-10-25 12:40:31', 'USD', 'HKD', '7.7573');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('7', '2014-10-25 12:40:32', 'GBP', 'USD', '1.6089');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('8', '2014-10-25 12:40:34', 'GBP', 'INR', '98.5903');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('9', '2014-10-25 12:40:42', 'INR', 'GBP', '0.0101');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('11', '2014-10-24 18:22:07', 'CNY', 'USD', '0.1635');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('12', '2014-10-24 18:22:08', 'USD', 'CNY', '6.1173');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('13', '2014-10-25 12:40:45', 'CNY', 'INR', '10.0175');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('14', '2014-10-25 12:40:46', 'INR', 'CNY', '0.0998');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('15', '2014-10-25 12:40:47', 'USD', 'YER', '215.015');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('16', '2014-10-24 21:43:56', 'YER', 'USD', '0.0047');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('17', '2014-10-25 15:40:16', 'CAD', 'INR', '54.5458');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('18', '2014-10-25 15:40:17', 'INR', 'CAD', '0.0183');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('19', '2014-10-25 15:40:18', 'CAD', 'GBP', '0.5533');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('20', '2014-10-25 20:40:11', 'CAD', 'SEK', '6.4548');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('21', '2014-10-25 20:40:12', 'SEK', 'CAD', '0.1549');
/*---------------------------------------------  End 296 ----------------------------*/
   

ALTER TABLE bill_duty CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

UPDATE `resourcebundle` SET `msg_content`='All Same' WHERE `msg_id`='label.package.allsame' and`locale`='en_CA' and`is_fmk`=0;

UPDATE `resourcebundle` SET `msg_content`='As Above' WHERE `msg_id`='label.package.allabove' and`locale`='en_CA' and`is_fmk`=0;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('btn.admin.create', 'CREATE', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.clearexception', 'Clear Exception', 'en_CA', 0);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '163');//markup
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '164');//addnew
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '161');//reset
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '169');//copymarkup
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '166');//apply all
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '165');//delete
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '162');//save

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('solutions_manager', '146');//save

ALTER TABLE `shipping_order` 
ADD COLUMN `unitmeasure` INT NULL DEFAULT '0' AFTER `slave_service_id`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.navigation.caltax', 'Calculate Tax', 'en_CA', 1);



---------------------------------- Issue 497 ---------------------------------------------------------------------
INSERT INTO `menu` (`name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`, `image_over`) VALUES ('Invoice Breakdown', '/admin/commReport.action?method=new', '3', 'LEVEL_1', '1', '400', 'menu.admin.invoiceBreakdown', 'N', 'N');
INSERT INTO `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('428', 'busadmin', '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.invoiceBreakdown', 'INVOICE BREAKDOWN', 'en_CA', 1);
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '428');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('invoiceBreakdown', '400', 1, 'Invoice Breakdown', 1, NULL);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1018');
UPDATE `menu` SET `url`='/admin/invoiceBreakdown.action?method=new' WHERE `id`='428';

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.report.invoicebreakdown', 'Invoice Breakdown', 'en_CA', 0);


INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('invoiceBreakdown.download', '400', 1, 'Invoice Breakdown Report', 1);

INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_admin', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_base', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('admin', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sales', '1019', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_shipper', '1019', NULL);

ALTER TABLE `invoice` 
ADD COLUMN `chb_total` DOUBLE NOT NULL DEFAULT '0' AFTER `commission_calculated`,
ADD COLUMN `spd_total` DOUBLE NOT NULL DEFAULT '0' AFTER `chb_total`,
ADD COLUMN `ltl_total` DOUBLE NOT NULL DEFAULT '0' AFTER `spd_total`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.included.cancelledinvoice', 'Included Cancelled Invoices', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.invoice.breakdown', 'Invoice Breakdown', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.inv', 'Inv', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.company', 'Company', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.datecreated', 'Date Created', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.Amount', 'Amount', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.Tax', 'Tax', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.SPD', 'SPD', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.LTL', 'LTL', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('invoice.breakedown.CHB', 'CHB', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('invoice.breakdown.total', 'Total', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.csv', 'CSV', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.excel', 'Excel', 'en_CA', 0, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.xml', 'XML', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.export.to', 'Export to', 'en_CA', 0);
------------------------------------------------------ Issue 493 ------------------------------
alter table customer add chb_customer bit(1) default 0;

update business set ship_order_notification_body='mail.shipment.notification.body' where business_id=1;

----------------------------- Issue 490 ------------------------------------------------------------alterALTER TABLE `newsouship`.`charges` 
ALTER TABLE `charges` ADD COLUMN `calculate_tax` INT NULL DEFAULT 0 AFTER `charge_group_id`;

-------------------------------Issue 499 --------------------------------------------------------
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.not include Taxes', 'The Totals do not include Taxes', 'en_CA', 1);


----------------------------- issue 433 -------------------------------------------------------
ALTER TABLE `shipping_order` 
ADD COLUMN `save_shipment` INT NULL DEFAULT 0 AFTER `signature_image`;

----------------------------- issue 296 ------------------------------------------------------ // need to go live

UPDATE `currency_symbol` SET `country_code`='EUCG' WHERE `id`='35';
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '0000-00-00 00:00:00', 'CAD', 'EUR', '0.7');
INSERT INTO `exchange_rate_currency` (`id`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('','0000-00-00 00:00:00', 'USD', 'EUR', '0.8');
INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '0000-00-00 00:00:00', 'CAD', 'CNY', '5.3987');

INSERT INTO `newsoluship`.`locale` (`locale`, `description`, `locale_text`, `active`, `display_text`) VALUES ('en_US', 'English America', 'English', 1, 'en_CA');
INSERT INTO `newsoluship`.`locale` (`locale`, `description`, `locale_text`, `active`, `display_text`) VALUES ('zh_CN', 'Chinese China', 'Chinese', 1, 'zh_CN');

INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'MNT', 'CAD', '0.00060');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'MNT', 'USD', '0.00053');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'MNT', 'EUR', '0.00043');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'CAD', 'MNT', '1673.10');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'USD', 'MNT', '1882.50');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'EUR', 'MNT', '2345.21');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'MNT', 'CNY', '0.0033');
INSERT INTO `exchange_rate_currency` (`date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('2014-11-19 15:30:08', 'CNY', 'MNT', '307.27');




INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '2014-11-19 15:30:08', 'CNY', 'CAD', '0.18');

INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '2014-11-19 15:30:08', 'CNY', 'EUR', '0.13');

INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '2014-11-19 15:30:08', 'EUR', 'CAD', '1.41');

INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '2014-11-19 15:30:08', 'EUR', 'USD', '1.24');

INSERT INTO `exchange_rate_currency` (`id`, `date`, `cur_from`, `cur_to`, `exch_rate`) VALUES ('', '2014-11-19 15:30:08', 'EUR', 'CNY', '7.63');


ALTER TABLE `locale` 
ADD COLUMN `display_text` VARCHAR(45) NULL DEFAULT NULL AFTER `active`;

ALTER TABLE `charges` 
CHANGE COLUMN `exchange_rate` `exchange_rate` DECIMAL(10,2) NULL DEFAULT '0.00' ;


UPDATE `locale` SET `display_text`='en_CA' WHERE `locale`='en_CA';
UPDATE `locale` SET `display_text`='fr_CA' WHERE `locale`='fr_CA';
UPDATE `locale` SET `display_text`='zh_CN' WHERE `locale`='zh_CN';

----------------------------- end -----------------------------------------------------------

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.customer.chbCustomer', 'CHB Customer', 'en_CA', 0);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('mail.shipment.notification.subject', '%COMPANYNAME - Shipment Notification', 'en_CA', 1, '');
UPDATE `business` SET `ship_order_notification_subject`='mail.shipment.notification.subject' WHERE `business_id`='1';
UPDATE `resourcebundle` SET `msg_id`='mail.shipment.notify.body' WHERE `msg_id`='mail.shipment.notification.body' and`locale`='en_CA' and`is_fmk`=0;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.error.invalidquotedchargecurrency', 'Quoted charge currency is not matching with order currency. Please change it to Order\'s currency', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.error.invalidactualchargecurrency', 'Actual charge currency is not matching with order currency. Please change it to Order\'s currency', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.error.invalidchargecurrency', 'Empty currnecy value or multiple currencies are not allowed', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.alert.accept', 'Accept', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.alert.cancel', 'Cancel', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.alert.actualcurrencychange', 'This would replace order\'s currency with new actual charge\'s currency?', 'en_CA', b'0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipment.alert.quotedcurrencychange', 'This would replace order\'s currency with new quoted charge\'s currency?', 'en_CA', b'0');

ALTER TABLE `commission` 
ADD COLUMN `total_fpa` DOUBLE(10,2) NULL DEFAULT '0.00' AFTER `total_fwd`;



CREATE TABLE `sp_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(45) NOT NULL DEFAULT 'INFO',
  `message` varchar(500) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3517 DEFAULT CHARSET=latin1;

ALTER TABLE `customer_sales` 
ADD COLUMN `comm_perc_FPA` DOUBLE NULL DEFAULT '0' AFTER `commission_percentage`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalfpa', 'Total FPA', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.commission.totalfwd', 'Total FWD', 'en_CA', 0);

ALTER TABLE `commission` 
ADD COLUMN `currency` VARCHAR(10) NULL AFTER `total_fpa`;





INSERT INTO resourcebundle (msg_id, msg_content, locale, is_fmk) VALUES ('label.salesuser.fwd', 'FWD', 'en_CA', 1);


INSERT INTO resourcebundle (msg_id, msg_content, locale, is_fmk) VALUES ('label.salesuser.fpa', 'FPA', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.display.records', 'Display Records', 'en_CA', 1);

UPDATE `carrier` SET `tracking_url`='http://www.dhl.co.uk/content/gb/en/express/tracking.shtml?AWB=*trackingnum&brand=DHL' WHERE `carrier_id`='3';

UPDATE `carrier` SET `tracking_url`='http://wwwapps.ups.com/etracking/tracking.cgi?tracknums_displayed=25&TypeOfInquiryNumber=T&HTMLVersion=4.0&InquiryNumber=*trackingnum' WHERE `carrier_id`='5';

UPDATE `carrier` SET `tracking_url`='http://shipnow.purolator.com/shiponline/track/purolatortrack.asp?pinno=*trackingnum' WHERE `carrier_id`='20';

UPDATE `carrier` SET `tracking_url`='https://www.fedex.com/fedextrack/WTRK/index.html?action=track&action=track&tracknumbers=*trackingnum,&fdx=1490' WHERE `carrier_id`='1';



insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values(' edifile.already.exist', '<div class=\"browseBox\">\r\n<h1>Unexpected Error</h1>\r\n</div>\r\n<p>If problem persists, please contact our support group. Thank you.</p>', 'en_CA', '1');

ALTER TABLE `currency_symbol` 
ADD COLUMN `language_code` VARCHAR(45) NULL AFTER `currency_name`;

UPDATE `currency_symbol` SET `language_code`='en' WHERE `id`='1';
UPDATE `currency_symbol` SET `language_code`='en' WHERE `id`='2';


ALTER TABLE `invoice` 
ADD COLUMN `fwd_total` DOUBLE NOT NULL DEFAULT '0' AFTER `ltl_total`,
ADD COLUMN `fpa_total` DOUBLE NOT NULL DEFAULT '0' AFTER `fwd_total`;

ALTER TABLE `charges` 
CHANGE COLUMN `cost` `cost` DOUBLE(10,2) NULL DEFAULT '0' ,
CHANGE COLUMN `charge` `charge` DOUBLE(10,2) NULL DEFAULT '0' ,
CHANGE COLUMN `discount_amount` `discount_amount` DOUBLE(10,2) NOT NULL DEFAULT '0' ,
CHANGE COLUMN `exchange_rate` `exchange_rate` DECIMAL(10,4) NULL DEFAULT '0.00' ;

ALTER TABLE `charges` 
CHANGE COLUMN `tariff_rate` `tariff_rate` DOUBLE(10,2) NULL DEFAULT '0' ;

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`soluship`@`%` PROCEDURE `insert_commission_amount`()
BEGIN
	DECLARE no_of_days INTEGER DEFAULT 150;
	DECLARE no_record_found INTEGER DEFAULT 0;
	DECLARE customer_id INTEGER;
	DECLARE customer_name VARCHAR(255);
    DECLARE invoice_id INTEGER;
	DECLARE invoice_num VARCHAR(45);
	DECLARE invoice_amount DOUBLE;
	DECLARE invoice_amount1 DOUBLE;
	DECLARE invoice_cost DOUBLE;
	DECLARE invoice_cost1 DOUBLE;
	DECLARE payment_status INTEGER;
	DECLARE invoice_date DATETIME;
	DECLARE chb_total DOUBLE;
	DECLARE spd_total DOUBLE;
	DECLARE ltl_total DOUBLE;
	DECLARE fwd_total DOUBLE;
	DECLARE fpa_total DOUBLE;
	DECLARE inv_currency VARCHAR(45);
	DECLARE cur_invoices CURSOR FOR
		SELECT 	i.customer_id, c.name, i.invoice_id, i.invoice_num,
				i.invoice_amount, i.invoice_cost, i.payment_status,
				i.invoice_date, i.chb_total, i.spd_total, i.ltl_total, 
				i.fwd_total, i.fpa_total, i.currency
		FROM 	invoice i, customer c
		WHERE	i.customer_id = c.customer_id
		AND		i.commission_calculated = 0
		AND		i.invoice_date BETWEEN DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL no_of_days DAY) 
		AND 	CURRENT_TIMESTAMP();
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_record_found = 1;
	DELETE FROM sp_log WHERE id <> -1;
	OPEN cur_invoices;
	GET_INVOICES: LOOP
		FETCH cur_invoices INTO customer_id, customer_name, invoice_id, invoice_num, 
							   invoice_amount, invoice_cost, payment_status,
							   invoice_date, chb_total, spd_total, ltl_total,
							   fwd_total, fpa_total, inv_currency;
        IF no_record_found = 1 THEN
			INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', 'No Data found in cur_invoices');
			LEAVE GET_INVOICES;
        END IF;
		INNERBLOCK1: BEGIN
			DECLARE user_id INTEGER;
			DECLARE sales_user VARCHAR(255);
			DECLARE spd_percent DOUBLE;
			DECLARE ltl_percent DOUBLE;
			DECLARE chb_percent DOUBLE;
			DECLARE fwd_percent DOUBLE;
			DECLARE fpa_percent DOUBLE;
			DECLARE usr_currency VARCHAR(45);
			DECLARE cur_users CURSOR FOR
				SELECT 	cs.id as user_id, cs.sales_user as sales_user,
						COALESCE(cs.comm_perc_PS,0) as spd_percent, 
						COALESCE(cs.comm_perc_PP,0) as ltl_percent, 
						COALESCE(cs.comm_perc_CHB,0) as chb_percent,
						COALESCE(cs.comm_perc_FWD,0) as fwd_percent, 
						COALESCE(cs.comm_perc_FPA,0) as fpa_percent,
						COALESCE(sy.currency_code,'CAD') as currency_code
				FROM 	customer_sales cs left join user us on cs.sales_user = us.username
						left join currency_symbol sy on 
						(sy.country_code = SUBSTRING_INDEX(SUBSTRING_INDEX( us.locale , '_', 2 ),'_',-1) 
						and sy.language_code = SUBSTRING_INDEX(us.locale , '_', 1 ))
				WHERE 	cs.customer_id = customer_id;
			OPEN cur_users;
			GET_USERS: LOOP
				FETCH cur_users INTO user_id, sales_user, spd_percent, ltl_percent,
									 chb_percent, fwd_percent, fpa_percent, usr_currency;
				IF no_record_found = 1 THEN
					INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', 'No Data found in cur_users');
                    SET no_record_found = 0;
					LEAVE GET_USERS;
				END IF;
				set @exchangeRate = 1;
				IF (STRCMP(usr_currency, inv_currency) <> 0) THEN
					INSERT INTO sp_log (`level`, `message`) VALUES ('INFO', 'Conversion Needed');
					set @exchangeRate = (SELECT COALESCE(ex.exch_rate,1) FROM exchange_rate_currency ex WHERE cur_from = inv_currency AND cur_to = usr_currency);
				END IF;
				set @commissionSPD = 0;
				set @commissionLTL = 0;
				set @commissionCHB = 0;
				set @commissionFWD = 0;
				set @commissionFPA = 0;
                INNERBLOCK2: BEGIN
					DECLARE profit DOUBLE;
                    DECLARE email_type VARCHAR(45);
					DECLARE cur_charges CURSOR FOR
						SELECT	sum(res.charge - (res.cost * res.conversion_rate)) as 							
								profit, res.email_type
						FROM 	(SELECT COALESCE(	(SELECT 	ex.exch_rate 
													FROM 	exchange_rate_currency ex 
													WHERE 	cur_from = cost_currency 
													AND		cur_to = charge_currency),1) as conversion_rate, 
										chs.cost, chs.charge, chs.email_type, chs.id, chs.is_tax
								FROM	(SELECT	ch.id, ch.charge, cs1.currency_code as charge_currency,
												ch.cost, cs2.currency_code as cost_currency, s.email_type,
												COALESCE((
													SELECT	cg.is_tax
													FROM	charge_group cg, carrier_charge_code ccc
													WHERE	ccc.carrier_id = ch.carrier_id
													AND		ccc.charge_code = ch.charge_code
													AND		(SELECT IF((ch.charge_code_level_2 is NULL),true,
															(ccc.charge_code_level_2 = ch.charge_code_level_2)))
													AND		cg.id = ccc.charge_group_id
													LIMIT 1),0) as is_tax
										FROM  	invoice_charges ic, shipping_order so, currency_symbol cs1, 
												currency_symbol cs2, charges ch, service s
										WHERE	ic.invoice_id = invoice_id
										AND  	so.order_id = ic.order_id
										AND  	ic.charge_id=ch.id
										AND  	ic.cancelled_invoice='No'
										AND  	ch.type = 1
										AND  	ch.status !=40
										AND  	s.service_id=so.service_id
										AND  	cs1.id = ch.charge_currency
										AND  	cs2.id = ch.cost_currency) as chs) as res
						WHERE res.is_tax = 0
						GROUP BY email_type;
                    OPEN cur_charges;
					GET_CHARGES: LOOP
						FETCH cur_charges INTO profit, email_type;
						IF no_record_found = 1 THEN
							INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', usr_currency);
							SET no_record_found = 0;
							LEAVE GET_CHARGES;
						END IF;
						IF (STRCMP(email_type, 'SPD') = 0)THEN
                            SET @commissionSPD = (profit * (spd_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(email_type, 'CHB') = 0)THEN
							SET @commissionCHB = (profit * (chb_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(email_type, 'LTL') = 0)THEN
							SET @commissionLTL = (profit * (ltl_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(email_type, 'FWD') = 0)THEN
							SET @commissionFWD = (profit * (fwd_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(email_type, 'FPA') = 0)THEN
							SET @commissionFPA = (profit * (fpa_percent / 100)) * @exchangeRate;
						END IF;
                    END LOOP GET_CHARGES;
                    CLOSE cur_charges;
                END INNERBLOCK2;
				set invoice_amount1 = invoice_amount*@exchangeRate;
				set invoice_cost1 = invoice_cost*@exchangeRate;
				set @commissionPayable = @commissionSPD + @commissionLTL + @commissionCHB + @commissionFWD + @commissionFPA;
				INSERT INTO commission(`customer_id`,`customer_name`,`invoice_id`,`invoice_num`,`user_id`,`sales_user`,
									   `invoice_total`,`cost_total`,`commission_payable`,`customer_paid`,`rep_paid`,
									   `date_created`,`total_spd`,`total_ltl`,`total_chb`,`total_fwd`,`total_fpa`, `currency`)
				VALUES				  (customer_id, customer_name, invoice_id, invoice_num, user_id, sales_user,
									   invoice_amount1, invoice_cost1, @commissionPayable, payment_status, payment_status,
									   invoice_date, @commissionSPD, @commissionLTL, @commissionCHB, @commissionFWD, @commissionFPA, usr_currency);
			END LOOP GET_USERS;
			CLOSE cur_users;
		END INNERBLOCK1;
    END LOOP GET_INVOICES;
    CLOSE cur_invoices;
END


drop trigger total_quoted_charges_cost
==========================================================================

DELIMITER $$
CREATE TRIGGER insert_cost_calculation AFTER INSERT ON charges FOR EACH ROW 
BEGIN
 DECLARE no_record_found INTEGER DEFAULT 0;
 DECLARE header_currency VARCHAR(10);
 DECLARE cost_currency VARCHAR(10);
 DECLARE cost DOUBLE;
 DECLARE charge DOUBLE;
 DECLARE cost_cur_id INT;
 DECLARE charge_cur_id INT;
 DECLARE charge_type INT;
 DECLARE cost_exchange DOUBLE default 1;
 DECLARE quote_cost DOUBLE;
 DECLARE actual_cost DOUBLE;
 DECLARE quote_charge DOUBLE;
 DECLARE actual_charge DOUBLE;
 DECLARE order_id INTEGER;
  DECLARE cur_charges CURSOR FOR 
  SELECT c.cost,c.type,c.cost_currency,c.charge FROM charges c 
  WHERE c.order_id=new.order_id and c.status !=40;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_record_found = 1;
 SET @quote_cost=0.0;
 SET @actual_cost=0.0;
 SET @quote_charge=0.0;
 SET @actual_charge=0.0;
 SET @order_id=new.order_id;
 
DELETE FROM sp_log WHERE id <> -1;
insert into sp_log (message) values('Insert_cost_trigger');
OPEN cur_charges; 
 GET_CHARGES: LOOP
 
 FETCH cur_charges INTO cost,charge_type,cost_cur_id,charge;
        IF no_record_found = 1 THEN
   SET @exchangeRate = 0;	
   LEAVE GET_CHARGES;
        END IF;
	SET @tcount = @tcount + 1;
	SET @header_currency=(SELECT a1.currency FROM shipping_order a1 WHERE a1.order_id=@order_id);
	SET @cost_currency=(SELECT c.currency_code FROM currency_symbol c  WHERE c.id=cost_cur_id); 
    SET @cost_exchange=(SELECT COALESCE(
	(SELECT ex.exch_rate FROM 
		exchange_rate_currency ex 
		WHERE	cur_from = @cost_currency 
		AND		cur_to = @header_currency),1));
  IF charge_type =1 then
      SET @actual_cost =@actual_cost + (cost * @cost_exchange);
	  SET @actual_charge=@actual_charge+charge;
	 ELSEIF charge_type=0 THEN
   SET @quote_cost= @quote_cost+ (cost* @cost_exchange);
   SET @quote_charge=@quote_charge+charge;
    END IF;  
    END LOOP GET_CHARGES;
  IF charge_type =1 then
		-- SET @actual_cost =@actual_cost + (cost * @cost_exchange);
		UPDATE shipping_order s SET s.actual_cost =@actual_cost,s.actual_total = @actual_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@actual_cost );
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@quote_cost);
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;

====================================================================================================================


========================================COST CALCULATION ON AFTER update===========================

DELIMITER $$
CREATE TRIGGER update_cost_calculation AFTER UPDATE ON charges FOR EACH ROW 
BEGIN
 DECLARE no_record_found INTEGER DEFAULT 0;
 DECLARE header_currency VARCHAR(10);
 DECLARE cost_currency VARCHAR(10);
 DECLARE cost DOUBLE;
  DECLARE charge DOUBLE;
 DECLARE cost_cur_id INT;
 DECLARE charge_cur_id INT;
 DECLARE charge_type INT;
 DECLARE cost_exchange DOUBLE default 1;
 DECLARE quote_cost DOUBLE;
 DECLARE actual_cost DOUBLE;
  DECLARE quote_charge DOUBLE;
 DECLARE actual_charge DOUBLE;
 DECLARE order_id INTEGER;
  DECLARE cur_charges CURSOR FOR 
  SELECT c.cost,c.type,c.cost_currency,c.charge FROM charges c 
  WHERE c.order_id=new.order_id and c.status !=40;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_record_found = 1;
 SET @quote_cost=0.0;
 SET @actual_cost=0.0;
  SET @quote_charge=0.0;
 SET @actual_charge=0.0;
 SET @order_id=new.order_id;
 

insert into sp_log (message) values('update_cost_trigger');
OPEN cur_charges; 
 GET_CHARGES: LOOP
 
 FETCH cur_charges INTO cost,charge_type,cost_cur_id,charge;
        IF no_record_found = 1 THEN
   SET @exchangeRate = 0;	
   LEAVE GET_CHARGES;
        END IF;
	SET @tcount = @tcount + 1;
	SET @header_currency=(SELECT a1.currency FROM shipping_order a1 WHERE a1.order_id=@order_id);
	SET @cost_currency=(SELECT c.currency_code FROM currency_symbol c  WHERE c.id=cost_cur_id); 
    SET @cost_exchange=(SELECT COALESCE(
	(SELECT ex.exch_rate FROM 
		exchange_rate_currency ex 
		WHERE	cur_from = @cost_currency 
		AND		cur_to = @header_currency),1));
  IF charge_type =1 then
      SET @actual_cost =@actual_cost + cost * @cost_exchange;
	  SET @actual_charge=@actual_charge+charge;
	 ELSEIF charge_type=0 THEN
   SET @quote_cost= @quote_cost+ cost* @cost_exchange;
   SET @quote_charge=@quote_charge+charge;
    END IF;  
    END LOOP GET_CHARGES;
  IF charge_type =1 then
		-- SET @actual_cost =@actual_cost + cost * @cost_exchange;
		UPDATE shipping_order s SET s.actual_cost =@actual_cost,s.actual_total = @actual_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@actual_cost );
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@quote_cost);
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;

==============================================================================================================

========================================COST CALCULATION ON AFTER delete===========================

DELIMITER $$
CREATE TRIGGER delete_cost_calculation AFTER DELETE ON charges FOR EACH ROW 
BEGIN
 DECLARE no_record_found INTEGER DEFAULT 0;
 DECLARE header_currency VARCHAR(10);
 DECLARE cost_currency VARCHAR(10);
 DECLARE cost DOUBLE;
  DECLARE charge DOUBLE;
 DECLARE cost_cur_id INT;
 DECLARE charge_cur_id INT;
 DECLARE charge_type INT;
 DECLARE cost_exchange DOUBLE default 1;
 DECLARE quote_cost DOUBLE;
 DECLARE actual_cost DOUBLE;
 DECLARE order_id INTEGER;
  DECLARE cur_charges CURSOR FOR 
  SELECT c.cost,c.type,c.cost_currency,c.charge FROM charges c 
  WHERE c.order_id=OLD.order_id and c.status !=40;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_record_found = 1;
 SET @quote_cost=0.0;
 SET @actual_cost=0.0;
  SET @quote_charge=0.0;
 SET @actual_charge=0.0;
 SET @order_id=OLD.order_id;
insert into sp_log (message) values('delete_cost_trigger');
OPEN cur_charges; 
 GET_CHARGES: LOOP
 
 FETCH cur_charges INTO cost,charge_type,cost_cur_id,charge;
        IF no_record_found = 1 THEN
   SET @exchangeRate = 0;	
   LEAVE GET_CHARGES;
        END IF;
	SET @tcount = @tcount + 1;
	SET @header_currency=(SELECT a1.currency FROM shipping_order a1 WHERE a1.order_id=@order_id);
	SET @cost_currency=(SELECT c.currency_code FROM currency_symbol c  WHERE c.id=cost_cur_id); 
    SET @cost_exchange=(SELECT COALESCE(
	(SELECT ex.exch_rate FROM 
		exchange_rate_currency ex 
		WHERE	cur_from = @cost_currency 
		AND		cur_to = @header_currency),1));
  IF charge_type =1 then
      SET @actual_cost =@actual_cost + cost * @cost_exchange;
	  SET @actual_charge=@actual_charge+charge;
	 ELSEIF charge_type=0 THEN
   SET @quote_cost= @quote_cost+ cost* @cost_exchange;
   SET @quote_charge=@quote_charge+charge;
    END IF;  
    END LOOP GET_CHARGES;
  IF charge_type =1 then
		-- SET @actual_cost =@actual_cost + cost * @cost_exchange;
		UPDATE shipping_order s SET s.actual_cost =@actual_cost,s.actual_total = @actual_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@actual_cost );
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
		insert into sp_log (message) values(@order_id);
		insert into sp_log (message) values(@quote_cost);
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;

insert into resourcebundle (msg_id,msg_content,locale,is_fmk)
values('invoice.breakedown.FWD','FWD','en_CA',0);
insert into resourcebundle (msg_id,msg_content,locale,is_fmk)
values('invoice.breakedown.FPA','FPA','en_CA',0);
UPDATE carrier SET tracking_url='https://onlineservices.midlandcourier.com/cgi-bin/wspd_cgi.sh/reftrace.htm?wbill-no=*trackingnum' WHERE carrier_id='80';

create table schedular(id int(5),host varchar(100),scheduler_flag tinyint(1));

insert into schedular values(1,'localhost',0);
insert into schedular values(2,'live',1);
insert into schedular values(3,'test',0);
insert into schedular values(4,'test1',0);
insert into schedular values(5,'zipdandy',0);

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`%` PROCEDURE `sp_for_old_records`(
			IN start_date DATE,
 			IN end_date   DATE)
BEGIN

			DECLARE cur_invoiceId  INT(10);
			DECLARE temp_invoiceId INT(10);
			DECLARE emailtype      VARCHAR(5);
			DECLARE cur_emailtype  VARCHAR(5);
			DECLARE done           INT(1);
			DECLARE totcharge      DOUBLE(10,2);
			DECLARE cur_charge     DOUBLE(10,2);
			DECLARE second_done    INT(1);

			DECLARE invoice_cursor CURSOR FOR SELECT invoice_id from invoice where date_created between start_date and end_date;
			DECLARE continue handler for not found set done=1;
				SET done = 0;
				OPEN invoice_cursor;
					igmLoop: loop
						FETCH invoice_cursor into cur_invoiceId;
						IF done = 1 then leave igmLoop; end if;
							SET @temp_invoiceId = (select invoice_id from invoice where invoice_id = cur_invoiceId);
								BLOCK2: BEGIN
								DECLARE invoice_cursortwo CURSOR FOR select sum(charge),res.email_type 
									from(
										SELECT 	s.email_type,
										coalesce((
											select 	cg.is_tax
											from 	charge_group cg,
													carrier_charge_code ccc
											where	ccc.carrier_id = ch.carrier_id
											and		ccc.charge_code = ch.charge_code
											and		(SELECT IF((ch.charge_code_level_2 is NULL),true,(ccc.charge_code_level_2 = ch.charge_code_level_2)))
											and		cg.id = ccc.charge_group_id
											limit 1
										),0) as is_tax,
										ch.charge AS charge 
								FROM 	invoice_charges ic,
										shipping_order so,
										charges ch, service s
								WHERE 	ic.invoice_id = @temp_invoiceId
								and 	so.order_id = ic.order_id
								and 	ic.charge_id=ch.id
								and  	ic.cancelled_invoice='No'
								and 	ch.type = 1    
								and 	s.service_id=so.service_id
								) as res
								 where res.is_tax = 0
								group by res.email_type ;

			DECLARE continue handler for not found set second_done=1;
			SET second_done = 0;
				OPEN invoice_cursortwo;
					second_igmLoop: loop
						FETCH invoice_cursortwo into cur_charge,cur_emailtype;
						IF second_done = 1 then leave second_igmLoop; end if;
								SET @totcharge = cur_charge; 
								SET @emailtype = cur_emailtype;
									IF @emailtype='SPD' THEN
										UPDATE invoice SET spd_total=@totcharge where invoice_id =@temp_invoiceId;
										ELSEIF @emailtype='LTL' THEN
										UPDATE invoice SET ltl_total=@totcharge where invoice_id =@temp_invoiceId;
										ELSEIF  @emailtype='CHB' THEN
										UPDATE invoice SET chb_total=@totcharge where invoice_id =@temp_invoiceId;
										ELSEIF  @emailtype='FWD' THEN
										UPDATE invoice SET fwd_total=@totcharge where invoice_id =@temp_invoiceId;
										ELSE
										UPDATE invoice SET fpa_total=@totcharge where invoice_id =@temp_invoiceId;
									END IF;
					END loop second_igmLoop;
			   CLOSE invoice_cursortwo;
			END BLOCK2;
    	END loop igmLoop;
	CLOSE invoice_cursor;
END


insert into resourcebundle(msg_id,msg_content,locale,is_fmk) values ('label.system.log.edi.message','EDI Upload Processing - Address correction occured!','en_CA',1);

CREATE TABLE `future_customer` (
  `future_customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NULL,
  `name` VARCHAR(255) NULL,
  `from_city` VARCHAR(45) NULL,
  `from_state` VARCHAR(45) NULL,
  `from_country` VARCHAR(45) NULL,
  `to_city` VARCHAR(45) NULL,
  `to_state` VARCHAR(45) NULL,
  `to_country` VARCHAR(45) NULL,
  PRIMARY KEY (`future_customer_id`));




alter table future_customer add foreign key(customer_id) references customer(customer_id)



ALTER TABLE `future_customer` 
ADD COLUMN `created` DATETIME NULL AFTER `to_country`;

ALTER TABLE `future_customer` 
ADD COLUMN `createdBy` INT(11) NULL AFTER `created`;

ALTER TABLE `future_customer` 
RENAME TO  `future_Reference` ;



INSERT INTO  `menu` (`id`, `name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`) VALUES ('432', 'Future Reference', '/admin/futRef.action', '4', 'LEVEL_1', '1', '400', 'menu.admin.futureReference', '');

INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.futureReference', 'Future Reference', 'en_CA', 1, '');

INSERT INTO `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('432', 'busadmin', '');

INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '432', '');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`, `parent_action_id`, `namespace`) VALUES ('futRef', '432', 1, 'Future References', 1, '1022', '', 'admin');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1022');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('futRef1', '432', 1, 'Future References Delete', 1, '1023');

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1023');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('futRef2', '432', 1, 'Future Reference  view', 1, '1024');


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1024');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.name', 'Name', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.fromstate', 'From State', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.from country', 'FromCountry', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.tostate', 'To State', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.tocountry', 'To Country', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.delete', 'DELETE', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.customerid', 'CustomerID', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.fromcity', 'From City', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.tocity', 'To City', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.datecreated', 'Date Created', 'en_CA');



INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.view', 'View', 'en_CA');



ALTER TABLE `future_reference` 
ADD COLUMN `ship_from_address` VARCHAR(200) NULL AFTER `createdBy`;

ALTER TABLE `future_reference` 
ADD COLUMN `ship_to_address` VARCHAR(45) NULL AFTER `ship_from_address`;

ALTER TABLE `future_reference` 
ADD COLUMN `no_of_packages` INT(50) NULL AFTER `ship_to_address`;

ALTER TABLE `future_reference` 
ADD COLUMN `service_type` VARCHAR(45) NULL AFTER `no_of_packages`;

ALTER TABLE `future_reference` 
ADD COLUMN  `ship_schedule_date` VARCHAR(45) NULL ;

ALTER TABLE `future_reference` 
ADD COLUMN `ship_from_email` VARCHAR(45) NULL AFTER `ship_schedule_date`,
ADD COLUMN `ship_to_email` VARCHAR(45) NULL AFTER `ship_from_email`,
ADD COLUMN `ship_from_phone` VARCHAR(45) NULL AFTER `ship_to_email`,
ADD COLUMN `ship_to_phone` VARCHAR(45) NULL AFTER `ship_from_phone`;

ALTER TABLE `future_reference` 
ADD COLUMN `from_company` VARCHAR(45) NULL AFTER `ship_to_phone`,
ADD COLUMN `to_company` VARCHAR(45) NULL AFTER `from_company`;


CREATE TABLE `future_ref_packages` (
  `fut_ref_pack_id` INT NOT NULL AUTO_INCREMENT,
  `pack_length` DECIMAL(10,4) NULL,
  `pack_width` DECIMAL(10,4) NULL,
  `pack_height` DECIMAL(10,4) NULL,
  `pack_weight` DECIMAL(10,4) NULL,
  `pack_cod_amount` DECIMAL(10,4) NULL,
  `pack_insurance_amount` DECIMAL(10,4) NULL,
  `pack_description` VARCHAR(100) NULL,
  `customer_id` INT(11) NULL,
  `future_reference_id` INT(15) NULL,
  PRIMARY KEY (`fut_ref_pack_id`));


ALTER TABLE `future_ref_packages` 
DROP FOREIGN KEY `future_ref_packages_ibfk_1`;
ALTER TABLE `future_ref_packages` 
ADD CONSTRAINT `future_ref_packages_ibfk_1`
  FOREIGN KEY (`future_reference_id`)
  REFERENCES `future_reference` (`future_reference_id`)
  ON DELETE CASCADE
  ON UPDATE RESTRICT;



alter table future_ref_packages add foreign key(future_reference_id) references future_reference(future_reference_id)


ALTER TABLE `future_reference` 
ADD COLUMN `from_residential` TINYINT NULL AFTER `to_company`,
ADD COLUMN `to_residential` TINYINT NULL AFTER `from_residential`;

ALTER TABLE `soluship`.`future_reference` 
CHANGE COLUMN `from_residential` `from_residential` BIT(1) NULL DEFAULT b'0' ,
CHANGE COLUMN `to_residential` `to_residential` BIT(1) NULL DEFAULT NULL ;



INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.backfr', 'Back', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.ship.from', 'Ship From', 'en_CA');


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.shipfrom.company', 'Company', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.shipfrom.residential', 'Residential', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.shipfrom.address', 'Address', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.from.email', 'Email', 'en_CA');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.from.phoneno', 'Phone No', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.shipto', 'Ship To', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.shipto.company', 'Company', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.shipto.residential', 'Residential', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.shipto.address', 'Address', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.add.shipto.email', 'Email', 'en_CA', 1, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.shipto.phoneno', 'Phone No', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.future.services', 'SERVICES', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.service.type', 'Service Type', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.ship.date', 'Ship Date', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.add.package.details', 'Package Details', 'en_CA', 1, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.no.of.packages', 'Number Of Packages', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`) VALUES ('label.add.view.future.refenence', 'View Future Reference', 'en_CA');



ALTER TABLE `future_reference` 
CHANGE COLUMN `from_residential` `from_residential` BIT(1) NOT NULL DEFAULT b'0' ,
ADD COLUMN `ready_time` VARCHAR(45) NULL DEFAULT NULL AFTER `to_residential`,
ADD COLUMN `close_time` VARCHAR(45) NULL DEFAULT NULL AFTER `ready_time`,
ADD COLUMN `pickup_instruction` VARCHAR(200) NULL DEFAULT NULL AFTER `close_time`,
ADD COLUMN `pickup_location` VARCHAR(105) NULL DEFAULT NULL AFTER `pickup_instruction`,
ADD COLUMN `from_contact_name` VARCHAR(45) NULL DEFAULT NULL AFTER `pickup_location`,
ADD COLUMN `to_contact_name` VARCHAR(45) NULL DEFAULT NULL AFTER `from_contact_name`,
ADD COLUMN `from_postal_code` VARCHAR(45) NULL DEFAULT NULL AFTER `to_contact_name`,
ADD COLUMN `to_postal_code` VARCHAR(45) NULL DEFAULT NULL AFTER `from_postal_code`;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.from.attention', 'Attention', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.from.postal.zipcode', 'Postal/zip Code', 'en_CA', 1);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.toattention', 'Attention', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.to.postal.zipcode', 'Postal Code/Zip Code', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.quick.ship', 'Quick Ship', 'en_CA', 1);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.ready.time', 'Ready Time', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.close.time', 'Close Time', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.quickship.location', 'Location', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.quickship.instruction', 'Instruction', 'en_CA', 1);

ALTER TABLE `future_reference` 
ADD COLUMN `documents_only` BIT(1) NULL DEFAULT b'0' AFTER `to_postal_code`;

ALTER TABLE `future_reference` 
ADD COLUMN `appointment_pickup` BIT(1) NULL DEFAULT b'0' AFTER `documents_only`;

ALTER TABLE `future_reference` 
ADD COLUMN `tradeshow_pickup` BIT(1) NULL DEFAULT b'0' AFTER `appointment_pickup`;

ALTER TABLE `future_reference` 
ADD COLUMN `taligate_delivery` BIT(1) NULL DEFAULT b'0' AFTER `tradeshow_pickup`;


ALTER TABLE `future_reference` 
ADD COLUMN `trade_show_delivery` BIT(1) NULL DEFAULT b'0' AFTER `taligate_delivery`,
ADD COLUMN `appointment_delivery` BIT(1) NULL DEFAULT b'0' AFTER `trade_show_delivery`,
ADD COLUMN `inside_pickup` BIT(1) NULL DEFAULT b'0' AFTER `appointment_delivery`,
ADD COLUMN `taligate_pickup` BIT(1) NULL DEFAULT b'0' AFTER `inside_pickup`;


ALTER TABLE `future_reference` 
ADD COLUMN `dangerous_goods` VARCHAR(85) NULL DEFAULT NULL AFTER `taligate_pickup`,
ADD COLUMN `ref_code` VARCHAR(85) NULL DEFAULT NULL AFTER `dangerous_goods`,
ADD COLUMN `hold_for_pickup` BIT(1) NULL DEFAULT b'0' AFTER `fef_code`;

ALTER TABLE `future_reference` 
ADD COLUMN `saturday_delivery` BIT(1) NULL DEFAULT b'0' AFTER `hold_for_pickup`,
ADD COLUMN `signature_required` VARCHAR(85) NULL DEFAULT NULL AFTER `saturday_delivery`,
ADD COLUMN `dutiable_code` VARCHAR(85) NULL DEFAULT NULL AFTER `signature_required`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.dangerous.goods', 'Dangerous Goods', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.documents.only', 'Documents Only', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.trade.show.pickup', 'Trade Show Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.ref.code', 'Ref.Code', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.appointment.pickup', 'Appointment Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.taligate.delivery', 'Taligate Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.hold.pickup', 'Hold For Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.trade.show.delivery', 'Trade Show Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.appointment.delivery', 'Appointment Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.saturday.delivery', 'Saturday Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.inside.pickup', 'Inside Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.add.taligate.pickup', 'Taligate Pickup', 'en_CA', 1, '');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.signature.required', 'Signature Required', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.dutiable.code', 'Dutiable Amount', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.fromcountry', 'From Country', 'en_CA', 1);


ALTER TABLE `future_reference` 
ADD COLUMN `notify_shipper` BIT(1) NULL DEFAULT b'0' AFTER `dutiable_code`,
ADD COLUMN `notify_consignee` BIT(1) NULL DEFAULT b'0' AFTER `notify_shipper`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.notify.shipper', 'Notify Shipper', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.notify.consignee', 'Notify Consignee', 'en_CA', 1);



ALTER TABLE `future_reference` 
RENAME TO  `quote` ;

ALTER TABLE `future_ref_packages` 
RENAME TO  `quote_packages` ;

ALTER TABLE `future_ref_packages` 
ADD INDEX `future_ref_packages_ibfk_1_idx` (`future_reference_id` ASC);
ALTER TABLE `future_ref_packages` 
ADD CONSTRAINT `future_ref_packages_ibfk_1`
  FOREIGN KEY (`future_reference_id`)
  REFERENCES `future_reference` (`future_reference_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;

  
alter table business add report_path varchar(60), add report_pathinvoice varchar(60);
alter table customer add status varchar(60) default 'Prospect';

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('add.label.status.customer', 'Status', 'en_CA', 1);

alter table customer add available_credit varchar(60)

insert into resourcebundle (msg_id,msg_content,locale,is_fmk) values ('label.customer.availableCredit', 'Available Credit:', 'en_CA', 1)

alter table customer add hold_terms int(10) unsigned default 65;

alter table business add default_hold_terms int(10) unsigned default 0;

insert into resourcebundle (msg_id,msg_content,locale,is_fmk) values('label.customer.hold.terms', 'Hold Terms (Days)', 'en_CA',1);

UPDATE `business` SET `default_hold_terms`='65' WHERE `business_id`='1';

update customer c,invoice i set c.status='Customer' where c.customer_id=i.customer_id

ALTER TABLE shipping_order 
ADD COLUMN `link_to_order` INT(11) NULL DEFAULT NULL AFTER `followup`;


INSERT INTO resourcebundle (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.viewship.link.to.shipid', 'Link To', 'en_CA', 1);


SET SQL_SAFE_UPDATES = 0;


update shipping_order set link_to_order=0;

--Business Filter Query
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.managerole', 'Manage Role', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.menuName', 'Menu', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.Menuaction.actionName', 'Action', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.Menuaction.business', 'Business', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.name', 'Role', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.addNewMenu', 'Add New Menu', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.menuName', 'Menu Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.menuUrl', 'Url', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.menuLevel', 'Menu Level', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.menuLevel', 'Menu Level', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.resourcebundle', 'Resource Bundle', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.msgkey', 'Message key', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.msgContent', 'Message Content', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.locale', 'Locale', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.menuRole', 'Role', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.role', 'Role', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.business', 'Business', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.displayOrder', 'Display Order', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.topLevel', 'Top Level', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.firstLevel', 'First Level', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.msgLocale', 'Locale', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.actionName', 'Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.menu', 'Menu', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.highlight', 'isHighlighted', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.description', 'Description', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.shortCode', 'Short Code', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.name', 'Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.systemName', 'System Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.logOutUrl', 'Log Out URL', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.storeCC', 'Store CC', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.logoUrl', 'Logo URL', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.subDomain', 'SubDomain', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.supportUrl', 'Support URL', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.termsUrl', 'Terms URL', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.smtpHost', 'Smtp Host', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.smtpUsername', 'Smtp Username', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.smtpPassword', 'Smtp Password', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.smtpPort', 'Smtp Port', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.roleName', 'Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.roleDescription', 'Description', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.newRole', 'New Role', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.actions', 'Actions', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.roleActions', 'Action(s)', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.roleMenu', 'Menu(s)', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.titleAction', 'New Action', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.addNewBusiness', 'New Business', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.menuName', 'Menu', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('role.admin.roleHeader', 'Edit Role', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.editBusiness ', 'Edit Business', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.address ', 'Address', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.abbreviationName ', 'Abbreviation Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.addressone ', 'Address 1', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.addresstwo ', 'Address 2', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.city ', 'City', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.phoneno', 'Phone Number', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.faxno', 'Fax Number', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.mobilePhoneNumber', 'Mobile Number', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.emailAddress', 'E-Mail', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.contactName', 'Contact Name', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.postalCode', 'Postal Code', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.url', 'URL', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.residential', 'Residential', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.provinceCode', 'Province Code', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.residential', 'Residential Address', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.defaultFrom', 'Default From', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.defaultTo', 'Default To', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.sendNotification', 'Send Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.businessfilter.labelName', 'Business Filter', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.partner.edit.partner', 'EDIT PARTNER', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.partner.add.partner', 'ADD PARTNER', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.partner.partner.name', 'PARTNER NAME', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.short.code', 'SHORT CODE', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.partner.partner', 'PARTNER', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.partner.list', 'PARTNER LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.view.country', 'VIEW NATION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.country', 'ADD NATION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.nation', 'NATION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business', 'BUSINESS', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.nation.list', 'NATION LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch.list', 'BRANCH LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch.add', 'ADD BRANCH', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch.view', 'VIEW BRANCH', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch.name', 'BRANCH NAME', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.desc', 'DESCRIPTION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch', 'BRANCH', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.name', 'Role', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.add', 'ADD ACTION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.name', 'ACTION NAME', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.highlight', 'HIGHLIGHT', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action.list', 'ACTION LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.action', 'ACTION', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.add', 'ADD MENU', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.list', 'MENU LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.menu.parentid', 'PARENT ID', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.list', 'ROLE LIST', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.role.add', 'ADD ROLE', 'en_CA', 0);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.newbranch', 'Add Branch', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.partner.country', 'PARTNER COUNTRY', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.default.loader', 'DEFAULT LOADERS', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.partner.allcountry', 'ALL COUNTRIES', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.branch.nation.partner', 'SELECT A PARTNER AND COUNTRY', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.newbranch', 'Add Branch', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.newpartner', 'Add Partner', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.businessfilter', 'Business Filter', 'en_CA', 1, '');

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('433', 'Manage Role', '/admin/list.menu.action', '4', 'LEVEL_1', '1', '111', 'menu.admin.managerole', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('434', 'Menu', '/admin/list.menu.action', '1', 'LEVEL_2', '2', '433', 'menu.admin.menuName', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('435', 'Action', '/admin/list.strutsAction.action', '2', 'LEVEL_2', '0', '433', 'label.Menuaction.actionName', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('436', 'Role', '/admin/list.role.action', '3', 'LEVEL_2', '0', '436', 'label.role.name', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('437', 'Business Filter', '/admin/list.business.action', '5', 'LEVEL_1', '1', '111', 'menu.admin.businessfilter', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('438', 'Business ', '/admin/list.business.action', '1', 'LEVEL_2', '0', '437', 'label.Menuaction.business', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'

);

 INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('439', 'Partner', '/admin/list.partner.action', '2', 'LEVEL_2', '2', '437', 'menu.admin.partner', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'

);

 INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('440', 'Nation', '/admin/list.countrypartner.action', '3', 'LEVEL_2', '2', '437', 'menu.admin.countrypartner', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `menu`
(`id`,
`name`,
`url`,
`display_order`,
`level`,
`level_no`,
`parent_id`,
`label_id`,
`image`,
`image_over`,
`help_tag`,
`support_tag`)
VALUES
('441', 'Branch', '/admin/list.branch.action', '4', 'LEVEL_2', '2', '437', 'menu.admin.branch', '', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>'
);

INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('433', 'sysadmin', '');
 INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('434', 'sysadmin', '');
 INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('435', 'sysadmin', '');
 INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('436', 'sysadmin', '');
 
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('438', 'sysadmin', '');
  
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('437', 'sysadmin', '');
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('437', 'busadmin', '');

  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('439', 'sysadmin', '');
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('439', 'busadmin', '');

   INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('440', 'sysadmin', '');
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('440', 'busadmin', '');

  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('441', 'sysadmin', '');
  INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('441', 'busadmin', '');
  
  INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '433', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '434', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '435', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '436');
INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '437', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '438', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`, `bm_id`) VALUES ('1', '439', '');
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '440');
INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '441');

ALTER TABLE `business` 
ADD COLUMN `isPartner` BIT(1) NOT NULL DEFAULT b'0' AFTER `report_pathinvoice`,
ADD COLUMN `isNation` BIT(1) NOT NULL DEFAULT b'0' AFTER `isPartner`,
ADD COLUMN `isBranch` BIT(1) NOT NULL DEFAULT b'0' AFTER `isNation`,
ADD COLUMN `partner_id` INT(10) NOT NULL DEFAULT 0 AFTER `isBranch`,
ADD COLUMN `country_partner_id` INT(10) NOT NULL DEFAULT 0 AFTER `partner_id`,
ADD COLUMN `branch_id` INT(10) NOT NULL DEFAULT 0 AFTER `country_partner_id`,
ADD COLUMN `partner_business_id` INT(10) NOT NULL DEFAULT 0 AFTER `branch_id`;

ALTER TABLE `user_history` 
ADD COLUMN `ispartner_level` BIT(1) NULL DEFAULT b'0' AFTER `bo_id`,
ADD COLUMN `isnation_level` BIT(1) NULL DEFAULT b'0' AFTER `ispartner_level`,
ADD COLUMN `isbranch_level` BIT(1) NULL DEFAULT b'0' AFTER `isnation_level`,
ADD COLUMN `isdivision_level` BIT(1) NULL DEFAULT b'0' AFTER `isbranch_level`,
ADD COLUMN `spd_enabled` BIT(1) NULL DEFAULT b'0' AFTER `isdivision_level`,
ADD COLUMN `ltl_enabled` BIT(1) NULL DEFAULT b'0' AFTER `spd_enabled`,
ADD COLUMN `chb_enabled` BIT(1) NULL DEFAULT b'0' AFTER `ltl_enabled`,
ADD COLUMN `fwd_enabled` BIT(1) NULL DEFAULT b'0' AFTER `chb_enabled`,
ADD COLUMN `fpa_enabled` BIT(1) NULL DEFAULT b'0' AFTER `fwd_enabled`,
ADD COLUMN `isbusiness_level` BIT(1) NULL DEFAULT b'0' AFTER `fpa_enabled`;

ALTER TABLE `user` 
ADD COLUMN `ispartner_level` BIT(1) NULL DEFAULT b'0' AFTER `bo_id`,
ADD COLUMN `isnation_level` BIT(1) NULL DEFAULT b'0' AFTER `ispartner_level`,
ADD COLUMN `isbranch_level` BIT(1) NULL DEFAULT b'0' AFTER `isnation_level`,
ADD COLUMN `isdivision_level` BIT(1) NULL DEFAULT b'0' AFTER `isbranch_level`,
ADD COLUMN `spd_enabled` BIT(1) NULL DEFAULT b'0' AFTER `isdivision_level`,
ADD COLUMN `ltl_enabled` BIT(1) NULL DEFAULT b'0' AFTER `spd_enabled`,
ADD COLUMN `chb_enabled` BIT(1) NULL DEFAULT b'0' AFTER `ltl_enabled`,
ADD COLUMN `fwd_enabled` BIT(1) NULL DEFAULT b'0' AFTER `chb_enabled`,
ADD COLUMN `fpa_enabled` BIT(1) NULL DEFAULT b'0' AFTER `fwd_enabled`,
ADD COLUMN `isbusiness_level` BIT(1) NULL DEFAULT b'0' AFTER `fpa_enabled`;


INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('addmenu.listTopMenu', '434', 0, 'addmenu.listTopMenu', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('save.menu', '434', 0, 'saving the menu', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.menu', '434', 0, 'edit the menu', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.strutsAction', '434', 0, ' list.strutsAction', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('new.strutsAction', '434', 0, 'new.strutsAction', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('add.strutsAction', '434', 0, 'add.strutsAction', 1, '');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('save.business', '434', 0, 'save.business', 1, '');
	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.role', '434', 0, 'edit.role', 1, '');
	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('save.role', '434', 0, 'save.role', 1, '');
 	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('new.role', '434', 0, 'new.role', 1, '');
 	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.role', '434', 0, 'list.role', 1, '');
	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('delete.role', '434', 0, 'delete.role', 1, '');
	INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.strutsAction', '434', 0, 'edit.strutsAction', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('delete.strutsAction', '434', 0, 'delete.strutsAction', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('delete.menu', '434', 0, 'delete.menu', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.menu', '434', 0, 'list.menu', 1, '');
    INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('add.menu', '434', 0, 'add.menu', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('new.strutsAction', '434', 0, 'new.strutsAction', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.businessfilter', '434', 0, 'list.businessfilter', 1, '');
    INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.business', '438', 0, 'edit.business', 1, '');
     INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.business', '437', 0, 'list.business', 1, '');
     INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('new.business', '437', 0, 'new.business', 1, '');
     
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1025', '');
INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1026', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1027', '');
INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1028', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1029', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1030', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1031', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1032', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1033', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1034', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1035', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1036', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1037', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1038', '');
  INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1040', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1041', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1042', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1043', '');
  INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1044', '');
   INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1039', '');
  INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1045', '');
   INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1046', '');
   
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('addPartner', '439', 0, 'addPartner', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.partner', '439', 0, 'list.partner', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('createPartner', '439', 0, 'createPartner', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.partner', '439', 0, 'edit.partner', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('addNation', '439', 0, 'addNation', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('createNation', '439', 0, 'createNation', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('delete.partner', '439', 0, 'delete.partner', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.nation', '439', 0, 'edit.nation', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.branch', '439', 0, 'edit.branch', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('set.branch', '439', 0, 'set.branch', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('login.business', '439', 0, 'login.business', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('login.partner', '439', 0, 'login.partner', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('login.country', '439', 0, 'createNation', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('login.branch', '439', 0, 'login.branch', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('listCountryName', '439', 0, 'listCountryName', 1, '');
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('listBranchName', '439', 0, 'listBranchName', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('viewPartner', '439', 0, 'viewPartner', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('viewCountry', '439', 0, 'viewCountry', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('viewBranch', '439', 0, 'viewBranch', 1, '');
  INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('viewBus', '439', 0, 'viewBus', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.countrypartner', '440', 0, 'list.countrypartner', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('list.branch', '441', 0, 'list.countrypartner', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('addbranch', '441', 0, 'addbranch', 1, '');
   INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('saveBranch', '441', 0, 'saveBranch', 1, '');
   
   INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1047', '');
INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1047', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1048', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1048', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1049', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1049', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1050', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1050', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1051', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1051', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1052', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1052', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1053', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1053', '');
       INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1054', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1054', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1055', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1055', '');
      INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1056', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1056', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1057', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1057', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1058', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1058', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1059', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1059', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1060', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1060', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1061', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1061', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1062', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1062', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1063', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1063', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1064', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1064', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1065', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1065', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1066', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1066', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1067', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1067', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1068', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1068', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1069', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1069', '');
 INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1070', '');
     INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1070', '');
     
     INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('111', 'sysadmin', '');
INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('439', 'sysadmin', '');
INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('440', 'sysadmin', '');
INSERT INTO  `role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('441', 'sysadmin', '');

INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.countrypartner', 'Nation', 'en_CA', 1, '');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.branch', 'Branch', 'en_CA', 1, '');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.headerKey', 'Header Key', 'en_CA', 1, '');

ALTER TABLE  `quote` 
ADD COLUMN `business_id` INT(10) NULL AFTER `followed_up_by`;

INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('shippingOrder.unpaid.invoice.error', 'your account is on hold for severely past due invoices', 'en_CA', 1, '');

CREATE TABLE `customer_business` (
  `customer_business_id` int(10) NOT NULL AUTO_INCREMENT,
  `business_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `partner_id` int(10) DEFAULT NULL,
  `branch_id` int(10) DEFAULT NULL,
  `country_partner_id` int(10) DEFAULT NULL,
  `ispartner_level` bit(1) DEFAULT b'0',
  `isnation_level` bit(1) DEFAULT b'0',
  `isbranch_level` bit(1) DEFAULT b'0',
  `isdivition_level` bit(1) DEFAULT b'0',
  `isbusiness_level` bit(1) DEFAULT b'0',
  PRIMARY KEY (`customer_business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

CREATE TABLE `partner` (
  `partner_id` int(10) NOT NULL AUTO_INCREMENT,
  `short_code` varchar(45) DEFAULT NULL,
  `address_id` int(10) unsigned DEFAULT NULL,
  `business_id` int(10) unsigned DEFAULT NULL,
  `partner_name` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`partner_id`),
  UNIQUE KEY `partner_name_UNIQUE` (`partner_name`),
  UNIQUE KEY `short_code_UNIQUE` (`short_code`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

CREATE TABLE `branch` (
  `branch_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `short_code` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `country_partner_id` int(10) DEFAULT NULL,
  `business_id` int(10) DEFAULT NULL,
  `partner_id` int(10) DEFAULT NULL,
  `address_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

CREATE TABLE `user_filter` (
  `user_filter_id` int(10) NOT NULL AUTO_INCREMENT,
  `division_id` int(10) DEFAULT '0',
  `business_id` int(10) unsigned DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `country_partner_id` int(10) DEFAULT NULL,
  `partner_id` int(10) DEFAULT NULL,
  `branch_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_filter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

CREATE TABLE `country_partner` (
  `country_partner_id` int(10) NOT NULL AUTO_INCREMENT,
  `partner_id` int(10) DEFAULT NULL,
  `business_id` int(10) DEFAULT NULL,
  `country_code` varchar(45) NOT NULL,
  `address_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`country_partner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

ALTER TABLE `newsoluship`.`business` 
CHANGE COLUMN `partner_business_id` `parent_business_id` INT(10) NOT NULL DEFAULT '0' ;

INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`, `role_menu_id`) VALUES ('106', 'sysadmin', '');

DELETE FROM `newsoluship`.`role_menu` WHERE `role_menu_id`='222';
DELETE FROM `newsoluship`.`role_menu` WHERE `role_menu_id`='215';
DELETE FROM `newsoluship`.`role_menu` WHERE `role_menu_id`='213';
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('432', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('428', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('419', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('418', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('417', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('416', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('410', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('409', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('408', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('407', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('405', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('402', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('401', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('400', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('271', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('270', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('261', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('222', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('202', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('185', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('184', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('182', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('181', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('172', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('171', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('162', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('161', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('152', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('151', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('142', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('141', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('123', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('122', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('121', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('113', 'sysadmin');
INSERT INTO `newsoluship`.`role_menu` (`menu_id`, `role`) VALUES ('112', 'sysadmin');

INSERT INTO `newsoluship`.`resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk` ) VALUES ('menu.admin.partner', 'Partner', 'en_CA', 1);

update user set isnation_level=false and default_menu_id=437 where username='jcook35'

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('add.label.error.exception', 'Exception Details', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.user.business', 'User Business', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business', 'Business', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('menu.admin.rootBusiness', 'Root Business', 'en_CA', 1, '');


select * from action order by id desc

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('ajax.getchildBus', '437', 0, 'get partner business', 1, '');
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1071', '');
 
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('new.userBus', '437', 0, 'add user bus', 1);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1072', '');



CREATE TABLE `user_business` (
  `user_business_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `parent_buiness_id` int(10) DEFAULT '-1',
  `partner_id` int(10) DEFAULT '-1',
  `nation_id` int(10) DEFAULT '-1',
  `branch_id` int(10) DEFAULT '-1',
  PRIMARY KEY (`user_business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('add.label.flat', 'Flat', 'en_CA', 1);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('add.label.markup', 'Markup', 'en_CA', 1);
ALTER TABLE `user` 
ADD COLUMN `summary_label` BIT(1) NULL DEFAULT b'0' AFTER `isbusiness_level`;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.summary.label', 'Summary Label', 'en_CA', 1);



ALTER TABLE `user_history` 
ADD COLUMN `summary_label` BIT(1) NULL DEFAULT b'0' AFTER `log_date`;


ALTER TABLE `user_history` 
CHANGE COLUMN `summary_label` `summary_label` BIT(1) NULL DEFAULT b'0' AFTER `isbusiness_level`;

#new Tables:

 CREATE TABLE `business_email` (
  `business_email_id` int(10) NOT NULL AUTO_INCREMENT,
  `email_type` varchar(60) DEFAULT NULL,
  `locale` varchar(45) DEFAULT NULL,
  `msg_id` varchar(150) DEFAULT NULL,
  `business_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`business_email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;


 CREATE TABLE `business_loader` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_id` int(10) unsigned NOT NULL,
  `grid_header_color` varchar(30) DEFAULT NULL,
  `menu_color` varchar(30) NOT NULL,
  `menu_hover_color` varchar(30) NOT NULL,
  `button_color` varchar(30) NOT NULL,
  `bar_first_color` varchar(30) NOT NULL,
  `bar_second_color` varchar(30) NOT NULL,
  `footer_color` varchar(45) NOT NULL,
  `css_text` longtext,
  `logo_img` blob,
  `back_ground_img` blob,
  `fav_icon` blob,
  `email_header` blob,
  PRIMARY KEY (`id`),
  KEY `business_id` (`business_id`),
  CONSTRAINT `business_loader_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
 
#--------------------------------------------------------------
#actions

INSERT INTO  `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('ImageAction', '261', 1, 'IMAGE LOAD', 1, '1073');
INSERT INTO  `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1073' );
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('busadmin', '1073' );
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('customer_admin', '1073' );
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('customer_base', '1073' );
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('customer_shipper', '1073' );

INSERT INTO  `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('add.emailcont', '437', 1, 'Add business email cont', 1, '1074');
INSERT INTO  `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sysadmin', '1074', '');
 
#--------------------------------------------------------------



#Resource bundle
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.back.logo', 'Upload BackGround Image', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.buisness.email.html', 'Business Email Contents', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.header', 'Email Header Image', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.ship.confim', 'Shipment Conformation', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailPickup', 'Shipment Pick Up Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailShipCancel', 'Shipment Cancel Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailNewCustomer', 'New Customer Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailCusNotify', 'Customer Email Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailInvoiceNotify', 'Invoice Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailSalesRepNewCust', 'Sale User New Customer Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailRateNotify', 'Shipment Rate Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.email.emailWareHouse', 'Whare House Order Creation Notification', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.buisness.email.type', 'Business Email Type', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.buisness.email.html.cont', 'Email HTML Content', 'en_CA', 1);
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.menu.color', 'Menu Color', 'en_CA', 1, '5574');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.buisness.theme', 'BUSINESS THEME', 'en_CA', 1, '5575');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.menu.color.hover', 'Menu Color Hover', 'en_CA', 1, '5576');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.button.color', 'Button Color', 'en_CA', 1, '5577');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.header.bar.first.color', 'Header Bar First Color', 'en_CA', 1, '5578');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.header.bar.second.color', 'Header Bar Second Color', 'en_CA', 1, '5579');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.footer.color', 'Footer Color', 'en_CA', 1, '5580');
INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.upload.logo', 'Upload Logo Image', 'en_CA', 1, '5581');

 


#business email

INSERT INTO  `business_email` (`business_email_id`, `email_type`, `locale`, `msg_id`, `business_id`) VALUES ('1', 'Pick Up Notification', 'en_CA', 'mail.pickup.notification.body', '1');
INSERT INTO  `business_email` (`business_email_id`, `email_type`, `locale`, `msg_id`, `business_id`) VALUES ('2', 'Customer Notification', 'en_CA', 'message.send.customer.notification.body', '1');
INSERT INTO  `business_email` (`business_email_id`, `email_type`, `locale`, `msg_id`, `business_id`) VALUES ('3', 'Shipment Notification', 'en_CA', 'mail.shipment.notification.body', '1');
INSERT INTO  `business_email` (`business_email_id`, `email_type`, `locale`, `msg_id`, `business_id`) VALUES ('4', 'Shipment Cancel Notification', 'en_CA', 'mail.cancel.shipment.notification.body', '1');

#business_loader

INSERT INTO  `business_loader` (`id`, `business_id`, `grid_header_color`, `menu_color`, `menu_hover_color`, `button_color`, `bar_first_color`, `bar_second_color`, `footer_color`, `css_text`) VALUES ('1', '1', '#000000', '#000000', '#F04F23', '#F04F23', '#F04F23', '#000000', '#000000', 'body{ height:100%; width:100%; margin:0px; padding:0px; font-family:arial;}\ninput[type=\'text\'],input[type=\'password\']{ border:1px solid #c7c6c6; padding:0px; box-sizing: border-box; -moz-box-sizing: border-box; -webkit-box-sizing: border-box;}\n\n/* HEADER */\n#wrapper                                { height:auto; float:left; width:100%; }\n#payment_info_pnl              { display: none;}\n.top_line                              { height:5px; background-color:#990000; float:left; width:100%;margin-top:0px; }\n.header-top,.wrapper_btm{ height:auto; width:100%; float:left;}\n.top_header_inner              { height:auto; width:1024px; margin:0px auto; }\n.logo                                      { height:67px; /*width:200px;*/ width:0px; float:left; padding-top:10px;}\n.top_navi                              { height:auto; /*width:453px;*/ width:569px; float:right; margin:40px 23px 0px 236px;}\n.top_navi ul                          { list-style-type:none; font-size:10px; text-align:right;}\n.top_navi ul li                        { display:inline;}\n.top_navi ul li:nth-child(1),.top_navi ul li:nth-child(2),.top_navi ul li:nth-child(3),.top_navi ul li:nth-child(4){ border-right:1px solid #999;}\n.top_navi ul li a                     { text-decoration:none; color:#555555; padding:0px 8px;}\n.live_chat                            { height:auto; width:116px; float:left; margin-top:27px;}\n.header_logo_body		{ width:100%; height:auto; float:left;}\n.navi_icon   				{ display:none;}\n.navigation				{ height:50px; width:100%; float:left; }\n.naviinner{ width:960px; margin:0px auto; height:50px; background-color:#000000; overflow:hidden;}\n.navigation ul			{ list-style-type:none; margin:0px; padding:0px; width:690px; float:left; text-transform:uppercase;}\n.navigation ul li			{ display:inline; color:#fff; float:left; border-right:1px solid #666; }\n.navigation ul li a			{ padding:17px 20px 18px 20px; color:#fff; float:left; font-size:14px; font-weight:bold; text-decoration:none;}\n.navigation ul li a:hover	{ background-color:#990000;}\n\n.navigation2				{ height:50px; width:100%; float:left; }\n.navigation2 ul			{ list-style-type:none; margin:0px; padding:0px; width:690px; float:left; text-transform:uppercase;}\n.navigation2 ul li			{ display:inline; color:#fff; float:left; border-right:1px solid #666; }\n.navigation2 ul li a			{ padding:17px 15px 18px 15px; color:#fff; float:left; font-size:14px; font-weight:bold; text-decoration:none;}\n.navigation2 ul li a:hover	{ background-color:#990000;}\n\n.search_body			{ height:auto; width:260px; float:right; margin-right:-2px;}\n.search_body input[type=\'text\']{ background-color:#000; border:0px; color:#FFF; width:180px; padding:18px 10px; float:left; }\n.search_body input[type=\'submit\']{background-image:url(\'../mmr/images/part-btn-search.png\'); background-color:#990000;  background-repeat:no-repeat; background-position:15px 16px;  width:50px; height:50px; padding:0px; border:0px; margin:0px; float:right;}\n.navi_shadow			{ width:100%; float:left; height:28px; background-image:url(\'../mmr/images/shadow.png\'); background-repeat:no-repeat;\n	background-size:1024px 34px;\n }\n\n \n.wrapper_btm_inner		{ height: auto; width:1024px; margin:0px auto;  }\n.wrapper_body			{ height: auto; width:100%; float:left; background-color:#E7E7E7;  }\n.wrapper_navi			{ height: 37px;  float:left; width:100%; background-color:#555555;  }\n.wrapper_navi p			{ color:#FFF;  padding:0px; margin:11px 20px 11px 5px; font-size:12px; float:right; }\n.wrapper_navi ul			{ list-style-type:none; text-transform:uppercase;  margin:0px 0px 0px 8px; padding:0px;  float:left; width:auto;font-size:12px;}\n.wrapper_navi ul li		{ display:inline; text-transform:uppercase; float:left;	}\n.wrapper_navi ul li a		{ text-decoration:none; text-transform:uppercase; color:#ffffff ; padding:11px 17px 11px 5px; font-weight:600; float:left;}\n.wrapper_navi ul li a:hover { color:#CCC;}\n.nextlevelmenu			{ height:26px; width:960px;background-color:#f7f7f7 ;   margin:0px 32px 5px 32px; float:left; border:1px solid #C4C2C2;}\n.nextlevelmenu ul		{ list-style-type:none; background-color:#f7f7f7 ;  float:left;  width:958px; font-size:11px; margin:0px; padding:0px;}\n.nextlevelmenu ul li		{ display:inline; height:28px;}\n.nextlevelmenu ul li a		{ text-decoration:none; color:#555555 ; padding:6px 10px; float:left;}\n.nextlevelmenu ul li a:hover{ color:#000 ;}\n\n/*   CONTENT   */\n.content				{ height:auto; width:100%;  float:left;  }\n.content_body			{ width:1024px; padding:0px 0px 0px 0px;overflow:auto;  margin:0px auto; height:auto; overflow:none; background-color:#E7E7E7; }\n.content_body_index		{ width:1024px; padding:0px 0px 0px 0px;overflow:hidden;  margin:0px auto; height:auto; overflow:none; background-color:#E7E7E7; }\n.content_table			{ height:auto;  margin:0px auto; width:960px;  background-color:#e5e5e5;  }\n.content_header			{ height:25px; width:960px; float:left; background-color:#000; overflow:hidden; }\n.cont_hdr_title			{ height:15px; font-weight:bold; width:305px; float:left; background-color:#990000; color:#FFF; padding:6px 0px 5px 5px; font-size:12px;\n	text-transform:uppercase;\n }\n.cont_hdrtitle_w		{ height:15px; font-weight:bold; float:left;  color:#FFF; padding:5px; font-size:12px;}\n.cont_data_body			{height:auto; width:958px; float:left; background-color:#E7E7E7; border:1px solid #C4C2C2; }\n.cont_data_body p{ padding:5px 0px 5px 5px; margin:0px; float:left; width:100%; font-size:14px;} \n.rows					{ width:100%; height:auto; float:left; padding:3px 0px; font-size:12px;}\n.fields					{ width:314px; margin-left:5px; float:left; padding:3px 0px; height:25px;font-size:12px;}\n.fields label				{ width:139px; float:left; padding-top:3px;font-size:12px;}\n.fields .controls				{ width:174px; float:left;font-size:12px;}\n.fields .controls p{ font-size:12px;}\n.fields .controls span { padding-top:3px; padding-right:10px; float:left;}\n.fields .controls input[type=\"text\"],.fields .controls input[type=\"password\"]{height:22px; width:160px; padding-left:5px; float:left;}\n.fields .controls input[type=\"checkbox\"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }\n.fields .controls select			{ height:22px; width:160px; float:left;}\n.fields .controls textarea		{ height:17px; width:153px; float:left;}\n.fields a{ color:#990000;}\n.credit_cardimg{ height:63px; width:100px; float:left; margin-left:5px;}\n.fieldsvl ul{ width:200px; float:left; border:1px solid #999; margin:0px; padding:5px; list-style-type:none;}\n\n\n\n\n.largeFields					{ width:200px;  float:left; padding:3px 0px; height:25px;font-size:12px;}\n.largeFields .controls				{ width:200px; float:left;font-size:12px;}\n.largeFields .controls input[type=\"text\"],.largeFields .controls select{height:22px; width:190px; padding-left:5px; float:left;}\n\n.smallFields					{ width:80px;  float:left; padding:3px 0px; height:25px;font-size:12px;}\n.smallFields .controls				{ width:80px; float:left;font-size:12px;}\n.smallFields .controls input[type=\"text\"]{height:22px; width:75px; padding-left:5px; float:left;}\n\n.largeUpdownFields					{ width:200px;  float:left; padding:3px 0px; height:50px;font-size:12px;}\n.largeUpdownFields .controls				{ width:200px; float:left;font-size:12px;}\n.largeUpdownFields .controls input[type=\"text\"],.largeUpdownFields .controls select{height:22px; width:190px; padding-left:5px; float:left;}\n\n.smallUpdownFields					{ width:80px;  float:left; padding:3px 0px; height:50px;font-size:12px;}\n.smallUpdownFields .controls				{ width:80px; float:left;font-size:12px;}\n.smallUpdownFields .controls input[type=\"text\"]{height:22px; width:75px; padding-left:5px; float:left;}\n\n\n.fieldsTextLong					{ width:435px; margin-left:5px; float:left; padding:3px 0px; height:25px;}\n.fieldsTextLong label				{ width:139px; float:left; padding-top:3px;}\n.fieldsTextLong .controls				{ width:294px; float:left;}\n.fieldsTextLong .controls span { padding-top:3px; padding-right:10px; float:left;}\n.fieldsTextLong .controls input[type=\"text\"],.fieldsTextLong .controls select{height:22px; width:280px; padding-left:5px; float:left;}\n\n\n.fields_checkleft{ width:90%; padding:10px 5px; float:left; }\n.fields_checkleft .controls{ width:20px; float:left; padding:0px; margin:0px; height:30px; text-align:left; }\n.fields_checkleft .controls input[type=\"checkbox\"]{ float:left; width:14px; padding:0px; margin:0px;}\n\n.rows p{ padding-left:5px;}\n.form { margin:0px; padding:0px;}\n.form_buttons			{ height:auto;   float:right; text-transform:uppercase; font-weight:bold; }\n.form_buttons a{ text-decoration:none; font-size:12px;font-weight:bold; text-transform:uppercase;}\n.form_buttons input	{ float:right; background-color:#990000; color:#FFF; border:0px; margin:0px 0px 0px 1px; padding:4px 10px 6px 10px;text-transform:uppercase;font-weight:bold;font-family:arial;font-size:12px;}\n.form_buttons a	{ float:right; background-color:#990000; color:#FFF; border:0px; margin:0px 0px 0px 1px; padding:6px 10px 5px 10px;}\n\n.form_buttons_small			{ height:auto; width:60px; float:right; text-transform:uppercase; font-weight:bold; }\n\n\n.fieldsl				{ width:400px; height:25px; float:left; margin-left:5px; padding:3px 0px;}\n.fieldsl label			{ width:200px; float:left; padding-top:3px;}\n.fieldsl .controls		{ width:174px; float:left;}\n.fieldsl .controls input[type=\"checkbox\"]		{ width:15px; text-align:left;  float:left; margin:4px 0px 0px 0px; }\n.fieldsl .controls select{ width:160px; height:22px; float:left;}\n.fieldsl .check span{ float:left; padding-top:3px;}\n.fieldsl .check{ }\n.check input[type=\"checkbox\"]{ margin: 5px 0px 0px 9px;}\n.fieldsl .controls span { padding-top:3px; padding-right:10px; float:left;}\n.fieldsl .controls input[type=\"text\"],.fieldsl .controls input[type=\"password\"]{height:22px; width:160px; padding-left:5px; float:left;}\n.fieldsl .controls span{ float:left; padding-top:3px; }\n\n.fieldsm				{ width:300px; float:left; margin-left:5px;  padding:3px 0px;}\n.fieldsm label			{ width:150px; float:left; padding-top:1px;}\n.fieldsm .controls		{ width:150px; float:left;}\n\n.fieldss				{ width:142px; float:left; margin-left:5px; padding:3px 0px;}\n.fieldss label			{ width:100px; float:left; padding-top:4px;}\n.fieldss .controls		{ width:30px; float:left;}\n.fieldss .controls input	{ width:20px; }\n\n.fieldsvl				{ width:555px; float:left; margin-left:5px; padding:3px 0px;}\n.fieldsvl label			{ width:128px; float:left; padding-top:0px;}\n.fieldsvl .controls           { width:321px; float:left;}\n.controls textarea		{ height:12px; width:300px; float:right;}\n.fieldsvl .controls select{ width:160px; height:22px; float:left; }\n\n.fields_topdown { width:153px; margin-left:30px; float:left; margin-left:5px; padding:3px 0px;}\n.fields_topdown label			{ width:150px; float:left; padding:4px 0px;}\n.fields_topdown .controls { width:150px ;}\n\n\n.controlscheck		{ width:30px; float:left;  padding-top:3px; }\n.controlscheck	span{  padding-top:1px; float:left;}\n.controlscheck input { margin-left:9px; float:left;}\n\n.fields_topdown .controls input	{ width:130px; float:left; height:22px; }\n.fields_topdown .controls select	{ width:130px; float:left; height:22px; }\n\n.fields_check					{ width:275px; margin-left:5px; float:left; padding:3px 0px;}\n.fields_check label				{ width:188px; float:left; margin-top:0px;}\n/*.controls				{ width:30px; float:left;}*/\n\n\n.fieldscheckgroup{ width:275px; margin-left:5px; float:left; padding:3px 0px;}\n.checkgroupctrls	{ width:23px; float:left; text-align:left !important; padding:0px; margin:0px;}\n.checkgroupctrls input{ width:15px; float:left; text-align:left !important; padding:0px; margin:0px;}\n.fieldscheckgroup label{ width:230px; float:left; padding-top:0px;}\n\n#takemargin{ margin-top:22px; }\n#messages{ float: left;\nwidth: 100%;\nheight: auto;}\n.message_inner{ width:1024px; height:auto; margin:0px auto; font-size:12px; }\n.message_inner ul{ padding:3px 0px 3px 20px; height:auto; margin:0px; padding:0px;}\n\n\n/*   FOOTER */\n.footer               			{ height:41px; width:100%;  background-color:#000; margin-top:5px; float: left; } \n\n.footer_body    			{ height:40px; width:1024px; margin:0px auto; background-color:#000;}\n.footer_inner  		        { height:15px; width:960px; margin:0px auto; padding:13px 0px; color:#FFF; font-size:10px;}\n.footer_inner p			{ padding:0px; margin:0px; float:left;  width:50%; }\n.footer_inner p:nth-child(1){ text-align:left;}\n.footer_inner p:nth-child(2){ text-align:right;}\n\n\n\n\n\n#sample2_length ,#sample2_filter{ padding:0px 0px;}\n#errorMsgContainer ul{ padding:0px; margin:0px 0px 2px 0px;}\n\n\n\n/* shipping */\n.cont_hdrtitle_c		{ height:15px; font-weight:bold; width:390px; margin-left:250px; float:left;  color:#FFF; padding:5px; font-size:12px;}\n.cont_hdrtitle_c2		{ height:15px; font-weight:bold; width:450px; margin-left:280px; float:left;  color:#FFF; padding:5px; font-size:12px;}\n.cont_hdrtitle_c3		{ height:15px; font-weight:bold; width:390px; margin-left:360px; float:left;  color:#FFF; padding:5px; font-size:12px;}\n.cont_hdrtitle_l,.cont_hdrtitle_L		{ height:15px; font-weight:bold; width:500px; float:left;  color:#FFF; padding:5px; font-size:12px; overflow:hidden;white-space:nowrap;text-overflow: ellipsis;}\n\n.logo_mini				{ height:40px; float:left; width:100%; overflow:hidden;}\n.cont_hdrtitle_s{ height:15px; font-weight:bold; width:200px; margin-left:0px; float:left;  color:#FFF; padding:5px; font-size:12px;}\n\n.fields_topdown_s { width:65px; margin-left:30px; float:left; margin-left:2px; padding:3px 0px;}\n.fields_topdown_s label			{ width:64px; float:left; padding:4px 0px;}\n\n.fields_topdown_s .controls input	{ width:60px; float:left; }\n.fields_topdown_s .controls select	{   float: left;\n    height: 22px;\n    width: 60px;\n}\n.fields_topdown_s .controls{ width:61px;}\n.fields_topdown_a a{ padding:3px 0px; text-align:center; background-color:#990000; width:80px;   color:#FFF; float:left; text-decoration:none;}\n.fields_topdown_a{ width:auto; float:right; padding:3px 0px; margin-right:10px;}\n\n.allsame{ height:auto; width:auto; float:left; margin-left:10px; padding:3px 0px 0px 0px;}\n.allsame a{ padding:3px 10px; background-color:#990000; float:left; color:#fff; text-decoration:none; font-weight:bold;}\n.sl_no{ height:auto; width:auto; float:left; padding:6px 5px;}\n.cont_hdr_title input[type=\"checkbox\"]{padding: 0px; margin: 1px 20px 0px 10px; float: right;}\n.cont_hdrtitlelarge{ height:15px; font-weight:bold; width:460px; float:left; background-color:#990000; color:#FFF; padding:6px 0px 5px 5px; font-size:12px;\n	text-transform:uppercase;\n }\n .cont_hdrtitlelarge input[type=\"checkbox\"]{ padding:0px; float:right; margin:0px 10px 0px 10px;}\n.form_buttons p{padding:5px; margin:0px; color:#fff; font-size:0.75em;}\n.form_buttons p span{  font-size:2em;  height:auto; margin:-3px 5px 0px 0px; float:left; color:#f00; }\n.form_radio{ width:auto; margin:6px; float:right; height:auto; color:#fff; font-size:0.75em;}\n.form_radio label{ float:left;}\n.form_radio input{float:left; margin:0px 5px;}\n.fields label span{ color:#f00;}\n.fields .controls img{ float:right;}\n\n\n\n/*    GRID  */\n#srchusr_results,#rate_results,#results,#srchinv_results,#srchaddrss_results{ background:url(\"/ShipLinx/ImageAction.action?id=back\");\n	border-top:1px solid #c4c2c2; border-left:1px solid #c4c2c2; border-right:1px solid #c4c2c2;\n}\n\n#srchusr_res span,#results span { width:auto; height:auto; float:left; color:#990000;font-size: 14px;font-weight: bold; padding:20px 0px 5px 60px;}\n#srchusr_results,#results,#srchinv_results,#srchaddrss_results { height:57px;}\n\n	\n#srchusr_results .form_buttons,#results .form_buttons,#rate_res .form_buttons,#srchinv_results .form_buttons,#srchaddrss_results .form_buttons,#rate_results .form_buttons{ \n	padding:15px 3px 0px 0px;\n}\n\n.dataTables_wrapper{border-left:1px solid #C4C2C2; overflow:auto; border-right:1px solid #C4C2C2;border-bottom:1px solid #C4C2C2;}\n.form-container { height:auto; width:100%; float:left;}auto\n#sample1_wrapper { width:100%; float:left;}\n#sample2_wrapper { width:100%; float:left;}\n.grid_table_body{ height:auto; width:100%; float:left; background-color:#FFFFFF; }\n.grid_table_body table{ font-size:12px;}\n.grid_table_body table thead tr{ background-color:#000; color:#FFF; padding:5px; font-size:12px;  font-weight:normal;}\n.grid_table_body table thead tr th{text-align:left;}\n.grid_table_body table thead tr th,.grid_table_body table tbody tr td{  padding-left:5px;}\n.grid_table_body table thead tr th:first-child{ padding:5px;}\n.grid_table_body table tbody tr td:first-child{  padding:5px;}\n.box-cont1,.box-cont2{ float:left; width:100%; height:auto; font-size:12px; padding:1px 0px 1px 0px;}\n.dataTables_length,.dataTables_info{ float:left !important; width:45% !important; height:auto !important;}\n.dataTables_filter,.dataTables_paginate { float:right !important; width:auto !important; height:auto !important;}\n.dataTables_paginate{ text-align:right; cursor:pointer;}\n\n.dataTables_filter label { float:right; font-size:12px; }\n.paginate_disabled_next{ margin-left:10px;}\n#messages{ float:left; padding:0px; margin:0px; height:auto; width:100%;}\n\n.logo_mini    { height:40px; float:left; width:100%; overflow:hidden;background-image: url(../mmr/images/panelResults_top_shipping.png);\nbackground-repeat: no-repeat;}\n\n.fieldsvvl					{ width:635px; margin-left:5px; float:left; padding:3px 0px; height:25px;}\n.fieldsvvl label				{ width:139px; float:left; padding-top:3px;}\n.fieldsvvl .controlsvvl			{ width:495px; float:left;}\n.fieldsvvl .controlsvvl span 	{ padding-top:3px; float:left;}\n.fieldsvvl .controlsvvl input { margin-left:9px; float:left; padding:2px 5px; width:481px;}\n\n.fieldsll{  width:340px; margin-left:5px; float:left; padding:3px 0px; height:25px; }\n.fieldsll label{ width:165px; float:left; padding-top:3px;}\n\n\n.fieldsll .controls				{ width:174px; float:left;}\n.fieldsll .controls span { padding-top:3px; padding-right:10px; float:left;}\n.fieldsll .controls input[type=\"text\"],.fields .controls input[type=\"password\"]{height:22px; width:160px; padding-left:5px; float:left;}\n.fieldsll .controls input[type=\"checkbox\"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }\n.fieldsll .controls select			{ height:22px; width:160px; float:left;}\n\n.fields_checkleft{ width:90%; padding:10px 5px; float:left; }\n.fields_checkleft .controls{ width:20px; float:left; padding:0px; margin:0px; height:30px; text-align:left; }\n.fields_checkleft .controls input[type=\"checkbox\"]{ float:left; width:14px; padding:0px; margin:0px;}\n\n\n#sample1_filter input,#sample2_filter input,#samplesearchproduct_filter input,#invoicesTable_filter input{ margin:3px 0px 2px 0px;}\n\n\n#sample1_length,#sample2_length,#invoicesTable_length,#invoicesTable_info,#sample1_info,#sample2_info,#samplesearchproduct_length,#samplesearchproduct_info{ margin:3px 0px 0px 5px;}\n#sample1_paginate,#sample2_paginate{ padding:3px 5px 0px 5px;}\n#sample1_filter,#sample2_filter,#invoicesTable_filter,#invoicesTable_paginate,#samplesearchproduct_filter,#samplesearchproduct_paginate{padding:0px 2px 0px 5px;}\n.fieldErrorStyle { font-size:12px;}\n\n\n.exportlinks{\n\n   		position:relative; \n   		font-family:Arial;\n		font-size:13px;\n		font-weight:bold;\n		text-decoration: none; \n		float:right;\n		height:30px;  \n		padding-top:5px;\n	\n	}\n	\n	.exportlinks a{\n		font-family:Arial;\n		font-size:13px;\n		font-weight:bold;\n		text-decoration: none;		\n	}\n	#exportlnks_img\n	{\n		margin-left: 664px;\n    margin-top: -20px;\n    width: 250px;\n	}\n	\n	#exportcsv\n	{\n		background-image:url(../mmr/images/csvDoc_icon.png);\n		background-repeat:no-repeat;\n	}\n	\n	#exportexcel\n	{\n		background-image:url(../mmr/images/exelDoc_icon.png);\n		background-repeat:no-repeat;\n	}\n	\n	#exportxml\n	{\n		background-image:url(../mmr/images/xmlDoc_icon.png);\n		background-repeat:no-repeat;\n	}\n	\n	#exportpdf\n	{\n		background-image:url(../mmr/images/pdf_doc.png);\n		background-repeat:no-repeat;\n	}\n	.exportcsv{\n		color:#555555;\n 		background-image:url(../mmr/images/csvDoc_icon.png);\n\n		margin-left:-52px;\n		background-repeat:no-repeat;		\n 	}\n	\n	.exportexcel{\n		color:#555555;\n 		background-image:url(../mmr/images/exelDoc_icon.png);\n		background-repeat:no-repeat;\n		margin-left:-3px;\n\n		\n 	}\n 	\n 	.exportxml{\n		color:#555555;\n 		background-image:url(../mmr/images/xmlDoc_icon.png);\n		background-repeat:no-repeat;\n		margin-left:-3px;\n\n\n		\n 	}\n 	\n 	.exportpdf{\n		color:#555555;\n 		background-image:url(../mmr/images/pdf_doc.png);\n		background-repeat:no-repeat;		\n 	}\n	\n	#rate_results\n	{\n	background-image: url(\"/ShipLinx/ImageAction.action?id=back\");\n    background-repeat: no-repeat;\n    color: #000000;\n    font-family: Arial;\n    font-size: 12px;\n    font-weight: bold;\n    height: 57px;\n\n	}\n	.odd\n	{\n		font-family: Arial;\n		font-size: 12px;\n		font-weight: normal;\n		color: #000000;\n		height:20px;\n		background-color: #ffffff;\n		\n	}\n	\n	.odd td{\n		font-family: Arial;\n		font-size: 12px;\n		font-weight: normal;\n		color: #000000;\n		height:20px;\n	}\n	\n	.even\n	{\n		font-family: Arial;\n		font-size: 12px;\n		font-weight: normal;\n		color: #000000;\n		height:20px;\n		background-color: #EAECEE;\n	}\n	.form-container{\n	width: 100%;\n	height: auto;\n	float: left;\n	vertical-align: top;\n	background-color:#FFF;\n	/*border:1px solid #c4c2c2;*/\n	\n}\n#summary_div_middle{\n	\n    display: none;\n	\n	}\n	#summary_div_bot{\n	\n    display: none;\n	}\n	#rate_img{\n\n	display: none;\n	}\n	#customs_invoice_checkbox{\n	padding-top: 0px;\n	margin-left: 0px;\n	}\n	#customs_invoice_panel{	\n   /* height: auto;\n   	position: relative;\n    width: 1100px;\n    margin-left: 70px;*/\n    display: none;\n	}\n	#loading-billto\n	{\n		margin-top: 2px;\n		margin-left: 404px;\n		position: absolute;\n		z-index: 100;\n	}\n	\n	\n	#srchshipmnt_result_tbl_print_label\n {\n	height: 23px;\n	margin-left: 320px;\n	margin-top: 17px;\n	width: 368px;\n	float:left;\n\n }\n \n #srchshipmnt_result_tbl_print_label a\n {\n  Color: #000;\n  font-family: Arial;\n     font-size: 12px;\n     font-weight: bold;\n     text-decoration: none;\n }\n .srchshipmnt_text_03{\n font-family: Arial;\n font-size:12px;\n color:#000;\n \n text-decoration:none;\n line-height:20px;  \n }\n .text_01_combo_small{\n width: 87px;\n height: 22px;\n border: 0px solid;\n border-radius: 0px;\n padding: 1px 1 1 1px; \n background-color: #F5F5F5;\n border: 1px solid #CBCBCA;\n }\n\n.form_buttons_radio{ float:right; width:auto;}\n.radiocontrols{ padding:3px 10px; margin-right:5px; color:#fff; float:left; font-size:12px; font-weight:bold; }\n.radiocontrols label{ float:left; padding:2px 5px;}\n.radiocontrols input{ float:left;}\n\n	.dojoComboBoxItem dojoComboBoxItemOdd{\n	color: #848484 !important;\n    font-family: Arial !important;\n    font-size: 13px !important;\n    font-weight: bold !important;\n	}\n	\n	.dojoComboBoxItem dojoComboBoxItemEven{\n	color: #848484 !important;\n    font-family: Arial !important;\n    font-size: 13px !important;\n    font-weight: bold !important;\n	}\n	.dojoComboBoxOptions{\n	width:400px;\n	font-size:10px;\n	}\n\n\n\n\n	\n.grid_table_body table thead,.grid_table_body table tbody{ font-size:12px;}	\n\n\n\n\n\n\n\n/*   new update  */\n#autoaddresst,#autoaddressf{ width:160px !important;}\n\n\n.grid_header_title{ font-size:12px; font-weight:bold; margin-bottom:-26px; width:100%; float:left; text-align:center; padding:5px 0px; }\n\n.fields_empty{ width:100px; margin-left:5px; float:left; padding:3px 0px; height:25px;}\n\n#srchusr_result_tbl{ width:960px;}\n#billToType,#billToPostalCode{width:160px;}\n\n\nnavi4{ width:100%; height:auto; float:left;}\n.navi4 ul{ list-style-type:none;border-bottom:1px solid #999;width:302px;; overflow:auto; margin:0px 0px 1px 0px; padding:0px; font-size:12px;}\n.navi4 ul li{ float:left; cursor:pointer; border-radius:5px 5px 0px 0px; padding:5px 10px; background-color:#000; color:#FFF;}\n.fields .controls p{padding:3px 0px; margin:0px; color:#666; width:160px;}\n.fields_long { width:630px; float:left; height:25px; padding:3px 0px 3px 5px; }\n.fields_long label{ width:100px; height:auto; float:left;padding:3px 0px; }\n.fields_long .controls{ width:520px; float:left;}\n.fields_long .controls span{ float:left; padding-right:5px;}\n.fields_long .controls p{ width:500px; float:left; color:#666; font-size:12px; padding:0px;}\n\n.fieldauto1 {color:#FFF; padding-top:2px; font-size:12px; float:left; width:200px;}\n.fieldauto2 {color:#FFF; padding-top:2px; font-size:12px; float:left; width:200px;}\n\n.text_01_combo_small{ margin-top:1px;}\n#srchshipmnt_text_04{color:#fff;}\n\n\n\n#pckg_results{margin-top: 9px	;margin-left: 43;color: 990000;font-size: 13;font-weight: bold; width:200px; float:left;}\n.domention{ width:300px; float:left; height:auto; margin-top:0px;}\n.domention .fields{ width:300px; floaat:left; height:left;}\n#actionmenu li{  height:26px; margin:2px 0px; background:#990000; text-align:left !important; width:auto; min-width:100px;}\n.domention .fields .controls{ width:80px; float:left;}\n.domention .fields .controls  span{ width:5px; float:left; padding-right:5px; }\n.domention .fields .controls select{ width:50px; float:left; margin-top:3px;}\n\n/* very very small fields*/\n.fieldsvvs{ width:207px; margin-left:5px; float:left; padding:3px 0px; height:25px;}\n.fieldsvvs label{ width:80px; float:left;   padding-top:3px; }\n.fieldsvvs .controls{ width:127px; float:left;}\n.fieldsvvs .controls  span{ padding-top:3px; padding-right:10px; float:left; }\n.fieldsvvs .controls input{ height:22px; width:113px; padding-left:5px; float:left;}\n\n\n/*\n.fields					{ width:315px; margin-left:5px; float:left; padding:3px 0px; height:25px;}\n.fields label				{ width:139px; float:left; padding-top:3px;}\n.fields .controls				{ width:174px; float:left;}\n.fields .controls span { padding-top:3px; padding-right:10px; float:left;}\n.fields .controls input[type=\"text\"],.fields .controls input[type=\"password\"]{height:22px; width:160px; padding-left:5px; float:left;}\n.fields .controls input[type=\"checkbox\"]{ width:18px; text-align:left;  float:left; margin:4px 0px 0px 0px; padding:0px; }\n.fields .controls select			{ height:22px; width:160px; float:left;}\n\n*/\n\n/* for action button */\n\n	#actionmenu{position:absolute; float:left; display:none; background-color:#000; list-style-type:none; padding:5px; margin-top:30px;   z-index:1000; }\n	#actionmenu li{  height:26px; margin:2px 0px; background:#990000; text-align:left !important; width:auto;}\n	#actionmenu li a{ float:left; padding:5px;}\n	#actionup {display:none;}\n\n	/* package dimention */\n	#dimention_grid input[type=\"text\"],#dimention_grid select{height:20px}\n\n	\n	\n#dimention_grid tbody tr td input{ text-align:right;}	\n\n\n/* auto complete */\n.autocomplete-suggestions {\n/*  width:100px !important;\noverflow:hidden;\nwhite-space:nowrap;\ntext-overflow: ellipsis;  */\n\nborder: 1px solid #999;\nbackground: #FFF;\ncursor: default;\noverflow: auto;\n-webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);\n-moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);\nbox-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);\nheight:200px;\ndisplay:none;\nfont-size:12px;\npadding:3px 0px 3px 0px;\ncursor:pointer;\n\n}\n\n.autocomplete-suggestion{\n	width:96% !important;\n	\n	padding:1px 2%;\n	overflow:hidden;\n	white-space:nowrap;\n	text-overflow: ellipsis;\n}\n\n#customersautocomplete,#auto,#customerautocomplete,.text_01_combo_big_customer{ background-image:url(\"../mmr/images/arrow_bottom.png\");padding:0px 16px 0px 5px; background-repeat: no-repeat;\n font-size:12px; border:1px solid #c7c6c6; }\n .text_01_combo_big_customer option{ width:92px !important;}\n\n \n \n.closebtn{ height:12px; width:12px; position:absolute; top:2px; right:2px; background-image:url(\'../mmr/images/close_icon.png\');\n	background-size:10px 10px;\n	background-position:top right;\n	background-repeat:no-repeat;\n} \n\n.total_label:hover{ text-decoration:underline; cursor:pointer;}\n\n\n/* popup */\n\n#popup_container {\n        font-family: Arial, sans-serif;\n        font-size: 12px;\n        min-width: 300px; /* Dialog will be no smaller than this */\n        max-width: 600px; /* Dialog will wrap after this width */\n        background: #FFF;\n        border: solid 2px #990000;\n        color: #000;\n        -moz-border-radius: 5px;\n        -webkit-border-radius: 5px;\n        border-radius: 5px;\n}\n\n#popup_content { \n        background: 16px 16px no-repeat url(images/info.gif);\n        padding: 1em 1.75em;\n        margin: 0em;\n}\n\n#popup_content.alert {\n        background-image: url(images/info.gif);\n}\n\n#popup_content.confirm {\n        background-image: url(images/important.gif);\n}\n\n#popup_content.prompt {\n        background-image: url(images/help.gif);\n}\n\n#popup_message {\n        padding: 0px 20px;\n		text-align: justify;\n}\n\n#popup_panel {\n        text-align: center;\n        margin: 1em 0em 0em 1em;\n}\n\n#popup_prompt {\n        margin: .5em 0em;\n}\n\n/* hacks */\n\n#popup_panel input{\n        color: #FFF;\n        margin: 0px;\n        padding: 2px;\n        display: inline;\n        width: 60px;\n        height: 30px;\n        font-size: 12px;\n        background: #990000 url(images/title.gif) top repeat-x;\n        \n}\n\n#popup_panel input:focus{\n        border: 1px solid #999;\n}\n\n\n\n\n\n\n\n\n\n/*  custom design for alert box */\n\n		#modalContainer ,#modalContainer2{\n	background-color:rgba(0, 0, 0, 0.3);\n	position:absolute;\n	width:100%;\n	height:100%;\n	top:0px;\n	left:0px;\n	z-index:10000;\n	background-image:url(tp.png); /* required by MSIE to prevent actions on lower z-index elements */\n}\n\n#alertBox,#confirmBox ,#alertBox2{\n	position:relative;\n	width:auto;\n	padding:0px 40px;\n	height:auto;\n	margin-top:150px;\n	border:2px solid #990000;\n	background-color:#fff;\n	background-repeat:no-repeat;\n	background-position:20px 30px;\n	text-align:center;\n	border-radius:5px;\n}\n\n#modalContainer > #alertBox {\n	position:fixed;\n}\n#modalContainer > #alertBox2 {\n	position:fixed;\n}\n#modalContainer > #confirmBox {\n	position:fixed;\n}\n\n#alertBox h1,#confirmBox h1 {\n	margin:0;\n	font:bold 0.9em arial;\n	background-color:#3073BB;\n	color:#FFF;\n	border-bottom:1px solid #000;\n	/*padding:2px 0 2px 5px;*/\n}\n\n#alertBox p,#confirmBox p  {\n	font:12px arial;\n	min-height:40px;\n	/*margin-left:55px;*/\n}\n#alertBox2  p{\n	font:12px arial;\n	min-height:40px;\n	/*margin-left:55px;*/\n}\n\n#alertBox #closeBtn ,#confirmBtn,#confirmCancelBtn,#alertBox2 {\n	display:block;\n	position:relative;\n	margin:10px auto;\n	padding:7px;\n	border:0 none;\n	width:70px;\n	font:0.7em verdana,arial;\n	text-transform:uppercase;\n	text-align:center;\n	color:#FFF;\n	background-color:#990000;\n	border-radius: 3px;\n	text-decoration:none;\n}\n#confirmBtn{ float:left; }\n#confirmCancelBtn{ float:right;}\n\n/* unrelated styles */\n\n#mContainer {\n	position:relative;\n	width:600px;\n	margin:auto;\n	padding:5px;\n	border-top:2px solid #000;\n	border-bottom:2px solid #000;\n	font:0.7em verdana,arial;\n}\n\n\ncode {\n	font-size:1.2em;\n	color:#069;\n}\n\n#credits {\n	position:relative;\n	margin:25px auto 0px auto;\n	width:350px; \n	font:0.7em verdana;\n	border-top:1px solid #000;\n	border-bottom:1px solid #000;\n	height:90px;\n	padding-top:4px;\n}\n\n#credits img {\n	float:left;\n	margin:5px 10px 5px 0px;\n	border:1px solid #000000;\n	width:80px;\n	height:79px;\n}\n\n.important {\n	background-color:#F5FCC8;\n	padding:2px;\n}\n\ncode span {\n	color:green;\n}\n\n.borderLeftRight{ border-left:1px solid #c4c2c2;  border-right:1px solid #c4c2c2; width:958px !important;}\n.borderLeftRightTopDown{ border-left:1px solid #c4c2c2;  border:1px solid #c4c2c2; width:958px !important;}\n.borderOnlyBottom{ border-bottom:1px solid #c4c2c2;}\n.borderOnlyBottom{ border-bottom:1px solid #c4c2c2;}\n.ac_results{ background-color:#FFF; border:1px solid #999; width:158px !important;}\n.ac_results ul{ padding:5px 0px 0px 0px; margin:0px; list-style-type:none;}\n.ac_results ul li{ font-size:12px; color:#000; padding:1px 0px 0px 5px;}\n.ac_results ul li:hover{ background-color:#3399ff; color:#FFF; cursor:default;}\n.menu_button{\n	color: #ffffff; \n background: #990000;\n}\n.package1{\n	\n	\n	width:100px;font-size:12px; float:right; height:auto; color:#FFF;  background-color:#990000; text-decoration:none; padding:3px 0px; text-align:center;\n\n}\n.package2{\n	 width:100px; float:right; height:auto; color:#FFF;  background-color:#990000;font-size:12px; text-decoration:none; padding:3px 0px; text-align:center;\n}\n.package3{\n	font-size:12px;font-size:bold;color:#990000;margin-left:40px;\n}\n\n.invoice1{\n	padding:3px 10px; position:relative; background-color:#990000; color:#FFF; text-decoration:none; font-size:12px; font-weight:bold;\n}');


#vivek changes
ALTER TABLE  `business_loader` 
ADD COLUMN `arrow_color` VARCHAR(45) NOT NULL AFTER `email_header`;

INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.business.footer.menu.color', 'Footer Font Color', 'en_CA', 1, '');

ALTER TABLE `business_loader` 
ADD COLUMN `footer_font_color` VARCHAR(45) NOT NULL AFTER `email_header`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.arrowColor', 'Arrow Color', 'en_CA', 1);

#css changes

 
insert into action(action, menu_id, highlight, description, 
reload_safe) values('contactMenu', 108,1,'contact', 1)

insert into action(action, menu_id, highlight, description, 
reload_safe) values('feedbackMenu', 108,1,'contact', 1)


insert into role_action(role, action_id) values('admin', 1076);

insert into role_action(role, action_id) values('busadmin', 1076);

insert into role_action(role, action_id) values('sysadmin', 1076);

insert into role_action(role, action_id) values('admin', 1078);

insert into role_action(role, action_id) values('busadmin', 1078);

insert into role_action(role, action_id) values('sysadmin', 1078);



alter table business add contact_path varchar(120);
alter table business add feedback_path varchar(120);

insert into resourcebundle(msg_id, msg_content, locale, is_fmk)
values('label.business.contactpath', 'Contact Path', 'en_CA', 1)

insert into resourcebundle(msg_id, msg_content, locale, is_fmk)
values('label.business.feedbackpath', 'Feedback Path', 'en_CA', 1)



UPDATE `menu` SET `url`='/admin/new.address.action' WHERE `id`='242';


UPDATE `menu` SET `url`='/admin/upload.addressbook.action?method=new' WHERE `id`='403';


UPDATE `menu` SET `url`='/admin/distribution.list.action' WHERE `id`='251';




UPDATE `menu` SET `url`='/admin/addressbook.list.req.action' WHERE `id`='65';
UPDATE `menu` SET `url`='/admin/addressbook.list.req.action' WHERE `id`='203';
UPDATE `menu` SET `url`='/admin/addressbook.list.req.action' WHERE `id`='231';
UPDATE `menu` SET `url`='/admin/addressbook.list.req.action' WHERE `id`='241';


alter table business_loader add footer1 varchar(120);
alter table business_loader add footer2 varchar(120);

insert into resourcebundle(msg_id, msg_content, locale, is_fmk)
values('label.business.footer1', 'Footer1', 'en_CA', 1);

insert into resourcebundle(msg_id, msg_content, locale, is_fmk)
values('label.business.footer2', 'Footer2', 'en_CA', 1);

INSERT INTO  `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.upload.favIcon', 'Upload FavIcon', 'en_CA', 1);

ALTER TABLE `user` ADD COLUMN `auto_freight_class` BIT(1) NOT NULL DEFAULT b'0' AFTER `isbusiness_level`;

alter table shipping_order add from_pallet_jack bit(1) not null default b'0';

alter table shipping_order add to_pallet_jack bit(1) not null default b'0';

alter table shipping_order add from_dock_level bit(1) not null default b'0';

alter table shipping_order add to_dock_level bit(1) not null default b'0';


alter table shipping_order add from_floor_no varchar(6) not null default -1;

alter table shipping_order add to_floor_no varchar(6) not null default -1;

alter table shipping_order add istempControl bit(1) default b'0';

alter table shipping_order add temperature varchar(6);


ALTER TABLE `shipping_order` 
CHANGE COLUMN `link_to_order` `link_to_order` INT(11) NULL DEFAULT 0 ;

alter table `shipping_order`ADD COLUMN `def_pickup_time_between` VARCHAR(45) NULL DEFAULT NULL AFTER `link_to_order`;

ALTER TABLE `customer_carrier` CHANGE COLUMN `property_5` `property_5` VARCHAR(100) NULL DEFAULT NULL ;

ALTER TABLE `shipping_labels` ADD COLUMN `original_label` MEDIUMBLOB NULL DEFAULT NULL AFTER `cod_label`,
ADD COLUMN `label_updated` TINYINT(4) NULL DEFAULT '0' AFTER `original_label`;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.tailGateDelivery', 
'Tailgate Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.fromPalletJack', 
'PalletJack Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.toPalletJack', 
'PalletJack Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.fromDockLevel', 
'DockLevel Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.toDockLevel', 
'DockLevel Delivery', 'en_CA', 1);


insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.additionalInformation','ADDITIONAL INFORMATION',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.worshipPickup','Worship pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.worshipDelivery','Worship Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.utilityPickup','Construction/Utility Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.utilityDelivery','Construction/Utility Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.governmentPickup','Government Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.governmentDelivery','Government Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.mineSitePickup','Mine site Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.mineSiteDelivery','Mine site Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.limitedAccessPickup','Non commercial Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.limitedAccessDelivery','Non commercial Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.nonCommercialDelivery','Non commercial Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.schoolPickup','School Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.schoolDelivery','School Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.insideStreetPickup','Inside Pickup-Street Level',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.insideStreetDelivery','Inside Delivery-Street Level',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.liftGatePickup','Lift Gate Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.liftGateDelivery','Lift Gate Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.selfStoragePickup','Self-storage Pickup',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.selfStorageDelivery','Self-storage Delivery',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.hazardousDelivery','Hazardous Materials',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.protectFreeze','Protect from freeze',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.destinationNotify','Destination notify',
'en_CA',1);

insert into resourcebundle(msg_id,msg_content,locale,is_fmk)
values ('label.shipping_order.correctionFee','Correction fee',
'en_CA',1);

UPDATE `service` SET `master_carrier_id`='10' WHERE `service_id`='200021';
UPDATE `service` SET `master_carrier_id`='10' WHERE `service_id`='200022';
UPDATE `service` SET `master_carrier_id`='10' WHERE `service_id`='2000023';
UPDATE `carrier` SET `name`='eShipPlus' WHERE `carrier_id`='6';
UPDATE `service` SET `master_service_id`='200021' WHERE `service_id`='200021';
UPDATE `service` SET `master_service_id`='200022' WHERE `service_id`='200022';
UPDATE `service` SET `master_service_id`='200023' WHERE `service_id`='2000023';

INSERT INTO `business_carrier` (`business_id`, `carrier_id`, `business_carrier_discount`, `display_name`) VALUES ('1', '6', '0', 'eShipPlus');

INSERT INTO `carrier` (`carrier_id`, `name`, `tracking_url`, `implementing_class`, `currency_id`) VALUES ('6', 'eShipPlus', 'http://www.eshipplus.com/Public/com/Default.aspx', 'eShipPlus', '1');

INSERT INTO `service` VALUES(200021,'IC Class Expedited',6,6,NULL,'LTL - Logistics Plus','LTL-PP',NULL,NULL,2,0,NULL,2000021,0,0,0,0,'LTL',0,0,0,0,0,0),
(200022,'IC Class Standard',6,6,NULL,'LTL - Logistics Plus','LTL-PP',NULL,NULL,2,0,NULL,2000022,0,0,0,0,'LTL',0,0,0,0,0,0),
(2000023,'IC Class Economy',6,6,NULL,'LTL - Logistics Plus','LTL-PP',NULL,NULL,2,0,NULL,2000023,0,0,0,0,'LTL',0,0,0,0,0,0),
(2000024,'PurolatorExpressU.S. Import',20,20,NULL,'PurolatorExpressU.S. Import','PurolatorExpressU.S. Import','601',NULL,1,0,NULL,0,0,0,0,151,'SPD',0,0,0,0,0,0);

DROP TABLE IF EXISTS `eshipplus_packages`;
CREATE TABLE `eshipplus_packages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_key` int(11) NOT NULL DEFAULT '0',
  `def_height` double NOT NULL DEFAULT '0',
  `def_length` double NOT NULL DEFAULT '0',
  `def_width` double NOT NULL DEFAULT '0',
  `message` varchar(45) DEFAULT NULL,
  `ic_package_name` varchar(45) DEFAULT NULL,
  `eship_package_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `eshipplus_packages` VALUES (1,258,40,48,48,NULL,'Pallet','PALLET(S)'),(2,250,0,0,0,NULL,'Carton','CARTONS'),(3,253,0,0,0,NULL,'Crate','CRATE(S)'),(4,255,0,0,0,NULL,'Drum','DRUM(S)'),(5,244,0,0,0,NULL,'Boxes','BOX(ES)'),(6,261,0,0,0,NULL,'Rolls','ROLL(S)'),(7,266,0,0,0,NULL,'Pipes/TubesBales','TUBES/PIPES'),(8,242,0,0,0,NULL,'Bags','BAGS'),(9,254,0,0,0,NULL,'Cylinder','CYLINDER(S)'),(10,260,0,0,0,NULL,'Pails,Reels','REELS(S)'),(11,267,0,0,0,NULL,'Other','YOUR PACKAGING/UNITS');

CREATE TABLE `eshipplus_carrier_filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(12) DEFAULT '0',
  `eship_carrier_id` int(10) DEFAULT '0',
  `eship_carrier_scac` varchar(45) DEFAULT NULL,
  `eship_carrier_name` varchar(65) DEFAULT NULL,
  `disabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

INSERT INTO `eshipplus_carrier_filter` VALUES (1,0,97,'AACT','AAA COOPER',0),(2,0,98,'BAHF','B & H FREIGHT LINE',0),(3,0,99,'CNWY','CON-WAY FREIGHT',0),(4,0,100,'CNWY','CON-WAY FREIGHT (Canada)',0),(5,0,101,'CPPV','CALTOP LOGISTICS',0),(6,0,102,'CRST','CRYSTAL MOTOR EXPRESS',0),(7,0,103,'DAFG','Dayton Freight',0),(8,0,104,'DUBL','Dugan Truck Line',0),(9,0,105,'EXLA','ESTES',0),(10,0,106,'FXFE','Fedex Freight - Priority',0),(11,0,107,'FXNL','Fedex Freight - Economy',0),(12,0,108,'HMES','HOLLAND',0),(13,0,109,'HWEP','RIST TRANSPORT (Howards)',0),(14,0,110,'LAXV','Land Air Express of New England',0),(15,0,111,'LKVL','Lakeville Motor Express',0),(16,0,112,'NEBT','Nebraska Transport',0),(17,0,113,'NEMF','NEMF (New England Motor Freight)',0),(18,0,114,'NPME','NEW PENN',0),(19,0,115,'ODFL','OLD DOMINION',0),(20,0,116,'PITD','Pitt Ohio',0),(21,0,117,'PITD','Pitt Ohio (FRIDAY rates- for FRIDAY PICKUPS ONLY)',0),(22,0,118,'PYLE','A Duie Pyle Inc',0),(23,0,119,'RDWY','YRC <1000 EFF 6-16-2014',0),(24,0,120,'RDWY','YRC >1000 EFF 6-16-2014',0),(25,0,121,'RETL','REDDAWAY',0),(26,0,122,'SAIA','SAIA',0),(27,0,123,'SEFL','SOUTHEASTERN [SEFL]',0),(28,0,124,'SMTL','Southwestern Motor Transport (SMT)',0),(29,0,125,'STDF','STANDARD FORWARDING',0),(30,0,126,'UPGF','UPS Freight',0),(31,0,127,'USRD','US ROAD FREIGHT EXPRESS',0),(32,0,128,'WARD','Ward Trucking',0),(33,0,129,'WTVA','WILSON TRUCKING',0),(199,4161,97,'AACT','AAA COOPER',0),(200,4161,98,'BAHF','B & H FREIGHT LINE',0),(201,4161,99,'CNWY','CON-WAY FREIGHT',0),(202,4161,100,'CNWY','CON-WAY FREIGHT (Canada)',0),(203,4161,101,'CPPV','CALTOP LOGISTICS',0),(204,4161,102,'CRST','CRYSTAL MOTOR EXPRESS',0),(205,4161,103,'DAFG','Dayton Freight',0),(206,4161,104,'DUBL','Dugan Truck Line',0),(207,4161,105,'EXLA','ESTES',0),(208,4161,106,'FXFE','Fedex Freight - Priority',0),(209,4161,107,'FXNL','Fedex Freight - Economy',0),(210,4161,108,'HMES','HOLLAND',0),(211,4161,109,'HWEP','RIST TRANSPORT (Howards)',0),(212,4161,110,'LAXV','Land Air Express of New England',0),(213,4161,111,'LKVL','Lakeville Motor Express',0),(214,4161,112,'NEBT','Nebraska Transport',0),(215,4161,113,'NEMF','NEMF (New England Motor Freight)',0),(216,4161,114,'NPME','NEW PENN',0),(217,4161,115,'ODFL','OLD DOMINION',0),(218,4161,116,'PITD','Pitt Ohio',0),(219,4161,117,'PITD','Pitt Ohio (FRIDAY rates- for FRIDAY PICKUPS ONLY)',0),(220,4161,118,'PYLE','A Duie Pyle Inc',0),(221,4161,119,'RDWY','YRC <1000 EFF 6-16-2014',0),(222,4161,120,'RDWY','YRC >1000 EFF 6-16-2014',0),(223,4161,121,'RETL','REDDAWAY',0),(224,4161,122,'SAIA','SAIA',0),(225,4161,123,'SEFL','SOUTHEASTERN [SEFL]',0),(226,4161,124,'SMTL','Southwestern Motor Transport (SMT)',0),(227,4161,125,'STDF','STANDARD FORWARDING',0),(228,4161,126,'UPGF','UPS Freight',0),(229,4161,127,'USRD','US ROAD FREIGHT EXPRESS',0),(230,4161,128,'WARD','Ward Trucking',0),(231,4161,129,'WTVA','WILSON TRUCKING',0),(232,4948,97,'AACT','AAA COOPER',0),(233,4948,98,'BAHF','B & H FREIGHT LINE',0),(234,4948,99,'CNWY','CON-WAY FREIGHT',0),(235,4948,100,'CNWY','CON-WAY FREIGHT (Canada)',0),(236,4948,101,'CPPV','CALTOP LOGISTICS',0),(237,4948,102,'CRST','CRYSTAL MOTOR EXPRESS',0),(238,4948,103,'DAFG','Dayton Freight',0),(239,4948,104,'DUBL','Dugan Truck Line',0),(240,4948,105,'EXLA','ESTES',0),(241,4948,106,'FXFE','Fedex Freight - Priority',0),(242,4948,107,'FXNL','Fedex Freight - Economy',0),(243,4948,108,'HMES','HOLLAND',0),(244,4948,109,'HWEP','RIST TRANSPORT (Howards)',0),(245,4948,110,'LAXV','Land Air Express of New England',0),(246,4948,111,'LKVL','Lakeville Motor Express',0),(247,4948,112,'NEBT','Nebraska Transport',0),(248,4948,113,'NEMF','NEMF (New England Motor Freight)',0),(249,4948,114,'NPME','NEW PENN',0),(250,4948,115,'ODFL','OLD DOMINION',0),(251,4948,116,'PITD','Pitt Ohio',0),(252,4948,117,'PITD','Pitt Ohio (FRIDAY rates- for FRIDAY PICKUPS ONLY)',0),(253,4948,118,'PYLE','A Duie Pyle Inc',0),(254,4948,119,'RDWY','YRC <1000 EFF 6-16-2014',0),(255,4948,120,'RDWY','YRC >1000 EFF 6-16-2014',0),(256,4948,121,'RETL','REDDAWAY',0),(257,4948,122,'SAIA','SAIA',0),(258,4948,123,'SEFL','SOUTHEASTERN [SEFL]',0),(259,4948,124,'SMTL','Southwestern Motor Transport (SMT)',0),(260,4948,125,'STDF','STANDARD FORWARDING',0),(261,4948,126,'UPGF','UPS Freight',0),(262,4948,127,'USRD','US ROAD FREIGHT EXPRESS',0),(263,4948,128,'WARD','Ward Trucking',0),(264,4948,129,'WTVA','WILSON TRUCKING',0),(265,4151,97,'AACT','AAA COOPER',0),(266,4151,98,'BAHF','B & H FREIGHT LINE',0),(267,4151,99,'CNWY','CON-WAY FREIGHT',0),(268,4151,100,'CNWY','CON-WAY FREIGHT (Canada)',0),(269,4151,101,'CPPV','CALTOP LOGISTICS',0),(270,4151,102,'CRST','CRYSTAL MOTOR EXPRESS',0),(271,4151,103,'DAFG','Dayton Freight',0),(272,4151,104,'DUBL','Dugan Truck Line',0),(273,4151,105,'EXLA','ESTES',0),(274,4151,106,'FXFE','Fedex Freight - Priority',0),(275,4151,107,'FXNL','Fedex Freight - Economy',0),(276,4151,108,'HMES','HOLLAND',0),(277,4151,109,'HWEP','RIST TRANSPORT (Howards)',0),(278,4151,110,'LAXV','Land Air Express of New England',0),(279,4151,111,'LKVL','Lakeville Motor Express',0),(280,4151,112,'NEBT','Nebraska Transport',0),(281,4151,113,'NEMF','NEMF (New England Motor Freight)',0),(282,4151,114,'NPME','NEW PENN',0),(283,4151,115,'ODFL','OLD DOMINION',0),(284,4151,116,'PITD','Pitt Ohio',0),(285,4151,117,'PITD','Pitt Ohio (FRIDAY rates- for FRIDAY PICKUPS ONLY)',0),(286,4151,118,'PYLE','A Duie Pyle Inc',0),(287,4151,119,'RDWY','YRC <1000 EFF 6-16-2014',0),(288,4151,120,'RDWY','YRC >1000 EFF 6-16-2014',0),(289,4151,121,'RETL','REDDAWAY',0),(290,4151,122,'SAIA','SAIA',0),(291,4151,123,'SEFL','SOUTHEASTERN [SEFL]',0),(292,4151,124,'SMTL','Southwestern Motor Transport (SMT)',0),(293,4151,125,'STDF','STANDARD FORWARDING',0),(294,4151,126,'UPGF','UPS Freight',0),(295,4151,127,'USRD','US ROAD FREIGHT EXPRESS',0),(296,4151,128,'WARD','Ward Trucking',0),(297,4151,129,'WTVA','WILSON TRUCKING',0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.shippingOrder.temperature', 'Temperature', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.shippingOrder.tempControl', 'Temp Control', 'en_CA', 1);

DROP TABLE IF EXISTS `address_checklist`;
CREATE TABLE `address_checklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) NOT NULL,
  `commercial_business` tinyint(1) DEFAULT '0',
  `description` varchar(45) DEFAULT NULL,
  `pickup_or_deliver` tinyint(1) DEFAULT '0',
  `prior_to_pickup` tinyint(1) DEFAULT '0',
  `dock_level` tinyint(1) DEFAULT '0',
  `pallet_jack` tinyint(1) DEFAULT '0',
  `power_tailgate` tinyint(1) DEFAULT '0',
  `inside_pickup` tinyint(1) DEFAULT '0',
  `floor_no` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`,`address_id`)
);
INSERT INTO `address_checklist` VALUES (1,97445,0,'',0,0,0,0,0,0,-1),(2,3504,0,'IC10006_1',0,0,0,0,0,0,-1),(3,426,1,'IC10006_1',1,0,0,0,0,1,-1),(4,0,0,'IC10008_1',0,0,0,0,0,0,-1),(5,314543,0,'',1,0,0,1,0,0,0),(6,164619,0,'IC10014_0',0,0,0,0,0,0,0);

INSERT INTO `carrier_charge_code` (carrier_id,charge_code,charge_code_level_2,charge_name,charge_desc,charge_group_id,charge,cost,customer_id)VALUES (6,'050','050','Freight Charge','Freight Charge',1,0,0,0)
,(6,'FUEL','010','Fuel Surcharge','Fuel Surcharge',3,0,0,0),
(6,'IC10001_1','CHURCHP','CHURCH OR PLACE OF WORSHIP','CHURCH OR PLACE OF WORSHIP PICKUP',2,0,0,0),
(6,'IC10001_2','CHURCHD','CHURCH OR PLACE OF WORSHIP','CHURCH OR PLACE OF WORSHIP DELIVERY	',2,0,0,0),
(6,'IC10002_1','CUP','CONSTRUCTION/UTILITY','CONSTRUCTION/UTILITY PICK-UP',2,0,0,0),
(6,'IC10002_2','CUD','CONSTRUCTION/UTILITY','CONSTRUCTION/UTILITY DELIVERY',2,0,0,0),
(6,'IC10003_1','GOVPU','GOVERNMENT SERVICE','GOVERNMENT PICKUP',2,0,0,0),
(6,'IC10003_2','GOVDE','GOVERNMENT SERVICE','GOVERNMENT DELIVERY',2,0,0,0),
(6,'IC10004_1','MINE SITE PICKUP','MINE SITE ','MINE SITE PICKUP',2,0,0,0),
(6,'IC10004_2','MINE SITE DELIVERY','MINE SITE ','MINE SITE DELIVERY',2,0,0,0),
(6,'IC10005_1','LAP','NON-COMMERCIAL/ LIMITED ACCESS','NON-COMMERCIAL/ LIMITED ACCESS PICKUP/NOI',2,0,0,0),
(6,'IC10005_2','LAD','NON-COMMERCIAL/ LIMITED ACCESS','NON-COMMERCIAL/ LIMITED ACCESS DELIVERY/NOI',2,0,0,0),
(6,'IC10006_1','SCHP','SCHOOL SERVICE','SCHOOL PICKUP',2,0,0,0),
(6,'IC10006_2','SCHD','SCHOOL SERVICE','SCHOOL DELIVERY',2,0,0,0),
(6,'IC10007_1','INP','INSIDE -STREET LEVEL SERVICE','INSIDE PICKUP -STREET LEVEL',2,0,0,0),
(6,'IC10007_2','IND','INSIDE -STREET LEVEL SERVICE','INSIDE DELIVERY -Street Level',2,0,0,0),
(6,'IC10008_1','PJACKP','PALLET JACK','PALLET JACK SERVICE AT PICKUP',2,0,0,0),
(6,'IC10008_2','PJACKD','PALLET JACK','PALLET JACK SERVICE AT DELIVERY',2,0,0,0),
(6,'IC10009_1','LGP','LIFT GATE ','LIFT-GATE SERVICE PICKUP',2,0,0,0),
(6,'IC10009_2','LGD','LIFT GATE ','LIFT GATE DELIVERY',2,0,0,0),
(6,'IC10010_1','SELFSTOP','PICKUP/DELIVERY TO SELF STORAGE FACILITY','PU AT SELF STORAGE FACILITY',2,0,0,0),
(6,'IC10010_2','SELFSTOD','PICKUP/DELIVERY TO SELF STORAGE FACILITY','DELIVERY TO SELF STORAGE FACILITY',2,0,0,0),
(6,'IC10011_2','RAD','REMOTE ACCESS DELIVERY','REMOTE ACCESS DELIVERY',2,0,0,0),
(6,'IC10011_0','HAZ','HAZARDOUS MATERIALS	','HAZARDOUS MATERIALS	',2,0,0,0),
(6,'IC10012_0','PTS','PROTECT FROM FREEZE','PROTECT FROM FREEZE	',2,0,0,0),
(6,'IC10013_0','DNT','DESTINATION NOTIFY OR APPOINTMENT','DESTINATION NOTIFY OR APPOINTMENT',2,0,0,0),
(6,'IC10014_0','DENCOR','CORRECTION/VERIFICATION FEE','CORRECTION/VERIFICATION FEE',2,0,0,0),
(6,'IC10015_2','RESD','RESIDENTIAL','RESIDENTIAL DELIVERY',2,0,0,0),
(6,'IC10015_1','RESP','RESIDENTIAL','RESIDENTIAL PICKUP',2,0,0,0);

INSERT INTO `carrier_charge_code` ( `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ( '10', 'IC10008_1', 'PJACKP', 'PALLET JACK', 'PALLET JACK SERVICE AT PICKUP', '2', '0', '50', '0');

INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('10', 'IC10008_2', 'PJACKD', 'PALLET JACK', 'PALLET JACK SERVICE AT DELIVERY', '2', '0', '50', '0');

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.tailGateDelivery', 
'Tailgate Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.fromPalletJack', 
'PalletJack Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.toPalletJack', 
'PalletJack Delivery', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.fromDockLevel', 
'DockLevel Pickup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, 
`locale`, `is_fmk`) VALUES ('label.viewship.toDockLevel', 
'DockLevel Delivery', 'en_CA', 1);

CREATE TABLE `user_eshipplus_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL,
  `date_time` VARCHAR(45) NULL,
  `order_id` VARCHAR(45) NULL,
  `auto_freight_class` BIT NULL DEFAULT b'0',
  PRIMARY KEY (`id`));


DROP TABLE IF EXISTS `accessorial_services`;
CREATE TABLE `accessorial_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billing_code` varchar(45) DEFAULT NULL,
  `service_code` varchar(45) DEFAULT NULL,
  `service_description` varchar(45) DEFAULT NULL,
  `type` tinyint(1) DEFAULT '0',
  `service_group` varchar(45) DEFAULT NULL,
  `service_group_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `accessorial_services` VALUES (1,'CHURCHD','CHURCHD','CHURCH OR PLACE OF WORSHIP DELIVERY',2,'CHURCH OR PLACE OF WORSHIP','CHURCH'),(2,'CHURCHP','CHURCHP','CHURCH OR PLACE OF WORSHIP PICKUP',1,'CHURCH OR PLACE OF WORSHIP','CHURCH'),(3,'CUD','CUD','CONSTRUCTION/UTILITY DELIVERY',2,'CONSTRUCTION/UTILITY','CU'),(4,'CUP','CUP','CONSTRUCTION/UTILITY PICK-UP',1,'CONSTRUCTION/UTILITY','CU'),(5,'SELFSTOD','SELFSTOD','DELIVERY TO SELF STORAGE FACILITY',2,'DELIVERY TO SELF STORAGE FACILITY','SELFSTO'),(6,'GOVDE','GOVDE','GOVERNMENT DELIVERY',2,'GOVERNMENT SERVICE','GOV'),(7,'GOVPU','GOVPU','GOVERNMENT PICKUP',1,'GOVERNMENT SERVICE','GOV'),(8,'MSD','MSD','MINE SITE DELIVERY',2,'MINE SITE ','MS'),(9,'MSP','MSP','MINE SITE PICKUP',1,'MINE SITE ','MS'),(10,'LAD','LAD','NON-COMMERCIAL/ LIMITED ACCESS DELIVERY/NOI',2,'NON-COMMERCIAL/ LIMITED ACCESS','LA'),(11,'LAP','LAP','NON-COMMERCIAL/ LIMITED ACCESS PICKUP/NOI',1,'NON-COMMERCIAL/ LIMITED ACCESS','LA'),(12,'SELFSTOP','SELFSTOP','PU AT SELF STORAGE FACILITY',1,'PU AT SELF STORAGE FACILITY','SELFSTO'),(13,'RAD','RAD','REMOTE ACCESS DELIVERY',2,'REMOTE ACCESS','RA'),(14,'SCHD','SCHD','SCHOOL DELIVERY',2,'SCHOOL SERVICE','SCH'),(15,'SCHP','SCHP','SCHOOL PICKUP',1,'SCHOOL SERVICE','SCH'),(16,'HAZ','HAZ','HAZARDOUS MATERIALS',0,'HAZARDOUS MATERIALS','HAZ'),(17,'PTS','PTS','PROTECT FROM FREEZE',0,'PROTECT FROM FREEZE','PTS'),(18,'INP','INP','INSIDE PICKUP -STREET LEVEL',1,'INSIDE -STREET LEVEL SERVICE','IN'),(19,'DNT','DNT','DESTINATION NOTIFY OR APPOINTMENT',0,'DESTINATION NOTIFY OR APPOINTMENT','DNT'),(20,'PJACKD','PJACKD','PALLET JACK SERVICE AT DELIVERY',2,'PALLET JACK ','PJACK'),(21,'PJACKP','PJACKP','PALLET JACK SERVICE AT PICKUP',1,'PALLET JACK ','PJACK'),(22,'LGD','LGD','LIFT GATE DELIVERY',2,'LIFT GATE ','LG'),(23,'LGP','LGP','LIFT-GATE SERVICE PICKUP',1,'LIFT GATE ','LG'),(24,'IND','IND','INSIDE DELIVERY -Street Level',2,'INSIDE -STREET LEVEL SERVICE','IN');

  
UPDATE `carrier` SET `name`='Integrated Carriers' WHERE `carrier_id`='6';
  
UPDATE `business_carrier` SET `display_name`='Integrated Carriers' WHERE `bc_id`='2031' and`business_id`='1' and`carrier_id`='6';
  
INSERT INTO carrier_charge_code(`id`, `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('', '6', 'HOM', 'HOM', 'HOMELAND SECURITY-BORDER PROCESSING FEE', 'HOMELAND SECURITY-BORDER PROCESSING FEE', '1', '0', '0', '0');

ALTER TABLE `customer_carrier` 
CHANGE COLUMN `property_5` `property_5` VARCHAR(100) NULL DEFAULT NULL ;

INSERT INTO `carrier_charge_code` (`id`, `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('', '1070', 'FRT', 'FRT', 'Freight', 'Freight', '1', '0', '0', '0');
INSERT INTO `carrier_charge_code` (`id`, `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('', '1070', 'FRT', 'FRT', 'Freight', 'Freight', '1', '0', '0', '0');

INSERT INTO `carrier_charge_code` (`id`, `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('', '1070', 'FRT', 'FRT', 'Freight', 'Freight', '1', '0', '0', '0');
INSERT INTO `carrier_charge_code` (`id`, `carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('', '1070', 'FSC', 'FUE', 'Fuel', 'Fuel', '3', '0', '0', '0');

UPDATE `charge_group` SET `is_taxable`='Yes' WHERE `id`='1';
UPDATE `charge_group` SET `is_taxable`='Yes' WHERE `id`='2';
UPDATE `charge_group` SET `is_taxable`='Yes' WHERE `id`='3';
UPDATE `charge_group` SET `is_taxable`='Yes' WHERE `id`='4';

UPDATE `carrier_charge_code` SET `charge_group_id`='14' WHERE `id`='4045';

UPDATE `carrier` SET `name`='eShipPlus' WHERE `carrier_id`='6';

UPDATE `service` SET `master_carrier_id`='10', `master_service_id`='200021' WHERE `service_id`='200021';
UPDATE `service` SET `master_carrier_id`='10', `master_service_id`='200022' WHERE `service_id`='200022';
UPDATE `service` SET `master_carrier_id`='10' WHERE `service_id`='2000023';

INSERT INTO `customer_carrier` (`customer_id`, `business_id`, `country`, `isdefaultaccount`, `account_number1`, `account_number2`, `property_1`, `property_2`, `property_3`, `property_4`, `property_5`, `carrier_id`, `isLive`) VALUES ('0', '1', 'US', '1', '20000', '', 'ryan.blakey', 'ry1234', 'TENANT1', 'dd9c2694-672b-4498-9a96-2a96f3f82364', 'http://lpdev.eshipplus.com/services/eshippluswsv4.asmx', '6', '0');

ALTER TABLE `user_history` 
ADD COLUMN `auto_freight_class` BIT(1) NULL DEFAULT b'0' AFTER `isbusiness_level`;


INSERT INTO `customer_carrier` (`carrieraccount_id`, `customer_id`, `business_id`, `country`, `isdefaultaccount`, `account_number1`, `account_number2`, `property_1`, `property_2`, `property_3`, `property_4`, `property_5`, `carrier_id`, `isLive`) VALUES (NULL, '0', '1', 'US', '1', '20000', '', 'ryan.blakey', 'ry1234', 'TENANT1', 'dd9c2694-672b-4498-9a96-2a96f3f82364', 'http://lpdev.eshipplus.com/services/eshippluswsv4.asmx', '6', '0');

ALTER TABLE `business_loader` 
ADD COLUMN `error_msg` VARCHAR(200) NULL DEFAULT NULL AFTER `footer2`;

ALTER TABLE `business_loader` 
ADD COLUMN `package_image` BLOB NULL DEFAULT NULL AFTER `error_msg`;


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.upload.packageImage', 'Upload Package Image', 'en_CA', 1);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.upload.actionError.ForBusiness', 'Enter Transit Time Action Msg', 'en_CA', 1);

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('shipment.autoFreightClassCheck', '221', 1, 'Auto Frieght class check', 1);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1079');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1079');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_shipper', '1079');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1079');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_base', '1079');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('shipment.updateCheckList', '221', 1, 'AddressChecklist', 1);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1080');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1080');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1080');

insert into action(action,menu_id,highlight,description,reload_safe) values('searchEshipPlusMarkup',161,1,'Eship markup search page',1);
insert into role_action(role,action_id) values('busadmin',1081);
insert into role_action(role,action_id) values('sysadmin',1081);
 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('delete.invoice.pdf', '181', 1, 'Delete invoice pdf', 1);


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1082');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1082');



INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.delete.pdfFile', 'DELETE FILE', 'en_CA', 1);
--------------------------------------END of LIVE SERVER COMMIT---------------------------------------

DROP PROCEDURE IF EXISTS insert_commission_amount;
DELIMITER $$
CREATE DEFINER=`soluship`@`%` PROCEDURE `insert_commission_amount`()
BEGIN
	DECLARE no_record_found INTEGER DEFAULT 0;
	DECLARE var_customer_id INTEGER;
	DECLARE var_customer_name VARCHAR(255);
    DECLARE var_invoice_id INTEGER;
	DECLARE var_invoice_num VARCHAR(45);
	DECLARE var_invoice_amount DOUBLE;
	DECLARE var_invoice_amount1 DOUBLE;
	DECLARE var_invoice_cost DOUBLE;
	DECLARE var_invoice_cost1 DOUBLE;
	DECLARE var_payment_status INTEGER;
	DECLARE var_invoice_date DATETIME;
	DECLARE var_chb_total DOUBLE;
	DECLARE var_spd_total DOUBLE;
	DECLARE var_ltl_total DOUBLE;
	DECLARE var_fwd_total DOUBLE;
	DECLARE var_fpa_total DOUBLE;
	DECLARE var_inv_currency VARCHAR(45);
	DECLARE cur_invoices CURSOR FOR
		SELECT 	i.customer_id, c.name, i.invoice_id, i.invoice_num,
				i.invoice_amount, i.invoice_cost, i.payment_status,
				i.invoice_date, i.chb_total, i.spd_total, i.ltl_total, 
				i.fwd_total, i.fpa_total, i.currency
		FROM 	invoice i, customer c
		WHERE	i.customer_id = c.customer_id
		AND		i.commission_calculated = 0;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_record_found = 1;
	DELETE FROM sp_log WHERE id <> -1;
	OPEN cur_invoices;
	GET_INVOICES: LOOP
		FETCH cur_invoices INTO var_customer_id, var_customer_name, var_invoice_id, var_invoice_num, 
							   var_invoice_amount, var_invoice_cost, var_payment_status,
							   var_invoice_date, var_chb_total, var_spd_total, var_ltl_total,
							   var_fwd_total, var_fpa_total, var_inv_currency;
        IF no_record_found = 1 THEN
			INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', 'No Data found in cur_invoices');
			LEAVE GET_INVOICES;
        END IF;
		INNERBLOCK1: BEGIN
			DECLARE var_user_id INTEGER;
			DECLARE var_sales_user VARCHAR(255);
			DECLARE var_spd_percent DOUBLE;
			DECLARE var_ltl_percent DOUBLE;
			DECLARE var_chb_percent DOUBLE;
			DECLARE var_fwd_percent DOUBLE;
			DECLARE var_fpa_percent DOUBLE;
			DECLARE var_usr_currency VARCHAR(45);
			DECLARE cur_users CURSOR FOR
				SELECT 	cs.id as user_id, cs.sales_user as sales_user,
						COALESCE(cs.comm_perc_PS,0) as spd_percent, 
						COALESCE(cs.comm_perc_PP,0) as ltl_percent, 
						COALESCE(cs.comm_perc_CHB,0) as chb_percent,
						COALESCE(cs.comm_perc_FWD,0) as fwd_percent, 
						COALESCE(cs.comm_perc_FPA,0) as fpa_percent,
						COALESCE(sy.currency_code,'CAD') as currency_code
				FROM 	customer_sales cs left join user us on cs.sales_user = us.username
						left join currency_symbol sy on 
						(sy.country_code = SUBSTRING_INDEX(SUBSTRING_INDEX( us.locale , '_', 2 ),'_',-1) 
						and sy.language_code = SUBSTRING_INDEX(us.locale , '_', 1 ))
				WHERE 	cs.customer_id = var_customer_id;
			OPEN cur_users;
			GET_USERS: LOOP
				FETCH cur_users INTO var_user_id, var_sales_user, var_spd_percent, var_ltl_percent,
									 var_chb_percent, var_fwd_percent, var_fpa_percent, var_usr_currency;
				IF no_record_found = 1 THEN
					INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', 'No Data found in cur_users');
                    SET no_record_found = 0;
					LEAVE GET_USERS;
				END IF;
				set @exchangeRate = 1;
				IF (STRCMP(var_usr_currency, var_inv_currency) <> 0) THEN
					INSERT INTO sp_log (`level`, `message`) VALUES ('INFO', 'Conversion Needed');
					set @exchangeRate = (SELECT COALESCE(ex.exch_rate,1) FROM exchange_rate_currency ex WHERE cur_from = var_inv_currency AND cur_to = var_usr_currency);
				END IF;
				set @commissionSPD = 0;
				set @commissionLTL = 0;
				set @commissionCHB = 0;
				set @commissionFWD = 0;
				set @commissionFPA = 0;
                INNERBLOCK2: BEGIN
					DECLARE var_profit DOUBLE;
                    DECLARE var_email_type VARCHAR(45);
					DECLARE cur_charges CURSOR FOR
						SELECT	sum(res.charge - (res.cost * res.conversion_rate)) as 							
								profit, res.email_type
						FROM 	(SELECT COALESCE(	(SELECT 	ex.exch_rate 
													FROM 	exchange_rate_currency ex 
													WHERE 	cur_from = cost_currency 
													AND		cur_to = charge_currency),1) as conversion_rate, 
										chs.cost, chs.charge, chs.email_type, chs.id, chs.is_tax
								FROM	(SELECT	ch.id, ch.charge, cs1.currency_code as charge_currency,
												ch.cost, cs2.currency_code as cost_currency, s.email_type,
												COALESCE((
													SELECT	cg.is_tax
													FROM	charge_group cg, carrier_charge_code ccc
													WHERE	ccc.carrier_id = ch.carrier_id
													AND		ccc.charge_code = ch.charge_code
													AND		(SELECT IF((ch.charge_code_level_2 is NULL),true,
															(ccc.charge_code_level_2 = ch.charge_code_level_2)))
													AND		cg.id = ccc.charge_group_id
													LIMIT 1),0) as is_tax
										FROM  	invoice_charges ic, shipping_order so, currency_symbol cs1, 
												currency_symbol cs2, charges ch, service s
										WHERE	ic.invoice_id = var_invoice_id
										AND  	so.order_id = ic.order_id
										AND  	ic.charge_id=ch.id
										AND  	ic.cancelled_invoice='No'
										AND  	ch.type = 1
										AND     ch.commissionable =1
										AND  	ch.status !=40
										AND  	s.service_id=so.service_id
										AND  	cs1.id = ch.charge_currency
										AND  	cs2.id = ch.cost_currency) as chs) as res
						WHERE res.is_tax = 0
						GROUP BY email_type;
                    OPEN cur_charges;
					GET_CHARGES: LOOP
						FETCH cur_charges INTO var_profit, var_email_type;
						IF no_record_found = 1 THEN
							INSERT INTO sp_log (`level`, `message`) VALUES ('DEBUG', var_usr_currency);
							SET no_record_found = 0;
							LEAVE GET_CHARGES;
						END IF;
						IF (STRCMP(var_email_type, 'SPD') = 0)THEN
                            SET @commissionSPD = (var_profit * (var_spd_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(var_email_type, 'CHB') = 0)THEN
							SET @commissionCHB = (var_profit * (var_chb_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(var_email_type, 'LTL') = 0)THEN
							SET @commissionLTL = (var_profit * (var_ltl_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(var_email_type, 'FWD') = 0)THEN
							SET @commissionFWD = (var_profit * (var_fwd_percent / 100)) * @exchangeRate;
						ELSEIF (STRCMP(var_email_type, 'FPA') = 0)THEN
							SET @commissionFPA = (var_profit * (var_fpa_percent / 100)) * @exchangeRate;
						END IF;
                    END LOOP GET_CHARGES;
                    CLOSE cur_charges;
                END INNERBLOCK2;
				set var_invoice_amount1 = var_invoice_amount*@exchangeRate;
				set var_invoice_cost1 = var_invoice_cost*@exchangeRate;
				set @commissionPayable = @commissionSPD + @commissionLTL + @commissionCHB + @commissionFWD + @commissionFPA;
				INSERT INTO commission(`customer_id`,`customer_name`,`invoice_id`,`invoice_num`,`user_id`,`sales_user`,
									   `invoice_total`,`cost_total`,`commission_payable`,`customer_paid`,`rep_paid`,
									   `date_created`,`total_spd`,`total_ltl`,`total_chb`,`total_fwd`,`total_fpa`, `currency`)
				VALUES				  (var_customer_id, var_customer_name, var_invoice_id, var_invoice_num, var_user_id, var_sales_user,
									   var_invoice_amount1, var_invoice_cost1, @commissionPayable, var_payment_status, var_payment_status,
									   var_invoice_date, @commissionSPD, @commissionLTL, @commissionCHB, @commissionFWD, @commissionFPA, var_usr_currency);
				
			END LOOP GET_USERS;
			CLOSE cur_users;
		END INNERBLOCK1;
		update invoice i set i.commission_calculated=true where i.invoice_id=var_invoice_id;
    END LOOP GET_INVOICES;
    CLOSE cur_invoices;
END$$
DELIMITER ;

ALTER TABLE charges 
ADD COLUMN `commissionable` BIT NOT NULL DEFAULT 1 AFTER `calculate_tax`;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ghead.commissonable', 'Commissionable', 'en_CA', 1);

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`) VALUES ('copyParent.markup', '161', 1, 'Copy Parent Markup');


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1084');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1084');


alter table business add isCopy_markup bit(1) default b'0';


insert into resourcebundle (msg_id, msg_content, locale, is_fmk)
values ('label.btn.copy.parent', 'COPY PARENT MARKUP', 'en_CA', 1);



ALTER TABLE `business` ADD COLUMN `custom_markup` INT(10) NULL DEFAULT '0' AFTER `feedback_path`;

ALTER TABLE `business` ADD COLUMN `markup_type` INT(10) NULL DEFAULT '0' AFTER `custom_markup`;

ALTER TABLE `business_markup` CHANGE COLUMN `disabled` `disabled` TINYINT(1) NOT NULL DEFAULT '0' ;

ALTER TABLE `business_markup` 
CHANGE COLUMN `mu_perc` `mu_perc` INT(10) UNSIGNED NOT NULL DEFAULT '0' ,
CHANGE COLUMN `flat_mu` `flat_mu` FLOAT NOT NULL DEFAULT '0' ;

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.customMarkup', 'Business Markup', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.markupType', 'Markup Type', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.admin.businessmarkup', 'Business Markup', 'en_CA', 0);
INSERT INTO `menu` (`name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`, `image_over`, `help_tag`, `support_tag`) VALUES ('Business Markup', '/admin/businessMarkup.action', '1', 'LEVEL_2', '2', '123', 'menu.admin.businessmarkup', 'N', 'N', '<p>This section is for Help Information</p>', '<p>This section is for Support Information</p>');

INSERT INTO `role_menu` (`menu_id`, `role`) VALUES ('442', 'sysadmin');

INSERT INTO `business_menu` (`business_id`, `menu_id`) VALUES ('1', '442');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('markup.listCarriers', '161', 1, 'Search Carrier for Markup page', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1086');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('addBusinessMarkup', '161', 1, 'Add Business Markup page', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1087');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('searchBusinessMarkup', '161', 1, 'Search Business Markup page', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1088');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('markup.listCustomers', '161', 1, 'Search Customer for Markup page', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1089');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('deleteBusinessMarkup', '161', 1, 'Search Customer for Markup page', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1090');
INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('saveBusinessMarkupList', '161', 1, 'Save Business Markup List', 1);
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1091');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.heading.businessmarkup', 'Business Markups', 'en_CA', 0);
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.business.parentBusiness', 'Parent Markup Business', 'en_CA', 1);

ALTER TABLE `business` 
ADD COLUMN `parent_markup_business_id` INT(10) NOT NULL AFTER `markup_type`;

ALTER TABLE `business` 
ADD COLUMN `parent_customer_id` INT(10) NULL DEFAULT '0' AFTER `parent_markup_business_id`;

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('businessMarkup', '161', 1, 'Business Markup page', 1, '');
INSERT INTO `solushipbusmark`.`role_action` (`role`, `action_id`) VALUES ('sysadmin', '1083');

#Shopify sql


#Level 1 E-commerce MENU
INSERT INTO   `menu` (`id`, `name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`, `image_over`, `help_tag`) VALUES ('443', 'E-commerce', '/admin/listEcom.store.action', '6', 'LEVEL_1', '1', '111', 'menu.ecommerce', 'N', 'N', '');
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.ecommerce', 'E-COMMERCE', 'en_CA', 1);
INSERT INTO   `business_menu` (`business_id`, `menu_id` ) VALUES ('1', '443');
INSERT INTO   `role_menu` (`menu_id`, `role` ) VALUES ('443', 'sysadmin');
INSERT INTO   `role_menu` (`menu_id`, `role` ) VALUES ('443', 'busadmin');
INSERT INTO   `role_menu` (`menu_id`, `role` ) VALUES ('443', 'customer_admin');

#Level 2 Store MENU

INSERT INTO   `menu` (`id`, `name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`, `image_over`, `help_tag`) VALUES ('444', 'Store', '/admin/listEcom.store.action', '1', 'LEVEL_2', '2', '443', 'menu.ecom.store', 'N', 'N', '');
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk` ) VALUES ('menu.ecom.store', 'Stores', 'en_CA', 1 );
INSERT INTO   `business_menu` (`business_id`, `menu_id`) VALUES ('1', '444');
INSERT INTO   `role_menu` (`menu_id`, `role` ) VALUES ('444', 'sysadmin');
INSERT INTO   `role_menu` (`menu_id`, `role`) VALUES ('444', 'busadmin');
INSERT INTO   `role_menu` (`menu_id`, `role`) VALUES ('444', 'customer_admin');





#MENU
INSERT INTO `menu` (`id`, `name`, `url`, `display_order`, `level`, `level_no`, `parent_id`, `label_id`, `image`, `image_over`) VALUES ('445', 'Product-Package Map', '/admin/packageMap.action', '7', 'LEVEL_1', '1', '111', 'menu.product.pack.map', 'N', 'N');
INSERT INTO    `role_menu` (`menu_id`, `role`) VALUES ('445', 'customer_admin');
INSERT INTO    `business_menu` (`business_id`, `menu_id`) VALUES ('1', '445');
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.product.pack.map', 'PACKAGE MAP', 'en_CA', 1);




#Ecommerce Actions


INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('listEcom.store', '443', 0, 'List Ecommerce', 1, '1099'  );
INSERT INTO   `role_action` (`role`, `action_id` ) VALUES ('sysadmin', '1099'  );
INSERT INTO   `role_action` (`role`, `action_id` ) VALUES ('busadmin', '1099'  );
INSERT INTO  `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1099');


INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('newEcom.store', '444', 0, 'Add stores', 1, '1100');
INSERT INTO   `role_action` (`role`, `action_id` ) VALUES ('sysadmin', '1100');
INSERT INTO   `role_action` (`role`, `action_id` ) VALUES ('busadmin', '1100');
INSERT INTO  `role_action` (`role`, `action_id`  ) VALUES ('customer_admin', '1100');

#....................................................

INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('saveEcom.store', '444', 1, 'Save the store', 1, '1101');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('sysadmin', '1101' );
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('busadmin', '1101');
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('customer_admin', '1101');


INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('editEcom.store', '444', 1, 'edit the store', 1, '1102');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('sysadmin', '1102');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('busadmin', '1102');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1102');

INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('deleteEcom.store', '444', 1, 'delete the store', 1, '1103');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('sysadmin', '1103');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('busadmin', '1103');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1103');


 
INSERT INTO   `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('editEcom.config', '444', 1, 'edit Ecommerfce configration', 1, '1104');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('sysadmin', '1104');
INSERT INTO   `role_action` (`role`, `action_id`   ) VALUES ('busadmin', '1104');

 
#=================================================
#SHOPIFY PRODUCT SYNC

INSERT INTO  `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('synchShopifyProduct', '404', 1, 'synch shopify product', 1,'1105');
INSERT INTO `role_action` (`role`, `action_id` ) VALUES ('sysadmin', '1105');
INSERT INTO  `role_action` (`role`, `action_id` ) VALUES ('busadmin', '1105');
INSERT INTO  `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1105');





INSERT INTO    `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('packageMap', '445', 1, 'prodcut package map', 1, '1106');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1106');



INSERT INTO    `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('addPackageMap', '445', 1, 'addPackageMap', 1, '1107');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1107');

#delete.packageMap
INSERT INTO    `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('delete.packageMap', '445', 1, 'delete.packageMap', 1, '1108');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1108');

#edit.packageMap
INSERT INTO    `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`, `id`) VALUES ('edit.packageMap', '445', 1, 'edit.packageMap', 1, '1109');
INSERT INTO    `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1109');






#resource bundles

INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.list.EcommerceStores', 'E-Commerce Store List', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`   ) VALUES ('label.add.ecom.store', 'ADD STORE', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.name', 'Store Name', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.url', 'Store URL', 'en_CA', 1);INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.username', 'Store User', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.username', 'Store User', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.add', 'Add Store', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.edit', 'Edit Store', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.apiKey', 'API Key', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`   ) VALUES ('label.ecom.store.sharedkey', 'shared secret key', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`   ) VALUES ('label.ecom.store.password', 'Store Password', 'en_CA', 1);
INSERT INTO   `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.ecom.store.username', 'Store User Name', 'en_CA', 1);




INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.ship.customize', 'E-commerce  Shipment Customisation', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.customer', 'E-commerce Customer', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.rate.url', 'Rate Service URL', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.createship.url', 'Create Shipment URL', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.cancelship.url', 'Cancel Shipment URL', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.domain', 'E-Commerce Domain', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('package.products', 'Products', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('product.package.map.list', 'PRODUCT PACKAGE MAP LIST', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('product.package.map', 'PRODUCT PACKAGE MAP', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('shipping.service', 'Shipping Service', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('ecommerce.cancelship.url', 'Cancel Shipment URL', 'en_CA', 1);
INSERT INTO    `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('menu.product.sych', 'SYNC PRODUCTS', 'en_CA', 1);





#=============================================================
#ALTER TABLE IN PROUDCTS TABEL

ALTER TABLE `products` 
ADD COLUMN `sku_id` VARCHAR(45) NULL AFTER `total_count`,
ADD COLUMN `reference1` VARCHAR(45) NULL AFTER `sku_id`,
ADD COLUMN `reference1_name` VARCHAR(45) NULL AFTER `reference1`;
ALTER TABLE  `products` 
CHANGE COLUMN `product_description` `product_description` TEXT NULL DEFAULT NULL ;

 ALTER TABLE `products` 
ADD COLUMN `length` DOUBLE NULL AFTER `reference1_name`,
ADD COLUMN `width` DOUBLE NULL AFTER `length`,
ADD COLUMN `height` DOUBLE NULL AFTER `width`;


ALTER TABLE  `package_types` 
ADD COLUMN `unitmeasure_id` INT(10) NULL AFTER `customer_id`;

ALTER TABLE `products` 
ADD COLUMN `unitmeasure_id` INT(10) NULL AFTER `height`;

ALTER TABLE `shipping_order` 
ADD COLUMN `generated_by` VARCHAR(45) NULL AFTER `link_to_order`;




INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SHOPIFY_SOLUHSIP_API_NAME', 'SOLUSHIP SHIPPING API');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SHOPIFY_API_KEY', 'a76d9d5646b2eb37a2fdb9e781c1ffdc');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SHOPIFY_SHARED_SCRECT', 'cdf05280b09cc480740a967fb265e0dc');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SHOPIFY_REDIRECT_URL', 'https://soluship.com/ecomSignup.action');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SOLUSHIP_CONTACT_US_URL', 'https://soluship.com/solutions/visitor/index.php?/LiveChat/Chat/Request/_sessionID=3fqxtvus07lqnvwu81rosvypi1dwfw0o/_proactive=0/_filterDepartmentID=/_randomNumber=59/_fullName=/_email=/_promptType=chat');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'SHOPIFY_REQUEST_SCOPES', 'read_products, write_products,read_customers ,read_orders,write_shipping,read_fulfillments, write_fulfillments');
INSERT INTO     `property` (`scope`, `name`, `value`) VALUES ('SHOPIFY', 'WEBHOOK_UNINSTALL_NOTIFY_URL', 'https://soluship.com/api/v1/uninstallShopify');

 

 CREATE TABLE `ecommerce_store` (
  `store_ecom_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_url` varchar(150) DEFAULT NULL,
  `business_id` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `scopes` varchar(500) DEFAULT NULL,
  `access_key` varchar(45) DEFAULT NULL,
  `rate_service_url` varchar(150) DEFAULT NULL,
  `create_shipment_url` varchar(150) DEFAULT NULL,
  `api_key` varchar(45) DEFAULT NULL,
  `shared_scret` varchar(45) DEFAULT NULL,
  `ecommerce_domain` varchar(45) DEFAULT NULL,
  `chepest` bit(1) DEFAULT b'0',
  `fastest` bit(1) DEFAULT b'0',
  `both_service` bit(1) DEFAULT b'0',
  `cancel_shipment_url` varchar(150) DEFAULT NULL,
  `webhook_create_shipment` varchar(45) DEFAULT NULL,
  `webhook_cancel_shipment` varchar(45) DEFAULT NULL,
  `rate_service_id` varchar(45) DEFAULT NULL,
  `active` bit(1) DEFAULT b'0',
  `installation_url` varchar(200) DEFAULT NULL,
  `package_map` bit(1) DEFAULT b'0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `installed_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`store_ecom_id`),
  UNIQUE KEY `store_url_UNIQUE` (`store_url`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;


CREATE TABLE `product_package_map` (
  `product_package_map_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) DEFAULT NULL,
  `package_id` int(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`product_package_map_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;



CREATE TABLE `ecom_log` (
  `ecom_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `shopify_order_id` varchar(45) DEFAULT '0',
  `customer_id` int(10) DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ecom_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

#end of shoipfy query

#start of purolator freight query

insert into carrier values('21', 'Purolator Frieght', 'http://www.purolatorfreight.com/scripts/cgiip.exe/boldetail.htm?wbtn=PRO&wpro1=*trackingnum&seskey=&nav=side&language=english', 'purolatorFreightService', '18', NULL, NULL, '1', NULL)



insert into service values('4100', 'Expedited LTL', '21', '21', NULL, 'Expedited LTL', 'Expedited LTL', '202', NULL, '1', '0', NULL, '0', '48', '48', '96', '10000', 'LTL', '0', '0', '0', '0', '0', '0')

INSERT INTO `business_carrier` (`business_id`, `carrier_id`, `business_carrier_discount`, `display_name`) VALUES ('1', '21', '0', 'Purolator Frieght');

INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`) VALUES ('21', 'FRT', 'FRT', 'Freight', 'Freight', '1', '0', '0');
INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`) VALUES ('21', 'FSC', 'FSC', 'Fuel', 'Fuel', '3', '0', '0');
INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`) VALUES ('21', 'TAX', 'GST', 'GST', 'GST', '11', '0', '0');
INSERT INTO `carrier_charge_code` (`carrier_id`, `charge_code`, `charge_code_level_2`, `charge_name`, `charge_desc`, `charge_group_id`, `charge`, `cost`, `customer_id`) VALUES ('21', 'FSC', 'FSC', 'Fuel', 'Fuel', '3', '0', '0', '0');


update resourcebundle set msg_content='<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <title>Integrated Carriers SoluShip&trade;</title>
 <style type="text/css">
 <!--
 body {
 font: 100% Verdana, Arial, Helvetica, sans-serif;
 background: #fff;
 margin: 0;
 padding: 0;
 text-align: center;
 color: #000000;
 }
 .oneColElsCtr #container {
 width: 550px;
 background: #FFFFFF;
 margin: 0 auto;
 border: 1px solid #000000;
 text-align: left;
 height:auto;
 }
 .oneColElsCtr #mainContent {
 padding: 10px 10px 10px 10px;
 font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 font-size: 12px;
 }
 .oneColElsCtr #mainContent h1 {
 padding: 5px 0px 0px 0px;
 font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 font-size: 18px;
 color:#990000;
 }
 .oneColElsCtr #mainFooter {
 padding: 5px 0px 0px 10px;
 font-family: "lucida grande",tahoma,verdana,arial,sans-serif;
 font-size: 10px;
 color:#fff;
 background-color:#000000;
 height:35px;
 }
 #mainContent p{
 text-align: left;
 margin-left: 30px;
 }
 ul{
 text-align: left;
 }
 -->
 </style>
 </head>

 <body class="oneColElsCtr">

 <div id="container">
 <img src="http://www.soluship.com/mmr/images/ic-header.jpg" includeContext="true" />
 <div id="mainContent">
 <h1>Integrated Carriers SoluShip&trade;</h1>
 <p>A shipment has been cancelled. Details are as follows.</p>
 <p>This e-mail confirms receipt of your shipment request for %ShipDate.</p>
 <p>
 <b>Ship From:</b><br/>
 Company: %SFROMCOMPANY<br/>
 Address: %SFROMADDRESS1, %SFROMADDRESS2%<br/>
 City: %SFROMCITY<br/>
 Zip/Postal Code: %SFROMZIP<br/>
 Province: %SFROMPROVINCE<br/>
 Country: %SFROMCOUNTRY<br/>
 </p>
 <p>
 <b>Ship To:</b><br/>
 Company: %STOCOMPANY<br/>
 Address: %STOADDRESS1, %STOADDRESS2%<br/>
 City: %STOCITY<br/>
 Zip/Postal Code: %STOZIP<br/>
 Province: %STOPROVINCE<br/>
 Country: %STOCOUNTRY<br/>
 </p>
 <p><b>Carrier and Service:</b> %CARRIERSERVICE</p>
 <p><b>Total Pieces:</b> %TOTALPIECES<br/>
 <b>Total Weight:</b> %TOTALWEIGHT</p>
 <p><b>Tracking Details:</b>%TRACKINGURL</br>
 </p>
 <p><b>Pickup Confirmation Number:</b>%PICKUPCONFIRMATION</br>
 </p>
 <p>Did you know you can create and track shipments and get estimates and transit times online at <a href="https://www.integratedcarriers.com/?page_id=143">www.integratedcarriers.com?</a> Visit our website today and learn how our online services can benefit you.</br>
 </p>
 <p>Thank you for choosing Integrated Carriers.</p>


 <br/>

 <!-- end #mainContent -->
 </div>
 <div id="mainFooter">
 &copy; 2013 Integrated Carriers
 <!-- end #mainFooter -->
 </div>
 <!-- end #container -->
 </div>
 </body>
 </html>' where msg_id='mail.cancel.shipment.notification.body';

ALTER TABLE `business`
ADD COLUMN `send_cancel_purolator_freight_email` VARCHAR(255) NULL AFTER `ltl_email`;

UPDATE `business` SET `send_cancel_purolator_freight_email`='PurolatorFreightCustserv@purolator.com ' WHERE `business_id`='1';
UPDATE `business` SET `send_cancel_purolator_freight_email`='PurolatorFreightCustserv@purolator.com ' WHERE `business_id`='2';

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('cancel.spdPurolatorFreightShipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to save@integratedcarriers.com as well as PurolatorFreightCustserv@purolator.com ', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk` VALUES ('cancel.ltlPurolatorFreightShipment.notification.mail.success', 'Cancelled Shipment Notification has been sent to saveltl@integratedcarriers.com as well as PurolatorFreightCustserv@purolator.com', 'en_CA', 0);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.subject.cancel.pickup.notification', 'Pickup Cancel Notification', 'en_CA', 1);


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('mail.cancel.pickup.notification.body', '<html>\n<head>\n <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n <title>Integrated Carriers SoluShip&trade;</title>\n <style type=\"text/css\">\n <!--\n body {\n font: 100% Verdana, Arial, Helvetica, sans-serif;\n background: #fff;\n margin: 0;\n padding: 0;\n text-align: center;\n color: #000000;\n }\n .oneColElsCtr #container {\n width: 550px;\n background: #FFFFFF;\n margin: 0 auto;\n border: 1px solid #000000;\n text-align: left;\n height:auto;\n }\n .oneColElsCtr #mainContent {\n padding: 10px 10px 10px 10px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 12px;\n }\n .oneColElsCtr #mainContent h1 {\n padding: 5px 0px 0px 0px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 18px;\n color:#990000;\n }\n .oneColElsCtr #mainFooter {\n padding: 5px 0px 0px 10px;\n font-family: \"lucida grande\",tahoma,verdana,arial,sans-serif;\n font-size: 10px;\n color:#fff;\n background-color:#000000;\n height:35px;\n }\n #mainContent p{\n text-align: left;\n margin-left: 30px;\n }\n ul{\n text-align: left;\n }\n -->\n </style>\n </head>\n \n <body class=\"oneColElsCtr\">\n \n <div id=\"container\">\n <img src=\"http://www.soluship.com/mmr/images/ic-header.jpg\" includeContext=\"true\" />\n <div id=\"mainContent\">\n <h1>Integrated Carriers SoluShip&trade;</h1>\n <p>A Pickup has been cancelled. Details are as follows.</p>\n <p>This e-mail confirms receipt of your Pickup Cancellation request for %PickupDate.</p>\n <p>\n <b>Pickup Details:</b><br/>\n Company: %PICKUPABBREVIATION<br/>\n Address: %PICKUPADDRESS1, %PICKUPADDRESS2<br/>\n City: %PICKUPCITY<br/>\n Zip/Postal Code: %PICKUPZIP<br/>\n Province: %PICKUPPROVINCE<br/>\n Country: %PICKUPCOUNTRY<br/>\n </p>\n\n <p><b>Pickup Confirmation Number:</b>%PICKUPCONFIRMATION</br>\n </p>\n <p><b>Pickup Location:</b>%PICKUPLOCATION</br>\n </p>\n <p><b>Pickup Reference:</b>%PICKUPREFERENCE</br>\n </p>\n <p><b>Pickup Carrier Name:</b>%CARRIER</br>\n </p>\n\n <p>Did you know you can create and track shipments and get estimates and transit times online at <a href=\"https://www.integratedcarriers.com/?page_id=143\">www.integratedcarriers.com?</a> Visit our website today and learn how our online services can benefit you.</br> \n </p>\n <p>Thank you for choosing Integrated Carriers.</p> \n \n \n <br/>\n \n <!-- end #mainContent -->\n </div>\n <div id=\"mainFooter\">\n &copy; 2013 Integrated Carriers\n <!-- end #mainFooter -->\n </div>\n <!-- end #container -->\n </div>\n </body>\n </html>', 'en_CA', 1);

INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.pickup', 'Pickup', 'en_CA', 1);

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('createOrderPickup', '271', 1, 'Order Pickup', 1);

INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_admin', '1020', NULL);
INSERT INTO `newsoluship`.`role_action` (`role`, `action_id`, `role_action_id`) VALUES ('busadmin', '1020', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('sales', '1020', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_shipper', '1020', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('customer_base', '1020', NULL);
INSERT INTO `role_action` (`role`, `action_id`, `role_action_id`) VALUES ('solutions_manager', '1020', NULL);


ALTER TABLE `logged_event` 
CHANGE COLUMN `message` `message` VARCHAR(3000) NULL DEFAULT NULL ;
 INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.add.edi.number', 'Edi Number', 'en_CA', 1);


#end od purolator freight query

ALTER TABLE `shipping_order` 
ADD COLUMN `signature_required` INT(10) NULL DEFAULT '0' AFTER `eta`;




CREATE TABLE `document` (
  `doument_id` INT(10) NOT NULL,
  `order_id` INT(10) NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `file_name` VARCHAR(45) NULL,
  `ispublic` BIT(1) NULL DEFAULT b'0',
  `isprivate` BIT(1) NULL DEFAULT b'0',
  `doc_type` VARCHAR(45) NULL,
  PRIMARY KEY (`doument_id`));



ALTER TABLE `document` 
ADD COLUMN `file_path` VARCHAR(145) NULL DEFAULT NULL AFTER `doc_type`,
ADD COLUMN `uploadedBy` VARCHAR(75) NULL DEFAULT NULL AFTER `file_path`,
ADD COLUMN `uploadedDate` TIMESTAMP NULL DEFAULT NULL AFTER `uploadedBy`;


ALTER TABLE `document` 
CHANGE COLUMN `doument_id` `document_id` INT(10) NOT NULL AUTO_INCREMENT ;

===============================


ALTER TABLE `business` 
ADD COLUMN `ship_doc_path` VARCHAR(45) NULL DEFAULT NULL AFTER `default_hold_terms`;


INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('upload.document', '261', 0, 'upload documetnt', 1);


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1112');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1112');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1112');



 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('download.doc', '261', 0, 'download document', 1);


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1114');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1114');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1114');

INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('delete.doc', '261', 0, 'delete document', 1);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1116');
  INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1116');  
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1116');


INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe` ) VALUES ('change.visbility.doc', '261', 1, 'change visibiliy', 1);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1118');
  INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1118');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1118');

 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('update.doc', '261', 0, 'edit document', 1);


INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1120');
  INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1120');
 INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1120');

 INSERT INTO `action` (`action`, `menu_id`, `highlight`, `description`, `reload_safe`) VALUES ('edit.doc', '261', 0, 'edit document', 1);

INSERT INTO `role_action` (`role`, `action_id`) VALUES ('sysadmin', '1122');
  INSERT INTO `role_action` (`role`, `action_id`) VALUES ('busadmin', '1122');
INSERT INTO `role_action` (`role`, `action_id`) VALUES ('customer_admin', '1122');


INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.edit.doc', 'EDIT DOCUMENT', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.add.doc', 'ADD DOCUMENT', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.doc.type', 'Document Type', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.doc.mk.public', 'MAKE PUBLIC', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.doc.mk.private', 'MAKE PRIVATE', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.visibility', 'Visibility', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`, `resourcebundle_id`) VALUES ('label.uploadedBy', 'Uploaded By', 'en_CA', 1, '');
INSERT INTO `resourcebundle` (`msg_id`, `msg_content`, `locale`, `is_fmk`) VALUES ('label.document', 'Documents', 'en_CA', 1);


.......................................End of Live server commit.......................................