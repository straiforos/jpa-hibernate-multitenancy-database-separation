package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.entity;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;


import org.hibernate.annotations.TimeZoneColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private Boolean active;
    @TimeZoneColumn(name = "time_zone")
    private String timeZone;
    @Column(name = "database")
    private String database;
}
