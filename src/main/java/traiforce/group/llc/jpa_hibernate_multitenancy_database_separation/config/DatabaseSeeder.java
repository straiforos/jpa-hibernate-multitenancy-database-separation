package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity.CompanyEntity;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.repository.CompanyRepository;

import java.util.Arrays;
import java.util.List;

/**
 * DatabaseSeeder is responsible for seeding the platform database with companies
 * and initializing the tenant databases for each company.
 */
@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TenantDatabaseManager tenantDatabaseManager;

    @Override
    public void run(String... args) {
        try {
            seedCompanies();
            initializeTenantDatabases();
        } catch (DataAccessException e) {
            log.error("Error occurred during database seeding: ", e);
        }
    }

    /**
     * Seeds companies into the platform database.
     */
    private void seedCompanies() {
        try {
            long companyCount = companyRepository.count();
            if (companyCount == 0) {
                log.info("Seeding companies into the platform database");
                List<CompanyEntity> companies = Arrays.asList(
                    new CompanyEntity("Company A", true, "America/New_York", "company_a_db"),
                    new CompanyEntity("Company B", true, "America/Chicago", "company_b_db"),
                    new CompanyEntity("Company C", true, "America/Los_Angeles", "company_c_db")
                );

                companyRepository.saveAll(companies);
                log.info("Seeded {} companies into the platform database", companies.size());
            } else {
                log.info("Found {} existing companies in the platform database, skipping seeding", companyCount);
            }
        } catch (DataAccessException e) {
            log.error("Error occurred while seeding companies: ", e);
            throw e;
        }
    }

    /**
     * Initializes the tenant databases by creating them for all existing companies.
     */
    private void initializeTenantDatabases() {
        log.info("Initializing tenant databases");
        try {
            List<CompanyEntity> companies = companyRepository.findAll();
            log.info("Found {} companies to initialize databases for", companies.size());
            
            for (CompanyEntity company : companies) {
                try {
                    tenantDatabaseManager.createTenantDatabaseIfNotExists(company);
                    log.info("Initialized database for company: {}", company.getName());
                } catch (Exception e) {
                    log.error("Error initializing database for company {}: ", company.getName(), e);
                }
            }
            
            log.info("Finished initializing tenant databases");
        } catch (DataAccessException e) {
            log.error("Error occurred while initializing tenant databases: ", e);
            throw e;
        }
    }
}