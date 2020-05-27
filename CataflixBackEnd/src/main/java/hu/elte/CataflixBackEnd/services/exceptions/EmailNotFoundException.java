package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailNotFoundException extends Exception {
    /** Creates EmailNotFoundException based on message String
     * @param msg
     */
    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
