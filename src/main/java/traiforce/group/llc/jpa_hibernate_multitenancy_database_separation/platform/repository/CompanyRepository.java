package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity.CompanyEntity;

/**
 * Company repository interface to handle our platforms company entities.
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
