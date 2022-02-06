package com.pjada.GeneratorPdf;

import com.pjada.GeneratorPdf.repo.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.pjada.GeneratorPdf.models")
@EnableJpaRepositories("com.pjada.GeneratorPdf.repo")
public class GeneratorPdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorPdfApplication.class, args);
	}

}
