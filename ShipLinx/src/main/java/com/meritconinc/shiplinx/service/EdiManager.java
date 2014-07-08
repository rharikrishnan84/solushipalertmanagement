package com.meritconinc.shiplinx.service;

import java.io.InputStream;
import java.util.List;

import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;

public interface EdiManager {
	public List<EdiInfo> getEdiInfoList(long busId);
	public EdiInfo getEdiInfo(Long carrierId, long busId);
	public List<EdiItem> getEdiItemList(EdiItem ediItem, long busId);
	public List<EdiItem> uploadEdiFiles(EdiInfo ediInfo, long busId);
	public EdiItem uploadEdiFile(EdiInfo ediInfo, String uploadFileName, InputStream uploadStream, boolean startNow, long busId);
	public boolean releaseEdiInvoice(long ediItemId, String invoiceNumber);
	public List<EdiItem> searchByInvoiceNumber(EdiItem item);
	public List<EdiItem> searchByFileName(EdiItem item);
	public List<EdiItem> findEdiItems(EdiItem item);
	public int releaseEdiFile(String ediFileName) throws Exception;
}
 