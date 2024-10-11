package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity.CompanyEntity;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.repository.CompanyRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(String... args) {
        seedCompanies();
    }

    // TODO make it dynamic and detect missing companies
    // TODO make it configurable
    private void seedCompanies() {
        if (companyRepository.count() == 0) {
            List<CompanyEntity> companies = Arrays.asList(
                new CompanyEntity("Company A", true, "America/New_York", "company_a_db"),
                new CompanyEntity("Company B", true, "America/Chicago", "company_b_db"),
                new CompanyEntity("Company C", true, "America/Los_Angeles", "company_c_db")
            );

            companyRepository.saveAll(companies);
            System.out.println("Companies seeded successfully.");
        } else {
            System.out.println("Companies already exist, skipping seeding.");
        }
    }
}