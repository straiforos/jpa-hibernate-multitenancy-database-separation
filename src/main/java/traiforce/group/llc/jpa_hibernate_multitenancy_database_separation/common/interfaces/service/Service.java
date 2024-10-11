package traiforce.group.llc.jpa_hibernate_multitenancy_database_separation.common.interfaces.service;

/**
 * Service interface to handle our services.
 * Supports create, read, update, delete.
 */
public interface Service<T, I> {
    // TODO create, read, update, delete

    /**
     * Create a new entity.
     * @param entity The entity to create.
     * @return The created entity.
     */
    T create(T entity);

    /**
     * Read an entity by its ID.
     * @param id The ID of the entity to read.
     * @return The read entity.
     */
    T read(I id);

    /**
     * Update an entity by its ID.
     * @param id The ID of the entity to update.
     * @param entity The entity to update.
     * @return The updated entity.
     */
    T update(I id, T entity) throws Exception;

    /**
     * Delete an entity by its ID.
     * @param id The ID of the entity to delete.
     */
    void delete(I id);
}

