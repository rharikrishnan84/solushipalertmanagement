package com.meritconinc.shiplinx.model;

import java.io.Serializable;

public class DangerousGoods implements Serializable{
	
	private Long dangerousgoodsId;
	private String dangerousGoodsName;
	
	
	public Long getDangerousgoodsId() {
		return dangerousgoodsId;
	}
	public void setDangerousgoodsId(Long dangerousgoodsId) {
		this.dangerousgoodsId = dangerousgoodsId;
	}
	public String getDangerousGoodsName() {
		return dangerousGoodsName;
	}
	public void setDangerousGoodsName(String dangerousgoodsName) {
		this.dangerousGoodsName = dangerousgoodsName;
	}

} 
