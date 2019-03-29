package Repository;

import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity> implements IRepository<T> {

    private Map<String, T> storage = new HashMap<>();
    private IValidator<T> validator;

    /**
     * Instantiates a repository for entity.
     * @param validator is the validator used.
     */

    public InMemoryRepository(IValidator<T> validator) { this.validator = validator; }

    /**
     * Finds an entity by ID.
     * @param id is the ID of the entity to find.
     * @return the entity with the given ID if it exists.
     */

    public T findById(String id) { return storage.get(id); }

    /**
     * Adds an entity to storage.
     * @param entity is the entity to add.
     */

    public void insert(T entity) {
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    /**
     * Updates an entity from storage if it already exists.
     * @param entity is the entity to update.
     */

    public void update(T entity) {
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    /**
     * Removes an entity from storage.
     * @param id is the ID of the entity to remove.
     * @throws InMemoryRepositoryException if there is no entity with the given ID.
     */

    public void remove(String id) {
        if (!storage.containsKey(id))
            throw new InMemoryRepositoryException("There is no entity with the given ID to remove!");
        storage.remove(id);
    }

    /**
     * @return a list of all entities.
     */

    public List<T> getAll() { return new ArrayList<>(storage.values()); }

    public Map<String, T> getStorage() { return storage; }

}
