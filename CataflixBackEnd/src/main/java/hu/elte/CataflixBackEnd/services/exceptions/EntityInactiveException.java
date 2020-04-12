package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityInactiveException extends Exception {

    public EntityInactiveException(String message) {
        super(message);
    }
}
