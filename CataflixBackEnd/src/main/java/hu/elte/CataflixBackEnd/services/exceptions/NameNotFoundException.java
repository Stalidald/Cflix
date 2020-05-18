package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NameNotFoundException extends Exception {
    /** Creates NameNotFoundException based on message String
     * @param msg
     */
    public NameNotFoundException(String msg) {
        super(msg);
    }
}
