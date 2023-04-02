package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.GroupRegistration;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class TestDto {

    private Long id;

    @NotEmpty(message = "test name is required")
    private String name;

    @NotNull(message = "test percentage is required")
    private int percentage;

    @NotNull(message = "test score is required")
    private double score;

    private Long registrationId;
}
