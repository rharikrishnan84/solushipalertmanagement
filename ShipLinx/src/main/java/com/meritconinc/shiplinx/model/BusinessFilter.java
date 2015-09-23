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
	//division level filter
		    private boolean spdEnabled;
		    private boolean ltlEnabled;
		    private boolean chbEnabled;
		   private boolean fwdEnabled;
		  private boolean fpaEnabled;
			
		  private boolean allEmailType;
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
	public boolean isSpdEnabled() {
		return spdEnabled;
	}
	public void setSpdEnabled(boolean spdEnabled) {
		this.spdEnabled = spdEnabled;
	}
	public boolean isLtlEnabled() {
		return ltlEnabled;
	}
	public void setLtlEnabled(boolean ltlEnabled) {
		this.ltlEnabled = ltlEnabled;
	}
	public boolean isChbEnabled() {
		return chbEnabled;
	}
	public void setChbEnabled(boolean chbEnabled) {
		this.chbEnabled = chbEnabled;
	}
	public boolean isFwdEnabled() {
		return fwdEnabled;
	}
	public void setFwdEnabled(boolean fwdEnabled) {
		this.fwdEnabled = fwdEnabled;
	}
	public boolean isFpaEnabled() {
		return fpaEnabled;
	}
	public void setFpaEnabled(boolean fpaEnabled) {
		this.fpaEnabled = fpaEnabled;
	}
	public boolean isAllEmailType() {
		return allEmailType;
	}
	public void setAllEmailType(boolean allEmailType) {
		this.allEmailType = allEmailType;
	}
 
 
	
}
