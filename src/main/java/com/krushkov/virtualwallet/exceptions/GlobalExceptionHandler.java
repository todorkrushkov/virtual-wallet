package com.krushkov.virtualwallet.exceptions;

import com.krushkov.virtualwallet.models.dtos.responses.api.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDenied(
            AccessDeniedException e,
            HttpServletRequest request
    ) {
        return error(HttpStatus.FORBIDDEN, request, e.getMessage());
    }

    @ExceptionHandler(EntityDuplicateException.class)
    public ResponseEntity handleDuplicate(
            EntityDuplicateException e,
            HttpServletRequest request
    ) {
        return error(HttpStatus.CONFLICT, request, e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFound(
            EntityNotFoundException e,
            HttpServletRequest request
    ) {
        return error(HttpStatus.NOT_FOUND, request, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidation(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiErrorResponse.validationError(
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        request.getRequestURI(),
                        "Validation failed.",
                        errors
                ));
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity handleInvalidOperation(
            InvalidOperationException e,
            HttpServletRequest request
    ) {
        return error(HttpStatus.BAD_REQUEST, request, e.getMessage());
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity handleBadCredentials(HttpServletRequest request) {
//        return error(HttpStatus.UNAUTHORIZED, request, "Wrong username or password.");
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handleWrongUri(HttpServletRequest request) {
        return error(HttpStatus.NOT_FOUND, request, "Endpoint not found.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnexpected(HttpServletRequest request) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, request, "Unexpected server error.");
    }

    private ResponseEntity error(HttpStatus status, HttpServletRequest request, String message) {
        return ResponseEntity
                .status(status)
                .body(ApiErrorResponse.error(status.value(), request.getRequestURI(), message));
    }
}
