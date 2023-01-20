package s5.cloud.enchere;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.exception.LoginException;
import s5.cloud.enchere.util.ErrorDisplay;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> customError(CustomException e) {
        return new ResponseEntity<>( new ErrorDisplay(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> customError2(CustomException e) {
        e.printStackTrace();
        return new ResponseEntity<>( new ErrorDisplay(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
    }

}
