package hu.elte.CataflixBackEnd.services;

import hu.elte.CataflixBackEnd.entities.BaseEntity;
import hu.elte.CataflixBackEnd.services.exceptions.EntityCannotBeChangedException;
import hu.elte.CataflixBackEnd.services.exceptions.EntityInactiveException;
import hu.elte.CataflixBackEnd.services.validator.AbstractDataValidator;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class BaseService<Data extends BaseEntity> {

    protected AbstractDataValidator<Data, Data> validator;

    public abstract Iterable<Data> listAllData();

    public abstract Data loadDataById(Long id) throws EntityNotFoundException;

    public abstract Data save(Data data);

    private boolean dataValid(Data data) {
        return validator.validate(listAllData(), data);
    }


    protected void setModificationMetaData(BaseEntity entity) {
        entity.setModified(Calendar.getInstance().getTime());
        entity.setModifiedBy(getCurrentUser());
    }

    protected void setCreationMetaData(BaseEntity entity) {
        entity.setCreatedBy(getCurrentUser());
        entity.setCreated(Calendar.getInstance().getTime());
    }

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Data addData(Data data) throws EntityExistsException {
        if (!dataValid(data)) {
            throw new EntityExistsException("SSS-1: Source data(" + data.getClass().toString() + ", " + data.getId() + ") exists)");
        }
        setCreationMetaData(data);
        return save(data);
    }

    public Data updateData(Data data) throws EntityNotFoundException, EntityCannotBeChangedException {
        Data dataToUpdate = loadDataById(data.getId());
        if (!dataValid(data)) {
            throw new EntityCannotBeChangedException("SSS-1:Source data(" + data.getClass().toString() + ", " + data.getId() + ") cannot be changed!");
        }

        setModificationMetaData(data);
        data.setVersion(dataToUpdate.getVersion());
        data.setCreated(dataToUpdate.getCreated());
        data.setCreatedBy(dataToUpdate.getCreatedBy());

        return save(data);
    }

    public Data deleteData(Data data) throws EntityNotFoundException {
        Data dataToDelete = loadDataById(data.getId());
        setModificationMetaData(dataToDelete);

        return save(dataToDelete);
    }

}
