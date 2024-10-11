package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.model;

import lombok.Data;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;

@Data
public class ContactImpl implements Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
