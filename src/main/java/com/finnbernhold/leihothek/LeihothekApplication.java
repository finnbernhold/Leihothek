package com.finnbernhold.leihothek;

import com.finnbernhold.leihothek.auditing.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class LeihothekApplication {
	public static void main(String[] args) {
		SpringApplication.run(LeihothekApplication.class, args);
	}

	@Bean
	public AuditorAware<String> myAuditorProvider() {
		return new AuditorAwareImpl();
	}
}