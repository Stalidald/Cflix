package hu.elte.CataflixBackEnd.services.validator;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class AbstractDataValidator<ListType, DataType> {
    public static final boolean DATA_VALID = true;

    protected abstract Predicate<ListType> condition(DataType data);

    protected abstract boolean preCondition(DataType data);

    public boolean validate(Iterable<ListType> dataCollection, DataType data) {
        return preCondition(data) && ((ArrayList<ListType>) dataCollection)
                .stream()
                .noneMatch(condition(data));
    }

}
