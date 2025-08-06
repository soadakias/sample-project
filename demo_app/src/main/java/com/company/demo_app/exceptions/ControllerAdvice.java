package com.company.demo_app.exceptions;

import com.company.demo_app.model.errors.ApiError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;
import java.time.Instant;
import java.time.ZoneId;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        return createErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ServiceUnavailableException.class})
    public ResponseEntity<ApiError> handleServiceUnavailableException(ServiceUnavailableException ex) {
        return createErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {HttpClientErrorException.BadRequest.class})
    public ResponseEntity<ApiError> handleServiceUnavailableException(HttpClientErrorException.BadRequest ex) {
        return createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        return createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> createErrorResponse(Exception e, HttpStatus httpStatus) {
        ApiError apiError = ApiError.builder()
                .code(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .timestamp(Instant.now().atZone(ZoneId.of("UTC")).toString())
                .build();
        return new ResponseEntity<>(apiError, httpStatus);
    }
}
