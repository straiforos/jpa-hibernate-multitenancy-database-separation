package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.service;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.entity.ContactEntity;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save((ContactEntity) contact);
    }

    @Override
    public Contact read(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    @Override
    public Contact update(Long id, Contact contact) throws Exception {
        return contactRepository.save((ContactEntity) contact);
    }

    @Override
    public void delete(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}
