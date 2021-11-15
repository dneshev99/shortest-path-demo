package bg.neshev.demo.mapper;

import bg.neshev.demo.dto.ErrorDTO;
import bg.neshev.demo.validation.enums.ApiErrorCode;

public class ErrorMapper {
    private ErrorMapper() { }

    public static ErrorDTO toDto(ApiErrorCode errorCode) {
        return ErrorDTO.builder()
                       .code(errorCode.getCode())
                       .message(errorCode.getMessage())
                       .build();
    }

}
