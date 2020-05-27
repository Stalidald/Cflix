package hu.elte.CataflixBackEnd.services;

import hu.elte.CataflixBackEnd.entities.MovieMembersEntity;
import hu.elte.CataflixBackEnd.repositories.MovieMembersRepository;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.EntityNotFoundException;

@Service
@SessionScope
public class MovieMemberService extends BaseService<MovieMembersEntity> {
    MovieMembersRepository movieMembersRepository;

    /**
     * Constructor for MovieMemberService
     */
    public MovieMemberService() {

    }

    /**
     * Constructor for MovieMemberService based on movie members repository
     * @param movieMembersRepository
     */
    @Autowired
    public MovieMemberService(MovieMembersRepository movieMembersRepository) {
        this.movieMembersRepository = movieMembersRepository;
    }

    /**
     * @return all data from movie members repository
     */
    @Override
    public Iterable<MovieMembersEntity> listAllData() {
        return movieMembersRepository.findAll();
    }

    /**
     * Loads movie member by ID
     * @param id
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    @Override
    public MovieMembersEntity loadDataById(Long id) throws EntityNotFoundException {
        return movieMembersRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    /**
     * Saves movie members entity to repository
     * @param movieMembersEntity to save
     * @return
     */
    @Override
    public MovieMembersEntity save(MovieMembersEntity movieMembersEntity) {
        return movieMembersRepository.save(movieMembersEntity);
    }

    /**
     * Loads movie member by name
     * @param name
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    public MovieMembersEntity loadMovieMemberByName(String name) throws NameNotFoundException {
        return movieMembersRepository
                .findByName(name)
                .orElseThrow(() -> new NameNotFoundException(name + " not found!"));
    }

    public Iterable<MovieMembersEntity> loadByRole(String role) {
        return movieMembersRepository.findAllByRole(role);
    }

}
