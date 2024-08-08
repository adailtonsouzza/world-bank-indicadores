package br.com.worldbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodigoPaisAusenteException extends RuntimeException {
    public CodigoPaisAusenteException(String message) {
        super(message);
    }
}
