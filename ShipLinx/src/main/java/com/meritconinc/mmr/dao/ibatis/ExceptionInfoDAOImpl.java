package com.meritconinc.mmr.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.ExceptionInfoDAO;
import com.meritconinc.mmr.model.admin.ExceptionSearchCriteria;
import com.meritconinc.mmr.model.common.ExceptionInfoVO;

public class ExceptionInfoDAOImpl extends SqlMapClientDaoSupport implements ExceptionInfoDAO {

  /**
   * Insert Exception related info to DB
   * 
   * @param exceptionInfoVO
   * @return
   */
  public ExceptionInfoVO insertExceptionInfo(ExceptionInfoVO exceptionInfoVO) {
    if (exceptionInfoVO == null)
      return null;
    Integer key = (Integer) getSqlMapClientTemplate().insert("insertExeceptionDetails",
        exceptionInfoVO);
    if (key == null)
      return null;
    exceptionInfoVO.setId(key.intValue());
    return exceptionInfoVO;
  }

  /**
   * Find a list of exceptions that fit the search criteria
   * 
   * @param criteria
   * @param startIndex
   * @param endIndex
   * @return
   */
  public int findDataRowsCount(ExceptionSearchCriteria criteria) {
    return (Integer) getSqlMapClientTemplate().queryForObject("findExceptionsCount", criteria);
  }

  /**
   * Find the number of exceptions that fit the search criteria
   * 
   * @param criteria
   * @return
   */
  public List<ExceptionInfoVO> findExceptions(ExceptionSearchCriteria criteria, int startIndex,
      int endIndex) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", criteria.getId());
    params.put("exceptionId", criteria.getExceptionId());
    params.put("details", criteria.getDetails());
    params.put("updateDate", criteria.getUpdateDate());
    params.put("username", criteria.getUsername());
    params.put("orderBy", criteria.getOrderBy());
    params.put("sortOrder", criteria.getOrder());
    params.put("top", endIndex);
    int skipResults = startIndex - 1;
    int maxResults = endIndex - startIndex + 1;
    return getSqlMapClientTemplate()
        .queryForList("findExceptions", params, skipResults, maxResults);
  }

}