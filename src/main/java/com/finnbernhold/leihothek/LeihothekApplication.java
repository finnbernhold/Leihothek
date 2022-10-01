package com.finnbernhold.leihothek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class LeihothekApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeihothekApplication.class, args);
	}
}