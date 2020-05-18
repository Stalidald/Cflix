package hu.elte.CataflixBackEnd.services;

import hu.elte.CataflixBackEnd.entities.AchivementEntity;
import hu.elte.CataflixBackEnd.repositories.AchivementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.EntityNotFoundException;

@Service
@SessionScope
public class AchivementService extends BaseService<AchivementEntity> {
    AchivementRepository achivementRepository;

    /**
     * Constructor for AchivementService based on achivementRepository
     * @param achivementRepository
     */
    @Autowired
    public AchivementService(AchivementRepository achivementRepository) {
        this.achivementRepository = achivementRepository;
    }

    /**
     * @return all data from achievement repository
     */
    @Override
    public Iterable<AchivementEntity> listAllData() {
        return achivementRepository.findAll();
    }

    /**
     * Loads achievement by ID
     * @param id
     * @return loaded data
     * @throws EntityNotFoundException if data by ID does not exist
     */
    @Override
    public AchivementEntity loadDataById(Long id) throws EntityNotFoundException {
        return achivementRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    /**
     * Saves achievement entity to repository
     * @param achivementEntity to save
     * @return
     */
    @Override
    public AchivementEntity save(AchivementEntity achivementEntity) {
        return achivementRepository.save(achivementEntity);
    }

    /**
     * Finds achievement by name
     * @param name
     * @return movies
     */
    public AchivementEntity findByName(String name) {
        return achivementRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(name + " not found!"));
    }

}
