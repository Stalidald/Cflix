package hu.elte.CataflixBackEnd.controllers;

import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity createBadRequest(Exception e) {
        return ResponseEntity.badRequest().header("error", e.getMessage()).build();
    }

    public ResponseEntity createBadRequest() {
        return ResponseEntity.badRequest().header("error").build();
    }
}
