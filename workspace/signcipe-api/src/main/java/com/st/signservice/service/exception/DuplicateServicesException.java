package com.st.signservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class DuplicateServicesException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public String message;

	public DuplicateServicesException(String message) {
		this.message = message;
	}
	
    @Override
    public String getMessage(){
        return message;
    }	
}
