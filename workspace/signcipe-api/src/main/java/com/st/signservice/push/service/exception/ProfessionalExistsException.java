package com.st.signservice.push.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="YA EXISTE UN PROFESIONAL CON EL MISMO DOCUMENTO O MATRICULA")
public class ProfessionalExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
}
