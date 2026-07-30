package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {


    @Bean
    OpenAPI customOpenAPI() {

	        return new OpenAPI()
	                .info(new Info()
	                        .title("Employee Management System API")
	                        .version("1.0")
	                        .description("""
	                                Employee Management System REST API provides complete functionality 
	                                for managing employees and departments.

	                                Features include employee and department CRUD operations, 
	                                request validation, global exception handling, pagination, 
	                                sorting, and search functionality.

	                                This API is built using Spring Boot, Spring Data JPA, Hibernate, 
	                                and follows RESTful API design principles.
	                                """));
	    }

}
