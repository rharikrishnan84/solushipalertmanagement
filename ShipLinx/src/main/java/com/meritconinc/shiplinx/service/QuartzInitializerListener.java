package com.meritconinc.shiplinx.service;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzInitializerListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(QuartzInitializerListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {


		log.debug("Starting The Application");
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();

			long ctime = System.currentTimeMillis();

			JobDetail job = new JobDetail();
			job.setName("Job");
			job.setJobClass(ManifestCreator.class);

			CronTrigger crt = new CronTrigger();
			
			crt.setName("Trigger"); 
			//0 m H
			
			crt.setCronExpression("0 0/5 * * * ?");
			crt.setTimeZone(TimeZone.getTimeZone("EST"));
			//crt.setCronExpression("0 11 15 * * ?");
			log.debug("TIME "+crt.getTimeZone().getDisplayName());
						JobDetail job3 = new JobDetail();
						job3.setName("Job3");
						job3.setJobClass(ExchangeRateCurrencyUpdater.class);
						CronTrigger crt3 = new CronTrigger();
						crt3.setName("Trigger3");
						//crt3.setCronExpression("05 * * * * ?");
						crt3.setCronExpression("0 00 05 * * ?");
						crt3.setTimeZone(TimeZone.getTimeZone("EST"));
			
			scheduler.start();
			scheduler.scheduleJob(job, crt);
			scheduler.scheduleJob(job3, crt3);

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}