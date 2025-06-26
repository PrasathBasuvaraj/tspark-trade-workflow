package org.techspark.starter.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(
        title = "TechSpark SpringBoot Starter Template",
        version = "1.0",
        description = "The TechSpark SpringBoot Starter Template is a pre-configured boilerplate designed to accelerate Spring Boot application development by integrating essential libraries, best practices.",
        contact = @Contact(name = "Support Team", email = "codrixtechtrainer@gmail.com"),
        license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT")
))
public class OpenAPIConfig {
}
