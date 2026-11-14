
# Feature 01 - Project Setup and Initial Configuration

## Overview

This feature establishes the initial foundation of the Hospital Management System backend using Java 25 and Spring Boot.

It defines the Maven configuration, application entry point, project package structure, database configuration, and initial development standards.

## Objectives

- Initialize the Spring Boot application.
- Configure Maven and required dependencies.
- Establish a layered package structure.
- Configure MySQL database connectivity.
- Configure application properties.
- Establish Git ignore rules.
- Document the initial project structure.

## Technology Used

- Java 25
- Spring Boot 3.5.16
- Maven
- MySQL
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- JUnit 5
- Mockito

## Implementation Details

### 1. Maven Configuration

Configured `pom.xml` with:

- Spring Boot parent dependency management.
- Java 25 compatibility.
- Spring Web.
- Spring Data JPA.
- MySQL Connector/J.
- Spring Security.
- Spring Validation.
- Spring Boot Test.
- Spring Security Test.
- Spring Boot Maven Plugin.

### 2. Project Structure

Established packages for:

| Package | Responsibility |
|---|---|
| config | Application and framework configuration |
| controller | REST API endpoints |
| dto | Request and response objects |
| entity | JPA database entities |
| exception | Custom exceptions and exception handling |
| mapper | Entity and DTO conversions |
| repository | Database access |
| security | Authentication and authorization |
| service/interfaces | Service contracts |
| service/implementations | Business logic |
| validation | Custom validation components |

### 3. Application Entry Point

Created `HospitalManagementApplication.java` with `@SpringBootApplication`.

This annotation enables component scanning, auto-configuration, and Spring configuration.

### 4. Database Configuration

Configured MySQL connectivity through environment variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

The default database schema is `hospital_management`.

### 5. Application Configuration

Configured:

- Application name.
- Server port.
- MySQL connection.
- Hibernate and JPA settings.
- SQL formatting.
- Application logging.

### 6. Git Configuration

Created `.gitignore` to exclude:

- Maven build output.
- Compiled Java files.
- IDE configuration files.
- Logs and temporary files.
- Environment files containing secrets.

## Verification

The following commands are used to verify the Maven project:

```bash
mvn -version
mvn clean
mvn test
mvn package
```

The application will also be verified by starting the Spring Boot server.

## Expected Outcome

A properly configured Spring Boot backend project with:

- A consistent package structure.
- Maven dependency management.
- MySQL connection configuration.
- A working Spring Boot entry point.
- Initial documentation and Git configuration.

## Feature Status: Completed