package dev.koicreek.springshipwreck.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    ResponseEntity<ErrorPayloadCM> handleFlightNotFound(FlightNotFoundException e,
                                                        WebRequest webRequest,
                                                        HttpServletRequest httpRequest) {
        ErrorPayloadCM body = new ErrorPayloadCM(
                HttpStatus.NOT_FOUND,
                httpRequest.getServletPath()
        );

        body.setMessage(e.getMessage());
        // body.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest webRequest) {

        // Just like a POJO, a Map is also converted to a JSON key-value structure
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("exception", e.getClass());
        body.put("path", webRequest.getDescription(false));
        return new ResponseEntity<>(body, headers, status);
    }
}
