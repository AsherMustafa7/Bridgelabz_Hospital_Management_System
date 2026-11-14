# Feature 02 – Entity Layer

## 1. Overview

This feature implements the JPA entity classes for the Hospital Management System.

The entity layer represents the application's database structure and defines how Java objects map to MySQL tables.

## 2. Entities Implemented

### 2.1 Role.java

An enum representing the available user roles.

Current value:
- ADMIN

The enum can be extended if additional roles are required in the future.

### 2.2 User.java

Represents a registered user in the application.

Database table: `users`

Fields:
- id
- name
- email
- password
- role

Important configurations:
- Primary key with auto-generated ID.
- Email must be unique.
- Required columns use NOT NULL constraints.
- Role is stored using `EnumType.STRING`.
- A default constructor is provided for JPA.

### 2.3 Patient.java

Represents a patient record.

Database table: `patients`

Fields:
- id
- name
- age
- gender
- disease
- address
- phoneNumber

Important configurations:
- Primary key with auto-generated ID.
- Required fields use NOT NULL constraints.
- Java property `phoneNumber` maps to database column `phone_number`.
- Phone number is represented as a String.

## 3. Technologies Used

- Java 25
- Spring Boot 3.5.16
- Spring Data JPA
- Hibernate
- Jakarta Persistence
- MySQL
- Maven

## 4. Database Mapping

The feature introduces two database tables:

### users

| Column | Description |
|---|---|
| id | Primary key |
| name | User's name |
| email | Unique email address |
| password | Encoded password (encoding implemented in Auth Module) |
| role | User role |

### patients

| Column | Description |
|---|---|
| id | Primary key |
| name | Patient name |
| age | Patient age |
| gender | Patient gender |
| disease | Disease or condition |
| address | Patient address |
| phone_number | Patient phone number |

## 5. JPA Annotations Used

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`
- `@Enumerated`
- `@UniqueConstraint`

## 6. Verification

The following checks are used to verify this feature:

1. Run `mvn clean package`.
2. Start the application using `mvn spring-boot:run`.
3. Confirm the application starts on port 8081.
4. Verify that Hibernate creates or updates the database tables.
5. Inspect the tables using MySQL Workbench.
6. Verify primary keys, column constraints and unique email constraint.

## 7. Scope

This feature implements only the entity layer.

Repositories, DTOs, mappers, authentication logic, JWT configuration, patient CRUD operations and controllers will be implemented in subsequent features.

## 8. Feature Status

In progress – pending build and database verification.
