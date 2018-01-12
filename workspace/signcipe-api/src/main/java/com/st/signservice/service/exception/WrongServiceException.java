package com.st.signservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="WRONG SERVICE")
public class WrongServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
}
