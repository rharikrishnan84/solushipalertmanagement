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
import com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice.EShipPlusAPI;

import com.meritconinc.shiplinx.carrier.midland.MidlandAPI;

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
			
			crt.setCronExpression("0 55 19 * * ?");
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
						
						//e ship plus
						JobDetail job1 = new JobDetail();
									job1.setName("Job4");
									job1.setJobClass(EShipPlusAPI.class);
									CronTrigger crt1 = new CronTrigger();
						
									crt1.setName("Trigger1");
									// 0 m H
						
									crt1.setCronExpression("0 30 * * * ?");
									crt1.setTimeZone(TimeZone.getTimeZone("EST"));
									 scheduler.start();
								     scheduler.scheduleJob(job1, crt1);
								     
								     //eship plus end
									
						JobDetail commisionjob = new JobDetail();
						commisionjob.setName("Commision Procedure Job");
						commisionjob.setJobClass(CommsionUpdateController.class);
						CronTrigger commissionCrt = new CronTrigger();
						commissionCrt.setName("Commission Trigger");
						commissionCrt.setCronExpression("0	19	*	*	6");
						commissionCrt.setTimeZone(TimeZone.getTimeZone("EST"));
						scheduler.scheduleJob(commisionjob, commissionCrt);
			
			scheduler.start();
			scheduler.scheduleJob(job, crt);
			scheduler.scheduleJob(job3, crt3);
			//scheduler.scheduleJob(job1, crt1);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}