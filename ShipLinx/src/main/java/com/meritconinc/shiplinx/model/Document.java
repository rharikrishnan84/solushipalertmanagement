package com.meritconinc.shiplinx.model;

import java.io.File;
import java.sql.Timestamp;

public class Document {

	private long documentId;
	private long orderId;
	private String name;
	private String description;
	private String fileName;
	private String docType;
	private boolean publicEnable;
	private boolean privateEnable;
    private File uploadDoc;
    private String uploadDocFileName;
    private String uploadDocContentType;
    private String uploadedBy;
    private Timestamp uploadedTime;
    private String visibilty;
	private String filePath;
	
	public long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public boolean isPublicEnable() {
		return publicEnable;
	}
	public void setPublicEnable(boolean publicEnable) {
		this.publicEnable = publicEnable;
	}
	public boolean isPrivateEnable() {
		return privateEnable;
	}
	public void setPrivateEnable(boolean privateEnable) {
		this.privateEnable = privateEnable;
	}
	 
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public File getUploadDoc() {
		return uploadDoc;
	}
	public void setUploadDoc(File uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	public String getUploadDocFileName() {
		return uploadDocFileName;
	}
	public void setUploadDocFileName(String uploadDocFileName) {
		this.uploadDocFileName = uploadDocFileName;
	}
	public String getUploadDocContentType() {
		return uploadDocContentType;
	}
	public void setUploadDocContentType(String uploadDocContentType) {
		this.uploadDocContentType = uploadDocContentType;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Timestamp getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Timestamp uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getVisibilty() {
		return visibilty;
	}
	public void setVisibilty(String visibilty) {
		this.visibilty = visibilty;
	}
	 
	
}
