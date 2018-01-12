package com.st.signservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="DISABLED")
public class DisabledException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

}
