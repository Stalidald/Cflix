package hu.elte.CataflixBackEnd.repositories;

import java.util.Optional;

import hu.elte.CataflixBackEnd.models.ERole;
import hu.elte.CataflixBackEnd.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
