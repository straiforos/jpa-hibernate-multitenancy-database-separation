package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT = "company_a_db";

    @Value("${spring.datasource.url}")
    private String baseUrl;

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

    public String resolveTenantUrl() {
        String tenant = resolveCurrentTenantIdentifier();
        log.info("Resolved tenant URL: {}", baseUrl.replace("platform_db", tenant));
        return baseUrl.replace("platform_db", tenant);
    }
}