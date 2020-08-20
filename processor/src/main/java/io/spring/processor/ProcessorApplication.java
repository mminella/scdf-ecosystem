package io.spring.processor;

import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

	@Bean
	public Function<Map<String, String>, Map<String, String>> processor() {
		return item -> {
			item.put("coversheet", "I've got you covered");
			return item;
		};
	}

}
