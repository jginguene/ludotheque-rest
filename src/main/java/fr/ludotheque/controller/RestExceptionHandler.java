package fr.ludotheque.controller;

import fr.ludotheque.controller.exception.GameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {GameNotFoundException.class})
    public ResponseEntity gameNotFound(GameNotFoundException ex, WebRequest request) {
        log.debug("handling GameNotFoundException...");
        return notFound().build();
    }


}
