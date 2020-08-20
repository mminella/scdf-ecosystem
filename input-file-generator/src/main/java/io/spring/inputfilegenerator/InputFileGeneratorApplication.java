package io.spring.inputfilegenerator;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class InputFileGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputFileGeneratorApplication.class, args);
	}

}
