package com.meritconinc.shiplinx.service;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.meritconinc.shiplinx.utils.EODManifestCreator;
import com.meritconinc.shiplinx.utils.ExchangeRateCurrencyUpdateActivater;
public class ExchangeRateCurrencyUpdater implements Job {
	private static final Logger logger = Logger.getLogger(ManifestCreator.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.error("Sucessfully Job Executed");
		logger.info("Sucessfully Job Executed");
		//EODManifestCreator eodManifestCreator=new EODManifestCreator();
		ExchangeRateCurrencyUpdateActivater activater=new ExchangeRateCurrencyUpdateActivater();
		activater.updateExchangeRateTable();
		
	}

}
