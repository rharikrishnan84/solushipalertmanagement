package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.util.Date;

public class PackageType implements Serializable {
	private Long packageTypeId;
	private String name;
	private String type;
	private String description;
	
	public Long getPackageTypeId() {
		return packageTypeId;
	}
	public void setPackageTypeId(Long packagetypeId) {
		this.packageTypeId = packagetypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
