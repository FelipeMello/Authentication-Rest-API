package com.felipemello.auth.config;

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
public class Swagger2Config {
    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
	return new ApiInfo("Authentication API", "REST API for learning purposes", "API TOS", "Terms of service",
		new Contact("Felipe Silva de Mello", "https://www.linkedin.com/in/felipemelloit",
			"felipe.silva.mello@gmail.com"),
		"Free API", "API URL", Collections.emptyList());
    }
}
