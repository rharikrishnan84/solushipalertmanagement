package com.meritconinc.mmr.dao.ibatis;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.UtilDAO;
import com.meritconinc.mmr.model.system.DatabaseInfoVO;

public class UtilDAOImpl extends SqlMapClientDaoSupport implements UtilDAO {
  private static final Logger logger = Logger.getLogger(UtilDAOImpl.class);

  public void clearCache() {
    getSqlMapClient().flushDataCache();
  }

  public DatabaseInfoVO getDatabaseInfoVO() {
    DatabaseInfoVO dbi = new DatabaseInfoVO();
    Connection conn = null;
    try {
      DataSource ds = getDataSource();
      conn = ds.getConnection();
      DatabaseMetaData md = conn.getMetaData();
      dbi.setDatabaseProductName(md.getDatabaseProductName());
      dbi.setDatabaseProductVersion(md.getDatabaseProductVersion());
      dbi.setDriverName(md.getDriverName());
      dbi.setDriverVersion(md.getDriverVersion());
      dbi.setUrl(md.getURL());
    } catch (Throwable ex) {
      logger.error("Unable to find out the DatabaseMetadata", ex);
      dbi.setDriverName(ex.getMessage());
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
        logger.error("Unable to close database connection", ex);
      }
    }
    return dbi;
  }

}
