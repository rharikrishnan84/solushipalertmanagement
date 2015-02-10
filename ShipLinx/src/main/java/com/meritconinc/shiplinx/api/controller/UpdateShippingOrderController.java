package com.meritconinc.shiplinx.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.api.base.SolushipRestServerResource;
import com.meritconinc.shiplinx.api.model.ODBCShipOrder;
import com.meritconinc.shiplinx.api.model.ShipOrderList;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.PickupDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class UpdateShippingOrderController extends SolushipRestServerResource {
	private static final Logger log = LogManager
			.getLogger(UpdateShippingOrderController.class);
	ShippingDAO shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance()
			.findBean("shippingDAO");
	PickupDAO pickupDAO = (PickupDAO) MmrBeanLocator.getInstance().findBean(
			"pickupDAO");
	ShippingOrder existingOrder;
	private AddressDAO addressDAO = (AddressDAO) MmrBeanLocator.getInstance()
			.findBean("addressDAO");;

	@Post
	public String updateShippingOrder(Representation entity)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		ObjectMapper mapper = new ObjectMapper();
		List<ODBCShipOrder> odcbOrdersList = new ArrayList<ODBCShipOrder>();
		ShipOrderList shippingOrderList = mapper.readValue(entity.getStream(),
				ShipOrderList.class);
		odcbOrdersList = shippingOrderList.getOrders();
		for (ODBCShipOrder order : odcbOrdersList) {
			this.shippingDAO.updateShippingOrderExtended(convertOrder(order));
			log.debug("Order ID: " + order.getSoluShipOrderID()
					+ " Updated Successfully");
		}
		return "success";
	}

	public List<ShippingOrder> getShippingOrders(List<ODBCShipOrder> ordersList) {
		List<ShippingOrder> shipOrders = null;
		List<Long> soluShipOrderIds = new ArrayList<Long>();
		for (int i = 0; i < ordersList.size(); i++) {
			soluShipOrderIds.add(Long.parseLong(ordersList.get(i)
					.getSoluShipOrderID()));
		}
		shipOrders = shippingDAO.getShippingOrders(soluShipOrderIds);
		return shipOrders;
	}

	public ShippingOrder convertOrder(ODBCShipOrder odbcOrder)
			throws ParseException {
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");// 2015-01-29 17:53:56.3
		ShippingOrder order = shippingDAO.getShippingOrder(Long
				.parseLong(odbcOrder.getSoluShipOrderID()));
		Address address = convertAddress(order.getToAddress(), odbcOrder);
		if (address != null) {
			addressDAO.edit(address);
		}
		Pickup pickUp = convertPickup(order.getPickup(), odbcOrder);
		if (pickUp != null) {
			pickupDAO.updatePickup(pickUp);
		}
		if (odbcOrder.getToDangerousGoods() != null) {
			order.setDangerousGoodsName(odbcOrder.getToDangerousGoods());
		}
		if (odbcOrder.getToQuotedWeight() != null
				&& !odbcOrder.getToQuotedWeight().equals("null")) {
			order.setQuotedWeight(Float.parseFloat(odbcOrder
					.getToQuotedWeight()));
		}
		if (odbcOrder.getToQuotedUOM() != null
				&& !odbcOrder.getToQuotedUOM().equals("null")) {
			order.setQuotedWeightUOM(odbcOrder.getToQuotedUOM());
		}
		if (odbcOrder.getToSaturdayDelivery() != null
				&& !odbcOrder.getToSaturdayDelivery().equals("null")) {
			order.setSatDelivery(Boolean.parseBoolean(odbcOrder
					.getToSaturdayDelivery()));
		}
		if (odbcOrder.getToInstructions() != null
				&& !odbcOrder.getToInstructions().equals("null")) {
			order.setToInstructions(odbcOrder.getToInstructions());
		}
		if (odbcOrder.getToBilledUOM() != null
				&& !odbcOrder.getToBilledUOM().equals("null")) {
			order.setBilledWeightUOM(odbcOrder.getToBilledUOM());
		}
		if (odbcOrder.getToCarrierName() != null
				&& !odbcOrder.getToCarrierName().equals("null")) {
			order.setCarrierName(odbcOrder.getToCarrierName());
		}
		if (odbcOrder.getToDutiableAmount() != null
				&& !odbcOrder.getToDutiableAmount().equals("null")) {
			order.setDutiableAmount(new BigDecimal(odbcOrder
					.getToDutiableAmount()));
		}
		if (odbcOrder.getToBilledWeight() != null
				&& !odbcOrder.getToBilledWeight().equals("null")) {
			order.setBilledWeight(Float.parseFloat(odbcOrder
					.getToBilledWeight()));
		}
		if (odbcOrder.getToNotifyRecipient() != null
				&& !odbcOrder.getToNotifyRecipient().equals("null")) {
			order.setNotifyRecipient(Boolean.parseBoolean(odbcOrder
					.getToNotifyRecipient()));
		}
		if (odbcOrder.getToServiceCode() != null
				&& !odbcOrder.getToServiceCode().equals("null")) {
			order.setServiceId(Long.parseLong(odbcOrder.getToServiceCode()));
		}
		if (odbcOrder.getToCarrierID() != null
				&& !odbcOrder.getToCarrierID().equals("null")) {
			order.setCarrierId(Long.parseLong(odbcOrder.getToCarrierID()));
		}
		if (odbcOrder.getToInsideDelivery() != null
				&& !odbcOrder.getToInsideDelivery().equals("null")) {
			order.setInsideDelivery(Boolean.parseBoolean(odbcOrder
					.getToInsideDelivery()));
		}
		if (odbcOrder.getToSignatureRequired() != null
				&& !odbcOrder.getToSignatureRequired().equals("null")) {
			order.setSignatureRequired(Short.parseShort(odbcOrder
					.getToSignatureRequired()));
		}
		if (odbcOrder.getToConformDelivery() != null
				&& !odbcOrder.getToConformDelivery().equals("null")) {
			order.setConfirmDelivery(Boolean.parseBoolean(odbcOrder
					.getToConformDelivery()));
		}
		if (odbcOrder.getToReference1() != null
				&& !odbcOrder.getToReference1().equals("null")) {
			order.setReferenceOne(odbcOrder.getToReference1());
		}
		if (odbcOrder.getToReference2() != null
				&& !odbcOrder.getToReference2().equals("null")) {
			order.setReferenceTwo(odbcOrder.getToReference2());
		}
		if (odbcOrder.getToHoldForPickupRequired() != null
				&& !odbcOrder.getToHoldForPickupRequired().equals("null")) {
			order.setHoldForPickupRequired(Boolean.parseBoolean(odbcOrder
					.getToHoldForPickupRequired()));
		}
		if (odbcOrder.getToRequiredDeliveryDate() != null
				&& !odbcOrder.getToRequiredDeliveryDate().equals("null")) {
			order.setReqDeliveryDate(odbcOrder.getToRequiredDeliveryDate());
		}
		if (odbcOrder.getToHoldForpickup() != null
				&& !odbcOrder.getToHoldForpickup().equals("null")) {
			order.setHoldForPickupRequired(Boolean.parseBoolean(odbcOrder
					.getToHoldForpickup()));
		}
		if (odbcOrder.getToSpecialEquipment() != null
				&& !odbcOrder.getToSpecialEquipment().equals("null")) {
			order.setSpecialEquipment(odbcOrder.getToSpecialEquipment());
		}
		if (odbcOrder.getToCurrency() != null
				&& !odbcOrder.getToCurrency().equals("null")) {
			order.setCurrency(odbcOrder.getToCurrency());
		}
		if (odbcOrder.getToAppointmentDelivery() != null
				&& !odbcOrder.getToAppointmentDelivery().equals("null")) {
			order.setAppointmentDelivery(Boolean.parseBoolean(odbcOrder
					.getToAppointmentDelivery()));
		}
		if (odbcOrder.getToTradeShowDelivery() != null
				&& !odbcOrder.getToTradeShowDelivery().equals("null")) {
			order.setTradeShowDelivery(Boolean.parseBoolean(odbcOrder
					.getToTradeShowDelivery()));
		}
		if (odbcOrder.getToTradeShowPickup() != null
				&& !odbcOrder.getToTradeShowPickup().equals("null")) {
			order.setTradeShowPickup(Boolean.parseBoolean(odbcOrder
					.getToTradeShowPickup()));
		}
		if (odbcOrder.getToAppointmentPickup() != null
				&& !odbcOrder.getToAppointmentPickup().equals("null")) {
			order.setAppointmentPickup(Boolean.parseBoolean(odbcOrder
					.getToAppointmentPickup()));
		}
		if (odbcOrder.getToReceiver() != null
				&& !odbcOrder.getToReceiver().equals("null")) {
			order.setPodReceiver(odbcOrder.getToReceiver());
		}
		if (odbcOrder.getToPaymentType() != null
				&& !odbcOrder.getToPaymentType().equals("null")) {
			order.setCODPayment(odbcOrder.getToPaymentType());
		}
		if (odbcOrder.getToTrackingNumber() != null
				&& !odbcOrder.getToTrackingNumber().equals("null")) {
			order.setTrackingURL(odbcOrder.getToTrackingNumber());
		}
		if (odbcOrder.getToScheduledShipDate() != null
				&& !odbcOrder.getToScheduledShipDate().equals("null")) {
			String strDate = odbcOrder.getToScheduledShipDate();
			String[] strArr = strDate.split("\\.");
			String temp1 = dateFormater.format(strArr[0]);
			Date temp = dateFormater.parse(temp1);
			order.setScheduledShipDate((new java.sql.Timestamp(temp.getTime())));
		}
		return order;
	}

	private static Address convertAddress(Address a, ODBCShipOrder odbcOrder) {

		if (odbcOrder != null) {
			if (odbcOrder.getToCompanyName() != null
					&& !odbcOrder.getToCompanyName().equals("null")) {
				a.setAbbreviationName(odbcOrder.getToCompanyName());
			}
			if (odbcOrder.getToAddress1() != null
					&& !odbcOrder.getToAddress1().equals("null")) {
				a.setAddress1(odbcOrder.getToAddress1());
			}
			if (odbcOrder.getToAddress2() != null
					&& !odbcOrder.getToAddress2().equals("null")) {
				a.setAddress2(odbcOrder.getToAddress2());
			}
			if (odbcOrder.getToCity() != null
					&& !odbcOrder.getToCity().equals("null")) {
				a.setCity(odbcOrder.getToCity());
			}
			if (odbcOrder.getToContact() != null
					&& !odbcOrder.getToContact().equals("null")) {
				a.setContactName(odbcOrder.getToContact());
			}
			if (odbcOrder.getToCountryCode() != null
					&& !odbcOrder.getToCountryCode().equals("null")) {
				a.setCountryCode(odbcOrder.getToCountryCode());
			}
			if (odbcOrder.getToEmail() != null
					&& !odbcOrder.getToEmail().equals("null")) {
				a.setEmailAddress(odbcOrder.getToEmail());
			}
			if (odbcOrder.getToFaxNo() != null
					&& !odbcOrder.getToFaxNo().equals("null")) {
				a.setFaxNo(odbcOrder.getToFaxNo());
			}
			if (odbcOrder.getToMobileNumber() != null
					&& !odbcOrder.getToMobileNumber().equals("null")) {
				a.setMobilePhoneNo(odbcOrder.getToMobileNumber());
			}
			if (odbcOrder.getToPhone() != null
					&& !odbcOrder.getToPhone().equals("null")) {
				a.setPhoneNo(odbcOrder.getToPhone());
			}
			if (odbcOrder.getToPostalCode() != null
					&& !odbcOrder.getToPostalCode().equals("null")) {
				a.setPostalCode(odbcOrder.getToPostalCode());
			}
			if (odbcOrder.getToProvinceCode() != null
					&& !odbcOrder.getToProvinceCode().equals("null")) {
				a.setProvinceCode(odbcOrder.getToProvinceCode());
			}
			if (odbcOrder.getToIsRes() != null
					&& !odbcOrder.getToIsRes().equals("null"))
				a.setResidential(Boolean.parseBoolean(odbcOrder.getToIsRes()));
		}
		return a;
	}

	private static Pickup convertPickup(Pickup pt, ODBCShipOrder odbcOrder) {
		if (pt != null) {
			if (odbcOrder.getToCloseTime() != null
					&& !odbcOrder.getToCloseTime().equals("null")) {
				long cTime = Long.parseLong(odbcOrder.getToCloseTime());
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(cTime);
				int cMins = calendar.get(Calendar.MINUTE);
				int cHour = calendar.get(Calendar.HOUR);
				pt.setCloseHour("" + cHour);
				pt.setCloseMin("" + cMins);
			}
			if (odbcOrder.getToInstructions() != null
					&& !odbcOrder.getToInstructions().equals("null")) {
				pt.setInstructions(odbcOrder.getToInstructions());
			}
			if (odbcOrder.getToLocation() != null
					&& !odbcOrder.getToLocation().equals("null")) {
				pt.setPickupLocation(odbcOrder.getToLocation());
			}
			if (odbcOrder.getToReadyTime() != null
					&& !odbcOrder.getToReadyTime().equals("null")) {
				long cTime = Long.parseLong(odbcOrder.getToCloseTime());
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(cTime);
				int cMins = calendar.get(Calendar.MINUTE);
				int cHour = calendar.get(Calendar.HOUR);
				pt.setReadyHour("" + cHour);
				pt.setReadyMin("" + cMins);
			}
		}
		return pt;
	}

	private static Service convertService(Service s, ODBCShipOrder odbcOrder) {
		if (odbcOrder.getToServiceCode() != null
				&& !odbcOrder.getToServiceCode().equals("null")) {
			s.setId(Long.parseLong(odbcOrder.getToServiceCode())); // s.setCode(st.getCode());
		}
		if (odbcOrder.getToServiceName() != null
				&& !odbcOrder.getToServiceName().equals("null")) {
			s.setName(odbcOrder.getToServiceName());
		}
		if (odbcOrder.getToServicetoDescription() != null
				&& !odbcOrder.getToServicetoDescription().equals("null")) {
			s.setDescription(odbcOrder.getToServicetoDescription());
		}
		return s;
	}

	private static Duration getDuration(String h, String m) {

		try {
			if (!StringUtil.isEmpty(h) && !StringUtil.isEmpty(m)) {
				Duration d = DatatypeFactory.newInstance().newDurationDayTime(
						true, 0, Integer.parseInt(h), Integer.parseInt(m), 0);
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	public ShippingOrder getExistingOrder() {
		return existingOrder;
	}

	public void setExistingOrder(ShippingOrder existingOrder) {
		this.existingOrder = existingOrder;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

}
