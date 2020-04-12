package hu.elte.CataflixBackEnd.controllers;

import hu.elte.CataflixBackEnd.entities.AchivementEntity;
import hu.elte.CataflixBackEnd.services.AchivementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/achivement")
public class AchivementController extends BaseController {

    @Autowired
    AchivementService achivementService;

    @GetMapping("")
    public ResponseEntity<Iterable<AchivementEntity>> listAllAchivement() {
        return ResponseEntity.ok(achivementService.listAllData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchivementEntity> getAchivementById(@PathVariable long id) {
        return ResponseEntity.ok(achivementService.loadDataById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AchivementEntity> getAchivementByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(achivementService.findByName(name));
        } catch (EntityNotFoundException e) {
            return createBadRequest(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAchivementById(@PathVariable long id) {
        return ResponseEntity
                .ok(achivementService.deleteData(achivementService.loadDataById(id)));
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteAchivementByName(@PathVariable String name) {
        return ResponseEntity.ok(achivementService.deleteData(achivementService.findByName(name)));
    }
}
