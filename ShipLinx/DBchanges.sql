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
--------------------------------------END of LIVE SERVER COMMIT---------------------------------------

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

