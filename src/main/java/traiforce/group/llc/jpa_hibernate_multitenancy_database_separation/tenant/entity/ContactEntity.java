package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity;

import lombok.Data;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// TODO make for tenant database
@Data
@Entity
@Table(name = "contacts")
public class ContactEntity implements Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
}
