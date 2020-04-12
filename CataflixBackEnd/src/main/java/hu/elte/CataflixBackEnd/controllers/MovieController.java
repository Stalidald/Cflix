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

    @GetMapping("")
    public ResponseEntity<Iterable<MovieEntity>> getAllMovie() {
        return ResponseEntity.ok(movieService.listAllData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.loadDataById(id));
    }

    @GetMapping("/rating/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRating(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findRating(rate));
    }

    @GetMapping("/ratingIsHigher/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRatingAfter(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findAboveRating(rate));
    }

    @GetMapping("/ratingIsLower/{rate}")
    public ResponseEntity<Iterable<MovieEntity>> getMoviesByRatingBefore(@PathVariable int rate) {
        return ResponseEntity.ok(movieService.findBelowRating(rate));
    }

    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody MovieEntity movieEntity) {
        return ResponseEntity.ok(movieService.save(movieEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovieById(@PathVariable long id) {
        return ResponseEntity.ok(movieService.deleteData(movieService.loadDataById(id)));
    }

    @DeleteMapping("/deleteByTitle/{title}")
    public ResponseEntity deleteMovieByTitle(@PathVariable String title) {
        try {
            return ResponseEntity.ok(movieService.deleteData(movieService.loadDataByTitle(title)));
        } catch (EntityNotFoundException ex) {
            return createBadRequest(ex);
        }
    }


}
