package hu.elte.CataflixBackEnd.repositories;

import hu.elte.CataflixBackEnd.entities.MovieMembersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieMembersRepository extends CrudRepository<MovieMembersEntity, Long> {
    Optional<MovieMembersEntity> findByName(String name);

    Iterable<MovieMembersEntity> findAllByRole(String role);
}
