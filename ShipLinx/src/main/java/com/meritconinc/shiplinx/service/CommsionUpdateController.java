package com.meritconinc.shiplinx.service;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;

import com.meritconinc.shiplinx.utils.CommissionUpdateActivater;
import org.apache.log4j.Logger;
import org.quartz.Job;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;

//public class CommsionUpdateController {
public class CommsionUpdateController implements Job{
	private CarrierServiceDAO carrierDAO = null;
		private static final Logger logger = Logger.getLogger(CommsionUpdateController.class);
	
		public CommsionUpdateController() {
			this.carrierDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance()
					.findBean("carrierServiceDAO");
		}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		/*CommissionUpdateActivater updater = new CommissionUpdateActivater();
		updater.update();*/
		String domain = System.getProperty("DOMAIN_NAME");
						logger.info("DOMAIN NAME :" + " " + domain);
						if (domain != null) {
							boolean flag = carrierDAO.getSchdulerFlagByDomain(domain);
							synchronized (this) {
								if (flag) {
									CommissionUpdateActivater updater = new CommissionUpdateActivater();
									updater.update();
									logger.info("Successfully Commission job Executed");
								} else {
									logger.info("Commission Job  not executed");
								}
				
							}
						}

	}
}