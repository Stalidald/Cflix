package hu.elte.CataflixBackEnd.repositories;

import hu.elte.CataflixBackEnd.entities.AchivementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchivementRepository extends CrudRepository<AchivementEntity, Long> {

    Optional<AchivementEntity> findByName(String name);
}
