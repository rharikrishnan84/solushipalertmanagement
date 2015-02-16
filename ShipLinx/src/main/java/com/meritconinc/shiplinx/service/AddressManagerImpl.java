package com.meritconinc.shiplinx.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.Ostermiller.util.ExcelCSVParser;
import com.Ostermiller.util.LabeledCSVParser;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.dhl.dao.DHLCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLESD;
import com.meritconinc.shiplinx.carrier.purolator.stub.ServiceAvailabilityWebServiceClient;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.AddressType;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

public class AddressManagerImpl implements AddressManager,Runnable  { 

	private static final Logger log = LogManager
			.getLogger(AddressManagerImpl.class);
	AddressDAO addressDAO;
	private String customerId; 
		private InputStream data;
		private String name;
		private boolean deleteExistingAddressess;
		private Thread t;
	public void setAddressDAO(AddressDAO addressBookDAO) {
		this.addressDAO = addressBookDAO;
	}

	public List<Address> findAddressesByCustomer(Long custId) {
		log.debug("ADDRESS BOOK CHECK INSIDE SERVICE IMPL  ---- ");
		return addressDAO.findaddressesByCustomer(custId);
	}

	public Long add(final Address addressbook) {
		addressbook.setDateCreated(new Date());
		return addressDAO.add(addressbook);
	}

	public List<String> getCanadianProvinces() {
		return addressDAO.getCanadianProvinces();
	}

	public List<String> getUSStates() {

		return addressDAO.getUSStates();
	}

	public List<Country> getCountries() {

		return addressDAO.getCountries();
	}

	public void delete(String id) {
		addressDAO.delete(id);
	}

	public void edit(Address addressbook) {
		addressDAO.edit(addressbook);
	}

	public Address findAddressById(String id) {
		Address address = addressDAO.findAddressById(id);
		return address;
	}

	public List<String> findDistributionListForCustomer(Long customerId) {
		log.debug("ADDRESS BOOK CHECK INSIDE SERVICE IMPL  --findDistributionListForCustomer-- ");
		return addressDAO.findDistributionListForCustomer(customerId);
	}

	public void addDistributionList(final Address addressbook) {
		addressDAO.addDistributionList(addressbook);
	}

	public void deleteDistributionList(String id, Long customerId) {
		addressDAO.deleteDistributionList(id, customerId);
	}

	public void run(){
		try {
			long customer_id = Long.valueOf(customerId).longValue();
			BufferedReader br = new BufferedReader(new InputStreamReader(data));
			String[][] values = null;
			try {
				values = new LabeledCSVParser(new ExcelCSVParser(br))
						.getAllValues();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (deleteExistingAddressess)
				deleteDistributionList("", customer_id);

			for (int i = 0; i < values.length; i++) {
				Address entry = new Address();
				entry.setCustomerId(customer_id);
				if (name == null)
					entry.setDistributionName("");
				else
					entry.setDistributionName(name);

				if (values[i].length != 12) {
					throw new ShiplinxException("Invalid file format at Line "
							+ (i + 1));
				}

				for (int j = 0; j < values[i].length; j++) {

					if (j == 0) {
						entry.setContactName(values[i][j]);
					}
					if (j == 1) {
						entry.setAbbreviationName(values[i][j]);
					}
					if (j == 2) {
						entry.setAddress1(values[i][j]);
					}
					if (j == 3) {
						entry.setAddress2(values[i][j]);
					}
					if (j == 4) {
						entry.setCity(values[i][j]);
					}
					if (j == 5) {
						entry.setProvinceCode(values[i][j]);
					}
					if (j == 6) {
						entry.setPostalCode(values[i][j].replace(" ", ""));
					}
					if (j == 7) {
						entry.setCountryCode(values[i][j]);
					}
					if (j == 8) {
						entry.setPhoneNo(values[i][j].replace("-", ""));
					}
					if (j == 9) {
						entry.setPhoneExt(values[i][j]);
					}
					if (j == 10) {
						entry.setEmailAddress(values[i][j]);
					}
					if (j == 11) {
						if (values[i][j] != null
								&& values[i][j].equalsIgnoreCase("Y"))
							entry.setResidential(true);
						else
							entry.setResidential(false);
					}
				}
				log.debug("Entry: " + entry.getLongAddress());
				entry.setDateCreated(new Date());
				addressDAO.addDistributionList(entry);

			}
		} catch (ShiplinxException e) {
			throw new ShiplinxException(e.toString());
		} catch (Exception e) {
			log.debug(e.toString());
		}
	}

	public void parseFile(String customerId, InputStream data, String name,
						boolean deleteExistingAddressess) throws ShiplinxException {
				this.customerId=customerId;
					this.data=data;
					this.name=name;
					this.deleteExistingAddressess=deleteExistingAddressess;
					if (t == null)
				    {
				         t = new Thread(this);
				         t.start ();
				         t=null;
				    }
				}
	public List<Province> getProvinces(String country) {
		return addressDAO.getProvinces(country);
	}

	public Province getProvince(String provinceCode) {
		return addressDAO.getProvince(provinceCode);
	}

	public List<Address> findAddresses(Address addressBook) {
		log.debug("-----2222----");
		log.debug("ADDRESS BOOK CHECK INSIDE SERVICE IMPL  findNonDistrubutionAddressesByCustomer--addressBook- ");
		return addressDAO.findAddresses(addressBook);
	}

	public Address findDefaultToAddressForCustomer(Long customerId) {
		return addressDAO.findDefaultToAddressForCustomer(customerId);
	}

	public Address findDefaultFromAddressForCustomer(Long customerId) {
		return addressDAO.findDefaultFromAddressForCustomer(customerId);
	}
	
	public Address findDefaultCustomsBrokerAddress(Long customerId, String countryCode) {
		return addressDAO.findDefaultCustomsBrokerAddress(customerId,countryCode);
	}
	
	public Address findToAddressForOrder(Long orderId) {
		return addressDAO.findToAddressForOrder(orderId);
	}

	public Address findFromAddressForOrder(Long orderId) {
		return addressDAO.findFromAddressForOrder(orderId);
	}

	public List<Address> searchAddresses(Address add) {
		return addressDAO.searchAddresses(add);
	}
	
	public List<Address> searchCustomsBrokerAddress(String customerId,String countryCode) {
		return addressDAO.searchCustomsBrokerAddress(customerId,countryCode);
	}
	
	public List<Address> searchShipperAndConsigneeAddress(String customerId) {
		return addressDAO.searchShipperAndConsigneeAddress(customerId);
	}

	public void runAddressValidation(Address address) {
		try {
			// currently this will run only for US and Canadian addresses
			if (!address.getCountryCode().equalsIgnoreCase(
					ShiplinxConstants.CANADA)
					&& !address.getCountryCode().equalsIgnoreCase(
							ShiplinxConstants.US))
				return;
			Address found = new Address();
			found.setPostalCode(address.getPostalCode());
			found.setCountryCode(address.getCountryCode());
			ServiceAvailabilityWebServiceClient zipCodeValidator = new ServiceAvailabilityWebServiceClient();
			found = zipCodeValidator.getSuggestedAddress(found);

			if (found != null) {
				if (!StringUtil.isEmpty(found.getCity()))
					address.setCity(found.getCity());
				if (!StringUtil.isEmpty(found.getProvinceCode())) {
					address.setProvinceCode(found.getProvinceCode());
					if (address.getProvinceCode().equalsIgnoreCase("QC"))
						address.setProvinceCode("PQ");
					if (address.getProvinceCode().equalsIgnoreCase("NL"))
						address.setProvinceCode("NF");
				}
			}
		} catch (Exception e) {
			log.error("Could not validate address for postal and country: "
					+ address.getPostalCode() + " / "
					+ address.getCountryCode());
		}

	}

	/*
	 * This method retrieves the list of [cities, zipcode] from the database for
	 * provided city and country code.
	 * 
	 * @param String, String
	 * 
	 * @return List<String>
	 * 
	 * @Exception Nothing.
	 */

	public List<String> getCitySuggest(String country, String city) {

		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext((ServletContext) ActionContext
						.getContext().get(ServletActionContext.SERVLET_CONTEXT));
		DHLCanadaTariffDAO dhlCanadaTariffDAO = (DHLCanadaTariffDAO) context
				.getBean("dhlCanadaTariffDAO");

		DHLESD dhlEsd = new DHLESD();
		dhlEsd.setCityName(city);
		dhlEsd.setCountryCode(country);

		List<DHLESD> dhlEsdList = dhlCanadaTariffDAO.getDHLESDByCity(dhlEsd);
		if (dhlEsdList == null || dhlEsdList.size() == 0)
			return null;

		List<String> result = new ArrayList<String>();

		for (DHLESD d : dhlEsdList) {

			StringBuilder stb = new StringBuilder();
			stb.append(d.getCityName());
			if (!StringUtil.isEmpty(d.getZipCodeStart())) {
				stb.append(" , " + d.getZipCodeStart());
			}

			result.add(stb.toString());
		}
		return result;
	}

	/*
	 * This method retrieves the list of [zipcode, cities] from the database for
	 * provided zipcode and country code.
	 * 
	 * @param String, String
	 * 
	 * @return List<String>
	 * 
	 * @Exception Nothing.
	 */

	public List<String> getZipSuggest(String country, String zip) {

		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext((ServletContext) ActionContext
						.getContext().get(ServletActionContext.SERVLET_CONTEXT));
		DHLCanadaTariffDAO dhlCanadaTariffDAO = (DHLCanadaTariffDAO) context
				.getBean("dhlCanadaTariffDAO");

		DHLESD dhlEsd = new DHLESD();
		dhlEsd.setZipCodeStart(zip);
		dhlEsd.setCountryCode(country);

		List<DHLESD> dhlEsdList = dhlCanadaTariffDAO.getDHLESDByZip(dhlEsd);
		if (dhlEsdList == null || dhlEsdList.size() == 0)
			return null;

		List<String> result = new ArrayList<String>();

		for (DHLESD d : dhlEsdList) {

			StringBuilder stb = new StringBuilder();
			stb.append(d.getZipCodeStart());
			if (!StringUtil.isEmpty(d.getCityName())) {
				stb.append(" , " + d.getCityName());
			}

			result.add(stb.toString());
		}
		return result;
	}

	/**
	 * method to get custom broker
	 */
	@Override
	public List<AddressType> getCustomBrokers() {
		return addressDAO.getCustomBrokers();
	}

	@Override
	public List<Address> findaddressbyid(Long customerId) {
		return addressDAO.findaddressbyid(customerId);
	}

	@Override
	public Address findDefaultFromAddressForAddress(Long addressId) {
		return addressDAO.findDefaultFromAddressForAddress(addressId);
	}
	public Province getProvinceName(String provinceCode){
				 return addressDAO.getProvinceName(provinceCode);
			}
	
	public void setSendNotification(Long addressId){
				  addressDAO.setSendNotification(addressId);
			}
	@Override
	public Long findAddressId(long id) {
		// TODO Auto-generated method stub
		return addressDAO.findAddressId(id);
	}
}