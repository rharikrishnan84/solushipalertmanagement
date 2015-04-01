package com.meritconinc.shiplinx.model;

import java.util.ArrayList;
import java.util.List;

public class UserBusiness {
	
	private Long parentId;
	private Long partnerId;
	private Long nationId;
	private Long branchId;
	private Long userBusId;
   private BusinessFilter businessFilter;
   private List<BusinessFilter> businessFilterList=new ArrayList<BusinessFilter>();
	private String username;
 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BusinessFilter getBusinessFilter() {
		return businessFilter;
	}
	public void setBusinessFilter(BusinessFilter businessFilter) {
		this.businessFilter = businessFilter;
	}
	public List<BusinessFilter> getBusinessFilterList() {
		return businessFilterList;
	}
	public void setBusinessFilterList(List<BusinessFilter> businessFilterList) {
		this.businessFilterList = businessFilterList;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	public Long getNationId() {
		return nationId;
	}
	public void setNationId(Long nationId) {
		this.nationId = nationId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getUserBusId() {
		return userBusId;
	}
	public void setUserBusId(Long userBusId) {
		this.userBusId = userBusId;
	}
	 

}
