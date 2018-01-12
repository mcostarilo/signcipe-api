package com.st.signservice.security.jwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.security.jwt.token.util.TokenExtractor;

public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(JwtSettings.TOKEN_HEADER_PARAM);
		String token = null;

		if (request.getParameter("token") != null) {
			token = request.getParameter("token");			
		}
		else {
			if (authHeader != null && authHeader.startsWith(JwtSettings.HEADER_PREFIX)) {
				token = authHeader.replace(JwtSettings.HEADER_PREFIX, "");
			}
		} 

//		if (authHeader == null || !authHeader.startsWith(JwtSettings.HEADER_PREFIX)) {
//			throw new ServletException("Missing or invalid Authorization header");
//		}
		if (token == null) {
			throw new ServletException("Missing or invalid Authorization header");
		}
	
		
		try {
			Claims claims = TokenExtractor.extractClaims(token);
			request.setAttribute("claims", claims);
		} catch (final SignatureException e) {
			throw new ServletException("Invalid token");
		}
		
		final HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("version", ConstantConfig.APP_VERSION);

		chain.doFilter(request, response);
		
	}

}
