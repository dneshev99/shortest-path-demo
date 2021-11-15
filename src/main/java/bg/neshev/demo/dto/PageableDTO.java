package bg.neshev.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
public abstract class PageableDTO {
    @PositiveOrZero(message = "Page number must be a positive number or zero")
    protected Integer pageNum = 0;

    @Positive(message = "Page size must be a positive number")
    protected Integer pageSize = 10;

    protected String    sortBy  = "id";
    protected Boolean   isAsc   = false;
}
