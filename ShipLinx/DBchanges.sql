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

############################################################

		Need To Go Live
#############################################################




