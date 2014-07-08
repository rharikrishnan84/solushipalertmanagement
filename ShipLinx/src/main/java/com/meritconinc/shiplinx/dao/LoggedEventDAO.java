package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.LoggedEvent;

public interface LoggedEventDAO {
	
	public List<LoggedEvent> getLoggedEventInfo(LoggedEvent loggedEvent, boolean isBusAdmin);
	
	public void addLoggedEventInfo(LoggedEvent loggedEvent);
	
	public void deleteLoggedEventInfo(LoggedEvent loggedEvent);
} 
