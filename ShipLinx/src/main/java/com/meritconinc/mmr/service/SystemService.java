package com.meritconinc.mmr.service;

import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletContext;

import com.meritconinc.mmr.model.system.DatabaseInfoVO;

import com.meritconinc.mmr.model.system.LibraryInfoVO;

public interface SystemService {

	String getServerIP();
	String getServerName();

	/**
	 * Returns the name representing the running Java virtual machine. The returned name string 
	 * can be any arbitrary string and a Java virtual machine implementation can choose to embed 
	 * platform-specific useful information in the returned name string. Each running virtual machine could have a different name.
	 * 
	 * @return
	 */
	String getVmName();
	
	/**
	 * Returns the start time of the Java virtual machine
	 * @return
	 */
	Date getVmStartTime();
	
	/**
	 * Returns the current number of live threads including both daemon and non-daemon threads.
	 * @return
	 */
	int getThreadCount(); 
	
	/**
	 * Returns the peak live thread count since the Java virtual machine started or peak was reset.
	 * @return
	 */
	int getPeakThreadCount();
	
	/**
	 * Returns the total number of threads created and also started since the Java virtual machine started.
	 * @return
	 */
	long getTotalStartedThreadCount();
	
	/**
	 * Returns the current number of live daemon threads.
	 * @return
	 */
	int getDaemonThreadCount(); 	
	
	/**
	 * Returns the approximate number of objects for which finalization is pending.
	 * @return
	 */
	int getObjectPendingFinalizationCount();

	/**
	 * Returns the current memory usage of the heap that is used for object allocation. 
	 * The heap consists of one or more memory pools. The used and committed size of 
	 * the returned memory usage is the sum of those values of all heap memory pools 
	 * whereas the init and max size of the returned memory usage represents the 
	 * setting of the heap memory which may not be the sum of those of all heap memory pools.
	 * 
	 * The amount of used memory in the returned memory usage is the amount of memory occupied 
	 * by both live objects and garbage objects that have not been collected, if any.  
	 * @return
	 * 
	 */
	MemoryUsage getHeapMemoryUsage();
	
	/**
	 * Returns the current memory usage of non-heap memory that is used by the Java virtual 
	 * machine. The non-heap memory consists of one or more memory pools. The used and 
	 * committed size of the returned memory usage is the sum of those values of all 
	 * non-heap memory pools whereas the init and max size of the returned memory usage 
	 * represents the setting of the non-heap memory which may not be the sum of those 
	 * of all non-heap memory pools.
	 * 
	 * @return
	 */
	MemoryUsage getNonHeapMemoryUsage();

	DatabaseInfoVO getDatabaseInfo();
	
	void clearCache();
	
	void setServletContext(ServletContext ctx);
	
	List<LibraryInfoVO> getLibraryInfos();
}
