package com.meritconinc.mmr.service.impl;

import java.util.List;

import com.meritconinc.mmr.dao.ExceptionInfoDAO;
import com.meritconinc.mmr.model.admin.ExceptionSearchCriteria;
import com.meritconinc.mmr.model.common.ExceptionInfoVO;
import com.meritconinc.mmr.service.ExceptionInfoService;

public class ExceptionInfoServiceImpl implements ExceptionInfoService {

	private ExceptionInfoDAO exceptionInfoDAO;

	public void setExceptionInfoDAO(ExceptionInfoDAO exceptionInfoDAO) {
		this.exceptionInfoDAO = exceptionInfoDAO;
	}

	public ExceptionInfoVO insertExceptionInfo(ExceptionInfoVO exceptionInfoVO) {
		return exceptionInfoDAO.insertExceptionInfo(exceptionInfoVO);
	}
	
	public int findDataRowsCount(ExceptionSearchCriteria criteria) {
		return exceptionInfoDAO.findDataRowsCount(criteria);
	}

	public List<ExceptionInfoVO> findExceptions(
			ExceptionSearchCriteria criteria, int startIndex, int endIndex)	{
		return exceptionInfoDAO.findExceptions(criteria, startIndex, endIndex);
	} 
}
