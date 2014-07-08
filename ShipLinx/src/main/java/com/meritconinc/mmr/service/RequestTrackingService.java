package com.meritconinc.mmr.service;

import java.util.List;

import com.meritconinc.mmr.model.common.RequestDetailsVO;

public interface RequestTrackingService {
	
	public abstract void insertRequestTracking(RequestDetailsVO requestdetailsVo);

	public abstract List getRequestParamMetadata();
 
} 
