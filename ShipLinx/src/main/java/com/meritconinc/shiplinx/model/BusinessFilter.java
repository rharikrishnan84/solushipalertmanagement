package com.meritconinc.shiplinx.model;

import java.util.ArrayList;
import java.util.List;

public class BusinessFilter {
    private List<Business> ParentBusList=new ArrayList<Business>();
	private List<Business> partnerBusList=new ArrayList<Business>(); 
	private List<Business> nationBusList=new ArrayList<Business>() ; 
	private List<Business> branchBusList=new ArrayList<Business>();
	private Business parentBus;
	private Business partnerBus;
	private Business nationBus;
	private Business branchBus;
	private long id;
	public List<Business> getBranchBusList() {
		return branchBusList;
	}
	public void setBranchBusList(List<Business> branchBusList) {
		this.branchBusList = branchBusList;
	}
	public List<Business> getNationBusList() {
		return nationBusList;
	}
	public void setNationBusList(List<Business> nationBusList) {
		this.nationBusList = nationBusList;
	}
	public List<Business> getPartnerBusList() {
		return partnerBusList;
	}
	public void setPartnerBusList(List<Business> partnerBusList) {
		this.partnerBusList = partnerBusList;
	}
	public List<Business> getParentBusList() {
		return ParentBusList;
	}
	public void setParentBusList(List<Business> parentBusList) {
		ParentBusList = parentBusList;
	}
	public Business getParentBus() {
		return parentBus;
	}
	public void setParentBus(Business parentBus) {
		this.parentBus = parentBus;
	}
	public Business getPartnerBus() {
		return partnerBus;
	}
	public void setPartnerBus(Business partnerBus) {
		this.partnerBus = partnerBus;
	}
	public Business getNationBus() {
		return nationBus;
	}
	public void setNationBus(Business nationBus) {
		this.nationBus = nationBus;
	}
	public Business getBranchBus() {
		return branchBus;
	}
	public void setBranchBus(Business branchBus) {
		this.branchBus = branchBus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
 
 
	
}
