package com.meritconinc.shiplinx.carrier.dhl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidatePiece;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.Reference;
import com.meritconinc.shiplinx.carrier.dhl.xml.ShipValResponsePiece;
import com.meritconinc.shiplinx.carrier.dhl.xml.SpecialService;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.utils.DHLException;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class DhlResponseHandler {
	private static final Logger logger = Logger.getLogger(DhlResponseHandler.class);

	public DhlShipValidateResponse getShipValidateResponse(ShipmentResponse res) {
		// TODO Auto-generated method stub
		DhlShipValidateResponse r = new DhlShipValidateResponse();

		r.setAirwayBillNumber( res.getAirwayBillNumber() );
		
		if (res.getConsignee() != null) {
			r.setConsigneeFederalTaxId( res.getConsignee().getFederalTaxId() );
			r.setConsigneeStateTaxId(res.getConsignee().getStateTaxId());
		}
		
		r.setContentsDescription(res.getContents());
		r.setDeliveryDateCode(res.getDeliveryDateCode());
		r.setDeliveryTimeCode(res.getDeliveryTimeCode());
		
		if (res.getDestinationServiceArea() != null) {
			r.setDestFacilityCode(res.getDestinationServiceArea().getFacilityCode());
			r.setDestServiceAreaCode(res.getDestinationServiceArea().getServiceAreaCode());
			r.setInBoundSortCode(res.getDestinationServiceArea().getInboundSortCode());
		}
		 
		r.setDhlRoutingCode(res.getDHLRoutingCode());
		r.setDhlRoutingDataId(res.getDHLRoutingDataId());
		
		if (res.getBilling() != null) {
			r.setDutyAccountNumber( "" + res.getBilling().getDutyAccountNumber());
			if (res.getBilling().getDutyPaymentType() != null)
				r.setDutyPaymentType(res.getBilling().getDutyPaymentType().value());
			r.setShipperAccountNumber((res.getBilling().getShipperAccountNumber()));
			r.setBillingAccountNumber((res.getBilling().getBillingAccountNumber()));
		}
		if (res.getDutiable() != null) {
			r.setDutyDeclaredCurrency(res.getDutiable().getDeclaredCurrency());
			r.setDutyDeclaredValue(res.getDutiable().getDeclaredValue());
			if (res.getDutiable().getTermsOfTrade() != null)
				r.setDutyTermsOfTrade(res.getDutiable().getTermsOfTrade().toString());
		}
		if (res.getOriginServiceArea() != null) {
			r.setOriginServiceAreaCode(res.getOriginServiceArea().getServiceAreaCode());
			r.setOutBoundSortCode(res.getOriginServiceArea().getOutboundSortCode());
		}
		
		r.setInsuredAmount(res.getInsuredAmount());
		if (res.getInternalServiceCode() != null) {
			r.setInternalServiceCode(convertInternalServiceCodeListToString(res.getInternalServiceCode()));
		}
		if (res.getGlobalProductCode() != null) 
			r.setGlobalProductCode(res.getGlobalProductCode().value());
		
		if (res.getPieces() != null && res.getPieces().getPiece() != null && res.getPieces().getPiece().size() > 0) {
			r.setPieces(convertPiecesList(res.getPieces().getPiece()));
		}

		r.setProductContentCode(res.getProductContentCode());
		r.setProductShortName(res.getProductShortName());
		
		r.setReference(convertReferenceListToString(res.getReference()));
		
		if (res.getShipper() != null) {
			r.setShipperFederalTaxId(res.getShipper().getFederalTaxId());
			r.setShipperStateTaxId(res.getShipper().getStateTaxId());
		}
		
		r.setSpecialServiceType(convertSpecialServiceListToString(res.getSpecialService()));
		
		if (res.getWeightUnit() != null)
			r.setWeightUnit(res.getWeightUnit());
		
		
		r.setChargeableWeight(res.getChargeableWeight());
		
		return r;
	}

	private List<DhlShipValidatePiece> convertPiecesList(List<com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipValResponsePiece> list) {
		if (list != null && list.size() > 0) {
			List<DhlShipValidatePiece> pList = new ArrayList<DhlShipValidatePiece>();
			for (com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipValResponsePiece pSrc:list) {
				DhlShipValidatePiece pDest = new DhlShipValidatePiece();
				pDest.setLicensePlate(pSrc.getLicensePlate());
				pDest.setNumber(pSrc.getPieceNumber().longValue());
				pDest.setPieceIdentifier(pSrc.getDataIdentifier());
				pDest.setWeight(pSrc.getWeight());
				pList.add(pDest);
			}
			
			return pList;
		}
		return null;
	}
	
	
	private String convertSpecialServiceListToString(List<com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.SpecialService> list) {
		StringBuilder sb = new StringBuilder();
		boolean bSeperateIt = false;
		for (com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.SpecialService r:list) {
			if (bSeperateIt)
				sb.append(DhlShipValidateResponse.SEPERATOR);
			sb.append(r.getSpecialServiceType());
			bSeperateIt = true;
		}		
		return sb.toString();
	}	
	
	
	private String convertReferenceListToString(List<com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.Reference> list) {
		StringBuilder sb = new StringBuilder();
		boolean bSeperateIt = false;
		for (com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.Reference r:list) {
			if (bSeperateIt)
				sb.append(DhlShipValidateResponse.SEPERATOR);
			sb.append(r.getReferenceID());
			bSeperateIt = true;
		}		
		return sb.toString();
	}

	private String convertInternalServiceCodeListToString(List<String> list) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		boolean bSeperateIt = false;
		for (String s:list) {
			if (bSeperateIt)
				sb.append(DhlShipValidateResponse.SEPERATOR);
			sb.append(s);
			bSeperateIt = true;
		}		
		return sb.toString();
	}
	
	public void generateLabel(ShippingOrder order, DhlShipValidateResponse res, OutputStream outStream) 
			throws Exception {
		// TODO Auto-generated method stub
		try {
			if (order != null && res != null) {
				PDFRenderer pdfRenderer = new PDFRenderer();
				String mainPDFFileName = pdfRenderer.getUniqueTempPDFFileName("SOP_" + res.getAirwayBillNumber());
				generateLabel(order, res, mainPDFFileName);
				String arcPDFFileName = pdfRenderer.getUniqueTempPDFFileName("ARC_" + res.getAirwayBillNumber());
				generateArchiveLabel(order, res, arcPDFFileName);
				ArrayList<String> srcList = new ArrayList<String>();
				srcList.add(mainPDFFileName);
				srcList.add(arcPDFFileName);

				if (outStream != null)
					pdfRenderer.concatPDF(srcList, outStream);
			
				// delete temp files
				pdfRenderer.deleteFiles(srcList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}		
		
	}	

	private void generateArchiveLabel(ShippingOrder order, DhlShipValidateResponse res, String pdfFileName) {
		// TODO Auto-generated method stub
	    HashMap <String, Object> shipmentDetails = new HashMap <String, Object>();
	    
		shipmentDetails.put("ProductShortName", res.getProductShortName() );
		shipmentDetails.put("ProductContentCode", res.getProductContentCode());
		
		
		String shipperCNPJ = res.getShipperFederalTaxId();
		String consigneeCNPJ = res.getConsigneeFederalTaxId();
		String shipperIE = res.getShipperStateTaxId();
		String consigneeIE = res.getConsigneeStateTaxId();
		
		if("BR".equalsIgnoreCase(order.getToAddress().getCountryCode())){
			if (shipperCNPJ != null && !"".equals(shipperCNPJ.trim())) {	
				shipmentDetails.put("ShipperCNPJ", "CNPJ / CPF:"+shipperCNPJ);
			}
			if (consigneeCNPJ != null && !"".equals(consigneeCNPJ.trim())) {
				shipmentDetails.put("ConsigneeCNPJ", "CNPJ / CPF:"+consigneeCNPJ);
			}
			if (shipperIE != null && !"".equals(shipperIE.trim())) {
				shipmentDetails.put("ShipperIE", shipperIE );
			}
			if (consigneeIE != null && !"".equals(consigneeIE.trim())) {
				shipmentDetails.put("ConsigneeIE", consigneeIE );
			}
		} else {
			if (shipperCNPJ != null && !"".equals(shipperCNPJ.trim())) {
				shipmentDetails.put("ShipperCNPJ", "ID:"+shipperCNPJ);
			}
			if (consigneeCNPJ != null && !"".equals(consigneeCNPJ.trim())) {
				shipmentDetails.put("ConsigneeCNPJ", "ID:"+consigneeCNPJ);
			}
		}
		
		if("US".equalsIgnoreCase(order.getFromAddress().getCountryCode()) 
			&& !"US".equalsIgnoreCase(order.getToAddress().getCountryCode())){
			
			shipmentDetails.put("CommerceControlStatement", 
					"These commodities, technology or software were exported " +
					"from the United States in accordance with the Export Administration " +
					"regulations. Diversion contrary to U.S law prohibited. " +
					"Shipment may be varied via intermediate stopping places " +
					"which DHL deems appropriate." );
		}
        

        shipmentDetails.put("ShipperCompanyName", order.getFromAddress().getAbbreviationName());
		StringBuilder sb = new StringBuilder();
		sb.append(order.getFromAddress().getAddress1());
		if (order.getFromAddress().getAddress2() != null) {
			sb.append("\n");
			sb.append(order.getFromAddress().getAddress2());
		}
		shipmentDetails.put("ShipperAddress", sb.toString());
	
		//Populate Shippper City Divison Code and postal code
		sb = new StringBuilder();
		String shipperCity = order.getFromAddress().getCity();
		if (shipperCity != null && !"".equals(shipperCity.trim())) {
			sb.append(shipperCity);
			sb.append("  ");
		}
		String shipperDivisionCode = order.getFromAddress().getProvinceCode();
		if (shipperDivisionCode != null && !"".equals(shipperDivisionCode.trim())) {
			sb.append(shipperDivisionCode);
			sb.append("  ");
		}
		String shipperPostalCode = order.getFromAddress().getPostalCode();
		if (shipperPostalCode != null && !"".equals(shipperPostalCode.trim())) {
			shipperPostalCode = shipperPostalCode.toUpperCase();
			sb.append(shipperPostalCode);
		}
		shipmentDetails.put("ShipperCity", sb.toString());
		shipmentDetails.put("ShipperCountry", order.getFromAddress().getCountryCode()); //.getShipper().getCountryName());
		
		shipmentDetails.put("OriginServiceAreaCode", res.getOriginServiceAreaCode());
		
		shipmentDetails.put("ShipperContactName", order.getFromAddress().getContactName());
		shipmentDetails.put("ShipperPhoneNumber", "Ph:" + order.getFromAddress().getPhoneNo());
		
		//Populate Consignee company name & Consignee address line 
		sb = new StringBuilder();
		sb.append(order.getToAddress().getAbbreviationName());
		sb.append("\n");
		sb.append(order.getToAddress().getContactName());
		sb.append(order.getToAddress().getAddress1());
		if (order.getToAddress().getAddress2() != null) {
			sb.append("\n");
			sb.append(order.getToAddress().getAddress2());
		}
		shipmentDetails.put("DestinationAddress", sb.toString());
		
		//Populate consignee Address details
		sb = new StringBuilder();
		String receiverCity = order.getToAddress().getCity();
		if(receiverCity != null && !"".equals(receiverCity.trim())){
			sb.append(receiverCity);
			sb.append("  ");			
		}
		String receiverDivisionCode = order.getToAddress().getProvinceCode();
		if(receiverDivisionCode != null && !"".equals(receiverDivisionCode.trim())){
			sb.append(receiverDivisionCode);
			sb.append("  ");			
		}
		String receiverPostalCode = order.getToAddress().getPostalCode();
		if (receiverPostalCode != null && !"".equals(receiverPostalCode.trim())) {
			receiverPostalCode = receiverPostalCode.toUpperCase();
			sb.append(receiverPostalCode);
		}
		int destCityLength = sb.toString().length();
		if(destCityLength > 46) {
			shipmentDetails.put("DestinationCity", sb.toString());
		} else {
			shipmentDetails.put("DestinationCityExtra", sb.toString());
		}
		shipmentDetails.put("DestinationCountry", order.getToAddress().getCountryCode());
	    
		//Populate consignee Contact details
		shipmentDetails.put("ReceiverContactDetails", "Ph:" + order.getToAddress().getPhoneNo());
		
		//Populate OutBoundSortCode, InBoundSortCode and DestinationFacilityCode
		shipmentDetails.put("OutBoundSortCode", res.getOutBoundSortCode());
		shipmentDetails.put("InBoundSortCode", res.getInBoundSortCode());
		
		sb = new StringBuilder();
		sb.append(order.getToAddress().getCountryCode());
		sb.append("-");
		sb.append(res.getDestServiceAreaCode());
		String facilityCode= res.getDestFacilityCode();
		if ( !StringUtil.isEmpty(res.getDestFacilityCode()) )	{
			sb.append("-");
			sb.append(res.getDestFacilityCode());
		}	
		shipmentDetails.put("DestinationFacilityCode", sb.toString());
		
		//Populate feature codes
		List<String> internalServiceCodeList = convertToStringList(res.getInternalServiceCode());
        if(internalServiceCodeList!=null && internalServiceCodeList.contains("C"))	{		
			shipmentDetails.put("DuitableFlag","true");
		} else {					
			shipmentDetails.put("DuitableFlag","false");
		}
        // Added for sorting product feature
		Collections.sort(internalServiceCodeList, new DhlInternalServiceCodeComparator());
		sb = new StringBuilder();
		for(String iSC: internalServiceCodeList){
			if (sb.length() > 0)
				sb.append("-");	
			sb.append(iSC);
		}
//		shipmentDetails.put("ServiceCode",serviceCode);		// In sample code, it' populated with empty string, therefore not populating
		
		if (internalServiceCodeList.size() == 0	|| internalServiceCodeList.size() == 1) {
			shipmentDetails.put("InternalServiceFlag", "one");
		} else if (internalServiceCodeList.size() == 2) {
			shipmentDetails.put("InternalServiceFlag", "two");
		} else if (internalServiceCodeList.size() == 3) {
			shipmentDetails.put("InternalServiceFlag", "three");
		} else if (internalServiceCodeList.size() == 4) {
			shipmentDetails.put("InternalServiceFlag", "four");
		} else {
			shipmentDetails.put("InternalServiceFlag", "five");
		}
		shipmentDetails.put("InternalServiceCode", sb.toString());
		
		//Populate Date Time Code
		shipmentDetails.put("DeliveryTime", res.getDeliveryTimeCode());
		shipmentDetails.put("CalendarDate", res.getDeliveryDateCode());
		
		shipmentDetails.put("AccountNumber", res.getBillingAccountNumber());		
		
		shipmentDetails.put("ReferenceNumber", res.getReference());
        shipmentDetails.put("Date", FormattingUtil.getFormattedDate(order.getScheduledShipDate(), FormattingUtil.DATE_FORMAT_WEB));
        shipmentDetails.put("ContentsDescription","Content: " + res.getContentsDescription());
//		shipmentDetails.put("DataId", "(" + res.getDhlRoutingDataId() + ")");
				
		String airWayBillNumber = res.getAirwayBillNumber();
		String airWayBillNumberWithSpaces="";
		while(airWayBillNumber.length() > 0){
			int len = airWayBillNumber.length() - 4;
			if(len > 0){
				airWayBillNumberWithSpaces = airWayBillNumber.substring(len) + " " + airWayBillNumberWithSpaces;
				airWayBillNumber = airWayBillNumber.substring(0,len);
			}else{
				airWayBillNumberWithSpaces = airWayBillNumber +  " " + airWayBillNumberWithSpaces;
				airWayBillNumber="";
			}			
		}
		shipmentDetails.put("AirWayBillNumber", airWayBillNumberWithSpaces);
		shipmentDetails.put("AirWayBillBarCode", res.getAirwayBillNumber());
		
//		String routingBarCodeText = res.getDhlRoutingCode();
//		if (routingBarCodeText != null) {
//			routingBarCodeText = routingBarCodeText.toUpperCase();			
//		}
//		shipmentDetails.put("DHLRoutingBarCodeText",routingBarCodeText);
//		String routingBarcode = res.getDhlRoutingDataId() + res.getDhlRoutingCode();		
//		shipmentDetails.put("DHLRoutingBarCode", routingBarcode);

		String unit = res.getWeightUnit();
		String weightUnit;
		if (unit != null) {
			weightUnit = unit.toString();
		} else {
			weightUnit = "";
		}
		if (weightUnit.equalsIgnoreCase("G")) {
			weightUnit = "gm";
		} else if (weightUnit.equalsIgnoreCase("K")) {
			weightUnit = "Kg";
		} else {
			weightUnit = "Lbs";
		}
		if (res.getChargeableWeight() != null) {
			shipmentDetails.put("ShipmentWeight", res.getChargeableWeight().toString() + " " + weightUnit);
		}
		shipmentDetails.put("GlobalProductCode", res.getGlobalProductCode()); // End
		
		
		//Start:Populating piece specific values in pieceMap which will be different on each page
//		ArrayList <ShipValResponsePiece> pieceList = (ArrayList <ShipValResponsePiece> ) shipmentValidateResponse.getPieces().getPiece();
//		Iterator <ShipValResponsePiece> itrPiece = pieceList.iterator();
		String totalPieces = "0";
		if(res.getPieces() != null){
			totalPieces = "" + res.getPieces().size();
		}		
		shipmentDetails.put("Pieces",totalPieces);
		
		//List hold maps which contains piece details for each page
		ArrayList<HashMap<String,String>> pieceFieldMaps = new ArrayList<HashMap<String,String>>();
		
		//Start:Populating piece specific values in pieceMap
		List <DhlShipValidatePiece> pieceList = res.getPieces();
		
		DhlShipValidatePiece shipValResponsePiece;

		StringBuilder licensePlateBarCodeText = new StringBuilder();		

		//if(Integer.parseInt(totalPieces) <= 11 || Integer.parseInt(totalPieces) > 11 )	{
		List<DhlShipValidatePiece> mainpageList;
		List<DhlShipValidatePiece> subpageList;
			
		
		//LP numbers on 1st Archive page
		if(pieceList.size() > 7){
			mainpageList = pieceList.subList(0, 7);
			subpageList = pieceList.subList(7, pieceList.size());
		} else {
			mainpageList = pieceList;
			subpageList = new ArrayList<DhlShipValidatePiece>();
		}
		//subpageList = subpageList.subList(0, 39 + 38);
		//int x = 0;
		
		Iterator<DhlShipValidatePiece> mainpageIterator = mainpageList.iterator();
		while(mainpageIterator.hasNext()){				
			shipValResponsePiece = (DhlShipValidatePiece) mainpageIterator.next();
			String licensePlateWithSpacesFirst = getLicensePlateWithSpaces(shipValResponsePiece.getLicensePlate());
			licensePlateBarCodeText.append(licensePlateWithSpacesFirst+"\n");			
		}
		shipmentDetails.put("LicensePlateBarCodeText",licensePlateBarCodeText.toString());	
		if (subpageList.size() > 0) {	
			shipmentDetails.put("Continued_Title","(continued):");								
		} else {
			shipmentDetails.put("Continued_Title",":");	
		}
		  
		Iterator<DhlShipValidatePiece> subpageListIterator = subpageList.iterator();
		
		int count = 0;
		
		HashMap<String, String> pieceMap  = new HashMap<String, String>();
		pieceMap.put("PageCounter","2");
		StringBuilder	licensePlateSB = new StringBuilder();
		while(subpageListIterator.hasNext()){
			if(count == 39){
				
				pieceMap.put("FieldLicensePlateBarCodeText",licensePlateSB.toString());
				pieceMap.put("Continued","(continued):");
				pieceFieldMaps.add(pieceMap);
				Integer pageCounter = new Integer(pieceMap.get("PageCounter"));
				
				count = 0;
				pieceMap  = new HashMap<String, String>();
				
				pageCounter++;
				pieceMap.put("PageCounter", pageCounter.toString());
				licensePlateSB = new StringBuilder();
			}
			shipValResponsePiece = (DhlShipValidatePiece)subpageListIterator.next();
			String licensePlateWithSpaces = getLicensePlateWithSpaces(shipValResponsePiece.getLicensePlate());
			licensePlateSB.append(licensePlateWithSpaces+"\n");			
			count++;
		}
		if (subpageList.size() > 0) {
			pieceMap.put("FieldLicensePlateBarCodeText",licensePlateSB.toString());
			pieceMap.put("Continued",":");
			pieceFieldMaps.add(pieceMap);			
		}
		
		
		List<String> serviceList = convertToStringList(res.getSpecialServiceType());
		if (serviceList != null) {		
			for (String specialService:serviceList) {
				if ("D".equalsIgnoreCase(specialService) && "T".equalsIgnoreCase(res.getDutyPaymentType())) {
					shipmentDetails.put("DutyAccountNumber", "" + res.getDutyAccountNumber());
				}
			}
		}
        if(res.getInsuredAmount() != null) {
        	shipmentDetails.put("InsuredAmount", res.getInsuredAmount() + " " + order.getCurrency());
        }

		if (res.getDutyDeclaredValue() != null && res.getDutyDeclaredCurrency() != null) {
			shipmentDetails.put("DeclaredValue", res.getDutyDeclaredValue() + " " + res.getDutyDeclaredCurrency());
			if (res.getDutyTermsOfTrade() != null) {				
				shipmentDetails.put("TermsOfTrade", res.getDutyTermsOfTrade());
			}
		}
		if (res.getBillingAccountNumber() != null) {			
			shipmentDetails.put("BillingAccountNumber", res.getBillingAccountNumber());
		}
		
		if(order.getCustomsInvoice()!=null){ //if bill to shipper, then try to get the DHL account number related to the customer, if not found then use the generic billing account # of the business
			if(order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_SHIPPER)){
				
				shipmentDetails.put("specialServiceType", "DD"); //Jay Cook}
				
				if(!StringUtil.isEmpty(res.getShipperAccountNumber()))
					shipmentDetails.put("DutyAccountNumber", res.getShipperAccountNumber());
				else
					shipmentDetails.put("DutyAccountNumber", res.getBillingAccountNumber());
			}
			else if(!StringUtil.isEmpty(order.getCustomsInvoice().getBillToAccountNum()) || order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY))
				shipmentDetails.put("DutyAccountNumber", order.getCustomsInvoice().getBillToAccountNum());
		}
		
//		String pdfFileName = "D:\\temp\\" + res.getAirwayBillNumber() + ".pdf";
		generateArchiveReport(shipmentDetails, pieceFieldMaps, pdfFileName);
	}

	private void generateArchiveReport(HashMap<String, Object> shipmentDetails, ArrayList<HashMap<String, String>> pieceFieldMaps,
													String pdfFileName) {
		// TODO Auto-generated method stub
		try {
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream(
								"./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/DHLArchiveLabel.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
        
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(pieceFieldMaps);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, shipmentDetails, ds);

			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName );
			System.out.println("generateArchiveReport : Archive Label generated");
		} catch (Exception e) {
			logger.error("Could not generate Shiplinx Invoice Main !!");
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}		
	}

	private void generateLabel(ShippingOrder order, DhlShipValidateResponse res, String pdfFileName) {
		// TODO Auto-generated method stub
	    HashMap <String, Object> shipmentDetails = new HashMap <String, Object>();
	    
		shipmentDetails.put("ProductShortName", res.getProductShortName() );
		String shipperCNPJ = res.getShipperFederalTaxId();
		String consigneeCNPJ = res.getConsigneeFederalTaxId();
		String shipperIE = res.getShipperStateTaxId();
		String consigneeIE = res.getConsigneeStateTaxId();
		
		if("BR".equalsIgnoreCase(order.getToAddress().getCountryCode())){
			if (shipperCNPJ != null && !"".equals(shipperCNPJ.trim())) {	
				shipmentDetails.put("ShipperCNPJ", "CNPJ / CPF:"+shipperCNPJ);
			}
			if (consigneeCNPJ != null && !"".equals(consigneeCNPJ.trim())) {
				shipmentDetails.put("ConsigneeCNPJ", "CNPJ / CPF:"+consigneeCNPJ);
			}
			if (shipperIE != null && !"".equals(shipperIE.trim())) {
				shipmentDetails.put("ShipperIE", shipperIE );
			}
			if (consigneeIE != null && !"".equals(consigneeIE.trim())) {
				shipmentDetails.put("ConsigneeIE", consigneeIE );
			}
		} else {
			if (shipperCNPJ != null && !"".equals(shipperCNPJ.trim())) {
				shipmentDetails.put("ShipperCNPJ", "ID:"+shipperCNPJ);
			}
			if (consigneeCNPJ != null && !"".equals(consigneeCNPJ.trim())) {
				shipmentDetails.put("ConsigneeCNPJ", "ID:"+consigneeCNPJ);
			}
		}
		
		if("US".equalsIgnoreCase(order.getFromAddress().getCountryCode()) 
			&& !"US".equalsIgnoreCase(order.getToAddress().getCountryCode())){
			
			shipmentDetails.put("CommerceControlStatement", 
					"These commodities, technology or software were exported " +
					"from the United States in accordance with the Export Administration " +
					"regulations. Diversion contrary to U.S law prohibited. " +
					"Shipment may be varied via intermediate stopping places " +
					"which DHL deems appropriate." );
		}
        shipmentDetails.put("ProductContentCode", res.getProductContentCode());

        shipmentDetails.put("ShipperCompanyName", order.getFromAddress().getAbbreviationName());
		StringBuilder sb = new StringBuilder();
		sb.append(order.getFromAddress().getAddress1());
		if (order.getFromAddress().getAddress2() != null) {
			sb.append("\n");
			sb.append(order.getFromAddress().getAddress2());
		}
		shipmentDetails.put("ShipperAddress", sb.toString());
	
		//Populate Shippper City Divison Code and postal code
		sb = new StringBuilder();
		String shipperCity = order.getFromAddress().getCity();
		if (shipperCity != null && !"".equals(shipperCity.trim())) {
			sb.append(shipperCity);
			sb.append("  ");
		}
		String shipperDivisionCode = order.getFromAddress().getProvinceCode();
		if (shipperDivisionCode != null && !"".equals(shipperDivisionCode.trim())) {
			sb.append(shipperDivisionCode);
			sb.append("  ");
		}
		String shipperPostalCode = order.getFromAddress().getPostalCode();
		if (shipperPostalCode != null && !"".equals(shipperPostalCode.trim())) {
			shipperPostalCode = shipperPostalCode.toUpperCase();
			sb.append(shipperPostalCode);
		}
		shipmentDetails.put("ShipperCity", sb.toString());
		shipmentDetails.put("ShipperCountry", order.getFromAddress().getCountryCode()); //.getShipper().getCountryName());
		
		shipmentDetails.put("OriginServiceAreaCode", res.getOriginServiceAreaCode());
		
		shipmentDetails.put("ShipperContactName", order.getFromAddress().getContactName());
		shipmentDetails.put("ShipperPhoneNumber", "Ph:" + order.getFromAddress().getPhoneNo());
		
		//Populate Consignee company name & Consignee address line 
		sb = new StringBuilder();
		sb.append(order.getToAddress().getAbbreviationName());
		sb.append("\n");
		sb.append(order.getToAddress().getContactName());
		sb.append(order.getToAddress().getAddress1());
		if (order.getToAddress().getAddress2() != null) {
			sb.append("\n");
			sb.append(order.getToAddress().getAddress2());
		}
		shipmentDetails.put("DestinationAddress", sb.toString());
		
		//Populate consignee Address details
		sb = new StringBuilder();
		String receiverCity = order.getToAddress().getCity();
		if(receiverCity != null && !"".equals(receiverCity.trim())){
			sb.append(receiverCity);
			sb.append("  ");			
		}
		String receiverDivisionCode = order.getToAddress().getProvinceCode();
		if(receiverDivisionCode != null && !"".equals(receiverDivisionCode.trim())){
			sb.append(receiverDivisionCode);
			sb.append("  ");			
		}
		String receiverPostalCode = order.getToAddress().getPostalCode();
		if (receiverPostalCode != null && !"".equals(receiverPostalCode.trim())) {
			receiverPostalCode = receiverPostalCode.toUpperCase();
			sb.append(receiverPostalCode);
		}
		int destCityLength = sb.toString().length();
		if(destCityLength > 46) {
			shipmentDetails.put("DestinationCity", sb.toString());
		} else {
			shipmentDetails.put("DestinationCityExtra", sb.toString());
		}
		shipmentDetails.put("DestinationCountry", order.getToAddress().getCountryCode());
	    
		//Populate consignee Contact details
		shipmentDetails.put("ReceiverContactDetails", "Ph:" + order.getToAddress().getPhoneNo());
		
		//Populate OutBoundSortCode, InBoundSortCode and DestinationFacilityCode
		shipmentDetails.put("OutBoundSortCode", res.getOutBoundSortCode());
		shipmentDetails.put("InBoundSortCode", res.getInBoundSortCode());
		
		sb = new StringBuilder();
		sb.append(order.getToAddress().getCountryCode());
		sb.append("-");
		sb.append(res.getDestServiceAreaCode());
		String facilityCode= res.getDestFacilityCode();
		if ( !StringUtil.isEmpty(res.getDestFacilityCode()) )	{
			sb.append("-");
			sb.append(res.getDestFacilityCode());
		}	
		shipmentDetails.put("DestinationFacilityCode", sb.toString());
		
		//Populate feature codes
		List<String> internalServiceCodeList = convertToStringList(res.getInternalServiceCode());
        if(internalServiceCodeList!=null && internalServiceCodeList.contains("C"))	{		
			shipmentDetails.put("DuitableFlag","true");
		} else {					
			shipmentDetails.put("DuitableFlag","false");
		}
        // Added for sorting product feature
		Collections.sort(internalServiceCodeList, new DhlInternalServiceCodeComparator());
		sb = new StringBuilder();
		for(String iSC: internalServiceCodeList){
			if (sb.length() > 0)
				sb.append("-");	
			sb.append(iSC);
		}
		if (internalServiceCodeList.size() == 0	|| internalServiceCodeList.size() == 1) {
			shipmentDetails.put("InternalServiceFlag", "one");
		} else if (internalServiceCodeList.size() == 2) {
			shipmentDetails.put("InternalServiceFlag", "two");
		} else if (internalServiceCodeList.size() == 3) {
			shipmentDetails.put("InternalServiceFlag", "three");
		} else if (internalServiceCodeList.size() == 4) {
			shipmentDetails.put("InternalServiceFlag", "four");
		} else {
			shipmentDetails.put("InternalServiceFlag", "five");
		}
		shipmentDetails.put("InternalServiceCode", sb.toString());
		
		//Populate Date Time Code
		shipmentDetails.put("DeliveryTime", res.getDeliveryTimeCode());
		shipmentDetails.put("CalendarDate", res.getDeliveryDateCode());
		
		shipmentDetails.put("ReferenceNumber", res.getReference());
        shipmentDetails.put("Date", FormattingUtil.getFormattedDate(order.getScheduledShipDate(), FormattingUtil.DATE_FORMAT_WEB));
        shipmentDetails.put("ContentsDescription","Content: " + res.getContentsDescription());
		shipmentDetails.put("DataId", "(" + res.getDhlRoutingDataId() + ")");
				
		String airWayBillNumber = res.getAirwayBillNumber();
		String airWayBillNumberWithSpaces="";
		while(airWayBillNumber.length() > 0){
			int len = airWayBillNumber.length() - 4;
			if(len > 0){
				airWayBillNumberWithSpaces = airWayBillNumber.substring(len) + " " + airWayBillNumberWithSpaces;
				airWayBillNumber = airWayBillNumber.substring(0,len);
			}else{
				airWayBillNumberWithSpaces = airWayBillNumber +  " " + airWayBillNumberWithSpaces;
				airWayBillNumber="";
			}			
		}
		shipmentDetails.put("AirWayBillNumber", airWayBillNumberWithSpaces);
		
		String routingBarCodeText = res.getDhlRoutingCode();
		if (routingBarCodeText != null) {
			routingBarCodeText = routingBarCodeText.toUpperCase();			
		}
		shipmentDetails.put("DHLRoutingBarCodeText",routingBarCodeText);
		shipmentDetails.put("AirWayBillBarCode", res.getAirwayBillNumber());
		String routingBarcode = res.getDhlRoutingDataId() + res.getDhlRoutingCode();		
		shipmentDetails.put("DHLRoutingBarCode", routingBarcode);
		
		//Start:Populating piece specific values in pieceMap which will be different on each page
//		ArrayList <ShipValResponsePiece> pieceList = (ArrayList <ShipValResponsePiece> ) shipmentValidateResponse.getPieces().getPiece();
//		Iterator <ShipValResponsePiece> itrPiece = pieceList.iterator();
		String totalPieces = "0";
		if(res.getPieces() != null){
			totalPieces = "" + res.getPieces().size();
		}		
		shipmentDetails.put("Pieces",totalPieces);
		
		//List hold maps which contains piece details for each page
		ArrayList<HashMap<String,String>> pieceFieldMaps = new ArrayList<HashMap<String,String>>();
		
		String unit = res.getWeightUnit();
		String weightUnit;
		if(unit != null){
			weightUnit = unit.toString();
		} else {
			weightUnit = "";
		}		
		
		//Weight Unit conversion
		if(weightUnit.equalsIgnoreCase("G")) {			
			weightUnit="gm";				
		}else if(weightUnit.equalsIgnoreCase("K")) {			
			weightUnit="Kg";			
		} else {			
			weightUnit="Lbs";
		}
		//Populate values for each piece
		for (DhlShipValidatePiece p:res.getPieces()) {
			HashMap<String, String> pieceMap = new HashMap<String, String>();
			pieceMap.put("PieceNumber", p.getNumber() + "/" + totalPieces);
			pieceMap.put("PieceCounter", p.getNumber().toString());
			pieceMap.put("PieceWeight", p.getWeight().longValue() + " " + weightUnit);
			pieceMap.put("PieceIdentifier", "(" + p.getPieceIdentifier() + ")");
			String licensePlateWithSpaces = getLicensePlateWithSpaces(p.getLicensePlate());
			pieceMap.put("LicensePlateBarCodeText", licensePlateWithSpaces);
			pieceMap.put("LicensePlateBarCode", p.getPieceIdentifier() + p.getLicensePlate());
			pieceFieldMaps.add(pieceMap);
		}
		List<String> serviceList = convertToStringList(res.getSpecialServiceType());
		if (serviceList != null) {		
			for (String specialService:serviceList) {
				if ("D".equalsIgnoreCase(specialService) && "T".equalsIgnoreCase(res.getDutyPaymentType())) {
					shipmentDetails.put("DutyAccountNumber", "" + res.getDutyAccountNumber());
				}
			}
		}
        if(res.getInsuredAmount() != null) {
        	shipmentDetails.put("InsuredAmount", res.getInsuredAmount() + " " + order.getCurrency());
        }

		if (res.getDutyDeclaredValue() != null && res.getDutyDeclaredCurrency() != null) {
			shipmentDetails.put("DeclaredValue", res.getDutyDeclaredValue() + " " + res.getDutyDeclaredCurrency());
			if (res.getDutyTermsOfTrade() != null) {				
				shipmentDetails.put("TermsOfTrade", res.getDutyTermsOfTrade());
			}
		}
		if (res.getBillingAccountNumber() != null) {			
			shipmentDetails.put("BillingAccountNumber", res.getBillingAccountNumber());
		}
		
//		String pdfFileName = "D:\\temp\\" + res.getAirwayBillNumber() + ".pdf";
		generateReport(shipmentDetails, pieceFieldMaps, pdfFileName);
	
	}

	/**
	 * This method modifies license plate in bunch of 
	 * four digits starting from right.
	 * 
	 * @param String
	 *            licensePlateWithoutSpaces to put spaces
	 *            
	 * @return String
	 */ 
	private static String getLicensePlateWithSpaces(String licensePlateWithoutSpaces)	{
		String licensePlateWithSpaces = "";
		if(licensePlateWithoutSpaces == null){
			return licensePlateWithSpaces;
		}
		licensePlateWithoutSpaces = licensePlateWithoutSpaces.toUpperCase();
		int index  = licensePlateWithoutSpaces.indexOf("JD01"); 
		String jd01 = licensePlateWithoutSpaces.substring(0, index + 4);
		licensePlateWithoutSpaces = licensePlateWithoutSpaces.substring(index + 4);
		while(licensePlateWithoutSpaces.length() > 0){
			int len = licensePlateWithoutSpaces.length() - 4;
			if(len > 0){
				licensePlateWithSpaces = licensePlateWithoutSpaces.substring(len) + " " + licensePlateWithSpaces;
				licensePlateWithoutSpaces = licensePlateWithoutSpaces.substring(0,len);
			} else {
				licensePlateWithSpaces = licensePlateWithoutSpaces +  " " + licensePlateWithSpaces;
				licensePlateWithoutSpaces="";
			}
		}
		licensePlateWithSpaces = jd01 +  " " + licensePlateWithSpaces;

		return licensePlateWithSpaces;
	}

	private List<String> convertToStringList(String s) {
		// TODO Auto-generated method stub
		if ( !StringUtil.isEmpty(s) ) {
			StringTokenizer st = new StringTokenizer(s, DhlShipValidateResponse.SEPERATOR);
			List<String> sList = new ArrayList<String>();
			while (st.hasMoreTokens())
				sList.add(st.nextToken());
			
			return sList;
		}
		return (new ArrayList<String>());
	}
	
	private void generateReport(HashMap<String, Object> shipmentDetails,
					ArrayList<HashMap<String, String>> pieceFieldMaps, String pdfFileName) throws DHLException {
		// TODO Auto-generated method stub
		try {
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream(
								"./jasperreports/src/main/java/com/meritconinc/shiplinx/service/impl/jasperreports/DHLLabel.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
        
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(pieceFieldMaps);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, shipmentDetails, ds);

			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName );
			System.out.println("LabelReportController:generateSOPLabel() : Label generated");
		} catch (Exception e) {
			logger.error("Could not generate Shiplinx Invoice Main !!");
			e.printStackTrace();
			throw new DHLException(e.getMessage());
		}
	}


}
