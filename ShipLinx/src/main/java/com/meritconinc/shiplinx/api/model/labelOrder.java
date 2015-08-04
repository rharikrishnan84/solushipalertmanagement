package com.meritconinc.shiplinx.api.model;

import java.util.ArrayList;
import java.util.List;

import com.meritconinc.mmr.model.security.User;

public class labelOrder {
	private List<String>orderId=new ArrayList<String>();
	private String scopies;
	private String ccopies;
	private User user;


	
	public List<String> getOrderId() {
		return orderId;
	}

	public void setOrderId(List<String> orderId) {
		this.orderId = orderId;
	}

	public String getScopies() {
		return scopies;
	}

	public void setScopies(String scopies) {
		this.scopies = scopies;
	}

	public String getCcopies() {
		return ccopies;
	}

	public void setCcopies(String ccopies) {
		this.ccopies = ccopies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
