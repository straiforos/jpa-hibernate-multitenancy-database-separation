package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "traiforce.group.llc.jpa_hibernate_multitenancy_database_separation")
public class JpaHibernateMultitenancyDatabaseSeparationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateMultitenancyDatabaseSeparationApplication.class, args);
    }
}