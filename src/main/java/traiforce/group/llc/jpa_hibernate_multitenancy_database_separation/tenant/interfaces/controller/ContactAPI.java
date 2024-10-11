package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.controller;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.controller.RESTAPI;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;

/**
 * Contact API interface to handle our contact REST API requests.
 */
public interface ContactAPI extends RESTAPI<Contact, Long> {
}
