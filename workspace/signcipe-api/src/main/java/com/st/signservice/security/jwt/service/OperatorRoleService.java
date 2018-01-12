package com.st.signservice.security.jwt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.st.signservice.security.jwt.entity.OperatorRole;

public interface OperatorRoleService {

	List<OperatorRole> getOperatorRoles();
	
	List<OperatorRole> findOperatorRoleByOperatorId(Integer operatorId);
	
	OperatorRole createOperatorRol(OperatorRole operatorRole);

	OperatorRole findOperatorRoleByOperator(String email, String rolName);
	
	boolean hasRol(HttpServletRequest request, String rolName, Integer serviceProviderId);
}
