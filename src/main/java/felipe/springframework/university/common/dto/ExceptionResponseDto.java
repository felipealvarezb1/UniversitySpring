package felipe.springframework.university.common.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class ExceptionResponseDto {

    private LocalDateTime timestamp;

    private String error;

    private Map<String, ?> message;
}
