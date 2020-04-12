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

    public MovieService() { }

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Iterable<MovieEntity> listAllData() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity loadDataById(Long id) throws EntityNotFoundException {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    public MovieEntity loadDataByTitle(String title) throws EntityNotFoundException {
        return movieRepository
                .findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException(title + " not found!"));
    }

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    public MovieEntity findByTitle(String title) {
        return movieRepository
                .findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException(title + " not found!"));
    }

    public Iterable<MovieEntity> findRating(int rating) {
        return movieRepository.findAllByRating(rating);
    }

    public Iterable<MovieEntity> findAboveRating(int rating) {
        return movieRepository.findAllByRatingAfter(rating);
    }

    public Iterable<MovieEntity> findBelowRating(int rating) {
        return movieRepository.findAllByRatingBefore(rating);
    }



}
