package springapp.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springapp.domain.SimpleEntity;
import springapp.repository.SimpleEntityRepository;

/**
 * Service layer for {@link webapp.domain.SimpleEntity} objects.
 *
 * Methods required transactions.
 *
 * @author Eugene Khrustalev <eugene.khrustalev@gmail.com>
 */
@Service
public class SimpleEntityService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEntityService.class);
    @Autowired
    private SimpleEntityRepository repo;

    /**
     * Find a SimpleEntity object by id. Does not change a database
     *
     * @param id object id
     * @return object with specified id or null
     */
    @Transactional(readOnly = true)
    public SimpleEntity findById(Long id) {
        return repo.findById(id);
    }

    /**
     * Find all SimpleEntity objects. Does not change a database
     *
     * @return list of all SimpleEntity objects
     */
    @Transactional(readOnly = true)
    public List<SimpleEntity> findAll() {
        return repo.findAll();
    }

    /**
     * Insert or update a SimpleEntity object in database. Method modifies a
     * database
     *
     * @param entity object to insert or update
     * @return inserted or updated object
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SimpleEntity save(SimpleEntity entity) {
        if (entity == null) {
            throw new NullPointerException();
        }
        // SimpleEntity.id is read-only and method calls repo.create if id is null
        // or repo.update otherwise
        if (entity.getId() == null) {
            entity = repo.create(entity);
            logger.debug("{} created", entity);
        } else {
            entity = repo.update(entity);
            logger.debug("{} updated", entity);
        }
        return entity;
    }

    /**
     * Remove a SimpleEntity object from database. Method modifies a database
     *
     * @param entity object to remove
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void remove(SimpleEntity entity) {
        if (entity == null) {
            throw new NullPointerException();
        }
        repo.remove(entity);
        logger.debug("{} removed", entity);
    }
}
