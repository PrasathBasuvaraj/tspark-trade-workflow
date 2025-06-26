package org.techspark.starter.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleEntityNotFoundException_ShouldReturnNotFoundResponse() {
        // Given
        EntityNotFoundException exception = new EntityNotFoundException("Stock not found");

        // When
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleEntityNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity Not Found", response.getBody().get("error"));
        assertEquals("Stock not found", response.getBody().get("message"));
    }

    @Test
    void handleValidationException_ShouldReturnBadRequestResponse() {
        // Given
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
//        when(exception.getBindingResult()).thenReturn(new DefaultHandlerExceptionResolver().resolveException(null, null, null, exception));

        BindingResult mockBindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(mockBindingResult);

        // When
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Disabled
    void handleConstraintViolationException_ShouldReturnBadRequestResponse() {
        Set<? extends ConstraintViolation<?>> violations = new HashSet<>();

//        ConstraintViolation<?> violation = null;
//        violations.add(violation);
        ConstraintViolationException exception = new ConstraintViolationException("Invalid input", violations);

        // When
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleConstraintViolationException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
    }

    @Test
    void handleGenericException_ShouldReturnInternalServerErrorResponse() {
        // Given
        Exception exception = new Exception("Something went wrong");

        // When
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleGenericException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody().get("error"));
        assertEquals("Something went wrong", response.getBody().get("message"));
    }

}