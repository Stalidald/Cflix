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

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
