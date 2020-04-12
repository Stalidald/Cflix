package hu.elte.CataflixBackEnd.controllers;

import hu.elte.CataflixBackEnd.entities.MovieMembersEntity;
import hu.elte.CataflixBackEnd.services.MovieMemberService;
import hu.elte.CataflixBackEnd.services.exceptions.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/moviemember")
public class MovieMemberController extends BaseController {

    @Autowired
    MovieMemberService movieMemberService;

    @GetMapping("")
    public ResponseEntity<Iterable<MovieMembersEntity>> listAllMovieMember() {
        return ResponseEntity.ok(movieMemberService.listAllData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieMembersEntity> getMovieMemberById(@PathVariable long id) {
        return ResponseEntity.ok(movieMemberService.loadDataById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MovieMembersEntity> getMovieMemberByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(movieMemberService.loadMovieMemberByName(name));
        } catch (NameNotFoundException e) {
            return createBadRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieMemberById(@PathVariable long id) {
        return ResponseEntity
                .ok(movieMemberService.deleteData(movieMemberService.loadDataById(id)));
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteMovieByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(movieMemberService.deleteData(movieMemberService.loadMovieMemberByName(name)));
        } catch (NameNotFoundException ex) {
            return createBadRequest(ex);
        }
    }
}
