package com.meritconinc.mmr.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;


/**
 * Generate the paging tab
 */
public class MmrPagingTag extends SimpleTagSupport {
    
	private static int DISPLAY_PAGES_MAX = 11; // Maximum # of pages to display (set to default)
	
	// {pageNumber} is a keyword to be substituted with the actual page number when rendered
	private static String PAGE_KEYWORD = "\\{pageNumber\\}";
	
    /**
     * This is for logging.
     */
	public static final Logger LOGGER = Logger.getLogger(MmrPagingTag.class);   

	/**
	 * For getting the totalRecords attribute
	 */ 
	public static final String TOTAL_RECORDS = "totalRecords";
	
	/**
	 * For getting the pageSize attribute
	 */
	public static final String PAGE_SIZE = "pageSize";
	
	/**
	 * For getting the pageNumber attribute
	 */
	public static final String PAGE_NUMBER = "pageNumber";
	
	/**
	 * For getting the NAME attribute
	 */
	public static final String FILTER_BY = "NAME";
	
	/**
	 * For providing the Html Blank Space 
	 */
	public static final String BLANK_SPACE = "&nbsp;";
	
    /**
     * For providing the Html Blank Space 
     */
	private int currentPage;
    
	 /**
     * For storing number of records that can be shown on any page.
     */
	private int pageSize;
    
	 /**
     * For storing total number of records that 
     * are to be displayed on all the pages 
     */
    private int totalRecords;
    
    /**
     * The URL of the page that is to be opened on clicking a link.
     */
    private String url;
    
    /**
     * The number of pages that are to be displayed.
     */
    private int numberOfPages;
    
    /**
     * This is used to create a string anchor.
     */
    private String anchor = "";
     
    private String cssClass = "";
    
    /**
     * @return Returns the firstRecord.
     */
    public int getCurrentPage() {
        return currentPage;
    }
    /**
     * @param p_currentPage The firstRecord to set.
     */
    public void setCurrentPage(int p_currentPage) {
        this.currentPage = p_currentPage;
    }
    
    /**
	 * 
	 * @return pageSize for pagination
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * Sets pageSize for pagination
	 * @param p_pageSize
	 */
	public void setPageSize(int p_pageSize) {
		this.pageSize = p_pageSize;
	}
	
    public int getTotalRecords() {
        return totalRecords;
    }
    
    public void setTotalRecords(int p_totalRecords) {
        this.totalRecords = p_totalRecords;
    }
    
	/**
	 * This method is executed for the tag
     * @throws JspException . 
	 */
	public void doTag() throws JspException {
		JspWriter l_out = getJspContext().getOut();
		try {
			String l_markup = getMarkup();
			l_out.print(l_markup);
		} catch (Throwable l_t) {
			LOGGER.error("An error occured while processing the taglib", l_t);
			throw new JspException("An error occured while processing the taglib", l_t);
		} 
	}
	
    /**
     * Method for showing paging tab on the front end
     * @return String. The HTML that will form the pagination code. 
     * @throws Exception . 
     */
    public String getMarkup() throws Exception {
        StringBuffer l_buffer = new StringBuffer();
                  
            if (totalRecords == 0) {
                l_buffer.append("<table width=\"100%\" border=\"0\""
                		+ " cellspacing=\"0\" cellpadding=\"0\" height=\"15px\" class=\"" + cssClass + "\" >\n");
                l_buffer.append("<tr>");
                l_buffer.append("<td align=\"center\">");
                l_buffer.append("<b> No records were found!</b> ");
                l_buffer.append("</td></tr></table>");
            } else {   
                int l_lastPage = (totalRecords < pageSize) 
                ? 1 : (totalRecords / pageSize);
                
                l_lastPage = ((totalRecords % pageSize) == 0) 
                ? l_lastPage : l_lastPage + 1;
                
                if (totalRecords > pageSize) {	
                	l_buffer.append(showPagingTab(l_lastPage));
                }
            }
            
        String l_html = l_buffer.toString()
        .replaceAll("<td", "<td style=\"border: none;\"");
        return l_html;
    }
    /**
     * Method to show paging tab on the front end.
     * @param p_lastPage as int.
     * @return StringBuffer. This is the HTML for pagination.
     */
    private StringBuffer showPagingTab(int p_lastPage) {
        
    	// get 'DISPLAY_PAGES_MAX' from properties if set
    	String displayPagesMaxStr = WebUtil.getProperty("PAGING", "DISPLAY_PAGES_MAX");
    	
    	if(StringUtil.isPositiveInteger(displayPagesMaxStr))
    		DISPLAY_PAGES_MAX = Integer.parseInt(displayPagesMaxStr);
    	
        StringBuffer l_buffer = new StringBuffer();
       
        if (anchor == null) {
        	anchor = "";
        }
             
        l_buffer.append("<table height=\"15px\" " 
        		+ "cellspacing=\"0\" cellpadding=\"0\" class=\"" + cssClass + "\" ><tr>\n");
        
        numberOfPages = (totalRecords / pageSize);  
        if (totalRecords % pageSize != 0) {
        	numberOfPages++;
        }
        
        // add label: 'Page x of X'                
        l_buffer.append("<td>");  
        l_buffer.append(MessageUtil.getMessage("label.paging.page", MessageUtil.getLocale()) + 
        		BLANK_SPACE + currentPage + BLANK_SPACE + 
        		MessageUtil.getMessage("label.paging.of", MessageUtil.getLocale()) + 
        		BLANK_SPACE + numberOfPages);        
        l_buffer.append("</td>");
        l_buffer.append("<td>" + BLANK_SPACE + "</td>");  
        
        // add label: 'First' 
	      if (currentPage != 1) {
				l_buffer.append("<td>");
				l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(1)) + anchor +"\">");
				l_buffer.append("&#171;");
				l_buffer.append("</a>");
				l_buffer.append(BLANK_SPACE);
				l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(1)) + anchor +"\">");
				l_buffer.append(MessageUtil.getMessage("label.paging.first", MessageUtil.getLocale()));				
				l_buffer.append("</a>");
				l_buffer.append(" |");
	      } else {
	        	l_buffer.append("<td>");
	        	l_buffer.append("&#171; " + MessageUtil.getMessage("label.paging.first", MessageUtil.getLocale()) + " |");
	        }
	        l_buffer.append("</td>");
          
        // add label: 'Previous' 
          if (currentPage > 1) {
        	  l_buffer.append("<td style=\"padding-left:0\" >");
	          l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(currentPage - 1)) + anchor +"\">");
	          l_buffer.append("&#171;");
	          l_buffer.append("</a>");
	          l_buffer.append(BLANK_SPACE);
        	  l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(currentPage - 1)) + anchor + "\">");
	          l_buffer.append(MessageUtil.getMessage("label.paging.previous", MessageUtil.getLocale()));
	          l_buffer.append("</a>");
	          l_buffer.append(" |");
          } else {
        	  l_buffer.append("<td>");
        	  l_buffer.append("&#171; " + MessageUtil.getMessage("label.paging.previous", MessageUtil.getLocale()) + " |");
          }
          l_buffer.append("</td>");
            
        // calculate start and end page numbers - to the left and right of current page
        int displayInterval = DISPLAY_PAGES_MAX / 2;
        int startPageNum = 0;
        int endPageNum = 0;
        
        if(currentPage == 1)
        	startPageNum = 1;
        else {
        	if((currentPage - displayInterval) <= 1)
        		startPageNum = 1;
        	else
        		startPageNum = currentPage - displayInterval;
        }
        
        if(currentPage == numberOfPages) {
        	endPageNum = numberOfPages;
    		// reset startPageNum in this case
    		if((numberOfPages - DISPLAY_PAGES_MAX) > 0) 
    			startPageNum = endPageNum - DISPLAY_PAGES_MAX + 1;
        }
        else {
        	if((numberOfPages - currentPage) <= displayInterval) {
        		endPageNum = numberOfPages;
        		// reset startPageNum in this case
        		if((numberOfPages - DISPLAY_PAGES_MAX) > 0) 
        			startPageNum = endPageNum - DISPLAY_PAGES_MAX + 1;
        	}
        	else
        		endPageNum = startPageNum + DISPLAY_PAGES_MAX - 1;
        }
        
        // add label: Page Numbers 
		for (int l_pageCounter = startPageNum; l_pageCounter <= endPageNum;	l_pageCounter++) {
			if (l_pageCounter == currentPage) {
				l_buffer.append("<td><font color=\"#FBB117\">").append(l_pageCounter).append("</font></td>");
			} else {
				l_buffer.append("<td>");
				l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(l_pageCounter)) + anchor + "\">");
		        l_buffer.append(l_pageCounter + "</a>").append("</td>");
			}
		}
  		
        // add label: 'Next' 
            if (currentPage < p_lastPage) {
            	l_buffer.append("<td style=\"padding-right:0\" >");
            	l_buffer.append("| ");
            	l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(currentPage + 1)) + anchor + "\">");
	            l_buffer.append(MessageUtil.getMessage("label.paging.next", MessageUtil.getLocale()));
	            l_buffer.append("</a>");
	            l_buffer.append(BLANK_SPACE);
	            l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(currentPage + 1)) + anchor + "\">");
	        	l_buffer.append("&#187;");
	        	l_buffer.append("</a>");
            } else {
            	l_buffer.append("<td>");
            	l_buffer.append("| " + MessageUtil.getMessage("label.paging.next", MessageUtil.getLocale()) + " &#187;");
            }
            l_buffer.append("</td>");
        
        // add label: 'Last'             
        	if (currentPage != p_lastPage) {	
        		l_buffer.append("<td>");
        		l_buffer.append("| ");
        		l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(p_lastPage)) + anchor + "\">");
	        	l_buffer.append(MessageUtil.getMessage("label.paging.last", MessageUtil.getLocale()));
	        	l_buffer.append("</a>");
	        	l_buffer.append(BLANK_SPACE);
	        	l_buffer.append("<a href=\"" + url.replaceAll(PAGE_KEYWORD, String.valueOf(p_lastPage)) + anchor + "\">");
	        	l_buffer.append("&#187;");
	        	l_buffer.append("</a>");
            } else {
        		l_buffer.append("<td>");
        		l_buffer.append("| " + MessageUtil.getMessage("label.paging.last", MessageUtil.getLocale()) + " &#187;");
        	}
        	l_buffer.append("</td>");
        
        l_buffer.append("</tr></table>");
                
        return l_buffer;
    }
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String p_url) {
		this.url = p_url;
	}

	public String getAnchor() {
		return anchor;
	}	
	public void setAnchor(String p_anchor) {
		this.anchor = p_anchor;
	}
	
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String p_cssClass) {
		this.cssClass = p_cssClass;
	}
    
}