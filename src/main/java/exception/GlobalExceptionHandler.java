package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException exception, WebRequest webRequest){
        ErroDetails erroDetails=new ErroDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(erroDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> resourceNotFound(Exception exception, WebRequest webRequest){
        ErroDetails erroDetails=new ErroDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(erroDetails, HttpStatus.NOT_FOUND);
    }
}
