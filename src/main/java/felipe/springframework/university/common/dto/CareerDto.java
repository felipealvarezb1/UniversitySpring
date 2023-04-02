package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.Subject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class CareerDto {

    private Long id;

    @NotEmpty(message = "Career name is required")
    @Size(max = 50, message = "career name is too long")
    private String name;

    @NotEmpty(message = "Career description is required")
    @Length(max = 500, message = "career description is too long")
    private String description;

    @NotNull(message = "Career credits are required")
    private int totalCredits;

    private Set<Subject> subjects;
}
