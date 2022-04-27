package com.cdns.banking.transaction;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TransactionServiceApplication
 * 
 * @version 1.0
 */
@SpringBootApplication
@EnableSwagger2
public class TransactionServiceApplication {

	/**
	 * main
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

	/**
	 * swaggerConfiguration
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cdns.banking.transaction")).build()
				.apiInfo(getAPIInfo());
	}

	/**
	 * getRestTemplate
	 * 
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * getAPIInfo
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo getAPIInfo() {
		return new ApiInfo("CDNS Banking Transaction Service APIs",
				"Documentation of APIs provided by the microservice transaction-service", "1.0", "Free to use", null,
				"API License", null, Collections.emptyList());
	}
}
