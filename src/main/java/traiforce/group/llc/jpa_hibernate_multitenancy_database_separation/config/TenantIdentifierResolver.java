package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT = "default";

    @Override
    public String resolveCurrentTenantIdentifier() {
        // TODO: Implement logic to determine the current tenant
        // This could be based on a ThreadLocal, request parameter, or other mechanism
        return DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}