package com.st.signservice.push.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="EL OPERADOR YA EXISTE")
public class OperatorExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
}
