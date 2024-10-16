package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.repository.ContactRepository;

import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MultiTenantConfig
 * 
 * This class is used to configure the multi-tenant data source, entity manager,
 * and transaction manager.
 * 
 * @author traiforce.group.llc
 * @version 1.0
 * @since 2024-10-01
 */
@Configuration
@EnableJpaRepositories(
    basePackageClasses = ContactRepository.class,
    entityManagerFactoryRef = "tenantEntityManager",
    transactionManagerRef = "tenantTransactionManager"
)
public class MultiTenantConfig {

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "tenantDataSource")
    public DataSource tenantDataSource() {
        return DataSourceBuilder.create()
                .url(hibernateProperties.getTenantUrl())
                .username(hibernateProperties.getUsername())
                .password(hibernateProperties.getPassword())
                .driverClassName(hibernateProperties.getDriverClass())
                .build();
    }

    @Bean(name = "tenantEntityManager")
    public LocalContainerEntityManagerFactoryBean tenantEntityManager(
            @Qualifier("tenantDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties.getTenantProperties());

        return em;
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManager") LocalContainerEntityManagerFactoryBean tenantEntityManager) {
        return new JpaTransactionManager(tenantEntityManager.getObject());
    }

    @Bean(name = "tenantEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(
            @Qualifier("tenantDataSource") DataSource dataSource,
            MultiTenantConnectionProviderImpl multiTenantConnectionProvider,
            TenantIdentifierResolver currentTenantIdentifierResolver) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        properties.putAll(hibernateProperties.getTenantProperties());

        em.setJpaProperties(properties);
        return em;
    }
}
