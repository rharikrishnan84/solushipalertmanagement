package com.meritconinc.shiplinx.carrier.ups.dao;

import com.meritconinc.shiplinx.carrier.ups.model.UPSAccCharges;
import com.meritconinc.shiplinx.carrier.ups.model.UPSCANEAS;
import com.meritconinc.shiplinx.carrier.ups.model.UPSDASZipsUS;
import com.meritconinc.shiplinx.carrier.ups.model.UPSTariff;
import com.meritconinc.shiplinx.carrier.ups.model.UPSZoneDiscount;
import com.meritconinc.shiplinx.model.Service;



public interface UPSCanadaTariffDAO {
		public UPSTariff getTariffRecord(UPSTariff tariff);
		public UPSAccCharges getAccCharge(UPSAccCharges upsAccCharge);
		public UPSDASZipsUS getUPSDASZipsUSByZipCode(String zipcode);
		public UPSZoneDiscount getDiscountByZoneAndPackage(UPSZoneDiscount upsZoneDiscount);
		public Service getServiceCodeByService(Service s);
		public UPSCANEAS getUPSCANEAS(String toPostal, String city, String countryCode);

} 
