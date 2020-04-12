package hu.elte.CataflixBackEnd.services.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NameNotFoundException extends Exception {
    public NameNotFoundException(String msg) {
        super(msg);
    }
}
