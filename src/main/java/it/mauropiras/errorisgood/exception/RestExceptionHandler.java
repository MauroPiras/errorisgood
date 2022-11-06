package it.mauropiras.errorisgood.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception e) {
        log.warn("error not handled", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Optional.of(
                                ExceptionMessage.builder()
                                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .message(e.getMessage())
                                        .build()
                        )
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handle(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        Optional.of(
                                ExceptionMessage.builder()
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message(e.getMessage())
                                        .build()
                        )
                );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handle(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        Optional.of(
                                ExceptionMessage.builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message(e.getMessage())
                                        .parameters(e.getParameters())
                                        .build()
                        )
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handle(AlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        Optional.of(
                                ExceptionMessage.builder()
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message(e.getMessage())
                                        .parameters(e.getParameters())
                                        .build()
                        )
                );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handle(AccessDeniedException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        Optional.of(
                                ExceptionMessage.builder()
                                        .code(HttpStatus.FORBIDDEN.value())
                                        .message(e.getMessage())
                                        .build()
                        )
                );
    }
}
