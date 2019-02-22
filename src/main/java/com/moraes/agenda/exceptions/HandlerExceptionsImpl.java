package com.moraes.agenda.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionsImpl {

    @ExceptionHandler(NotFoundItemException.class)
    public ResponseEntity<Void> notFound(NotFoundItemException e){
        return ResponseEntity.notFound().build();
    }
}
