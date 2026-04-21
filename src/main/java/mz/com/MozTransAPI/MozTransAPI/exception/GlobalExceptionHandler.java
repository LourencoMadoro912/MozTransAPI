package mz.com.MozTransAPI.MozTransAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<?> handlerNotFound(ResouceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(

                "error","nao encontrado",
                "message",ex.getMessage()
        ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequest(BadRequestException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error","nao encontrado",
                "message", ex.getMessage()
        ));
    }

  @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error","erro interno do servidor"
        ));
    }

}
