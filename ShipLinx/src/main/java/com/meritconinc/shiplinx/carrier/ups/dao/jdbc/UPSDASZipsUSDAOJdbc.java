package com.meritconinc.shiplinx.carrier.ups.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.meritconinc.shiplinx.carrier.ups.dao.UPSDASZipsUSDAO;
import com.meritconinc.shiplinx.carrier.ups.model.UPSDASZipsUS;

public class UPSDASZipsUSDAOJdbc extends JdbcDaoSupport implements UPSDASZipsUSDAO {
	
	
	private static Logger log = Logger.getLogger(UPSDASZipsUSDAOJdbc.class); 
	
	@Override
	public UPSDASZipsUS getUPSDASZipsUSByZipCode(String zipcode) {
		// TODO Auto-generated method stub
		
		List<UPSDASZipsUS> results = new UPSDASZipsUSQuery(getDataSource()).execute(new Object[]{zipcode});
		if(results.isEmpty()){
			return null;
		} else {
			return (UPSDASZipsUS)results.get(0);
		} 
	}
	
	//Query to get a major points row based on postal code
	class UPSDASZipsUSQuery extends MappingSqlQuery{
		
		public UPSDASZipsUSQuery(DataSource ds){ 
			super(ds, "SELECT * FROM ups_das_zips_us WHERE zip_code =? ");
			super.declareParameter(new SqlParameter("zip_code",Types.VARCHAR));
			
			compile();
		}
		
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException{
			UPSDASZipsUS upsDasZipsUs = new UPSDASZipsUS();
			upsDasZipsUs.setZipCode(rs.getString("zip_code"));
//			upsDasZipsUs.setDAS(rs.getBoolean("is_das"));
//			upsDasZipsUs.setDASExtended(rs.getBoolean("is_das_extended"));
			upsDasZipsUs.setRemoteHI(rs.getBoolean("is_remote_hi"));
			upsDasZipsUs.setRemoteAK(rs.getBoolean("is_remote_ak"));
			return upsDasZipsUs;			
		}		
	}


}
