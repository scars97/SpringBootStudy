package com.springboot.advanced_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class Advanced_JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Advanced_JpaApplication.class, args);
	}

}
