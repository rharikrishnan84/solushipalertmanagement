package com.meritconinc.mmr.action.security;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.soluship.businessfilter.util.BusinessFilterUtil;
 
public class ImageAction extends ActionSupport  implements ServletRequestAware{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932668839940741408L;
	byte[] imageInByte = null;
	String imageId;
	private HttpServletRequest request;    
	private HttpServletResponse response;
 
  
	public String getImageId() {
		return imageId;
	}
 
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
 
	public ImageAction() {
		System.out.println("ImageAction");
	}
 
	public String execute() {
		
		return SUCCESS;
	}
 
	  private Image TransformGrayToTransparency(BufferedImage image)
	  {
	    ImageFilter filter = new RGBImageFilter()
	    {
	      public final int filterRGB(int x, int y, int rgb)
	      {
	        return (rgb << 8) & 0xFF000000;
	      }
	    };

	    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	  }
	  BufferedImage getRenderedImage(Image in)
	    {
	        int w = in.getWidth(null);
	        int h = in.getHeight(null);
	        int type = BufferedImage.TYPE_INT_RGB;
	        BufferedImage out = new BufferedImage(w, h, type);
	        Graphics2D g2 = out.createGraphics();
	        g2.drawImage(in, null,null);
	        g2.dispose();
	        return out;
	    }
	public byte[] getCustomImageInBytes() {
 
		/*Image image =null;
		BufferedImage originalImage=null;
		byte[] b=null;
		b=getImage();
		try {
			if(b!=null){
			originalImage = createImageFromBytes(b);
			image=TransformGrayToTransparency(originalImage);
			originalImage=getRenderedImage(image);
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		imageInByte=getImage();
		return imageInByte;
	}
 
	public String getCustomContentType() {
		return "image/jpg";
	}
 
	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}
 
 
	
	private BufferedImage createImageFromBytes(byte[] imageData) {
	    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    try {
	        return ImageIO.read(bais);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}


	public  byte[] getImage(){
		try{
		 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		   Long businessId=BusinessFilterUtil.setBusinessIdbyUserLevel();
		   if(UserUtil.getMmrUser()!=null){
			   if(!UserUtil.getMmrUser().getUserRole().equalsIgnoreCase(ShiplinxConstants.ROLE_SYSADMIN)){
				   return loadImgForOtherUser();
			   }
		   }
		   if(UserUtil.getMmrUser()==null){
			   businessId=1L;
		   }
		   Business business=businessDAO.getBusiessById(businessId);
		   if(business!=null && business.isNationLevel()){
			   business=businessDAO.getBusiessById(business.getParentBusinessId());
			   
		   }else if( business!=null && business.isBranchLevel() && business.getPartnerId()!=0){

			   business=businessDAO.getBusiessById(business.getPartnerId());
			   
		   }else if(business.isPartnerLevel() && business.getCssVO()==null){
			   business=businessDAO.getBusiessById(business.getParentBusinessId());
		   }
		   
		   
		 
		   
		   Business defBus=businessDAO.getBusiessById(1L);
		   if(getRequest()!=null){
		   	     String img=getRequest().getParameter("id");
		   	  byte b[]=null;
		   	      if(img!=null && img.equals("logoImg")){
		   	    	  if(business.getCssVO()!=null && business.getCssVO().getLogoImgByte()!=null){
		   	    		  b=business.getCssVO().getLogoImgByte();
		   	    	  }else{
		   	    		  b=defBus.getCssVO().getLogoImgByte();
		   	    	  }
		   	      }else if(img!=null && img.equals("back")){
		   	    	 if(business.getCssVO()!=null && business.getCssVO().getBackGroudImgByte()!=null){
		   	    		  b=business.getCssVO().getBackGroudImgByte();
		   	    	  }else{
		   	    		  b=defBus.getCssVO().getBackGroudImgByte();
		   	    	  }
		   	      }
		   		 
		   	      return b;
		   }
		}catch(Exception e){
			
		}
		   return null;
		     }
	   public   byte[] logo=null;
	   public   byte[] backG=null;
	   private byte[] loadImgForOtherUser() {
		// TODO Auto-generated method stub
		   
		   Long businessId=UserUtil.getMmrUser().getBusinessId();
		   BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
		   Business business=businessDAO.getBusiessById(businessId);
		   Business defBus=businessDAO.getBusiessById(1L);
		   if(business!=null && business.isNationLevel()){
			   business=businessDAO.getBusiessById(business.getParentBusinessId());
			   
		   }else if( business!=null && business.isBranchLevel() && business.getPartnerId()!=0){

			   business=businessDAO.getBusiessById(business.getPartnerId());
			   
		   }else if(business.isPartnerLevel() && business.getCssVO()==null){
			   business=businessDAO.getBusiessById(business.getParentBusinessId());
		   }

	 	     String img=getRequest().getParameter("id");
	 	      if(img!=null && img.equals("logoImg")){
	 	    	  if(logo==null){
		 	    	  if(business.getCssVO()!=null && business.getCssVO().getLogoImgByte()!=null){
		 	    		 logo=business.getCssVO().getLogoImgByte();
		 	    	  }else{

		 	    		 logo=defBus.getCssVO().getLogoImgByte();
		 	    	  }
	 	    	  }
	 	    	  return logo;
	 	      }else if(img!=null && img.equals("back")){
	 	    	  if(backG==null){
		 	    	  if(business.getCssVO()!=null && business.getCssVO().getBackGroudImgByte()!=null){
		 	    		backG=business.getCssVO().getBackGroudImgByte();
		 	    	  }else{

		 	    		 backG=defBus.getCssVO().getBackGroudImgByte();
		 	    	  }
	 	    	  }
	 	    	  return backG;
	 	      }
	 	
	 
	 		return null;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}

 
}
