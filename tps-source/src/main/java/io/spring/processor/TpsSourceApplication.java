package io.spring.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpsSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpsSourceApplication.class, args);
	}

	@Bean
	public Supplier<Map<String, String>> processor() {
		return () -> {
			Map<String, String> item = new HashMap<>(1);
			item.put("report", String.format("This is TPS report id %s.", UUID.randomUUID()));
			return item;
		};
	}

}
