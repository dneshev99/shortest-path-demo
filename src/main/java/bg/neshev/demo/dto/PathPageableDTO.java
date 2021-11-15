package bg.neshev.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathPageableDTO extends PageableDTO {
    @Positive(message = "Distance must be grater than zero")
    private Integer distance;
}
