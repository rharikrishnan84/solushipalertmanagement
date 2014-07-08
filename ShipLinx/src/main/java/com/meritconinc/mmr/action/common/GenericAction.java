/**
 * 
 */
package com.meritconinc.mmr.action.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.action.BaseAction;


/**
 * @author brinzf2
 *
 */
public class GenericAction extends BaseAction {
	private static final Logger log = LogManager.getLogger(GenericAction.class);
	private static final long serialVersionUID	= 18052007;
 
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		String msg = "GenericAction.execute - ....";
		log.debug(msg);
		return SUCCESS;
	}
}
