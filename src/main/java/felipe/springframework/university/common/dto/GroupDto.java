package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.GroupRegistration;
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
public class GroupDto {

    private Long id;

    @NotEmpty(message = "Group name is required")
    private String name;

    @NotEmpty(message = "Group schedule is required")
    private String schedule;

    @NotEmpty(message = "Group room is required")
    private String room;

    @NotEmpty(message = "Group teacher is required")
    private String teacher;

    @NotNull(message = "subjectId is required")
    private Long subjectId;

    private List<GroupRegistration> registrations;
}
