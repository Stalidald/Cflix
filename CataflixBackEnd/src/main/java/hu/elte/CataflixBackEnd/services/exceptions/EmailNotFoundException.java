package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailNotFoundException extends Exception {
    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
