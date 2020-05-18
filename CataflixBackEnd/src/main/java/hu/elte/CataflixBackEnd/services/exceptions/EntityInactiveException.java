package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityInactiveException extends Exception {
    /** Creates EntityInactiveException based on message String
     * @param message
     */
    public EntityInactiveException(String message) {
        super(message);
    }
}
