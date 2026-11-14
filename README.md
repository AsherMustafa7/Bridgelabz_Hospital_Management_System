
# Hospital Management System

A backend REST API application developed using Java, Spring Boot, Spring Security, and MySQL.

The application focuses on implementing secure REST APIs, managing hospital-related data, handling authentication and authorization, and following maintainable backend development practices.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 25 | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | ORM implementation |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| MySQL | Relational database |
| Maven | Dependency and build management |
| JUnit 5 | Unit testing |
| Mockito | Mocking dependencies during testing |
| Postman | API testing |

## Project Architecture

The application follows a layered architecture:

```text
Client (Postman)
      |
      v
Controller Layer
      |
      v
Service Interface
      |
      v
Service Implementation
      |
      v
Repository Layer
      |
      v
MySQL Database
```

DTOs and Mapper classes handle data transfer and conversion between API requests/responses and database entities.

## Project Structure

```text
Hospital-Management-System/
│
├── docs/
│   └── features/
│       └── 01-project-setup/
│           └── README.md
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/asher/hospitalmanagement/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── mapper/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       ├── service/
│   │   │       │   ├── interfaces/
│   │   │       │   └── implementations/
│   │   │       ├── validation/
│   │   │       └── HospitalManagementApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/asher/hospitalmanagement/
│
├── .gitignore
├── pom.xml
└── README.md
```

## Coding Standards

The project follows these development standards:

- Maintain separation of concerns using layered architecture.
- Use DTOs instead of exposing JPA entities directly.
- Use Mapper classes for Entity-to-DTO conversions.
- Prefer constructor-based dependency injection.
- Define service interfaces and separate implementation classes.
- Validate incoming request data.
- Implement centralized exception handling.
- Use appropriate HTTP methods and response status codes.
- Apply database constraints wherever required.
- Keep credentials and sensitive configuration outside source control.
- Add meaningful logging.
- Write unit tests for business logic.

## Database Configuration

The project uses MySQL with the following default schema:

`hospital_management`

Database connection details are supplied through environment variables:

| Variable | Description |
|---|---|
| `DB_URL` | MySQL JDBC connection URL |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |

## Running the Application

### Prerequisites

- JDK 25
- Apache Maven
- MySQL Server

### 1. Create the database

```sql
CREATE DATABASE hospital_management;
```

### 2. Configure environment variables

Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in your environment.

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The application will run at:

`http://localhost:8080`

## API Testing

Postman will be used to:

- Send HTTP requests.
- Test REST API endpoints.
- Verify request validation.
- Check response status codes and response bodies.
- Test authentication and authorization.
- Verify JWT-protected endpoints.

## Development Roadmap

Features will be developed independently using Git feature branches.

Each feature will include its own implementation, testing, and documentation.

## Git Workflow

```text
main
  |
  v
develop
  |
  v
feature/<feature-name>
  |
  v
Implementation + Testing + Documentation
  |
  v
Merge into develop
```

## Project Status

Currently in development.

**Current Feature:** Feature 01 - Project Setup and Initial Configuration
