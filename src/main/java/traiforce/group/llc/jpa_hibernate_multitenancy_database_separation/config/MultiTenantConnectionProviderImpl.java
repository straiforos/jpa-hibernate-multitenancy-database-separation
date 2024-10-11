package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl<Company> {

    @Autowired
    private DataSource defaultDataSource;

    private Map<String, DataSource> dataSources = new HashMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        return defaultDataSource;
    }

    @Override
    protected DataSource selectDataSource(Company company) {
        if (!dataSources.containsKey(company.getDatabase())) {
            dataSources.put(company.getDatabase(), defaultDataSource);
        }
        return dataSources.get(company.getDatabase());
    }
}