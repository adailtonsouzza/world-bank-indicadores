package br.com.worldbank.exceptions.handle;

import br.com.worldbank.exceptions.CodigoPaisAusenteException;
import br.com.worldbank.exceptions.ExceptionResponse;
import br.com.worldbank.exceptions.PaginaNaoEncontradaException;
import br.com.worldbank.exceptions.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ManipuladorGlobalExcecoes  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TimeoutException.class)
    public final ResponseEntity<ExceptionResponse> handleTimeoutException(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                "Tempo de resposta excedido ao chamar API do Banco Mundial / Verificar o código do país",
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(CodigoPaisAusenteException.class)
    public final ResponseEntity<ExceptionResponse> handleCodigoPaisAusenteException(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                "Código do país ausente",
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PaginaNaoEncontradaException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}