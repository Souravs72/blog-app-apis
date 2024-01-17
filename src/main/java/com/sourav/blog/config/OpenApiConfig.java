package com.sourav.blog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Sourav", email = "souravsingh2609@gmail.com", url = "https://souravsingh2609.com"), description = "OpenAPI documentation for Blog Application", title = "Blogging Application: REST API", version = "1.0", license = @License(name = "Apache License", url = "https://souravsingh2609.com"), termsOfService = "Terms ofService"), servers = {
		@Server(description = "Local ENV", url = "http://localhost:8085"),
		@Server(description = "PROD ENV", url = "http://localhost:8080") }, security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
@Configuration
public class OpenApiConfig {

}
