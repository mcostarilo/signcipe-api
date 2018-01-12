package com.st.signservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.st.signservice.storage.StorageProperties;
import com.st.signservice.storage.StorageService;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(StorageProperties.class)
@EnableTransactionManagement
public class SigncipeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigncipeApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}
	
	
}
