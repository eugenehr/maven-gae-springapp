package springapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springapp.domain.SimpleEntity;
import springapp.service.SimpleEntityService;

/**
 * Web layer for SimpleEntity. REST controller
 *
 * @author Eugene Khrustalev <eugene.khrustalev@gmail.com>
 */
@Controller
@RequestMapping(value = "/SimpleEntity", produces = "application/json")
public class SimpleEntityController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEntityController.class);
    @Autowired
    private SimpleEntityService service;

    /**
     * Find object by id
     *
     * @param id object id. Passed as path param
     * @return objects with specified id or null
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SimpleEntity findById(@PathVariable Long id) {
        SimpleEntity entity = service.findById(id);
        if (entity == null) {
            logger.debug("SimpleEntity with id={} was not found", id);
        } else {
            logger.debug("{} was found", entity);
        }
        return entity;
    }

    /**
     * Find all objects
     *
     * @return list of all known objects
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SimpleEntity> findAll() {
        return service.findAll();
    }

    /**
     * Create new object
     *
     * @param entity object to created
     * @return created object
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public SimpleEntity create(@Valid SimpleEntity entity) {
        entity = service.save(entity);
        logger.debug("{} created", entity);
        return entity;
    }

    /**
     * Update object
     *
     * @param entity object to update
     * @return updated object
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public SimpleEntity update(@Valid SimpleEntity entity) {
        entity = service.save(entity);
        logger.debug("{} update", entity);
        return entity;
    }

    /**
     * Remove object
     *
     * @param entity object to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        SimpleEntity entity = service.findById(id);
        if (entity != null) {
            service.remove(entity);
            logger.debug("{} removed", entity);
        } else {
            logger.debug("SimpleEntity with id={} was not found", id);
        }
    }
}
