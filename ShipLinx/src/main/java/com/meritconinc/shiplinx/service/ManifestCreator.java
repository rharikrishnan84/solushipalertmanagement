package com.meritconinc.shiplinx.service;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.quartz.JobDataMap;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;

import com.meritconinc.shiplinx.utils.EODManifestCreator;

public class ManifestCreator implements Job {
	private CarrierServiceDAO carrierDAO = null;
 
	public ManifestCreator(){
		this.carrierDAO=(CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
				"carrierServiceDAO");
	}
	private static final Logger logger = Logger
			.getLogger(ManifestCreator.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			String	domain = System.getProperty("DOMAIN_NAME");
			logger.info("DOMAIN NAME :" + " " + domain);
			if (domain != null) {
				if (carrierDAO.getSchdulerFlagByDomain(domain)) {
					logger.info("Sucessfully Job Executed");
					EODManifestCreator eodManifestCreator = new EODManifestCreator();
					eodManifestCreator.generateEManifestFile();
				}
			}


	}

}
 