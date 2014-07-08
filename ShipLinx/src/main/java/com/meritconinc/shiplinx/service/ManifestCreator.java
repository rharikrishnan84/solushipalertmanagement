package com.meritconinc.shiplinx.service;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.meritconinc.shiplinx.utils.EODManifestCreator;

public class ManifestCreator implements Job{
	private static final Logger logger = Logger.getLogger(ManifestCreator.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.error("Sucessfully Job Executed");
		logger.info("Sucessfully Job Executed");
		EODManifestCreator eodManifestCreator=new EODManifestCreator();
		eodManifestCreator.generateEManifestFile();
	}

}
 