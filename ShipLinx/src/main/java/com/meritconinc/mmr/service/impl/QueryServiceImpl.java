package com.meritconinc.mmr.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.dao.QueryDAO;
import com.meritconinc.mmr.exception.DAOException;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.query.QueryResultVO;
import com.meritconinc.mmr.model.query.QueryVO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.service.QueryService;

public class QueryServiceImpl implements QueryService {

	private static final Logger log = Logger.getLogger(QueryServiceImpl.class);

	private QueryDAO queryDAO;

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	public List<QueryVO> getQueriesByUser(String userId){
		return queryDAO.getQueriesByUser(userId);
	}

	public List<QueryVO> getAllQueries(){
		return queryDAO.getAllQueries();
	}
	
	public QueryResultVO executeQuery(QueryVO query) throws DAOException{
		return queryDAO.executeQuery(query);
	} 
	
	public QueryVO getQuery(int queryId){
		return queryDAO.getQuery(queryId);
	}

	public void  saveQuery(QueryVO query, String[] roles, String[] users){
		queryDAO.saveQuery(query);
		queryDAO.saveQueryPermission(query.getId(), roles, users);
	}
	 
	public void deleteQuery(int queryId){
		queryDAO.deleteQuery(queryId);
	}
			
	public List<User> getUnauthorizedUsersForQuery(int queryId) {
		return queryDAO.getUnauthorizedUsersForQuery(queryId);
	}
	
	public List<User> getAuthorizedUsersForQuery(int queryId) {
		return queryDAO.getAuthorizedUsersForQuery(queryId);
	}
	
	public List<RoleVO> getUnauthorizedRolesForQuery(int queryId) {
		return queryDAO.getUnauthorizedRolesForQuery(queryId);
	}
	
	public List<RoleVO> getAuthorizedRolesForQuery(int queryId) {
		return queryDAO.getAuthorizedRolesForQuery(queryId);
	}
	
	public void saveQueryPermission(int queryId, String[] roles, String[] users) {
		queryDAO.saveQueryPermission(queryId, roles, users);
	}
	
	public boolean isUserAllowedToRunQuery(String username, int queryId) {
		return queryDAO.isUserAllowedToRunQuery(username, queryId);
	}

}
