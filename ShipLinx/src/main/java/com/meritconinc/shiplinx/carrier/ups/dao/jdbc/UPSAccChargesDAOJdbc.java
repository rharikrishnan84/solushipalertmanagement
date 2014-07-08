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

import com.meritconinc.shiplinx.carrier.ups.dao.UPSAccChargesDAO;
import com.meritconinc.shiplinx.carrier.ups.model.UPSAccCharges;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class UPSAccChargesDAOJdbc  extends JdbcDaoSupport  implements UPSAccChargesDAO {
	
	private static Logger logger = Logger.getLogger(UPSAccChargesDAOJdbc.class); 
	
	
	public UPSAccCharges getAccCharge(Long businessId, String chargeCode,String chargeCodeLevel2, String country) {
		logger.debug("Accessorial search for business / chargeCode / chargeCode2 / country : " + businessId + " / " + chargeCode + " / " + chargeCodeLevel2 + " / " + country );
		List<UPSAccCharges> results = new UPSAccChargesQuery(getDataSource()).execute(new Object[]{businessId, chargeCode, chargeCodeLevel2, country});
		if(results.isEmpty()){
		logger.debug("No accessorial defined for business / chargeCode / chargeCode2 / country : " + businessId + " / " + chargeCode + " / " + chargeCodeLevel2 + " / " + country );
			results = new UPSAccChargesQuery(getDataSource()).execute(new Object[]{0, chargeCode, chargeCodeLevel2, country});
			if(results.isEmpty()){
				logger.debug("No default accessorial defined for chargeCode / chargeCode2 / country : " + chargeCode + " / " + chargeCodeLevel2 + " / " + country );
				return null;
			} else { 
				return (UPSAccCharges)results.get(0);
			}
		} else {
			return (UPSAccCharges)results.get(0);
		}
	}
	
	//Query to get a Accessorial charge row based on charge name
	class UPSAccChargesQuery extends MappingSqlQuery{
		
		public UPSAccChargesQuery(DataSource ds){ 
			super(ds, "SELECT * FROM ups_acc_charges WHERE business_id =? AND charge_code=? AND charge_code_level_2=? AND country_code=?");
			super.declareParameter(new SqlParameter("business_id",Types.BIGINT));
			super.declareParameter(new SqlParameter("charge_code",Types.VARCHAR));
			super.declareParameter(new SqlParameter("charge_code_level_2",Types.VARCHAR));
			super.declareParameter(new SqlParameter("country_code",Types.VARCHAR));
			compile();
		}
		
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException{
			UPSAccCharges upsAccCharges =  new UPSAccCharges();
			upsAccCharges.setBusinessId(rs.getLong("business_id"));
			upsAccCharges.setChargeCode(rs.getString("charge_code"));
			upsAccCharges.setChargeCodeLevel2(rs.getString("charge_code_level_2"));
			upsAccCharges.setCost(rs.getFloat("cost"));
			upsAccCharges.setCharge(rs.getFloat("charge"));
			upsAccCharges.setPerPackage(rs.getBoolean("per_package"));
			upsAccCharges.setMaxCharge(rs.getFloat("max_charge"));
			upsAccCharges.setMinCharge(rs.getFloat("min_charge"));
			return upsAccCharges;			
		}		
	}
	
}
