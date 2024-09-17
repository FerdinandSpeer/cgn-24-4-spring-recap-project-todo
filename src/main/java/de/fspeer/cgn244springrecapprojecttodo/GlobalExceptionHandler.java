package de.fspeer.cgn244springrecapprojecttodo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404 Not Found
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException() {
        // Einfacher Handler, der 404 zurückgibt, keine Rückgabe erforderlich
    }
}

