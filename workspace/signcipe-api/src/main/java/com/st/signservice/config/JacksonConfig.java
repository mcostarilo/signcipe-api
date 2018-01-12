package com.st.signservice.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        System.out.println("Config is starting.");
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
//        return objectMapper;
//    }

//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//		System.out.println("Config is starting.");
//	    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//	    jsonConverter.setObjectMapper(objectMapper);
//	    jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
//	    return jsonConverter;
//	}	

//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	CharacterEncodingFilter characterEncodingFilter() {
//	  CharacterEncodingFilter filter = new CharacterEncodingFilter();
//	  filter.setEncoding("UTF-8");
//	  filter.setForceEncoding(true);
//	  return filter;
//	}	
}