package com.postech.challenge_01.controllers.handlers;

import com.postech.challenge_01.dtos.exceptions.ResourceNotFoundDTO;
import com.postech.challenge_01.dtos.exceptions.ValidationErrorDTO;
import com.postech.challenge_01.services.exceptions.ResourceNotFoundException;
import com.postech.challenge_01.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException error) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(error.getMessage(), status.value()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerUnauthorizedException(UnauthorizedException error) {
        var status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(error.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var errorField : error.getBindingResult().getFieldErrors()) {
            errors.add(errorField.getField() + ": " + errorField.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }
}
