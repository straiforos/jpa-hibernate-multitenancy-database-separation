package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;

import javax.sql.DataSource;

@Slf4j
@Component
public class TenantDatabaseManager {

    @Autowired
    private DataSource dataSource;

    public void createTenantDatabaseIfNotExists(Company company) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String databaseName = company.getDatabase();

        try {
            // Check if the database exists
            String checkDbSql = "SELECT 1 FROM pg_database WHERE datname = ?";
            Integer result = jdbcTemplate.queryForObject(checkDbSql, Integer.class, databaseName);
            boolean databaseExists = (result != null && result == 1);

            if (!databaseExists) {
                // Create the database if it doesn't exist
                String createDbSql = "CREATE DATABASE " + databaseName;
                jdbcTemplate.execute(createDbSql);
                log.info("Created database: {}", databaseName);
            } else {
                log.info("Database {} already exists", databaseName);
            }
        } catch (Exception e) {
            log.error("Error creating database for company {}: ", company.getName(), e);
            throw new RuntimeException("Failed to create database for company: " + company.getName(), e);
        }
    }
}