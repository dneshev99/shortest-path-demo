package bg.neshev.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathDTO {
    private Integer id;

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be greater than zero")
    private Integer distance;

    @Size(min = 2, max = 2, message = "Path must contain two bases")
    private List<BaseDTO> bases;
}
