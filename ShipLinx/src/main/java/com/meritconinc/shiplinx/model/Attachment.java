package com.meritconinc.shiplinx.model;

import java.io.File;
import java.io.Serializable;

public class Attachment implements Serializable {
	static final long serialVersionUID = 17092007;
	 
	private File file;
	private String contentType;

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
