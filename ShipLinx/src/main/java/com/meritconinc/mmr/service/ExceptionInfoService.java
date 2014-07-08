package com.meritconinc.mmr.service;

import java.util.List;

import com.meritconinc.mmr.model.admin.ExceptionSearchCriteria;
import com.meritconinc.mmr.model.common.ExceptionInfoVO;

public interface ExceptionInfoService {
	
	/**
	 * Insert Exception related info to DB
	 * 
	 * @param exceptionInfoVO
	 * @return
	 */
	public abstract ExceptionInfoVO insertExceptionInfo(ExceptionInfoVO exceptionInfoVO);

	/**
	 * Find a list of exceptions that fit the search criteria
	 * @param criteria
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */ 
	List<ExceptionInfoVO> findExceptions(ExceptionSearchCriteria criteria, int startIndex, int endIndex);
	
	/**
	 * Find the number of exceptions that fit the search criteria
	 * @param criteria
	 * @return
	 */
	int findDataRowsCount(ExceptionSearchCriteria criteria);	
	
}
