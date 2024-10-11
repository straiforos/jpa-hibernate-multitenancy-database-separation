package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.model.Identifiable;


/**
 * Our platforms tenants are companies.
 * Company interface defines our companies we support in our platform.
 */
public interface Company extends Identifiable{
    /*
     * Get the name of the company
     * @return the name of the company
     */
    String getName();
    /*
     * Get the active status of the company
     * @return the active status of the company in our platform
     */
    Boolean getActive();

    /*
     * Get the time zone of the company
     * @return the time zone of the company
     */
    String getTimeZone();

    /**
     * Get the database of the company
     * @return the database of the company
     */
    String getDatabase();
}
