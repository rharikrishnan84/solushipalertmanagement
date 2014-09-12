/**
 * 
 */
package com.meritconinc.mmr.action.common;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;


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
	private UserDAO userDAO;
	private ArrayList<User> users=new ArrayList<User>();
	public String execute() throws Exception {
		User user = UserUtil.getMmrUser();
					
						Collection<String> roles=user.getRoles();
						String role=roles.toString().replace("[","").replace("]","").trim();
						
						userDAO=(UserDAO) MmrBeanLocator.getInstance().findBean("userDAO");
						setUsers((ArrayList) userDAO.getUserEmailById(user.getCustomerId(),role ,user.getUsername()));
		String msg = "GenericAction.execute - ....";
		log.debug(msg);
		return SUCCESS;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
