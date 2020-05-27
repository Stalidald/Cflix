package hu.elte.CataflixBackEnd.services;

import hu.elte.CataflixBackEnd.entities.MovieEntity;
import hu.elte.CataflixBackEnd.entities.UserEntity;
import hu.elte.CataflixBackEnd.models.ERole;
import hu.elte.CataflixBackEnd.models.Role;
import hu.elte.CataflixBackEnd.repositories.RoleRepository;
import hu.elte.CataflixBackEnd.repositories.UserRepository;
import hu.elte.CataflixBackEnd.services.exceptions.EmailNotFoundException;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
@SessionScope
public class UserService extends BaseService<UserEntity> {
    @Autowired
    private AchivementService achivementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MovieService movieService;

    /**
     * Constructor of UserService instance
     */
    public UserService() {

    }

    /**
     * Constructor of UserService instance based on user repository
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return all data from user repository
     */
    @Override
    public Iterable<UserEntity> listAllData() {
        return userRepository.findAll();
    }

    /**
     * Loads data by ID
     * @param id
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    @Override
    public UserEntity loadDataById(Long id) throws EntityNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    /**
     * Updates user
     * @param newUser user to update
     * @param id the if of the user
     * @return updated user, or null, if user does not exist
     */
    public UserEntity updateUser(UserEntity newUser, Long id) {
        Optional<UserEntity> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()){
            newUser.setId(id);
                if(newUser.ownedMovies.size() == 1){
                    if(oldUser.get().ownedMovies.size() == 0){
                        newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(2)));
                    }
                }else{
                    if(newUser.ownedMovies.size() == 3){
                        if(oldUser.get().ownedMovies.size() == 2){
                            newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(3)));
                        }
                    }else{
                        if(newUser.ownedMovies.size() == 5){
                            if(oldUser.get().ownedMovies.size() == 4){
                                newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(4)));
                            }
                        }
                    }
                }


            UserEntity newSavedUser = userRepository.save(newUser);
            return newSavedUser;
        }else{
            return null;
        }
    }

    /**
     * Upgrade user roles
     * @param newUser user to upgrade
     * @param id the if of the user
     * @return upgraded user, or null, if user does not exist
     */
    public UserEntity upgradeUser(UserEntity newUser, Long id) {
        Optional<UserEntity> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()){
            newUser.setId(id);
            if(newUser.getRoles().size()<2){
                Set<Role> roles = new HashSet<>();

                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);

                Role premiumRole = roleRepository.findByName(ERole.ROLE_PREMIUM)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(premiumRole);

                newUser.setRoles(roles);
            }

            newUser.ownedMovies = new ArrayList<MovieEntity>();
            for(MovieEntity movie: movieService.listAllData()){
                newUser.ownedMovies.add(movie);
            }
            newUser.unlockedAchivements = new ArrayList<>();
            newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(1)));
            newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(2)));
            newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(3)));
            newUser.unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(4)));
            UserEntity newSavedUser = userRepository.save(newUser);
            return newSavedUser;

        }else{
            return null;
        }
    }

    /**
     * Saves user entity to repository
     * @param userEntity to save
     * @return
     */
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    /**
     * Loads user by email
     * @param email
     * @return
     * @throws EmailNotFoundException
     */
    public UserEntity loadUserByEmail(String email) throws EmailNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email + " not found!"));
    }

    /**
     * Loads user by name
     * @param name
     * @return
     * @throws NameNotFoundException
     */
    public UserEntity loadUserByName(String name) throws NameNotFoundException {
        return userRepository
                .findByName(name)
                .orElseThrow(() -> new NameNotFoundException(name + " not found!"));
    }
}
