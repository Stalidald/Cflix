package hu.elte.CataflixBackEnd.controllers;

import hu.elte.CataflixBackEnd.entities.UserEntity;
import hu.elte.CataflixBackEnd.services.UserService;
import hu.elte.CataflixBackEnd.services.exceptions.EmailNotFoundException;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    /**
     * @return all stored users
     */
    @GetMapping("")
    public ResponseEntity<Iterable<UserEntity>> listAllUser() {
        return ResponseEntity.ok(userService.listAllData());
    }

    /**
     * @return one user, selected by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.loadDataById(id));
    }

    /**
     *
     * @param newUser user to update
     * @param id the id of user
     * @return updated user, or null if user does not exits
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> putUser(@RequestBody UserEntity newUser,@PathVariable long id) {
        return ResponseEntity.ok(userService.updateUser(newUser,id));
    }

    /**
     *
     * @param newUser user to upgrade
     * @param id the id of user
     * @return upgraded user, or null if user does not exits
     */
    @PutMapping("/upgrade/{id}")
    public ResponseEntity<UserEntity> upgradeUser(@RequestBody UserEntity newUser,@PathVariable long id) {
        return ResponseEntity.ok(userService.upgradeUser(newUser,id));
    }

    /**
     * @return one user, selected by e-mail
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.loadUserByEmail(email));
        } catch (EmailNotFoundException e) {
            return createBadRequest(e);
        }

    }

    /**
     * @return one user, selected by name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.loadUserByName(name));
        } catch (NameNotFoundException e) {
            return createBadRequest(e);
        }
    }

    /**
     * Delete a single user selected by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteData(userService.loadDataById(id)));
    }

    /**
     * Delete a single user selected by name
     */
    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteMovieByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.deleteData(userService.loadUserByName(name)));
        } catch (NameNotFoundException ex) {
            return createBadRequest(ex);
        }
    }

}
