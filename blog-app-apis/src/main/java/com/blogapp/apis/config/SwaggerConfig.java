package com.blogapp.apis.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}

	private List<SecurityReference> sf() {

		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");

		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));// Anonymous array
	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys())).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo getInfo() {

		return new ApiInfo("Blogging appication : Backend course", "This project is developed by me", "3.6",
				"terms of servics",
				new Contact("Jayesh", "https://learncodewithdurgesh.com", "jayeshgautam50@gmail.com"),
				"License Of Apis", "Api License URL", Collections.emptyList());
	}

}
