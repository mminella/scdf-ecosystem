/**
 * Copyright 2020-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.inputfilegenerator.configuration;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author Michael Minella
 */
@Configuration
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("job")
			.start(step1(null))
			.build();
	}

	@Bean
	public Step step1(ItemWriter<String> itemWriter) {
		return this.stepBuilderFactory.get("step1")
				.<String, String>chunk(100)
				.reader(new ItemReader<String>() {
					private int count = 0;

					@Override
					public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
						if(count < 5000) {
							count++;
							return String.format("This is file based TPS report id %s.", UUID.randomUUID());
						}
						return null;
					}
				}).writer(itemWriter)
				.build();
	}

	@Bean
	public FlatFileItemWriter<String> itemWriter() {
		return new FlatFileItemWriterBuilder<String>()
				.name("itemWriter")
				.resource(new FileSystemResource("/Users/mminella/temp/output.csv"))
				.lineAggregator(new PassThroughLineAggregator<>())
				.build();
	}
}
