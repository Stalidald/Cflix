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

    @GetMapping("")
    public ResponseEntity<Iterable<UserEntity>> listAllUser() {
        return ResponseEntity.ok(userService.listAllData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.loadDataById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.loadUserByEmail(email));
        } catch (EmailNotFoundException e) {
            return createBadRequest(e);
        }

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.loadUserByName(name));
        } catch (NameNotFoundException e) {
            return createBadRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteData(userService.loadDataById(id)));
    }


    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteMovieByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.deleteData(userService.loadUserByName(name)));
        } catch (NameNotFoundException ex) {
            return createBadRequest(ex);
        }
    }

}
