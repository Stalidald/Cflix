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

    /**
     * @return all data
     */
    public abstract Iterable<Data> listAllData();

    /**
     * Load data based on id
     * @param id
     * @return loaded data
     * @throws EntityNotFoundException
     */
    public abstract Data loadDataById(Long id) throws EntityNotFoundException;

    /**
     * save data
     * @param data
     * @return
     */
    public abstract Data save(Data data);

    /**
     * Validates data
     * @param data to validate
     * @return data validity
     */
    private boolean dataValid(Data data) {
        return validator.validate(listAllData(), data);
    }


    /**
     * Set modification metadata of entity
     * @param entity
     */
    protected void setModificationMetaData(BaseEntity entity) {
        entity.setModified(Calendar.getInstance().getTime());
        entity.setModifiedBy(getCurrentUser());
    }

    /**
     * Set creation metadata of entity
     * @param entity
     */
    protected void setCreationMetaData(BaseEntity entity) {
        entity.setCreatedBy(getCurrentUser());
        entity.setCreated(Calendar.getInstance().getTime());
    }

    /**
     * @return current user
     */
    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Add data to database
     * @param data to add
     * @return
     * @throws EntityExistsException
     */
    public Data addData(Data data) throws EntityExistsException {
        if (!dataValid(data)) {
            throw new EntityExistsException("SSS-1: Source data(" + data.getClass().toString() + ", " + data.getId() + ") exists)");
        }
        setCreationMetaData(data);
        return save(data);
    }

    /**
     * Update data in database
     * @param data to update
     * @return
     * @throws EntityExistsException
     */
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

    /**
     * Delete data from database
     * @param data to delete
     * @return
     * @throws EntityExistsException
     */
    public Data deleteData(Data data) throws EntityNotFoundException {
        Data dataToDelete = loadDataById(data.getId());
        setModificationMetaData(dataToDelete);

        return save(dataToDelete);
    }

}
