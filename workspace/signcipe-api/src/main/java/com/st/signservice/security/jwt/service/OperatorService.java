package com.st.signservice.security.jwt.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.st.signservice.push.service.exception.OperatorExistsException;
import com.st.signservice.security.jwt.entity.Operator;

public interface OperatorService {

	Page<Operator> getOperators(String search, Pageable pageable, Integer serviceProviderId) throws IOException;
	
	Operator findOperatorById(Integer operatorId);
	
	List<Operator> findByUserName(String userName);
	
	Operator getOperatorFromCipeWS(String userName, String serialNumber) throws ServletException, IOException, ParseException;

	Operator createOperator(Operator operator, Operator createdBy) throws OperatorExistsException;

	Operator updateOperator(Operator operator, Operator updatedBy);

	Operator deleteOperator(Integer operatorId, Operator createdBy);
	
	Operator getOperatorByToken(HttpServletRequest request);
	
	List<Operator> findSystemOwners();
	
	boolean validateCsnFromCipeWS(String userName, String serialNumber) throws ServletException;

	List<Operator> getOperatorByPersonId(Integer personId);
	
}
