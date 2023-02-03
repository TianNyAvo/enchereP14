package s5.cloud.enchere.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDisplay {

    private Integer code;
    private String message;

    public ErrorDisplay(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }

    public ErrorDisplay(HttpStatus code, Exception e) {
        this.code = code.value();
        this.message = e.getMessage();
    }

    public static ResponseEntity<?> returnError (HttpStatus code, String message) {
        return new ResponseEntity<>(new ErrorDisplay(code, message), code);
    }

    public static ResponseEntity<?> returnError (HttpStatus code, Exception e) {
        return returnError(code, e.getMessage());
    }

}
