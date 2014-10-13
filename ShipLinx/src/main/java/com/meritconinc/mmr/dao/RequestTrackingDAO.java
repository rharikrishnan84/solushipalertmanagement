package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.common.RequestDetailsVO;

public interface RequestTrackingDAO {
	static final long serialVersionUID = 1L;
  /**
   * insertRequestTracking(), inserts request tracking details and request parameters in
   * fmk_navigation_log and fmk_navigation_param_log table respectively.
   * 
   * @param requestdetailsVo
   */
  public abstract void insertRequestTracking(RequestDetailsVO requestdetailsVo);

  /**
   * getRequestParamMetadata(), get request parameter metadata list from the database. Only
   * parameter and action available in this list would be persisted.
   * 
   * @return List @
   */
  public abstract List getRequestParamMetadata();
}
