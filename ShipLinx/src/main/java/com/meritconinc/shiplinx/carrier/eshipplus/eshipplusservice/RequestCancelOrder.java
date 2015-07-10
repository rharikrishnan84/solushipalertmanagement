package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import com.meritconinc.shiplinx.carrier.eshipplus.WSReturn;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axis.types.PositiveInteger;
import org.apache.axis.types.Time;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fedex.ship.stub.NotificationSeverityType;
import com.fedex.ws.pickup.v3.Address;
import com.fedex.ws.pickup.v3.BuildingPartCode;
import com.fedex.ws.pickup.v3.CarrierCodeType;
import com.fedex.ws.pickup.v3.ClientDetail;
import com.fedex.ws.pickup.v3.Contact;
import com.fedex.ws.pickup.v3.ContactAndAddress;
import com.fedex.ws.pickup.v3.CountryRelationshipType;
import com.fedex.ws.pickup.v3.CreatePickupReply;
import com.fedex.ws.pickup.v3.CreatePickupRequest;
import com.fedex.ws.pickup.v3.Localization;
import com.fedex.ws.pickup.v3.PickupBuildingLocationType;
import com.fedex.ws.pickup.v3.PickupOriginDetail;
import com.fedex.ws.pickup.v3.TransactionDetail;
import com.fedex.ws.pickup.v3.VersionId;
import com.fedex.ws.pickup.v3.WebAuthenticationDetail;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.ServiceMode;
import com.meritconinc.shiplinx.carrier.eshipplus.SubmitBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSLocation2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSReturn;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;
import com.meritconinc.shiplinx.carrier.fedex.CancelPackageWebServiceClient;
import com.meritconinc.shiplinx.carrier.fedex.FedExLabelGenerator;
import com.meritconinc.shiplinx.carrier.fedex.PickupWebServiceClient;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class RequestCancelOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		WSReturn Shipment2 = eshipplusws4.getEShipPlusWSv4Soap().requestBookingRequestCancellation("11935", authenticationtoken);
        System.out.println(Shipment2);
	}

}
