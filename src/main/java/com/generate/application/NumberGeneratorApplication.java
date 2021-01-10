package com.generate.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.generate.entity.Task;


@EnableAsync
@SpringBootApplication(scanBasePackages="com.generate")
@EntityScan(basePackageClasses = Task.class) 
public class NumberGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumberGeneratorApplication.class, args);
	}

}
