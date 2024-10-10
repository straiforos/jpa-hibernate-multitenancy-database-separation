# Multi-Tenant Database Management with Spring Boot

This project demonstrates a Spring Boot application using JPA/Hibernate to manage multiple tenant databases and a platform database. It showcases database creation, seeding, and updating for both tenant-specific and shared platform data.

## Features

- Multi-tenant database support
- Platform (shared) database management
- Automatic database creation and schema generation
- Data seeding for initial setup
- Database migration and updates

## Technologies

- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL

## Project Structure
src/
├── main/
│ ├── java/
│ │ └── com/
│ │ └── example/
│ │ └── multitenant/
│ │ ├── config/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ └── MultiTenantApplication.java
│ └── resources/
│ ├── db/
│ │ └── migration/
│ └── application.properties
└── test/
└── java/
└── com/
└── example/
└── multitenant/

## Setup and Configuration

1. Clone the repository
2. Configure your database connection in `application.properties`
3. Run the application using `./mvnw spring-boot:run`

## Usage

Explain how to use the application, including:
- How to add a new tenant
- How to perform operations on tenant-specific data
- How to manage shared platform data

## Testing

`mvn test`
