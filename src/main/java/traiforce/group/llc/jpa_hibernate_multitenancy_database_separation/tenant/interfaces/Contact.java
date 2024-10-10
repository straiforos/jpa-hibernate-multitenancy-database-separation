package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.Identifiable;

/**
 * Contact interface for a tenant
 */
public interface Contact extends Identifiable{
    /*
     * Get the first name of the contact
     * @return the first name of the contact
     */
    String getFirstName();
    /*
     * Get the last name of the contact
     * @return the last name of the contact
     */
    String getLastName();
    /*
     * Get the email of the contact
     * @return the email of the contact
     */
    String getEmail();
    /*
     * Get the phone number of the contact
     * @return the phone number of the contact
     */
    String getPhoneNumber();
}
