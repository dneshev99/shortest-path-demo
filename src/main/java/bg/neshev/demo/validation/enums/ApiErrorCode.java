package bg.neshev.demo.validation.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiErrorCode {
    BASE_NOT_FOUND              (HttpStatus.NOT_FOUND,   "DEMO-1", "No base entity was found for given id"),
    PATH_NOT_FOUND              (HttpStatus.NOT_FOUND,   "DEMO-2", "No path entity was found for given id"),
    SAME_BASES_PATH_CREATION    (HttpStatus.BAD_REQUEST, "DEMO-3", "Path contains duplicate bases"),
    SAME_BASES                  (HttpStatus.BAD_REQUEST, "DEMO-4", "From base and to base need to be different");

    private final String        code;
    private final String        message;
    private final HttpStatus    httpStatus;

    ApiErrorCode(HttpStatus httpStatus, String code, String message) {
        this.code       = code;
        this.message    = message;
        this.httpStatus = httpStatus;
    }

}
