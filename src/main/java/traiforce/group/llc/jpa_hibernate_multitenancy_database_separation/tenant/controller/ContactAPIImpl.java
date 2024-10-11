package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.controller.ContactAPI;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.model.Contact;
import traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.tenant.interfaces.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactAPIImpl implements ContactAPI {
    private final ContactService contactService;

    public ContactAPIImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Contact> get(@PathVariable Long id) {
        Contact contact = contactService.read(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Contact> post(@RequestBody Contact entity) {
        Contact createdContact = contactService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Contact> put(@PathVariable Long id, @RequestBody Contact entity) {
        try {
            Contact updatedContact = contactService.update(id, entity);
            return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Contact> patch(@PathVariable Long id, @RequestBody Contact partialEntity) {
        Contact existingContact = contactService.read(id);

        // TODO use JSON Merge Patch RFC 7396
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Update only non-null fields from partialEntity
        if (partialEntity.getFirstName() != null) {
            existingContact.setFirstName(partialEntity.getFirstName());
        }

        if (partialEntity.getLastName() != null) {
            existingContact.setLastName(partialEntity.getLastName());
        }

        if (partialEntity.getEmail() != null) {
            existingContact.setEmail(partialEntity.getEmail());
        }
        
        if (partialEntity.getPhoneNumber() != null) {
            existingContact.setPhoneNumber(partialEntity.getPhoneNumber());
        }

        try {
            Contact updatedContact = contactService.update(id, existingContact);
            return ResponseEntity.ok(updatedContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
