package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;

public interface EdiDAO {
	public List<EdiInfo> getEdiInfoList(long busId);
	public EdiInfo getEdiInfo(Long carrierId, long busId);
	public List<EdiItem> getEdiItemList(EdiItem item, String ediFileName, long busId);
	public long addEdiItem(EdiItem item);
	public void updateEdiItem(EdiItem item);
	public void updateEdiItemStatus(long ediItemId, int status);
	public List<EdiItem> getMatchingEdiItemList(EdiItem item);
	public List<EdiItem> findEdiItems(EdiItem item);
} 
