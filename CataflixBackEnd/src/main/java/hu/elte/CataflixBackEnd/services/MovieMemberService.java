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

    public MovieMemberService() {

    }

    @Autowired
    public MovieMemberService(MovieMembersRepository movieMembersRepository) {
        this.movieMembersRepository = movieMembersRepository;
    }

    @Override
    public Iterable<MovieMembersEntity> listAllData() {
        return movieMembersRepository.findAll();
    }

    @Override
    public MovieMembersEntity loadDataById(Long id) throws EntityNotFoundException {
        return movieMembersRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    @Override
    public MovieMembersEntity save(MovieMembersEntity movieMembersEntity) {
        return movieMembersRepository.save(movieMembersEntity);
    }

    public MovieMembersEntity loadMovieMemberByName(String name) throws NameNotFoundException {
        return movieMembersRepository
                .findByName(name)
                .orElseThrow(() -> new NameNotFoundException(name + " not found!"));
    }

    public Iterable<MovieMembersEntity> loadByRole(String role) {
        return movieMembersRepository.findAllByRole(role);
    }

}
