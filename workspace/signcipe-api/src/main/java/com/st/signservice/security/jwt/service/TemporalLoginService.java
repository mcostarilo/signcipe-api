package com.st.signservice.security.jwt.service;

public interface TemporalLoginService {

	boolean checkPasswod(String userName, String passwordBase64);
	
	String generatePassword(String userName);
	
}
