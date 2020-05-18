package hu.elte.CataflixBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    public static final int ENTITY_ACTIVE = 0;
    public static final int ENTITY_INACTIVE = 1;
    public static final int ENTITY_CANNOT_BE_MODIFIED = 2;

    public static final int BASE_VERSION = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    protected long id;

    @Version
    protected int version;

    @Column
    @JsonIgnore
    protected String createdBy;

    @Column
    @JsonIgnore
    protected String modifiedBy;

    @Column
    @JsonIgnore
    protected Date created;

    @Column
    @JsonIgnore
    protected Date modified;

    /**
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * @return version
     */
    public int getVersion() {
        return version;
    }

    /** set
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /** set
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** set
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /** set
     * @param modifiedBy
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return
     */
    public Date getCreated() {
        return created;
    }

    /** set
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return
     */
    public Date getModified() {
        return modified;
    }

    /** set
     * @param modified
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }
}
