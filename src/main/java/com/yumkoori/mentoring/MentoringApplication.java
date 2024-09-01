package com.yumkoori.mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MentoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoringApplication.class, args);
	}

}
