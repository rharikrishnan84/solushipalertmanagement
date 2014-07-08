package com.meritconinc.shiplinx.carrier.ups.service;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.shiplinx.carrier.utils.GenericLabelGenerator;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class UPSLabelGenerator extends GenericLabelGenerator {
	private static Logger logger = Logger.getLogger(UPSLabelGenerator.class);
	
	private ShippingDAO shippingDAO;
	
	public void setShippingLabelDAO(ShippingDAO dao) {
		this.shippingDAO = dao;
	}
	
	public UPSLabelGenerator(){
		setUp();
	}
	 
	public void setUp() {
		this.labelHeader = "PACKAGE LABEL : UPS";
	}
	
	public void generateShippingLabel(ShippingOrder order, OutputStream outputStream) {
		logger.debug("---generateShippingLabel-----");
		this.generateShippingLabel(order, outputStream, null, null);
		
	}
	

	public void generateShippingLabel(ShippingOrder order, OutputStream outputStream, Document document, PdfWriter writer){
		Rectangle pdfRect = new Rectangle(4*72,6*72);
		Rectangle pdfRect_HTML = new Rectangle(PageSize._11X17);
        boolean isDocNull = false;
        document=null;
        writer=null;
        if(document==null){
        	isDocNull = true;
        	document = new Document(pdfRect, 0, 0, 0, 0);
        }

        logger.debug("---generateShippingLabel---111111--");
		try {
            // step 2: creation of the writer
//            if(writer==null){
            	writer = PdfWriter.getInstance(document, outputStream);
                // step 3: we open the document
                document.open();
//            }
			List<ShippingLabel> shippingLabels = shippingDAO.getLabelsByOrderId(order.getId().longValue());
			
			int page = 1;
			for(ShippingLabel label:shippingLabels) {
				
				if(label.getLabelType().equalsIgnoreCase("GIF")){
					if(page > 1) {
						document.setPageSize(pdfRect);
						document.newPage();
					}	
					Image gif = Image.getInstance(label.getLabel()); 
					logger.debug("HEIGHT, WIDTH " + gif.getHeight() + " " + gif.getWidth()); 
					gif.scalePercent(35.00f, 36.00f); 
					gif.setRotationDegrees(270f);
					//gif.scaleToFit(4*72, 6*72);
	//				png.scaleToFit(4*72,6*72);
					document.add(gif); 
				}
				
				if(label.getLabelType().equalsIgnoreCase("HTML")){					
					if(page > 1) {
						document.setPageSize(pdfRect_HTML);
						document.newPage();
					}	
					HTMLWorker html = new HTMLWorker(document);
					html.parse(new StringReader(new String(label.getLabel())));
				}
				
		
				page++;
				
			}
	        if(isDocNull){
	        	document.close();
	        }			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("UPS label generation error",e);
		}
	}

	public void generateShippingLabel(ShippingOrder order,  Document document) {
		logger.error("document.getPageNumber()" +document.getPageNumber());
		
		try {
			List<ShippingLabel> shippingLabels = shippingDAO.getLabelsByOrderId(order.getId().longValue());
			for(ShippingLabel label:shippingLabels) {
				if(document.getPageNumber() > 1) {
					document.newPage();
				}	
				
				Image gif = Image.getInstance(label.getLabel()); 
				logger.debug("HEIGHT, WIDTH " + gif.getHeight() + " " + gif.getWidth()); 
				gif.scalePercent(35.00f, 36.00f); 
				gif.setRotationDegrees(270f);
				document.add(gif);
			}			
		}catch(Exception e) {
			logger.error("UPS label generation error",e);
		}
	}
	


	
	
}
