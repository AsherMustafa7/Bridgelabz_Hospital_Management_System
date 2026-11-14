# Feature 03 – Repository Layer

## Overview

This feature implements the repository layer of the Hospital Management System using Spring Data JPA.

The repository layer is responsible for communicating with the database. It separates database access logic from the service and controller layers.

## Objectives

- Implement UserRepository.
- Implement PatientRepository.
- Use Spring Data JPA's JpaRepository.
- Understand automatically generated CRUD operations.
- Implement derived query methods.
- Use Optional to handle potentially missing users.

## Package Structure

```text
repository/
├── UserRepository.java
└── PatientRepository.java
```

## 1. UserRepository

UserRepository extends:

`JpaRepository<User, Long>`

It provides inherited CRUD operations and the following custom query methods:

| Method | Description |
|---|---|
| findByEmail(email) | Finds a user using their email |
| existsByEmail(email) | Checks whether an email is already registered |

The findByEmail method returns Optional<User> to handle cases where no matching user exists.

## 2. PatientRepository

PatientRepository extends:

`JpaRepository<Patient, Long>`

It inherits standard CRUD operations for the Patient entity.

No custom query methods are currently required.

## 3. Spring Data JPA

Spring Data JPA automatically creates repository implementations at runtime.

The application does not need manually implemented repository classes for these operations.

It provides methods such as:

- save()
- findById()
- findAll()
- existsById()
- deleteById()
- count()

## 4. Derived Query Methods

Spring Data JPA can derive database queries from repository method names.

For example:

`findByEmail(String email)`

This method retrieves a user whose email matches the supplied value.

`existsByEmail(String email)`

This method checks whether a matching user exists.

## 5. Architecture

The intended application flow is:

Controller → Service → Repository → Database

- Controller handles HTTP requests.
- Service contains business logic.
- Repository handles database access.
- MySQL stores application data.

## 6. Testing and Verification

Run the Maven build:

`mvn clean package`

Start the application after loading the local environment variables:

`mvn spring-boot:run`

Verify that the application starts successfully and that Hibernate can access the configured database.

No Postman endpoints are introduced in this feature. API testing will be performed when the controller layer is implemented.

## 7. Dependencies

No additional dependencies were required.

The project already includes Spring Data JPA and the MySQL Connector/J driver.

## Status

Repository layer implemented for User and Patient entities.
