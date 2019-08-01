package com.moraes.agenda.exceptions;

import com.moraes.agenda.dtos.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HandlerExceptionsImpl {

    @ExceptionHandler(NotFoundItemException.class)
    public ResponseEntity<Void> notFound(NotFoundItemException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(RuntimeException e){
        log.error("Ocorreu um erro inesperado", e);
        return new ResponseEntity(new ErrorResponse("Ocorreu um erro ao processar a requisição",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(TokenInvalidException e){
        return new ResponseEntity(new ErrorResponse(e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                null),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(BadCredentialsException e){
        return new ResponseEntity(new ErrorResponse(e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                null),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(MethodArgumentNotValidException e){

        ErrorResponse response = new ErrorResponse();
        response.setMessage("Ocorreu um erro na validação da requisição");
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());

        for(FieldError error :e.getBindingResult().getFieldErrors()){
            response.addErro(error.getDefaultMessage());
        }

        return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
