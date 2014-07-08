/**
 * 
 */
package com.meritconinc.mmr.utilities.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Some of the factory/managers (e.g. the ObjectFactory) need access to 
 * the {@link org.apache.struts2.ServletActionContext} object when initializing.
 * This {@link javax.servlet.ServletContextListener} keeps a reference to the 
 * {@link javax.servlet.ServletContext} and exposes it through a <code>public static</code>
 * method.
 * 
 */
public class ServletContextHolderListener implements ServletContextListener { 
	
	      private static ServletContext context = null;
	
	      /**
	       * @return The current servlet context
	       */
	      public static ServletContext getServletContext() {
	          return context;
	      }
	
	      /**
	       * Stores the reference to the {@link ServletContext}.
	       * 
	       * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	       */
	      public void contextInitialized(ServletContextEvent event) {
	          context = event.getServletContext();
	
	      }
	
	      /**
	       * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	       */
	      public void contextDestroyed(ServletContextEvent event) {
	          context = null;
	      }

}
