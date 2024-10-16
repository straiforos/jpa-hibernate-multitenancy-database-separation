package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TenantIdentifierResolver
 * 
 * This class is used to resolve the current tenant identifier.
 * @author traiforce.group.llc
 * @version 1.0
 * @since 2024-10-01
 */
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Autowired
    private HibernateProperties hibernateProperties;

    @Override
    public String resolveCurrentTenantIdentifier() {
        // For simplicity, we're returning a default tenant.
        // In a real application, you'd determine the current tenant based on the request or user context.
        return "default";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    public String resolveTenantUrl() {
        // Use the tenant URL from HibernateProperties
        return hibernateProperties.getTenantUrl();
    }
}
