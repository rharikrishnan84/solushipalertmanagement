package com.meritconinc.mmr.action.query;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Preparable;
import com.meritconinc.mmr.action.BaseAction;
import com.meritconinc.mmr.exception.DAOException;
import com.meritconinc.mmr.model.query.QueryParameterVO;
import com.meritconinc.mmr.model.query.QueryResultVO;
import com.meritconinc.mmr.model.query.QueryVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.QueryService;

public class QueryAction extends BaseAction implements Preparable, ServletResponseAware {
	private static final long serialVersionUID = 7881565850482988085L;

	// loggers	
	private static final Logger log = Logger.getLogger(QueryAction.class);
	
	private static final String QUERY_RESULT = "queryResult";
	private static final String QUERY_PARAMETERS = "queryParameters";
		
	private QueryService queryService;
	private QueryResultVO queryResult;
	private String queryError;
	
	private int selectedQueryId = -1;
	private QueryVO query;
	private List<QueryVO>queries; 
	
	private HttpServletResponse response;
	
	private String responseType;
	private String RESPONSE_TYPE_XLS = "XLS";
	private String RESPONSE_TYPE_CSV = "CSV";
			
	public void prepare() throws Exception {
		loadQueryDetail();
	}
	
	private void loadQueryDetail() throws Exception {
		if ( selectedQueryId >= 0 ) {
			query = queryService.getQuery(selectedQueryId);			
		}
		else {
			query = new QueryVO();
		}
	}
	
	public String init() { return SUCCESS; }
		
	public String showQueryParameters() throws Exception {
		return QUERY_PARAMETERS;
	}
		
	
	public String runQuery() throws Exception {		
		if ( ! isUserAllowedToRunQuery() ) {			
			addActionError(getMessage("label.querytool.permissions.noPermission"));
		} 
		else if(!parametersValid()){
			//error messages have been added to this action
		}		
		else {
			try {
				queryResult = queryService.executeQuery(query);			
			} catch (DAOException e) {
				this.queryError = e.getCause().getLocalizedMessage();
			}	
			if(this.getResponseType().equalsIgnoreCase(RESPONSE_TYPE_XLS)){			
				return extractToXLS(queryResult);
			}
			if(this.getResponseType().equalsIgnoreCase(RESPONSE_TYPE_CSV)){			
				return extractToCSV(queryResult);
			}
		
		}		
		return QUERY_RESULT;		
	}
	
	
	private boolean isUserAllowedToRunQuery() throws DAOException {
		boolean allowed = false;
		User user = getLoginUser();
		if ( user.isSysAdmin() ) {
			allowed = true;
		} else {
			List<QueryVO> l = queryService.getQueriesByUser(user.getUsername());
			for (Iterator<QueryVO> iterator = l.iterator(); iterator.hasNext();) {
				QueryVO q = (QueryVO) iterator.next();
				if ( q.getId() == query.getId() ) {
					allowed = true;
					break;
				}
			}
		}
		return allowed;
	}

	protected String extractToXLS(QueryResultVO queryResult) {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + getExportFilename(queryResult) + ".xls\";");
		response.setContentType("application/vnd.ms-excel");

		
		HSSFWorkbook wb = new HSSFWorkbook();
		// create a new sheet
		HSSFSheet sheet = wb.createSheet();
		// declare a row object reference
		HSSFRow row = null;
		// declare a cell object reference
		HSSFCell cell = null;
		// create a font object
		HSSFFont f = wb.createFont();
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// create a cell style
		HSSFCellStyle cs = wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setFont(f);

		// create a date cell style
		HSSFCellStyle dtCellStyle = wb.createCellStyle();
		//dtCellStyle.setDataFormat(HSSFDataFormat.getFormat("m/d/yy"));
		dtCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		// does not yet support custom formats
		//dtCellStyle.setDataFormat(HSSFDataFormat.getFormat(DT_FMT));


        // add titles
        short nextrow = 0;
        
		row = sheet.createRow(nextrow++);
        cs.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
        cell = row.createCell((short)0);
        cell.setCellStyle(cs);
        cell.setCellValue(queryResult.getTitle());
        
        row = sheet.createRow(nextrow++);
        cs.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
        cell = row.createCell((short)0);
        cell.setCellStyle(dtCellStyle);
        cell.setCellValue(new Date());
 
		// add a space
		row = sheet.createRow(nextrow++);
    
		// add headers
		row = sheet.createRow(nextrow++);
		for (short i=0; i < queryResult.getHeaders().size(); i++) {
			String value = queryResult.getHeaders().get(i);
			cell = row.createCell(i);
			cell.setCellStyle(cs);
			cell.setCellValue(value);
		}

		// add a space
		row = sheet.createRow(nextrow++);
		
		
		// add data fields
		for (short i = 0; i < queryResult.size(); i++) {

			row = sheet.createRow(nextrow++);
			List<String> dataValues = queryResult.get(i);
			
			for (short j = 0; j < dataValues.size(); j++) {
				String x = dataValues.get(j);
				if (x instanceof String) {
					row.createCell(j).setCellValue(((String) x).trim());
				} 
				
//				if(sheet.getColumnWidth(j) < x.length())
//					sheet.setColumnWidth(j, (short) x.length());
				
			}
		}
	

		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			wb.write(out);
		} catch (IOException io) {
			log.fatal("Failed to create XLS file", io);
		} finally {
			out.close();
		}

		
		return null;		
	}

	/**
	 * Generates the report as in csv format.  
	 *
	 * @param queryResult
	 */
	protected String extractToCSV(QueryResultVO queryResult) {

		response.setContentType("application/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + getExportFilename(queryResult) + ".csv\";");
		StringBuffer buf = new StringBuffer();
		PrintStream out = null;
		try {
			out = new PrintStream(response.getOutputStream());
			log.debug("Extract To CSV");
			log.debug(response.getOutputStream());
		} catch (IOException io) {
			log.fatal("Failed to create CSV file", io);
		} 

        // add titles
		String value = queryResult.getTitle();
        buf.append("\"" + ((value == null) ? "" : ((String) value).trim()) + "\"");
        out.println(buf.toString());
		

		// add headers
		buf = new StringBuffer();
		for (short i=0; i < queryResult.getHeaders().size(); i++) {
			value = queryResult.getHeaders().get(i);
			buf.append("\"" + ((value == null) ? "" : ((String) value).trim()) + "\"");
			if ((i + 1) != queryResult.getHeaders().size()) 
				buf.append(",");			
		}
		out.println(buf.toString());

		// add data fields
		for (short i = 0; i < queryResult.size(); i++) {

			buf = new StringBuffer();
			List<String> dataValues = queryResult.get(i);
			
			for (short j = 0; j < dataValues.size(); j++) {
				String x = dataValues.get(j);
				buf.append("\"" + ((x == null) ? "" : ((String) x).trim()) + "\"");
				if ((j + 1) != dataValues.size()) {
					buf.append(",");
				}				
			}
			out.println(buf.toString());
		}
		
		out.flush();
		out.close();
		
		return null;
	}
	
	private String getExportFilename(QueryResultVO queryResult) {
		String queryTitle = queryResult.getTitle();
		if ( queryTitle == null || queryTitle.length() == 0 ) {
			return "Untitled";
		} else if ( queryTitle.matches("\\w*\\W+\\w*") ) {
			return queryTitle.replaceAll("\\W", "_");
		} else {
			return queryTitle;
		}		
	}
	
	//=====================================
	// Getters/Setters
	//=====================================	
	public List<QueryVO> getQueries() throws DAOException {
		if ( queries == null ) {
			User user = getLoginUser();
			if (user == null) {
				log.debug(new StringBuilder("End: ").append("returning : ").append(ERROR));				
			}
			queries = queryService.getQueriesByUser(user.getUsername());			
		}
		return queries;
	}	
	
	private boolean parametersValid(){
		
		List<QueryParameterVO> parameters = query.getParameters();
		
		boolean parametersValid = true;
		
		for(QueryParameterVO parameter: parameters){
			log.debug("Name/RegEx/Value: " + parameter.getName() + ", " + parameter.getValidationRegExp() + ", " + parameter.getValue());
			//validationRegExp is not a required field. Perform pattern matching only if reg exp is specified for this parameter in the query definition
			if(parameter.getValidationRegExp()!=null && parameter.getValidationRegExp().length()>0){
				if(!parameter.getValue().matches(parameter.getValidationRegExp())){					
									
					this.addActionError(this.getMessage("label.querytool.parameters.notValid" ) + parameter.getName());
					parametersValid = false;
				}			
			}			
		}
		
		return parametersValid;
	}
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public int getSelectedQueryId() {
		return selectedQueryId;
	}

	public void setSelectedQueryId(int selectedQueryId) {
		this.selectedQueryId = selectedQueryId;
	}
		
	public QueryVO getQuery() {
		return query;
	}
	
	public void setQuery(QueryVO query) {
		this.query = query;
	}
	
	public QueryResultVO getQueryResult() {
		return this.queryResult;
	}

	public String getQueryError() {
		return this.queryError;
	}
	
	/**
	 * sets the HttpServletRequest
	 * 
	 * @param httpServletRequest
	 */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
    	this.response = httpServletResponse;     
    }

	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	public int getResultSize(){
		return queryResult.size();
	}

}
