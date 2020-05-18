package hu.elte.CataflixBackEnd.controllers;

import org.springframework.http.ResponseEntity;

public class BaseController {

    /**
     * Creates a bad request in case of error
     * @param e exception value
     * @return the thrown request
     */
    public ResponseEntity createBadRequest(Exception e) {
        return ResponseEntity.badRequest().header("error", e.getMessage()).build();
    }

    /**
     * Creates a bad request in case of error
     * @return the thrown request
     */
    public ResponseEntity createBadRequest() {
        return ResponseEntity.badRequest().header("error").build();
    }
}
