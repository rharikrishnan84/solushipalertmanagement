package com.meritconinc.shiplinx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.LoggedEventDAO;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class LoggedEventServiceImpl implements LoggedEventService
{
	
	private LoggedEventDAO loggedEventDAO;
	
	public List<LoggedEvent> getLoggedEventInfo(LoggedEvent loggedEvent, boolean isBusAdmin)
	{
		List<LoggedEvent> loggedList = new ArrayList<LoggedEvent>();
		try 
		{
			loggedList = loggedEventDAO.getLoggedEventInfo(loggedEvent,isBusAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loggedList;
	}
	 
	public void addLoggedEventInfo(LoggedEvent loggedEvent)
	{
		loggedEventDAO.addLoggedEventInfo(loggedEvent);
	}
	
	public void deleteLoggedEventInfo(LoggedEvent loggedEvent)
	{
		loggedEventDAO.deleteLoggedEventInfo(loggedEvent);
	}

	public LoggedEventDAO getLoggedEventDAO() {
		return loggedEventDAO;
	}

	public void setLoggedEventDAO(LoggedEventDAO loggedEventDAO) {
		this.loggedEventDAO = loggedEventDAO;
	}

}
