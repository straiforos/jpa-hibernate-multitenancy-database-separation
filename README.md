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

Explain how to use the application, including:
- How to add a new tenant
- How to perform operations on tenant-specific data
- How to manage shared platform data

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
