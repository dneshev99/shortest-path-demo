package bg.neshev.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;
}
