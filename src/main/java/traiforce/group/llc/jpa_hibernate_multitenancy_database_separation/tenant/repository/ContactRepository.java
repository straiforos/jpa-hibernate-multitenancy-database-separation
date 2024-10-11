package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity.ContactEntity;

/**
 * Contact repository interface to handle our tenants contact entities.
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
