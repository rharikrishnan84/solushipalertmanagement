package com.meritconinc.mmr.model.admin;

import java.io.Serializable;

import com.meritconinc.mmr.model.common.ExceptionInfoVO;
import com.meritconinc.mmr.utilities.StringUtil;

public class ExceptionSearchCriteria extends ExceptionInfoVO implements Serializable {
	private static final long serialVersionUID = 1816223036613976178L;
	
	private String sortBy = "id";
	private String order = "desc";

	public String getSortBy() {
		if (StringUtil.isEmpty(sortBy)) {
			sortBy = "id";
		}
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

 
	public boolean hasCriteria() {
		if (StringUtil.isEmpty(getExceptionId())
				&& StringUtil.isEmpty(getDetails())
				&& getUpdateDate() == null ) {
			return false;
		} else {
			return true;
		}
	}

	public String getOrderBy() {
		String orderBy = "id";
		if ("id".equals(getSortBy())) {
			orderBy = "id";
		} else if ("exceptionId".equals(getSortBy()) ) {
			orderBy = "exception_id";
		} else if ( "details".equals(getSortBy())) {
			orderBy = "details";
		} else if ("updateDate".equals(getSortBy())) {
			orderBy = "update_date";
		} 
		return orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getReverseOrder() {
		if (order.equals("asc")) {
			return "desc";
		} else {
			return "asc";
		}
	}
}
