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

    /**
     * @return all achievements available
     */
    @GetMapping("")
    public ResponseEntity<Iterable<AchivementEntity>> listAllAchivement() {
        return ResponseEntity.ok(achivementService.listAllData());
    }

    /**
     * @return one achievement by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<AchivementEntity> getAchivementById(@PathVariable long id) {
        return ResponseEntity.ok(achivementService.loadDataById(id));
    }

    /**
     * @return one achievement by name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<AchivementEntity> getAchivementByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(achivementService.findByName(name));
        } catch (EntityNotFoundException e) {
            return createBadRequest(e);
        }
    }

    /**
     * Delete one achievement by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAchivementById(@PathVariable long id) {
        return ResponseEntity
                .ok(achivementService.deleteData(achivementService.loadDataById(id)));
    }

    /**
     * Delete one achievement by name
     */
    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity deleteAchivementByName(@PathVariable String name) {
        return ResponseEntity.ok(achivementService.deleteData(achivementService.findByName(name)));
    }
}
