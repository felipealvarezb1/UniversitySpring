package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.GroupRegistration;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class StudentDto {

    private Long id;

    @NotEmpty(message = "Student first name is required")
    private String firstName;

    @NotEmpty(message = "Student last name is required")
    private String lastName;

    @NotEmpty(message = "Student email is required")
    @Email
    private String email;

    @NotEmpty(message = "Student username is required")
    private String username;

    @NotNull(message = "career ID is required")
    private Long careerId;

    private List<GroupRegistrationGeneralDto> registrations;
}
