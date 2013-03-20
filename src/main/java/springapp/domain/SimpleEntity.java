package springapp.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * A Simple Entity class
 *
 * @author Eugene Khrustalev <eugene.khrustalev@gmail.com>
 */
@Entity
public class SimpleEntity implements Serializable {

    /**
     * Object identifier. Read-only property
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Object version. Read-only property
     */
    @Version
    private Long version;
    /**
     * Name
     */
    @NotNull
    private String name;

    public SimpleEntity() {
    }

    public SimpleEntity(String name) {
        this.name = name;
    }

    public SimpleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" + "id=" + id + ", version=" + version + ", name=" + name + '}';
    }
}
