# Multi-Tenant Database Management with Spring Boot

This project is a proof of concept exploration using a Spring Boot application with JPA/Hibernate to manage multiple tenant databases and a platform database. It showcases database creation, seeding, and updating for both tenant-specific and shared platform data.

## Disclaimer

**Important:** During the development of this project, it was discovered that Hibernate does not natively support schema export for multitenancy when using the separate database approach. This limitation makes Hibernate less suitable for this specific use case.

As a result, we are planning to explore Flyway as an alternative solution for managing database schemas in a multi-tenant environment. Flyway provides more robust support for database migrations and schema management across multiple databases.

Future updates to this project will likely involve integrating Flyway for improved multi-tenant database management.

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
- Flyway
- PostgreSQL

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── traiforce/
│   │       └── group/
│   │           └── llc/
│   │               └── jpa_hibernate_multitenancy_database_separation/
│   │                   ├── config/
│   │                   ├── platform/
│   │                   │   ├── model/
│   │                   │   ├── repository/
│   │                   │   └── service/
│   │                   ├── tenant/
│   │                   │   ├── model/
│   │                   │   ├── repository/
│   │                   │   └── service/
│   │                   └── JpaHibernateMultitenancyDatabaseSeparationApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
    └── java/
        └── traiforce/
            └── group/
                └── llc/
                    └── jpa_hibernate_multitenancy_database_separation/
                        ├── platform/
                        └── tenant/

## Setup and Configuration

1. Clone the repository
2. Configure your database connection in `application.properties`
3. Run the application using `./mvnw spring-boot:run`

## Usage

### Prerequisites
- Docker and Docker Compose
- JDK 11+
- Maven

### Quick Start
1. Start PostgreSQL:
   ```bash
   docker-compose up -d
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Adding Tenants

Tenants (companies) are automatically added to the platform database when the application starts, using the `DatabaseSeeder` class. Here's how it works:

1. The `DatabaseSeeder` class is annotated with `@Component`, making it a Spring-managed bean.

2. It implements `CommandLineRunner`, which allows it to execute code when the application starts.

3. The `seedCompanies()` method checks if there are any existing companies in the database:

   ```java
   if (companyRepository.count() == 0) {
       // Seed companies if none exist
   } else {
       System.out.println("Companies already exist, skipping seeding.");
   }
   ```

4. If no companies exist, it creates a list of `CompanyEntity` objects:

   ```java
   List<CompanyEntity> companies = Arrays.asList(
       new CompanyEntity("Company A", true, "America/New_York", "company_a_db"),
       new CompanyEntity("Company B", true, "America/Chicago", "company_b_db"),
       new CompanyEntity("Company C", true, "America/Los_Angeles", "company_c_db")
   );
   ```

5. These companies are then saved to the database:

   ```java
   companyRepository.saveAll(companies);
   ```

To add more tenants or modify the initial set of tenants, you can update the `seedCompanies()` method in the `DatabaseSeeder` class. For example, to add a new tenant:

## Testing

`mvn test`
