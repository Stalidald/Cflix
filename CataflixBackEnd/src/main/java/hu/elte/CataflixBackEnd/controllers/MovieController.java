package hu.elte.CataflixBackEnd.controllers;

import hu.elte.CataflixBackEnd.entities.MovieEntity;
import hu.elte.CataflixBackEnd.services.MovieService;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("movies")
public class MovieController extends BaseController {
    @Autowired
    MovieService movieService;

    /**
     * @return the list of every movie
     */
    @GetMapping("")
    public ResponseEntity<Iterable<MovieEntity>> getAllMovie() {
        return ResponseEntity.ok(movieService.listAllData());
    }

    /**
     * @return one movie, selected by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.loadDataById(id));
    }

    /**
     * @return all movies with the selected rating
     */
    @GetMapping("/rating/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRating(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findRating(rate));
    }

    /**
     * @return all movies with rating higher than selected
     */
    @GetMapping("/ratingIsHigher/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRatingAfter(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findAboveRating(rate));
    }

    /**
     * @return all movies with rating lower than selected
     */
    @GetMapping("/ratingIsLower/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRatingBefore(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findBelowRating(rate));
    }

    /**
     * Add new movie
     */
    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody MovieEntity movieEntity) {
        return ResponseEntity.ok(movieService.save(movieEntity));
    }
    /**
     * Delete one movie selected by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.deleteData(movieService.loadDataById(id)));
    }

    /**
     * Delete one movie selected by Title
     */
    @DeleteMapping("/deleteByTitle/{title}")
    public ResponseEntity deleteMovieByTitle(@PathVariable String title) {
        try {
            return ResponseEntity.ok(movieService.deleteData(movieService.loadDataByTitle(title)));
        } catch (EntityNotFoundException ex) {
            return createBadRequest(ex);
        }
    }


}
