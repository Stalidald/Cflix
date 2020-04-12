package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityCannotBeChangedException extends Exception {

    public EntityCannotBeChangedException(String message) {
        super(message);
    }
}
