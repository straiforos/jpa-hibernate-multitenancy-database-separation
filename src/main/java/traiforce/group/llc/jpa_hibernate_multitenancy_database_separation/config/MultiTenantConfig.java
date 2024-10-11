package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
    basePackages = "traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.repository",
    entityManagerFactoryRef = "tenantEntityManager",
    transactionManagerRef = "tenantTransactionManager"
)
public class MultiTenantConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean formatSql;

    @Bean(name = "tenantDataSource")
    public DataSource tenantDataSource() {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
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

        return em;
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManager") LocalContainerEntityManagerFactoryBean tenantEntityManager) {
        return new JpaTransactionManager(tenantEntityManager.getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("tenantDataSource") DataSource dataSource,
            MultiTenantConnectionProvider<Company> multiTenantConnectionProvider,
            CurrentTenantIdentifierResolver<Company> currentTenantIdentifierResolver
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.SHOW_SQL, showSql);
        properties.put(Environment.FORMAT_SQL, formatSql);
        properties.put(Environment.HBM2DDL_AUTO, ddlAuto);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaPropertyMap(properties);

        return em;
    }
}