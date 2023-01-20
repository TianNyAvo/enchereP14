package s5.cloud.enchere.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerUtil {

    public static ResponseEntity<SuccessResponse> returnSuccess(Object obj, HttpStatus status) {
        return new ResponseEntity<>(new SuccessResponse(obj), status);
    }

    public static ResponseEntity<ErrorDisplay> returnError(Exception obj, HttpStatus status) {
        return new ResponseEntity<>(new ErrorDisplay(status, obj), status);
    }

}
