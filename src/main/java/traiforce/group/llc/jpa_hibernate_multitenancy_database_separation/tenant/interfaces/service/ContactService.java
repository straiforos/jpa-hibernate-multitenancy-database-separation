package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.service;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.service.Service;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;

/**
 * Contact service interface to handle our contact services.
 */
public interface ContactService extends Service<Contact, Long> {

}
