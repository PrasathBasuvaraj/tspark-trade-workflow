# Spring Boot Starter Template Repository

Welcome to the Spring Boot Starter Template Repository! This project provides a ready-to-use Spring Boot Starter setup,
helping you jumpstart new applications quickly and efficiently.

## Getting Started

- Click the “Use this template” button at the top of this repository’s page.
- Provide a name for your new repository and choose whether it should be public or private.
- Click “Create repository from template” to generate your own copy.
 
## Clone Your New Repository

- Open your new repository on GitHub.
- Click the “Code” button and copy the repository URL.
- Run git clone <your-repo-url> in your terminal to clone the code locally.

## Customize Project Settings

- Update the build.gradle (Gradle) file with your group ID, artifact ID, or any relevant project information.
- If necessary, rename packages or modules to suit your organization’s naming conventions.

## Build and Run the Application

```shell
./gradlew build bootRun
```

Open your browser and goto [API Documentation](http://localhost:8080/swagger-ui/index.html)

- Access the running application by opening your browser at http://localhost:8080 (unless you changed the port).

## Starter Template Features

This starter template comes with below features.

- Spring Boot Web, Validation, JPA, and Actuator
- SpringDoc OpenAPI with Swagger UI
- Lombok for reducing boilerplate code
- MapStruct for DTO-to-Entity conversion
- H2 In-Memory Database for testing
- Stock Portfolio CRUD with Service, Repo, Controller
- Global Exception Handling with @ControllerAdvice
- JUnit 5 & Mockito for unit testing
- Feature-based folder structure