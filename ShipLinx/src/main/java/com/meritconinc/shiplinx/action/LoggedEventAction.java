package com.meritconinc.shiplinx.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.LoggedEventService;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class LoggedEventAction extends BaseAction implements ServletRequestAware, ServletResponseAware{
	
	private static final Logger log = LogManager.getLogger(ShipmentAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private LoggedEventService loggedEventService;
	private List<LoggedEvent> loggedList;
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String addCommentInfo()
	{
		log.debug("-----------------Inside----addCommentInfo()----------------");
		String strComment = request.getParameter("comment");
		boolean boolPvt = Boolean.valueOf(request.getParameter("pvt"));
		long lOrderId = Long.valueOf(request.getParameter("order_id"));
		 
		LoggedEvent loggedEvent = getLoggedEvent();
		loggedEvent.setMessage(strComment);
		loggedEvent.setPrivateMessage(boolPvt);
		loggedEvent.setEntityId(lOrderId);
		loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE);
		loggedEvent.setDeletedMessage(false);
		loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername());
		loggedEvent.setEventDateTime(new Date());
		
		loggedEventService.addLoggedEventInfo(loggedEvent);
		
		if(!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
		{
			loggedEvent.setPrivateMessage(false);
			loggedEvent.setDeletedMessage(false);
			loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,false);
		}
		else
		{
			loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,true);
		}
		//getSession().remove("loggedEvent");
		return SUCCESS;
	}
	
	public String deleteCommentInfo()
	{
		log.debug("-----------------Inside----deleteCommentInfo()----------------");
		long commentId = Long.valueOf(request.getParameter("commentId"));
		long lOrderId = Long.valueOf(request.getParameter("oid"));
		
		LoggedEvent loggedEvent = getLoggedEvent();
		loggedEvent.setId(commentId);
		loggedEvent.setEntityId(lOrderId);
		loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE);
		loggedEvent.setDeletedMessage(true);
		loggedEventService.deleteLoggedEventInfo(loggedEvent);
		
		if(!UserUtil.getMmrUser().getUserRole().equals("busadmin"))
		{
			loggedEvent.setPrivateMessage(false);
			loggedEvent.setDeletedMessage(false);
			loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,false);
		}
		else
		{
			loggedList = loggedEventService.getLoggedEventInfo(loggedEvent,true);
		}
		
		return SUCCESS;
	}

	public LoggedEventService getLoggedEventService() {
		return loggedEventService;
	}

	public void setLoggedEventService(LoggedEventService loggedEventService) {
		this.loggedEventService = loggedEventService;
	}
	
	public LoggedEvent getLoggedEvent() {
		LoggedEvent loggedEvent = (LoggedEvent)getSession().get("loggedEvent");
		if (loggedEvent == null) {
			loggedEvent = new LoggedEvent();
			setLoggedEvent(loggedEvent);
		}
		return loggedEvent;
	}
	public void setLoggedEvent(LoggedEvent loggedEvent) {

		getSession().put("loggedEvent", loggedEvent);
	}

	public List<LoggedEvent> getLoggedList() {
		return loggedList;
	}

	public void setLoggedList(List<LoggedEvent> loggedList) {
		this.loggedList = loggedList;
	}

}
