CREATE DEFINER=`soluship`@`61.12.78.170` PROCEDURE `insert_commission_amount`()
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
	
	OPEN cur_invoices;
	GET_INVOICES: LOOP
		FETCH cur_invoices INTO var_customer_id, var_customer_name, var_invoice_id, var_invoice_num, 
							   var_invoice_amount, var_invoice_cost, var_payment_status,
							   var_invoice_date, var_chb_total, var_spd_total, var_ltl_total,
							   var_fwd_total, var_fpa_total, var_inv_currency;
        IF no_record_found = 1 THEN
			
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
					
                    SET no_record_found = 0;
					LEAVE GET_USERS;
				END IF;
				set @exchangeRate = 1;
				IF (STRCMP(var_usr_currency, var_inv_currency) <> 0) THEN
					
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
										AND  	ch.status !=40
										AND  	ch.commissionable !=0
										AND  	s.service_id=so.service_id
										AND  	cs1.id = ch.charge_currency
										AND  	cs2.id = ch.cost_currency) as chs) as res
						WHERE res.is_tax = 0
						GROUP BY email_type;
                    OPEN cur_charges;
					GET_CHARGES: LOOP
						FETCH cur_charges INTO var_profit, var_email_type;
						IF no_record_found = 1 THEN
							
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
END


----------------------------------------------------------------------

----------------------------------------------------------------------

CREATE DEFINER=`soluship`@`61.12.78.170` PROCEDURE `sp_for_old_records`(
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
	
	---------------------------------------------------------------------------------
	
	---------------------------------------------------------------------------------------
	
	
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
		
		
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
		
		
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;



---------------------------------------------------------------------------------
	
	---------------------------------------------------------------------------------------
	
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
					
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
			
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;


---------------------------------------------------------------------------------
	
	---------------------------------------------------------------------------------------
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
		
		
	 ELSEIF charge_type=0 THEN
		UPDATE shipping_order s SET s.quote_cost=@quote_cost,s.quote_total = @quote_charge WHERE s.order_id=@order_id;
				
    END IF; 	
 CLOSE cur_charges;
END;$$
Delimiter ;




END

