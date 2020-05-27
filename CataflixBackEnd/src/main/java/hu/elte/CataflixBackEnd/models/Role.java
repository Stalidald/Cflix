package hu.elte.CataflixBackEnd.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    /**
     * Constructor of Role
     */
    public Role() {

    }

    /**
     * Constructor of role using
     * @param name
     */
    public Role(ERole name) {
        this.name = name;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set ID to
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public ERole getName() {
        return name;
    }

    /**
     * Set name to
     * @param name
     */
    public void setName(ERole name) {
        this.name = name;
    }
}
