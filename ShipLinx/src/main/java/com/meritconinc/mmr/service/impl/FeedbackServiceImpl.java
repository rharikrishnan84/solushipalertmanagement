package com.meritconinc.mmr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.dao.FeedbackDAO;
import com.meritconinc.mmr.model.aboutus.FeedbackVO;
import com.meritconinc.mmr.model.common.KeyValueVO;
import com.meritconinc.mmr.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {

	private static final Logger log = Logger.getLogger(FeedbackServiceImpl.class);

	private FeedbackDAO feedbackDAO;

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	public void insertFeedback(FeedbackVO feedbackVO){
		feedbackDAO.insertFeedback(feedbackVO);
 	}
	
	public List<KeyValueVO> getFeedbackTypes(String locale){
		return feedbackDAO.getFeedbackTypes(locale);
	}


} 
