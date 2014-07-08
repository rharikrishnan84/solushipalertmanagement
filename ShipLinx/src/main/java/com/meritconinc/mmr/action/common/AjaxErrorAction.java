/**
 * 
 */
package com.meritconinc.mmr.action.common;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.utilities.StringUtil;


/**
 * @author brinzf2
 *
 */
public class AjaxErrorAction extends BaseAction { 
	private static final long serialVersionUID	= 18052007;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		if (!StringUtil.isEmpty(getErrorId())) {
			addFieldError("errorID", "Error ID: "+getErrorId());
		}
		return SUCCESS;
	}
}
