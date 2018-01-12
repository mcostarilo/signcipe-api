package com.st.signservice.security.jwt.token;

import io.jsonwebtoken.Claims;

public class JwtToken {

	private final String token;
	private Claims claims;
	
	public JwtToken(String token, Claims claims) {
		this.token = token;
		this.claims = claims;
	}
	
	public Claims getClaims() {
		return claims;
	}
	public void setClaims(Claims claims) {
		this.claims = claims;
	}
	public String getToken() {
		return token;
	}	
	
}
