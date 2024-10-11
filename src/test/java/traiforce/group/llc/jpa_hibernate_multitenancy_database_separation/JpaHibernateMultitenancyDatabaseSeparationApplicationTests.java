package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(tableExistsInDatabase("companies", "platform_db"));
        assertFalse(tableExistsInDatabase("contacts", "platform_db"));

        // Test tenant database
        String tenantDb = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
        assertFalse(tableExistsInDatabase("companies", tenantDb));
        assertTrue(tableExistsInDatabase("contacts", tenantDb));
    }

    private boolean tableExistsInDatabase(String tableName, String dbName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = ? AND table_catalog = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tableName);
            stmt.setString(2, dbName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}