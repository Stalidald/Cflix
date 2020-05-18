package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityCannotBeChangedException extends Exception {
    /** Creates EntityCannotBeChangedException based on message String
     * @param message
     */
    public EntityCannotBeChangedException(String message) {
        super(message);
    }
}
