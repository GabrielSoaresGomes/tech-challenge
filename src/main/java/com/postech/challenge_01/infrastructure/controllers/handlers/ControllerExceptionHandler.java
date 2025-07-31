package com.postech.challenge_01.infrastructure.controllers.handlers;

import com.postech.challenge_01.dtos.exceptions.ResourceNotFoundDTO;
import com.postech.challenge_01.dtos.exceptions.ValidationErrorDTO;
import com.postech.challenge_01.dtos.responses.ErrorResponseDTO;
import com.postech.challenge_01.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private ErrorResponseDTO buildError(HttpStatus status, String message) {
        return new ErrorResponseDTO(
                status.value(),
                status.getReasonPhrase(),
                message,
                Instant.now()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundDTO handlerResourceNotFoundException(ResourceNotFoundException error) {
        return new ResourceNotFoundDTO(error.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBusinessException(BusinessException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResourceNotFoundDTO handlerUnauthorizedException(UnauthorizedException error) {
        return new ResourceNotFoundDTO(error.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handlerMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var errorField : error.getBindingResult().getFieldErrors()) {
            errors.add(errorField.getField() + ": " + errorField.getDefaultMessage());
        }
        return new ValidationErrorDTO(errors, status.value());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleBadCredentials(BadCredentialsException ex) {
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleUsernameNotFound(UsernameNotFoundException ex) {
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
}
