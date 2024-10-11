package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.controller;

import org.springframework.http.ResponseEntity;

/**
 * REST API interface to handle our REST API requests.
 * Supports GET, POST, PUT, DELETE, PATCH, etc.
 */
public interface RESTAPI<T, I> {

    /**
     * GET request to retrieve an entity by its ID.
     * @param id The ID of the entity to retrieve.
     * @return ResponseEntity containing the retrieved entity.
     */
    ResponseEntity<T> get(I id);

    /**
     * POST request to create a new entity.
     * @param entity The entity to create.
     * @return ResponseEntity containing the created entity.
     */
    ResponseEntity<T> post(T entity);

    /**
     * PUT request to update an existing entity.
     * @param id The ID of the entity to update.
     * @param entity The updated entity.
     * @return ResponseEntity containing the updated entity.
     */
    ResponseEntity<T> put(I id, T entity);

    /**
     * DELETE request to remove an entity.
     * @param id The ID of the entity to delete.
     * @return ResponseEntity with no content.
     */
    ResponseEntity<Void> delete(I id);

    /**
     * PATCH request to partially update an existing entity.
     * @param id The ID of the entity to update.
     * @param partialEntity The partial entity with updated fields.
     * @return ResponseEntity containing the updated entity.
     */
    ResponseEntity<T> patch(I id, T partialEntity);
}
