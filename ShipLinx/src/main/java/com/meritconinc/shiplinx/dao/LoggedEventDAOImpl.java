package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.LoggedEvent;

public class LoggedEventDAOImpl extends SqlMapClientDaoSupport implements LoggedEventDAO{

	public List<LoggedEvent> getLoggedEventInfo(LoggedEvent loggedEvent, boolean isBusAdmin)
	{
		List<LoggedEvent> loggedList = new ArrayList<LoggedEvent>();
		if(isBusAdmin)
			loggedList = (List) getSqlMapClientTemplate().queryForList("getLoggedEventList", loggedEvent);
		else
			loggedList = (List) getSqlMapClientTemplate().queryForList("getLoggedEventListCustomers", loggedEvent);
		return loggedList;
	} 
	
	public void addLoggedEventInfo(LoggedEvent loggedEvent)
	{
		getSqlMapClientTemplate().insert("createLoggedEvent", loggedEvent);
	}
	
	public void deleteLoggedEventInfo(LoggedEvent loggedEvent)
	{
		getSqlMapClientTemplate().update("deleteLoggedEvent", loggedEvent);
	}
}
