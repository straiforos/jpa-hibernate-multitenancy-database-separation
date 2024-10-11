package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.model;

import lombok.Data;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.platform.interfaces.Company;

@Data
public class CompanyImpl implements Company {
    private Long id;
    private String name;
    private Boolean active;
    private String timeZone;
    private String database;

    @Override
    public String toString(){
        return database;
    }
}
