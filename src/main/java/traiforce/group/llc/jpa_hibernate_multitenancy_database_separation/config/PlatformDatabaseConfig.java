package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * PlatformDatabaseConfig
 * 
 * This class is used to configure the platform data source, entity manager, and transaction manager.
 * @author traiforce.group.llc
 * @version 1.0
 * @since 2024-10-01
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.repository",
    entityManagerFactoryRef = "platformEntityManager",
    transactionManagerRef = "platformTransactionManager"
)
public class PlatformDatabaseConfig {

    @Autowired
    private HibernateProperties hibernateProperties;

    @Primary
    @Bean(name = "platformDataSource")
    public DataSource platformDataSource() {
        return DataSourceBuilder.create()
            .url(hibernateProperties.getPlatformUrl())
            .username(hibernateProperties.getPlatformUsername())
            .password(hibernateProperties.getPlatformPassword())
            .driverClassName(hibernateProperties.getPlatformDriverClass())
            .build();
    }

    @Primary
    @Bean(name = "platformEntityManager")
    public LocalContainerEntityManagerFactoryBean platformEntityManager(
            @Qualifier("platformDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties.getPlatformProperties());

        return em;
    }

    @Primary
    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("platformEntityManager") LocalContainerEntityManagerFactoryBean platformEntityManager) {
        return new JpaTransactionManager(platformEntityManager.getObject());
    }
}
