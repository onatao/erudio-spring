package com.devnatao.springrestfulapicourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	/*
	 * Beans are objects instantiated, mounted 
	 * and managed by Spring IOC Container.
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
					.info(new Info()
							.title("RESTful API with Java 17 and Spring Boot 3")
							.version("v1")
							.description("Udemy course")
							.termsOfService("devnatao.vercel.app")
							.license(
									new License()
									.name("Apache 2.0")
									.url("devnatao.vercel.app")
									)
							);
	}
}
