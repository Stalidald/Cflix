package hu.elte.CataflixBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "movie_members")
public class MovieMembersEntity extends BaseEntity{
    @Column
    private String name;

    @Column
    private String role;

    /**
     * Constructor of movie member entity
     * @param name of movie member
     * @param role of movie member
     */

}
