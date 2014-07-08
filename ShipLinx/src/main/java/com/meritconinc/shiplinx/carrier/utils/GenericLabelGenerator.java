package com.meritconinc.shiplinx.carrier.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public abstract class GenericLabelGenerator{
	
	private static Logger logger = Logger.getLogger(GenericLabelGenerator.class);
	protected static Font[] shipping_label_fonts = new Font[24];
	protected static Font[] package_label_fonts = new Font[9];

	static{ 
		//fonts for shipping label
		shipping_label_fonts[0] = new Font(Font.HELVETICA, Font.DEFAULTSIZE-5, Font.NORMAL);
		shipping_label_fonts[1] = new Font(Font.HELVETICA, Font.DEFAULTSIZE-5, Font.ITALIC);
		shipping_label_fonts[2] = new Font(Font.HELVETICA, Font.DEFAULTSIZE-5, Font.BOLD);
		shipping_label_fonts[3] = new Font(Font.HELVETICA, Font.DEFAULTSIZE-2, Font.NORMAL);
		shipping_label_fonts[4] = new Font(Font.HELVETICA, Font.DEFAULTSIZE-2, Font.BOLD);
		shipping_label_fonts[5] = new Font(Font.HELVETICA, Font.DEFAULTSIZE+30, Font.BOLD);
		shipping_label_fonts[6] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
		shipping_label_fonts[7] = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		shipping_label_fonts[8] = new Font(Font.HELVETICA, Font.DEFAULTSIZE+2, Font.BOLD);
		//fedex
		shipping_label_fonts[9] = new Font(Font.HELVETICA, 8, Font.NORMAL);
		shipping_label_fonts[10] = new Font(Font.HELVETICA, 8, Font.BOLD);
		shipping_label_fonts[11] = new Font(Font.HELVETICA, 10, Font.BOLD);
		shipping_label_fonts[12] = new Font(Font.HELVETICA, 14, Font.BOLD);
		shipping_label_fonts[13] = new Font(Font.HELVETICA, 16, Font.NORMAL);
		shipping_label_fonts[14] = new Font(Font.HELVETICA, 5, Font.NORMAL);
		shipping_label_fonts[15] = new Font(Font.HELVETICA, 7, Font.NORMAL);
		shipping_label_fonts[16] = new Font(Font.COURIER, 14, Font.NORMAL);
		shipping_label_fonts[17] = new Font(Font.HELVETICA, 6, Font.BOLD);
		shipping_label_fonts[18] = new Font(Font.HELVETICA, 14, Font.NORMAL);
		shipping_label_fonts[19] = new Font(Font.HELVETICA, 12, Font.NORMAL);
		shipping_label_fonts[20] = new Font(Font.COURIER, 20, Font.BOLD);
		shipping_label_fonts[21] = new Font(Font.HELVETICA, 12, Font.BOLD);
		shipping_label_fonts[22] = new Font(Font.HELVETICA, 30, Font.BOLD);
		shipping_label_fonts[23] = new Font(Font.HELVETICA, 20, Font.BOLD);
		
		//fonts for package label
		package_label_fonts[0] = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
		package_label_fonts[1] = new Font(Font.TIMES_ROMAN, 8, Font.ITALIC);
		package_label_fonts[2] = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
		package_label_fonts[3] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL);
		package_label_fonts[4] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
		package_label_fonts[5] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
		package_label_fonts[6] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL);
		package_label_fonts[7] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
		package_label_fonts[8] = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
		
		
	}

	public GenericLabelGenerator(){}

	public GenericLabelGenerator(int carrierId){
		setCarrierId(carrierId);
	}

	public String labelHeader = null;
	public int carrierId;
//	protected Order order = null;
//	protected OutputStream outputStream = null;

	public abstract void setUp();
	

	public abstract void generateShippingLabel(ShippingOrder o, OutputStream outputStream);

	public abstract void generateShippingLabel(ShippingOrder o, OutputStream outputStream, Document document, PdfWriter writer);
	
	
	public void generateGenericLabel(OutputStream outputStream, ShippingOrder order, Customer customer){
		try{
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream("./jasperreports/CWSWayBill2.jasper");				
			
			if(stream==null) logger.error("Stream is NULL!");
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(stream);
			
			
		    Map parameters = new HashMap();
		   // Franchise f = BusinessRequestSession.getFranchise();
		   // if(f==null)
		  //  	logger.error("Franchise is NULL!");
		   // parameters.put("FRANCHISE", f);
		    parameters.put("Order", order);
	
		    JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(order.getPackages());
		    
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, 
			                                           parameters, 
			                                           ds);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
		catch(Exception e)
		{
			logger.error("Could not generate way bill label for order with id " + order.getId() + " and customer " + customer.getName(), e);
			e.printStackTrace();
			//throw new PdfGenerationException();
		}	
	}

	public void generateGenericLabel(OutputStream outputStream, ShippingOrder order, Customer customer, Document document, PdfWriter writer){
		try{
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream("./jasperreports/CWSWayBill2.jasper");				
			
			if(stream==null) logger.error("Stream is NULL!");
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(stream);
			
			
		    Map parameters = new HashMap();
		   // Franchise f = BusinessRequestSession.getFranchise();
		  //  if(f==null)
		  //  	logger.error("Franchise is NULL!");
		   // parameters.put("FRANCHISE", f);
		    parameters.put("Order", order);
	
		    JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(order.getPackages());
		    
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, 
			                                           parameters, 
			                                           ds);
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
		catch(Exception e)
		{
			logger.error("Could not generate way bill label for order with id " + order.getId() + " and customer " + customer.getName(), e);
			e.printStackTrace();
		//	throw new PdfGenerationException();
		}	
	}


	/**
	 * @param carrierId The carrierId to set.
	 */
	public void setCarrierId(int carrier_id) {
		carrierId = carrier_id;
	}

}
