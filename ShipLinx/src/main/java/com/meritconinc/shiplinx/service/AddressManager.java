package com.meritconinc.shiplinx.service;

import java.io.InputStream;
import java.util.List;

import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.AddressType;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.Province;




public interface AddressManager{ 
	
	public List<Address> findAddressesByCustomer(Long custId);
	public Long add(Address addressbook);
	List<String> getCanadianProvinces();
	List<String> getUSStates();
	public List<Country> getCountries();
	public void delete(String id);
	public Address findAddressById(String id);
	public void edit(Address addressbook);
	
	public List<String> findDistributionListForCustomer(Long id);
	public void parseFile(String customerId, InputStream data,String name, boolean deleteExistingAddressess) throws ShiplinxException;
	public void deleteDistributionList(String id, Long customerID);

	
	List<Province> getProvinces(String country);
	public Province getProvince(String provinceCode);
	public Address findDefaultToAddressForCustomer(Long customerId);
	public Address findDefaultFromAddressForCustomer(Long customerId);
	public Address findDefaultFromAddressForAddress(Long addressId);
	public Address findToAddressForOrder(Long orderId);
	public Address findFromAddressForOrder(Long orderId);
	
	public List<Address> findAddresses(Address addressBook);
	public List<Address> searchAddresses(Address addressBook);
	
	public void runAddressValidation(Address address);
	// This method returns list of strings in city, zip pair for selected country
	public List<String> getCitySuggest(String country, String city);
	// This method returns list of strings in zip, city pair for selected country
	public List<String> getZipSuggest(String country, String zip);
	public List<AddressType> getCustomBrokers();
	public Address findDefaultCustomsBrokerAddress(Long customerId,String countryCode);
	public List<Address> searchCustomsBrokerAddress(String customerId,String countryCode);
	public List<Address> searchShipperAndConsigneeAddress(String customerId);
	public List<Address> findaddressbyid(Long customerId);
	public Province getProvinceName(String provinceCode);
	
}