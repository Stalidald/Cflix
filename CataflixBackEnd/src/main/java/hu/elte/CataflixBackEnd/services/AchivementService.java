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

    @Autowired
    public AchivementService(AchivementRepository achivementRepository) {
        this.achivementRepository = achivementRepository;
    }

    @Override
    public Iterable<AchivementEntity> listAllData() {
        return achivementRepository.findAll();
    }

    @Override
    public AchivementEntity loadDataById(Long id) throws EntityNotFoundException {
        return achivementRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found!"));
    }

    @Override
    public AchivementEntity save(AchivementEntity achivementEntity) {
        return achivementRepository.save(achivementEntity);
    }


    public AchivementEntity findByName(String name) {
        return achivementRepository
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(name + " not found!"));
    }

}
