package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;


import org.hibernate.annotations.TimeZoneColumn;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

// TODO make this a platform entity
// TODO make entity interface to contain annotations keeping our classes clean
@Entity
@Table(name = "companies")
@Data
public class CompanyEntity implements Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean active;
    @TimeZoneColumn(name = "time_zone")
    private String timeZone;
    private String database;
}
