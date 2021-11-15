package bg.neshev.demo.validation.advice;

import bg.neshev.demo.dto.ErrorDTO;
import bg.neshev.demo.exception.ApiErrorException;
import bg.neshev.demo.mapper.ErrorMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(ApiErrorException.class)
    protected ResponseEntity<ErrorDTO> handleApiError(ApiErrorException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(ErrorMapper.toDto(ex.getErrorCode()));
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<List<String>> handleValidationErrors(BindException ex) {
        return ResponseEntity.badRequest().body(extractFieldErrors(ex.getAllErrors()));
    }

    private List<String> extractFieldErrors(List<ObjectError> errors) {
        return errors.stream()
                     .map(DefaultMessageSourceResolvable::getDefaultMessage)
                     .collect(Collectors.toList());
    }

}
