package com.meritconinc.shiplinx.carrier.purolatorFreight;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.purolatorFreight.stub.FreightPickupServiceClient;
import com.meritconinc.shiplinx.carrier.purolatorFreight.stub.PurolatorFreightEstimatingClient;
import com.meritconinc.shiplinx.carrier.purolatorFreight.stub.PurolatorFreightShippingClient;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAOImpl;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class PurolatorFreightAPI implements CarrierService {
 private static final Logger log = LogManager.getLogger(PurolatorFreightAPI.class);
 private CarrierServiceDAO carrierServiceDAO;
 private ShippingDAO shippingDAO;
 public static final String LIVE_URL_RATE = "https://webservices.purolator.com/EWS/V1/Estimating/EstimatingService.asmx";
 public static final String TEST_URL_RATE = "https://devwebservices.purolator.com/EWS/V1/Estimating/EstimatingService.asmx";
 public static String URL_RATE = "";
 private Connection connection = null;
 private String hostName = "https://localhost:8443/ShipLinx";
 public PurolatorFreightAPI(){
carrierServiceDAO = (CarrierServiceDAO)MmrBeanLocator.getInstance().findBean("carrierServiceDAO");
 DataSource ds = ((CarrierServiceDAOImpl) (carrierServiceDAO)).getDataSource();
 try {
 connection = ds.getConnection();
 } catch (Throwable ex) {
 log.debug(ex);
 }
 }
 public CarrierServiceDAO getCarrierServiceDAO() {
 return carrierServiceDAO;
 }

 public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
 this.carrierServiceDAO = carrierServiceDAO;
 }

 public ShippingDAO getShippingDAO() {
 return shippingDAO;
 }

 public void setShippingDAO(ShippingDAO shippingDAO) {
 this.shippingDAO = shippingDAO;
 }
 /*public void rateShipmentFreight(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {
 PurolatorEstimatingClient purolatorEstimatingClient=new PurolatorEstimatingClient(order,customerCarrier);
 try {
 purolatorEstimatingClient.getEstimate();
 } catch (RemoteException e) {
 // TODO Auto-generated catch block
e.printStackTrace();
 }

 }*/

/*
 public void createShipment(ShippingOrder shippingOrder,CustomerCarrier customercarrier){
 PurolatorFreightShippingClient purolatorFreightShippingClient=new PurolatorFreightShippingClient(shippingOrder,customercarrier);
 purolatorFreightShippingClient.createShipping();
 }*/

 public List<Rating> rateShipment(ShippingOrder order,
 List<Service> services, long carrierId,
 CustomerCarrier customerCarrier) {

log.debug("In Purolator Freight rating...");

 if (order.getPackageTypeId().getPackageTypeId() != ShiplinxConstants.PACKAGE_TYPE_PALLET) {
 return null;
 }

long start = System.currentTimeMillis();
// if(order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_PALLET)
// return null;

 if(ShiplinxConstants.isTestMode())
URL_RATE = TEST_URL_RATE;
 else
 URL_RATE = LIVE_URL_RATE;

 if(! ShiplinxConstants.CANADA.equalsIgnoreCase(order.getFromAddress().getCountryCode()))
 return null;

 List<Rating> rates = new ArrayList<Rating>();
 // TODO Auto-generated method stub
 PurolatorFreightEstimatingClient purolatorFreightEstimatingClient=new PurolatorFreightEstimatingClient(order,customerCarrier);
try {
 rates = (List<Rating>) purolatorFreightEstimatingClient.getEstimates();
 } catch (Exception e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
 return rates;
 }

 @Override
 public long getCarrierId() {
 // TODO Auto-generated method stub
 return 0;
 }

 @Override
 public Object getShippingOrderStatus(ShippingOrder order) {
 // TODO Auto-generated method stub
 return null;
 }

 private boolean validateScheduledShipDate(Date scheduledShipDate)
{
 boolean boolReturn=false;
 long futureDate=777600000;
 try {
 long longScheduledShipDate = FormattingUtil.DATE_FORMAT_WEB.parse(FormattingUtil.DATE_FORMAT_WEB.format(scheduledShipDate)).getTime();
 // Adding the 9 days in Current date so the customer can create the shipment upto next 10 days
 long longCurrentDate=System.currentTimeMillis()+futureDate;
 log.debug("longScheduledShipDate= "+longScheduledShipDate);
 log.debug("longCurrentDate= "+longCurrentDate);
 long diff=longCurrentDate-longScheduledShipDate;
 log.debug("diff= "+diff);
 if(diff >=0)
 {
 boolReturn=true;
 }else
 {
 boolReturn=false;
 }
 } catch (Exception e) {
 boolReturn=false;
 }
 return boolReturn;
 }

 @Override
 public void shipOrder(ShippingOrder order, Rating rateInfo,
 CustomerCarrier customerCarrier) {
 // TODO Auto-generated method stub
 if(!validateScheduledShipDate(order.getScheduledShipDate()))
 {
 log.info("Purolator allows creation of future dated shipments upto 10 calendar days.Scheduled ship date must be less than 11 calendar days from today.");
 throw new PurolatorException("Purolator allows creation of future dated shipments upto 10 calendar days.Scheduled ship date must be less than 11 calendar days from today.");
 }
 /* List<com.meritconinc.shiplinx.model.Package> packages = order.getPackages();
 for(com.meritconinc.shiplinx.model.Package p:packages){
 float actualWeight = p.getWeight().floatValue();
 //determine which is greater, actual weight or cubed weight and set the cubed weight of the package accordingly

 p.setBilledWeight(actualWeight);

 }*/
 try{
 PurolatorFreightShippingClient purolatorFreightShippingClient=new PurolatorFreightShippingClient(order,customerCarrier);
 purolatorFreightShippingClient.createShipping();
 }catch(Exception e){
 log.error("Can not create the Purolator Freight Shipment"+e);
 throw new PurolatorException(e.getMessage());
 }
 }

 @Override
 public boolean cancelOrder(ShippingOrder order,
 CustomerCarrier customerCarrier) {
 // TODO Auto-generated method stub
 return true;
 }

 @Override
 public String getTrackingURL(ShippingOrder o) {
// TODO Auto-generated method stub
 return null;
 }

 @Override
 public void generateShippingLabel(OutputStream outputStream, long orderId,
 CustomerCarrier customerCarrier) {
 // TODO Auto-generated method stub
 //ShippingOrder order = shippingDAO.getShippingOrder(orderId);
 try {
 InputStream stream = this
 .getClass()
 .getClassLoader()
 .getResourceAsStream(
 "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/purolatorFreight/jasperreports/PurolatorFreight.jasper");
 if (stream == null) {
 log.error("Stream is NULL!");
 }
 JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

 Map<String, Object> parameters = new HashMap<String, Object>();
 //Business business = businessDAO.getBusiessById(order.getBusinessId());
 String logoPath ="/mmr/images/purolatorFreight.png";
 String checked="/mmr/images/checked.png";
 /* String packageType = "", type;
 for (Package p : order.getPackages()) {
 type = p.getType();
 if (type != null && !type.isEmpty()) {
packageType = type.replaceAll("[^\\p{L}\\p{Nd}]", "");
 packageType = packageType.replaceAll("\\d", "");
 p.setType(packageType);
 }
 }*/
 parameters.put("logo", ClassLoader.getSystemResourceAsStream(logoPath));
 parameters.put("checked", ClassLoader.getSystemResourceAsStream(checked));
 parameters.put("orderId", orderId);

 JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getShippingDAO().getShippingOrder(orderId).getPackages());
 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
 JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 } catch (Exception e) {
 log.error("Could not generate label for order with id " + orderId , e);
 throw new ShiplinxException();
 }
 /* try{
 int page = 1;
 PDFRenderer pdfRenderer = new PDFRenderer();
 ArrayList<String> srcList = new ArrayList<String>();
for(byte[] label:raw_labels) {

 String pdfFileName = pdfRenderer.getUniqueTempPDFFileName("label" + order.getId() + page);
 File f = new File(pdfFileName);
 BufferedOutputStream sbos = new BufferedOutputStream(new FileOutputStream(f));
 sbos.write(label);
 sbos.close();
 srcList.add(pdfFileName);
 page++;
 }
 // delete temp files
 pdfRenderer.concatPDF(srcList, outputStream);
 pdfRenderer.deleteFiles(srcList);
 }catch (Exception e) {
 logger.error("Exception" + e);
 }*/

 }

 @Override
 public void requestPickup(Pickup pickup) {
 // TODO Auto-generated method stub
 FreightPickupServiceClient freightPickupServiceClient = new FreightPickupServiceClient();
 freightPickupServiceClient.schedulePickup(pickup);
 System.out.println("Pickup Request");
 }

 @Override
 public boolean cancelPickup(Pickup pickup) {
 // TODO Auto-generated method stub
 boolean retval = false;
 try {
 if(pickup!=null){
 BusinessDAO businessDAO = (BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
 Business business = businessDAO.getBusiessById(pickup.getBusinessId());
 String toAddress = business.getLtlEmail();
 if (toAddress == null || toAddress.length() == 0) {
 log.error("User's email address is not sent, cannot send an email quote!");
 return false;
 }
 // GROUP_EMAIL_ADDRESS_en_CA

 String subject = MessageUtil.getMessage("label.subject.cancel.pickup.notification");

 String body = MessageUtil.getMessage("mail.cancel.pickup.notification.body");

 if (body == null || body.length() == 0) {
 log.error("Cannot find template to cancel pickup notification for business ");
 return false;
 }
 body = new String(
 body.replaceAll(
 "%PickupDate",
 FormattingUtil.getFormattedDate(pickup.getPickupDate(),
 FormattingUtil.DATE_FORMAT_WEB) + ""));
 body = new String(body.replaceAll("%PICKUPCONFIRMATION", pickup.getConfirmationNum()));
 body = new String(body.replaceAll("%PICKUPLOCATION", pickup.getPickupLocation()));
 body = new String(body.replaceAll("%PICKUPREFERENCE", pickup.getPickupReference()));
 if(pickup.getAddress()!=null){
 body = new String(body.replaceAll("%PICKUPABBREVIATION", pickup.getAddress().getAbbreviationName()));
 body = new String(body.replaceAll("%PICKUPADDRESS1", pickup.getAddress().getAddress1()));
 body = new String(body.replaceAll("%PICKUPADDRESS2", pickup.getAddress().getAddress2()));
 body = new String(body.replaceAll("%PICKUPZIP", pickup.getAddress().getPostalCode()));
 body = new String(body.replaceAll("%PICKUPPROVINCE", pickup.getAddress().getProvinceCode()));
 body = new String(body.replaceAll("%PICKUPCOUNTRY", pickup.getAddress().getCountryCode()));
 body = new String(body.replaceAll("%PICKUPCITY", pickup.getAddress().getCity()));
 }
 if(pickup.getCarrier()!=null){
 body = new String(body.replaceAll("%CARRIER", pickup.getCarrier().getName()));
 }
 body = new String(body.replaceAll("%CARRIER", String.valueOf(pickup.getQuantity())));
 String fromAddress = null;
 if (pickup.getAddress() != null && pickup.getAddress().getEmailAddress() != null) {
 fromAddress = pickup.getAddress().getEmailAddress();
 } else {
 fromAddress = business.getAddress().getEmailAddress();
 }
 List<String> bccAddress = new ArrayList<String>();
 bccAddress.add(business.getCancelPurolatorFreightEmail());
 retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
 business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
 business.getAddress().getEmailAddress(), bccAddress, subject, body, null, true);
 }
 } catch (MessagingException e) {
 log.error("Error sending email - Messaging Exception: ", e);
 retval = false;
 } catch (Exception e) {
 log.error("Error sending email - Exception: ", e);
 retval = false;
 }
 return retval;
 }

 @Override
 public List<CarrierErrorMessage> getErrorMessages() {
 // TODO Auto-generated method stub
 return null;
 }

 @Override
 public void uploadRates(Service service, long customerId, long busId,
 boolean isOverwrite) throws Exception {
 // TODO Auto-generated method stub

 }
}
