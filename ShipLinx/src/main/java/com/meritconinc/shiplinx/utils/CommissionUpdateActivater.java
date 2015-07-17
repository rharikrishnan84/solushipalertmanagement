package com.meritconinc.shiplinx.utils;

import org.quartz.JobExecutionException;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.CustomerDAO;

import org.apache.log4j.Logger;

public class CommissionUpdateActivater {
	CustomerDAO customermanagerDAO = (CustomerDAO) MmrBeanLocator.getInstance()
			.findBean("customermanagerDAO");

	private static final Logger log = Logger
			.getLogger(CommissionUpdateActivater.class);

	public void update() throws JobExecutionException {
		try {
			log.info(" Commision update stored procedure start at ==== "
					+ System.currentTimeMillis());
			customermanagerDAO.callCommissionStoredProcedure();
			log.info("commision update stored procedure completed at ===="
					+ System.currentTimeMillis());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}