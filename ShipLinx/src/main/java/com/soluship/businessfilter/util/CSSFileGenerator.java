package com.soluship.businessfilter.util;
import java.io.BufferedWriter;import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meritconinc.shiplinx.model.CSSVO;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;
 

public class CSSFileGenerator {
    public  long businessId;
    public   String fileName;
     
	
	public   void GenerateCSS(File cssFile,Long busId,CSSVO cssVo){
		businessId=busId;
		fileName=cssFile.getName();
		if(getExtention(cssFile).equalsIgnoreCase("css")){
			try{
				 
		 CSSParser cp=new CSSParser();
		List<CSSClass> cssClassList=cp.getAllclassofCSS(cssFile.getAbsolutePath());
	    cssClassList=modifyCSS(cssClassList,cssVo);
	    writetoFile(cssClassList);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		//System.out.println(addCssForBusiness(new File("WebContent/mmr/styles/")).size());
		}
	}

	
	public   List<CSSClass> getCSSList(File cssFile ,CSSVO cssVo){
	 
	 fileName="laptop.css";
		List<CSSClass> cssClassList=null;
		 
			try{
				 
		 CSSParser cp=new CSSParser();
		 cssClassList=cp.getAllclassofCSS(cssVo.getCssText());
	    cssClassList=modifyCSS(cssClassList,cssVo);
	    //writetoFile(cssClassList);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		//System.out.println(addCssForBusiness(new File("WebContent/mmr/styles/")).size());
		 
		return cssClassList;
	}

	private   void writetoFile(List<CSSClass> cssClassList) {
		// TODO Auto-generated method stub
		
		
		try {
			 
			String content =getCssAsString(cssClassList);
			
			
			File file = new File("/home/system9/git/cssparsing/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/soluship/mmr/bus_"+businessId+"/"+fileName);
            File folder=new File("/home/system9/git/cssparsing/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/soluship/mmr/bus_"+businessId+"/");
			// if file doesnt exists, then create it
			if(!folder.isDirectory()){
				folder.mkdir();
			}
			
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public   String getCSSText(List<CSSClass> cssClassList) {
		// TODO Auto-generated method stub
			 if(cssClassList!=null && cssClassList.size()>0){
			String content =getCssAsString(cssClassList);
			
			 return content;
			 }
			 return null;
	}

	private   String getCssAsString(List<CSSClass> cssClassList) {
		// TODO Auto-generated method stub
		
		
		String	selector="";
		if(cssClassList!=null && cssClassList.size()>0){
		for(CSSClass c:cssClassList){
			String cls=c.getSelector();
			cls=cls.substring(1, cls.length()-1);
	 	selector+=cls+"{";
	 	String css = "";
			 Set setOfKeys = c.getPropertyMap().keySet();
			 Iterator iterator = setOfKeys.iterator();
			 while (iterator.hasNext()) {
				 String key = (String) iterator.next();
				 css+=key+":";
				 String value = (String)c.getPropertyMap().get(key);
				 css+=value+";";
				  }
			 css+="}\n";
			 selector+=css;
			
			 }
		
			 System.out.println(selector);
		 
		}
		return selector;
		 
	}

	private   List<CSSClass> modifyCSS(List<CSSClass> cssClassList,CSSVO cssVo) {
		// TODO Auto-generated method stub
		 
		System.out.println("\nBefore Updation--------\n"  );
		 print(cssClassList);
		for(CSSClass css:cssClassList){
			 if(css.getSelector()!=null && css.getSelector().contains(".form_buttons")|| css.getSelector().contains(".search_body input[type='submit']")){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")){
						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
						
					}
			 }
			 if(css.getSelector()!=null && css.getSelector().contains("#srchusr_res span, #results span")){
				 if(css.getPropertyMap().get("color")!=null
						 && css.getPropertyMap().get("color").equalsIgnoreCase("#990000")){
						css.getPropertyMap().put("color", cssVo.getButtonColor());
						
					}
			 }
			 
			 
			 if(css.getSelector()!=null && css.getSelector().contains(".naviinner")){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000")){
						css.getPropertyMap().put("background-color", cssVo.getMenuColor());
						
					}
			 }
			 if(css.getSelector()!=null && css.getSelector().contains(".navigation ul li a:hover") ||
					 css.getSelector().contains(".navigation2 ul li a:hover")){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")){
						css.getPropertyMap().put("background-color", cssVo.getMenuHoverColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null && css.getSelector().contains(".wrapper_navi ul li a:hover") ||
					 css.getSelector().contains(".navigation2 ul li a:hover")){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#CCC")){
						css.getPropertyMap().put("background-color", cssVo.getMenuHoverColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null && (css.getSelector().contains(".cont_hdr_title") ||
					 css.getSelector().contains(".navigation2 ul li a:hover"))){
				 if(css.getPropertyMap().get("background-color")!=null
						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
						css.getPropertyMap().put("background-color", cssVo.getBarFirstColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null &&( css.getSelector().contains(".cont_hdrtitlelarge") ||
					 css.getSelector().contains(".navigation2 ul li a:hover"))){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")){
						css.getPropertyMap().put("background-color", cssVo.getBarSecondColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null &&( css.getSelector().contains(".content_header") ||
					 css.getSelector().contains(".navigation2 ul li a:hover"))){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000")){
						css.getPropertyMap().put("background-color", cssVo.getBarSecondColor());
						
					}
			 }
			 if(css.getSelector()!=null &&( css.getSelector().contains(".footer") ||
					 css.getSelector().contains(".footer_body"))){
				 if(css.getPropertyMap().get("background-color")!=null
						 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000")){
						css.getPropertyMap().put("background-color", cssVo.getFooterColor());
						
					}
			 }
			 if(css.getSelector()!=null && css.getSelector().contains("#actionmenu li")){
				 if(css.getPropertyMap().get("background")!=null
						 &&( css.getPropertyMap().get("background").equalsIgnoreCase("#990000")|| 
						 css.getPropertyMap().get("background").equalsIgnoreCase("#900"))){
						css.getPropertyMap().put("background", cssVo.getButtonColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null && css.getSelector().contains(".top_line")){
				 if(css.getPropertyMap().get("background-color")!=null
						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#900"))){
						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
						
					}
			 }
			 if(css.getSelector()!=null && css.getSelector().contains("#srchusr_res span,#results span")){
				 if(css.getPropertyMap().get("color")!=null
						 && (css.getPropertyMap().get("color").equalsIgnoreCase("#990000")|| 
						 css.getPropertyMap().get("color").equalsIgnoreCase("#900"))){
						css.getPropertyMap().put("color", cssVo.getButtonColor());
						
					}
			 }
			 
			
			 if(css.getSelector()!=null && css.getSelector().contains(".menu_button")){
				 if(css.getPropertyMap().get("background")!=null
						 && (css.getPropertyMap().get("background").equalsIgnoreCase("#990000")|| 
						 css.getPropertyMap().get("background").equalsIgnoreCase("#990000"))){
						css.getPropertyMap().put("background", cssVo.getMenuHoverColor());
						
					}
			 }
			 
			 /*selva start css*/
			 if(css.getSelector()!=null && css.getSelector().contains(".footer_inner")){
				 if(css.getPropertyMap().get("color")!=null
						 && (css.getPropertyMap().get("color").equalsIgnoreCase("#FFF")|| 
						 css.getPropertyMap().get("color").contains("#fff"))){
						css.getPropertyMap().put("color", cssVo.getFooterFontColor());
						
					}
			 }
			  /*selva end css*/
			 /*vivek*/
			 if(css.getSelector()!=null && css.getSelector().contains(".arrowPackage")){
				 if(css.getPropertyMap().get("color")!=null
						 && (css.getPropertyMap().get("color").equalsIgnoreCase("#990000")|| 
						 css.getPropertyMap().get("color").equalsIgnoreCase("#990000"))){
						css.getPropertyMap().put("color", cssVo.getArrowColor());
						
					}
			 }
			 
			 if(css.getSelector()!=null && css.getSelector().contains(".navi4 ul li") ||
					 					 css.getSelector().contains(".navi4 ul li")){
					 				 if(css.getPropertyMap().get("background-color")!=null
					 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000")||
					 								 css.getPropertyMap().get("background-color").equalsIgnoreCase("#000") )){
					 						css.getPropertyMap().put("background-color", cssVo.getBarSecondColor());
					 						
					 					}
					 			 }
			 
			//vijay code
			 			 if(css.getSelector()!=null && css.getSelector().contains(".package1")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }	
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".package2")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }	
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".package3")){
			 				 if(css.getPropertyMap().get("color")!=null
			 						 && (css.getPropertyMap().get("color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".back_shipment_list")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".cancel_shipment")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".saveCharge")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".deleteCharge")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains(".addCharge")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains("#confirmCancelBtn")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 if(css.getSelector()!=null && css.getSelector().contains("#confirmBtn")){
			 				 if(css.getPropertyMap().get("background-color")!=null
			 						 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000")|| 
			 						 css.getPropertyMap().get("background-color").equalsIgnoreCase("#990000"))){
			 						css.getPropertyMap().put("background-color", cssVo.getButtonColor());
			 						
			 					}
			 			 }
			 			 
			 			 //selva popup
			 			 
			 			if(css.getSelector()!=null && css.getSelector().contains("#alertBox")){
			 								 			 				 if(css.getPropertyMap().get("border")!=null
			 								 		 						){  
			 								 			 						css.getPropertyMap().put("border", "2px solid "+cssVo.getButtonColor());
			 								 			 						
			 								 			 					}
			 								 			 			 }
			 								 			 			 if(css.getSelector()!=null && css.getSelector().contains("#confirmBox")){
			 								 			 				 if(css.getPropertyMap().get("border")!=null
			 								 		 						){  
			 								 			 						css.getPropertyMap().put("border", "2px solid "+cssVo.getButtonColor());
			 								 			 						
			 								 			 					}
			 								 			 			 }
			 								 			 			 if(css.getSelector()!=null && css.getSelector().contains("#alertBox2")){
			 								 			 				 if(css.getPropertyMap().get("border")!=null
			 								 		 						){  
			 								 			 						css.getPropertyMap().put("border", "2px solid "+cssVo.getButtonColor());
			 								 			 						
			 								 			 					}
			 								 			 			 }
			 								 			 			 
			 								 			 		//vivek search
			 								 			 			if(css.getSelector()!=null && css.getSelector().contains(".search_body")){
			 								 			 			  if(css.getPropertyMap().get("background-color")!=null
			 								 			 				 && (css.getPropertyMap().get("background-color").equalsIgnoreCase("#000")|| 
			 								 			 					 css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000"))){
			 								 			 						css.getPropertyMap().put("background-color", cssVo.getMenuColor());
			 								 			 					 					}
			 								 			 			 			 }


			 			 
		}
		System.out.println("\nAfter Updation--------\n" );
		print(cssClassList);
		 
		/*	System.out.println("\nBefore Updation--------\n"  );
			 print(cssClassList);
			for(CSSClass css:cssClassList){
				 if(css.getSelector()!=null && css.getSelector().contains("table.display thead th") ||
						 css.getSelector().contains(".navigation2 ul li a:hover")){
					 if(css.getPropertyMap().get("background-color")!=null
							 && css.getPropertyMap().get("background-color").equalsIgnoreCase("#000000")){
							css.getPropertyMap().put("background-color", cssVo.getGridHeaderColor());
							
						}
				 }
				 
				
			}
			System.out.println("\nAfter Updation--------\n" );
			print(cssClassList);*/

		return cssClassList;
	}

	private   void print(List<CSSClass> cssClassList) {
		// TODO Auto-generated method stub
		 for(CSSClass css:cssClassList){
			 Set setOfKeys = css.getPropertyMap().keySet();
			 Iterator iterator = setOfKeys.iterator();
			 while (iterator.hasNext()) {
				 String key = (String) iterator.next();
				 String value = (String)css.getPropertyMap().get(key);
				 
				/* System.out.println("Key: "+ key+", Value: "+ value);*/
				  
				  }
			 }
		 }
	
	
		 
	public static String getExtention(File f){
		String name = f.getName();
		int dot = name.lastIndexOf('.');
		String base = (dot == -1) ? name : name.substring(0, dot);
		String extension = (dot == -1) ? "" : name.substring(dot+1);
		return extension;
	}
	

}
