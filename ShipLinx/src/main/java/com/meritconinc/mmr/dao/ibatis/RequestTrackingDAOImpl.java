package com.meritconinc.mmr.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.PropertyDAO;
import com.meritconinc.mmr.dao.RequestTrackingDAO;
import com.meritconinc.mmr.model.common.RequestDetailsVO;
import com.meritconinc.mmr.utilities.MmrBeanLocator;

public class RequestTrackingDAOImpl extends SqlMapClientDaoSupport implements RequestTrackingDAO {

  private static final long serialVersionUID = 1L;
  private PropertyDAO propertyDAO;

  public RequestTrackingDAOImpl() {
  }

  /**
   * insertRequestTracking(), inserts request tracking details and request parameters in
   * fmk_navigation_log and fmk_navigation_param_log table respectively.
   * 
   * @param requestdetailsVo
   */
  public void insertRequestTracking(RequestDetailsVO reqDetailsVO) {

    // if (true)
    // return;
    propertyDAO = (PropertyDAO) MmrBeanLocator.getInstance().findBean("propertyDAO");
    String navigationLog = propertyDAO.readProperty(Constants.SYSTEM_SCOPE, "NAVIGATION_LOG");
    if (navigationLog != null) {
      boolean checkTermsOfUse = Boolean.valueOf(navigationLog);
      if (checkTermsOfUse) {
        if (reqDetailsVO.getUri() != null && reqDetailsVO.getUri().length() > 1000)
          reqDetailsVO.setUri(reqDetailsVO.getUri().substring(0, 900));
        Object object = null;
        if (reqDetailsVO != null)
          object = getSqlMapClientTemplate().insert("insertRequestTracking", reqDetailsVO);
      }
    }
  }

  /**
   * getRequestParamMetadata(), get request parameter metadata list from the database. Only
   * parameter and action available in this list would be persisted.
   * 
   * @return List
   */
  public List getRequestParamMetadata() {
    return getSqlMapClientTemplate().queryForList("getRequestParamMetadata");
  }
}
