package com.meritconinc.shiplinx.service;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;

import com.meritconinc.shiplinx.utils.CommissionUpdateActivater;

public class CommsionUpdateController {
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		CommissionUpdateActivater updater = new CommissionUpdateActivater();
		updater.update();

	}
}