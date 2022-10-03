package dev.koicreek.springshipwreck;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    ResponseEntity<ErrorPayload> handleFlightNotFound(FlightNotFoundException e, WebRequest req) {

        ErrorPayload body = new ErrorPayload(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getMessage(),
                req.getDescription(false)
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        // Just like a POJO, a Map is also converted to a JSON key-value structure
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("exception", ex.getClass());
        return new ResponseEntity<>(body, headers, status);
    }
}
