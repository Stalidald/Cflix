package hu.elte.CataflixBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hu.elte.CataflixBackEnd.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(	name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity extends BaseEntity {
    @Column
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column
    private String type;
    @Column
    private int ageLimit;

    @Column
    private String creditCardNumber;
    @Column
    private int cvc;
    @Column
    private String expireDate;
    @Column
    private double balance;

    @Column
    @ManyToMany
    public List<MovieEntity> ownedMovies;

    @Column
    @ManyToMany
    private List<MovieEntity> rentedMovies;

    @Column
    @ManyToMany
    public List<AchivementEntity> unlockedAchivements;

    @Column
    @ManyToMany
    private List<MovieEntity> wishList;

    @Column
    @ManyToMany
    @JsonBackReference
    private List<UserEntity> relatives;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    /**
     * Constructor of user entity with following parameters
     * @param userName
     * @param password
     * @param email
     * @param type
     * @param version
     */
    public UserEntity(String userName, String password,String email, String type, int version) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
        this.version = version;
    }

    /**
     * Constructor of user entity with following parameters
     * @param userName
     * @param password
     * @param email
     */
    public UserEntity(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * equals function of User entity, checking current object against another given User entity
     * @param o other user entity to check against
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(type, that.type);
    }

    /**
     * @return hash code of user entity
     * hash code calculated based on:
     *      name, email, userName, type
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, userName, type);
    }

    /**
     * @return roles of user
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Add roles to user
     * @param roles to add
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return users name
     */
    public String getName() {
        return name;
    }

    /**
     * Set users name to
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set users email to
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return users username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set users username to
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set users password to
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
