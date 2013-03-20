package springapp.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springapp.domain.SimpleEntity;

/**
 * DAO layer (CRUD operations) for {@link webapp.domain.SimpleEntity}.
 *
 * Methods aren't transactional
 *
 * @author Eugene Khrustalev <eugene.khrustalev@gmail.com>
 */
@Repository
public class SimpleEntityRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Find objects by id
     *
     * @param id object identifier
     * @return object with specified identifier or null
     */
    public SimpleEntity findById(Long id) {
        try {
            return em.find(SimpleEntity.class, id);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * Find all SimpleEntity objects in database
     *
     * @return list of objects
     */
    public List<SimpleEntity> findAll() {
        return em.createQuery("SELECT e FROM SimpleEntity e").getResultList();
    }

    /**
     * Create a new SimpleEntity object into database
     *
     * @param entity object to create
     * @return object
     */
    public SimpleEntity create(SimpleEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Update a SimpleEntity object in database
     *
     * @param entity object to update
     * @return object
     */
    public SimpleEntity update(SimpleEntity entity) {
        return em.merge(entity);
    }

    /**
     * Remove a SimpleEntity object from database
     *
     * @param entity object to remove
     */
    public void remove(SimpleEntity entity) {
        em.merge(entity); // attach entity
        em.remove(entity); // and remove it
    }
}
