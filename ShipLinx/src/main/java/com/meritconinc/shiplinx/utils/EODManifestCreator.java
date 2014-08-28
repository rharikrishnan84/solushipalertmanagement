package com.meritconinc.shiplinx.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.TableModel;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAOImpl;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.opensymphony.xwork2.ActionContext;

public class EODManifestCreator implements BinaryExportView {
  private static final Logger logger = Logger.getLogger(EODManifestCreator.class);
  private ShippingDAO shippingDAO;
  private CarrierServiceDAO carrierServiceDAO;
  CustomerCarrier customerCarrier = null;
  ShippingOrder order = null;
  // static String fromDate;
  // static String toDate;
  private Connection connection = null;

  public EODManifestCreator() {
    shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
    carrierServiceDAO = (CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
        "carrierServiceDAO");
    DataSource ds = ((CarrierServiceDAOImpl) (carrierServiceDAO)).getDataSource();
    try {
      connection = ds.getConnection();
    } catch (Throwable ex) {
      logger.debug(ex);
    }
  }

  /*
   * public EODManifestCreator(String fromDate,String toDate){ this.fromDate=fromDate;
   * this.toDate=toDate; }
   */

  public void generateEManifestFile() {
    List<ShippingOrder> shippingOrder = shippingDAO.getShippingOrderByCurrentShipDate();
    FTPClient client = new FTPClient();
    FileInputStream txtFileInputStream = null;
    FileInputStream trgFileInputStream = null;
    try {
      /*
       * client.connect("198.73.126.254"); client.login("smackay", "zxc67*^");
       */
      DateFormat currentDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
      Date currentDate = new Date();
      logger.debug("Current Date FormaT" + currentDateFormat.format(currentDate));
      String tempDirectory = System.getProperty("java.io.tmpdir");
      logger.debug("tempDirectory" + tempDirectory);
      String txtFileName = "MANIFEST_" + currentDateFormat.format(currentDate) + ".TXT";
      String trgFileName = "MANIFEST_" + currentDateFormat.format(currentDate) + ".TRG";
      String txtFilePath = tempDirectory +File.separatorChar+ "MANIFEST_" + currentDateFormat.format(currentDate)
          + ".TXT";
      String trgFilePath = tempDirectory +File.separatorChar+ "MANIFEST_" + currentDateFormat.format(currentDate)
          + ".TRG";
      logger.debug("File Path " + txtFilePath);
      File txtFile = new File(txtFilePath);
      File trgFile = new File(trgFilePath);

      if (!txtFile.exists()) {
        txtFile.createNewFile();
        trgFile.createNewFile();
      }
      FileWriter txtFileWriter = new FileWriter(txtFile.getAbsoluteFile());
      FileWriter trgFileWriter = new FileWriter(trgFile.getAbsoluteFile());
      BufferedWriter txtBufferWritter = new BufferedWriter(txtFileWriter);
      BufferedWriter trgBufferWritter = new BufferedWriter(trgFileWriter);
      StringBuilder content = new StringBuilder();
      // Split Year ,Month and Date
      for (ShippingOrder order : shippingOrder) {
    	  if(order.getStatusId() != ShiplinxConstants.STATUS_CANCELLED){
    	  customerCarrier = carrierServiceDAO.getCutomerCarrierDefaultAccount(order.getCarrierId(),
    			  order.getCustomerId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        String scheduledShipDate = String.valueOf(order.getScheduledShipDate());
        Date date = dateFormat.parse(scheduledShipDate);
        String shippingYear = year.format(date);
        String shippingMonth = month.format(date);
        String shippingDay = day.format(date);
        long areaCode = Long.parseLong(order.getFromAddress().getPhoneNo()) % 1000;
        String manifest = removeNull(customerCarrier.getAccountNumber1()) + shippingDay
            + shippingMonth + shippingYear;
        long chequeReturnAmount = (long) (order.getCODValue() * 100);
        content.append(StringUtils.rightPad("MDQD", 4).substring(0, 4));
        content.append(StringUtils.leftPad(removeNull(removeNull(customerCarrier.getProperty4())),
            12).substring(0, 12));
        content.append(StringUtils.rightPad("", 25).substring(0, 25));
        content.append(StringUtils.rightPad(order.getFromAddress().getAbbreviationName(), 25)
            .substring(0, 25));
        content.append(StringUtils.leftPad("", 5).substring(0, 5)
            + StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.rightPad(removeNull(order.getFromAddress().getAddress1()), 30)
            .substring(0, 30));
        content.append(StringUtils.rightPad(removeNull(order.getFromAddress().getCity()), 30)
            .substring(0, 30));
        content.append(StringUtils
            .rightPad(removeNull(order.getFromAddress().getProvinceCode()), 2).substring(0, 2));
        content.append(StringUtils.rightPad(removeNull(order.getFromAddress().getCountryCode()), 2)
            .substring(0, 2));
        content.append(StringUtils.rightPad(removeNull(order.getFromAddress().getPostalCode()), 9)
            .substring(0, 9));
        content.append(StringUtils.leftPad(removeNull(order.getMasterTrackingNum()), 20).substring(
            0, 20));
        if ("Soluship Acct".equalsIgnoreCase(order.getBillToType())) {
          content.append(StringUtils.rightPad("P", 1).substring(0, 1));
        } else if ("Collect".equalsIgnoreCase(order.getBillToType())) {
          content.append(StringUtils.rightPad("C", 1).substring(0, 1));
        } else {
          content.append(StringUtils.rightPad("T", 1).substring(0, 1));
        }
        content.append(StringUtils.rightPad(removeNull(order.getReferenceCode()), 20).substring(0,
            20));
        content.append(StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.leftPad(shippingYear, 4).substring(0, 4));
        content.append(StringUtils.leftPad(shippingMonth, 2).substring(0, 2));
        content.append(StringUtils.leftPad(shippingDay, 2).substring(0, 2));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getAbbreviationName()),
            25).substring(0, 25));
        content.append(StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getAddress1()), 30)
            .substring(0, 30));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getCity()), 30)
            .substring(0, 30));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getProvinceCode()), 2)
            .substring(0, 2));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getCountryCode()), 2)
            .substring(0, 2));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getPostalCode()), 9)
            .substring(0, 9));
        content.append(StringUtils.rightPad("", 25).substring(0, 25));
        content.append(StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.leftPad("", 5).substring(0, 5));
        content.append(StringUtils.rightPad("", 30).substring(0, 30));
        content.append(StringUtils.rightPad("", 30).substring(0, 30));
        if (order.getService() != null) {
          if (order.getService().getCode() != null
              && "GND".equalsIgnoreCase(order.getService().getCode())) {
            content.append(StringUtils.rightPad("GRD", 3).substring(0, 3));
          } else {
            content.append(StringUtils.rightPad(removeNull(order.getService().getCode()), 3)
                .substring(0, 3));
          }
        } else {
          content.append(StringUtils.rightPad(removeNull(null), 3).substring(0, 3));
        }
        content.append(StringUtils.leftPad(String.valueOf(order.getQuantity()), 3).substring(0, 3));
        if (order.getQuotedWeight() > 0) {
          content.append(StringUtils.leftPad(String.valueOf((int) (order.getQuotedWeight() * 100)),
              7).substring(0, 7));
        } else {
          content.append(StringUtils.leftPad("", 7).substring(0, 7));
        }
        content.append(StringUtils.rightPad("L", 1).substring(0, 1));
        content.append(StringUtils.leftPad("", 7).substring(0, 7));
        content.append(StringUtils.rightPad("", 1).substring(0, 1));
        if (order.getDangerousGoods() > 0) {
          content.append(StringUtils.rightPad("Y", 1).substring(0, 1));
        } else {
          content.append(StringUtils.rightPad("", 1).substring(0, 1));
        }
        content.append(StringUtils.rightPad("", 1).substring(0, 1));
        content.append(StringUtils.rightPad("", 1).substring(0, 1));
        if (order.getCODValue() == 0) {
          content.append(StringUtils.rightPad("", 1).substring(0, 1));
          content.append(StringUtils.leftPad("", 7).substring(0, 7));
        } else {
          content.append(StringUtils.rightPad("Y", 1).substring(0, 1));
          content.append(StringUtils.leftPad(String.valueOf((int) (order.getCODValue() * 100)), 7)
              .substring(0, 7));
        }
        content.append(StringUtils.rightPad(removeNull(order.getFromInstructions()), 30).substring(
            0, 30));
        content.append(StringUtils.rightPad(removeNull(order.getToInstructions()), 30).substring(0,
            30));
        content.append(StringUtils.rightPad(removeNull(order.getToAddress().getAddress2()), 30)
            .substring(0, 30));
        content.append(StringUtils.leftPad("", 7).substring(0, 7));
        content.append(StringUtils.rightPad(removeNull(order.getCODPin()), 10).substring(0, 10));
        content.append(StringUtils.rightPad("N", 1).substring(0, 1));
        content.append(StringUtils.rightPad(manifest, 15).substring(0, 15));
        if (order.isAppointmentDelivery() == true) {
          content.append(StringUtils.rightPad("Y", 1).substring(0, 1));
        } else {
          content.append(StringUtils.rightPad("N", 1).substring(0, 1));
        }
        content.append(StringUtils.leftPad("", 6).substring(0, 6));
        content.append(StringUtils.leftPad("", 4).substring(0, 4));
        content
            .append(StringUtils.leftPad(removeNull(String.valueOf(areaCode)), 3).substring(0, 3));
        content.append(StringUtils.leftPad(removeNull(order.getFromAddress().getPhoneNo()), 7)
            .substring(0, 7));
        content.append(StringUtils.rightPad("", 30).substring(0, 30));
        if (chequeReturnAmount > 0) {
          content.append(StringUtils.leftPad(removeNull(String.valueOf(chequeReturnAmount)), 7)
              .substring(0, 7));
        } else {
          content.append(StringUtils.leftPad("", 7).substring(0, 7));
        }
        content.append(StringUtils.leftPad("", 6).substring(0, 6));
        content.append(StringUtils.leftPad("", 1).substring(0, 1));
        content.append(StringUtils.leftPad("", 1).substring(0, 1));
        content.append(StringUtils.rightPad("", 20).substring(0, 20));
        content.append(StringUtils.rightPad("", 8).substring(0, 8));
        content.append(StringUtils.rightPad(removeNull(order.getReferenceTwo()), 20).substring(0,
            20));
        content.append(StringUtils.rightPad("", 2).substring(0, 2));
        content.append(StringUtils.rightPad("", 9).substring(0, 9));
        content.append(StringUtils.rightPad(removeNull(customerCarrier.getAccountNumber2()), 6)
            .substring(0, 6));
        content.append(StringUtils.rightPad("", 107).substring(0, 107) + "\n");
    	  }
      }

      // bufferWritter.append(content.toString());
      txtBufferWritter.write(content.toString());
      trgBufferWritter.write(content.toString());
      txtBufferWritter.close();
      trgBufferWritter.close();
      String path = customerCarrier.getProperty3();
      String[] pathSplit = path.split("/");
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < pathSplit.length; i++) {
        if (i > 0) {
          stringBuilder.append(pathSplit[i] + "/");
        }
      }
      client.connect(pathSplit[0]);
      client.login(customerCarrier.getProperty1(), customerCarrier.getProperty2());
      txtFileInputStream = new FileInputStream(txtFile);
      trgFileInputStream = new FileInputStream(trgFile);
      if (stringBuilder != null && stringBuilder.length() > 0) {
        txtFileName = stringBuilder + txtFileName;
        trgFileName = stringBuilder + trgFileName;
      }
      logger.debug(txtFileName);
      client.storeFile(txtFileName, txtFileInputStream);
      client.storeFile(trgFileName, trgFileInputStream);
      client.logout();
    } catch (Exception e) {
      logger.error("Could not generate EOD for order ", e);
      throw new ShiplinxException();
    } finally {

      try {

        if (txtFileInputStream != null || trgFileInputStream != null) {

          txtFileInputStream.close();
          txtFileInputStream.close();

        }
        client.disconnect();

      } catch (IOException e) {
        e.printStackTrace();

      }

    }

  }

  private String removeNull(String text) {
    if (null == text) {
      return "";
    }
    return text;
  }

  @Override
  public String getMimeType() {
    return "application/pdf";
  }

  @Override
  public void setParameters(TableModel tableModel, boolean arg1, boolean arg2, boolean arg3) {
  }

  public void doExport(OutputStream out) throws IOException, JspException {
    Map session = ActionContext.getContext().getSession();
    if (session.get("CARRIER_ID") != null && session.get("CARRIER_ID").toString().equals("80")) {
      String fromDate = session.get("MIDLND_SHIPPING_ORDER_FROM_DATE").toString();
      String toDate = session.get("MIDLND_SHIPPING_ORDER_TO_DATE").toString();
      logger.debug("MIDLND_SHIPPING_ORDER_DATE");
      logger.debug(fromDate);
      logger.debug(toDate);
      User user = UserUtil.getMmrUser();
      long customerId = user.getCustomerId();
      logger.debug("EOD Customer");
      logger.debug(customerId);
      // Connection connection=null;
      InputStream stream = this
          .getClass()
          .getClassLoader()
          .getResourceAsStream(
              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/Midland_Courier_Manifest.jasper");
      if (stream == null) {
        logger.error("Manifest Stream is NULL!");
      }
      try {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fromDate", fromDate);
        parameters.put("toDate", toDate);
        parameters.put("customerId", customerId);
        // connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiplinx",
        // "shiplinx","shiplinx");
        JasperPrint jasperPrint = JasperFillManager
            .fillReport(jasperReport, parameters, connection);
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
      } catch (JRException e) {
        e.printStackTrace();
      }
    } else {
      if (session.get("file") != null) {
        String manifestFile = session.get("file").toString();
        // URL url = new URL(manifestFile);
        InputStream in = new FileInputStream(new File(manifestFile));

        OutputStream fos = out;
        System.out.println("reading file...");
        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from
                                       // connection
        while ((length = in.read(buffer)) > -1) {
          fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("file was downloaded");
      }
    }
    session.remove(session);
  }
  public void midlandEODPdf(String midlandEODFileName) throws IOException, JspException {
	  	    Map session = ActionContext.getContext().getSession();
	  	    if (session.get("CARRIER_ID") != null && session.get("CARRIER_ID").toString().equals("80")) {
	  	      String fromDate = session.get("MIDLND_SHIPPING_ORDER_FROM_DATE").toString();
	  	      String toDate = session.get("MIDLND_SHIPPING_ORDER_TO_DATE").toString();
	  	      logger.debug("MIDLND_SHIPPING_ORDER_DATE");
	  	      logger.debug(fromDate);
	  	      logger.debug(toDate);
	  	      User user = UserUtil.getMmrUser();
	  	      long customerId = user.getCustomerId();
	  	      logger.debug("EOD Customer");
	  	      logger.debug(customerId);
	  	      // Connection connection=null;
	  	      InputStream stream = this
	  	          .getClass()
	  	          .getClassLoader()
	  	          .getResourceAsStream(
	  	              "./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/Midland_Courier_Manifest.jasper");
	  	      if (stream == null) {
	  	        logger.error("Manifest Stream is NULL!");
	  	      }
	  	      try {
	  	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
	  	        Map<String, Object> parameters = new HashMap<String, Object>();
	  	        parameters.put("fromDate", fromDate);
	  	        parameters.put("toDate", toDate);
	          parameters.put("customerId", customerId);
	  	        // connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiplinx",
	          // "shiplinx","shiplinx");	
	  	        logger.debug("EOD");
	  	        logger.debug(midlandEODFileName);
	          JasperPrint jasperPrint = JasperFillManager
	  	            .fillReport(jasperReport, parameters, connection);
	  	        JasperExportManager.exportReportToPdfFile(jasperPrint, midlandEODFileName);
	  	      } catch (JRException e) {
	  	        e.printStackTrace();
	  	      }
	  	    } 
	  	    session.remove(session);
	  	  }
}
