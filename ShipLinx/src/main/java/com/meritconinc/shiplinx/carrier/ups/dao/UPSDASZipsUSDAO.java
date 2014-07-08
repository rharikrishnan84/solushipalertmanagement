package com.meritconinc.shiplinx.carrier.ups.dao;

import com.meritconinc.shiplinx.carrier.ups.model.UPSDASZipsUS;


public interface UPSDASZipsUSDAO {

	public UPSDASZipsUS getUPSDASZipsUSByZipCode(String zipCode);
}
 