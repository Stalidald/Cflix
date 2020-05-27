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
    private String category;

    @Column
    private int price;

    @Column
    @Lob
    private String imageURL;

    @Column
    private String videoURL;

    @Column
    @ManyToMany
    private List<MovieMembersEntity> relatedMovieMembers;

    @Column
    private int ageLimit;

    @Column
    private int releaseYear;

    @Column
    @Lob
    private String description;

    @Column
    private int rating;

    /**
     * Movie entity constructor
     * @param title of the movie
     * @param rating of the movie
     */
    public MovieEntity(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }
}
