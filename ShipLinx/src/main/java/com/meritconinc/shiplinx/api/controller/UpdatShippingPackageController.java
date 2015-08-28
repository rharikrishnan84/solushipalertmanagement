package com.meritconinc.shiplinx.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware; 
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.api.base.SolushipRestServerResource;
import com.meritconinc.shiplinx.api.model.ODBCShipPackage;
import com.meritconinc.shiplinx.api.model.ShipOrderList;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.ShippingOrder;


public class UpdatShippingPackageController extends SolushipRestServerResource{
	
	
	private static final Logger log = LogManager
			.getLogger(UpdateShippingOrderController.class);
	ShippingDAO shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance()
			.findBean("shippingDAO");	
	@Post
	public String updateShippingOrder(Representation entity)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		ObjectMapper mapper = new ObjectMapper();
		List<ODBCShipPackage> odcbPackageList = new ArrayList<ODBCShipPackage>();
		ShipOrderList shippingOrderList = mapper.readValue(entity.getStream(),
				ShipOrderList.class);
		odcbPackageList = shippingOrderList.getPackages();
		for (ODBCShipPackage tempPackage : odcbPackageList) {
			ShippingOrder order = null ;
			Long orderId = Long.parseLong(tempPackage.getSoluShipOrderId());
			if(orderId != null){
				order = shippingDAO.getShippingOrder(orderId);
			}
			float totatlWeight = order.getQuotedWeight();
			int quantity = order.getQuantity();
			List<String> ref2List = new  ArrayList<String>();
			List<String> ref2Code = new  ArrayList<String>();
			String ref2 = order.getReferenceTwo(); 
			String refCode = order.getReferenceCode();
			String[] ref2Array = null;
			String[] refCodeArray = null;
			if(ref2.contains(",")){
				ref2Array = ref2.split(","); 
			}
			if(refCode.contains(",")){
				refCodeArray = refCode.split(","); 
			}
			if( ref2Array != null && ref2Array.length>0){
				for(String s:ref2Array){
					if(s != null){
						ref2List.add(s);
					}
				}
			}else{
				ref2List.add(ref2); 
			}
			if( refCodeArray != null && refCodeArray.length>0){
				for(String s:refCodeArray){
					if(s != null){
					ref2Code.add(s);
					}
				}
			}else{
				ref2Code.add(refCode); 
			}
			Package convertPackage = convertPackage(tempPackage);
			if(tempPackage.getType().equalsIgnoreCase("package")){
			this.shippingDAO.addPackage(convertPackage);
			totatlWeight +=convertPackage.getWeight().floatValue();
			if(!ref2List.contains(convertPackage.getTrackingNumber())){
				ref2List.add(convertPackage.getTrackingNumber());
			}
			if(!ref2Code.contains(tempPackage.getShipId())){
				ref2Code.add(tempPackage.getShipId());
			}
			
			quantity++;
			}else if (tempPackage.getType().equalsIgnoreCase("pallet")){
					List<Package> packages =shippingDAO.getShippingPackages(orderId);
					if(packages.size() == 1){
						for(Package pack:packages){
							BigDecimal oldWeight= pack.getWeight();
							BigDecimal newWeight= convertPackage.getWeight();
							BigDecimal totalWeight= oldWeight.add(newWeight);
							convertPackage.setWeight(totalWeight);
							long oldQuantity =  getPackageQuantity(pack.getDescription());
							long newQuantity =  getPackageQuantity(convertPackage.getDescription());
							newQuantity += oldQuantity;
							convertPackage.setDescription("STC "+newQuantity+" Units" );
							convertPackage.setPackageId(pack.getPackageId());
							if(!ref2List.contains(convertPackage.getTrackingNumber())){
								ref2List.add(convertPackage.getTrackingNumber());
							}
							if(!ref2Code.contains(tempPackage.getShipId())){
								ref2Code.add(tempPackage.getShipId());
							}
							if(!pack.getTrackingNumber().equalsIgnoreCase(convertPackage.getTrackingNumber())){
								String temp = pack.getTrackingNumber()+"," + convertPackage.getTrackingNumber();
								convertPackage.setTrackingNumber(temp);
							}
							totatlWeight +=newWeight.floatValue();
							
							shippingDAO.updatePackage(convertPackage);
					}
				}
			}
			 StringBuilder strRef2 = new StringBuilder();
			 StringBuilder strRefCode = new StringBuilder();
			 order.setQuotedWeight(totatlWeight);
			 order.setQuantity(quantity);
			 if(ref2List.size() >0){
				
					for(int i = 0; i< ref2List.size(); i++ ){
						if(i == 0){
							strRef2.append(ref2List.get(i));
							strRef2.append(",");
						}else if (i == ref2List.size() - 1){
							strRef2.append(ref2List.get(i));
						}else if(i == 1){
							strRef2.append(ref2List.get(i));
							strRef2.append(",");
						}else{
							strRef2.append(ref2List.get(i));
							strRef2.append(",");
						}
					} 
			 }
			 if(ref2Code.size() >0){
					
					for(int i = 0; i< ref2Code.size(); i++ ){
						if(i == 0){
							strRefCode.append(ref2Code.get(i));
							strRefCode.append(",");
						}else if (i == ref2Code.size() - 1){
							strRefCode.append(ref2Code.get(i));
						}else if(i == 1){
							strRefCode.append(ref2Code.get(i));
							strRefCode.append(",");
						}else{
							strRefCode.append(ref2Code.get(i));
							strRefCode.append(",");
						}
					} 
			 }
			 order.setReferenceTwo(strRef2.toString());
			 order.setReferenceCode(strRefCode.toString());
			 shippingDAO.updateShippingOrderExtended(order);
			log.debug("Order ID: " + tempPackage.getSoluShipOrderId()
					+ "Packages Updated Successfully");
		}
		return "Packages Updated Successfully";
	}
	
	public Long getPackageQuantity(String temp){
		String[] temp1 = temp.split(" ");
		Long quantity = Long.parseLong(temp1[1]);
		return quantity;
	}
	public Package convertPackage(ODBCShipPackage odbcPackage){
		Long orderId = Long
				.parseLong(odbcPackage.getSoluShipOrderId());
		Package newPackage = new Package();
		if(odbcPackage.getCodAmount() != null && !odbcPackage.getCodAmount().equals("null")){
			newPackage.setCodAmount(new BigDecimal(odbcPackage.getCodAmount()));
		}
		if(odbcPackage.getDescription() != null && !odbcPackage.getDescription().equals("null")){
			newPackage.setDescription(odbcPackage.getDescription());
		}
		if(odbcPackage.getFreightClass() != null && !odbcPackage.getFreightClass().equals("null")){
			newPackage.setFreightClass(odbcPackage.getFreightClass());
		}
		if(odbcPackage.getHeight() != null && !odbcPackage.getHeight().equals("null")){
			newPackage.setHeight(new BigDecimal(odbcPackage.getHeight()));
		}
		if(odbcPackage.getLength() != null && !odbcPackage.getLength().equals("null")){
			newPackage.setLength(new BigDecimal(odbcPackage.getLength()));
		}
		if(odbcPackage.getCodAmount() != null && !odbcPackage.getCodAmount().equals("null")){
			newPackage.setCodAmount(new BigDecimal(odbcPackage.getCodAmount()));
		}
		if(odbcPackage.getWidth() != null && !odbcPackage.getWidth().equals("null")){
			newPackage.setWidth(new BigDecimal(odbcPackage.getWidth()));
		}
		if(odbcPackage.getWeight() != null && !odbcPackage.getWeight().equals("null")){
			newPackage.setWeight(new BigDecimal(odbcPackage.getWeight()));
		}
		if(odbcPackage.getWeightUOM() != null && !odbcPackage.getWeightUOM().equals("null")){
			newPackage.setWeightUOM(odbcPackage.getWeightUOM());
		}
		if(odbcPackage.getInsuranceAmount() != null && !odbcPackage.getInsuranceAmount().equals("null")){
			newPackage.setInsuranceAmount(new BigDecimal(odbcPackage.getInsuranceAmount()));
		}
		if(odbcPackage.getTrackingNumber() != null && !odbcPackage.getTrackingNumber().equals("null")){
			newPackage.setTrackingNumber(odbcPackage.getTrackingNumber());
		}
		if(odbcPackage.getType() != null && !odbcPackage.getType().equals("null")){
			newPackage.setType(odbcPackage.getType());
		}
		if(orderId != null){
			newPackage.setOrderId(orderId);
		}
		return newPackage;
		
	}
}
