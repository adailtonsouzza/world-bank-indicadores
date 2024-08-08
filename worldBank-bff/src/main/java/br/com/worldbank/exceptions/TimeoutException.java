package br.com.worldbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
        super(message);
    }
}
