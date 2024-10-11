package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.model.Identifiable;

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
     * Set the first name of the contact
     * @param firstName the first name of the contact
     */
    void setFirstName(String firstName);
    /*
     * Get the last name of the contact
     * @return the last name of the contact
     */
    String getLastName();
    /*
     * Set the last name of the contact
     * @param lastName the last name of the contact
     */
    void setLastName(String lastName);
    /*
     * Get the email of the contact
     * @return the email of the contact
     */
    String getEmail();
    /*
     * Set the email of the contact
     * @param email the email of the contact
     */
    void setEmail(String email);
    /*
     * Get the phone number of the contact
     * @return the phone number of the contact
     */
    String getPhoneNumber();
    /*
     * Set the phone number of the contact
     * @param phoneNumber the phone number of the contact
     */
    void setPhoneNumber(String phoneNumber);
}
