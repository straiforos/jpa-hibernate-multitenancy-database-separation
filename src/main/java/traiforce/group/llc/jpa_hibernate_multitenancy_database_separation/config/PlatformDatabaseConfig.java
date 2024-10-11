package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = "traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.repository",
    entityManagerFactoryRef = "platformEntityManager",
    transactionManagerRef = "platformTransactionManager"
)
public class PlatformDatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private boolean formatSql;

    @Primary
    @Bean(name = "platformDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.platform")
    public DataSource platformDataSource() {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
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

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(Environment.HBM2DDL_AUTO, ddlAuto);
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.SHOW_SQL, showSql);
        properties.put(Environment.FORMAT_SQL, formatSql);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("platformEntityManager") LocalContainerEntityManagerFactoryBean platformEntityManager) {
        return new JpaTransactionManager(platformEntityManager.getObject());
    }
}