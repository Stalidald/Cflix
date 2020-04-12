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
@Table(name = "movie")
public class MovieEntity extends BaseEntity {
    @Column
    private String title;

    @Column
    @ManyToMany(targetEntity = MovieMembersEntity.class, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<MovieMembersEntity> relatedMovieMembers;

    @Column
    private int ageLimit;

    @Column
    private int releaseYear;

    @Column
    private String description;

    @Column
    private int rating;

    public MovieEntity(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }
}
