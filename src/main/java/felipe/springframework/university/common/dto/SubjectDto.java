package felipe.springframework.university.common.dto;

import felipe.springframework.university.domain.model.Group;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class SubjectDto {

    private Long id;

    @NotEmpty(message = "Subject name is required")
    private String name;

    @NotNull(message = "Subject credits is required")
    private int  credits;

    @NotNull(message = "Subject start date is required")
    private Date startDate;

    @NotNull(message = "Subject end date is required")
    private Date endDate;

    @NotNull(message = "career ID is required")
    private Long careerId;

    private List<Group> groups;

}
