package com.st.signservice.service;

import javax.annotation.Resource;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.security.jwt.service.OperatorService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class AuditorAwareImpl  implements AuditorAware<Operator>  {

	@Resource
	OperatorService operatorService;

	@Override
	public Operator getCurrentAuditor() {

		HttpServletRequest request =  getCurrentHttpRequest();

		Operator operator = operatorService.getOperatorByToken(request);

		return operator;
	}
	
	
	public static HttpServletRequest getCurrentHttpRequest(){
	    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	    if (requestAttributes instanceof ServletRequestAttributes) {
	        HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes)requestAttributes).getRequest();
	        return request;
	    }
	    return null;
	}	
}
