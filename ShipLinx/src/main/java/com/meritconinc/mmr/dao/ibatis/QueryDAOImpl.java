package com.meritconinc.mmr.dao.ibatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.dao.QueryDAO;
import com.meritconinc.mmr.exception.DAOException;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.query.QueryParameterVO;
import com.meritconinc.mmr.model.query.QueryResultVO;
import com.meritconinc.mmr.model.query.QueryVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.WebUtil;

public class QueryDAOImpl extends SqlMapClientDaoSupport implements QueryDAO {

  private static final Logger log = Logger.getLogger(QueryDAOImpl.class);

  public void deleteQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("id", queryId);
    getSqlMapClientTemplate().delete("deleteQuery", paramObj);
  }

  public QueryResultVO executeQuery(QueryVO query) throws DAOException {
    if (query == null)
      throw new NullPointerException("cannot execute null query");
    if (query.getSql() == null)
      throw new NullPointerException("cannot execute query with null sql");
    if (query.getParameters() == null)
      throw new NullPointerException("cannot execute query with null parameters");

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      if (query.getDatasource() == null || query.getDatasource().trim().length() == 0) {
        conn = getDataSource().getConnection();
      } else {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/" + query.getDatasource());
        conn = ds.getConnection();
      }

      conn.setReadOnly(true);
      stmt = conn.prepareStatement(query.getSql());
      int paramIndex = 1;
      for (QueryParameterVO param : query.getParameters()) {
        if (param.isIncluded()) {
          stmt.setString(paramIndex, param.getValue());
          paramIndex++;
        }
      }

      log.debug("Setting fetch size for query to " + query.getJdbcFetchSize());
      stmt.setFetchSize(query.getJdbcFetchSize());

      // set the max rows for the query. If query max rows is not set (or is zero), then use system
      // default from PROPERTY
      if (query.getMaxRows() <= 0) {
        int default_max_rows = Integer.parseInt(WebUtil.getProperty(Constants.SYSTEM_SCOPE,
            Constants.QUERY_TOOL_DEFAULT_MAX_ROWS));
        stmt.setMaxRows(default_max_rows);
      } else {
        stmt.setMaxRows(query.getMaxRows());
      }
      log.info("Max rows has been set to " + stmt.getMaxRows());

      rs = stmt.executeQuery();

      QueryResultVO result = new QueryResultVO();

      // title
      result.setTitle(query.getName());

      // headers
      ResultSetMetaData metadata = rs.getMetaData();
      int colCount = metadata.getColumnCount();
      List<String> headers = new ArrayList<String>(metadata.getColumnCount());
      for (int i = 1; i <= colCount; i++) {
        headers.add(metadata.getColumnName(i));
      }
      result.setHeaders(headers);

      // result
      while (rs.next()) {
        List<String> row = new ArrayList<String>(colCount);
        for (int i = 1; i <= colCount; i++) {
          row.add(rs.getString(i));
        }
        result.add(row);
      }
      return result;
    } catch (SQLException ex) {
      throw new DAOException("SQLException is caught", ex);
    } catch (NamingException ex) {
      throw new DAOException("NamingException is caught", ex);
    } finally {
      SQLException ex = null;
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          if (ex == null)
            ex = e;
          else
            ex.setNextException(e);
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          if (ex == null)
            ex = e;
          else
            ex.setNextException(e);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          ex = e;
        }
      }

      if (ex != null) {
        throw new DAOException("SQLException is caught", ex);
      }
    }
  }

  public List<QueryVO> getAllQueries() {
    List<QueryVO> result = (List<QueryVO>) getSqlMapClientTemplate().queryForList("selectQueries");
    return result;
  }

  public List<QueryVO> getQueriesByUser(String userId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("username", userId);
    List<QueryVO> result = (List<QueryVO>) getSqlMapClientTemplate().queryForList(
        "selectQueriesByUser", paramObj);
    return result;
  }

  public List<QueryParameterVO> getQueryParameters(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);
    List<QueryParameterVO> result = (List<QueryParameterVO>) getSqlMapClientTemplate()
        .queryForList("selectParametersByQuery", paramObj);
    return result;
  }

  public void saveQueryPermission(int queryId, String[] roles, String[] users) {

    // roles
    Map<String, Object> deleteRolesParamObj = new HashMap<String, Object>(5);
    deleteRolesParamObj.put("queryId", queryId);
    getSqlMapClientTemplate().delete("deleteQueryRole", deleteRolesParamObj);
    if (roles != null) {
      for (int i = 0; i < roles.length; i++) {
        Map<String, Object> paramObj = new HashMap<String, Object>(5);
        paramObj.put("queryId", queryId);
        paramObj.put("role", roles[i]);
        getSqlMapClientTemplate().insert("insertQueryRole", paramObj);
      }
    }

    // users
    Map<String, Object> deleteUsersParamObj = new HashMap<String, Object>(5);
    deleteUsersParamObj.put("queryId", queryId);
    getSqlMapClientTemplate().delete("deleteQueryUser", deleteUsersParamObj);

    if (users != null) {
      for (int i = 0; i < users.length; i++) {
        Map<String, Object> paramObj = new HashMap<String, Object>(5);
        paramObj.put("queryId", queryId);
        paramObj.put("username", users[i]);
        getSqlMapClientTemplate().insert("insertQueryUser", paramObj);
      }

    }
  }

  public void saveQuery(QueryVO query) {
    if (query == null)
      throw new NullPointerException("can't save a null query.");
    if (query.getId() == -1) {
      insertQuery(query);
    } else {
      updateQuery(query);
    }
  }

  private void insertQuery(QueryVO query) {
    Date now = new Date();

    Map<String, Object> paramObj = new HashMap<String, Object>(6);
    paramObj.put("datasource", query.getDatasource());
    paramObj.put("name", query.getName());
    paramObj.put("query", query.getSql());
    paramObj.put("creationDate", now);
    paramObj.put("creator", query.getCreatedBy());
    paramObj.put("lastUpdated", now);
    paramObj.put("updater", query.getUpdatedBy());
    paramObj.put("jdbcFetchSize", query.getJdbcFetchSize());
    paramObj.put("maxRows", query.getMaxRows());
    int queryId = (Integer) getSqlMapClientTemplate().insert("insertQuery", paramObj);
    query.setId(queryId);

    insertQueryParameters(queryId, query.getParameters());

    query.setCreationDate(now);
    query.setLastUpdated(now);

  }

  private void insertQueryParameters(int queryId, List<QueryParameterVO> params) {
    int paramOrder = 0;
    for (Iterator<QueryParameterVO> iterator = params.iterator(); iterator.hasNext();) {
      QueryParameterVO queryParameterVO = (QueryParameterVO) iterator.next();
      if (queryParameterVO.isIncluded()) {
        Map<String, Object> paramObj2 = new HashMap<String, Object>(5);
        paramObj2.put("queryId", queryId);
        paramObj2.put("name", queryParameterVO.getName());
        paramObj2.put("paramOrder", paramOrder);
        paramObj2.put("defaultValue", queryParameterVO.getDefaultValue());
        paramObj2.put("validation", queryParameterVO.getValidationRegExp());
        getSqlMapClientTemplate().insert("insertParameter", paramObj2);
        paramOrder++;
      }
    }
  }

  private void updateQuery(QueryVO query) {
    Date now = new Date();
    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    paramObj.put("datasource", query.getDatasource());
    paramObj.put("name", query.getName());
    paramObj.put("query", query.getSql());
    paramObj.put("lastUpdated", now);
    paramObj.put("updater", query.getUpdatedBy());
    paramObj.put("id", query.getId());
    paramObj.put("jdbcFetchSize", query.getJdbcFetchSize());
    paramObj.put("maxRows", query.getMaxRows());

    getSqlMapClientTemplate().update("updateQuery", paramObj);

    Map<String, Object> deleteParamObj = new HashMap<String, Object>(1);
    deleteParamObj.put("queryId", query.getId());
    getSqlMapClientTemplate().delete("deleteParameters", deleteParamObj);
    insertQueryParameters(query.getId(), query.getParameters());
    query.setLastUpdated(now);

  }

  public List<RoleVO> getAuthorizedRolesForQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);

    List<RoleVO> result = (List<RoleVO>) getSqlMapClientTemplate().queryForList(
        "selectAuthorizedRolesByQuery", paramObj);
    return result;
  }

  public List<User> getAuthorizedUsersForQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);
    List<User> result = (List<User>) getSqlMapClientTemplate().queryForList(
        "selectAuthorizedUsersByQuery", paramObj);
    return result;
  }

  public List<RoleVO> getUnauthorizedRolesForQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);
    List<RoleVO> result = (List<RoleVO>) getSqlMapClientTemplate().queryForList(
        "selectUnauthorizedRolesByQuery", paramObj);
    return result;
  }

  public List<User> getUnauthorizedUsersForQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);
    List<User> result = (List<User>) getSqlMapClientTemplate().queryForList(
        "selectUnauthorizedUsersByQuery", paramObj);
    return result;
  }

  public QueryVO getQuery(int queryId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("queryId", queryId);
    return (QueryVO) getSqlMapClientTemplate().queryForObject("selectQuery", paramObj);
  }

  public boolean isUserAllowedToRunQuery(String username, int queryId) {

    Map<String, Object> paramObj = new HashMap<String, Object>(2);
    paramObj.put("queryId", queryId);
    paramObj.put("username", username);
    return (Boolean) getSqlMapClientTemplate().queryForObject("selectQuery", paramObj);
  }

}
