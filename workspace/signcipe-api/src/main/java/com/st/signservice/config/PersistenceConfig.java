package com.st.signservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.service.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

	@Bean
    AuditorAware<Operator> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
