package com.meritconinc.mmr.service;

import java.util.List;

import com.meritconinc.mmr.exception.DAOException;
import com.meritconinc.mmr.model.common.RoleVO;
import com.meritconinc.mmr.model.query.QueryResultVO;
import com.meritconinc.mmr.model.query.QueryVO;
import com.meritconinc.mmr.model.security.User;

public interface QueryService {
	
	List<QueryVO> getQueriesByUser(String userId);	

	List<QueryVO> getAllQueries();
	
	QueryResultVO executeQuery(QueryVO query) throws DAOException;
	
	QueryVO getQuery(int queryId);

	void  saveQuery(QueryVO query, String[] roles, String[] users);
	 
	void deleteQuery(int queryId);
		
	
	List<User> getUnauthorizedUsersForQuery(int queryId);
	List<User> getAuthorizedUsersForQuery(int queryId);
	
	List<RoleVO> getUnauthorizedRolesForQuery(int queryId);
	List<RoleVO> getAuthorizedRolesForQuery(int queryId);	
	
	void saveQueryPermission(int queryId, String[] roles, String[] users);
	
	boolean isUserAllowedToRunQuery(String username, int queryId);
	
} 
