package com.soluship.businessfilter.util;

import java.util.HashMap;

public class CSSClass {
    
	private String selector;
	private String propery;
	private String value;
	private HashMap<String, String> propertyMap;
	
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getPropery() {
		return propery;
	}
	public void setPropery(String propery) {
		this.propery = propery;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public HashMap<String, String> getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(HashMap<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}
 
	
}
