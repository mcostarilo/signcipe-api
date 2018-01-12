package com.st.signservice.security.jwt.config;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	//@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	
	    CommonsMultipartResolver multipartResolver 
	            = new CommonsMultipartResolver();
	    return multipartResolver;
	    
	}
}