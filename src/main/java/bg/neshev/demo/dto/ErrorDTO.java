package bg.neshev.demo.dto;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String code;
    private String message;
}
