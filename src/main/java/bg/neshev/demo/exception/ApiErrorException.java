package bg.neshev.demo.exception;

import bg.neshev.demo.validation.enums.ApiErrorCode;
import lombok.Getter;

@Getter
public class ApiErrorException extends RuntimeException {
    private final ApiErrorCode errorCode;

    public ApiErrorException(ApiErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
