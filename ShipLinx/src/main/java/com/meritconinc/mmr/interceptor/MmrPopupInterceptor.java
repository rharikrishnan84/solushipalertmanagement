/**
 * 
 */
package com.meritconinc.mmr.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.constants.NavConsts;

/**
 * @author brinzf2
 *
 */
public class MmrPopupInterceptor extends MmrBaseInterceptor {
	private static final long serialVersionUID	= 10092007;
	private static final Logger log = Logger.getLogger(MmrPopupInterceptor.class);

	/**
	 * default constructor
	 *
	 */
	public MmrPopupInterceptor(){
		super();
	} 

	/**
	 * 
	 * @return
	 */
	protected boolean isAjaxCall(){
		return false;
	}


	/**
	 * Override to handle interception 
	 * 
	 * @param invocation
	 * @return
	 * @throws Exception
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		String _logger_method="intercept";
		if (log.isTraceEnabled()) {
			log.trace("> "+_logger_method);
		}

        String result;
        try{
         	String nextPage = checkAccess(invocation);
        	if(nextPage==null){
        		Logger invocationLog = null;
        		if(log.isDebugEnabled()){
            		invocationLog = Logger.getLogger( invocation.getAction().getClass() );
        			invocationLog.debug("> " + invocation.getProxy().getMethod());
        		}
        		result = invocation.invoke();
        		if(log.isDebugEnabled()){
        			invocationLog.debug("< " + invocation.getProxy().getMethod());
        		}
        	}
        	else{
        		result = NavConsts.POPUP_TIME_OUT;
        	}
        }
        catch(Exception e){
        	String excID = Long.toString(System.currentTimeMillis());
        	BaseAction baseAction = (BaseAction)invocation.getAction();
        	baseAction.addFieldError("errorID", "Error ID: "+excID);
        	publishException(invocation, new ExceptionHolder(e));

    		return NavConsts.POPUP_GLOBAL_ERROR;
        }
		if (log.isTraceEnabled()) {
			log.trace("< "+_logger_method);
		}
        return result;
	}
	
}
