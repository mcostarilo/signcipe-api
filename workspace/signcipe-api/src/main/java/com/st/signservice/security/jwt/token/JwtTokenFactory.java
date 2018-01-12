package com.st.signservice.security.jwt.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.st.signservice.security.jwt.config.JwtSettings;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.security.jwt.service.OperatorRoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {
	
	@Resource
	private JwtSettings jwtSettings;
	
	@Resource
	private OperatorRoleService operatorRoleService;
	
	public JwtToken createTokenOne(Operator operator , TokenType tokenType) {
		
		Claims claims = Jwts.claims().setSubject(operator.getUserName());

			operator.setPerson(null);
//			List<HealthCenterHasOperator> healthCenterHasOperatorList =  operator.getHealthCenterHasOperator();
//			if (healthCenterHasOperatorList != null && !healthCenterHasOperatorList.isEmpty()) {
//				for (HealthCenterHasOperator healthCenterHasOperator : healthCenterHasOperatorList) {
//					healthCenterHasOperator.setProfile(null);
//				}
//			}
			
			claims.put("scopes", operator);

//			HealthCenter healthCenter = healthCenterService.findHealtCenterByOperatorId(operator.getId());
//			if (healthCenter != null) {
//				healthCenter.setServices(null);
//				healthCenter.setCity(null);
//			}
			
//			claims.put("healthCenter", healthCenter);
			if (tokenType == TokenType.REFRESH) {
				claims.put("tokenType", JwtSettings.REFRESH_TOKEN);
			}
		
		
		LocalDateTime currentTime = LocalDateTime.now();
        
		long tokenExpirationTime = (tokenType == TokenType.AUTH)?jwtSettings.getTokenExpirationTime():jwtSettings.getRefreshTokenExpTime();
		
        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(jwtSettings.getTokenIssuer())
          .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
          .setExpiration(Date.from(currentTime
              .plusMinutes(tokenExpirationTime)
              .atZone(ZoneId.systemDefault()).toInstant()))
          .signWith(SignatureAlgorithm.HS512, JwtSettings.SIGNING_KEY)
        .compact();
		
        return new JwtToken(token, claims);
	}
	
}
