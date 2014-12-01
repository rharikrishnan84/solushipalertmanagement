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
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AFN', '؋', 'Afghanistan', 'Afghani');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ARS', '$', 'Argentina', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AWG', 'ƒ', 'Aruba', 'Guilder');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AUD', '$', 'Australia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('AZN', 'ман', 'Azerbaijan', 'Manat');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BSD', '$', 'Bahamas', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BBD', '$', 'Barbados', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BYR', 'p.', 'Belarus', 'Ruble');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BZD', 'BZ$', 'Belize', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BMD', '$', 'Bermuda', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BOB', '$b', 'Bolivia', 'Boliviano');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BAM', 'KM', 'Bosnia and Herzegovina', 'Convertible Marka');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BWP', 'P', 'Botswana', 'Pula');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BGN', 'лв', 'Bulgaria', 'Lev');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BRL', 'R$', 'Brazil', 'Real');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('BND', '$', 'Brunei', 'Darussalam Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KHR', '៛', 'Cambodia', 'Riel');

INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KYD', '$', 'Cayman', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CLP', '$', 'Chile', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CNY', '¥', 'China', 'Yuan Renminbi');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('COP', '$', 'Colombia', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CRC', '₡', 'Costa Rica', 'Colon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HRK', 'kn', 'Croatia', 'Kuna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CUP', '₱', 'Cuba', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CZK', 'Kč', 'Czech Republic', 'Koruna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('DKK', 'kr', 'Denmark', 'Krone');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('DOP', 'RD$', 'Dominican Republic', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('XCD', '$', 'East Caribbean', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EGP', '£', 'Egypt', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SVC', '$', 'El Salvador', 'Colon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EEK', 'kr', 'Estonia', 'Kroon');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('EUR', '€', 'Euro Member', 'Euro');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('FKP', '£', 'Falkland Islands', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('FJD', '$', 'Fiji', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GHC', '¢', 'Ghana', 'Cedis');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GIP', '£', 'Gibraltar', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GTQ', 'Q', 'Guatemala', 'Quetzal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GGP', '£', 'Guernsey', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GYD', '$', 'Guyana', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HNL', 'L', 'Honduras', 'Lempira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HKD', '$', 'Hong Kong', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('HUF', 'Ft', 'Hungary', 'Forint');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ISK', 'kr', 'Iceland', 'Krona');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('INR', 'र', 'India', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IDR', 'Rp', 'Indonesia', 'Rupiah');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IRR', '﷼', 'Iran', 'Rial');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('IMP', '£', 'Isle of Man', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ILS', '₪', 'Israel', 'Shekel');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JMD', 'J$', 'Jamaica', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JPY', '¥', 'Japan', 'Yen');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('JEP', '£', 'Jersey', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KZT', 'лв', 'Kazakhstan', 'Tenge');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KPW', '₩', 'Korea (North)', 'Won');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KRW', '₩', 'Korea (South)', 'Won');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('KGS', 'лв', 'Kyrgyzstan', 'Som');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LAK', '₭', 'Laos', 'Kip');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LVL', 'Ls', 'Latvia', 'Lat');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LBP', '£', 'Lebanon', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LRD', '$', 'Liberia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LTL', 'Lt', 'Lithuania', 'Litas');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MKD', 'ден', 'Macedonia', 'Denar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MYR', 'RM', 'Malaysia', 'Ringgit');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MUR', '₨', 'Mauritius', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MXN', '$', 'Mexico', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MNT', '₮', 'Mongolia', 'Tughrik');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('MZN', 'MT', 'Mozambique', 'Metical');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NAD', '$', 'Namibia', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NPR', '₨', 'Nepal', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ANG', 'ƒ', 'Netherlands', 'Antilles Guilder');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NZD', '$', 'New Zealand', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NIO', 'C$', 'Nicaragua', 'Cordoba');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NGN', '₦', 'Nigeria', 'Naira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('NOK', 'kr', 'Norway', 'Krone');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('OMR', '﷼', 'Oman', 'Rial');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PKR', '₨', 'Pakistan', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PAB', 'B/.', 'Panama', 'Balboa');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PYG', 'Gs', 'Paraguay', 'Guarani');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PEN', 'S/.', 'Peru', 'Nuevo Sol');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PHP', '₱', 'Philippines', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('PLN', 'zł', 'Poland', 'Zloty');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('QAR', '﷼', 'Qatar', 'Riyal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RON', 'lei', 'Romania', 'New Leu');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RUB', 'руб', 'Russia', 'Ruble');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SHP', '£', 'Saint Helena', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SAR', '﷼', 'Saudi Arabia', 'Riyal');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('RSD', 'Дин.', 'Serbia', 'Dinar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SCR', '₨', 'Seychelles', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SGD', '$', 'Singapore', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SBD', '$', 'Solomon Islands', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SOS', 'S', 'Somalia', 'Shilling');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('ZAR', 'S', 'South Africa', 'Rand');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('LKR', '₨', 'Sri Lanka', 'Rupee');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SEK', 'kr', 'Sweden', 'Krona');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('CHF', 'CHF', 'Switzerland', 'Franc');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SRD', '$', 'Suriname', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('SYP', '£', 'Syria', 'Pound');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TWD', 'NT$', 'Taiwan', 'New Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('THB', '฿', 'Thailand', 'Baht');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TTD', 'TT$', 'Trinidad and Tobago', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TRL', '₤', 'Turkey', 'Lira');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('TVD', '$', 'Tuvalu', 'Dollar');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UAH', '₴', 'Ukraine', 'Hryvna');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('GBP', '£', 'United Kingdom', 'Pound');

INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UYU', '$U', 'Uruguay', 'Peso');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('UZS', 'лв', 'Uzbekistan', 'Som');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('VEF', 'Bs', 'Venezuela', 'Bolivar Fuerte');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('VND', '₫', 'Viet Nam', 'Dong');
INSERT INTO `currency_symbol` (`currency_code`, `currency_symbol`, `country_name`, `currency_name`) VALUES ('YER', '﷼', 'Yemen', 'Rial');
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



