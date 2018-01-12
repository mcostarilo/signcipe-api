package com.st.signservice.security.jwt.token.util;

import com.st.signservice.security.jwt.config.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenExtractor {
	
	public static Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(JwtSettings.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

}
