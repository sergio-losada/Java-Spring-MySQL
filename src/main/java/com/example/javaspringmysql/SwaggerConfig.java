package com.example.javaspringmysql;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		//http://localhost:8080/swagger-ui.html
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.javaspringmysql.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		//http://localhost:8080/v2/api-docs
		return new ApiInfo(
				"Spring Boot MySQL - CRUD",
				"REST API Description",
				"1.0",
				"https://github.com/sergio-losada",
				new Contact("Sergio Losada", "https://www.linkedin.com/in/sergio-losada-gonz%C3%A1lez-5a4b12215/?trk=public_profile_browsemap&originalSubdomain=es", "UO271744@uniovi.es"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList());
	}

}
