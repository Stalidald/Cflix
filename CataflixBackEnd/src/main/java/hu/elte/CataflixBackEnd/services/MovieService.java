package hu.elte.CataflixBackEnd.services;

import hu.elte.CataflixBackEnd.entities.MovieEntity;
import hu.elte.CataflixBackEnd.entities.UserEntity;
import hu.elte.CataflixBackEnd.repositories.MovieRepository;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.EntityNotFoundException;

@Service
@SessionScope
public class MovieService extends BaseService<MovieEntity> {
    private MovieRepository movieRepository;

    /**
     * Constructor for MovieService
     */
    public MovieService() { }

    /**
     * Constructor for MovieService based on movie repository
     * @param movieRepository
     */
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * @return all data from movie repository
     */
    @Override
    public Iterable<MovieEntity> listAllData() {
        return movieRepository.findAll();
    }

    /**
     * Loads movie by ID
     * @param id
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    @Override
    public MovieEntity loadDataById(Long id) throws EntityNotFoundException {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    /**
     * Loads movie by title
     * @param title
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    public MovieEntity loadDataByTitle(String title) throws EntityNotFoundException {
        return movieRepository
                .findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException(title + " not found!"));
    }

    /**
     * Saves movie entity to repository
     * @param movieEntity to save
     * @return
     */
    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    /**
     * Finds movie by title
     * @param title
     * @return
     */
    public MovieEntity findByTitle(String title) {
        return movieRepository
                .findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException(title + " not found!"));
    }

    /**
     * Finds movie by rating
     * @param rating
     * @return movies
     */
    public Iterable<MovieEntity> findRating(int rating) {
        return movieRepository.findAllByRating(rating);
    }

    /**
     * Finds movies above rating
     * @param rating
     * @return movies
     */
    public Iterable<MovieEntity> findAboveRating(int rating) {
        return movieRepository.findAllByRatingAfter(rating);
    }

    /**
     * Finds movies below rating
     * @param rating
     * @return movies
     */
    public Iterable<MovieEntity> findBelowRating(int rating) {
        return movieRepository.findAllByRatingBefore(rating);
    }



}
