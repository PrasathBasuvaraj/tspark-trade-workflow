package org.techspark.starter.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(
        title = "Spring Boot-based trade approval workflow powered by Flowable BPM.",
        version = "1.0",
        description = "Demo repository showcasing a Spring Boot-based trade approval workflow powered by Flowable BPM. Designed to demonstrate lightweight workflow automation using BPMN with service and user tasks.",
        contact = @Contact(name = "Support Team", email = "codrixtechtrainer@gmail.com"),
        license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT")
))
public class OpenAPIConfig {
}
