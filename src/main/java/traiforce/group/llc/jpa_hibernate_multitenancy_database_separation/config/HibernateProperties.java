package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.hibernate.cfg.AvailableSettings;

import java.util.Properties;

/**
 * HibernateProperties
 * 
 * This class is used to load the Hibernate properties from the properties file.
 * @author traiforce.group.llc
 * @version 1.0
 * @since 2024-10-01
 */
@Configuration
@PropertySource("classpath:hibernate.properties")
public class HibernateProperties {

    @Value("${hibernate.platform.connection.url}")
    private String platformUrl;

    @Value("${hibernate.platform.connection.username}")
    private String platformUsername;

    @Value("${hibernate.platform.connection.password}")
    private String platformPassword;

    @Value("${hibernate.platform.connection.driver_class}")
    private String platformDriverClass;

    @Value("${hibernate.tenant.connection.url}")
    private String tenantUrl;

    @Value("${hibernate.tenant.connection.username}")
    private String tenantUsername;

    @Value("${hibernate.tenant.connection.password}")
    private String tenantPassword;

    @Value("${hibernate.tenant.connection.driver_class}")
    private String tenantDriverClass;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    public Properties getPlatformProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", platformUrl);
        props.setProperty("hibernate.connection.username", platformUsername);
        props.setProperty("hibernate.connection.password", platformPassword);
        props.setProperty("hibernate.connection.driver_class", platformDriverClass);
        props.setProperty("hibernate.dialect", dialect);
        props.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        props.setProperty("hibernate.show_sql", showSql);
        props.setProperty("hibernate.format_sql", formatSql);
        return props;
    }

    public Properties getTenantProperties() {
        Properties props = new Properties();
        props.setProperty(AvailableSettings.JAKARTA_JDBC_URL, tenantUrl);
        props.setProperty(AvailableSettings.JAKARTA_JDBC_USER, tenantUsername);
        props.setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, tenantPassword);
        props.setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, tenantDriverClass);
        props.setProperty(AvailableSettings.DIALECT, dialect);
        props.setProperty(AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION, hbm2ddlAuto);
        props.setProperty(AvailableSettings.SHOW_SQL, showSql);
        props.setProperty(AvailableSettings.FORMAT_SQL, formatSql);
        return props;
    }

    // Getters for individual properties
    public String getPlatformUrl() {
        return platformUrl;
    }

    public String getPlatformUsername() {
        return platformUsername;
    }

    public String getPlatformPassword() {
        return platformPassword;
    }

    public String getPlatformDriverClass() {
        return platformDriverClass;
    }

    public String getTenantUrl() {
        return tenantUrl;
    }

    public String getTenantUsername() {
        return tenantUsername;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public String getTenantDriverClass() {
        return tenantDriverClass;
    }

    public String getDialect() {
        return dialect;
    }

    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }
}
