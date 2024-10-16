package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.config.TenantIdentifierResolver;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JpaHibernateMultitenancyDatabaseSeparationApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    @Test
    void testDatabaseSchemas() throws SQLException {
        // Test platform database
        log.info("Testing platform_db schema");
        assertTrue(tableExistsInDatabase("companies", "platform_db"), "companies table should exist in platform_db");
        assertFalse(tableExistsInDatabase("contacts", "platform_db"), "contacts table should not exist in platform_db");

        // Test tenant database
        String tenantDb = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
        log.info("Testing tenant database: {}", tenantDb);
        assertFalse(tableExistsInDatabase("companies", tenantDb), "companies table should not exist in " + tenantDb);
        assertTrue(tableExistsInDatabase("contacts", tenantDb), "contacts table should exist in " + tenantDb);
    }

    private boolean tableExistsInDatabase(String tableName, String dbName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = ? AND table_catalog = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            stmt.setString(2, dbName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean exists = rs.getInt(1) > 0;
                    log.info("Table '{}' in database '{}' exists: {}", tableName, dbName, exists);
                    return exists;
                }
            }
        }
        log.warn("Failed to check if table '{}' exists in database '{}'", tableName, dbName);
        return false;
    }

    @Test
    void listTablesInDatabases() {
        try {
            log.info("Starting listTablesInDatabases test");
            listTablesInDatabase("platform_db");
            String tenantDb = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            log.info("Tenant database identified as: {}", tenantDb);
            listTablesInDatabase(tenantDb);
            log.info("Finished listTablesInDatabases test");
        } catch (SQLException e) {
            log.error("Error in listTablesInDatabases test", e);
            fail("Test failed due to SQL exception: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error in listTablesInDatabases test", e);
            fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }

    private void listTablesInDatabase(String dbName) throws SQLException {
        log.info("Listing tables for database: {}", dbName);
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_catalog = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dbName);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean tablesFound = false;
                while (rs.next()) {
                    tablesFound = true;
                    log.info("- {}", rs.getString(1));
                }
                if (!tablesFound) {
                    log.warn("No tables found in database '{}'", dbName);
                }
            }
        } catch (SQLException e) {
            log.error("Error listing tables for database '{}'", dbName, e);
            throw e;
        }
    }
}